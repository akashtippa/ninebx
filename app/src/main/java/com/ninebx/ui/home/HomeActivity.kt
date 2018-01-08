package com.ninebx.ui.home

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ninebx.R
import com.ninebx.ui.home.customView.BottomNavigationViewHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.*
import kotlinx.android.synthetic.main.activity_home.*
import android.support.v4.app.Fragment
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.home.account.AccountFragment
import com.ninebx.ui.home.adapter.HomeViewPagerAdapter
import com.ninebx.ui.home.calendar.CalendarFragment
import com.ninebx.ui.home.lists.ListsFragment
import com.ninebx.ui.home.notifications.NotificationsFragment
import com.ninebx.ui.home.search.SearchFragment
import com.ninebx.utility.Constants
import android.text.Html
import com.ninebx.ui.home.passcode.PassCodeDialog


@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.layoutHome -> {

            }
            R.id.layoutTravell -> {

            }
            R.id.layoutContacts -> {

            }
            R.id.layoutEducation -> {

            }
            R.id.layoutPersonal -> {

            }
            R.id.layoutInterests -> {

            }
            R.id.layoutWellness -> {

            }
            R.id.layoutMemories -> {

            }
            R.id.layoutShopping -> {

            }
        }
    }

    var strUsername: String = "test.box24@yopmail.com"
    var strPassword: String = "[188, 156, 77, 221, 202, 199, 239, 127, 240, 3, 139, 248, 54, 89, 82, 75, 68, 77, 138, 158, 124, 167, 135, 222, 160, 208, 203, 142, 112, 179, 91, 49]"

    var myCredentials: SyncCredentials? = null
    var user: SyncUser? = null
    var config: SyncConfiguration? = null
    var realm: Realm? = null

    val titleText = "<font color=#cc0029>Nine</font><font color=#7C4DFF>bx</font>"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        }

        layoutHome.setOnClickListener(this)
        layoutTravell.setOnClickListener(this)
        layoutContacts.setOnClickListener(this)
        layoutEducation.setOnClickListener(this)
        layoutPersonal.setOnClickListener(this)
        layoutInterests.setOnClickListener(this)
        layoutWellness.setOnClickListener(this)
        layoutMemories.setOnClickListener(this)
        layoutShopping.setOnClickListener(this)

        ivHome.hide()
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

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

        changeToolbarTitle(titleText)

        ivHome.setOnClickListener {
            vpParent.hide()
            layoutMenu.show()
            tvTapABox.show()
            tvQuickAdd.show()
            ivHome.hide()
            toggleCheck(false)
        }
        setupPager()

    }

    private fun toggleCheck(isCheckable: Boolean) {
        bottomNavigationView.menu.getItem(0).isCheckable = isCheckable
        bottomNavigationView.menu.getItem(1).isCheckable = isCheckable
        bottomNavigationView.menu.getItem(2).isCheckable = isCheckable
        bottomNavigationView.menu.getItem(3).isCheckable = isCheckable
        bottomNavigationView.menu.getItem(4).isCheckable = isCheckable
    }

    public fun changeToolbarTitle(title: String) {
        toolbarTitle.text = Html.fromHtml(title)
    }


    private fun loadFragment(index: Int) {

        toggleCheck(true)

        bottomNavigationView.menu.getItem(0).isChecked = index == 0
        bottomNavigationView.menu.getItem(1).isChecked = index == 1
        bottomNavigationView.menu.getItem(2).isChecked = index == 2
        bottomNavigationView.menu.getItem(3).isChecked = index == 3
        bottomNavigationView.menu.getItem(4).isChecked = index == 4

        vpParent.show()
        layoutMenu.hide()
        ivHome.show()
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

    private var isPasswordRequired: Boolean = false

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

    override fun onPause() {
        super.onPause()
        isPasswordRequired = true
    }

    override fun onResume() {
        super.onResume()
        if( isPasswordRequired )
            PassCodeDialog( this, "111111", object : PassCodeDialog.PassCodeDialogListener {
                override fun onSuccess() {
                    isPasswordRequired = false
                }

                override fun onFailure(error: Int) {
                    Toast.makeText(this@HomeActivity, error, Toast.LENGTH_LONG).show()
                    isPasswordRequired = true
                }

            } )
    }
}
