package com.ninebx.ui.home.lists

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.decrypted.*

/**
 * Created by Alok on 03/01/18.
 */
interface ListsCommunicationView : BaseView {
    fun homeListCount(contactsUpdating: Int, decryptCombine : ArrayList<DecryptedHomeList>?)
    fun travelListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedTravelList>)
    fun contactListCount(contactsUpdating: Int, decryptCombine : ArrayList<DecryptedContactsList>)
    fun educationListCount(contactsUpdating: Int, decryptCombine : ArrayList<DecryptedEducationList>)
    fun interestListCount(contactsUpdating: Int, decryptCombine : ArrayList<DecryptedInterestsList>)
    fun countPersonalList(contactsUpdating: Int, decryptCombine : ArrayList<DecryptedPersonalList>)
    fun wellnessListCount(contactsUpdating: Int, decryptCombine : ArrayList<DecryptedWellnessList>)
    fun memoryListCount(contactsUpdating: Int, decryptCombine : ArrayList<DecryptedMemoriesList>)
    fun shoppingListCount(contactsUpdating: Int, decryptCombine : ArrayList<DecryptedShoppingList>)
}