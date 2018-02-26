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

    datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
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