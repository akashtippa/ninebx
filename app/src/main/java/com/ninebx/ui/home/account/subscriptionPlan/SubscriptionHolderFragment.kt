package com.ninebx.ui.home.account.subscriptionPlan

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.view.ViewPager
import android.view.*
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.R.id.switchTouchId
import com.ninebx.ui.auth.AuthActivity
import com.ninebx.ui.home.BaseHomeFragment
import com.ninebx.ui.home.account.AccountView
import com.ninebx.ui.home.account.MyProfileFragment
import com.ninebx.ui.home.account.addmembers.AddFamilyUsersFragment
import com.ninebx.ui.home.account.changePassword.MasterPasswordFragment
import com.ninebx.ui.home.adapter.SubscriptionPlanAdapter
import com.ninebx.ui.tutorial.adapter.TutorialAdapter
import com.ninebx.ui.tutorial.view.CirclePageIndicator
import com.ninebx.utility.AWSFileTransferHelper
import com.ninebx.utility.Constants
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.closeAllConnections
import io.realm.SyncUser
import kotlinx.android.synthetic.main.fragment_subscription_holder.*
import java.io.File

/**
 * Created by Alok on 03/01/18.
 */
class SubscriptionHolderFragment : FragmentBackHelper() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_subscription_holder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Subscription plan")
        NineBxApplication.instance.activityInstance!!.hideHomeIcon()
        val viewPager = view.findViewById<View>(R.id.activity_help_view_pager) as ViewPager

        viewPager.adapter = SubscriptionAdapter(activity!!.supportFragmentManager)

        indicator_subscription.setViewPager(view_subscription!!)

        indicator_subscription.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }

        })

    }


}