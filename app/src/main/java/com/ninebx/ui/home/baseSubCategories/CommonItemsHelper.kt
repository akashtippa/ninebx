package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Parcelable
import com.ninebx.NineBxApplication
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.utility.*
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by venu on 21-03-2018.
 */
class CommonItemsHelper(var category_name: String,
                        val categoryID: String,
                        var classType: String?,
                        var selectedDocument: Parcelable?,
                        val categoryView: Level2CategoryView) {

    private var decryptedMainContacts: DecryptedMainContacts ?= null
    private var decryptedCombine: DecryptedCombine ?= null // need to check
    private var decryptedTravel: DecryptedTravel ?= null
    private var decryptedEducation: DecryptedEducation ?= null
    private var decryptedPersonal: DecryptedPersonal ?= null
    private var decryptedInterests: DecryptedInterests ?= null
    private var decryptedWellness: DecryptedWellness ?= null
    private var decryptedMemories: DecryptedMemoryTimeline ?= null  // needs to be checked
    private var decryptedShopping: DecryptedShopping ?= null

    fun initialize() {
        if (selectedDocument == null) {
            return
        }

        when (classType) {
            DecryptedMainContacts::class.java.simpleName -> {
                decryptedMainContacts = selectedDocument as DecryptedMainContacts
            }
            DecryptedCombine::class.java.simpleName -> {
                decryptedCombine = selectedDocument as DecryptedCombine
            }
            DecryptedTravel::class.java.simpleName -> {
                decryptedTravel = selectedDocument as DecryptedTravel
            }
            DecryptedEducation::class.java.simpleName -> {
                decryptedEducation = selectedDocument as DecryptedEducation
            }
            DecryptedPersonal::class.java.simpleName -> {
                decryptedPersonal = selectedDocument as DecryptedPersonal
            }
            DecryptedInterests::class.java.simpleName -> {
                decryptedInterests = selectedDocument as DecryptedInterests
            }
            DecryptedWellness::class.java.simpleName -> {
                decryptedWellness = selectedDocument as DecryptedWellness
            }
            DecryptedMemoryTimeline::class.java.simpleName -> {
                decryptedMemories = selectedDocument as DecryptedMemoryTimeline
            }
            DecryptedShopping::class.java.simpleName -> {
                decryptedShopping = selectedDocument as DecryptedShopping
            }
        }
    }

    fun getFormForCategory() {
        when (category_name) {
            "Services/Other Accounts" -> {
                getServicesOthersAccounts()
            }
            "Other Attachments" -> {}
        }
    }

    private fun getServicesOthersAccounts() {
        val categoryList = ArrayList<Level2Category>()
        if(decryptedMainContacts == null) decryptedMainContacts = DecryptedMainContacts() //testing for contacts only
        /*else if(decryptedCombine == null) decryptedCombine = DecryptedCombine()
        else if(decryptedEducation == null) decryptedEducation = DecryptedEducation()
        else if(decryptedPersonal == null) decryptedPersonal = DecryptedPersonal()
        else if(decryptedInterests == null) decryptedInterests = DecryptedInterests()
        else if(decryptedWellness == null) decryptedWellness = DecryptedWellness()
        else if(decryptedMemories == null) decryptedMemories = DecryptedMemoryTimeline()
        else if(decryptedShopping == null) decryptedShopping = DecryptedShopping()*/
        var categoryIndex = 1001
        var category_id = "interest_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedMainContacts!!.accountType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedMainContacts!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedMainContacts!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedMainContacts!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedMainContacts!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedMainContacts!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedMainContacts!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedMainContacts!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedMainContacts!!.pin, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Payment method on file", decryptedMainContacts!!.paymentMethodOnFile, "", Constants.LEVEL2_SPINNER))

        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedMainContacts!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedMainContacts!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    fun setValue(level2SubCategory: Level2SubCategory) {
        when (category_name) {
            "Services/Other Accounts" -> {
                setServiceAccounts(level2SubCategory)
            }
            "Other Attachments" -> {

            }
        }
    }

    fun setServiceAccounts(level2SubCategory: Level2SubCategory) { //for contacts only
        when(level2SubCategory.title) {
            "Loan type", "Account type" -> {decryptedMainContacts!!.accountType = level2SubCategory.titleValue}
            "Name(s) on account" -> {decryptedMainContacts!!.nameOnAccount = level2SubCategory.titleValue}
            "Account number" -> {decryptedMainContacts!!.accountNumber = level2SubCategory.titleValue}
            "Location" -> {decryptedMainContacts!!.location = level2SubCategory.titleValue}
            "Contacts" -> {decryptedMainContacts!!.contacts = level2SubCategory.titleValue}
            "Website" -> {decryptedMainContacts!!.website = level2SubCategory.titleValue}
            "Username/login" -> {decryptedMainContacts!!.userName = level2SubCategory.titleValue}
            "Password" -> {decryptedMainContacts!!.password = level2SubCategory.titleValue}
            "PIN" -> {decryptedMainContacts!!.pin = level2SubCategory.titleValue}
            "Account name" -> {decryptedMainContacts!!.accountName = level2SubCategory.titleValue}
            else -> {
                when (level2SubCategory.type) {
                    Constants.LEVEL2_NOTES -> { decryptedMainContacts!!.notes = level2SubCategory.titleValue}
                    Constants.LEVEL2_ATTACHMENTS -> { decryptedMainContacts!!.attachmentNames = level2SubCategory.titleValue}
                }
            }
        }
    }

    private var mCombine : Parcelable ?= null
    @SuppressLint("StaticFieldLeak")
    fun saveDocument(context: Context, combineItem: Parcelable?, title: String, subTitle: String) {
        mCombine = combineItem
        val currentUsers = NineBxApplication.getPreferences().userFirstName + " " + NineBxApplication.getPreferences().userLastName
        val sdf = SimpleDateFormat(" E,MMM dd,yyyy, HH:mm")
        val currentDateandTime = sdf.format(Date())

        if(decryptedMainContacts != null) {
            decryptedMainContacts!!.selectionType = categoryID
            decryptedMainContacts!!.institutionName = title
            decryptedMainContacts!!.accountName = subTitle

            AppLogger.d("SelectionType ", "DecryptedMainContacts" + decryptedMainContacts!!.selectionType)
            if(decryptedMainContacts!!.created.isEmpty()) {
                decryptedMainContacts!!.created = currentUsers + " " + currentDateandTime
            }
            decryptedMainContacts!!.modified = currentUsers + " " + currentDateandTime

            var isSaveComplete = false
            if( decryptedMainContacts!!.id.toInt() == 0) {
                decryptedMainContacts!!.id = getUniqueId()
                AppLogger.d("saveDocument", "id" + decryptedMainContacts!!.id)
            }

            AppLogger.d("saveDocument", "Document Id " + decryptedMainContacts!!.id)
            AppLogger.d("saveDocument", "Document : " + decryptedMainContacts!!)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val mainContacts = encryptMainContacts(decryptedMainContacts!!)
                            realm.insertOrUpdate(mainContacts)
                            AppLogger.d("CombineMainContacts ", "Inserted ")
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm(mCombine!!)
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombineContacts = mCombine as DecryptedCombineContacts
                            AppLogger.d("saveDocument", "Combine Id " + combine.id)
                            var combineRealm = realm!!.where(CombineContacts::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineContacts::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptMainContacts(decryptedMainContacts!!)
                            if (combineRealm!!.mainContactsItems.contains(encryptedObject)) {
                                val index = combineRealm.mainContactsItems.indexOf(encryptedObject)
                                if (index != -1) {
                                    combineRealm.mainContactsItems[index] = (encryptedObject)
                                }
                            } else {
                                combineRealm.mainContactsItems.add(encryptedObject)
                            }
                            /*combine.financialItems.add( decryptedFinancial )
                            val encryptedCombine = encryptCombine(combine)*/
                            AppLogger.d("MAINCONTACTS ", "Combine mainContact " + encryptedObject.accountName + " " +
                            encryptedObject.institutionName + " " +encryptedObject.accountType)
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                        context.hideProgressDialog()
                    } else {
                        categoryView.savedToRealm(mCombine!!)
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

    }

}