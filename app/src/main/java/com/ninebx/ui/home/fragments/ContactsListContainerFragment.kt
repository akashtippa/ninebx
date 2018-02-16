package com.ninebx.ui.home.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_contact_list.*
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
        startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), ADD_CONTACTS)
    }


    private val ADD_CONTACTS = 3000

    private var mListsAdapter: ContactsAdapter? = null
    var myList: ArrayList<Contacts> = ArrayList()
    private var contactsList: ArrayList<Contacts>? = ArrayList()
    private var contactsRealm: Realm? = null

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
            val bundle = Bundle()
            bundle.putParcelable(Constants.CONTACTS_VIEW, Contacts())
            bundle.putString(Constants.FROM_CLASS, "Contacts")
            bundle.putString("ContactOperation", "Add")
            bundle.putString("ID", "0")
            startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), ADD_CONTACTS)
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
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }

}