package com.ninebx.ui.home.account.interfaces

import android.os.Parcelable

/**
 * Created by venu on 23-03-2018.
 */
interface PersonalItemsAdded {

    fun contactsClicked(contacts: Parcelable, isEditable: Boolean)

    fun contactsDeleted(contacts: Parcelable)
}