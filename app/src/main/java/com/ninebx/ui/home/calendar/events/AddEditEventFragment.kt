package com.ninebx.ui.home.calendar.events

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.HomeView
import com.ninebx.ui.base.realm.CalendarEvents
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
import com.ninebx.ui.base.ActionClickListener
import com.ninebx.ui.base.kotlin.*
import com.ninebx.ui.home.customView.CustomBottomSheetProfileDialogFragment
import com.ninebx.utility.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


/***
 * Created by Alok Omkar on 10/01/18.
 */
class AddEditEventFragment : FragmentBackHelper(), CustomBottomSheetProfileDialogFragment.BottomSheetSelectedListener {


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
    private lateinit var mAWSFileTransferHelper: AWSFileTransferHelper
    private var isAddEvent = false
    private lateinit var mSelectedDate : Date

    private var mSelectedDateIndex = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        mAWSFileTransferHelper = AWSFileTransferHelper(context!!)
        bottomSheetDialogFragment = CustomBottomSheetProfileDialogFragment()
        bottomSheetDialogFragment.setBottomSheetSelectionListener(this)
        isAddEvent = arguments!!.getBoolean("isAddEvent", false)
        val selectedDate = Calendar.getInstance()
        selectedDate.timeInMillis = arguments!!.getLong("selectedDate", Date().time)
        mSelectedDate = selectedDate.time

        if( !isAddEvent ) {
            mSelectedDateIndex = mCalendarEvent.startsDate.indexOf(getDateMonthYearTimeFormat(mSelectedDate))
        }

        NineBxApplication.instance.activityInstance!!.hideToolbar()
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        ivBack.setOnClickListener { goBack() }
        tvSave.setOnClickListener {
            if( validate() ) {
                saveCalendarEvent()
            }
            //uploadImageAws()
            //downloadImageAws()
            //val decryptFile = decryptFile(File("/storage/emulated/0/IMG-20180121-WA0000.jpg"))

            //PDF : "/storage/emulated/0/Download/ninebx/pdf-sample.pdf"
            //Image : ""
            mAWSFileTransferHelper.performDecryption(
                    File( "/storage/emulated/0/Encrypted_image.jpg"),
                    "nB8hEnaqppfWOp5L".toCharArray(),
                    object : AWSFileTransferHelper.FileOperationsCompletionListener {
                override fun onSuccess(outputFile: File?) {

                    mImagesList.add( Uri.fromFile(outputFile) )
                    setImagesAdapter()

                }

            })



            //mAWSFileTransferHelper.decryptEncryptedFile( File("/storage/emulated/0/ios_normal.jpeg"), "nB8hEnaqppfWOp5L".toCharArray())
            //decryptFileIOS( File("/storage/emulated/0/ios_normal.jpeg"), "nB8hEnaqppfWOp5L".toCharArray())
        }
        layoutEndRepeat.hide()
        setValues( mCalendarEvent )

        switchAllDay.setOnCheckedChangeListener { _, isChecked -> changeDateFormat(isChecked) }

        tvStarts.setOnClickListener {
            val calendar = Calendar.getInstance()
            if( mCalendarEvent.startsDate.size > 0 )
                calendar.time = parseDateMonthYearTimeFormat(mCalendarEvent.startsDate[mSelectedDateIndex]!!)
            showDateTimeSelector( tvStarts, calendar, switchAllDay.isSelected )
        }

        tvEnds.setOnClickListener {
            val calendar = Calendar.getInstance()
            if( mCalendarEvent.endsDate.size > 0 )
                calendar.time = parseDateMonthYearTimeFormat(mCalendarEvent.endsDate[mSelectedDateIndex]!!)
            showDateTimeSelector(tvEnds, calendar, switchAllDay.isSelected)
        }

        cvAttachment.setOnClickListener { startCameraIntent() }

        layoutRepeat.setOnClickListener { showSelectionDialog(tvRepeat.text.toString().trim(), "Repeat" ) }
        layoutEndRepeat.setOnClickListener {

            var endRepeat = ""

            if( mCalendarEvent.endRepeat.size > 0 )
                endRepeat = mCalendarEvent.endRepeat[mSelectedDateIndex]!!

            if( endRepeat.isEmpty() ) endRepeat = "Never"

            if( endRepeat != "Never" ) {
                endRepeat = "On Date"
            }
            showSelectionDialog(endRepeat, "End Repeat" )
        }
        layoutReminder.setOnClickListener { showSelectionDialog(tvReminder.text.toString().trim(), "Reminder" ) }

        rvAttachments.layoutManager = LinearLayoutManager(context)


        etLocation.setOnTouchListener{ _, event ->
            if( event.action == MotionEvent.ACTION_UP ) {
                val DRAWABLE_RIGHT = 2;
                if(event.rawX >= (etLocation.right - etLocation.compoundDrawables[DRAWABLE_RIGHT].bounds.width()))
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

    private fun downloadImageAws() {
        mAWSFileTransferHelper.beginDownload("IMG-20180121-WA0000.jpg")
    }

    private fun showDateTimeSelector(dateTimeTextView: TextView?, calendar: Calendar?, isAllDay: Boolean) {

        if( isAllDay ) {
            calendar!!.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
        }

        getDateFromPicker( context!!, calendar!!, object : DateTimeSelectionListener {

            override fun onDateTimeSelected(selectedDate: Calendar) {

                if( isAllDay ) {
                    setDateTime( dateTimeTextView, selectedDate, isAllDay )
                }
                else {
                    getTimeFromPicker( context!!, selectedDate, object  : DateTimeSelectionListener {
                        override fun onDateTimeSelected(selectedDate: Calendar) {
                            setDateTime(dateTimeTextView, selectedDate, isAllDay)
                        }

                    })
                }

            }
        })

    }

    private fun setDateTime(dateTimeTextView: TextView?, selectedDate: Calendar, allDay: Boolean) {

        if( allDay ) {
            dateTimeTextView!!.text = getDateMonthYearFormat(selectedDate.time)
        }
        else
            dateTimeTextView!!.text = getDateMonthYearTimeFormat(selectedDate.time)

        if( dateTimeTextView.id == tvStarts.id ) {
            if( mCalendarEvent.startsDate.size > 0 )
                mCalendarEvent.startsDate[mSelectedDateIndex] = getDateMonthYearTimeFormat(selectedDate.time)
            else
                mCalendarEvent.startsDate.add( getDateMonthYearTimeFormat(selectedDate.time) )
        }
        else {
            if( mCalendarEvent.endsDate.size > 0 )
                mCalendarEvent.endsDate[mSelectedDateIndex] = getDateMonthYearTimeFormat(selectedDate.time)
            else
                mCalendarEvent.endsDate.add( getDateMonthYearTimeFormat(selectedDate.time) )
        }
    }

    private fun showSelectionDialog( selectedInterval : String, selectionType : String ) {
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
            "End Repeat" -> {
                intervals.add("Never")
                intervals.add("On Date")
            }
            "Reminder" -> {

                intervals.add("None")

                if( switchAllDay.isSelected ) {
                    intervals.add("On day of event(9:00 AM)")
                    intervals.add("One day before(9:00 AM)")
                    intervals.add("Two days before(9:00 AM)")
                    intervals.add("1 week before")
                }
                else {
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
        }

        val tvTitle = dialog.findViewById<TextView>(R.id.tvTitle)
        val rvRepeatInterval = dialog.findViewById<RecyclerView>(R.id.rvRepeatInterval)
        rvRepeatInterval.layoutManager = LinearLayoutManager( context )


        rvRepeatInterval.adapter = RepeatIntervalAdapter(intervals, selectedInterval, if (selectionType != "End Repeat") intervals[0] else "", object : ActionClickListener {
            override fun onItemClick(position: Int, action: String) {

                if (selectionType == "Repeat") {
                    tvRepeat.text = action
                    hideShowEndRepeat()
                    dialog.dismiss()
                } else if (selectionType == "End Repeat") {

                    tvEndRepeat.text = action
                    if (action != "Never") {

                        val calendar = Calendar.getInstance()
                        if (mCalendarEvent.endRepeat.size > 0 && mCalendarEvent.endRepeat[mSelectedDateIndex] != "Never") {
                            calendar.time = parseDateMonthYearFormat(mCalendarEvent.endRepeat[mSelectedDateIndex]!!)
                        }

                        getDateFromPicker(context!!, calendar, object : DateTimeSelectionListener {
                            override fun onDateTimeSelected(selectedDate: Calendar) {
                                if (mCalendarEvent.endRepeat.size > 0)
                                    mCalendarEvent.endRepeat[mSelectedDateIndex] = getDateMonthYearFormat(selectedDate.time)
                                else
                                    mCalendarEvent.endRepeat.add(getDateMonthYearFormat(selectedDate.time))
                                tvEndRepeat.text = mCalendarEvent.endRepeat[mSelectedDateIndex]
                                dialog.dismiss()
                            }
                        })
                    } else {
                        if (mCalendarEvent.endRepeat.size > 0)
                            mCalendarEvent.endRepeat[mSelectedDateIndex] = action
                        else
                            mCalendarEvent.endRepeat.add(action)
                        dialog.dismiss()
                    }


                } else {
                    tvReminder.text = action
                    dialog.dismiss()
                }

            }

        })
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

    private val TAG: String = AddEditEventFragment::class.java.simpleName

    //a Uri object to store file path
    private var filePath: Uri? = null
    private var mImagesList : ArrayList<Uri> = ArrayList()
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
        else if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null ) {
            if (data.clipData != null) {
                val count = data.clipData.itemCount
                var currentItem = 0
                while (currentItem < count) {
                    val imageUri = data.clipData.getItemAt(currentItem).uri
                    mImagesList.add(imageUri)
                    currentItem += 1
                }
                etAttachment.isEnabled = true
                setImagesAdapter()
            } else if (data.data != null) {
                mImagesList.add(data.data)
                etAttachment.isEnabled = true
                setImagesAdapter()
            }

        }
        else if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            saveImage(data.extras.get("data") as Bitmap)
            filePath = activity!!.getImageUri(data.extras.get("data") as Bitmap)
            mImagesList.add(filePath!!)
            etAttachment.isEnabled = true
            setImagesAdapter()

        }
        else
            super.onActivityResult(requestCode, resultCode, data)
    }

    private var attachmentRecyclerAdapter : AttachmentRecyclerViewAdapter?= null
    private fun setImagesAdapter() {
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
                }
            }
        }, LinearLayoutManager.VERTICAL )
        rvAttachments.adapter = attachmentRecyclerAdapter
    }

    private fun uploadImageAws() {
        if( mImagesList.size > 0 ) {
            for( imageUri in mImagesList ) {
                mAWSFileTransferHelper.beginUpload(getPath(imageUri))
            }
        }
    }


    var PLACE_AUTOCOMPLETE_REQUEST_CODE : Int = 1

    private fun changeDateFormat(isAllDay: Boolean) {
        val calendar = Calendar.getInstance()

        if( mCalendarEvent.startsDate.size > 0 )
            calendar.time = parseDateMonthYearTimeFormat(mCalendarEvent.startsDate[mSelectedDateIndex]!!)

        if( isAllDay ) {
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
        }
        setDateTime(tvStarts, calendar, isAllDay)

        if( mCalendarEvent.endsDate.size > 0 )
            calendar.time = parseDateMonthYearTimeFormat(mCalendarEvent.endsDate[mSelectedDateIndex]!!)

        if( isAllDay ) {
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
        }
        setDateTime(tvEnds, calendar, isAllDay)
        if( mCalendarEvent.isAllDay.size > 0 )
            mCalendarEvent.isAllDay[mSelectedDateIndex] = isAllDay
        else
            mCalendarEvent.isAllDay.add( isAllDay )

    }

    private fun setValues(mCalendarEvent: CalendarEvents) {
        if( !isAddEvent ) {

            etTitle.setText(  mCalendarEvent.alert[mSelectedDateIndex] )
            etLocation.setText( mCalendarEvent.location[mSelectedDateIndex] )
            tvStarts.text = (mCalendarEvent.startsDate[mSelectedDateIndex])
            tvEnds.text = (mCalendarEvent.endsDate[mSelectedDateIndex])
            tvRepeat.text = mCalendarEvent.repeats[mSelectedDateIndex]
            if( mCalendarEvent.endRepeat.size > 0 ) {
                tvEndRepeat.text = mCalendarEvent.endRepeat[mSelectedDateIndex]
                layoutEndRepeat.show()
            }
            switchAllDay.isSelected = mCalendarEvent.isAllDay[mSelectedDateIndex]!!
            etNotes.setText( mCalendarEvent.notes[mSelectedDateIndex] )

            hideShowEndRepeat()

            tvReminder.text = mCalendarEvent.reminder[mSelectedDateIndex]

        }
    }

    private fun hideShowEndRepeat() {
        if( tvRepeat.text.toString().trim() == "Never" )
            layoutEndRepeat.hide()
        else
            layoutEndRepeat.show()
    }

    private fun validate(): Boolean {

        if( etTitle.text.toString().isEmpty() ) {
            etTitle.requestFocus()
            context!!.showToast(R.string.error_empty_title)
            return false
        }
        if( mCalendarEvent.title.size > 0 )
            mCalendarEvent.title[mSelectedDateIndex] = etTitle.text.toString().trim()
        else
            mCalendarEvent.title.add( etTitle.text.toString().trim() )

        if( tvStarts.text.toString().isEmpty() ) {
            tvStarts.requestFocus()
            context!!.showToast(R.string.error_empty_start_date)
            return false
        }
        //mCalendarEvent.startsDate[mSelectedDateIndex] = tvStarts.text.toString().trim()

        if( tvEnds.text.toString().isEmpty() ) {
            tvEnds.requestFocus()
            context!!.showToast(R.string.error_empty_end_date)
            return false
        }
        //mCalendarEvent.endsDate[mSelectedDateIndex] = tvEnds.text.toString().trim()

        return true
    }

    private fun saveCalendarEvent() {
        val eventCalendar = Calendar.getInstance()
        eventCalendar.time = parseDateMonthYearTimeFormat(mCalendarEvent.startsDate[mSelectedDateIndex]!!)
        AlarmJob.scheduleJob( mCalendarEvent, eventCalendar )
        uploadImageAws()
        //goBack()
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
        intent.action = Intent.ACTION_GET_CONTENT
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

    fun setCalendarEvent(event: CalendarEvents) {
        mCalendarEvent = event
    }
}