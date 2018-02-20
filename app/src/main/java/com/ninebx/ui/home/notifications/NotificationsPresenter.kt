package com.ninebx.ui.home.notifications

import com.ninebx.ui.base.realm.Notifications
import com.ninebx.ui.base.realm.decrypted.DecryptedNotifications
import com.ninebx.utility.AppLogger
import com.ninebx.utility.decryptNotifications
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok on 03/01/18.
 */
class NotificationsPresenter(val notificationsView: NotificationsView)  {
    private val context = getApplicationContext()
    private val mDecryptNotifications = ArrayList<DecryptedNotifications>()

    init {
        prepareRealmConnections(context, false, "Notifications", object : Realm.Callback(){
            override fun onSuccess(realm: Realm?) {
                val getNotification = realm!!.where(Notifications::class.java).findAll()
                if(getNotification.size > 0){
                    for(i in 0 until getNotification.size){
                       mDecryptNotifications.add(decryptNotifications(getNotification[i]!!))
                        notificationsView.onNotificationsFetched(mDecryptNotifications)
                    }
                    AppLogger.d("Notification", "Notification Decrypted" )
                }
                else
                    AppLogger.d("Notification", "No data" )
            }
        })
    }
}