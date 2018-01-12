package com.ninebx.ui.home.baseCategories

import com.ninebx.R

/**
 * Created by Alok on 12/01/18.
 */
class CategoryPresenter( private val categoryId : Int, private val categoryView: CategoryView ) {

    init {
        categoryView.showProgress(R.string.loading)
        CategoryHelper( categoryId, categoryView )
    }
}