package com.ninebx.utility

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.ninebx.NineBxApplication
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alok on 16/01/18.
 */

fun getMonthYearFormat( date : Date ) : String {
    return SimpleDateFormat("MM/YYYY", Locale.getDefault()).format( date )
}

fun getDateMonthYearFormat( date : Date ) : String {
    return SimpleDateFormat("MM/DD/YYYY", Locale.getDefault()).format( date )
}

fun getDateMonthYearTimeFormat( date : Date ) : String {
    return SimpleDateFormat("MM/DD/YYYY HH:mm", Locale.getDefault()).format( date )
}

fun getDateFromPicker(calendar: Calendar, dateTimeSelectionListener: DateTimeSelectionListener) {

    val datePickerDialog = DatePickerDialog(NineBxApplication.instance.activityInstance,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                calendar.set(Calendar.YEAR, year)
                dateTimeSelectionListener.onDateTimeSelected((calendar))

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))

    datePickerDialog.show()

}

interface DateTimeSelectionListener {
    fun onDateTimeSelected(selectedDate : Calendar )
}

fun getTimeFromPicker(calendar: Calendar, dateTimeSelectionListener: DateTimeSelectionListener) {

    val timePickerDialog = TimePickerDialog( NineBxApplication.instance.activityInstance, TimePickerDialog.OnTimeSetListener { _, hours, minutes ->
            calendar.set(Calendar.HOUR_OF_DAY, hours)
            calendar.set(Calendar.MINUTE, minutes)
            dateTimeSelectionListener.onDateTimeSelected(calendar)
    },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), true)

    timePickerDialog.show()

}