package com.ninebx.ui.home.calendar

import com.ninebx.ui.base.BaseView
import java.util.*

/**
 * Created by Alok on 03/01/18.
 */
interface CalendarView : BaseView {
    fun setDateWithEvents(datesWithEvents: ArrayList<Date>, dateStringWithEvents: ArrayList<String>)
}