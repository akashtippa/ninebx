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
            "Travel Dates And Plans"-> {
                getTravelDatesAndPlans()
            }

        }
    }

    fun setValue(level2Category: Level2SubCategory) {
        when (category_name) {
            "Airline" -> {
                setLoyalty(level2Category)
            }
            "Hotel" -> {
                setLoyalty(level2Category)
            }
            "Car Rental" -> {
                setLoyalty(level2Category)
            }
            "Cruiseline" -> {
                setLoyalty(level2Category)
            }
            "Railway" -> {
                setLoyalty(level2Category)
            }
            "Other" -> {
                setLoyalty(level2Category)
            }
            "Passport" -> {
                setTravelDocuments(level2Category)
            }
            "Visa" -> {
                setTravelDocuments(level2Category)
            }
            "Other travel document" -> {
                setTravelDocuments(level2Category)
            }

            "Travel" -> {
                setTravelItems(level2Category)
            }
            "TravelInstitution" -> {
                setTravelItems(level2Category)
            }
            "Travel Dates And Plans" ->
            {
                setVacationItems(level2Category)
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
        category.subCategories.add(Level2SubCategory("Issuing country", decryptedDocuments!!.issuingCountry, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Passport number", decryptedDocuments!!.passportNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place issued", decryptedDocuments!!.placeIssued, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", decryptedDocuments!!.dateIssued, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", decryptedDocuments!!.expirationDate, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
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
        category.subCategories.add(Level2SubCategory("Issuing country", decryptedDocuments!!.issuingCountry, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Visa type", decryptedDocuments!!.visaType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Visa number", decryptedDocuments!!.visaNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place issued", decryptedDocuments!!.placeIssued, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", decryptedDocuments!!.dateIssued, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", decryptedDocuments!!.expirationDate, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
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
        category.subCategories.add(Level2SubCategory("Issuing country", decryptedDocuments!!.issuingCountry, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Travel document type", decryptedDocuments!!.travelDocumentType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Travel document number", decryptedDocuments!!.travelDocumentNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place issued", decryptedDocuments!!.placeIssued, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", decryptedDocuments!!.dateIssued, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", decryptedDocuments!!.expirationDate, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
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

    private fun getTravelDatesAndPlans() {
        val categoryList = ArrayList<Level2Category>()
        if( decryptedVacations == null ) decryptedVacations = DecryptedVacations()
        var categoryIndex = 3001
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Plans confirmed?", "", "", Constants.LEVEL2_SWITCH, decryptedVacations!!.plansConfirmed))
        category.subCategories.add(Level2SubCategory("Start date", decryptedVacations!!.startDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("End date", decryptedVacations!!.endDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Plans to visit/consider 1", decryptedVacations!!.placesToVisit_1, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Plans to visit/consider 2", decryptedVacations!!.placesToVisit_2, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Plans to visit/consider 3", decryptedVacations!!.placesToVisit_3, "", Constants.LEVEL2_LOCATION))
        categoryList.add(category)

        categoryIndex += 2035
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedVacations!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2035
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }


    private fun setLoyalty(level2Category: Level2SubCategory) {
        AppLogger.d("Level2Category", " " + level2Category)
        when (level2Category.title) {
            "Airline" -> decryptedLoyalty!!.airLine = level2Category.titleValue
            "Hotel" -> decryptedLoyalty!!.hotel = level2Category.titleValue
            "Car Rental Company" -> decryptedLoyalty!!.carRentalCompany = level2Category.titleValue
            "Cruiseline" -> decryptedLoyalty!!.cruiseline = level2Category.titleValue
            "Railway" -> decryptedLoyalty!!.railway = level2Category.titleValue
            "Account name" -> decryptedLoyalty!!.accountName = level2Category.titleValue
            "Other" -> decryptedLoyalty!!.other = level2Category.titleValue
            "Account name" -> decryptedLoyalty!!.accountName = level2Category.titleValue
            "Name on account" -> decryptedLoyalty!!.nameOnAccount = level2Category.titleValue
            "Account number" -> decryptedLoyalty!!.accountNumber = level2Category.titleValue
            "Website" -> decryptedLoyalty!!.website = level2Category.titleValue
            "Username/login" -> decryptedLoyalty!!.userName = level2Category.titleValue
            "Password" -> decryptedLoyalty!!.password = level2Category.titleValue
            "PIN" -> decryptedLoyalty!!.pin = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedLoyalty!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedLoyalty!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setTravelDocuments(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Passport name" -> decryptedDocuments!!.passportName = level2Category.titleValue
            "Name on passport" -> decryptedDocuments!!.nameOnPassport = level2Category.titleValue
            "Issuing country" -> decryptedDocuments!!.issuingCountry = level2Category.titleValue
            "Passport number" -> decryptedDocuments!!.passportNumber = level2Category.titleValue
            "Place issued" -> decryptedDocuments!!.placeIssued = level2Category.titleValue
            "Date issued" -> decryptedDocuments!!.dateIssued = level2Category.titleValue
            "Visa name" -> decryptedDocuments!!.visaName = level2Category.titleValue
            "Name on visa" -> decryptedDocuments!!.nameOnVisa = level2Category.titleValue
            "Visa type" -> decryptedDocuments!!.visaType = level2Category.titleValue
            "Visa number" -> decryptedDocuments!!.visaNumber = level2Category.titleValue
            "Travel document title" -> decryptedDocuments!!.travelDocumentTitle = level2Category.titleValue
            "Name on travel document" -> decryptedDocuments!!.nameOnTravelDocument = level2Category.titleValue
            "Travel document type" -> decryptedDocuments!!.travelDocumentType = level2Category.titleValue
            "Travel document number" -> decryptedDocuments!!.travelDocumentNumber = level2Category.titleValue
            "Expiration date" -> decryptedDocuments!!.expirationDate = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedDocuments!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedDocuments!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setVacationItems(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Description" -> decryptedVacations!!.vac_description = level2Category.titleValue
            "Start date" -> decryptedVacations!!.startDate = level2Category.titleValue
            "End date" -> decryptedVacations!!.endDate = level2Category.titleValue
            "Plans to visit/consider 1" -> decryptedVacations!!.placesToVisit_1 = level2Category.titleValue
            "Plans to visit/consider 2" -> decryptedVacations!!.placesToVisit_2 = level2Category.titleValue
            "Plans to visit/consider 3" -> decryptedVacations!!.placesToVisit_3 = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedVacations!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedVacations!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setTravelItems(level2Category: Level2SubCategory) {
        when(level2Category.title){
            "Institution name" -> decryptedTravel!!.institutionName = level2Category.titleValue
            "Account name" -> decryptedTravel!!.accountName = level2Category.titleValue
            "Account type" -> decryptedTravel!!.accountType = level2Category.titleValue
            "Name(s) on account" -> decryptedTravel!!.nameOnAccount = level2Category.titleValue

            "Location" -> decryptedTravel!!.location = level2Category.titleValue
            "SWIFT/other code" -> decryptedTravel!!.swiftCode = level2Category.titleValue
            "ABA routing number" -> decryptedTravel!!.abaRoutingNumber = level2Category.titleValue
            "Contacts" -> decryptedTravel!!.contacts = level2Category.titleValue
            "Account number" -> decryptedTravel!!.accountNumber = level2Category.titleValue

            "Website" -> decryptedTravel!!.website = level2Category.titleValue
            "Contacts" -> decryptedTravel!!.contacts = level2Category.titleValue
            "Username/login" -> decryptedTravel!!.userName = level2Category.titleValue
            "Password" -> decryptedTravel!!.password = level2Category.titleValue
            "PIN" -> decryptedTravel!!.pin = level2Category.titleValue
            "Payment method on file" -> decryptedTravel!!.paymentMethodOnFile = level2Category.titleValue
            "Notes" -> decryptedTravel!!.notes = level2Category.titleValue
            "Title" -> decryptedTravel!!.title = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedTravel!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedTravel!!.attachmentNames = level2Category.titleValue
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

        if (decryptedLoyalty != null) {
            decryptedLoyalty!!.selectionType = categoryID


            when( decryptedLoyalty!!.selectionType ) {
                "travel_1001" -> {
                    decryptedLoyalty!!.airLine = title
                }
                "travel_1002" -> {
                    decryptedLoyalty!!.hotel = title
                }
                "travel_1003" -> {
                    decryptedLoyalty!!.carRentalCompany = title
                }
                "travel_1004" -> {
                    decryptedLoyalty!!.cruiseline = title
                }
                "travel_1005" -> {
                    decryptedLoyalty!!.railway = title
                }
                "travel_1006" -> {
                    decryptedLoyalty!!.other = title
                }
            }

            decryptedLoyalty!!.accountName = subTitle

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
                            val index = decryptedCombineTravel.loyaltyItems.indexOf(decryptedLoyalty)
                            if( index != -1 ) {
                                decryptedCombineTravel.loyaltyItems[index] = decryptedLoyalty
                            }
                            else decryptedCombineTravel.loyaltyItems.add(decryptedLoyalty)
                            mCombine = decryptedCombineTravel
                            var realmloyalty = realm!!.where(CombineTravel::class.java).equalTo("id", decryptedCombineTravel .id).findFirst()
                            realm.beginTransaction()
                            if (realmloyalty == null) {
                                realmloyalty = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptLoyalty(decryptedLoyalty!!)
                            if(realmloyalty!!.loyaltyItems.contains(encryptedObject)){
                                val index = realmloyalty!!.loyaltyItems.indexOf(encryptedObject)
                                if(index != -1){
                                    realmloyalty!!.loyaltyItems[index] = encryptedObject
                                }
                            }else{
                                realmloyalty!!.loyaltyItems.add(encryptLoyalty(decryptedLoyalty!!))
                            }

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
                            val index = combineTravel.travelItems.indexOf(decryptedTravel)
                            if( index != -1 ) {
                                combineTravel.travelItems[index] = decryptedTravel
                            }
                            else combineTravel.travelItems.add(decryptedTravel)
                            mCombine = combineTravel
                            var realmTravel = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmTravel == null) {
                                realmTravel = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                             val encryptedObject = encryptTravel(decryptedTravel!!)
                             if(realmTravel!!.travelItems.contains(encryptedObject)){
                                 val index = realmTravel!!.travelItems.indexOf(encryptedObject)
                                 if (index != -1){
                                     realmTravel!!.travelItems[index] = encryptedObject
                                 }
                             }else{
                                 realmTravel!!.travelItems.add(encryptTravel(decryptedTravel!!))
                             }

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


        if (decryptedDocuments != null) {
            decryptedDocuments!!.selectionType = categoryID

            when(decryptedDocuments!!.selectionType){
                "travel_2001" -> {
                    decryptedDocuments!!.passportName = title
                }
                "travel_2002"->{
                    decryptedDocuments!!.visaName = title
                }
                "travel_2003" -> {
                    decryptedDocuments!!.travelDocumentTitle = title
                }
            }
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
                            val index = combineTravel.documentsItems.indexOf(decryptedDocuments)
                            if( index != -1 ) {
                                combineTravel.documentsItems[index] = decryptedDocuments
                            }
                            else combineTravel.documentsItems.add(decryptedDocuments)
                            mCombine = combineTravel
                            var realmTravel = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmTravel == null) {
                                realmTravel = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                             val encryptedObject = encryptDocuments(decryptedDocuments!!)
                             if(realmTravel!!.documentsItems.contains(encryptedObject)){
                                 val index = realmTravel!!.documentsItems.indexOf(encryptedObject)
                                 if (index != -1){
                                     realmTravel!!.documentsItems[index] = encryptedObject
                                 }
                             }else{
                                 realmTravel!!.documentsItems.add(encryptDocuments(decryptedDocuments!!))
                             }

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
            decryptedVacations!!.vac_description = title

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
                            val index = combineTravel.vacationsItems.indexOf(decryptedVacations)
                            if( index != -1 ) {
                                combineTravel.vacationsItems[index] = decryptedVacations
                            }
                            else combineTravel.vacationsItems.add(decryptedVacations)
                            mCombine = combineTravel
                            var realmVacations = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmVacations == null) {
                                realmVacations = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptVacations(decryptedVacations!!)
                            if(realmVacations!!.vacationsItems.contains(encryptedObject)){
                                val index = realmVacations!!.vacationsItems.indexOf(encryptedObject)
                                if (index != -1){
                                    realmVacations!!.vacationsItems[index] = encryptedObject
                                }
                            }else{
                                realmVacations!!.vacationsItems.add(encryptVacations(decryptedVacations!!))
                            }
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