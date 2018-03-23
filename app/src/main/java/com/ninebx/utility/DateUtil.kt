package com.ninebx.utility

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.realm.CalendarEvents
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Alok on 16/01/18.
 */

fun getMonthYearFormat(date: Date): String {
    return SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(date)
}

fun getDateMonthYearFormat(date: Date): String {
    return SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(date)
}

fun getTimeFormat(date: Date): String {
    return SimpleDateFormat("hh:mm", Locale.getDefault()).format(date)
}
fun parseDateForMemory(date: Date): String {
    return SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(date)
}

fun parseDateForCreatedUser(date: Date): String {
    return SimpleDateFormat("MMM d, yyyy, hh:mm", Locale.getDefault()).format(date)
}

fun getDateMonthYearTimeFormat(date: Date): String {
    return SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault()).format(date)
}

fun parseDateMonthYearFormat(date: String): Date {
    return SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).parse(date)
}

fun parseDateMonthYearTimeFormat(date: String): Date {
    return SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault()).parse(date)
}

fun parseDateForFormat( dateString : String, dateFormat : String ) : Date {
    return SimpleDateFormat(dateFormat, Locale.getDefault()).parse(dateString)
}

fun parseDateForFormat( dateString : Date, dateFormat : String ) : String {
    return SimpleDateFormat(dateFormat, Locale.getDefault()).format(dateString)
}

fun getDateFromPicker(context: Context, calendar: Calendar, minDateCalendar : Calendar, dateTimeSelectionListener: DateTimeSelectionListener) {

    val datePickerDialog = DatePickerDialog(context,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                calendar.set(Calendar.YEAR, year)
                dateTimeSelectionListener.onDateTimeSelected((calendar))

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))

    datePickerDialog.datePicker.minDate = minDateCalendar.timeInMillis + (1000 * 24 * 60 * 60)
    datePickerDialog.show()

}

fun getDateFromPicker(context: Context, calendar: Calendar, dateTimeSelectionListener: DateTimeSelectionListener) {

    val datePickerDialog = DatePickerDialog(context,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                calendar.set(Calendar.YEAR, year)
                dateTimeSelectionListener.onDateTimeSelected((calendar))

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))

    //datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
    datePickerDialog.show()
}

interface DateTimeSelectionListener {
    fun onDateTimeSelected(selectedDate: Calendar)
}

fun getTimeFromPicker(context: Context, calendar: Calendar, dateTimeSelectionListener: DateTimeSelectionListener) {

    val timePickerDialog = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { _, hours, minutes ->
        calendar.set(Calendar.HOUR_OF_DAY, hours)
        calendar.set(Calendar.MINUTE, minutes)
        dateTimeSelectionListener.onDateTimeSelected(calendar)
    },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), false)

    timePickerDialog.show()

}

interface YearSelectionListener {
    fun onYearSelected(selectedYear : String)
}

fun getYearFromPicker(context: Context, minYear : Int, maxYear : Int, dateTimeSelectionListener: YearSelectionListener) {

    val yearsList = ArrayList<String>()
    var popupWindow : AlertDialog ?= null
    for( year in minYear..maxYear ) {
        yearsList.add(year.toString())
    }
    yearsList.reverse()
    val popupView = LayoutInflater.from(context).inflate(R.layout.popup_window_list_layout, null)

    val optionsListView = popupView.findViewById<ListView>(R.id.optionsListView)
    val arrayAdapter = ArrayAdapter(context, R.layout.txt_usd, yearsList)
    optionsListView.adapter = arrayAdapter
    optionsListView.onItemClickListener = AdapterView.OnItemClickListener { p0, p1, p2, p3 ->
        dateTimeSelectionListener.onYearSelected(yearsList[p2])
        popupWindow!!.dismiss()
    }
    popupWindow = AlertDialog.Builder(context).setView(popupView).create()
    popupWindow.show()

}

fun getDaysBetweenDates(startDate: Date, endDate: Date): List<Date> {
    val dates = ArrayList<Date>()
    val calendar = GregorianCalendar()
    calendar.time = startDate
    val newEndDate = Date(endDate.time /*+ ( 1000 * 60 * 60 * 24 )*/)

    while (calendar.time.before(newEndDate)) {
        val result = calendar.time
        dates.add(result)
        calendar.add(Calendar.DATE, 1)
    }
    return dates
}

fun getDateDifference(startDate: Date, endDate: Date): Int {
    return (( endDate.time - startDate.time ) / ( 1000 * 60 * 60 * 24 )).toInt()
}


fun getDayStringForDates(startDate: Date, endDate: Date): Collection<String> {
    val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    val dates = ArrayList<String>()
    val calendar = GregorianCalendar()
    calendar.time = startDate
    val newEndDate = Date(endDate.time /*+ ( 1000 * 60 * 60 * 24 )*/)
    while (calendar.time.before(newEndDate)) {
        val result = calendar.time
        dates.add(dateFormat.format(result))
        calendar.add(Calendar.DATE, 1)
    }
    dates.add(dateFormat.format(endDate))
    return dates
}


///set local notification
/*
fun findRepeatAndReminder(calendarModel: CalendarEvents ) {

    for ( i in (0..calendarModel.startsDate.size - 1) ) {

        val dateFormat = if ( calendarModel.isAllDay[i]!! ) {
            "MMMM dd, yyyy"
        } else {
            "MMMM dd, yyyy  hh:mm a"
        }


        let content = UNMutableNotificationContent()
        content.title = "This is a reminder notification"
        //content.subtitle = "Event is scheduled"
        content.body = calendarModel.title[i] + " event is scheduled"
        content.categoryIdentifier = "actionCategory"
        content.sound = UNNotificationSound.default()
        // content.badge = 1

        // other settings...
        content.userInfo = ["NotificationID": calendarModel.id]
        content.setValue(true, forKey: "shouldAlwaysAlertWhileAppIsForeground")

        //nextTriggerDate?.startOfDay
        var dateToFire = Date() //Calendar.current.dateComponents([.year, .month, .day, .hour, .minute], from:  sDate)

        if !calendarModel.isAllDay[i] {
            //find the reminder
            switch calendarModel.reminder[i]  {
                case "At time of event" :
                dateToFire = sDate
                case Repeaters.k5MinutesBefore :
                dateToFire =  Calendar.current.date(byAdding: .minute, value: -5, to: sDate)!
                case Repeaters.k15MinutesBefore :
                dateToFire =  Calendar.current.date(byAdding: .minute, value: -15, to: sDate)!
                case Repeaters.k30MinutesBefore :
                dateToFire =  Calendar.current.date(byAdding: .minute, value: -30, to: sDate)!
                case Repeaters.k1HourBefore :
                dateToFire =  Calendar.current.date(byAdding: .hour, value: -1, to: sDate)!
                case Repeaters.k2HourBefore:
                dateToFire =  Calendar.current.date(byAdding: .hour, value: -2, to: sDate)!
                case Repeaters.k1DayBefore :
                dateToFire =  Calendar.current.date(byAdding: .day, value: -1, to: sDate)!
                case Repeaters.k2DayBefore :
                dateToFire =  Calendar.current.date(byAdding: .day, value: -2, to: sDate)!
                case Repeaters.k1WeekBefore :
                dateToFire =  Calendar.current.date(byAdding: .day, value: -7, to: sDate)!
                default:
                print("")
            }

        } else {
            switch calendarModel.reminder[i]  {
                case "On day of event (9:00 AM)" :
                dateToFire = sDate //Calendar.current.date(byAdding: .hour, value: 9, to: )!
                case Repeaters.kOneDayBefore :
                dateToFire = Calendar.current.date(byAdding: .day, value: -1, to: sDate)!
                case Repeaters.kTwoDayBefore :
                dateToFire = Calendar.current.date(byAdding: .day, value: -2, to: sDate)!
                case Repeaters.k1WeekBeforeForAllDay :
                dateToFire = Calendar.current.date(byAdding: .day, value: -7, to: sDate)!
                default:
                print("")
            }
        }

        var comps = Calendar.current.dateComponents([.year, .month, .day, .hour, .minute], from:  dateToFire)
        if calendarModel.isAllDay[i] {
            comps.hour = 9
        }

        var trigger = UNCalendarNotificationTrigger(dateMatching: comps, repeats: false)
        trigger = UNCalendarNotificationTrigger(dateMatching: comps, repeats: true)

        let request = UNNotificationRequest(identifier: UUID().uuidString, content: content, trigger: trigger)
        UNUserNotificationCenter.current().add(request) { (error:Error?) in
            if error != nil {
                // print(error?.localizedDescription ?? "")
            }
            // print("Notification Register Success")
        }
    }*/
