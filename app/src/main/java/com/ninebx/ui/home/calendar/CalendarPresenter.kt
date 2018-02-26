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
import java.text.SimpleDateFormat
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
                refreshData()
            }
        })
    }

    private lateinit var datesWithEvents: ArrayList<Date>
    fun refreshData() : ArrayList<Date> {
        datesWithEvents = ArrayList<Date>()
        calendarView.showProgress(R.string.loading)
        calendarEvents = calendarRealm!!.where(CalendarEvents::class.java).findAll()
        for( event in calendarEvents!! ) {
            val eventCount = event.startsDate.count()
            for( i in 0 until eventCount ) {

                val startDate = getDateForString(event.startsDate[i])
                val endDate = getDateForString(event.endsDate[i])

                val daysCount = getDateDifference( startDate, endDate )
                if( daysCount > 0 ) {
                    datesWithEvents.addAll(getDaysBetweenDates(startDate, endDate))
                    event.allDays.addAll(getDayStringForDates( startDate, endDate ))
                }
                else if( !event.isAllDay[i]!! ) {
                    datesWithEvents.add(startDate)
                    event.allDays.add(dateFormat.format(startDate))
                    datesWithEvents.add(endDate)
                    event.allDays.add(dateFormat.format(endDate))
                }

            }
        }
        calendarView.setDateWithEvents(datesWithEvents)
        calendarView.hideProgress()
        return datesWithEvents
    }

    private val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

    private fun getDayStringForDates(startDate: Date, endDate: Date): Collection<String> {
        val dates = ArrayList<String>()
        val calendar = GregorianCalendar()
        calendar.time = startDate

        while (calendar.time.before(endDate)) {
            val result = calendar.time
            dates.add(dateFormat.format(result))
            calendar.add(Calendar.DATE, 1)
        }
        return dates
    }


    fun getEventsForDate( selectedDate : Date ) : ArrayList<CalendarEvents> {
        val dateEvents = ArrayList<CalendarEvents>()
        calendarEvents!!.filterTo(dateEvents) {
            //check if selectedDate is present either in startDate[] or endDate[]
            //if present add the event to dateEvents
            checkForDateInEvent(selectedDate, it)
        }
        return dateEvents
    }

    private fun checkForDateInEvent(selectedDate: Date, event: CalendarEvents?): Boolean {
        return ( event!!.allDays.contains(dateFormat.format(selectedDate)) )
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