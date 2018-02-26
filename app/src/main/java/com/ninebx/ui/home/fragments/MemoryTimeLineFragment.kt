package com.ninebx.ui.home.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_CANCELED
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Contacts.People
import android.provider.ContactsContract
import android.provider.MediaStore
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.ActionClickListener
import com.ninebx.ui.base.kotlin.*
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.account.memoryView.MemoryView
import com.ninebx.utility.AWSFileTransferHelper
import com.ninebx.ui.home.calendar.events.AttachmentRecyclerViewAdapter
import com.ninebx.ui.home.calendar.events.ImageViewDialog
import com.ninebx.ui.home.customView.CustomBottomSheetProfileDialogFragment
import com.ninebx.utility.*
import io.realm.Realm
import kotlinx.android.synthetic.main.add_memory.*
import java.io.File
import java.util.*


/***
 * Created by TechnoBlogger on 24/01/18.
 */
class MemoryTimeLineFragment : FragmentBackHelper(), AWSFileTransferHelper.FileOperationsCompletionListener, CustomBottomSheetProfileDialogFragment.BottomSheetSelectedListener {

    override fun onSuccess(outputFile: File?) {
//        if (outputFile != null && ivAttachment != null)
//            Glide.with(context).asBitmap().load(outputFile).into(ivAttachment)
    }

    var strToolbarTitle = "Add Memory"
    var strMemoryTitle = ""
    var strDate = ""
    var strLocation = ""
    var strContacts = ""
    var strNotes = ""
    private lateinit var memoryTimeLine: MemoryTimeline
    val PICK_CONTACT = 1
    var PLACE_AUTOCOMPLETE_REQUEST_CODE: Int = 2


    private lateinit var memberView: MemoryView
    private lateinit var mAWSFileTransferHelper: AWSFileTransferHelper

    var addOrEdit = ""
    val preferences = NineBxPreferences()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_memory, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MemoryView) {
            memberView = context
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        memoryTimeLine = arguments!!.getParcelable(Constants.MEMORY_TIMELINE)
        mAWSFileTransferHelper = AWSFileTransferHelper(context!!)

        addOrEdit = arguments!!.getString("Operation")

        if (addOrEdit == "Add") {
            enableAdding()
        }

        bottomSheetDialogFragment = CustomBottomSheetProfileDialogFragment()
        bottomSheetDialogFragment.setBottomSheetSelectionListener(this)

        ivBackFromMemory.setOnClickListener {
            activity!!.finish()
        }

        txtDate.setOnClickListener {
            getDateFromPicker(this.context!!, Calendar.getInstance(), object : DateTimeSelectionListener {
                override fun onDateTimeSelected(selectedDate: Calendar) {
                    txtDate.text = (parseDateForMemory(selectedDate.time))
                }
            })
        }

        txtSaveMemory.setOnClickListener {
            checkValidation()
        }

        edtLocation.setOnTouchListener(View.OnTouchListener { _, event ->
            val DRAWABLE_RIGHT = 2

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= view.findViewById<EditText>(R.id.edtLocation).right - view.findViewById<EditText>(R.id.edtLocation).compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                    val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(activity)
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
                    return@OnTouchListener true
                }
            }
            false
        })

        edtContacts.setOnTouchListener(View.OnTouchListener { _, event ->
            val DRAWABLE_RIGHT = 2

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= view.findViewById<EditText>(R.id.edtContacts).right - view.findViewById<EditText>(R.id.edtContacts).compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                    val intent = Intent(Intent.ACTION_PICK, People.CONTENT_URI)
                    startActivityForResult(intent, PICK_CONTACT)
                    return@OnTouchListener true
                }
            }
            false
        })

        cvAttachment.setOnClickListener {
            startCameraIntent()
        }

        imgEdit.setOnClickListener {
            enableEditing()
        }

        ivHome.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
            activity!!.finish()
        }

        // Deleting the Memory from the Realm
        imgDelete.setOnClickListener {
            imgDelete.setOnClickListener {
                prepareRealmConnections(context, true, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val memoryDeleting = realm!!
                                .where(MemoryTimeline::class.java)
                                .equalTo("id", memoryTimeLine.id)
                                .findAll()
                        if (memoryDeleting.isValid) {
                            realm.beginTransaction()
                            memoryDeleting.deleteAllFromRealm()
                            realm.commitTransaction()
                            activity!!.onBackPressed()
                        }
                    }
                })
            }
        }

        populateView(memoryTimeLine)

    }

    // When clicked on Existing Memory, enableEditing() will be called on imgEdit Click..
    private fun enableEditing() {
        edtTitle.isEnabled = true
        txtDate.isEnabled = true
        cvAttachment.isClickable = true
        edtLocation.isEnabled = true
        edtContacts.isEnabled = true
        edtNotes.isEnabled = true
        NineBxApplication.instance.activityInstance!!.hideToolbar()
        toolbar.show()
        ivHome.hide()
        txtSaveMemory.show()
        imgEdit.setImageResource(R.drawable.ic_icon_save)
    }

    // When clicked on Add Memory, everything should be in editable mode.
    private fun enableAdding() {
        edtTitle.isEnabled = true
        txtDate.isEnabled = true
        cvAttachment.isClickable = true
        edtLocation.isEnabled = true
        edtContacts.isEnabled = true
        edtNotes.isEnabled = true
        NineBxApplication.instance.activityInstance!!.hideToolbar()
        toolbar.show()
        ivHome.hide()
        txtSaveMemory.show()
    }

    private fun checkValidation() {
        strMemoryTitle = edtTitle.text.toString()
        strDate = txtDate.text.toString()
        strLocation = edtLocation.text.toString()
        strContacts = edtContacts.text.toString()
        strNotes = edtNotes.text.toString()

        if (strMemoryTitle.trim().isEmpty()) {
            Toast.makeText(context, "Please enter Title", Toast.LENGTH_LONG).show()
            return
        }
        if (strDate.trim().isEmpty()) {
            Toast.makeText(context, "Please enter Date", Toast.LENGTH_LONG).show()
            return
        }



        if (addOrEdit == "Add") {
            var memoryTimeLineData = MemoryTimeline()
            memoryTimeLineData.id = UUID.randomUUID().hashCode().toLong()
            memoryTimeLineData.title = strMemoryTitle.encryptString()
            memoryTimeLineData.date = strDate.encryptString()
            memoryTimeLineData.place = strLocation.encryptString()
            memoryTimeLineData.contacts = strContacts.encryptString()
            memoryTimeLineData.notes = strNotes.encryptString()
            memoryTimeLineData.selectionType = "Memories".encryptString()
//            memoryTimeLineData.createdUser =preferences.
            prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    memoryTimeLineData.insertOrUpdate(realm!!)
                    memberView.onMemoryTimeLine(memoryTimeLineData)
                }
            })

        } else {
            prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    val updatingUserInfo = realm!!
                            .where(MemoryTimeline::class.java)
                            .equalTo("id", memoryTimeLine.id)
                            .findAll()

                    if (updatingUserInfo!!.isValid) {
                        realm.executeTransaction {
                            var memoryTimeLineData = MemoryTimeline()
                            memoryTimeLineData.id = memoryTimeLine.id
                            memoryTimeLineData.title = strMemoryTitle.encryptString()
                            memoryTimeLineData.date = strDate.encryptString()
                            memoryTimeLineData.place = strLocation.encryptString()
                            memoryTimeLineData.contacts = strContacts.encryptString()
                            memoryTimeLineData.notes = strNotes.encryptString()
                            memoryTimeLineData.selectionType = "Memories".encryptString()
                            realm.copyToRealmOrUpdate(memoryTimeLineData)
//                            memberView.onMemoryTimeLine(memoryTimeLineData)
                            activity!!.finish()
                        }
                    }
                }
            })
        }
    }


    private fun populateView(memoryTimeline: MemoryTimeline?) {

//        mAWSFileTransferHelper.setFileTransferListener(this)
//        if (memoryTimeline!!.photosId.isNotEmpty())
//            mAWSFileTransferHelper.beginDownload("images/" + memoryTimeline.id + "/" + memoryTimeline.photosId)

        if (memoryTimeline!!.title.decryptString().trim().isEmpty()) {
            titleMemory.text = strToolbarTitle
        } else {
            titleMemory.text = memoryTimeline.title.decryptString()
        }
        if (memoryTimeline.title.isNotEmpty())
            edtTitle.setText(memoryTimeline.title.decryptString())

        if (memoryTimeline.date.isNotEmpty())
            txtDate.text = memoryTimeline.date.decryptString()

        if (memoryTimeline.place.isNotEmpty())
            edtLocation.setText(memoryTimeline.place.decryptString())

        if (memoryTimeline.contacts.isNotEmpty())
            edtContacts.setText(memoryTimeline.contacts.decryptString())

        if (memoryTimeline.notes.isNotEmpty())
            edtNotes.setText(memoryTimeline.notes.decryptString())

        if (memoryTimeline.created.isNotEmpty())
            txtCreated.text = memoryTimeline.created.decryptString()

        if (memoryTimeline.modified.isNotEmpty())
            txtModified.text = memoryTimeline.modified.decryptString()

    }

    @SuppressLint("Recycle")
    private fun contactPicked(data: Intent) {
        var cursor: Cursor? = null
        try {
            var name: String? = null
            val uri = data.data
            cursor = context!!.contentResolver.query(uri, null, null, null, null)
            cursor!!.moveToFirst()
            val nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            name = cursor.getString(nameIndex)
            edtContacts.setText(name)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.showToolbar()
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        return super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // For Contacts
        if (requestCode == PICK_CONTACT && resultCode == Activity.RESULT_OK) {
            contactPicked(data!!)
        }
        // For Camera
        else if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            if (data.clipData != null) {
                val count = data.clipData.itemCount
                var currentItem = 0
                while (currentItem < count) {
                    val imageUri = data.clipData.getItemAt(currentItem).uri
                    mImagesList.add(imageUri)
                    currentItem += 1
                }

                setImagesAdapter()
            } else if (data.data != null) {
                mImagesList.add(data.data)
                setImagesAdapter()
            }
        } else if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            saveImage(data.extras.get("data") as Bitmap)
            filePath = activity!!.getImageUri(data.extras.get("data") as Bitmap)
            mImagesList.add(filePath!!)
            setImagesAdapter()
        }
        // For Location
        else if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val place = PlaceAutocomplete.getPlace(context, data)
                edtLocation.setText(place.name)
                AppLogger.e("Selected Place ", " is " + place.name)
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                val status = PlaceAutocomplete.getStatus(context, data)

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    // Handling Camera.

    lateinit var bottomSheetDialogFragment: CustomBottomSheetProfileDialogFragment
    private val PICK_IMAGE_REQUEST = 238
    private val CAMERA_REQUEST_CODE = 239
    private val PERMISSIONS_REQUEST_CODE_CAMERA = 115
    private val PERMISSIONS_REQUEST_CODE_GALLERY = 116

    private fun startCameraIntent() {
        bottomSheetDialogFragment.show(activity!!.supportFragmentManager, bottomSheetDialogFragment.tag)
    }

    override fun onOptionSelected(position: Int) {
        bottomSheetDialogFragment.dismiss()
        if (position == 1) {
            val permissionList = arrayListOf<String>(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            if (!handleMultiplePermission(context!!, permissionList)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissionList.toTypedArray(), PERMISSIONS_REQUEST_CODE_CAMERA)
                } else {
                    beginCameraAttachmentFlow()
                }
            } else {
                beginCameraAttachmentFlow()
            }

        } else {
            val permissionList = arrayListOf<String>(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            if (!handleMultiplePermission(context!!, permissionList)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissionList.toTypedArray(), PERMISSIONS_REQUEST_CODE_GALLERY)
                } else {
                    beginGalleryAttachmentFlow()
                }
            } else {
                beginGalleryAttachmentFlow()
            }
        }
    }

    private fun beginGalleryAttachmentFlow() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun beginCameraAttachmentFlow() {

        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (callCameraIntent.resolveActivity(context!!.packageManager) != null) {
            startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (requestCode == PERMISSIONS_REQUEST_CODE_CAMERA) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                beginCameraAttachmentFlow()
            } else {
                Toast.makeText(context!!, "Some permissions were denied", Toast.LENGTH_LONG).show()
            }
        } else if (requestCode == PERMISSIONS_REQUEST_CODE_GALLERY) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                beginGalleryAttachmentFlow()
            } else {
                Toast.makeText(context!!, "Some permissions were denied", Toast.LENGTH_LONG).show()
            }
        }

    }

    //a Uri object to store file path
    private var filePath: Uri? = null
    private var mImagesList: ArrayList<Uri> = ArrayList()

    private var attachmentRecyclerAdapter: AttachmentRecyclerViewAdapter? = null
    private fun setImagesAdapter() {
        cvAttachment.hide()
        rvAttachments.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        hideShowAttachments()

        attachmentRecyclerAdapter = AttachmentRecyclerViewAdapter(mImagesList, object : ActionClickListener {
            override fun onItemClick(position: Int, action: String) {
                when (action) {
                    "delete" -> {
                        mImagesList.removeAt(position)
                        attachmentRecyclerAdapter!!.notifyDataSetChanged()
                    }
                    "view" -> {
                        ImageViewDialog(context!!, mImagesList, getString(R.string.attachments))
                    }
                    "add" -> {
                        startCameraIntent()
                    }
                }
            }
        }, LinearLayoutManager.HORIZONTAL)
        rvAttachments.adapter = attachmentRecyclerAdapter
    }

}