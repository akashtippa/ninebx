package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Parcelable
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.home.contacts.MainContacts
import com.ninebx.ui.base.realm.home.education.CombineEducation
import com.ninebx.ui.base.realm.home.education.Education
import com.ninebx.ui.base.realm.home.interests.CombineInterests
import com.ninebx.ui.base.realm.home.interests.Interests
import com.ninebx.ui.base.realm.home.memories.CombineMemories
import com.ninebx.ui.base.realm.home.memories.MainMemories
import com.ninebx.ui.base.realm.home.personal.CombinePersonal
import com.ninebx.ui.base.realm.home.personal.Personal
import com.ninebx.ui.base.realm.home.shopping.CombineShopping
import com.ninebx.ui.base.realm.home.shopping.Shopping
import com.ninebx.ui.base.realm.home.travel.CombineTravel
import com.ninebx.ui.base.realm.home.travel.Travel
import com.ninebx.ui.base.realm.home.wellness.CombineWellness
import com.ninebx.ui.base.realm.home.wellness.Wellness
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
                        val categoryView: Level2CategoryView,
                        val categoryInt: Int) {

    private var decryptedMainContacts: DecryptedMainContacts ?= null
    private var decryptedCombine: DecryptedCombine ?= null // need to check
    private var decryptedTravel: DecryptedTravel ?= null
    private var decryptedEducation: DecryptedEducation ?= null
    private var decryptedPersonal: DecryptedPersonal ?= null
    private var decryptedInterests: DecryptedInterests ?= null
    private var decryptedWellness: DecryptedWellness ?= null
    private var decryptedMemories: DecryptedMainMemories ?= null  // needs to be checked
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
                decryptedMemories = selectedDocument as DecryptedMainMemories
            }
            DecryptedShopping::class.java.simpleName -> {
                decryptedShopping = selectedDocument as DecryptedShopping
            }
        }
    }

    fun getFormForCategory() {
        when (category_name) {
            "Services/Other Accounts" -> {
                when(categoryInt) {
                    R.string.home_amp_money -> {  }
                    R.string.travel -> { getServicesOthersAccountsForTravel() }
                    R.string.contacts -> { getServicesOthersAccountsForContacts() }
                    R.string.education_work -> { getServicesOthersaccountsForEducation() }
                    R.string.personal -> { getServicesOthersAccountsForPersonal() }
                    R.string.interests -> { getServicesOthersAccountsForInterests() }
                    R.string.wellness -> { getServicesOthersAccountsForWellness() }
                    R.string.memories -> { getServicesOthersAccountsForMemories() }
                    R.string.shopping -> { getServicesOthersAccountsForShopping() }
                    else -> {  }
                }
            }
            "Other Attachments" -> {
                when(categoryInt) {
                    R.string.home_amp_money -> {  }
                    R.string.travel -> { }
                    R.string.contacts -> {  }
                    R.string.education -> {  }
                    R.string.interests -> {  }
                    R.string.wellness -> {  }
                    R.string.memories -> {  }
                    R.string.shopping -> {  }
                    else -> {  }
                }
            }
        }
    }

    private fun getServicesOthersAccountsForPersonal() {
        val categoryList = ArrayList<Level2Category>()
        if(decryptedPersonal == null) decryptedPersonal = DecryptedPersonal() //testing for contacts only
        var categoryIndex = 1001
        var category_id = "interest_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedPersonal!!.accountType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedPersonal!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedPersonal!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedPersonal!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedPersonal!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedPersonal!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedPersonal!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedPersonal!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedPersonal!!.pin, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Payment method on file", decryptedPersonal!!.paymentMethodOnFile, "", Constants.LEVEL2_SPINNER))

        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedPersonal!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedPersonal!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getServicesOthersAccountsForShopping() {
        val categoryList = ArrayList<Level2Category>()
        if(decryptedShopping == null) decryptedShopping = DecryptedShopping() //testing for contacts only
        var categoryIndex = 1001
        var category_id = "interest_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedShopping!!.accountType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedShopping!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedShopping!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedShopping!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedShopping!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedShopping!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedShopping!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedShopping!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedShopping!!.pin, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Payment method on file", decryptedShopping!!.paymentMethodOnFile, "", Constants.LEVEL2_SPINNER))

        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedShopping!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedShopping!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getServicesOthersAccountsForMemories() {
        val categoryList = ArrayList<Level2Category>()
        if(decryptedMemories == null) decryptedMemories = DecryptedMainMemories() //testing for contacts only
        var categoryIndex = 1001
        var category_id = "interest_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedMemories!!.accountType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedMemories!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedMemories!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedMemories!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedMemories!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedMemories!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedMemories!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedMemories!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedMemories!!.pin, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Payment method on file", decryptedMemories!!.paymentMethodOnFile, "", Constants.LEVEL2_SPINNER))

        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedMemories!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedMemories!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getServicesOthersAccountsForWellness() {
        val categoryList = ArrayList<Level2Category>()
        if(decryptedWellness == null) decryptedWellness = DecryptedWellness() //testing for contacts only
        var categoryIndex = 1001
        var category_id = "interest_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedWellness!!.accountType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedWellness!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedWellness!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedWellness!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedWellness!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedWellness!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedWellness!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedWellness!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedWellness!!.pin, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Payment method on file", decryptedWellness!!.paymentMethodOnFile, "", Constants.LEVEL2_SPINNER))

        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedWellness!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedWellness!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getServicesOthersAccountsForInterests() {
        val categoryList = ArrayList<Level2Category>()
        if(decryptedInterests == null) decryptedInterests = DecryptedInterests() //testing for contacts only
        var categoryIndex = 1001
        var category_id = "interest_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedInterests!!.accountType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedInterests!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedInterests!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedInterests!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedInterests!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedInterests!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedInterests!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedInterests!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedInterests!!.pin, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Payment method on file", decryptedInterests!!.paymentMethodOnFile, "", Constants.LEVEL2_SPINNER))

        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedInterests!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedInterests!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getServicesOthersaccountsForEducation() {
        val categoryList = ArrayList<Level2Category>()
        if(decryptedEducation == null) decryptedEducation = DecryptedEducation() //testing for contacts only
        var categoryIndex = 1001
        var category_id = "interest_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedEducation!!.accountType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedEducation!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedEducation!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedEducation!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedEducation!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedEducation!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedEducation!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedEducation!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedEducation!!.pin, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Payment method on file", decryptedEducation!!.paymentMethodOnFile, "", Constants.LEVEL2_SPINNER))

        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedEducation!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedEducation!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getServicesOthersAccountsForTravel() {
        val categoryList = ArrayList<Level2Category>()
        if(decryptedTravel == null) decryptedTravel = DecryptedTravel() //testing for contacts only
        var categoryIndex = 1001
        var category_id = "interest_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedTravel!!.accountType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedTravel!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedTravel!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedTravel!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedTravel!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedTravel!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedTravel!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedTravel!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedTravel!!.pin, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Payment method on file", decryptedTravel!!.paymentMethodOnFile, "", Constants.LEVEL2_SPINNER))

        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedTravel!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedTravel!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getServicesOthersAccountsForContacts() {
        val categoryList = ArrayList<Level2Category>()
        if(decryptedMainContacts == null) decryptedMainContacts = DecryptedMainContacts() //testing for contacts only
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
                when(categoryInt) {
                    R.string.home_amp_money -> {  }
                    R.string.travel -> { setServiceAccountsForTravel(level2SubCategory) }
                    R.string.contacts -> { setServiceAccountsForContacts(level2SubCategory) }
                    R.string.education_work -> { setServiceAccountsForEducation(level2SubCategory) }
                    R.string.personal -> { setServiceAccountsForPersonal(level2SubCategory) }
                    R.string.interests -> { setServiceAccountsForInterests(level2SubCategory) }
                    R.string.wellness -> { setServiceAccountsForWellness(level2SubCategory) }
                    R.string.memories -> { setServiceAccountsForMemories(level2SubCategory) }
                    R.string.shopping -> { setServiceAccountsForShopping(level2SubCategory) }
                    else -> {  }
                }
            }
            "Other Attachments" -> {

            }
        }
    }

    private fun setServiceAccountsForPersonal(level2SubCategory: Level2SubCategory) {
        when(level2SubCategory.title) {
            "Loan type", "Account type" -> {decryptedPersonal!!.accountType = level2SubCategory.titleValue}
            "Name(s) on account" -> {decryptedPersonal!!.nameOnAccount = level2SubCategory.titleValue}
            "Account number" -> {decryptedPersonal!!.accountNumber = level2SubCategory.titleValue}
            "Location" -> {decryptedPersonal!!.location = level2SubCategory.titleValue}
            "Contacts" -> {decryptedPersonal!!.contacts = level2SubCategory.titleValue}
            "Website" -> {decryptedPersonal!!.website = level2SubCategory.titleValue}
            "Username/login" -> {decryptedPersonal!!.userName = level2SubCategory.titleValue}
            "Password" -> {decryptedPersonal!!.password = level2SubCategory.titleValue}
            "PIN" -> {decryptedPersonal!!.pin = level2SubCategory.titleValue}
            "Account name" -> {decryptedPersonal!!.accountName = level2SubCategory.titleValue}
            "Payment method on file" -> { decryptedPersonal!!.paymentMethodOnFile = level2SubCategory.titleValue}
            else -> {
                when (level2SubCategory.type) {
                    Constants.LEVEL2_NOTES -> { decryptedPersonal!!.notes = level2SubCategory.titleValue}
                    Constants.LEVEL2_ATTACHMENTS -> { decryptedPersonal!!.attachmentNames = level2SubCategory.titleValue}
                }
            }
        }
    }

    private fun setServiceAccountsForShopping(level2SubCategory: Level2SubCategory) {
        when(level2SubCategory.title) {
            "Loan type", "Account type" -> {decryptedShopping!!.accountType = level2SubCategory.titleValue}
            "Name(s) on account" -> {decryptedShopping!!.nameOnAccount = level2SubCategory.titleValue}
            "Account number" -> {decryptedShopping!!.accountNumber = level2SubCategory.titleValue}
            "Location" -> {decryptedShopping!!.location = level2SubCategory.titleValue}
            "Contacts" -> {decryptedShopping!!.contacts = level2SubCategory.titleValue}
            "Website" -> {decryptedShopping!!.website = level2SubCategory.titleValue}
            "Username/login" -> {decryptedShopping!!.userName = level2SubCategory.titleValue}
            "Password" -> {decryptedShopping!!.password = level2SubCategory.titleValue}
            "PIN" -> {decryptedShopping!!.pin = level2SubCategory.titleValue}
            "Account name" -> {decryptedShopping!!.accountName = level2SubCategory.titleValue}
            "Payment method on file" -> { decryptedShopping!!.paymentMethodOnFile = level2SubCategory.titleValue}
            else -> {
                when (level2SubCategory.type) {
                    Constants.LEVEL2_NOTES -> { decryptedShopping!!.notes = level2SubCategory.titleValue}
                    Constants.LEVEL2_ATTACHMENTS -> { decryptedShopping!!.attachmentNames = level2SubCategory.titleValue}
                }
            }
        }
    }

    private fun setServiceAccountsForMemories(level2SubCategory: Level2SubCategory) {
        when(level2SubCategory.title) {
            "Loan type", "Account type" -> {decryptedMemories!!.accountType = level2SubCategory.titleValue}
            "Name(s) on account" -> {decryptedMemories!!.nameOnAccount = level2SubCategory.titleValue}
            "Account number" -> {decryptedMemories!!.accountNumber = level2SubCategory.titleValue}
            "Location" -> {decryptedMemories!!.location = level2SubCategory.titleValue}
            "Contacts" -> {decryptedMemories!!.contacts = level2SubCategory.titleValue}
            "Website" -> {decryptedMemories!!.website = level2SubCategory.titleValue}
            "Username/login" -> {decryptedMemories!!.userName = level2SubCategory.titleValue}
            "Password" -> {decryptedMemories!!.password = level2SubCategory.titleValue}
            "PIN" -> {decryptedMemories!!.pin = level2SubCategory.titleValue}
            "Account name" -> {decryptedMemories!!.accountName = level2SubCategory.titleValue}
            "Payment method on file" -> { decryptedMemories!!.paymentMethodOnFile = level2SubCategory.titleValue}
            else -> {
                when (level2SubCategory.type) {
                    Constants.LEVEL2_NOTES -> { decryptedMemories!!.notes = level2SubCategory.titleValue}
                    Constants.LEVEL2_ATTACHMENTS -> { decryptedMemories!!.attachmentNames = level2SubCategory.titleValue}
                }
            }
        }
    }

    private fun setServiceAccountsForWellness(level2SubCategory: Level2SubCategory) {
        when(level2SubCategory.title) {
            "Loan type", "Account type" -> {decryptedWellness!!.accountType = level2SubCategory.titleValue}
            "Name(s) on account" -> {decryptedWellness!!.nameOnAccount = level2SubCategory.titleValue}
            "Account number" -> {decryptedWellness!!.accountNumber = level2SubCategory.titleValue}
            "Location" -> {decryptedWellness!!.location = level2SubCategory.titleValue}
            "Contacts" -> {decryptedWellness!!.contacts = level2SubCategory.titleValue}
            "Website" -> {decryptedWellness!!.website = level2SubCategory.titleValue}
            "Username/login" -> {decryptedWellness!!.userName = level2SubCategory.titleValue}
            "Password" -> {decryptedWellness!!.password = level2SubCategory.titleValue}
            "PIN" -> {decryptedWellness!!.pin = level2SubCategory.titleValue}
            "Account name" -> {decryptedWellness!!.accountName = level2SubCategory.titleValue}
            "Payment method on file" -> { decryptedWellness!!.paymentMethodOnFile = level2SubCategory.titleValue}
            else -> {
                when (level2SubCategory.type) {
                    Constants.LEVEL2_NOTES -> { decryptedWellness!!.notes = level2SubCategory.titleValue}
                    Constants.LEVEL2_ATTACHMENTS -> { decryptedWellness!!.attachmentNames = level2SubCategory.titleValue}
                }
            }
        }
    }

    private fun setServiceAccountsForInterests(level2SubCategory: Level2SubCategory) {
        when(level2SubCategory.title) {
            "Loan type", "Account type" -> {decryptedInterests!!.accountType = level2SubCategory.titleValue}
            "Name(s) on account" -> {decryptedInterests!!.nameOnAccount = level2SubCategory.titleValue}
            "Account number" -> {decryptedInterests!!.accountNumber = level2SubCategory.titleValue}
            "Location" -> {decryptedInterests!!.location = level2SubCategory.titleValue}
            "Contacts" -> {decryptedInterests!!.contacts = level2SubCategory.titleValue}
            "Website" -> {decryptedInterests!!.website = level2SubCategory.titleValue}
            "Username/login" -> {decryptedInterests!!.userName = level2SubCategory.titleValue}
            "Password" -> {decryptedInterests!!.password = level2SubCategory.titleValue}
            "PIN" -> {decryptedInterests!!.pin = level2SubCategory.titleValue}
            "Account name" -> {decryptedInterests!!.accountName = level2SubCategory.titleValue}
            "Payment method on file" -> { decryptedInterests!!.paymentMethodOnFile = level2SubCategory.titleValue}
            else -> {
                when (level2SubCategory.type) {
                    Constants.LEVEL2_NOTES -> { decryptedInterests!!.notes = level2SubCategory.titleValue}
                    Constants.LEVEL2_ATTACHMENTS -> { decryptedInterests!!.attachmentNames = level2SubCategory.titleValue}
                }
            }
        }
    }

    private fun setServiceAccountsForEducation(level2SubCategory: Level2SubCategory) {
        when(level2SubCategory.title) {
            "Loan type", "Account type" -> {decryptedEducation!!.accountType = level2SubCategory.titleValue}
            "Name(s) on account" -> {decryptedEducation!!.nameOnAccount = level2SubCategory.titleValue}
            "Account number" -> {decryptedEducation!!.accountNumber = level2SubCategory.titleValue}
            "Location" -> {decryptedEducation!!.location = level2SubCategory.titleValue}
            "Contacts" -> {decryptedEducation!!.contacts = level2SubCategory.titleValue}
            "Website" -> {decryptedEducation!!.website = level2SubCategory.titleValue}
            "Username/login" -> {decryptedEducation!!.userName = level2SubCategory.titleValue}
            "Password" -> {decryptedEducation!!.password = level2SubCategory.titleValue}
            "PIN" -> {decryptedEducation!!.pin = level2SubCategory.titleValue}
            "Account name" -> {decryptedEducation!!.accountName = level2SubCategory.titleValue}
            "Payment method on file" -> { decryptedEducation!!.paymentMethodOnFile = level2SubCategory.titleValue}
            else -> {
                when (level2SubCategory.type) {
                    Constants.LEVEL2_NOTES -> { decryptedEducation!!.notes = level2SubCategory.titleValue}
                    Constants.LEVEL2_ATTACHMENTS -> { decryptedEducation!!.attachmentNames = level2SubCategory.titleValue}
                }
            }
        }
    }

    private fun setServiceAccountsForTravel(level2SubCategory: Level2SubCategory) { //for contacts only
        when(level2SubCategory.title) {
            "Loan type", "Account type" -> {decryptedTravel!!.accountType = level2SubCategory.titleValue}
            "Name(s) on account" -> {decryptedTravel!!.nameOnAccount = level2SubCategory.titleValue}
            "Account number" -> {decryptedTravel!!.accountNumber = level2SubCategory.titleValue}
            "Location" -> {decryptedTravel!!.location = level2SubCategory.titleValue}
            "Contacts" -> {decryptedTravel!!.contacts = level2SubCategory.titleValue}
            "Website" -> {decryptedTravel!!.website = level2SubCategory.titleValue}
            "Username/login" -> {decryptedTravel!!.userName = level2SubCategory.titleValue}
            "Password" -> {decryptedTravel!!.password = level2SubCategory.titleValue}
            "PIN" -> {decryptedTravel!!.pin = level2SubCategory.titleValue}
            "Account name" -> {decryptedTravel!!.accountName = level2SubCategory.titleValue}
            "Payment method on file" -> { decryptedTravel!!.paymentMethodOnFile = level2SubCategory.titleValue }
            else -> {
                when (level2SubCategory.type) {
                    Constants.LEVEL2_NOTES -> { decryptedTravel!!.notes = level2SubCategory.titleValue}
                    Constants.LEVEL2_ATTACHMENTS -> { decryptedTravel!!.attachmentNames = level2SubCategory.titleValue}
                }
            }
        }
    }

    private fun setServiceAccountsForContacts(level2SubCategory: Level2SubCategory) { //for contacts only
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
            "Payment method on file" -> { decryptedMainContacts!!.paymentMethodOnFile = level2SubCategory.titleValue}
            else -> {
                when (level2SubCategory.type) {
                    Constants.LEVEL2_NOTES -> { decryptedMainContacts!!.notes = level2SubCategory.titleValue}
                    Constants.LEVEL2_ATTACHMENTS -> { decryptedMainContacts!!.attachmentNames = level2SubCategory.titleValue}
                }
            }
        }
    }

    private var mCombine : Parcelable ?= null //contacts -> service accounts
    fun saveDocument(context: Context, combineItem: Parcelable?, title: String, subTitle: String) {

        mCombine = combineItem
        val currentUsers = NineBxApplication.getPreferences().userFirstName + " " + NineBxApplication.getPreferences().userLastName
        val sdf = SimpleDateFormat(" E,MMM dd,yyyy, HH:mm")
        val currentDateandTime = sdf.format(Date())

        when(categoryInt) {
            R.string.home_amp_money -> {  }
            R.string.travel -> { saveDecryptedTravel(title, subTitle, currentUsers, currentDateandTime, context) }
            R.string.contacts -> { saveDecryptedMainContacts(title, subTitle, currentUsers, currentDateandTime, context) }
            R.string.education_work -> { saveDecryptedEducationItems(title, subTitle, currentUsers, currentDateandTime, context) }
            R.string.personal -> { saveDecryptedPersonalItems(title, subTitle, currentUsers, currentDateandTime, context)}
            R.string.interests -> { saveDecryptedInterestItems(title, subTitle, currentUsers, currentDateandTime, context) }
            R.string.wellness -> { saveDecryptedWellnessItems(title, subTitle, currentUsers, currentDateandTime, context) }
            R.string.memories -> { saveDecryptedMainMemory(title, subTitle, currentUsers, currentDateandTime, context) }
            R.string.shopping -> { saveDecryptedShoppingItems(title, subTitle, currentUsers, currentDateandTime, context) }
            else -> {  }
        }

    }

    @SuppressLint("StaticFieldLeak")
    private fun saveDecryptedPersonalItems(title: String, subTitle: String, currentUsers: String, currentDateandTime: String, context: Context) {
        if(decryptedPersonal != null) {
            decryptedPersonal!!.selectionType = categoryID
            decryptedPersonal!!.institutionName = title
            decryptedPersonal!!.accountName = subTitle

            AppLogger.d("SelectionType ", "DecryptedPersonalItems" + decryptedPersonal!!.selectionType)
            if(decryptedPersonal!!.created.isEmpty()) {
                decryptedPersonal!!.created = currentUsers + " " + currentDateandTime
            }
            decryptedPersonal!!.modified = currentUsers + " " + currentDateandTime

            if( decryptedPersonal!!.id.toInt() == 0) {
                decryptedPersonal!!.id = getUniqueId()
                AppLogger.d("saveDocument", "id" + decryptedPersonal!!.id)
            }

            val combine: DecryptedCombinePersonal = mCombine as DecryptedCombinePersonal
            val index = combine.personalItems.indexOf(decryptedPersonal)
            if( index != -1 ) {
                combine.personalItems[index] = decryptedPersonal
            }
            else combine.personalItems.add(decryptedPersonal)
            mCombine = combine

            AppLogger.d("saveDocument", "Document Id " + decryptedPersonal!!.id)
            AppLogger.d("saveDocument", "Document : " + decryptedPersonal!!)


            var mainContacts: Personal?= null
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            mainContacts = encryptPersonal(decryptedPersonal!!)
                            realm.insertOrUpdate(mainContacts!!)
                            AppLogger.d("CombinePersonalItems ", "Inserted ")
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    //context.hideProgressDialog()
                    saveToCombinePersonalRealm(context, mainContacts)
                    //categoryView.savedToRealm(mCombine!!)
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)


        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveToCombinePersonalRealm(context: Context, mainContacts: Personal?) {
        object : AsyncTask<Void, Void, Unit>() {

            override fun doInBackground(vararg p0: Void?) {

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        AppLogger.d("saveDocument", "Combine Id " + mainContacts!!.id)
                        var combineRealm = realm!!.where(CombinePersonal::class.java).equalTo("id", mainContacts.id).findFirst()
                        realm.beginTransaction()
                        if (combineRealm == null) {
                            combineRealm = realm.createObject(CombinePersonal::class.java, mainContacts.id)
                        }
                        val encryptedObject = encryptPersonal(decryptedPersonal!!)
                        if (combineRealm!!.personalItems.contains(encryptedObject)) {
                            val index = combineRealm.personalItems.indexOf(encryptedObject)
                            if (index != -1) {
                                combineRealm.personalItems[index] = (encryptedObject)
                            }
                        } else {
                            combineRealm.personalItems.add(encryptedObject)
                        }
                        /*combine.financialItems.add( decryptedFinancial )
                        val encryptedCombine = encryptCombine(combine)*/
                        AppLogger.d("PERSONAL ITEMS ", "Combine personalItems " + encryptedObject.accountName + " " +
                                encryptedObject.institutionName + " " +encryptedObject.accountType)
                        realm.insertOrUpdate(combineRealm)
                        realm.commitTransaction()
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                context.hideProgressDialog()
                categoryView.savedToRealm(mCombine!!)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveDecryptedShoppingItems(title: String, subTitle: String, currentUsers: String, currentDateandTime: String, context: Context) {
        if(decryptedShopping != null) {
            decryptedShopping!!.selectionType = categoryID
            decryptedShopping!!.institutionName = title
            decryptedShopping!!.accountName = subTitle

            AppLogger.d("SelectionType ", "DecryptedShoppingItems" + decryptedShopping!!.selectionType)
            if(decryptedShopping!!.created.isEmpty()) {
                decryptedShopping!!.created = currentUsers + " " + currentDateandTime
            }
            decryptedShopping!!.modified = currentUsers + " " + currentDateandTime

            if( decryptedShopping!!.id.toInt() == 0) {
                decryptedShopping!!.id = getUniqueId()
                AppLogger.d("saveDocument", "id" + decryptedShopping!!.id)
            }

            val combine: DecryptedCombineShopping = mCombine as DecryptedCombineShopping
            val index = combine.shoppingItems.indexOf(decryptedShopping)
            if( index != -1 ) {
                combine.shoppingItems[index] = decryptedShopping
            }
            else combine.shoppingItems.add(decryptedShopping)
            mCombine = combine

            AppLogger.d("saveDocument", "Document Id " + decryptedShopping!!.id)
            AppLogger.d("saveDocument", "Document : " + decryptedShopping!!)


            var mainContacts: Shopping?= null
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            mainContacts = encryptShopping(decryptedShopping!!)
                            realm.insertOrUpdate(mainContacts!!)
                            AppLogger.d("CombineShoppingItems ", "Inserted ")
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    //context.hideProgressDialog()
                    saveToCombineShoppingRealm(context, mainContacts)
                    //categoryView.savedToRealm(mCombine!!)
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)


        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveToCombineShoppingRealm(context: Context, mainContacts: Shopping?) {
        object : AsyncTask<Void, Void, Unit>() {

            override fun doInBackground(vararg p0: Void?) {

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        AppLogger.d("saveDocument", "Combine Id " + mainContacts!!.id)
                        var combineRealm = realm!!.where(CombineShopping::class.java).equalTo("id", mainContacts.id).findFirst()
                        realm.beginTransaction()
                        if (combineRealm == null) {
                            combineRealm = realm.createObject(CombineShopping::class.java, mainContacts.id)
                        }
                        val encryptedObject = encryptShopping(decryptedShopping!!)
                        if (combineRealm!!.shoppingItems.contains(encryptedObject)) {
                            val index = combineRealm.shoppingItems.indexOf(encryptedObject)
                            if (index != -1) {
                                combineRealm.shoppingItems[index] = (encryptedObject)
                            }
                        } else {
                            combineRealm.shoppingItems.add(encryptedObject)
                        }
                        /*combine.financialItems.add( decryptedFinancial )
                        val encryptedCombine = encryptCombine(combine)*/
                        AppLogger.d("SHOPPING ITEMS ", "Combine shoppingItems " + encryptedObject.accountName + " " +
                                encryptedObject.institutionName + " " +encryptedObject.accountType)
                        realm.insertOrUpdate(combineRealm)
                        realm.commitTransaction()
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                context.hideProgressDialog()
                categoryView.savedToRealm(mCombine!!)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveDecryptedMainMemory(title: String, subTitle: String, currentUsers: String, currentDateandTime: String, context: Context) {
        if(decryptedMemories != null) {
            decryptedMemories!!.selectionType = categoryID
            decryptedMemories!!.institutionName = title
            decryptedMemories!!.accountName = subTitle

            AppLogger.d("SelectionType ", "DecryptedMemoriesItems" + decryptedMemories!!.selectionType)
            if(decryptedMemories!!.created.isEmpty()) {
                decryptedMemories!!.created = currentUsers + " " + currentDateandTime
            }
            decryptedMemories!!.modified = currentUsers + " " + currentDateandTime

            if( decryptedMemories!!.id.toInt() == 0) {
                decryptedMemories!!.id = getUniqueId()
                AppLogger.d("saveDocument", "id" + decryptedMemories!!.id)
            }

            val combine: DecryptedCombineMemories = mCombine as DecryptedCombineMemories
            val index = combine.mainMemoriesItems.indexOf(decryptedMemories)
            if( index != -1 ) {
                combine.mainMemoriesItems[index] = decryptedMemories
            }
            else combine.mainMemoriesItems.add(decryptedMemories)
            mCombine = combine

            AppLogger.d("saveDocument", "Document Id " + decryptedMemories!!.id)
            AppLogger.d("saveDocument", "Document : " + decryptedMemories!!)


            var mainContacts: MainMemories?= null
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            mainContacts = encryptMainMemories(decryptedMemories!!)
                            realm.insertOrUpdate(mainContacts!!)
                            AppLogger.d("CombineWellnessItems ", "Inserted ")
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    //context.hideProgressDialog()
                    saveToCombineMemoriesRealm(context, mainContacts)
                    //categoryView.savedToRealm(mCombine!!)
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)


        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveToCombineMemoriesRealm(context: Context, mainContacts: MainMemories?) {
        object : AsyncTask<Void, Void, Unit>() {

            override fun doInBackground(vararg p0: Void?) {

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        AppLogger.d("saveDocument", "Combine Id " + mainContacts!!.id)
                        var combineRealm = realm!!.where(CombineMemories::class.java).equalTo("id", mainContacts.id).findFirst()
                        realm.beginTransaction()
                        if (combineRealm == null) {
                            combineRealm = realm.createObject(CombineMemories::class.java, mainContacts.id)
                        }
                        val encryptedObject = encryptMemories(decryptedMemories!!)
                        if (combineRealm!!.mainMemoriesItems.contains(encryptedObject)) {
                            val index = combineRealm.mainMemoriesItems.indexOf(encryptedObject)
                            if (index != -1) {
                                combineRealm.mainMemoriesItems[index] = (encryptedObject)
                            }
                        } else {
                            combineRealm.mainMemoriesItems.add(encryptedObject)
                        }
                        /*combine.financialItems.add( decryptedFinancial )
                        val encryptedCombine = encryptCombine(combine)*/
                        AppLogger.d("MEMORY ITEMS ", "Combine mainMemoriesItems " + encryptedObject.accountName + " " +
                                encryptedObject.institutionName + " " +encryptedObject.accountType)
                        realm.insertOrUpdate(combineRealm)
                        realm.commitTransaction()
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                context.hideProgressDialog()
                categoryView.savedToRealm(mCombine!!)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveDecryptedWellnessItems(title: String, subTitle: String, currentUsers: String, currentDateandTime: String, context: Context) {
        if(decryptedWellness != null) {
            decryptedWellness!!.selectionType = categoryID
            decryptedWellness!!.institutionName = title
            decryptedWellness!!.accountName = subTitle

            AppLogger.d("SelectionType ", "DecryptedWellnessItems" + decryptedWellness!!.selectionType)
            if(decryptedWellness!!.created.isEmpty()) {
                decryptedWellness!!.created = currentUsers + " " + currentDateandTime
            }
            decryptedWellness!!.modified = currentUsers + " " + currentDateandTime

            if( decryptedWellness!!.id.toInt() == 0) {
                decryptedWellness!!.id = getUniqueId()
                AppLogger.d("saveDocument", "id" + decryptedWellness!!.id)
            }

            val combine: DecryptedCombineWellness = mCombine as DecryptedCombineWellness
            val index = combine.wellnessItems.indexOf(decryptedWellness)
            if( index != -1 ) {
                combine.wellnessItems[index] = decryptedWellness
            }
            else combine.wellnessItems.add(decryptedWellness)
            mCombine = combine

            AppLogger.d("saveDocument", "Document Id " + decryptedWellness!!.id)
            AppLogger.d("saveDocument", "Document : " + decryptedWellness!!)


            var mainContacts: Wellness?= null
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            mainContacts = encryptWellness(decryptedWellness!!)
                            realm.insertOrUpdate(mainContacts!!)
                            AppLogger.d("CombineWellnessItems ", "Inserted ")
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    //context.hideProgressDialog()
                    saveToCombineWellnessRealm(context, mainContacts)
                    //categoryView.savedToRealm(mCombine!!)
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)


        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveToCombineWellnessRealm(context: Context, mainContacts: Wellness?) {
        object : AsyncTask<Void, Void, Unit>() {

            override fun doInBackground(vararg p0: Void?) {

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        AppLogger.d("saveDocument", "Combine Id " + mainContacts!!.id)
                        var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", mainContacts.id).findFirst()
                        realm.beginTransaction()
                        if (combineRealm == null) {
                            combineRealm = realm.createObject(CombineWellness::class.java, mainContacts.id)
                        }
                        val encryptedObject = encryptWellness(decryptedWellness!!)
                        if (combineRealm!!.wellnessItems.contains(encryptedObject)) {
                            val index = combineRealm.wellnessItems.indexOf(encryptedObject)
                            if (index != -1) {
                                combineRealm.wellnessItems[index] = (encryptedObject)
                            }
                        } else {
                            combineRealm.wellnessItems.add(encryptedObject)
                        }
                        /*combine.financialItems.add( decryptedFinancial )
                        val encryptedCombine = encryptCombine(combine)*/
                        AppLogger.d("WELLNESS ITEMS ", "Combine wellnessItems " + encryptedObject.accountName + " " +
                                encryptedObject.institutionName + " " +encryptedObject.accountType)
                        realm.insertOrUpdate(combineRealm)
                        realm.commitTransaction()
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                context.hideProgressDialog()
                categoryView.savedToRealm(mCombine!!)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveDecryptedInterestItems(title: String, subTitle: String, currentUsers: String, currentDateandTime: String, context: Context) {
        if(decryptedInterests != null) {
            decryptedInterests!!.selectionType = categoryID
            decryptedInterests!!.institutionName = title
            decryptedInterests!!.accountName = subTitle

            AppLogger.d("SelectionType ", "DecryptedInterestItems" + decryptedInterests!!.selectionType)
            if(decryptedInterests!!.created.isEmpty()) {
                decryptedInterests!!.created = currentUsers + " " + currentDateandTime
            }
            decryptedInterests!!.modified = currentUsers + " " + currentDateandTime

            if( decryptedInterests!!.id.toInt() == 0) {
                decryptedInterests!!.id = getUniqueId()
                AppLogger.d("saveDocument", "id" + decryptedInterests!!.id)
            }

            val combine: DecryptedCombineInterests = mCombine as DecryptedCombineInterests
            val index = combine.interestItems.indexOf(decryptedInterests)
            if( index != -1 ) {
                combine.interestItems[index] = decryptedInterests
            }
            else combine.interestItems.add(decryptedInterests)
            mCombine = combine

            AppLogger.d("saveDocument", "Document Id " + decryptedInterests!!.id)
            AppLogger.d("saveDocument", "Document : " + decryptedInterests!!)


            var mainContacts: Interests?= null
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_INTERESTS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            mainContacts = encryptInterests(decryptedInterests!!)
                            realm.insertOrUpdate(mainContacts!!)
                            AppLogger.d("CombineInterestItems ", "Inserted ")
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    //context.hideProgressDialog()
                    saveToCombineInterestRealm(context, mainContacts)
                    //categoryView.savedToRealm(mCombine!!)
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)


        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveToCombineInterestRealm(context: Context, mainContacts: Interests?) {
        object : AsyncTask<Void, Void, Unit>() {

            override fun doInBackground(vararg p0: Void?) {

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_INTERESTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        AppLogger.d("saveDocument", "Combine Id " + mainContacts!!.id)
                        var combineRealm = realm!!.where(CombineInterests::class.java).equalTo("id", mainContacts.id).findFirst()
                        realm.beginTransaction()
                        if (combineRealm == null) {
                            combineRealm = realm.createObject(CombineInterests::class.java, mainContacts.id)
                        }
                        val encryptedObject = encryptInterests(decryptedInterests!!)
                        if (combineRealm!!.interestItems.contains(encryptedObject)) {
                            val index = combineRealm.interestItems.indexOf(encryptedObject)
                            if (index != -1) {
                                combineRealm.interestItems[index] = (encryptedObject)
                            }
                        } else {
                            combineRealm.interestItems.add(encryptedObject)
                        }
                        /*combine.financialItems.add( decryptedFinancial )
                        val encryptedCombine = encryptCombine(combine)*/
                        AppLogger.d("INTEREST ITEMS ", "Combine interestItems " + encryptedObject.accountName + " " +
                                encryptedObject.institutionName + " " +encryptedObject.accountType)
                        realm.insertOrUpdate(combineRealm)
                        realm.commitTransaction()
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                context.hideProgressDialog()
                categoryView.savedToRealm(mCombine!!)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveDecryptedEducationItems(title: String, subTitle: String, currentUsers: String, currentDateandTime: String, context: Context) {
        if(decryptedEducation != null) {
            decryptedEducation!!.selectionType = categoryID
            decryptedEducation!!.institutionName = title
            decryptedEducation!!.accountName = subTitle

            AppLogger.d("SelectionType ", "DecryptedEducation" + decryptedEducation!!.selectionType)
            if(decryptedEducation!!.created.isEmpty()) {
                decryptedEducation!!.created = currentUsers + " " + currentDateandTime
            }
            decryptedEducation!!.modified = currentUsers + " " + currentDateandTime

            if( decryptedEducation!!.id.toInt() == 0) {
                decryptedEducation!!.id = getUniqueId()
                AppLogger.d("saveDocument", "id" + decryptedEducation!!.id)
            }

            val combine: DecryptedCombineEducation = mCombine as DecryptedCombineEducation
            val index = combine.educationItems.indexOf(decryptedEducation)
            if( index != -1 ) {
                combine.educationItems[index] = decryptedEducation
            }
            else combine.educationItems.add(decryptedEducation)
            mCombine = combine

            AppLogger.d("saveDocument", "Document Id " + decryptedEducation!!.id)
            AppLogger.d("saveDocument", "Document : " + decryptedEducation!!)


            var mainContacts: Education?= null
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_EDUCATION, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            mainContacts = encryptEducation(decryptedEducation!!)
                            realm.insertOrUpdate(mainContacts!!)
                            AppLogger.d("CombineEducationItems ", "Inserted ")
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    //context.hideProgressDialog()
                    saveToCombineEducationRealm(context, mainContacts)
                    //categoryView.savedToRealm(mCombine!!)
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)


        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveToCombineEducationRealm(context: Context, mainContacts: Education?) {
        object : AsyncTask<Void, Void, Unit>() {

            override fun doInBackground(vararg p0: Void?) {

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_EDUCATION, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        AppLogger.d("saveDocument", "Combine Id " + mainContacts!!.id)
                        var combineRealm = realm!!.where(CombineEducation::class.java).equalTo("id", mainContacts.id).findFirst()
                        realm.beginTransaction()
                        if (combineRealm == null) {
                            combineRealm = realm.createObject(CombineEducation::class.java, mainContacts.id)
                        }
                        val encryptedObject = encryptEducation(decryptedEducation!!)
                        if (combineRealm!!.educationItems.contains(encryptedObject)) {
                            val index = combineRealm.educationItems.indexOf(encryptedObject)
                            if (index != -1) {
                                combineRealm.educationItems[index] = (encryptedObject)
                            }
                        } else {
                            combineRealm.educationItems.add(encryptedObject)
                        }
                        /*combine.financialItems.add( decryptedFinancial )
                        val encryptedCombine = encryptCombine(combine)*/
                        AppLogger.d("EDUCATON ITEMS ", "Combine educationItems " + encryptedObject.accountName + " " +
                                encryptedObject.institutionName + " " +encryptedObject.accountType)
                        realm.insertOrUpdate(combineRealm)
                        realm.commitTransaction()
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                context.hideProgressDialog()
                categoryView.savedToRealm(mCombine!!)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveDecryptedMainContacts(title: String, subTitle: String, currentUsers: String, currentDateandTime: String, context: Context) {
        if(decryptedMainContacts != null) {
            decryptedMainContacts!!.selectionType = categoryID
            decryptedMainContacts!!.institutionName = title
            decryptedMainContacts!!.accountName = subTitle

            AppLogger.d("SelectionType ", "DecryptedMainContacts" + decryptedMainContacts!!.selectionType)
            if(decryptedMainContacts!!.created.isEmpty()) {
                decryptedMainContacts!!.created = currentUsers + " " + currentDateandTime
            }
            decryptedMainContacts!!.modified = currentUsers + " " + currentDateandTime

            if( decryptedMainContacts!!.id.toInt() == 0) {
                decryptedMainContacts!!.id = getUniqueId()
                AppLogger.d("saveDocument", "id" + decryptedMainContacts!!.id)
            }

            val combine: DecryptedCombineContacts = mCombine as DecryptedCombineContacts
            val index = combine.mainContactsItems.indexOf(decryptedMainContacts)
            if( index != -1 ) {
                combine.mainContactsItems[index] = decryptedMainContacts
            }
            else combine.mainContactsItems.add(decryptedMainContacts)
            mCombine = combine

            AppLogger.d("saveDocument", "Document Id " + decryptedMainContacts!!.id)
            AppLogger.d("saveDocument", "Document : " + decryptedMainContacts!!)


            var mainContacts: MainContacts?= null
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            mainContacts = encryptMainContacts(decryptedMainContacts!!)
                            realm.insertOrUpdate(mainContacts!!)
                            AppLogger.d("CombineMainContacts ", "Inserted ")
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    //context.hideProgressDialog()
                    saveToCombineContactsRealm(context, mainContacts)
                    //categoryView.savedToRealm(mCombine!!)
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)


        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveToCombineContactsRealm(context: Context, mainContacts: MainContacts?) {
        object : AsyncTask<Void, Void, Unit>() {

            override fun doInBackground(vararg p0: Void?) {

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        AppLogger.d("saveDocument", "Combine Id " + mainContacts!!.id)
                        var combineRealm = realm!!.where(CombineContacts::class.java).equalTo("id", mainContacts.id).findFirst()
                        realm.beginTransaction()
                        if (combineRealm == null) {
                            combineRealm = realm.createObject(CombineContacts::class.java, mainContacts.id)
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
                context.hideProgressDialog()
                categoryView.savedToRealm(mCombine!!)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveDecryptedTravel(title: String, subTitle: String, currentUsers: String, currentDateandTime: String, context: Context) {
        if(decryptedTravel != null) {
            decryptedTravel!!.selectionType = categoryID
            decryptedTravel!!.institutionName = title
            decryptedTravel!!.accountName = subTitle

            AppLogger.d("SelectionType ", "DecryptedMainContacts" + decryptedTravel!!.selectionType)
            if(decryptedTravel!!.created.isEmpty()) {
                decryptedTravel!!.created = currentUsers + " " + currentDateandTime
            }
            decryptedTravel!!.modified = currentUsers + " " + currentDateandTime

            if( decryptedTravel!!.id.toInt() == 0) {
                decryptedTravel!!.id = getUniqueId()
                AppLogger.d("saveDocument", "id" + decryptedTravel!!.id)
            }

            val combine: DecryptedCombineTravel = mCombine as DecryptedCombineTravel
            val index = combine.travelItems.indexOf(decryptedTravel)
            if( index != -1 ) {
                combine.travelItems[index] = decryptedTravel
            }
            else combine.travelItems.add(decryptedTravel)
            mCombine = combine

            AppLogger.d("saveDocument", "Document Id " + decryptedTravel!!.id)
            AppLogger.d("saveDocument", "Document : " + decryptedTravel!!)


            var travelItems: Travel?= null
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            travelItems = encryptTravel(decryptedTravel!!)
                            realm.insertOrUpdate(travelItems!!)
                            AppLogger.d("CombineTravel ", "Inserted ")
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    //context.hideProgressDialog()
                    saveToCombineTravelRealm(context, travelItems)
                    //categoryView.savedToRealm(mCombine!!)
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)


        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveToCombineTravelRealm(context: Context, mainContacts: Travel?) {
        object : AsyncTask<Void, Void, Unit>() {

            override fun doInBackground(vararg p0: Void?) {

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        AppLogger.d("saveDocument", "Combine Id " + mainContacts!!.id)
                        var combineRealm = realm!!.where(CombineTravel::class.java).equalTo("id", mainContacts.id).findFirst()
                        realm.beginTransaction()
                        if (combineRealm == null) {
                            combineRealm = realm.createObject(CombineTravel::class.java, mainContacts.id)
                        }
                        val encryptedObject = encryptTravel(decryptedTravel!!)
                        if (combineRealm!!.travelItems.contains(encryptedObject)) {
                            val index = combineRealm.travelItems.indexOf(encryptedObject)
                            if (index != -1) {
                                combineRealm.travelItems[index] = (encryptedObject)
                            }
                        } else {
                            combineRealm.travelItems.add(encryptedObject)
                        }
                        /*combine.financialItems.add( decryptedFinancial )
                        val encryptedCombine = encryptCombine(combine)*/
                        AppLogger.d("TRAVEL ITEMS ", "Combine travelItems " + encryptedObject.accountName + " " +
                                encryptedObject.institutionName + " " +encryptedObject.accountType)
                        realm.insertOrUpdate(combineRealm)
                        realm.commitTransaction()
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                context.hideProgressDialog()
                categoryView.savedToRealm(mCombine!!)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }


}