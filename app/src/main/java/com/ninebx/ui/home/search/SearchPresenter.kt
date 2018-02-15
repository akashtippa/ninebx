package com.ninebx.ui.home.search

import android.content.Intent
import com.ninebx.ui.base.realm.decrypted.DecryptedCombine
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.home.education.CombineEducation
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.ui.base.realm.home.interests.CombineInterests
import com.ninebx.ui.base.realm.home.memories.CombineMemories
import com.ninebx.ui.base.realm.home.personal.CombinePersonal
import com.ninebx.ui.base.realm.home.shopping.CombineShopping
import com.ninebx.ui.base.realm.home.travel.CombineTravel
import com.ninebx.ui.base.realm.home.wellness.CombineWellness
import com.ninebx.utility.AppLogger
import com.ninebx.utility.decryptCombine
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok on 03/01/18.
 */
class SearchPresenter( private val searchView: SearchView ) {

    init
   {
        val context = getApplicationContext()

        prepareRealmConnections(context, false, "Combine", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineResult = realm!!.where(Combine::class.java).findAll()
                AppLogger.d("Combine", "Combined Results : " + combineResult)
                if( combineResult.size > 0 ) {
                    for(i in 0 until combineResult.size ){
                        val decryptedCombine = decryptCombine(combineResult[i]!!)
                        AppLogger.d("COmbineDecrypted", "Decrypted combine financial" + decryptedCombine)
                        searchView.onCombineFetched(decryptedCombine)
                    }
                }
            }
        })

        prepareRealmConnections(context, false, "CombineTravel", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineTravel = realm!!.where(CombineTravel::class.java).findAll()
                AppLogger.d("Combine", "CombinedTravel : " + combineTravel)
            }
        })

        prepareRealmConnections(context, false, "CombineMemories", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineMemories = realm!!.where(CombineMemories::class.java).findAll()
                AppLogger.d("Combine", "CombinedMemories : " + combineMemories)
            }
        })

        prepareRealmConnections(context, false, "CombineShopping", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineShopping = realm!!.where(CombineShopping::class.java).findAll()
                AppLogger.d("Combine", "CombinedShopping : " + combineShopping)
            }
        })

        prepareRealmConnections(context, false, "CombineContacts", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineContacts = realm!!.where(CombineContacts::class.java).findAll()
                AppLogger.d("Combine", "CombinedContacts : " + combineContacts)
            }
        })

        prepareRealmConnections(context, false, "CombineEducation", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineEducation = realm!!.where(CombineEducation::class.java).findAll()
                AppLogger.d("Combine", "CombinedEducation : " + combineEducation)
            }
        })

        prepareRealmConnections(context, false, "CombinePersonal", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combinePersonal = realm!!.where(CombinePersonal::class.java).findAll()
                AppLogger.d("Combine", "CombinedPersonal : " + combinePersonal)
            }
        })

        prepareRealmConnections(context, false, "CombineInterests", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineInterests = realm!!.where(CombineInterests::class.java).findAll()
                AppLogger.d("Combine", "CombinedInterests : " + combineInterests)
            }
        })

        prepareRealmConnections(context, false, "CombineWellness", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineWellness = realm!!.where(CombineWellness::class.java).findAll()
                AppLogger.d("Combine", "CombinedWellness : " + combineWellness)
            }
        })

        prepareRealmConnections(context, false, "CombineEvents", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineEvents = realm!!.where(Combine::class.java).findAll()
                AppLogger.d("Combine", "CombinedEvents : " + combineEvents)
            }
        })
    }
}
