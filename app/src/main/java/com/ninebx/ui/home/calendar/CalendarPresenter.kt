package com.ninebx.ui.home.calendar

import com.ninebx.ui.base.realm.CalendarEvents
import com.ninebx.ui.base.realm.Notifications
import com.ninebx.ui.base.realm.decrypted.DecryptedNotifications
import com.ninebx.utility.AppLogger
import com.ninebx.utility.decryptNotifications
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import io.realm.RealmList
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok on 03/01/18.
 */
class CalendarPresenter( val calendarView: CalendarView)  {
    private val context = getApplicationContext()
    private val calendarEventsMap = HashMap<String, ArrayList<CalendarEvents>>()

    init {
        prepareRealmConnections(context, false, "CalendarEvents", object : Realm.Callback(){
            override fun onSuccess(realm: Realm?) {
                val calendarEvents = realm!!.where(CalendarEvents::class.java).findAll()

                var getCalendarItems = RealmList<String>()
                if (calendarEvents.size > 0){
                    for (i in 0 until calendarEvents.size) {
                        getCalendarItems.add("ClassType : " + calendarEvents[i]!!.classType)
                        getCalendarItems.add("Event ID : " + calendarEvents[i]!!.eventID)
                        getCalendarItems.add("Title : " + calendarEvents[i]!!.title)
                        getCalendarItems.add("Location : " + calendarEvents[i]!!.location)
                        getCalendarItems.add("IsAllDay : " + calendarEvents[i]!!.isAllDay)
                        getCalendarItems.add("Notes : " + calendarEvents[i]!!.notes)
                        getCalendarItems.add("StartDate : " + calendarEvents[i]!!.startsDate)
                        getCalendarItems.add("EndDate : " + calendarEvents[i]!!.endsDate)
                        getCalendarItems.add("Repeat : " + calendarEvents[i]!!.repeats)
                        getCalendarItems.add("EndRepeat : " + calendarEvents[i]!!.endRepeat)
                        getCalendarItems.add("TravelTime : " + calendarEvents[i]!!.travelTime)
                        getCalendarItems.add("Remainder : " + calendarEvents[i]!!.reminder)
                        getCalendarItems.add("Alert : " + calendarEvents[i]!!.alert)
                        getCalendarItems.add("ShowAs : " + calendarEvents[i]!!.showAs)
                        getCalendarItems.add("URL : " + calendarEvents[i]!!.url)
                        getCalendarItems.add("AttachmentNames : " + calendarEvents[i]!!)
                        getCalendarItems.add("BackingImages : " + calendarEvents[i]!!)
                        getCalendarItems.add("PhotosID : " + calendarEvents[i]!!)
                    }
                AppLogger.d("CalendarEvents", "Calendar events fetched" + getCalendarItems)
            }
                else{
                    AppLogger.d("CalendarEvents", "calendar events not available")
                }
            }
        })
    }
}