package com.ninebx.ui.home.lists.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

import com.ninebx.R
import com.ninebx.ui.home.lists.model.AddedItem

import java.util.ArrayList

class RecyclerAdapter(private var myList: ArrayList<AddedItem>?) : RecyclerView.Adapter<RecyclerAdapter.RecyclerItemViewHolder>() {
    internal var mLastPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return RecyclerItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.etTitleTextView.text = myList!![position].strAddedItem
        mLastPosition = position
    }

    override fun getItemCount(): Int {
        return if (null != myList) myList!!.size else 0
    }

    fun notifyData(myList: ArrayList<AddedItem>) {
        this.myList = myList
        notifyDataSetChanged()
    }

    internal inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        private val etTitleTextView: TextView
        private val layoutAddedList: RelativeLayout

        init {
            etTitleTextView = parent.findViewById<View>(R.id.txtListAdded) as TextView
            layoutAddedList = parent.findViewById<View>(R.id.layoutAddedList) as RelativeLayout
        }
    }

    fun add(location: Int, iName: String) {
        notifyItemInserted(location)
    }
}