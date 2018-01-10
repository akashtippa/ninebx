package com.ninebx.ui.home.fragments.wellness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.utility.FragmentBackHelper

/***
 * Created by TechnoBlogger on 09/01/18.
 */
class WellnessFragment : FragmentBackHelper() {

    val titleText = "<font color=#263238>nine</font><font color=#FF00B0FF>bx</font>"


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_wellness, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(titleText)
        return super.onBackPressed()
    }
}