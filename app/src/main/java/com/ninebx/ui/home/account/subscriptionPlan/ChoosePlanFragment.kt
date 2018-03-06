package com.ninebx.ui.home.account.subscriptionPlan

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.ninebx.R
import com.ninebx.ui.tutorial.fragment.FragmentOrganized
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_subscription_holder.*
import kotlinx.android.synthetic.main.pager_choose_plan.*
import com.android.billingclient.api.BillingClient.BillingResponse
import com.android.billingclient.api.BillingClientStateListener
import com.module.inappbilling.billing.BillingManager
import com.module.inappbilling.billing.BillingProvider
import com.ninebx.utility.AppLogger


/**
* Created by TechnoBlogger on 03/01/18.
*/
class ChoosePlanFragment : FragmentBackHelper() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pager_choose_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFree.setOnClickListener {
            Toast.makeText(context, "btnFree", Toast.LENGTH_LONG).show()
        }
        btnOnePlusPerMonth.setOnClickListener {
            Toast.makeText(context, "btnOnePlusPerMonth", Toast.LENGTH_LONG).show()
        }
        btnOnePlusPerYear.setOnClickListener {
            Toast.makeText(context, "btnOnePlusPerYear", Toast.LENGTH_LONG).show()
        }
        btnFamilyPerMonth.setOnClickListener {
            Toast.makeText(context, "btnFamilyPerMonth", Toast.LENGTH_LONG).show()
        }
        btnFamilyPerYear.setOnClickListener {
            Toast.makeText(context, "btnFamilyPerYear", Toast.LENGTH_LONG).show()
        }
        btnFamilyPlusPerMonth.setOnClickListener {
            Toast.makeText(context, "btnFamilyPlusPerMonth", Toast.LENGTH_LONG).show()
        }
        btnFamilyPlusPerYear.setOnClickListener {
            Toast.makeText(context, "btnFamilyPlusPerYear", Toast.LENGTH_LONG).show()
        }

    }

    companion object {
        // newInstance constructor for creating fragment with arguments
        fun newInstance(): ChoosePlanFragment {
            val fragmentFirst = ChoosePlanFragment()
            val args = Bundle()
            fragmentFirst.arguments = args
            return fragmentFirst
        }
    }


}