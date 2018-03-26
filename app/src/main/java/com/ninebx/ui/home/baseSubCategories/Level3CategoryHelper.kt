package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context

import android.os.Bundle
import android.os.Parcelable
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.*

import com.ninebx.ui.home.fragments.MemoryTimeLineFragment
import com.ninebx.ui.home.fragments.SingleContactViewFragment
import com.ninebx.ui.home.lists.SubListsFragment
import com.ninebx.utility.*
import java.text.SimpleDateFormat

import java.util.*

/***
 * Created by TechnoBlogger on 23/01/18.
 */
class Level3CategoryHelper(
        val categoryInt : Int,
        val category_name: String,
        val categoryID: String,
        val categoryView: Level2CategoryView,
        val selectedDocument: Parcelable?,
        val classType: String?
) {
    // For Travel
    private var decryptedLoyalty: DecryptedLoyalty? = null // decryptedLoyalty()
    private var decryptedTravel: DecryptedTravel? = null // DecryptedTravel()
    private var decryptedDocuments: DecryptedDocuments? = null // DecryptedTravel()
    private var decryptedVacations: DecryptedVacations? = null // DecryptedTravel()

    //For Wellness


    //For Shopping


    private var homeHelper : HomeHelper ?= null
    private var personalHelper : PersonalHelper ?= null

    private var wellnessHelper : WellnessHelper ?= null
    private var shoppingHelper : ShoppingHelper ?= null

    private var travelHelper: TravelHelper ?= null
    private var educationAndWorkHelper : EducationAndWorkHelper ?= null
    private var commonItemHelper : CommonItemsHelper ?= null

    init {
        extractObject()
        setupCommonHelper()
        when( categoryInt ) {
            R.string.home_n_money -> {
                homeHelper = HomeHelper(category_name, categoryID, classType, selectedDocument, categoryView)
                homeHelper!!.initialize()
                homeHelper!!.getFormForCategory()
                searchByOthers()
            }
            R.string.personal -> {
                personalHelper = PersonalHelper(category_name, categoryID, classType, selectedDocument, categoryView)
                personalHelper!!.initialize()
                personalHelper!!.getFormForCategory()
                searchByOthers()
            }

            R.string.wellness -> {
                wellnessHelper = WellnessHelper(category_name,categoryID,classType,selectedDocument,categoryView)
                wellnessHelper!!.initialize()
                wellnessHelper!!.getFormForCategory()
                searchByOthers()
            }
            R.string.shopping -> {
                shoppingHelper = ShoppingHelper(category_name, categoryID, classType, selectedDocument, categoryView)
                shoppingHelper!!.initialize()
                shoppingHelper!!.getFormForCategory()
                searchByOthers()
            }
            R.string.travel -> {
                travelHelper = TravelHelper(category_name, categoryID, classType, selectedDocument, categoryView)
                travelHelper!!.initialize()
                travelHelper!!.getFormForCategory()
                searchByOthers()
            }
            R.string.education_work ->{
                educationAndWorkHelper = EducationAndWorkHelper(category_name, categoryID, classType, selectedDocument, categoryView)
                educationAndWorkHelper!!.initialize()
                educationAndWorkHelper!!.getFormForCategory()
                searchByOthers()
            }
            else -> {
                searchByOthers()
            }
        }
    }

    private fun setupCommonHelper() {
        commonItemHelper = CommonItemsHelper(category_name, categoryID, classType, selectedDocument, categoryView, categoryInt)
        commonItemHelper!!.initialize()
    }

    private fun searchByOthers() {
        when (category_name) {

        // Common View
            "Services/Other Accounts" -> {
                commonItemHelper!!.getFormForCategory()
            }
            "Other Attachments" -> {
                getOtherAttachments()
            }



            "Add Person" -> {
                getEducation()
            }

            "Add person" -> {
                getWork()
            }
            "Travel Dates And Plans"-> {
                getTravelDatesAndPlans()
            }

        // Wellness


        }
    }


    private fun extractObject() {
        if (selectedDocument == null) {
            return
        }
        when (classType) {


        // For Travel
            DecryptedLoyalty::class.java.simpleName -> {
                decryptedLoyalty = selectedDocument as DecryptedLoyalty
            }
            DecryptedTravel::class.java.simpleName -> {
                decryptedTravel = selectedDocument as DecryptedTravel
            }
            DecryptedDocuments::class.java.simpleName -> {
                decryptedDocuments = selectedDocument as DecryptedDocuments
            }
            DecryptedVacations::class.java.simpleName -> {
                decryptedVacations = selectedDocument as DecryptedVacations
            }

        // For Wellness


        //For Shopping

            else -> {
                //TODO
            }
        }
    }




    // Types of InputTYpe
    // Number
    // Picker
    // Password








    // TRAVEL











    private fun getTravelDatesAndPlans() {
        val categoryList = ArrayList<Level2Category>()
        if( decryptedVacations == null ) decryptedVacations = DecryptedVacations()
        var categoryIndex = 3001
        var category_id = "travel_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Plans confirmed?", "", "", Constants.LEVEL2_SWITCH, decryptedVacations!!.plansConfirmed))
        category.subCategories.add(Level2SubCategory("Start date", decryptedVacations!!.startDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("End date", decryptedVacations!!.endDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Plans to visit/consider 1", decryptedVacations!!.placesToVisit_1, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Plans to visit/consider 2", decryptedVacations!!.placesToVisit_2, "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Plans to visit/consider 3", decryptedVacations!!.placesToVisit_3, "", Constants.LEVEL2_LOCATION))
        categoryList.add(category)

        categoryIndex += 2035
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedVacations!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2035
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }



    // Types of InputTYpe
    // Number
    // Picker
    // Password




    private fun getFragmentSubList() {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        val bundle = Bundle()
        bundle.putString("homeScreen", "HomeScreen")

        val categoryFragment = SubListsFragment()
        categoryFragment.arguments = bundle

        fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
    }

    private fun getMemoryTimeLine() {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        val categoryFragment = MemoryTimeLineFragment()
        fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
    }

    private fun getContactsList() {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        val categoryFragment = SingleContactViewFragment()
        fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
    }



    // SERVICES/OTHER ACCOUNTS
    private fun getServicesOthersAccounts() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 1001
        var category_id = "interest_" + categoryIndex
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
        category.subCategories.add(Level2SubCategory("Payment method on file", "Payment method on file", "", Constants.LEVEL2_SPINNER))

        categoryList.add(category)

        categoryIndex += 3001
        category_id = "service_details" + categoryIndex
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







    // Education and Work


    private fun getEducation() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 1001
        var category_id = "edu_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name", "Name", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Concenteration/majaor", "Concenteration/majaor", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("From", "From", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("To", "To", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Currently studying here", "", "", Constants.LEVEL2_SWITCH))
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

        var categoryIndex = 2001
        var category_id = "edu_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name", "Name", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("From", "From", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("To", "To", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Currently working here", "", "", Constants.LEVEL2_SWITCH))
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

    private fun getOtherAttachments() {
        val categoryList = ArrayList<Level2Category>()

        var categoryIndex = 2001
        var category_id = "interest_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2032
        category_id = "notes" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }







    // Education and Work
    // Wellness








    fun setValue(level2Category: Level2SubCategory) {
        if( homeHelper != null ) {
            homeHelper!!.setValue(level2Category)
        }
        if( personalHelper != null){
            personalHelper!!.setValue(level2Category)
        }

        if( shoppingHelper != null){
            shoppingHelper!!.setValue(level2Category)
        }

        if(travelHelper != null) {
            travelHelper!!.setValue(level2Category)
        }
        if(educationAndWorkHelper != null) {
            educationAndWorkHelper!!.setValue(level2Category)
        }

        if(commonItemHelper != null) {
            when (category_name) {

            // Common View
                "Services/Other Accounts" -> {
                    commonItemHelper!!.setValue(level2Category)
                }
                "Other Attachments" -> {
                    getOtherAttachments()
                }

                "Add Person" -> {
                    getEducation()
                }

                "Add person" -> {
                    getWork()
                }

            // Wellness




            }
        }

    }

    private var mCombine : Parcelable ?= null
    @SuppressLint("StaticFieldLeak")
    fun saveDocument(context: Context, combineItem: Parcelable?, title: String, subTitle: String) {
        mCombine = combineItem
        val currentUsers = NineBxApplication.getPreferences().userFirstName +" " +  NineBxApplication.getPreferences().userLastName
        val sdf = SimpleDateFormat(" E,MMM dd,yyyy, HH:mm")
        val currentDateandTime = sdf.format( Date())


        if( homeHelper != null ) {
            homeHelper!!.saveDocument(context, combineItem, title, subTitle)
        }

        if(personalHelper != null){
            personalHelper!!.saveDocument(context,combineItem, title)
        }
        if(travelHelper != null) {
            travelHelper!!.saveDocument(context, combineItem, title, subTitle)
        }
        if(educationAndWorkHelper != null) {
            educationAndWorkHelper!!.saveDocument(context,combineItem,title, subTitle)
        }

        if(wellnessHelper!=null){
            wellnessHelper!!.saveDocument(context,combineItem, title, subTitle)
        }
        if(shoppingHelper!=null){
            shoppingHelper!!.saveDocument(context,combineItem, title, subTitle)
        }
        if(commonItemHelper != null) {
            commonItemHelper!!.saveDocument(context, combineItem, title, subTitle)
        }

   /*     if (decryptedLoyalty != null) {
            decryptedLoyalty!!.selectionType = categoryID
            if (decryptedLoyalty!!.selectionType.equals("travel_1001"))
                decryptedLoyalty!!.airLine = title
            if (decryptedLoyalty!!.selectionType.equals("travel_1002"))
                decryptedLoyalty!!.hotel = title
            if (decryptedLoyalty!!.selectionType.equals("travel_1003"))
                decryptedLoyalty!!.carRentalCompany = title
            if (decryptedLoyalty!!.selectionType.equals("travel_1004"))
                decryptedLoyalty!!.cruiseline = title
            if (decryptedLoyalty!!.selectionType.equals("travel_1005"))
                decryptedLoyalty!!.railway = title
            if (decryptedLoyalty!!.selectionType.equals("travel_1006"))
                decryptedLoyalty!!.other = title
            if( decryptedLoyalty!!.created.isEmpty() )
                decryptedLoyalty!!.created = currentUsers + " " + currentDateandTime

            decryptedLoyalty!!.modified = currentUsers + " " + currentDateandTime
            decryptedLoyalty!!.accountName = subTitle

            AppLogger.d("LoyaltySelectionType", " " + decryptedLoyalty!!.selectionType)
            AppLogger.d("Level2Category", "decryptedLoyalty " + decryptedLoyalty)

            if (decryptedLoyalty!!.id.toInt() == 0) {
                decryptedLoyalty!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            AppLogger.d("saveDocument", "decryptedLoyalty" + decryptedLoyalty!!)
                            var getDecryptedLoyalty = realm!!.where(CombineTravel::class.java).findFirst()
                            AppLogger.d("getDecryptedLoyalty", "DecryptedCombineTravel" + getDecryptedLoyalty)

                            if(getDecryptedLoyalty!!.loyaltyItems != null && getDecryptedLoyalty!!.loyaltyItems.size > 0){
                                for(loyaltyItems in getDecryptedLoyalty!!.loyaltyItems){
                                    var deleteLoyalty = realm!!.where(CombineTravel::class.java).equalTo("id", loyaltyItems.id).findAll()
                                    realm.beginTransaction()
                                    if( deleteLoyalty != null )
                                        deleteLoyalty.deleteAllFromRealm()
                                    realm.commitTransaction()
                                }
                            }

                            realm!!.beginTransaction()
                            val loyalty = encryptLoyalty(decryptedLoyalty!!)
                            realm.insertOrUpdate(loyalty)
                            realm.copyToRealmOrUpdate(loyalty)
                            realm.commitTransaction()
                            //  fragmentListContainer!!.setRecyclerView()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {

                            var realmLoyalty = realm!!.where(CombineTravel::class.java).findFirst()

                            if (realmLoyalty == null) {
                                realm.beginTransaction()
                                realmLoyalty = realm.createObject(CombineTravel::class.java, getUniqueId())
                                realm.commitTransaction()
                            }
                            for(loyaltyItems in realmLoyalty!!.loyaltyItems){
                                if (loyaltyItems!!.id.toInt() == decryptedLoyalty!!.id.toInt() ){
                                    var deleteLoyalty : RealmResults<CombineTravel> = realm!!.where(CombineTravel::class.java)
                                            .equalTo("id", loyaltyItems.id)
                                            .findAll()
                                    realm.beginTransaction()
                                    if( deleteLoyalty != null )
                                        deleteLoyalty.deleteAllFromRealm()
                                    realm.commitTransaction()
                                }
                            }
                            realm.beginTransaction()
                            realmLoyalty!!.loyaltyItems.add(encryptLoyality(decryptedLoyalty!!))
                            realm.copyToRealmOrUpdate(realmLoyalty)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedTravel != null) {
            decryptedTravel!!.selectionType = categoryID
            decryptedTravel!!.nameOnAccount = title
            decryptedTravel!!.accountName = subTitle
            if( decryptedTravel!!.created.isEmpty() )
                decryptedTravel!!.created = currentUsers + " " + currentDateandTime
            decryptedTravel!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedTravel!!.id.toInt() == 0) {
                decryptedTravel!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var travel = encryptTravel(decryptedTravel!!)
                            realm!!.insertOrUpdate(travel)
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineTravel: DecryptedCombineTravel = mCombine as DecryptedCombineTravel
                            var realmTravel = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmTravel == null) {
                                realmTravel = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            for(travelItems in realmTravel!!.travelItems){
                                if (travelItems!!.id.toInt() == decryptedTravel!!.id.toInt() ){
                                    var deleteTravel : RealmResults<CombineTravel> = realm!!.where(CombineTravel::class.java)
                                            .equalTo("id", decryptedTravel!!.id).findAll()
                                    deleteTravel.deleteAllFromRealm()
                                    realm.commitTransaction()
                                }
                            }
                            realmTravel!!.travelItems.add(encryptTravel(decryptedTravel!!))
                            realm.copyToRealmOrUpdate(realmTravel)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (decryptedDocuments != null) {
            decryptedDocuments!!.selectionType = categoryID
            decryptedDocuments!!.nameOnTravelDocument = subTitle
            if (decryptedDocuments!!.selectionType.equals("travel_2001"))
                decryptedDocuments!!.passportName = title
            if (decryptedDocuments!!.selectionType.equals("travel_2002"))
                decryptedDocuments!!.visaName = title
            if (decryptedDocuments!!.selectionType.equals("travel_2003"))
                decryptedDocuments!!.travelDocumentTitle = title

            AppLogger.d("SelectionType", " " + decryptedDocuments!!.selectionType)
            AppLogger.d("SelectionType", " Visa Name " + decryptedDocuments!!.visaName)
            AppLogger.d("SelectionType", " Passport Name " + decryptedDocuments!!.passportName)
            AppLogger.d("SelectionType", " Travel Document " + decryptedDocuments!!.selectionType)
            if( decryptedDocuments!!.created.isEmpty() )
                decryptedDocuments!!.created = currentUsers + " " + currentDateandTime
            decryptedDocuments!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedDocuments!!.id.toInt() == 0) {
                decryptedDocuments!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var documents = encryptDocuments(decryptedDocuments!!)
                            realm!!.insertOrUpdate(documents)
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineTravel: DecryptedCombineTravel = mCombine as DecryptedCombineTravel
                            var realmDocument = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmDocument == null) {
                                realmDocument = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            for(documentItems in realmDocument!!.documentsItems){
                                if (documentItems!!.id.toInt() == decryptedDocuments!!.id.toInt() ){
                                    var deleteDocuments : RealmResults<CombineTravel> = realm!!.where(CombineTravel::class.java)
                                            .equalTo("id", decryptedDocuments!!.id).findAll()
                                    deleteDocuments.deleteAllFromRealm()
                                    realm.commitTransaction()
                                }
                            }
                            realmDocument!!.documentsItems.add(encryptDocuments(decryptedDocuments!!))
                            realm.copyToRealmOrUpdate(realmDocument)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

      *//*  if (decryptedVacations != null) {
            decryptedVacations!!.selectionType = categoryID
            decryptedVacations!!.vac_description = title
            AppLogger.d("SelectionType", "decryptedVacations" + decryptedVacations!!.selectionType)
            if( decryptedVacations!!.created.isEmpty() )
                decryptedVacations!!.created = currentUsers + " " + currentDateandTime
            decryptedVacations!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedVacations!!.id.toInt() == 0) {
                decryptedVacations!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val vacations = encryptVacations(decryptedVacations!!)
                            realm!!.insertOrUpdate(vacations)
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineTravel: DecryptedCombineTravel = mCombine as DecryptedCombineTravel
                            var realmVacations = realm!!.where(CombineTravel::class.java).equalTo("id", combineTravel.id).findFirst()
                            realm.beginTransaction()
                            if (realmVacations == null) {
                                realmVacations = realm.createObject(CombineTravel::class.java, getUniqueId())
                            }
                            for(vacationItems in realmVacations!!.vacationsItems){
                                if (vacationItems!!.id.toInt() == decryptedVacations!!.id.toInt() ){
                                    var deleteVacations : RealmResults<CombineTravel> = realm!!.where(CombineTravel::class.java)
                                            .equalTo("id", decryptedVacations!!.id).findAll()
                                    deleteVacations.deleteAllFromRealm()
                                    realm.commitTransaction()
                                }
                            }
                            realmVacations!!.vacationsItems.add(encryptVacations(decryptedVacations!!))
                            realm.copyToRealmOrUpdate(realmVacations)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
*//*

*/



    }
}
