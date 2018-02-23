package com.ninebx.ui.home.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.home.account.interfaces.IMemoryAdded
import com.ninebx.utility.AppLogger
import com.ninebx.utility.decryptString
import java.util.*

internal class MemoriesDateAdapter(private var myList: ArrayList<Date>, private val iMemoryAdded: IMemoryAdded) : RecyclerView.Adapter<MemoriesDateAdapter.RecyclerItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_date, parent, false)
        return RecyclerItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val member = myList[position]

        AppLogger.d("Decrypt", "Decrypting : " + member.toString())
        var date = member.strDate.decryptString()

        val separated = date.split(",")
        separated[0]
        separated[1]

        holder.txtDate.text = separated[1]

        holder.txtDate.setOnClickListener {
            iMemoryAdded.onDateClicked(separated[1])
        }
    }

    override fun getItemCount(): Int {
        return if (null != myList) myList.size else 0
    }


    internal inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        val pos = adapterPosition

        val txtDate: TextView = parent.findViewById<View>(R.id.txtDate) as TextView

    }

}