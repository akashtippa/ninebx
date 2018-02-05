package com.ninebx.utility

import android.content.Context
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.showProgressDialog
import com.ninebx.ui.base.kotlin.showToast
import io.realm.*

/**
 * Created by Alok on 18/01/18.
 */


fun RealmObject.insertOrUpdate( realmInstance : Realm ) {
    realmInstance.executeTransaction { realm -> realm.insertOrUpdate(this) }
}

fun Realm.insertOrUpdateObjects( realmInstance: Realm, realmObjects : RealmList<RealmObject> ) {
    realmInstance.insertOrUpdate( realmObjects )
}

fun RealmObject.retrieveObject( realmInstance: Realm ) {
    realmInstance.copyFromRealm( this )
}

fun closeAllConnections() {
    for( realmConnection in connectionsMap.values ) {
        realmConnection.close()
    }
}

fun closeConnection( realmConnection : Realm ) {
    realmConnection.close()
}

private val TAG = "RealmUtils"

private val connectionsMap = HashMap<String, Realm>()
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun prepareRealmConnections( context: Context?,
                             isForeground : Boolean,
                             realmEndPoint: String,
                             callback: Realm.Callback ) {

    if( isForeground )
        context!!.showProgressDialog( context.getString(R.string.connecting) )

    if( connectionsMap.containsKey(realmEndPoint) ) {
        AppLogger.d(TAG, "Connection Found : " + realmEndPoint )
        callback.onSuccess(connectionsMap[realmEndPoint])
    }
    else {
        AppLogger.d(TAG, "New Connection : " + realmEndPoint )
        getRealmInstance( realmEndPoint, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {

                AppLogger.d(TAG, "Connection established : " + realmEndPoint )

                connectionsMap.put( realmEndPoint, realm!! )
                callback.onSuccess( realm )

                if( isForeground )
                    context!!.hideProgressDialog()
            }

            override fun onError(exception: Throwable?) {

                if( exception != null && exception.localizedMessage != null ) {

                    AppLogger.e(TAG, "Connection error : " + realmEndPoint )

                    if( isForeground )
                        context!!.showToast(exception.localizedMessage)

                    exception.printStackTrace()
                }

                if( isForeground )
                    context!!.hideProgressDialog()

            }
        })
    }

}

private fun getRealmInstance( realmEndPoint : String, callback : Realm.Callback ) {

    val user = SyncUser.currentUser()
    val config = SyncConfiguration.Builder(user, Constants.SERVER_URL + realmEndPoint )
            .waitForInitialRemoteData()
            .build()
    Realm.getInstanceAsync( config, callback )

}

