package com.ninebx.ui.home.calendar

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alok on 03/01/18.
 */
class CalendarFragment : Fragment(), CalendarView {

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
    private val mCalendar = Calendar.getInstance()
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMonthFormat = SimpleDateFormat("MMMM YYYY", Locale.getDefault())

        ivPreviousMonth.setOnClickListener {
            mCalendar.add( Calendar.MONTH, -1 )
            tvMonthYear.text = mMonthFormat.format(mCalendar.time)
            setDaysAdapter()
        }

        ivNextMonth.setOnClickListener {
            mCalendar.add( Calendar.MONTH, 1 )
            tvMonthYear.text = mMonthFormat.format(mCalendar.time)
            setDaysAdapter()
        }

        tvMonthYear.text = mMonthFormat.format(mCalendar.time)

        rvDays.layoutManager = LinearLayoutManager(context)
        setDaysAdapter()

        rvDayEvents.layoutManager = LinearLayoutManager( context )
        rvDayEvents.adapter = DayEventsRecyclerViewAdapter()

    }

    private fun setDaysAdapter() {
        val monthStartDate = mCalendar
        monthStartDate.set(Calendar.DAY_OF_MONTH, 1)
        rvDays.adapter = DaysRecyclerViewAdapter( mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), monthStartDate.get(Calendar.DAY_OF_WEEK) )
    }

}