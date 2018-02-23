package com.ninebx.ui.home.notifications

import android.app.Dialog
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.Notifications
import com.ninebx.ui.base.realm.decrypted.DecryptedNotifications
import com.ninebx.ui.home.BaseHomeFragment
import com.ninebx.utility.AppLogger
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_notifications.*

/**
 * Created by Alok on 03/01/18.
 */
class NotificationsFragment : BaseHomeFragment(), NotificationsView {

    var encryptedNotifications : RealmResults<Notifications> ?= null
    private var decryptedNotifications = ArrayList<DecryptedNotifications>()
    lateinit var mNotificationsPresenter : NotificationsPresenter

    override fun showProgress(message: Int) {
        progressLayout.show()
    }

    override fun onEncryptedNotifications(notifications: RealmResults<Notifications>) {
        encryptedNotifications = notifications
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
        rvNotification.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        val mAdapter = NotificationAdapter(decryptedNotifications)
        rvNotification.adapter = mAdapter

        mAdapter.onClickListener(object : NotificationAdapter.ClickListener{
            override fun onItemClick(position: Int, v: View, id: Long) {
            }
            override fun onItemLongClick(position: Int, v: View, txtMessage: TextView) {
                var dialog =  Dialog(context)
                dialog.setContentView(R.layout.layout_dialog)
                dialog.window.setGravity(Gravity.CENTER)
                if(! dialog.isShowing) {
                    dialog.show()
                } else{
                    dialog.dismiss()
                }

                val ivDelete : ImageView = dialog.findViewById(R.id.ivDeleteNotification)
                ivDelete.setOnClickListener(View.OnClickListener {
                    AppLogger.d("NotificationFragment", "Delete Button clicked")
                    mNotificationsPresenter.deleteNotification(position)
                    mAdapter.delete(position)
                    dialog.dismiss()
                })
                val ivShare : ImageView = dialog.findViewById(R.id.ivShareNotification)
                ivShare.setOnClickListener(View.OnClickListener {
                    AppLogger.d("NotificationFragment", "Share Button clicked")
                    sendEmail(decryptedNotifications[position].boxName, decryptedNotifications[position].message, decryptedNotifications[position].subTitle, decryptedNotifications[position].dueDate)
                })
                val ivFlag : ImageView = dialog.findViewById(R.id.ivFlagNotification)
                ivFlag.setOnClickListener(View.OnClickListener {
                    AppLogger.d("NotificationFragment", "Flag button clicked")

                    mNotificationsPresenter.markAsUnread( position )
                    mAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                })
            }
        })
    }

    private fun sendEmail(boxName: String, message: String, subTitle: String, dueDate: String) {
        val TO = arrayOf("")
        val CC = arrayOf("")
        val emailIntent = Intent(Intent.ACTION_SEND)

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