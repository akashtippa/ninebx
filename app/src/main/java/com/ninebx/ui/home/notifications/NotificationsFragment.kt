package com.ninebx.ui.home.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.isVisible
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.Notifications
import com.ninebx.ui.home.BaseHomeFragment
import com.ninebx.ui.home.HomeActivity
import com.ninebx.utility.*
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_notifications.*
import java.text.SimpleDateFormat
import java.util.*
import android.content.Context.NOTIFICATION_SERVICE
import android.app.NotificationManager
import android.content.Context.ALARM_SERVICE
import android.app.AlarmManager
import com.ninebx.ui.base.realm.decrypted.*
import io.realm.Realm
import kotlin.collections.ArrayList


/**
 * Created by Alok on 03/01/18.
 */
class NotificationsFragment : BaseHomeFragment(), NotificationsView {

    private var encryptedNotifications : RealmResults<Notifications> ?= null
    private var decryptedNotifications = ArrayList<DecryptedNotifications>()
    private var mNotificationsPresenter : NotificationsPresenter ?= null

    var count = 0

    override fun showProgress(message: Int) {
        if( progressLayout != null )
            progressLayout.show()
    }

    override fun onEncryptedNotifications(notifications: RealmResults<Notifications>) {
        encryptedNotifications = notifications
    }

    override fun hideProgress() {
        if( progressLayout != null )
            progressLayout.hide()
    }

    override fun onError(error: Int) {
        hideProgress()
        context!!.showToast(error)
    }

    override fun onNotificationsFetched(notifications: ArrayList<DecryptedNotifications>) {

        hideProgress()
        if( rvNotification != null ) {
            this.decryptedNotifications = notifications
            for (notification in decryptedNotifications) {
                count += if (notification.read) 0 else 1
            }
            AppLogger.d("notificationCount", " " + count)
            mHomeView.setNotificationCount(count)
            rvNotification.layoutManager = LinearLayoutManager(context)
            val mAdapter = NotificationAdapter(decryptedNotifications)
            mAdapter.onClickListener(object : NotificationAdapter.ClickListener {

                override fun onItemClick(position: Int, v: View, id: Long, optionsLayout: View) {

                    when (v.id) {
                        R.id.tvBoxName,
                        R.id.tvMessage,
                        R.id.tvDueDate,
                        R.id.tvSubTitle -> {
                            decryptedNotifications[position].read = true
                            optionsLayout.hide()
                            mNotificationsPresenter!!.markAsRead(position)
                            mHomeView.setNotificationCount(--count)
                            mAdapter.notifyDataSetChanged()
                        }
                        R.id.ivMore -> {
                            if (optionsLayout.isVisible()) {
                                optionsLayout.hide()
                            } else optionsLayout.show()
                        }
                        R.id.ivDeleteNotification -> {
                            optionsLayout.hide()
                            decryptedNotifications.removeAt(position)
                            mNotificationsPresenter!!.deleteNotification(position)
                            mHomeView.setNotificationCount(--count)
                            mAdapter.notifyDataSetChanged()
                        }
                        R.id.ivShareNotification -> {
                            optionsLayout.hide()
                            sendEmail(decryptedNotifications[position].boxName, decryptedNotifications[position].message, decryptedNotifications[position].subTitle, decryptedNotifications[position].dueDate)
                        }
                        R.id.ivFlagNotification -> {
                            optionsLayout.hide()
                            decryptedNotifications[position].read = false
                            mHomeView.setNotificationCount(++count)
                            mNotificationsPresenter!!.markAsUnread(position)
                            mAdapter.notifyDataSetChanged()
                        }
                    }
                }

                override fun onItemLongClick(position: Int, v: View, txtMessage: TextView) {
                }
            })
            rvNotification.adapter = mAdapter
            if(decryptedNotifications.isEmpty()){
                tv_noNotifications.show()
            }
            else {
                tv_noNotifications.hide()
            }
        }
    }

    private fun sendEmail(boxName: String, message: String, subTitle: String, dueDate: String) {
        var TO = arrayOf("")
        var CC = arrayOf("")
        var emailIntent = Intent(Intent.ACTION_SEND)

        var emailBody : String = boxName + "\n" + message + "\n" + subTitle + "\t" + dueDate
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
        emailIntent.putExtra(Intent.EXTRA_CC, CC)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "NineBx Notifications & Alerts")
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody)

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."))
            AppLogger.d("SendingEmail","Finished sending email")
        } catch (ex: android.content.ActivityNotFoundException) {
            AppLogger.d("SendingEmail", "There is no email client installed.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNotificationsPresenter = NotificationsPresenter(this)
    }
}