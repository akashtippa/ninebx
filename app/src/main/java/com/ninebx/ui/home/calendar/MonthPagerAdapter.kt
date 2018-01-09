package com.ninebx.ui.home.calendar

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by Alok on 09/01/18.
 */
class MonthPagerAdapter( private val fragments : ArrayList<Fragment>, private val fragmentManager: FragmentManager ) : FragmentStatePagerAdapter( fragmentManager ) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
       return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "January"
    }
}