package com.ninebx

import android.app.DatePickerDialog
import android.widget.DatePicker

import java.util.Calendar

import io.realm.RealmConfiguration

/***
 * Created by TechnoBlogger on 19/12/17.
 */

class Test {
    //    RealmConfiguration config = RealmConfiguration.Builder(this).build();

    fun test() {
        // calender class's instance and get current date , month and year from calender
        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR) // current year
        val mMonth = c.get(Calendar.MONTH) // current month
        val mDay = c.get(Calendar.DAY_OF_MONTH) // current day
        // date picker dialog
        datePickerDialog = DatePickerDialog(this@MainActivity,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // set day of month , month and year value in the edit text
                    date.setText(dayOfMonth.toString() + "/"
                            + (monthOfYear + 1) + "/" + year)
                }, mYear, mMonth, mDay)
        datePickerDialog.show()
    }
}
