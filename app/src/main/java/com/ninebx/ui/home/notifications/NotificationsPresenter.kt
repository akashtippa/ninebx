package com.ninebx.ui.home.notifications

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.ninebx.ui.base.realm.Notifications
import com.ninebx.ui.base.realm.decrypted.DecryptedNotifications
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import io.realm.internal.SyncObjectServerFacade.getApplicationContext
import java.util.*

/**
 * Created by Alok on 03/01/18.
 */
@SuppressLint("StaticFieldLeak")
class NotificationsPresenter(val notificationsView: NotificationsView)  {
    private val context = getApplicationContext()
    private val mDecryptNotifications = ArrayList<DecryptedNotifications>()
    private lateinit var mNotificationsRealm : Realm

    private var getNotification: RealmResults<Notifications>?=null

    init {

        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_NOTIFICATIONS, object : Realm.Callback(){
                    override fun onSuccess(realm: Realm?) {
                        mNotificationsRealm = realm!!
                        getNotification = realm.where(Notifications::class.java).sort("updatedDate", Sort.ASCENDING).findAll()
                        if(getNotification!!.size > 0){
                            getNotification!!.mapTo(mDecryptNotifications) { decryptNotifications(it) }

                            AppLogger.d("Notification", "Notification Decrypted" + mDecryptNotifications)
                        }
                        else
                            AppLogger.d("Notification", "No data" )

                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                notificationsView.onNotificationsFetched(mDecryptNotifications)
                notificationsView.onEncryptedNotifications(getNotification!!)
                notificationsView.hideProgress()
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)


    }

    fun deleteNotification(position: Int) {
        mNotificationsRealm.beginTransaction()
        getNotification!![position]!!.deleteFromRealm()
        mNotificationsRealm.commitTransaction()
    }

    fun markAsUnread(position: Int) {
        mDecryptNotifications[position].read = false
        mNotificationsRealm.beginTransaction()
        //AppLogger.d("SearchFor", "Notification : " + abs(getNotification!![position]!!.id))
        //AppLogger.d("SearchFor", "Notification : " + ((Math.ceil(getNotification!![position]!!.id/1000.0))*1000).toLong())
        //AppLogger.d("SearchFor", "Notification : " + ((1000 - (getNotification!![position]!!.id % 1000)) + getNotification!![position]!!.id))

        val id = getNotification!![position]!!.id //((1000 - (getNotification!![position]!!.id % 1000)) + getNotification!![position]!!.id)
        AppLogger.d("SearchFor", "Notification : " + id)
        val notification = mNotificationsRealm.where(Notifications::class.java)
                .equalTo("id", (id))
                .findFirst()
        getNotification!![position]!!.read = false
        notification!!.read = false
        mNotificationsRealm.copyToRealmOrUpdate(notification)
        mNotificationsRealm.commitTransaction()
    }

    fun markAsRead(position: Int) {
        mDecryptNotifications[position].read = true
        mNotificationsRealm.beginTransaction()
        //AppLogger.d("SearchFor", "Notification : " + abs(getNotification!![position]!!.id))
        //AppLogger.d("SearchFor", "Notification : " + ((Math.ceil(getNotification!![position]!!.id/1000.0))*1000).toLong())
        //AppLogger.d("SearchFor", "Notification : " + ((1000 - (getNotification!![position]!!.id % 1000)) + getNotification!![position]!!.id))

        val id = getNotification!![position]!!.id//((1000 - (getNotification!![position]!!.id % 1000)) + getNotification!![position]!!.id)
        AppLogger.d("SearchFor", "Notification : " + id)
        val notification = mNotificationsRealm.where(Notifications::class.java)
                .equalTo("id", (id))
                .findFirst()
        getNotification!![position]!!.read = true
        notification!!.read = true
        mNotificationsRealm.copyToRealmOrUpdate(notification)
        mNotificationsRealm.commitTransaction()
    }
}