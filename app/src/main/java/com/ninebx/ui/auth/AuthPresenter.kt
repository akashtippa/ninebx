package com.ninebx.ui.auth

import android.os.AsyncTask

/**
 * Created by Alok on 03/01/18.
 */
class AuthPresenter( private val authView: AuthView ) {

    init {
        authView.navigateToSignIn()
    }

    fun signUp(userName : String, password : String) {
        LoginTask(userName, password, authView ).executeOnExecutor( AsyncTask.SERIAL_EXECUTOR, null )
    }

    fun signIn( userName : String, password : String ) {
        LoginTask(userName, password, authView ).executeOnExecutor( AsyncTask.SERIAL_EXECUTOR, null )
    }
}