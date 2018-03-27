package com.ninebx.ui.home.baseSubCategories

import android.content.Context
import android.os.Parcelable
import com.ninebx.R
import com.ninebx.ui.home.baseCategories.SubCategory

/**
 * Created by Alok on 12/01/18.
 */
class Level3CategoryPresenter(private val categoryInt : Int, private val categoryName: String, private val categoryID: String, private val selectedDocument : Parcelable?, private val classType : String?, private val categoryView: Level2CategoryView) {

    private var level3CategoryHelper: Level3CategoryHelper

    init {
        categoryView.showProgress(R.string.loading)
        level3CategoryHelper = Level3CategoryHelper(categoryInt, categoryName, categoryID, categoryView, selectedDocument, classType )
    }

    fun setValueToDocument(level2Category: Level2SubCategory, parentCategory: Level2Category) {
        level3CategoryHelper.setValue( level2Category, parentCategory )
    }

    fun saveDocument(context: Context?, combineItem: Parcelable?, title: String, subTitle: String, subCategory: SubCategory?, categoryName: String) {
        level3CategoryHelper.saveDocument( context!!, combineItem, title, subTitle, subCategory, categoryName )
    }
}