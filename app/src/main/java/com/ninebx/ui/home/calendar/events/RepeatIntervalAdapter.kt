package com.ninebx.ui.home.calendar.events

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.ActionClickListener
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show

/**
 * Created by Alok on 16/01/18.
 */
class RepeatIntervalAdapter( val intervals: ArrayList<String>, var selectedInterval: String, var emptyInterval : String, val actionClickListener : ActionClickListener) : RecyclerView.Adapter<RepeatIntervalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_repeat_interval, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val interval = intervals[position]
        holder!!.tvTitle.text = interval

        if( interval == selectedInterval )
            holder.ivChecked.show()
        else
            holder.ivChecked.hide()

        if( interval == emptyInterval ) holder.divider.show()
        else holder.divider.hide()

    }

    override fun getItemCount(): Int {
        return intervals.count()
    }

    inner class ViewHolder( itemView : View ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(view: View?) {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                selectedInterval = intervals[position]
                actionClickListener.onItemClick(position, selectedInterval)
                notifyDataSetChanged()
            }
        }

        val divider = itemView.findViewById<TextView>(R.id.divider)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val ivChecked = itemView.findViewById<ImageView>(R.id.ivChecked)

        init {
            itemView.setOnClickListener(this)
        }

    }
}