package com.ninebx.ui.home.baseSubCategories

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.ninebx.ui.base.BaseView
import com.ninebx.ui.home.baseCategories.Category

/**
 * Created by Alok on 12/01/18.
 */
interface Level2CategoryView : BaseView {
    fun onSuccess( categories : ArrayList<Level2Category> )
    fun setValueToDocument( level2Category: Level2SubCategory, parentCategory : Level2Category )
    fun saveDocument( context: Context? )
    fun savedToRealm( combine: Parcelable)
}