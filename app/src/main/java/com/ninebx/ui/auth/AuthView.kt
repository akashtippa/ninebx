package com.ninebx.ui.auth

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.Users
import io.realm.SyncUser

/**
 * Created by Alok on 03/01/18.
 */
interface AuthView : BaseView {
    fun navigateToSignUp()
    fun navigateToSignIn()
    fun navigateToHome()
    fun navigateToOTP()
    fun navigateToAccountPassword( users : Users )
    fun navigateToCreatePassCode(isCreatePassCode: Boolean, passCode: String)
    fun navigateToFingerPrint()
    fun navigateToInvitePeople()
    fun getAuthPresenter() : AuthPresenter
    fun onSuccess( syncUser: SyncUser? )
    fun onError( error : String )
    fun getAccountEmail() : String
    fun setAccountEmail(accountEmail: String)
    fun createUser(firstName: String, lastName: String, email: String)
    fun validateEmailOTP(emailOtp: String)
    fun navigateToStart()
    fun fingerPrintCancelled()
}