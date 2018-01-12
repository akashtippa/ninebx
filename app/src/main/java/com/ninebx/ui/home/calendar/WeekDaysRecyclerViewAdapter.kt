package com.ninebx.ui.home.calendar

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import java.util.ArrayList

/**
 * Created by Alok on 11/01/18.
 */
class WeekDaysRecyclerViewAdapter (val minDate: Int,
                                   val maxDate: Int,
                                   var selectedDate: Int,
                                   val startDay : Int,
                                   val adapterClickListener: DaysAdapterClickListener) : RecyclerView.Adapter<WeekDaysRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {


        setDrawable( holder!!.tvSunday1)
        setDrawable( holder.tvMonday1)
        setDrawable( holder.tvTuesday1)
        setDrawable( holder.tvWednesday1)
        setDrawable( holder.tvThursday1)
        setDrawable( holder.tvFriday1)
        setDrawable( holder.tvSaturday1)

    }

    private fun setDrawable(textView: TextView?) {
        textView!!.background = if( textView.text.toString().isNotEmpty() && textView.text.toString().toInt() == selectedDate ) mSelectedDrawable
        else if( textView.text.toString().isNotEmpty() && textView.text.toString().toInt() % 7 != 0 ) mEventDrawable
        else mUnSelectedDrawable


        textView.setTextColor(if( textView.text.toString().isNotEmpty() && textView.text.toString().toInt() == selectedDate ) mWhiteColor
        else if( textView.text.toString().isNotEmpty() && textView.text.toString().toInt() % 7 != 0 ) mBlackColor
        else mBlackColor)
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

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_week_days, parent, false))
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(view: View?) {
            val dayTextView : TextView = view as TextView
            if( dayTextView.text.toString().isNotEmpty() ) {
                selectedDate = dayTextView.text.toString().toInt()
                adapterClickListener.onDayClick( selectedDate )
                notifyDataSetChanged()
            }
        }

        private val viewsList = ArrayList<TextView>()

        val tvSunday1 = itemView!!.findViewById<TextView>(R.id.tvSunday1)

        val tvMonday1 = itemView!!.findViewById<TextView>(R.id.tvMonday1)

        val tvTuesday1 = itemView!!.findViewById<TextView>(R.id.tvTuesday1)

        val tvWednesday1 = itemView!!.findViewById<TextView>(R.id.tvWednesday1)

        val tvThursday1 = itemView!!.findViewById<TextView>(R.id.tvThursday1)

        val tvFriday1 = itemView!!.findViewById<TextView>(R.id.tvFriday1)

        val tvSaturday1 = itemView!!.findViewById<TextView>(R.id.tvSaturday1)


        init {

            tvSunday1.setOnClickListener( this )
            tvMonday1.setOnClickListener( this )
            tvTuesday1.setOnClickListener( this )
            tvWednesday1.setOnClickListener( this )
            tvThursday1.setOnClickListener( this )
            tvFriday1.setOnClickListener( this )
            tvSaturday1.setOnClickListener( this )

            viewsList.add(tvSunday1)
            viewsList.add(tvMonday1)
            viewsList.add(tvTuesday1)
            viewsList.add(tvWednesday1)
            viewsList.add(tvThursday1)
            viewsList.add(tvFriday1)
            viewsList.add(tvSaturday1)


            setValues( startDay - 1 )

        }

        private fun setValues(skip: Int) {

            var skippedIndex = 0
            for( skipped in 0 until skip ) {
                viewsList[skipped].text = ""
                skippedIndex = skipped
            }

            for( date in minDate..maxDate + 1 ) {
                if( skippedIndex < 7 ) {
                    viewsList[ skippedIndex ].text = date.toString()
                    viewsList[ skippedIndex++ ].isSelected = date == selectedDate
                }
            }

        }

    }

}