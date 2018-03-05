package com.ninebx.ui.home.account.subscriptionPlan

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.ui.tutorial.fragment.FragmentOrganized
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_subscription_holder.*
import kotlinx.android.synthetic.main.pager_choose_plan.*

/**
 * Created by Alok on 03/01/18.
 */
class ChoosePlanFragment : FragmentBackHelper() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pager_choose_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFree.setOnClickListener {

        }
        btnOnePlusPerMonth.setOnClickListener {

        }
        btnOnePlusPerYear.setOnClickListener {

        }
        btnFamilyPerMonth.setOnClickListener {

        }
        btnFamilyPerYear.setOnClickListener {

        }
        btnFamilyPlusPerMonth.setOnClickListener {

        }
        btnFamilyPlusPerYear.setOnClickListener {

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