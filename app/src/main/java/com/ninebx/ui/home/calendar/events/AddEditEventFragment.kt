package com.ninebx.ui.home.calendar.events

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.HomeView
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
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ninebx.ui.base.ActionClickListener
import com.ninebx.ui.base.kotlin.*
import com.ninebx.ui.base.realm.*
import com.ninebx.ui.home.calendar.CalendarFragment.Companion.getCalendarInstance
import com.ninebx.ui.home.customView.CalendarBottomFragment
import com.ninebx.utility.*
import com.ninebx.utility.Constants.REALM_END_POINT_CALENDAR_EVENTS
import io.realm.Realm
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


/***
 * Created by Alok Omkar on 10/01/18.
 */
class AddEditEventFragment : FragmentBackHelper(), CalendarBottomFragment.BottomSheetSelectedListener, AWSFileTransferHelper.FileOperationsCompletionListener {


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

    var editEnabled : Boolean = false

    private var mSelectedDateIndex = 0

    private lateinit var calendarRealm: Realm

    private var startDateCalendar: Calendar?= null
    private var endDateCalendar: Calendar?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        prepareRealmConnections(context, true, Constants.REALM_END_POINT_CALENDAR_EVENTS, object : Realm.Callback(){
            override fun onSuccess(realm: Realm?) {
                calendarRealm = realm!!
                context!!.hideProgressDialog()
            }
        })

        mAWSFileTransferHelper = AWSFileTransferHelper(context!!)
        optionsDialogFragment = CalendarBottomFragment()
        optionsDialogFragment.setBottomSheetSelectionListener(this)
        isAddEvent = arguments!!.getBoolean("isAddEvent", false)
        val selectedDate = Calendar.getInstance()
        selectedDate.timeInMillis = arguments!!.getLong("selectedDate", Date().time)
        mSelectedDate = selectedDate.time

        if( !isAddEvent ) {
            mSelectedDateIndex = mCalendarEvent.startsDate.indexOf((getDateMonthYearTimeFormat(mSelectedDate)))
        }
        if( mSelectedDateIndex == -1 ) mSelectedDateIndex = 0


        if(isAddEvent){
            editBtn.hide()
            deleteBtn.hide()
            enableEdit()
        }
        else{
            editBtn.show()
            deleteBtn.show()
            disableEdit()
        }

        NineBxApplication.instance.activityInstance!!.hideBottomView()

        ivBack.setOnClickListener { goBack() }
        tvSave.setOnClickListener {
            if(tvSave.text != "") {
                if( validate() ) {
                    saveCalendarEvent()
                }
            } else {
                NineBxApplication.instance.activityInstance!!.callHomeFragment()
            }
            //uploadImageAws()
            //downloadImageAws()
            //val decryptFile = decryptFile(File("/storage/emulated/0/IMG-20180121-WA0000.jpg"))

            //PDF : "/storage/emulated/0/Download/ninebx/pdf-sample.pdf"
            //Image : ""
            /*mAWSFileTransferHelper.performDecryption(
                    File( "/storage/emulated/0/Encrypted_image.jpg"),
                    "nB8hEnaqppfWOp5L".toCharArray(),
                    object : AWSFileTransferHelper.FileOperationsCompletionListener {
                override fun onSuccess(outputFile: File?) {

                    mImagesList.add( Uri.fromFile(outputFile) )
                    setImagesAdapter()

                }

            })*/



            //mAWSFileTransferHelper.decryptEncryptedFile( File("/storage/emulated/0/ios_normal.jpeg"), "nB8hEnaqppfWOp5L".toCharArray())
            //decryptFileIOS( File("/storage/emulated/0/ios_normal.jpeg"), "nB8hEnaqppfWOp5L".toCharArray())
        }

        deleteBtn.setOnClickListener {

            if(mCalendarEvent.id != null) {
                AlarmJob.cancelJob(mCalendarEvent.id.toString()) //cancel alarm
                val instance = getCalendarInstance()// remove from recylerview
                instance.mDayEventsAdapter.remove(mCalendarEvent)
                removeCalenderEvent() // remove event from database
                goBack()
            }
        }


        editBtn.setOnClickListener {
            enableEdit()
        }

        layoutEndRepeat.hide()
        setValues( )

        switchAllDay.setOnCheckedChangeListener { _, isChecked -> changeDateFormat(isChecked) }

        tvStarts.setOnClickListener {
            startDateCalendar = Calendar.getInstance()
            if( mCalendarEvent.startsDate.size > 0 )
                startDateCalendar!!.time = parseDateForFormat(mCalendarEvent.startsDate[mSelectedDateIndex]!!, DATE_FORMAT)
            showDateTimeSelector( tvStarts, startDateCalendar, switchAllDay.isChecked )
        }

        tvEnds.setOnClickListener {
            if( startDateCalendar != null ) {
                endDateCalendar = Calendar.getInstance()
                if( mCalendarEvent.endsDate.size > 0 )
                    endDateCalendar!!.time = parseDateForFormat(mCalendarEvent.endsDate[mSelectedDateIndex]!!, DATE_FORMAT)
                showDateTimeSelector( tvEnds, endDateCalendar, startDateCalendar!!, switchAllDay.isChecked)
            }
            else {
                context!!.showToast(R.string.pick_start_date_for_event)
            }
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
                        //AppLogger.e(TAG, "GooglePlayServicesRepairableException: " + e.message)
                        e.printStackTrace()
                        NineBxApplication.getPreferences().isPasswordEnabled = false
                    } catch (e: GooglePlayServicesNotAvailableException) {
                        //AppLogger.e(TAG, "GooglePlayServicesNotAvailableException: " + e.message)
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
        if( !isAddEvent ) {
            for( imageFilePath in mCalendarEvent.backingImages ) {
                mAWSFileTransferHelper.beginDownload(imageFilePath.stringValue)
            }
        }
    }

    private fun disableEdit() {
        var appIcon = ContextCompat.getDrawable(context!!, R.drawable.search_bx)
        appIcon!!.setBounds(0,0,120,120)
        tvSave.text = ""
        tvSave.setCompoundDrawables(null, null, appIcon, null)
        editBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_icon_edit))
        etTitle.isEnabled = false
        etLocation.isEnabled = false
        tvStarts.isEnabled = false
        tvEnds.isEnabled = false
        switchAllDay.isEnabled = false
        etNotes.isEnabled = false
        layoutRepeat.isEnabled = false
        layoutEndRepeat.isEnabled = false
        layoutReminder.isEnabled = false
        cvAttachment.isEnabled = false
        etAttachment.isEnabled = false
    }

    private fun enableEdit() {
        tvSave.text = "Save"
        tvSave.setCompoundDrawables(null,null,null,null)
        editBtn.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_icon_edit_blue))
        etTitle.isEnabled = true
        etLocation.isEnabled = true
        tvStarts.isEnabled = true
        tvEnds.isEnabled = true
        switchAllDay.isEnabled = true
        etNotes.isEnabled = true
        layoutRepeat.isEnabled = true
        layoutEndRepeat.isEnabled = true
        layoutReminder.isEnabled = true
        cvAttachment.isEnabled = true
        etAttachment.isEnabled = false

    }

    private fun removeCalenderEvent() {

        prepareRealmConnections(context, false, REALM_END_POINT_CALENDAR_EVENTS, object : Realm.Callback(){
            override fun onSuccess(realm: Realm?) {
                realm?.beginTransaction()
                mCalendarEvent.deleteFromRealm()
                realm?.commitTransaction()
            }
        })

    }

    private fun downloadImageAws() {
        mAWSFileTransferHelper.beginDownload("IMG-20180121-WA0000.jpg")
    }

    private fun showDateTimeSelector(dateTimeTextView: TextView?, calendar: Calendar?, minDateCalendar : Calendar, isAllDay: Boolean) {

        if( isAllDay ) {
            calendar!!.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
        }

        getDateFromPicker( context!!, calendar!!, minDateCalendar, object : DateTimeSelectionListener {

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

    private fun showDateTimeSelector(dateTimeTextView: TextView?, calendar: Calendar?, isAllDay: Boolean) {

        if( isAllDay ) {
            calendar!!.set(Calendar.HOUR_OF_DAY, 9)
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
                            if(endDateCalendar != null) {
                                if(selectedDate.get(Calendar.DAY_OF_MONTH) > endDateCalendar!!.get(Calendar.DAY_OF_MONTH)) {
                                    AppLogger.d("WRONGTIME ", " is set")
                                    endDateCalendar = null
                                    tvEnds.text = ""
                                }
                            }
                            selectedDate.set(Calendar.SECOND, 0)
                            selectedDate.set(Calendar.MILLISECOND, 0)
                            setDateTime(dateTimeTextView, selectedDate, isAllDay)
                        }

                    })
                }

            }
        })

    }

    private fun setDateTime(dateTimeTextView: TextView?, selectedDate: Calendar, allDay: Boolean) {

        if( allDay ) {
            dateTimeTextView!!.text = parseDateForFormat(selectedDate.time, DATE_FORMAT_ALL_DAY)
        }
        else
            dateTimeTextView!!.text = parseDateForFormat(selectedDate.time , DATE_FORMAT)

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

                if( !switchAllDay.isChecked ) {
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
                else {
                    intervals.add("On day of event(9:00 AM)")
                    intervals.add("One day before(9:00 AM)")
                    intervals.add("Two days before(9:00 AM)")
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
                        if (mCalendarEvent.endRepeat.size > 0 && mCalendarEvent.endRepeat[mSelectedDateIndex]!! != "Never") {
                            calendar.time = parseDateMonthYearFormat(mCalendarEvent.endRepeat[mSelectedDateIndex]!!)
                        }

                        getDateFromPicker(context!!, calendar, object : DateTimeSelectionListener {
                            override fun onDateTimeSelected(selectedDate: Calendar) {
                                if (mCalendarEvent.endRepeat.size > 0)
                                    mCalendarEvent.endRepeat[mSelectedDateIndex] = (getDateMonthYearFormat(selectedDate.time))
                                else
                                    mCalendarEvent.endRepeat.add((getDateMonthYearFormat(selectedDate.time)))
                                tvEndRepeat.text = mCalendarEvent.endRepeat[mSelectedDateIndex]!!
                                dialog.dismiss()
                            }
                        })
                    } else {
                        if (mCalendarEvent.endRepeat.size > 0)
                            mCalendarEvent.endRepeat[mSelectedDateIndex] = (action)
                        else
                            mCalendarEvent.endRepeat.add((action))
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
    private val DATE_FORMAT = "MMMM dd, yyyy hh:mm a"
    private val DATE_FORMAT_ALL_DAY = "MMMM dd, yyyy"
    //a Uri object to store file path
    private var filePath: Uri? = null
    private var mImagesList : ArrayList<Uri> = ArrayList()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            NineBxApplication.getPreferences().isPasswordEnabled = false
            if (resultCode == RESULT_OK) {
                val place = PlaceAutocomplete.getPlace(context, data)
                //AppLogger.i(TAG, "Place: " + place.name)
                etLocation.setText( place.name )
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                val status = PlaceAutocomplete.getStatus(context, data)
                // TODO: Handle the error.
                //AppLogger.i(TAG, status.statusMessage!!)

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
        if( rvAttachments == null ) {
            return
        }
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
                val actualFilePath = getPath(imageUri)
                val imageFile = File(actualFilePath)
                val thumbFile = File( imageFile.parent + "/" + imageFile.nameWithoutExtension + "_thumb." + imageFile.extension )
                saveFileTask( imageUri, thumbFile, object : AWSFileTransferHelper.FileOperationsCompletionListener {
                    override fun onSuccess(outputFile: File?) {
                        val renameFile = File(imageFile.parent + "/" + imageFile.nameWithoutExtension + "_normal." + imageFile.extension)
                        //AppLogger.d(TAG, "New File Name : " + renameFile.absolutePath)
                        imageFile.renameTo(renameFile)
                        mCalendarEvent.backingImages.add(RealmString(thumbFile.name))
                        mCalendarEvent.backingImages.add(RealmString(imageFile.name))
                        mAWSFileTransferHelper.beginUpload(thumbFile.absolutePath)
                        mAWSFileTransferHelper.beginUpload(imageFile.absolutePath)
                    }

                })
            }
        }
    }


    var PLACE_AUTOCOMPLETE_REQUEST_CODE : Int = 1

    private fun changeDateFormat(isAllDay: Boolean) {
        if( startDateCalendar != null ) {

            if( isAllDay ) {
                startDateCalendar!!.set(Calendar.HOUR_OF_DAY, 0)
                startDateCalendar!!.set(Calendar.MINUTE, 0)
            }
            setDateTime(tvStarts, startDateCalendar!!, isAllDay)

            if( endDateCalendar != null ) {
                if( isAllDay ) {
                    endDateCalendar!!.set(Calendar.HOUR_OF_DAY, 0)
                    endDateCalendar!!.set(Calendar.MINUTE, 0)
                }
                setDateTime(tvEnds, endDateCalendar!!, isAllDay)
            }


        }

    }

    private fun setValues() {

        if( !isAddEvent ) {

            if( mCalendarEvent.startsDate.size > 0 ) {
                startDateCalendar = Calendar.getInstance()
                startDateCalendar!!.time = parseDateForFormat(mCalendarEvent.startsDate[mSelectedDateIndex]!!, DATE_FORMAT)
            }

            if( mCalendarEvent.endsDate.size > 0 ) {
                endDateCalendar = Calendar.getInstance()
                endDateCalendar!!.time = parseDateForFormat(mCalendarEvent.endsDate[mSelectedDateIndex]!!, DATE_FORMAT)
            }

            etTitle.setText(  mCalendarEvent.title[mSelectedDateIndex]!! )
            etLocation.setText( mCalendarEvent.location[mSelectedDateIndex]!! )
            tvStarts.text = (mCalendarEvent.startsDate[mSelectedDateIndex]!!)
            tvEnds.text = (mCalendarEvent.endsDate[mSelectedDateIndex]!!)
            tvRepeat.text = mCalendarEvent.repeats[mSelectedDateIndex]!!
            if( mCalendarEvent.endRepeat.size > 0 ) {
                tvEndRepeat.text = mCalendarEvent.endRepeat[mSelectedDateIndex]!!
                layoutEndRepeat.show()
            }
            switchAllDay.isSelected = mCalendarEvent.isAllDay[mSelectedDateIndex]!!
            etNotes.setText( mCalendarEvent.notes[mSelectedDateIndex]!! )

            hideShowEndRepeat()

            tvReminder.text = mCalendarEvent.reminder[mSelectedDateIndex]!!
            showAttachments()
        }
    }

    private fun showAttachments() {
        for( attachmentName in mCalendarEvent.backingImages ) {

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

        if( tvStarts.text.toString().isEmpty() ) {
            tvStarts.requestFocus()
            context!!.showToast(R.string.error_empty_start_date)
            return false
        }

        if( tvEnds.text.toString().isEmpty() ) {
            tvEnds.requestFocus()
            context!!.showToast(R.string.error_empty_end_date)
            return false
        }

        return true
    }

    private fun saveCalendarEvent() {

        /*calendarRealm.beginTransaction()*/

        if( isAddEvent ) mCalendarEvent.id = getUniqueId()
        else {
            val id = mCalendarEvent.id
            mCalendarEvent = CalendarEvents()
            mCalendarEvent.id = id
        }

        val startRepeatEvent = tvRepeat.text.toString().trim()
        val endRepeatEvent = tvEndRepeat.text.toString().trim()
        val isAllDay = switchAllDay.isChecked
        var maxRepeatDays = 60

        val eventTitle = etTitle.text.toString().trim()
        val eventLocation = etLocation.text.toString().trim()
        val eventNotes = etNotes.text.toString().trim()
        val eventReminder = tvReminder.text.toString().trim()
        val eventReminderSet = tvReminder.text.toString().trim() != "None"

        if( startRepeatEvent != "Never" ) {
            //Set repeating events here
            val startDate = startDateCalendar!!.time
            if( endRepeatEvent != "Never" ) {
                //val endDate = endDateCalendar!!.time
                val lastDate = Calendar.getInstance()
                lastDate.set(Calendar.MONTH, endRepeatEvent.subSequence(0,2).toString().toInt() - 1)
                lastDate.set(Calendar.DATE, endRepeatEvent.subSequence(3,5).toString().toInt())
                lastDate.set(Calendar.YEAR, endRepeatEvent.substring(6).toInt())
                val noOfDays = getDateDifference(startDate, lastDate.time)
                if( noOfDays > 0 ) {
                    maxRepeatDays = when( startRepeatEvent ) {
                        REMINDER_EveryDay -> noOfDays
                        REMINDER_EveryWeek -> noOfDays / 7
                        REMINDER_Every2Weeks -> noOfDays / 14
                        REMINDER_EveryMonth -> noOfDays / 30
                        REMINDER_EveryYear -> noOfDays / 365
                        else -> maxRepeatDays
                    }
                }
                sheduleRepeatsInBackground(maxRepeatDays, startRepeatEvent, eventTitle, eventLocation,
                        eventNotes, eventReminder, eventReminderSet, endRepeatEvent,
                        isAllDay)
            }
            else {
                //startRepeat is set ........ endRepeat not set
                sheduleRepeatsInBackground(maxRepeatDays, startRepeatEvent, eventTitle, eventLocation,
                        eventNotes, eventReminder, eventReminderSet, endRepeatEvent,
                        isAllDay)
            }
        }
        else {
            //Non repeating events here
            mCalendarEvent.title.add(eventTitle)
            mCalendarEvent.location.add(eventLocation)
            mCalendarEvent.notes.add(eventNotes)
            mCalendarEvent.reminder.add(eventReminder)
            mCalendarEvent.isReminderSet.add(eventReminderSet.toString())
            mCalendarEvent.startsDate.add(parseDateForFormat(startDateCalendar!!.time, DATE_FORMAT))
            mCalendarEvent.endsDate.add(parseDateForFormat(endDateCalendar!!.time, DATE_FORMAT))
            mCalendarEvent.repeats.add(startRepeatEvent)
            mCalendarEvent.endRepeat.add(endRepeatEvent)
            mCalendarEvent.eventID.add("0")
            mCalendarEvent.isAllDay.add(isAllDay)
            AlarmJob.scheduleJob( mCalendarEvent, startDateCalendar!! )
            calendarRealm.beginTransaction()
            uploadImageAws()
            calendarRealm.copyToRealmOrUpdate(mCalendarEvent)
            //mCalendarEvent.insertOrUpdate( calendarRealm )
            calendarRealm.commitTransaction()
        }

        /*uploadImageAws()
        calendarRealm.copyToRealmOrUpdate(mCalendarEvent)
        //mCalendarEvent.insertOrUpdate( calendarRealm )
        calendarRealm.commitTransaction()*/
        goBack()
    }

    @SuppressLint("StaticFieldLeak")
    private fun sheduleRepeatsInBackground(maxRepeatDays: Int, startRepeatEvent: String, eventTitle: String,
                                           eventLocation: String, eventNotes: String, eventReminder: String,
                                           eventReminderSet: Boolean, endRepeatEvent: String, isAllDay: Boolean) {

        for( index in 0..maxRepeatDays ) {

            var eventStartDate: Date? = null
            var eventEndDate: Date? = null

            when (startRepeatEvent) {
                REMINDER_EveryDay -> {
                    if(index == 0) {
                        startDateCalendar!!.add(Calendar.DATE, index)
                        endDateCalendar!!.add(Calendar.DATE, index)
                    } else {
                        startDateCalendar!!.add(Calendar.DATE, 1)
                        endDateCalendar!!.add(Calendar.DATE, 1)
                    }
                    eventStartDate = startDateCalendar!!.time
                    eventEndDate = endDateCalendar!!.time
                }
                REMINDER_EveryWeek -> {
                    if(index == 0) {
                        startDateCalendar!!.add(Calendar.DATE, index * 7)
                        endDateCalendar!!.add(Calendar.DATE, index * 7)
                    } else {
                        startDateCalendar!!.add(Calendar.DATE, 7)
                        endDateCalendar!!.add(Calendar.DATE, 7)
                    }
                    eventStartDate = startDateCalendar!!.time
                    eventEndDate = endDateCalendar!!.time
                }
                REMINDER_Every2Weeks -> {
                    if(index == 0) {
                        startDateCalendar!!.add(Calendar.DATE, index)
                        endDateCalendar!!.add(Calendar.DATE, index)
                    } else {
                        startDateCalendar!!.add(Calendar.DATE, 14)
                        endDateCalendar!!.add(Calendar.DATE, 14)
                    }
                    eventStartDate = startDateCalendar!!.time
                    eventEndDate = endDateCalendar!!.time
                }
                REMINDER_EveryMonth -> {
                    if(index == 0) {
                        startDateCalendar!!.add(Calendar.MONTH, index)
                        endDateCalendar!!.add(Calendar.MONTH, index)
                    } else {
                        startDateCalendar!!.add(Calendar.MONTH, 1)
                        endDateCalendar!!.add(Calendar.MONTH, 1)
                    }
                    eventStartDate = startDateCalendar!!.time
                    eventEndDate = endDateCalendar!!.time
                }
                REMINDER_EveryYear -> {
                    if(index == 0) {
                        startDateCalendar!!.add(Calendar.YEAR, index)
                        endDateCalendar!!.add(Calendar.YEAR, index)
                    } else {
                        startDateCalendar!!.add(Calendar.YEAR, 1)
                        endDateCalendar!!.add(Calendar.YEAR, 1)
                    }
                    eventStartDate = startDateCalendar!!.time
                    eventEndDate = endDateCalendar!!.time
                }
            }

            mCalendarEvent.title.add(eventTitle)
            mCalendarEvent.location.add(eventLocation)
            mCalendarEvent.notes.add(eventNotes)
            mCalendarEvent.reminder.add(eventReminder)
            mCalendarEvent.isReminderSet.add(eventReminderSet.toString())
            mCalendarEvent.startsDate.add(parseDateForFormat(eventStartDate!!, DATE_FORMAT))
            mCalendarEvent.endsDate.add(parseDateForFormat(eventEndDate!!, DATE_FORMAT))
            mCalendarEvent.repeats.add(startRepeatEvent)
            mCalendarEvent.endRepeat.add(endRepeatEvent)
            if( startRepeatEvent != "Never" ) {
                if( endRepeatEvent != "Never") {
                    mCalendarEvent.eventID.add(index.toString())
                } else {
                    mCalendarEvent.eventID.add("0")
                }
            }
            mCalendarEvent.isAllDay.add(isAllDay)
            AlarmJob.scheduleJob(mCalendarEvent, startDateCalendar!!)
        }
        calendarRealm.beginTransaction()
        uploadImageAws()
        calendarRealm.copyToRealmOrUpdate(mCalendarEvent)
        //mCalendarEvent.insertOrUpdate( calendarRealm )
        calendarRealm.commitTransaction()

    }

    fun goBack() {
        NineBxApplication.getPreferences().isPasswordEnabled = false
        //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.calendar))

        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.onBackPressed()
    }

    lateinit var optionsDialogFragment: CalendarBottomFragment
    private val PICK_IMAGE_REQUEST = 234
    private val CAMERA_REQUEST_CODE = 235
    private val PERMISSIONS_REQUEST_CODE_CAMERA = 111
    private val PERMISSIONS_REQUEST_CODE_GALLERY = 112

    private fun startCameraIntent() {
        optionsDialogFragment.show(childFragmentManager, "optionsDialogFragment")
    }

    override fun onOptionSelected(position: Int) {
        optionsDialogFragment.dismiss()
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

    override fun onSuccess(outputFile: File?) {
        if( !mImagesList.contains(Uri.fromFile(outputFile)))
            mImagesList.add(Uri.fromFile(outputFile))
        setImagesAdapter()
    }

    fun setCalendarEvent(event: CalendarEvents) {
        mCalendarEvent = event
    }
}