package com.ninebx.ui.home.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.handleMultiplePermission
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.realm.RealmString
import com.ninebx.ui.base.realm.decrypted.DecryptedContacts
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.account.interfaces.IContactsAdded
import com.ninebx.ui.home.adapter.ContactsAdapter
import com.ninebx.utility.*
import com.ninebx.utility.Constants.REALM_END_POINT_COMBINE_CONTACTS
import com.onegravity.contactpicker.ContactElement
import com.onegravity.contactpicker.contact.Contact
import com.onegravity.contactpicker.contact.ContactDescription
import com.onegravity.contactpicker.contact.ContactSortOrder
import com.onegravity.contactpicker.core.ContactPickerActivity
import com.onegravity.contactpicker.group.Group
import com.onegravity.contactpicker.picture.ContactPictureType
import io.realm.Realm
import io.realm.RealmList
import kotlinx.android.synthetic.main.fragment_contact_list.*
import kotlinx.android.synthetic.main.row_notification.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

/***
 * Created by TechnoBlogger on 24/01/18.
 */
class ContactsListContainerFragment : FragmentBackHelper(), IContactsAdded {

    override fun contactsDeleted(contacts: DecryptedContacts?) { //not working
        prepareRealmConnections(context!!, false, REALM_END_POINT_COMBINE_CONTACTS, object: Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                contactsRealm = realm
                if(contactsRealm != null) {
                    contactsRealm!!.beginTransaction()
                    contactsRealm!!.where(Contacts::class.java).equalTo("id", contacts!!.id).findAll().deleteAllFromRealm()
                    contactsRealm!!.commitTransaction()
                    contactsRealm!!.close()
                }
            }

        })
    }

    override fun contactsEdited(contacts: DecryptedContacts?) {
        //AppLogger.d("Contacts", "are" + contacts)
        //finalList?.add(contacts!!)
        mListsAdapter!!.updateContact(contacts!!)
    }

    var selectedContact : DecryptedContacts ?= null
    override fun contactsClicked(contacts: DecryptedContacts?, isEditable: Boolean) {
        mListsAdapter!!.notifyDataSetChanged()
        selectedContact = contacts
        val bundle = Bundle()
        bundle.putBoolean("isEditable", isEditable)
        bundle.putParcelable(Constants.CONTACTS_VIEW, contacts)
        bundle.putString(Constants.FROM_CLASS, "Contacts")
        bundle.putLong("ID", contacts!!.id)
        startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), ADD_CONTACTS)
    }

    private val ADD_CONTACTS = 3000

    private var mListsAdapter: ContactsAdapter? = null
    var myList: ArrayList<Contacts> = ArrayList()
    private var contactsList: ArrayList<Contacts>? = ArrayList()
    private var contactsRealm: Realm? = null

    private var firstName = ""
    private var lastName = ""
    private var strMobileNumber = ""

    private val PERMISSIONS_REQUEST_CODE_CONTACTS = 111

    private val REQUEST_CONTACT = 0
    private var mContacts: ArrayList<Contact>? = ArrayList()
    private var mGroups: List<Group>? = null
    private val EXTRA_DARK_THEME = "EXTRA_DARK_THEME"
    private val EXTRA_GROUPS = "EXTRA_GROUPS"
    private val EXTRA_CONTACTS = "EXTRA_CONTACTS"
    private var mDarkTheme: Boolean = false
    var count = 0


    //

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    var finalList: ArrayList<DecryptedContacts> ?= ArrayList()
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Shared Contacts")

        contactsList = arguments!!.getParcelableArrayList<Contacts>(Constants.REALM_CONTACTS)
        myList.addAll(contactsList!!)
        myList.reverse()

        decryptMyContacts(myList)
        if(decrypedContacts != null)
        sortMyList(decrypedContacts!!)

        mListsAdapter = ContactsAdapter(finalList, this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvContactList!!.layoutManager = layoutManager
        rvContactList!!.adapter = mListsAdapter

        ivHome.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.callHomeFragment()
        }

        ivBackContactView.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        layoutAddList.setOnClickListener {
            val permissionList = arrayListOf<String>(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            if (!handleMultiplePermission(context!!, permissionList)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissionList.toTypedArray(), PERMISSIONS_REQUEST_CODE_CONTACTS)
                } else {
                    callForContact()
                }
            } else {
                callForContact()
            }
        }

        /*prepareRealmConnections(context, true, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                contactsRealm = realm
                context!!.hideProgressDialog()

            }
        })*/

    }

    /*private fun createIndex(finalList: ArrayList<DecryptedContacts>) {


        var AindexedList = ArrayList<String>()
        loop@ for(contact in finalList) {
            when(contact.firstName[0].toString()) {
                "A" -> {
                    if(AheaderCount == false) {
                        AindexedList.add(contact.firstName[0].toString())
                        AheaderCount = true
                    }
                    AindexedList.add(contact.firstName)
                    continue@loop
                }
                "B" -> {}
                "C" -> {}
                "D" -> {}
                "E" -> {}
                "F" -> {}
                "G" -> {}
                "H" -> {}
                "I" -> {}
                "J" -> {}
                "K" -> {}
                "L" -> {}
                "M" -> {}
                "N" -> {}
                "O" -> {}
                "P" -> {}
                "Q" -> {}
                "R" -> {}
                "S" -> {}
                "T" -> {}
                "U" -> {}
                "V" -> {}
                "W" -> {}
                "X" -> {}
                "Y" -> {}
                "Z" -> {}
                "a" -> {}
                "b" -> {}
                "c" -> {}
                "d" -> {}
                "e" -> {}
                "f" -> {}
                "g" -> {}
                "h" -> {}
                "i" -> {}
                "j" -> {}
                "k" -> {}
                "l" -> {}
                "m" -> {}
                "n" -> {}
                "o" -> {}
                "p" -> {}
                "q" -> {}
                "r" -> {}
                "s" -> {}
                "t" -> {}
                "u" -> {}
                "v" -> {}
                "w" -> {}
                "x" -> {}
                "y" -> {}
                "z" -> {}            }
        }
    }*/

    private fun sortMyList(decrypedContacts: ArrayList<DecryptedContacts>){
        val list = decrypedContacts.sortedWith( compareBy( { it.firstName }))
        finalList = ArrayList(list)
        if(mListsAdapter != null)
            mListsAdapter!!.notifyData(finalList!!)
    }

    var decrypedContacts: ArrayList<DecryptedContacts> ?= ArrayList()
    fun decryptMyContacts(myList: ArrayList<Contacts>) {
        for(contact in myList) {
            decrypedContacts!!.add(decryptContact(contact))
        }
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideQuickAdd()
        NineBxApplication.instance.activityInstance!!.showBottomView()

        return super.onBackPressed()
    }

    @SuppressLint("StaticFieldLeak")
    fun deleteContactFromRealm() {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                prepareRealmConnections(context!!, false, REALM_END_POINT_COMBINE_CONTACTS, object: Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        if(contactsRealm != null) {
                            contactsRealm!!.beginTransaction()
                            contactsRealm!!.where(Contacts::class.java).equalTo("id", selectedContact!!.id).findAll().deleteAllFromRealm()
                            contactsRealm!!.commitTransaction()
                            //contactsRealm!!.close()
                        }
                    }
                })
            return null
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_CONTACTS && resultCode == Activity.RESULT_OK) {
            if(data!!.hasExtra("DELETED")) {
                mListsAdapter!!.deleteContact(selectedContact!!)
                //contactsDeleted(selectedContact!!)
                deleteContactFromRealm()
            } else {
                contactsEdited(data.getParcelableExtra(Constants.CONTACTS_VIEW))
            }
        } else if (requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK && data != null &&
                (data.hasExtra(ContactPickerActivity.RESULT_GROUP_DATA) || data.hasExtra(ContactPickerActivity.RESULT_CONTACT_DATA))) {
            mGroups = data.getSerializableExtra(ContactPickerActivity.RESULT_GROUP_DATA) as List<Group>
            mContacts = data.getSerializableExtra(ContactPickerActivity.RESULT_CONTACT_DATA) as ArrayList<Contact>

            for (contact in mContacts!!) {
                val realmContacts = Contacts()
                realmContacts.id = UUID.randomUUID().hashCode().toLong()
                realmContacts.firstName = contact.firstName
                realmContacts.lastName = contact.lastName
                if(contact.getPhone(0) != null)
                    realmContacts.mobileOne = contact.getPhone(0)
                else
                    realmContacts.mobileOne = ""

                firstName = contact.firstName
                strMobileNumber = contact.getPhone(0)

                val newContact = DecryptedContacts()
                newContact.id = realmContacts.id
                newContact.firstName = realmContacts.firstName
                newContact.lastName = realmContacts.lastName
                newContact.mobileOne = realmContacts.mobileOne

                finalList!!.add(newContact)
                sortMyList(finalList!!)

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var contacts = Contacts()
                        contacts.id = realmContacts.id
                        contacts.firstName = firstName.encryptString()
                        contacts.lastName = lastName.encryptString()
                        contacts.mobileOne = strMobileNumber.encryptString()
                        contacts.selectionType = "Contacts".encryptString()
                        contacts.insertOrUpdate(realm!!)
                        contactsRealm!!.refresh()
                    }

                })
                //push to combineContacts also

            }
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }

    private fun populateContact(result: SpannableStringBuilder, element: ContactElement, prefix: String) {
        //int start = result.length();
        val displayName = element.displayName
        result.append(prefix)
        result.append(displayName + "\n")
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

    //
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (requestCode == PERMISSIONS_REQUEST_CODE_CONTACTS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callForContact()
            } else {
                Toast.makeText(context, "Some permissions were denied", Toast.LENGTH_LONG).show()
            }
        }
    }


    //


}