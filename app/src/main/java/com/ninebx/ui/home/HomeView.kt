package com.ninebx.ui.home

import android.content.Context
import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.CalendarEvents
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.DecryptedCombine
import io.realm.RealmResults
import java.util.*

/**
 * Created by Alok on 16/01/18.
 */
interface HomeView : BaseView {
    fun addEditCalendarEvent(calendarEvent : CalendarEvents?, selectedDate : Date )
    fun getCurrentUsers(): RealmResults<Users>
    fun setNotificationCount(notificationCount: Int)
    fun getContextForScreen() : Context
    fun setCurrentUsers(currentUsers: RealmResults<Users>?)
    /*fun onCombineFetched(decryptCombine: DecryptedCombine)*/
}