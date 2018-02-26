package com.ninebx.ui.home.lists

import com.ninebx.ui.base.BaseView

/**
 * Created by Alok on 03/01/18.
 */
interface ListsCommunicationView : BaseView {
    fun homeListCount(contactsUpdating: Long)
    fun travelListCount(contactsUpdating: Long)
    fun contactListCount(contactsUpdating: Long)
    fun educationListCount(contactsUpdating: Long)
    fun interestListCount(contactsUpdating: Long)
    fun countPersonalList(contactsUpdating: Long)
    fun wellnessListCount(contactsUpdating: Long)
    fun memoryListCount(contactsUpdating: Long)
    fun shoppingListCount(contactsUpdating: Long)
}