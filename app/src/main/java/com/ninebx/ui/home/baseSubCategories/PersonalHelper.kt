package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Parcelable
import com.ninebx.NineBxApplication
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.personal.CombinePersonal
import com.ninebx.utility.*
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by smrit on 19-03-2018.
 */
class PersonalHelper(var category_name : String,
                     val categoryID: String,
                     var classType : String?,
                     var selectedDocument : Parcelable?,
                     val categoryView : Level2CategoryView) {

    private var decryptedDriversLicense: DecryptedLicense? = null
    private var decryptedSocial: DecryptedSocial? = null
    private var decryptedTAX_ID: DecryptedTaxID? = null
    private var decryptedCertificate: DecryptedCertificate? = null
    private var decryptedOtherGovernment: DecryptedGovernment? = null

    fun initialize(){
        if( selectedDocument == null ) {
            return
        }
        when (classType) {

        // For Personal
            DecryptedLicense::class.java.simpleName -> {
                decryptedDriversLicense = selectedDocument as DecryptedLicense
            }
            DecryptedSocial::class.java.simpleName -> {
                decryptedSocial = selectedDocument as DecryptedSocial
            }
            DecryptedTaxID::class.java.simpleName -> {
                decryptedTAX_ID = selectedDocument as DecryptedTaxID
            }
            DecryptedCertificate::class.java.simpleName -> {
                decryptedCertificate = selectedDocument as DecryptedCertificate
            }
            DecryptedGovernment::class.java.simpleName -> {
                decryptedOtherGovernment = selectedDocument as DecryptedGovernment
            }
        }
    }
    fun getFormForCategory() {
        when (category_name) {
            "Drivers License" -> {
                getDriversLicense()
            }
            "Social Security Card" -> {
                getSocialSecurityCard()
            }

            "Tax ID" -> {
                getTaxID()
            }

            "Birth Certificate" -> {
                getBirthCertificate()
            }
            "Marriage Certificate" -> {
                getMarriageCertificate()
            }
            "Other Government-Issued ID" -> {
                getOtherGovernmentIssuedID()
            }
        }
    }

    private fun getDriversLicense() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedDriversLicense == null) decryptedDriversLicense = DecryptedLicense()
        var categoryIndex = 1001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on license", decryptedDriversLicense!!.nameOnLicense, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", decryptedDriversLicense!!.issuingCountry, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Issuing state", decryptedDriversLicense!!.issuingState, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("License number", decryptedDriversLicense!!.licenseNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", decryptedDriversLicense!!.dateIssued, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", decryptedDriversLicense!!.expirationDate, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
        categoryList.add(category)

        categoryIndex += 2018
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedDriversLicense!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getSocialSecurityCard() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedSocial == null) decryptedSocial = DecryptedSocial()
        var categoryIndex = 2001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on card", decryptedSocial!!.nameOnCard, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Social security number", decryptedSocial!!.socialSecurityNumber, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2019
        category_id = "social_security_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedSocial!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("Attachments", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }


    private fun getTaxID() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedTAX_ID == null) decryptedTAX_ID = DecryptedTaxID()
        var categoryIndex = 3001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on ID", decryptedTAX_ID!!.taxIdName, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", decryptedTAX_ID!!.issuingCountry, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Tax ID number", decryptedTAX_ID!!.taxIdNumber, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2020
        category_id = "tax_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedTAX_ID!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getOtherGovernmentIssuedID() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedOtherGovernment == null) decryptedOtherGovernment = DecryptedGovernment()
        var categoryIndex = 6001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on ID", decryptedOtherGovernment!!.nameOnId, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", decryptedOtherGovernment!!.issuingCountry, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Issuing state", decryptedOtherGovernment!!.issuingState, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("ID number",  decryptedOtherGovernment!!.idNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", decryptedOtherGovernment!!.dateIssued, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", decryptedOtherGovernment!!.expirationDate, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))

        categoryList.add(category)

        categoryIndex += 2022
        category_id = "other_certificate_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedOtherGovernment!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getMarriageCertificate() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedCertificate == null) decryptedCertificate = DecryptedCertificate()
        var categoryIndex = 5001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name 1 on certificate", decryptedCertificate!!.nameOneCertificate, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name 2 on certificate", decryptedCertificate!!.nameTwoCertificate, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date of marriage", decryptedCertificate!!.dateOfMarriage, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Place of marriage", decryptedCertificate!!.placeOfMarriage, Constants.KEYBOARD_PICKER, Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2020
        category_id = "birth_certificate_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedCertificate!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getBirthCertificate() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedCertificate == null) decryptedCertificate = DecryptedCertificate()
        var categoryIndex = 4001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on certificate", decryptedCertificate!!.nameOnCertificate, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Gender", decryptedCertificate!!.gender, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date of birth", decryptedCertificate!!.dateOfBirth, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Time of birth", decryptedCertificate!!.timeOfBirth, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place of birth", decryptedCertificate!!.placeOfBirth, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2020
        category_id = "birth_certificate_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedCertificate!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    fun setValue(level2Category: Level2SubCategory) {
        when (category_name) {
            "Drivers License" -> {
                setDriversLicense(level2Category)
            }
            "Social Security Card" -> {
                setSocialSecurityCard(level2Category)
            }

            "Tax ID" -> {
                setTaxID(level2Category)
            }

            "Birth Certificate" -> {
                setCertificate(level2Category)
            }
            "Marriage Certificate" -> {
                setCertificate(level2Category)
            }
            "Other Government-Issued ID" -> {
                setOtherGovernmentIssuedID(level2Category)
            }
        }
    }

    private fun setDriversLicense(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Description" -> decryptedDriversLicense!!.lic_description = level2Category.titleValue
            "Name on license" -> decryptedDriversLicense!!.nameOnLicense = level2Category.titleValue
            "Issuing country" -> decryptedDriversLicense!!.issuingCountry = level2Category.titleValue
            "License number" -> decryptedDriversLicense!!.licenseNumber = level2Category.titleValue
            "Date issued" -> decryptedDriversLicense!!.dateIssued = level2Category.titleValue
            "Expiration date" -> decryptedDriversLicense!!.expirationDate = level2Category.titleValue
            "Issuing state" -> decryptedDriversLicense!!.issuingState = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedDriversLicense!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedDriversLicense!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setSocialSecurityCard(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Card name" -> decryptedSocial!!.nameOnCard = level2Category.titleValue
            "Name on card" -> decryptedSocial!!.nameOnCard = level2Category.titleValue
            "Social security number" -> decryptedSocial!!.socialSecurityNumber = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedSocial!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedSocial!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setTaxID(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Tax ID name" -> decryptedTAX_ID!!.name = level2Category.titleValue
            "Name on ID" -> decryptedTAX_ID!!.taxIdName = level2Category.titleValue
            "Tax ID number" -> decryptedTAX_ID!!.taxIdNumber = level2Category.titleValue
            "Issuing country" -> decryptedTAX_ID!!.issuingCountry = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedTAX_ID!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedTAX_ID!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setCertificate(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Description" -> decryptedCertificate!!.cer_description = level2Category.titleValue
            "Name on certificate" -> decryptedCertificate!!.nameOnCertificate = level2Category.titleValue
            "Gender" -> decryptedCertificate!!.gender = level2Category.titleValue
            "Date of birth" -> decryptedCertificate!!.dateOfBirth = level2Category.titleValue
            "Time of birth" -> decryptedCertificate!!.timeOfBirth = level2Category.titleValue
            "Place of birth" -> decryptedCertificate!!.placeOfBirth = level2Category.titleValue
            "Name 1 on certificate" -> decryptedCertificate!!.nameOneCertificate = level2Category.titleValue
            "Name 2 on certificate" -> decryptedCertificate!!.nameTwoCertificate = level2Category.titleValue
            "Date of marriage" -> decryptedCertificate!!.dateOfMarriage = level2Category.titleValue
            "Place of marriage" -> decryptedCertificate!!.placeOfMarriage = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedCertificate!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedCertificate!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setOtherGovernmentIssuedID(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "ID name" -> decryptedOtherGovernment!!.idName = level2Category.titleValue
            "Name on ID" -> decryptedOtherGovernment!!.nameOnId = level2Category.titleValue
            "Issuing country" -> decryptedOtherGovernment!!.issuingCountry = level2Category.titleValue
            "ID number" -> decryptedOtherGovernment!!.idNumber = level2Category.titleValue
            "Date issued" -> decryptedOtherGovernment!!.dateIssued = level2Category.titleValue
            "Expiration date" -> decryptedOtherGovernment!!.expirationDate = level2Category.titleValue
            "Issuing state" -> decryptedOtherGovernment!!.issuingState = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedOtherGovernment!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedOtherGovernment!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private var mCombine : Parcelable ?= null
    @SuppressLint("StaticFieldLeak")
    fun saveDocument(context: Context, combineItem: Parcelable?, title: String, subTitle: String) {
        mCombine = combineItem
        val currentUsers = NineBxApplication.getPreferences().userFirstName +" " +  NineBxApplication.getPreferences().userLastName
        val sdf = SimpleDateFormat(" E,MMM dd,yyyy, HH:mm")
        val currentDateandTime = sdf.format( Date())

        if (decryptedDriversLicense != null) {
            decryptedDriversLicense!!.selectionType = categoryID
            decryptedDriversLicense!!.lic_description = title

            if( decryptedDriversLicense!!.created.isEmpty() )
                decryptedDriversLicense!!.created = currentUsers + " " + currentDateandTime

            decryptedDriversLicense!!.modified = currentUsers + " " + currentDateandTime

            if (decryptedDriversLicense!!.id.toInt() == 0) {
                decryptedDriversLicense!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val realmLicense = realm.where(CombinePersonal::class.java).equalTo("id", decryptedDriversLicense!!.id).findFirst()
                            val driversLicense = encryptLicense(decryptedDriversLicense!!)
                            realm.insertOrUpdate(driversLicense)
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
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombinePersonal = mCombine as DecryptedCombinePersonal
                            var combinePersonal1 = realm!!.where(CombinePersonal::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if (combinePersonal1 == null) {
                                combinePersonal1 = realm.createObject(CombinePersonal::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptLicense(decryptedDriversLicense!!)
                            if(combinePersonal1!!.licenseItems.contains(encryptedObject)){
                                val index = combinePersonal1!!.licenseItems.indexOf(encryptedObject)
                                if(index != -1){
                                    combinePersonal1!!.licenseItems[index] = encryptedObject
                                }
                            }else{
                                combinePersonal1!!.licenseItems.add(encryptLicense(decryptedDriversLicense!!))
                            }
                           // combinePersonal1!!.licenseItems.add(encryptLicense(decryptedDriversLicense!!))
                            realm.insertOrUpdate(combinePersonal1)
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

        if (decryptedSocial != null) {
            decryptedSocial!!.selectionType = categoryID
            decryptedSocial!!.cardName = title

            if( decryptedSocial!!.created.isEmpty() )
                decryptedSocial!!.created = currentUsers + " " + currentDateandTime
            decryptedSocial!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedSocial!!.id.toInt() == 0) {
                decryptedSocial!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val social = encryptSocial(decryptedSocial!!)
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
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combinePersonal: DecryptedCombinePersonal = mCombine as DecryptedCombinePersonal
                            var realmSocial = realm!!.where(CombinePersonal::class.java).equalTo("id", combinePersonal.id).findFirst()
                            realm.beginTransaction()
                            if (realmSocial == null) {
                                realmSocial = realm.createObject(CombinePersonal::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptSocial(decryptedSocial!!)
                            if(realmSocial!!.socialItems.contains(encryptedObject)){
                                val index = realmSocial!!.socialItems.indexOf(encryptedObject)
                                if (index != -1){
                                    realmSocial!!.socialItems[index] = encryptedObject
                                }
                            }else{
                                realmSocial!!.socialItems.add(encryptSocial(decryptedSocial!!))
                            }

                            realm.copyToRealmOrUpdate(realmSocial)
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

        if (decryptedTAX_ID != null) {
            decryptedTAX_ID!!.selectionType = categoryID
            decryptedTAX_ID!!.taxIdName = title
            decryptedTAX_ID!!.modified = currentUsers + " " + currentDateandTime
            if( decryptedTAX_ID!!.created.isEmpty() )
                decryptedTAX_ID!!.created = currentUsers + " " + currentDateandTime

            if (decryptedTAX_ID!!.id.toInt() == 0) {
                decryptedTAX_ID!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var taxID = encryptTaxID(decryptedTAX_ID!!)
                            realm.insertOrUpdate(taxID)
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
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combinePersonal: DecryptedCombinePersonal = mCombine as DecryptedCombinePersonal
                            var realmTaxID = realm!!.where(CombinePersonal::class.java).equalTo("id", combinePersonal.id).findFirst()
                            realm.beginTransaction()
                            if (realmTaxID == null) {
                                realmTaxID = realm.createObject(CombinePersonal::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptTaxID(decryptedTAX_ID!!)
                            if(realmTaxID!!.taxIDItems.contains(encryptedObject)){
                                val index = realmTaxID!!.taxIDItems.indexOf(encryptedObject)
                                if(index != -1){
                                    realmTaxID!!.taxIDItems[index] = encryptedObject
                                }
                            }else{
                                realmTaxID!!.taxIDItems.add(encryptTaxID(decryptedTAX_ID!!))
                            }
                            realm.insertOrUpdate(realmTaxID)
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

        if (decryptedOtherGovernment != null) {
            decryptedOtherGovernment!!.selectionType = categoryID
            decryptedOtherGovernment!!.idName = title
            decryptedOtherGovernment!!.modified = currentUsers + " " + currentDateandTime
            if( decryptedOtherGovernment!!.created.isEmpty() )
                decryptedOtherGovernment!!.created = currentUsers + " " + currentDateandTime

            if (decryptedOtherGovernment!!.id.toInt() == 0) {
                decryptedOtherGovernment!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var otherGovernment = encryptGovernment(decryptedOtherGovernment!!)
                            realm!!.insertOrUpdate(otherGovernment)
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
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combinePersonal: DecryptedCombinePersonal = mCombine as DecryptedCombinePersonal
                            var realmGovernment = realm!!.where(CombinePersonal::class.java).equalTo("id", combinePersonal.id).findFirst()
                            realm.beginTransaction()
                            if (realmGovernment == null) {
                                realmGovernment = realm.createObject(CombinePersonal::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptGovernment(decryptedOtherGovernment!!)
                            if(realmGovernment!!.governmentItems.contains(encryptedObject)){
                                val index = realmGovernment!!.governmentItems.indexOf(encryptedObject)
                                if(index != -1){
                                    realmGovernment!!.governmentItems[index] = encryptedObject
                                }
                            }else{
                                realmGovernment!!.governmentItems.add(encryptGovernment(decryptedOtherGovernment!!))
                            }
                            realm.copyToRealmOrUpdate(realmGovernment)
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

        if (decryptedCertificate != null) {
            decryptedCertificate!!.selectionType = categoryID
            decryptedCertificate!!.nameOnCertificate = title
            if( decryptedCertificate!!.created.isEmpty() )
                decryptedCertificate!!.created = currentUsers + " " + currentDateandTime
            decryptedCertificate!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedCertificate!!.id.toInt() == 0) {
                decryptedCertificate!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var certificate = encryptCertificate(decryptedCertificate!!)
                            realm.insertOrUpdate(certificate)
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
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combinePersonal: DecryptedCombinePersonal = mCombine as DecryptedCombinePersonal
                            var realmCertificate = realm!!.where(CombinePersonal::class.java).equalTo("id", combinePersonal.id).findFirst()
                            realm.beginTransaction()
                            if (realmCertificate == null) {
                                realmCertificate = realm.createObject(CombinePersonal::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptCertificate(decryptedCertificate!!)
                            if(realmCertificate!!.certificateItems.contains(decryptedCertificate)){
                                val index = realmCertificate!!.certificateItems.indexOf(encryptedObject)
                                if(index != -1){
                                    realmCertificate!!.certificateItems[index] = encryptedObject
                                }
                            }else{
                                realmCertificate!!.certificateItems.add(encryptCertificate(decryptedCertificate!!))
                            }
                            realm.copyToRealmOrUpdate(realmCertificate)
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