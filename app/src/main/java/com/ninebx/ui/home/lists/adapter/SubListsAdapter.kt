package com.ninebx.ui.home.lists.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.home.lists.model.AddedSubItem
import com.ninebx.utility.DateTimeSelectionListener
import com.ninebx.utility.getDateFromPicker
import com.ninebx.utility.getDateMonthYearFormat
import java.util.*

internal class SubListsAdapter(private var myList: ArrayList<AddedSubItem>?) : RecyclerView.Adapter<SubListsAdapter.RecyclerItemViewHolder>() {
    private var mLastPosition = 0

    lateinit var selectedDate: String
    lateinit var selectedYear: String
    lateinit var selectedMonth: String
    private var isSelectedAll: Boolean = false
    private var showSelected: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.super_sub_lists, parent, false)
        return RecyclerItemViewHolder(view)
    }

    fun selectAll() {
        isSelectedAll = true
        notifyDataSetChanged()
    }

    fun deSelectAll() {
        isSelectedAll = false
        notifyDataSetChanged()
    }

    fun showSelected() {
        showSelected = true
    }

    fun hideSelected() {
        showSelected = false
    }

    fun removeAt(position: Int) {
        myList!!.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreAt(position: Int, iItem: AddedSubItem) {
        myList!!.add(position, iItem)
        notifyItemRemoved(position)
    }


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.checkSubList.isChecked = isSelectedAll

        holder.edtSuperSubItemName.setText(myList!![position].strAddedItem)
//        holder.checkSubList.isChecked = myList!![position].isSelected


        if (showSelected) {
            val pos = holder.checkSubList.tag as Int

            if (myList!![pos].isSelected) {
                holder.laySubItems.hide()
            } else {
                holder.laySubItems.show()
            }
        }


        mLastPosition = position
        holder.txtDate.setOnClickListener {
            getDateFromPicker(holder.itemView.context, Calendar.getInstance(), object : DateTimeSelectionListener {
                override fun onDateTimeSelected(selectedDate: Calendar) {
                    holder.txtDate.text = (getDateMonthYearFormat(selectedDate.time))
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return if (null != myList) myList!!.size else 0
    }

    fun notifyData(myList: ArrayList<AddedSubItem>) {
        this.myList = myList
        notifyDataSetChanged()
    }

    internal inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        val edtSuperSubItemName: EditText = parent.findViewById<View>(R.id.edtSuperSubItemName) as EditText
        val laySubItems: RelativeLayout = parent.findViewById<View>(R.id.laySubItems) as RelativeLayout
        val checkSubList: CheckBox = parent.findViewById<View>(R.id.checkSubList) as CheckBox
        var txtDate: TextView = parent.findViewById<View>(R.id.txtDate) as TextView
    }

    fun add(location: Int, iName: String) {
//        myList!!.add(location, iName)
        notifyItemInserted(location)
    }

    fun removeItem(position: Int) {
        myList!!.removeAt(position)
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position)
    }

}