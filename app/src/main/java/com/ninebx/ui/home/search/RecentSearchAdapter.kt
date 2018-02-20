package com.ninebx.ui.home.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedRecentSearch

/**
 * Created by smrit on 19-02-2018.
 */
class RecentSearchAdapter(private val mRecentSearch: ArrayList<DecryptedRecentSearch>) : RecyclerView.Adapter<RecentSearchAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.row_recentsearch, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return if( mRecentSearch.size > 5 ) 5 else mRecentSearch.size
    }

    override fun onBindViewHolder(holder: RecentSearchAdapter.ViewHolder, position: Int) {
        holder.tvRecentSearch.text = mRecentSearch[position].listName
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvRecentSearch : TextView = view.findViewById<View>(R.id.txtRecentSearch) as TextView
    }
}

