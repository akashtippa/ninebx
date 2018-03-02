package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.Parcelable
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.ui.home.fragments.MemoryTimeLineFragment
import com.ninebx.ui.home.fragments.SingleContactViewFragment
import com.ninebx.ui.home.lists.SubListsFragment
import com.ninebx.utility.*
import io.realm.Realm

/***
 * Created by TechnoBlogger on 23/01/18.
 */
class Level2CategoryHelper(
        val category_name: String,
        val categoryID: String,
        val categoryView: Level2CategoryView,
        val selectedDocument: Parcelable?,
        val classType: String?
) {

    // For Home & Money
    private var decryptedFinancial: DecryptedFinancial ?= null // DecryptedFinancial()
    private var decryptedPayment: DecryptedPayment ?= null // DecryptedPayment()
    private var decryptedProperty: DecryptedProperty ?= null // DecryptedProperty()
    private var decryptedVehicle: DecryptedVehicle ?= null // DecryptedVehicle()
    private var decryptedAssets: DecryptedAsset ?= null // DecryptedAsset()
    private var decryptedInsurance: DecryptedInsurance ?= null // DecryptedInsurance()
    private var decryptedTaxes: DecryptedTax ?= null // DecryptedTax()

    // For Travel
    private var decryptedLoyaltyPrograms: DecryptedLoyaltyPrograms ?= null // DecryptedLoyaltyPrograms()
    private var decryptedTravel: DecryptedTravel ?= null // DecryptedTravel()

    // For Personal
    private var decryptedDriversLicense: DecryptedLicense ?= null // DecryptedLicense()
    private var decryptedSocial: DecryptedSocial ?= null // DecryptedSocial()
    private var decryptedTAX_ID: DecryptedTaxID ?= null // DecryptedTaxID()
    private var decryptedCertificate: DecryptedCertificate ?= null // DecryptedCertificate()
    private var decryptedOtherGovernment: DecryptedGovernment ?= null // DecryptedGovernment()

    init {
        extractObject()
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

        // Personal

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

        //Travel

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

        // Common View
            "Services/Other Accounts" -> {
                getServicesOthersAccounts()
            }
            "Other Attachments" -> {
                getOtherAttachments()
            }

        // Shopping
            "Loyalty Programs" -> {
                getLoyaltyPrograms()
            }

            "Recent Purchases" -> {
                getRecentPurchases()
            }

            "Add Person" -> {
                getEducation()
            }

            "Add person" -> {
                getWork()
            }
            "Travel Dates And Plans" -> {
                getTravelDatesAndPlans()
            }

        // Wellness

            "Identification" -> {
                getIdentification()
            }
            "Medical history" -> {
                getMedicalHistory()
            }
            "Healthcare providers" -> {
                getHealthCareProviders()
            }
            "Emergency contacts" -> {

            }
            "Medications" -> {
                getMedications()
            }
            "Medical conditions/Allergies" -> {
                getMedicalConditions()
            }
            "Eyeglass prescriptions" -> {
                getEyeGlassPrescriptions()
            }
            "Vital numbers" -> {
                getVitalNumbers()
            }
            "Checkups and visits" -> {
                getCheckUps()
            }

        // Clothing Sizes
            "Womens sizes" -> {
                getWomensSizes()
            }
            "Mens sizes" -> {
                getMenSizes()
            }
            "Girls sizes" -> {
                getGirlsSizes()
            }
            "Boy's sizes" -> {
                getBoysSizes()
            }
            "Baby's sizes" -> {
                getBabysSizes()
            }
        }
    }


    private fun extractObject() {
        if( selectedDocument == null ) {
            return
        }
        when (classType) {

        // For Home and Money
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

            DecryptedTravel::class.java.simpleName -> {
                decryptedTravel = selectedDocument as DecryptedTravel
            }

        // For Travel
            DecryptedLoyaltyPrograms::class.java.simpleName -> {
                decryptedLoyaltyPrograms = selectedDocument as DecryptedLoyaltyPrograms
            }
            DecryptedTravel::class.java.simpleName -> {
                decryptedTravel = selectedDocument as DecryptedTravel
            }

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

            else -> {
                //TODO
            }
        }
    }
    private fun getBabysSizes() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Clothing", "Clothing", Constants.PICKER_BABY_CLOTHING, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Shoes", "Shoes", Constants.PICKER_BABY_SHOES, Constants.LEVEL_NORMAL_SPINNER))

        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getBoysSizes() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Tops"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))

        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Bottoms"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)


        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Outerwear"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Shoes"
        category.subCategories.add(Level2SubCategory("Toddler size (US)", "Toddler size (US)", Constants.PICKER_GIRLS_SHOES_TODDLER, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Little and big kid size(US)", "Little and big kid size(US)", Constants.PICKER_GIRLS_SHOES_LITTLE_AND_BIG_KID, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Width", "Width", Constants.PICKER_GIRLS_SHOES_WIDTH, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Accessories"
        category.subCategories.add(Level2SubCategory("Belts", "Belts", Constants.PICKER_GIRLS_ACCESSORIES_BELTS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Belts(Numeric size)", "Belts(Numeric size)", Constants.PICKER_GIRLS_ACCESSORIES_BELTS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Hats", "Hats", Constants.PICKER_GIRLS_ACCESSORIES_HATS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Gloves", "Gloves", Constants.PICKER_GIRLS_ACCESSORIES_GLOVES, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Tights", "Tights", Constants.PICKER_GIRLS_ACCESSORIES_TIGHTS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Socks", "Socks", Constants.PICKER_GIRLS_ACCESSORIES_SOCKS, Constants.LEVEL_NORMAL_SPINNER))

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Measurements"
        category.subCategories.add(Level2SubCategory("Chest(in)", "Chest(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Waist(in)", "Waist(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Seat(in)", "Seat(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Inseam(in)", "Inseam(in)", "", Constants.LEVEL2_NUMBER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getGirlsSizes() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Tops"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))

        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Bottoms"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Dresses"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Outerwear"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Swimwear"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Shoes"
        category.subCategories.add(Level2SubCategory("Toddler size (US)", "Toddler size (US)", Constants.PICKER_GIRLS_SHOES_TODDLER, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Little and big kid size(US)", "Little and big kid size(US)", Constants.PICKER_GIRLS_SHOES_LITTLE_AND_BIG_KID, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Width", "Width", Constants.PICKER_GIRLS_SHOES_WIDTH, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Accessories"
        category.subCategories.add(Level2SubCategory("Belts", "Belts", Constants.PICKER_GIRLS_ACCESSORIES_BELTS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Belts(Numeric size)", "Belts(Numeric size)", Constants.PICKER_GIRLS_ACCESSORIES_BELTS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Hats", "Hats", Constants.PICKER_GIRLS_ACCESSORIES_HATS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Gloves", "Gloves", Constants.PICKER_GIRLS_ACCESSORIES_GLOVES, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Tights", "Tights", Constants.PICKER_GIRLS_ACCESSORIES_TIGHTS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Socks", "Socks", Constants.PICKER_GIRLS_ACCESSORIES_SOCKS, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Measurements"
        category.subCategories.add(Level2SubCategory("Chest(in)", "Chest(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Waist(in)", "Waist(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Seat(in)", "Seat(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Inseam(in)", "Inseam(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Torso(in)", "Torso(in)", "", Constants.LEVEL2_NUMBER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getMenSizes() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Size category(US)", "Size category(US)", Constants.PICKER_MENS_SIZE_CATEGORY_US, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Tops"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_MENS_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_MENS_NUMERIC_SIZE_TOPS, Constants.LEVEL_NORMAL_SPINNER))

        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Bottoms"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_MENS_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_MENS_NUMERIC_SIZE_BOTTOMS, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Suiting-Jackets"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_JACKETS, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Suiting-Pants"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_MENS_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_PANTS, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Outwear"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_MENS_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_OUTERWEAR, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Shoes"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_MENS_SHOES, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Width", "Width", Constants.PICKER_MENS_SHOES_WIDTH, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Accessories"
        category.subCategories.add(Level2SubCategory("Belts", "Belts", Constants.PICKER_MENS_BELTS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Belts(Numeric size)", "Belts(Numeric size)", Constants.PICKER_MENS_BELTS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Gloves", "Gloves", Constants.PICKER_MENS_GLOVES, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Tights", "Tights", Constants.PICKER_MENS_TIGHTS, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Measurements"
        category.subCategories.add(Level2SubCategory("Neck(in)", "Neck(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Chest(in)", "Chest(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Waist(in)", "Waist(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Arm length(in)", "Arm length(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Inseam(in)", "Inseam(in)", "", Constants.LEVEL2_NUMBER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getWomensSizes() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Size category(US)", "Size category(US)", Constants.PICKER_WOMENS_DETAILS_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Tops"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_WOMEN_SIZE_US, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_WOMEN_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))

        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Bottoms"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_WOMEN_SIZE_US, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_WOMEN_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Dresses"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_WOMEN_SIZE_US, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_WOMEN_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Outwear"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_WOMEN_SIZE_US, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_WOMEN_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Swimwear"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_WOMEN_SIZE_US, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_WOMEN_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Bra band/cup size (US)", "Bra band/cup size (US)", "", Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Shoes"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_WOMEN_SHOES, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Width", "Width", Constants.PICKER_WOMEN_SHOES_WIDTH, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Accessories"
        category.subCategories.add(Level2SubCategory("Belts", "Belts", Constants.PICKER_WOMEN_ACCESSORIES_BELTS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Hats", "Hats", Constants.PICKER_WOMEN_ACCESSORIES_HATS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Gloves", "Gloves", Constants.PICKER_WOMEN_ACCESSORIES_GLOVES, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Tights", "Tights", Constants.PICKER_WOMEN_ACCESSORIES_TIGHTS, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Measurements"
        category.subCategories.add(Level2SubCategory("Bust(in)", "Bust(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Waist(in)", "Waist(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Hips(in)", "Hips(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Arm length(in)", "Arm length(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Inseam(in)", "Inseam(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Torso(in)", "Torso(in)", "", Constants.LEVEL2_NUMBER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getMaintenance() {
        val categoryList = ArrayList<Level2Category>()

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

    // Types of InputTYpe
    // Number
    // Picker
    // Password


    private fun getUmbrellaInsurance() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 6005
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Policy Details"
        category.subCategories.add(Level2SubCategory("Policy number", "Policy number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Policy effective date", "Policy effective date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Policy effective date", "Policy expiration date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", Constants.KEYBOARD_NUMBER, Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2036
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2006
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getHealthInsurance() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2036
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Policy Details"
        category.subCategories.add(Level2SubCategory("Policy number", "Policy number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Policy effective date", "Policy effective date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Policy effective date", "Policy expiration date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2036
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2006
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getLifeInsurance() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 6003
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Policy Details"
        category.subCategories.add(Level2SubCategory("Policy number", "Policy number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Policy effective date", "Policy effective date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Policy effective date", "Policy expiration date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2036
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2006
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getAutoInsurance() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 6002
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Policy Details"
        category.subCategories.add(Level2SubCategory("Policy number", "Policy number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Policy effective date", "Policy effective date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Policy effective date", "Policy expiration date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2036
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2006
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun gtHomeOwnerRentersInsurance() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 6001
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Policy Details"
        category.subCategories.add(Level2SubCategory("Policy number", "Policy number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Policy effective date", "Policy effective date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Policy effective date", "Policy expiration date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2036
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2006
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getFragmentSubList() {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        val bundle = Bundle()
        bundle.putString("homeScreen", "HomeScreen")

        val categoryFragment = SubListsFragment()
        categoryFragment.arguments = bundle

        fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
    }

    private fun getMemoryTimeLine() {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        val categoryFragment = MemoryTimeLineFragment()
        fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
    }

    private fun getContactsList() {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        val categoryFragment = SingleContactViewFragment()
        fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
    }

    // Home & Money

    private fun getReturnsToBeFiled() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 7002
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Tax year", "Tax year", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Taxpayer(s)", "Taxpayer(s)", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2017
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getPastReturns() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 7001
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Tax year", "Tax year", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Taxpayer(s)", "Taxpayer(s)", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2017
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getOtherDetails() {
        val categoryList = ArrayList<Level2Category>()

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
        category.subCategories.add(Level2SubCategory("Name(s) on title", decryptedProperty!!.propertyName, "", Constants.LEVEL2_SPINNER))
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
        if( decryptedFinancial == null ) decryptedFinancial = DecryptedFinancial()
        var categoryIndex = 2002
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedFinancial!!.accountType, Constants.BANK_ACCOUNT_TYPE, Constants.LEVEL_NORMAL_SPINNER))
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
        category.subCategories.add(Level2SubCategory("Password", decryptedFinancial!!.password, "" , Constants.LEVEL2_PASSWORD))
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
        if( decryptedPayment == null ) decryptedPayment = DecryptedPayment()
        var categoryIndex = 2001
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Card Details"
        category.subCategories.add(Level2SubCategory("Card number", decryptedPayment!!.cardNumber, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Card type", decryptedPayment!!.cardType, Constants.KEYBOARD_SPINNER, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Card holder", decryptedPayment!!.cardHolder, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Expiry date", decryptedPayment!!.expiryDate, Constants.KEYBOARD_PICKER, Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("CVV code", decryptedPayment!!.cvvCode, "", Constants.LEVEL2_PASSWORD))
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
        if( decryptedFinancial == null ) decryptedFinancial = DecryptedFinancial()
        var categoryIndex = 1004
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedFinancial!!.accountType, Constants.BANK_ACCOUNT_TYPE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedFinancial!!.accountName, "", Constants.LEVEL2_SPINNER))
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
        category.subCategories.add(Level2SubCategory("Password", decryptedFinancial!!.password, "" , Constants.LEVEL2_PASSWORD))
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
        if( decryptedFinancial == null ) decryptedFinancial = DecryptedFinancial()
        var categoryIndex = 1003
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Loan type", decryptedFinancial!!.accountType, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedFinancial!!.accountName, "", Constants.LEVEL2_SPINNER))
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
        category.subCategories.add(Level2SubCategory("Password", decryptedFinancial!!.password, "" , Constants.LEVEL2_PASSWORD))
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

    private fun getInvestments() {
        val categoryList = ArrayList<Level2Category>()
        if( decryptedFinancial == null ) decryptedFinancial = DecryptedFinancial()
        var categoryIndex = 1002
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedFinancial!!.accountType, Constants.BANK_ACCOUNT_TYPE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedFinancial!!.accountName, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedFinancial!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", decryptedFinancial!!.location, "" , Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", decryptedFinancial!!.contacts, Constants.CONTACT_SPINNER, Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2002
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedFinancial!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedFinancial!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory(decryptedFinancial!!.password, "" , "", Constants.LEVEL2_PASSWORD))
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

    private fun getBanking() {
        val categoryList = ArrayList<Level2Category>()
        if( decryptedFinancial == null ) decryptedFinancial = DecryptedFinancial()
        var categoryIndex = 1001
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedFinancial!!.accountType, Constants.BANK_ACCOUNT_TYPE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedFinancial!!.accountName, "", Constants.LEVEL2_SPINNER))
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
        category.subCategories.add(Level2SubCategory("Password", decryptedFinancial!!.password, "" , Constants.LEVEL2_PASSWORD))
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

    // SERVICES/OTHER ACCOUNTS
    private fun getServicesOthersAccounts() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 1001
        var category_id = "interest_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", "Account type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", "Name(s) on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Payment method on file", "Payment method on file", "", Constants.LEVEL2_SPINNER))

        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    // PERSONAL
    private fun getOtherGovernmentIssuedID() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 6001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on ID", "Name on ID", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", "Issuing country", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Issuing state", "Issuing state", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("ID number", "ID number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", "Date issued", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", "Expiration date", "", Constants.LEVEL2_PICKER))

        categoryList.add(category)

        categoryIndex += 2022
        category_id = "other_certificate_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
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
        var categoryIndex = 5001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name 1 on certificate", "Name 1 on certificate", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name 2 on certificate", "Name 2 on certificate", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date of marriage", "Date of marriage", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Place of marriage", "Place of marriage", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2020
        category_id = "birth_certificate_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
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
        var categoryIndex = 4001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on certificate", "Name on certificate", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Gender", "Gender", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date of birth", "Date of birth", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Time of birth", "Time of birth", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place of birth", "Place of birth", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2020
        category_id = "birth_certificate_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getTaxID() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 3001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on ID", "Name on ID", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", "Issuing country", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Tax ID number", "Tax ID number", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2020
        category_id = "tax_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getSocialSecurityCard() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 2001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on card", "Name on card", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Social security number", "Social security number", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2019
        category_id = "social_security_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getDriversLicense() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 1001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on license", "Name on license", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", "Issuing country", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Issuing state", "Issuing state", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("License number", "License number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Data issued", "Data issued", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", "Expiration date", "", Constants.LEVEL2_PICKER))
        categoryList.add(category)

        categoryIndex += 2018
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    // TRAVEL

    private fun getAirline() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 1001
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name on account", "Name on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2023
        category_id = "online_access" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2018
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
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
        var categoryIndex = 1002
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name on account", "Name on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2024
        category_id = "online_access" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2024
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
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
        var categoryIndex = 1003
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name on account", "Name on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2025
        category_id = "online_access" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2025
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
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
        var categoryIndex = 1004
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name on account", "Name on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2026
        category_id = "online_access" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2026
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
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
        var categoryIndex = 1005
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name on account", "Name on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2027
        category_id = "online_access" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2027
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
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
        var categoryIndex = 1006
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name on account", "Name on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2028
        category_id = "online_access" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2028
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
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
        var categoryIndex = 2001
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Passport Details"
        category.subCategories.add(Level2SubCategory("Name on passport", "Name on passport", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", "Account number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Passport number", "Passport number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place issued", "Place issued", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", "Date issued", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", "Expiration date", "", Constants.LEVEL2_PICKER))
        categoryList.add(category)

        categoryIndex += 2029
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
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
        var categoryIndex = 2002
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Visa Details"
        category.subCategories.add(Level2SubCategory("Name on visa", "Name on visa", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", "Account number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Visa type", "Visa type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Visa number", "Visa number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place issued", "Place issued", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", "Date issued", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", "Expiration date", "", Constants.LEVEL2_PICKER))
        categoryList.add(category)

        categoryIndex += 2030
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
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
        var categoryIndex = 2003
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Travel Document Details"
        category.subCategories.add(Level2SubCategory("Name on travel document", "Name on travel document", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", "Account number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Travel document type", "Travel document type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Travel document number", "Travel document number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place issued", "Place issued", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", "Date issued", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", "Expiration date", "", Constants.LEVEL2_PICKER))
        categoryList.add(category)

        categoryIndex += 2031
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2031
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }


    // Education and Work

    private fun getEducation() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 1001
        var category_id = "edu_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name", "Name", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Concenteration/majaor", "Concenteration/majaor", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("From", "From", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("To", "To", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Currently studying here", "", "", Constants.LEVEL2_SWITCH))
        categoryList.add(category)

        categoryIndex += 2032
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2032
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getWork() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2001
        var category_id = "edu_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name", "Name", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("From", "From", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("To", "To", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Currently working here", "", "", Constants.LEVEL2_SWITCH))
        categoryList.add(category)

        categoryIndex += 2033
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2032
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getOtherAttachments() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2001
        var category_id = "interest_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2032
        category_id = "notes" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getRecentPurchases() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2034
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Purchased by", "Purchased by", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Purchased date", "Purchased date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Purchased price", "Purchased price", "", Constants.LEVEL2_USD))
        categoryList.add(category)

        categoryIndex += 2034
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2034
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getLoyaltyPrograms() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2033
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name(s) on account", "Name(s) on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2033
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2033
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2033
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getTravelDatesAndPlans() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 3001
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Plans confirmed?", "", "", Constants.LEVEL2_SWITCH))
        category.subCategories.add(Level2SubCategory("Start date", "Start date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("End date", "End date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Plans to visit/consider 1", "Plans to visit/consider 1", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Plans to visit/consider 2", "Plans to visit/consider 2", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Plans to visit/consider 3", "Plans to visit/consider 3", "", Constants.LEVEL2_LOCATION))
        categoryList.add(category)

        categoryIndex += 2035
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2035
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }


    // Wellness

    private fun getIdentification() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Gender", "Gender", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date of birth", "Date of birth", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Age", "Age", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Height(ft, in)", "Height(ft, in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Weight", "Weight", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Hair color", "Hair color", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Eye color", "Eye color", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Visible marks", "Visible marks", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Blood type", "Blood type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Organ donor", "Organ donor", "", Constants.LEVEL2_SWITCH))
        categoryList.add(category)



        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getMedicalHistory() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Past Conditions And Treatment"
        category.subCategories.add(Level2SubCategory("Description", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Immunization History"
        category.subCategories.add(Level2SubCategory("Description", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Family History"
        category.subCategories.add(Level2SubCategory("Description", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)



        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getHealthCareProviders() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 6004
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Practice/Group name", "Practice/Group name", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Phone number 1", "Phone number 1", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Phone number 2", "Phone number 2", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Email address", "Email address", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Street address 1", "Street address 1", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Street address 2", "Street address 2", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("City", "City", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("State", "State", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Zip code", "Zip code", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Country", "Country", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)



        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getMedications() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Frequency", "Frequency", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Start date", "Start date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("End date", "End date", "", Constants.LEVEL2_PICKER))
        categoryList.add(category)



        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getMedicalConditions() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Description", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)



        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getEyeGlassPrescriptions() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Description", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)



        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getVitalNumbers() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Height (ft, in)", "Height (ft, in)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Weight(lbs)", "Weight(lbs)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Waist(in)", "Waist(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Body fat(%)", "Body fat(%)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Body mass index(BMI kg/m2)", "Body mass index(BMI kg/m2)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Blood pressure(xx/yy)", "Blood pressure(xx/yy)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Heart rate(bpm)", "Heart rate(bpm)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Total cholesterol(mg/dL)", "Total cholesterol(mg/dL)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("HDL cholesterol(mg/dL)", "HDL cholesterol(mg/dL)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("LDL cholesterol(mg/dL)", "LDL cholesterol(mg/dL)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Cholesterol ration(Total cholesterol/HDL)", "Cholesterol ration(Total cholesterol/HDL)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Triglycerides(mg/dL)", "Triglycerides(mg/dL)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Blood glucose(mg/dL)", "Blood glucose(mg/dL)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Hemoglobin A1C(%)", "Hemoglobin A1C(%)", "", Constants.LEVEL2_SWITCH))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getCheckUps() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Type of physician", "Type of physician", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Reason", "Reason", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date of visit", "Date of visit", "", Constants.LEVEL2_PICKER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    fun setValue( level2Category: Level2SubCategory ) {
        when (category_name) {
            "Banking" -> {
                setBanking( level2Category )
            }
            "Investments/Retirement" -> {
                setBanking( level2Category )
            }
            "Loans/Mortgages" -> {
                setBanking( level2Category )
            }
            "Other financial accounts" -> {
                setBanking( level2Category )
            }
            "Credit/Debit cards" -> {
                setCardDebitCardDetails( level2Category )
            }
            "Other payment accounts" -> {
                setBanking( level2Category )
            }
            "Primary home (owned)" -> {
                setProperty( level2Category )
            }
            "Property (rented for own use)" -> {
                setProperty( level2Category )
            }
            "Vacation home" -> {
                setProperty( level2Category )
            }
            "Investment/Rental property" -> {
                setProperty( level2Category )
            }
            "Vehicles" -> {
                setVehicles( level2Category )
            }

            "Maintenance" -> {
                setMaintenance(level2Category)
            }

            "Jewelry" -> {
                setAssets( level2Category )

            }
            "Art and collectibles" -> {
                setAssets( level2Category )
            }
            "Computers and electronics" -> {
                setAssets( level2Category )
            }
            "Furniture" -> {
                setAssets( level2Category )
            }
            "Others" -> {
                setAssets( level2Category )
            }

        //Check from here
        // Insurance Left

            "Past returns" -> {
                getPastReturns()
            }

            "Returns to be filed" -> {
                getReturnsToBeFiled()
            }

        // Personal

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

        // Common View
            "Services/Other Accounts" -> {
                getServicesOthersAccounts()
            }
            "Other Attachments" -> {
                getOtherAttachments()
            }

            "Loyalty Programs" -> {
                getLoyaltyPrograms()
            }

            "Recent Purchases" -> {
                getRecentPurchases()
            }

            "Add Person" -> {
                getEducation()
            }

            "Add person" -> {
                getWork()
            }
            "Travel Dates And Plans" -> {
                getTravelDatesAndPlans()
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

        // Wellness

            "Identification" -> {
                getIdentification()
            }
            "Medical history" -> {
                getMedicalHistory()
            }
            "Healthcare providers" -> {
                getHealthCareProviders()
            }
            "Emergency contacts" -> {

            }
            "Medications" -> {
                getMedications()
            }
            "Medical conditions/Allergies" -> {
                getMedicalConditions()
            }
            "Eyeglass prescriptions" -> {
                getEyeGlassPrescriptions()
            }
            "Vital numbers" -> {
                getVitalNumbers()
            }
            "Checkups and visits" -> {
                getCheckUps()
            }

        // Clothing Sizes
            "Womens sizes" -> {
                getWomensSizes()
            }
            "Mens sizes" -> {
                getMenSizes()
            }
            "Girls sizes" -> {
                getGirlsSizes()
            }
            "Boy's sizes" -> {
                getBoysSizes()
            }
            "Baby's sizes" -> {
                getBabysSizes()
            }

        }
    }

    private fun setAssets(level2Category: Level2SubCategory) {
        when( level2Category.title ) {
            "Estimated current market value"-> decryptedAssets!!.estimatedMarketValue= level2Category.titleValue //, "", Constants.LEVEL2_USD))
            "Serial number"-> decryptedAssets!!.serialNumber= level2Category.titleValue //, "", Constants.LEVEL2_NORMAL))
            "Purchase date"-> decryptedAssets!!.purchaseDate= level2Category.titleValue //, "", Constants.LEVEL2_PICKER))
            "Purchase price"-> decryptedAssets!!.purchasePrice= level2Category.titleValue //, "", Constants.LEVEL2_USD))
            "Contacts"-> decryptedAssets!!.contacts= level2Category.titleValue //, "", Constants.LEVEL2_SPINNER))
            else -> {
                when( level2Category.type ) {
                    Constants.LEVEL2_NOTES -> decryptedAssets!!.notes= level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedAssets!!.attachmentNames= level2Category.titleValue
                }
            }
        }
    }

    private fun setMaintenance(level2Category: Level2SubCategory) {
        when( level2Category.title ) {
            "Name of service provider"-> decryptedVehicle!!.maintenanceEvent = level2Category.titleValue
            "Date of service"-> decryptedVehicle!!.dateOfService = level2Category.titleValue
            "Contacts" -> decryptedVehicle!!.contacts = level2Category.titleValue//stants.LEVEL2_SPINNER))
            else -> {
                when( level2Category.type ) {
                    Constants.LEVEL2_NOTES -> decryptedVehicle!!.notes= level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedVehicle!!.attachmentNames= level2Category.titleValue
                }
            }
        }
    }

    private fun setVehicles(level2Category: Level2SubCategory) {
        when( level2Category.titleValue ) {
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
            "Financed through loan" -> decryptedVehicle!!.financedThroughLoan = level2Category.titleValue//stants.LEVEL2_SWITCH))
            "Contacts" -> decryptedVehicle!!.contacts = level2Category.titleValue//stants.LEVEL2_SPINNER))
            else -> {
                when( level2Category.type ) {
                    Constants.LEVEL2_NOTES -> decryptedVehicle!!.notes= level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedVehicle!!.attachmentNames= level2Category.titleValue
                }
            }
        }
    }

    private fun setProperty(level2Category: Level2SubCategory) {
        when( level2Category.title ) {
            "Name of landlord" -> decryptedProperty!!.tenantName = level2Category.titleValue
            "Street address 1"-> decryptedProperty!!.streetAddressOne= level2Category.titleValue
            "Street address 2"-> decryptedProperty!!.streetAddressTwo= level2Category.titleValue
            "City"-> decryptedProperty!!.city= level2Category.titleValue//.LEVEL2_NORMAL))
            "State"-> decryptedProperty!!.state= level2Category.titleValue//.LEVEL2_NORMAL))
            "Zip Code"-> decryptedProperty!!.zipCode= level2Category.titleValue//.LEVEL2_NUMBER))
            "Country"-> decryptedProperty!!.country= level2Category.titleValue//.LEVEL2_NORMAL))
            "Currently rented" -> decryptedProperty!!.currentlyRented = level2Category.isValueSet

            "Name(s) on title"-> decryptedProperty!!.propertyName= level2Category.titleValue//.LEVEL2_SPINNER))
            "Purchase date"-> decryptedProperty!!.purchaseDate= level2Category.titleValue//.LEVEL2_PICKER))
            "Purchase price"-> decryptedProperty!!.purchasePrice= level2Category.titleValue//.LEVEL2_USD))
            "Estimated market value"-> decryptedProperty!!.estimatedMarketValue= level2Category.titleValue//.LEVEL2_USD))
            "Contacts"-> decryptedProperty!!.contacts= level2Category.titleValue//.LEVEL2_SPINNER))

            else -> {
                when( level2Category.type ) {
                    Constants.LEVEL2_NOTES -> decryptedProperty!!.notes= level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedProperty!!.attachmentNames= level2Category.titleValue
                }
            }
        }

    }

    private fun setCardDebitCardDetails(level2Category: Level2SubCategory) {
        when( level2Category.title ) {

            "Card number"-> decryptedPayment!!.cardNumber = level2Category.titleValue
            "Card type"-> decryptedPayment!!.cardType = level2Category.titleValue
            "Card holder"-> decryptedPayment!!.cardHolder = level2Category.titleValue
            "Expiry date"-> decryptedPayment!!.expiryDate = level2Category.titleValue
            "CVV code"-> decryptedPayment!!.cvvCode = level2Category.titleValue
            "Issuing bank"-> decryptedPayment!!.issuingBank = level2Category.titleValue
            "Website"-> decryptedPayment!!.website = level2Category.titleValue
            "Username/login"-> decryptedPayment!!.userName = level2Category.titleValue
            "Password"-> decryptedPayment!!.password = level2Category.titleValue
            "PIN"-> decryptedPayment!!.pin = level2Category.titleValue

            else -> {
                when( level2Category.type ) {
                    Constants.LEVEL2_NOTES -> decryptedPayment!!.notes= level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedPayment!!.attachmentNames= level2Category.titleValue
                }
            }

        }
    }

    private fun setBanking(level2Category: Level2SubCategory) {
        when( level2Category.title ) {
            "Loan type","Account type"-> decryptedFinancial!!.accountType = level2Category.titleValue
            "Name(s) on account"-> decryptedFinancial!!.accountName= level2Category.titleValue
            "Account number"-> decryptedFinancial!!.accountNumber= level2Category.titleValue
            "Location"-> decryptedFinancial!!.location= level2Category.titleValue
            "SWIFT/other code"-> decryptedFinancial!!.swiftCode= level2Category.titleValue
            "ABA routing number"-> decryptedFinancial!!.abaRoutingNumber= level2Category.titleValue
            "Contacts"-> decryptedFinancial!!.contacts = level2Category.titleValue
            "Website"-> decryptedFinancial!!.website = level2Category.titleValue
            "Username/login"-> decryptedFinancial!!.userName= level2Category.titleValue
            "Password"-> decryptedFinancial!!.password= level2Category.titleValue
            "PIN"-> decryptedFinancial!!.pin= level2Category.titleValue
            else -> {
                when( level2Category.type ) {
                    Constants.LEVEL2_NOTES -> decryptedFinancial!!.notes= level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedFinancial!!.attachmentNames= level2Category.titleValue
                }
            }

        }
    }

    @SuppressLint("StaticFieldLeak")
    fun saveDocument(context: Context, combineItem: Parcelable?, title : String ) {
        if( decryptedFinancial != null ) {
            decryptedFinancial!!.selectionType = categoryID
            decryptedFinancial!!.institutionName = title
            if( decryptedFinancial!!.id.toInt() == 0 ) {
                decryptedFinancial!!.id = getUniqueId()
            }
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections( context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val financial = encryptFinancial(decryptedFinancial!!)
                            realm.insertOrUpdate(financial)
                            realm.commitTransaction()
                        }
                    })
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections( context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine : DecryptedCombine = combineItem as DecryptedCombine
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if( combineRealm == null ) {
                                combineRealm = realm.createObject(Combine::class.java)
                            }
                            combineRealm!!.financialItems.add(encryptFinancial(decryptedFinancial!!))
                            /*combine.financialItems.add( decryptedFinancial )
                            val encryptedCombine = encryptCombine(combine)*/
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if( decryptedPayment!= null ) {
            decryptedPayment!!.selectionType = categoryID
            decryptedPayment!!.cardName = title
            if( decryptedPayment!!.id.toInt() == 0 ) {
                decryptedPayment!!.id = getUniqueId()
            }
            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections( context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val payment = encryptPayment(decryptedPayment!!)
                            realm.insertOrUpdate(payment)
                            realm.commitTransaction()
                        }
                    })
                }

            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections( context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine : DecryptedCombine = combineItem as DecryptedCombine
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if( combineRealm == null ) {
                                combineRealm = realm.createObject(Combine::class.java)
                            }
                            combineRealm!!.paymentItems.add(encryptPayment(decryptedPayment!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                    }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if( decryptedProperty!= null ) {
            decryptedProperty!!.selectionType = categoryID
            decryptedProperty!!.titleName = title
            if( decryptedProperty!!.id.toInt() == 0 ) {
                decryptedProperty!!.id = getUniqueId()
            }
            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val property = encryptProperty(decryptedProperty!!)
                            realm.insertOrUpdate(property)
                            realm.commitTransaction()
                        }
                    })
                }

            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            val combine : DecryptedCombine = combineItem as DecryptedCombine
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if( combineRealm == null ) {
                                combineRealm = realm.createObject(Combine::class.java)
                            }
                            combineRealm!!.propertyItems.add(encryptProperty(decryptedProperty!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if( decryptedVehicle!= null ) {
            decryptedVehicle!!.titleName = title
            decryptedVehicle!!.selectionType = categoryID
            if( decryptedVehicle!!.id.toInt() == 0 ) {
                decryptedVehicle!!.id = getUniqueId()
            }
            object  : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections( context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val vehicle = encryptVehicle(decryptedVehicle!!)
                            realm.insertOrUpdate(vehicle)
                            realm.commitTransaction()
                        }
                    })
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            val combine : DecryptedCombine = combineItem as DecryptedCombine
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if( combineRealm == null ) {
                                combineRealm = realm.createObject(Combine::class.java)
                            }
                            combineRealm!!.vehicleItems.add(encryptVehicle(decryptedVehicle!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if( decryptedAssets!= null ) {
            decryptedAssets!!.selectionType = categoryID
            decryptedAssets!!.test = title
            if( decryptedAssets!!.id.toInt() == 0 ) {
                decryptedAssets!!.id = getUniqueId()
            }
            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val assets = encryptAsset(decryptedAssets!!)
                            realm.insertOrUpdate(assets)
                            realm.commitTransaction()
                        }
                    })
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            val combine : DecryptedCombine = combineItem as DecryptedCombine
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if( combineRealm == null ) {
                                combineRealm = realm.createObject(Combine::class.java)
                            }
                            combineRealm!!.assetItems.add(encryptAsset(decryptedAssets!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if( decryptedInsurance!= null ) {
            decryptedInsurance!!.selectionType = categoryID
            decryptedInsurance!!.insuranceCompany = title
            if( decryptedInsurance!!.id.toInt() == 0 ) {
                decryptedInsurance!!.id = getUniqueId() }
            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections( context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val insurance = encryptInsurance(decryptedInsurance!!)
                            realm.insertOrUpdate(insurance)
                            realm.commitTransaction()
                        }
                    })
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections( context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine : DecryptedCombine = combineItem as DecryptedCombine
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if( combineRealm == null ) {
                                combineRealm = realm.createObject(Combine::class.java)
                            }
                            combineRealm!!.insuranceItems.add(encryptInsurance(decryptedInsurance!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if( decryptedTaxes!= null ) {
            decryptedTaxes!!.selectionType = categoryID
            decryptedTaxes!!.title = title
            if( decryptedTaxes!!.id.toInt() == 0 ) {
                decryptedTaxes!!.id = getUniqueId()
            }
           object : AsyncTask<Void, Void, Unit>(){
               override fun doInBackground(vararg params: Void?) {
                   prepareRealmConnections( context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                       override fun onSuccess(realm: Realm?) {
                           realm!!.beginTransaction()
                           val insurance = encryptInsurance(decryptedInsurance!!)
                           realm.insertOrUpdate(insurance)
                           realm.commitTransaction()
                       }
                   })
               }
           }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections( context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine : DecryptedCombine = combineItem as DecryptedCombine
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if( combineRealm == null ) {
                                combineRealm = realm.createObject(Combine::class.java)
                            }
                            combineRealm!!.insuranceItems.add(encryptInsurance(decryptedInsurance!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }
            }
        }
    }
}