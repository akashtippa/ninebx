package com.ninebx.ui.home.baseSubCategories

import com.ninebx.utility.Constants

/***
 * Created by TechnoBlogger on 23/01/18.
 */
class Level2CategoryHelper(
        val category_name: String,
        val categoryView: Level2CategoryView
) {

    init {
        when (category_name) {
            "Banking" -> {
                getBanking()
            }
            "Investments/Retirement" -> {
                getInvestments()
            }
            "Loan/Mortgages" -> {
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
        }
    }

    // Home & Money

    private fun getReturnsToBeFiled() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 2017
        var category_id = "returns_details" + categoryIndex
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

        categoryView.onSuccess(categoryList)
    }

    private fun getPastReturns() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2017
        var category_id = "past_details" + categoryIndex
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

        categoryView.onSuccess(categoryList)
    }

    private fun getOtherDetails() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2016
        var category_id = "other_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Asset Details"
        category.subCategories.add(Level2SubCategory("Estimated current market value", "Estimated current market value", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Serial number", "Serial number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", "Purchase date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase price", "Purchase price", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2016
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getFurnitureDetails() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2015
        var category_id = "furniture_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Asset Details"
        category.subCategories.add(Level2SubCategory("Estimated current market value", "Estimated current market value", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Serial number", "Serial number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", "Purchase date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase price", "Purchase price", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2015
        category_id = "furniture_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getComputerAndElectronics() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2014
        var category_id = "computer_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Asset Details"
        category.subCategories.add(Level2SubCategory("Estimated current market value", "Estimated current market value", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Serial number", "Serial number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", "Purchase date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase price", "Purchase price", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2014
        category_id = "computer_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getArtsAndCollectibles() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2013
        var category_id = "art_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Asset Details"
        category.subCategories.add(Level2SubCategory("Estimated current market value", "Estimated current market value", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Serial number", "Serial number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", "Purchase date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase price", "Purchase price", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2013
        category_id = "art_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getJewelry() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2012
        var category_id = "jewelry_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Asset Details"
        category.subCategories.add(Level2SubCategory("Estimated current market value", "Estimated current market value", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Serial number", "Serial number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", "Purchase date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase price", "Purchase price", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2012
        category_id = "jewelry_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getVehicles() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2011
        var category_id = "vehicle_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Vehicle Details"
        category.subCategories.add(Level2SubCategory("Vehicle identification number (VIN)", "Vehicle identification number (VIN)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Make", "Make", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Model", "Model", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Model year", "Model year", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Color", "Color", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name on title", "Name on title", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Estimated market value", "Estimated market value", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Registration expiration date", "Registration expiration date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchased", "Leased", "", Constants.LEVEL2_RADIO))
        category.subCategories.add(Level2SubCategory("Purchase date", "Purchase date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Financed through loan", "Financed through loan", "", Constants.LEVEL2_SWITCH))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2011
        category_id = "vehicle_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getInvestmentRentalProperty() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2010
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Property Address"
        category.subCategories.add(Level2SubCategory("Street address 1", "Street address 1", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Street address 2", "Street address 2", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("City", "City", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("State", "State", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Zip Code", "Zip Code", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Country", "Country", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2010
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Property Details"
        category.subCategories.add(Level2SubCategory("Name(s) on title", "Name(s) on title", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", "Purchase date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase price", "Purchase price", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Estimated market value", "Estimated market value", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2010
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Rental Details"
        category.subCategories.add(Level2SubCategory("Currently rented", "", "", Constants.LEVEL2_SWITCH))
        category.subCategories.add(Level2SubCategory("Name of tenant", "Name of tenant", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Lease start date", "Lease start date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Lease end date", "Lease end date", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2010
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getVacationHome() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2009
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Property Address"
        category.subCategories.add(Level2SubCategory("Street address 1", "Street address 1", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Street address 2", "Street address 2", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("City", "City", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("State", "State", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Zip Code", "Zip Code", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Country", "Country", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2009
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Property Details"
        category.subCategories.add(Level2SubCategory("Name(s) on title", "Name(s) on title", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", "Purchase date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase price", "Purchase price", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Estimated market value", "Estimated market value", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2009
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getPropertyRentedForOwnUse() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2008
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Property Address"
        category.subCategories.add(Level2SubCategory("Street address 1", "Street address 1", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Street address 2", "Street address 2", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("City", "City", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("State", "State", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Zip Code", "Zip Code", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Country", "Country", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2008
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Rental Details"
        category.subCategories.add(Level2SubCategory("Name of landlord", "Name of landlord", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Lease start date", "Lease start date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Lease end date", "Lease end date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2008
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getPrimaryHomeOwned() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2007
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Property Address"
        category.subCategories.add(Level2SubCategory("Street address 1", "Street address 1", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Street address 2", "Street address 2", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("City", "City", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("State", "State", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Zip Code", "Zip Code", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Country", "Country", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2007
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Property Details"
        category.subCategories.add(Level2SubCategory("Name(s) on title", "Name(s) on title", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase date", "Purchase date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Purchase price", "Purchase price", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Estimated market value", "Estimated market value", "", Constants.LEVEL2_USD))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2007
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getOtherPaymentAccounts() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2006
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", "Account type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", "Name(s) on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2006
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

        categoryView.onSuccess(categoryList)
    }

    private fun getCardDebitCardDetails() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2005
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Card Details"
        category.subCategories.add(Level2SubCategory("Card number", "Card number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Card type", "Card type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Card holder", "Card holder", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Expiry date", "Expiry date", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("CVV code", "CVV code", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("Issuing bank", "Issuing bank", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2005
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2005
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getOtherFinancialAccounts() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2004
        var category_id = "investment_retirement" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", "Account type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", "Name(s) on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("SWIFT/other code", "SWIFT/other code", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("ABA routing number", "ABA routing number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2004
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2004
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getLoadMortgages() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2003
        var category_id = "investment_retirement" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Loan type", "Loan type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", "Name(s) on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2003
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2003
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getInvestments() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2002
        var category_id = "investment_retirement" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", "Account type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", "Name(s) on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2002
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2002
        category_id = "investment_retirement" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getBanking() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2001
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Account type", "Account type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name(s) on account", "Name(s) on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("SWIFT/other code", "SWIFT/other code", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("ABA routing number", "ABA routing number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Contacts", "Contacts", "", Constants.LEVEL2_SPINNER))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2001
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

    // SERVICES/OTHER ACCOUNTS
    private fun getServicesOthersAccounts() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 3001
        var category_id = "service_details" + categoryIndex
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
        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    // PERSONAL
    private fun getOtherGovernmentIssuedID() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 2022
        var category_id = "other_certificate_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on ID", "Name on ID", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", "Issuing country", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Issuing state", "Issuing state", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("ID number", "ID number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", "Date issued", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Expiration date", "Expiration date", "", Constants.LEVEL2_NORMAL))

        categoryList.add(category)

        categoryIndex += 2022
        category_id = "other_certificate_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getMarriageCertificate() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 2021
        var category_id = "birth_certificate_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name 1 on certificate", "Name 1 on certificate", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Name 2 on certificate", "Name 2 on certificate", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date of marriage", "Date of marriage", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place of marriage", "Place of marriage", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2020
        category_id = "birth_certificate_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getBirthCertificate() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 2021
        var category_id = "birth_certificate_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on certificate", "Name on certificate", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Gender", "Gender", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date of birth", "Date of birth", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Time of birth", "Time of birth", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place of birth", "Place of birth", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2020
        category_id = "birth_certificate_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getTaxID() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 2020
        var category_id = "tax_details" + categoryIndex
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
        var categoryIndex = 2019
        var category_id = "social_security_details" + categoryIndex
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

        categoryView.onSuccess(categoryList)
    }

    private fun getDriversLicense() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 2018
        var category_id = "driver_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name on license", "Name on license", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", "Issuing country", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Issuing state", "Issuing state", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("License number", "License number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Data issued", "Data issued", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Expiration date", "Expiration date", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2018
        category_id = "other_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    // TRAVEL

    private fun getAirline() {
        val categoryList = ArrayList<Level2Category>()
        var categoryIndex = 2023
        var category_id = "airline_details" + categoryIndex
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
        var categoryIndex = 2024
        var category_id = "airline_details" + categoryIndex
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
        var categoryIndex = 2025
        var category_id = "airline_details" + categoryIndex
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
        var categoryIndex = 2026
        var category_id = "airline_details" + categoryIndex
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
        var categoryIndex = 2027
        var category_id = "airline_details" + categoryIndex
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
        var categoryIndex = 2028
        var category_id = "airline_details" + categoryIndex
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
        var categoryIndex = 2029
        var category_id = "airline_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Passport Details"
        category.subCategories.add(Level2SubCategory("Name on passport", "Name on passport", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", "Account number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Passport number", "Passport number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place issued", "Place issued", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", "Date issued", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Expiration date", "Expiration date", "", Constants.LEVEL2_NORMAL))
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
        var categoryIndex = 2030
        var category_id = "airline_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Visa Details"
        category.subCategories.add(Level2SubCategory("Name on visa", "Name on visa", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", "Account number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Visa type", "Visa type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Visa number", "Visa number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place issued", "Place issued", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", "Date issued", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Expiration date", "Expiration date", "", Constants.LEVEL2_NORMAL))
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
        var categoryIndex = 2031
        var category_id = "airline_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Travel Document Details"
        category.subCategories.add(Level2SubCategory("Name on travel document", "Name on travel document", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Issuing country", "Account number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Travel document type", "Travel document type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Travel document number", "Travel document number", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Place issued", "Place issued", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date issued", "Date issued", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Expiration date", "Expiration date", "", Constants.LEVEL2_NORMAL))
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

        var categoryIndex = 2032
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name", "Name", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Concenteration/major", "Concenteration/major", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("From", "From", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("To", "To", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Currently studying here", "", "", Constants.LEVEL2_SWITCH))
        categoryList.add(category)

        categoryIndex += 2032
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
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

        var categoryIndex = 2033
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name", "Name", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("From", "From", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("To", "To", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Currently studying here", "", "", Constants.LEVEL2_SWITCH))
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


}