package com.ninebx.ui.home

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.home.account.AccountFragment
import com.ninebx.ui.home.calendar.CalendarFragment
import com.ninebx.ui.home.lists.ListsFragment
import com.ninebx.ui.home.notifications.NotificationsFragment
import com.ninebx.ui.home.search.SearchFragment
import com.ninebx.utility.Constants
import android.text.Html
import com.ninebx.NineBxApplication
import com.ninebx.ui.home.passcode.PassCodeDialog




@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.txtSearch -> {
                callBottomViewFragment(getString(R.string.search))
            }
            R.id.txtCalendar -> {
                callBottomViewFragment(getString(R.string.calendar))
            }
            R.id.txtLists -> {
                callBottomViewFragment(getString(R.string.lists))
            }
            R.id.txtNotifications -> {
                callBottomViewFragment(getString(R.string.notifications))
            }
            R.id.txtAccount -> {
                callBottomViewFragment(getString(R.string.account))
            }
        }
    }


    var strUsername: String = "test.box24@yopmail.com"
    var strPassword: String = "[188, 156, 77, 221, 202, 199, 239, 127, 240, 3, 139, 248, 54, 89, 82, 75, 68, 77, 138, 158, 124, 167, 135, 222, 160, 208, 203, 142, 112, 179, 91, 49]"

    var myCredentials: SyncCredentials? = null
    var user: SyncUser? = null
    var config: SyncConfiguration? = null
    var realm: Realm? = null

    val titleText = "<font color=#263238>nine</font><font color=#FF00B0FF>bx</font>"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        }

        ivHome.hide()
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        SyncTheDb().execute()

      /*  bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_search -> {
                    callBottomViewFragment(getString(R.string.search))
                }
                R.id.item_calendar -> {
                    callBottomViewFragment(getString(R.string.calendar))
                }
                R.id.item_lists -> {
                    callBottomViewFragment(getString(R.string.lists))
                }
                R.id.item_notifications -> {
                    callBottomViewFragment(getString(R.string.notifications))
                }
                R.id.item_account -> {
                    callBottomViewFragment(getString(R.string.account))
                }
            }
            true
        }*/

        changeToolbarTitle(titleText)

        ivHome.setOnClickListener {
            layoutQuickAdd.show()
            ivHome.hide()
            toggleCheck(false)
            changeToolbarTitle(titleText)
            callHomeFragment()
        }

        callHomeFragment()

        txtSearch.setOnClickListener(this)
        txtCalendar.setOnClickListener(this)
        txtLists.setOnClickListener(this)
        txtNotifications.setOnClickListener(this)
        txtAccount.setOnClickListener(this)
    }

    private fun callHomeFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.frameLayout, HomeFragment()).commit()
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

    // Calling the BottomView Navigation
    private fun callBottomViewFragment(option: String) {
        toggleCheck(true)
        ivHome.show()
        layoutQuickAdd.hide()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        when (option) {
            getString(R.string.search) -> {
                toolbarTitle.text = getString(R.string.search)
                fragmentTransaction.replace(R.id.frameLayout, SearchFragment()).commit()
            }
            getString(R.string.calendar) -> {
                toolbarTitle.text = getString(R.string.calendar)
                fragmentTransaction.replace(R.id.frameLayout, CalendarFragment()).commit()
            }
            getString(R.string.lists) -> {
                toolbarTitle.text = getString(R.string.lists)
                fragmentTransaction.replace(R.id.frameLayout, ListsFragment()).commit()
            }
            getString(R.string.notifications) -> {
                toolbarTitle.text = getString(R.string.notifications)
                fragmentTransaction.replace(R.id.frameLayout, NotificationsFragment()).commit()
            }
            getString(R.string.account) -> {
                toolbarTitle.text = getString(R.string.account)
                fragmentTransaction.replace(R.id.frameLayout, AccountFragment()).commit()
            }
        }
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


    override fun onPause() {
        super.onPause()
        NineBxApplication.getPreferences().isPasswordRequired = true
        if( mPassCodeDialog != null ) {
            mPassCodeDialog!!.dismissDialog()
        }
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed( {
            showPasswordDialog()
        }, 700)
    }

    private var mPassCodeDialog: PassCodeDialog ?= null
    private fun showPasswordDialog() {
        if( NineBxApplication.getPreferences().isPasswordRequired ) {
            mPassCodeDialog = PassCodeDialog( this, "111111", object : PassCodeDialog.PassCodeDialogListener {
                override fun onSuccess() {
                    NineBxApplication.getPreferences().isPasswordRequired = false
                }

                override fun onFailure(error: Int) {
                    Toast.makeText(this@HomeActivity, error, Toast.LENGTH_LONG).show()
                    NineBxApplication.getPreferences().isPasswordRequired = true
                }

            } )
        }

    }
}
