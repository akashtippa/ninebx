package com.ninebx.ui.home.search

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.realm.RecentSearch
import com.ninebx.ui.base.realm.decrypted.DecryptedCombine
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.home.education.CombineEducation
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.MainContacts
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.ui.base.realm.home.interests.CombineInterests
import com.ninebx.ui.base.realm.home.memories.MainMemories
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.base.realm.home.personal.CombinePersonal
import com.ninebx.ui.base.realm.home.shopping.CombineShopping
import com.ninebx.ui.base.realm.home.travel.CombineTravel
import com.ninebx.ui.base.realm.home.wellness.CombineWellness
import com.ninebx.ui.base.realm.lists.MemoriesList
import com.ninebx.ui.home.baseCategories.CategoryView
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.internal.SyncObjectServerFacade.getApplicationContext
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Alok on 03/01/18.
 */
@SuppressLint("StaticFieldLeak")
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
                fetchCombineMemories()
//                gettingMemoryTimeLineView()
            }
            R.string.shopping -> {
                fetchCombineShopping()
            }
        }
    }

    fun updateRecentSearch(listname: String, subCategory: String, mainCategory: String, classType: String) {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_RECENT_SEARCH, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        var updateRecent = RecentSearch(getUniqueId(), getUniqueId(), getUniqueId(), listname.encryptString(), subCategory.encryptString(), mainCategory.encryptString(), Date(), classType.encryptString())
                         updateRecent.insertOrUpdate(realm!!)
                        //AppLogger.d("RecentSearch", "Update successful " + encryptRecentSearch(updateRecent))
                    }
                })
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private fun fetchRecentSearch() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_RECENT_SEARCH, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val recentSearch = realm!!.where(RecentSearch::class.java)/*.distinctValues("id")*//*.sort("createdDate", Sort.ASCENDING)*/.findAll()
                        if (recentSearch.size > 0) {
                            for (i in 0 until recentSearch.size) {
                                decryptedRecentSearch.add(decryptRecentSearch(recentSearch[i]!!))
                                //AppLogger.d("Recent Search", "Decrypted Recent Search " + decryptRecentSearch(recentSearch[i]!!))
                            }
                        }
                    }
                })
            }
            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                searchView!!.onRecentSearchFetched(decryptedRecentSearch)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private fun fetchCombineContacts() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        /*val contactItems = realm!!.where(Contacts::class.java).findAll() //shared contacts
                        val mainContactsItems = realm.where(MainContacts::class.java).findAll()
                        val listItems = realm.where(ContactsList::class.java).findAll()

                        val decryptedCombineContacts = decryptCombineContacts(0L,
                                mainContactsItems , contactItems , listItems )
                        appendToDecryptCombineContacts(decryptedCombineContacts)*/

                        val combineContacts = realm!!.where(CombineContacts::class.java).distinctValues("id").findAll()
                        if (combineContacts.size > 0) {
                            for (i in 0 until combineContacts.size) {
                                val decryptedCombineContacts = decryptCombineContacts(combineContacts[i]!!)
                                appendToDecryptCombineContacts(decryptedCombineContacts)
                                //AppLogger.d("Recent Search", "Decrypted Recent Search " + decryptCombineContacts(combineContacts[i]!!))
                            }
                            //AppLogger.d("Combine", "CombineContacts : " + mDecryptedCombineContacts)
                        }
                    }
                })
            }
            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                val items = filterDuplicatesforContacts(mDecryptedCombineContacts.mainContactsItems!!)
                mDecryptedCombineContacts.mainContactsItems.clear()
                mDecryptedCombineContacts.mainContactsItems.addAll(items)
                searchView!!.onCombineContactsFetched(mDecryptedCombineContacts)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private fun filterDuplicatesforContacts(mCombineContacts: java.util.ArrayList<DecryptedMainContacts>): ArrayList<DecryptedMainContacts> {
        val singleCountList = ArrayList<DecryptedMainContacts>()
        for( item in mCombineContacts ) {
            if( !singleCountList.contains(item) ) {
                singleCountList.add(item)
            }
            else {
                singleCountList[singleCountList.indexOf(item)] = item
            }
        }
        return singleCountList
    }

    private fun fetchCombineShopping() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val combineShopping = realm!!.where(CombineShopping::class.java)/*.distinctValues("id")*/.findAll()
                        if (combineShopping.size > 0) {
                            for (i in 0 until combineShopping.size) {
                                val decryptedCombineShopping = decryptCombineShopping(combineShopping[i]!!)
                                appendToDecryptCombineShopping(decryptedCombineShopping)
                            }

                        }
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                searchView!!.onCombineShoppingFetched(mDecryptCombineShopping)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private fun fetchCombinePersonal() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val combinePersonal = realm!!.where(CombinePersonal::class.java)/*.distinctValues("id")*/.findAll()
                        if (combinePersonal.size > 0) {
                            for (i in 0 until combinePersonal.size) {
                                val decryptedCombinePersonal = decryptCombinePersonal(combinePersonal[i]!!)
                                appendToDecryptCombinePersonal(decryptedCombinePersonal)
                            }

                        }
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                searchView!!.onCombinePersonalFetched(mDecryptCombinePersonal)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private fun fetchCombineWellness() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val combineWellness = realm!!.where(CombineWellness::class.java)/*.distinctValues("id")*/.findAll()
                        if (combineWellness.size > 0) {
                            for (i in 0 until combineWellness.size) {
                                val decryptedCombineWellness = decryptCombineWellness(combineWellness[i]!!)
                                appendToDecryptCombineWellness(decryptedCombineWellness)
                            }
                        }
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                searchView!!.onCombineWellnessFetched(mDecryptCombineWellness)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private fun fetchCombineInterests() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_INTERESTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val combineInterests = realm!!.where(CombineInterests::class.java)/*.distinctValues("id")*/.findAll()
                        if (combineInterests.size > 0) {
                            for (i in 0 until combineInterests.size) {
                                val decryptedCombineInterests = decryptCombineInterests(combineInterests[i]!!)
                                appendToDecryptCombineInterests(decryptedCombineInterests)
                            }

                        }

                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                searchView!!.onCombineInterestsFetched(mDecryptCombineInterests)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private fun fetchCombineEducation() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_EDUCATION, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val combineEducation = realm!!.where(CombineEducation::class.java)/*.distinctValues("id")*/.findAll()
                        if (combineEducation.size > 0) {
                            for (i in 0 until combineEducation.size) {
                                val decryptedCombineEducation = decryptCombineEducation(combineEducation[i]!!)
                                appendToDecryptCombineEducation(decryptedCombineEducation)
                            }
                            for (finance in mDecryptCombineEducation.workItems) {
                                //AppLogger.d("REcords", finance.toString())
                            }

                        }
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                searchView!!.onCombineEducationFetched(mDecryptCombineEducation)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private fun fetchCombineMemories() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val memoryTimelines = realm!!.where(MemoryTimeline::class.java).findAll()
                        val mainMemories = realm.where(MainMemories::class.java).findAll()
                        val memoryLists = realm.where(MemoriesList::class.java).findAll()

                        val decryptedCombineMemories = decryptCombineMemories(0L,
                                mainMemories, memoryTimelines , memoryLists )
                        appendToDecryptCOmbineMemories(decryptedCombineMemories)

                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                searchView!!.onCombineMemoryFetched(mDecryptedCombineMemories)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private fun fetchCombineTravel() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val combineTravel = realm!!.where(CombineTravel::class.java)/*.distinctValues("id")*/.findAll()
                        if (combineTravel.size > 0) {
                            for (i in 0 until combineTravel.size) {
                                val decryptedCombineTravel = decryptCombineTravel(combineTravel[i]!!)
                                appendToDecryptCombineTravel(decryptedCombineTravel)
                            }
                        }
                        //AppLogger.d("Combine", "CombinedTravel : " + combineTravel)
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                val items = filterDuplicatesforTravel(mDecryptedCombineTravel.travelItems!!)
                mDecryptedCombineTravel.travelItems.clear()
                mDecryptedCombineTravel.travelItems.addAll(items)
                searchView!!.onCombineTravelFetched(mDecryptedCombineTravel)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private fun filterDuplicatesforTravel(mCombineTravel: java.util.ArrayList<DecryptedTravel>): ArrayList<DecryptedTravel> {
        val singleCountList = ArrayList<DecryptedTravel>()
        for( item in mCombineTravel ) {
            if( !singleCountList.contains(item) ) {
                singleCountList.add(item)
            }
            else {
                singleCountList[singleCountList.indexOf(item)] = item
            }
        }
        return singleCountList
    }

    private fun fetchCombine() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {

                        val combineResult = realm!!.where(Combine::class.java)/*.distinctValues("id")*/.findAll()
                        if( combineResult.size > 0 ) {
                            for (i in 0 until combineResult.size) {
                                val decryptedCombine = decryptCombine(combineResult[i]!!)
                                appendToDecrypt(decryptedCombine)
                            }
                        }
                        if( categoryView != null ) {
                            categoryView!!.onCombineResultsFetched(combineResult)
                        }
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                searchView!!.onCombineFetched(mDecryptCombine)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }


    fun appendToDecrypt(decryptedCombine: DecryptedCombine) {
        mDecryptCombine.id = decryptedCombine.id
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
        mDecryptedCombineTravel.id = decryptedCombineTravel.id
        mDecryptedCombineTravel.documentsItems.addAll(decryptedCombineTravel.documentsItems)
        mDecryptedCombineTravel.loyaltyItems.addAll(decryptedCombineTravel.loyaltyItems)
        mDecryptedCombineTravel.travelItems.addAll(decryptedCombineTravel.travelItems)
        mDecryptedCombineTravel.vacationsItems.addAll(decryptedCombineTravel.vacationsItems)
        mDecryptedCombineTravel.listItems.addAll(decryptedCombineTravel.listItems)
    }

    private fun appendToDecryptCOmbineMemories(decryptedCombineMemories: DecryptedCombineMemories) {
        mDecryptedCombineMemories.id = decryptedCombineMemories.id
        mDecryptedCombineMemories.listItems.addAll(decryptedCombineMemories.listItems)
        mDecryptedCombineMemories.mainMemoriesItems.addAll(decryptedCombineMemories.mainMemoriesItems)
        mDecryptedCombineMemories.memoryTimelineItems.addAll(decryptedCombineMemories.memoryTimelineItems)
    }

    private fun appendToDecryptCombineEducation(decryptedCombineEducation: DecryptedCombineEducation) {
        mDecryptCombineEducation.id = decryptedCombineEducation.id
        mDecryptCombineEducation.listItems.addAll(decryptedCombineEducation.listItems)
        mDecryptCombineEducation.educationItems.addAll(decryptedCombineEducation.educationItems)
        mDecryptCombineEducation.mainEducationItems.addAll(decryptedCombineEducation.mainEducationItems)
        mDecryptCombineEducation.workItems.addAll(decryptedCombineEducation.workItems)
    }

    private fun appendToDecryptCombineInterests(decryptedCombineInterests: DecryptedCombineInterests) {
        mDecryptCombineInterests.id = decryptedCombineInterests.id
        mDecryptCombineInterests.interestItems.addAll(decryptedCombineInterests.interestItems)
        mDecryptCombineInterests.listItems.addAll(decryptedCombineInterests.listItems)
    }

    private fun appendToDecryptCombineWellness(decryptedCombineWellness: DecryptedCombineWellness) {
        mDecryptCombineWellness.id = decryptedCombineWellness.id
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
        mDecryptCombinePersonal.id = decryptedCombinePersonal.id
        mDecryptCombinePersonal.certificateItems.addAll(decryptedCombinePersonal.certificateItems)
        mDecryptCombinePersonal.governmentItems.addAll(decryptedCombinePersonal.governmentItems)
        mDecryptCombinePersonal.licenseItems.addAll(decryptedCombinePersonal.licenseItems)
        mDecryptCombinePersonal.personalItems.addAll(decryptedCombinePersonal.personalItems)
        mDecryptCombinePersonal.socialItems.addAll(decryptedCombinePersonal.socialItems)
        mDecryptCombinePersonal.taxIDItems.addAll(decryptedCombinePersonal.taxIDItems)
        mDecryptCombinePersonal.listItems.addAll(decryptedCombinePersonal.listItems)
    }

    private fun appendToDecryptCombineShopping(decryptedCombineShopping: DecryptedCombineShopping) {
        mDecryptCombineShopping.id = decryptedCombineShopping.id
        mDecryptCombineShopping.loyaltyProgramsItems.addAll(decryptedCombineShopping.loyaltyProgramsItems)
        mDecryptCombineShopping.recentPurchaseItems.addAll(decryptedCombineShopping.recentPurchaseItems)
        mDecryptCombineShopping.shoppingItems.addAll(decryptedCombineShopping.shoppingItems)
        mDecryptCombineShopping.clothingSizesItems.addAll(decryptedCombineShopping.clothingSizesItems)
        mDecryptCombineShopping.listItems.addAll(decryptedCombineShopping.listItems)
    }

    private fun appendToDecryptCombineContacts(decryptedCombineContacts: DecryptedCombineContacts) {
        mDecryptedCombineContacts.id = decryptedCombineContacts.id
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
        //AppLogger.d("Search", "Decryptex : " + mDecryptCombine.financialItems)
        for (financeItems in mDecryptCombine.financialItems) {
            val searchResult = performSearchForResult(financeItems, text)
            if (searchResult.isSearchFound) {
                financeItems.searchField = searchResult.searchFieldName
                searchFinanceItems.add(financeItems)
            }
        }

        searchDecryptCombine.financialItems.addAll(searchFinanceItems)
        //AppLogger.d("Search", "SearchItems : " + searchFinanceItems)
        //AppLogger.d("Search", "DecryptedCombine : " + searchDecryptCombine)

        for (paymentItems in mDecryptCombine.paymentItems) {
            val searchResult = performSearchForResult(paymentItems, text)
            if (performSearch(paymentItems, text)) {
                paymentItems.searchField = searchResult.searchFieldName
                searchPaymentItems.add(paymentItems)
            }

        }

        searchDecryptCombine.paymentItems.addAll(searchPaymentItems)
        //AppLogger.d("Search", "SearchPayment : " + searchPaymentItems)

        for (propertyItems in mDecryptCombine.propertyItems) {
            val searchResult = performSearchForResult(propertyItems, text)
            if (performSearch(propertyItems, text))
                propertyItems.searchField = searchResult.searchFieldName
            searchPropertyItems.add(propertyItems)
        }

        searchDecryptCombine.propertyItems.addAll(searchPropertyItems)
        //AppLogger.d("Search", "SearchProperty : " + searchPropertyItems)

        for (vehicleItems in mDecryptCombine.vehicleItems) {
            val searchResult = performSearchForResult(vehicleItems, text)
            if (performSearch(vehicleItems, text))
                vehicleItems.searchField = searchResult.searchFieldName
            searchVehicleItems.add(vehicleItems)
        }
        searchDecryptCombine.vehicleItems.addAll(searchVehicleItems)
        //AppLogger.d("Search", "SearchVehicle" + searchVehicleItems)

        for (assetItems in mDecryptCombine.assetItems) {
            val searchResult = performSearchForResult(assetItems, text)
            if (performSearch(assetItems, text))
                assetItems.searchField = searchResult.searchFieldName
            searchAssetItems.add(assetItems)
        }
        searchDecryptCombine.assetItems.addAll(searchAssetItems)
        //AppLogger.d("Search", "SearchAsset" + searchAssetItems)

        for (insuranceItems in mDecryptCombine.insuranceItems) {
            val searchResult = performSearchForResult(insuranceItems, text)
            if (performSearch(insuranceItems, text))
                insuranceItems.searchField = searchResult.searchFieldName
            searchInsuranceItems.add(insuranceItems)
        }
        searchDecryptCombine.insuranceItems.addAll(searchInsuranceItems)
        //AppLogger.d("Search", "SearchInsurance" + searchInsuranceItems)

        for (taxItems in mDecryptCombine.taxesItems) {
            val searchResult = performSearchForResult(taxItems, text)
            if (performSearch(taxItems, text))
                taxItems.searchField = searchResult.searchFieldName
            searchTaxItems.add(taxItems)
        }
        searchDecryptCombine.taxesItems.addAll(searchTaxItems)
        //AppLogger.d("Search", "SearchTax" + searchTaxItems)

        for (listItems in mDecryptCombine.listItems) {
            val searchResult = performSearchForResult(listItems, text)
            if (performSearch(listItems, text))
                listItems.searchField = searchResult.searchFieldName
            searchHomeList.add(listItems)
        }
        searchDecryptCombine.listItems.addAll(searchHomeList)
        //AppLogger.d("Search", "SearchHomeList" + searchHomeList)

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
            val searchResult = performSearchForResult(documentsItems, text)
            if (performSearch(documentsItems, text))
                documentsItems.searchField = searchResult.searchFieldName
            searchDocumentItems.add(documentsItems)
        }
        searchDecryptCombineTravel.documentsItems.addAll(searchDocumentItems)

        for (loyaltyItems in mDecryptedCombineTravel.loyaltyItems) {
            val searchResult = performSearchForResult(loyaltyItems, text)
            if (performSearch(loyaltyItems, text))
                loyaltyItems.searchField = searchResult.searchFieldName
            searchLoyaltyItems.add(loyaltyItems)
        }
        searchDecryptCombineTravel.loyaltyItems.addAll(searchLoyaltyItems)

        for (travelItems in mDecryptedCombineTravel.travelItems) {
            val searchResult = performSearchForResult(travelItems, text)
            if (performSearch(travelItems, text))
                travelItems.searchField = searchResult.searchFieldName
            searchTravelItems.add(travelItems)
        }
        searchDecryptCombineTravel.travelItems.addAll(searchTravelItems)

        for (vacationItems in mDecryptedCombineTravel.vacationsItems) {
            val searchResult = performSearchForResult(vacationItems, text)
            if (performSearch(vacationItems, text))
                vacationItems.searchField = searchResult.searchFieldName
            searchVacationItems.add(vacationItems)
        }
        searchDecryptCombineTravel.vacationsItems.addAll(searchVacationItems)

        for (listItems in mDecryptedCombineTravel.listItems) {
            val searchResult = performSearchForResult(listItems, text)
            if (performSearch(listItems, text))
                listItems.searchField = searchResult.searchFieldName
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
            val searchResult = performSearchForResult(mainMemoryItems, text)
            if (performSearch(mainMemoryItems, text))
                mainMemoryItems.searchField = searchResult.searchFieldName
            searchMainMemoryItems.add(mainMemoryItems)
        }
        searchDecryptCombineMemories.mainMemoriesItems.addAll(searchMainMemoryItems)
        //AppLogger.d("Search", "SearchMainMemoryItems" + searchMainMemoryItems)

        for (memoryTimelineItems in mDecryptedCombineMemories.memoryTimelineItems) {
            val searchResult = performSearchForResult(memoryTimelineItems, text)
            if (performSearch(memoryTimelineItems, text))
                memoryTimelineItems.searchField = searchResult.searchFieldName
            searchMemoryTimelineItems.add(memoryTimelineItems)
        }
        searchDecryptCombineMemories.memoryTimelineItems.addAll(searchMemoryTimelineItems)
        //AppLogger.d("Search", "SearchMemoryTimeLineItems" + searchMainMemoryItems)

        for (listItems in mDecryptedCombineMemories.listItems) {
            val searchResult = performSearchForResult(listItems, text)
            if (performSearch(listItems, text))
                listItems.searchField = searchResult.searchFieldName
            searchMemorylistItems.add(listItems)
        }
        searchDecryptCombineMemories.listItems.addAll(searchMemorylistItems)
        //AppLogger.d("Search", "SearchMemoryListItems" + searchMainMemoryItems)
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
            val searchResult = performSearchForResult(educationItems, text)
            if (performSearch(educationItems, text))
                educationItems.searchField = searchResult.searchFieldName
            searchEducationItems.add(educationItems)
        }
        searchDecryptCombineEducation.educationItems.addAll(searchEducationItems)

        for (mainEducationItems in mDecryptCombineEducation.mainEducationItems) {
            val searchResult = performSearchForResult(mainEducationItems, text)
            if (performSearch(mainEducationItems, text))
                mainEducationItems.searchField = searchResult.searchFieldName
            searchMainEduactionItems.add(mainEducationItems)
        }
        searchDecryptCombineEducation.mainEducationItems.addAll(searchMainEduactionItems)

        for (workItems in mDecryptCombineEducation.workItems) {
            val searchResult = performSearchForResult(workItems, text)
            if (performSearch(workItems, text))
                workItems.searchField = searchResult.searchFieldName
            searchWorkItems.add(workItems)
        }
        searchDecryptCombineEducation.workItems.addAll(searchWorkItems)

        for (listItems in mDecryptCombineEducation.listItems) {
            val searchResult = performSearchForResult(listItems, text)
            if (performSearch(listItems, text))
                listItems.searchField = searchResult.searchFieldName
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
            val searchResult = performSearchForResult(interestitems, text)
            if (performSearch(interestitems, text))
                interestitems.searchField = searchResult.searchFieldName
            searchInterestItems.add(interestitems)
        }
        searchDecryptCombineInterests.interestItems.addAll(searchInterestItems)

        for (listItems in mDecryptCombineInterests.listItems) {
            val searchResult = performSearchForResult(listItems, text)
            if (performSearch(listItems, text))
                listItems.searchField = searchResult.searchFieldName
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
            val searchResult = performSearchForResult(checkupItems, text)
            if (performSearch(checkupItems, text))
                checkupItems.searchField = searchResult.searchFieldName
            searchCheckupItems.add(checkupItems)
        }
        searchDecryptCombineWellness.checkupsItems.addAll(searchCheckupItems)

        for (emergencyContactItems in mDecryptCombineWellness.emergencyContactsItems) {
            val searchResult = performSearchForResult(emergencyContactItems, text)
            if (performSearch(emergencyContactItems, text))
                emergencyContactItems.searchField = searchResult.searchFieldName
            searchEmergencyContacts.add(emergencyContactItems)
        }
        searchDecryptCombineWellness.emergencyContactsItems.addAll(searchEmergencyContacts)

        for (eyeglassPrescriptionItems in mDecryptCombineWellness.eyeglassPrescriptionsItems) {
            val searchResult = performSearchForResult(eyeglassPrescriptionItems, text)
            if (performSearch(eyeglassPrescriptionItems, text))
                eyeglassPrescriptionItems.searchField = searchResult.searchFieldName
            searchEyeglassPrescriptions.add(eyeglassPrescriptionItems)
        }
        searchDecryptCombineWellness.eyeglassPrescriptionsItems.addAll(searchEyeglassPrescriptions)

        for (healthCareProviderItems in mDecryptCombineWellness.healthcareProvidersItems) {
            val searchResult = performSearchForResult(healthCareProviderItems, text)
            if (performSearch(healthCareProviderItems, text))
                healthCareProviderItems.searchField = searchResult.searchFieldName
            searchhealthcareProviders.add(healthCareProviderItems)
        }
        searchDecryptCombineWellness.healthcareProvidersItems.addAll(searchhealthcareProviders)

        for (identificationItems in mDecryptCombineWellness.identificationItems) {
            val searchResult = performSearchForResult(identificationItems, text)
            if (performSearch(identificationItems, text))
                identificationItems.searchField = searchResult.searchFieldName
            searchIdentification.add(identificationItems)
        }
        searchDecryptCombineWellness.identificationItems.addAll(searchIdentification)

        for (medicalConditionsItems in mDecryptCombineWellness.medicalConditionsItems) {
            val searchResult = performSearchForResult(medicalConditionsItems, text)
            if (performSearch(medicalConditionsItems, text))
                medicalConditionsItems.searchField = searchResult.searchFieldName
            searchMedicalConditions.add(medicalConditionsItems)
        }
        searchDecryptCombineWellness.medicalConditionsItems.addAll(searchMedicalConditions)

        for (medicalHistoryItems in mDecryptCombineWellness.medicalHistoryItems) {
            val searchResult = performSearchForResult(medicalHistoryItems, text)
            if (performSearch(medicalHistoryItems, text))
                medicalHistoryItems.searchField = searchResult.searchFieldName
            searchMedicalHistory.add(medicalHistoryItems)
        }
        searchDecryptCombineWellness.medicalHistoryItems.addAll(searchMedicalHistory)

        for (medicationItems in mDecryptCombineWellness.medicationsItems) {
            val searchResult = performSearchForResult(medicationItems, text)
            if (performSearch(medicationItems, text))
                medicationItems.searchField = searchResult.searchFieldName
            searchMedications.add(medicationItems)
        }
        searchDecryptCombineWellness.medicationsItems.addAll(searchMedications)

        for (vitalNumberItems in mDecryptCombineWellness.vitalNumbersItems) {
            val searchResult = performSearchForResult(vitalNumberItems, text)
            if (performSearch(vitalNumberItems, text))
                vitalNumberItems.searchField = searchResult.searchFieldName
            searchVitalNumber.add(vitalNumberItems)
        }
        searchDecryptCombineWellness.vitalNumbersItems.addAll(searchVitalNumber)

        for (wellnessItems in mDecryptCombineWellness.wellnessItems) {
            val searchResult = performSearchForResult(wellnessItems, text)
            if (performSearch(wellnessItems, text))
                wellnessItems.searchField = searchResult.searchFieldName
            searchWellness.add(wellnessItems)
        }
        searchDecryptCombineWellness.wellnessItems.addAll(searchWellness)

        for (listItems in mDecryptCombineWellness.listItems) {
            val searchResult = performSearchForResult(listItems, text)
            if (performSearch(listItems, text))
                listItems.searchField = searchResult.searchFieldName
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
            val searchResult = performSearchForResult(certificateItems, text)
            if (performSearch(certificateItems, text))
                certificateItems.searchField = searchResult.searchFieldName
            searchCertifiacate.add(certificateItems)
        }
        searchDecryptCombinePersonal.certificateItems.addAll(searchCertifiacate)
        for (governmentItems in mDecryptCombinePersonal.governmentItems) {
            val searchResult = performSearchForResult(governmentItems, text)
            if (performSearch(governmentItems, text))
                governmentItems.searchField = searchResult.searchFieldName
            searchGovernment.add(governmentItems)
        }
        searchDecryptCombinePersonal.governmentItems.addAll(searchGovernment)
        for (liceneItems in mDecryptCombinePersonal.licenseItems) {
            val searchResult = performSearchForResult(liceneItems, text)
            if (performSearch(liceneItems, text))
                liceneItems.searchField = searchResult.searchFieldName
            searchLicense.add(liceneItems)
        }
        searchDecryptCombinePersonal.licenseItems.addAll(searchLicense)
        for (personalItems in mDecryptCombinePersonal.personalItems) {
            val searchResult = performSearchForResult(personalItems, text)
            if (performSearch(personalItems, text))
                personalItems.searchField = searchResult.searchFieldName
            searchPersonal.add(personalItems)
        }
        searchDecryptCombinePersonal.personalItems.addAll(searchPersonal)
        for (socialItems in mDecryptCombinePersonal.socialItems) {
            val searchResult = performSearchForResult(socialItems, text)
            if (performSearch(socialItems, text))
                socialItems.searchField = searchResult.searchFieldName
            searchSocial.add(socialItems)
        }
        searchDecryptCombinePersonal.socialItems.addAll(searchSocial)
        for (taxIDItems in mDecryptCombinePersonal.taxIDItems) {
            val searchResult = performSearchForResult(taxIDItems, text)
            if (performSearch(taxIDItems, text))
                taxIDItems.searchField = searchResult.searchFieldName
            searchTaxID.add(taxIDItems)
        }
        searchDecryptCombinePersonal.taxIDItems.addAll(searchTaxID)
        for (listItems in mDecryptCombinePersonal.listItems) {
            val searchResult = performSearchForResult(listItems, text)
            if (performSearch(listItems, text))
                listItems.searchField = searchResult.searchFieldName
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
            val searchResult = performSearchForResult(contactsItems, text)
            if (performSearch(contactsItems, text))
                contactsItems.searchField = searchResult.searchFieldName
            searchContacts.add(contactsItems)
        }
        searchDecryptCombineContacts.contactsItems.addAll(searchContacts)
        for (mainContactItems in mDecryptedCombineContacts.mainContactsItems) {
            val searchResult = performSearchForResult(mainContactItems, text)
            if (performSearch(mainContactItems, text))
                mainContactItems.searchField = searchResult.searchFieldName
            searchMainContacts.add(mainContactItems)
        }
        searchDecryptCombineContacts.mainContactsItems.addAll(searchMainContacts)
        for (listItems in mDecryptedCombineContacts.listItems) {
            val searchResult = performSearchForResult(listItems, text)
            if (performSearch(listItems, text))
                listItems.searchField = searchResult.searchFieldName
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
            val searchResult = performSearchForResult(recentPurchaseItems, text)
            if (performSearch(recentPurchaseItems, text))
                recentPurchaseItems.searchField = searchResult.searchFieldName
            searchRecentPurchase.add(recentPurchaseItems)
        }
        searchDecryptCombineShopping.recentPurchaseItems.addAll(searchRecentPurchase)
        for (shoppingItems in mDecryptCombineShopping.shoppingItems) {
            val searchResult = performSearchForResult(shoppingItems, text)
            if (performSearch(shoppingItems, text))
                shoppingItems.searchField = searchResult.searchFieldName
            searchShopping.add(shoppingItems)
        }
        searchDecryptCombineShopping.shoppingItems.addAll(searchShopping)
        for (clothingSizeItems in mDecryptCombineShopping.clothingSizesItems) {
            val searchResult = performSearchForResult(clothingSizeItems, text)
            if (performSearch(clothingSizeItems, text))
                clothingSizeItems.searchField = searchResult.searchFieldName
            searchClothingSize.add(clothingSizeItems)
        }
        searchDecryptCombineShopping.clothingSizesItems.addAll(searchClothingSize)
        for (listItems in mDecryptCombineShopping.listItems) {
            val searchResult = performSearchForResult(listItems, text)
            if (performSearch(listItems, text))
                listItems.searchField = searchResult.searchFieldName
            searchShoppingList.add(listItems)
        }
        searchDecryptCombineShopping.listItems.addAll(searchShoppingList)
        return searchDecryptCombineShopping
    }
}






