package com.ninebx.ui.home.calendar

import com.ninebx.R
import com.ninebx.ui.base.realm.CalendarEvents
import com.ninebx.utility.parseDateForFormat
import com.ninebx.utility.parseDateMonthYearFormat
import com.ninebx.utility.parseDateMonthYearTimeFormat
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import io.realm.RealmResults
import io.realm.internal.SyncObjectServerFacade.getApplicationContext
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap



/**
 * Created by Alok on 03/01/18.
 */
class CalendarPresenter( val calendarView: CalendarView)  {
    private val context = getApplicationContext()
    private lateinit var calendarEventsMap : HashMap<Int, ArrayList<CalendarEvents>>

    private var calendarEvents: RealmResults<CalendarEvents>? = null
    private var calendarRealm: Realm? = null

    init {
        calendarView.showProgress(R.string.loading)
        prepareRealmConnections(context, false, "CalendarEvents", object : Realm.Callback(){
            override fun onSuccess(realm: Realm?) {
                calendarRealm = realm
                calendarView.hideProgress()
            }
        })
    }

    private lateinit var datesWithEvents: ArrayList<Date>
    fun refreshData() {
        datesWithEvents = ArrayList<Date>()
        calendarEvents = calendarRealm!!.where(CalendarEvents::class.java).findAll()
        for( event in calendarEvents!! ) {
            val eventCount = event.startsDate.count()
            for( i in 0 until eventCount ) {

                var startDate = getDateForString(event.startsDate[i])
                var endDate = getDateForString(event.endsDate[i])

                val daysCount = getDateDifference( startDate, endDate )
                if( daysCount > 0 ) {
                    datesWithEvents.addAll(getDaysBetweenDates(startDate, endDate))
                }
                else if( !event.isAllDay[i]!! ) {
                    datesWithEvents.add(startDate)
                    datesWithEvents.add(endDate)
                }

            }
        }
    }



    fun getEventsForDate( selectedDate : Date ) {

    }

    fun getDaysBetweenDates(startDate: Date, endDate: Date): List<Date> {
        val dates = ArrayList<Date>()
        val calendar = GregorianCalendar()
        calendar.time = startDate

        while (calendar.time.before(endDate)) {
            val result = calendar.time
            dates.add(result)
            calendar.add(Calendar.DATE, 1)
        }
        return dates
    }

    private fun getDateDifference(startDate: Date, endDate: Date): Int {
        return (( endDate.time - startDate.time ) / ( 1000 * 60 * 60 * 24 )).toInt()
    }

    private fun getDateForString(dateString: String?): Date {

        try {
            return parseDateForFormat( dateString!!, "MMMM dd, yyyy hh:mm a")
        } catch ( e: Exception ) {
            return parseDateForFormat( dateString!!, "MMMM dd, yyyy")
        }
    }

    fun filterEventsForMonth( monthString : String ) : HashMap<Int, ArrayList<CalendarEvents>> {
        calendarEvents = calendarRealm!!.where(CalendarEvents::class.java)
                .beginGroup()
                .contains("startsDate", monthString )
                .endGroup()
                .findAll()

        calendarEventsMap = HashMap()
        return addEventToMap( calendarEvents )
    }

    private fun addEventToMap(events: RealmResults<CalendarEvents>?): HashMap<Int, ArrayList<CalendarEvents>> {
        for( calendarEvent in  events!! ) {
            for( startDate in calendarEvent.startsDate ) {
                val parsedDate = parseAnyDate( startDate )
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = parsedDate.time

                if( !calendarEventsMap.containsKey(calendar.get(Calendar.DATE)) )
                    calendarEventsMap.put(calendar.get(Calendar.DATE), ArrayList())

                val eventsList = calendarEventsMap.get(calendar.get(Calendar.DATE))
                eventsList!!.add(calendarEvent)
            }

        }
        return calendarEventsMap
    }

    private fun parseAnyDate(startDate: String?): Date {
        try {
            return parseDateMonthYearFormat(startDate!!)
        } catch ( e : Exception ) {
            return parseDateMonthYearTimeFormat(startDate!!)
        }

    }
}