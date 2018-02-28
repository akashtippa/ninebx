package com.ninebx.ui.home.notifications

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
class NotificationsPresenter(val notificationsView: NotificationsView)  {
    private val context = getApplicationContext()
    private val mDecryptNotifications = ArrayList<DecryptedNotifications>()
    private lateinit var mNotificationsRealm : Realm

    private var getNotification: RealmResults<Notifications>?=null

    init {
        prepareRealmConnections(context, false, "Notifications", object : Realm.Callback(){
            override fun onSuccess(realm: Realm?) {
                mNotificationsRealm = realm!!
                getNotification = realm.where(Notifications::class.java).sort("updatedDate", Sort.ASCENDING).findAll()
                if(getNotification!!.size > 0){
                    getNotification!!.mapTo(mDecryptNotifications) { decryptNotifications(it) }
                    notificationsView.onNotificationsFetched(mDecryptNotifications)
                    notificationsView.onEncryptedNotifications(getNotification!!)
                    AppLogger.d("Notification", "Notification Decrypted" + mDecryptNotifications)
                }
                else
                    AppLogger.d("Notification", "No data" )
                    notificationsView.hideProgress()
            }
        })
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