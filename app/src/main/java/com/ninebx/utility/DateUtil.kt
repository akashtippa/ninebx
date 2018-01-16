package com.ninebx.utility

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