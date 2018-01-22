package com.ninebx.ui.home.account.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.account.AddFamilyMemberOrUsersFragment
import com.ninebx.ui.home.account.PermissionFragment
import com.ninebx.ui.home.account.model.AddedFamilyMembers
import java.util.*

internal class AddedFamilyMemberAdapter(private var myList: ArrayList<AddedFamilyMembers>?) : RecyclerView.Adapter<AddedFamilyMemberAdapter.RecyclerItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_add_users, parent, false)
        return RecyclerItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.txtProfileName.text = myList!![position].profileName
        holder.txtAccountHolder.text = myList!![position].users
        holder.txtRole.text = myList!![position].role
        holder.txtProfileEmail.text = myList!![position].email

        holder.imgEdit.setOnClickListener {
            val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.frameLayout, AddFamilyMemberOrUsersFragment()).commit()
        }

        holder.imgDelete.setOnClickListener {
            myList!!.removeAt(holder.position)
        }

    }

    override fun getItemCount(): Int {
        return if (null != myList) myList!!.size else 0
    }


    fun restoreAt(position: Int, iItem: AddedFamilyMembers) {
        myList!!.add(position, iItem)
        notifyItemRemoved(position)
    }


    fun removeAt(position: Int) {
        myList!!.removeAt(position)
        notifyItemRemoved(position)
    }


    fun notifyData(myList: ArrayList<AddedFamilyMembers>) {
        this.myList = myList
        notifyDataSetChanged()
    }

    internal inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

        val imgProfilePic: ImageView = parent.findViewById<View>(R.id.imgProfilePic) as ImageView
        val txtProfileName: TextView = parent.findViewById<View>(R.id.txtProfileName) as TextView
        val txtAccountHolder: TextView = parent.findViewById<View>(R.id.txtAccountHolder) as TextView
        val txtRole: TextView = parent.findViewById<View>(R.id.txtRole) as TextView
        val txtProfileEmail: TextView = parent.findViewById<View>(R.id.txtProfileEmail) as TextView
        val layAddedMembers: RelativeLayout = parent.findViewById<View>(R.id.layAddedMembers) as RelativeLayout

        val imgEdit: ImageView = parent.findViewById<View>(R.id.imgEdit) as ImageView
        val imgDelete: ImageView = parent.findViewById<View>(R.id.imgDelete) as ImageView
    }

    fun add(location: Int, iName: String) {
        notifyItemInserted(location)
    }


}