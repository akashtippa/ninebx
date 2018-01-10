package com.ninebx.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.auth.SignUpFragment
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.home.account.AccountFragment
import com.ninebx.ui.home.adapter.HomeViewPagerAdapter
import com.ninebx.ui.home.calendar.CalendarFragment
import com.ninebx.ui.home.fragments.contacts.ContactsFragment
import com.ninebx.ui.home.fragments.interests.InterestFragment
import com.ninebx.ui.home.fragments.memories.MemoriesFragment
import com.ninebx.ui.home.fragments.personal.PersonalFragment
import com.ninebx.ui.home.lists.ListsFragment
import com.ninebx.ui.home.notifications.NotificationsFragment
import com.ninebx.ui.home.search.SearchFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*

/***
 * Created by TechnoBlogger on 08/01/18.
 */
class HomeFragment : Fragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.layoutHome -> {
                callBottomViewFragment(getString(R.string.home_amp_money))
            }
            R.id.layoutTravell -> {
                callBottomViewFragment(getString(R.string.travel))
            }
            R.id.layoutContacts -> {
                callBottomViewFragment(getString(R.string.contacts))
            }
            R.id.layoutEducation -> {
                callBottomViewFragment(getString(R.string.education_work))
            }
            R.id.layoutPersonal -> {
                callBottomViewFragment(getString(R.string.personal))
            }
            R.id.layoutInterests -> {
                callBottomViewFragment(getString(R.string.interests))
            }
            R.id.layoutWellness -> {
                callBottomViewFragment(getString(R.string.wellness))
            }
            R.id.layoutMemories -> {
                callBottomViewFragment(getString(R.string.memories))
            }
            R.id.layoutShopping -> {
                callBottomViewFragment(getString(R.string.shopping))
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

//        setupPager()

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


    private fun callBottomViewFragment(option: String) {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        when (option) {
            getString(R.string.home_amp_money) -> {
//                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Interests")
//                fragmentTransaction.replace(R.id.frameLayout, InterestFragment()).commit()
            }
            getString(R.string.travel) -> {
//                fragmentTransaction.replace(R.id.frameLayout, CalendarFragment()).commit()
            }
            getString(R.string.contacts) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.contacts))
                fragmentTransaction.add(R.id.frameLayout, ContactsFragment()).commit()
            }
            getString(R.string.education_work) -> {
//                fragmentTransaction.replace(R.id.frameLayout, NotificationsFragment()).commit()
            }
            getString(R.string.personal) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.personal))
                fragmentTransaction.add(R.id.frameLayout, PersonalFragment()).commit()
            }
            getString(R.string.interests) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.interests))
                fragmentTransaction.add(R.id.frameLayout, InterestFragment()).commit()
            }
            getString(R.string.wellness) -> {

            }
            getString(R.string.memories) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.memories))
                fragmentTransaction.add(R.id.frameLayout, MemoriesFragment()).commit()
            }
            getString(R.string.shopping) -> {

            }
        }
    }


}