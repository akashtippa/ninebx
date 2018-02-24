package com.ninebx.ui.home

import com.ninebx.utility.getCurrentUsers
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm

/**
 * Created by Alok Omkar on 2018-02-24.
 */
class HomePresenter( val homeView : HomeView ) {

    init {

    }

    fun fetchCurrentUsers() {
        prepareRealmConnections(homeView.getContextForScreen(),
                true,
                "Users",
                object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        homeView.setCurrentUsers(getCurrentUsers(realm!!))
                    }
                })
    }
}