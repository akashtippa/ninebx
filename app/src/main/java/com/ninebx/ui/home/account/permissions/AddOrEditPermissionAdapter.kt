package com.ninebx.ui.home.account.permissions

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ninebx.R
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.home.account.model.AddEditPermissions
import java.util.*

internal class AddOrEditPermissionAdapter(private val myList: ArrayList<AddEditPermissions>) : RecyclerView.Adapter<AddOrEditPermissionAdapter.RecyclerItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_permissions, parent, false)
        return RecyclerItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val addEditPermissions = myList[position]

        holder.txtMenu.text = addEditPermissions.menuName
        holder.layoutMenu.setBackgroundResource(addEditPermissions.background)
        holder.imgMenu.setImageResource(addEditPermissions.menuImage)

        holder.chkAdd.isChecked = addEditPermissions.isAdd
        holder.chkEdit.isChecked = addEditPermissions.isEdit
        holder.chkView.isChecked = addEditPermissions.isView


    }

    override fun getItemCount(): Int {
        return myList.size
    }


    internal inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent), CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(button: CompoundButton?, isChecked: Boolean) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val addEditPermissions = myList[position]

                when (button!!.id) {
                    R.id.chkView -> {
                        addEditPermissions.isView = isChecked
                        if (!isChecked) {
                            addEditPermissions.isAdd = isChecked
                            addEditPermissions.isEdit = isChecked
                        }
                        setPermissions( addEditPermissions )
                    }
                    R.id.chkEdit -> {
                        addEditPermissions.isEdit = isChecked
                        if (isChecked) {
                            addEditPermissions.isAdd = isChecked
                            addEditPermissions.isView = isChecked
                        }
                        setPermissions( addEditPermissions )
                    }
                    R.id.chkAdd -> {
                        addEditPermissions.isAdd = isChecked
                        if (isChecked) {
                            addEditPermissions.isView = isChecked
                        }
                        else {
                            addEditPermissions.isEdit = isChecked
                        }
                        setPermissions( addEditPermissions )
                    }
                }

            }
        }

        private fun setPermissions(addEditPermissions: AddEditPermissions) {
            chkAdd.isChecked = addEditPermissions.isAdd
            chkEdit.isChecked = addEditPermissions.isEdit
            chkView.isChecked = addEditPermissions.isView

        }

        val layoutMenu: LinearLayout = parent.findViewById<View>(R.id.layoutMenu) as LinearLayout
        val imgMenu: ImageView = parent.findViewById<View>(R.id.imgMenu) as ImageView
        val txtMenu: TextView = parent.findViewById<View>(R.id.txtMenu) as TextView

        val chkView: CheckBox = parent.findViewById<View>(R.id.chkView) as CheckBox
        val chkAdd: CheckBox = parent.findViewById<View>(R.id.chkAdd) as CheckBox
        val chkEdit: CheckBox = parent.findViewById<View>(R.id.chkEdit) as CheckBox

        init {
            chkView.setOnCheckedChangeListener(this)
            chkAdd.setOnCheckedChangeListener(this)
            chkEdit.setOnCheckedChangeListener(this)
        }
    }

    fun add(location: Int, iName: String) {
        notifyItemInserted(location)
    }

    fun getPermissions(tempMember: Member) {
        
        var i = 0
        
        tempMember.homeView = myList[i].isView
        tempMember.homeAdd = myList[i].isAdd
        tempMember.homeEdit = myList[i++].isEdit

        tempMember.travelView = myList[i].isView
        tempMember.travelAdd = myList[i].isAdd
        tempMember.travelEdit = myList[i++].isEdit

        tempMember.contactsView = myList[i].isView
        tempMember.contactsAdd = myList[i].isAdd
        tempMember.contactsEdit = myList[i++].isEdit

        tempMember.educationlView = myList[i].isView
        tempMember.educationlAdd = myList[i].isAdd
        tempMember.educationlEdit = myList[i++].isEdit

        tempMember.personalView = myList[i].isView
        tempMember.personalAdd = myList[i].isAdd
        tempMember.personalEdit = myList[i++].isEdit

        tempMember.interestsView = myList[i].isView
        tempMember.interestsAdd = myList[i].isAdd
        tempMember.interestsEdit = myList[i++].isEdit

        tempMember.wellnessView = myList[i].isView
        tempMember.wellnessAdd = myList[i].isAdd
        tempMember.wellnessEdit = myList[i++].isEdit

        tempMember.memoriesView = myList[i].isView
        tempMember.memoriesAdd = myList[i].isAdd
        tempMember.memoriesEdit = myList[i++].isEdit

        tempMember.shoppingView = myList[i].isView
        tempMember.shoppingAdd = myList[i].isAdd
        tempMember.shoppingEdit = myList[i].isEdit
    }


}