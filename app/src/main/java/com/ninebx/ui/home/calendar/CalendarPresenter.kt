package com.ninebx.ui.home.calendar

import android.annotation.SuppressLint
import android.os.AsyncTask
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
@SuppressLint("StaticFieldLeak")
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
        prepareRealmConnectionsRealmThread(context, false, Constants.REALM_END_POINT_CALENDAR_EVENTS, object : Realm.Callback(){
            override fun onSuccess(realm: Realm?) {
                calendarRealm = realm
                refreshData()
                calendarView.setDateWithEvents(datesWithEvents, dateStringWithEvents)
                calendarView.hideProgress()
            }
        })
       /* object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {

            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                //AppLogger.d(TAG, "setDateWithEvents : " + datesWithEvents)

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)*/

    }

    private lateinit var datesWithEvents: ArrayList<Date>
    private lateinit var dateStringWithEvents: ArrayList<String>
    fun refreshData() : ArrayList<Date> {
        datesWithEvents = ArrayList<Date>()
        dateStringWithEvents = ArrayList<String>()
        calendarView.showProgress(R.string.loading)
        calendarEvents = calendarRealm!!.where(CalendarEvents::class.java).findAll()
        calendarEventsList = ArrayList()
        calendarEventsList!!.addAll(calendarEvents!!.toList())
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
            //AppLogger.d(TAG, "Calendar Event : " + event)
            //AppLogger.d(TAG, "Calendar Event Days : " + event.allDays)
        }

        return datesWithEvents
    }


    fun getEventsForDate( selectedDate : Date ) : ArrayList<CalendarEvents> {
        val dateEvents = ArrayList<CalendarEvents>()
        calendarEventsList!!.filterTo(dateEvents) {
            //check if selectedDate is present either in startDate[] or endDate[]
            //if present add the event to dateEvents
            checkForDateInEvent(selectedDate, it)
        }
        //AppLogger.d(TAG, "getEventsForDate : " + dateEvents)
        //AppLogger.d(TAG, "getEventsForDate : " + dateEvents.count())
        return dateEvents
    }

    private fun checkForDateInEvent(selectedDate: Date, event: CalendarEvents?): Boolean {
        //AppLogger.d(TAG, "checkForDateInEvent : " + selectedDate + " All Days " + event!!.allDays)
        return ( event!!.allDays.contains(dateFormat!!.format(selectedDate)) )
    }


    private fun getDateForString(dateString: String?): Date {

        try {
            return parseDateForFormat( dateString!!, "MMMM dd, yyyy hh:mm a")
        } catch ( e: Exception ) {
            return parseDateForFormat( dateString!!, "MMMM dd, yyyy")
        }
    }

}