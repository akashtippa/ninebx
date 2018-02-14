package com.ninebx.ui.home.account.addmembers

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.Member
import io.realm.SyncUser

/**
 * Created by Alok on 14/02/18.
 */
interface MemberView : BaseView {
    fun onMemberSignup(user: SyncUser)
    fun onNewMember(member: Member)
    fun onConfirmPassword(password: String)
    fun showError(error: String)
}