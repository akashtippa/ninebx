package com.ninebx.ui.home.notifications

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
class NotificationAdapter(val data: ArrayList<DecryptedNotifications>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    lateinit var clickListener :  ClickListener

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NotificationAdapter.ViewHolder, position: Int) {
        holder.txtBoxName.text = data[position].boxName
        holder.txtMessage.text = data[position].message
        holder.txtDueDate.text = data[position].dueDate
        holder.txtSubTitle.text = data[position].subTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val rvView = LayoutInflater.from(parent!!.context).inflate(row_notification, parent, false)
        return ViewHolder(rvView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), ClickListener, View.OnClickListener, View.OnLongClickListener {
        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                onItemLongClick(position, v!!)
            }
            return true
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                onItemClick(position, v!!, data[position].id)
            }
        }

        val txtBoxName: TextView = view.findViewById<View>(R.id.tvBoxName) as TextView
        val txtMessage: TextView = view.findViewById<View>(R.id.tvMessage) as TextView
        val txtDueDate: TextView = view.findViewById<View>(R.id.tvDueDate) as TextView
        val txtSubTitle: TextView = view.findViewById<View>(R.id.tvSubTitle) as TextView
        init {
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        override fun onItemClick(position: Int, v: View, id: Int) {
            clickListener.onItemClick(getAdapterPosition(), v, data[position].id)
        }

        override fun onItemLongClick(position: Int, v: View) {
            clickListener.onItemLongClick(getAdapterPosition(), v)
        }
    }

     fun onClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

     interface ClickListener {
        fun onItemClick(position: Int, v: View, id: Int)
        fun onItemLongClick(position: Int, v: View)
    }

    fun delete(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }
}