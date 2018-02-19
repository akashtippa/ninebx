package com.ninebx.ui.home.search

import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedCombine
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.home.education.CombineEducation
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.ui.base.realm.home.interests.CombineInterests
import com.ninebx.ui.base.realm.home.memories.CombineMemories
import com.ninebx.ui.base.realm.home.personal.CombinePersonal
import com.ninebx.ui.base.realm.home.shopping.CombineShopping
import com.ninebx.ui.base.realm.home.travel.CombineTravel
import com.ninebx.ui.base.realm.home.wellness.CombineWellness
import com.ninebx.ui.home.baseCategories.CategoryView
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok on 03/01/18.
 */
class SearchPresenter {

    private var searchView: SearchView ?= null
    constructor( searchView: SearchView ) {
        this.searchView = searchView
        initialize()
    }

    private var categoryView : CategoryView ?= null
    private var categoryId : Int = -1
    constructor( categoryView: CategoryView, categoryId : Int  ) {
        this.categoryView = categoryView
        this.categoryId = categoryId
        initialize()
    }

    private val context = getApplicationContext()
    private val mDecryptCombine: DecryptedCombine = DecryptedCombine()
    private val mDecryptedCombineMemories = DecryptedCombineMemories()
    private val mDecryptedCombineTravel = DecryptedCombineTravel()
    private val mDecryptCombineEducation = DecryptedCombineEducation()
    private val mDecryptCombineInterests = DecryptedCombineInterests()
    private val mDecryptCombineWellness = DecryptedCombineWellness()
    private val mDecryptCombinePersonal = DecryptedCombinePersonal()
    private val mDecryptCombineShopping = DecryptedCombineShopping()
    private val mDecryptedCombineContacts = DecryptedCombineContacts()

    private fun initialize()
    {

        if( searchView != null ) {
            fetchCombine()
            fetchCombineTravel()
            fetchCombineMemories()
            fetchCombineEducation()
            fetchCombineInterests()
            fetchCombineWellness()
            fetchCombinePersonal()
            fetchCombineShopping()
            fetchCombineContacts()
        }
        else {
            searchView = categoryView
            fetchForCategory()
        }
    }

    private fun fetchForCategory() {
        when( categoryId ) {
            R.string.home_amp_money -> {
                fetchCombine()
            }
            R.string.travel -> {
                fetchCombineTravel()
            }
            R.string.contacts -> {
                //fetchCombineContacts()
            }
            R.string.education_work -> {
                fetchCombineEducation()
            }
            R.string.personal -> {
                fetchCombineEducation()
            }
            R.string.interests -> {
                fetchCombineInterests()
            }
            R.string.wellness -> {
                fetchCombineWellness()
            }
            R.string.memories -> {
                //fetchCombineMemories()
            }
            R.string.shopping -> {
                fetchCombineShopping()
            }
        }
    }

    private fun fetchCombineContacts() {
        prepareRealmConnections(context, false, "CombineContacts", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineContacts = realm!!.where(CombineContacts::class.java).findAll()
                if(combineContacts.size > 0){
                    for(i in 0 until combineContacts.size){
                        val decryptedCombineContacts = decryptCombineContacts(combineContacts[i]!!)
                        appendToDecryptCombineContacts(decryptedCombineContacts)
                    }
                    searchView!!.onCombineContactsFetched(mDecryptedCombineContacts)
                    AppLogger.d("Combine", "CombineContacts : " + mDecryptedCombineContacts)
                }
            }
        })
    }

    private fun fetchCombineShopping() {
        prepareRealmConnections(context, false, "CombineShopping", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineShopping = realm!!.where(CombineShopping::class.java).findAll()
                if(combineShopping.size > 0){
                    for(i in 0 until combineShopping.size){
                        val decryptedCombineShopping = decryptCombineShopping(combineShopping[i]!!)
                        appendToDecryptCombineShopping(decryptedCombineShopping)
                    }
                    searchView!!.onCombineShoppingFetched(mDecryptCombineShopping)
                    AppLogger.d("Combine", "CombineShopping : " + mDecryptCombineShopping)
                }
                else{
                    searchView!!.onCombineShoppingFetched(mDecryptCombineShopping)
                }
            }
        })
    }

    private fun fetchCombinePersonal() {
        prepareRealmConnections(context, false, "CombinePersonal", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combinePersonal = realm!!.where(CombinePersonal::class.java).findAll()
                if(combinePersonal.size > 0){
                    for(i in 0 until combinePersonal.size){
                        val decryptedCombinePersonal = decryptCombinePersonal(combinePersonal[i]!!)
                        appendToDecryptCombinePersonal(decryptedCombinePersonal)
                    }
                    searchView!!.onCombinePersonalFetched(mDecryptCombinePersonal)
                    AppLogger.d("Combine", "CombinePersonal : " + mDecryptCombinePersonal)
                }
                else{
                    searchView!!.onCombinePersonalFetched(mDecryptCombinePersonal)
                }
            }
        })
    }

    private fun fetchCombineWellness() {
        prepareRealmConnections(context, false, "CombineWellness", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineWellness = realm!!.where(CombineWellness::class.java).findAll()
                if(combineWellness.size > 0 ) {
                    for(i in 0 until combineWellness.size){
                        val decryptedCombineWellness = decryptCombineWellness(combineWellness[i]!!)
                        appendToDecryptCombineWellness(decryptedCombineWellness)
                    }
                    searchView!!.onCombineWellnessFetched(mDecryptCombineWellness)
                    AppLogger.d("Combine", "CombinedWellness : " + mDecryptCombineWellness)
                }
                else{
                    searchView!!.onCombineWellnessFetched(mDecryptCombineWellness)
                }
            }
        })
    }

    private fun fetchCombineInterests() {
        prepareRealmConnections(context, false, "CombineInterests", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineInterests = realm!!.where(CombineInterests::class.java).findAll()
                if(combineInterests.size > 0 ){
                    for(i in 0 until combineInterests.size){
                        val decryptedCombineInterests = decryptCombineInterests(combineInterests[i]!!)
                        appendToDecryptCombineInterests(decryptedCombineInterests)
                    }
                    searchView!!.onCombineInterestsFetched(mDecryptCombineInterests)
                    AppLogger.d("Combine", "CombinedInterests : " + mDecryptCombineInterests)
                }
                else{
                    searchView!!.onCombineInterestsFetched(mDecryptCombineInterests)
                }

            }
        })
    }

    private fun fetchCombineEducation() {
        prepareRealmConnections(context, false, "CombineEducation", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineEducation = realm!!.where(CombineEducation::class.java).findAll()
                if (combineEducation.size > 0) {
                    for (i in 0 until combineEducation.size) {
                        val decryptedCombineEducation = decryptCombineEducation(combineEducation[i]!!)
                        appendToDecryptCombineEducation(decryptedCombineEducation)
                    }
                    searchView!!.onCombineEducationFetched(mDecryptCombineEducation)
                    AppLogger.d("Combine", "Decrypted Combined Education : " + mDecryptCombineEducation)
                }
                else{
                    searchView!!.onCombineEducationFetched(mDecryptCombineEducation)
                }
            }
        })
    }

    private fun fetchCombineMemories() {
        prepareRealmConnections(context, false, "CombineMemories", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineMemories = realm!!.where(CombineMemories::class.java).findAll()
                if(combineMemories.size > 0){
                    for(i in 0 until combineMemories.size){
                        val decryptedCombineMemories = decryptCombineMemories(combineMemories[i]!!)
                        appendToDecryptCOmbineMemories(decryptedCombineMemories)
                    }
                    AppLogger.d("DecryptedCOmbineMemories", "Decrypted combine memories" + mDecryptedCombineMemories)
                    searchView!!.onCombineMemoryFetched(mDecryptedCombineMemories)
                }
                else{
                    searchView!!.onCombineMemoryFetched(mDecryptedCombineMemories)
                }
            }
        })
    }

    private fun fetchCombineTravel() {
        prepareRealmConnections(context, false, "CombineTravel", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineTravel = realm!!.where(CombineTravel::class.java).findAll()
                if (combineTravel.size > 0) {
                    for (i in 0 until combineTravel.size) {
                        val decryptedCombineTravel = decryptCombineTravel(combineTravel[i]!!)
                        appendToDecryptCombineTravel(decryptedCombineTravel)
                    }
                    AppLogger.d("CombineTravel", "Decrypted combine travel" + mDecryptedCombineTravel)
                    searchView!!.onCombineTravelFetched(mDecryptedCombineTravel)
                } else {
                    searchView!!.onCombineTravelFetched(mDecryptedCombineTravel)
                }
                AppLogger.d("Combine", "CombinedTravel : " + combineTravel)
            }
        })
    }

    private fun fetchCombine() {
        prepareRealmConnections(context, false, "Combine", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineResult = realm!!.where(Combine::class.java).findAll()
                if( combineResult.size > 0 ) {
                    for(i in 0 until combineResult.size ){
                        val decryptedCombine = decryptCombine(combineResult[i]!!)
                        appendToDecrypt(decryptedCombine)
                    }
                    AppLogger.d("COmbineDecrypted", "Decrypted combine financial" + mDecryptCombine)
                    searchView!!.onCombineFetched(mDecryptCombine)
                }
                else {
                    searchView!!.onCombineFetched(mDecryptCombine)
                }
            }
        })
    }

    private fun appendToDecrypt(decryptedCombine: DecryptedCombine) {
        mDecryptCombine.listItems.addAll(decryptedCombine.listItems)
        mDecryptCombine.propertyItems.addAll(decryptedCombine.propertyItems)
        mDecryptCombine.vehicleItems.addAll(decryptedCombine.vehicleItems)
        mDecryptCombine.taxesItems.addAll(decryptedCombine.taxesItems)
        mDecryptCombine.insuranceItems.addAll(decryptedCombine.insuranceItems)
        mDecryptCombine.assetItems.addAll(decryptedCombine.assetItems)
        mDecryptCombine.paymentItems.addAll(decryptedCombine.paymentItems)
        mDecryptCombine.financialItems.addAll(decryptedCombine.financialItems)
    }

    private fun appendToDecryptCombineTravel(decryptedCombineTravel: DecryptedCombineTravel) {
        mDecryptedCombineTravel.documentsItems.addAll(decryptedCombineTravel.documentsItems)
        mDecryptedCombineTravel.loyaltyItems.addAll(decryptedCombineTravel.loyaltyItems)
        mDecryptedCombineTravel.travelItems.addAll(decryptedCombineTravel.travelItems)
        mDecryptedCombineTravel.vacationsItems.addAll(decryptedCombineTravel.vacationsItems)
        mDecryptedCombineTravel.listItems.addAll(decryptedCombineTravel.listItems)
    }

    private fun appendToDecryptCOmbineMemories(decryptedCombineMemories: DecryptedCombineMemories){
        mDecryptedCombineMemories.listItems.addAll(decryptedCombineMemories.listItems)
        mDecryptedCombineMemories.mainMemoriesItems.addAll(decryptedCombineMemories.mainMemoriesItems)
        mDecryptedCombineMemories.memoryTimelineItems.addAll(decryptedCombineMemories.memoryTimelineItems)
    }

    private fun appendToDecryptCombineEducation(decryptedCombineEducation: DecryptedCombineEducation){
        mDecryptCombineEducation.listItems.addAll(decryptedCombineEducation.listItems)
        mDecryptCombineEducation.educationItems.addAll(decryptedCombineEducation.educationItems)
        mDecryptCombineEducation.mainEducationItems.addAll(decryptedCombineEducation.mainEducationItems)
        mDecryptCombineEducation.workItems.addAll(decryptedCombineEducation.workItems)
    }

    private fun appendToDecryptCombineInterests(decryptedCombineInterests: DecryptedCombineInterests){
        mDecryptCombineInterests.interestItems.addAll(decryptedCombineInterests.interestItems)
        mDecryptCombineInterests.listItems.addAll(decryptedCombineInterests.listItems)
    }
    private fun appendToDecryptCombineWellness(decryptedCombineWellness: DecryptedCombineWellness){
        mDecryptCombineWellness.checkupsItems.addAll(decryptedCombineWellness.checkupsItems)
        mDecryptCombineWellness.emergencyContactsItems.addAll(decryptedCombineWellness.emergencyContactsItems)
        mDecryptCombineWellness.eyeglassPrescriptionsItems.addAll(decryptedCombineWellness.eyeglassPrescriptionsItems)
        mDecryptCombineWellness.healthcareProvidersItems.addAll(decryptedCombineWellness.healthcareProvidersItems)
        mDecryptCombineWellness.identificationItems.addAll(decryptedCombineWellness.identificationItems)
        mDecryptCombineWellness.medicalConditionsItems.addAll(decryptedCombineWellness.medicalConditionsItems)
        mDecryptCombineWellness.medicalHistoryItems.addAll(decryptedCombineWellness.medicalHistoryItems)
        mDecryptCombineWellness.medicationsItems.addAll(decryptedCombineWellness.medicationsItems)
        mDecryptCombineWellness.vitalNumbersItems.addAll(decryptedCombineWellness.vitalNumbersItems)
        mDecryptCombineWellness.wellnessItems.addAll(decryptedCombineWellness.wellnessItems)
        mDecryptCombineWellness.listItems.addAll(decryptedCombineWellness.listItems)
    }
    private fun appendToDecryptCombinePersonal(decryptedCombinePersonal: DecryptedCombinePersonal){
        mDecryptCombinePersonal.certificateItems.addAll(decryptedCombinePersonal.certificateItems)
        mDecryptCombinePersonal.governmentItems.addAll(decryptedCombinePersonal.governmentItems)
        mDecryptCombinePersonal.licenseItems.addAll(decryptedCombinePersonal.licenseItems)
        mDecryptCombinePersonal.personalItems.addAll(decryptedCombinePersonal.personalItems)
        mDecryptCombinePersonal.socialItems.addAll(decryptedCombinePersonal.socialItems)
        mDecryptCombinePersonal.taxIDItems.addAll(decryptedCombinePersonal.taxIDItems)
        mDecryptCombinePersonal.listItems.addAll(decryptedCombinePersonal.listItems)
    }
    private fun appendToDecryptCombineShopping(decryptedCombineShopping: DecryptedCombineShopping){
        mDecryptCombineShopping.loyaltyProgramsItems.addAll(decryptedCombineShopping.loyaltyProgramsItems)
        mDecryptCombineShopping.recentPurchaseItems.addAll(decryptedCombineShopping.recentPurchaseItems)
        mDecryptCombineShopping.shoppingItems.addAll(decryptedCombineShopping.shoppingItems)
        mDecryptCombineShopping.clothingSizesItems.addAll(decryptedCombineShopping.clothingSizesItems)
        mDecryptCombineShopping.listItems.addAll(decryptedCombineShopping.listItems)
    }
    private fun appendToDecryptCombineContacts(decryptedCombineContacts: DecryptedCombineContacts){
        mDecryptedCombineContacts.contactsItems.addAll(decryptedCombineContacts.contactsItems)
        mDecryptedCombineContacts.mainContactsItems.addAll(decryptedCombineContacts.mainContactsItems)
        mDecryptedCombineContacts.listItems.addAll(decryptedCombineContacts.listItems)
    }

    fun searchHomeItems(text: String) : DecryptedCombine {
        if( text.contains("Home", ignoreCase = true) ) {
            return mDecryptCombine
        }
        val searchDecryptCombine = DecryptedCombine()
        var searchFinanceItems = ArrayList<DecryptedFinancial>()
        var searchPaymentItems = ArrayList<DecryptedPayment>()
        var searchPropertyItems = ArrayList<DecryptedProperty>()
        var searchVehicleItems = ArrayList<DecryptedVehicle>()
        var searchAssetItems = ArrayList<DecryptedAsset>()
        var searchInsuranceItems = ArrayList<DecryptedInsurance>()
        var searchTaxItems = ArrayList<DecryptedTax>()
        var searchHomeList = ArrayList<DecryptedHomeList>()
        AppLogger.d("Search", "Decryptex : " + mDecryptCombine.financialItems)
        for( financeItems in mDecryptCombine.financialItems ) {
            if( financeItems.selectionType.contains(text, true) || financeItems.institutionName.contains(text, true) || financeItems.accountName.contains(text, true) ||
                    financeItems.accountType.contains(text, true) || financeItems.nameOnAccount.contains(text, true) || financeItems.accountNumber.contains(text, true) ||
                    financeItems.location.contains(text, true) || financeItems.swiftCode.contains(text, true) || financeItems.abaRoutingNumber.contains(text, true) ||
                    financeItems.contacts.contains(text, true) || financeItems.website.contains(text, true) || financeItems.userName.contains(text, true) ||
                    financeItems.password.contains(text, true) ||  financeItems.pin.contains(text, true) || financeItems.created.contains(text, true) ||
                    financeItems.modified.contains(text, true) || financeItems.createdUser.contains(text, true) || financeItems.notes.contains(text, true)
                    || financeItems.attachmentNames.contains(text, true))

                searchFinanceItems.add(financeItems)
        }

        searchDecryptCombine.financialItems.addAll(searchFinanceItems)
        AppLogger.d("Search", "SearchItems : " + searchFinanceItems)
        AppLogger.d("Search", "DecryptedCombine : " + searchDecryptCombine)

        for( paymentItems in mDecryptCombine.paymentItems){

            if (paymentItems.selectionType.contains(text, true) || paymentItems.insuranceCompany.contains(text, true) || paymentItems.insuredProperty.contains(text, true) ||
                    paymentItems.insuredVehicle.contains(text, true) || paymentItems.insuredPerson.contains(text, true) || paymentItems.policyNumber.contains(text, true) ||
                    paymentItems.policyEffectiveDate.contains(text, true) || paymentItems.policyExpirationDate.contains(text, true) || paymentItems.contacts.contains(text, true) ||
                    paymentItems.website.contains(text, true) || paymentItems.userName.contains(text, true) || paymentItems.password.contains(text, true) ||
                    paymentItems.pin.contains(text, true) || paymentItems.created.contains(text, true) || paymentItems.modified.contains(text, true) ||
                    paymentItems.createdUser.contains(text, true) || paymentItems.notes.contains(text, true) || paymentItems.attachmentNames.contains(text, true))


                searchPaymentItems.add(paymentItems)
        }

        searchDecryptCombine.paymentItems.addAll(searchPaymentItems)
        AppLogger.d("Search", "SearchPayment : " + searchPaymentItems)

        for( propertyItems in mDecryptCombine.propertyItems) {
            if(propertyItems.selectionType.contains(text, true) || propertyItems.propertyName.contains(text, true) || propertyItems.streetAddressOne.contains(text, true) ||
                    propertyItems.streetAddressTwo.contains(text, true) || propertyItems.city.contains(text, true) || propertyItems.state.contains(text, true) ||
                    propertyItems.zipCode.contains(text, true) || propertyItems.country.contains(text, true) || propertyItems.titleName.contains(text, true) ||
                    propertyItems.purchaseDate.contains(text, true) || propertyItems.purchasePrice.contains(text, true) || propertyItems.estimatedMarketValue.contains(text, true) ||
                    propertyItems.contacts.contains(text, true) || propertyItems.selectionType.contains(text, true) || propertyItems.tenantName.contains(text, true) ||
                    propertyItems.leaseEndDate.contains(text, true) || propertyItems.leaseStartDate.contains(text, true) || propertyItems.created.contains(text, true) ||
                    propertyItems.modified.contains(text, true) || propertyItems.createdUser.contains(text, true) || propertyItems.notes.contains(text, true) || propertyItems.attachmentNames.contains(text, true))

                searchPropertyItems.add(propertyItems)
        }

        searchDecryptCombine.propertyItems.addAll(searchPropertyItems)
        AppLogger.d("Search", "SearchProperty : " + searchPropertyItems)

        for(vehicleItems in mDecryptCombine.vehicleItems){
            if(vehicleItems.selectionType.contains(text, true) || vehicleItems.vehicleName.contains(text, true) || vehicleItems.licenseNumber.contains(text, true) ||
                    vehicleItems.vinNumber.contains(text, true) || vehicleItems.make.contains(text, true) || vehicleItems.model.contains(text, true) || vehicleItems.modelYear.contains(text, true) ||
                    vehicleItems.color.contains(text, true) || vehicleItems.titleName.contains(text, true) || vehicleItems.estimatedMarketValue.contains(text, true) || vehicleItems.registrationExpirydate.contains(text, true) ||
                    vehicleItems.purchasedOrLeased.contains(text, true) || vehicleItems.purchaseDate.contains(text, true) || vehicleItems.financedThroughLoan.contains(text, true) ||
                    vehicleItems.created.contains(text, true) || vehicleItems.modified.contains(text, true) || vehicleItems.createdUser.contains(text, true) || vehicleItems.leaseStartDate.contains(text, true) ||
                    vehicleItems.leaseEndDate.contains(text, true) || vehicleItems.contacts.contains(text, true) || vehicleItems.maintenanceEvent.contains(text, true) || vehicleItems.serviceProviderName.contains(text, true) ||
                    vehicleItems.dateOfService.contains(text, true) || vehicleItems.vehicle.contains(text, true) || vehicleItems.notes.contains(text, true) || vehicleItems.attachmentNames.contains(text, true) )

                searchVehicleItems.add(vehicleItems)
        }
        searchDecryptCombine.vehicleItems.addAll(searchVehicleItems)
        AppLogger.d("Search", "SearchVehicle" + searchVehicleItems)

        for(assetItems in mDecryptCombine.assetItems)
        {
            if(assetItems.selectionType.contains(text, true) || assetItems.test.contains(text, true) || assetItems.assetName.contains(text, true) || assetItems.descriptionOrLocation.contains(text, true)
                    || assetItems.estimatedMarketValue.contains(text, true) || assetItems.serialNumber.contains(text, true) || assetItems.purchaseDate.contains(text, true)
                    || assetItems.purchasePrice.contains(text, true) || assetItems.contacts.contains(text, true) || assetItems.created.contains(text, true) || assetItems.modified.contains(text, true)
                    || assetItems.createdUser.contains(text, true) || assetItems.notes.contains(text, true) || assetItems.imageName.contains(text) || assetItems.attachmentNames.contains(text, true))

                searchAssetItems.add(assetItems)
        }
        searchDecryptCombine.assetItems.addAll(searchAssetItems)
        AppLogger.d("Search", "SearchAsset" + searchAssetItems)

        for(insuranceItems in mDecryptCombine.insuranceItems){
            if(insuranceItems.selectionType.contains(text, true) || insuranceItems.insuranceCompany.contains(text, true) || insuranceItems.insuredProperty.contains(text, true) || insuranceItems.insuredVehicle.contains(text, true)
                    || insuranceItems.insuredPerson.contains(text, true) || insuranceItems.policyNumber.contains(text, true) ||  insuranceItems.policyEffectiveDate.contains(text, true) || insuranceItems.policyExpirationDate.contains(text, true)
                    || insuranceItems.contacts.contains(text, true) || insuranceItems.website.contains(text, true) || insuranceItems.userName.contains(text, true) || insuranceItems.password.contains(text, true) || insuranceItems.pin.contains(text, true)
                    || insuranceItems.created.contains(text, true) || insuranceItems.modified.contains(text, true)|| insuranceItems.createdUser.contains(text, true) || insuranceItems.notes.contains(text, true) || insuranceItems.attachmentNames.contains(text, true))

                searchInsuranceItems.add(insuranceItems)
        }
        searchDecryptCombine.insuranceItems.addAll(searchInsuranceItems)
        AppLogger.d("Search", "SearchInsurance" + searchInsuranceItems)

        for (taxItems in mDecryptCombine.taxesItems)
        {
            if (taxItems.selectionType.contains(text, true) || taxItems.returnName.contains(text, true) || taxItems.taxYear.contains(text, true) || taxItems.taxPayer.contains(text, true) || taxItems.contacts.contains(text, true)
                    || taxItems.imageName.contains(text, true) || taxItems.attachmentNames.contains(text, true) || taxItems.notes.contains(text, true) || taxItems.title.contains(text, true) || taxItems.created.contains(text,true)
                    || taxItems.modified.contains(text, true) || taxItems.createdUser.contains(text, true))

                searchTaxItems.add(taxItems)
        }
        searchDecryptCombine.taxesItems.addAll(searchTaxItems)
        AppLogger.d("Search", "SearchTax" + searchTaxItems)

        for(listItems in mDecryptCombine.listItems){
            if (listItems.selectionType.contains(text, true) || listItems.classType.contains(text, true) || listItems.listName.contains(text, true) || listItems.dueDate.contains(text, true)
                    || listItems.created.contains(text,true ) || listItems.modified.contains(text, true) || listItems.createdUser.contains(text, true))

                searchHomeList.add(listItems)
        }
        searchDecryptCombine.listItems.addAll(searchHomeList)
        AppLogger.d("Search", "SearchHomeList" + searchHomeList)

        return searchDecryptCombine
    }

    fun searchTravelItems(text: String): DecryptedCombineTravel {
        if (text.contains("Travels", ignoreCase = true)) {
            return mDecryptedCombineTravel
        }

        val searchDecryptCombineTravel = DecryptedCombineTravel()
        var searchDocumentItems = ArrayList<DecryptedDocuments>()
        var searchLoyaltyItems = ArrayList<DecryptedLoyalty>()
        var searchTravelItems = ArrayList<DecryptedTravel>()
        var searchVacationItems = ArrayList<DecryptedVacations>()
        var searchListItems = ArrayList<DecryptedTravelList>()

        for (documentsItems in mDecryptedCombineTravel.documentsItems){
            if(documentsItems.selectionType.contains(text, true) || documentsItems.passportName.contains(text, true) || documentsItems.visaName.contains(text, true)
                    || documentsItems.nameOnPassport.contains(text, true) || documentsItems.nameOnVisa.contains(text, true) || documentsItems.visaType.contains(text, true)
                    || documentsItems.visaNumber.contains(text, true) || documentsItems.travelDocumentTitle.contains(text, true) || documentsItems.nameOnTravelDocument.contains(text, true)
                    || documentsItems.travelDocumentType.contains(text, true) || documentsItems.travelDocumentNumber.contains(text, true) || documentsItems.issuingCountry.contains(text, true)
                    || documentsItems.passportNumber.contains(text, true) || documentsItems.placeIssued.contains(text, true) || documentsItems.dateIssued.contains(text, true)
                    || documentsItems.expirationDate.contains(text, true) || documentsItems.notes.contains(text, true) || documentsItems.attachmentNames.contains(text, true)
                    || documentsItems.created.contains(text, true) || documentsItems.modified.contains(text, true) || documentsItems.createdUser.contains(text, true))
                searchDocumentItems.add(documentsItems)
        }
        searchDecryptCombineTravel.documentsItems.addAll(searchDocumentItems)

        for(loyaltyItems in mDecryptedCombineTravel.loyaltyItems){
            if (loyaltyItems.selectionType.contains(text, true) || loyaltyItems.airLine.contains(text, true) || loyaltyItems.hotel.contains(text, true) || loyaltyItems.carRentalCompany.contains(text, true)
                    || loyaltyItems.cruiseline.contains(text, true) || loyaltyItems.railway.contains(text, true) || loyaltyItems.other.contains(text, true) || loyaltyItems.accountName.contains(text, true)
                    || loyaltyItems.nameOnAccount.contains(text, true) || loyaltyItems.accountNumber.contains(text, true) || loyaltyItems.website.contains(text, true) || loyaltyItems.userName.contains(text, true)
                    || loyaltyItems.password.contains(text, true) || loyaltyItems.pin.contains(text, true) || loyaltyItems.notes.contains(text, true) || loyaltyItems.attachmentNames.contains(text, true)
                    || loyaltyItems.created.contains(text, true) || loyaltyItems.modified.contains(text, true) || loyaltyItems.createdUser.contains(text, true))
                searchLoyaltyItems.add(loyaltyItems)
        }
        searchDecryptCombineTravel.loyaltyItems.addAll(searchLoyaltyItems)

        for(travelItems in mDecryptedCombineTravel.travelItems){
            if(travelItems.selectionType.contains(text, true) || travelItems.institutionName.contains(text, true) || travelItems.accountName.contains(text, true) || travelItems.accountType.contains(text, true)
                    || travelItems.nameOnAccount.contains(text, true) || travelItems.accountNumber.contains(text, true) || travelItems.location.contains(text, true) || travelItems.swiftCode.contains(text, true)
                    || travelItems.abaRoutingNumber.contains(text, true) || travelItems.contacts.contains(text, true) || travelItems.website.contains(text, true) || travelItems.userName.contains(text, true)
                    || travelItems.password.contains(text, true) || travelItems.pin.contains(text, true) || travelItems.paymentMethodOnFile.contains(text, true) || travelItems.notes.contains(text, true)
                    || travelItems.imageName.contains(text, true) || travelItems.attachmentNames.contains(text, true) || travelItems.title.contains(text, true) || travelItems.created.contains(text, true)
                    || travelItems.modified.contains(text, true) || travelItems.createdUser.contains(text, true))
                searchTravelItems.add(travelItems)
        }
        searchDecryptCombineTravel.travelItems.addAll(searchTravelItems)

        for(vacationItems in mDecryptedCombineTravel.vacationsItems){
            if(vacationItems.selectionType.contains(text, true) || vacationItems.vac_description.contains(text, true) || vacationItems.startDate.contains(text, true) || vacationItems.endDate.contains(text, true)
                    || vacationItems.placesToVisit_1.contains(text, true) || vacationItems.placesToVisit_2.contains(text, true) || vacationItems.placesToVisit_3.contains(text, true)
                    || vacationItems.notes.contains(text, true) || vacationItems.attachmentNames.contains(text, true) || vacationItems.created.contains(text, true)
                    || vacationItems.modified.contains(text, true) || vacationItems.createdUser.contains(text, true))
                searchVacationItems.add(vacationItems)
        }
        searchDecryptCombineTravel.vacationsItems.addAll(searchVacationItems)

        for(listItems in mDecryptedCombineTravel.listItems){
            if(listItems.selectionType.contains(text, true) || listItems.classType.contains(text, true) || listItems.listName.contains(text, true) || listItems.dueDate.contains(text, true)
                    || listItems.created.contains(text, true) || listItems.modified.contains(text, true) || listItems.createdUser.contains(text, true))
                searchListItems.add(listItems)
        }
        searchDecryptCombineTravel.listItems.addAll(searchListItems)

        return searchDecryptCombineTravel
    }

    fun searchMemoryItems(text: String): DecryptedCombineMemories {
        if (text.contains("Memories", ignoreCase = true)) {
            return mDecryptedCombineMemories
        }
        val searchDecryptCombineMemories = DecryptedCombineMemories()
        var searchMainMemoryItems = ArrayList<DecryptedMainMemories>()
        var searchMemoryTimelineItems = ArrayList<DecryptedMemoryTimeline>()
        var searchMemorylistItems = ArrayList<DecryptedMemoriesList>()

        for (mainMemoryItems in mDecryptedCombineMemories.mainMemoriesItems) {
            if (mainMemoryItems.selectionType.contains(text, true) || mainMemoryItems.institutionName.contains(text, true) || mainMemoryItems.accountName.contains(text, true) || mainMemoryItems.accountType.contains(text, true)
                    || mainMemoryItems.nameOnAccount.contains(text, true) || mainMemoryItems.accountName.contains(text, true) || mainMemoryItems.location.contains(text, true) || mainMemoryItems.swiftCode.contains(text, true)
                    || mainMemoryItems.abaRoutingNumber.contains(text, true) || mainMemoryItems.contacts.contains(text, true) || mainMemoryItems.website.contains(text, true) || mainMemoryItems.userName.contains(text, true)
                    || mainMemoryItems.password.contains(text, true) || mainMemoryItems.pin.contains(text, true) || mainMemoryItems.paymentMethodOnFile.contains(text, true) || mainMemoryItems.created.contains(text, true)
                    || mainMemoryItems.modified.contains(text, true) || mainMemoryItems.notes.contains(text, true) || mainMemoryItems.attachmentNames.contains(text, true) || mainMemoryItems.title.contains(text, true)
                    || mainMemoryItems.createdUser.contains(text, true))
                searchMainMemoryItems.add(mainMemoryItems)
        }
        searchDecryptCombineMemories.mainMemoriesItems.addAll(searchMainMemoryItems)
        AppLogger.d("Search", "SearchMainMemoryItems" + searchMainMemoryItems)

        for (memoryTimelineItems in mDecryptedCombineMemories.memoryTimelineItems) {
            if (memoryTimelineItems.selectionType.contains(text, true) || memoryTimelineItems.title.contains(text, true) || memoryTimelineItems.date.contains(text, true)
                    || memoryTimelineItems.place.contains(text, true) || memoryTimelineItems.contacts.contains(text, true) || memoryTimelineItems.notes.contains(text, true)
                    || memoryTimelineItems.attachmentNames.contains(text, true) || memoryTimelineItems.created.contains(text, true) || memoryTimelineItems.modified.contains(text, true)
                    || memoryTimelineItems.created.contains(text, true))
                searchMemoryTimelineItems.add(memoryTimelineItems)
        }
        searchDecryptCombineMemories.memoryTimelineItems.addAll(searchMemoryTimelineItems)
        AppLogger.d("Search", "SearchMemoryTimeLineItems" + searchMainMemoryItems)

        for (listItems in mDecryptedCombineMemories.listItems) {
            if (listItems.selectionType.contains(text, true) || listItems.classType.contains(text, false) || listItems.listName.contains(text, false) || listItems.dueDate.contains(text, false)
                    || listItems.created.contains(text, false) || listItems.modified.contains(text, false) || listItems.createdUser.contains(text, false))
                searchMemorylistItems.add(listItems)
        }
        searchDecryptCombineMemories.listItems.addAll(searchMemorylistItems)
        AppLogger.d("Search", "SearchMemoryListItems" + searchMainMemoryItems)
        return searchDecryptCombineMemories
    }

    fun searchEducationItems(text: String) : DecryptedCombineEducation{
        if (text.contains("Education", ignoreCase = true)) {
            return mDecryptCombineEducation
        }
        val searchDecryptCombineEducation = DecryptedCombineEducation()
        var searchEducationItems = ArrayList<DecryptedEducation> ()
        var searchMainEduactionItems = ArrayList<DecryptedMainEducation>()
        var searchWorkItems = ArrayList<DecryptedWork>()
        var searchEducationListItems = ArrayList<DecryptedEducationList>()

        for(educationItems in mDecryptCombineEducation.educationItems){
            if(educationItems.selectionType.contains(text, true) || educationItems.institutionName.contains(text, true) || educationItems.accountName.contains(text, true)
                    || educationItems.accountType.contains(text, true) || educationItems.nameOnAccount.contains(text, true) || educationItems.accountNumber.contains(text, true)
                    || educationItems.location.contains(text, true) || educationItems.swiftCode.contains(text, true) || educationItems.abaRoutingNumber.contains(text, true)
                    || educationItems.contacts.contains(text, true) || educationItems.website.contains(text, true) || educationItems.userName.contains(text, true) || educationItems.password.contains(text, true)
                    || educationItems.pin.contains(text, true) || educationItems.paymentMethodOnFile.contains(text, true) || educationItems.notes.contains(text, true) || educationItems.attachmentNames.contains(text, true)
                    || educationItems.title.contains(text, true) || educationItems.created.contains(text, true) || educationItems.modified.contains(text, true) || educationItems.createdUser.contains(text, true))
                searchEducationItems.add(educationItems)
        }
        searchDecryptCombineEducation.educationItems.addAll(searchEducationItems)

        for(mainEducationItems in mDecryptCombineEducation.mainEducationItems){
            if(mainEducationItems.selectionType.contains(text, true) || mainEducationItems.classType.contains(text, true) || mainEducationItems.institutionName.contains(text, true)
                    || mainEducationItems.qualification.contains(text, true) || mainEducationItems.name.contains(text, true) || mainEducationItems.location.contains(text, true)
                    || mainEducationItems.major.contains(text, true) || mainEducationItems.from.contains(text, true) || mainEducationItems.to.contains(text, true)
                    || mainEducationItems.currentlyStudying.contains(text, true) || mainEducationItems.notes.contains(text, true) || mainEducationItems.created.contains(text, true)
                    || mainEducationItems.modified.contains(text, true) || mainEducationItems.attachmentNames.contains(text, true) || mainEducationItems.createdUser.contains(text, true))
                searchMainEduactionItems.add(mainEducationItems)
        }
        searchDecryptCombineEducation.mainEducationItems.addAll(searchMainEduactionItems)

        for(workItems in mDecryptCombineEducation.workItems){
            if(workItems.selectionType.contains(text, true) || workItems.classType.contains(text, true) || workItems.companyName.contains(text, true)  || workItems.position.contains(text, true)
                    || workItems.name.contains(text, true) || workItems.location.contains(text, true) || workItems.from.contains(text, true) || workItems.to.contains(text, true)
                    || workItems.currentWork.contains(text, true) || workItems.created.contains(text, true) || workItems.modified.contains(text, true) || workItems.notes.contains(text, true)
                    || workItems.attachmentNames.contains(text, true) || workItems.createdUser.contains(text, true))
                searchWorkItems.add(workItems)
        }
        searchDecryptCombineEducation.workItems.addAll(searchWorkItems)

        for(listItems in mDecryptCombineEducation.listItems){
            if(listItems.selectionType.contains(text, true) || listItems.classType.contains(text, true) || listItems.listName.contains(text, true) || listItems.dueDate.contains(text, true)
                    || listItems.created.contains(text, true) || listItems.modified.contains(text, true) || listItems.createdUser.contains(text, true))
                searchEducationListItems.add(listItems)
        }
        searchDecryptCombineEducation.listItems.addAll(searchEducationListItems)

        return searchDecryptCombineEducation
    }

    fun searchInterestItems(text : String) : DecryptedCombineInterests {
        if (text.contains("Education", ignoreCase = true)) {
            return mDecryptCombineInterests
        }
        val searchDecryptCombineInterests = DecryptedCombineInterests()
        val searchInterestItems = ArrayList<DecryptedInterests>()
        val searchInterestList = ArrayList<DecryptedInterestsList>()

        for(interestitems in mDecryptCombineInterests.interestItems){
            if(interestitems.selectionType.contains(text, true) || interestitems.institutionName.contains(text, true) || interestitems.accountName.contains(text, true)
                    || interestitems.accountType.contains(text, true) || interestitems.nameOnAccount.contains(text, true) || interestitems.accountNumber.contains(text, true)
                    || interestitems.location.contains(text, true) || interestitems.swiftCode.contains(text, true) || interestitems.abaRoutingNumber.contains(text, true)
                    || interestitems.contacts.contains(text, true) || interestitems.website.contains(text, true) || interestitems.userName.contains(text, true) || interestitems.password.contains(text, true)
                    || interestitems.pin.contains(text, true) || interestitems.paymentMethodOnFile.contains(text, true) || interestitems.notes.contains(text, true)
                    || interestitems.attachmentNames.contains(text, true) || interestitems.title.contains(text, true) || interestitems.created.contains(text, true)
                    || interestitems.modified.contains(text, true) || interestitems.createdUser.contains(text, true))
                searchInterestItems.add(interestitems)
        }
        searchDecryptCombineInterests.interestItems.addAll(searchInterestItems)

        for(listItems in mDecryptCombineInterests.listItems){
            if(listItems.selectionType.contains(text, true) || listItems.classType.contains(text, true) || listItems.listName.contains(text, true) || listItems.dueDate.contains(text, true)
                    || listItems.created.contains(text, true) || listItems.modified.contains(text, true) || listItems.createdUser.contains(text, true))
                searchInterestList.add(listItems)
        }
        searchDecryptCombineInterests.listItems.addAll(searchInterestList)

        return searchDecryptCombineInterests
    }

    fun searchWellnessItems(text : String) : DecryptedCombineWellness {
        if(text.contains("Wellness", true)){
            return mDecryptCombineWellness
        }
        val searchDecryptCombineWellness = DecryptedCombineWellness()
        var searchCheckupItems = ArrayList<DecryptedCheckups>()
        var searchEmergencyContacts = ArrayList<DecryptedEmergencyContacts>()
        var searchEyeglassPrescriptions = ArrayList<DecryptedEyeglassPrescriptions>()
        var searchhealthcareProviders = ArrayList<DecryptedHealthcareProviders>()
        var searchIdentification = ArrayList<DecryptedIdentification>()
        var searchMedicalConditions = ArrayList<DecryptedMedicalConditions>()
        var searchMedicalHistory = ArrayList<DecryptedMedicalHistory>()
        var searchMedications = ArrayList<DecryptedMedications>()
        var searchVitalNumber = ArrayList<DecryptedVitalNumbers>()
        var searchWellness = ArrayList<DecryptedWellness>()
        var searchWellnessList = ArrayList<DecryptedWellnessList>()
        /*  for(checkupItems in mDecryptCombineWellness.checkupsItems){
              if(checkupItems.selectionType.contains(text, true) || checkupItems.classType.contains(text, true) || checkupItems.physicianName.contains(text, true)
                      || checkupItems.checkup_description.contains(text, true) || checkupItems.physicianType.contains(text, true) || checkupItems.classType.contains(text, true)
                      || checkupItems.reason.contains(text, true) || checkupItems.dateOfVisit.contains(text, true) || checkupItems.notes.contains(text, true) || checkupItems.attachmentNames.contains(text, true)
                      || checkupItems.created.contains(text, true) || checkupItems.modified.contains(text, true)|| checkupItems.createdUser.contains(text, true))
                  searchCheckupItems.add(checkupItems)
          }
          searchDecryptCombineWellness.checkupsItems.addAll(searchCheckupItems)

          for(emergencyContactsItems in mDecryptCombineWellness.emergencyContactsItems){
              if(emergencyContactsItems.selectionType.contains(text, true) || emergencyContactsItems.classType.contains(text, true) || emergencyContactsItems.name.contains(text, true)
                      || emergencyContactsItems.relationShip.contains(text, true) || emergencyContactsItems.phoneNumberOne.contains(text, true) || emergencyContactsItems.phoneNumberTwo.contains(text, true)
                      || emergencyContactsItems.emailAddress.contains(text, true) || emergencyContactsItems.streetAddressOne.contains(text, true) || emergencyContactsItems.streetAddressTwo.contains(text, true)
                      || emergencyContactsItems.city.contains(text, true) || emergencyContactsItems.state.contains(text, true) || emergencyContactsItems.zipCode.contains(text, true)
                      || emergencyContactsItems.country.contains(text, true) || emergencyContactsItems.created.contains(text, true) || emergencyContactsItems.modified.contains(text, true)
                      || emergencyContactsItems.notes.contains(text, true) || emergencyContactsItems.attachmentNames.contains(text, true) || emergencyContactsItems.createdUser.contains(text, true))
                  searchEmergencyContacts.add(emergencyContactsItems)
          }
          searchDecryptCombineWellness.emergencyContactsItems.addAll(searchEmergencyContacts)

          for(eyeglassPrescriptionsItems in mDecryptCombineWellness.eyeglassPrescriptionsItems){
              if(eyeglassPrescriptionsItems.selectionType.contains(text, true) || eyeglassPrescriptionsItems.classType.contains(text, true) || eyeglassPrescriptionsItems.physicianName.contains(text, true)
                      || eyeglassPrescriptionsItems.datePrescribed.contains(text, true) || eyeglassPrescriptionsItems.odSphereValue.contains(text, true) || eyeglassPrescriptionsItems.osSphereValue.contains(text, true)
                      || eyeglassPrescriptionsItems.odCylinderValue.contains(text, true) || eyeglassPrescriptionsItems.osCylinderValue.contains(text, true) || eyeglassPrescriptionsItems.odAxisValue.contains(text, true)
                      || eyeglassPrescriptionsItems.osAxisValue.contains(text, true) || eyeglassPrescriptionsItems.odPrismValue.contains(text, true) || eyeglassPrescriptionsItems.osPrismValue.contains(text, true)
                      || eyeglassPrescriptionsItems.odAddValue.contains(text, true) || eyeglassPrescriptionsItems.osAddValue.contains(text, true) || eyeglassPrescriptionsItems.classType.contains(text, true)
                      || eyeglassPrescriptionsItems.odBaseValue.contains(text, true) || eyeglassPrescriptionsItems.osBaseValue.contains(text, true) || eyeglassPrescriptionsItems.notes.contains(text, true) || eyeglassPrescriptionsItems.attachmentNames.contains(text, true)
                      || eyeglassPrescriptionsItems.created.contains(text, true) || eyeglassPrescriptionsItems.modified.contains(text, true) || eyeglassPrescriptionsItems.createdUser.contains(text, true))
                  searchEyeglassPrescriptions.add(eyeglassPrescriptionsItems)
          }
          searchDecryptCombineWellness.eyeglassPrescriptionsItems.addAll(searchEyeglassPrescriptions)*/

        for(checkupItems in mDecryptCombineWellness.checkupsItems){
            if(performSearch(checkupItems, text)!!)
                searchCheckupItems.add(checkupItems)
        }
        searchDecryptCombineWellness.checkupsItems.addAll(searchCheckupItems)

        for(emergencyContactItems in mDecryptCombineWellness.emergencyContactsItems){
            if( performSearch(emergencyContactItems,text)!!)
                searchEmergencyContacts.add(emergencyContactItems)
        }
        searchDecryptCombineWellness.emergencyContactsItems.addAll(searchEmergencyContacts)

        for(eyeglassPrescriptionItems in mDecryptCombineWellness.eyeglassPrescriptionsItems){
            if(performSearch(eyeglassPrescriptionItems, text)!!)
                searchEyeglassPrescriptions.add(eyeglassPrescriptionItems)
        }
        searchDecryptCombineWellness.eyeglassPrescriptionsItems.addAll(searchEyeglassPrescriptions)

        for(healthCareProviderItems in mDecryptCombineWellness.healthcareProvidersItems){
            if( performSearch(healthCareProviderItems, text)!!)
                searchhealthcareProviders.add(healthCareProviderItems)
        }
        searchDecryptCombineWellness.healthcareProvidersItems.addAll(searchhealthcareProviders)

        for(identificationItems in mDecryptCombineWellness.identificationItems){
            if(performSearch(identificationItems, text)!!)
                searchIdentification.add(identificationItems)
        }
        searchDecryptCombineWellness.identificationItems.addAll(searchIdentification)

        for(medicalConditionsItems in mDecryptCombineWellness.medicalConditionsItems){
            if(performSearch(medicalConditionsItems, text)!!)
                searchMedicalConditions.add(medicalConditionsItems)
        }
        searchDecryptCombineWellness.medicalConditionsItems.addAll(searchMedicalConditions)

        for(medicalHistoryItems in mDecryptCombineWellness.medicalHistoryItems){
            if(performSearch(medicalHistoryItems, text)!!)
                searchMedicalHistory.add(medicalHistoryItems)
        }
        searchDecryptCombineWellness.medicalHistoryItems.addAll(searchMedicalHistory)

        for(medicationItems in mDecryptCombineWellness.medicationsItems){
            if(performSearch(medicationItems, text)!!)
                searchMedications.add(medicationItems)
        }
        searchDecryptCombineWellness.medicationsItems.addAll(searchMedications)

        for(vitalNumberItems in mDecryptCombineWellness.vitalNumbersItems){
            if(performSearch(vitalNumberItems, text)!!)
                searchVitalNumber.add(vitalNumberItems)
        }
        searchDecryptCombineWellness.vitalNumbersItems.addAll(searchVitalNumber)

        for(wellnessItems in mDecryptCombineWellness.wellnessItems){
            if(performSearch(wellnessItems, text)!!)
                searchWellness.add(wellnessItems)
        }
        searchDecryptCombineWellness.wellnessItems.addAll(searchWellness)

        for(listItems in mDecryptCombineWellness.listItems){
            if(performSearch(listItems, text)!!)
                searchWellnessList.add(listItems)
        }
        searchDecryptCombineWellness.listItems.addAll(searchWellnessList)

        return searchDecryptCombineWellness
    }
    fun searchPersonalItems(text : String) : DecryptedCombinePersonal{
        if(text.contains("Personal", true)){
            return mDecryptCombinePersonal
        }
        val searchDecryptCombinePersonal = DecryptedCombinePersonal()
        var searchCertifiacate = ArrayList<DecryptedCertificate>()
        var searchGovernment = ArrayList<DecryptedGovernment>()
        var searchLicense = ArrayList<DecryptedLicense>()
        var searchPersonal = ArrayList<DecryptedPersonal>()
        var searchSocial = ArrayList<DecryptedSocial>()
        var searchTaxID = ArrayList<DecryptedTaxID>()
        var searchPersonalList = ArrayList<DecryptedPersonalList>()

        for (certificateItems in mDecryptCombinePersonal.certificateItems){
            if(performSearch(certificateItems, text)!!)
                searchCertifiacate.add(certificateItems)
        }
        searchDecryptCombinePersonal.certificateItems.addAll(searchCertifiacate)
        for(governmentItems in mDecryptCombinePersonal.governmentItems){
            if(performSearch(governmentItems, text)!!)
                searchGovernment.add(governmentItems)
        }
        searchDecryptCombinePersonal.governmentItems.addAll(searchGovernment)
        for(liceneItems in mDecryptCombinePersonal.licenseItems){
            if(performSearch(liceneItems, text)!!)
                searchLicense.add(liceneItems)
        }
        searchDecryptCombinePersonal.licenseItems.addAll(searchLicense)
        for(personalItems in mDecryptCombinePersonal.personalItems){
            if(performSearch(personalItems, text)!!)
                searchPersonal.add(personalItems)
        }
        searchDecryptCombinePersonal.personalItems.addAll(searchPersonal)
        for(socialItems in mDecryptCombinePersonal.socialItems){
            if(performSearch(socialItems, text)!!)
                searchSocial.add(socialItems)
        }
        searchDecryptCombinePersonal.socialItems.addAll(searchSocial)
        for(taxIDItems in mDecryptCombinePersonal.taxIDItems){
            if(performSearch(taxIDItems, text)!!)
                searchTaxID.add(taxIDItems)
        }
        searchDecryptCombinePersonal.taxIDItems.addAll(searchTaxID)
        for(listItems in mDecryptCombinePersonal.listItems){
            if(performSearch(listItems, text)!!)
                searchPersonalList.add(listItems)
        }
        searchDecryptCombinePersonal.listItems.addAll(searchPersonalList)
        return searchDecryptCombinePersonal
    }
    fun searchContactItems(text : String) : DecryptedCombineContacts{
        if(text.contains("Contact", true)){
            return mDecryptedCombineContacts
        }
        val searchDecryptCombineContacts = DecryptedCombineContacts()
        var searchContacts = ArrayList<DecryptedContacts>()
        var searchMainContacts = ArrayList<DecryptedMainContacts>()
        var searchContactsList = ArrayList<DecryptedContactsList>()
        for(contactsItems in mDecryptedCombineContacts.contactsItems){
            if(performSearch(contactsItems, text)!!)
                searchContacts.add(contactsItems)
        }
        searchDecryptCombineContacts.contactsItems.addAll(searchContacts)
        for(mainContactItems in mDecryptedCombineContacts.mainContactsItems){
            if(performSearch(mainContactItems, text)!!)
                searchMainContacts.add(mainContactItems)
        }
        searchDecryptCombineContacts.mainContactsItems.addAll(searchMainContacts)
        for(listItems in mDecryptedCombineContacts.listItems){
            if(performSearch(listItems, text)!!)
                searchContactsList.add(listItems)
        }
        searchDecryptCombineContacts.listItems.addAll(searchContactsList)
        return searchDecryptCombineContacts
    }
    fun searchShoppingItems(text : String) : DecryptedCombineShopping{
        if(text.contains("Shopping", true)){
            return mDecryptCombineShopping
        }
        val searchDecryptCombineShopping = DecryptedCombineShopping()
        var searchLoyaltyPrograms = ArrayList<DecryptedLoyaltyPrograms>()
        var searchRecentPurchase = ArrayList<DecryptedRecentPurchase>()
        var searchShopping = ArrayList<DecryptedShopping>()
        var searchClothingSize = ArrayList<DecryptedClothingSizes>()
        var searchShoppingList = ArrayList<DecryptedShoppingList>()
        for(loyaltyProgramsItems in mDecryptCombineShopping.loyaltyProgramsItems){
            if(performSearch(loyaltyProgramsItems, text)!!)
                searchLoyaltyPrograms.add(loyaltyProgramsItems)
        }
        searchDecryptCombineShopping.loyaltyProgramsItems.addAll(searchLoyaltyPrograms)
        for(recentPurchaseItems in mDecryptCombineShopping.recentPurchaseItems){
            if(performSearch(recentPurchaseItems, text)!!)
                searchRecentPurchase.add(recentPurchaseItems)
        }
        searchDecryptCombineShopping.recentPurchaseItems.addAll(searchRecentPurchase)
        for(shoppingItems in mDecryptCombineShopping.shoppingItems){
            if(performSearch(shoppingItems, text)!!)
                searchShopping.add(shoppingItems)
        }
        searchDecryptCombineShopping.shoppingItems.addAll(searchShopping)
        for(clothingSizeItems in mDecryptCombineShopping.clothingSizesItems){
            if(performSearch(clothingSizeItems, text)!!)
                searchClothingSize.add(clothingSizeItems)
        }
        searchDecryptCombineShopping.clothingSizesItems.addAll(searchClothingSize)
        for(listItems in mDecryptCombineShopping.listItems){
            if(performSearch(listItems, text)!!)
                searchShoppingList.add(listItems)
        }
        searchDecryptCombineShopping.listItems.addAll(searchShoppingList)
        return searchDecryptCombineShopping
    }
}






