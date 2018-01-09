package com.ninebx.ui.home.calendar

import java.util.*
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.home.calendar.DaysRecyclerViewAdapter.ViewHolder

/**
 * Created by Alok on 09/01/18.
 */
class DaysRecyclerViewAdapter( val monthDates : Int, val startDay : Int  ) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_rv_days, parent, false))
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val viewsList = ArrayList<TextView>()

        val tvSunday1 = itemView!!.findViewById<TextView>(R.id.tvSunday1)
        val tvSunday2 = itemView!!.findViewById<TextView>(R.id.tvSunday2)
        val tvSunday3 = itemView!!.findViewById<TextView>(R.id.tvSunday3)
        val tvSunday4 = itemView!!.findViewById<TextView>(R.id.tvSunday4)
        val tvSunday5 = itemView!!.findViewById<TextView>(R.id.tvSunday5)
        //val tvSunday6 = itemView!!.findViewById<TextView>(R.id.tvSunday6)

        val tvMonday1 = itemView!!.findViewById<TextView>(R.id.tvMonday1)
        val tvMonday2 = itemView!!.findViewById<TextView>(R.id.tvMonday2)
        val tvMonday3 = itemView!!.findViewById<TextView>(R.id.tvMonday3)
        val tvMonday4 = itemView!!.findViewById<TextView>(R.id.tvMonday4)
        val tvMonday5 = itemView!!.findViewById<TextView>(R.id.tvMonday5)
        //val tvMonday6 = itemView!!.findViewById<TextView>(R.id.tvMonday6)

        val tvTuesday1 = itemView!!.findViewById<TextView>(R.id.tvTuesday1)
        val tvTuesday2 = itemView!!.findViewById<TextView>(R.id.tvTuesday2)
        val tvTuesday3 = itemView!!.findViewById<TextView>(R.id.tvTuesday3)
        val tvTuesday4 = itemView!!.findViewById<TextView>(R.id.tvTuesday4)
        val tvTuesday5 = itemView!!.findViewById<TextView>(R.id.tvTuesday5)
        //val tvTuesday6 = itemView!!.findViewById<TextView>(R.id.tvTuesday6)

        val tvWednesday1 = itemView!!.findViewById<TextView>(R.id.tvWednesday1)
        val tvWednesday2 = itemView!!.findViewById<TextView>(R.id.tvWednesday2)
        val tvWednesday3 = itemView!!.findViewById<TextView>(R.id.tvWednesday3)
        val tvWednesday4 = itemView!!.findViewById<TextView>(R.id.tvWednesday4)
        val tvWednesday5 = itemView!!.findViewById<TextView>(R.id.tvWednesday5)
        //val tvWednesday6 = itemView!!.findViewById<TextView>(R.id.tvWednesday6)

        val tvThursday1 = itemView!!.findViewById<TextView>(R.id.tvThursday1)
        val tvThursday2 = itemView!!.findViewById<TextView>(R.id.tvThursday2)
        val tvThursday3 = itemView!!.findViewById<TextView>(R.id.tvThursday3)
        val tvThursday4 = itemView!!.findViewById<TextView>(R.id.tvThursday4)
        val tvThursday5 = itemView!!.findViewById<TextView>(R.id.tvThursday5)
        //val tvThursday6 = itemView!!.findViewById<TextView>(R.id.tvThursday6)

        val tvFriday1 = itemView!!.findViewById<TextView>(R.id.tvFriday1)
        val tvFriday2 = itemView!!.findViewById<TextView>(R.id.tvFriday2)
        val tvFriday3 = itemView!!.findViewById<TextView>(R.id.tvFriday3)
        val tvFriday4 = itemView!!.findViewById<TextView>(R.id.tvFriday4)
        val tvFriday5 = itemView!!.findViewById<TextView>(R.id.tvFriday5)
        //val tvFriday6 = itemView!!.findViewById<TextView>(R.id.tvFriday6)

        val tvSaturday1 = itemView!!.findViewById<TextView>(R.id.tvSaturday1)
        val tvSaturday2 = itemView!!.findViewById<TextView>(R.id.tvSaturday2)
        val tvSaturday3 = itemView!!.findViewById<TextView>(R.id.tvSaturday3)
        val tvSaturday4 = itemView!!.findViewById<TextView>(R.id.tvSaturday4)
        val tvSaturday5 = itemView!!.findViewById<TextView>(R.id.tvSaturday5)
        //val tvSaturday6 = itemView!!.findViewById<TextView>(R.id.tvSaturday6)

        init {

            viewsList.add(tvSunday1)
            viewsList.add(tvMonday1)
            viewsList.add(tvTuesday1)
            viewsList.add(tvWednesday1)
            viewsList.add(tvThursday1)
            viewsList.add(tvFriday1)
            viewsList.add(tvSaturday1)

            viewsList.add(tvSunday2)
            viewsList.add(tvMonday2)
            viewsList.add(tvTuesday2)
            viewsList.add(tvWednesday2)
            viewsList.add(tvThursday2)
            viewsList.add(tvFriday2)
            viewsList.add(tvSaturday2)

            viewsList.add(tvSunday3)
            viewsList.add(tvMonday3)
            viewsList.add(tvTuesday3)
            viewsList.add(tvWednesday3)
            viewsList.add(tvThursday3)
            viewsList.add(tvFriday3)
            viewsList.add(tvSaturday3)

            viewsList.add(tvSunday4)
            viewsList.add(tvMonday4)
            viewsList.add(tvTuesday4)
            viewsList.add(tvWednesday4)
            viewsList.add(tvThursday4)
            viewsList.add(tvFriday4)
            viewsList.add(tvSaturday4)

            viewsList.add(tvSunday5)
            viewsList.add(tvMonday5)
            viewsList.add(tvTuesday5)
            viewsList.add(tvWednesday5)
            viewsList.add(tvThursday5)
            viewsList.add(tvFriday5)
            viewsList.add(tvSaturday5)

            /*viewsList.add(tvSunday6)
            viewsList.add(tvMonday6)
            viewsList.add(tvTuesday6)
            viewsList.add(tvWednesday6)
            viewsList.add(tvThursday6)
            viewsList.add(tvFriday6)
            viewsList.add(tvSaturday6)*/

            setValues( startDay )

        }

        private fun setValues(skip: Int) {

            for( skipped in 0 until skip ) {
                viewsList[skipped].text = ""
            }

            for( skipped in monthDates - 1 until 35 ) {
                viewsList[skipped].text = ""
            }

            for((dayIndex, i) in (1..monthDates).withIndex()) {
                viewsList[dayIndex + (skip - 1) ].text = i.toString()
            }
        }

    }
}