package com.ninebx.ui.auth

import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by Alok on 03/01/18.
 */
abstract class BaseAuthFragment : Fragment() {

    lateinit var mAuthView : AuthView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if( context is AuthView )
            mAuthView = context
    }

    abstract fun validate() : Boolean

}