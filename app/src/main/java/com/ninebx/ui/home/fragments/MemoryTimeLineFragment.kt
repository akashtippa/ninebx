package com.ninebx.ui.home.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.Contacts.People
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.home.account.memoryView.MemoryView
import com.ninebx.ui.home.calendar.events.AWSFileTransferHelper
import com.ninebx.utility.*
import com.ninebx.utility.countryPicker.CountryPicker
import io.realm.Realm
import kotlinx.android.synthetic.main.add_memory.*
import java.io.File
import java.util.*


/***
 * Created by TechnoBlogger on 24/01/18.
 */
class MemoryTimeLineFragment : FragmentBackHelper(), AWSFileTransferHelper.FileOperationsCompletionListener {
    override fun onSuccess(outputFile: File?) {
        if (outputFile != null && ivAttachment != null)
            Glide.with(context).asBitmap().load(outputFile).into(ivAttachment)
    }

    var strToolbarTitle = ""
    var strMemoryTitle = ""
    var strDate = ""
    var strLocation = ""
    var strContacts = ""
    var strNotes = ""
    private lateinit var memoryTimeLine: MemoryTimeline
    val PICK_CONTACT = 1

    private lateinit var memberView: MemoryView
    private lateinit var mAWSFileTransferHelper: AWSFileTransferHelper


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
        NineBxApplication.instance.activityInstance!!.hideToolbar()
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        memoryTimeLine = arguments!!.getParcelable(Constants.MEMORY_TIMELINE)
        mAWSFileTransferHelper = AWSFileTransferHelper(context!!)


        ivBack.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        txtDate.setOnClickListener {
            getDateFromPicker(this.context!!, Calendar.getInstance(), object : DateTimeSelectionListener {
                override fun onDateTimeSelected(selectedDate: Calendar) {
                    txtDate.text = (getDateMonthYearFormat(selectedDate.time))
                }
            })
        }

        txtSaveMemory.setOnClickListener {
            checkValidation()
        }

        edtLocation.setOnTouchListener(View.OnTouchListener { _, event ->
            val DRAWABLE_RIGHT = 2

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= edtLocation.right - edtLocation.compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                    val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.replace(R.id.frameLayout, CountryPicker()).commit()
                    return@OnTouchListener true
                }
            }
            false
        })

        edtContacts.setOnClickListener {

        }

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

        populateView(memoryTimeLine)

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


        var memoryTimeLineData = MemoryTimeline()
        memoryTimeLineData.id = this@MemoryTimeLineFragment.memoryTimeLine.id
        memoryTimeLineData.title = strMemoryTitle.encryptString()
        memoryTimeLineData.date = strDate.encryptString()
        memoryTimeLineData.place = strLocation.encryptString()
        memoryTimeLineData.contacts = strContacts.encryptString()
        memoryTimeLineData.notes = strNotes.encryptString()

        memberView.onMemoryTimeLine(memoryTimeLineData)
        sendDataToServer(memoryTimeLineData)
        populateView(memoryTimeLine)
    }


    private fun populateView(memoryTimeline: MemoryTimeline?) {

        mAWSFileTransferHelper.setFileTransferListener(this)
        if (memoryTimeline!!.photosId.isNotEmpty())
            mAWSFileTransferHelper.beginDownload("images/" + memoryTimeline.id + "/" + memoryTimeline.photosId)

        titleMemory.text = memoryTimeline.title.decryptString()
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

    private fun sendDataToServer(memoryTimeLineData: MemoryTimeline) {
//        var memories = MemoryTimeline()
//        memories.id = getUniqueId()
//        memories.title = strMemoryTitle.encryptString()
//        memories.date = strDate.encryptString()
//        memories.place = strLocation.encryptString()
//        memories.contacts = strContacts.encryptString()
//        memories.notes = strNotes.encryptString()

        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                memoryTimeLineData.insertOrUpdate(realm!!)
                NineBxApplication.instance.activityInstance!!.onBackPressed()
            }
        })
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
        if (requestCode == PICK_CONTACT && resultCode == Activity.RESULT_OK) {
            contactPicked(data!!)
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }
}