package com.ninebx.ui.home.calendar

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.HomeView
import com.ninebx.ui.home.calendar.model.CalendarEvents
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.getDateMonthYearFormat
import kotlinx.android.synthetic.main.fragment_add_calendar_every.*
import android.support.v4.app.ShareCompat.IntentBuilder
import android.content.Intent
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import android.app.Activity.RESULT_CANCELED
import com.google.android.gms.location.places.ui.PlaceAutocomplete.getStatus
import com.google.android.gms.location.places.Place
import android.app.Activity.RESULT_OK
import android.view.MotionEvent
import com.ninebx.utility.AppLogger


/***
 * Created by Alok Omkar on 10/01/18.
 */
class AddEventFragment : FragmentBackHelper() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_add_calendar_every, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if( context is HomeView)
            mHomeView = context
    }

    private lateinit var mHomeView : HomeView
    private lateinit var mCalendarEvent : CalendarEvents
    private var isAddEvent = false

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        mCalendarEvent = arguments.getParcelable<CalendarEvents>("calendarEvent")
        isAddEvent = arguments.getBoolean("isAddEvent", false)

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
        layoutRepeat.setOnClickListener {  }
        layoutEndRepeat.setOnClickListener {  }
        layoutReminder.setOnClickListener {  }

        rvAttachments.layoutManager = LinearLayoutManager(context)
        rvAttachments.adapter = DayEventsRecyclerViewAdapter(4)

        etLocation.setOnTouchListener{ _, event ->
            if( event.action == MotionEvent.ACTION_UP ) {
                val DRAWABLE_RIGHT = 2;
                if(event.getRawX() >= (etLocation.getRight() - etLocation.compoundDrawables[DRAWABLE_RIGHT].getBounds().width()))
                {
                    try {
                        NineBxApplication.getPreferences().isMapsShown = true
                        val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                .build(activity)
                        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
                    } catch (e: GooglePlayServicesRepairableException) {
                        AppLogger.e(TAG, "GooglePlayServicesRepairableException: " + e.message)
                        e.printStackTrace()
                        NineBxApplication.getPreferences().isMapsShown = false
                    } catch (e: GooglePlayServicesNotAvailableException) {
                        AppLogger.e(TAG, "GooglePlayServicesNotAvailableException: " + e.message)
                        e.printStackTrace()
                        NineBxApplication.getPreferences().isMapsShown = false
                    }
                    true
                }
                else
                    false
            }
            else false
        }
    }

    private val TAG: String = AddEventFragment::class.java.simpleName

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            NineBxApplication.getPreferences().isMapsShown = false
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
        else
            super.onActivityResult(requestCode, resultCode, data)
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


        }
    }

    private fun validate(): Boolean {
        return true
    }

    private fun saveCalendarEvent() {
        goBack()
    }

    fun goBack() {
        NineBxApplication.getPreferences().isMapsShown = false
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.calendar))
        NineBxApplication.instance.activityInstance!!.showToolbar()
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.onBackPressed()
    }

}