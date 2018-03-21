package com.ninebx.ui.home.account.interfaces

import com.ninebx.ui.base.realm.decrypted.DecryptedMainContacts

/**
 * Created by venu on 21-03-2018.
 */
interface MainContactsAdded {
    fun contactsEdited(contacts: DecryptedMainContacts)

    fun contactsClicked(contacts: DecryptedMainContacts, isEditable: Boolean?)

    fun contactsDeleted(contacts: DecryptedMainContacts)

}