package com.ninebx.ui.home.baseSubCategories

import com.ninebx.R

/**
 * Created by Alok on 12/01/18.
 */
class Level2CategoryPresenter(private val categoryName: String, private val categoryID: String, private val categoryView: Level2CategoryView) {

    init {
        categoryView.showProgress(R.string.loading)
        Level2CategoryHelper(categoryName, categoryID, categoryView)
    }
}