package com.ninebx.ui.home.search

import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.utility.AppLogger
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok on 03/01/18.
 */
class SearchPresenter( private val searchView: SearchView ) {

    init {
        val context = getApplicationContext()

        prepareRealmConnections( context, false, "Combine", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineResult = realm!!.where(Combine::class.java!!).findAll()
                AppLogger.d("Combine", "Combined Results : " + combineResult)
                if( combineResult.size > 0 ) {
                    searchView.onCombineFetchedFinancial(combineResult[0]!!)
                    searchView.onCombineFetchedPayment(combineResult[1]!!)
                }
            }
        })

        prepareRealmConnections( context, false, "CombineTravel", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineTravel = realm!!.where(Combine::class.java!!).findAll()
                AppLogger.d("Combine", "CombinedTravel : " + combineTravel)
            }
        })

        prepareRealmConnections( context, false, "CombineMemories", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineMemories = realm!!.where(Combine::class.java!!).findAll()
                AppLogger.d("Combine", "CombinedMemories : " + combineMemories)
            }
        })

        prepareRealmConnections( context, false, "CombineShopping", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineShopping = realm!!.where(Combine::class.java!!).findAll()
                AppLogger.d("Combine", "CombinedMemories : " + combineShopping)
            }
        })

        prepareRealmConnections( context, false, "CombineContacts", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineContacts = realm!!.where(Combine::class.java!!).findAll()
                AppLogger.d("Combine", "CombinedContacts : " + combineContacts)
            }
        })

        prepareRealmConnections( context, false, "CombineEducation", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineEducation = realm!!.where(Combine::class.java!!).findAll()
                AppLogger.d("Combine", "CombinedEducation : " + combineEducation)
            }
        })

        prepareRealmConnections( context, false, "CombinePersonal", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combinePersonal = realm!!.where(Combine::class.java!!).findAll()
                AppLogger.d("Combine", "CombinedPersonal : " + combinePersonal)
            }
        })

        prepareRealmConnections( context, false, "CombineInterests", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineInterests = realm!!.where(Combine::class.java!!).findAll()
                AppLogger.d("Combine", "CombinedInterests : " + combineInterests)
            }
        })

        prepareRealmConnections( context, false, "CombineWellness", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineWellness = realm!!.where(Combine::class.java!!).findAll()
                AppLogger.d("Combine", "CombinedWellness : " + combineWellness)
            }
        })

        prepareRealmConnections( context, false, "CombineEvents", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineEvents = realm!!.where(Combine::class.java!!).findAll()
                AppLogger.d("Combine", "CombinedEvents : " + combineEvents)
            }
        })
    }
}