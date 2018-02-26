package com.ninebx.ui.home.notifications

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.Notifications
import com.ninebx.ui.base.realm.decrypted.DecryptedCombine
import com.ninebx.ui.base.realm.decrypted.DecryptedNotifications
import io.realm.RealmResults

/**
 * Created by Alok on 03/01/18.
 */
interface NotificationsView : BaseView {
    fun onNotificationsFetched(notifications : ArrayList<DecryptedNotifications>)
    fun onEncryptedNotifications( notifications : RealmResults<Notifications> )
    fun onCombineFetched(decryptCombine: DecryptedCombine)
}