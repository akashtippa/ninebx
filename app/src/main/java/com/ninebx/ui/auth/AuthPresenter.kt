package com.ninebx.ui.auth

import android.os.AsyncTask
import com.ninebx.ui.auth.email.SendEmailTask
import com.ninebx.ui.base.realm.Users
import com.ninebx.utility.*

/**
 * Created by Alok on 03/01/18.
 */
class AuthPresenter(private val authView: AuthView){

    private var TAG = AuthPresenter::class.java.simpleName

    init {
        authView.navigateToSignIn()
    }

    fun signUp(userName: String, password: String) {
        LoginSignupTask(userName, password, authView, "Signup").executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, null)
    }

    fun createUser(email: String, firstName: String, lastName: String): Users {
        return Users.createUser(email, firstName, lastName)
    }

    fun signIn(userName: String, password: String) {
        LoginSignupTask(userName, password, authView, "Signin").executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, null)
    }

    fun requestOTP(accountEmail: String) {
        val emailOTP = generateRandomOTP()
        SendEmailTask(emailOTP, accountEmail, authView).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, null)
    }

}