package com.ninebx.ui.home

import android.content.Context
import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.CalendarEvents
import com.ninebx.ui.base.realm.decrypted.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Alok on 16/01/18.
 */
interface HomeView : BaseView {
    fun addEditCalendarEvent(calendarEvent : CalendarEvents?, selectedDate : Date)
    fun getCurrentUsers(): ArrayList<DecryptedUsers>
    fun setNotificationCount(notificationCount: Int)
    fun getContextForScreen() : Context
    fun setCurrentUsers(currentUsers: ArrayList<DecryptedUsers>?)
    fun onCombineHomeFetched(mDecryptCombineHome: DecryptedCombine)
    fun onCombineTravelFetched(mDecryptCombineTravel: DecryptedCombineTravel)
    fun onCombineContactsFetched(mDecryptCombineContacts: DecryptedCombineContacts)
    fun onCombinePersonalFetched(mDecryptCombinePersonal: DecryptedCombinePersonal)
    fun onCombineWellnessFetched(mDecryptCombineWellness: DecryptedCombineWellness)
    fun onCombineEducationFetched(mDecryptCombineEducation: DecryptedCombineEducation)
}