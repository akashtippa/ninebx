package com.ninebx.ui.home.lists.travel

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedShoppingList
import com.ninebx.ui.base.realm.decrypted.DecryptedTravelList
import com.ninebx.ui.home.lists.SuperSubListFragment
import java.util.ArrayList

internal class TravelListsAdapter(private var myList: ArrayList<DecryptedTravelList>) : RecyclerView.Adapter<TravelListsAdapter.RecyclerItemViewHolder>() {

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
            fragmentTransaction.replace(R.id.frameLayout, SuperSubListFragment()).commit()
        }
    }

    override fun getItemCount(): Int {
        return if (null != myList) myList!!.size else 0
    }


    fun restoreAt(position: Int, iItem: DecryptedTravelList) {
        myList!!.add(position, iItem)
        notifyItemRemoved(position)
    }


    fun removeAt(position: Int) {
        myList!!.removeAt(position)
        notifyItemRemoved(position)
    }


    fun notifyData(myList: ArrayList<DecryptedTravelList>) {
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