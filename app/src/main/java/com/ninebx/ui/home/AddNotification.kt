package com.ninebx.ui.home

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.ninebx.NineBxApplication
import com.ninebx.ui.base.realm.CalendarEvents
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.home.notifications.NotificationsPresenter
import com.ninebx.utility.AppLogger
import io.realm.RealmResults
import io.realm.internal.SyncObjectServerFacade.getApplicationContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by smrit on 26-02-2018.
 */
class AddNotification : HomeView {
    private var mDecryptedCombine : DecryptedCombine ?= null
    private var mDecryptedCombineTravel : DecryptedCombineTravel ?= null
    private var mDecryptedCombineEducation : DecryptedCombineEducation ?= null
    private var mDecryptedCombineWellness : DecryptedCombineWellness ?= null
    private var mDecryptedCombineContacts : DecryptedCombineContacts ?= null
    private var mDecryptedCombinePersonal : DecryptedCombinePersonal ?= null
    private var currentUsers: RealmResults<Users>? = null
    private var mNotificationsPresenter : NotificationsPresenter?= null

    private val context = getApplicationContext()

    override fun showProgress(message: Int) {    }

    override fun hideProgress() {    }

    override fun onError(error: Int) {    }

    override fun addEditCalendarEvent(calendarEvent: CalendarEvents?, selectedDate: Date) {    }

    override fun getCurrentUsers(): RealmResults<Users> {
        NineBxApplication.instance.currentUser = currentUsers!![0]
        return currentUsers!!
    }

    override fun setNotificationCount(notificationCount: Int) {    }

    override fun getContextForScreen(): Context {  return context }

    override fun setCurrentUsers(currentUsers: RealmResults<Users>?) {
        this.currentUsers = currentUsers
        if( currentUsers != null ) {
            personalNotification()
            personalHealthNotification()
        }
    }

    override fun onCombineHomeFetched(mDecryptCombineHome: DecryptedCombine) {
        this.mDecryptedCombine = mDecryptCombineHome
        homeNotification()
    }

    override fun onCombineTravelFetched(mDecryptCombineTravel: DecryptedCombineTravel) {
        this.mDecryptedCombineTravel = mDecryptCombineTravel
        travelNotification()
    }

    override fun onCombineContactsFetched(mDecryptCombineContacts: DecryptedCombineContacts) {
        this.mDecryptedCombineContacts = mDecryptCombineContacts
        contactsNotification()
    }

    override fun onCombinePersonalFetched(mDecryptCombinePersonal: DecryptedCombinePersonal) {
        this.mDecryptedCombinePersonal = mDecryptCombinePersonal
        if( currentUsers != null )
            personalNotification()
    }

    override fun onCombineWellnessFetched(mDecryptCombineWellness: DecryptedCombineWellness) {
        this.mDecryptedCombineWellness = mDecryptCombineWellness
        if( currentUsers != null )
        wellnessNotification()
    }

    override fun onCombineEducationFetched(mDecryptCombineEducation: DecryptedCombineEducation) {
       this.mDecryptedCombineEducation = mDecryptCombineEducation
        educationWorkNotification()
    }

    private fun homeNotification() {
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
        sharedContactsNotification()
    }

    private fun educationWorkNotification() {
        gradudationAnniversaryNotification()
        workAnniversaryNotification()
    }

    private fun personalNotification() {
        dlExpiryNotification()
        birthdayNotification()
        governmentIDNotification()
    }
    private fun wellnessNotification() {
        personalHealthNotification()
    }

    var date : Date = Calendar.getInstance().time
    var sdf: SimpleDateFormat = SimpleDateFormat("MM/yyyy")
    var dateFormat : SimpleDateFormat = SimpleDateFormat("MM/dd/yyyy")
    var birthdayFormat : SimpleDateFormat = SimpleDateFormat("MM/dd")

    private fun paymentNotification() {
        AppLogger.d("Notification", "Decrypted combine payment " + mDecryptedCombine!!.paymentItems)
        var decryptedPayment = ArrayList<DecryptedPayment>()
        for (paymentItems in mDecryptedCombine!!.paymentItems){
            decryptedPayment.add(paymentItems) }
        AppLogger.d("Notification", "Decrypted payment " + decryptedPayment)
        var expirationDate : String = " "
        var cardName : String = ""
        for (i in 0 until decryptedPayment.size){
            expirationDate = decryptedPayment[i].expiryDate
            cardName = decryptedPayment[i].cardName
            AppLogger.d("expirationDate", "" + expirationDate)
            try {
                var dateOfExpiry = sdf.parse(expirationDate)
                AppLogger.d("expirationDate", "" + dateOfExpiry)
                var difference : Long = dateOfExpiry.getTime() - date.getTime()
                var daysBetween = (difference / (1000 * 60 * 60 * 24))
                AppLogger.d("DaysInbetween", " " + daysBetween)
                if (daysBetween.equals(90))
                    newNotification(date, dateOfExpiry, cardName)
            }
            catch (e :Exception){
                AppLogger.d("Exception", "paymentNotification" + e.message )
            }
        }

    }

    private fun propertyNotification() {
        AppLogger.d("Notification", "Decrypted combine property" + mDecryptedCombine!!.propertyItems)
        var decryptedProperty = ArrayList<DecryptedProperty>()
        for(propertyItems in mDecryptedCombine!!.propertyItems){
            decryptedProperty.add(propertyItems)
        }
        AppLogger.d("NotificationDecryptedProperty", "Decrypted payment " + decryptedProperty)
        var leaseEndDate = ""
        var propertyName = ""
        var isRented : Boolean
        var purchaseDate = ""
        for (i in 0 until decryptedProperty.size){
            leaseEndDate = decryptedProperty[i].leaseEndDate
            propertyName = decryptedProperty[i].propertyName
            isRented = decryptedProperty[i].currentlyRented
            purchaseDate = decryptedProperty[i].purchaseDate
            if(isRented) {
                try {
                    var endDate = dateFormat.parse(leaseEndDate)
                    AppLogger.d("LeaseEndDate", "end Date" + endDate)
                    var propertyDateDifference: Long = endDate.getTime() - date.getTime()
                    var propertyDaysBetween = (propertyDateDifference / (1000 * 60 * 60 * 24))
                    AppLogger.d("LeaseEndDate", "Days in between " + propertyDaysBetween)
                    if(propertyDaysBetween.equals(180))
                        newNotification(date, endDate, propertyName)
                } catch (e: Exception) {
                    AppLogger.d("Exception", "propertyLeaseNotification" + e.message)
                }
            }
            else{
                try {
                    var purchase = birthdayFormat.parse(purchaseDate)
                    var present : Date = birthdayFormat.format(date) as Date
                    AppLogger.d("PurchaseAnniversary", "end Date" + purchase)
                    if (purchase.equals(present)){
                        AppLogger.d("purchaseAnniversary", " " + purchase)
                        newNotification(date, present, propertyName)
                    }
                } catch (e: Exception) {
                    AppLogger.d("Exception", "propertyLeaseNotification" + e.message)
                }
            }
        }
        AppLogger.d("LeaseEndDate", "" + leaseEndDate)
    }

    private fun vehicleNotification() {
        AppLogger.d("Notification", "Decrypted combine vehicle" + mDecryptedCombine!!.vehicleItems)
        var decryptedVehicle = ArrayList<DecryptedVehicle>()
        for(vehicleItems in mDecryptedCombine!!.vehicleItems){
            decryptedVehicle.add(vehicleItems)
        }
        AppLogger.d("Vehicle", "Decrypted vehicle" + decryptedVehicle)
        var registrationExpiryDate = ""
        var vehicleName = ""
        var purchasedOrLeased = ""
        var leaseEnd = ""
        for(i in 0 until decryptedVehicle.size){
            registrationExpiryDate = decryptedVehicle[i].registrationExpirydate
            vehicleName = decryptedVehicle[i].vehicleName
            purchasedOrLeased = decryptedVehicle[i].purchasedOrLeased
            leaseEnd = decryptedVehicle[i].leaseEndDate
            if(purchasedOrLeased.equals("Leased")){
                var lease = dateFormat.parse(leaseEnd)
                var differenceDate: Long = lease.getTime() - date.getTime()
                var vehicle = (differenceDate / (1000 * 60 * 60 * 24))
                AppLogger.d("LeaseEndDate", "Days in between " + vehicle)
                if(differenceDate.equals(180)){
                    newNotification(date, lease, vehicleName)
                }
            }else {
                try {
                    var regExpiryDate = dateFormat.parse(registrationExpiryDate)
                    var registrationDateDifference: Long = regExpiryDate.getTime() - date.getTime()
                    var VehicleDaysBetween = (registrationDateDifference / (1000 * 60 * 60 * 24))
                    AppLogger.d("LeaseEndDate", "Days in between " + VehicleDaysBetween)
                    if(registrationDateDifference.equals(90)){
                        newNotification(date, regExpiryDate, vehicleName)
                    }
                } catch (e: Exception) {
                    AppLogger.d("Exception", "vehicleRegistrationExpiration" + e.message)
                }
            }
        }
        AppLogger.d("Vehicle", "Registration expiry date " + registrationExpiryDate)
        AppLogger.d("Vehicle", "Vehicle Name " + vehicleName)
    }

    private fun insuranceNotification() {
        AppLogger.d("Notification", "Decrypted combine Insurance" + mDecryptedCombine!!.insuranceItems)
        var decryptedInsurance = ArrayList<DecryptedInsurance>()
        for(insuranceItems in mDecryptedCombine!!.insuranceItems){
            decryptedInsurance.add(insuranceItems)
        }
        var insuranceExpiryDate = ""
        var insuranceType = ""
        for(i in 0 until decryptedInsurance.size){
            insuranceExpiryDate = decryptedInsurance[i].policyExpirationDate
            insuranceType = decryptedInsurance[i].selectionType
            var insuranceExpiryDate = dateFormat.parse(insuranceExpiryDate)
            var insuranceDateDifference: Long = insuranceExpiryDate.getTime() - date.getTime()
            var insuranceDaysBetween = (insuranceDateDifference / (1000 * 60 * 60 * 24))
            if(insuranceType.equals("Life"))
            {
                if(insuranceDaysBetween.equals(90))
                    newNotification(date, insuranceExpiryDate, insuranceType)
            }
            else{
                if(insuranceDaysBetween.equals(30))
                    newNotification(date, insuranceExpiryDate, insuranceType)
            }
        }
    }

    private fun travelDatesAndPlans() {
        AppLogger.d("Notification", "Decrypted combineTravel Vacation " + mDecryptedCombineTravel!!.vacationsItems)
        var decryptedVacations = ArrayList<DecryptedVacations>()
        for(vacationItems in mDecryptedCombineTravel!!.vacationsItems){
            decryptedVacations.add(vacationItems)
        }
        var startDate = ""
        var description = ""
        for(i in 0 until decryptedVacations.size){
            startDate = decryptedVacations[i].startDate
            description = decryptedVacations[i].vac_description
            try{
                var vacStart = dateFormat.parse(startDate)
                var vacationDateDifference: Long = vacStart.getTime() - date.getTime()
                var vacDaysBetween = (vacationDateDifference / (1000 * 60 * 60 * 24))
                if(vacDaysBetween.equals(90))
                    newNotification(date, vacStart, description)
            }
            catch(e : Exception){
                AppLogger.d("Exception", " " + e.message)
            }
        }
    }

    private fun travelDocuments() {
        AppLogger.d("Notification", "Decrypted combineTravel Documents " + mDecryptedCombineTravel!!.documentsItems)
        var decryptedTravelDocuments = ArrayList<DecryptedDocuments>()
        for(documentItems in mDecryptedCombineTravel!!.documentsItems){
            decryptedTravelDocuments.add(documentItems)
        }
        var docExpirationDate = ""
        var docType = ""
        for(i in 0 until decryptedTravelDocuments.size){
            docExpirationDate = decryptedTravelDocuments[i].expirationDate
            docType = decryptedTravelDocuments[i].travelDocumentType
            var docExpiry = dateFormat.parse(docExpirationDate)
            var docDateDifference: Long = docExpiry.getTime() - date.getTime()
            var docDaysBetween = (docDateDifference / (1000 * 60 * 60 * 24))
            if(docType.equals("Visa")){
                if(docDaysBetween.equals(180))
                    newNotification(date, docExpiry, docType)
            }else{
                if (docDateDifference.equals(270))
                    newNotification(date, docExpiry, docType)
            }
        }
    }

    private fun sharedContactsNotification() {
        AppLogger.d("Notification", "Decrypted combinecontacts" + mDecryptedCombineContacts!!.contactsItems)
        var decryptedContacts = ArrayList<DecryptedContacts>()
        for(contactItems in mDecryptedCombineContacts!!.contactsItems){
            decryptedContacts.add(contactItems)
        }
        AppLogger.d("Contacts", "Decrypted contacts" + decryptedContacts)
        var contactsAnniversary = ""
        var contactsBirthday = ""
        var contactsName = ""
        for(i in 0 until decryptedContacts.size){
            contactsAnniversary = decryptedContacts[i].anniversary
            contactsBirthday = decryptedContacts[i].dateOfBirth
            contactsName = decryptedContacts[i].firstName
            try {
                var anniversary = birthdayFormat.parse(contactsAnniversary)
                var present : Date = birthdayFormat.format(date) as Date
                AppLogger.d("PurchaseAnniversary", "end Date" + anniversary)
                if (anniversary.equals(present)){
                    AppLogger.d("purchaseAnniversary", " " + anniversary)
                    newNotification(date, present, contactsName)
                }
            } catch (e: Exception) {
                AppLogger.d("Exception", "birthdayNotification" + e.message) }
            try {
                var birthday = birthdayFormat.parse(contactsBirthday)
                var present: Date = birthdayFormat.format(date) as Date
                AppLogger.d("PurchaseAnniversary", "end Date" + birthday)
                if (birthday.equals(present)) {
                    AppLogger.d("purchaseAnniversary", " " + birthday)
                    newNotification(date, present, contactsName)
                }
            } catch (e: Exception) {
                AppLogger.d("Exception", "birthdayNotification" + e.message)
            }
        }
        }
    private fun gradudationAnniversaryNotification() {
        AppLogger.d("Notification", "Decrypted combine work and Education" + mDecryptedCombineEducation!!.educationItems)
        var decryptedEducation = ArrayList<DecryptedEducation>()
        for(educationItems in mDecryptedCombineEducation!!.educationItems){
            decryptedEducation.add(educationItems)
        }
        AppLogger.d("Education", "Decrypted education" + decryptedEducation)
              var graduationAnniversary = ""
            var graduationName = ""
            for(i in 0 until decryptedEducation.size){
                graduationAnniversary = decryptedEducation[i].created
                try {
                    var anniversary = birthdayFormat.parse(graduationAnniversary)
                    var present : Date = birthdayFormat.format(date) as Date
                    AppLogger.d("PurchaseAnniversary", "end Date" + anniversary)
                    if (anniversary.equals(present)){
                        AppLogger.d("purchaseAnniversary", " " + anniversary)
                        newNotification(date, present, graduationName)
                    }
                } catch (e: Exception) {
                    AppLogger.d("Exception", "birthdayNotification" + e.message) }
            }
    }

    private fun workAnniversaryNotification() {
        AppLogger.d("Notification", "Decrypted combine work and Education" + mDecryptedCombineEducation!!.workItems)
        var decryptedWork = ArrayList<DecryptedWork>()
        for(workItems in mDecryptedCombineEducation!!.workItems){
            decryptedWork.add(workItems)
        }
        AppLogger.d("Work", "Decrypted education" + decryptedWork)
        var workAnniversary = ""
        var companyName = ""
        for(i in 0 until decryptedWork.size){
            workAnniversary = decryptedWork[i].from
            companyName = decryptedWork[i].companyName
            try {
                var anniversaryWork = birthdayFormat.parse(workAnniversary)
                var present : Date = birthdayFormat.format(date) as Date
                AppLogger.d("PurchaseAnniversary", "end Date" + anniversaryWork)
                if (anniversaryWork.equals(present)){
                    AppLogger.d("purchaseAnniversary", " " + anniversaryWork)
                    newNotification(date, present, companyName)
                }
            } catch (e: Exception) {
                AppLogger.d("Exception", "birthdayNotification" + e.message) }
        }
    }

    private fun dlExpiryNotification() {
        AppLogger.d("Notification", "Decrypted combinePersonal " + mDecryptedCombinePersonal!!.licenseItems)
        var decryptedLicense = ArrayList<DecryptedLicense>()
        for (licenseItems in mDecryptedCombinePersonal!!.licenseItems){
            decryptedLicense.add(licenseItems)
        }
        var driversLicense = "Drivers License"
        var licenceExpirationDate = ""
        for(i in 0 until decryptedLicense.size){
            licenceExpirationDate = decryptedLicense[i].expirationDate
            try{
                var licenseExpiry = dateFormat.parse(licenceExpirationDate)
                var licenseDateDifference: Long = licenseExpiry.getTime() - date.getTime()
                var insuranceDaysBetween = (licenseDateDifference / (1000 * 60 * 60 * 24))
                if (insuranceDaysBetween.equals(90))
                    newNotification(date, licenseExpiry, driversLicense)
            }
            catch (e: Exception){
                AppLogger.d("Exception", "" + e.message)
            }
        }
    }

    private fun birthdayNotification() {
        AppLogger.d("Notification", "Decrypted combine personal" + mDecryptedCombinePersonal!!.personalItems)
        var decryptedPersonal = ArrayList<DecryptedPersonal>()
        for(personalItems in mDecryptedCombinePersonal!!.personalItems){
            decryptedPersonal.add(personalItems)
        }
        AppLogger.d("Personal", "Decrypted personal" + decryptedPersonal)
        var anniversary =  currentUsers!![0]!!.anniversary
        var birthday = currentUsers!![0]!!.dateOfBirth

        try {
            var anniversary = birthdayFormat.parse(anniversary)
            var present : Date = birthdayFormat.format(date) as Date
            AppLogger.d("PurchaseAnniversary", "end Date" + anniversary)
            if (anniversary.equals(present)){
                AppLogger.d("anniversary", " " + anniversary)
                newNotification(date, present, anniversary.toString())
            }
            if (birthday.equals(present)){
                AppLogger.d("birthday", " " + birthday)
                newNotification(date, present, birthday.toString())
            }
        } catch (e: Exception) {
            AppLogger.d("Exception", "Anniversary" + e.message) }
    }

    private fun governmentIDNotification() {
        AppLogger.d("Notification", "Decrypted Combine personal" + mDecryptedCombinePersonal!!.governmentItems)
        var decryptedPersonalGovernment = ArrayList<DecryptedGovernment>()
        for (governmentItems in mDecryptedCombinePersonal!!.governmentItems){
            decryptedPersonalGovernment.add(governmentItems)
        }
        var govIDExpiryDate = ""
        var govIDName = ""
        for (i in 0 until decryptedPersonalGovernment.size){
            govIDExpiryDate = decryptedPersonalGovernment[i].expirationDate
            govIDName = decryptedPersonalGovernment[i].nameOnId
            try{
                var governmentExpiry = dateFormat.parse(govIDExpiryDate)
                var governmentDateDifference: Long = governmentExpiry.getTime() - date.getTime()
                var insuranceDaysBetween = (governmentDateDifference / (1000 * 60 * 60 * 24))
                if (insuranceDaysBetween.equals(90))
                    newNotification(date, governmentExpiry, govIDName)
            }
            catch (e: Exception){
                AppLogger.d("Exception", "" + e.message)
            }
        }
    }

    private fun personalHealthNotification() {
        AppLogger.d("Notification", "Decrypted Combine " + mDecryptedCombineWellness!!.eyeglassPrescriptionsItems)
        var decryptedEyeglassPrescriptions = ArrayList<DecryptedEyeglassPrescriptions>()
        var decryptedVitalNumbers = ArrayList<DecryptedVitalNumbers>()
        for(eyeglassPrescriptionsItems in mDecryptedCombineWellness!!.eyeglassPrescriptionsItems){
            decryptedEyeglassPrescriptions.add(eyeglassPrescriptionsItems)
        }
        for(vitalNumberItems in mDecryptedCombineWellness!!.vitalNumbersItems){
            decryptedVitalNumbers.add(vitalNumberItems)
        }
        var eyeglasslastCheckUp = ""
        var userName = currentUsers!![0]!!.fullName
        for(i in 0 until decryptedEyeglassPrescriptions.size) {
            eyeglasslastCheckUp = decryptedEyeglassPrescriptions[i].datePrescribed
            try {
                var eyeglassPrevious = dateFormat.parse(eyeglasslastCheckUp)
                var eyeglassDateDifference: Long = date.getTime() - eyeglassPrevious.getTime()
                var eyeglassDaysBetween = (eyeglassDateDifference / (1000 * 60 * 60 * 24))
                if (eyeglassDaysBetween.equals(330))
                    newNotification(date, eyeglassPrevious, userName)
            }catch(e : Exception){
                AppLogger.d("Exception", " " + e.message)
            }
        }
        var vitalMeasurement = ""
        for(i in 0 until decryptedVitalNumbers.size) {
            vitalMeasurement = decryptedVitalNumbers[i].measurementDate
            try {
                var vitalPrevious = dateFormat.parse(vitalMeasurement)
                var vitalDateDifference: Long = date.getTime() - vitalPrevious.getTime()
                var vitalDaysBetween = (vitalDateDifference / (1000 * 60 * 60 * 24))
                if (vitalDaysBetween.equals(330))
                    newNotification(date, vitalPrevious, userName)
            }catch(e : Exception){
                AppLogger.d("Exception", " " + e.message)
            }
        }
    }

    private fun newNotification(currentDate: Date, expiryDate: Date, subTitle: String) {
        mNotificationsPresenter!!.addNotification(expiryDate.toString(), currentDate, subTitle)
        var intent : Intent = Intent(context, HomeActivity::class.java)
        var pIntent : PendingIntent = PendingIntent.getActivity(context, System.currentTimeMillis().toInt(), intent, 0)
        val alarmManager1 = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar1Notify = Calendar.getInstance()
        calendar1Notify.timeInMillis = System.currentTimeMillis()
        calendar1Notify.set(Calendar.HOUR_OF_DAY, 12)
        calendar1Notify.set(Calendar.MINUTE, 0)
        alarmManager1.set(AlarmManager.RTC_WAKEUP, calendar1Notify.timeInMillis, pIntent)

        val time24h = (24 * 60 * 60 * 1000).toLong()

        alarmManager1.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar1Notify.timeInMillis, time24h, pIntent)
        val mNotifiction = Notification.Builder(context)
                .setContentTitle("Notification from " + subTitle)
                .setContentText("Subject")
                .setContentIntent(pIntent).setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_ALL).build()

        val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotifiction.flags = mNotifiction.flags or Notification.FLAG_AUTO_CANCEL
        notificationManager.notify(0, mNotifiction)
    }
}