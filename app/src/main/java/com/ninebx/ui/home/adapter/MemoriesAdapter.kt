package com.ninebx.ui.home.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ninebx.R
import com.ninebx.R.id.imgProfilePic
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.home.account.interfaces.IMemoryAdded
import com.ninebx.utility.AWSFileTransferHelper
import com.ninebx.utility.AppLogger
import com.ninebx.utility.decryptString
import java.io.File
import java.util.*

internal class MemoriesAdapter(private var myList: ArrayList<MemoryTimeline>?, private val iMemoryAdded: IMemoryAdded) : RecyclerView.Adapter<MemoriesAdapter.RecyclerItemViewHolder>(), AWSFileTransferHelper.FileOperationsCompletionListener {
    override fun onSuccess(outputFile: File?) {
        if (outputFile != null && imgProfilePic != null)
            //AppLogger.e("Image ", " is " + outputFile)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_memory_timeline, parent, false)
        return RecyclerItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val member = myList!![position]
        //AppLogger.d("Decrypt", "Decrypting : " + member.toString())
        holder.txtMemoryTitle.text = member.title.decryptString()
        holder.txtMemoryDate.text = member.date.decryptString()

        holder.layoutMemoryHolder.setOnClickListener {
            iMemoryAdded.onMemoryEdit(member)
        }

        holder.imgDelete.setOnClickListener {
            iMemoryAdded.onMemoryDeleted(member)
            myList!!.remove(member)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return if (null != myList) myList!!.size else 0
    }


    internal inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

        val imgMemoryImage: ImageView = parent.findViewById<View>(R.id.imgMemoryImage) as ImageView
        val imgEdit: ImageView = parent.findViewById<View>(R.id.imgEdit) as ImageView
        val imgDelete: ImageView = parent.findViewById<View>(R.id.imgDelete) as ImageView
        val txtMemoryTitle: TextView = parent.findViewById<View>(R.id.txtMemoryTitle) as TextView
        val txtMemoryDate: TextView = parent.findViewById<View>(R.id.txtMemoryDate) as TextView
        val layoutMemoryHolder: LinearLayout = parent.findViewById<View>(R.id.layoutMemoryHolder) as LinearLayout

    }

    fun add(location: Int, iName: String) {
        notifyItemInserted(location)
    }


}