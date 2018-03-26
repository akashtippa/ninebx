package com.ninebx.ui.home.baseCategories

import android.os.Parcelable
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.utility.Constants
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm

/**
 * Created by Alok on 12/01/18.
 */
class CategoryHelper(
        val category_id: Int,
        val categoryView: CategoryView,
        val combineItems: Parcelable
) {

    init {
        when (category_id) {
            R.string.home_amp_money -> {
                getHomeAndMoneyCategories()
            }
            R.string.travel -> {
                getTravelCategories()
            }
            R.string.contacts -> {
                getContacts()
            }
            R.string.education_work -> {
                getEducation()
            }
            R.string.personal -> {
                getPersonal()
            }
            R.string.interests -> {
                getInterests()
            }
            R.string.wellness -> {
                getWellness()
            }
            R.string.memories -> {
                getMemories()
            }
            R.string.shopping -> {
                getShoppingCategories()
            }
        }
    }

    private fun getWellness() {

        val decryptedCombine: DecryptedCombineWellness = combineItems as DecryptedCombineWellness
        //AppLogger.d("CategoryHelper", " DecryptedCombineTravel : " + decryptedCombine)

        val categoryList = ArrayList<Category>()

        var categoryIndex = 1007
        var category_id = "wellness_" + categoryIndex
        var category = Category(category_id)
        category.title = "Personal Health Record"
        category.drawableString = "ic_icon_health_records"

        for(wellness in decryptedCombine.wellnessItems) {
            category.subCategories.add(SubCategory(category.title, "", 0, Constants.SUB_CATEGORY_DISPLAY_PERSON, category_id, wellness.userName))
        }
        category.subCategories.add(SubCategory("Add Persons.", "", 0, Constants.SUB_CATEGORY_ADD_PERSON))


        categoryList.add(category)

        categoryIndex += 1003
        category_id = "wellness_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"
        category.formsCount = decryptedCombine.getServices("wellness_2001")
        category.category_id = "wellness_2001"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "wellness_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"
        category.formsCount = decryptedCombine.getLists("WellNess", 0)
        category.category_id = "wellness_3001"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "wellness_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"
        category.formsCount = decryptedCombine.getLists("WellNess", 0)
        category.category_id = "wellness_4001"

        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getPersonal() {
        val decryptedCombinePersonal: DecryptedCombinePersonal = combineItems as DecryptedCombinePersonal
        //AppLogger.d("CategoryHelper", " DecryptedCombineTravel : " + decryptedCombinePersonal)
        val categoryList = ArrayList<Category>()

        var categoryIndex = 1005
        var category_id = "personal_" + categoryIndex
        var category = Category(category_id)
        category.title = "Drivers License"
        category.drawableString = "ic_icon_driver_license"
        category.formsCount = decryptedCombinePersonal.getDriversLicense("personal_1001")
        category.category_id = "personal_1001"

        categoryList.add(category)

        categoryIndex += 1005
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Social Security Card"
        category.drawableString = "ic_icon_ssn"
        category.formsCount = decryptedCombinePersonal.getSocialSecurity("personal_2001")
        category.category_id = "personal_2001"

        categoryList.add(category)

        categoryIndex += 1005
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Tax ID"
        category.drawableString = "ic_icon_tax_id"
        category.formsCount = decryptedCombinePersonal.getTAXID("personal_3001")
        category.category_id = "personal_3001"

        categoryList.add(category)

        categoryIndex += 1005
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Birth Certificate"
        category.drawableString = "ic_icon_birth_certificate"
        category.formsCount = decryptedCombinePersonal.getMarriageCertificate("personal_5001")
        category.category_id = "personal_4001"

        categoryList.add(category)

        categoryIndex += 1005
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Marriage Certificate"
        category.drawableString = "ic_icon_marriage_certificate"
        category.formsCount = decryptedCombinePersonal.getMarriageCertificate("personal_5001")
        category.category_id = "personal_5001"

        categoryList.add(category)

        categoryIndex += 1005
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Other Government-Issued ID"
        category.drawableString = "ic_icon_lists_contacts"
        category.formsCount = decryptedCombinePersonal.getOtherGovernment("personal_6001")
        category.category_id = "personal_6001"

        categoryList.add(category)

        categoryIndex += 1005
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"
        category.category_id = "personal_7001"
        category.formsCount = decryptedCombinePersonal.getServicesAttachments("personal_7001")

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"
        category.category_id = "personal_8001"
        category.formsCount = decryptedCombinePersonal.getOtherAttach("Personal")

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"
        category.category_id = "personal_9001"
        category.formsCount = decryptedCombinePersonal.getListsCount("Personal", 0)

        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getContacts() {
        val decryptedCombinePersonal: DecryptedCombineContacts = combineItems as DecryptedCombineContacts
        //AppLogger.d("CategoryHelper", " DecryptedCombineTravel : " + decryptedCombinePersonal)

        val categoryList = ArrayList<Category>()

        var categoryIndex = 1003
        var category_id = "contacts_" + categoryIndex
        var category = Category(category_id)
        category.title = "Shared Contacts"
        category.drawableString = "ic_icon_lists_contacts"
        category.category_id = "cont_1001"
        category.formsCount = decryptedCombinePersonal.getAllContacts("Contacts")

        categoryList.add(category)

        categoryIndex += 1003
        category_id = "contacts_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"
        category.category_id = "cont_2001"
        category.formsCount = decryptedCombinePersonal.getAllContactsTest("cont_2001")

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "contacts_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"
        category.category_id = "cont_3001"
//        category.formsCount = decryptedCombinePersonal.getAllContacts("cont_3001")
        category.formsCount = decryptedCombinePersonal.getAllContactsTest("Contacts")

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "contacts_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"
        category.category_id = "cont_4001"
        category.formsCount = decryptedCombinePersonal.getListsCount("Contacts", 0)

        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getTravelCategories() {

        val decryptedCombine: DecryptedCombineTravel = combineItems as DecryptedCombineTravel
        //AppLogger.d("CategoryHelper", " DecryptedCombineTravel : " + decryptedCombine)

        val categoryList = ArrayList<Category>()
        var categoryIndex = 1002
        var category_id = "travel_" + categoryIndex
        var category = Category(category_id)
        category.title = "Loyalty Programs"
        category.drawableString = "ic_icon_loyalty_program"
        var subId = 1001

        category.subCategories.add(SubCategory("Airline", "", decryptedCombine.getLoyaltyCount("travel_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "travel_" + subId++))
        category.subCategories.add(SubCategory("Hotel", "", decryptedCombine.getLoyaltyCount("travel_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "travel_" + subId++))
        category.subCategories.add(SubCategory("Car Rental", "", decryptedCombine.getLoyaltyCount("travel_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "travel_" + subId++))
        category.subCategories.add(SubCategory("Cruiseline", "", decryptedCombine.getLoyaltyCount("travel_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "travel_" + subId++))
        category.subCategories.add(SubCategory("Railway", "", decryptedCombine.getLoyaltyCount("travel_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "travel_" + subId++))
        category.subCategories.add(SubCategory("Other", "", decryptedCombine.getLoyaltyCount("travel_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "travel_" + subId++))

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "travel_" + categoryIndex
        category = Category(category_id)
        category.title = "Travel Documents"
        category.drawableString = "ic_icon_travel_document"
        subId = 2001

        category.subCategories.add(SubCategory("Passport", "", decryptedCombine.getTravelDocuments("travel_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "travel_" + subId++))
        category.subCategories.add(SubCategory("Visa", "", decryptedCombine.getTravelDocuments("travel_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "travel_" + subId++))
        category.subCategories.add(SubCategory("Other travel document", "", decryptedCombine.getTravelDocuments("travel_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "travel_" + subId++))

        categoryList.add(category)

        subId = 3001
        categoryIndex += 1000
        category_id = "travel_" + categoryIndex
        category = Category(category_id)
        category.title = "Travel Dates And Plans"
        category.drawableString = "ic_icon_calender_selected"
        category.formsCount = decryptedCombine.getTravelDatesPlans("travel_3001")
        category.category_id = "travel_3001"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "travel_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"
        category.formsCount = decryptedCombine.getServices("travel_4001")
        category.category_id = "travel_4001"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "travel_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"
        category.formsCount = decryptedCombine.getTravelDatesAndPlans("travel_5001")
        category.category_id = "travel_5001"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "travel_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"
        category.formsCount = decryptedCombine.getTravelLists("Travel", 0)

        categoryList.add(category)
        categoryView.onSuccess(categoryList)
    }

    private fun getHomeAndMoneyCategories() {

        val decryptedCombine: DecryptedCombine = combineItems as DecryptedCombine
        //AppLogger.d("CategoryHelper", "Decrypted Combine : " + decryptedCombine)

        val categoryList = ArrayList<Category>()

        var categoryIndex = 1001
        var category_id = "home_" + categoryIndex
        var category = Category(category_id)
        category.title = "Financial Accounts"
        category.drawableString = "ic_icon_financial_accounts"
        var subId = 1001

        category.subCategories.add(SubCategory("Banking", "", decryptedCombine.getFinanceCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Investments/Retirement", "", decryptedCombine.getFinanceCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Loans/Mortgages", "", decryptedCombine.getFinanceCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Other financial accounts", "", decryptedCombine.getFinanceCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))


        categoryList.add(category)

        subId = 2001
        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Payment Methods"
        category.drawableString = "ic_icon_payment_methods"

        category.subCategories.add(SubCategory("Credit/Debit cards", "", decryptedCombine.getPaymentCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Other payment accounts", "", decryptedCombine.getFinanceCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))

        categoryList.add(category)
        subId = 3001
        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Property"
        category.drawableString = "ic_icon_property"

        category.subCategories.add(SubCategory("Primary home (owned)", "", decryptedCombine.getPropertyCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Property (rented for own use)", "", decryptedCombine.getPropertyCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Vacation home", "", decryptedCombine.getPropertyCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Investment/Rental property", "", decryptedCombine.getPropertyCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))

        categoryList.add(category)

        subId = 4001
        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Auto"
        category.drawableString = "ic_icon_auto"

        category.subCategories.add(SubCategory("Vehicles", "", decryptedCombine.getAutoCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Maintenance", "", decryptedCombine.getAutoCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))

        categoryList.add(category)

        subId = 5001
        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Assets"
        category.drawableString = "ic_icon_other_assets"

        category.subCategories.add(SubCategory("Jewelry", "", decryptedCombine.getAssetCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Art and collectibles", "", decryptedCombine.getAssetCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Computers and electronics", "", decryptedCombine.getAssetCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Furniture", "", decryptedCombine.getAssetCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Others", "", decryptedCombine.getAssetCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))

        categoryList.add(category)

        subId = 6001
        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Insurance"
        category.drawableString = "ic_icon_insurance"

        category.subCategories.add(SubCategory("Homeowners/Renters insurance", "", decryptedCombine.getInsuranceCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Auto insurance", "", decryptedCombine.getInsuranceCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Life insurance", "", decryptedCombine.getInsuranceCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Health insurance", "", decryptedCombine.getInsuranceCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Umbrella insurance", "", decryptedCombine.getInsuranceCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))

        categoryList.add(category)

        subId = 7001
        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Taxes"
        category.drawableString = "ic_icon_tax"

        category.subCategories.add(SubCategory("Past returns", "", decryptedCombine.getTaxesCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))
        category.subCategories.add(SubCategory("Returns to be filed", "", decryptedCombine.getTaxesCount("home_" + subId), Constants.SUB_CATEGORY_ADD_ITEM, "home_" + subId++))

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"
        category.formsCount = decryptedCombine.getFinanceCount("home_8001")
        category.category_id = "home_8001"
        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"
        category.formsCount = decryptedCombine.getTaxesCount("home_9001")
        category.category_id = "home_9001"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"
        category.formsCount = decryptedCombine.getListsCount("HomeBanking", 0)

        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getShoppingCategories() {
        val decryptedCombine: DecryptedCombineShopping = combineItems as DecryptedCombineShopping
        //AppLogger.e("CategoryHelper", " DecryptedCombineShopping : " + decryptedCombine)

        val categoryList = ArrayList<Category>()

        var categoryIndex = 1009
        var category_id = "shopping_" + categoryIndex
        var category = Category(category_id)
        category.title = "Loyalty Programs"
        category.drawableString = "ic_icon_loyalty_program"
        category.formsCount = decryptedCombine.getLoyaltyPrograms("shopping_1001")
        category.category_id = "shopping_1001"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Recent Purchases"
        category.drawableString = "ic_icon_recent_purchases"
        category.formsCount = decryptedCombine.getRecentPurchases("shopping_2001")
        category.category_id = "shopping_2001"
        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Clothing sizes"
        category.formsCount = decryptedCombine.getClothingSizesItems("shopping_2001")
        category.drawableString = "ic_icon_clothing_sizes"

        for(shoppingItem in decryptedCombine.clothingSizesItems) {
            category.subCategories.add(SubCategory(category.title, "", 0, Constants.SUB_CATEGORY_DISPLAY_PERSON, category_id, shoppingItem.personName))
        }

        category.subCategories.add(SubCategory("Add Persons.", "", 0, Constants.SUB_CATEGORY_ADD_PERSON))
        category.category_id = "shopping_3001"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"
        category.formsCount = decryptedCombine.getServices("shopping_4001")
        category.category_id = "shopping_4001"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"
        category.formsCount = decryptedCombine.getServices("shopping_5001")
        category.category_id = "shopping_5001"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"
        category.formsCount = decryptedCombine.getShoppingLists("Shopping", 0 )
        category.category_id = "shopping_6001"

        categoryList.add(category)

        categoryView.onSuccess(categoryList)

    }

    private fun getInterests() {
        val decryptedCombine: DecryptedCombineInterests = combineItems as DecryptedCombineInterests
        //AppLogger.e("CategoryHelper", " DecryptedCombineInterests : " + decryptedCombine)

        val categoryList = ArrayList<Category>()

        var categoryIndex = 1006
        var category_id = "personal" + categoryIndex
        var category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"
        category.formsCount = decryptedCombine.getServicesOrOtherAccounts("interest_1001")
        category.category_id = "interest_1001"
        categoryList.add(category)

        categoryIndex += 1000
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"
        category.formsCount = decryptedCombine.getAttachments("interest_2001")
        category.category_id = "interest_2001"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"
        category.formsCount = decryptedCombine.getLists("Interests", 0)
        category.category_id = "interest_3001"

        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getMemories() {
        val decryptedCombine: DecryptedCombineMemories = combineItems as DecryptedCombineMemories
        //AppLogger.d("CategoryHelper", " DecryptedCombineTravel : " + decryptedCombine)

        val categoryList = ArrayList<Category>()

        var categoryIndex = 1008
        var category_id = "memories_" + categoryIndex
        var category = Category(category_id)
        category.title = "Memory Timeline"
        category.drawableString = "ic_home_sub_memories"
        category.category_id = "memory_1001"
        category.formsCount =  decryptedCombine.getMemory("Memories")

        categoryList.add(category)

        categoryIndex += 1003
        category_id = "memories_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"
        category.category_id = "memory_2001"
        category.formsCount = decryptedCombine.getOther("memory_2001")

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "memories_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"
        category.category_id = "memory_3001"
        category.formsCount = decryptedCombine.getOther("memory_3001")

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "memories_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"
        category.category_id = "memory_4001"
        category.formsCount = decryptedCombine.getLists("Memories", 0)
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getEducation() {
        val decryptedCombine: DecryptedCombineEducation = combineItems as DecryptedCombineEducation
        //AppLogger.d("CategoryHelper", " DecryptedCombineTravel : " + decryptedCombine)

        val categoryList = ArrayList<Category>()

        var categoryIndex = 1004
        var category_id = "education_" + categoryIndex
        var category = Category(category_id)
        category.title = "Education"
        category.drawableString = "ic_icon_education"
        category.category_id = "edu_1001"

        for(educationItem in decryptedCombine.mainEducationItems) {
            category.subCategories.add(SubCategory(category.title, "", 0, Constants.SUB_CATEGORY_DISPLAY_PERSON, "edu_1001", educationItem.name))
        }

        category.subCategories.add(SubCategory("Add Persons.", "", 0, Constants.SUB_CATEGORY_ADD_PERSON))

        categoryList.add(category)

        categoryIndex += 1004
        category_id = "education_" + categoryIndex
        category = Category(category_id)
        category.title = "Work"
        category.drawableString = "ic_icon_work"
        category.category_id = "edu_2001"

        for(workItem in decryptedCombine.workItems) {
            category.subCategories.add(SubCategory(category.title, "", 0, Constants.SUB_CATEGORY_DISPLAY_PERSON, "edu_2001", workItem.name))
        }

        category.subCategories.add(SubCategory("Add Persons.", "", 0, Constants.SUB_CATEGORY_ADD_PERSON))

        categoryList.add(category)


        categoryIndex += 1004
        category_id = "education_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"
        category.formsCount = decryptedCombine.getServices("edu_3001")
        category.category_id = "edu_3001"
        categoryList.add(category)

        categoryIndex += 1000
        category_id = "education_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"
        category.category_id = "edu_4001"
        category.formsCount = decryptedCombine.getServices("edu_4001")

//        category.formsCount = decryptedCombine.getLists("WellNess")

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "education_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"
        category.formsCount = decryptedCombine.getListItemsCount("Education", 0)
        category.category_id = "edu_5001"

        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }
}