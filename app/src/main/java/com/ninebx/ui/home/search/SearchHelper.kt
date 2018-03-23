package com.ninebx.ui.home.search

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Parcelable
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.showProgressDialog
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmModel

/**
 * Created by Alok on 20/02/18.
 */
@SuppressLint("StaticFieldLeak")
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

    private var mCombineItems : Parcelable ?= null

    fun getSearchItems( combineItems : Parcelable ) : ArrayList<Level3SearchItem> {
        this.mCombineItems = combineItems
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
            mSearchEducationItems.add(Level3SearchItem(R.string.education_work, education.institutionName, "education", education.selectionType, itemIndex++, education.id, education.accountName))
        }
        itemIndex = 0
        for(mainEducation in searchDecryptCombineEducation.mainEducationItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education_work, mainEducation.institutionName, "mainEducation", mainEducation.selectionType, itemIndex++, mainEducation.id, mainEducation.qualification))
        }
        itemIndex = 0
        for(work in searchDecryptCombineEducation.workItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education_work, work.companyName, "work", work.selectionType, itemIndex++, work.id))
        }
        itemIndex = 0
        for(educationList in searchDecryptCombineEducation.listItems){
            mSearchEducationItems.add(Level3SearchItem(R.string.education_work, educationList.listName, "educationList", educationList.selectionType, itemIndex++, educationList.id))
        }
        mSearchEducationItems = filterDuplicates( mSearchEducationItems )
        return mSearchEducationItems
    }

    private fun setupPersonalItems(): ArrayList<Level3SearchItem> {
        mSearchPersonalItems.clear()
        var itemIndex = 0
        for(certificate in searchDecryptedCombinePersonal.certificateItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, certificate.cer_description, "certificate", certificate.selectionType, itemIndex++, certificate.id, certificate.nameOnCertificate))
        }
        itemIndex = 0
        for(government in searchDecryptedCombinePersonal.governmentItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, government.idName, "government", government.selectionType, itemIndex++, government.id, government.nameOnId))
        }
        itemIndex = 0
        for(license in searchDecryptedCombinePersonal.licenseItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, license.lic_description, "license", license.selectionType, itemIndex++, license.id, license.nameOnLicense))
        }
        itemIndex = 0
        for(personal in searchDecryptedCombinePersonal.personalItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, personal.institutionName, "personal", personal.selectionType, itemIndex++, personal.id, personal.accountName))
        }
        itemIndex = 0
        for(social in searchDecryptedCombinePersonal.socialItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, social.cardName, "social", social.selectionType, itemIndex++, social.id, social.nameOnCard))
        }
        itemIndex = 0
        for(taxID in searchDecryptedCombinePersonal.taxIDItems){
            mSearchPersonalItems.add(Level3SearchItem(R.string.personal, taxID.taxIdName, "taxID", taxID.selectionType, itemIndex++, taxID.id, taxID.name))
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
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, emergencyContacts.name, "emergencyContacts", emergencyContacts.selectionType, itemIndex++, emergencyContacts.id, emergencyContacts.relationShip))
        }
        itemIndex = 0
        for(eyeglassPrescription in searchDecryptedCombineWellness.eyeglassPrescriptionsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, eyeglassPrescription.physicianName, "eyeglassPrescription", eyeglassPrescription.selectionType, itemIndex++, eyeglassPrescription.id, eyeglassPrescription.datePrescribed))
        }
        itemIndex = 0
        for(healthcareProvider in searchDecryptedCombineWellness.healthcareProvidersItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, healthcareProvider.name, "healthcareProvider", healthcareProvider.selectionType, itemIndex++, healthcareProvider.id, healthcareProvider.physicianType))
        }
        itemIndex = 0
        for(identification in searchDecryptedCombineWellness.identificationItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, identification.name, "identification", identification.selectionType, itemIndex++, identification.id, identification.gender))
        }
        itemIndex = 0
        for(medicalConditions in searchDecryptedCombineWellness.medicalConditionsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicalConditions.condition, "medicalConditions", medicalConditions.selectionType, itemIndex++, medicalConditions.id, medicalConditions.dateDiagnosed))
        }
        itemIndex = 0
        for(medicalHistory in searchDecryptedCombineWellness.medicalHistoryItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicalHistory.history, "medicalHistory", medicalHistory.selectionType, itemIndex++, medicalHistory.id, medicalHistory.treatmentDiscription))
        }
        itemIndex = 0
        for(medicaltion in searchDecryptedCombineWellness.medicationsItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, medicaltion.name, "medicationItems", medicaltion.selectionType, itemIndex++, medicaltion.id, medicaltion.strength))
        }
        itemIndex = 0
        for(vitalNumbers in searchDecryptedCombineWellness.vitalNumbersItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, vitalNumbers.vital_description, "vitalNumbers", vitalNumbers.selectionType, itemIndex++, vitalNumbers.id, vitalNumbers.measurementDate))
        }
        itemIndex = 0
        for(wellness in searchDecryptedCombineWellness.wellnessItems){
            mSearchWellnessItems.add(Level3SearchItem(R.string.wellness, wellness.institutionName,"wellness",  wellness.selectionType, itemIndex++, wellness.id, wellness.accountName))
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
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, loyaltyPrograms.brandName, "loyaltyPrograms", loyaltyPrograms.selectionType, itemIndex++, loyaltyPrograms.id, loyaltyPrograms.accountName))
        }
        itemIndex = 0
        for(recentPurchase in searchDecryptedCombineShopping.recentPurchaseItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, recentPurchase.itemName, "recentPurchase", recentPurchase.selectionType, itemIndex++, recentPurchase.id, recentPurchase.brandName))
        }
        itemIndex = 0
        for(shopping in searchDecryptedCombineShopping.shoppingItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, shopping.institutionName, "shopping", shopping.selectionType, itemIndex++, shopping.id, shopping.accountName))
        }
        itemIndex = 0
        for(clothingSizes in searchDecryptedCombineShopping.clothingSizesItems){
            mSearchShoppingItems.add(Level3SearchItem(R.string.shopping, clothingSizes.personName, "clothingSizes", clothingSizes.selectionType, itemIndex++, clothingSizes.id, clothingSizes.sizeName))
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
            mSearchMemoriesItems.add(Level3SearchItem(R.string.memories, mainMemory.nameOnAccount,"mainMemory",  mainMemory.selectionType, itemIndex++, mainMemory.id, mainMemory.accountName))
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
                mSearchTravelItems.add(Level3SearchItem(R.string.travel, documents.passportName, "documents", documents.selectionType, itemIndex++, documents.id, documents.nameOnPassport))
            if (documents!!.selectionType.equals("travel_2002"))
                mSearchTravelItems.add(Level3SearchItem(R.string.travel, documents.visaName, "documents", documents.selectionType, itemIndex++, documents.id, documents.nameOnVisa))
            if (documents!!.selectionType.equals("travel_2003"))
                mSearchTravelItems.add(Level3SearchItem(R.string.travel, documents.travelDocumentTitle, "documents", documents.selectionType, itemIndex++, documents.id, documents.nameOnTravelDocument))
        }
        itemIndex = 0
        for(loyalty in searchDecryptCombineTravel.loyaltyItems){
            if (loyalty!!.selectionType.equals("travel_1001"))
                mSearchTravelItems.add(Level3SearchItem(R.string.travel, loyalty.airLine, "loyalty", loyalty.selectionType, itemIndex++, loyalty.id))
            if (loyalty!!.selectionType.equals("travel_1002"))
                mSearchTravelItems.add(Level3SearchItem(R.string.travel, loyalty.hotel, "loyalty", loyalty.selectionType, itemIndex++, loyalty.id))
            if (loyalty!!.selectionType.equals("travel_1003"))
                mSearchTravelItems.add(Level3SearchItem(R.string.travel, loyalty.carRentalCompany, "loyalty", loyalty.selectionType, itemIndex++, loyalty.id))
            if (loyalty!!.selectionType.equals("travel_1004"))
                mSearchTravelItems.add(Level3SearchItem(R.string.travel, loyalty.cruiseline, "loyalty", loyalty.selectionType, itemIndex++, loyalty.id))
            if (loyalty!!.selectionType.equals("travel_1005"))
                mSearchTravelItems.add(Level3SearchItem(R.string.travel, loyalty.railway, "loyalty", loyalty.selectionType, itemIndex++, loyalty.id))
            if (loyalty!!.selectionType.equals("travel_1006"))
                mSearchTravelItems.add(Level3SearchItem(R.string.travel, loyalty.other, "loyalty", loyalty.selectionType, itemIndex++, loyalty.id))
        }
        itemIndex = 0
        for(travel in searchDecryptCombineTravel.travelItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.travel, travel.institutionName, "travel", travel.selectionType, itemIndex++, travel.id, travel.accountName))
        }
        itemIndex = 0
        for(vacation in searchDecryptCombineTravel.vacationsItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.travel, vacation.vac_description, "vacation", vacation.selectionType, itemIndex++, vacation.id))
        }
        itemIndex = 0
        for(travelList in searchDecryptCombineTravel.listItems){
            mSearchTravelItems.add(Level3SearchItem(R.string.travel, travelList.listName, "travelList", travelList.selectionType, itemIndex++, travelList.id))
        }
        mSearchTravelItems = filterDuplicates( mSearchTravelItems )
        return mSearchTravelItems
    }

    private fun setupHomeItems(): ArrayList<Level3SearchItem> {
        mSearchHomeList.clear()
        var itemIndex = 0
        for( finance in searchDecryptCombine.financialItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  finance.institutionName, "finance", finance.selectionType, itemIndex++, finance.id, finance.accountName))
        }
        itemIndex = 0
        for( payment in searchDecryptCombine.paymentItems ) {
            //if( !mSearchHomeList.contains(Level3SearchItem( R.string.home_amp_money,  payment.cardName, "payment", payment.selectionType, itemIndex, payment.id)))
            if(payment.selectionType.equals("home_2001")){
                mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  payment.cardName, "payment", payment.selectionType, itemIndex++, payment.id))
            }
        }
        itemIndex = 0
        for( asset in searchDecryptCombine.assetItems ) {
            //if( !mSearchHomeList.contains(Level3SearchItem( R.string.home_amp_money,  asset.assetName, "asset", asset.selectionType, itemIndex, asset.id)))
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  asset.assetName, "asset", asset.selectionType, itemIndex++, asset.id, asset.descriptionOrLocation))
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
            if(vehicle.selectionType.equals("home_4001")){
                mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  vehicle.vehicleName, "vehicle", vehicle.selectionType, itemIndex++, vehicle.id))
            }
            if (vehicle.selectionType.equals("home_4002")){
                mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  vehicle.maintenanceEvent, "vehicle", vehicle.selectionType, itemIndex++, vehicle.id))
            }
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
            else {
                level3Items[level3Items.indexOf(item)] = item
            }
        }
        return level3Items
    }

    private var mAction : String = ""
    fun switchAndSearch( searchItem: Level3SearchItem, action : String ) {
        val position = searchItem.itemIndex
        mAction = action

        when( searchItem.searchCategory ) {

            (R.string.home_amp_money) -> {
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
            "loyaltyPrograms" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineShopping.loyaltyProgramsItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_SHOPPING, selectedDocument.id, encryptLoyaltyProgram(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineShopping.loyaltyProgramsItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "recentPurchase" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineShopping.recentPurchaseItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_SHOPPING, selectedDocument.id, encryptRecentPurchase(selectedDocument)  )
                }
                else {
                    val selectedDocument = searchDecryptedCombineShopping.recentPurchaseItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "shopping" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineShopping.shoppingItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_SHOPPING, selectedDocument.id, encryptShopping(selectedDocument)  )
                }
                else {
                    val selectedDocument = searchDecryptedCombineShopping.shoppingItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "clothingSize" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineShopping.clothingSizesItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_SHOPPING, selectedDocument.id, encryptClothingSizes(selectedDocument)  )
                }
                else {
                    val selectedDocument = searchDecryptedCombineShopping.clothingSizesItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "shoppingList" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineShopping.listItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_SHOPPING, selectedDocument.id, encryptShoppingList(selectedDocument)  )
                }
                else {
                    val selectedDocument = searchDecryptedCombineShopping.listItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
        }
    }

    private fun deleteDocumentFromRealm(realmEndPoint : String, selectedDocumentId: Long, clazz : RealmModel ) {

        object : AsyncTask<Void, Void, Unit>() {

            override fun onPreExecute() {
                super.onPreExecute()
                NineBxApplication.instance.showProgressDialog("Deleting Item")
            }

            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections( NineBxApplication.instance, false, realmEndPoint, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        realm!!.beginTransaction()
                        realm.where(clazz.javaClass).equalTo("id", selectedDocumentId).findAll().deleteAllFromRealm()
                        realm.commitTransaction()
                        realm.close()
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                NineBxApplication.instance.hideProgressDialog()
                if( mOnDocumentSelection != null ) {
                    mOnDocumentSelection!!.onDocumentRemoved(mCombineItems!!)
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private fun switchMemoriesItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "mainMemory" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombineMemories.mainMemoriesItems.removeAt(position)
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_MEMORIES, selectedDocument.id, encryptMainMemories(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombineMemories.mainMemoriesItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "memoryTimeline" -> {

                if( mAction == "delete" ) {
                    val  selectedDocument = searchDecryptCombineMemories.memoryTimelineItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_MEMORIES, selectedDocument.id, encryptMemoryTimeLIne(selectedDocument) )
                }
                else {
                    val  selectedDocument = searchDecryptCombineMemories.memoryTimelineItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "memorylist" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombineMemories.listItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_MEMORIES, selectedDocument.id, encryptMemoriesList(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombineMemories.listItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }
            }
        }
    }

    private fun switchWellnessItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "checkups" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineWellness.checkupsItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_WELLNESS, selectedDocument.id, encryptCheckUps(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineWellness.checkupsItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "emergencyContacts" ->{

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineWellness.emergencyContactsItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_WELLNESS, selectedDocument.id, encryptEmergencyContacts(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineWellness.emergencyContactsItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "eyeglassPrescription" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineWellness.eyeglassPrescriptionsItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_WELLNESS, selectedDocument.id, encryptEyeGlassPrescriptions(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineWellness.eyeglassPrescriptionsItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "healthcareProvider" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineWellness.healthcareProvidersItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_WELLNESS, selectedDocument.id, encryptHealthCareProviders(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineWellness.healthcareProvidersItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "identification" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineWellness.identificationItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_WELLNESS, selectedDocument.id, encryptIdentification(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineWellness.identificationItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "medicalCondition" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineWellness.medicalConditionsItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_WELLNESS, selectedDocument.id, encryptMedicalConditions(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineWellness.medicalConditionsItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "medicalHistory" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineWellness.medicalHistoryItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_WELLNESS, selectedDocument.id, encryptMedicalHistory(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineWellness.medicalHistoryItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "medications" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineWellness.medicationsItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_WELLNESS, selectedDocument.id, encryptMedications(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineWellness.medicationsItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "vitalNumbers" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineWellness.vitalNumbersItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_WELLNESS, selectedDocument.id, encryptVitalNumbers(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineWellness.vitalNumbersItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "wellness" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineWellness.wellnessItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_WELLNESS, selectedDocument.id, encryptWellness(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineWellness.wellnessItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "wellnessList" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineWellness.listItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_WELLNESS, selectedDocument.id, encryptWellnessList(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineWellness.listItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
        }
    }

    private fun switchInterestsItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "interests" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineInterests.interestItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_INTERESTS, selectedDocument.id, encryptInterests(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineInterests.interestItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "interestsList" ->
            {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineInterests.listItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_INTERESTS, selectedDocument.id, encryptInterestList(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombineInterests.listItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
        }
    }

    private fun switchPersonalItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "certificate" ->{

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombinePersonal.certificateItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_PERSONAL, selectedDocument.id, encryptCertificate(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombinePersonal.certificateItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "government" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombinePersonal.governmentItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_PERSONAL, selectedDocument.id, encryptGovernment(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombinePersonal.governmentItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "license" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombinePersonal.licenseItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_PERSONAL, selectedDocument.id, encryptLicense(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombinePersonal.licenseItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "personal" ->{

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombinePersonal.personalItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_PERSONAL, selectedDocument.id, encryptPersonal(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombinePersonal.personalItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "social" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombinePersonal.socialItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_PERSONAL, selectedDocument.id, encryptSocial(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombinePersonal.socialItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "taxID" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombinePersonal.taxIDItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_PERSONAL, selectedDocument.id, encryptTaxID(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombinePersonal.taxIDItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "personalList" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombinePersonal.listItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_PERSONAL, selectedDocument.id, encryptPersonalList(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptedCombinePersonal.listItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
        }
    }

    private fun switchEducationItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "education" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombineEducation.educationItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_EDUCATION, selectedDocument.id, encryptEducation(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombineEducation.educationItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "mainEducation" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombineEducation.mainEducationItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_EDUCATION, selectedDocument.id, encryptMainEducation(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombineEducation.mainEducationItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "work" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombineEducation.workItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_EDUCATION, selectedDocument.id, encryptWork(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombineEducation.workItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "educationList" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombineEducation.listItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_EDUCATION, selectedDocument.id, encryptEducationList(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombineEducation.listItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
        }
    }

    private fun switchContactsItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "contacts" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineContacts.contactsItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_CONTACTS, selectedDocument.id, encryptContact(selectedDocument) )
                    return
                }
                val selectedDocument = searchDecryptedCombineContacts.contactsItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "mainContacts" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineContacts.mainContactsItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_CONTACTS, selectedDocument.id, encryptMainContacts(selectedDocument) )
                    return
                }
                val selectedDocument = searchDecryptedCombineContacts.mainContactsItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "contactList" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptedCombineContacts.listItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_CONTACTS, selectedDocument.id, encryptContactsList(selectedDocument) )
                    return
                }
                val selectedDocument = searchDecryptedCombineContacts.listItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
        }
    }

    private fun switchTravelItems(position: Int, searchItem: Level3SearchItem) {
        when(searchItem.categoryName){
            "documents" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombineTravel.documentsItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_TRAVEL, selectedDocument.id, encryptDocuments(selectedDocument) )
                    return
                }
                val selectedDocument = searchDecryptCombineTravel.documentsItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "loyalty" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombineTravel.loyaltyItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_TRAVEL, selectedDocument.id, encryptLoyalty(selectedDocument) )
                    return
                }
                val selectedDocument = searchDecryptCombineTravel.loyaltyItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "travel" -> {

                if( mAction == "delete" ) {
                    val selectedItems = searchDecryptCombineTravel.travelItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_TRAVEL, selectedItems.id, encryptTravel(selectedItems) )
                    return
                }
                val selectedDocument = searchDecryptCombineTravel.travelItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "vacation" -> {

                if( mAction == "delete" ) {
                    val selectedItems = searchDecryptCombineTravel.vacationsItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_TRAVEL, selectedItems.id, encryptVacations(selectedItems) )
                    return
                }
                val selectedDocument = searchDecryptCombineTravel.vacationsItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
            "travelList" -> {

                if( mAction == "delete" ) {
                    val selectedItems = searchDecryptCombineTravel.listItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE_TRAVEL, selectedItems.id, encryptTravelList(selectedItems) )
                    return
                }
                val selectedDocument = searchDecryptCombineTravel.listItems[position]
                goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
            }
        }
    }

    private fun switchHomeItems(position: Int, searchItem: Level3SearchItem) {

        when( searchItem.categoryName ) {
            "finance" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombine.financialItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE, selectedDocument.id, encryptFinancial(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombine.financialItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "payment" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombine.paymentItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE, selectedDocument.id, encryptPayment(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombine.paymentItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "asset" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombine.assetItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE, selectedDocument.id, encryptAsset(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombine.assetItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "insurance" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombine.insuranceItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE, selectedDocument.id, encryptInsurance(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombine.insuranceItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "tax" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombine.taxesItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE, selectedDocument.id, encryptTaxes(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombine.taxesItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "vehicle" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombine.vehicleItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE, selectedDocument.id, encryptVehicle(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombine.vehicleItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "property" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombine.propertyItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE, selectedDocument.id, encryptProperty(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombine.propertyItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
            "home" -> {

                if( mAction == "delete" ) {
                    val selectedDocument = searchDecryptCombine.listItems.removeAt( position )
                    deleteDocumentFromRealm( Constants.REALM_END_POINT_COMBINE, selectedDocument.id, encryptHomeList(selectedDocument) )
                }
                else {
                    val selectedDocument = searchDecryptCombine.listItems[position]
                    goToCategoryFragment( selectedDocument, selectedDocument::class.java.simpleName )
                }

            }
        }
    }


    interface OnDocumentSelection {
        fun onDocumentSelected( selectedDocument: Parcelable?, classType : String, action : String )
        fun onDocumentRemoved( combineParcelable: Parcelable )
    }

    private var mOnDocumentSelection : OnDocumentSelection ?= null

    fun setOnDocumentSelection( onDocumentSelection: OnDocumentSelection ) {
        this.mOnDocumentSelection = onDocumentSelection
    }

    private fun goToCategoryFragment( selectedDocument: Parcelable?, classType: String ) {
        if( mOnDocumentSelection != null && mAction != "delete" ) {
            //AppLogger.d("ClassType", "Parcelable classType : " + classType )
            mOnDocumentSelection!!.onDocumentSelected( selectedDocument, classType, mAction )
        }
    }

    fun switchAndAdd(mCurrentSearchItem: Level3SearchItem, action: String?, parcelableExtra: Parcelable?) {

    }
}