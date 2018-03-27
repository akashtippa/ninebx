package com.ninebx.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedEmergencyContacts
import java.util.ArrayList

/**
 * Created by smrit on 27-03-2018.
 */
class EmergencyContactAdapter(val emergencyContactsItems: ArrayList<DecryptedEmergencyContacts>) : RecyclerView.Adapter<EmergencyContactAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.row_contacts, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return emergencyContactsItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val emergencyContacts = emergencyContactsItems[position]
        holder!!.txtContacts.text = emergencyContacts.name
    }

    class ViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        var txtContacts: TextView = parent.findViewById<View>(R.id.txtContacts) as TextView
        val imgDeleteContact: ImageView = parent.findViewById<View>(R.id.imgDeleteContact) as ImageView
        val layoutContacts: LinearLayout = parent.findViewById<View>(R.id.layoutContacts) as LinearLayout
        val imgEditContact: ImageView = parent.findViewById<View>(R.id.imgEditContact) as ImageView
    }
}