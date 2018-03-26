package com.ninebx.ui.home.adapter

import android.annotation.SuppressLint
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedShopping
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.home.account.interfaces.MainContactsAdded

internal class ShoppingItemsAdapter(private var myList: ArrayList<DecryptedShopping>?, private val mainContactsAdded: MainContactsAdded) : RecyclerView.Adapter<ShoppingItemsAdapter.RecyclerItemViewHolder>() {
    internal var mLastPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_service_accounts, parent, false)
        return RecyclerItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val contacts = myList!![position]
        holder.txtInstitutionName.text = contacts.institutionName
        holder.txtAccountName.text = contacts.accountName
        mLastPosition = position

        holder.layoutContacts.setOnClickListener {
            mainContactsAdded.contactsClicked(contacts, false)
        }

        holder.imgDeleteContact.setOnClickListener {
            //show dialog and delete
            mainContactsAdded.contactsDeleted(contacts)
            //deleteContact(contacts)
        }

        holder.imgEditContact.setOnClickListener {
            mainContactsAdded.contactsClicked(contacts, true)
        }
    }

    override fun getItemCount(): Int {
        return myList!!.size
    }

    fun deleteContact(contact: DecryptedShopping) {
        myList!!.remove(contact)
        notifyData(myList!!)
    }

    fun removeAt(position: Int, contactPosition: Contacts) {
        myList!!.removeAt(position)
        notifyItemRemoved(position)
    }

    fun notifyData(myList: ArrayList<DecryptedShopping>) {
        this.myList = myList
        notifyDataSetChanged()
    }

    fun getList(): ArrayList<DecryptedShopping>? {
        return this.myList
    }

    fun updateContact(contact: DecryptedShopping) {
        for(item in myList!!) {
            if(item.id == contact.id) {
                myList!!.remove(item)
                break
            }
        }
        myList!!.add(contact)
        val list = myList!!.sortedWith( compareBy( { it.accountName }))
        myList!!.clear()
        myList = ArrayList(list)
        notifyData(myList!!)
    }

    internal inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        @SuppressLint("CommitTransaction")
        var txtInstitutionName: TextView = parent.findViewById<View>(R.id.txtInstitution) as TextView
        var txtAccountName: TextView = parent.findViewById<View>(R.id.txtAccountName) as TextView
        val imgDeleteContact: ImageView = parent.findViewById<View>(R.id.imgDeleteContact) as ImageView
        val layoutContacts: LinearLayout = parent.findViewById<View>(R.id.layoutContacts) as LinearLayout
        val imgEditContact: ImageView = parent.findViewById<View>(R.id.imgEditContact) as ImageView
    }

    fun add(location: Int, iName: String) {
        notifyItemInserted(location)
    }


}