package com.ninebx.ui.home.baseSubCategories

import android.os.Parcelable
import com.ninebx.ui.base.realm.decrypted.DecryptedInterests
import com.ninebx.utility.Constants
import java.util.ArrayList

/**
 * Created by Sourav on 19-03-2018.
 */
class InterestHelper(var category_name : String,
                     val categoryID: String,
                     var classType : String?,
                     var selectedDocument : Parcelable?,
                     val categoryView : Level2CategoryView) {
    private  var decryptedInterests:DecryptedInterests? = null
    fun initialize(){
        if( selectedDocument == null ) {
            return
        }
        when(classType){
            DecryptedInterests::class.java.simpleName -> {
                decryptedInterests = selectedDocument as DecryptedInterests
            }
        }
    }
    fun getFormForCategory(){
        when(category_name){
            "Services/Other Accounts" -> {
                getServicesOthersAccounts()
            }

        }

    }
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
}