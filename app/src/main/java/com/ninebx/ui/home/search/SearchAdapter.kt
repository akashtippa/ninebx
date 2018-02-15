package com.ninebx.ui.home.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedCombine
import com.ninebx.ui.base.realm.decrypted.DecryptedFinancial

/**
 * Created by smrit on 14-02-2018.
 */
class SearchAdapter(private val decryptedCombine: DecryptedCombine) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.row_search, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

           if (decryptedCombine.financialItems.size > decryptedCombine.paymentItems.size || decryptedCombine.financialItems.size > decryptedCombine.propertyItems.size ||
                   decryptedCombine.financialItems.size > decryptedCombine.vehicleItems.size || decryptedCombine.financialItems.size > decryptedCombine.assetItems.size ||
                   decryptedCombine.financialItems.size > decryptedCombine.insuranceItems.size || decryptedCombine.financialItems.size > decryptedCombine.taxesItems.size ||
                   decryptedCombine.financialItems.size > decryptedCombine.listItems.size)
            return decryptedCombine.financialItems.size

        else if (decryptedCombine.paymentItems.size > decryptedCombine.propertyItems.size || decryptedCombine.paymentItems.size > decryptedCombine.vehicleItems.size || decryptedCombine.paymentItems.size > decryptedCombine.assetItems.size ||
                decryptedCombine.paymentItems.size > decryptedCombine.insuranceItems.size || decryptedCombine.paymentItems.size > decryptedCombine.taxesItems.size ||
                decryptedCombine.paymentItems.size > decryptedCombine.listItems.size)
            return decryptedCombine.paymentItems.size


        else if (decryptedCombine.propertyItems.size > decryptedCombine.vehicleItems.size || decryptedCombine.propertyItems.size > decryptedCombine.assetItems.size ||
                decryptedCombine.propertyItems.size > decryptedCombine.insuranceItems.size || decryptedCombine.propertyItems.size > decryptedCombine.taxesItems.size ||
                decryptedCombine.propertyItems.size > decryptedCombine.listItems.size)
            return decryptedCombine.paymentItems.size

        else if (decryptedCombine.vehicleItems.size > decryptedCombine.assetItems.size || decryptedCombine.vehicleItems.size > decryptedCombine.insuranceItems.size || decryptedCombine.vehicleItems.size > decryptedCombine.taxesItems.size ||
                decryptedCombine.vehicleItems.size > decryptedCombine.listItems.size)
            return decryptedCombine.vehicleItems.size

        else if (decryptedCombine.insuranceItems.size > decryptedCombine.taxesItems.size || decryptedCombine.insuranceItems.size > decryptedCombine.listItems.size)
               return decryptedCombine.insuranceItems.size

           else if ( decryptedCombine.taxesItems.size > decryptedCombine.listItems.size)
               return decryptedCombine.taxesItems.size

        else
               return decryptedCombine.listItems.size
            /*return decryptedFinance.size*/

    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.bindItems(decryptedCombine)

       /* for(i in 0..decryptedCombine.financialItems.size)
        {
            holder.bindItems(decryptedCombine)
        }*/

    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = view.findViewById<View>(R.id.txtListSearch) as TextView

        fun bindItems(decryptedCombine: DecryptedCombine) {

           /* textView.setText(decryptedFinance.accountName)*/
            for (i in 0 until decryptedCombine.paymentItems.size) {
                var result = decryptedCombine.paymentItems[i].toString()
                textView.setText(result)
            }

            for (i in 0 until decryptedCombine.propertyItems.size) {
                var result = decryptedCombine.propertyItems[i].toString()
                textView.setText(result)
            }

            for (i in 0 until decryptedCombine.vehicleItems.size) {
                var result = decryptedCombine.vehicleItems[i].toString()
                textView.setText(result)
            }

            for (i in 0 until decryptedCombine.assetItems.size) {
                var result = decryptedCombine.assetItems[i].toString()
                textView.setText(result)
            }

            for (i in 0 until decryptedCombine.insuranceItems.size) {
                var result = decryptedCombine.insuranceItems[i].toString()
                textView.setText(result)
            }

            for (i in 0 until decryptedCombine.financialItems.size) {
                var result = decryptedCombine.financialItems[i].toString()
                textView.setText(result)
            }

            for (i in 0 until decryptedCombine.taxesItems.size) {
                var result = decryptedCombine.taxesItems[i].toString()
                textView.setText(result)
            }

            for (i in 0 until decryptedCombine.listItems.size) {
                var result = decryptedCombine.listItems[i].toString()
                textView.setText(result)
            }
        }
    }
}