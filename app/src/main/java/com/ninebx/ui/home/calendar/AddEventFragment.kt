package com.ninebx.ui.home.calendar

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.HomeView
import com.ninebx.ui.home.calendar.model.CalendarEvents
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.getDateMonthYearFormat
import kotlinx.android.synthetic.main.fragment_add_calendar_every.*
import android.content.Intent
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ninebx.ui.base.ActionClickListener
import com.ninebx.ui.base.kotlin.handleMultiplePermission
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.saveImage
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.home.customView.CustomBottomSheetProfileDialogFragment
import com.ninebx.utility.AppLogger
import java.io.ByteArrayOutputStream
import java.io.IOException


/***
 * Created by Alok Omkar on 10/01/18.
 */
class AddEventFragment : FragmentBackHelper(), CustomBottomSheetProfileDialogFragment.BottomSheetSelectedListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_calendar_every, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if( context is HomeView)
            mHomeView = context
    }

    private lateinit var mHomeView : HomeView
    private lateinit var mCalendarEvent : CalendarEvents
    private var isAddEvent = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        bottomSheetDialogFragment = CustomBottomSheetProfileDialogFragment()
        bottomSheetDialogFragment.setBottomSheetSelectionListener(this)
        mCalendarEvent = arguments!!.getParcelable<CalendarEvents>("calendarEvent")
        isAddEvent = arguments!!.getBoolean("isAddEvent", false)

        NineBxApplication.instance.activityInstance!!.hideToolbar()
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        ivBack.setOnClickListener { goBack() }
        tvSave.setOnClickListener {
            if( validate() ) {
                saveCalendarEvent()
            }
        }

        setValues( mCalendarEvent )

        switchAllDay.setOnCheckedChangeListener { button, isChecked -> toggleViews(isChecked) }

        tvStarts.setOnClickListener {  }
        tvEnds.setOnClickListener {  }
        tvAttachment.setOnClickListener { startCameraIntent() }

        layoutRepeat.setOnClickListener { showSelectionDialog(tvRepeat.text.toString().trim(), "Repeat" ) }
        layoutEndRepeat.setOnClickListener {  }
        layoutReminder.setOnClickListener { showSelectionDialog(tvReminder.text.toString().trim(), "Reminder" ) }

        rvAttachments.layoutManager = LinearLayoutManager(context)
        rvAttachments.adapter = DayEventsRecyclerViewAdapter(4)

        etLocation.setOnTouchListener{ _, event ->
            if( event.action == MotionEvent.ACTION_UP ) {
                val DRAWABLE_RIGHT = 2;
                if(event.getRawX() >= (etLocation.getRight() - etLocation.compoundDrawables[DRAWABLE_RIGHT].getBounds().width()))
                {
                    try {
                        NineBxApplication.getPreferences().isPasswordEnabled = true
                        val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                .build(activity)
                        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
                    } catch (e: GooglePlayServicesRepairableException) {
                        AppLogger.e(TAG, "GooglePlayServicesRepairableException: " + e.message)
                        e.printStackTrace()
                        NineBxApplication.getPreferences().isPasswordEnabled = false
                    } catch (e: GooglePlayServicesNotAvailableException) {
                        AppLogger.e(TAG, "GooglePlayServicesNotAvailableException: " + e.message)
                        e.printStackTrace()
                        NineBxApplication.getPreferences().isPasswordEnabled = false
                    }
                    true
                }
                else
                    false
            }
            else false
        }
    }

    private fun showSelectionDialog(selectedInterval : String, selectionType : String ) {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.setContentView(R.layout.dialog_repeat)

        val intervals = ArrayList<String>()
        when( selectionType ) {
            "Repeat" -> {

                intervals.add("Never")
                intervals.add("Every Day")
                intervals.add("Every Week")
                intervals.add("Every 2 Weeks")
                intervals.add("Every Month")
                intervals.add("Every Year")

            }
            "Reminder" -> {

                intervals.add("None")
                intervals.add("At time of event")
                intervals.add("5 minutes before")
                intervals.add("15 minutes before")
                intervals.add("30 minutes before")
                intervals.add("1 hour before")
                intervals.add("2 hour before")
                intervals.add("1 day before")
                intervals.add("2 day before")
                intervals.add("1 week before")

            }
        }

        val tvTitle = dialog.findViewById<TextView>(R.id.tvTitle)
        val rvRepeatInterval = dialog.findViewById<RecyclerView>(R.id.rvRepeatInterval)
        rvRepeatInterval.layoutManager = LinearLayoutManager( context )
        rvRepeatInterval.adapter = RepeatIntervalAdapter( intervals, selectedInterval, intervals[0], object : ActionClickListener {
            override fun onItemClick(position: Int, action: String) {
                if( selectionType == "Repeat" ) {
                    tvRepeat.text = action
                    hideShowEndRepeat()
                }
                else {
                    tvReminder.text = action
                }
                dialog.dismiss()
            }

        } )
        tvTitle.text = selectionType

        val window = dialog.window
        val wlp = window.attributes

        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window.attributes = wlp
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.show()

        val imgBack = dialog.findViewById<View>(R.id.imgBack) as ImageView
        imgBack.setOnClickListener {
            dialog.cancel()
        }
    }

    private val TAG: String = AddEventFragment::class.java.simpleName

    //a Uri object to store file path
    private var filePath: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            NineBxApplication.getPreferences().isPasswordEnabled = false
            if (resultCode == RESULT_OK) {
                val place = PlaceAutocomplete.getPlace(context, data)
                AppLogger.i(TAG, "Place: " + place.name)
                etLocation.setText( place.name )
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                val status = PlaceAutocomplete.getStatus(context, data)
                // TODO: Handle the error.
                AppLogger.i(TAG, status.statusMessage!!)

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            try {
                uploadImageFirebase()
                val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, filePath)

                /*if( profileImageView != null )
                    Glide.with(profileImageView.context).load(bitmap).apply(options).into(profileImageView)*/

            } catch (e: IOException) {
                e.printStackTrace()
            }


        }
        else if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            saveImage(data.extras.get("data") as Bitmap)
            filePath = getImageUri(data.extras.get("data") as Bitmap)
            try {
                if (filePath != null) {
                    uploadImageFirebase()
                }
                /*if( profileImageView != null )
                    profileImageView!!.setImageBitmap(data.extras.get("data") as Bitmap)*/
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        else
            super.onActivityResult(requestCode, resultCode, data)
    }

    private fun uploadImageFirebase() {
        //TODO
    }

    fun getImageUri(bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(activity!!.contentResolver, bitmap, "Title", null)
        return Uri.parse(path);
    }

    var PLACE_AUTOCOMPLETE_REQUEST_CODE : Int = 1

    private fun toggleViews(isAllDay: Boolean) {

    }

    private fun setValues(mCalendarEvent: CalendarEvents) {
        if( !isAddEvent ) {

            etTitle.setText(  mCalendarEvent.alert )
            etLocation.setText( mCalendarEvent.location )
            tvStarts.text = getDateMonthYearFormat(mCalendarEvent.startsDate)
            tvEnds.text = getDateMonthYearFormat(mCalendarEvent.endsDate)
            tvRepeat.text = mCalendarEvent.repeats

            hideShowEndRepeat()

            tvReminder.text = mCalendarEvent.reminder

        }
    }

    private fun hideShowEndRepeat() {
        if( tvRepeat.text.toString().trim() == "Never" )
            layoutEndRepeat.hide()
        else
            layoutEndRepeat.show()
    }

    private fun validate(): Boolean {
        return true
    }

    private fun saveCalendarEvent() {
        goBack()
    }

    fun goBack() {
        NineBxApplication.getPreferences().isPasswordEnabled = false
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.calendar))
        NineBxApplication.instance.activityInstance!!.showToolbar()
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.onBackPressed()
    }

    lateinit var bottomSheetDialogFragment: CustomBottomSheetProfileDialogFragment
    private val PICK_IMAGE_REQUEST = 234
    private val CAMERA_REQUEST_CODE = 235
    private val PERMISSIONS_REQUEST_CODE_CAMERA = 111
    private val PERMISSIONS_REQUEST_CODE_GALLERY = 112

    private fun startCameraIntent() {
        bottomSheetDialogFragment.show(childFragmentManager, bottomSheetDialogFragment.tag)
    }

    override fun onOptionSelected(position: Int) {
        bottomSheetDialogFragment.dismiss()
        if (position == 1) {
            val permissionList = arrayListOf<String>(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            if (!handleMultiplePermission(context!!, permissionList)) {
                requestPermissions( permissionList.toTypedArray(), PERMISSIONS_REQUEST_CODE_CAMERA)
            } else {
                beginCameraAttachmentFlow()
            }

        } else {
            val permissionList = arrayListOf<String>(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            if (!handleMultiplePermission(context!!, permissionList)) {
                requestPermissions( permissionList.toTypedArray(), PERMISSIONS_REQUEST_CODE_GALLERY)
            } else {
                beginGalleryAttachmentFlow()
            }
        }
    }

    private fun beginGalleryAttachmentFlow() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private fun beginCameraAttachmentFlow() {
        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (callCameraIntent.resolveActivity(activity!!.packageManager) != null) {
            startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (requestCode == PERMISSIONS_REQUEST_CODE_CAMERA) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                beginCameraAttachmentFlow()
            } else {
                Toast.makeText(context, "Some permissions were denied", Toast.LENGTH_LONG).show()
            }
        }
        else if( requestCode == PERMISSIONS_REQUEST_CODE_GALLERY ) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                beginGalleryAttachmentFlow()
            } else {
                Toast.makeText(context, "Some permissions were denied", Toast.LENGTH_LONG).show()
            }
        }

    }
}