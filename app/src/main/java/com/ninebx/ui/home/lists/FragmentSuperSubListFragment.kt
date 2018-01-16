package com.ninebx.ui.home.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.utility.FragmentBackHelper

/***
 * Created by TechnoBlogger on 15/01/18.
 */
class FragmentSuperSubListFragment : FragmentBackHelper() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_super_sub_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}