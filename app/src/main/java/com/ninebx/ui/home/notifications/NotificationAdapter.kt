package com.ninebx.ui.home.notifications

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import com.ninebx.R.layout.row_notification
import com.ninebx.ui.base.realm.decrypted.DecryptedNotifications

/**
 * Created by smrit on 20-02-2018.
 */
class NotificationAdapter(val data: ArrayList<DecryptedNotifications>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NotificationAdapter.ViewHolder, position: Int) {
        holder.txtTitle.text = data[position].subTitle
        holder.txtMessage.text = data[position].message
        holder.txtDueDate.text = data[position].dueDate
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val rvView = LayoutInflater.from(parent!!.context).inflate(row_notification, parent, false)
        return ViewHolder(rvView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle : TextView = view.findViewById<View>(R.id.tvTitle) as TextView
        val txtMessage : TextView = view.findViewById<View>(R.id.tvMessage) as TextView
        val txtDueDate : TextView = view.findViewById<View>(R.id.tvDueDate) as TextView
    }
}