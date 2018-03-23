package com.ninebx.ui.home.account.interfaces

import android.os.Parcelable
import com.ninebx.ui.base.realm.decrypted.DecryptedTravel

/**
 * Created by venu on 22-03-2018.
 */

interface TravelItemsAdded {

    fun contactsClicked(contacts: Parcelable, isEditable: Boolean)

    fun contactsDeleted(contacts: Parcelable)

}