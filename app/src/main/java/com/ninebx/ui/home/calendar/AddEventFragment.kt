package com.ninebx.ui.home.calendar

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.HomeView
import com.ninebx.ui.home.calendar.model.CalendarEvents
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_add_calendar_every.*

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

    }

    private fun setValues(mCalendarEvent: CalendarEvents) {
        if( !isAddEvent ) {
            rvAttachments.layoutManager = LinearLayoutManager(context)
            rvAttachments.adapter = DayEventsRecyclerViewAdapter(4)
        }
    }

    private fun validate(): Boolean {
        return true
    }

    private fun saveCalendarEvent() {
        goBack()
    }

    fun goBack() {
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.calendar))
        NineBxApplication.instance.activityInstance!!.showToolbar()
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.onBackPressed()
    }

}