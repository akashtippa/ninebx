package com.ninebx.ui.home.search

import android.util.Log
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.utility.AppLogger
import com.ninebx.utility.decryptAESKEY
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok on 03/01/18.
 */
class SearchPresenter( private val searchView: SearchView ) {

    init {
        val context = getApplicationContext()
        /*prepareRealmConnections( context, true, "Combine", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineResult = realm!!.where(Combine::class.java!!).findAll()
                AppLogger.d("Combine", "Combined REsults : " + combineResult)

                for ( index in 0 until  combineResult.size){
                    var res = combineResult[index].toString().toByteArray(Charsets.UTF_8)
                    var decrypt = decryptAESKEY( res, "brbEsfBnWDn45QJx" )
                    Log.e("Search", "Search Decrypted" + decrypt)
                }
            }
        })*/
    }
}