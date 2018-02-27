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
import kotlin.collections.ArrayList


/**
 * Created by Alok on 03/01/18.
 */
class NotificationsFragment : BaseHomeFragment(), NotificationsView {

    private var encryptedNotifications : RealmResults<Notifications> ?= null
    private var decryptedNotifications = ArrayList<DecryptedNotifications>()
    private var mNotificationsPresenter : NotificationsPresenter ?= null

    var count = 0

    private var mDecryptedCombine : DecryptedCombine ?= null

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
            for( notification in decryptedNotifications ) {
                count += if ( notification.read ) 0 else 1
            }
            AppLogger.d("notificationCount", " " + count)
            mHomeView.setNotificationCount(count)
            rvNotification.layoutManager = LinearLayoutManager(context)
            val mAdapter = NotificationAdapter(decryptedNotifications)
            mAdapter.onClickListener(object : NotificationAdapter.ClickListener{

                override fun onItemClick(position: Int, v: View, id: Long, optionsLayout : View) {

                    when( v.id ) {
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
       /* homeNotification()
        travelNotification()
        contactsNotification()
        educationWorkNotification()
        personalNotification()
        wellnessNotification()*/
    }

   /* private fun homeNotification() {
        paymentNotification()
        propertyNotification()
        vehicleNotification()
        insuranceNotification()
    }

    private fun travelNotification() {
        travelDocuments()
        travelDatesAndPlans()
    }

    private fun contactsNotification() {
        sharedCOntactsNotification()
    }

    private fun educationWorkNotification() {
        gradudationAnniversaryNotification()
        workAnniversaryNotification()
    }

    private fun personalNotification() {
        dlExpiryNotification()
        birthdayNotification()
        anniversaryNotification()
        governMentIDNotification()
    }
    private fun wellnessNotification() {
        personalHealthNotification()
    }

    var date : Date = Calendar.getInstance().time

    private fun paymentNotification() {
        val paymentString = "Payment"
        AppLogger.d("Notification", "Decrypted combine " + mDecryptedCombine!!.paymentItems)
        var decryptedPayment = ArrayList<DecryptedPayment>()
        for (paymentItems in mDecryptedCombine!!.paymentItems){
            decryptedPayment.add(paymentItems) }
        AppLogger.d("Notification", "Decrypted payment " + decryptedPayment)
        var expirationDate : String = " "
        var cardName : String = ""
        for (i in 0 until decryptedPayment.size){
            expirationDate = decryptedPayment[i].expiryDate
            cardName = decryptedPayment[i].cardName }
        AppLogger.d("expirationDate", "" + expirationDate)
        try {
            var sdf: SimpleDateFormat = SimpleDateFormat("MM/yyyy")
            var dateOfExpiry = sdf.parse(expirationDate)
            AppLogger.d("expirationDate", "" + dateOfExpiry)
            var difference : Long = dateOfExpiry.getTime() - date.getTime()
            var daysBetween = (difference / (1000 * 60 * 60 * 24))
            AppLogger.d("DaysInbetween", " " + daysBetween)
            addNewNotification(daysBetween, date, dateOfExpiry, paymentString, cardName)
        }
        catch (e :Exception){
            AppLogger.d("Exception", "paymentNotification" + e.message ) }
    }

    private fun propertyNotification() {
        val propertyString = "Property"
        AppLogger.d("Notification", "Decrypted combine property" + mDecryptedCombine!!.propertyItems)
        var decryptedProperty = ArrayList<DecryptedProperty>()
        for(propertyItems in mDecryptedCombine!!.propertyItems){
            decryptedProperty.add(propertyItems)
        }
        AppLogger.d("NotificationDecryptedProperty", "Decrypted payment " + decryptedProperty)
        var leaseEndDate = ""
        var propertyName = ""
        for (i in 0 until decryptedProperty.size){
            leaseEndDate = decryptedProperty[i].leaseEndDate
            propertyName = decryptedProperty[i].propertyName}
        AppLogger.d("LeaseEndDate", "" + leaseEndDate)

        try{
            var propertyDateFormat : SimpleDateFormat = SimpleDateFormat("MM/dd/yyyy")
            var endDate = propertyDateFormat.parse(leaseEndDate)
            AppLogger.d("LeaseEndDate", "end Date" + endDate)
            var propertyDateDifference : Long = endDate.getTime() - date.getTime()
            var propertyDaysBetween = (propertyDateDifference / (1000 * 60 * 60 * 24))
            AppLogger.d("LeaseEndDate", "Days in between " + propertyDaysBetween)
            addNewNotification(propertyDaysBetween, date, endDate, propertyString, propertyName)
        }catch(e : Exception) {
            AppLogger.d("Exception", "propertyLeaseNotification" + e.message)
        }
    }

    private fun vehicleNotification() {
        val vehicleString = "Vehicle"
        AppLogger.d("Notification", "Decrypted combine vehicle" + mDecryptedCombine!!.vehicleItems)
        var decryptedVehicle = ArrayList<DecryptedVehicle>()
        for(vehicleItems in mDecryptedCombine!!.vehicleItems){
            decryptedVehicle.add(vehicleItems)
        }
        AppLogger.d("Vehicle", "Decrypted vehicle" + decryptedVehicle)
        var registrationExpiryDate = ""
        var vehicleName = ""
        for(i in 0 until decryptedVehicle.size){
            registrationExpiryDate = decryptedVehicle[i].registrationExpirydate
            vehicleName = decryptedVehicle[i].vehicleName
        }
        AppLogger.d("Vehicle", "Registration expiry date " + registrationExpiryDate)
        AppLogger.d("Vehicle", "Vehicle Name " + vehicleName)
        try{
            var vehicleDateFormat : SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            var regExpiryDate = vehicleDateFormat.parse(registrationExpiryDate)
            var registrationDateDifference : Long = regExpiryDate.getTime() - date.getTime()
            var propertyDaysBetween = (registrationDateDifference / (1000 * 60 * 60 * 24))
            AppLogger.d("LeaseEndDate", "Days in between " + propertyDaysBetween)
            addNewNotification(propertyDaysBetween, date, regExpiryDate, vehicleName, vehicleString)
        }catch(e: Exception){
            AppLogger.d("Exception", "vehicleRegistrationExpiration" + e.message)
        }
    }

    private fun insuranceNotification() {
        //Todo
    }

    private fun travelDatesAndPlans() {
        //Todo
    }

    private fun travelDocuments() {
        //Todo
    }

    private fun sharedCOntactsNotification() {
        //Todo
    }

    private fun gradudationAnniversaryNotification() {
        //Todo
    }

    private fun workAnniversaryNotification() {
        //Todo
    }

    private fun dlExpiryNotification() {
        //Todo
    }

    private fun birthdayNotification() {
        //Todo
    }

    private fun anniversaryNotification() {
        //Todo
    }

    private fun governMentIDNotification() {
        //Todo
    }

    private fun personalHealthNotification() {
        //Todo
    }
    private fun addNewNotification(numberOfDays: Long, currentDate: Date, expiryDate: Date, type: String, subTitle : String) {
        try {
            when(type){
                "Payment" ->{
                    if (numberOfDays.equals(90))
                        newNotification(currentDate, expiryDate, subTitle)
                    }
                "Property" ->{
                    if(numberOfDays.equals(180))
                        newNotification(currentDate, expiryDate, subTitle)
                }
                "Vehicle" -> {
                    if (numberOfDays.equals(90))
                        newNotification(currentDate, expiryDate, subTitle)
                }
            }

        }catch(e : Exception){
            AppLogger.d("PaymentNotification", "" + e.message)
        }
    }

    private fun newNotification(currentDate: Date, expiryDate: Date, subTitle: String) {
        mNotificationsPresenter!!.addNotification(expiryDate.toString(), currentDate, subTitle)
        var intent : Intent = Intent(getActivity(), HomeActivity::class.java)
        var pIntent : PendingIntent = PendingIntent.getActivity(getActivity(), System.currentTimeMillis().toInt(), intent, 0)
        val alarmManager1 = context!!.getSystemService(ALARM_SERVICE) as AlarmManager
        val calendar1Notify = Calendar.getInstance()
        calendar1Notify.timeInMillis = System.currentTimeMillis()
        calendar1Notify.set(Calendar.HOUR_OF_DAY, 12)
        calendar1Notify.set(Calendar.MINUTE, 0)
        alarmManager1.set(AlarmManager.RTC_WAKEUP, calendar1Notify.timeInMillis, pIntent)

        val time24h = (24 * 60 * 60 * 1000).toLong()

        alarmManager1.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar1Notify.timeInMillis, time24h, pIntent)
        val mNotifiction = Notification.Builder(getActivity())
                .setContentTitle("Notification from " + subTitle)
                .setContentText("Subject")
                .setContentIntent(pIntent).build()
        val notificationManager = context!!.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        mNotifiction.flags = mNotifiction.flags or Notification.FLAG_AUTO_CANCEL
        notificationManager.notify(0, mNotifiction)
    }*/
}