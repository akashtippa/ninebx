package com.ninebx.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ninebx.R
import com.ninebx.ui.home.adapter.MenuAdapterList
import com.ninebx.ui.home.customView.BottomNavigationViewHelper
import com.ninebx.ui.home.model.MenuItemList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.*
import kotlinx.android.synthetic.main.activity_home.*
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.transition.Fade
import android.view.MenuItem
import com.ninebx.R.id.bottomNavigationView
import com.ninebx.R.id.message
import com.ninebx.ui.auth.SignInFragment
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.home.account.AccountFragment
import com.ninebx.ui.home.adapter.HomeViewPagerAdapter
import com.ninebx.ui.home.calendar.CalendarFragment
import com.ninebx.ui.home.lists.ListsFragment
import com.ninebx.ui.home.notifications.NotificationsFragment
import com.ninebx.ui.home.search.SearchFragment
import com.ninebx.utility.Constants
import io.realm.RealmAsyncTask
import io.realm.kotlin.createObject


class HomeActivity : AppCompatActivity() {

    var adapter: MenuAdapterList? = null
    var menuList = ArrayList<MenuItemList>()

    var strUsername: String = "test.box24@yopmail.com"
    var strPassword: String = "[188, 156, 77, 221, 202, 199, 239, 127, 240, 3, 139, 248, 54, 89, 82, 75, 68, 77, 138, 158, 124, 167, 135, 222, 160, 208, 203, 142, 112, 179, 91, 49]"

    var myCredentials: SyncCredentials? = null
    var user: SyncUser? = null
    var config: SyncConfiguration? = null
    var realm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        }

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        menuList.add(MenuItemList("Home & Money", R.drawable.ic_home_icon_home_money, R.drawable.grid1))
        menuList.add(MenuItemList("Travel", R.drawable.ic_home_icon_travel, R.drawable.grid2))
        menuList.add(MenuItemList("Contacts", R.drawable.ic_home_icon_contacts, R.drawable.grid3))
        menuList.add(MenuItemList("Education & Work", R.drawable.ic_home_icon_education_work, R.drawable.grid4))
        menuList.add(MenuItemList("Personal", R.drawable.ic_home_icon_personal, R.drawable.grid5))
        menuList.add(MenuItemList("Interests", R.drawable.ic_home_icon_interests, R.drawable.grid6))
        menuList.add(MenuItemList("Wellness", R.drawable.ic_home_icon_wellness, R.drawable.grid7))
        menuList.add(MenuItemList("Memories", R.drawable.ic_home_icon_memories, R.drawable.grid8))
        menuList.add(MenuItemList("Shopping", R.drawable.ic_home_icon_shopping, R.drawable.grid9))
        adapter = MenuAdapterList(this, menuList)

        gridMenu.adapter = adapter

        SyncTheDb().execute()

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_search -> {
                    loadFragment(0)
                }
                R.id.item_calendar -> {
                    loadFragment(1)
                }
                R.id.item_lists -> {
                    loadFragment(2)
                }
                R.id.item_notifications -> {
                    loadFragment(3)
                }
                R.id.item_account -> {
                    loadFragment(4)
                }

            }
            true
        }

        setupPager()

        ivHome.setOnClickListener {
            vpParent.hide()
            gridMenu.show()
            tvTapABox.show()
            tvQuickAdd.show()
        }


    }

    private fun loadFragment(index: Int) {
        vpParent.show()
        gridMenu.hide()
        tvTapABox.hide()
        tvQuickAdd.hide()
        vpParent.currentItem = index
    }

    private fun setupPager() {

        val fragments = ArrayList<Fragment>()
        fragments.add(SearchFragment())
        fragments.add(CalendarFragment())
        fragments.add(ListsFragment())
        fragments.add(NotificationsFragment())
        fragments.add(AccountFragment())
        vpParent.adapter = HomeViewPagerAdapter(fragments, supportFragmentManager)
        vpParent.setPagingEnabled(false)
        ivHome.callOnClick()

    }

    @SuppressLint("StaticFieldLeak")
    inner class SyncTheDb : AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: String?): String {
            var Result: String = "";
            try {
                syncNow()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return Result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }
    }

    private fun syncNow() {
        myCredentials = SyncCredentials.usernamePassword(strUsername, strPassword, false)
        user = SyncUser.login(myCredentials, Constants.SERVER_IP)
        config = SyncConfiguration.Builder(user, Constants.SERVER_URL + "DummyTB")
                .waitForInitialRemoteData()
                .build()

        realm = Realm.getInstance(config)
        showToast("try")
    }

    fun showToast(msg: String) {
        Observable.just(msg)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val task = Realm.getInstanceAsync(config, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm) {
                            Toast.makeText(this@HomeActivity, "Sync Successful", Toast.LENGTH_LONG).show()
                            Log.e("Check ", " is " + realm.path)
                        }

                        override fun onError(exception: Throwable?) {
                            super.onError(exception)
                        }
                    })
                })
    }

    /**
    // For iOS
    let syncServerURL = URL(string: serverUrl + "Combine")!
            var configuration = Realm.Configuration()
            configuration.encryptionKey = Utility().getKey() as Data
            configuration.syncConfiguration = SyncConfiguration(user: SyncUser.current!, realmURL: syncServerURL)

            Realm.asyncOpen(configuration: configuration, callback: { realm, error in
                if let realm = realm {}
    )
    }
     */

}
