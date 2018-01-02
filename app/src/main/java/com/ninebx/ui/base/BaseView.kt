package com.ninebx.ui.base

/**
 * Created by Alok on 02/01/18.
 */
interface BaseView {
    fun showProgress( message : Int )
    fun hideProgress()
    fun onError( error : Int )
}