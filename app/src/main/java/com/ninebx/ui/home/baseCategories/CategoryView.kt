package com.ninebx.ui.home.baseCategories

import com.ninebx.ui.home.search.SearchView

/**
 * Created by Alok on 12/01/18.
 */
interface CategoryView : SearchView {
    fun onSuccess( categories : ArrayList<Category> )
}