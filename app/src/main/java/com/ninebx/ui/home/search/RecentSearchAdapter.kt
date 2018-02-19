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
class RecentSearchAdapter(context: Context?, val mRecentSearch: ArrayList<DecryptedRecentSearch>) : RecyclerView.Adapter<RecentSearchAdapter.Viewholder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Viewholder? {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.row_recentsearch, parent, false)
        return Viewholder(v)
    }

    override fun getItemCount(): Int {
        return mRecentSearch.size
    }

    override fun onBindViewHolder(holder: RecentSearchAdapter.Viewholder, position: Int) {
        holder.tvRecentSearch.text = mRecentSearch[position].mainCategory
    }

    class Viewholder(view : View) : RecyclerView.ViewHolder(view) {
        val tvRecentSearch : TextView = view.findViewById<View>(R.id.txtRecentSearch) as TextView
    }
}

