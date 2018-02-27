package com.ninebx.ui.home

import android.content.Context
import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.CalendarEvents
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.*
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

    fun onCombineHomeFetched(mDecryptCombineHome: DecryptedCombine)
    fun onCombineTravelFetched(mDecryptCombineTravel: DecryptedCombineTravel)
    fun onCombineContactsFetched(mDecryptCombineContacts: DecryptedCombineContacts)
    fun onCombinePersonalFetched(mDecryptCombinePersonal: DecryptedCombinePersonal)
    fun onCombineWellnessFetched(mDecryptCombineWellness: DecryptedCombineWellness)
    fun onCombineEducationFetched(mDecryptCombineEducation: DecryptedCombineEducation)
}