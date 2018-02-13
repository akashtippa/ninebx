package com.ninebx.utility

import android.content.Context
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.showProgressDialog
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.Users
import io.realm.*
import java.util.*

/**
 * Created by Alok on 18/01/18.
 */


fun RealmObject.insertOrUpdate(realmInstance: Realm) {
    realmInstance.executeTransaction { realm -> realm.insertOrUpdate(this) }
}

fun RealmObject.insertOrUpdateObjects(realmInstance: Realm, realmObjects: RealmList<RealmObject>) {
    realmInstance.insertOrUpdate(realmObjects)
}

fun RealmObject.retrieveObject(realmInstance: Realm) {
    realmInstance.copyFromRealm(this)
}

fun closeAllConnections() {
    for (realmConnection in connectionsMap.values) {
        realmConnection.close()
    }
}

fun closeConnection(realmConnection: Realm) {
    realmConnection.close()
}

fun getCurrentUsers(realmInstance: Realm): RealmResults<Users>? {
    return realmInstance.where(Users::class.java).findAll()
}

private val TAG = "RealmUtils"

private val connectionsMap = HashMap<String, Realm>()
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun prepareRealmConnections(context: Context?,
                            isForeground: Boolean,
                            realmEndPoint: String,
                            callback: Realm.Callback) {

    if (isForeground)
        context!!.showProgressDialog(context.getString(R.string.connecting))

    if (connectionsMap.containsKey(realmEndPoint)) {
        AppLogger.d(TAG, "Connection Found : " + realmEndPoint)
        callback.onSuccess(connectionsMap[realmEndPoint])
    } else {
        AppLogger.d(TAG, "New Connection : " + realmEndPoint)
        getRealmInstance(realmEndPoint, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {

                AppLogger.d(TAG, "Connection established : " + realmEndPoint)

                connectionsMap.put(realmEndPoint, realm!!)
                callback.onSuccess(realm)

                if (isForeground)
                    context!!.hideProgressDialog()
            }

            override fun onError(exception: Throwable?) {

                if (exception != null && exception.localizedMessage != null) {

                    AppLogger.e(TAG, "Connection error : " + realmEndPoint)

                    if (isForeground)
                        context!!.showToast(exception.localizedMessage)

                    exception.printStackTrace()
                }

                if (isForeground)
                    context!!.hideProgressDialog()

            }
        })
    }

}

private fun getRealmInstance(realmEndPoint: String, callback: Realm.Callback) {

    val user = SyncUser.currentUser()
    AppLogger.d(TAG, "getRealmInstance : " + Constants.SERVER_URL + realmEndPoint)
    val config = SyncConfiguration.Builder(user, Constants.SERVER_URL + realmEndPoint)
            .waitForInitialRemoteData()
            .build()
    Realm.getInstanceAsync(config, callback)

}


fun getRealmServerConnection(realmEndPoint: String, callback: Realm.Callback) {
    val user = SyncUser.currentUser()
    AppLogger.d(TAG, "getRealmInstance : " + Constants.SERVER_ADDRESS + realmEndPoint)
    val config = SyncConfiguration.Builder(user, Constants.SERVER_ADDRESS + realmEndPoint)
            .waitForInitialRemoteData()
            .build()
    Realm.getInstanceAsync(config, object : Realm.Callback() {
        override fun onSuccess(realm: Realm?) {
            AppLogger.d(TAG, "Connection established for Path : " + realm!!.path)
            AppLogger.d(TAG, "Connection established : " + realmEndPoint)
            connectionsMap.put(realmEndPoint, realm)
            callback.onSuccess(realm)
        }

        override fun onError(exception: Throwable?) {
            super.onError(exception)
            if (exception != null && exception.localizedMessage != null) {
                AppLogger.e(TAG, "Connection error : " + realmEndPoint)
                exception.printStackTrace()
            }
            callback.onError(exception)
        }
    })
}

fun getUniqueId(): Int {
    return UUID.randomUUID().hashCode()
}

fun generateAttachmentFileName(): String {
    return UUID.randomUUID().toString()
}

fun generateRandomOTP(): String {
    var otp = ""
    val random = Random()
    otp += random.nextInt(10)
    otp += random.nextInt(10)
    otp += random.nextInt(10)
    otp += random.nextInt(10)
    otp += random.nextInt(10)
    otp += random.nextInt(10)
    AppLogger.d("EmailOTP", otp)
    return otp
}


