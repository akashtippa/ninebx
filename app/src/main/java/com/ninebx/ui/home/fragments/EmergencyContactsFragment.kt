package com.ninebx.ui.home.fragments


import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.realm.decrypted.DecryptedCombineWellness
import com.ninebx.ui.base.realm.decrypted.DecryptedContacts
import com.ninebx.ui.base.realm.decrypted.DecryptedEmergencyContacts
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.base.realm.home.wellness.CombineWellness
import com.ninebx.ui.base.realm.home.wellness.EmergencyContacts
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.account.interfaces.IEmergencyContacts
import com.ninebx.ui.home.adapter.EmergencyContactAdapter
import com.ninebx.ui.home.baseCategories.SubCategory
import com.ninebx.utility.*
import com.onegravity.contactpicker.ContactElement
import com.onegravity.contactpicker.contact.Contact
import com.onegravity.contactpicker.contact.ContactDescription
import com.onegravity.contactpicker.contact.ContactSortOrder
import com.onegravity.contactpicker.core.ContactPickerActivity
import com.onegravity.contactpicker.group.Group
import com.onegravity.contactpicker.picture.ContactPictureType
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_emergency_contacts.*
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean


/**
 * A simple [Fragment] subclass.
 */
class EmergencyContactsFragment() : FragmentBackHelper() , IEmergencyContacts {
    override fun emergencyContactsEdited(contacts: DecryptedEmergencyContacts) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var selectedContact : DecryptedEmergencyContacts ?= null
    private val LEVEL_3: Int = 3232

    override fun emergencyContactsClicked(contacts: DecryptedEmergencyContacts, isEditable: Boolean) {
        mListsAdapter!!.notifyDataSetChanged()
        selectedContact = contacts
        val intent = Intent( context, ContainerActivity::class.java)
        intent.putExtras(intent.extras)
        intent.putExtra("selectedDocument", selectedContact )
        startActivityForResult(intent, LEVEL_3)
    }

    override fun emergencyContactsDeleted(contacts: DecryptedEmergencyContacts) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val mRequestPermissionsInProcess = AtomicBoolean()
    private val REQUEST_PERMISSION = 3
    private val PREFERENCE_PERMISSION_DENIED = "PREFERENCE_PERMISSION_DENIED"
    private val REQUEST_CONTACT = 0
    private var mContacts: ArrayList<Contact>? = ArrayList()
    private var mGroups: List<Group>? = null
    private var contactsRealm: Realm? = null
    var finalList: ArrayList<DecryptedEmergencyContacts> ?= ArrayList()
    private lateinit var combineItems: DecryptedCombineWellness
    private var mListsAdapter: EmergencyContactAdapter? = null

    private lateinit var subCategory: SubCategory

    private val ADD_CONTACTS = 3000

    private var firstName = ""

    private var strMobileNumber = ""

    private var combineContactsRealm: CombineWellness ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_emergency_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        combineItems = arguments!!.getParcelable<DecryptedCombineWellness>(Constants.COMBINE_ITEMS)
        subCategory = arguments!!.getParcelable<SubCategory>(Constants.SUB_CATEGORY)

        mListsAdapter = EmergencyContactAdapter(combineItems!!.emergencyContactsItems, this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvContactList!!.layoutManager = layoutManager
        rvContactList!!.adapter = mListsAdapter
        rvContactList!!.adapter.notifyDataSetChanged()

        ivHome.setOnClickListener {
            val homeIntent = Intent(context, HomeActivity::class.java)
            startActivity(homeIntent)
            activity!!.finishAffinity()
        }

        ivBackContactView.setOnClickListener{
            activity!!.finish()
        }

        layoutAddList.setOnClickListener{
            checkPermissions(arrayOf(Manifest.permission.READ_CONTACTS))
            callForContact()
        }

        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback(){
            override fun onSuccess(realm: Realm?) {
                contactsRealm = realm
                context!!.hideProgressDialog()
            }
        })
    }

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
    private fun userDeniedPermissionAfterRationale(permission: String): Boolean {
        val sharedPrefs = context!!.getSharedPreferences(javaClass.simpleName, Context.MODE_PRIVATE)
        return sharedPrefs.getBoolean(PREFERENCE_PERMISSION_DENIED + permission, false)
    }
    private fun callForContact() {
        callContactPicker()
        populateContactList(mGroups, mContacts)
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
    private fun populateContact(result: SpannableStringBuilder, element: ContactElement, prefix: String) {
        //int start = result.length();
        val displayName = element.displayName
        result.append(prefix)
        result.append(displayName + "\n")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK && data != null &&
                (data.hasExtra(ContactPickerActivity.RESULT_GROUP_DATA) || data.hasExtra(ContactPickerActivity.RESULT_CONTACT_DATA))) {

            mGroups = data.getSerializableExtra(ContactPickerActivity.RESULT_GROUP_DATA) as List<Group>
            mContacts = data.getSerializableExtra(ContactPickerActivity.RESULT_CONTACT_DATA) as ArrayList<Contact>
            AppLogger.d("EmergencyContacts", " extracted " + mContacts)

            for (contact in mContacts!!) {
                val realmContacts = EmergencyContacts()
                realmContacts.id = UUID.randomUUID().hashCode().toLong()
                realmContacts.name = contact.firstName + contact.lastName
                
                if(!contact.getPhone(0).isNullOrEmpty())
                    realmContacts.phoneNumberOne = contact.getPhone(0)
                else
                    realmContacts.phoneNumberOne = ""

                firstName = contact.firstName + contact.lastName
                if(!contact.getPhone(0).isNullOrEmpty())
                    strMobileNumber = contact.getPhone(0)
                else
                    strMobileNumber = ""

                val newContact = DecryptedEmergencyContacts()
                newContact.id = realmContacts.id
                newContact.name = realmContacts.name
                newContact.phoneNumberOne = realmContacts.phoneNumberOne

                finalList!!.add(newContact)
                sortMyList(finalList!!)

                contactsRealm!!.beginTransaction()

                var contacts = EmergencyContacts()
                contacts.id = realmContacts.id
                contacts.name = firstName .encryptString()
                contacts.phoneNumberOne = strMobileNumber.encryptString()
                contacts.selectionType = subCategory.personName.encryptString()
                contactsRealm!!.insertOrUpdate(contacts)


                if(combineContactsRealm == null) {
                    combineContactsRealm = contactsRealm!!.createObject(CombineWellness::class.java, getUniqueId())
                }
                if(combineContactsRealm!!.emergencyContactsItems!!.contains(contacts)) {
                    val index = combineContactsRealm!!.emergencyContactsItems.indexOf(contacts)
                    if(index != -1) {
                        combineContactsRealm!!.emergencyContactsItems[index] = (contacts)
                    }
                } else {
                    combineContactsRealm!!.emergencyContactsItems.add(contacts)
                }
                contactsRealm!!.insertOrUpdate(combineContactsRealm)


            }
            contactsRealm!!.commitTransaction()
            contactsRealm!!.refresh()

            AppLogger.d("Successfully " , " Updated emergency contacts")
        }
        else if( requestCode == LEVEL_3 ) {
            //TODO
        }
    }

    private fun sortMyList(decryptedEmergencyContacts: ArrayList<DecryptedEmergencyContacts>) {
        val list = decryptedEmergencyContacts.sortedWith( compareBy( { it.name }))
        finalList = ArrayList(list)
        if(mListsAdapter != null)
            mListsAdapter!!.notifyData(finalList!!)
    }

}