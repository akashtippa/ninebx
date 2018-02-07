package com.ninebx.ui.home.baseCategories

import com.ninebx.R
import com.ninebx.utility.Constants

/**
 * Created by Alok on 12/01/18.
 */
class CategoryHelper(
        val category_id: Int,
        val categoryView: CategoryView
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
        val categoryList = ArrayList<Category>()

        var categoryIndex = 1007
        var category_id = "wellness_" + categoryIndex
        var category = Category(category_id)
        category.title = "Personal Health Record"
        category.drawableString = "ic_icon_health_records"

        category.subCategories.add(SubCategory("Add Persons.", "", 0, Constants.SUB_CATEGORY_ADD_PERSON))


        categoryList.add(category)


        categoryIndex += 1003
        category_id = "wellness_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "wellness_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "wellness_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"

        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getPersonal() {
        val categoryList = ArrayList<Category>()

        var categoryIndex = 1005
        var category_id = "personal" + categoryIndex
        var category = Category(category_id)
        category.title = "Drivers License"
        category.drawableString = "ic_icon_driver_license"

        categoryList.add(category)


        categoryIndex += 1005
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Social Security Card"
        category.drawableString = "ic_icon_ssn"

        categoryList.add(category)

        categoryIndex += 1005
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Tax ID"
        category.drawableString = "ic_icon_tax_id"

        categoryList.add(category)


        categoryIndex += 1005
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Birth Certificate"
        category.drawableString = "ic_icon_birth_certificate"

        categoryList.add(category)


        categoryIndex += 1005
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Marriage Certificate"
        category.drawableString = "ic_icon_marriage_certificate"

        categoryList.add(category)

        categoryIndex += 1005
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Other Government-Issued ID"
        category.drawableString = "ic_icon_lists_contacts"

        categoryList.add(category)


        categoryIndex += 1005
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"

        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getContacts() {
        val categoryList = ArrayList<Category>()

        var categoryIndex = 1003
        var category_id = "contacts_" + categoryIndex
        var category = Category(category_id)
        category.title = "Shared Contacts"
        category.drawableString = "ic_icon_lists_contacts"

        categoryList.add(category)




        categoryIndex += 1003
        category_id = "contacts_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "contacts_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "contacts_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"

        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getTravelCategories() {
        val categoryList = ArrayList<Category>()

        var categoryIndex = 1002
        var category_id = "travel_" + categoryIndex
        var category = Category(category_id)
        category.title = "Loyalty Programs"
        category.drawableString = "ic_icon_loyalty_program"

        category.subCategories.add(SubCategory("Airline", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Hotel", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Car Rental", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Cruiseline", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Railway", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Other", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))


        categoryList.add(category)

        categoryIndex += 1000
        category_id = "travel_" + categoryIndex
        category = Category(category_id)
        category.title = "Travel Documents"
        category.drawableString = "ic_icon_travel_document"

        category.subCategories.add(SubCategory("Passport", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Visa", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Other travel document", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))

        categoryList.add(category)


        categoryIndex += 1000
        category_id = "travel_" + categoryIndex
        category = Category(category_id)
        category.title = "Travel Dates And Plans"
        category.drawableString = "ic_icon_calender_selected"

        categoryList.add(category)


        categoryIndex += 1000
        category_id = "travel_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "travel_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "travel_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"

        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getHomeAndMoneyCategories() {
        val categoryList = ArrayList<Category>()

        var categoryIndex = 1001
        var category_id = "home_" + categoryIndex
        var category = Category(category_id)
        category.title = "Financial Accounts"
        category.drawableString = "ic_icon_financial_accounts"

        category.subCategories.add(SubCategory("Banking", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Investments/Retirement", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Loans/Mortgages", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Other financial accounts", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))


        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Payment Methods"
        category.drawableString = "ic_icon_payment_methods"

        category.subCategories.add(SubCategory("Credit/Debit cards", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Other payment accounts", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Property"
        category.drawableString = "ic_icon_property"

        category.subCategories.add(SubCategory("Primary home (owned)", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Property (rented for own use)", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Vacation home", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Investment/Rental property", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Auto"
        category.drawableString = "ic_icon_auto"

        category.subCategories.add(SubCategory("Vehicles", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Maintenance", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Assets"
        category.drawableString = "ic_icon_other_assets"

        category.subCategories.add(SubCategory("Jewelry", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Art and collectibles", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Computers and electronics", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Furniture", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Others", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Insurance"
        category.drawableString = "ic_icon_insurance"

        category.subCategories.add(SubCategory("Homeowners/Renters insurance", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Auto insurance", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Life insurance", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Health insurance", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Umbrella insurance", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Taxes"
        category.drawableString = "ic_icon_tax"

        category.subCategories.add(SubCategory("Past returns", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))
        category.subCategories.add(SubCategory("Returns to be filed", "", 0, Constants.SUB_CATEGORY_ADD_ITEM))

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_Services_accounts"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"

        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getShoppingCategories() {

        val categoryList = ArrayList<Category>()

        var categoryIndex = 1009
        var category_id = "shopping_" + categoryIndex
        var category = Category(category_id)
        category.title = "Loyalty Programs"
        category.drawableString = "ic_icon_loyalty_program"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Recent Purchases"
        category.drawableString = "ic_icon_recent_purchases"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Clothing sizes"
        category.drawableString = "ic_icon_clothing_sizes"

        category.subCategories.add(SubCategory("Add Person.", "", 0, Constants.SUB_CATEGORY_ADD_PERSON))

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_Services_accounts"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"

        categoryList.add(category)

        categoryView.onSuccess(categoryList)

    }

    private fun getInterests() {
        val categoryList = ArrayList<Category>()

        var categoryIndex = 1006
        var category_id = "personal" + categoryIndex
        var category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_Services_accounts"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "personal" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"

        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getMemories() {
        val categoryList = ArrayList<Category>()

        var categoryIndex = 1008
        var category_id = "memories_" + categoryIndex
        var category = Category(category_id)
        category.title = "Memory Timeline"
        category.drawableString = "ic_home_sub_memories"

        categoryList.add(category)


        categoryIndex += 1003
        category_id = "memories_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_Services_accounts"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "memories_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "memories_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"

        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getEducation() {
        val categoryList = ArrayList<Category>()

        var categoryIndex = 1004
        var category_id = "education_" + categoryIndex
        var category = Category(category_id)
        category.title = "Education"
        category.drawableString = "ic_icon_education"

        category.subCategories.add(SubCategory("Add Person", "", 0, Constants.SUB_CATEGORY_ADD_PERSON))

        categoryList.add(category)

        categoryIndex += 1004
        category_id = "education_" + categoryIndex
        category = Category(category_id)
        category.title = "Work"
        category.drawableString = "ic_icon_work"

        category.subCategories.add(SubCategory("Add person", "", 0, Constants.SUB_CATEGORY_ADD_PERSON))

        categoryList.add(category)


        categoryIndex += 1004
        category_id = "education_" + categoryIndex
        category = Category(category_id)
        category.title = "Services/Other Accounts"
        category.drawableString = "ic_icon_Services_accounts"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "education_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "education_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"

        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }


}