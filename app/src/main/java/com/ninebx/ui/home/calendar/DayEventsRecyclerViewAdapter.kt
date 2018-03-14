package com.ninebx.ui.home.calendar

import android.app.Application
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.AdapterClickListener
import com.ninebx.ui.base.realm.CalendarEvents
import com.ninebx.ui.home.calendar.DayEventsRecyclerViewAdapter.ViewHolder
import com.ninebx.utility.AppLogger
import java.util.*

/**
 * Created by Alok on 09/01/18.
 */
class DayEventsRecyclerViewAdapter( val calendarEvents : ArrayList<CalendarEvents>,
                                    val selectedDate : Date,
                                    val adapterClickListener: AdapterClickListener ) : RecyclerView.Adapter<ViewHolder>()  {

    override fun getItemCount(): Int {
        return calendarEvents.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val event = getItemAtPosition( position )
        val selectedDateIndex = event.getPositionForDay( selectedDate )

        var eventStartDate = event.startsDate.toString().substringAfter(" ").substring(0,2)
        var eventEndDate = event.endsDate.toString().substringAfter(" ").substring(0,2)
        var selecteddateDate = selectedDate.date.toString()

        var selectedDateMonth = selectedDate.toString().substringAfter(" ").substring(0,3)
        var eventStartMonth = event.startsDate.toString().substringAfter("[").substring(0,3)
        var eventEndMonth = event.endsDate.toString().substringAfter("[").substring(0,3)

        var selectedDateYear = selectedDate.toString().substring(selectedDate.toString().length - 4)
        var eventStartYear = event.startsDate.toString().substringAfter(", ").substring(0,4)
        var eventEndYear = event.endsDate.toString().substringAfter(", ").substring(0,4)

        if( selectedDateIndex != -1 && event.isAllDay[selectedDateIndex]!! ) {
            holder!!.tvEvent.text = ALL_DAY
        }


        if(!event.isAllDay[selectedDateIndex]!!){
            holder!!.tvEvent.text = ALL_DAY
        }

        /*AppLogger.e("selected date month", selecteddateDate +"," + eventStartDate+",")

        AppLogger.e("selected date month", selectedDateMonth + "," + eventStartMonth+",")

        AppLogger.e("selected date year", selectedDateYear + "," + eventStartYear+",")*/


        if(eventStartDate.equals(selecteddateDate) &&
                selectedDateMonth.equals(eventStartMonth,true) &&
                selectedDateYear.equals(eventStartYear) && !event.isAllDay[selectedDateIndex]!!) {

            var formattedString = "Starts\n" + event.startsDate[selectedDateIndex]!!.substring(event.startsDate[selectedDateIndex]!!.length - 8);
            holder!!.tvEvent.text = formattedString
        }

        if(eventEndDate.equals(selecteddateDate) &&
                selectedDateMonth.equals(eventEndMonth,true) &&
                selectedDateYear.equals(eventEndYear) && !event.isAllDay[selectedDateIndex]!!) {

            var formattedString = "Ends\n" + event.endsDate[selectedDateIndex]!!.substring(event.endsDate[selectedDateIndex]!!.length - 8);
            holder!!.tvEvent.text = formattedString
        }


        holder!!.tvTitle.text = event.title[selectedDateIndex]
        holder!!.tvSubTitle.text = event.location[selectedDateIndex]
    }

    fun getItemAtPosition(position: Int): CalendarEvents {
        return calendarEvents[position]
    }

    fun remove(item : CalendarEvents){
        calendarEvents.remove(item)
    }
    fun getSelectedDateForEvent() : Date {
        return selectedDate
    }

    private lateinit var ALL_DAY : String

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        ALL_DAY = parent!!.context.getString(R.string.all_day)
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false))
    }

    inner class ViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(view: View?) {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                adapterClickListener.onItemClick(position)
            }
        }

        val tvEvent : TextView = itemView!!.findViewById(R.id.tvEvent)
        val tvTitle : TextView = itemView!!.findViewById(R.id.tvTitle)
        val tvSubTitle : TextView = itemView!!.findViewById(R.id.tvSubTitle)
        val ivAlarm : ImageView = itemView!!.findViewById(R.id.ivAlarm)

        init {
            itemView!!.setOnClickListener(this)
        }
    }
}