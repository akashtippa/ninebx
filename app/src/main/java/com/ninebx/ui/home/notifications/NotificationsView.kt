package com.ninebx.ui.home.notifications

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.decrypted.DecryptedNotifications

/**
 * Created by Alok on 03/01/18.
 */
interface NotificationsView : BaseView {
    fun onNotificationsFetched(notifications : ArrayList<DecryptedNotifications>)
}