package com.ninebx.ui.home.lists

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.lists.*
import io.realm.RealmResults

/**
 * Created by Alok on 03/01/18.
 */
interface ListsCommunicationView : BaseView {
    fun homeListCount(contactsUpdating: Int, decryptCombine : RealmResults<HomeList>?)
    fun travelListCount(contactsUpdating: Int, decryptCombine: RealmResults<TravelList>)
    fun contactListCount(contactsUpdating: Int, decryptCombine : RealmResults<ContactsList>)
    fun educationListCount(contactsUpdating: Int, decryptCombine : RealmResults<EducationList>)
    fun interestListCount(contactsUpdating: Int, decryptCombine : RealmResults<InterestsList>)
    fun countPersonalList(contactsUpdating: Int, decryptCombine : RealmResults<PersonalList>)
    fun wellnessListCount(contactsUpdating: Int, decryptCombine : RealmResults<WellnessList>)
    fun memoryListCount(contactsUpdating: Int, decryptCombine : RealmResults<MemoriesList>)
    fun shoppingListCount(contactsUpdating: Int, decryptCombine : RealmResults<ShoppingList>)
}