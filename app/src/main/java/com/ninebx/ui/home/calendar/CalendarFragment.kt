package com.ninebx.ui.home.calendar

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.R.string.date
import com.ninebx.ui.base.AdapterClickListener
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.home.BaseHomeFragment
import com.ninebx.ui.home.search.SearchFragment
import com.ninebx.utility.AppLogger
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Alok on 03/01/18.
 */
class CalendarFragment : BaseHomeFragment(), CalendarView, DaysAdapterClickListener {

    private lateinit var dateStringWithEvents: java.util.ArrayList<String>

    override fun setDateWithEvents(datesWithEvents: java.util.ArrayList<Date>, dateStringWithEvents: java.util.ArrayList<String>) {
        hideProgress()
        this.datesWithEvents = datesWithEvents
        this.dateStringWithEvents = dateStringWithEvents
        setupUI()
    }

    private fun setupUI() {
        if( ivPreviousMonth == null ) return

        mMonthFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

        ivPreviousMonth.setOnClickListener {
            if( isWeekView ) {
                mCalendar.add( Calendar.WEEK_OF_YEAR, -1 )

                if( mPrevMonth != mMonthFormat.format(mCalendar.time) ) {
                    //AppLogger.d(TAG, "Week dates : Max Date for month : " + mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                    mCalendar.set(Calendar.DAY_OF_MONTH, mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                    //AppLogger.d(TAG, "Week dates : Months : " + mPrevMonth + " : " + mMonthFormat.format(mCalendar.time) )
                    if( mPrevMonth.contains("January") && mMonthFormat.format(mCalendar.time).contains("December") ) {
                        isYearChange = true
                    }
                }
            }
            else {
                mCalendar.add( Calendar.MONTH, -1 )
            }
            tvMonthYear.text = mMonthFormat.format(mCalendar.time)
            mPrevMonth = tvMonthYear.text.toString()
            setDaysAdapter(mCalendar.get(Calendar.DATE), mCalendar.get(Calendar.WEEK_OF_MONTH))

        }

        ivNextMonth.setOnClickListener {
            if( isWeekView ) {
                mCalendar.add( Calendar.WEEK_OF_YEAR, 1 )
                if( mPrevMonth != mMonthFormat.format(mCalendar.time) ) {
                    mCalendar.set(Calendar.DAY_OF_MONTH, 1)
                }
            }
            else
                mCalendar.add( Calendar.MONTH, 1 )
            tvMonthYear.text = mMonthFormat.format(mCalendar.time)
            mPrevMonth = tvMonthYear.text.toString()
            setDaysAdapter(mCalendar.get(Calendar.DATE), mCalendar.get(Calendar.WEEK_OF_MONTH))
        }

        tvMonthYear.text = mMonthFormat.format(mCalendar.time)
        mPrevMonth = tvMonthYear.text.toString()

        rvDayEvents.layoutManager = LinearLayoutManager( context )
        //rvDayEvents.adapter = DayEventsRecyclerViewAdapter( mCalendar.get(Calendar.DAY_OF_MONTH) % 7 )

        rvDays.layoutManager = LinearLayoutManager(context)
        setDaysAdapter(mCalendar.get(Calendar.DATE), mCalendar.get(Calendar.WEEK_OF_MONTH))

        tvToday.setOnClickListener {
            mCalendar = Calendar.getInstance()
            tvMonthYear.text = mMonthFormat.format(mCalendar.time)
            mPrevMonth = tvMonthYear.text.toString()
            if( isWeekView ) {
                tvWeekMonth.callOnClick()
            }
            else {
                setDaysAdapter(mCalendar.get(Calendar.DATE), mCalendar.get(Calendar.WEEK_OF_MONTH))
            }
        }

        tvWeekMonth.setOnClickListener {
            when( tvWeekMonth.text.toString() ) {
                "Week" -> {
                    tvWeekMonth.text = getString(R.string.month)
                    setDaysAdapter(mCalendar.get(Calendar.DATE), mCalendar.get(Calendar.WEEK_OF_MONTH))
                }
                "Month" -> {
                    tvWeekMonth.text = getString(R.string.week)
                    setDaysAdapter(mCalendar.get(Calendar.DATE), mCalendar.get(Calendar.WEEK_OF_MONTH))
                }
            }
        }

        tvAddEvent.setOnClickListener {
            mHomeView.addEditCalendarEvent( null, Date() )
        }
    }


    override fun showProgress(message: Int) {
        if( progressLayout != null )
            progressLayout.show()
    }

    override fun hideProgress() {
        if( progressLayout != null )
            progressLayout.hide()
    }

    override fun onError(error: Int) {
        hideProgress()
        if( context != null )
            context!!.showToast(error)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }
    private lateinit var datesWithEvents: ArrayList<Date>
    private lateinit var mMonthFormat: SimpleDateFormat
    private lateinit var mPrevMonth : String
    private var mCalendarPresenter : CalendarPresenter ?= null
    private var mCalendar = Calendar.getInstance()
    private var isWeekView = false
    private var isYearChange = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivHome.setOnClickListener { NineBxApplication.instance.activityInstance!!.callHomeFragment() }
        mCalendarPresenter = CalendarPresenter(this)
    }

    private val TAG: String = CalendarFragment::class.java.simpleName
    private var mDaysRecyclerAdapter : DaysRecyclerViewAdapter ?= null
    private var mWeekDaysRecyclerAdpater : WeekDaysRecyclerViewAdapter ?= null

    private fun setDaysAdapter(selectedDate: Int, weekOfMonth: Int) {

        if( tvWeekMonth == null ) return

        isWeekView = tvWeekMonth.text.toString() == "Month"


        if( isWeekView ) {
            //Need separate adapter for display of weeks
            val weekCalendar = Calendar.getInstance()
            weekCalendar.clear()
            if( isYearChange ) {
                weekCalendar.set(Calendar.WEEK_OF_YEAR, mCalendar.getMaximum(Calendar.WEEK_OF_YEAR) - 1)
                isYearChange = false
            }
            else
                weekCalendar.set(Calendar.WEEK_OF_YEAR, mCalendar.get(Calendar.WEEK_OF_YEAR))
            weekCalendar.set(Calendar.YEAR, mCalendar.get(Calendar.YEAR))

            val weekDates = ArrayList<Int>()
            val minDate = weekCalendar.get(Calendar.DATE)
            weekDates.add(minDate)
            for( i in 1 until 7 ) {
                weekCalendar.add(Calendar.DAY_OF_MONTH, 1)
                weekDates.add(weekCalendar.get(Calendar.DATE))
            }

            val maxDate = weekCalendar.get(Calendar.DATE)

            //AppLogger.d(TAG, "Week dates : " + minDate + " : " + maxDate )
            //AppLogger.d(TAG, "Week dates : List : " + weekDates )
            //Create a list of dates to be passed across.

            mWeekDaysRecyclerAdpater = WeekDaysRecyclerViewAdapter(
                    weekDates,
                    selectedDate,
                    mCalendar.get(Calendar.MONTH),
                    mCalendar.get(Calendar.YEAR),
                    datesWithEvents,
                    dateStringWithEvents,
                    this)
            rvDays.adapter = mWeekDaysRecyclerAdpater
            //mDaysRecyclerAdapter!!.toggleWeekView( selectedDate, weekOfMonth, isWeekView )
        }
        else {
            val monthStartDate = mCalendar
            monthStartDate.set(Calendar.DAY_OF_MONTH, 1)
            mDaysRecyclerAdapter = DaysRecyclerViewAdapter(
                    mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH),
                    monthStartDate.get(Calendar.DAY_OF_WEEK),
                    mCalendar.get(Calendar.MONTH),
                    mCalendar.get(Calendar.YEAR),
                    datesWithEvents,
                    dateStringWithEvents,
                    selectedDate,
                    isWeekView,
                    weekOfMonth,
                    this )
            rvDays.adapter = mDaysRecyclerAdapter
        }


        onDayClick(selectedDate)

        //AppLogger.d(TAG, "Week dates : Selected Date : " + selectedDate + " : Date in month : " + mCalendar.get(Calendar.DATE))

    }

     lateinit var mDayEventsAdapter: DayEventsRecyclerViewAdapter

    override fun onDayClick(dayOfMonth: Int) {
        if( mCalendarPresenter != null && rvDayEvents != null ) {
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val eventsForDate = mCalendarPresenter!!.getEventsForDate(mCalendar.time)


            mDayEventsAdapter = DayEventsRecyclerViewAdapter( eventsForDate, mCalendar.time , object : AdapterClickListener {
                override fun onItemClick(position: Int) {
                    val calendarEvent = mDayEventsAdapter.getItemAtPosition(position)
                    mHomeView.addEditCalendarEvent(
                            calendarEvent,
                            mDayEventsAdapter.getSelectedDateForEvent() )
                }
            })
            rvDayEvents.adapter = mDayEventsAdapter
        }
    }

    companion object {
        fun getCalendarInstance() : CalendarFragment {
            if( instance == null ) {
                instance = CalendarFragment()
            }
            return instance!!
        }
        var instance : CalendarFragment? = null

    }
}