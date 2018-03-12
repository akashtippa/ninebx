package com.ninebx.utility

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import com.ninebx.NineBxApplication
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alok on 16/01/18.
 */

fun getMonthYearFormat(date: Date): String {
    return SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(date)
}

fun getDateMonthYearFormat(date: Date): String {
    return SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(date)
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
    return dates
}