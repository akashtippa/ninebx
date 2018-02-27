package com.ninebx.ui.home.lists.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedCombine
import com.ninebx.ui.base.realm.decrypted.DecryptedHomeList
import com.ninebx.ui.home.lists.SuperSubListFragment
import com.ninebx.ui.home.lists.model.AddedItem
import com.ninebx.utility.decryptString
import java.util.*

internal class ListsAdapter(private var myList: ArrayList<DecryptedHomeList>) : RecyclerView.Adapter<ListsAdapter.RecyclerItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return RecyclerItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val member = myList!![position]

        holder.etTitleTextView.text = member.listName

        holder.layoutAddedList.setOnClickListener {
            val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val superSubListFragment = SuperSubListFragment()
//            val bundle = Bundle()
//            bundle.putString("homeScreen", "HomeScreen")
//            superSubListFragment.arguments = bundle
            fragmentTransaction.replace(R.id.frameLayout, superSubListFragment).commit()
        }
    }

    override fun getItemCount(): Int {
        return if (null != myList) myList!!.size else 0
    }


    fun restoreAt(position: Int, iItem: DecryptedHomeList) {
        myList!!.add(position, iItem)
        notifyItemRemoved(position)
    }


    fun removeAt(position: Int) {
        myList!!.removeAt(position)
        notifyItemRemoved(position)
    }


    fun notifyData(myList: ArrayList<DecryptedHomeList>) {
        this.myList = myList
        notifyDataSetChanged()
    }

    internal inner class RecyclerItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        val etTitleTextView: TextView = parent.findViewById<View>(R.id.txtListAdded) as TextView
        val layoutAddedList: RelativeLayout = parent.findViewById<View>(R.id.layoutAddedList) as RelativeLayout

    }

    fun add(location: Int, iName: String) {
        notifyItemInserted(location)
    }
}




