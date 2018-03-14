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
import com.ninebx.ui.base.realm.home.personal.CombinePersonal
import com.ninebx.ui.base.realm.home.shopping.CombineShopping
import com.ninebx.ui.base.realm.home.travel.CombineTravel
import com.ninebx.ui.base.realm.home.wellness.CombineWellness
import com.ninebx.ui.home.fragments.FragmentListContainer
import com.ninebx.ui.home.fragments.MemoryTimeLineFragment
import com.ninebx.ui.home.fragments.SingleContactViewFragment
import com.ninebx.ui.home.lists.SubListsFragment
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import java.text.SimpleDateFormat

import java.util.*

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
    private var fragmentListContainer = FragmentListContainer()
    // For Home & Money
    private var decryptedFinancial: DecryptedFinancial? = null // DecryptedFinancial()
    private var decryptedPayment: DecryptedPayment? = null // DecryptedPayment()
    private var decryptedProperty: DecryptedProperty? = null // DecryptedProperty()
    private var decryptedVehicle: DecryptedVehicle? = null // DecryptedVehicle()
    private var decryptedAssets: DecryptedAsset? = null // DecryptedAsset()
    private var decryptedInsurance: DecryptedInsurance? = null // DecryptedInsurance()
    private var decryptedTaxes: DecryptedTax? = null // DecryptedTax()

    // For Travel
    private var decryptedLoyalty: DecryptedLoyalty? = null // decryptedLoyalty()
    private var decryptedTravel: DecryptedTravel? = null // DecryptedTravel()
    private var decryptedDocuments: DecryptedDocuments? = null // DecryptedTravel()
    private var decryptedVacations: DecryptedVacations? = null // DecryptedTravel()

    // For Personal
    private var decryptedDriversLicense: DecryptedLicense? = null // DecryptedLicense()
    private var decryptedSocial: DecryptedSocial? = null // DecryptedSocial()
    private var decryptedTAX_ID: DecryptedTaxID? = null // DecryptedTaxID()
    private var decryptedCertificate: DecryptedCertificate? = null // DecryptedCertificate()
    private var decryptedOtherGovernment: DecryptedGovernment? = null // DecryptedGovernment()

    //For Wellness
    private var decryptedIdentification: DecryptedIdentification? = null
    private var decryptedMedicalHistory: DecryptedMedicalHistory? = null
    private var decryptedHealthcareProviders: DecryptedHealthcareProviders? = null
    private var decryptedEmergencyContacts: DecryptedEmergencyContacts? = null
    private var decryptedMedications: DecryptedMedications? = null
    private var decryptedMedicalConditions: DecryptedMedicalConditions? = null
    private var decryptedEyeglassPrescriptions: DecryptedEyeglassPrescriptions? = null
    private var decryptedVitalNumbers: DecryptedVitalNumbers? = null
    private var decryptedCheckups: DecryptedCheckups? = null

    //For Shopping
    private var decryptedLoyaltyPrograms: DecryptedLoyaltyPrograms? = null
    private var decryptedRecentPurchase: DecryptedRecentPurchase? = null
    private var decryptedClothingSizes: DecryptedClothingSizes? = null
    private var decryptedShopping: DecryptedShopping? = null

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

            "Add Person" -> {
                getEducation()
            }

            "Add person" -> {
                getWork()
            }
             "Travel Dates And Plans"-> {
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
        }
    }


    private fun extractObject() {
        if (selectedDocument == null) {
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

        // For Travel
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

        // For Wellness
            DecryptedCheckups::class.java.simpleName -> {
                decryptedCheckups = selectedDocument as DecryptedCheckups
            }
            DecryptedEmergencyContacts::class.java.simpleName -> {
                decryptedEmergencyContacts = selectedDocument as DecryptedEmergencyContacts
            }
            DecryptedEyeglassPrescriptions::class.java.simpleName -> {
                decryptedEyeglassPrescriptions = selectedDocument as DecryptedEyeglassPrescriptions
            }
            DecryptedHealthcareProviders::class.java.simpleName -> {
                decryptedHealthcareProviders = selectedDocument as DecryptedHealthcareProviders
            }
            DecryptedIdentification::class.java.simpleName -> {
                decryptedIdentification = selectedDocument as DecryptedIdentification
            }
            DecryptedMedicalConditions::class.java.simpleName -> {
                decryptedMedicalConditions = selectedDocument as DecryptedMedicalConditions
            }
            DecryptedMedicalHistory::class.java.simpleName -> {
                decryptedMedicalHistory = selectedDocument as DecryptedMedicalHistory
            }
            DecryptedMedications::class.java.simpleName -> {
                decryptedMedications = selectedDocument as DecryptedMedications
            }
            DecryptedVitalNumbers::class.java.simpleName -> {
                decryptedVitalNumbers = selectedDocument as DecryptedVitalNumbers
            }

        //For Shopping
            DecryptedLoyaltyPrograms::class.java.simpleName -> {
                decryptedLoyaltyPrograms = selectedDocument as DecryptedLoyaltyPrograms
            }
            DecryptedRecentPurchase::class.java.simpleName -> {
                decryptedRecentPurchase = selectedDocument as DecryptedRecentPurchase
            }
            DecryptedClothingSizes::class.java.simpleName -> {
                decryptedClothingSizes = selectedDocument as DecryptedClothingSizes
            }
            DecryptedShopping::class.java.simpleName -> {
                decryptedShopping = selectedDocument as DecryptedShopping
            }
            else -> {
                //TODO
            }
        }
    }

    private fun getBabysSizes() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedClothingSizes == null) decryptedClothingSizes = DecryptedClothingSizes()

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
        if (decryptedClothingSizes == null) decryptedClothingSizes = DecryptedClothingSizes()

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
        if (decryptedClothingSizes == null) decryptedClothingSizes = DecryptedClothingSizes()

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
        if (decryptedClothingSizes == null) decryptedClothingSizes = DecryptedClothingSizes()

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
        if (decryptedClothingSizes == null) decryptedClothingSizes = DecryptedClothingSizes()

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

    // Types of InputTYpe
    // Number
    // Picker
    // Password


    private fun getUmbrellaInsurance() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedInsurance == null) decryptedInsurance = DecryptedInsurance()
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
        if (decryptedInsurance == null) decryptedInsurance = DecryptedInsurance()
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
        if (decryptedInsurance == null) decryptedInsurance = DecryptedInsurance()
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
        if (decryptedInsurance == null) decryptedInsurance = DecryptedInsurance()
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
        if (decryptedInsurance == null) decryptedInsurance = DecryptedInsurance()
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
        if (decryptedTaxes == null) decryptedTaxes = DecryptedTax()
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
        if (decryptedTaxes == null) decryptedTaxes = DecryptedTax()
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
        if (decryptedFinancial == null) decryptedFinancial = DecryptedFinancial()
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

    private fun getInvestments() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedFinancial == null) decryptedFinancial = DecryptedFinancial()
        var categoryIndex = 1002
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", decryptedFinancial!!.accountType, Constants.BANK_ACCOUNT_TYPE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedFinancial!!.accountName, "", Constants.LEVEL2_SPINNER))
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
        category.subCategories.add(Level2SubCategory(decryptedFinancial!!.password, "", "", Constants.LEVEL2_PASSWORD))
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
        if (decryptedOtherGovernment == null) decryptedOtherGovernment = DecryptedGovernment()
        var categoryIndex = 6001
        var category_id = "personal_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on ID", decryptedOtherGovernment!!.nameOnId, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", decryptedOtherGovernment!!.issuingCountry, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Issuing state", decryptedOtherGovernment!!.issuingState, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("ID number", "ID number", decryptedOtherGovernment!!.idNumber, Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", "Date issued", decryptedOtherGovernment!!.dateIssued, Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", "Expiration date", decryptedOtherGovernment!!.expirationDate, Constants.LEVEL2_PICKER))

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
        category.subCategories.add(Level2SubCategory("Date of marriage", decryptedCertificate!!.dateOfMarriage, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Place of marriage", decryptedCertificate!!.placeOfMarriage, "", Constants.LEVEL2_NORMAL))
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
        category.subCategories.add(Level2SubCategory("Date of birth", decryptedCertificate!!.dateOfBirth, "", Constants.LEVEL2_PICKER))
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
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
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
        category.subCategories.add(Level2SubCategory("Date issued", decryptedDriversLicense!!.dateIssued, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Expiration date", decryptedDriversLicense!!.expirationDate, "", Constants.LEVEL2_PICKER))
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

    // TRAVEL

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
        if (decryptedRecentPurchase == null) decryptedRecentPurchase = DecryptedRecentPurchase()

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
        if (decryptedLoyaltyPrograms == null) decryptedLoyaltyPrograms = DecryptedLoyaltyPrograms()
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


    // Wellness

    private fun getIdentification() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedIdentification == null) decryptedIdentification = DecryptedIdentification()
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
        if (decryptedMedicalHistory == null) decryptedMedicalHistory = DecryptedMedicalHistory()
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
        if (decryptedHealthcareProviders == null) decryptedHealthcareProviders = DecryptedHealthcareProviders()
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
        if (decryptedMedications == null) decryptedMedications = DecryptedMedications()
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
        if (decryptedMedicalConditions == null) decryptedMedicalConditions = DecryptedMedicalConditions()
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
        if (decryptedEyeglassPrescriptions == null) decryptedEyeglassPrescriptions = DecryptedEyeglassPrescriptions()
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
        if (decryptedVitalNumbers == null) decryptedVitalNumbers = DecryptedVitalNumbers()
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
        if (decryptedCheckups == null) decryptedCheckups = DecryptedCheckups()
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
            "Homeowners/Renters insurance" -> {
                gtHomeOwnerRentersInsurance()
            }

        //Check from here


            "Past returns" -> {
                getPastReturns()
            }

            "Returns to be filed" -> {
                getReturnsToBeFiled()
            }

        // Personal

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

        // Travel

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


        // Common View
            "Services/Other Accounts" -> {
                getServicesOthersAccounts()
            }
            "Other Attachments" -> {
                getOtherAttachments()
            }

            "Add Person" -> {
                getEducation()
            }

            "Add person" -> {
                getWork()
            }

        // Wellness

            "Identification" -> {
                setIdentification(level2Category)
            }
            "Medical history" -> {
                setMedicalHistory(level2Category)
            }
            "Healthcare providers" -> {
                setHealthCareProviders(level2Category)
            }
            "Emergency contacts" -> {
                setEmergencyContacts(level2Category)
            }
            "Medications" -> {
                setMedications(level2Category)
            }
            "Medical conditions/Allergies" -> {
                setMedicalConditions(level2Category)
            }
            "Eyeglass prescriptions" -> {
                setEyeGlassPrescriptions(level2Category)
            }
            "Vital numbers" -> {
                setVitalNumbers(level2Category)
            }
            "Checkups and visits" -> {
                setCheckUps(level2Category)
            }

        // Shopping
            "Loyalty Programs" -> {
                setLoyaltyPrograms(level2Category)
            }

            "Recent Purchases" -> {
                setRecentPurchases(level2Category)
            }
        // Clothing Sizes
            "Womens sizes" -> {
                setClothingSizes(level2Category)
            }
            "Mens sizes" -> {
                setClothingSizes(level2Category)
            }
            "Girls sizes" -> {
                setClothingSizes(level2Category)
            }
            "Boy's sizes" -> {
                setClothingSizes(level2Category)
            }
            "Baby's sizes" -> {
                setClothingSizes(level2Category)
            }
        }
    }

    private fun setClothingSizes(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Name of person" -> decryptedClothingSizes!!.personName = level2Category.titleValue
            "Size name" -> decryptedClothingSizes!!.sizeName = level2Category.titleValue
            "detailsSizeCategory" -> decryptedClothingSizes!!.sizeCategory = level2Category.titleValue
            "topsSize" -> decryptedClothingSizes!!.topsSize = level2Category.titleValue
            "topsNumericSize" -> decryptedClothingSizes!!.topsNumericSize = level2Category.titleValue
            "bottomsSize" -> decryptedClothingSizes!!.bottomsSize = level2Category.titleValue
            "bottomsNumericSize" -> decryptedClothingSizes!!.bottomsNumericSize = level2Category.titleValue
            "dressesSize" -> decryptedClothingSizes!!.dressesSize = level2Category.titleValue
            "dressesNumericSize" -> decryptedClothingSizes!!.dressesNumericSize = level2Category.titleValue
            "outWearSize" -> decryptedClothingSizes!!.outWearSize = level2Category.titleValue
            "outWearNumericSize" -> decryptedClothingSizes!!.outWearNumericSize = level2Category.titleValue
            "swimWearSize" -> decryptedClothingSizes!!.swimWearSize = level2Category.titleValue
            "swimWearNumericSize" -> decryptedClothingSizes!!.swimWearNumericSize = level2Category.titleValue
            "swimWearbraBandSize" -> decryptedClothingSizes!!.swimWearBraBandCupSize = level2Category.titleValue
            "shoeSize" -> decryptedClothingSizes!!.shoeSize = level2Category.titleValue
            "shoeWidth" -> decryptedClothingSizes!!.shoeWidth = level2Category.titleValue

            "Belts" -> decryptedClothingSizes!!.beltSize = level2Category.titleValue
            "Waist (in)" -> decryptedClothingSizes!!.waist = level2Category.titleValue
            "Hats" -> decryptedClothingSizes!!.hats = level2Category.titleValue
            "Gloves" -> decryptedClothingSizes!!.gloves = level2Category.titleValue
            "Tights" -> decryptedClothingSizes!!.tights = level2Category.titleValue
            "Bust (in)" -> decryptedClothingSizes!!.bust = level2Category.titleValue
            "Hips (in)" -> decryptedClothingSizes!!.hips = level2Category.titleValue
            "Socks" -> decryptedClothingSizes!!.socks = level2Category.titleValue
            "Seat (in)" -> decryptedClothingSizes!!.seat = level2Category.titleValue

            "Arm length (in)" -> decryptedClothingSizes!!.armLength = level2Category.titleValue
            "Inseam (in)" -> decryptedClothingSizes!!.inseam = level2Category.titleValue
            "Torso (in)" -> decryptedClothingSizes!!.torso = level2Category.titleValue

            "Notes" -> decryptedClothingSizes!!.notes = level2Category.titleValue

            "clothing" -> decryptedClothingSizes!!.clothing = level2Category.titleValue
            "shoes" -> decryptedClothingSizes!!.shoes = level2Category.titleValue

            "jacketsNumericSize" -> decryptedClothingSizes!!.jacketsNumericSize = level2Category.titleValue
            "toddlerSize" -> decryptedClothingSizes!!.toddlerSize = level2Category.titleValue
            "kidsize" -> decryptedClothingSizes!!.kidSize = level2Category.titleValue

            "beltsSize" -> decryptedClothingSizes!!.beltSize = level2Category.titleValue
            "beltsNumericSize" -> decryptedClothingSizes!!.beltsNumericSize = level2Category.titleValue

            "Neck (in)" -> decryptedClothingSizes!!.neck = level2Category.titleValue
            "Chest (in)" -> decryptedClothingSizes!!.chest = level2Category.titleValue

            "beltsNumericSize" -> decryptedClothingSizes!!.beltsNumericSize = level2Category.titleValue

            "pantsSize" -> decryptedClothingSizes!!.pantsSize = level2Category.titleValue
            "pantsNumericSize" -> decryptedClothingSizes!!.pantsNumericSize = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedClothingSizes!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedClothingSizes!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }


    private fun setRecentPurchases(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Item" -> decryptedRecentPurchase!!.itemName = level2Category.titleValue
            "Brand/Retailer" -> decryptedRecentPurchase!!.brandName = level2Category.titleValue
            "Purchased by" -> decryptedRecentPurchase!!.purchasedBy = level2Category.titleValue
            "Purchase date" -> decryptedRecentPurchase!!.purchasedDate = level2Category.titleValue
            "Purchase price ($)" -> decryptedRecentPurchase!!.purchasedPrice = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedRecentPurchase!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedRecentPurchase!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setLoyaltyPrograms(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Brand/Retailer" -> decryptedLoyaltyPrograms!!.brandName = level2Category.titleValue
            "Account name" -> decryptedLoyaltyPrograms!!.accountName = level2Category.titleValue
            "Name on account" -> decryptedLoyaltyPrograms!!.nameOnAccount = level2Category.titleValue
            "Account number" -> decryptedLoyaltyPrograms!!.accountNumber = level2Category.titleValue
            "Website" -> decryptedLoyaltyPrograms!!.website = level2Category.titleValue
            "Username/login" -> decryptedLoyaltyPrograms!!.userName = level2Category.titleValue
            "Password" -> decryptedLoyaltyPrograms!!.password = level2Category.titleValue
            "PIN" -> decryptedLoyaltyPrograms!!.pin = level2Category.titleValue
            "Notes" -> decryptedLoyaltyPrograms!!.notes = level2Category.titleValue

            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedLoyaltyPrograms!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedLoyaltyPrograms!!.attachmentNames = level2Category.titleValue
                }
            }
        }
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
            "Name of landlord" -> decryptedProperty!!.tenantName = level2Category.titleValue
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

    private fun setDriversLicense(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Description" -> decryptedDriversLicense!!.lic_description = level2Category.titleValue
            "Name on license" -> decryptedDriversLicense!!.nameOnLicense = level2Category.titleValue
            "Issuing country" -> decryptedDriversLicense!!.issuingCountry = level2Category.titleValue
            "License number" -> decryptedDriversLicense!!.licenseNumber = level2Category.titleValue
            "Date issued" -> decryptedDriversLicense!!.dateIssued = level2Category.titleValue
            "Expiration date" -> decryptedDriversLicense!!.expirationDate = level2Category.titleValue
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
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedOtherGovernment!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedOtherGovernment!!.attachmentNames = level2Category.titleValue
                }
            }
        }
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
            "Places to visit/consider 1" -> decryptedVacations!!.placesToVisit_1 = level2Category.titleValue
            "Places to visit/consider 2" -> decryptedVacations!!.placesToVisit_2 = level2Category.titleValue
            "Places to visit/consider 3" -> decryptedVacations!!.placesToVisit_3 = level2Category.titleValue
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


    private fun setIdentification(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Name" -> decryptedIdentification!!.name = level2Category.titleValue
            "Gender" -> decryptedIdentification!!.gender = level2Category.titleValue
            "Age" -> decryptedIdentification!!.age = level2Category.titleValue
            "Date of birth" -> decryptedIdentification!!.dateofBirth = level2Category.titleValue
            "Height (ft, in)" -> decryptedIdentification!!.height = level2Category.titleValue
            "Weight (lbs)" -> decryptedIdentification!!.weight = level2Category.titleValue
            "Hair color" -> decryptedIdentification!!.hairColor = level2Category.titleValue
            "Eye color" -> decryptedIdentification!!.eyeColor = level2Category.titleValue
            "Visible marks" -> decryptedIdentification!!.visibleMarks = level2Category.titleValue
            "Blood type" -> decryptedIdentification!!.bloodType = level2Category.titleValue
            "Organ donor" -> decryptedIdentification!!.orgonDonor = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedIdentification!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedIdentification!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setMedicalHistory(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "History" -> decryptedMedicalHistory!!.history = level2Category.titleValue
            "pastConditions" -> decryptedMedicalHistory!!.treatmentDiscription = level2Category.titleValue
            "immunications" -> decryptedMedicalHistory!!.immunizationDiscription = level2Category.titleValue
            "family" -> decryptedMedicalHistory!!.familyDiscription = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedMedicalHistory!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedMedicalHistory!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setHealthCareProviders(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Name" -> decryptedHealthcareProviders!!.name = level2Category.titleValue
            "Type of physician" -> decryptedHealthcareProviders!!.physicianType = level2Category.titleValue
            "Practice/Group name" -> decryptedHealthcareProviders!!.practiceName = level2Category.titleValue
            "Phone number 1" -> decryptedHealthcareProviders!!.phoneNumberOne = level2Category.titleValue
            "Phone number 2" -> decryptedHealthcareProviders!!.phoneNumberTwo = level2Category.titleValue
            "Email address" -> decryptedHealthcareProviders!!.emailAddress = level2Category.titleValue
            "Street Address 1" -> decryptedHealthcareProviders!!.streetAddressOne = level2Category.titleValue
            "Street Address 2" -> decryptedHealthcareProviders!!.streetAddressTwo = level2Category.titleValue
            "City" -> decryptedHealthcareProviders!!.city = level2Category.titleValue
            "State" -> decryptedHealthcareProviders!!.state = level2Category.titleValue
            "Zip code" -> decryptedHealthcareProviders!!.zipCode = level2Category.titleValue
            "Country" -> decryptedHealthcareProviders!!.country = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedHealthcareProviders!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedHealthcareProviders!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setEmergencyContacts(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Name" -> decryptedEmergencyContacts!!.name = level2Category.titleValue
            "Relationship" -> decryptedEmergencyContacts!!.relationShip = level2Category.titleValue
            "Phone number 1" -> decryptedEmergencyContacts!!.phoneNumberOne = level2Category.titleValue
            "Phone number 2" -> decryptedEmergencyContacts!!.phoneNumberTwo = level2Category.titleValue
            "Email address" -> decryptedEmergencyContacts!!.emailAddress = level2Category.titleValue
            "Street Address 1" -> decryptedEmergencyContacts!!.streetAddressOne = level2Category.titleValue
            "Street Address 2" -> decryptedEmergencyContacts!!.streetAddressTwo = level2Category.titleValue
            "City" -> decryptedEmergencyContacts!!.city = level2Category.titleValue
            "State" -> decryptedEmergencyContacts!!.state = level2Category.titleValue
            "Zip code" -> decryptedEmergencyContacts!!.zipCode = level2Category.titleValue
            "Country" -> decryptedEmergencyContacts!!.country = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedEmergencyContacts!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedEmergencyContacts!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setMedications(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Name" -> decryptedMedications!!.name = level2Category.titleValue
            "Dose/strength" -> decryptedMedications!!.strength = level2Category.titleValue
            "Frequency" -> decryptedMedications!!.frequency = level2Category.titleValue
            "Start date" -> decryptedMedications!!.startDate = level2Category.titleValue
            "End date" -> decryptedMedications!!.endDate = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedMedications!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedMedications!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setMedicalConditions(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Condition" -> decryptedMedicalConditions!!.condition = level2Category.titleValue
            "Date diagnosed" -> decryptedMedicalConditions!!.dateDiagnosed = level2Category.titleValue
            "Description" -> decryptedMedicalConditions!!.medi_description = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedMedicalConditions!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedMedicalConditions!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setEyeGlassPrescriptions(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Physician name" -> decryptedEyeglassPrescriptions!!.physicianName = level2Category.titleValue
            "Date prescribed" -> decryptedEyeglassPrescriptions!!.datePrescribed = level2Category.titleValue

            "odSphereValue" -> decryptedEyeglassPrescriptions!!.odSphereValue = level2Category.titleValue
            "osSphereValue" -> decryptedEyeglassPrescriptions!!.osSphereValue = level2Category.titleValue

            "odCylinderValue" -> decryptedEyeglassPrescriptions!!.odCylinderValue = level2Category.titleValue
            "osCylinderValue" -> decryptedEyeglassPrescriptions!!.osCylinderValue = level2Category.titleValue

            "odAxisValue" -> decryptedEyeglassPrescriptions!!.odAxisValue = level2Category.titleValue
            "osAxisValue" -> decryptedEyeglassPrescriptions!!.osAxisValue = level2Category.titleValue

            "odPrismValue" -> decryptedEyeglassPrescriptions!!.odPrismValue = level2Category.titleValue
            "osPrismValue" -> decryptedEyeglassPrescriptions!!.osPrismValue = level2Category.titleValue

            "odAddValue" -> decryptedEyeglassPrescriptions!!.odAddValue = level2Category.titleValue
            "osAddValue" -> decryptedEyeglassPrescriptions!!.osAddValue = level2Category.titleValue

            "odBaseValue" -> decryptedEyeglassPrescriptions!!.odBaseValue = level2Category.titleValue
            "osBaseValue" -> decryptedEyeglassPrescriptions!!.osBaseValue = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedEyeglassPrescriptions!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedEyeglassPrescriptions!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setVitalNumbers(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Description" -> decryptedVitalNumbers!!.vital_description = level2Category.titleValue
            "Measurement date" -> decryptedVitalNumbers!!.measurementDate = level2Category.titleValue
            "Height (ft, in)" -> decryptedVitalNumbers!!.height = level2Category.titleValue
            "Weight (lbs)" -> decryptedVitalNumbers!!.weight = level2Category.titleValue
            "Waist (in)" -> decryptedVitalNumbers!!.waist = level2Category.titleValue
            "Body fat (%)" -> decryptedVitalNumbers!!.bodyFat = level2Category.titleValue
            "Body mass index (BMI kg/m2)" -> decryptedVitalNumbers!!.bodyMassIndex = level2Category.titleValue
            "Blood pressure (xx/yy)" -> decryptedVitalNumbers!!.bloodPressure = level2Category.titleValue
            "Heart rate (bpm)" -> decryptedVitalNumbers!!.heartRate = level2Category.titleValue
            "Total cholesterol (mg/dL)" -> decryptedVitalNumbers!!.totalCholesterol = level2Category.titleValue
            "HDL cholesterol (mg/dL)" -> decryptedVitalNumbers!!.hdlCholesterol = level2Category.titleValue
            "LDL cholesterol (mg/dL)" -> decryptedVitalNumbers!!.ldlCholesterol = level2Category.titleValue
            "Cholesterol ratio (Total cholesterol/HDL)" -> decryptedVitalNumbers!!.cholesterolRatio = level2Category.titleValue
            "Triglycerides (mg/dL)" -> decryptedVitalNumbers!!.triglycerides = level2Category.titleValue
            "Blood glucose (mg/dL)" -> decryptedVitalNumbers!!.bloodGlucose = level2Category.titleValue
            "Hemoglobin A1C (%)" -> decryptedVitalNumbers!!.hemoglobin = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedVitalNumbers!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedVitalNumbers!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setCheckUps(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Description" -> decryptedCheckups!!.checkup_description = level2Category.titleValue
            "Physician name" -> decryptedCheckups!!.physicianName = level2Category.titleValue
            "Type of physician" -> decryptedCheckups!!.physicianType = level2Category.titleValue
            "Reason" -> decryptedCheckups!!.reason = level2Category.titleValue
            "Date of visit" -> decryptedCheckups!!.dateOfVisit = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedCheckups!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedCheckups!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    fun saveDocument(context: Context, combineItem: Parcelable?, title: String, subTitle: String) {
        val currentUsers = NineBxApplication.getPreferences().userFirstName +" " +  NineBxApplication.getPreferences().userLastName
        val sdf = SimpleDateFormat(" E,MMM dd,yyyy, HH:mm")
        val currentDateandTime = sdf.format( Date())
        if (decryptedFinancial != null) {
            decryptedFinancial!!.selectionType = categoryID
            decryptedFinancial!!.institutionName = title
            decryptedFinancial!!.accountName = subTitle
            AppLogger.d("SelectionType ", "DecryptedFinancial" + decryptedFinancial!!.selectionType)
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = combineItem as DecryptedCombine
                            AppLogger.d("saveDocument", "Combine Id " + combine!!.id)
                            var combineRealm = realm!!.where(Combine::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(Combine::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptFinancial( decryptedFinancial!!)
                            if( combineRealm!!.financialItems.contains(encryptedObject) ) {
                                val index = combineRealm!!.financialItems.indexOf(encryptedObject)
                                if( index != -1 ) {
                                    combineRealm!!.financialItems[index] = (encryptedObject)
                                }
                            }
                            else {
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedPayment != null) {
            decryptedPayment!!.cardName = title
            decryptedPayment!!.selectionType = categoryID
            AppLogger.d("SelectionType ", "DecryptedPayment" + decryptedPayment!!.selectionType)

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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = combineItem as DecryptedCombine
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedProperty != null) {
            decryptedProperty!!.selectionType = categoryID
            decryptedProperty!!.propertyName = title
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)


            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = combineItem as DecryptedCombine
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedVehicle != null) {
            decryptedVehicle!!.vehicleName = title
            var isSaveComplete = false
            decryptedVehicle!!.selectionType = categoryID
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = combineItem as DecryptedCombine
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedAssets != null) {
            decryptedAssets!!.selectionType = categoryID
            decryptedAssets!!.assetName = title
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = combineItem as DecryptedCombine
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedInsurance != null) {
            decryptedInsurance!!.selectionType = categoryID
            decryptedInsurance!!.insuranceCompany = title
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = combineItem as DecryptedCombine
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedTaxes != null) {
            decryptedTaxes!!.selectionType = categoryID
            decryptedTaxes!!.returnName = title
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = combineItem as DecryptedCombine
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedDriversLicense != null) {
            decryptedDriversLicense!!.selectionType = categoryID
            decryptedDriversLicense!!.nameOnLicense = title
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combinePersonal: DecryptedCombinePersonal = combineItem as DecryptedCombinePersonal
                            var realmDriversLicense = realm!!.where(CombinePersonal::class.java).equalTo("id", combinePersonal.id).findFirst()
                            realm.beginTransaction()
                            if (realmDriversLicense == null) {
                                realmDriversLicense = realm.createObject(CombinePersonal::class.java, getUniqueId())
                            }
                            realmDriversLicense!!.licenseItems.add(encryptLicense(decryptedDriversLicense!!))
                            realm.copyToRealmOrUpdate(realmDriversLicense)
                            AppLogger.d("Adding ", " Combine personal")
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedSocial != null) {
            decryptedSocial!!.selectionType = categoryID
            decryptedSocial!!.cardName = title
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combinePersonal: DecryptedCombinePersonal = combineItem as DecryptedCombinePersonal
                            var realmSocial = realm!!.where(CombinePersonal::class.java).equalTo("id", combinePersonal.id).findFirst()
                            realm.beginTransaction()
                            if (realmSocial == null) {
                                realmSocial = realm.createObject(CombinePersonal::class.java, getUniqueId())
                            }
                            realmSocial!!.socialItems.add(encryptSocial(decryptedSocial!!))
                            realm.copyToRealmOrUpdate(realmSocial)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedTAX_ID != null) {
            decryptedTAX_ID!!.selectionType = categoryID
            decryptedTAX_ID!!.taxIdName = title
            decryptedTAX_ID!!.modified = currentUsers + " " + currentDateandTime

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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combinePersonal: DecryptedCombinePersonal = combineItem as DecryptedCombinePersonal
                            var realmTaxID = realm!!.where(CombinePersonal::class.java).equalTo("id", combinePersonal.id).findFirst()
                            realm.beginTransaction()
                            if (realmTaxID == null) {
                                realmTaxID = realm.createObject(CombinePersonal::class.java, getUniqueId())
                            }
                            realmTaxID!!.taxIDItems.add(encryptTaxID(decryptedTAX_ID!!))
                            realm.insertOrUpdate(realmTaxID)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedOtherGovernment != null) {
            decryptedOtherGovernment!!.selectionType = categoryID
            decryptedOtherGovernment!!.idName = title
            decryptedOtherGovernment!!.modified = currentUsers + " " + currentDateandTime
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combinePersonal: DecryptedCombinePersonal = combineItem as DecryptedCombinePersonal
                            var realmGovernment = realm!!.where(CombinePersonal::class.java).equalTo("id", combinePersonal.id).findFirst()
                            realm.beginTransaction()
                            if (realmGovernment == null) {
                                realmGovernment = realm.createObject(CombinePersonal::class.java, getUniqueId())
                            }
                            realmGovernment!!.governmentItems.add(encryptGovernment(decryptedOtherGovernment!!))
                            realm.copyToRealmOrUpdate(realmGovernment)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedCertificate != null) {
            decryptedCertificate!!.selectionType = categoryID
            decryptedCertificate!!.nameOnCertificate = title
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combinePersonal: DecryptedCombinePersonal = combineItem as DecryptedCombinePersonal
                            var realmCertificate = realm!!.where(CombinePersonal::class.java).equalTo("id", combinePersonal.id).findFirst()
                            realm.beginTransaction()
                            if (realmCertificate == null) {
                                realmCertificate = realm.createObject(CombinePersonal::class.java, getUniqueId())
                            }
                            realmCertificate!!.certificateItems.add(encryptCertificate(decryptedCertificate!!))
                            realm.copyToRealmOrUpdate(realmCertificate)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedIdentification != null) {
            decryptedIdentification!!.selectionType = categoryID
            decryptedIdentification!!.name = title
            decryptedIdentification!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedIdentification!!.id.toInt() == 0) {
                decryptedIdentification!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var identification = encryptIdentification(decryptedIdentification!!)
                            realm!!.insertOrUpdate(identification)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            var combineWellness: DecryptedCombineWellness = combineItem as DecryptedCombineWellness
                            var realmIdentification = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (realmIdentification == null) {
                                realmIdentification = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            realmIdentification!!.identificationItems.add(encryptIdentification(decryptedIdentification!!))
                            realm.insertOrUpdate(realmIdentification)
                            realm.commitTransaction()
                        }

                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedMedicalHistory != null) {
            decryptedMedicalHistory!!.selectionType = categoryID
            decryptedMedicalHistory!!.attachmentNames = title
            decryptedMedicalHistory!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedMedicalHistory!!.id.toInt() == 0) {
                decryptedMedicalHistory!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val medicalHistory = encryptMedicalHistory(decryptedMedicalHistory!!)
                            realm.insertOrUpdate(medicalHistory)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {

                            val combineWellness: DecryptedCombineWellness = combineItem as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.medicalHistoryItems.add(encryptMedicalHistory(decryptedMedicalHistory!!))
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedHealthcareProviders != null) {
            decryptedHealthcareProviders!!.selectionType = categoryID
            decryptedHealthcareProviders!!.name = title
            decryptedHealthcareProviders!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedHealthcareProviders!!.id.toInt() == 0) {
                decryptedHealthcareProviders!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val healthcareProviders = encryptHealthCareProviders(decryptedHealthcareProviders!!)
                            realm.insertOrUpdate(healthcareProviders)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            //val combine: DecryptedCombine = combineItem as DecryptedCombine
                            //AppLogger.d("saveDocument", "Combine Id " + combine!!.id)
                            val combineWellness: DecryptedCombineWellness = combineItem as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.healthcareProvidersItems.add(encryptHealthCareProviders(decryptedHealthcareProviders!!))
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedEmergencyContacts != null) {
            decryptedEmergencyContacts!!.selectionType = categoryID
            decryptedEmergencyContacts!!.name = title
            decryptedEmergencyContacts!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedEmergencyContacts!!.id.toInt() == 0) {
                decryptedEmergencyContacts!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val emergencyConatcts = encryptEmergencyContacts(decryptedEmergencyContacts!!)
                            realm.insertOrUpdate(emergencyConatcts)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineWellness: DecryptedCombineWellness = combineItem as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.emergencyContactsItems.add(encryptEmergencyContacts(decryptedEmergencyContacts!!))
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedMedications != null) {
            decryptedMedications!!.selectionType = categoryID
            decryptedMedications!!.name = title
            decryptedMedications!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedMedications!!.id.toInt() == 0) {
                decryptedMedications!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val medications = encryptMedications(decryptedMedications!!)
                            realm.insertOrUpdate(medications)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineWellness: DecryptedCombineWellness = combineItem as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.medicationsItems.add(encryptMedications(decryptedMedications!!))
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedMedicalConditions != null) {
            decryptedMedicalConditions!!.selectionType = categoryID
            decryptedMedicalConditions!!.attachmentNames = title
            decryptedMedicalConditions!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedMedicalConditions!!.id.toInt() == 0) {
                decryptedMedicalConditions!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val medicalConditions = encryptMedicalConditions(decryptedMedicalConditions!!)
                            realm.insertOrUpdate(medicalConditions)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineWellness: DecryptedCombineWellness = combineItem as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.medicalConditionsItems.add(encryptMedicalConditions(decryptedMedicalConditions!!))
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedEyeglassPrescriptions != null) {
            decryptedEyeglassPrescriptions!!.selectionType = categoryID
            decryptedEyeglassPrescriptions!!.attachmentNames = title
            decryptedEyeglassPrescriptions!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedEyeglassPrescriptions!!.id.toInt() == 0) {
                decryptedEyeglassPrescriptions!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val eyeglassPrescriptions = encryptEyeGlassPrescriptions(decryptedEyeglassPrescriptions!!)
                            realm.insertOrUpdate(eyeglassPrescriptions)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineWellness: DecryptedCombineWellness = combineItem as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.eyeglassPrescriptionsItems.add(encryptEyeGlassPrescriptions(decryptedEyeglassPrescriptions!!))
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedVitalNumbers != null) {
            decryptedVitalNumbers!!.selectionType = categoryID
            decryptedVitalNumbers!!.attachmentNames = title
            decryptedVitalNumbers!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedVitalNumbers!!.id.toInt() == 0) {
                decryptedVitalNumbers!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val vitalNumbers = encryptVitalNumbers(decryptedVitalNumbers!!)
                            realm.insertOrUpdate(vitalNumbers)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineWellness: DecryptedCombineWellness = combineItem as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.vitalNumbersItems.add(encryptVitalNumbers(decryptedVitalNumbers!!))
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedCheckups != null) {
            decryptedCheckups!!.selectionType = categoryID
            decryptedCheckups!!.attachmentNames = title
            decryptedCheckups!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedCheckups!!.id.toInt() == 0) {
                decryptedCheckups!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val checkUps = encryptCheckUps(decryptedCheckups!!)
                            realm.insertOrUpdate(checkUps)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineWellness: DecryptedCombineWellness = combineItem as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.checkupsItems.add(encryptCheckUps(decryptedCheckups!!))
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedLoyalty != null) {
            decryptedLoyalty!!.selectionType = categoryID
            if (decryptedLoyalty!!.selectionType.equals("travel_1001"))
                decryptedLoyalty!!.airLine = title
            if (decryptedLoyalty!!.selectionType.equals("travel_1002"))
                decryptedLoyalty!!.hotel = title
            if (decryptedLoyalty!!.selectionType.equals("travel_1003"))
                decryptedLoyalty!!.carRentalCompany = title
            if (decryptedLoyalty!!.selectionType.equals("travel_1004"))
                decryptedLoyalty!!.cruiseline = title
            if (decryptedLoyalty!!.selectionType.equals("travel_1005"))
                decryptedLoyalty!!.railway = title
            if (decryptedLoyalty!!.selectionType.equals("travel_1006"))
                decryptedLoyalty!!.other = title
            decryptedLoyalty!!.modified = currentUsers + " " + currentDateandTime
            decryptedLoyalty!!.accountName = subTitle

            AppLogger.d("LoyaltySelectionType", " " + decryptedLoyalty!!.selectionType)
            AppLogger.d("Level2Category", "decryptedLoyalty " + decryptedLoyalty)

            if (decryptedLoyalty!!.id.toInt() == 0) {
                decryptedLoyalty!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            AppLogger.d("saveDocument", "decryptedLoyalty" + decryptedLoyalty!!)
                            var getDecryptedLoyalty = realm!!.where(CombineTravel::class.java).findFirst()
                            AppLogger.d("getDecryptedLoyalty", "DecryptedCombineTravel" + getDecryptedLoyalty)

                            if(getDecryptedLoyalty!!.loyaltyItems.size != null){
                                for(loyaltyItems in getDecryptedLoyalty!!.loyaltyItems){
                                    var deleteLoyalty = realm!!.where(CombineTravel::class.java).equalTo("id", loyaltyItems.id).findAll()
                                    realm.beginTransaction()
                                    if( deleteLoyalty != null )
                                        deleteLoyalty.deleteAllFromRealm()
                                    realm.commitTransaction()
                                }
                            }

                            realm!!.beginTransaction()
                            val loyalty = encryptLoyalty(decryptedLoyalty!!)
                            realm.insertOrUpdate(loyalty)
                            realm.copyToRealmOrUpdate(loyalty)
                            realm.commitTransaction()
                          //  fragmentListContainer!!.setRecyclerView()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {

                            var realmLoyalty = realm!!.where(CombineTravel::class.java).findFirst()

                            if (realmLoyalty == null) {
                                realm.beginTransaction()
                                realmLoyalty = realm.createObject(CombineTravel::class.java, getUniqueId())
                                realm.commitTransaction()
                            }
                            for(loyaltyItems in realmLoyalty!!.loyaltyItems){
                                if (loyaltyItems!!.id.toInt() == decryptedLoyalty!!.id.toInt() ){
                                    var deleteLoyalty : RealmResults<CombineTravel> = realm!!.where(CombineTravel::class.java)
                                            .equalTo("id", loyaltyItems.id)
                                            .findAll()
                                    realm.beginTransaction()
                                    if( deleteLoyalty != null )
                                        deleteLoyalty.deleteAllFromRealm()
                                    realm.commitTransaction()
                                }
                            }
                            realm.beginTransaction()
                            realmLoyalty!!.loyaltyItems.add(encryptLoyality(decryptedLoyalty!!))
                            realm.copyToRealmOrUpdate(realmLoyalty)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedTravel != null) {
            decryptedTravel!!.selectionType = categoryID
            decryptedTravel!!.nameOnAccount = title
            decryptedTravel!!.accountName = subTitle
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
                            var travel = encryptTravel(decryptedTravel!!)
                            realm!!.insertOrUpdate(travel)
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineTravel: DecryptedCombineTravel = combineItem as DecryptedCombineTravel
                            var realmTravel = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmTravel == null) {
                                realmTravel = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            for(travelItems in realmTravel!!.travelItems){
                                if (travelItems!!.id.toInt() == decryptedTravel!!.id.toInt() ){
                                    var deleteTravel : RealmResults<CombineTravel> = realm!!.where(CombineTravel::class.java)
                                            .equalTo("id", decryptedTravel!!.id).findAll()
                                    deleteTravel.deleteAllFromRealm()
                                    realm.commitTransaction()
                                }
                            }
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedDocuments != null) {
            decryptedDocuments!!.selectionType = categoryID
            decryptedDocuments!!.nameOnTravelDocument = subTitle
            if (decryptedDocuments!!.selectionType.equals("travel_2001"))
                decryptedDocuments!!.passportName = title
            if (decryptedDocuments!!.selectionType.equals("travel_2002"))
                decryptedDocuments!!.visaName = title
            if (decryptedDocuments!!.selectionType.equals("travel_2003"))
                decryptedDocuments!!.travelDocumentTitle = title

            AppLogger.d("SelectionType", " " + decryptedDocuments!!.selectionType)
            AppLogger.d("SelectionType", " Visa Name " + decryptedDocuments!!.visaName)
            AppLogger.d("SelectionType", " Passport Name " + decryptedDocuments!!.passportName)
            AppLogger.d("SelectionType", " Travel Document " + decryptedDocuments!!.selectionType)
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
                            var documents = encryptDocuments(decryptedDocuments!!)
                            realm!!.insertOrUpdate(documents)
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineTravel: DecryptedCombineTravel = combineItem as DecryptedCombineTravel
                            var realmDocument = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmDocument == null) {
                                realmDocument = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            for(documentItems in realmDocument!!.documentsItems){
                                if (documentItems!!.id.toInt() == decryptedDocuments!!.id.toInt() ){
                                    var deleteDocuments : RealmResults<CombineTravel> = realm!!.where(CombineTravel::class.java)
                                            .equalTo("id", decryptedDocuments!!.id).findAll()
                                    deleteDocuments.deleteAllFromRealm()
                                    realm.commitTransaction()
                                }
                            }
                            realmDocument!!.documentsItems.add(encryptDocuments(decryptedDocuments!!))
                            realm.copyToRealmOrUpdate(realmDocument)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if (decryptedVacations != null) {
            decryptedVacations!!.selectionType = categoryID
            decryptedVacations!!.vac_description = title
            AppLogger.d("SelectionType", "decryptedVacations" + decryptedVacations!!.selectionType)
            decryptedVacations!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedVacations!!.id.toInt() == 0) {
                decryptedVacations!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val vacations = encryptVacations(decryptedVacations!!)
                            realm!!.insertOrUpdate(vacations)
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineTravel: DecryptedCombineTravel = combineItem as DecryptedCombineTravel
                            var realmVacations = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmVacations == null) {
                                realmVacations = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            for(vacationItems in realmVacations!!.vacationsItems){
                                if (vacationItems!!.id.toInt() == decryptedVacations!!.id.toInt() ){
                                    var deleteVacations : RealmResults<CombineTravel> = realm!!.where(CombineTravel::class.java)
                                            .equalTo("id", decryptedVacations!!.id).findAll()
                                    deleteVacations.deleteAllFromRealm()
                                    realm.commitTransaction()
                                }
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
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedLoyaltyPrograms != null) {
            decryptedLoyaltyPrograms!!.selectionType = categoryID
            decryptedLoyaltyPrograms!!.brandName = title
            decryptedLoyaltyPrograms!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedLoyaltyPrograms!!.id.toInt() == 0) {
                decryptedLoyaltyPrograms!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var loyaltyPrograms = encryptLoyaltyPrograms(decryptedLoyaltyPrograms!!)
                            realm.insertOrUpdate(loyaltyPrograms)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineShopping: DecryptedCombineShopping = combineItem as DecryptedCombineShopping
                            var realmLoyaltyPrograms = realm!!.where(CombineShopping::class.java).equalTo("id", combineShopping.id).findFirst()
                            realm.beginTransaction()
                            if (realmLoyaltyPrograms == null) {
                                realmLoyaltyPrograms = realm.createObject(CombineShopping::class.java, getUniqueId())
                            }
                            realmLoyaltyPrograms!!.loyaltyProgramsItems.add(encryptLoyaltyProgram(decryptedLoyaltyPrograms!!))
                            realm.copyToRealmOrUpdate(realmLoyaltyPrograms)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if (decryptedRecentPurchase != null) {
            decryptedRecentPurchase!!.selectionType = categoryID
            decryptedRecentPurchase!!.itemName = title
            decryptedRecentPurchase!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedRecentPurchase!!.id.toInt() == 0) {
                decryptedRecentPurchase!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var recentPurchase = encryptRecentPurchase(decryptedRecentPurchase!!)
                            realm!!.insertOrUpdate(recentPurchase)
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            val combineShopping: DecryptedCombineShopping = combineItem as DecryptedCombineShopping
                            var realmRecentPurchase = realm!!.where(CombineShopping::class.java).equalTo("id", combineShopping.id).findFirst()
                            realm.beginTransaction()
                            if (realmRecentPurchase == null) {
                                realmRecentPurchase = realm.createObject(CombineShopping::class.java, getUniqueId())
                            }
                            realmRecentPurchase!!.recentPurchaseItems.add(encryptRecentPurchase(decryptedRecentPurchase!!))
                            realm.copyToRealmOrUpdate(realmRecentPurchase)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if(decryptedClothingSizes != null){
            decryptedClothingSizes!!.selectionType = categoryID
            decryptedClothingSizes!!.personName = title
            decryptedClothingSizes!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedClothingSizes!!.id.toInt() == 0) {
                decryptedClothingSizes!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var clothingSize = encryptClothingSizes(decryptedClothingSizes!!)
                            realm!!.insertOrUpdate(clothingSize)
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            val combineShopping: DecryptedCombineShopping = combineItem as DecryptedCombineShopping
                            var realmClothingSizes = realm!!.where(CombineShopping::class.java).equalTo("id", combineShopping.id).findFirst()
                            realm.beginTransaction()
                            if (realmClothingSizes == null) {
                                realmClothingSizes = realm.createObject(CombineShopping::class.java, getUniqueId())
                            }
                            realmClothingSizes!!.clothingSizesItems.add(encryptClothingSizes(decryptedClothingSizes!!))
                            realm.copyToRealmOrUpdate(realmClothingSizes)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if(decryptedShopping != null){
            decryptedShopping!!.selectionType = categoryID
            decryptedShopping!!.userName = title
            decryptedShopping!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedShopping!!.id.toInt() == 0) {
                decryptedShopping!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var shopping = encryptShopping(decryptedShopping!!)
                            realm!!.insertOrUpdate(shopping)
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            val combineShopping: DecryptedCombineShopping = combineItem as DecryptedCombineShopping
                            var realmShopping = realm!!.where(CombineShopping::class.java).equalTo("id", combineShopping.id).findFirst()
                            realm.beginTransaction()
                            if (realmShopping == null) {
                                realmShopping = realm.createObject(CombineShopping::class.java, getUniqueId())
                            }
                            realmShopping!!.shoppingItems.add(encryptShopping(decryptedShopping!!))
                            realm.copyToRealmOrUpdate(realmShopping)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm()
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
    }
}
