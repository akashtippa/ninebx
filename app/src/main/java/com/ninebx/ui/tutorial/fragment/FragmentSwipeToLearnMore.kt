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

class FragmentSwipeToLearnMore : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_swipe_to_learn, container, false)
    }

    companion object {

        // newInstance constructor for creating fragment with arguments
        fun newInstance(): FragmentSwipeToLearnMore {
            val fragmentFirst = FragmentSwipeToLearnMore()
            val args = Bundle()
            fragmentFirst.arguments = args
            return fragmentFirst
        }
    }
}