package com.ninebx.ui.home.calendar.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Alok on 10/01/18.
 */
class CalendarEvents(
        @PrimaryKey
        var id : Int = 0,
        var classType : String = "Calendar",
        var title : String = "",
        var location : String = "",
        var isAllDay : Boolean= false,
        var notes : String = "",
        var startsDate : Date = Date(),
        var endsDate : Date = Date(),
        var repeats : String = "",
        var endRepeat : String = "",
        var reminder : String = "",
        var travelTime : String = "",
        var alert : String = "",
        var showAs : String = "",
        var url : String = "",
        var isReminderSet : Boolean = false,
        var attachmentNames : String = ""
) : RealmObject() {
}