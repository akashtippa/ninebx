package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Parcelable
import com.ninebx.NineBxApplication
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.utility.*
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alok on 16/03/18.
 */
class HomeHelper( var category_name : String,
                  val categoryID: String,
                  var classType : String?,
                  var selectedDocument : Parcelable?,
                  val categoryView : Level2CategoryView ) {

    private var decryptedFinancial: DecryptedFinancial? = null // DecryptedFinancial()
    private var decryptedPayment: DecryptedPayment? = null // DecryptedPayment()
    private var decryptedProperty: DecryptedProperty? = null // DecryptedProperty()
    private var decryptedVehicle: DecryptedVehicle? = null // DecryptedVehicle()
    private var decryptedAssets: DecryptedAsset? = null // DecryptedAsset()
    private var decryptedInsurance: DecryptedInsurance? = null // DecryptedInsurance()
    private var decryptedTaxes: DecryptedTax? = null // DecryptedTax()

    fun initialize() {
        if( selectedDocument == null ) {
            return
        }
        when (classType) {

            DecryptedFinancial::class.java.simpleName -> {
                decryptedFinancial = selectedDocument as DecryptedFinancial
            }

            DecryptedPayment::class.java.simpleName -> {
                decryptedPayment = selectedDocument as DecryptedPayment
            }

            DecryptedProperty::class.java.simpleName -> {
                decryptedProperty = selectedDocument as DecryptedProperty
            }

            DecryptedVehicle::class.java.simpleName -> {
                decryptedVehicle = selectedDocument as DecryptedVehicle
            }

            DecryptedAsset::class.java.simpleName -> {
                decryptedAssets = selectedDocument as DecryptedAsset
            }

            DecryptedInsurance::class.java.simpleName -> {
                decryptedInsurance = selectedDocument as DecryptedInsurance
            }

            DecryptedTax::class.java.simpleName -> {
                decryptedTaxes = selectedDocument as DecryptedTax
            }
        }
    }

    fun getFormForCategory() {
        when (category_name) {

        //Home&Money
            "Banking" -> {
                getBanking()
            }
            "Investments/Retirement" -> {
                getInvestments()
            }
            "Loans/Mortgages" -> {
                getLoadMortgages()
            }
            "Other financial accounts" -> {
                getOtherFinancialAccounts()
            }
            "Credit/Debit cards" -> {
                getCardDebitCardDetails()
            }
            "Other payment accounts" -> {
                getOtherPaymentAccounts()
            }
            "Primary home (owned)" -> {
                getPrimaryHomeOwned()
            }
            "Property (rented for own use)" -> {
                getPropertyRentedForOwnUse()
            }

            "Vacation home" -> {
                getVacationHome()
            }

            "Investment/Rental property" -> {
                getInvestmentRentalProperty()
            }

            "Vehicles" -> {
                getVehicles()
            }

            "Maintenance" -> {
                getMaintenance()
            }

            "Jewelry" -> {
                getJewelry()

            }
            "Art and collectibles" -> {
                getArtsAndCollectibles()
            }
            "Computers and electronics" -> {
                getComputerAndElectronics()
            }
            "Furniture" -> {
                getFurnitureDetails()
            }
            "Others" -> {
                getOtherDetails()
            }

            "Homeowners/Renters insurance" -> {
                gtHomeOwnerRentersInsurance()
            }
            "Auto insurance" -> {
                getAutoInsurance()
            }
            "Life insurance" -> {
                getLifeInsurance()
            }
            "Health insurance" -> {
                getHealthInsurance()
            }
            "Umbrella insurance" -> {
                getUmbrellaInsurance()
            }
            "Past returns" -> {
                getPastReturns()
            }
            "Returns to be filed" -> {
                getReturnsToBeFiled()
            }
        }
    }

    private fun getUmbrellaInsurance() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedInsurance == null) decryptedInsurance = DecryptedInsurance()
        var categoryIndex = 6005
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Policy Details"
        category.subCategories.add(Level2SubCategory("Policy number", decryptedInsurance!!.policyNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Policy effective date",  decryptedInsurance!!.policyEffectiveDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Policy expiration date",  decryptedInsurance!!.policyExpirationDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Contacts",  decryptedInsurance!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2036
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedInsurance!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedInsurance!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedInsurance!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedInsurance!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2006
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedInsurance!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedInsurance!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getHealthInsurance() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedInsurance == null) decryptedInsurance = DecryptedInsurance()
        var categoryIndex = 2036
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Policy Details"
        category.subCategories.add(Level2SubCategory("Policy number", decryptedInsurance!!.policyNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Policy effective date",  decryptedInsurance!!.policyEffectiveDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Policy expiration date",  decryptedInsurance!!.policyExpirationDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Contacts",  decryptedInsurance!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2036
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedInsurance!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedInsurance!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedInsurance!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedInsurance!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2006
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedInsurance!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedInsurance!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getLifeInsurance() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedInsurance == null) decryptedInsurance = DecryptedInsurance()
        var categoryIndex = 6003
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Policy Details"
        category.subCategories.add(Level2SubCategory("Policy number", decryptedInsurance!!.policyNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Policy effective date",  decryptedInsurance!!.policyEffectiveDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Policy expiration date",  decryptedInsurance!!.policyExpirationDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Contacts",  decryptedInsurance!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2036
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedInsurance!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedInsurance!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedInsurance!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedInsurance!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2006
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedInsurance!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedInsurance!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getAutoInsurance() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedInsurance == null) decryptedInsurance = DecryptedInsurance()
        var categoryIndex = 6002
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Policy Details"
        category.subCategories.add(Level2SubCategory("Policy number", decryptedInsurance!!.policyNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Policy effective date",  decryptedInsurance!!.policyEffectiveDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Policy expiration date",  decryptedInsurance!!.policyExpirationDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Contacts",  decryptedInsurance!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2036
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedInsurance!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedInsurance!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedInsurance!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedInsurance!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2006
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedInsurance!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedInsurance!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun gtHomeOwnerRentersInsurance() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedInsurance == null) decryptedInsurance = DecryptedInsurance()
        var categoryIndex = 6001
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Policy Details"
        category.subCategories.add(Level2SubCategory("Policy number", decryptedInsurance!!.policyNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Policy effective date",  decryptedInsurance!!.policyEffectiveDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Policy expiration date",  decryptedInsurance!!.policyExpirationDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Contacts",  decryptedInsurance!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2036
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedInsurance!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedInsurance!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedInsurance!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedInsurance!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2006
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedInsurance!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedInsurance!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getReturnsToBeFiled() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedTaxes == null) decryptedTaxes = DecryptedTax()
        var categoryIndex = 7002
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Tax year", decryptedTaxes!!.taxYear, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Taxpayer(s)", decryptedTaxes!!.taxPayer, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedTaxes!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2017
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedTaxes!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedTaxes!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getPastReturns() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedTaxes == null) decryptedTaxes = DecryptedTax()
        var categoryIndex = 7001
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Tax year", decryptedTaxes!!.taxYear, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Taxpayer(s)", decryptedTaxes!!.taxPayer, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedTaxes!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2017
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedTaxes!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedTaxes!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getOtherDetails() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedAssets == null) decryptedAssets = DecryptedAsset()
        var categoryIndex = 5005
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Asset Details"
        category.subCategories.add(Level2SubCategory("Estimated current market value", decryptedAssets!!.estimatedMarketValue, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Serial number", decryptedAssets!!.serialNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", decryptedAssets!!.purchaseDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Purchase price", decryptedAssets!!.purchasePrice, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedAssets!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2016
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedAssets!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedAssets!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getFurnitureDetails() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedAssets == null) decryptedAssets = DecryptedAsset()
        var categoryIndex = 5004
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Asset Details"
        category.subCategories.add(Level2SubCategory("Estimated current market value", decryptedAssets!!.estimatedMarketValue, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Serial number", decryptedAssets!!.serialNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", decryptedAssets!!.purchaseDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Purchase price", decryptedAssets!!.purchasePrice, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedAssets!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2015
        category_id = "furniture_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedAssets!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedAssets!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getComputerAndElectronics() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedAssets == null) decryptedAssets = DecryptedAsset()
        var categoryIndex = 5003
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Asset Details"
        category.subCategories.add(Level2SubCategory("Estimated current market value", decryptedAssets!!.estimatedMarketValue, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Serial number", decryptedAssets!!.serialNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", decryptedAssets!!.purchaseDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Purchase price", decryptedAssets!!.purchasePrice, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedAssets!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2014
        category_id = "computer_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedAssets!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedAssets!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getArtsAndCollectibles() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedAssets == null) decryptedAssets = DecryptedAsset()
        var categoryIndex = 5002
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Asset Details"
        category.subCategories.add(Level2SubCategory("Estimated current market value", decryptedAssets!!.estimatedMarketValue, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Serial number", decryptedAssets!!.serialNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", decryptedAssets!!.purchaseDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Purchase price", decryptedAssets!!.purchasePrice, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedAssets!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2013
        category_id = "art_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedAssets!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedAssets!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getJewelry() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedAssets == null) decryptedAssets = DecryptedAsset()
        var categoryIndex = 5001
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Asset Details"
        category.subCategories.add(Level2SubCategory("Estimated current market value", decryptedAssets!!.estimatedMarketValue, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Serial number", decryptedAssets!!.serialNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", decryptedAssets!!.purchaseDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Purchase price", decryptedAssets!!.purchasePrice, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedAssets!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2012
        category_id = "jewelry_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedAssets!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedAssets!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getVehicles() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedVehicle == null) decryptedVehicle = DecryptedVehicle()
        var categoryIndex = 4001
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Vehicle Details"
        category.subCategories.add(Level2SubCategory("Vehicle identification number (VIN)", decryptedVehicle!!.vehicle, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Make", decryptedVehicle!!.make, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Model", decryptedVehicle!!.model, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Model year", decryptedVehicle!!.modelYear, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Color", decryptedVehicle!!.color, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name on title", decryptedVehicle!!.titleName, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Estimated market value", decryptedVehicle!!.estimatedMarketValue, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Registration expiration date", decryptedVehicle!!.registrationExpirydate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Purchased", decryptedVehicle!!.purchasedOrLeased, "", Constants.LEVEL2_RADIO))
        category.subCategories.add(Level2SubCategory("Purchase date", decryptedVehicle!!.purchaseDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Lease start date", decryptedVehicle!!.purchaseDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Lease end date", decryptedVehicle!!.purchaseDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Financed through loan", decryptedVehicle!!.financedThroughLoan, "", Constants.LEVEL2_SWITCH))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedVehicle!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2011
        category_id = "vehicle_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedVehicle!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedVehicle!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getInvestmentRentalProperty() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedProperty == null) decryptedProperty = DecryptedProperty()
        var categoryIndex = 3004
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Property Address"
        category.subCategories.add(Level2SubCategory("Street address 1", decryptedProperty!!.streetAddressOne, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Street address 2", decryptedProperty!!.streetAddressTwo, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("City", decryptedProperty!!.city, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("State", decryptedProperty!!.state, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Zip Code", decryptedProperty!!.zipCode, "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Country", decryptedProperty!!.country, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2010
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Property Details"
        category.subCategories.add(Level2SubCategory("Name(s) on title", decryptedProperty!!.propertyName, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Purchase date", decryptedProperty!!.purchaseDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Purchase price", decryptedProperty!!.purchasePrice, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Estimated market value", decryptedProperty!!.estimatedMarketValue, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedProperty!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2010
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Rental Details"
        category.subCategories.add(Level2SubCategory("Currently rented", "", "", Constants.LEVEL2_SWITCH, decryptedProperty!!.currentlyRented))
        category.subCategories.add(Level2SubCategory("Name of tenant", decryptedProperty!!.tenantName, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Lease start date", decryptedProperty!!.leaseStartDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Lease end date", decryptedProperty!!.leaseEndDate, "", Constants.LEVEL2_PICKER))
        categoryList.add(category)

        categoryIndex += 2010
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedProperty!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedProperty!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getVacationHome() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedProperty == null) decryptedProperty = DecryptedProperty()
        var categoryIndex = 3003
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Property Address"
        category.subCategories.add(Level2SubCategory("Street address 1", decryptedProperty!!.streetAddressOne, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Street address 2", decryptedProperty!!.streetAddressTwo, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("City", decryptedProperty!!.city, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("State", decryptedProperty!!.state, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Zip Code", decryptedProperty!!.zipCode, "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Country", decryptedProperty!!.country, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2009
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Property Details"
        category.subCategories.add(Level2SubCategory("Name(s) on title", decryptedProperty!!.propertyName, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Purchase date", decryptedProperty!!.purchaseDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Purchase price", decryptedProperty!!.purchasePrice, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Estimated market value", decryptedProperty!!.estimatedMarketValue, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedProperty!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2009
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedProperty!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedProperty!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getPropertyRentedForOwnUse() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedProperty == null) decryptedProperty = DecryptedProperty()
        var categoryIndex = 3002
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Property Address"
        category.subCategories.add(Level2SubCategory("Street address 1", decryptedProperty!!.streetAddressOne, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Street address 2", decryptedProperty!!.streetAddressTwo, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("City", decryptedProperty!!.city, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("State", decryptedProperty!!.state, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Zip Code", decryptedProperty!!.zipCode, "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Country", decryptedProperty!!.country, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2008
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Rental Details"
        category.subCategories.add(Level2SubCategory("Name of landlord", decryptedProperty!!.tenantName, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Lease start date", decryptedProperty!!.leaseStartDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Lease end date", decryptedProperty!!.leaseEndDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedProperty!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2008
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedProperty!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedProperty!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getPrimaryHomeOwned() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedProperty == null) decryptedProperty = DecryptedProperty()
        var categoryIndex = 3001
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Property Address"
        category.subCategories.add(Level2SubCategory("Street address 1", decryptedProperty!!.streetAddressOne, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Street address 2", decryptedProperty!!.streetAddressTwo, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("City", decryptedProperty!!.city, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("State", decryptedProperty!!.state, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Zip Code", decryptedProperty!!.zipCode, "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Country", decryptedProperty!!.country, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2007
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Property Details"
        category.subCategories.add(Level2SubCategory("Name(s) on title", decryptedProperty!!.titleName, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Purchase date", decryptedProperty!!.purchaseDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Purchase price", decryptedProperty!!.purchasePrice, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Estimated market value", decryptedProperty!!.estimatedMarketValue, "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedProperty!!.contacts, "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2007
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedProperty!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedProperty!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getOtherPaymentAccounts() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedFinancial == null) decryptedFinancial = DecryptedFinancial()
        var categoryIndex = 2002
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedFinancial!!.accountType, Constants.OTHER_ACCOUNT_TYPE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedFinancial!!.accountName, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedFinancial!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedFinancial!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedFinancial!!.contacts, Constants.CONTACT_SPINNER, Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2006
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedFinancial!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedFinancial!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedFinancial!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedFinancial!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2006
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedFinancial!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedFinancial!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getCardDebitCardDetails() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedPayment == null) decryptedPayment = DecryptedPayment()
        var categoryIndex = 2001
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Card Details"
        category.subCategories.add(Level2SubCategory("Card number", decryptedPayment!!.cardNumber, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Card type", decryptedPayment!!.cardType, Constants.KEYBOARD_SPINNER, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Card holder", decryptedPayment!!.cardHolder, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Expiry date", decryptedPayment!!.expiryDate, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("CVV code", decryptedPayment!!.cvvCode, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Card PIN", decryptedPayment!!.cardPin, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Issuing bank", decryptedPayment!!.issuingBank, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2005
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedPayment!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedPayment!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedPayment!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedPayment!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2005
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedPayment!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedPayment!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getOtherFinancialAccounts() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedFinancial == null) decryptedFinancial = DecryptedFinancial()
        AppLogger.d("Document", "Details : " + decryptedFinancial.toString())
        var categoryIndex = 1004
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedFinancial!!.accountType, Constants.BANK_ACCOUNT_TYPE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedFinancial!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedFinancial!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedFinancial!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("SWIFT/other code", decryptedFinancial!!.swiftCode, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("ABA routing number", decryptedFinancial!!.abaRoutingNumber, "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedFinancial!!.contacts, Constants.CONTACT_SPINNER, Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2004
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedFinancial!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedFinancial!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedFinancial!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedFinancial!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2004
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedFinancial!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedFinancial!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)
        categoryView.onSuccess(categoryList)
    }

    private fun getLoadMortgages() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedFinancial == null) decryptedFinancial = DecryptedFinancial()
        var categoryIndex = 1003
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Loan type", decryptedFinancial!!.accountType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedFinancial!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedFinancial!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedFinancial!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedFinancial!!.contacts, Constants.CONTACT_SPINNER, Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2003
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedFinancial!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedFinancial!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedFinancial!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedFinancial!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2003
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedFinancial!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedFinancial!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getMaintenance() {

        val categoryList = ArrayList<Level2Category>()
        if (decryptedVehicle == null) decryptedVehicle = DecryptedVehicle()
        var categoryIndex = 4002
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Service Details"
        category.subCategories.add(Level2SubCategory("Name of service provider", decryptedVehicle!!.maintenanceEvent, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date of service", decryptedVehicle!!.dateOfService, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedVehicle!!.contacts, Constants.CONTACT_SPINNER, Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedVehicle!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedVehicle!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun setAssets(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Estimated current market value" -> decryptedAssets!!.estimatedMarketValue = level2Category.titleValue //, "", Constants.LEVEL2_USD))
            "Serial number" -> decryptedAssets!!.serialNumber = level2Category.titleValue //, "", Constants.LEVEL2_NORMAL))
            "Purchase date" -> decryptedAssets!!.purchaseDate = level2Category.titleValue //, "", Constants.LEVEL2_PICKER))
            "Purchase price" -> decryptedAssets!!.purchasePrice = level2Category.titleValue //, "", Constants.LEVEL2_USD))
            "Contacts" -> decryptedAssets!!.contacts = level2Category.titleValue //, "", Constants.LEVEL2_SPINNER))
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedAssets!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedAssets!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setMaintenance(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Name of service provider" -> decryptedVehicle!!.maintenanceEvent = level2Category.titleValue
            "Date of service" -> decryptedVehicle!!.dateOfService = level2Category.titleValue
            "Contacts" -> decryptedVehicle!!.contacts = level2Category.titleValue//stants.LEVEL2_SPINNER))
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedVehicle!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedVehicle!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setVehicles(level2Category: Level2SubCategory) {
        when (level2Category.titleValue) {
            "Vehicle identification number (VIN)" -> decryptedVehicle!!.vehicle = level2Category.titleValue//stants.LEVEL2_NORMAL))
            "Make" -> decryptedVehicle!!.make = level2Category.titleValue//stants.LEVEL2_NORMAL))
            "Model" -> decryptedVehicle!!.model = level2Category.titleValue//stants.LEVEL2_NORMAL))
            "Model year" -> decryptedVehicle!!.modelYear = level2Category.titleValue//stants.LEVEL2_NORMAL))
            "Color" -> decryptedVehicle!!.color = level2Category.titleValue//stants.LEVEL2_NORMAL))
            "Name on title" -> decryptedVehicle!!.titleName = level2Category.titleValue//stants.LEVEL2_SPINNER))
            "Estimated market value" -> decryptedVehicle!!.estimatedMarketValue = level2Category.titleValue//stants.LEVEL2_USD))
            "Registration expiration date" -> decryptedVehicle!!.registrationExpirydate = level2Category.titleValue//stants.LEVEL2_PICKER))
            "Purchased" -> decryptedVehicle!!.purchasedOrLeased = level2Category.titleValue//stants.LEVEL2_RADIO))
            "Purchase date" -> decryptedVehicle!!.purchaseDate = level2Category.titleValue//stants.LEVEL2_PICKER))
            "Lease start date" -> decryptedVehicle!!.leaseStartDate = level2Category.titleValue//stants.LEVEL2_PICKER))
            "Lease end date" -> decryptedVehicle!!.leaseEndDate = level2Category.titleValue//stants.LEVEL2_PICKER))
            "Financed through loan" -> decryptedVehicle!!.financedThroughLoan = level2Category.titleValue//stants.LEVEL2_SWITCH))
            "Contacts" -> decryptedVehicle!!.contacts = level2Category.titleValue//stants.LEVEL2_SPINNER))
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedVehicle!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedVehicle!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setProperty(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Name of tenant", "Name of landlord" -> decryptedProperty!!.tenantName = level2Category.titleValue
            "Street address 1" -> decryptedProperty!!.streetAddressOne = level2Category.titleValue
            "Street address 2" -> decryptedProperty!!.streetAddressTwo = level2Category.titleValue
            "City" -> decryptedProperty!!.city = level2Category.titleValue//.LEVEL2_NORMAL))
            "State" -> decryptedProperty!!.state = level2Category.titleValue//.LEVEL2_NORMAL))
            "Zip Code" -> decryptedProperty!!.zipCode = level2Category.titleValue//.LEVEL2_NUMBER))
            "Country" -> decryptedProperty!!.country = level2Category.titleValue//.LEVEL2_NORMAL))
            "Currently rented" -> decryptedProperty!!.currentlyRented = level2Category.isValueSet

            "Name(s) on title" -> decryptedProperty!!.propertyName = level2Category.titleValue//.LEVEL2_SPINNER))
            "Purchase date" -> decryptedProperty!!.purchaseDate = level2Category.titleValue//.LEVEL2_PICKER))
            "Purchase price" -> decryptedProperty!!.purchasePrice = level2Category.titleValue//.LEVEL2_USD))
            "Estimated market value" -> decryptedProperty!!.estimatedMarketValue = level2Category.titleValue//.LEVEL2_USD))
            "Contacts" -> decryptedProperty!!.contacts = level2Category.titleValue//.LEVEL2_SPINNER))

            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedProperty!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedProperty!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setCardDebitCardDetails(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Card number" -> decryptedPayment!!.cardNumber = level2Category.titleValue
            "Card type" -> decryptedPayment!!.cardType = level2Category.titleValue
            "Card holder" -> decryptedPayment!!.cardHolder = level2Category.titleValue
            "Card PIN" -> decryptedPayment!!.cardPin = level2Category.titleValue
            "Expiry date" -> decryptedPayment!!.expiryDate = level2Category.titleValue
            "CVV code" -> decryptedPayment!!.cvvCode = level2Category.titleValue
            "Issuing bank" -> decryptedPayment!!.issuingBank = level2Category.titleValue
            "Website" -> decryptedPayment!!.website = level2Category.titleValue
            "Username/login" -> decryptedPayment!!.userName = level2Category.titleValue
            "Password" -> decryptedPayment!!.password = level2Category.titleValue
            "PIN" -> decryptedPayment!!.pin = level2Category.titleValue

            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedPayment!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedPayment!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun getBanking() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedFinancial == null) decryptedFinancial = DecryptedFinancial()
        var categoryIndex = 1001
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedFinancial!!.accountType, Constants.BANK_ACCOUNT_TYPE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedFinancial!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedFinancial!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedFinancial!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("SWIFT/other code", decryptedFinancial!!.swiftCode, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("ABA routing number", decryptedFinancial!!.abaRoutingNumber, "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedFinancial!!.contacts, Constants.CONTACT_SPINNER, Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedFinancial!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedFinancial!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedFinancial!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedFinancial!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedFinancial!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedFinancial!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getInvestments() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedFinancial == null) decryptedFinancial = DecryptedFinancial()
        var categoryIndex = 1002
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedFinancial!!.accountType, Constants.BANK_ACCOUNT_TYPE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedFinancial!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedFinancial!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedFinancial!!.location, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedFinancial!!.contacts, Constants.CONTACT_SPINNER, Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2002
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedFinancial!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedFinancial!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedFinancial!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedFinancial!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2002
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", decryptedFinancial!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", decryptedFinancial!!.attachmentNames, "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    fun setValue(level2Category: Level2SubCategory) {
        when (category_name) {
            "Banking" -> {
                setBanking(level2Category)
            }
            "Investments/Retirement" -> {
                setBanking(level2Category)
            }
            "Loans/Mortgages" -> {
                setBanking(level2Category)
            }
            "Other financial accounts" -> {
                setBanking(level2Category)
            }
            "Credit/Debit cards" -> {
                setCardDebitCardDetails(level2Category)
            }
            "Other payment accounts" -> {
                setBanking(level2Category)
            }
            "Primary home (owned)" -> {
                setProperty(level2Category)
            }
            "Property (rented for own use)" -> {
                setProperty(level2Category)
            }
            "Vacation home" -> {
                setProperty(level2Category)
            }
            "Investment/Rental property" -> {
                setProperty(level2Category)
            }
            "Vehicles" -> {
                setVehicles(level2Category)
            }

            "Maintenance" -> {
                setMaintenance(level2Category)
            }

            "Jewelry" -> {
                setAssets(level2Category)

            }
            "Art and collectibles" -> {
                setAssets(level2Category)
            }
            "Computers and electronics" -> {
                setAssets(level2Category)
            }
            "Furniture" -> {
                setAssets(level2Category)
            }
            "Others" -> {
                setAssets(level2Category)
            }
            "Auto insurance" -> {
                setInsurance( level2Category )
            }
            "Life insurance" -> {
                setInsurance( level2Category )
            }
            "Health insurance" -> {
                setInsurance( level2Category )
            }
            "Umbrella insurance" -> {
                setInsurance( level2Category )
            }
            "Homeowners/Renters insurance" -> {
                setInsurance( level2Category )
            }
            "Past returns" -> {
                setPastReturns( level2Category )
            }
            "Returns to be filed" -> {
                setPastReturns( level2Category )
            }
        }
    }

    private fun setBanking(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Loan type", "Account type" -> decryptedFinancial!!.accountType = level2Category.titleValue
            "Name(s) on account" -> decryptedFinancial!!.nameOnAccount = level2Category.titleValue
            "Account number" -> decryptedFinancial!!.accountNumber = level2Category.titleValue
            "Location" -> decryptedFinancial!!.location = level2Category.titleValue
            "SWIFT/other code" -> decryptedFinancial!!. swiftCode = level2Category.titleValue
            "ABA routing number" -> decryptedFinancial!!.abaRoutingNumber = level2Category.titleValue
            "Contacts" -> decryptedFinancial!!.contacts = level2Category.titleValue
            "Website" -> decryptedFinancial!!.website = level2Category.titleValue
            "Username/login" -> decryptedFinancial!!.userName = level2Category.titleValue
            "Password" -> decryptedFinancial!!.password = level2Category.titleValue
            "PIN" -> decryptedFinancial!!.pin = level2Category.titleValue
            "Account name" -> decryptedFinancial!!.accountName = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedFinancial!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedFinancial!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setPastReturns(level2Category: Level2SubCategory) {
        when( level2Category.title ) {
            "Tax year"-> decryptedTaxes!!.taxYear = level2Category.titleValue // -> Constants.LEVEL2_SPINNER))
            "Taxpayer(s)"-> decryptedTaxes!!.taxPayer = level2Category.titleValue // -> Constants.LEVEL2_SPINNER))
            "Contacts"-> decryptedTaxes!!.contacts = level2Category.titleValue // -> Constants.LEVEL2_SPINNER))
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedTaxes!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedTaxes!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setInsurance(level2Category: Level2SubCategory) {
        when (level2Category.title) {

            "Policy number" -> decryptedInsurance!!.policyNumber = level2Category.titleValue////-> Constants.LEVEL2_NORMAL))
            "Policy effective date"->  decryptedInsurance!!.policyEffectiveDate= level2Category.titleValue //-> Constants.LEVEL2_PICKER))
            "Policy expiration date"->  decryptedInsurance!!.policyExpirationDate= level2Category.titleValue //-> Constants.LEVEL2_PICKER))
            "Contacts"->  decryptedInsurance!!.contacts= level2Category.titleValue //-> Constants.LEVEL2_SPINNER))

            "Website"-> decryptedInsurance!!.website= level2Category.titleValue //-> Constants.LEVEL2_NORMAL))
            "Username/login"-> decryptedInsurance!!.userName= level2Category.titleValue //-> Constants.LEVEL2_NORMAL))
            "Password"-> decryptedInsurance!!.password= level2Category.titleValue //-> Constants.LEVEL2_PASSWORD))
            "PIN"-> decryptedInsurance!!.pin= level2Category.titleValue //-> Constants.LEVEL2_PASSWORD))

            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedInsurance!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedInsurance!!.attachmentNames = level2Category.titleValue
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

        if (decryptedFinancial != null) {
            decryptedFinancial!!.selectionType = categoryID
            decryptedFinancial!!.institutionName = title
            decryptedFinancial!!.accountName = subTitle
            AppLogger.d("SelectionType ", "DecryptedFinancial" + decryptedFinancial!!.selectionType)
            if (decryptedFinancial!!.created.isEmpty())
                decryptedFinancial!!.created = currentUsers + " " + currentDateandTime
            decryptedFinancial!!.modified = currentUsers + " " + currentDateandTime

            var isSaveComplete = false
            if (decryptedFinancial!!.id.toInt() == 0) {
                decryptedFinancial!!.id = getUniqueId()
                AppLogger.d("saveDocument", "id" + decryptedFinancial!!.id)
            }

            AppLogger.d("saveDocument", "Document Id " + decryptedFinancial!!.id)
            AppLogger.d("saveDocument", "Document : " + decryptedFinancial!!)
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val financial = encryptFinancial(decryptedFinancial!!)
                            realm.insertOrUpdate(financial)
                            AppLogger.d("CombineLevel2 ", "Inserted ")
                            realm!!.commitTransaction()
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

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = mCombine as DecryptedCombine
                            AppLogger.d("saveDocument", "Combine Id " + combine!!.id)
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(Combine::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptFinancial(decryptedFinancial!!)
                            if (combineRealm!!.financialItems.contains(encryptedObject)) {
                                val index = combineRealm!!.financialItems.indexOf(encryptedObject)
                                if (index != -1) {
                                    combineRealm!!.financialItems[index] = (encryptedObject)
                                }
                            } else {
                                combineRealm!!.financialItems.add(encryptedObject)
                            }
                            /*combine.financialItems.add( decryptedFinancial )
                            val encryptedCombine = encryptCombine(combine)*/
                            realm.insertOrUpdate(combineRealm)
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
        }

        if (decryptedPayment != null) {
            decryptedPayment!!.cardName = title
            decryptedPayment!!.selectionType = categoryID
            AppLogger.d("SelectionType ", "DecryptedPayment" + decryptedPayment!!.selectionType)
            if (decryptedPayment!!.created.isEmpty())
                decryptedPayment!!.created = currentUsers + " " + currentDateandTime
            decryptedPayment!!.modified = currentUsers + " " + currentDateandTime

            var isSaveComplete = false
            if (decryptedPayment!!.id.toInt() == 0) {
                decryptedPayment!!.id = getUniqueId()
            }

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val payment = encryptPayment(decryptedPayment!!)
                            realm.insertOrUpdate(payment)
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

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = mCombine as DecryptedCombine
                            AppLogger.d("saveDocument", "Combine Id " + combine!!.id)
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(Combine::class.java, getUniqueId())
                            }
                            combineRealm!!.paymentItems.add(encryptPayment(decryptedPayment!!))
                            realm.insertOrUpdate(combineRealm)
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
        }

        if (decryptedProperty != null) {
            decryptedProperty!!.selectionType = categoryID
            decryptedProperty!!.propertyName = title
            if (decryptedProperty!!.created.isEmpty())
                decryptedProperty!!.created = currentUsers + " " + currentDateandTime
            decryptedProperty!!.modified = currentUsers + " " + currentDateandTime
            var isSaveComplete = false
            if (decryptedProperty!!.id.equals(0)) {
                decryptedProperty!!.id = getUniqueId()
            }

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val property = encryptProperty(decryptedProperty!!)
                            realm.insertOrUpdate(property)
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

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = mCombine as DecryptedCombine
                            AppLogger.d("saveDocument", "Combine Id " + combine!!.id)
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(Combine::class.java, getUniqueId())
                            }
                            combineRealm!!.propertyItems.add(encryptProperty(decryptedProperty!!))
                            realm.insertOrUpdate(combineRealm)
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
        }

        if (decryptedVehicle != null) {
            decryptedVehicle!!.vehicleName = title
            var isSaveComplete = false
            decryptedVehicle!!.selectionType = categoryID
            if (decryptedVehicle!!.created.isEmpty())
                decryptedVehicle!!.created = currentUsers + " " + currentDateandTime
            decryptedVehicle!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedVehicle!!.id.toInt() == 0) {
                decryptedVehicle!!.id = getUniqueId()
            }
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val vehicle = encryptVehicle(decryptedVehicle!!)
                            realm.insertOrUpdate(vehicle)
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

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = mCombine as DecryptedCombine
                            AppLogger.d("saveDocument", "Combine Id " + combine!!.id)
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(Combine::class.java, getUniqueId())
                            }
                            combineRealm!!.vehicleItems.add(encryptVehicle(decryptedVehicle!!))
                            realm.insertOrUpdate(combineRealm)
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
        }

        if (decryptedAssets != null) {
            decryptedAssets!!.selectionType = categoryID
            decryptedAssets!!.assetName = title
            if (decryptedAssets!!.created.isEmpty())
                decryptedAssets!!.created = currentUsers + " " + currentDateandTime
            decryptedAssets!!.modified = currentUsers + " " + currentDateandTime

            if (decryptedAssets!!.id.toInt() == 0) {
                decryptedAssets!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val assets = encryptAsset(decryptedAssets!!)
                            realm.insertOrUpdate(assets)
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

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = mCombine as DecryptedCombine
                            AppLogger.d("saveDocument", "Combine Id " + combine!!.id)
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(Combine::class.java, getUniqueId())
                            }
                            combineRealm!!.assetItems.add(encryptAsset(decryptedAssets!!))
                            realm.insertOrUpdate(combineRealm)
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
        }

        if (decryptedInsurance != null) {
            decryptedInsurance!!.selectionType = categoryID
            decryptedInsurance!!.insuranceCompany = title
            if (decryptedInsurance!!.created.isEmpty())
                decryptedInsurance!!.created = currentUsers + " " + currentDateandTime
            decryptedInsurance!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedInsurance!!.id.toInt() == 0) {
                decryptedInsurance!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val insurance = encryptInsurance(decryptedInsurance!!)
                            realm.insertOrUpdate(insurance)
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

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = mCombine as DecryptedCombine
                            AppLogger.d("saveDocument", "Combine Id " + combine!!.id)
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(Combine::class.java, getUniqueId())
                            }
                            combineRealm!!.insuranceItems.add(encryptInsurance(decryptedInsurance!!))
                            realm.insertOrUpdate(combineRealm)
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
        }

        if (decryptedTaxes != null) {
            decryptedTaxes!!.selectionType = categoryID
            decryptedTaxes!!.returnName = title
            if( decryptedTaxes!!.created.isEmpty() )
                decryptedTaxes!!.created = currentUsers + " " + currentDateandTime
            decryptedTaxes!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedTaxes!!.id.toInt() == 0) {
                decryptedTaxes!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val tax = encryptTaxes(decryptedTaxes!!)
                            realm.insertOrUpdate(tax)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = mCombine as DecryptedCombine
                            AppLogger.d("saveDocument", "Combine Id " + combine!!.id)
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(Combine::class.java, getUniqueId())
                            }
                            combineRealm!!.taxesItems.add(encryptTaxes(decryptedTaxes!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
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