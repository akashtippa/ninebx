package com.ninebx.ui.home.account.changePassword

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.Users

/**
 * Created by Alok on 22/02/18.
 */
interface MasterPasswordView : BaseView {
    fun onPasswordReset( message : Int )
    fun getNewPassword() : String
    fun getCurrentPassword() : String
    fun getCurrentUsers() : ArrayList<Users>
}