package com.ninebx.ui.home.account.subscriptionPlan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.ninebx.R
import kotlinx.android.synthetic.main.fragment_subscription_holder.*

class SubscriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_subscription_holder)

        val viewPager = findViewById<View>(R.id.view_subscription) as ViewPager

        viewPager.adapter = SubscriptionAdapter(supportFragmentManager)

        indicator_subscription.setViewPager(view_subscription!!)

        indicator_subscription.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }

        })
        ivBack.setOnClickListener {
            finish()
        }

    }
}
