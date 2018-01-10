package com.ninebx.utility

import android.support.v4.app.Fragment

/***
 * Created by TechnoBlogger on 10/01/18.
 */
open class FragmentBackHelper : Fragment(), IOnBackPressed {
    override fun onBackPressed(): Boolean {
        return true
    }
}