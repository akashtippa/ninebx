package com.ninebx.ui.home.baseCategories

import com.ninebx.R
import com.ninebx.ui.base.realm.home.homeBanking.Combine
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

        val context = getApplicationContext()

        if (categoryId.equals("home_1001")) {
            prepareRealmConnections(context, false, "Combine", object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    val result = realm!!.where(Combine::class.java).findAll()

                }
            })
        }

        if (categoryId.equals("travel_1002"))
            prepareRealmConnections(context, false, "TravelBox", object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    val result = realm!!.where(TravelList::class.java).findAll()
                    AppLogger.d("CategoryPresenter", "Travel Box " + result)
                }

            })

        if (categoryId.equals("education_1004"))
            prepareRealmConnections(context, false, "EducationAndWorkBox", object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    val result = realm!!.where(EducationList::class.java).findAll()
                    AppLogger.d("CategoryPresenter", "Education Box " + result)
                }
            })

        if (categoryId.equals("personal_1005"))
            prepareRealmConnections(context, false, "PersonalBox", object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    val result = realm!!.where(PersonalList::class.java).findAll()
                    AppLogger.d("CategoryPresenter", "PersonalList Box " + result)
                }
            })

        if (categoryId.equals("personal_1006"))
            prepareRealmConnections(context, false, "InterestsBox", object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    val result = realm!!.where(InterestsList::class.java).findAll()
                    AppLogger.d("CategoryPresenter", "InterestsList Box " + result)
                }
            })
    }
}