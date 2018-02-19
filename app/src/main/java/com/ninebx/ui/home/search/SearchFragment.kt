package com.ninebx.ui.home.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.SearchItemClickListener
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.home.BaseHomeFragment
import kotlinx.android.synthetic.main.fragment_search.*

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

    override fun onCombineFetched(combine: DecryptedCombine) {
        this.mDecryptCombine = combine
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
        edtSearch.isEnabled = false
        progressLayout.show()
    }

    override fun hideProgress() {
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
    private lateinit var searchDecryptCombineMemories: DecryptedCombineMemories
    private lateinit var searchDecryptCombineTravel: DecryptedCombineTravel
    private lateinit var searchDecryptCombineEducation: DecryptedCombineEducation
    private lateinit var searchDecryptedCombineInterests: DecryptedCombineInterests
    private lateinit var searchDecryptedCombineWellness: DecryptedCombineWellness
    private lateinit var searchDecryptedCombinePersonal: DecryptedCombinePersonal
    private lateinit var searchDecryptedCombineShopping: DecryptedCombineShopping
    private lateinit var searchDecryptedCombineContacts: DecryptedCombineContacts
    private lateinit var mSearchPresenter: SearchPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideAllLayouts()
        showProgress(R.string.loading)
        mSearchPresenter = SearchPresenter(this)

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = edtSearch.text.toString().trim()
                searchDecryptCombine = mSearchPresenter.searchHomeItems(text)
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
        for(contacts in searchDecryptedCombineContacts.contactsItems){
            mSearchContactsItems.add(Level3SearchItem(R.string.contacts, contacts.firstName, "contacts"))
        }
        for(mainContacts in searchDecryptedCombineContacts.mainContactsItems){
            mSearchContactsItems.add(Level3SearchItem(R.string.contacts, mainContacts.userName, "mainContacts"))
        }
        for(contactList in searchDecryptedCombineContacts.listItems){
            mSearchContactsItems.add(Level3SearchItem(R.string.contacts, contactList.listName, "contactsList"))
        }
        if( mSearchContactsItems.size > 0 )
            setupAdapter(rvContacts, contactsLayout, mSearchContactsItems)
    }

    private fun setupEducationItems() {
        for (education in searchDecryptCombineEducation.educationItems) {
            mSearchEducationItems.add(Level3SearchItem(R.string.education, education.userName, "education"))
        }
        for (mainEducation in searchDecryptCombineEducation.mainEducationItems) {
            mSearchEducationItems.add(Level3SearchItem(R.string.education, mainEducation.name, "mainEducation"))
        }
        for (work in searchDecryptCombineEducation.workItems) {
            mSearchEducationItems.add(Level3SearchItem(R.string.education, work.name, "work"))
        }
        for (educationList in searchDecryptCombineEducation.listItems) {
            mSearchEducationItems.add(Level3SearchItem(R.string.education, educationList.listName, "educationList"))
        }
        if( mSearchEducationItems.size > 0 )
        setupAdapter(rvEducationWork, educationLayout, mSearchEducationItems)
    }

    private fun setupPersonalItems() {
        for(certificate in searchDecryptedCombinePersonal.certificateItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, certificate.nameOnCertificate, "certificate"))
        }
        for(government in searchDecryptedCombinePersonal.governmentItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, government.name, "government"))
        }
        for(license in searchDecryptedCombinePersonal.licenseItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, license.nameOnLicense, "license"))
        }
        for(personal in searchDecryptedCombinePersonal.personalItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, personal.userName, "personal"))
        }
        for(social in searchDecryptedCombinePersonal.socialItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, social.attachmentNames, "social"))
        }
        for(taxID in searchDecryptedCombinePersonal.taxIDItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, taxID.name, "taxID"))
        }
        for(personalList in searchDecryptedCombinePersonal.listItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, personalList.listName, "personalList"))
        }
        if( mSearchPersonalItems.size > 0 )
        setupAdapter(rvPersonal, personalLayout, mSearchPersonalItems)
    }

    private fun setupInterestsItems() {
        for (interests in searchDecryptedCombineInterests.interestItems) {
            mSearchInterestsItems.add(Level3SearchItem(R.id.interests, interests.userName, "interests"))
        for(interests in searchDecryptedCombineInterests.interestItems){
            mSearchInterestsItems.add(Level3SearchItem(R.string.interests, interests.userName, "interests"))
        }
        for (interestList in searchDecryptedCombineInterests.listItems) {
            mSearchInterestsItems.add(Level3SearchItem(R.id.interests, interestList.listName, "interestsList"))
        for(interestList in searchDecryptedCombineInterests.listItems){
            mSearchInterestsItems.add(Level3SearchItem(R.string.interests, interestList.listName, "interestsList"))
        }
        if( mSearchInterestsItems.size > 0 )
        setupAdapter(rvInterests, interestsLayout, mSearchInterestsItems)
    }

    private fun setupWellnessItems() {
        for (checkups in searchDecryptedCombineWellness.checkupsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, checkups.attachmentNames, "checkups" ))
        }

        for(emergencyContacts in searchDecryptedCombineWellness.emergencyContactsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, emergencyContacts.name))
        }

        for(eyeglassPrescription in searchDecryptedCombineWellness.eyeglassPrescriptionsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, eyeglassPrescription.attachmentNames))
        }

        for(healthcareProvider in searchDecryptedCombineWellness.healthcareProvidersItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, healthcareProvider.name))
        }

        for(identification in searchDecryptedCombineWellness.identificationItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, identification.name))
        }

        for(medicalConditions in searchDecryptedCombineWellness.medicalConditionsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicalConditions.attachmentNames))
        }

        for(medicalHistory in searchDecryptedCombineWellness.medicalHistoryItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicalHistory.attachmentNames))
        }

        for(medicaltion in searchDecryptedCombineWellness.medicationsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicaltion.name))
        }

        for(vitalNumbers in searchDecryptedCombineWellness.vitalNumbersItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, vitalNumbers.attachmentNames))
        }

        for(wellness in searchDecryptedCombineWellness.wellnessItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, wellness.userName))
        }

        for(wellnessList in searchDecryptedCombineWellness.listItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, wellnessList.listName))
        }
        if( mSearchWellnessItems.size > 0 )
        setupAdapter(rvWellness, wellnessLayout, mSearchWellnessItems)
    }

    private fun setupShoppingItems() {
        for(loyaltyPrograms in searchDecryptedCombineShopping.loyaltyProgramsItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, loyaltyPrograms.userName))
        }
        for(recentPurchase in searchDecryptedCombineShopping.recentPurchaseItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, recentPurchase.attachmentNames))
        }
        for(shopping in searchDecryptedCombineShopping.shoppingItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, shopping.userName))
        }
        for(clothingSizes in searchDecryptedCombineShopping.clothingSizesItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, clothingSizes.personName))
        }
        for(shoppingList in searchDecryptedCombineShopping.listItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, shoppingList.listName))
        }
        if( mSearchShoppingItems.size > 0 )
       setupAdapter(rvShopping, shoppingLayout, mSearchShoppingItems)
    }

    private fun setupMemoriesItems() {
        for (mainMemory in searchDecryptCombineMemories.mainMemoriesItems) {
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, mainMemory.attachmentNames, "mainMemory"))
        }
        for (memoryTimeline in searchDecryptCombineMemories.memoryTimelineItems) {
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, memoryTimeline.attachmentNames, "memoryTimeline"))
        }
        for (memorylist in searchDecryptCombineMemories.listItems) {
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, memorylist.listName, "memorylist"))
        }
        if( mSearchMemoriesItems.size > 0 )
        setupAdapter(rvMemories, memoriesLayout, mSearchMemoriesItems)
    }

    private fun setupTravelItems() {
        for (documents in searchDecryptCombineTravel.documentsItems) {
            mSearchTravelItems.add(Level3SearchItem(R.string.travel_documents, documents.nameOnTravelDocument, "documents"))
        }

        for (loyalty in searchDecryptCombineTravel.loyaltyItems) {
            mSearchTravelItems.add(Level3SearchItem(R.string.loyality_programs, loyalty.accountName, "loyalty"))
        }

        for (travel in searchDecryptCombineTravel.travelItems) {
            mSearchTravelItems.add(Level3SearchItem(R.string.loyality_programs, travel.accountName, "travel"))
        }

        for (vacation in searchDecryptCombineTravel.vacationsItems) {
            mSearchTravelItems.add(Level3SearchItem(R.string.vacation_home, vacation.attachmentNames, "vacation"))
        }

        for (travelList in searchDecryptCombineTravel.listItems) {
            mSearchTravelItems.add(Level3SearchItem(R.string.travel_list, travelList.listName, "travelList"))
        }
        if( mSearchTravelItems.size > 0 )
        setupAdapter(rvTravel, travelLayout, mSearchTravelItems)
    }

    private fun setupHomeItems() {
        for (finance in searchDecryptCombine.financialItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, finance.accountName, "finance"))
        }
        for (payment in searchDecryptCombine.paymentItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, payment.userName, "payment"))
        }
        for (asset in searchDecryptCombine.assetItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, asset.assetName, "asset"))
        }
        for (insurance in searchDecryptCombine.insuranceItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, insurance.insuranceCompany, "insurance"))
        }
        for (tax in searchDecryptCombine.taxesItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, tax.taxPayer, "tax"))
        }
        for (vehicle in searchDecryptCombine.vehicleItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, vehicle.titleName, "vehicle"))
        }
        for (property in searchDecryptCombine.propertyItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, property.propertyName, "property"))
        }
        for (home in searchDecryptCombine.listItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, home.listName, "home"))
        }
        if (mSearchHomeList.size > 0) //Pass the right recyclerview and layout to be shown with searchlist to be populated
            setupAdapter(rvHomeMoney, homeLayout, mSearchHomeList)
    }

    private fun setupAdapter(searchRecyclerView: RecyclerView?, layout: LinearLayout, searchList: ArrayList<Level3SearchItem>) {

        searchRecyclerView!!.layoutManager = LinearLayoutManager(context)
        searchRecyclerView.adapter = SearchAdapter(searchList, object : SearchItemClickListener {
            override fun onItemClick(position: Int, searchItem: Level3SearchItem) {
                when (searchItem.searchCategory) {
                    R.string.home_amp_money -> {
                        switchHomeItems(position, searchItem)
                    }
                    (R.string.travel) -> {
                        switchTravelItems(position, searchItem)
                    }
                    (R.string.contacts) -> {
                        switchContactsItems(position, searchItem)
                    }
                    (R.string.education_work) -> {
                        switchEducationItems(position, searchItem)
                    }
                    (R.string.personal) -> {
                        switchPersonalItems(position, searchItem)
                    }
                    (R.string.interests) -> {
                        switchInterestsItems(position, searchItem)
                    }
                    (R.string.wellness) -> {
                        switchWellnessItems(position, searchItem)
                    }
                    (R.string.memories) -> {
                        switchMemoriesItems(position, searchItem)
                    }
                    (R.string.shopping) -> {
                        switchShoppingItems(position, searchItem)
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
            }
            "recentPurchase" -> {
                val selectedDocument = searchDecryptedCombineShopping.recentPurchaseItems[position]
            }
            "shopping" -> {
                val selectedDocument = searchDecryptedCombineShopping.shoppingItems[position]
            }
            "clothingSize" -> {
                val selectedDocument = searchDecryptedCombineShopping.clothingSizesItems[position]
            }
            "shoppingList" -> {
                val selectedDocument = searchDecryptedCombineShopping.listItems[position]
            }
        }
    }

    private fun switchMemoriesItems(position: Int, searchItem: Level3SearchItem) {
        when (searchItem.categoryName) {
            "mainMemory" -> {
                val selectedDocument = searchDecryptCombineMemories.mainMemoriesItems[position]
            }
            "memoryTimeline" -> {
                val selectedDocument = searchDecryptCombineMemories.memoryTimelineItems[position]
            }
            "memorylist" -> {
                val selectedDocument = searchDecryptCombineMemories.listItems[position]
            }
        }
    }

    private fun switchWellnessItems(position: Int, searchItem: Level3SearchItem) {
      when(searchItem.categoryName){
          "checkups" -> {
              val selectedDocument = searchDecryptedCombineWellness.checkupsItems[position]
          }
          "emergencyContacts" ->{
              val selectedDocument = searchDecryptedCombineWellness.emergencyContactsItems[position]
          }
          "eyeglassPrescription" -> {
              val selectedDocument = searchDecryptedCombineWellness.eyeglassPrescriptionsItems[position]
          }
          "healthcareProvider" -> {
              val selectedDocument = searchDecryptedCombineWellness.healthcareProvidersItems[position]
          }
          "identification" -> {
              val selectedDocument = searchDecryptedCombineWellness.identificationItems[position]
          }
          "medicalCondition" -> {
              val selectedDocument = searchDecryptedCombineWellness.medicalConditionsItems[position]
          }
          "medicalHistory" -> {
              val selectedDocument = searchDecryptedCombineWellness.medicalHistoryItems[position]
          }
          "medications" -> {
              val selectedDocument = searchDecryptedCombineWellness.medicationsItems[position]
          }
          "vitalNumbers" -> {
              val selectedDocument = searchDecryptedCombineWellness.vitalNumbersItems[position]
          }
          "wellness" -> {
              val selectedDocument = searchDecryptedCombineWellness.wellnessItems[position]
          }
          "wellnessList" -> {
              val selectedDocument = searchDecryptedCombineWellness.listItems[position]
          }
      }
    }

    private fun switchInterestsItems(position: Int, searchItem: Level3SearchItem) {
        when (searchItem.categoryName) {
            "interests" -> {
                val selectedDocument = searchDecryptedCombineInterests.interestItems[position]
            }
            "interestsList" -> {
                val selectedDocument = searchDecryptedCombineInterests.listItems[position]
            }
        }
    }

    private fun switchPersonalItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "certificate" ->{
                val selectedDocument = searchDecryptedCombinePersonal.certificateItems[position]
            }
            "govenment" -> {
                val selectedDocument = searchDecryptedCombinePersonal.governmentItems[position]
            }
            "license" -> {
                val selectedDocument = searchDecryptedCombinePersonal.licenseItems[position]
            }
            "personal" ->{
                val selectedDocument = searchDecryptedCombinePersonal.personalItems[position]
            }
            "social" -> {
                val selectedDocuments = searchDecryptedCombinePersonal.socialItems[position]
            }
            "taxID" -> {
                val selectedDocument = searchDecryptedCombinePersonal.taxIDItems[position]
            }
            "personalList" -> {
                val selectedDocument = searchDecryptedCombinePersonal.listItems[position]
            }
        }
    }

    private fun switchEducationItems(position: Int, searchItem: Level3SearchItem) {
        when (searchItem.categoryName) {
            "education" -> {
                val selectedDocument = searchDecryptCombineEducation.educationItems[position]
            }
            "mainEducation" -> {
                val selectedDocument = searchDecryptCombineEducation.mainEducationItems[position]
            }
            "work" -> {
                val selectedDocument = searchDecryptCombineEducation.workItems[position]
            }
            "educationList" -> {
                val selectedDocument = searchDecryptCombineEducation.listItems[position]
            }
        }
    }

    private fun switchContactsItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "contacts" -> {
                val selectedDocument = searchDecryptedCombineContacts.contactsItems[position]
            }
            "mainContacts" -> {
                val selectedDocument = searchDecryptedCombineContacts.mainContactsItems[position]
            }
            "contactList" -> {
                val selectedDocument = searchDecryptedCombineContacts.listItems[position]
            }
        }
    }

    private fun switchTravelItems(position: Int, searchItem: Level3SearchItem) {
        when (searchItem.categoryName) {
            "document" -> {
                val selectedDocument = searchDecryptCombineTravel.documentsItems[position]
            }
            "loyalty" -> {
                val selectedDocument = searchDecryptCombineTravel.loyaltyItems[position]
            }
            "travel" -> {
                val selectedItems = searchDecryptCombineTravel.travelItems[position]
            }
            "vacation" -> {
                val selectedItems = searchDecryptCombineTravel.vacationsItems[position]
            }
            "travelList" -> {
                val selectedItems = searchDecryptCombineTravel.listItems[position]
            }
        }
    }

    private fun switchHomeItems(position: Int, searchItem: Level3SearchItem) {
        when (searchItem.categoryName) {
            "finance" -> {
                val selectedDocument = searchDecryptCombine.financialItems[position]
            }
            "payment" -> {
                val selectedDocument = searchDecryptCombine.paymentItems[position]
            }
            "asset" -> {
                val selectedDocument = searchDecryptCombine.assetItems[position]
            }
            "insurance" -> {
                val selectedDocument = searchDecryptCombine.insuranceItems[position]
            }
            "tax" -> {
                val selectedDocument = searchDecryptCombine.taxesItems[position]
            }
            "vehicle" -> {
                val selectedDocument = searchDecryptCombine.vehicleItems[position]
            }
            "property" -> {
                val selectedDocument = searchDecryptCombine.propertyItems[position]
            }
            "home" -> {
                val selectedDocument = searchDecryptCombine.listItems[position]
            }
        }
    }
}


