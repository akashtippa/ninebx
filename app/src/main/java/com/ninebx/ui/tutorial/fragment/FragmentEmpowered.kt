package com.ninebx.ui.tutorial.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R

/***
 * Created by TechnoBlogger on 03/01/18.
 */

class FragmentEmpowered : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_empowered, container, false)
    }

    companion object {

        // newInstance constructor for creating fragment with arguments
        fun newInstance(): FragmentEmpowered {
            val fragmentFirst = FragmentEmpowered()
            val args = Bundle()
            fragmentFirst.arguments = args
            return fragmentFirst
        }
    }
}