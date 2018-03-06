package com.ninebx.ui.home.account.subscriptionPlan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.utility.FragmentBackHelper

/**
 * Created by Alok on 03/01/18.
 */
class ComparePlanFragment : FragmentBackHelper() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pager_compare_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {
        // newInstance constructor for creating fragment with arguments
        fun newInstance(): ComparePlanFragment {
            val fragmentFirst = ComparePlanFragment()
            val args = Bundle()
            fragmentFirst.arguments = args
            return fragmentFirst
        }
    }


}