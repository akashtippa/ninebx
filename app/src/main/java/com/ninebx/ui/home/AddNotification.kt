package com.ninebx.ui.home

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import com.ninebx.NineBxApplication
import com.ninebx.ui.base.realm.CalendarEvents
import com.ninebx.ui.base.realm.Notifications
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import io.realm.internal.SyncObjectServerFacade.getApplicationContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.content.Context.NOTIFICATION_SERVICE
import android.support.v4.app.NotificationCompat
import com.ninebx.R.drawable.logo_nine

/**
 * Created by smrit on 26-02-2018.
 */
class AddNotification : HomeView {
    override fun startCameraIntent() {

    }

    private var mDecryptedCombine : DecryptedCombine ?= null
    private var mDecryptedCombineTravel : DecryptedCombineTravel ?= null
    private var mDecryptedCombineEducation : DecryptedCombineEducation ?= null
    private var mDecryptedCombineWellness : DecryptedCombineWellness ?= null
    private var mDecryptedCombineContacts : DecryptedCombineContacts ?= null
    private var mDecryptedCombinePersonal : DecryptedCombinePersonal ?= null
    private var currentUsers: ArrayList<DecryptedUsers>? = null

    private val context = getApplicationContext()

    init {
        HomePresenter(this).fetchDataInBackground()
    }

    override fun showProgress(message: Int) {    }

    override fun hideProgress() {    }

    override fun onError(error: Int) {    }

    override fun addEditCalendarEvent(calendarEvent: CalendarEvents?, selectedDate: Date) {    }

    override fun getCurrentUsers(): ArrayList<DecryptedUsers> {
        NineBxApplication.instance.currentUser = currentUsers!![0]
        return currentUsers!!
    }

    override fun setNotificationCount(notificationCount: Int) {    }

    override fun getContextForScreen(): Context {  return context }

    override fun setCurrentUsers(currentUsers: ArrayList<DecryptedUsers> ?) {
        this.currentUsers = currentUsers
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
        graduationAnniversaryNotification()
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
        val boxName = "Home&Money"
        //AppLogger.d("AddNewNotification", "Decrypted combine payment " + mDecryptedCombine!!.paymentItems)
        var decryptedPayment = ArrayList<DecryptedPayment>()
        for (paymentItems in mDecryptedCombine!!.paymentItems){
            decryptedPayment.add(paymentItems) }
        //AppLogger.d("Notification", "Decrypted payment " + decryptedPayment)
        var expirationDate : String = " "
        var cardName : String = ""
        for (i in 0 until decryptedPayment.size){
            expirationDate = decryptedPayment[i].expiryDate
            cardName = decryptedPayment[i].cardName
            //AppLogger.d("expirationDate", "" + expirationDate)
            try {
                var dateOfExpiry = sdf.parse(expirationDate)
                //AppLogger.d("expirationDate", "" + dateOfExpiry)
                var difference : Long = dateOfExpiry.getTime() - date.getTime()
                var daysBetween = (difference / (1000 * 60 * 60 * 24))
                //AppLogger.d("DaysInbetween", " " + daysBetween)
                if (daysBetween.equals(90))
                {
                    val schecduledDate = Calendar.getInstance()
                    schecduledDate.timeInMillis = dateOfExpiry.time - (daysBetween * (1000 * 60 * 60 * 24))
                    newNotification(decryptedPayment[i].id, schecduledDate.time, dateOfExpiry, cardName,boxName)
                }
            }
            catch (e :Exception){
                AppLogger.d("Exception", "paymentNotification" + e.message )
            }
        }
    }

    private fun propertyNotification() {
        val boxName = "Home&Money"
        //AppLogger.d("AddNewNotification", "Decrypted combine property" + mDecryptedCombine!!.propertyItems)
        var decryptedProperty = ArrayList<DecryptedProperty>()
        for(propertyItems in mDecryptedCombine!!.propertyItems){
            decryptedProperty.add(propertyItems)
        }
        //AppLogger.d("NotificationDecryptedProperty", "Decrypted payment " + decryptedProperty)
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
                    //AppLogger.d("LeaseEndDate", "end Date" + endDate)
                    var propertyDateDifference: Long = endDate.getTime() - date.getTime()
                    var propertyDaysBetween = (propertyDateDifference / (1000 * 60 * 60 * 24)).toInt()
                    //AppLogger.d("LeaseEndDate", "Days in between " + propertyDaysBetween)
                    if(propertyDaysBetween == 180){
                        val schecduledDate = Calendar.getInstance()
                        schecduledDate.timeInMillis = endDate.time - (propertyDaysBetween * (1000 * 60 * 60 * 24))
                        newNotification(decryptedProperty[i].id, schecduledDate.time, endDate, propertyName, boxName)
                    }

                } catch (e: Exception) {
                    //AppLogger.d("Exception", "propertyLeaseNotification" + e.message)
                }
            }
            else{
                try {
                    var purchase = birthdayFormat.parse(purchaseDate)
                    var present : Date = birthdayFormat.format(date) as Date
                    //AppLogger.d("PurchaseAnniversary", "end Date" + purchase)
                    var propertyDateDifference: Long = purchase.getTime() - date.getTime()
                    var propertyDaysBetween = (propertyDateDifference / (1000 * 60 * 60 * 24)).toInt()
                    if (propertyDaysBetween == 1){
                        //AppLogger.d("purchaseAnniversary", " " + purchase)
                        val schecduledDate = Calendar.getInstance()
                        schecduledDate.timeInMillis = purchase.time - (propertyDaysBetween * (1000 * 60 * 60 * 24))
                        newNotification(decryptedProperty[i].id, schecduledDate.time, present, propertyName, boxName)
                    }
                } catch (e: Exception) {
                    //AppLogger.d("Exception", "propertyLeaseNotification" + e.message)
                }
            }
        }
        //AppLogger.d("LeaseEndDate", "" + leaseEndDate)
    }

    private fun vehicleNotification() {
        //AppLogger.d("AddNewNotification", "Decrypted combine vehicle" + mDecryptedCombine!!.vehicleItems)
        val boxName = "Home&Money"
        var decryptedVehicle = ArrayList<DecryptedVehicle>()
        for(vehicleItems in mDecryptedCombine!!.vehicleItems){
            decryptedVehicle.add(vehicleItems)
        }
        //AppLogger.d("Vehicle", "Decrypted vehicle" + decryptedVehicle)
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
                var vehicle = (differenceDate / (1000 * 60 * 60 * 24)).toInt()
                //AppLogger.d("LeaseEndDate", "Days in between " + vehicle)
                if(vehicle == 180){
                    val schecduledDate = Calendar.getInstance()
                    schecduledDate.timeInMillis = lease.time - (differenceDate * (1000 * 60 * 60 * 24))
                    newNotification(decryptedVehicle[i].id, schecduledDate.time, lease, vehicleName, boxName)
                }
            }else {
                try {
                    var regExpiryDate = dateFormat.parse(registrationExpiryDate)
                    var registrationDateDifference: Long = regExpiryDate.getTime() - date.getTime()
                    var vehicleDaysBetween = (registrationDateDifference / (1000 * 60 * 60 * 24)).toInt()
                    //AppLogger.d("LeaseEndDate", "Days in between " + VehicleDaysBetween)
                    if(vehicleDaysBetween == 90){
                        val schecduledDate = Calendar.getInstance()
                        schecduledDate.timeInMillis = regExpiryDate.time - (vehicleDaysBetween * (1000 * 60 * 60 * 24))
                        newNotification(decryptedVehicle[i].id, schecduledDate.time, regExpiryDate, vehicleName, boxName)
                    }
                } catch (e: Exception) {
                    //AppLogger.d("Exception", "vehicleRegistrationExpiration" + e.message)
                }
            }
        }
        //AppLogger.d("Vehicle", "Registration expiry date " + registrationExpiryDate)
        //AppLogger.d("Vehicle", "Vehicle Name " + vehicleName)
    }

    private fun insuranceNotification() {
        //AppLogger.d("AddNewNotification", "Decrypted combine Insurance" + mDecryptedCombine!!.insuranceItems)
        val boxName = "Home&Money"
        var decryptedInsurance = ArrayList<DecryptedInsurance>()
        for(insuranceItems in mDecryptedCombine!!.insuranceItems){
            decryptedInsurance.add(insuranceItems)
        }
        var insuranceExpiryDate = ""
        var insuranceType = ""
        for(i in 0 until decryptedInsurance.size){
            insuranceExpiryDate = decryptedInsurance[i].policyExpirationDate
            insuranceType = decryptedInsurance[i].selectionType
            try{
                var insuranceExpiryDate = dateFormat.parse(insuranceExpiryDate)
                var insuranceDateDifference: Long = insuranceExpiryDate.getTime() - date.getTime()
                var insuranceDaysBetween = (insuranceDateDifference / (1000 * 60 * 60 * 24)).toInt()
                if(insuranceType.equals("Life"))
                {
                    if(insuranceDaysBetween == 90){
                        val schecduledDate = Calendar.getInstance()
                        schecduledDate.timeInMillis = insuranceExpiryDate.time - (insuranceDaysBetween * (1000 * 60 * 60 * 24))
                        newNotification(decryptedInsurance[i].id, schecduledDate.time, insuranceExpiryDate, insuranceType, boxName)
                    }

                }
                else{
                    if(insuranceDaysBetween == 30){
                        val schecduledDate = Calendar.getInstance()
                        schecduledDate.timeInMillis = insuranceExpiryDate.time - (insuranceDaysBetween * (1000 * 60 * 60 * 24))
                        newNotification(decryptedInsurance[i].id, schecduledDate.time, insuranceExpiryDate, insuranceType, boxName)
                    }
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    private fun travelDatesAndPlans() {
        //AppLogger.d("AddNewNotification", "Decrypted combineTravel Vacation " + mDecryptedCombineTravel!!.vacationsItems)
        val boxName = "Travel"
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
                var vacDaysBetween = (vacationDateDifference / (1000 * 60 * 60 * 24)).toInt()
                if(vacDaysBetween == 90){
                    val schecduledDate = Calendar.getInstance()
                    schecduledDate.timeInMillis = vacStart.time - (vacDaysBetween * (1000 * 60 * 60 * 24))
                    newNotification(decryptedVacations[i].id, schecduledDate.time, vacStart, description, boxName)
                }
            }
            catch(e : Exception){
                //AppLogger.d("Exception", " " + e.message)
            }
        }
    }

    private fun travelDocuments() {
        //AppLogger.d("AddNewNotification", "Decrypted combineTravel Documents " + mDecryptedCombineTravel!!.documentsItems)
        val boxName = "Travel"
        var decryptedTravelDocuments = ArrayList<DecryptedDocuments>()
        for(documentItems in mDecryptedCombineTravel!!.documentsItems){
            decryptedTravelDocuments.add(documentItems)
        }
        var docExpirationDate = ""
        var docType = ""
        for(i in 0 until decryptedTravelDocuments.size){
            docExpirationDate = decryptedTravelDocuments[i].expirationDate
            docType = decryptedTravelDocuments[i].travelDocumentType
            try{
                var docExpiry = dateFormat.parse(docExpirationDate)
                var docDateDifference: Long = docExpiry.getTime() - date.getTime()
                var docDaysBetween = (docDateDifference / (1000 * 60 * 60 * 24)).toInt()
                if(docType.equals("Visa")){
                    if(docDaysBetween == 180){
                        val schecduledDate = Calendar.getInstance()
                        schecduledDate.timeInMillis = docExpiry.time - (docDaysBetween * (1000 * 60 * 60 * 24))
                        newNotification(decryptedTravelDocuments[i].id, schecduledDate.time, docExpiry, docType, boxName)
                    }
                }else{
                    if (docDaysBetween == 270){
                        val schecduledDate = Calendar.getInstance()
                        schecduledDate.timeInMillis = docExpiry.time - (docDaysBetween * (1000 * 60 * 60 * 24))
                        newNotification(decryptedTravelDocuments[i].id,schecduledDate.time, docExpiry, docType, boxName)
                    }
                }
            } catch (e : Exception){
                e.printStackTrace()
            }

        }
    }

    private fun sharedContactsNotification() {
        //AppLogger.d("AddNewNotification", "Decrypted combinecontacts" + mDecryptedCombineContacts!!.contactsItems)
        val boxName = "Contacts"
        var decryptedContacts = ArrayList<DecryptedContacts>()
        for(contactItems in mDecryptedCombineContacts!!.contactsItems){
            decryptedContacts.add(contactItems)
        }
        //AppLogger.d("Contacts", "Decrypted contacts" + decryptedContacts)
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
                var anniversaryDifference: Long = anniversary.getTime() - present.getTime()
                var anniversaryDaysBetween = (anniversaryDifference / (1000 * 60 * 60 * 24)).toInt()
                //AppLogger.d("PurchaseAnniversary", "end Date" + anniversary)
                if (anniversaryDaysBetween == 1){
                    val schecduledDate = Calendar.getInstance()
                    schecduledDate.timeInMillis = anniversary.time - (anniversaryDaysBetween * (1000 * 60 * 60 * 24))
                    newNotification(decryptedContacts[i].id, schecduledDate.time, present, contactsName, boxName)
                }
            } catch (e: Exception) {
                //AppLogger.d("Exception", "birthdayNotification" + e.message)
                e.printStackTrace()
            }
            try {
                var birthday = birthdayFormat.parse(contactsBirthday)
                var present: Date = birthdayFormat.format(date) as Date
                //AppLogger.d("PurchaseAnniversary", "end Date" + birthday)
                var birthdayDifference: Long = birthday.getTime() - present.getTime()
                var birthdayDaysBetween = (birthdayDifference / (1000 * 60 * 60 * 24)).toInt()
                if (birthdayDaysBetween == 1) {
                    //AppLogger.d("purchaseAnniversary", " " + birthday)
                    val schecduledDate = Calendar.getInstance()
                    schecduledDate.timeInMillis = birthday.time - (birthdayDaysBetween * (1000 * 60 * 60 * 24))
                    newNotification(decryptedContacts[i].id, schecduledDate.time, present, contactsName, boxName)
                }
            } catch (e: Exception) {
                //AppLogger.d("Exception", "birthdayNotification" + e.message)
                e.printStackTrace()
            }
        }
        }
    private fun graduationAnniversaryNotification() {
        val boxName = "Personal"
        //AppLogger.d("AddNewNotification", "Decrypted combine work and Education" + mDecryptedCombineEducation!!.educationItems)
        var decryptedEducation = ArrayList<DecryptedEducation>()
        for(educationItems in mDecryptedCombineEducation!!.educationItems){
            decryptedEducation.add(educationItems)
        }
        //AppLogger.d("Education", "Decrypted education" + decryptedEducation)
              var graduationAnniversary = ""
              var graduationName = ""
            for(i in 0 until decryptedEducation.size){
                graduationAnniversary = decryptedEducation[i].created
                try {
                    var anniversary = birthdayFormat.parse(graduationAnniversary)
                    var present : Date = birthdayFormat.format(date) as Date
                    var gradAnniversaryDifference: Long = anniversary.getTime() - present.getTime()
                    var gradAnniversaryBetween = (gradAnniversaryDifference / (1000 * 60 * 60 * 24)).toInt()
                    //AppLogger.d("PurchaseAnniversary", "end Date" + anniversary)
                    if (gradAnniversaryBetween == 1){
                        //AppLogger.d("purchaseAnniversary", " " + anniversary)
                        val schecduledDate = Calendar.getInstance()
                        schecduledDate.timeInMillis = anniversary.time - (gradAnniversaryBetween * (1000 * 60 * 60 * 24))
                        newNotification(decryptedEducation[i].id, schecduledDate.time, present, graduationName, boxName)
                    }
                } catch (e: Exception) {
                    //AppLogger.d("Exception", "birthdayNotification" + e.message)
                }
            }
    }

    private fun workAnniversaryNotification() {
        val boxName = "Personal"
        //AppLogger.d("AddNewNotification", "Decrypted combine work and Education" + mDecryptedCombineEducation!!.workItems)
        var decryptedWork = ArrayList<DecryptedWork>()
        for(workItems in mDecryptedCombineEducation!!.workItems){
            decryptedWork.add(workItems)
        }
        //AppLogger.d("Work", "Decrypted education" + decryptedWork)
        var workAnniversary = ""
        var companyName = ""
        for(i in 0 until decryptedWork.size){
            workAnniversary = decryptedWork[i].from
            companyName = decryptedWork[i].companyName
            try {
                var anniversaryWork = birthdayFormat.parse(workAnniversary)
                var present : Date = birthdayFormat.format(date) as Date
                var workAnniversaryDifference: Long = anniversaryWork.getTime() - present.getTime()
                var workAnniversaryBetween = (workAnniversaryDifference / (1000 * 60 * 60 * 24)).toInt()
                //AppLogger.d("PurchaseAnniversary", "end Date" + anniversaryWork)
                if (workAnniversaryBetween == 1){
                    //AppLogger.d("purchaseAnniversary", " " + anniversaryWork)
                    val schecduledDate = Calendar.getInstance()
                    schecduledDate.timeInMillis = anniversaryWork.time - (workAnniversaryBetween * (1000 * 60 * 60 * 24))
                    newNotification(decryptedWork[i].id, schecduledDate.time, present, companyName, boxName)
                }
            } catch (e: Exception) {
                //AppLogger.d("Exception", "birthdayNotification" + e.message)
                e.printStackTrace()
            }
        }
    }

    private fun dlExpiryNotification() {
        ////AppLogger.d("AddNewNotification", "Decrypted combinePersonal " + mDecryptedCombinePersonal!!.licenseItems)
        val boxName = "Personal"
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
                var licenceDaysBetween = (licenseDateDifference / (1000 * 60 * 60 * 24)).toInt()
                if (licenceDaysBetween == 90){
                    val schecduledDate = Calendar.getInstance()
                    schecduledDate.timeInMillis = licenseExpiry.time - (licenceDaysBetween * (1000 * 60 * 60 * 24))
                    newNotification(decryptedLicense[i].id, schecduledDate.time, licenseExpiry, driversLicense, boxName)
                }
            }
            catch (e: Exception){
                //AppLogger.d("Exception", "" + e.message)
                e.printStackTrace()
            }
        }
    }

    private fun birthdayNotification() {
        val boxName = "Personal"
        //AppLogger.d("AddNewNotification", "Decrypted combine personal" + mDecryptedCombinePersonal!!.personalItems)
        var decryptedPersonal = ArrayList<DecryptedPersonal>()
        for(personalItems in mDecryptedCombinePersonal!!.personalItems){
            decryptedPersonal.add(personalItems)
        }
        //AppLogger.d("Personal", "Decrypted personal" + decryptedPersonal)
        var anniversary =  currentUsers!![0]!!.anniversary
        var birthday = currentUsers!![0]!!.dateOfBirth

        try {
            var anniversaryy = birthdayFormat.parse(anniversary)
            var present : Date = birthdayFormat.format(date) as Date
            var birthdayy = birthdayFormat.parse(birthday)
            //AppLogger.d("PurchaseAnniversary", "end Date" + anniversary)
            var anniversaryyDifference: Long = anniversaryy.getTime() - present.getTime()
            var anniversaryDaysBetween = (anniversaryyDifference / (1000 * 60 * 60 * 24)).toInt()
            if (anniversaryDaysBetween == 1){
                //AppLogger.d("anniversary", " " + anniversary)
                val schecduledDate = Calendar.getInstance()
                schecduledDate.timeInMillis = anniversaryy.time - (anniversaryDaysBetween * (1000 * 60 * 60 * 24))
                newNotification(mDecryptedCombinePersonal!!.id, schecduledDate.time, present, anniversary.toString(), boxName)
            }
            var birthdayyDifference: Long = birthdayy.getTime() - present.getTime()
            var birthdayyDaysBetween = (birthdayyDifference / (1000 * 60 * 60 * 24)).toInt()
            if (birthdayyDaysBetween == 1){
                //AppLogger.d("birthday", " " + birthday)
                val schecduledDate = Calendar.getInstance()
                schecduledDate.timeInMillis = birthdayy.time - (anniversaryDaysBetween * (1000 * 60 * 60 * 24))
                newNotification(mDecryptedCombinePersonal!!.id, schecduledDate.time, present, birthday.toString(), boxName)
            }
        } catch (e: Exception) {
            //AppLogger.d("Exception", "Anniversary" + e.message)
            e.printStackTrace()
        }
    }

    private fun governmentIDNotification() {
        val boxName = "Personal"
        //AppLogger.d("AddNewNotification", "Decrypted Combine personal" + mDecryptedCombinePersonal!!.governmentItems)
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
                var govDaysBetween = (governmentDateDifference / (1000 * 60 * 60 * 24))
                if (govDaysBetween.equals(90))
                {
                    val schecduledDate = Calendar.getInstance()
                    schecduledDate.timeInMillis = governmentExpiry.time - (govDaysBetween * (1000 * 60 * 60 * 24))
                    newNotification(decryptedPersonalGovernment[i].id, schecduledDate.time, governmentExpiry, govIDName, boxName)
                }
            }
            catch (e: Exception){
                //AppLogger.d("Exception", "" + e.message)
                e.printStackTrace()
            }
        }
    }

    private fun personalHealthNotification() {
        val boxName = "Wellness"
        //AppLogger.d("AddNewNotification", "Decrypted Combine Wellness " + mDecryptedCombineWellness!!.eyeglassPrescriptionsItems)
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
                var eyeglassDaysBetween = (eyeglassDateDifference / (1000 * 60 * 60 * 24)).toInt()
                if (eyeglassDaysBetween == 330){
                    val schecduledDate = Calendar.getInstance()
                    schecduledDate.timeInMillis = eyeglassPrevious.time - (eyeglassDateDifference * (1000 * 60 * 60 * 24))
                    newNotification(decryptedEyeglassPrescriptions[i].id, schecduledDate.time, eyeglassPrevious, userName, boxName)
                }

            }catch(e : Exception){
                //AppLogger.d("Exception", " " + e.message)
                e.printStackTrace()
            }
        }
        var vitalMeasurement = ""
        for(i in 0 until decryptedVitalNumbers.size) {
            vitalMeasurement = decryptedVitalNumbers[i].measurementDate
            try {
                var vitalPrevious = dateFormat.parse(vitalMeasurement)
                var vitalDateDifference: Long = date.getTime() - vitalPrevious.getTime()
                var vitalDaysBetween = (vitalDateDifference / (1000 * 60 * 60 * 24)).toInt()
                if (vitalDaysBetween == 330) {
                    val schecduledDate = Calendar.getInstance()
                    schecduledDate.timeInMillis = vitalPrevious.time - (vitalDaysBetween * (1000 * 60 * 60 * 24))
                    newNotification(decryptedEyeglassPrescriptions[i].id, schecduledDate.time, vitalPrevious, userName, boxName)
                }

            }catch(e : Exception){
                //AppLogger.d("Exception", " " + e.message)
                e.printStackTrace()
            }
        }
    }

    private fun newNotification(id : Long, currentDate: Date, expiryDate: Date, subTitle: String, boxName: String) {
        //AppLogger.d("AddNewNotification", "Method invoked")
        addNotification(id, expiryDate, currentDate, subTitle, boxName)
        val mBuilder = NotificationCompat.Builder(context).setSmallIcon(logo_nine)
                .setContentTitle("NineBx").setContentText(subTitle)
        val mNotificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, mBuilder.build())
        /*  var intent : Intent = Intent(context, HomeActivity::class.java)
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
          notificationManager.notify(0, mNotifiction)*/
    }

    fun addNotification( id : Long, expirationDate: Date, date: Date, subTitle: String, box_Name : String) {
        //AppLogger.d("UpdateNotification", "Method invoked ")
        var notifications = Notifications()
        var message = "AndroidTest"
        notifications.id =  id
        notifications.message = message.encryptString()
        notifications.boxName = box_Name.encryptString()
        notifications.dueDate = expirationDate.toString()                                //dateFormat.format(expirationDate)
        notifications.subTitle = subTitle.encryptString()
        notifications.private = false
        notifications.created = box_Name + date
        notifications.read = false

       /* var sdfTime = SimpleDateFormat("dd/MM/yyy hh:mm")
        var myTime = sdfTime.parse("1/03/2018 15:50")
        var timeObj : Calendar = Calendar.getInstance()
        timeObj.time = myTime*/
        val scheduledCalendarTime = Calendar.getInstance()
        scheduledCalendarTime.time = date
        scheduledCalendarTime.set(Calendar.HOUR_OF_DAY, 0)
        scheduledCalendarTime.set(Calendar.MINUTE, 0)
        AlarmJob.scheduleNotificaiton(notifications, scheduledCalendarTime)

        prepareRealmConnections(context, false, Constants.REALM_END_POINT_NOTIFICATIONS, object : Realm.Callback(){
            override fun onSuccess(realm: Realm?) {
                //AppLogger.d("UpdatedNotification", "Connection successful")
                realm!!.beginTransaction()
                realm.copyToRealmOrUpdate(notifications)
                realm.commitTransaction()
                //AppLogger.d("NewNotification", "Added" )
            }
        })
    }
}