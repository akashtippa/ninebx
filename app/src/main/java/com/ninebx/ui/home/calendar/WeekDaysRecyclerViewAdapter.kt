package com.ninebx.ui.home.calendar

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alok on 11/01/18.
 */
class WeekDaysRecyclerViewAdapter (val weekDates : ArrayList<Int>,
                                   var selectedDate: Int,
                                   private val month : Int,
                                   private val year : Int,
                                   private val datesWithEvents: ArrayList<Date>,
                                   private val dateStringWithEvents: ArrayList<String>,
                                   val adapterClickListener: DaysAdapterClickListener) : RecyclerView.Adapter<WeekDaysRecyclerViewAdapter.ViewHolder>() {

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
            isEventPresent = dateStringWithEvents.contains(dateFormat.format(calendarDay.time))
        }

        textView.background = if( textView.text.toString().isNotEmpty() && textView.text.toString().toInt() == selectedDate ) mSelectedDrawable
        else if( isEventPresent ) mEventDrawable
        else if( isCurrentDay ) mCurrentDayDrawable
        else mUnSelectedDrawable


        textView.setTextColor(if( textView.text.toString().isNotEmpty() && textView.text.toString().toInt() == selectedDate ) mWhiteColor
        else if( isEventPresent ) mBlackColor
        else if( isCurrentDay ) mBlackColor
        else mBlackColor)
    }

    private lateinit var mEventDrawable : Drawable
    private lateinit var mSelectedDrawable : Drawable
    private lateinit var mUnSelectedDrawable : Drawable
    private lateinit var mCurrentDayDrawable : Drawable

    private var mBlackColor : Int = 0
    private var mWhiteColor : Int = 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        mEventDrawable = ContextCompat.getDrawable(parent!!.context, R.drawable.event_day)!!
        mSelectedDrawable = ContextCompat.getDrawable(parent.context, R.drawable.selected_day)!!
        mUnSelectedDrawable = ContextCompat.getDrawable(parent.context, R.drawable.unselected_day)!!
        mCurrentDayDrawable = ContextCompat.getDrawable(parent.context, R.drawable.current_day)!!

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


            setValues( )

        }

        private fun setValues() {

            for((dayIndex, date) in weekDates.withIndex()) {
                viewsList[ dayIndex ].text = date.toString()
                viewsList[dayIndex].isSelected = date == selectedDate
            }

        }

    }

}