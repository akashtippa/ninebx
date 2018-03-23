package com.ninebx.ui.home.account.interfaces

import android.os.Parcelable
import com.ninebx.ui.base.realm.decrypted.DecryptedEducation

/**
 * Created by venu on 23-03-2018.
 */
interface EducationItemsAdded {

    fun contactsClicked(contacts: Parcelable, isEditable: Boolean)

    fun contactsDeleted(contacts: Parcelable)
}