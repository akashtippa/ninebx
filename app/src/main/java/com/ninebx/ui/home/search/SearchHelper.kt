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


}