package com.ninebx.ui.home.search

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.ui.home.BaseHomeFragment
import kotlinx.android.synthetic.main.fragment_search.*
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import com.ninebx.NineBxApplication
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.SearchItemClickListener
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.home.baseCategories.CategoryFragment

/**
 * Created by Alok on 03/01/18.
 */

class SearchFragment : BaseHomeFragment(), SearchView {

    private var mDecryptCombine : DecryptedCombine ?= null
    private var mDecryptCombineMemories : DecryptedCombineMemories ?= null
    private var mDecryptedCombineTravel : DecryptedCombineTravel ?= null
    private var mDecryptedCombineEducation : DecryptedCombineEducation ?= null
    private var mDecryptedCombineInterests : DecryptedCombineInterests ?= null
    private var mDecryptedCombineWellness : DecryptedCombineWellness ?= null
    private var mDecryptedCombineContacts : DecryptedCombineContacts ?= null
    private var mDecryptedCombineShopping : DecryptedCombineShopping ?= null
    private var mDecryptedCombinePersonal : DecryptedCombinePersonal ?= null
    private var mRecentSearch = ArrayList<DecryptedRecentSearch>()

    /* private lateinit var recentSearchAdapter: RecentSearchAdapter*/

    private lateinit var mSearchPresenter: SearchPresenter

    override fun onCombineFetched(combine: DecryptedCombine) {
        this.mDecryptCombine = combine
        hideProgress()
    }

    override fun onRecentSearchFetched(recentSearch: ArrayList<DecryptedRecentSearch>) {
        this.mRecentSearch = recentSearch
        setRecentSearchAdapter()
        hideProgress()
    }

    override fun onCombineTravelFetched(combineTravel: DecryptedCombineTravel) {
        this.mDecryptedCombineTravel = combineTravel
        hideProgress()
    }

    override fun onCombineMemoryFetched(combineMemory: DecryptedCombineMemories) {
        this.mDecryptCombineMemories = combineMemory
        hideProgress()
    }

    override fun onCombineEducationFetched(combineEducation: DecryptedCombineEducation) {
        this.mDecryptedCombineEducation = combineEducation
        hideProgress()
    }

    override fun onCombineInterestsFetched(combineInterests: DecryptedCombineInterests) {
        mDecryptedCombineInterests = combineInterests
        hideProgress()
    }

    override fun onCombineWellnessFetched(combineWellness: DecryptedCombineWellness) {
        mDecryptedCombineWellness = combineWellness
        hideProgress()
    }

    override fun onCombinePersonalFetched(combinePersonal: DecryptedCombinePersonal) {
        mDecryptedCombinePersonal = combinePersonal
        hideProgress()
    }

    override fun onCombineShoppingFetched(combineShopping: DecryptedCombineShopping) {
        mDecryptedCombineShopping = combineShopping
        hideProgress()
    }

    override fun onCombineContactsFetched(combineContacts: DecryptedCombineContacts) {
        mDecryptedCombineContacts = combineContacts
        hideProgress()
    }

    override fun showProgress(message: Int) {
        if( edtSearch == null ) {
            return
        }
        edtSearch.isEnabled = false
        progressLayout.show()
    }

    override fun hideProgress() {
        if( edtSearch == null ) {
            return
        }
        edtSearch.isEnabled = true
        progressLayout.hide()
    }

    override fun onError(error: Int) {
        context!!.showToast(error)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    private lateinit var searchDecryptCombine: DecryptedCombine
    private lateinit var searchDecryptCombineMemories : DecryptedCombineMemories
    private lateinit var searchDecryptCombineTravel: DecryptedCombineTravel
    private lateinit var searchDecryptCombineEducation: DecryptedCombineEducation
    private lateinit var searchDecryptedCombineInterests: DecryptedCombineInterests
    private lateinit var searchDecryptedCombineWellness: DecryptedCombineWellness
    private lateinit var searchDecryptedCombinePersonal: DecryptedCombinePersonal
    private lateinit var searchDecryptedCombineShopping: DecryptedCombineShopping
    private lateinit var searchDecryptedCombineContacts: DecryptedCombineContacts




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideAllLayouts()
        showProgress(R.string.loading)

        mSearchPresenter = SearchPresenter(this)
        edtSearch.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                rvRecentSearch.visibility = View.GONE
                val text = edtSearch.text.toString().trim()
                searchDecryptCombine = mSearchPresenter.searchHomeItems( text )
                searchDecryptCombineTravel = mSearchPresenter.searchTravelItems(text)
                searchDecryptCombineMemories = mSearchPresenter.searchMemoryItems(text)
                searchDecryptCombineEducation = mSearchPresenter.searchEducationItems(text)
                searchDecryptedCombineInterests = mSearchPresenter.searchInterestItems(text)
                searchDecryptedCombineWellness = mSearchPresenter.searchWellnessItems(text)
                searchDecryptedCombinePersonal = mSearchPresenter.searchPersonalItems(text)
                searchDecryptedCombineContacts = mSearchPresenter.searchContactItems(text)
                searchDecryptedCombineShopping = mSearchPresenter.searchShoppingItems(text)
                setAdapter()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun setRecentSearchAdapter() {
        rvRecentSearch.layoutManager = LinearLayoutManager(context)
        val recentSearchAdapter  = RecentSearchAdapter(mRecentSearch)
        rvRecentSearch.adapter = recentSearchAdapter
    }

    private fun hideAllLayouts() {
        homeLayout.hide()
        travelLayout.hide()
        contactsLayout.hide()
        educationLayout.hide()
        personalLayout.hide()
        interestsLayout.hide()
        wellnessLayout.hide()
        memoriesLayout.hide()
        shoppingLayout.hide()
    }

    private var mSearchTravelItems = ArrayList<Level3SearchItem>()
    private var mSearchContactsItems = ArrayList<Level3SearchItem>()
    private var mSearchEducationItems = ArrayList<Level3SearchItem>()
    private var mSearchPersonalItems = ArrayList<Level3SearchItem>()
    private var mSearchInterestsItems = ArrayList<Level3SearchItem>()
    private var mSearchWellnessItems = ArrayList<Level3SearchItem>()
    private var mSearchMemoriesItems = ArrayList<Level3SearchItem>()
    private var mSearchShoppingItems = ArrayList<Level3SearchItem>()
    private var mSearchHomeList = ArrayList<Level3SearchItem>()

    private fun setAdapter() {

        hideAllLayouts()

        mSearchTravelItems.clear()
        mSearchContactsItems.clear()
        mSearchEducationItems.clear()
        mSearchPersonalItems.clear()
        mSearchInterestsItems.clear()
        mSearchWellnessItems.clear()
        mSearchMemoriesItems.clear()
        mSearchHomeList.clear()
        mSearchShoppingItems.clear()

        setupHomeItems()
        setupTravelItems()
        setupContactsItems()
        setupEducationItems()
        setupPersonalItems()
        setupInterestsItems()
        setupWellnessItems()
        setupMemoriesItems()
        setupShoppingItems()
    }

    private fun setupContactsItems() {
        var itemIndex = 0
        for(contacts in searchDecryptedCombineContacts.contactsItems){
            mSearchContactsItems.add(Level3SearchItem(R.string.contacts, contacts.firstName, "contacts", itemIndex++))
        }
        itemIndex = 0
        for(mainContacts in searchDecryptedCombineContacts.mainContactsItems){
            mSearchContactsItems.add(Level3SearchItem(R.string.contacts, mainContacts.userName, "mainContacts", itemIndex++))
        }
        itemIndex = 0
        for(contactList in searchDecryptedCombineContacts.listItems){
            mSearchContactsItems.add(Level3SearchItem(R.string.contacts, contactList.listName, "contactsList", itemIndex++))
        }
        if( mSearchContactsItems.size > 0 )
            setupAdapter(rvContacts, contactsLayout, mSearchContactsItems)
    }

    private fun setupEducationItems() {
        var itemIndex = 0
        for (education in searchDecryptCombineEducation.educationItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education, education.userName, "education", itemIndex++))
        }
        itemIndex = 0
        for(mainEducation in searchDecryptCombineEducation.mainEducationItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education, mainEducation.name, "mainEducation", itemIndex++))
        }
        itemIndex = 0
        for(work in searchDecryptCombineEducation.workItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education, work.name, "work", itemIndex++))
        }
        itemIndex = 0
        for(educationList in searchDecryptCombineEducation.listItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education, educationList.listName, "educationList", itemIndex++))
        }
        if( mSearchEducationItems.size > 0 )
            setupAdapter(rvEducationWork, educationLayout, mSearchEducationItems)
    }

    private fun setupPersonalItems() {
        var itemIndex = 0
        for(certificate in searchDecryptedCombinePersonal.certificateItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, certificate.nameOnCertificate, "certificate", itemIndex++))
        }
        itemIndex = 0
        for(government in searchDecryptedCombinePersonal.governmentItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, government.name, "government", itemIndex++))
        }
        itemIndex = 0
        for(license in searchDecryptedCombinePersonal.licenseItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, license.nameOnLicense, "license", itemIndex++))
        }
        itemIndex = 0
        for(personal in searchDecryptedCombinePersonal.personalItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, personal.userName, "personal", itemIndex++))
        }
        itemIndex = 0
        for(social in searchDecryptedCombinePersonal.socialItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, social.attachmentNames, "social", itemIndex++))
        }
        itemIndex = 0
        for(taxID in searchDecryptedCombinePersonal.taxIDItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, taxID.name, "taxID", itemIndex++))
        }
        itemIndex = 0
        for(personalList in searchDecryptedCombinePersonal.listItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, personalList.listName, "personalList", itemIndex++))
        }
        if( mSearchPersonalItems.size > 0 )
            setupAdapter(rvPersonal, personalLayout, mSearchPersonalItems)
    }

    private fun setupInterestsItems() {
        var itemIndex = 0
        for(interests in searchDecryptedCombineInterests.interestItems){
            mSearchInterestsItems.add(Level3SearchItem(R.string.interests, interests.userName, "interests", itemIndex++))
        }
        itemIndex = 0
        for(interestList in searchDecryptedCombineInterests.listItems){
            mSearchInterestsItems.add(Level3SearchItem(R.string.interests, interestList.listName, "interestsList", itemIndex++))
        }
        if( mSearchInterestsItems.size > 0 )
            setupAdapter(rvInterests, interestsLayout, mSearchInterestsItems)
    }

    private fun setupWellnessItems() {
        var itemIndex = 0
        for (checkups in searchDecryptedCombineWellness.checkupsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, checkups.attachmentNames, "checkups", itemIndex++))
        }
        itemIndex = 0
        for(emergencyContacts in searchDecryptedCombineWellness.emergencyContactsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, emergencyContacts.name, "emergencyContacts", itemIndex++))
        }
        itemIndex = 0
        for(eyeglassPrescription in searchDecryptedCombineWellness.eyeglassPrescriptionsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, eyeglassPrescription.attachmentNames, "eyeglassPrescription", itemIndex++))
        }
        itemIndex = 0
        for(healthcareProvider in searchDecryptedCombineWellness.healthcareProvidersItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, healthcareProvider.name, "healthcareProvider", itemIndex++))
        }
        itemIndex = 0
        for(identification in searchDecryptedCombineWellness.identificationItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, identification.name, "identification", itemIndex++))
        }
        itemIndex = 0
        for(medicalConditions in searchDecryptedCombineWellness.medicalConditionsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicalConditions.attachmentNames, "medicalConditions", itemIndex++))
        }
        itemIndex = 0
        for(medicalHistory in searchDecryptedCombineWellness.medicalHistoryItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicalHistory.attachmentNames, "medicalHistory", itemIndex++))
        }
        itemIndex = 0
        for(medicaltion in searchDecryptedCombineWellness.medicationsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicaltion.name, "medicaltion", itemIndex++))
        }
        itemIndex = 0
        for(vitalNumbers in searchDecryptedCombineWellness.vitalNumbersItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, vitalNumbers.attachmentNames, "vitalNumbers", itemIndex++))
        }
        itemIndex = 0
        for(wellness in searchDecryptedCombineWellness.wellnessItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, wellness.userName, "wellness", itemIndex++))
        }
        itemIndex = 0
        for(wellnessList in searchDecryptedCombineWellness.listItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, wellnessList.listName, "wellnessList", itemIndex++))
        }
        if( mSearchWellnessItems.size > 0 )
            setupAdapter(rvWellness, wellnessLayout, mSearchWellnessItems)
    }

    private fun setupShoppingItems() {
        var itemIndex = 0
        for(loyaltyPrograms in searchDecryptedCombineShopping.loyaltyProgramsItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, loyaltyPrograms.userName, "loyaltyPrograms", itemIndex++))
        }
        itemIndex = 0
        for(recentPurchase in searchDecryptedCombineShopping.recentPurchaseItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, recentPurchase.attachmentNames, "recentPurchase", itemIndex++))
        }
        itemIndex = 0
        for(shopping in searchDecryptedCombineShopping.shoppingItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, shopping.userName, "shopping", itemIndex++))
        }
        itemIndex = 0
        for(clothingSizes in searchDecryptedCombineShopping.clothingSizesItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, clothingSizes.personName, "clothingSizes", itemIndex++))
        }
        itemIndex = 0
        for(shoppingList in searchDecryptedCombineShopping.listItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, shoppingList.listName, "shoppingList", itemIndex++))
        }
        
        if( mSearchShoppingItems.size > 0 )
            setupAdapter(rvShopping, shoppingLayout, mSearchShoppingItems)
    }

    private fun setupMemoriesItems() {
        var itemIndex = 0
        for(mainMemory in searchDecryptCombineMemories.mainMemoriesItems){
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, mainMemory.attachmentNames, "mainMemory", itemIndex++))
        }
        itemIndex = 0
        for(memoryTimeline in searchDecryptCombineMemories.memoryTimelineItems){
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, memoryTimeline.attachmentNames, "memoryTimeline", itemIndex++))
        }
        itemIndex = 0
        for(memorylist in searchDecryptCombineMemories.listItems){
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, memorylist.listName, "memorylist", itemIndex++))
        }
        if( mSearchMemoriesItems.size > 0 )
            setupAdapter(rvMemories, memoriesLayout, mSearchMemoriesItems)
    }

    private fun setupTravelItems() {
        var itemIndex = 0
        for(documents in searchDecryptCombineTravel.documentsItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.travel_documents, documents.nameOnTravelDocument, "documents", itemIndex++))
        }
        itemIndex = 0
        for(loyalty in searchDecryptCombineTravel.loyaltyItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.loyality_programs, loyalty.accountName, "loyalty", itemIndex++))
        }
        itemIndex = 0
        for(travel in searchDecryptCombineTravel.travelItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.loyality_programs, travel.accountName, "travel", itemIndex++))
        }
        itemIndex = 0
        for(vacation in searchDecryptCombineTravel.vacationsItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.vacation_home, vacation.attachmentNames, "vacation", itemIndex++))
        }
        itemIndex = 0
        for(travelList in searchDecryptCombineTravel.listItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.travel_list, travelList.listName, "travelList", itemIndex++))
        }
        if( mSearchTravelItems.size > 0 )
            setupAdapter(rvTravel, travelLayout, mSearchTravelItems)
    }

    private fun setupHomeItems() {
        var itemIndex = 0
        for( finance in searchDecryptCombine.financialItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  finance.accountName, "finance", itemIndex++))
        }
        itemIndex = 0
        for( payment in searchDecryptCombine.paymentItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  payment.userName, "payment", itemIndex++))
        }
        itemIndex = 0
        for( asset in searchDecryptCombine.assetItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  asset.assetName, "asset", itemIndex++))
        }
        itemIndex = 0
        for( insurance in searchDecryptCombine.insuranceItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  insurance.insuranceCompany, "insurance", itemIndex++))
        }
        itemIndex = 0
        for( tax in searchDecryptCombine.taxesItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  tax.taxPayer, "tax", itemIndex++))
        }
        itemIndex = 0
        for( vehicle in searchDecryptCombine.vehicleItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  vehicle.titleName, "vehicle", itemIndex++))
        }
        itemIndex = 0
        for( property in searchDecryptCombine.propertyItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  property.propertyName, "property", itemIndex++))
        }
        itemIndex = 0
        for( home in searchDecryptCombine.listItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  home.listName, "home", itemIndex++))
        }
        itemIndex = 0
        if( mSearchHomeList.size > 0 ) //Pass the right recyclerview and layout to be shown with searchlist to be populated
            setupAdapter( rvHomeMoney, homeLayout, mSearchHomeList )
    }

    private var categoryFragment: CategoryFragment ?= null
    private var bundle: Bundle ?= null

    private var fragmentTransaction: FragmentTransaction? = null

    private fun setupAdapter(searchRecyclerView: RecyclerView?, layout: LinearLayout, searchList: ArrayList<Level3SearchItem>) {

        searchRecyclerView!!.layoutManager = LinearLayoutManager(context)
        searchRecyclerView.adapter = SearchAdapter( searchList, object : SearchItemClickListener {
            override fun onItemClick(position: Int, searchItem: Level3SearchItem) {

                fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction!!.addToBackStack(null)

                bundle = Bundle()
                bundle!!.putInt("category", R.string.home_amp_money)
                categoryFragment = CategoryFragment()
                categoryFragment!!.arguments = bundle

                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                when( searchItem.searchCategory ) {
                    R.string.home_amp_money -> {
                        switchHomeItems( position, searchItem )
                    }
                    (R.string.travel) -> {
                        switchTravelItems( position, searchItem )
                    }
                    (R.string.contacts) -> {
                        switchContactsItems( position, searchItem )
                    }
                    (R.string.education_work) -> {
                        switchEducationItems( position, searchItem )
                    }
                    (R.string.personal) -> {
                        switchPersonalItems( position, searchItem )
                    }
                    (R.string.interests) -> {
                        switchInterestsItems( position, searchItem )
                    }
                    (R.string.wellness) -> {
                        switchWellnessItems( position, searchItem )
                    }
                    (R.string.memories) -> {
                        switchMemoriesItems( position, searchItem )
                    }
                    (R.string.shopping) -> {
                        switchShoppingItems( position, searchItem )
                    }
                }
            }
        })
        layout.show()

    }

    private fun switchShoppingItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "loyalty" -> {
                val selectedDocument = searchDecryptedCombineShopping.loyaltyProgramsItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Shopping" ,selectedDocument::class.java.simpleName)
            }
            "recentPurchase" -> {
                val selectedDocument = searchDecryptedCombineShopping.recentPurchaseItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Shopping" ,selectedDocument::class.java.simpleName)
            }
            "shopping" -> {
                val selectedDocument = searchDecryptedCombineShopping.shoppingItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Shopping" ,selectedDocument::class.java.simpleName)
            }
            "clothingSize" -> {
                val selectedDocument = searchDecryptedCombineShopping.clothingSizesItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Shopping" ,selectedDocument::class.java.simpleName)
            }
            "shoppingList" -> {
                val selectedDocument = searchDecryptedCombineShopping.listItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.listName, selectedDocument.selectionType, "Shopping" ,selectedDocument::class.java.simpleName)
            }
        }
    }

    private fun switchMemoriesItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "mainMemory" -> {
                val selectedDocument = searchDecryptCombineMemories.mainMemoriesItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Memories" ,selectedDocument::class.java.simpleName)
            }
            "memoryTimeline" -> {
                val  selectedDocument = searchDecryptCombineMemories.memoryTimelineItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Memories" ,selectedDocument::class.java.simpleName)
            }
            "memorylist" -> {
                val selectedDocument = searchDecryptCombineMemories.listItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.listName, selectedDocument.selectionType, "Memories" ,selectedDocument::class.java.simpleName)
            }
        }
    }

    private fun switchWellnessItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "checkups" -> {
                val selectedDocument = searchDecryptedCombineWellness.checkupsItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Wellness" ,selectedDocument::class.java.simpleName)
            }
            "emergencyContacts" ->{
                val selectedDocument = searchDecryptedCombineWellness.emergencyContactsItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Wellness" ,selectedDocument::class.java.simpleName)
            }
            "eyeglassPrescription" -> {
                val selectedDocument = searchDecryptedCombineWellness.eyeglassPrescriptionsItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Wellness" ,selectedDocument::class.java.simpleName)
            }
            "healthcareProvider" -> {
                val selectedDocument = searchDecryptedCombineWellness.healthcareProvidersItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Wellness" ,selectedDocument::class.java.simpleName)
            }
            "identification" -> {
                val selectedDocument = searchDecryptedCombineWellness.identificationItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Wellness" ,selectedDocument::class.java.simpleName)
            }
            "medicalCondition" -> {
                val selectedDocument = searchDecryptedCombineWellness.medicalConditionsItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Wellness" ,selectedDocument::class.java.simpleName)
            }
            "medicalHistory" -> {
                val selectedDocument = searchDecryptedCombineWellness.medicalHistoryItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Wellness" ,selectedDocument::class.java.simpleName)
            }
            "medications" -> {
                val selectedDocument = searchDecryptedCombineWellness.medicationsItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Wellness" ,selectedDocument::class.java.simpleName)
            }
            "vitalNumbers" -> {
                val selectedDocument = searchDecryptedCombineWellness.vitalNumbersItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Wellness" ,selectedDocument::class.java.simpleName)
            }
            "wellness" -> {
                val selectedDocument = searchDecryptedCombineWellness.wellnessItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Wellness" ,selectedDocument::class.java.simpleName)
            }
            "wellnessList" -> {
                val selectedDocument = searchDecryptedCombineWellness.listItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.listName, selectedDocument.selectionType, "Wellness" ,selectedDocument::class.java.simpleName)
            }
        }
    }

    private fun switchInterestsItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "interests" -> {
                val selectedDocument = searchDecryptedCombineInterests.interestItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Interests" ,selectedDocument::class.java.simpleName)
            }
            "interestsList" ->
            {
                val selectedDocument = searchDecryptedCombineInterests.listItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.listName, selectedDocument.selectionType, "Interests" ,selectedDocument::class.java.simpleName)
            }
        }
    }

    private fun switchPersonalItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "certificate" ->{
                val selectedDocument = searchDecryptedCombinePersonal.certificateItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Personal" ,selectedDocument::class.java.simpleName)
            }
            "govenment" -> {
                val selectedDocument = searchDecryptedCombinePersonal.governmentItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Personal" ,selectedDocument::class.java.simpleName)
            }
            "license" -> {
                val selectedDocument = searchDecryptedCombinePersonal.licenseItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Personal" ,selectedDocument::class.java.simpleName)
            }
            "personal" ->{
                val selectedDocument = searchDecryptedCombinePersonal.personalItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Personal" ,selectedDocument::class.java.simpleName)
            }
            "social" -> {
                val selectedDocument = searchDecryptedCombinePersonal.socialItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Personal" ,selectedDocument::class.java.simpleName)
            }
            "taxID" -> {
                val selectedDocument = searchDecryptedCombinePersonal.taxIDItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Personal" ,selectedDocument::class.java.simpleName)
            }
            "personalList" -> {
                val selectedDocument = searchDecryptedCombinePersonal.listItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.listName, selectedDocument.selectionType, "Personal" ,selectedDocument::class.java.simpleName)
            }
        }
    }

    private fun switchEducationItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "education" -> {
                val selectedDocument = searchDecryptCombineEducation.educationItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.userName, selectedDocument.selectionType, "Education" ,selectedDocument::class.java.simpleName)
            }
            "mainEducation" -> {
                val selectedDocument = searchDecryptCombineEducation.mainEducationItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.name, selectedDocument.selectionType, "Education" ,selectedDocument::class.java.simpleName)
            }
            "work" -> {
                val selectedDocument = searchDecryptCombineEducation.workItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.name, selectedDocument.selectionType, "Education" ,selectedDocument::class.java.simpleName)
            }
            "educationList" -> {
                val selectedDocument = searchDecryptCombineEducation.listItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.listName, selectedDocument.selectionType, "Education" ,selectedDocument::class.java.simpleName)
            }
        }
    }

    private fun switchContactsItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "contacts" -> {
                val selectedDocument = searchDecryptedCombineContacts.contactsItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.firstName, selectedDocument.selectionType, "Contacts" ,selectedDocument::class.java.simpleName)
            }
            "mainContacts" -> {
                val selectedDocument = searchDecryptedCombineContacts.mainContactsItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Contacts" ,selectedDocument::class.java.simpleName)
            }
            "contactList" -> {
                val selectedDocument = searchDecryptedCombineContacts.listItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.listName, selectedDocument.selectionType, "Contacts" ,selectedDocument::class.java.simpleName)
            }
        }
    }

    private fun switchTravelItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "document" -> {
                val selectedDocument = searchDecryptCombineTravel.documentsItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Travel" ,selectedDocument::class.java.simpleName)
            }
            "loyalty" -> {
                val selectedDocument = searchDecryptCombineTravel.loyaltyItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "Travel" ,selectedDocument::class.java.simpleName)
            }
            "travel" -> {
                val selectedItems = searchDecryptCombineTravel.travelItems[position]
                goToCategoryFragment( selectedItems )
                mSearchPresenter.updateRecentSearch(selectedItems!!.attachmentNames, selectedItems.selectionType, "Travel" ,selectedItems::class.java.simpleName)
            }
            "vacation" -> {
                val selectedItems = searchDecryptCombineTravel.vacationsItems[position]
                goToCategoryFragment( selectedItems )
                mSearchPresenter.updateRecentSearch(selectedItems!!.attachmentNames, selectedItems.selectionType, "Travel" ,selectedItems::class.java.simpleName)
            }
            "travelList" -> {
                val selectedItems = searchDecryptCombineTravel.listItems[position]
                goToCategoryFragment( selectedItems )
                mSearchPresenter.updateRecentSearch(selectedItems!!.listName, selectedItems.selectionType, "Travel" ,selectedItems::class.java.simpleName)
            }
        }
    }

    private fun switchHomeItems(position: Int, searchItem: Level3SearchItem) {

        when( searchItem.categoryName ) {
            "finance" -> {
                val selectedDocument = searchDecryptCombine.financialItems[position]
                goToCategoryFragment(selectedDocument)
                mSearchPresenter.updateRecentSearch(selectedDocument!!.accountName, selectedDocument.selectionType, "HomeBank" ,selectedDocument::class.java.simpleName)
            }
            "payment" -> {
                val selectedDocument = searchDecryptCombine.paymentItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "HomeBank" ,selectedDocument::class.java.simpleName)
            }
            "asset" -> {
                val selectedDocument = searchDecryptCombine.assetItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.assetName, selectedDocument.selectionType, "HomeBank" ,selectedDocument::class.java.simpleName)
            }
            "insurance" -> {
                val selectedDocument = searchDecryptCombine.insuranceItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "HomeBank" ,selectedDocument::class.java.simpleName)
            }
            "tax" -> {
                val selectedDocument = searchDecryptCombine.taxesItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "HomeBank" ,selectedDocument::class.java.simpleName)
            }
            "vehicle" -> {
                val selectedDocument = searchDecryptCombine.vehicleItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "HomeBank" ,selectedDocument::class.java.simpleName)
            }
            "property" -> {
                val selectedDocument = searchDecryptCombine.propertyItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.attachmentNames, selectedDocument.selectionType, "HomeBank" ,selectedDocument::class.java.simpleName)
            }
            "home" -> {
                val selectedDocument = searchDecryptCombine.listItems[position]
                goToCategoryFragment( selectedDocument )
                mSearchPresenter.updateRecentSearch(selectedDocument!!.listName, selectedDocument.selectionType, "HomeBank" ,selectedDocument::class.java.simpleName)
            }
        }
    }

    private fun goToCategoryFragment(selectedDocument: Parcelable?) {
        bundle!!.putParcelable("selectedDocument", selectedDocument)
        fragmentTransaction!!.add(R.id.frameLayout, categoryFragment).commit()
    }
}


