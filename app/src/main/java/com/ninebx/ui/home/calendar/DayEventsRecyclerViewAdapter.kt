package com.ninebx.ui.home.calendar

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.AdapterClickListener
import com.ninebx.ui.base.realm.CalendarEvents
import com.ninebx.ui.home.calendar.DayEventsRecyclerViewAdapter.ViewHolder
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
        if( selectedDateIndex != -1 && event.isAllDay[selectedDateIndex]!! ) {
            holder!!.tvEvent.text = ALL_DAY
        }
        else {
            holder!!.tvEvent.text = event.startsDate[selectedDateIndex] + "\n-\n" + event.endsDate[selectedDateIndex]
        }
        holder.tvTitle.text = event.title[selectedDateIndex]
        holder.tvSubTitle.text = event.location[selectedDateIndex]
    }

    fun getItemAtPosition(position: Int): CalendarEvents {
        return calendarEvents[position]
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