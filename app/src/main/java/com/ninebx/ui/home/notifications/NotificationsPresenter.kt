package com.ninebx.ui.home.notifications

import com.ninebx.ui.base.realm.Notifications
import com.ninebx.ui.base.realm.decrypted.DecryptedNotifications
import com.ninebx.utility.AppLogger
import com.ninebx.utility.decryptNotifications
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import io.realm.RealmResults
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

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
                getNotification = realm!!.where(Notifications::class.java).findAll()
                if(getNotification!!.size > 0){
                    getNotification!!.mapTo(mDecryptNotifications) { decryptNotifications(it) }
                    notificationsView.onNotificationsFetched(mDecryptNotifications)
                    notificationsView.onEncryptedNotifications(getNotification!!)
                    AppLogger.d("Notification", "Notification Decrypted" )
                }
                else
                    AppLogger.d("Notification", "No data" )
            }
        })
    }
    fun deleteNotification(position: Int) {
        mNotificationsRealm.beginTransaction()
        getNotification!![position]!!.deleteFromRealm()
        mNotificationsRealm.commitTransaction()
    }
}