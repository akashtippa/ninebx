package com.ninebx.ui.home.baseCategories

import android.os.Parcelable
import com.ninebx.R
import com.ninebx.ui.base.realm.home.homeBanking.*
import com.ninebx.ui.base.realm.lists.HomeList
import io.realm.RealmResults

/**
 * Created by Alok on 12/01/18.
 */
class CategoryPresenter(private val categoryId: Int, private val combineItems: Parcelable, private val categoryView: CategoryView) {

    init {
        categoryView.showProgress(R.string.loading)
        CategoryHelper(categoryId, categoryView, combineItems )
    }

    var financialList   : RealmResults<Financial> ?= null
    var paymentList     : RealmResults<Payment> ?= null
    var propertyList    : RealmResults<Property> ?= null
    var vehicleList     : RealmResults<Vehicle> ?= null
    var assetList       : RealmResults<Asset> ?= null
    var insuranceList   : RealmResults<Insurance> ?= null
    var taxList         : RealmResults<Taxes> ?= null
    var homeList        : RealmResults<HomeList> ?= null

    fun setDataForCombine(combine: RealmResults<Combine>) {


    }
}