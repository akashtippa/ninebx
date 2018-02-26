package com.ninebx.ui.home.calendar

import com.ninebx.R
import com.ninebx.ui.base.realm.CalendarEvents
import com.ninebx.utility.*
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

    val TAG = "CalendarPresenter"
    private val context = getApplicationContext()
    private lateinit var calendarEventsMap : HashMap<Int, ArrayList<CalendarEvents>>

    private var calendarEvents: RealmResults<CalendarEvents>? = null
    private var calendarEventsList : ArrayList<CalendarEvents>? = null
    private var calendarRealm: Realm? = null
    private var dateFormat : SimpleDateFormat ?= null
    init {
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        calendarView.showProgress(R.string.loading)
        prepareRealmConnections(context, false, "CalendarEvents", object : Realm.Callback(){
            override fun onSuccess(realm: Realm?) {
                calendarRealm = realm
                refreshData()
            }
        })
    }

    private lateinit var datesWithEvents: ArrayList<Date>
    private lateinit var dateStringWithEvents: ArrayList<String>
    fun refreshData() : ArrayList<Date> {
        datesWithEvents = ArrayList<Date>()
        dateStringWithEvents = ArrayList<String>()
        calendarView.showProgress(R.string.loading)
        calendarEvents = calendarRealm!!.where(CalendarEvents::class.java).findAll()
        calendarEventsList = ArrayList()
        calendarEventsList!!.addAll(calendarEvents!!.asIterable())
        for( event in calendarEventsList!! ) {
            val eventCount = event.startsDate.count()
            for( i in 0 until eventCount ) {

                val startDate = getDateForString(event.startsDate[i])
                val endDate = getDateForString(event.endsDate[i])

                val daysCount = getDateDifference( startDate, endDate )
                if( daysCount > 0 ) {
                    datesWithEvents.addAll(getDaysBetweenDates(startDate, endDate))
                    event.allDays.addAll(getDayStringForDates( startDate, endDate ))
                    dateStringWithEvents.addAll(getDayStringForDates( startDate, endDate ))
                }
                else {

                    //if( !event.isAllDay[i]!! ) {
                    datesWithEvents.add(startDate)
                    dateStringWithEvents.add(dateFormat!!.format(startDate))
                    event.allDays.add(dateFormat!!.format(startDate))
                    datesWithEvents.add(endDate)
                    dateStringWithEvents.add(dateFormat!!.format(endDate))
                    event.allDays.add(dateFormat!!.format(endDate))
                    //}
                }

            }
            AppLogger.d(TAG, "Calendar Event : " + event)
            AppLogger.d(TAG, "Calendar Event Days : " + event.allDays)
        }
        AppLogger.d(TAG, "setDateWithEvents : " + datesWithEvents)
        calendarView.setDateWithEvents(datesWithEvents, dateStringWithEvents)
        calendarView.hideProgress()
        return datesWithEvents
    }



    private fun getDayStringForDates(startDate: Date, endDate: Date): Collection<String> {
        val dates = ArrayList<String>()
        val calendar = GregorianCalendar()
        calendar.time = startDate
        val newEndDate = Date(endDate.time + ( 1000 * 60 * 60 * 24 ))
        while (calendar.time.before(newEndDate)) {
            val result = calendar.time
            dates.add(dateFormat!!.format(result))
            calendar.add(Calendar.DATE, 1)
        }
        return dates
    }


    fun getEventsForDate( selectedDate : Date ) : ArrayList<CalendarEvents> {
        val dateEvents = ArrayList<CalendarEvents>()
        calendarEventsList!!.filterTo(dateEvents) {
            //check if selectedDate is present either in startDate[] or endDate[]
            //if present add the event to dateEvents
            checkForDateInEvent(selectedDate, it)
        }
        AppLogger.d(TAG, "getEventsForDate : " + dateEvents)
        AppLogger.d(TAG, "getEventsForDate : " + dateEvents.count())
        return dateEvents
    }

    private fun checkForDateInEvent(selectedDate: Date, event: CalendarEvents?): Boolean {
        AppLogger.d(TAG, "checkForDateInEvent : " + selectedDate + " All Days " + event!!.allDays)
        return ( event!!.allDays.contains(dateFormat!!.format(selectedDate)) )
    }

    fun getDaysBetweenDates(startDate: Date, endDate: Date): List<Date> {
        val dates = ArrayList<Date>()
        val calendar = GregorianCalendar()
        calendar.time = startDate
        val newEndDate = Date(endDate.time + ( 1000 * 60 * 60 * 24 ))

        while (calendar.time.before(newEndDate)) {
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

}