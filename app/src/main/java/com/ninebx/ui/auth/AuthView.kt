package com.ninebx.ui.auth

import com.ninebx.ui.base.BaseView
import io.realm.SyncUser

/**
 * Created by Alok on 03/01/18.
 */
interface AuthView : BaseView {
    fun navigateToSignUp()
    fun navigateToSignIn()
    fun navigateToHome()
    fun getAuthPresenter() : AuthPresenter
    fun onSuccess( syncUser: SyncUser? )
}