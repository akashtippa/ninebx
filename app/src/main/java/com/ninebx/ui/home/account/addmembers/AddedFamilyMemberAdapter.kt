package com.ninebx.ui.home.account.addmembers

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.base.realm.decrypted.DecryptedMember
import com.ninebx.ui.home.account.interfaces.IMemberAdded
import com.ninebx.utility.AppLogger
import com.ninebx.utility.decryptString
import java.util.*

class AddedFamilyMemberAdapter(private var myList: ArrayList<DecryptedMember>, private val iMemberAdded: IMemberAdded) : RecyclerView.Adapter<AddedFamilyMemberAdapter.RecyclerItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_add_users, parent, false)
        return RecyclerItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val member = myList[position]
        holder.txtProfileName.text = member.firstName + " " + member.lastName
        holder.txtAccountHolder.text = member.relationship
        holder.txtRole.text = member.role
        holder.txtProfileEmail.text = member.email

    }

    override fun getItemCount(): Int {
        return myList.size
    }

    inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent), View.OnClickListener {
        override fun onClick(view: View?) {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                when( view!!.id ) {
                    R.id.imgEdit -> {
                        iMemberAdded.onMemberEdit(myList[position])
                    }
                    R.id.imgDelete -> {
                        val member = myList[position]
                        iMemberAdded.onMemberDelete(member)
                    }
                }
            }

        }

        val imgProfilePic: ImageView = parent.findViewById<View>(R.id.imgProfilePic) as ImageView
        val txtProfileName: TextView = parent.findViewById<View>(R.id.txtProfileName) as TextView
        val txtAccountHolder: TextView = parent.findViewById<View>(R.id.txtAccountHolder) as TextView
        val txtRole: TextView = parent.findViewById<View>(R.id.txtRole) as TextView
        val txtProfileEmail: TextView = parent.findViewById<View>(R.id.txtProfileEmail) as TextView
        val layAddedMembers: RelativeLayout = parent.findViewById<View>(R.id.layAddedMembers) as RelativeLayout

        val imgEdit: ImageView = parent.findViewById<View>(R.id.imgEdit) as ImageView
        val imgDelete: ImageView = parent.findViewById<View>(R.id.imgDelete) as ImageView

        init {
            imgEdit.setOnClickListener(this)
            imgDelete.setOnClickListener(this)
        }
    }

    fun add(location: Int, iName: String) {
        notifyItemInserted(location)
    }

    fun deleteItem(member: DecryptedMember?) {
        myList.remove(member)
        notifyDataSetChanged()
    }


}