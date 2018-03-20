package com.ninebx.ui.home.adapter

import android.annotation.SuppressLint
import android.media.Image
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedContacts
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.home.account.interfaces.IContactsAdded
import com.ninebx.utility.decryptString

internal class ContactsAdapter(private var myList: ArrayList<DecryptedContacts>?, private val iContactsAdded: IContactsAdded) : RecyclerView.Adapter<ContactsAdapter.RecyclerItemViewHolder>() {
    internal var mLastPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_contacts, parent, false)
        return RecyclerItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val contacts = myList!![position]
        holder.txtContacts.text = contacts.firstName + " " + contacts.lastName
        mLastPosition = position

        holder.layoutContacts.setOnClickListener {
            iContactsAdded.contactsClicked(contacts, false)
        }

        holder.imgDeleteContact.setOnClickListener {
            //show dialog and delete
            iContactsAdded.contactsDeleted(contacts)
            deleteContact(contacts)
        }

        holder.imgEditContact.setOnClickListener {
            iContactsAdded.contactsClicked(contacts, true)
        }
    }

    override fun getItemCount(): Int {
        return myList!!.size
    }

    fun deleteContact(contact: DecryptedContacts) {
        myList!!.remove(contact)
        notifyData(myList!!)
    }

    fun removeAt(position: Int, contactPosition: Contacts) {
        myList!!.removeAt(position)
        notifyItemRemoved(position)
    }

    fun notifyData(myList: ArrayList<DecryptedContacts>) {
        this.myList = myList
        notifyDataSetChanged()
    }

    fun getList(): ArrayList<DecryptedContacts>? {
        return this.myList
    }

    fun updateContact(contact: DecryptedContacts) {
        for(item in myList!!) {
            if(item.id == contact.id) {
                myList!!.remove(item)
                break
            }
        }
        myList!!.add(contact)
        val list = myList!!.sortedWith( compareBy( { it.firstName }))
        myList!!.clear()
        myList = ArrayList(list)
        notifyData(myList!!)
    }

    internal inner class RecyclerIndexItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    }

    internal inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        @SuppressLint("CommitTransaction")
        var txtContacts: TextView = parent.findViewById<View>(R.id.txtContacts) as TextView
        val imgDeleteContact: ImageView = parent.findViewById<View>(R.id.imgDeleteContact) as ImageView
        val layoutContacts: LinearLayout = parent.findViewById<View>(R.id.layoutContacts) as LinearLayout
        val imgEditContact: ImageView = parent.findViewById<View>(R.id.imgEditContact) as ImageView
    }

    fun add(location: Int, iName: String) {
        notifyItemInserted(location)
    }


}