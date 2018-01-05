package com.ninebx.ui.auth

import android.os.AsyncTask
import java.util.concurrent.Executor

/**
 * Created by Alok on 03/01/18.
 */
class AuthPresenter( private val authView: AuthView ) {

    init {
        authView.navigateToSignIn()
    }

    fun signUp() {

    }

    fun signIn( userName : String, password : String ) {
        LoginTask(userName, password, authView ).executeOnExecutor( AsyncTask.SERIAL_EXECUTOR, null )
    }
}