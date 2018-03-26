package com.ninebx.ui.home.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.provider.ContactsContract
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.realm.SearchItemClickListener
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.base.realm.home.contacts.MainContacts
import com.ninebx.ui.base.realm.home.education.Education
import com.ninebx.ui.base.realm.home.interests.Interests
import com.ninebx.ui.base.realm.home.memories.MainMemories
import com.ninebx.ui.base.realm.home.personal.Personal
import com.ninebx.ui.base.realm.home.shopping.Shopping
import com.ninebx.ui.base.realm.home.travel.Travel
import com.ninebx.ui.base.realm.home.wellness.Wellness
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.account.interfaces.MainContactsAdded
import com.ninebx.ui.home.adapter.*
import com.ninebx.ui.home.baseCategories.OptionItem
import com.ninebx.ui.home.search.Level3SearchItem
import com.ninebx.ui.home.search.SearchAdapter
import com.ninebx.ui.home.search.SearchHelper
import com.ninebx.utility.AppLogger
import com.ninebx.utility.Constants
import com.ninebx.utility.Constants.SEARCH_EDIT
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.prepareRealmConnections
import com.onegravity.contactpicker.ContactElement
import com.onegravity.contactpicker.contact.Contact
import com.onegravity.contactpicker.contact.ContactDescription
import com.onegravity.contactpicker.contact.ContactSortOrder
import com.onegravity.contactpicker.core.ContactPickerActivity
import com.onegravity.contactpicker.group.Group
import com.onegravity.contactpicker.picture.ContactPictureType
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_list_container.*
import java.io.Serializable
import java.util.concurrent.atomic.AtomicBoolean

/***
 * Created by TechnoBlogger on 24/01/18.
 */
class Level2Fragment : FragmentBackHelper(), SearchItemClickListener, SearchHelper.OnDocumentSelection,
        MainContactsAdded {

    override fun contactsDeleted(contacts: Parcelable) {
        showDialogToDelete(contacts )
    }

    override fun onDocumentRemoved(combineParcelable: Parcelable) {
        combinedItems = combineParcelable
        loadItems()
    }

    override fun contactsClicked(contacts: Parcelable, isEditable: Boolean) {
        val bundle = Bundle()
        bundle.putString("categoryName", categoryName)
        bundle.putString("categoryId", categoryID)
        bundle.putParcelable(Constants.COMBINE_ITEMS, combinedItems)
        val action = if(isEditable) "edit" else "add"
        bundle.putString("action", action)
        val classType = getClassType()
        bundle.putString("classType", classType)
        bundle.putParcelable("selectedDocument", contacts)
        bundle.putString(Constants.FROM_CLASS, "Level2Fragment")
        bundle.putInt(Constants.CURRENT_BOX, categoryInt)
        bundle.putParcelableArrayList(Constants.SUB_OPTIONS, optionsList)
        val intent = Intent( context, ContainerActivity::class.java)
        intent.putExtras(bundle)
        startActivityForResult(intent, LEVEL_3)
    }

    private fun getClassType(): String {
        var classType = ""
        classType = when(categoryInt) {
            R.string.home_amp_money -> { "" }
            R.string.travel -> { DecryptedTravel::class.java.simpleName }
            R.string.contacts -> { DecryptedMainContacts::class.java.simpleName }
            R.string.education_work -> { DecryptedEducation::class.java.simpleName }
            R.string.personal-> { DecryptedPersonal::class.java.simpleName }
            R.string.interests -> { DecryptedInterests::class.java.simpleName }
            R.string.wellness -> { DecryptedWellness::class.java.simpleName }
            R.string.memories -> { DecryptedMainMemories::class.java.simpleName }
            R.string.shopping -> { DecryptedShopping::class.java.simpleName }
            else -> { "" }
        }
        return classType
    }

    fun deleteContact(contact: Parcelable) {
        /*when(categoryInt) {
            R.string.home_amp_money -> { contact as DecryptedCombine }
            R.string.travel -> { contact as DecryptedTravel}
            R.string.contacts -> { contact as DecryptedMainContacts }
            R.string.education -> { contact as DecryptedEducation }
            R.string.interests -> { contact as DecryptedInterests }
            R.string.wellness -> { contact as DecryptedWellness }
            R.string.memories -> { contact as DecryptedMainMemories }
            R.string.shopping -> { contact as DecryptedShopping }
            else -> { contact as DecryptedContacts }
        }*/
        if(contactsRealm != null) {
            contactsRealm!!.refresh()
            contactsRealm!!.beginTransaction()
            when (categoryInt) {
                R.string.home_amp_money -> {

                }
                R.string.travel -> {
                    contact as DecryptedTravel
                    contactsRealm!!.where(Travel::class.java).equalTo("id", contact.id).findAll().deleteAllFromRealm()
                    mTravelItemsAdapter!!.deleteContact(contact)
                }
                R.string.contacts -> {
                    contact as DecryptedMainContacts
                    contactsRealm!!.where(MainContacts::class.java).equalTo("id", contact.id).findAll().deleteAllFromRealm()
                    mContactsAdapter!!.deleteContact(contact)
                }
                R.string.education_work -> {
                    contact as DecryptedEducation
                    contactsRealm!!.where(Education::class.java).equalTo("id", contact.id).findAll().deleteAllFromRealm()
                    mEducationItemsAdapter!!.deleteContact(contact)
                }
                R.string.personal -> {
                    contact as DecryptedPersonal
                    contactsRealm!!.where(Personal::class.java).equalTo("id", contact.id).findAll().deleteAllFromRealm()
                    mPersonalItemsAdapter!!.deleteContact(contact)
                }
                R.string.interests -> {
                    contact as DecryptedInterests
                    contactsRealm!!.where(Interests::class.java).equalTo("id", contact.id).findAll().deleteAllFromRealm()
                    mInterestItemsAdapter!!.deleteContact(contact)
                }
                R.string.wellness -> {
                    contact as DecryptedWellness
                    contactsRealm!!.where(Wellness::class.java).equalTo("id", contact.id).findAll().deleteAllFromRealm()
                    mWellnessItemsAdapter!!.deleteContact(contact)
                }
                R.string.memories -> {
                    contact as DecryptedMainMemories
                    contactsRealm!!.where(MainMemories::class.java).equalTo("id", contact.id).findAll().deleteAllFromRealm()
                    mMainMemoriesAdapter!!.deleteContact(contact)
                }
                R.string.shopping -> {
                    contact as DecryptedShopping
                    contactsRealm!!.where(Shopping::class.java).equalTo("id", contact.id).findAll().deleteAllFromRealm()
                    mShoppingItemsAdapter!!.deleteContact(contact)
                }
            }
            contactsRealm!!.commitTransaction()
            contactsRealm!!.refresh()
        }
    }

    private fun showDialogToDelete(contact: Parcelable  ) {

        val alertDialogBuilder = AlertDialog.Builder(context!!)
        alertDialogBuilder.setTitle("Are you sure you want to delete the contact?")
        alertDialogBuilder.setPositiveButton(" Delete", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                deleteContact(contact)
                dialog!!.dismiss()
            }
        })
        alertDialogBuilder.setNegativeButton(" Cancel", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                dialog!!.dismiss()
            }
        })
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private val LEVEL_3: Int = 12312

    override fun onDocumentSelected(selectedDocument: Parcelable?, classType : String, action: String ) {
        startActivityForResult(
                Intent(context, ContainerActivity::class.java)
                        .putExtra("categoryName", categoryName)
                        .putExtra("categoryId", categoryID)
                        .putExtra("action", action)
                        .putExtra(Constants.CURRENT_BOX, categoryInt)
                        .putExtra("selectedDocument", selectedDocument)
                        .putExtra(Constants.COMBINE_ITEMS, combinedItems)
                        .putExtra("classType", classType)
                        .putExtra(Constants.FROM_CLASS, "Level2Fragment")
                        .putExtra(Constants.SUB_OPTIONS, optionsList)
                , LEVEL_3)
    }


    var categoryName = ""
    var categoryID = ""
    var categoryInt : Int = -1
    private val EXTRA_DARK_THEME = "EXTRA_DARK_THEME"
    private val EXTRA_GROUPS = "EXTRA_GROUPS"
    private val EXTRA_CONTACTS = "EXTRA_CONTACTS"

    var myList: ArrayList<Contacts> = ArrayList()

    private val REQUEST_CONTACT = 0

    private var mDarkTheme: Boolean = false
    private var mContacts: ArrayList<Contact>? = ArrayList()
    private var mGroups: List<Group>? = null

    private val REQUEST_PERMISSION = 3
    private val PREFERENCE_PERMISSION_DENIED = "PREFERENCE_PERMISSION_DENIED"
    private var contactList: RealmResults<Contacts>? = null
    private var contacts: ArrayList<Contacts>? = ArrayList()
    private var optionsList : ArrayList<OptionItem> ?= null
    private var combinedItems: Parcelable? = null
    private var contactsRealm: Realm? = null

    private val mRequestPermissionsInProcess = AtomicBoolean()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_container, container, false)
    }

    private lateinit var searchHelper: SearchHelper

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        combinedItems = arguments!!.getParcelable(Constants.COMBINE_ITEMS)
        categoryName = arguments!!.getString("categoryName")
        categoryID = arguments!!.getString("categoryId")
        categoryInt = arguments!!.getInt(Constants.CURRENT_BOX)
        if( arguments!!.containsKey(Constants.SUB_OPTIONS) ) optionsList = arguments!!.getParcelableArrayList(Constants.SUB_OPTIONS)

        toolbarTitle.text = categoryName

        ivHome.setOnClickListener {
            val homeIntent = Intent(context, HomeActivity::class.java)
            startActivity(homeIntent)
            activity!!.finishAffinity()
        }
        ivBack.setOnClickListener {  activity!!.onBackPressed()  }

        layoutAddList.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", categoryName)
            bundle.putString("categoryId", categoryID)
            bundle.putParcelableArrayList(Constants.SUB_OPTIONS, optionsList)

            if (categoryName == "Shared Contacts") {
                checkPermissions(arrayOf(Manifest.permission.READ_CONTACTS))
                callForContact()
            } else if (categoryName == "Memory Timeline") {
                val categoryFragment = MemoryTimeLineFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
            } else {

                bundle.putParcelable(Constants.COMBINE_ITEMS, combinedItems)
                bundle.putString("action", "add")
                bundle.putString(Constants.FROM_CLASS, "Level2Fragment")
                bundle.putInt(Constants.CURRENT_BOX, categoryInt)

                val intent = Intent( context, ContainerActivity::class.java)
                intent.putExtras(bundle)
                startActivityForResult(intent, LEVEL_3)
            }
        }
        loadItems()
        when (categoryInt) {
            R.string.home_amp_money -> { getRealmEndPoint(Constants.REALM_END_POINT_COMBINE) }
            R.string.travel -> { getRealmEndPoint(Constants.REALM_END_POINT_COMBINE_TRAVEL)}
            R.string.contacts -> { getRealmEndPoint(Constants.REALM_END_POINT_COMBINE_CONTACTS) }
            R.string.education_work -> { getRealmEndPoint(Constants.REALM_END_POINT_COMBINE_EDUCATION) }
            R.string.personal -> { getRealmEndPoint(Constants.REALM_END_POINT_COMBINE_PERSONAL) }
            R.string.interests -> { getRealmEndPoint(Constants.REALM_END_POINT_COMBINE_INTERESTS) }
            R.string.wellness -> { getRealmEndPoint(Constants.REALM_END_POINT_COMBINE_WELLNESS) }
            R.string.memories -> { getRealmEndPoint(Constants.REALM_END_POINT_COMBINE_MEMORIES) }
            R.string.shopping -> { getRealmEndPoint(Constants.REALM_END_POINT_COMBINE_SHOPPING) }
        }

    }

    private fun getRealmEndPoint(endPoint: String) {
        prepareRealmConnections(context, true, endPoint, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                contactsRealm = realm
                context!!.hideProgressDialog()
            }
        })
    }

    private var mContactsAdapter: MainContactsAdapter ?= null
    private var mTravelItemsAdapter: TravelItemsAdapter ?= null
    private var mEducationItemsAdapter: EducationItemsAdapter ?= null
    private var mPersonalItemsAdapter: PersonalItemsAdapter ?= null
    private var mInterestItemsAdapter: InterestItemsAdapter ?= null
    private var mWellnessItemsAdapter: WellnessItemsAdapter ?= null
    private var mMainMemoriesAdapter: MainMemoriesAdapter ?= null
    private var mShoppingItemsAdapter: ShoppingItemsAdapter ?= null
    private fun loadItems() {
        rvCommonList!!.layoutManager = LinearLayoutManager(context)
        if (categoryName == "Services/Other Accounts") {
            when(categoryInt) {
                R.string.contacts -> {
                    val list = combinedItems as DecryptedCombineContacts
                    mContactsAdapter = MainContactsAdapter(list.mainContactsItems, this)
                    rvCommonList!!.adapter = mContactsAdapter
                    mContactsAdapter!!.notifyDataSetChanged()
                }
                R.string.travel -> {
                    val list = combinedItems as DecryptedCombineTravel
                    mTravelItemsAdapter = TravelItemsAdapter(list.travelItems, this)
                    rvCommonList!!.adapter = mTravelItemsAdapter
                    mTravelItemsAdapter!!.notifyDataSetChanged()
                }
                R.string.education_work -> {
                    val list = combinedItems as DecryptedCombineEducation
                    mEducationItemsAdapter = EducationItemsAdapter(list.educationItems, this)
                    rvCommonList!!.adapter = mEducationItemsAdapter
                    mEducationItemsAdapter!!.notifyDataSetChanged()
                }
                R.string.personal -> {
                    var list = combinedItems as DecryptedCombinePersonal
                    mPersonalItemsAdapter = PersonalItemsAdapter(list.personalItems, this)
                    rvCommonList!!.adapter = mPersonalItemsAdapter
                    mPersonalItemsAdapter!!.notifyDataSetChanged()
                }
                R.string.interests -> {
                    var list = combinedItems as DecryptedCombineInterests
                    mInterestItemsAdapter = InterestItemsAdapter(list.interestItems, this)
                    rvCommonList!!.adapter = mInterestItemsAdapter
                    mInterestItemsAdapter!!.notifyDataSetChanged()
                }
                R.string.wellness -> {
                    var list = combinedItems as DecryptedCombineWellness
                    mWellnessItemsAdapter = WellnessItemsAdapter(list.wellnessItems, this)
                    rvCommonList!!.adapter = mWellnessItemsAdapter
                    mWellnessItemsAdapter!!.notifyDataSetChanged()
                }
                R.string.memories -> {
                    var list = combinedItems as DecryptedCombineMemories
                    mMainMemoriesAdapter = MainMemoriesAdapter(list.mainMemoriesItems, this)
                    rvCommonList!!.adapter = mMainMemoriesAdapter
                    mMainMemoriesAdapter!!.notifyDataSetChanged()
                }
                R.string.shopping -> {
                    var list = combinedItems as DecryptedCombineShopping
                    mShoppingItemsAdapter = ShoppingItemsAdapter(list.shoppingItems, this)
                    rvCommonList!!.adapter = mShoppingItemsAdapter
                    mShoppingItemsAdapter!!.notifyDataSetChanged()
                }
            /*R.string.home_amp_money -> {}*/
            }
        } else {
            searchHelper = SearchHelper()
            searchHelper.setOnDocumentSelection(this)

            val searchItems = searchHelper.getLevel3SearchItemsForCategory(categoryID, searchHelper.getSearchItems(combinedItems!!))
            AppLogger.d("SearchItems", " " + searchItems)
            rvCommonList!!.adapter = SearchAdapter(searchItems, SEARCH_EDIT, this)
        }
        rvCommonList!!.adapter.notifyDataSetChanged()
    }

    private var mCurrentSearchItem : Level3SearchItem ?= null
    override fun onItemClick(itemPosition : Int, position: Int, searchItem: Level3SearchItem, action : String ) {
        mCurrentSearchItem = searchItem
        searchHelper.switchAndSearch(searchItem, action)
        AppLogger.d("SearchCategory: ", getString(searchItem.searchCategory))
        AppLogger.d("searchItem", " categoryName: " + searchItem.categoryName)
        AppLogger.d("searchItem", " " + searchItem.toString())
    }

    override fun onBackPressed(): Boolean {
        activity!!.onBackPressed()
        return true
    }

    // Checking The Permission is Enabled Or Not

    private fun checkPermissions(permissions: Array<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissionInternal(permissions)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun checkPermissionInternal(permissions: Array<String>): Boolean {
        val requestPerms = ArrayList<String>()
        for (permission in permissions) {
            if (context!!.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED && !userDeniedPermissionAfterRationale(permission)) {
                requestPerms.add(permission)
            }
        }
        if (requestPerms.size > 0 && !mRequestPermissionsInProcess.getAndSet(true)) {
            //  We do not have this essential permission, ask for it
            requestPermissions(requestPerms.toTypedArray(), REQUEST_PERMISSION)
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSION) {
            var i = 0
            val len = permissions.size
            while (i < len) {
                val permission = permissions[i]
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    if (Manifest.permission.READ_CONTACTS == permission) {
                        showRationale(permission, R.string.permission_denied_contacts)
                    }
                }
                i++
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun showRationale(permission: String, promptResId: Int) {
        if (shouldShowRequestPermissionRationale(permission) && !userDeniedPermissionAfterRationale(permission)) {

            //  Notify the user of the reduction in functionality and possibly exit (app dependent)
            AlertDialog.Builder(context!!)
                    .setTitle(R.string.permission_denied)
                    .setMessage(promptResId)
                    .setPositiveButton(R.string.permission_deny) { dialog, _ ->
                        try {
                            dialog.dismiss()
                        } catch (ignore: Exception) {
                        }

                        setUserDeniedPermissionAfterRationale(permission)
                        mRequestPermissionsInProcess.set(false)
                    }
                    .setNegativeButton(R.string.permission_retry) { dialog, which ->
                        try {

                            dialog.dismiss()
                        } catch (ignore: Exception) {
                        }

                        mRequestPermissionsInProcess.set(false)
                        checkPermissions(arrayOf(permission))
                    }
                    .show()
        } else {
            mRequestPermissionsInProcess.set(false)
        }
    }


    private fun userDeniedPermissionAfterRationale(permission: String): Boolean {
        val sharedPrefs = context!!.getSharedPreferences(javaClass.simpleName, Context.MODE_PRIVATE)
        return sharedPrefs.getBoolean(PREFERENCE_PERMISSION_DENIED + permission, false)
    }

    private fun setUserDeniedPermissionAfterRationale(permission: String) {
        val editor = context!!.getSharedPreferences(javaClass.simpleName, Context.MODE_PRIVATE).edit()
        editor.putBoolean(PREFERENCE_PERMISSION_DENIED + permission, true).commit()
    }


    private fun callForContact() {
        callContactPicker()

        // populate contact list
        populateContactList(mGroups, mContacts)
    }

    private fun callContactPicker() {
        val intent = Intent(context, ContactPickerActivity::class.java)

                .putExtra(ContactPickerActivity.EXTRA_CONTACT_BADGE_TYPE,
                        ContactPictureType.ROUND.name)
                .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION,
                        ContactDescription.ADDRESS.name)
                .putExtra(ContactPickerActivity.EXTRA_SHOW_CHECK_ALL, true)
                .putExtra(ContactPickerActivity.EXTRA_SELECT_CONTACTS_LIMIT, 0)
                .putExtra(ContactPickerActivity.EXTRA_ONLY_CONTACTS_WITH_PHONE, false)
                .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION_TYPE,
                        ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                .putExtra(ContactPickerActivity.EXTRA_CONTACT_SORT_ORDER,
                        ContactSortOrder.AUTOMATIC.name)
        startActivityForResult(intent, REQUEST_CONTACT)
    }

    private fun populateContactList(groups: List<Group>?, contacts: List<Contact>?) {
        // we got a result from the contact picker --> show the picked contacts
        val result = SpannableStringBuilder()

        try {
            if (groups != null && !groups.isEmpty()) {
                result.append("GROUPS\n")
                for (group in groups) {
                    populateContact(result, group, "")
                    for (contact in group.contacts) {
                        populateContact(result, contact, "    ")
                    }
                }
            }
            if (contacts != null && !contacts.isEmpty()) {
                result.append("\n")
                for (contact in contacts) {
                    populateContact(result, contact, "")
                }
            }
        } catch (e: Exception) {
            result.append(e.message)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK && data != null &&
                (data.hasExtra(ContactPickerActivity.RESULT_GROUP_DATA) || data.hasExtra(ContactPickerActivity.RESULT_CONTACT_DATA))) {

            // we got a result from the contact picker --> show the picked contacts
            mGroups = data.getSerializableExtra(ContactPickerActivity.RESULT_GROUP_DATA) as List<Group>
            mContacts = data.getSerializableExtra(ContactPickerActivity.RESULT_CONTACT_DATA) as ArrayList<Contact>
//            setContactsList()
        }
        else if( requestCode == LEVEL_3 && resultCode == Activity.RESULT_OK ) {
            if( data!!.hasExtra("action") ) {
                val action = data.getStringExtra("action")
                when( action ) {
                    "delete" -> {
                        searchHelper.switchAndSearch(mCurrentSearchItem!!, action)
                    }
                    "add" -> {
                        combinedItems = data.getParcelableExtra(Constants.COMBINE_ITEMS)
                        loadItems()
                        //update list
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean(EXTRA_DARK_THEME, mDarkTheme)
        if (mGroups != null) {
            outState.putSerializable(EXTRA_GROUPS, mGroups as Serializable)
        }
        if (mContacts != null) {
            outState.putSerializable(EXTRA_CONTACTS, mContacts as Serializable)
        }
    }

    private fun populateContact(result: SpannableStringBuilder, element: ContactElement, prefix: String) {
        //int start = result.length();
        val displayName = element.displayName
        result.append(prefix)
        result.append(displayName + "\n")
    }

    /*override fun contactsEdited(contacts: DecryptedTravel) {
        mTravelItemsAdapter!!.updateContact(contacts)
    }*/

    /*override fun contactsDeleted(contacts: DecryptedTravel) {
        showDialogToDelete(contacts )
    }*/

    /*override fun contactsEdited(contacts: DecryptedMainContacts) {
        mContactsAdapter!!.updateContact(contacts)
    }*/

    /*override fun contactsDeleted(contacts: DecryptedMainContacts) {
        showDialogToDelete(contacts )
    }*/

    /*override fun contactsEdited(contacts: DecryptedEducation) {
        mEducationItemsAdapter!!.updateContact(contacts)
    }*/
}