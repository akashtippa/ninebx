package com.ninebx.ui.home.calendar

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.ui.home.calendar.DayEventsRecyclerViewAdapter.ViewHolder

/**
 * Created by Alok on 09/01/18.
 */
class DayEventsRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>()  {
    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_event, parent, false))
    }

    inner class ViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}