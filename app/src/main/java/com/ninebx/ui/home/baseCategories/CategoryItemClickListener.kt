package com.ninebx.ui.home.baseCategories

/**
 * Created by Alok on 12/01/18.
 */
interface CategoryItemClickListener {
    fun onItemClick( adapter : SubCategoryAdapter, mainCategory : Category, category: SubCategory, action : String )
}