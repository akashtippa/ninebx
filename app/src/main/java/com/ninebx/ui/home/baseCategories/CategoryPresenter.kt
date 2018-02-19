package com.ninebx.ui.home.baseCategories

import com.ninebx.R
import com.ninebx.ui.base.realm.lists.*
import com.ninebx.utility.AppLogger
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok on 12/01/18.
 */
class CategoryPresenter( private val categoryId : Int, private val categoryView: CategoryView ) {

    init {
        categoryView.showProgress(R.string.loading)
        CategoryHelper(categoryId, categoryView)
    }
}