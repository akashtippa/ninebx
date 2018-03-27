package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context

import android.os.Bundle
import android.os.Parcelable
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.home.baseCategories.SubCategory

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
    fun saveDocument(context: Context, combineItem: Parcelable?, title: String, subTitle: String, subCategory: SubCategory?, categoryName: String) {
        mCombine = combineItem

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
            educationAndWorkHelper!!.saveDocument(context,combineItem,title, subTitle, subCategory)
        }

        if(wellnessHelper!=null){
            wellnessHelper!!.saveDocument(context,combineItem, title, subTitle, subCategory)
        }
        if(shoppingHelper!=null){
            shoppingHelper!!.saveDocument(context,combineItem, title, subTitle, subCategory, categoryName)
        }
        if(commonItemHelper != null) {
            commonItemHelper!!.saveDocument(context, combineItem, title, subTitle)
        }

    }
}
