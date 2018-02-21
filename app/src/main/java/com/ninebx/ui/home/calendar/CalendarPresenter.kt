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

                var startDate = ArrayList<String>()
                var endDate = ArrayList<String>()
                if (calendarEvents.size > 0){
                    for (i in 0 until calendarEvents.size) {
                        startDate.add(calendarEvents[i]!!.startsDate.toString())
                        endDate.add(calendarEvents[i]!!.endsDate.toString())
                    }
                AppLogger.d("CalendarEvents", "Calendar start date" + startDate)
                AppLogger.d("CalendarEvents", "Calendar end date" + endDate)
            }
                else{
                    AppLogger.d("CalendarEvents", "calendar events not available")
                }
            }
        })
    }
}