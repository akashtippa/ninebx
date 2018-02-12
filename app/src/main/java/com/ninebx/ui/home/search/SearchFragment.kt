package com.ninebx.ui.home.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.ui.base.realm.home.homeBanking.Combine.*
import com.ninebx.ui.base.realm.home.homeBanking.Financial
import com.ninebx.utility.AppLogger
import com.ninebx.utility.decryptFinancial

/**
 * Created by Alok on 03/01/18.
 */
class SearchFragment : Fragment(), SearchView {

    override fun onCombineFetched(combine: Combine) {
        //Show in logs

         if(combine.financialItems.size > 0)
         AppLogger.d("Combine", "Combine result fetched for Financial" + decryptFinancial(combine.financialItems[0]!!))
         AppLogger.d("Combine", "Encrypted result fetched for Financial" + combine.financialItems[0]!!)

         if(combine.paymentItems.size > 0)
         AppLogger.d("CombinePayment", "Encrypted result fetched for Payment" + combine.paymentItems[0]!!)

         if(combine.propertyItems.size > 0)
         AppLogger.d("CombineProperty", "Encrypted result fetched for Property" + combine.propertyItems[0])

         if(combine.vehicleItems.size > 0)
             AppLogger.d("CombineProperty", "Encrypted result fetched for Vehicle" + combine.vehicleItems[0])

        if(combine.assetItems.size > 0)
            AppLogger.d("CombineProperty", "Encrypted result fetched for Asset" + combine.assetItems[0])

        if(combine.insuranceItems.size > 0)
            AppLogger.d("CombineProperty", "Encrypted result fetched for Insurance" + combine.insuranceItems[0])

        if(combine.taxesItems.size > 0)
            AppLogger.d("CombineProperty", "Encrypted result fetched for Tax" + combine.taxesItems[0])

        if(combine.listItems.size > 0)
            AppLogger.d("CombineProperty", "Encrypted result fetched for HomeList" + combine.listItems[0])
    }

    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {

    }

    override fun onError(error: Int) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SearchPresenter(this)
    }
}

