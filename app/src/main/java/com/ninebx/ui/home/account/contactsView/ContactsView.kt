package com.ninebx.ui.home.account.contactsView

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.home.contacts.Contacts

/**
 * Created by cognitive on 15/02/18.
 */

internal interface ContactsView : BaseView {
    fun onContacts(contacts: Contacts)
    fun showError(error: String)
    fun onContactsDelete(contacts: Contacts)
}