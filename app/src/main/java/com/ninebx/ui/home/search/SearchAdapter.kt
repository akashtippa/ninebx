package com.ninebx.ui.home.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedFinancial

/**
 * Created by smrit on 14-02-2018.
 */
class SearchAdapter(private val decryptedFinance: ArrayList<DecryptedFinancial>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.row_search, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return decryptedFinance.size
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.bindItems(decryptedFinance[position])
    }


    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = view.findViewById<View>(R.id.txtListSearch) as TextView

        fun bindItems(decryptedFinance: DecryptedFinancial) {

            textView.text = decryptedFinance.accountName

        }
    }
}