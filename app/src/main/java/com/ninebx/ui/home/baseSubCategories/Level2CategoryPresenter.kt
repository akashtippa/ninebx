package com.ninebx.ui.home.baseSubCategories

import android.content.Context
import android.os.Parcelable
import com.ninebx.R

/**
 * Created by Alok on 12/01/18.
 */
class Level2CategoryPresenter(private val categoryName: String, private val categoryID: String, private val selectedDocument : Parcelable, private val classType : String, private val categoryView: Level2CategoryView) {

    private var level2CategoryHelper: Level2CategoryHelper

    init {
        categoryView.showProgress(R.string.loading)
        level2CategoryHelper = Level2CategoryHelper(categoryName, categoryID, categoryView, selectedDocument, classType )
    }

    fun setValueToDocument( level2Category: Level2SubCategory ) {
        level2CategoryHelper.setValue( level2Category, selectedDocument )
    }

    fun saveDocument( context: Context? ) {
        level2CategoryHelper.saveDocument( context!! )
    }

}