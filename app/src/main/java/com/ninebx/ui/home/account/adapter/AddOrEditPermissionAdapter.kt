package com.ninebx.ui.home.account.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.ninebx.R
import com.ninebx.ui.home.account.model.AddEditPermissions
import java.util.*

internal class AddOrEditPermissionAdapter(private var myList: ArrayList<AddEditPermissions>?) : RecyclerView.Adapter<AddOrEditPermissionAdapter.RecyclerItemViewHolder>() {
    internal var mLastPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_permissions, parent, false)
        return RecyclerItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        mLastPosition = position
    }

    override fun getItemCount(): Int {
        return if (null != myList) {
            myList!!.size
        } else 0
    }


    internal inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {


        val chkView: CheckBox = parent.findViewById<View>(R.id.chkView) as CheckBox
        val chkAdd: CheckBox = parent.findViewById<View>(R.id.chkAdd) as CheckBox
        val chkEdit: CheckBox = parent.findViewById<View>(R.id.chkEdit) as CheckBox
    }

    fun add(location: Int, iName: String) {
        notifyItemInserted(location)
    }


}