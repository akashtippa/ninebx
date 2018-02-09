package com.ninebx.ui.home.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.SpannableStringBuilder
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.ninebx.R
import com.onegravity.contactpicker.ContactElement
import com.onegravity.contactpicker.contact.Contact
import com.onegravity.contactpicker.contact.ContactDescription
import com.onegravity.contactpicker.contact.ContactSortOrder
import com.onegravity.contactpicker.core.ContactPickerActivity
import com.onegravity.contactpicker.group.Group
import com.onegravity.contactpicker.picture.ContactPictureType
import kotlinx.android.synthetic.main.activity_contacts.*
import java.io.Serializable

class ContactsActivity : BaseActivity() {

    private val EXTRA_DARK_THEME = "EXTRA_DARK_THEME"
    private val EXTRA_GROUPS = "EXTRA_GROUPS"
    private val EXTRA_CONTACTS = "EXTRA_CONTACTS"

    private val REQUEST_CONTACT = 0

    private var mDarkTheme: Boolean = false
    private var mContacts: List<Contact>? = null
    private var mGroups: List<Group>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        layoutAddList.setOnClickListener {
            callContactPicker()
        }

        // populate contact list
        populateContactList(mGroups, mContacts)

    }

    fun callContactPicker() {
        val intent = Intent(this@ContactsActivity, ContactPickerActivity::class.java)
                .putExtra(ContactPickerActivity.EXTRA_THEME, if (mDarkTheme)
                    R.style.Theme_Dark
                else
                    R.style.Theme_Light)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK && data != null &&
                (data.hasExtra(ContactPickerActivity.RESULT_GROUP_DATA) || data.hasExtra(ContactPickerActivity.RESULT_CONTACT_DATA))) {

            // we got a result from the contact picker --> show the picked contacts
            mGroups = data.getSerializableExtra(ContactPickerActivity.RESULT_GROUP_DATA) as List<Group>
            mContacts = data.getSerializableExtra(ContactPickerActivity.RESULT_CONTACT_DATA) as List<Contact>
            populateContactList(mGroups, mContacts)
        }
    }

    private fun populateContactList(groups: List<Group>?, contacts: List<Contact>?) {
        // we got a result from the contact picker --> show the picked contacts
        val contactsView = findViewById<View>(R.id.contacts) as TextView
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
                result.append("CONTACTS\n")
                for (contact in contacts) {
                    populateContact(result, contact, "")
                }
            }
        } catch (e: Exception) {
            result.append(e.message)
        }

        contactsView.text = result
    }

    private fun populateContact(result: SpannableStringBuilder, element: ContactElement, prefix: String) {
        //int start = result.length();
        val displayName = element.displayName
        result.append(prefix)
        result.append(displayName + "\n")
        //result.setSpan(new BulletSpan(15), start, result.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.contact_picker_demo, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)

        val textId = if (mDarkTheme) R.string.light_theme else R.string.dark_theme
        menu.findItem(R.id.action_theme).setTitle(textId)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_theme) {
            mDarkTheme = !mDarkTheme
            val intent = Intent(this, this::class.java)
                    .putExtra(EXTRA_DARK_THEME, mDarkTheme)
            if (mGroups != null) {
                intent.putExtra(EXTRA_GROUPS, mGroups as Serializable)
            }
            if (mContacts != null) {
                intent.putExtra(EXTRA_CONTACTS, mContacts as Serializable)
            }
            startActivity(intent)
            finish()
            return true
        }

        return false
    }
}
