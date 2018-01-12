package com.ninebx.ui.home.baseCategories

import com.ninebx.R
import com.ninebx.utility.Constants

/**
 * Created by Alok on 12/01/18.
 */
class CategoryHelper(
        val category_id : Int,
        val categoryView: CategoryView
) {

    init {
        when( category_id ) {
            R.string.home_amp_money -> {
                getHomeAndMoneyCategories()
            }
            R.string.travel -> {

            }
            R.string.contacts -> {

            }
            R.string.education_work -> {

            }
            R.string.personal -> {

            }
            R.string.interests -> {

            }
            R.string.wellness -> {

            }
            R.string.memories -> {

            }
            R.string.shopping -> {
                getShoppingCategories()
            }
        }
    }

    private fun getHomeAndMoneyCategories() {
        val categoryList = ArrayList<Category>()

        var categoryIndex = 1001
        var category_id = "home_" + categoryIndex
        var category = Category(category_id)
        category.title = "Financial Accounts"
        category.drawableString = "ic_icon_financial_accounts"

        category.subCategories.add( SubCategory("Banking", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Investments/Retirement", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Loan/Mortgages", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Other financial accounts", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )


        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Payment Methods"
        category.drawableString = "ic_icon_payment_methods"

        category.subCategories.add( SubCategory("Credit/Debit Cards", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Other Payment Accounts", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Property"
        category.drawableString = "ic_icon_property"

        category.subCategories.add( SubCategory("Primary Home (owned)", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Property (rented for own use)", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Vacation Home", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Investment/Rental Property", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Auto"
        category.drawableString = "ic_icon_travel_document"

        category.subCategories.add( SubCategory("Vehicles", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Maintenance", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Assests"
        category.drawableString = "ic_icon_other_assets"

        category.subCategories.add( SubCategory("Jewelry", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Art and collectibles", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Computers and electronics", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Furniture", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Others", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Insurance"
        category.drawableString = "ic_icon_insurance"

        category.subCategories.add( SubCategory("Homeowners/\nRenters Insurance", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Auto Insurance", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Life Insurance", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Health Insurance", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Umbrella insurance", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "home_" + categoryIndex
        category = Category(category_id)
        category.title = "Taxes"
        category.drawableString = "ic_icon_tax"

        category.subCategories.add( SubCategory("Past returns", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )
        category.subCategories.add( SubCategory("Returns to be filed", "", 0, Constants.SUB_CATEGORY_ADD_ITEM ) )

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Service/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"

        categoryList.add(category)

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"

        categoryList.add(category)

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"

        categoryList.add(category)


        categoryView.onSuccess( categoryList )
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

        category.subCategories.add( SubCategory("Add Person", "", 0, Constants.SUB_CATEGORY_ADD_PERSON ) )

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Service/Other Accounts"
        category.drawableString = "ic_icon_services_accounts"

        categoryList.add(category)

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Other Attachments"
        category.drawableString = "ic_icon_attachments"

        categoryList.add(category)

        categoryList.add(category)

        categoryIndex += 1000
        category_id = "shopping_" + categoryIndex
        category = Category(category_id)
        category.title = "Lists"
        category.drawableString = "ic_icon_lists"

        categoryList.add(category)

        categoryView.onSuccess( categoryList )

    }

}