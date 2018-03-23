package com.ninebx.ui.home.baseCategories

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedMember
import com.ninebx.ui.home.account.addmembers.AddedFamilyMemberAdapter
import com.ninebx.ui.home.account.interfaces.IMemberAdded
import java.util.ArrayList

/**
 * Created by venu on 08-03-2018.
 */
class MemberListAdapter(private var myList: ArrayList<String>, val adapterClickListener: AdapterClickListener ) : RecyclerView.Adapter<MemberListAdapter.RecyclerItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_member_users, parent, false)
        return RecyclerItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val member = myList[position]
        holder.txtProfileName.text = member
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun getItem(position: Int): String {
        return myList[position]
    }

    fun removeItem(position: Int) {
        myList.remove(myList[position])
    }

    inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent), View.OnClickListener {
        override fun onClick(view: View?) {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                when( view!!.id ) {
                   R.id.txtProfileName -> {
                       val position = adapterPosition
                       if( position != RecyclerView.NO_POSITION )
                           adapterClickListener.onItemClick(position)
                   }
                }
            }

        }

        val txtProfileName: TextView = parent.findViewById<View>(R.id.txtProfileName) as TextView

        init {
            txtProfileName.setOnClickListener(this)
            }
    }
}