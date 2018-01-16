package com.ninebx.ui.home.lists.adapter

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.lists.model.AddedSubItem
import java.util.*


internal class SubListsAdapter(private var myList: ArrayList<AddedSubItem>?) : RecyclerView.Adapter<SubListsAdapter.RecyclerItemViewHolder>() {
    internal var mLastPosition = 0

    lateinit var selectedDate: String
    lateinit var selectedYear: String
    lateinit var selectedMonth: String
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.super_sub_lists, parent, false)
        return RecyclerItemViewHolder(view)
    }


    fun removeAt(position: Int) {
        myList!!.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.edtSuperSubItemName.setText(myList!![position].strAddedItem)
        mLastPosition = position
        holder.txtDate.setOnClickListener {
            val c = Calendar.getInstance()
            val mYear = c.get(Calendar.YEAR) // current year
            val mMonth = c.get(Calendar.MONTH) // current month
            val mDay = c.get(Calendar.DAY_OF_MONTH) // current day

            var datePickerDialog: DatePickerDialog? = null
            datePickerDialog = DatePickerDialog(NineBxApplication.instance.activityInstance,
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        selectedDate = dayOfMonth.toString()
                        selectedMonth = (monthOfYear + 1).toString()
                        selectedYear = year.toString()

                        holder.txtDate.text = ("$selectedMonth/$selectedDate/$selectedYear")
                    }, mYear, mMonth, mDay)
            datePickerDialog.show()
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
        val datePickerDialog: DatePickerDialog? = null
        val edtSuperSubItemName: EditText = parent.findViewById<View>(R.id.edtSuperSubItemName) as EditText
        val rdSuperSubItem: RadioButton = parent.findViewById<View>(R.id.rdSuperSubItem) as RadioButton
        var txtDate: TextView = parent.findViewById<View>(R.id.txtDate) as TextView
        val simpleDatePicker: DatePicker = parent.findViewById<View>(R.id.simpleDatePicker) as DatePicker

    }

    fun add(location: Int, iName: String) {
        notifyItemInserted(location)
    }
}