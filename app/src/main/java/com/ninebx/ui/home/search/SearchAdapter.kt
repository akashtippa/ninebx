package com.ninebx.ui.home.search

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.realm.SearchItemClickListener
import com.ninebx.ui.base.realm.decrypted.DecryptedHomeList
import com.ninebx.utility.AppLogger


/**
 * Created by smrit on 14-02-2018.
 */
class SearchAdapter(private val searchItems: ArrayList<Level3SearchItem>, private val adapterClickListener: SearchItemClickListener) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.row_search, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return searchItems.size
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.textView.text = searchItems[position].itemName
    }


    fun restoreAt(position: Int, iItem: Level3SearchItem) {
        searchItems!!.add(position, iItem)
        notifyItemRemoved(position)
    }


    fun removeAt(position: Int) {
        searchItems!!.removeAt(position)
        notifyItemRemoved(position)
    }

    fun add(position: Int , item: Level3SearchItem) {
        searchItems!!.add(position,item)
        notifyItemInserted(position)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                adapterClickListener.onItemClick(position, searchItems[position].itemIndex, searchItems[position])
            }
        }

        val textView: TextView = view.findViewById<View>(R.id.txtListSearch) as TextView

        init {
            view.setOnClickListener(this)
        }
    }
}