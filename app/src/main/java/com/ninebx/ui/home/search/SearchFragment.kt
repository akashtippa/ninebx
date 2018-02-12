package com.ninebx.ui.home.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.utility.AppLogger

/**
 * Created by Alok on 03/01/18.
 */
class SearchFragment : Fragment(), SearchView {
    override fun onCombineResultFetched(combine: Combine) {
        //Show in logs
        //  AppLogger.d("Combine", "DecryptedCombine" + decryptFinancial(combine[]!!))
        AppLogger.d("CombineResult", "COmbine result fetched" + combine.financialItems[0]!!)
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

