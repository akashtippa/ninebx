package com.ninebx.ui.home.baseSubCategories

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.home.baseCategories.Category

/**
 * Created by Alok on 12/01/18.
 */
interface Level2CategoryView : BaseView {
    fun onSuccess( categories : ArrayList<Level2Category> )
}