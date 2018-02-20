package com.ninebx.ui.home.notifications

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedNotifications
import com.ninebx.ui.home.BaseHomeFragment
import kotlinx.android.synthetic.main.fragment_notifications.*

/**
 * Created by Alok on 03/01/18.
 */
class NotificationsFragment : BaseHomeFragment(), NotificationsView {
    private var decryptedNotifications = ArrayList<DecryptedNotifications>()

      override fun showProgress(message: Int) {
    }

    override fun hideProgress() {
    }

    override fun onError(error: Int) {
    }

    override fun onNotificationsFetched(notifications: ArrayList<DecryptedNotifications>) {
        this.decryptedNotifications = notifications
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NotificationsPresenter(this)

        rvNotification.layoutManager = LinearLayoutManager(context)
        rvNotification.adapter = NotificationAdapter(decryptedNotifications)
    }
}