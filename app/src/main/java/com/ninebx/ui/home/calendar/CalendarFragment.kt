package com.ninebx.ui.home.calendar

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import kotlinx.android.synthetic.main.fragment_calendar.*

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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vpMonths.setPagingEnabled(true)
        val fragments = ArrayList<Fragment>()
        fragments.add(MonthFragment())
        fragments.add(MonthFragment())
        fragments.add(MonthFragment())
        vpMonths.adapter = MonthPagerAdapter( fragments, childFragmentManager )
        tabMonths.setViewPager(vpMonths)
        vpMonths.offscreenPageLimit = 3
        rvDayEvents.layoutManager = LinearLayoutManager( context )
        rvDayEvents.adapter = DayEventsRecyclerViewAdapter()

    }

}