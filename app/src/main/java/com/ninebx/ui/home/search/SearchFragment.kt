package com.ninebx.ui.home.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.ui.home.HomeActivity
import com.ninebx.utility.AppLogger
import com.ninebx.utility.prepareRealmConnections
import com.ninebx.utility.retrieveObject
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

/**
 * Created by Alok on 03/01/18.
 */
class SearchFragment : Fragment(), SearchView {

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

        prepareRealmConnections( context, true, "Combine", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                realm!!.beginTransaction()
                val combineResult = realm.where(Combine::class.java!!).findAll()
                AppLogger.d("Combine", "Combined REsults : " + combineResult)
            }
        })
    }
    }

