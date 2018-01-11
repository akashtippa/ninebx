package com.ninebx.ui.home.calendar

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import java.util.*
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.AdapterClickListener
import com.ninebx.ui.home.calendar.DaysRecyclerViewAdapter.ViewHolder

/**
 * Created by Alok on 09/01/18.
 */
class DaysRecyclerViewAdapter( val monthDates: Int, val startDay: Int, var selectedDate: Int, val adapterClickListener: DaysAdapterClickListener ) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

       /* holder!!.tvSunday1.isSelected = isSelected(holder.tvSunday1)
        holder.tvMonday1.isSelected = isSelected(holder.tvMonday1)
        holder.tvTuesday1.isSelected = isSelected(holder.tvTuesday1)
        holder.tvWednesday1.isSelected = isSelected(holder.tvWednesday1)
        holder.tvThursday1.isSelected = isSelected(holder.tvThursday1)
        holder.tvFriday1.isSelected = isSelected(holder.tvFriday1)
        holder.tvSaturday1.isSelected = isSelected(holder.tvSaturday1)

        holder.tvSunday2.isSelected = isSelected(holder.tvSunday2)
        holder.tvMonday2.isSelected = isSelected(holder.tvMonday2)
        holder.tvTuesday2.isSelected = isSelected(holder.tvTuesday2)
        holder.tvWednesday2.isSelected = isSelected(holder.tvWednesday2)
        holder.tvThursday2.isSelected = isSelected(holder.tvThursday2)
        holder.tvFriday2.isSelected = isSelected(holder.tvFriday2)
        holder.tvSaturday2.isSelected = isSelected(holder.tvSaturday2)

        holder.tvSunday3.isSelected = isSelected(holder.tvSunday3)
        holder.tvMonday3.isSelected = isSelected(holder.tvMonday3)
        holder.tvTuesday3.isSelected = isSelected(holder.tvTuesday3)
        holder.tvWednesday3.isSelected = isSelected(holder.tvWednesday3)
        holder.tvThursday3.isSelected = isSelected(holder.tvThursday3)
        holder.tvFriday3.isSelected = isSelected(holder.tvFriday3)
        holder.tvSaturday3.isSelected = isSelected(holder.tvSaturday3)

        holder.tvSunday4.isSelected = isSelected(holder.tvSunday4)
        holder.tvMonday4.isSelected = isSelected(holder.tvMonday4)
        holder.tvTuesday4.isSelected = isSelected(holder.tvTuesday4)
        holder.tvWednesday4.isSelected = isSelected(holder.tvWednesday4)
        holder.tvThursday4.isSelected = isSelected(holder.tvThursday4)
        holder.tvFriday4.isSelected = isSelected(holder.tvFriday4)
        holder.tvSaturday4.isSelected = isSelected(holder.tvSaturday4)

        holder.tvSunday5.isSelected = isSelected(holder.tvSunday5)
        holder.tvMonday5.isSelected = isSelected(holder.tvMonday5)
        holder.tvTuesday5.isSelected = isSelected(holder.tvTuesday5)
        holder.tvWednesday5.isSelected = isSelected(holder.tvWednesday5)
        holder.tvThursday5.isSelected = isSelected(holder.tvThursday5)
        holder.tvFriday5.isSelected = isSelected(holder.tvFriday5)
        holder.tvSaturday5.isSelected = isSelected(holder.tvSaturday5)

        holder.tvSunday6.isSelected = isSelected(holder.tvSunday6)
        holder.tvMonday6.isSelected = isSelected(holder.tvMonday6)
        holder.tvTuesday6.isSelected = isSelected(holder.tvTuesday6)
        holder.tvWednesday6.isSelected = isSelected(holder.tvWednesday6)
        holder.tvThursday6.isSelected = isSelected(holder.tvThursday6)
        holder.tvFriday6.isSelected = isSelected(holder.tvFriday6)
        holder.tvSaturday6.isSelected = isSelected(holder.tvSaturday6)*/

        setDrawable( holder!!.tvSunday1)
        setDrawable( holder.tvMonday1)
        setDrawable( holder.tvTuesday1)
        setDrawable( holder.tvWednesday1)
        setDrawable( holder.tvThursday1)
        setDrawable( holder.tvFriday1)
        setDrawable( holder.tvSaturday1)

        setDrawable( holder.tvSunday2)
        setDrawable( holder.tvMonday2)
        setDrawable( holder.tvTuesday2)
        setDrawable( holder.tvWednesday2)
        setDrawable( holder.tvThursday2)
        setDrawable( holder.tvFriday2)
        setDrawable( holder.tvSaturday2)

        setDrawable( holder.tvSunday3)
        setDrawable( holder.tvMonday3)
        setDrawable( holder.tvTuesday3)
        setDrawable( holder.tvWednesday3)
        setDrawable( holder.tvThursday3)
        setDrawable( holder.tvFriday3)
        setDrawable( holder.tvSaturday3)

        setDrawable( holder.tvSunday4)
        setDrawable( holder.tvMonday4)
        setDrawable( holder.tvTuesday4)
        setDrawable( holder.tvWednesday4)
        setDrawable( holder.tvThursday4)
        setDrawable( holder.tvFriday4)
        setDrawable( holder.tvSaturday4)

        setDrawable( holder.tvSunday5)
        setDrawable( holder.tvMonday5)
        setDrawable( holder.tvTuesday5)
        setDrawable( holder.tvWednesday5)
        setDrawable( holder.tvThursday5)
        setDrawable( holder.tvFriday5)
        setDrawable( holder.tvSaturday5)

        setDrawable( holder.tvSunday6)
        setDrawable( holder.tvMonday6)
        setDrawable( holder.tvTuesday6)
        setDrawable( holder.tvWednesday6)
        setDrawable( holder.tvThursday6)
        setDrawable( holder.tvFriday6)
        setDrawable( holder.tvSaturday6)

    }

    private fun setDrawable(textView: TextView?) {
        textView!!.background = if( textView.text.toString().isNotEmpty() && textView.text.toString().toInt() == selectedDate ) mSelectedDrawable
                              else if( textView.text.toString().isNotEmpty() && textView.text.toString().toInt() % 7 != 0 ) mEventDrawable
                              else mUnSelectedDrawable


        textView.setTextColor(if( textView.text.toString().isNotEmpty() && textView.text.toString().toInt() == selectedDate ) mWhiteColor
        else if( textView.text.toString().isNotEmpty() && textView.text.toString().toInt() % 7 != 0 ) mBlackColor
        else mBlackColor)
    }

    private fun isSelected(textView : TextView?): Boolean {
        return textView!!.text.isNotEmpty() && textView.text.toString().toInt() == selectedDate
    }
    private lateinit var mEventDrawable : Drawable
    private lateinit var mSelectedDrawable : Drawable
    private lateinit var mUnSelectedDrawable : Drawable

    private var mBlackColor : Int = 0
    private var mWhiteColor : Int = 0
    
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        
        mEventDrawable = ContextCompat.getDrawable(parent!!.context, R.drawable.event_day)
        mSelectedDrawable = ContextCompat.getDrawable(parent.context, R.drawable.selected_day)
        mUnSelectedDrawable = ContextCompat.getDrawable(parent.context, R.drawable.unselected_day)

        mBlackColor = ContextCompat.getColor(parent.context, R.color.black)
        mWhiteColor = ContextCompat.getColor(parent.context, R.color.white)

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_days, parent, false))
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(view: View?) {
            val dayTextView : TextView = view as TextView
            selectedDate = dayTextView.text.toString().toInt()
            adapterClickListener.onDayClick( selectedDate )
            notifyDataSetChanged()
        }

        private val viewsList = ArrayList<TextView>()

        val tvSunday1 = itemView!!.findViewById<TextView>(R.id.tvSunday1)
        val tvSunday2 = itemView!!.findViewById<TextView>(R.id.tvSunday2)
        val tvSunday3 = itemView!!.findViewById<TextView>(R.id.tvSunday3)
        val tvSunday4 = itemView!!.findViewById<TextView>(R.id.tvSunday4)
        val tvSunday5 = itemView!!.findViewById<TextView>(R.id.tvSunday5)
        val tvSunday6 = itemView!!.findViewById<TextView>(R.id.tvSunday6)

        val tvMonday1 = itemView!!.findViewById<TextView>(R.id.tvMonday1)
        val tvMonday2 = itemView!!.findViewById<TextView>(R.id.tvMonday2)
        val tvMonday3 = itemView!!.findViewById<TextView>(R.id.tvMonday3)
        val tvMonday4 = itemView!!.findViewById<TextView>(R.id.tvMonday4)
        val tvMonday5 = itemView!!.findViewById<TextView>(R.id.tvMonday5)
        val tvMonday6 = itemView!!.findViewById<TextView>(R.id.tvMonday6)

        val tvTuesday1 = itemView!!.findViewById<TextView>(R.id.tvTuesday1)
        val tvTuesday2 = itemView!!.findViewById<TextView>(R.id.tvTuesday2)
        val tvTuesday3 = itemView!!.findViewById<TextView>(R.id.tvTuesday3)
        val tvTuesday4 = itemView!!.findViewById<TextView>(R.id.tvTuesday4)
        val tvTuesday5 = itemView!!.findViewById<TextView>(R.id.tvTuesday5)
        val tvTuesday6 = itemView!!.findViewById<TextView>(R.id.tvTuesday6)

        val tvWednesday1 = itemView!!.findViewById<TextView>(R.id.tvWednesday1)
        val tvWednesday2 = itemView!!.findViewById<TextView>(R.id.tvWednesday2)
        val tvWednesday3 = itemView!!.findViewById<TextView>(R.id.tvWednesday3)
        val tvWednesday4 = itemView!!.findViewById<TextView>(R.id.tvWednesday4)
        val tvWednesday5 = itemView!!.findViewById<TextView>(R.id.tvWednesday5)
        val tvWednesday6 = itemView!!.findViewById<TextView>(R.id.tvWednesday6)

        val tvThursday1 = itemView!!.findViewById<TextView>(R.id.tvThursday1)
        val tvThursday2 = itemView!!.findViewById<TextView>(R.id.tvThursday2)
        val tvThursday3 = itemView!!.findViewById<TextView>(R.id.tvThursday3)
        val tvThursday4 = itemView!!.findViewById<TextView>(R.id.tvThursday4)
        val tvThursday5 = itemView!!.findViewById<TextView>(R.id.tvThursday5)
        val tvThursday6 = itemView!!.findViewById<TextView>(R.id.tvThursday6)

        val tvFriday1 = itemView!!.findViewById<TextView>(R.id.tvFriday1)
        val tvFriday2 = itemView!!.findViewById<TextView>(R.id.tvFriday2)
        val tvFriday3 = itemView!!.findViewById<TextView>(R.id.tvFriday3)
        val tvFriday4 = itemView!!.findViewById<TextView>(R.id.tvFriday4)
        val tvFriday5 = itemView!!.findViewById<TextView>(R.id.tvFriday5)
        val tvFriday6 = itemView!!.findViewById<TextView>(R.id.tvFriday6)

        val tvSaturday1 = itemView!!.findViewById<TextView>(R.id.tvSaturday1)
        val tvSaturday2 = itemView!!.findViewById<TextView>(R.id.tvSaturday2)
        val tvSaturday3 = itemView!!.findViewById<TextView>(R.id.tvSaturday3)
        val tvSaturday4 = itemView!!.findViewById<TextView>(R.id.tvSaturday4)
        val tvSaturday5 = itemView!!.findViewById<TextView>(R.id.tvSaturday5)
        val tvSaturday6 = itemView!!.findViewById<TextView>(R.id.tvSaturday6)

        init {

            tvSunday1.setOnClickListener( this )
            tvMonday1.setOnClickListener( this )
            tvTuesday1.setOnClickListener( this )
            tvWednesday1.setOnClickListener( this )
            tvThursday1.setOnClickListener( this )
            tvFriday1.setOnClickListener( this )
            tvSaturday1.setOnClickListener( this )

            tvSunday2.setOnClickListener( this )
            tvMonday2.setOnClickListener( this )
            tvTuesday2.setOnClickListener( this )
            tvWednesday2.setOnClickListener( this )
            tvThursday2.setOnClickListener( this )
            tvFriday2.setOnClickListener( this )
            tvSaturday2.setOnClickListener( this )

            tvSunday3.setOnClickListener( this )
            tvMonday3.setOnClickListener( this )
            tvTuesday3.setOnClickListener( this )
            tvWednesday3.setOnClickListener( this )
            tvThursday3.setOnClickListener( this )
            tvFriday3.setOnClickListener( this )
            tvSaturday3.setOnClickListener( this )

            tvSunday4.setOnClickListener( this )
            tvMonday4.setOnClickListener( this )
            tvTuesday4.setOnClickListener( this )
            tvWednesday4.setOnClickListener( this )
            tvThursday4.setOnClickListener( this )
            tvFriday4.setOnClickListener( this )
            tvSaturday4.setOnClickListener( this )

            tvSunday5.setOnClickListener( this )
            tvMonday5.setOnClickListener( this )
            tvTuesday5.setOnClickListener( this )
            tvWednesday5.setOnClickListener( this )
            tvThursday5.setOnClickListener( this )
            tvFriday5.setOnClickListener( this )
            tvSaturday5.setOnClickListener( this )

            tvSunday6.setOnClickListener( this )
            tvMonday6.setOnClickListener( this )
            tvTuesday6.setOnClickListener( this )
            tvWednesday6.setOnClickListener( this )
            tvThursday6.setOnClickListener( this )
            tvFriday6.setOnClickListener( this )
            tvSaturday6.setOnClickListener( this )


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

            viewsList.add(tvSunday6)
            viewsList.add(tvMonday6)
            viewsList.add(tvTuesday6)
            viewsList.add(tvWednesday6)
            viewsList.add(tvThursday6)
            viewsList.add(tvFriday6)
            viewsList.add(tvSaturday6)

            setValues( startDay )

        }

        private fun setValues(skip: Int) {

            for( skipped in 0 until skip ) {
                viewsList[skipped].text = ""
            }

            for( skipped in monthDates - 1 until 42 ) {
                viewsList[skipped].text = ""
            }

            for((dayIndex, i) in (1..monthDates).withIndex()) {
                if( dayIndex + (skip - 1 ) < 42 ) {
                    viewsList[dayIndex + (skip - 1) ].text = i.toString()
                    viewsList[dayIndex + (skip - 1)].isSelected = i == selectedDate
                }


            }
        }

    }
}