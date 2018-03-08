package com.ninebx.ui.home.calendar

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import java.util.*
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.home.calendar.DaysRecyclerViewAdapter.ViewHolder
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

/**
 * Created by Alok on 09/01/18.
 */
class DaysRecyclerViewAdapter(val monthDates: Int,
                              val startDay: Int,
                              private val month : Int,
                              private val year : Int,
                              private val datesWithEvents: ArrayList<Date>,
                              private val eventDateStrings : ArrayList<String>,
                              var selectedDate: Int,
                              private var isWeekView : Boolean,
                              private var weekOfMonth : Int,
                              val adapterClickListener: DaysAdapterClickListener) : RecyclerView.Adapter<ViewHolder>() {

    var dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    var mCurrentDateCalendar = Calendar.getInstance()
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

        if( isWeekView ) {
            holder.layoutWeek1.hide()
            holder.layoutWeek2.hide()
            holder.layoutWeek3.hide()
            holder.layoutWeek4.hide()
            holder.layoutWeek5.hide()
            holder.layoutWeek6.hide()
            when( weekOfMonth ) {
                1 -> holder.layoutWeek1.show()
                2 -> holder.layoutWeek2.show()
                3 -> holder.layoutWeek3.show()
                4 -> holder.layoutWeek4.show()
                5 -> holder.layoutWeek5.show()
                6 -> holder.layoutWeek6.show()
            }
        }
        else {
            holder.layoutWeek1.show()
            holder.layoutWeek2.show()
            holder.layoutWeek3.show()
            holder.layoutWeek4.show()
            holder.layoutWeek5.show()
            holder.layoutWeek6.show()
        }

    }

    private fun setDrawable(textView: TextView?) {

        var isEventPresent = false
        var isCurrentDay = false
        var dateInt = 0

        dateInt = if( textView!!.text.toString().isEmpty() ) 0 else textView.text.toString().toInt()
        if( dateInt != 0 ) {
            val calendarDay = Calendar.getInstance()
            calendarDay.set(Calendar.DAY_OF_MONTH, dateInt)
            calendarDay.set(Calendar.MONTH, month)
            calendarDay.set(Calendar.YEAR, year)
            isCurrentDay = (mCurrentDateCalendar.get(Calendar.DAY_OF_MONTH) == calendarDay.get(Calendar.DAY_OF_MONTH) &&
                    mCurrentDateCalendar.get(Calendar.MONTH) == calendarDay.get(Calendar.MONTH) &&
                    mCurrentDateCalendar.get(Calendar.YEAR) == calendarDay.get(Calendar.YEAR))
            isEventPresent = eventDateStrings.contains(dateFormat.format(calendarDay.time))
        }

        textView.background = if( textView.text.toString().isNotEmpty() && textView.text.toString().toInt() == selectedDate ) mSelectedDrawable
                              else if( isEventPresent && isCurrentDay ) mCurrentEventDrawable
                              else if( isEventPresent ) mEventDrawable
                              else if( isCurrentDay ) mCurrentDayDrawable
                              else mUnSelectedDrawable


        textView.setTextColor(if( textView.text.toString().isNotEmpty() && textView.text.toString().toInt() == selectedDate ) mWhiteColor
        else if( isEventPresent && isCurrentDay ) mBlackColor
        else if( isEventPresent ) mBlackColor
        else if( isCurrentDay ) mBlackColor
        else mBlackColor)

    }

    private fun isSelected(textView : TextView?): Boolean {
        return textView!!.text.isNotEmpty() && textView.text.toString().toInt() == selectedDate
    }

    private lateinit var mEventDrawable : Drawable
    private lateinit var mSelectedDrawable : Drawable
    private lateinit var mUnSelectedDrawable : Drawable
    private lateinit var mCurrentDayDrawable : Drawable
    private lateinit var mCurrentEventDrawable : Drawable

    private var mBlackColor : Int = 0
    private var mWhiteColor : Int = 0
    
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        
        mEventDrawable = ContextCompat.getDrawable(parent!!.context, R.drawable.event_day)!!
        mSelectedDrawable = ContextCompat.getDrawable(parent.context, R.drawable.selected_day)!!
        mUnSelectedDrawable = ContextCompat.getDrawable(parent.context, R.drawable.unselected_day)!!
        mCurrentDayDrawable = ContextCompat.getDrawable(parent.context, R.drawable.current_day)!!
        mCurrentEventDrawable = ContextCompat.getDrawable(parent.context, R.drawable.current_event_day)!!

        mBlackColor = ContextCompat.getColor(parent.context, R.color.black)
        mWhiteColor = ContextCompat.getColor(parent.context, R.color.white)

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_days, parent, false))
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

        val layoutWeek1 = itemView!!.findViewById<LinearLayout>(R.id.layoutWeek1)
        val layoutWeek2 = itemView!!.findViewById<LinearLayout>(R.id.layoutWeek2)
        val layoutWeek3 = itemView!!.findViewById<LinearLayout>(R.id.layoutWeek3)
        val layoutWeek4 = itemView!!.findViewById<LinearLayout>(R.id.layoutWeek4)
        val layoutWeek5 = itemView!!.findViewById<LinearLayout>(R.id.layoutWeek5)
        val layoutWeek6 = itemView!!.findViewById<LinearLayout>(R.id.layoutWeek6)

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

    fun toggleWeekView(selectedDate: Int, weekOfMonth: Int, weekView: Boolean) {
        this.selectedDate = selectedDate
        this.weekOfMonth = weekOfMonth
        this.isWeekView = weekView
        notifyDataSetChanged()
    }
}