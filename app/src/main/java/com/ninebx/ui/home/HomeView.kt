package com.ninebx.ui.home

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.CalendarEvents
import java.util.*

/**
 * Created by Alok on 16/01/18.
 */
interface HomeView : BaseView {
    fun addEditCalendarEvent(calendarEvent : CalendarEvents?, selectedDate : Date )
}