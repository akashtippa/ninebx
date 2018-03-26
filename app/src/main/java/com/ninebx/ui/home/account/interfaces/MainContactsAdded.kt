package com.ninebx.ui.home.account.interfaces

import android.os.Parcelable
import com.ninebx.ui.base.realm.decrypted.DecryptedMainContacts

/**
 * Created by venu on 21-03-2018.
 */
interface MainContactsAdded {

    fun contactsClicked(contacts: Parcelable, isEditable: Boolean)
    fun contactsDeleted(contacts: Parcelable)

}