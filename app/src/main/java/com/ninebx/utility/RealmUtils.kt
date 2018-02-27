package com.ninebx.utility

import android.content.Context
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.showProgressDialog
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.TestSearch
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.base.realm.lists.HomeList
import io.realm.*
import java.util.*
import kotlin.collections.ArrayList

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

fun getCurrentContactList(realmInstance: Realm): RealmResults<Contacts>? {
    return realmInstance.where(Contacts::class.java).findAll()
}

fun getAllMemoryTimeLine(realmInstance: Realm): RealmResults<MemoryTimeline>? {
    return realmInstance.where(MemoryTimeline::class.java).findAll()
}

private val TAG = "RealmUtils"

private val connectionsMap = HashMap<String, Realm>()
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun prepareRealmConnections(context: Context?,
                            isForeground: Boolean,
                            realmEndPoint: String,
                            callback: Realm.Callback) {

    if ( isForeground && context != null )
        context.showProgressDialog(context.getString(R.string.connecting))

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

fun getUniqueId(): Long {
    return UUID.randomUUID().hashCode().toLong()
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

private fun pojo2Map(obj: Any): Map<String, Any> {
    val hashMap = HashMap<String, Any>()
    try {
        val c = obj.javaClass
        val m = c.methods
        for (i in m.indices) {
            if (m[i].name.indexOf("get") == 0) {
                val name = m[i].name.toLowerCase().substring(3, 4) + m[i].name.substring(4)
                hashMap.put(name, m[i].invoke(obj, arrayOfNulls<Any>(0)))
            }
        }
    } catch (e: Throwable) {
        //log error
    }

    return hashMap
}

fun performSearchForString(classObject: Any, searchText: String): String {
    val objectHashMap = pojo2Map(classObject)
    var isSearchFound = false
    var searchString = ""
    if (objectHashMap.isNotEmpty()) {
        for (`object` in objectHashMap.values) {
            if (`object` is String && `object`.toLowerCase().contains(searchText.toLowerCase())) {
                searchString = `object`
                isSearchFound = true
                break
            }
        }
    }
    return searchString
}

class SearchResult(
        var searchFieldName : String = "",
        var isSearchFound : Boolean = false
)

fun performSearchForResult(classObject: Any, searchText: String): SearchResult {

    val objectHashMap = pojo2Map(classObject)
    var isSearchFound = false
    var searchString = ""
    if (objectHashMap.isNotEmpty()) {
        for (`object` in objectHashMap.values) {
            if (`object` is String && `object`.toLowerCase().contains(searchText.toLowerCase())) {
                isSearchFound = true
                searchString = `object`.toString()
                break
            }
        }
    }
    return SearchResult(searchString, isSearchFound)
}

fun performSearch(classObject: Any, searchText: String): Boolean {

    val objectHashMap = pojo2Map(classObject)
    var isSearchFound = false
    if (objectHashMap.isNotEmpty()) {
        for (`object` in objectHashMap.values) {
            if (`object` is String && `object`.toLowerCase().contains(searchText.toLowerCase())) {
                isSearchFound = true
                break
            }
        }
    }
    return isSearchFound
}

/*


@SuppressLint("StaticFieldLeak")
class SyncTheDb : AsyncTask<String, String, String>() {
    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg p0: String?): String {
        var Result: String = "";
        try {
            syncNow()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Result
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }
}


var strUsername: String = "aman.shekhar@cognitiveclouds.com"
var strPassword: String = "[219, 80, 120, 19, 74, 36, 40, 74, 173, 169, 201, 144, 10, 213, 102, 44, 154, 239, 237, 49, 132, 210, 196, 168, 186, 136, 44, 34, 0, 30, 35, 44]"

var myCredentials: SyncCredentials? = null
var user: SyncUser? = null
var config: SyncConfiguration? = null
lateinit var realm: Realm


private fun syncNow(realmEndPoint: String) {
    myCredentials = SyncCredentials.usernamePassword(strUsername, strPassword, false)
    user = SyncUser.login(myCredentials, Constants.SERVER_IP)
    config = SyncConfiguration.Builder(user, Constants.SERVER_URL + realmEndPoint)
            .waitForInitialRemoteData()
            .build()

    realm = Realm.getInstance(config)
}

private fun sendDataToServer() {
    realm = Realm.getInstance(config)

    realm.executeTransactionAsync({ bgRealm ->
        val user = bgRealm.createObject(TestDemo::class.java)

    }, {
        // Transaction was a success.
    }, {
        // Transaction was a failure.
    })

}
*/

