package com.ninebx.ui.home.search

import android.app.AlertDialog

import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.SearchItemClickListener
import com.ninebx.utility.Constants.SEARCH_EDIT
import com.ninebx.utility.Constants.SEARCH_NORMAL


/**
 * Created by smrit on 14-02-2018.
 */
class SearchAdapter(private val searchItems: ArrayList<Level3SearchItem>, private val mode : Int, private val adapterClickListener: SearchItemClickListener) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        when( mode ) {
            SEARCH_NORMAL -> {
                val v = LayoutInflater.from(parent!!.context).inflate(R.layout.row_search, parent, false)
                return ViewHolder(v)
            }
            SEARCH_EDIT -> {
                val v = LayoutInflater.from(parent!!.context).inflate(R.layout.row_search_edit, parent, false)
                return ViewHolder(v)
            }
            else -> {
                val v = LayoutInflater.from(parent!!.context).inflate(R.layout.row_search, parent, false)
                return ViewHolder(v)
            }
        }

    }

    override fun getItemCount(): Int {
        return searchItems.size
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.textView.text = searchItems[position].itemName
        if( mode == SEARCH_EDIT )
        if(  searchItems[position].subHeader.isEmpty() ) {
            holder.txtSubHeader!!.hide()
        }
        else {
            holder.txtSubHeader!!.show()
            holder.txtSubHeader!!.text = searchItems[position].subHeader
        }
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
                when( view!!.id ) {
                    R.id.txtListSearch, R.id.txtSubHeader -> adapterClickListener.onItemClick(position, searchItems[position].itemIndex, searchItems[position], "view")
                    R.id.ivEdit -> adapterClickListener.onItemClick(position, searchItems[position].itemIndex, searchItems[position], "edit")
                    R.id.ivDelete -> {
                        val builder = AlertDialog.Builder(view!!.context)
                        builder.setTitle("NineBx")
                        builder.setCancelable(false)
                        builder.setMessage("Are you sure you want to delete?")
                        builder.setPositiveButton("Ok") { dialog, p1 ->
                            val deletedItem = searchItems.removeAt(position)
                            adapterClickListener.onItemClick(position, deletedItem.itemIndex, deletedItem, "delete")
                            notifyDataSetChanged()
                        }
                        builder.setNegativeButton("Cancel") { dialog, which -> dialog?.cancel() }
                        builder.show()
                    }
                }
            }
        }

        val textView: TextView = view.findViewById<View>(R.id.txtListSearch) as TextView
        var ivEdit : ImageView?= null
        var ivDelete : ImageView ?= null
        var txtSubHeader : TextView ?= null

        init {
            if( mode == SEARCH_EDIT ) {
                ivDelete = view.findViewById(R.id.ivDelete)
                ivEdit = view.findViewById(R.id.ivEdit)
                txtSubHeader = view.findViewById(R.id.txtSubHeader)
                ivDelete!!.setOnClickListener(this)
                ivEdit!!.setOnClickListener(this)
                txtSubHeader!!.setOnClickListener(this)
            }
            textView.setOnClickListener(this)

        }
    }
}