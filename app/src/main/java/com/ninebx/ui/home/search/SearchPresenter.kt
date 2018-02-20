package com.ninebx.ui.home.search

import android.os.Bundle
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.realm.RecentSearch
import com.ninebx.ui.base.realm.decrypted.DecryptedCombine
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.home.education.CombineEducation
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.ui.base.realm.home.interests.CombineInterests
import com.ninebx.ui.base.realm.home.memories.CombineMemories
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.base.realm.home.personal.CombinePersonal
import com.ninebx.ui.base.realm.home.shopping.CombineShopping
import com.ninebx.ui.base.realm.home.travel.CombineTravel
import com.ninebx.ui.base.realm.home.wellness.CombineWellness
import com.ninebx.ui.home.baseCategories.CategoryView
import com.ninebx.ui.home.fragments.ContactsListContainerFragment
import com.ninebx.ui.home.fragments.FragmentMemoriesListContainer
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import io.realm.internal.SyncObjectServerFacade.getApplicationContext
import java.util.*

/**
 * Created by Alok on 03/01/18.
 */
class SearchPresenter {

    private var searchView: SearchView? = null

    constructor(searchView: SearchView) {
        this.searchView = searchView
        initialize()
    }

    private var categoryView: CategoryView? = null
    private var categoryId: Int = -1

    constructor(categoryView: CategoryView, categoryId: Int) {
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


    private var decryptedRecentSearch = ArrayList<DecryptedRecentSearch>()


    private fun initialize() {

        if (searchView != null) {
            fetchCombine()
            fetchCombineTravel()
            fetchCombineMemories()
            fetchCombineEducation()
            fetchCombineInterests()
            fetchCombineWellness()
            fetchCombinePersonal()
            fetchCombineShopping()
            fetchCombineContacts()
            fetchRecentSearch()
        } else {
            searchView = categoryView
            fetchForCategory()
        }
    }

    private fun fetchForCategory() {
        when (categoryId) {
            R.string.home_amp_money -> {
                fetchCombine()
            }
            R.string.travel -> {
                fetchCombineTravel()
            }
            R.string.contacts -> {
                fetchCombineContacts()
//                gettingContactsList()

            }
            R.string.education_work -> {
                fetchCombineEducation()
            }
            R.string.personal -> {
                fetchCombinePersonal()
            }
            R.string.interests -> {
                fetchCombineInterests()
            }
            R.string.wellness -> {
                fetchCombineWellness()
            }
            R.string.memories -> {
                //fetchCombineMemories()
                gettingMemoryTimeLineView()
            }
            R.string.shopping -> {
                fetchCombineShopping()
            }
        }
    }

    fun updateRecentSearch(listname: String, subCategory: String, mainCategory: String, classType: String) {
        prepareRealmConnections(context, false, "RecentSearch", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                var updateRecent = RecentSearch(getUniqueId(), getUniqueId(), getUniqueId(), listname.encryptString(), subCategory.encryptString(), mainCategory.encryptString(), Date(), classType.encryptString())
                updateRecent.insertOrUpdate(realm!!)
                AppLogger.d("RecentSearch", "Update successful " + encryptRecentSearch(updateRecent))
            }
        })
    }

    private fun fetchCombineContacts() {
        prepareRealmConnections(context, false, "CombineContacts", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineContacts = realm!!.where(CombineContacts::class.java).distinctValues("id").findAll()
                if (combineContacts.size > 0) {
                    for (i in 0 until combineContacts.size) {
                        val decryptedCombineContacts = decryptCombineContacts(combineContacts[i]!!)
                        appendToDecryptCombineContacts(decryptedCombineContacts)
                    }
                    for (finance in mDecryptedCombineContacts.listItems) {
                        AppLogger.d("Records", finance.toString())
                    }
                    searchView!!.onCombineContactsFetched(mDecryptedCombineContacts)
                    AppLogger.d("Combine", "CombineContacts : " + mDecryptedCombineContacts)
                }
            }
        })
    }

    private fun fetchRecentSearch() {
        prepareRealmConnections(context, false, "RecentSearch", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val recentSearch = realm!!.where(RecentSearch::class.java).distinctValues("id").findAll()
                if (recentSearch.size > 0) {
                    for (i in 0 until recentSearch.size) {
                        decryptedRecentSearch.add(decryptRecentSearch(recentSearch[i]!!))
                        searchView!!.onRecentSearchFetched(decryptedRecentSearch)
                        AppLogger.d("Recent Search", "Decrypted Recent Search " + decryptRecentSearch(recentSearch[i]!!))
                    }
                }
            }
        })
    }

    private fun fetchCombineShopping() {
        prepareRealmConnections(context, false, "CombineShopping", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineShopping = realm!!.where(CombineShopping::class.java).distinctValues("id").findAll()
                if (combineShopping.size > 0) {
                    for (i in 0 until combineShopping.size) {
                        val decryptedCombineShopping = decryptCombineShopping(combineShopping[i]!!)
                        appendToDecryptCombineShopping(decryptedCombineShopping)
                    }
                    searchView!!.onCombineShoppingFetched(mDecryptCombineShopping)
                    AppLogger.d("Combine", "CombineShopping : " + mDecryptCombineShopping)
                } else {
                    searchView!!.onCombineShoppingFetched(mDecryptCombineShopping)
                }
            }
        })
    }

    private fun fetchCombinePersonal() {
        prepareRealmConnections(context, false, "CombinePersonal", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combinePersonal = realm!!.where(CombinePersonal::class.java).distinctValues("id").findAll()
                if (combinePersonal.size > 0) {
                    for (i in 0 until combinePersonal.size) {
                        val decryptedCombinePersonal = decryptCombinePersonal(combinePersonal[i]!!)
                        appendToDecryptCombinePersonal(decryptedCombinePersonal)
                    }

                    searchView!!.onCombinePersonalFetched(mDecryptCombinePersonal)
                    AppLogger.d("Combine", "CombinePersonal : " + mDecryptCombinePersonal)
                } else {
                    searchView!!.onCombinePersonalFetched(mDecryptCombinePersonal)
                }
            }
        })
    }

    private fun fetchCombineWellness() {
        prepareRealmConnections(context, false, "CombineWellness", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineWellness = realm!!.where(CombineWellness::class.java).distinctValues("id").findAll()
                if (combineWellness.size > 0) {
                    for (i in 0 until combineWellness.size) {
                        val decryptedCombineWellness = decryptCombineWellness(combineWellness[i]!!)
                        appendToDecryptCombineWellness(decryptedCombineWellness)
                    }
                    searchView!!.onCombineWellnessFetched(mDecryptCombineWellness)
                    AppLogger.d("Combine", "CombinedWellness : " + mDecryptCombineWellness)
                } else {
                    searchView!!.onCombineWellnessFetched(mDecryptCombineWellness)
                }
            }
        })
    }

    private fun fetchCombineInterests() {
        prepareRealmConnections(context, false, "CombineInterests", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineInterests = realm!!.where(CombineInterests::class.java).distinctValues("id").findAll()
                if (combineInterests.size > 0) {
                    for (i in 0 until combineInterests.size) {
                        val decryptedCombineInterests = decryptCombineInterests(combineInterests[i]!!)
                        appendToDecryptCombineInterests(decryptedCombineInterests)
                    }
                    searchView!!.onCombineInterestsFetched(mDecryptCombineInterests)
                    AppLogger.d("Combine", "CombinedInterests : " + mDecryptCombineInterests)
                } else {
                    searchView!!.onCombineInterestsFetched(mDecryptCombineInterests)
                }

            }
        })
    }

    private fun fetchCombineEducation() {
        prepareRealmConnections(context, false, "CombineEducation", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineEducation = realm!!.where(CombineEducation::class.java).distinctValues("id").findAll()
                if (combineEducation.size > 0) {
                    for (i in 0 until combineEducation.size) {
                        val decryptedCombineEducation = decryptCombineEducation(combineEducation[i]!!)
                        appendToDecryptCombineEducation(decryptedCombineEducation)
                    }
                    for (finance in mDecryptCombineEducation.workItems) {
                        AppLogger.d("REcords", finance.toString())
                    }
                    searchView!!.onCombineEducationFetched(mDecryptCombineEducation)
                    AppLogger.d("Combine", "Decrypted Combined Education : " + mDecryptCombineEducation)
                } else {
                    searchView!!.onCombineEducationFetched(mDecryptCombineEducation)
                }
            }
        })
    }

    private fun fetchCombineMemories() {
        prepareRealmConnections(context, false, "CombineMemories", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineMemories = realm!!.where(CombineMemories::class.java).distinctValues("id").findAll()
                if (combineMemories.size > 0) {
                    for (i in 0 until combineMemories.size) {
                        val decryptedCombineMemories = decryptCombineMemories(combineMemories[i]!!)
                        appendToDecryptCOmbineMemories(decryptedCombineMemories)
                    }
                    AppLogger.d("DecryptedCOmbineMemories", "Decrypted combine memories" + mDecryptedCombineMemories)
                    searchView!!.onCombineMemoryFetched(mDecryptedCombineMemories)
                } else {
                    searchView!!.onCombineMemoryFetched(mDecryptedCombineMemories)
                }
            }
        })
    }

    private fun fetchCombineTravel() {
        prepareRealmConnections(context, false, "CombineTravel", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineTravel = realm!!.where(CombineTravel::class.java).distinctValues("id").findAll()
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
                val combineResult = realm!!.where(Combine::class.java).distinctValues("id").findAll()

                if (combineResult.size > 0) {
                    for (i in 0 until combineResult.size) {
                        val decryptedCombine = decryptCombine(combineResult[i]!!)
                        appendToDecrypt(decryptedCombine)
                    }
                    AppLogger.d("COmbineDecrypted", "Decrypted combine financial" + mDecryptCombine)
                    for (finance in mDecryptCombine.financialItems) {
                        AppLogger.d("REcords", finance.toString())
                    }
                    searchView!!.onCombineFetched(mDecryptCombine)
                } else {
                    searchView!!.onCombineFetched(mDecryptCombine)
                }
            }
        })
    }


    private fun fetchContacts() {
        prepareRealmConnections(context, false, "CombineContacts", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combinePersonal = realm!!.where(CombinePersonal::class.java).distinctValues("id").findAll()
                if (combinePersonal.size > 0) {
                    for (i in 0 until combinePersonal.size) {
                        val decryptedCombinePersonal = decryptCombinePersonal(combinePersonal[i]!!)
                        appendToDecryptCombinePersonal(decryptedCombinePersonal)
                    }
                    for (finance in mDecryptCombinePersonal.governmentItems) {
                        AppLogger.d("Records", finance.toString())
                    }

                    searchView!!.onCombinePersonalFetched(mDecryptCombinePersonal)
                    AppLogger.d("Combine", "CombinePersonal : " + mDecryptCombinePersonal)
                } else {
                    searchView!!.onCombinePersonalFetched(mDecryptCombinePersonal)
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

    private fun appendToDecryptCOmbineMemories(decryptedCombineMemories: DecryptedCombineMemories) {
        mDecryptedCombineMemories.listItems.addAll(decryptedCombineMemories.listItems)
        mDecryptedCombineMemories.mainMemoriesItems.addAll(decryptedCombineMemories.mainMemoriesItems)
        mDecryptedCombineMemories.memoryTimelineItems.addAll(decryptedCombineMemories.memoryTimelineItems)
    }

    private fun appendToDecryptCombineEducation(decryptedCombineEducation: DecryptedCombineEducation) {
        mDecryptCombineEducation.listItems.addAll(decryptedCombineEducation.listItems)
        mDecryptCombineEducation.educationItems.addAll(decryptedCombineEducation.educationItems)
        mDecryptCombineEducation.mainEducationItems.addAll(decryptedCombineEducation.mainEducationItems)
        mDecryptCombineEducation.workItems.addAll(decryptedCombineEducation.workItems)
    }

    private fun appendToDecryptCombineInterests(decryptedCombineInterests: DecryptedCombineInterests) {
        mDecryptCombineInterests.interestItems.addAll(decryptedCombineInterests.interestItems)
        mDecryptCombineInterests.listItems.addAll(decryptedCombineInterests.listItems)
    }

    private fun appendToDecryptCombineWellness(decryptedCombineWellness: DecryptedCombineWellness) {
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

    private fun appendToDecryptCombinePersonal(decryptedCombinePersonal: DecryptedCombinePersonal) {
        mDecryptCombinePersonal.certificateItems.addAll(decryptedCombinePersonal.certificateItems)
        mDecryptCombinePersonal.governmentItems.addAll(decryptedCombinePersonal.governmentItems)
        mDecryptCombinePersonal.licenseItems.addAll(decryptedCombinePersonal.licenseItems)
        mDecryptCombinePersonal.personalItems.addAll(decryptedCombinePersonal.personalItems)
        mDecryptCombinePersonal.socialItems.addAll(decryptedCombinePersonal.socialItems)
        mDecryptCombinePersonal.taxIDItems.addAll(decryptedCombinePersonal.taxIDItems)
        mDecryptCombinePersonal.listItems.addAll(decryptedCombinePersonal.listItems)
    }

    private fun appendToDecryptCombineShopping(decryptedCombineShopping: DecryptedCombineShopping) {
        mDecryptCombineShopping.loyaltyProgramsItems.addAll(decryptedCombineShopping.loyaltyProgramsItems)
        mDecryptCombineShopping.recentPurchaseItems.addAll(decryptedCombineShopping.recentPurchaseItems)
        mDecryptCombineShopping.shoppingItems.addAll(decryptedCombineShopping.shoppingItems)
        mDecryptCombineShopping.clothingSizesItems.addAll(decryptedCombineShopping.clothingSizesItems)
        mDecryptCombineShopping.listItems.addAll(decryptedCombineShopping.listItems)
    }

    private fun appendToDecryptCombineContacts(decryptedCombineContacts: DecryptedCombineContacts) {
        mDecryptedCombineContacts.contactsItems.addAll(decryptedCombineContacts.contactsItems)
        mDecryptedCombineContacts.mainContactsItems.addAll(decryptedCombineContacts.mainContactsItems)
        mDecryptedCombineContacts.listItems.addAll(decryptedCombineContacts.listItems)
    }

    fun searchHomeItems(text: String): DecryptedCombine {
        if (text.contains("Home", ignoreCase = true)) {
            return mDecryptCombine
        }
        val searchDecryptCombine = DecryptedCombine()
        val searchFinanceItems = ArrayList<DecryptedFinancial>()
        val searchPaymentItems = ArrayList<DecryptedPayment>()
        val searchPropertyItems = ArrayList<DecryptedProperty>()
        val searchVehicleItems = ArrayList<DecryptedVehicle>()
        val searchAssetItems = ArrayList<DecryptedAsset>()
        val searchInsuranceItems = ArrayList<DecryptedInsurance>()
        val searchTaxItems = ArrayList<DecryptedTax>()
        val searchHomeList = ArrayList<DecryptedHomeList>()
        AppLogger.d("Search", "Decryptex : " + mDecryptCombine.financialItems)
        for (financeItems in mDecryptCombine.financialItems) {
            if (performSearch(financeItems, text))
                searchFinanceItems.add(financeItems)
        }

        searchDecryptCombine.financialItems.addAll(searchFinanceItems)
        AppLogger.d("Search", "SearchItems : " + searchFinanceItems)
        AppLogger.d("Search", "DecryptedCombine : " + searchDecryptCombine)

        for (paymentItems in mDecryptCombine.paymentItems) {

            if (performSearch(paymentItems, text))


                searchPaymentItems.add(paymentItems)
        }

        searchDecryptCombine.paymentItems.addAll(searchPaymentItems)
        AppLogger.d("Search", "SearchPayment : " + searchPaymentItems)

        for (propertyItems in mDecryptCombine.propertyItems) {
            if (performSearch(propertyItems, text))

                searchPropertyItems.add(propertyItems)
        }

        searchDecryptCombine.propertyItems.addAll(searchPropertyItems)
        AppLogger.d("Search", "SearchProperty : " + searchPropertyItems)

        for (vehicleItems in mDecryptCombine.vehicleItems) {
            if (performSearch(vehicleItems, text))

                searchVehicleItems.add(vehicleItems)
        }
        searchDecryptCombine.vehicleItems.addAll(searchVehicleItems)
        AppLogger.d("Search", "SearchVehicle" + searchVehicleItems)

        for (assetItems in mDecryptCombine.assetItems) {
            if (performSearch(assetItems, text))

                searchAssetItems.add(assetItems)
        }
        searchDecryptCombine.assetItems.addAll(searchAssetItems)
        AppLogger.d("Search", "SearchAsset" + searchAssetItems)

        for (insuranceItems in mDecryptCombine.insuranceItems) {
            if (performSearch(insuranceItems, text))

                searchInsuranceItems.add(insuranceItems)
        }
        searchDecryptCombine.insuranceItems.addAll(searchInsuranceItems)
        AppLogger.d("Search", "SearchInsurance" + searchInsuranceItems)

        for (taxItems in mDecryptCombine.taxesItems) {
            if (performSearch(taxItems, text))

                searchTaxItems.add(taxItems)
        }
        searchDecryptCombine.taxesItems.addAll(searchTaxItems)
        AppLogger.d("Search", "SearchTax" + searchTaxItems)

        for (listItems in mDecryptCombine.listItems) {
            if (performSearch(searchTaxItems, text))

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
        val searchDocumentItems = ArrayList<DecryptedDocuments>()
        val searchLoyaltyItems = ArrayList<DecryptedLoyalty>()
        val searchTravelItems = ArrayList<DecryptedTravel>()
        val searchVacationItems = ArrayList<DecryptedVacations>()
        val searchListItems = ArrayList<DecryptedTravelList>()

        for (documentsItems in mDecryptedCombineTravel.documentsItems) {
            if (performSearch(documentsItems, text))
                searchDocumentItems.add(documentsItems)
        }
        searchDecryptCombineTravel.documentsItems.addAll(searchDocumentItems)

        for (loyaltyItems in mDecryptedCombineTravel.loyaltyItems) {
            if (performSearch(loyaltyItems, text))
                searchLoyaltyItems.add(loyaltyItems)
        }
        searchDecryptCombineTravel.loyaltyItems.addAll(searchLoyaltyItems)

        for (travelItems in mDecryptedCombineTravel.travelItems) {
            if (performSearch(travelItems, text))
                searchTravelItems.add(travelItems)
        }
        searchDecryptCombineTravel.travelItems.addAll(searchTravelItems)

        for (vacationItems in mDecryptedCombineTravel.vacationsItems) {
            if (performSearch(vacationItems, text))
                searchVacationItems.add(vacationItems)
        }
        searchDecryptCombineTravel.vacationsItems.addAll(searchVacationItems)

        for (listItems in mDecryptedCombineTravel.listItems) {
            if (performSearch(listItems, text))
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
        val searchMainMemoryItems = ArrayList<DecryptedMainMemories>()
        val searchMemoryTimelineItems = ArrayList<DecryptedMemoryTimeline>()
        val searchMemorylistItems = ArrayList<DecryptedMemoriesList>()

        for (mainMemoryItems in mDecryptedCombineMemories.mainMemoriesItems) {
            if (performSearch(mainMemoryItems, text))
                searchMainMemoryItems.add(mainMemoryItems)
        }
        searchDecryptCombineMemories.mainMemoriesItems.addAll(searchMainMemoryItems)
        AppLogger.d("Search", "SearchMainMemoryItems" + searchMainMemoryItems)

        for (memoryTimelineItems in mDecryptedCombineMemories.memoryTimelineItems) {
            if (performSearch(memoryTimelineItems, text))
                searchMemoryTimelineItems.add(memoryTimelineItems)
        }
        searchDecryptCombineMemories.memoryTimelineItems.addAll(searchMemoryTimelineItems)
        AppLogger.d("Search", "SearchMemoryTimeLineItems" + searchMainMemoryItems)

        for (listItems in mDecryptedCombineMemories.listItems) {
            if (performSearch(listItems, text))
                searchMemorylistItems.add(listItems)
        }
        searchDecryptCombineMemories.listItems.addAll(searchMemorylistItems)
        AppLogger.d("Search", "SearchMemoryListItems" + searchMainMemoryItems)
        return searchDecryptCombineMemories
    }

    fun searchEducationItems(text: String): DecryptedCombineEducation {
        if (text.contains("Education", ignoreCase = true)) {
            return mDecryptCombineEducation
        }
        val searchDecryptCombineEducation = DecryptedCombineEducation()
        val searchEducationItems = ArrayList<DecryptedEducation>()
        val searchMainEduactionItems = ArrayList<DecryptedMainEducation>()
        val searchWorkItems = ArrayList<DecryptedWork>()
        val searchEducationListItems = ArrayList<DecryptedEducationList>()

        for (educationItems in mDecryptCombineEducation.educationItems) {
            if (performSearch(educationItems, text))
                searchEducationItems.add(educationItems)
        }
        searchDecryptCombineEducation.educationItems.addAll(searchEducationItems)

        for (mainEducationItems in mDecryptCombineEducation.mainEducationItems) {
            if (performSearch(mainEducationItems, text))
                searchMainEduactionItems.add(mainEducationItems)
        }
        searchDecryptCombineEducation.mainEducationItems.addAll(searchMainEduactionItems)

        for (workItems in mDecryptCombineEducation.workItems) {
            if (performSearch(workItems, text))
                searchWorkItems.add(workItems)
        }
        searchDecryptCombineEducation.workItems.addAll(searchWorkItems)

        for (listItems in mDecryptCombineEducation.listItems) {
            if (performSearch(listItems, text))
                searchEducationListItems.add(listItems)
        }
        searchDecryptCombineEducation.listItems.addAll(searchEducationListItems)

        return searchDecryptCombineEducation
    }

    fun searchInterestItems(text: String): DecryptedCombineInterests {
        if (text.contains("Education", ignoreCase = true)) {
            return mDecryptCombineInterests
        }
        val searchDecryptCombineInterests = DecryptedCombineInterests()
        val searchInterestItems = ArrayList<DecryptedInterests>()
        val searchInterestList = ArrayList<DecryptedInterestsList>()

        for (interestitems in mDecryptCombineInterests.interestItems) {
            if (performSearch(interestitems, text))
                searchInterestItems.add(interestitems)
        }
        searchDecryptCombineInterests.interestItems.addAll(searchInterestItems)

        for (listItems in mDecryptCombineInterests.listItems) {
            if (performSearch(listItems, text))
                searchInterestList.add(listItems)
        }
        searchDecryptCombineInterests.listItems.addAll(searchInterestList)

        return searchDecryptCombineInterests
    }

    fun searchWellnessItems(text: String): DecryptedCombineWellness {
        if (text.contains("Wellness", true)) {
            return mDecryptCombineWellness
        }
        val searchDecryptCombineWellness = DecryptedCombineWellness()
        val searchCheckupItems = ArrayList<DecryptedCheckups>()
        val searchEmergencyContacts = ArrayList<DecryptedEmergencyContacts>()
        val searchEyeglassPrescriptions = ArrayList<DecryptedEyeglassPrescriptions>()
        val searchhealthcareProviders = ArrayList<DecryptedHealthcareProviders>()
        val searchIdentification = ArrayList<DecryptedIdentification>()
        val searchMedicalConditions = ArrayList<DecryptedMedicalConditions>()
        val searchMedicalHistory = ArrayList<DecryptedMedicalHistory>()
        val searchMedications = ArrayList<DecryptedMedications>()
        val searchVitalNumber = ArrayList<DecryptedVitalNumbers>()
        val searchWellness = ArrayList<DecryptedWellness>()
        val searchWellnessList = ArrayList<DecryptedWellnessList>()

        for (checkupItems in mDecryptCombineWellness.checkupsItems) {
            if (performSearch(checkupItems, text))
                searchCheckupItems.add(checkupItems)
        }
        searchDecryptCombineWellness.checkupsItems.addAll(searchCheckupItems)

        for (emergencyContactItems in mDecryptCombineWellness.emergencyContactsItems) {
            if (performSearch(emergencyContactItems, text))
                searchEmergencyContacts.add(emergencyContactItems)
        }
        searchDecryptCombineWellness.emergencyContactsItems.addAll(searchEmergencyContacts)

        for (eyeglassPrescriptionItems in mDecryptCombineWellness.eyeglassPrescriptionsItems) {
            if (performSearch(eyeglassPrescriptionItems, text))
                searchEyeglassPrescriptions.add(eyeglassPrescriptionItems)
        }
        searchDecryptCombineWellness.eyeglassPrescriptionsItems.addAll(searchEyeglassPrescriptions)

        for (healthCareProviderItems in mDecryptCombineWellness.healthcareProvidersItems) {
            if (performSearch(healthCareProviderItems, text))
                searchhealthcareProviders.add(healthCareProviderItems)
        }
        searchDecryptCombineWellness.healthcareProvidersItems.addAll(searchhealthcareProviders)

        for (identificationItems in mDecryptCombineWellness.identificationItems) {
            if (performSearch(identificationItems, text))
                searchIdentification.add(identificationItems)
        }
        searchDecryptCombineWellness.identificationItems.addAll(searchIdentification)

        for (medicalConditionsItems in mDecryptCombineWellness.medicalConditionsItems) {
            if (performSearch(medicalConditionsItems, text))
                searchMedicalConditions.add(medicalConditionsItems)
        }
        searchDecryptCombineWellness.medicalConditionsItems.addAll(searchMedicalConditions)

        for (medicalHistoryItems in mDecryptCombineWellness.medicalHistoryItems) {
            if (performSearch(medicalHistoryItems, text))
                searchMedicalHistory.add(medicalHistoryItems)
        }
        searchDecryptCombineWellness.medicalHistoryItems.addAll(searchMedicalHistory)

        for (medicationItems in mDecryptCombineWellness.medicationsItems) {
            if (performSearch(medicationItems, text))
                searchMedications.add(medicationItems)
        }
        searchDecryptCombineWellness.medicationsItems.addAll(searchMedications)

        for (vitalNumberItems in mDecryptCombineWellness.vitalNumbersItems) {
            if (performSearch(vitalNumberItems, text))
                searchVitalNumber.add(vitalNumberItems)
        }
        searchDecryptCombineWellness.vitalNumbersItems.addAll(searchVitalNumber)

        for (wellnessItems in mDecryptCombineWellness.wellnessItems) {
            if (performSearch(wellnessItems, text))
                searchWellness.add(wellnessItems)
        }
        searchDecryptCombineWellness.wellnessItems.addAll(searchWellness)

        for (listItems in mDecryptCombineWellness.listItems) {
            if (performSearch(listItems, text))
                searchWellnessList.add(listItems)
        }
        searchDecryptCombineWellness.listItems.addAll(searchWellnessList)

        return searchDecryptCombineWellness
    }

    fun searchPersonalItems(text: String): DecryptedCombinePersonal {
        if (text.contains("Personal", true)) {
            return mDecryptCombinePersonal
        }
        val searchDecryptCombinePersonal = DecryptedCombinePersonal()
        val searchCertifiacate = ArrayList<DecryptedCertificate>()
        val searchGovernment = ArrayList<DecryptedGovernment>()
        val searchLicense = ArrayList<DecryptedLicense>()
        val searchPersonal = ArrayList<DecryptedPersonal>()
        val searchSocial = ArrayList<DecryptedSocial>()
        val searchTaxID = ArrayList<DecryptedTaxID>()
        val searchPersonalList = ArrayList<DecryptedPersonalList>()

        for (certificateItems in mDecryptCombinePersonal.certificateItems) {
            if (performSearch(certificateItems, text))
                searchCertifiacate.add(certificateItems)
        }
        searchDecryptCombinePersonal.certificateItems.addAll(searchCertifiacate)
        for (governmentItems in mDecryptCombinePersonal.governmentItems) {
            if (performSearch(governmentItems, text))
                searchGovernment.add(governmentItems)
        }
        searchDecryptCombinePersonal.governmentItems.addAll(searchGovernment)
        for (liceneItems in mDecryptCombinePersonal.licenseItems) {
            if (performSearch(liceneItems, text))
                searchLicense.add(liceneItems)
        }
        searchDecryptCombinePersonal.licenseItems.addAll(searchLicense)
        for (personalItems in mDecryptCombinePersonal.personalItems) {
            if (performSearch(personalItems, text))
                searchPersonal.add(personalItems)
        }
        searchDecryptCombinePersonal.personalItems.addAll(searchPersonal)
        for (socialItems in mDecryptCombinePersonal.socialItems) {
            if (performSearch(socialItems, text))
                searchSocial.add(socialItems)
        }
        searchDecryptCombinePersonal.socialItems.addAll(searchSocial)
        for (taxIDItems in mDecryptCombinePersonal.taxIDItems) {
            if (performSearch(taxIDItems, text))
                searchTaxID.add(taxIDItems)
        }
        searchDecryptCombinePersonal.taxIDItems.addAll(searchTaxID)
        for (listItems in mDecryptCombinePersonal.listItems) {
            if (performSearch(listItems, text))
                searchPersonalList.add(listItems)
        }
        searchDecryptCombinePersonal.listItems.addAll(searchPersonalList)
        return searchDecryptCombinePersonal
    }

    fun searchContactItems(text: String): DecryptedCombineContacts {
        if (text.contains("Contact", true)) {
            return mDecryptedCombineContacts
        }
        val searchDecryptCombineContacts = DecryptedCombineContacts()
        val searchContacts = ArrayList<DecryptedContacts>()
        val searchMainContacts = ArrayList<DecryptedMainContacts>()
        val searchContactsList = ArrayList<DecryptedContactsList>()
        for (contactsItems in mDecryptedCombineContacts.contactsItems) {
            if (performSearch(contactsItems, text))
                searchContacts.add(contactsItems)
        }
        searchDecryptCombineContacts.contactsItems.addAll(searchContacts)
        for (mainContactItems in mDecryptedCombineContacts.mainContactsItems) {
            if (performSearch(mainContactItems, text))
                searchMainContacts.add(mainContactItems)
        }
        searchDecryptCombineContacts.mainContactsItems.addAll(searchMainContacts)
        for (listItems in mDecryptedCombineContacts.listItems) {
            if (performSearch(listItems, text))
                searchContactsList.add(listItems)
        }
        searchDecryptCombineContacts.listItems.addAll(searchContactsList)
        return searchDecryptCombineContacts
    }

    fun searchShoppingItems(text: String): DecryptedCombineShopping {
        if (text.contains("Shopping", true)) {
            return mDecryptCombineShopping
        }
        val searchDecryptCombineShopping = DecryptedCombineShopping()
        val searchLoyaltyPrograms = ArrayList<DecryptedLoyaltyPrograms>()
        val searchRecentPurchase = ArrayList<DecryptedRecentPurchase>()
        val searchShopping = ArrayList<DecryptedShopping>()
        val searchClothingSize = ArrayList<DecryptedClothingSizes>()
        val searchShoppingList = ArrayList<DecryptedShoppingList>()
        for (loyaltyProgramsItems in mDecryptCombineShopping.loyaltyProgramsItems) {
            if (performSearch(loyaltyProgramsItems, text))
                searchLoyaltyPrograms.add(loyaltyProgramsItems)
        }
        searchDecryptCombineShopping.loyaltyProgramsItems.addAll(searchLoyaltyPrograms)
        for (recentPurchaseItems in mDecryptCombineShopping.recentPurchaseItems) {
            if (performSearch(recentPurchaseItems, text))
                searchRecentPurchase.add(recentPurchaseItems)
        }
        searchDecryptCombineShopping.recentPurchaseItems.addAll(searchRecentPurchase)
        for (shoppingItems in mDecryptCombineShopping.shoppingItems) {
            if (performSearch(shoppingItems, text))
                searchShopping.add(shoppingItems)
        }
        searchDecryptCombineShopping.shoppingItems.addAll(searchShopping)
        for (clothingSizeItems in mDecryptCombineShopping.clothingSizesItems) {
            if (performSearch(clothingSizeItems, text))
                searchClothingSize.add(clothingSizeItems)
        }
        searchDecryptCombineShopping.clothingSizesItems.addAll(searchClothingSize)
        for (listItems in mDecryptCombineShopping.listItems) {
            if (performSearch(listItems, text))
                searchShoppingList.add(listItems)
        }
        searchDecryptCombineShopping.listItems.addAll(searchShoppingList)
        return searchDecryptCombineShopping
    }


    var categoryName = ""
    var categoryID = ""

    private var allMemoryView: RealmResults<MemoryTimeline>? = null
    private var allContacts: RealmResults<Contacts>? = null


    private fun gettingContactsList() {
        prepareRealmConnections(context, true, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {

                allContacts = getCurrentContactList(realm!!)
                if (allContacts != null) {
//                    context!!.hideProgressDialog()
                    AppLogger.e("Contacts", "Contacts from Realm : " + allContacts.toString())

                    val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    val addFamilyUsersFragment = ContactsListContainerFragment()
                    val bundle = Bundle()
                    bundle.putString("categoryName", categoryName)
                    bundle.putString("categoryId", categoryID)
                    bundle.putParcelableArrayList(Constants.REALM_CONTACTS, Contacts.createParcelableList(allContacts!!))
                    addFamilyUsersFragment.arguments = bundle
                    fragmentTransaction.replace(R.id.frameLayout, addFamilyUsersFragment).commit()
                }
            }
        })
    }


    private fun gettingMemoryTimeLineView() {
        prepareRealmConnections(context, true, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {

                allMemoryView = getAllMemoryTimeLine(realm!!)
                if (allMemoryView != null) {
                    context!!.hideProgressDialog()
                    AppLogger.e("Memory", "MemoryView from Realm : " + allMemoryView.toString())

                    val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    val addFamilyUsersFragment = FragmentMemoriesListContainer()
                    val bundle = Bundle()
                    bundle.putString("categoryName", categoryName)
                    bundle.putString("categoryId", categoryID)
                    bundle.putParcelableArrayList(Constants.REALM_MEMORY_VIEW, MemoryTimeline.createParcelableList(allMemoryView!!))
                    addFamilyUsersFragment.arguments = bundle
                    fragmentTransaction.replace(R.id.frameLayout, addFamilyUsersFragment).commit()
                }
            }
        })
    }
}






