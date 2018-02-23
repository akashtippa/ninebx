package com.ninebx.ui.home.notifications

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import com.ninebx.R.layout.row_notification
import com.ninebx.ui.base.realm.decrypted.DecryptedNotifications
import com.ninebx.utility.AppLogger
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by smrit on 20-02-2018.
 */
class NotificationAdapter(val data: ArrayList<DecryptedNotifications>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    lateinit var clickListener :  ClickListener
    private var redColor : Int = -1
    private var normalColor : Int = -1
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NotificationAdapter.ViewHolder, position: Int) {
        holder.txtBoxName.text = data[position].boxName
        holder.txtMessage.text = data[position].message
        holder.txtDueDate.text = data[position].dueDate
        holder.txtSubTitle.text = data[position].subTitle
        holder.txtMessage.typeface = if ( data[position].read ) Typeface.DEFAULT else Typeface.DEFAULT_BOLD
        notificationValidity(data[position].dueDate, holder.txtDueDate)
    }

    @SuppressLint("ResourceAsColor")
    private fun notificationValidity(dueDate: String, txtDueDate: TextView) {
        var c : Date = Calendar.getInstance().time
        AppLogger.d("CurrentDate ", "Current Date And Time" + c)
        if (dueDate.length == 10) {
            try {
                var sdf: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                var getDueDate = sdf.parse(dueDate)
                AppLogger.d("Date", "Converting string to date " + getDueDate)
                if (c.after(getDueDate)) txtDueDate.setTextColor(redColor)
                else txtDueDate.setTextColor(normalColor)
            } catch (e: Exception) {
                AppLogger.d("Date", "Exception thrown while converting string to date " + e.message)
            }
        }
        else if(dueDate.length == 7) {
            try {
                var simpleDateFormat = SimpleDateFormat("MM/yyyy")
                var getDueDate = simpleDateFormat.parse(dueDate)
                AppLogger.d("Date", "Converting string to date 2nd try-catch " + getDueDate)
            } catch (e: Exception) {
                AppLogger.d("Date", "Exception thrown while converting string to date 2nd try-catch " + e.message)
            }
        }
        }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        redColor = ContextCompat.getColor(parent!!.context, R.color.red_300)
        normalColor = ContextCompat.getColor(parent!!.context, R.color.grey_400)
        val rvView = LayoutInflater.from(parent!!.context).inflate(row_notification, parent, false)
        return ViewHolder(rvView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), ClickListener, View.OnClickListener, View.OnLongClickListener {

        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                onItemLongClick(position, v!!, txtMessage)
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

        override fun onItemClick(position: Int, v: View, id: Long) {
            clickListener.onItemClick(getAdapterPosition(), v, data[position].id)
        }

        override fun onItemLongClick(position: Int, v: View, txtMessage: TextView) {
            clickListener.onItemLongClick(getAdapterPosition(), v, txtMessage)
        }
    }

     fun onClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

     interface ClickListener {
        fun onItemClick(position: Int, v: View, id: Long)
        fun onItemLongClick(position: Int, v: View, txtMessage: TextView)
     }

    fun delete(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }
}