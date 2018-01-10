package com.ninebx.ui.home.calendar

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar
import com.desai.vatsal.mydynamiccalendar.EventModel
import com.desai.vatsal.mydynamiccalendar.GetEventListListener
import com.desai.vatsal.mydynamiccalendar.OnDateClickListener
import java.util.*
import com.desai.vatsal.mydynamiccalendar.OnEventClickListener
import com.desai.vatsal.mydynamiccalendar.OnWeekDayViewClickListener
import com.ninebx.R.id.myCalendar
import com.ninebx.R.id.myCalendar


/**
 * Created by Alok on 03/01/18.
 */
class CalendarFragment : Fragment(), CalendarView {

    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {

    }

    override fun onError(error: Int) {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myCalendar = MyDynamicCalendar(context)

        myCalendar.setCalendarBackgroundColor("#eeeeee");
        myCalendar.setHeaderBackgroundColor("#454265");
        myCalendar.setHeaderTextColor("#ffffff");
        myCalendar.setNextPreviousIndicatorColor("#245675");
        myCalendar.setWeekDayLayoutBackgroundColor("#965471");
        myCalendar.setWeekDayLayoutTextColor("#246245");
        myCalendar.setExtraDatesOfMonthBackgroundColor("#324568");
        myCalendar.setExtraDatesOfMonthTextColor("#756325");
        myCalendar.setDatesOfMonthBackgroundColor("#145687");
        myCalendar.setDatesOfMonthTextColor("#745632");
        myCalendar.setCurrentDateBackgroundColor(R.color.black);
        myCalendar.setCurrentDateTextColor("#00e600");
        myCalendar.setBelowMonthEventTextColor("#425684");
        myCalendar.setBelowMonthEventDividerColor("#635478");

        // set all saturday off(Holiday) - default value is false
        // isSaturdayOff(true/false, date_background_color, date_text_color);
        myCalendar.isSaturdayOff(true, "#ffffff", "#ff0000");

        // set all sunday off(Holiday) - default value is false
        // isSundayOff(true/false, date_background_color, date_text_color);
        myCalendar.isSundayOff(true, "#ffffff", "#ff0000");

        myCalendar.setEventCellBackgroundColor("#852365")
        myCalendar.setEventCellTextColor("#425684")

        // Add event  -  addEvent(event_date, event_start_time, event_end_time, event_title)
        myCalendar.addEvent("5-10-2016", "8:00", "8:15", "Today Event 1")
        myCalendar.addEvent("05-10-2016", "8:15", "8:30", "Today Event 2")
        myCalendar.addEvent("05-10-2016", "8:30", "8:45", "Today Event 3")

        // Get list of event with detail
        myCalendar.getEventList(object : GetEventListListener {
            override fun eventList(eventList: ArrayList<EventModel>) {

            }
        })

        // updateEvent(position, event_date, event_start_time, event_end_time, event_title)
        myCalendar.updateEvent(0, "5-10-2016", "8:00", "8:15", "Today Event 111111")

        // Delete single event
        myCalendar.deleteEvent(2)

        // Delete all events
        myCalendar.deleteAllEvent()


        myCalendar.setHolidayCellBackgroundColor("#654248");
        myCalendar.setHolidayCellTextColor("#d590bb");

        // set holiday date clickable true/false
        myCalendar.setHolidayCellClickable(false);

        // Add holiday  -  addHoliday(holiday_date)
        myCalendar.addHoliday("2-11-2016");
        myCalendar.addHoliday("13-11-2016");
        myCalendar.addHoliday("8-10-2016");
        myCalendar.addHoliday("10-12-2016");

        // setCalendarDate(date, month, year)
        myCalendar.setCalendarDate(5, 10, 2016);

        // show month view
        myCalendar.showMonthView()

        // date click listener
        myCalendar.setOnDateClickListener(object : OnDateClickListener {
            override fun onClick(date: Date) {

            }

            override fun onLongClick(date: Date) {

            }
        })


        // show month view with events
        myCalendar.showMonthViewWithBelowEvents()

        // date click listener
        myCalendar.setOnDateClickListener(object : OnDateClickListener {
            override fun onClick(date: Date) {

            }

            override fun onLongClick(date: Date) {

            }
        })

        // show week view
        myCalendar.showWeekView()

        // date click listener
        myCalendar.setOnDateClickListener(object : OnDateClickListener {
            override fun onClick(date: Date) {

            }

            override fun onLongClick(date: Date) {

            }
        })

        // week view blank cell click listener
        myCalendar.setOnWeekDayViewClickListener(object : OnWeekDayViewClickListener {
            override fun onClick(date: String, time: String) {

            }

            override fun onLongClick(date: String, time: String) {

            }
        })

        // single event cell click listener
        myCalendar.setOnEventClickListener(object : OnEventClickListener {
            override fun onClick() {

            }

            override fun onLongClick() {

            }
        })

        // show day view
        myCalendar.showDayView()

        // day view blank cell click listener
        myCalendar.setOnWeekDayViewClickListener(object : OnWeekDayViewClickListener {
            override fun onClick(date: String, time: String) {

            }

            override fun onLongClick(date: String, time: String) {

            }
        })

        // single event cell click listener
        myCalendar.setOnEventClickListener(object : OnEventClickListener {
            override fun onClick() {

            }

            override fun onLongClick() {

            }
        })

        // show agenda view
        myCalendar.showAgendaView()

        // date click listener
        myCalendar.setOnDateClickListener(object : OnDateClickListener {
            override fun onClick(date: Date) {

            }

            override fun onLongClick(date: Date) {

            }
        })

    }

}