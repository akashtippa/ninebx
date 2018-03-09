package com.ninebx.ui.home.search

import android.os.Bundle
import android.os.Parcelable
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.home.baseSubCategories.Level2CategoryFragment
import com.ninebx.utility.AppLogger

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
                searchDecryptCombine = combineItems //institution not saved for finance
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
        AppLogger.d("LevelList", "getLevel3SearchItemsForCategory : " + categoryId)
        var level3SearchItems = ArrayList<Level3SearchItem>()
        allItems.filterTo(level3SearchItems) { it.categoryId.equals(categoryId, true) }
        level3SearchItems = filterDuplicates(level3SearchItems)
        return level3SearchItems
    }

    private fun setupContactsItems(): ArrayList<Level3SearchItem> {
        mSearchContactsItems.clear()
        var itemIndex = 0
        for(contacts in searchDecryptedCombineContacts.contactsItems){
            mSearchContactsItems.add(Level3SearchItem(R.string.contacts, contacts.firstName + " " + contacts.lastName, "contacts", contacts.selectionType, itemIndex++, contacts.id))
        }
        itemIndex = 0
        for(mainContacts in searchDecryptedCombineContacts.mainContactsItems){
            mSearchContactsItems.add(Level3SearchItem(R.string.contacts, mainContacts.accountName, "mainContacts", mainContacts.selectionType, itemIndex++, mainContacts.id))
        }
        itemIndex = 0
        for(contactList in searchDecryptedCombineContacts.listItems){
            mSearchContactsItems.add(Level3SearchItem(R.string.contacts, contactList.listName, "contactList", contactList.selectionType, itemIndex++, contactList.id))
        }
        mSearchContactsItems = filterDuplicates( mSearchContactsItems )
        return mSearchContactsItems
    }

    private fun setupEducationItems(): ArrayList<Level3SearchItem> {
        mSearchEducationItems.clear()
        var itemIndex = 0
        for (education in searchDecryptCombineEducation.educationItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education, education.accountName, "education", education.selectionType, itemIndex++, education.id))
        }
        itemIndex = 0
        for(mainEducation in searchDecryptCombineEducation.mainEducationItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education, mainEducation.institutionName, "mainEducation", mainEducation.selectionType, itemIndex++, mainEducation.id))
        }
        itemIndex = 0
        for(work in searchDecryptCombineEducation.workItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education, work.companyName, "work", work.selectionType, itemIndex++, work.id))
        }
        itemIndex = 0
        for(educationList in searchDecryptCombineEducation.listItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education, educationList.listName, "educationList", educationList.selectionType, itemIndex++, educationList.id))
        }
        mSearchEducationItems = filterDuplicates( mSearchEducationItems )
        return mSearchEducationItems
    }

    private fun setupPersonalItems(): ArrayList<Level3SearchItem> {
        mSearchPersonalItems.clear()
        var itemIndex = 0
        for(certificate in searchDecryptedCombinePersonal.certificateItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, certificate.nameOnCertificate, "certificate", certificate.selectionType, itemIndex++, certificate.id))
        }
        itemIndex = 0
        for(government in searchDecryptedCombinePersonal.governmentItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, government.idName, "government", government.selectionType, itemIndex++, government.id))
        }
        itemIndex = 0
        for(license in searchDecryptedCombinePersonal.licenseItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, license.nameOnLicense, "license", license.selectionType, itemIndex++, license.id))
        }
        itemIndex = 0
        for(personal in searchDecryptedCombinePersonal.personalItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, personal.nameOnAccount, "personal", personal.selectionType, itemIndex++, personal.id))
        }
        itemIndex = 0
        for(social in searchDecryptedCombinePersonal.socialItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, social.cardName, "social", social.selectionType, itemIndex++, social.id))
        }
        itemIndex = 0
        for(taxID in searchDecryptedCombinePersonal.taxIDItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, taxID.taxIdName, "taxID", taxID.selectionType, itemIndex++, taxID.id))
        }
        itemIndex = 0
        for(personalList in searchDecryptedCombinePersonal.listItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, personalList.listName, "personalList", personalList.selectionType, itemIndex++, personalList.id))
        }
        mSearchPersonalItems = filterDuplicates( mSearchPersonalItems )
        return mSearchPersonalItems
    }

    private fun setupInterestsItems(): ArrayList<Level3SearchItem> {
        mSearchInterestsItems.clear()
        var itemIndex = 0
        for(interests in searchDecryptedCombineInterests.interestItems){
            mSearchInterestsItems.add(Level3SearchItem(R.string.interests, interests.nameOnAccount, "interests", interests.selectionType, itemIndex++, interests.id))
        }
        itemIndex = 0
        for(interestList in searchDecryptedCombineInterests.listItems){
            mSearchInterestsItems.add(Level3SearchItem(R.string.interests, interestList.listName, "interestList", interestList.selectionType, itemIndex++, interestList.id))
        }
        mSearchInterestsItems = filterDuplicates( mSearchInterestsItems )
        return mSearchInterestsItems
    }

    private fun setupWellnessItems(): ArrayList<Level3SearchItem> {
        mSearchWellnessItems.clear()
        var itemIndex = 0
        for (checkups in searchDecryptedCombineWellness.checkupsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, checkups.physicianName, "checkups", checkups.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(emergencyContacts in searchDecryptedCombineWellness.emergencyContactsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, emergencyContacts.name, "emergencyContacts", emergencyContacts.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(eyeglassPrescription in searchDecryptedCombineWellness.eyeglassPrescriptionsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, eyeglassPrescription.physicianName, "eyeglassPrescription", eyeglassPrescription.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(healthcareProvider in searchDecryptedCombineWellness.healthcareProvidersItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, healthcareProvider.practiceName, "healthcareProvider", healthcareProvider.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(identification in searchDecryptedCombineWellness.identificationItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, identification.name, "identification", identification.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(medicalConditions in searchDecryptedCombineWellness.medicalConditionsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicalConditions.medi_description, "medicalConditions", medicalConditions.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(medicalHistory in searchDecryptedCombineWellness.medicalHistoryItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicalHistory.treatmentDiscription, "medicalHistory", medicalHistory.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(medicaltion in searchDecryptedCombineWellness.medicationsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicaltion.name, "medicationItems", medicaltion.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(vitalNumbers in searchDecryptedCombineWellness.vitalNumbersItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, vitalNumbers.bloodGlucose, "vitalNumbers", vitalNumbers.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(wellness in searchDecryptedCombineWellness.wellnessItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, wellness.nameOnAccount,"wellness",  wellness.selectionType, itemIndex++))
        }
        itemIndex = 0
        for(wellnessList in searchDecryptedCombineWellness.listItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, wellnessList.listName,"wellnessList",  wellnessList.selectionType, itemIndex++))
        }
        mSearchWellnessItems = filterDuplicates( mSearchWellnessItems )
        return mSearchWellnessItems
    }

    private fun setupShoppingItems(): ArrayList<Level3SearchItem> {
        mSearchShoppingItems.clear()
        var itemIndex = 0
        for(loyaltyPrograms in searchDecryptedCombineShopping.loyaltyProgramsItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, loyaltyPrograms.brandName, "loyaltyPrograms", loyaltyPrograms.selectionType, itemIndex++, loyaltyPrograms.id))
        }
        itemIndex = 0
        for(recentPurchase in searchDecryptedCombineShopping.recentPurchaseItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, recentPurchase.itemName, "recentPurchase", recentPurchase.selectionType, itemIndex++, recentPurchase.id))
        }
        itemIndex = 0
        for(shopping in searchDecryptedCombineShopping.shoppingItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, shopping.nameOnAccount, "shopping", shopping.selectionType, itemIndex++, shopping.id))
        }
        itemIndex = 0
        for(clothingSizes in searchDecryptedCombineShopping.clothingSizesItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, clothingSizes.personName, "clothingSizes", clothingSizes.selectionType, itemIndex++, clothingSizes.id))
        }
        itemIndex = 0
        for(shoppingList in searchDecryptedCombineShopping.listItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, shoppingList.listName, "shoppingList", shoppingList.selectionType, itemIndex++, shoppingList.id))
        }
        mSearchShoppingItems = filterDuplicates( mSearchShoppingItems )
        return mSearchShoppingItems
    }

    private fun setupMemoriesItems(): ArrayList<Level3SearchItem> {
        mSearchMemoriesItems.clear()
        var itemIndex = 0
        for(mainMemory in searchDecryptCombineMemories.mainMemoriesItems){
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, mainMemory.nameOnAccount,"mainMemory",  mainMemory.selectionType, itemIndex++, mainMemory.id))
        }
        itemIndex = 0
        for(memoryTimeline in searchDecryptCombineMemories.memoryTimelineItems){
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, memoryTimeline.contacts, "memoryTimeline", memoryTimeline.selectionType, itemIndex++, memoryTimeline.id))
        }
        itemIndex = 0
        for(memorylist in searchDecryptCombineMemories.listItems){
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, memorylist.listName, "memorylist", memorylist.selectionType, itemIndex++, memorylist.id))
        }
        mSearchMemoriesItems = filterDuplicates( mSearchMemoriesItems )
        return mSearchMemoriesItems
    }

    private fun setupTravelItems(): ArrayList<Level3SearchItem> {
        mSearchTravelItems.clear()
        var itemIndex = 0
        for(documents in searchDecryptCombineTravel.documentsItems){
            if (documents!!.selectionType.equals("travel_2001"))
                mSearchTravelItems.add(Level3SearchItem(R.string.travel_documents, documents.passportName, "documents", documents.selectionType, itemIndex++, documents.id))
            if (documents!!.selectionType.equals("travel_2002"))
                mSearchTravelItems.add(Level3SearchItem(R.string.travel_documents, documents.visaName, "documents", documents.selectionType, itemIndex++, documents.id))
            if (documents!!.selectionType.equals("travel_2003"))
                mSearchTravelItems.add(Level3SearchItem(R.string.travel_documents, documents.travelDocumentTitle, "documents", documents.selectionType, itemIndex++, documents.id))
        }
        itemIndex = 0
        for(loyalty in searchDecryptCombineTravel.loyaltyItems){
            if (loyalty!!.selectionType.equals("travel_1001"))
                mSearchTravelItems.add(Level3SearchItem(R.string.loyality_programs, loyalty.airLine, "loyalty", loyalty.selectionType, itemIndex++, loyalty.id))
            if (loyalty!!.selectionType.equals("travel_1002"))
                mSearchTravelItems.add(Level3SearchItem(R.string.loyality_programs, loyalty.hotel, "loyalty", loyalty.selectionType, itemIndex++, loyalty.id))
            if (loyalty!!.selectionType.equals("travel_1003"))
                mSearchTravelItems.add(Level3SearchItem(R.string.loyality_programs, loyalty.carRentalCompany, "loyalty", loyalty.selectionType, itemIndex++, loyalty.id))
            if (loyalty!!.selectionType.equals("travel_1004"))
                mSearchTravelItems.add(Level3SearchItem(R.string.loyality_programs, loyalty.cruiseline, "loyalty", loyalty.selectionType, itemIndex++, loyalty.id))
            if (loyalty!!.selectionType.equals("travel_1005"))
                mSearchTravelItems.add(Level3SearchItem(R.string.loyality_programs, loyalty.railway, "loyalty", loyalty.selectionType, itemIndex++, loyalty.id))
            if (loyalty!!.selectionType.equals("travel_1006"))
                mSearchTravelItems.add(Level3SearchItem(R.string.loyality_programs, loyalty.other, "loyalty", loyalty.selectionType, itemIndex++, loyalty.id))
        }
        itemIndex = 0
        for(travel in searchDecryptCombineTravel.travelItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.travel, travel.nameOnAccount, "travel", travel.selectionType, itemIndex++, travel.id))
        }
        itemIndex = 0
        for(vacation in searchDecryptCombineTravel.vacationsItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.vacation_home, vacation.placesToVisit_1, "vacation", vacation.selectionType, itemIndex++, vacation.id))
        }
        itemIndex = 0
        for(travelList in searchDecryptCombineTravel.listItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.travel_list, travelList.listName, "travelList", travelList.selectionType, itemIndex++, travelList.id))
        }
        mSearchTravelItems = filterDuplicates( mSearchTravelItems )
        return mSearchTravelItems
    }

    private fun setupHomeItems(): ArrayList<Level3SearchItem> {
        mSearchHomeList.clear()
        var itemIndex = 0
        for( finance in searchDecryptCombine.financialItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  finance.accountName, "finance", finance.selectionType, itemIndex++, finance.id))
        }
        itemIndex = 0
        for( payment in searchDecryptCombine.paymentItems ) {
            //if( !mSearchHomeList.contains(Level3SearchItem( R.string.home_amp_money,  payment.cardName, "payment", payment.selectionType, itemIndex, payment.id)))
                mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  payment.cardName, "payment", payment.selectionType, itemIndex++, payment.id))
        }
        itemIndex = 0
        for( asset in searchDecryptCombine.assetItems ) {
            //if( !mSearchHomeList.contains(Level3SearchItem( R.string.home_amp_money,  asset.assetName, "asset", asset.selectionType, itemIndex, asset.id)))
                mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  asset.assetName, "asset", asset.selectionType, itemIndex++, asset.id))
        }
        itemIndex = 0
        for( insurance in searchDecryptCombine.insuranceItems ) {
            //if( !mSearchHomeList.contains(Level3SearchItem( R.string.home_amp_money,  insurance.insuranceCompany, "insurance", insurance.selectionType, itemIndex, insurance.id)))
                mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  insurance.insuranceCompany, "insurance", insurance.selectionType, itemIndex++, insurance.id))
        }
        itemIndex = 0
        for( tax in searchDecryptCombine.taxesItems ) {
            //if( !mSearchHomeList.contains(Level3SearchItem( R.string.home_amp_money,  tax.returnName, "tax", tax.selectionType, itemIndex, tax.id)))
                mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  tax.returnName, "tax", tax.selectionType, itemIndex++, tax.id))
        }
        itemIndex = 0
        for( vehicle in searchDecryptCombine.vehicleItems ) {
            //if( !mSearchHomeList.contains(Level3SearchItem( R.string.home_amp_money,  vehicle.vehicleName, "vehicle", vehicle.selectionType, itemIndex, vehicle.id)))
                mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  vehicle.vehicleName, "vehicle", vehicle.selectionType, itemIndex++, vehicle.id))
        }
        itemIndex = 0
        for( property in searchDecryptCombine.propertyItems ) {
            //if( !mSearchHomeList.contains(Level3SearchItem( R.string.home_amp_money,  property.propertyName, "property", property.selectionType, itemIndex, property.id)))
                mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  property.propertyName, "property", property.selectionType, itemIndex++, property.id))
        }
        itemIndex = 0
        for( home in searchDecryptCombine.listItems ) {
            //if( !mSearchHomeList.contains(Level3SearchItem( R.string.home_amp_money,  home.listName, "home", home.selectionType, itemIndex, home.id)))
                mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  home.listName, "home", home.selectionType, itemIndex++, home.id))
        }
        mSearchHomeList = filterDuplicates( mSearchHomeList )
        return mSearchHomeList
    }

    private fun filterDuplicates(mSearchList: ArrayList<Level3SearchItem>): ArrayList<Level3SearchItem> {
        val level3Items = ArrayList<Level3SearchItem>()
        for( item in mSearchList ) {
            if( !level3Items.contains(item) ) {
                level3Items.add(item)
            }
        }
        return level3Items
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
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "recentPurchase" -> {
                val selectedDocument = searchDecryptedCombineShopping.recentPurchaseItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "shopping" -> {
                val selectedDocument = searchDecryptedCombineShopping.shoppingItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "clothingSize" -> {
                val selectedDocument = searchDecryptedCombineShopping.clothingSizesItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "shoppingList" -> {
                val selectedDocument = searchDecryptedCombineShopping.listItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
        }
    }

    private fun switchMemoriesItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "mainMemory" -> {
                val selectedDocument = searchDecryptCombineMemories.mainMemoriesItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "memoryTimeline" -> {
                val  selectedDocument = searchDecryptCombineMemories.memoryTimelineItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "memorylist" -> {
                val selectedDocument = searchDecryptCombineMemories.listItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
        }
    }

    private fun switchWellnessItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "checkups" -> {
                val selectedDocument = searchDecryptedCombineWellness.checkupsItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "emergencyContacts" ->{
                val selectedDocument = searchDecryptedCombineWellness.emergencyContactsItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "eyeglassPrescription" -> {
                val selectedDocument = searchDecryptedCombineWellness.eyeglassPrescriptionsItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "healthcareProvider" -> {
                val selectedDocument = searchDecryptedCombineWellness.healthcareProvidersItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "identification" -> {
                val selectedDocument = searchDecryptedCombineWellness.identificationItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "medicalCondition" -> {
                val selectedDocument = searchDecryptedCombineWellness.medicalConditionsItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "medicalHistory" -> {
                val selectedDocument = searchDecryptedCombineWellness.medicalHistoryItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "medications" -> {
                val selectedDocument = searchDecryptedCombineWellness.medicationsItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "vitalNumbers" -> {
                val selectedDocument = searchDecryptedCombineWellness.vitalNumbersItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "wellness" -> {
                val selectedDocument = searchDecryptedCombineWellness.wellnessItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "wellnessList" -> {
                val selectedDocument = searchDecryptedCombineWellness.listItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
        }
    }

    private fun switchInterestsItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "interests" -> {
                val selectedDocument = searchDecryptedCombineInterests.interestItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "interestsList" ->
            {
                val selectedDocument = searchDecryptedCombineInterests.listItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
        }
    }

    private fun switchPersonalItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "certificate" ->{
                val selectedDocument = searchDecryptedCombinePersonal.certificateItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "govenment" -> {
                val selectedDocument = searchDecryptedCombinePersonal.governmentItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "license" -> {
                val selectedDocument = searchDecryptedCombinePersonal.licenseItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "personal" ->{
                val selectedDocument = searchDecryptedCombinePersonal.personalItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "social" -> {
                val selectedDocument = searchDecryptedCombinePersonal.socialItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "taxID" -> {
                val selectedDocument = searchDecryptedCombinePersonal.taxIDItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "personalList" -> {
                val selectedDocument = searchDecryptedCombinePersonal.listItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
        }
    }

    private fun switchEducationItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "education" -> {
                val selectedDocument = searchDecryptCombineEducation.educationItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "mainEducation" -> {
                val selectedDocument = searchDecryptCombineEducation.mainEducationItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "work" -> {
                val selectedDocument = searchDecryptCombineEducation.workItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "educationList" -> {
                val selectedDocument = searchDecryptCombineEducation.listItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
        }
    }

    private fun switchContactsItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "contacts" -> {
                val selectedDocument = searchDecryptedCombineContacts.contactsItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "mainContacts" -> {
                val selectedDocument = searchDecryptedCombineContacts.mainContactsItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "contactList" -> {
                val selectedDocument = searchDecryptedCombineContacts.listItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
        }
    }

    private fun switchTravelItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "document" -> {
                val selectedDocument = searchDecryptCombineTravel.documentsItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "loyalty" -> {
                val selectedDocument = searchDecryptCombineTravel.loyaltyItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "travel" -> {
                val selectedItems = searchDecryptCombineTravel.travelItems[position]
                goToCategoryFragment( selectedItems, selectedItems::class.java.simpleName )
            }
            "vacation" -> {
                val selectedItems = searchDecryptCombineTravel.vacationsItems[position]
                goToCategoryFragment( selectedItems, selectedItems::class.java.simpleName )
            }
            "travelList" -> {
                val selectedItems = searchDecryptCombineTravel.listItems[position]
                goToCategoryFragment( selectedItems, selectedItems::class.java.simpleName )
            }
        }
    }

    private fun switchHomeItems(position: Int, searchItem: Level3SearchItem) {

        when( searchItem.categoryName ) {
            "finance" -> {
                val selectedDocument = searchDecryptCombine.financialItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "payment" -> {
                val selectedDocument = searchDecryptCombine.paymentItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "asset" -> {
                val selectedDocument = searchDecryptCombine.assetItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "insurance" -> {
                val selectedDocument = searchDecryptCombine.insuranceItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "tax" -> {
                val selectedDocument = searchDecryptCombine.taxesItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "vehicle" -> {
                val selectedDocument = searchDecryptCombine.vehicleItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "property" -> {
                val selectedDocument = searchDecryptCombine.propertyItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "home" -> {
                val selectedDocument = searchDecryptCombine.listItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
        }
    }

    interface OnDocumentSelection {
        fun onDocumentSelected( selectedDocument: Parcelable?, classType : String )
    }

    private var mOnDocumentSelection : OnDocumentSelection ?= null

    fun setOnDocumentSelection( onDocumentSelection: OnDocumentSelection ) {
        this.mOnDocumentSelection = onDocumentSelection
    }

    private fun goToCategoryFragment( selectedDocument: Parcelable?, classType: String ) {
        if( mOnDocumentSelection != null ) {
            //AppLogger.d("ClassType", "Parcelable classType : " + classType )
            mOnDocumentSelection!!.onDocumentSelected( selectedDocument, classType )
        }
    }

}