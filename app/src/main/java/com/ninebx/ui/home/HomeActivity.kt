package com.ninebx.ui.home

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.support.design.internal.BottomNavigationMenuView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.view.View
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.ActionClickListener
import com.ninebx.ui.base.kotlin.*
import com.ninebx.ui.base.realm.CalendarEvents
import com.ninebx.ui.base.realm.Notifications
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.home.account.AccountFragment
import com.ninebx.ui.home.account.addmembers.AddFamilyUsersFragment
import com.ninebx.ui.home.calendar.CalendarFragment
import com.ninebx.ui.home.calendar.events.AddEditEventFragment
import com.ninebx.ui.home.calendar.events.AttachmentRecyclerViewAdapter
import com.ninebx.ui.home.calendar.events.ImageViewDialog
import com.ninebx.ui.home.customView.BottomNavigationViewHelper
import com.ninebx.ui.home.customView.CustomBottomSheetProfileDialogFragment
import com.ninebx.ui.home.lists.ListsFragment
import com.ninebx.ui.home.notifications.NotificationsFragment
import com.ninebx.ui.home.notifications.NotificationsPresenter
import com.ninebx.ui.home.notifications.NotificationsView
import com.ninebx.ui.home.passcode.PassCodeDialog
import com.ninebx.ui.home.search.SearchFragment
import com.ninebx.utility.*
import com.ninebx.utility.Constants.ALL_COMPLETE
import com.ninebx.utility.Constants.FINGER_PRINT_COMPLETE
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_home.*
import q.rorbin.badgeview.QBadgeView
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.util.*
import kotlin.collections.ArrayList


@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity(), HomeView, NotificationsView, CustomBottomSheetProfileDialogFragment.BottomSheetSelectedListener {

    private var addNotification = AddNotification()

        override fun onCombineHomeFetched(mDecryptCombineHome: DecryptedCombine) {
            addNotification.onCombineHomeFetched(mDecryptCombineHome)
        }

    override fun onCombineTravelFetched(mDecryptCombineTravel: DecryptedCombineTravel) {
        addNotification.onCombineTravelFetched(mDecryptCombineTravel)  }

    override fun onCombineContactsFetched(mDecryptCombineContacts: DecryptedCombineContacts) {
        addNotification.onCombineContactsFetched(mDecryptCombineContacts)
    }

    override fun onCombinePersonalFetched(mDecryptCombinePersonal: DecryptedCombinePersonal) {
        addNotification.onCombinePersonalFetched(mDecryptCombinePersonal)
    }

    override fun onCombineWellnessFetched(mDecryptCombineWellness: DecryptedCombineWellness) {
        addNotification.onCombineWellnessFetched(mDecryptCombineWellness)
    }

    override fun onCombineEducationFetched(mDecryptCombineEducation: DecryptedCombineEducation) {
        addNotification.onCombineEducationFetched(mDecryptCombineEducation)
    }

    override fun onNotificationsFetched(notifications: ArrayList<DecryptedNotifications>) {
        count = 0
        for (notification in notifications) {
            count += if (notification.read) 0 else 1
        }
        setNotificationCount(count)
    }

    override fun onEncryptedNotifications(notifications: RealmResults<Notifications>) {}



    override fun setCurrentUsers(currentUsers: RealmResults<Users>?) {
        this.currentUsers = currentUsers
        addNotification.setCurrentUsers(currentUsers)
        if (currentUsers != null) {
            this@HomeActivity.hideProgressDialog()
            AppLogger.d("CurrentUser", "Users from Realm : " + currentUsers.toString())
            AppLogger.e("CurrentUser", "Users from Realm : " +  currentUsers[0]!!.userId)

            prefrences.userID = currentUsers[0]!!.userId
            prefrences.userFirstName = currentUsers[0]!!.firstName.decryptString()
            prefrences.userLastName = currentUsers[0]!!.lastName.decryptString()

            for (member in currentUsers!![0]!!.members) {
                AppLogger.d("CurrentUser", "Members : " + member.toString())
            }
            if (NineBxApplication.getPreferences().currentStep == FINGER_PRINT_COMPLETE) {

                NineBxApplication.getPreferences().currentStep = ALL_COMPLETE

                toggleCheck(true)
                bottomNavigationView.menu.getItem(4).isChecked = true
                callBottomViewFragment(getString(R.string.account))

                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.add_others_to_account))
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                val addFamilyUsersFragment = AddFamilyUsersFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList(Constants.CURRENT_USER, Users.createParcelableList(currentUsers!!))
                addFamilyUsersFragment.arguments = bundle
                fragmentTransaction.replace(R.id.frameLayout, addFamilyUsersFragment).commit()
                hideQuickAdd()
            }
        }
    }

    override fun getContextForScreen(): Context {
        return this@HomeActivity
    }

    var count: Int = 0
    override fun setNotificationCount(notificationCount: Int) {
        if (count > 0) {
            this.count = notificationCount
            var bottomNavigationMenuView: BottomNavigationMenuView = bottomNavigationView.getChildAt(0) as BottomNavigationMenuView
            var v: View = bottomNavigationMenuView.getChildAt(3)
            QBadgeView(this).bindTarget(v).setBadgeNumber(count)
        }
    }

    val prefrences = NineBxPreferences()

    override fun getCurrentUsers(): RealmResults<Users> {
        NineBxApplication.instance.currentUser = currentUsers!![0]
        return currentUsers!!
    }

    override fun showProgress(message: Int) {
        showProgressDialog(getString(message))
    }

    override fun hideProgress() {
        hideProgressDialog()
    }

    override fun addEditCalendarEvent(calendarEvent: CalendarEvents?, selectedDate: Date) {
        val addEventFragment = AddEditEventFragment()
        val bundle = Bundle()
        bundle.putBoolean("isAddEvent", calendarEvent == null)
        var event = calendarEvent
        if (event == null) {
            event = CalendarEvents()
        }
        addEventFragment.setCalendarEvent(event)
        bundle.putLong("selectedDate", selectedDate.time)
        addEventFragment.arguments = bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.frameLayout, addEventFragment).commit()
    }

    override fun onError(error: Int) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    var backBtnCount = 0

    private var currentUsers: RealmResults<Users>? = null
    private lateinit var homePresenter: HomePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homePresenter = HomePresenter(this)
        NotificationsPresenter(this)

        bottomSheetDialogFragment = CustomBottomSheetProfileDialogFragment()
        bottomSheetDialogFragment.setBottomSheetSelectionListener(this)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        ivHome.hide()
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)

        NineBxApplication.instance.init(this)
        NotificationsPresenter(this)

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


        toggleToolbarImage()
        ivHome.setOnClickListener {
            layoutQuickAdd.show()
            ivHome.hide()
            toggleCheck(false)
            toggleToolbarImage()
            callHomeFragment()
            showBottomView()
            ivBack.hide()
            prefrences.currentBox = getString(R.string.home_amp_money)
        }

        ivBack.setOnClickListener {
            onBackPressed()
            KeyboardUtil.hideSoftKeyboard(this)
        }

        layoutQuickAdd.setOnClickListener {
            startCameraIntent()
        }

        callHomeFragment()
        toggleCheck(false)
        //SearchUtils.search()

        homePresenter.fetchCurrentUsers()
    }

    lateinit var bottomSheetDialogFragment: CustomBottomSheetProfileDialogFragment
    private val PICK_IMAGE_REQUEST = 238
    private val CAMERA_REQUEST_CODE = 239
    private val PERMISSIONS_REQUEST_CODE_CAMERA = 115
    private val PERMISSIONS_REQUEST_CODE_GALLERY = 116

    private fun startCameraIntent() {
        bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
    }

    override fun onOptionSelected(position: Int) {
        bottomSheetDialogFragment.dismiss()
        if (position == 1) {
            val permissionList = arrayListOf<String>(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            if (!handleMultiplePermission(this@HomeActivity, permissionList)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissionList.toTypedArray(), PERMISSIONS_REQUEST_CODE_CAMERA)
                } else {
                    beginCameraAttachmentFlow()
                }
            } else {
                beginCameraAttachmentFlow()
            }

        } else {
            val permissionList = arrayListOf<String>(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            if (!handleMultiplePermission(this@HomeActivity, permissionList)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissionList.toTypedArray(), PERMISSIONS_REQUEST_CODE_GALLERY)
                } else {
                    beginGalleryAttachmentFlow()
                }
            } else {
                beginGalleryAttachmentFlow()
            }
        }
    }

    private fun beginGalleryAttachmentFlow() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun beginCameraAttachmentFlow() {

        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (callCameraIntent.resolveActivity(this@HomeActivity.packageManager) != null) {
            startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSIONS_REQUEST_CODE_CAMERA) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                beginCameraAttachmentFlow()
            } else {
                Toast.makeText(this@HomeActivity, "Some permissions were denied", Toast.LENGTH_LONG).show()
            }
        } else if (requestCode == PERMISSIONS_REQUEST_CODE_GALLERY) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                beginGalleryAttachmentFlow()
            } else {
                Toast.makeText(this@HomeActivity, "Some permissions were denied", Toast.LENGTH_LONG).show()
            }
        }

    }

    //a Uri object to store file path
    private var filePath: Uri? = null
    private var mImagesList: ArrayList<Uri> = ArrayList()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            if (data.clipData != null) {
                val count = data.clipData.itemCount
                var currentItem = 0
                while (currentItem < count) {
                    val imageUri = data.clipData.getItemAt(currentItem).uri
                    mImagesList.add(imageUri)
                    currentItem += 1
                }

                setImagesAdapter()
            } else if (data.data != null) {
                mImagesList.add(data.data)
                setImagesAdapter()
            }
        } else if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            saveImage(data.extras.get("data") as Bitmap)
            filePath = getImageUri(data.extras.get("data") as Bitmap)
            mImagesList.add(filePath!!)
            setImagesAdapter()
        } else
            super.onActivityResult(requestCode, resultCode, data)

    }

    private var attachmentRecyclerAdapter: AttachmentRecyclerViewAdapter? = null
    private fun setImagesAdapter() {

        rvAttachments.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hideShowAttachments()

        attachmentRecyclerAdapter = AttachmentRecyclerViewAdapter(mImagesList, object : ActionClickListener {
            override fun onItemClick(position: Int, action: String) {
                when (action) {
                    "delete" -> {
                        mImagesList.removeAt(position)
                        attachmentRecyclerAdapter!!.notifyDataSetChanged()
                        hideShowAttachments()
                    }
                    "view" -> {
                        ImageViewDialog(this@HomeActivity, mImagesList, getString(R.string.attachments))
                    }
                    "add" -> {
                        startCameraIntent()
                    }
                }
            }
        }, LinearLayoutManager.HORIZONTAL)
        rvAttachments.adapter = attachmentRecyclerAdapter
    }

    private fun hideShowAttachments() {
        if (mImagesList.size > 0) {
            layoutQuickAdd.hide()
            cvAttachments.show()
        } else {

            if (imgToolbar.isVisible() && mImagesList.size == 0)
                layoutQuickAdd.show()

            cvAttachments.hide()
        }
    }

    fun pxFromDp(dp: Float, mContext: Context): Float {
        return dp * mContext.resources.displayMetrics.density
    }

    fun toggleToolbarImage() {
        imgToolbar.show()
        toolbarTitle.hide()

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

    fun changeToolbarTitle(title: String) {
        toolbarTitle.show()
        toolbarTitle.text = Html.fromHtml(title)
        imgToolbar.hide()
    }

    fun hideToolbar() {
        toolbar.hide()
    }

    fun showToolbar() {
        toolbar.show()
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

    private var mPassCodeDialog: PassCodeDialog? = null

    private fun showPasswordDialog() {
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
        ivHome.show()
        layoutQuickAdd.hide()
        toolbarTitle.show()
        imgToolbar.hide()
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
                val listsFragment = ListsFragment()

                toolbarTitle.text = getString(R.string.lists)
                val bundle = Bundle()
                bundle.putString("homeScreen", "bottomScreen")
                bundle.putInt("category", 0)
                listsFragment.arguments = bundle
                fragmentTransaction.replace(R.id.frameLayout, listsFragment).commit()
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
        hideShowAttachments()
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
        imgCameraNineBx.setImageResource(R.drawable.ic_icon_add_photo_memories)

        tvQuickAdd.show()
        layoutQuickAdd.show()
    }

    fun showQuickAddDisableText() {
        tvQuickAdd.hide()
        layoutQuickAdd.show()
    }

    fun changeQuickAddCamera(boxValue: String) {
        tvQuickAdd.hide()
        when (boxValue) {
            getString(R.string.home_amp_money) -> {
                imgCameraNineBx.setImageResource(R.drawable.camera_home)
            }
            getString(R.string.travel) -> {
                imgCameraNineBx.setImageResource(R.drawable.camera_travel)
            }
            getString(R.string.contacts) -> {
                imgCameraNineBx.setImageResource(R.drawable.camera_contacts)
            }
            getString(R.string.personal) -> {
                imgCameraNineBx.setImageResource(R.drawable.camera_personal)
            }
            getString(R.string.interests) -> {
                imgCameraNineBx.setImageResource(R.drawable.camera_interests)
            }
            getString(R.string.wellness) -> {
                imgCameraNineBx.setImageResource(R.drawable.camera_wellness)
            }
            getString(R.string.memories) -> {
                imgCameraNineBx.setImageResource(R.drawable.camera_memories)
            }
            getString(R.string.shopping) -> {
                imgCameraNineBx.setImageResource(R.drawable.camera_shopping)
            }
        }
    }
}
