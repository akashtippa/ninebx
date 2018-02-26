package com.ninebx.ui.home

import com.ninebx.ui.base.realm.decrypted.DecryptedCombine
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.utility.AppLogger
import com.ninebx.utility.getCurrentUsers
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok Omkar on 2018-02-24.
 */
class HomePresenter( val homeView : HomeView ) {
    val decryptCombine: DecryptedCombine = DecryptedCombine()
    init {
        /*var context = getApplicationContext()
        prepareRealmConnections(context, false, "Combine", object : Realm.Callback(){
            override fun onSuccess(realm: Realm?) {
                var getCombine = realm!!.where(Combine::class.java).findAll()
                if(getCombine.size > 0){
                    for (i in 0 until getCombine.size) {
                        val decryptedCombine = com.ninebx.utility.decryptCombine(getCombine[i]!!)
                        addDecryptCombine(decryptedCombine)
                    }
                    homeView.onCombineFetched(decryptCombine)
                }else{
                    AppLogger.d("Notification", "No data found")
                }
            }
        })
    }

    private fun addDecryptCombine(decryptedCombine: DecryptedCombine) {
        decryptCombine.listItems.addAll(decryptedCombine.listItems)
        decryptCombine.propertyItems.addAll(decryptedCombine.propertyItems)
        decryptCombine.vehicleItems.addAll(decryptedCombine.vehicleItems)
        decryptCombine.taxesItems.addAll(decryptedCombine.taxesItems)
        decryptCombine.insuranceItems.addAll(decryptedCombine.insuranceItems)
        decryptCombine.assetItems.addAll(decryptedCombine.assetItems)
        decryptCombine.paymentItems.addAll(decryptedCombine.paymentItems)
        decryptCombine.financialItems.addAll(decryptedCombine.financialItems)
    }*/

    }
        fun fetchCurrentUsers() {
            prepareRealmConnections(homeView.getContextForScreen(), true, "Users",
                    object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            homeView.setCurrentUsers(getCurrentUsers(realm!!))
                        }
                    })
        }
    }