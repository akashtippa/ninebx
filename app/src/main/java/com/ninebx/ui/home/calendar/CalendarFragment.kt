package com.ninebx.ui.home.calendar

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.utility.AppLogger
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alok on 03/01/18.
 */
class CalendarFragment : Fragment(), CalendarView, DaysAdapterClickListener {


    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {

    }

    override fun onError(error: Int) {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_calendar, container, false)
    }

    private lateinit var mMonthFormat: SimpleDateFormat
    private lateinit var mPrevMonth : String
    private var mCalendar = Calendar.getInstance()
    private var isWeekView = false
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMonthFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

        ivPreviousMonth.setOnClickListener {
            if( isWeekView ) {
                mCalendar.add( Calendar.WEEK_OF_YEAR, -1 )
                if( mPrevMonth != mMonthFormat.format(mCalendar.time) ) {
                    mCalendar.set(Calendar.DAY_OF_MONTH, mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
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

    }

    private val TAG: String = CalendarFragment::class.java.simpleName
    private var mDaysRecyclerAdapter : DaysRecyclerViewAdapter ?= null

    private fun setDaysAdapter(selectedDate: Int, weekOfMonth: Int) {

        isWeekView = tvWeekMonth.text.toString() == "Month"
        val monthStartDate = mCalendar
        monthStartDate.set(Calendar.DAY_OF_MONTH, 1)

        if( isWeekView ) {
            //Need separate adapter for display of weeks
            mDaysRecyclerAdapter!!.toggleWeekView( selectedDate, weekOfMonth, isWeekView )
        }
        else {
            mDaysRecyclerAdapter = DaysRecyclerViewAdapter(
                    mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH),
                    monthStartDate.get(Calendar.DAY_OF_WEEK),
                    selectedDate,
                    isWeekView,
                    weekOfMonth,
                    this )
            rvDays.adapter = mDaysRecyclerAdapter
        }


        onDayClick(selectedDate)

        AppLogger.d(TAG, "Selected Date : " + selectedDate + " : Date in month : " + mCalendar.get(Calendar.DATE))

    }

    override fun onDayClick(dayOfMonth: Int) {
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        rvDayEvents.adapter = DayEventsRecyclerViewAdapter( mCalendar.get(Calendar.DAY_OF_MONTH) % 7 )
    }

}