package com.ninebx.ui.home.lists

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.decrypted.*

/**
 * Created by Alok on 03/01/18.
 */
interface ListsCommunicationView : BaseView {
    fun homeListCount(contactsUpdating: Long, decryptCombine : DecryptedCombine)
    fun travelListCount(contactsUpdating: Long, decryptCombine : DecryptedCombineTravel)
    fun contactListCount(contactsUpdating: Long, decryptCombine : DecryptedCombineContacts)
    fun educationListCount(contactsUpdating: Long, decryptCombine : DecryptedCombineEducation)
    fun interestListCount(contactsUpdating: Long, decryptCombine : DecryptedCombineInterests)
    fun countPersonalList(contactsUpdating: Long, decryptCombine : DecryptedCombinePersonal)
    fun wellnessListCount(contactsUpdating: Long, decryptCombine : DecryptedCombineWellness)
    fun memoryListCount(contactsUpdating: Long, decryptCombine : DecryptedCombineMemories)
    fun shoppingListCount(contactsUpdating: Long, decryptCombine : DecryptedCombineShopping)
}