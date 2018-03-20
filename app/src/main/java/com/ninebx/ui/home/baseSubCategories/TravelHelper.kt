package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Parcelable
import com.ninebx.NineBxApplication
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.travel.CombineTravel
import com.ninebx.utility.*
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by venu on 20-03-2018.
 */
class TravelHelper(var category_name : String,
                   val categoryID: String,
                   var classType : String?,
                   var selectedDocument : Parcelable?,
                   val categoryView : Level2CategoryView) {

    private var decryptedLoyalty: DecryptedLoyalty? = null // decryptedLoyalty()
    private var decryptedTravel: DecryptedTravel? = null // DecryptedTravel()
    private var decryptedDocuments: DecryptedDocuments? = null // DecryptedTravel()
    private var decryptedVacations: DecryptedVacations? = null // DecryptedTravel()

    fun initialize() {
        if (selectedDocument == null) {
            return
        }

        when(classType) {
            DecryptedLoyalty::class.java.simpleName -> {
                decryptedLoyalty = selectedDocument as DecryptedLoyalty
            }
            DecryptedTravel::class.java.simpleName -> {
                decryptedTravel = selectedDocument as DecryptedTravel
            }
            DecryptedDocuments::class.java.simpleName -> {
                decryptedDocuments = selectedDocument as DecryptedDocuments
            }
            DecryptedVacations::class.java.simpleName -> {
                decryptedVacations = selectedDocument as DecryptedVacations
            }

        }
    }

    fun getFormForCategory() {
        when (category_name) {
            "Airline" -> {
                getAirline()
            }
            "Hotel" -> {
                getHotel()
            }
            "Car Rental" -> {
                getCarRental()
            }
            "Cruiseline" -> {
                getCruiseline()
            }
            "Railway" -> {
                getRailway()
            }
            "Other" -> {
                getOther()
            }
            "Passport" -> {
                getPassport()
            }
            "Visa" -> {
                getVisa()
            }
            "Other travel document" -> {
                getOtherTravelDocuments()
            }

        }
    }

    private fun getAirline() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedLoyalty == null) decryptedLoyalty = DecryptedLoyalty()
        var categoryIndex = 1001
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name on account", decryptedLoyalty!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedLoyalty!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2023
        category_id = "online_access" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedLoyalty!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedLoyalty!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedLoyalty!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedLoyalty!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2018
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedLoyalty!!.notes , "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getHotel() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedLoyalty == null) decryptedLoyalty = DecryptedLoyalty()
        var categoryIndex = 1002
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name on account", decryptedLoyalty!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedLoyalty!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2024
        category_id = "online_access" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedLoyalty!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedLoyalty!!.userName,  "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedLoyalty!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedLoyalty!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2024
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedLoyalty!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2024
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)

    }

    private fun getCarRental() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedLoyalty == null) decryptedLoyalty = DecryptedLoyalty()
        var categoryIndex = 1003
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name on account", decryptedLoyalty!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedLoyalty!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2025
        category_id = "online_access" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedLoyalty!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedLoyalty!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedLoyalty!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedLoyalty!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2025
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedLoyalty!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2025
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)

    }

    private fun getCruiseline() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedLoyalty == null) decryptedLoyalty = DecryptedLoyalty()
        var categoryIndex = 1004
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name on account", decryptedLoyalty!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedLoyalty!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2026
        category_id = "online_access" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedLoyalty!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedLoyalty!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedLoyalty!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedLoyalty!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2026
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedLoyalty!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2026
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)

    }

    private fun getRailway() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedLoyalty == null) decryptedLoyalty = DecryptedLoyalty()
        var categoryIndex = 1005
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name on account", decryptedLoyalty!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedLoyalty!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2027
        category_id = "online_access" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"

        category.subCategories.add(Level2SubCategory("Website", decryptedLoyalty!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedLoyalty!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedLoyalty!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedLoyalty!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2027
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedLoyalty!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2027
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)

    }

    private fun getOther() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedLoyalty == null) decryptedLoyalty = DecryptedLoyalty()
        var categoryIndex = 1006
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name on account", decryptedLoyalty!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedLoyalty!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2028
        category_id = "online_access" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedLoyalty!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedLoyalty!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedLoyalty!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedLoyalty!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2028
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedLoyalty!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2028
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)

    }

    private fun getPassport() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedDocuments == null) decryptedDocuments = DecryptedDocuments()
        var categoryIndex = 2001
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Passport Details"
        category.subCategories.add(Level2SubCategory("Name on passport", decryptedDocuments!!.nameOnPassport, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", decryptedDocuments!!.issuingCountry, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Passport number", decryptedDocuments!!.passportNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place issued", decryptedDocuments!!.placeIssued, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", decryptedDocuments!!.dateIssued, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", decryptedDocuments!!.expirationDate, "", Constants.LEVEL2_PICKER))
        categoryList.add(category)

        categoryIndex += 2029
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedDocuments!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2029
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getVisa() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedDocuments == null) decryptedDocuments = DecryptedDocuments()
        var categoryIndex = 2002
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Visa Details"
        category.subCategories.add(Level2SubCategory("Name on visa", decryptedDocuments!!.nameOnVisa, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", decryptedDocuments!!.issuingCountry, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Visa type", decryptedDocuments!!.visaType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Visa number", decryptedDocuments!!.visaNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place issued", decryptedDocuments!!.placeIssued, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", decryptedDocuments!!.placeIssued, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", decryptedDocuments!!.expirationDate, "", Constants.LEVEL2_PICKER))
        categoryList.add(category)

        categoryIndex += 2030
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedDocuments!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2030
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getOtherTravelDocuments() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedDocuments == null) decryptedDocuments = DecryptedDocuments()
        var categoryIndex = 2003
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Travel Document Details"
        category.subCategories.add(Level2SubCategory("Name on travel document", decryptedDocuments!!.nameOnTravelDocument, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", decryptedDocuments!!.issuingCountry, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Travel document type", decryptedDocuments!!.travelDocumentType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Travel document number", decryptedDocuments!!.travelDocumentNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place issued", decryptedDocuments!!.placeIssued, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", decryptedDocuments!!.dateIssued, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", decryptedDocuments!!.expirationDate, "", Constants.LEVEL2_PICKER))
        categoryList.add(category)

        categoryIndex += 2031
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedDocuments!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2031
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private var mCombine : Parcelable ?= null
    @SuppressLint("StaticFieldLeak")
    fun saveDocument(context: Context, combineItem: Parcelable?, title: String, subTitle: String) {
        mCombine = combineItem
        val currentUsers = NineBxApplication.getPreferences().userFirstName +" " +  NineBxApplication.getPreferences().userLastName
        val sdf = SimpleDateFormat(" E,MMM dd,yyyy, HH:mm")
        val currentDateandTime = sdf.format( Date())

        if (decryptedLoyalty != null) {
            decryptedLoyalty!!.selectionType = categoryID
            decryptedLoyalty!!.accountName = title

            if( decryptedLoyalty!!.created.isEmpty() )
                decryptedLoyalty!!.created = currentUsers + " " + currentDateandTime

            decryptedLoyalty!!.modified = currentUsers + " " + currentDateandTime

            if (decryptedLoyalty!!.id.toInt() == 0) {
                decryptedLoyalty!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val loyalty = encryptLoyalty(decryptedLoyalty!!)
                            realm.insertOrUpdate(loyalty)
                            realm.commitTransaction()
                            AppLogger.d("Adding ", " personal")
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val decryptedCombineTravel: DecryptedCombineTravel = mCombine as DecryptedCombineTravel
                            var realmloyalty = realm!!.where(CombineTravel::class.java).equalTo("id", decryptedCombineTravel .id).findFirst()
                            realm.beginTransaction()
                            if (realmloyalty == null) {
                                realmloyalty = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptLoyality(decryptedLoyalty!!)
                            /*if(realmDriversLicense!!.licenseItems.contains(encryptedObject)){
                                val index = realmDriversLicense!!.licenseItems.indexOf(encryptedObject)
                                if(index != -1){
                                    realmDriversLicense!!.licenseItems[index] = encryptedObject
                                }
                            }else{
                                realmDriversLicense!!.licenseItems.add(encryptLicense(decryptedDriversLicense!!))
                            }*/
                            realmloyalty!!.loyaltyItems.add(encryptLoyality(decryptedLoyalty!!))
                            realm.copyToRealmOrUpdate(realmloyalty)
                            AppLogger.d("Adding ", " Combine personal")
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedTravel != null) {
            decryptedTravel!!.selectionType = categoryID
            decryptedTravel!!.accountName = title
            if( decryptedTravel!!.created.isEmpty() )
                decryptedTravel!!.created = currentUsers + " " + currentDateandTime
            decryptedTravel!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedTravel!!.id.toInt() == 0) {
                decryptedTravel!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val social = encryptTravel(decryptedTravel!!)
                            realm.insertOrUpdate(social)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineTravel: DecryptedCombineTravel = mCombine as DecryptedCombineTravel
                            var realmTravel = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmTravel == null) {
                                realmTravel = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            /* val encryptedObject = encryptSocial(decryptedSocial!!)
                             if(realmSocial!!.socialItems.contains(encryptedObject)){
                                 val index = realmSocial!!.socialItems.indexOf(encryptedObject)
                                 if (index != -1){
                                     realmSocial!!.socialItems[index] = encryptedObject
                                 }
                             }else{
                                 realmSocial!!.socialItems.add(encryptSocial(decryptedSocial!!))
                             }*/
                            realmTravel!!.travelItems.add(encryptTravel(decryptedTravel!!))
                            realm.copyToRealmOrUpdate(realmTravel)
                            realm.commitTransaction()
                        }
                    })
                }
                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedTravel != null) {
            decryptedTravel!!.selectionType = categoryID
            decryptedTravel!!.accountName = title
            decryptedTravel!!.modified = currentUsers + " " + currentDateandTime
            if( decryptedTravel!!.created.isEmpty() )
                decryptedTravel!!.created = currentUsers + " " + currentDateandTime

            if (decryptedTravel!!.id.toInt() == 0) {
                decryptedTravel!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var documents = encryptTravel(decryptedTravel!!)
                            realm.insertOrUpdate(documents)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineTravel: DecryptedCombineTravel = mCombine as DecryptedCombineTravel
                            var realmTravelDocuments = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmTravelDocuments == null) {
                                realmTravelDocuments = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            /*  val encryptedObject = encryptTaxID(decryptedTAX_ID!!)
                              if(realmTaxID!!.taxIDItems.contains(encryptedObject)){
                                  val index = realmTaxID!!.taxIDItems.indexOf(encryptedObject)
                                  if(index != -1){
                                      realmTaxID!!.taxIDItems[index] = encryptedObject
                                  }
                              }else{
                                  realmTaxID!!.taxIDItems.add(encryptTaxID(decryptedTAX_ID!!))
                              }*/
                            realmTravelDocuments!!.travelItems.add(encryptTravel(decryptedTravel!!))
                            realm.insertOrUpdate(realmTravelDocuments)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }


        if (decryptedDocuments != null) {
            decryptedDocuments!!.selectionType = categoryID
            decryptedDocuments!!.nameOnTravelDocument = title
            if( decryptedDocuments!!.created.isEmpty() )
                decryptedDocuments!!.created = currentUsers + " " + currentDateandTime
            decryptedDocuments!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedDocuments!!.id.toInt() == 0) {
                decryptedDocuments!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val document = encryptDocuments(decryptedDocuments!!)
                            realm.insertOrUpdate(document)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineTravel: DecryptedCombineTravel = mCombine as DecryptedCombineTravel
                            var realmTravel = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmTravel == null) {
                                realmTravel = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            /* val encryptedObject = encryptSocial(decryptedSocial!!)
                             if(realmSocial!!.socialItems.contains(encryptedObject)){
                                 val index = realmSocial!!.socialItems.indexOf(encryptedObject)
                                 if (index != -1){
                                     realmSocial!!.socialItems[index] = encryptedObject
                                 }
                             }else{
                                 realmSocial!!.socialItems.add(encryptSocial(decryptedSocial!!))
                             }*/
                            realmTravel!!.documentsItems.add(encryptDocuments(decryptedDocuments!!))
                            realm.copyToRealmOrUpdate(realmTravel)
                            realm.commitTransaction()
                        }
                    })
                }
                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedVacations != null) {
            decryptedVacations!!.selectionType = categoryID
            decryptedVacations!!.createdUser = title
            decryptedVacations!!.modified = currentUsers + " " + currentDateandTime
            if( decryptedVacations!!.created.isEmpty() )
                decryptedVacations!!.created = currentUsers + " " + currentDateandTime

            if (decryptedVacations!!.id.toInt() == 0) {
                decryptedVacations!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var vacations = encryptVacations(decryptedVacations!!)
                            realm!!.insertOrUpdate(vacations)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineTravel: DecryptedCombineTravel = mCombine as DecryptedCombineTravel
                            var realmVacations = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmVacations == null) {
                                realmVacations = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            realmVacations!!.vacationsItems.add(encryptVacations(decryptedVacations!!))
                            realm.copyToRealmOrUpdate(realmVacations)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
    }
}