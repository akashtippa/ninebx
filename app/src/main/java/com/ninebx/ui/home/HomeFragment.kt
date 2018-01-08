package com.ninebx.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.ui.auth.SignUpFragment
import com.ninebx.ui.home.account.AccountFragment
import com.ninebx.ui.home.adapter.HomeViewPagerAdapter
import com.ninebx.ui.home.calendar.CalendarFragment
import com.ninebx.ui.home.lists.ListsFragment
import com.ninebx.ui.home.notifications.NotificationsFragment
import com.ninebx.ui.home.search.SearchFragment
import kotlinx.android.synthetic.main.fragment_home.*

/***
 * Created by TechnoBlogger on 08/01/18.
 */
class HomeFragment: Fragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.layoutHome -> {

            }
            R.id.layoutTravell -> {

            }
            R.id.layoutContacts -> {

            }
            R.id.layoutEducation -> {

            }
            R.id.layoutPersonal -> {

            }
            R.id.layoutInterests -> {

            }
            R.id.layoutWellness -> {

            }
            R.id.layoutMemories -> {

            }
            R.id.layoutShopping -> {

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutHome.setOnClickListener(this)
        layoutTravell.setOnClickListener(this)
        layoutContacts.setOnClickListener(this)
        layoutEducation.setOnClickListener(this)
        layoutPersonal.setOnClickListener(this)
        layoutInterests.setOnClickListener(this)
        layoutWellness.setOnClickListener(this)
        layoutMemories.setOnClickListener(this)
        layoutShopping.setOnClickListener(this)

        setupPager()

    }

    private fun setupPager() {
        val fragments = ArrayList<Fragment>()
        fragments.add(SearchFragment())
        fragments.add(CalendarFragment())
        fragments.add(ListsFragment())
        fragments.add(NotificationsFragment())
        fragments.add(AccountFragment())
        vpParent.adapter = HomeViewPagerAdapter(fragments, activity.supportFragmentManager)
        vpParent.setPagingEnabled(false)
    }


}