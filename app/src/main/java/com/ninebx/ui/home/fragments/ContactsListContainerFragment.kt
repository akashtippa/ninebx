package com.ninebx.ui.home.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.account.interfaces.IContactsAdded
import com.ninebx.ui.home.adapter.ContactsAdapter
import com.ninebx.utility.*
import com.onegravity.contactpicker.ContactElement
import com.onegravity.contactpicker.contact.Contact
import com.onegravity.contactpicker.contact.ContactDescription
import com.onegravity.contactpicker.contact.ContactSortOrder
import com.onegravity.contactpicker.core.ContactPickerActivity
import com.onegravity.contactpicker.group.Group
import com.onegravity.contactpicker.picture.ContactPictureType
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_contact_list.*
import java.io.Serializable
import java.util.*

/***
 * Created by TechnoBlogger on 24/01/18.
 */
class ContactsListContainerFragment : FragmentBackHelper(), IContactsAdded {

    override fun contactsAdded(contacts: Contacts?) {
        AppLogger.d("Contacts", "are" + contacts)
        myList.add(contacts!!)
        mListsAdapter!!.notifyDataSetChanged()
        saveContactsList()
    }

    override fun contactsEdited(contacts: Contacts?) {
        mListsAdapter!!.notifyDataSetChanged()
        val bundle = Bundle()
        bundle.putParcelable(Constants.CONTACTS_VIEW, contacts)
        bundle.putString(Constants.FROM_CLASS, "Contacts")
        bundle.putString("ContactOperation", "Edit")
        bundle.putString("ID", contacts!!.id.toString())
        AppLogger.e("ID ", " is " + contacts.id.toString())
        AppLogger.e("ID ", " is " + contacts.mobileOne)
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

    //

    private val REQUEST_CONTACT = 0
    private var mContacts: ArrayList<Contact>? = ArrayList()
    private var mGroups: List<Group>? = null
    private val EXTRA_DARK_THEME = "EXTRA_DARK_THEME"
    private val EXTRA_GROUPS = "EXTRA_GROUPS"
    private val EXTRA_CONTACTS = "EXTRA_CONTACTS"
    private var mDarkTheme: Boolean = false
    val PICK_CONTACT = 1


    //

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Shared Contacts")

        contactsList = arguments!!.getParcelableArrayList<Contacts>(Constants.REALM_CONTACTS)
        myList.addAll(contactsList!!)

        mListsAdapter = ContactsAdapter(myList, this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvContactList!!.layoutManager = layoutManager
        rvContactList!!.adapter = mListsAdapter

        layoutAddList.setOnClickListener {
            //            val bundle = Bundle()
//            bundle.putParcelable(Constants.CONTACTS_VIEW, Contacts())
//            bundle.putString(Constants.FROM_CLASS, "Contacts")
//            bundle.putString("ContactOperation", "Add")
//            bundle.putString("ID", "0")
//            startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), ADD_CONTACTS)
//
            callForContact()
        }

        prepareRealmConnections(context, true, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                contactsRealm = realm
                saveContactsList()
            }
        })

    }


    private fun saveContactsList() {
        val memoryObject = Contacts.createContacts(contactsList!![0])
        memoryObject.insertOrUpdate(contactsRealm!!)
        context!!.hideProgressDialog()
//        myList.clear()
        myList.add(memoryObject)
//        val index: Int = myList.size - 1
//        myList.removeAt(index)
        mListsAdapter!!.notifyDataSetChanged()
    }


    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideQuickAdd()
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.showBackIcon()
        return super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_CONTACTS && resultCode == Activity.RESULT_OK) {
            contactsAdded(data!!.getParcelableExtra(Constants.CONTACTS_VIEW))
        } else if (requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK && data != null &&
                (data.hasExtra(ContactPickerActivity.RESULT_GROUP_DATA) || data.hasExtra(ContactPickerActivity.RESULT_CONTACT_DATA))) {
            mGroups = data.getSerializableExtra(ContactPickerActivity.RESULT_GROUP_DATA) as List<Group>
            mContacts = data.getSerializableExtra(ContactPickerActivity.RESULT_CONTACT_DATA) as ArrayList<Contact>

            for (contact in mContacts!!) {
                val realmContacts = Contacts()
                realmContacts.id = getUniqueId()
                realmContacts.firstName = contact.firstName
                realmContacts.lastName = contact.lastName
                realmContacts.mobileOne = contact.getPhone(0)

                firstName = contact.firstName
                strMobileNumber = contact.getPhone(0)
                AppLogger.e("First Name ", " is " + contact.firstName)
                AppLogger.e("Mobile Number ", "0 is " + contact.getPhone(0))
                AppLogger.e("Mobile Number ", "1 is " + contact.getPhone(1))

                myList.add(realmContacts)
                mListsAdapter!!.notifyDataSetChanged()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        var contacts = Contacts()
                        contacts.id = getUniqueId()

                        /*
                        * If I'm sending like this,
                        * contacts.firstName = firstName
                        * It is showing some decrypted format in Android, but iOS is readable.
                        * But, if I'm using like this
                        * contacts.firstName = firstName.encryptString()
                        * It is showing some decrypted format in iOS, but Android is readable.
                        * */

                        contacts.firstName = firstName.encryptString()
                        contacts.lastName = lastName.encryptString()
                        contacts.mobileOne = strMobileNumber.encryptString()
                        contacts.insertOrUpdate(realm!!)

                        NineBxApplication.instance.activityInstance!!.onBackPressed()
                    }

                })

            }

//            setContactsList()

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
}