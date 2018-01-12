package com.ninebx.ui.home.baseCategories

import com.ninebx.ui.base.BaseView

/**
 * Created by Alok on 12/01/18.
 */
interface CategoryView : BaseView {
    fun onSuccess( categories : ArrayList<Category> )
}