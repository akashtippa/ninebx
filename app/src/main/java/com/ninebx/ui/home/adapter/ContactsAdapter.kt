package com.ninebx.ui.home.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.fragments.SingleContactViewFragment
import com.ninebx.utility.Constants
import com.onegravity.contactpicker.contact.Contact

internal class ContactsAdapter(private var myList: ArrayList<Contact>?) : RecyclerView.Adapter<ContactsAdapter.RecyclerItemViewHolder>() {
    internal var mLastPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_contacts, parent, false)
        return RecyclerItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.txtContacts.text = myList!![position].firstName
        mLastPosition = position
    }

    override fun getItemCount(): Int {
        return myList!!.size
    }

    fun removeAt(position: Int, contactPosition: Contact) {
        myList!!.removeAt(position)
        notifyItemRemoved(position)
    }

    fun notifyData(myList: ArrayList<Contact>) {
        this.myList = myList
        notifyDataSetChanged()
    }

    internal inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent), View.OnClickListener {
        @SuppressLint("CommitTransaction")
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val contactPosition = myList!![position]
                val contactNumber = myList!![position].getPhone(position)
                val contactName = myList!![position].firstName

                when (v!!.id) {
                    R.id.layoutContacts -> {
                        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
                        fragmentTransaction.addToBackStack(null)

                        val bundle = Bundle()
                        bundle.putString(Constants.BUNDLE_CONTACT_NO, contactNumber)
                        bundle.putString(Constants.BUNDLE_CONTACT_NAME, contactName)

                        val singleContactViewFragment = SingleContactViewFragment()
                        singleContactViewFragment.arguments = bundle
                        fragmentTransaction.add(R.id.frameLayout, singleContactViewFragment).commit()
                    }
                /* R.id.txtContacts -> {
                     val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
                     fragmentTransaction.addToBackStack(null)

                     val bundle = Bundle()
                     bundle.putString(Constants.BUNDLE_CONTACT_NO, contactNumber)
                     bundle.putString(Constants.BUNDLE_CONTACT_NAME, contactName)

                     val singleContactViewFragment = SingleContactViewFragment()
                     singleContactViewFragment.arguments = bundle
                     fragmentTransaction.add(R.id.frameLayout, singleContactViewFragment).commit()
                 }
                 R.id.imgEditContact -> {
                     val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
                     fragmentTransaction.addToBackStack(null)

                     val bundle = Bundle()
                     bundle.putString(Constants.BUNDLE_CONTACT_NO, contactNumber)
                     bundle.putString(Constants.BUNDLE_CONTACT_NAME, contactName)

                     val singleContactViewFragment = SingleContactViewFragment()
                     singleContactViewFragment.arguments = bundle
                     fragmentTransaction.add(R.id.frameLayout, singleContactViewFragment).commit()
                 }*/
                    R.id.imgDeleteContact -> {
                        removeAt(position, contactPosition)
                    }
                }

            }

        }

        var txtContacts: TextView = parent.findViewById<View>(R.id.txtContacts) as TextView
        //        val imgEditContact: ImageView = parent.findViewById<View>(R.id.imgEditContact) as ImageView
        val imgDeleteContact: ImageView = parent.findViewById<View>(R.id.imgDeleteContact) as ImageView
        val layoutContacts: LinearLayout = parent.findViewById<View>(R.id.layoutContacts) as LinearLayout

        init {
//            txtContacts.setOnClickListener(this)
//            imgEditContact.setOnClickListener(this)
            imgDeleteContact.setOnClickListener(this)
            layoutContacts.setOnClickListener(this)
        }

    }

    fun add(location: Int, iName: String) {
        notifyItemInserted(location)
    }


}