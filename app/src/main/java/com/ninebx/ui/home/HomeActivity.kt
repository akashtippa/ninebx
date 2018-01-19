package com.ninebx.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showProgressDialog
import com.ninebx.ui.home.account.AccountFragment
import com.ninebx.ui.home.calendar.AddEditEventFragment
import com.ninebx.ui.home.calendar.CalendarFragment
import com.ninebx.ui.home.calendar.model.CalendarEvents
import com.ninebx.ui.home.customView.BottomNavigationViewHelper
import com.ninebx.ui.home.lists.ListsFragment
import com.ninebx.ui.home.notifications.NotificationsFragment
import com.ninebx.ui.home.passcode.PassCodeDialog
import com.ninebx.ui.home.search.SearchFragment
import com.ninebx.utility.Constants
import com.ninebx.utility.FragmentBackHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm
import io.realm.SyncConfiguration
import io.realm.SyncCredentials
import io.realm.SyncUser
import kotlinx.android.synthetic.main.activity_home.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.util.*


@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity(), HomeView {
    override fun showProgress(message: Int) {
        showProgressDialog(getString(message))
    }

    override fun hideProgress() {
        hideProgressDialog()
    }

    override fun addEditCalendarEvent( calendarEvent: CalendarEvents?, selectedDate: Date ) {
        val addEventFragment = AddEditEventFragment()
    override fun addEditCalendarEvent(calendarEvent: CalendarEvents?) {
        val addEventFragment = AddEventFragment()
        val bundle = Bundle()
        bundle.putBoolean("isAddEvent", calendarEvent == null)
        var event = calendarEvent
        if( event == null ) {
            event = CalendarEvents()
        }
        addEventFragment.setCalendarEvent( event )
        bundle.putLong("selectedDate", selectedDate.time)
        if (event == null) event = CalendarEvents()
        bundle.putParcelable("calendarEvent", event)
        addEventFragment.arguments = bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.frameLayout, addEventFragment).commit()
    }

    override fun onError(error: Int) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    var strUsername: String = "test.box24@yopmail.com"
    var strPassword: String = "[188, 156, 77, 221, 202, 199, 239, 127, 240, 3, 139, 248, 54, 89, 82, 75, 68, 77, 138, 158, 124, 167, 135, 222, 160, 208, 203, 142, 112, 179, 91, 49]"

    var myCredentials: SyncCredentials? = null
    var user: SyncUser? = null
    var config: SyncConfiguration? = null
    var realm: Realm? = null

    var backBtnCount = 0

    val titleText = "<font color=#263238>nine</font><font color=#FF00B0FF>bx</font>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        }

        ivHome.hide()
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)

        SyncTheDb().execute()

        NineBxApplication.instance.init(this)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            toggleCheck(true)
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
        }

        changeToolbarTitle(titleText)

        ivHome.setOnClickListener {
            layoutQuickAdd.show()
            ivHome.hide()
            toggleCheck(false)
            changeToolbarTitle(titleText)
            callHomeFragment()
            showBottomView()
            ivBack.hide()
//            toolbarTitle.textSize = pxFromDp(16F, this)
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }

        callHomeFragment()
        toggleCheck(false)
//        toolbarTitle.textSize = pxFromDp(16F, this)
    }

    fun pxFromDp(dp: Float, mContext: Context): Float {
        return dp * mContext.resources.displayMetrics.density
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

    private fun callHomeFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.disallowAddToBackStack()
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

    fun hideToolbar() {
        toolbar.hide()
    }

    fun showToolbar() {
        toolbar.show()
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
        //NineBxApplication.getPreferences().isPasswordRequired = true
        dismissPasswordDialog()
    }

    private fun dismissPasswordDialog() {
        if (mPassCodeDialog != null) {
            mPassCodeDialog!!.dismissDialog()
        }
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            showPasswordDialog()
        }, 700)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private var mPassCodeDialog: PassCodeDialog? = null

    fun showPasswordDialog() {
        if (NineBxApplication.getPreferences().isPasswordRequired && !NineBxApplication.getPreferences().isPasswordEnabled) {
            if (mPassCodeDialog != null && mPassCodeDialog!!.isShowing()) {
                return
            }
            mPassCodeDialog = PassCodeDialog(this, "111111", object : PassCodeDialog.PassCodeDialogListener {
                override fun onSuccess() {
                    NineBxApplication.getPreferences().isPasswordRequired = false
                }

                override fun onFailure(error: Int) {
                    Toast.makeText(this@HomeActivity, error, Toast.LENGTH_LONG).show()
                    NineBxApplication.getPreferences().isPasswordRequired = true
                }

            })
        }

    }

    private fun callBottomViewFragment(option: String) {
//        toolbarTitle.textSize = pxFromDp(10F, this)
        ivHome.show()
        layoutQuickAdd.hide()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.disallowAddToBackStack()

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

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onBackPressed() {
        var isToWorkOnBack = true
        showToolbar()
        showBottomView()
        if (!NineBxApplication.instance.fragmentOpener.hasNoMoreBack()) {
            val list = supportFragmentManager.fragments
            if (list != null) {
                for (i in list.indices.reversed()) {
                    val fragment = list[i]
                    if (fragment is FragmentBackHelper) {
                        isToWorkOnBack = fragment
                                .onBackPressed()
                        break
                    }
                }
            }
        }
        if (!isToWorkOnBack)
            return

        if (!NineBxApplication.instance.fragmentOpener.hasNoMoreBack())
            super.onBackPressed()
        else {
            backBtnCount++

            if (backBtnCount == 2) {
                System.exit(0)
                finish()
                return
            } else {
                Toast.makeText(NineBxApplication.instance.activityInstance, "Press twice to exit", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({ backBtnCount = 0 }, 500)
            }
        }
    }

    fun showHomeNhideQuickAdd() {
        layoutQuickAdd.hide()
        ivHome.show()
        ivBack.show()
    }

    fun hideHomeNShowQuickAdd() {
        layoutQuickAdd.show()
        ivHome.hide()
        ivBack.hide()
//        toolbarTitle.textSize = pxFromDp(16F, this)
    }


    /*
    We need 4 different methods, to Show and Hide
    01. HomeIcon
    02. BackIcon
    03. BottomView
    04. QuickAdd

    Because, at some places only HomeIcon is there, and some places only BackIcon is there, and so on.
     */

    fun hideBottomView() {
        bottomNavigationView.hide()
    }

    fun showBottomView() {
        bottomNavigationView.show()
    }

    fun hideHomeIcon() {
        ivHome.hide()
    }

    fun showHomeIcon() {
        ivHome.show()
    }

    fun hideBackIcon() {
        ivBack.hide()
    }

    fun showBackIcon() {
        ivBack.show()
    }

    fun hideQuickAdd() {
        layoutQuickAdd.hide()
    }

    fun showQuickAdd() {
        layoutQuickAdd.show()
    }

}
