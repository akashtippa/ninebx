package com.ninebx.ui.home.search

import android.os.Parcelable
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.*

/**
 * Created by Alok on 20/02/18.
 */
class SearchHelper() {

    private var mSearchTravelItems = ArrayList<Level3SearchItem>()
    private var mSearchContactsItems = ArrayList<Level3SearchItem>()
    private var mSearchEducationItems = ArrayList<Level3SearchItem>()
    private var mSearchPersonalItems = ArrayList<Level3SearchItem>()
    private var mSearchInterestsItems = ArrayList<Level3SearchItem>()
    private var mSearchWellnessItems = ArrayList<Level3SearchItem>()
    private var mSearchMemoriesItems = ArrayList<Level3SearchItem>()
    private var mSearchShoppingItems = ArrayList<Level3SearchItem>()
    private var mSearchHomeList = ArrayList<Level3SearchItem>()

    private  var searchDecryptCombine: DecryptedCombine = DecryptedCombine()
    private  var searchDecryptCombineMemories : DecryptedCombineMemories = DecryptedCombineMemories()
    private  var searchDecryptCombineTravel: DecryptedCombineTravel = DecryptedCombineTravel()
    private  var searchDecryptCombineEducation: DecryptedCombineEducation = DecryptedCombineEducation()
    private  var searchDecryptedCombineInterests: DecryptedCombineInterests = DecryptedCombineInterests()
    private  var searchDecryptedCombineWellness: DecryptedCombineWellness = DecryptedCombineWellness()
    private  var searchDecryptedCombinePersonal: DecryptedCombinePersonal = DecryptedCombinePersonal()
    private  var searchDecryptedCombineShopping: DecryptedCombineShopping = DecryptedCombineShopping()
    private  var searchDecryptedCombineContacts: DecryptedCombineContacts = DecryptedCombineContacts()

    fun getSearchItems( combineItems : Parcelable ) : ArrayList<Level3SearchItem> {

        return when( combineItems ) {
            is DecryptedCombine -> {
                searchDecryptCombine = combineItems
                setupHomeItems()
            }
            is DecryptedCombineMemories -> {
                searchDecryptCombineMemories = combineItems
                setupMemoriesItems()
            }
            is DecryptedCombineTravel -> {
                searchDecryptCombineTravel = combineItems
                setupTravelItems()
            }
            is DecryptedCombineEducation -> {
                searchDecryptCombineEducation = combineItems
                setupEducationItems()
            }
            is DecryptedCombineInterests -> {
                searchDecryptedCombineInterests = combineItems
                setupInterestsItems()
            }
            is DecryptedCombineWellness -> {
                searchDecryptedCombineWellness = combineItems
                setupWellnessItems()
            }
            is DecryptedCombinePersonal -> {
                searchDecryptedCombinePersonal = combineItems
                setupPersonalItems()
            }
            is DecryptedCombineShopping -> {
                searchDecryptedCombineShopping = combineItems
                setupShoppingItems()
            }
            is DecryptedCombineContacts -> {
                searchDecryptedCombineContacts = combineItems
                setupContactsItems()
            }
            else -> {
                return ArrayList()
            }

        }
    }

    fun getLevel3SearchItemsForCategory( categoryId : String, allItems : ArrayList<Level3SearchItem> ) : ArrayList<Level3SearchItem> {
        val level3SearchItems = ArrayList<Level3SearchItem>()
        allItems.filterTo(level3SearchItems) { it.categoryId.equals(categoryId, true) }
        return level3SearchItems
    }

    private fun setupContactsItems(): ArrayList<Level3SearchItem> {
        mSearchContactsItems.clear()
        var itemIndex = 0
        for(contacts in searchDecryptedCombineContacts.contactsItems){
            mSearchContactsItems.add(Level3SearchItem(R.string.contacts, contacts.firstName, "contacts", contacts.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(mainContacts in searchDecryptedCombineContacts.mainContactsItems){
            mSearchContactsItems.add(Level3SearchItem(R.string.contacts, mainContacts.userName, "mainContacts", mainContacts.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(contactList in searchDecryptedCombineContacts.listItems){
            mSearchContactsItems.add(Level3SearchItem(R.string.contacts, contactList.listName, "contactList", contactList.selectionType, itemIndex++))
        }
        return mSearchContactsItems
    }

    private fun setupEducationItems(): ArrayList<Level3SearchItem> {
        mSearchEducationItems.clear()
        var itemIndex = 0
        for (education in searchDecryptCombineEducation.educationItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education, education.userName, "education", education.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(mainEducation in searchDecryptCombineEducation.mainEducationItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education, mainEducation.name, "mainEducation", mainEducation.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(work in searchDecryptCombineEducation.workItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education, work.name, "work", work.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(educationList in searchDecryptCombineEducation.listItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education, educationList.listName, "educationList", educationList.selectionType, itemIndex++))
        }
        return mSearchEducationItems
    }

    private fun setupPersonalItems(): ArrayList<Level3SearchItem> {
        mSearchPersonalItems.clear()
        var itemIndex = 0
        for(certificate in searchDecryptedCombinePersonal.certificateItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, certificate.nameOnCertificate, "certificate", certificate.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(government in searchDecryptedCombinePersonal.governmentItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, government.name, "government", government.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(license in searchDecryptedCombinePersonal.licenseItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, license.nameOnLicense, "license", license.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(personal in searchDecryptedCombinePersonal.personalItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, personal.userName, "personal", personal.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(social in searchDecryptedCombinePersonal.socialItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, social.attachmentNames, "social", social.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(taxID in searchDecryptedCombinePersonal.taxIDItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, taxID.name, "taxID", taxID.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(personalList in searchDecryptedCombinePersonal.listItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, personalList.listName, "personalList", personalList.selectionType, itemIndex++))
        }
        return mSearchPersonalItems
    }

    private fun setupInterestsItems(): ArrayList<Level3SearchItem> {
        mSearchInterestsItems.clear()
        var itemIndex = 0
        for(interests in searchDecryptedCombineInterests.interestItems){
            mSearchInterestsItems.add(Level3SearchItem(R.string.interests, interests.userName, "interests", interests.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(interestList in searchDecryptedCombineInterests.listItems){
            mSearchInterestsItems.add(Level3SearchItem(R.string.interests, interestList.listName, "interestList", interestList.selectionType, itemIndex++))
        }
        return mSearchInterestsItems
    }

    private fun setupWellnessItems(): ArrayList<Level3SearchItem> {
        mSearchWellnessItems.clear()
        var itemIndex = 0
        for (checkups in searchDecryptedCombineWellness.checkupsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, checkups.attachmentNames, "checkups", checkups.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(emergencyContacts in searchDecryptedCombineWellness.emergencyContactsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, emergencyContacts.name, "emergencyContacts", emergencyContacts.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(eyeglassPrescription in searchDecryptedCombineWellness.eyeglassPrescriptionsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, eyeglassPrescription.attachmentNames, "eyeglassPrescription", eyeglassPrescription.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(healthcareProvider in searchDecryptedCombineWellness.healthcareProvidersItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, healthcareProvider.name, "healthcareProvider", healthcareProvider.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(identification in searchDecryptedCombineWellness.identificationItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, identification.name, "identification", identification.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(medicalConditions in searchDecryptedCombineWellness.medicalConditionsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicalConditions.attachmentNames, "medicalConditions", medicalConditions.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(medicalHistory in searchDecryptedCombineWellness.medicalHistoryItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicalHistory.attachmentNames, "medicalHistory", medicalHistory.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(medicaltion in searchDecryptedCombineWellness.medicationsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicaltion.name, "medicationItems", medicaltion.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(vitalNumbers in searchDecryptedCombineWellness.vitalNumbersItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, vitalNumbers.attachmentNames, "vitalNumbers", vitalNumbers.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(wellness in searchDecryptedCombineWellness.wellnessItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, wellness.userName,"wellness",  wellness.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(wellnessList in searchDecryptedCombineWellness.listItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, wellnessList.listName,"wellnessList",  wellnessList.selectionType, itemIndex++))
        }
        return mSearchWellnessItems
    }

    private fun setupShoppingItems(): ArrayList<Level3SearchItem> {
        mSearchShoppingItems.clear()
        var itemIndex = 0
        for(loyaltyPrograms in searchDecryptedCombineShopping.loyaltyProgramsItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, loyaltyPrograms.userName, "loyaltyPrograms", loyaltyPrograms.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(recentPurchase in searchDecryptedCombineShopping.recentPurchaseItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, recentPurchase.attachmentNames, "recentPurchase", recentPurchase.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(shopping in searchDecryptedCombineShopping.shoppingItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, shopping.userName, "shopping", shopping.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(clothingSizes in searchDecryptedCombineShopping.clothingSizesItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, clothingSizes.personName, "clothingSizes", clothingSizes.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(shoppingList in searchDecryptedCombineShopping.listItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, shoppingList.listName, "shoppingList", shoppingList.selectionType, itemIndex++))
        }

        return mSearchShoppingItems
    }

    private fun setupMemoriesItems(): ArrayList<Level3SearchItem> {
        mSearchMemoriesItems.clear()
        var itemIndex = 0
        for(mainMemory in searchDecryptCombineMemories.mainMemoriesItems){
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, mainMemory.attachmentNames,"mainMemory",  mainMemory.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(memoryTimeline in searchDecryptCombineMemories.memoryTimelineItems){
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, memoryTimeline.attachmentNames, "memoryTimeline", memoryTimeline.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(memorylist in searchDecryptCombineMemories.listItems){
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, memorylist.listName, "memorylist", memorylist.selectionType, itemIndex++))
        }
        return mSearchMemoriesItems
    }

    private fun setupTravelItems(): ArrayList<Level3SearchItem> {
        mSearchTravelItems.clear()
        var itemIndex = 0
        for(documents in searchDecryptCombineTravel.documentsItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.travel_documents, documents.nameOnTravelDocument, "documents", documents.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(loyalty in searchDecryptCombineTravel.loyaltyItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.loyality_programs, loyalty.accountName, "loyalty", loyalty.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(travel in searchDecryptCombineTravel.travelItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.loyality_programs, travel.accountName, "travel", travel.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(vacation in searchDecryptCombineTravel.vacationsItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.vacation_home, vacation.attachmentNames, "vacation", vacation.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(travelList in searchDecryptCombineTravel.listItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.travel_list, travelList.listName, "travelList", travelList.selectionType, itemIndex++))
        }
        return mSearchTravelItems
    }

    private fun setupHomeItems(): ArrayList<Level3SearchItem> {
        mSearchHomeList.clear()
        var itemIndex = 0
        for( finance in searchDecryptCombine.financialItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  finance.accountName, "finance", finance.selectionType, itemIndex++))
        }
        itemIndex = 0
        for( payment in searchDecryptCombine.paymentItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  payment.userName, "payment", payment.selectionType, itemIndex++))
        }
        itemIndex = 0
        for( asset in searchDecryptCombine.assetItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  asset.assetName, "asset", asset.selectionType, itemIndex++))
        }
        itemIndex = 0
        for( insurance in searchDecryptCombine.insuranceItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  insurance.insuranceCompany, "insurance", insurance.selectionType, itemIndex++))
        }
        itemIndex = 0
        for( tax in searchDecryptCombine.taxesItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  tax.taxPayer, "tax", tax.selectionType, itemIndex++))
        }
        itemIndex = 0
        for( vehicle in searchDecryptCombine.vehicleItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  vehicle.titleName, "vehicle", vehicle.selectionType, itemIndex++))
        }
        itemIndex = 0
        for( property in searchDecryptCombine.propertyItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  property.propertyName, "property", property.selectionType, itemIndex++))
        }
        itemIndex = 0
        for( home in searchDecryptCombine.listItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  home.listName, "home", home.selectionType, itemIndex++))
        }
        return mSearchHomeList
    }

    fun switchAndSearch( searchItem: Level3SearchItem ) {
        val position = searchItem.itemIndex
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

    private fun switchShoppingItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "loyalty" -> {
                val selectedDocument = searchDecryptedCombineShopping.loyaltyProgramsItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "recentPurchase" -> {
                val selectedDocument = searchDecryptedCombineShopping.recentPurchaseItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "shopping" -> {
                val selectedDocument = searchDecryptedCombineShopping.shoppingItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "clothingSize" -> {
                val selectedDocument = searchDecryptedCombineShopping.clothingSizesItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "shoppingList" -> {
                val selectedDocument = searchDecryptedCombineShopping.listItems[position]
                goToCategoryFragment( selectedDocument )
            }
        }
    }



    private fun switchMemoriesItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "mainMemory" -> {
                val selectedDocument = searchDecryptCombineMemories.mainMemoriesItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "memoryTimeline" -> {
                val  selectedDocument = searchDecryptCombineMemories.memoryTimelineItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "memorylist" -> {
                val selectedDocument = searchDecryptCombineMemories.listItems[position]
                goToCategoryFragment( selectedDocument )
            }
        }
    }

    private fun switchWellnessItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "checkups" -> {
                val selectedDocument = searchDecryptedCombineWellness.checkupsItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "emergencyContacts" ->{
                val selectedDocument = searchDecryptedCombineWellness.emergencyContactsItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "eyeglassPrescription" -> {
                val selectedDocument = searchDecryptedCombineWellness.eyeglassPrescriptionsItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "healthcareProvider" -> {
                val selectedDocument = searchDecryptedCombineWellness.healthcareProvidersItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "identification" -> {
                val selectedDocument = searchDecryptedCombineWellness.identificationItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "medicalCondition" -> {
                val selectedDocument = searchDecryptedCombineWellness.medicalConditionsItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "medicalHistory" -> {
                val selectedDocument = searchDecryptedCombineWellness.medicalHistoryItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "medications" -> {
                val selectedDocument = searchDecryptedCombineWellness.medicationsItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "vitalNumbers" -> {
                val selectedDocument = searchDecryptedCombineWellness.vitalNumbersItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "wellness" -> {
                val selectedDocument = searchDecryptedCombineWellness.wellnessItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "wellnessList" -> {
                val selectedDocument = searchDecryptedCombineWellness.listItems[position]
                goToCategoryFragment( selectedDocument )
            }
        }
    }

    private fun switchInterestsItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "interests" -> {
                val selectedDocument = searchDecryptedCombineInterests.interestItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "interestsList" ->
            {
                val selectedDocument = searchDecryptedCombineInterests.listItems[position]
                goToCategoryFragment( selectedDocument )
            }
        }
    }

    private fun switchPersonalItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "certificate" ->{
                val selectedDocument = searchDecryptedCombinePersonal.certificateItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "govenment" -> {
                val selectedDocument = searchDecryptedCombinePersonal.governmentItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "license" -> {
                val selectedDocument = searchDecryptedCombinePersonal.licenseItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "personal" ->{
                val selectedDocument = searchDecryptedCombinePersonal.personalItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "social" -> {
                val selectedDocument = searchDecryptedCombinePersonal.socialItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "taxID" -> {
                val selectedDocument = searchDecryptedCombinePersonal.taxIDItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "personalList" -> {
                val selectedDocument = searchDecryptedCombinePersonal.listItems[position]
                goToCategoryFragment( selectedDocument )
            }
        }
    }

    private fun switchEducationItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "education" -> {
                val selectedDocument = searchDecryptCombineEducation.educationItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "mainEducation" -> {
                val selectedDocument = searchDecryptCombineEducation.mainEducationItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "work" -> {
                val selectedDocument = searchDecryptCombineEducation.workItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "educationList" -> {
                val selectedDocument = searchDecryptCombineEducation.listItems[position]
                goToCategoryFragment( selectedDocument )
            }
        }
    }

    private fun switchContactsItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "contacts" -> {
                val selectedDocument = searchDecryptedCombineContacts.contactsItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "mainContacts" -> {
                val selectedDocument = searchDecryptedCombineContacts.mainContactsItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "contactList" -> {
                val selectedDocument = searchDecryptedCombineContacts.listItems[position]
                goToCategoryFragment( selectedDocument )
            }
        }
    }

    private fun switchTravelItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "document" -> {
                val selectedDocument = searchDecryptCombineTravel.documentsItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "loyalty" -> {
                val selectedDocument = searchDecryptCombineTravel.loyaltyItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "travel" -> {
                val selectedItems = searchDecryptCombineTravel.travelItems[position]
                goToCategoryFragment( selectedItems )
            }
            "vacation" -> {
                val selectedItems = searchDecryptCombineTravel.vacationsItems[position]
                goToCategoryFragment( selectedItems )
            }
            "travelList" -> {
                val selectedItems = searchDecryptCombineTravel.listItems[position]
                goToCategoryFragment( selectedItems )
            }
        }
    }

    private fun switchHomeItems(position: Int, searchItem: Level3SearchItem) {

        when( searchItem.categoryName ) {
            "finance" -> {
                val selectedDocument = searchDecryptCombine.financialItems[position]
                goToCategoryFragment(selectedDocument)
            }
            "payment" -> {
                val selectedDocument = searchDecryptCombine.paymentItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "asset" -> {
                val selectedDocument = searchDecryptCombine.assetItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "insurance" -> {
                val selectedDocument = searchDecryptCombine.insuranceItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "tax" -> {
                val selectedDocument = searchDecryptCombine.taxesItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "vehicle" -> {
                val selectedDocument = searchDecryptCombine.vehicleItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "property" -> {
                val selectedDocument = searchDecryptCombine.propertyItems[position]
                goToCategoryFragment( selectedDocument )
            }
            "home" -> {
                val selectedDocument = searchDecryptCombine.listItems[position]
                goToCategoryFragment( selectedDocument )
            }
        }
    }

    private fun goToCategoryFragment(selectedDocument: Parcelable?) {

    }

}