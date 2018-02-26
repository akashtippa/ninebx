package com.ninebx.ui.home.lists

import android.os.Bundle
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.R.id.txtMemoriesNumber
import com.ninebx.ui.base.realm.lists.*
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok on 03/01/18.
 */
class ListsPresenter(val listsCommunicationView: ListsCommunicationView)  {
    init {
        val context = getApplicationContext()
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                var contacts = "HomeBanking".encryptString()
                val contactsUpdating = realm!!
                        .where(HomeList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.homeListCount(contactsUpdating)
            }
        })
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                var contacts = "Travel".encryptString()
                val contactsUpdating = realm!!
                        .where(TravelList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.travelListCount(contactsUpdating)
            }
        })
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                var contacts = "Contacts".encryptString()
                val contactsUpdating = realm!!
                        .where(ContactsList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.contactListCount(contactsUpdating)

            }
        })
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_EDUCATION, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                var contacts = "Education".encryptString()
                val contactsUpdating = realm!!
                        .where(EducationList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.educationListCount(contactsUpdating)
            }
        })
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_INTERESTS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                var contacts = "Interests".encryptString()
                val contactsUpdating = realm!!
                        .where(InterestsList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.interestListCount(contactsUpdating)
            }
        })
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                var contacts = "Personal".encryptString()
                val contactsUpdating = realm!!
                        .where(PersonalList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.countPersonalList(contactsUpdating)
            }
        })
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                var contacts = "WellNess".encryptString()
                val contactsUpdating = realm!!
                        .where(WellnessList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.wellnessListCount(contactsUpdating)
            }
        })
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                var contacts = "Memories".encryptString()
                val contactsUpdating = realm!!
                        .where(MemoriesList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.memoryListCount(contactsUpdating)
            }
        })
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                var contacts = "Shopping".encryptString()
                val contactsUpdating = realm!!
                        .where(ShoppingList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.shoppingListCount(contactsUpdating)
            }
        })
    }
}