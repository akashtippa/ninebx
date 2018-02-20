package com.ninebx.ui.home.notifications

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.decrypted.DecryptedNotifications
import com.ninebx.ui.home.BaseHomeFragment
import kotlinx.android.synthetic.main.fragment_notifications.*

/**
 * Created by Alok on 03/01/18.
 */
class NotificationsFragment : BaseHomeFragment(), NotificationsView {

    private var decryptedNotifications = ArrayList<DecryptedNotifications>()

    override fun showProgress(message: Int) {
        progressLayout.show()
    }

    override fun hideProgress() {
        progressLayout.hide()
    }

    override fun onError(error: Int) {
        hideProgress()
        context!!.showToast(error)
    }

    override fun onNotificationsFetched(notifications: ArrayList<DecryptedNotifications>) {
        hideProgress()
        this.decryptedNotifications = notifications
        rvNotification.layoutManager = LinearLayoutManager(context)
        rvNotification.adapter = NotificationAdapter(decryptedNotifications)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NotificationsPresenter(this)
    }
}