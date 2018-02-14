package com.ninebx.ui.home

import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by Alok on 13/02/18.
 */
abstract class BaseHomeFragment : Fragment() {

    lateinit var mHomeView: HomeView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is HomeView)
            mHomeView = context
    }
}