package com.ninebx.ui.home.lists

import android.os.Bundle
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.R.id.txtMemoriesNumber
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.home.education.CombineEducation
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.ui.base.realm.home.interests.CombineInterests
import com.ninebx.ui.base.realm.home.memories.CombineMemories
import com.ninebx.ui.base.realm.home.personal.CombinePersonal
import com.ninebx.ui.base.realm.home.shopping.CombineShopping
import com.ninebx.ui.base.realm.home.travel.CombineTravel
import com.ninebx.ui.base.realm.home.wellness.CombineWellness
import com.ninebx.ui.base.realm.lists.*
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok on 03/01/18.
 */
class ListsPresenter(val listsCommunicationView: ListsCommunicationView) {
    init {
        val context = getApplicationContext()

        var decryptedCombine = DecryptedCombine()
        var combineTravelFetched = DecryptedCombineTravel()
        var combineContactsFetched = DecryptedCombineContacts()
        var combineEducationFetched = DecryptedCombineEducation()
        var combineInterestsFetched = DecryptedCombineInterests()
        var combinePersonalFetched = DecryptedCombinePersonal()
        var combineWellnessFetched = DecryptedCombineWellness()
        var combineMemoriesFetched = DecryptedCombineMemories()
        var combineShoppingFetched = DecryptedCombineShopping()


        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                context!!.hideProgressDialog()
                var contacts = "HomeBanking".encryptString()
                val contactsUpdating = realm!!
                        .where(HomeList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.d("Count ", " is " + contactsUpdating)

                val fetchCombine = realm.where(Combine::class.java).findAll()
                if (fetchCombine.size > 0) {
                    for (i in 0 until fetchCombine.size) {
                        var decryptCombine = decryptCombine(fetchCombine[i]!!)
                        decryptedCombine.listItems.addAll(decryptCombine.listItems)
                    }
                    listsCommunicationView.homeListCount(contactsUpdating, decryptedCombine)
                }
            }
        })

        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                context!!.hideProgressDialog()
                var contacts = "Travel".encryptString()
                val contactsUpdating = realm!!
                        .where(TravelList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)

                val fetchCombine = realm.where(CombineTravel::class.java).findAll()
                if (fetchCombine.size > 0) {
                    for (i in 0 until fetchCombine.size) {
                        var combineTravelFetched = decryptCombineTravel(fetchCombine[i]!!)
                        combineTravelFetched.listItems.addAll(combineTravelFetched.listItems)
                    }
                    listsCommunicationView.travelListCount(contactsUpdating, combineTravelFetched)
                }
//                listsCommunicationView.travelListCount(contactsUpdating)
            }
        })

        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                context!!.hideProgressDialog()
                var contacts = "Contacts".encryptString()
                val contactsUpdating = realm!!
                        .where(ContactsList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                val fetchCombine = realm.where(CombineContacts::class.java).findAll()
                if (fetchCombine.size > 0) {
                    for (i in 0 until fetchCombine.size) {
                        var combineContactsFetched = decryptCombineContacts(fetchCombine[i]!!)
                        combineContactsFetched.listItems.addAll(combineContactsFetched.listItems)
                    }
                    listsCommunicationView.contactListCount(contactsUpdating, combineContactsFetched)
                }
//                listsCommunicationView.contactListCount(contactsUpdating)

            }
        })

        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_EDUCATION, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                context!!.hideProgressDialog()
                var contacts = "Education".encryptString()
                val contactsUpdating = realm!!
                        .where(EducationList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                val fetchCombine = realm.where(CombineEducation::class.java).findAll()
                if (fetchCombine.size > 0) {
                    for (i in 0 until fetchCombine.size) {
                        var combineEducationFetched = decryptCombineEducation(fetchCombine[i]!!)
                        combineEducationFetched.listItems.addAll(combineEducationFetched.listItems)
                    }
                    listsCommunicationView.educationListCount(contactsUpdating, combineEducationFetched)
                }
//                listsCommunicationView.educationListCount(contactsUpdating)
            }
        })

        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_INTERESTS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                context!!.hideProgressDialog()
                var contacts = "Interests".encryptString()
                val contactsUpdating = realm!!
                        .where(InterestsList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                val fetchCombine = realm.where(CombineInterests::class.java).findAll()
                if (fetchCombine.size > 0) {
                    for (i in 0 until fetchCombine.size) {
                        var combineInterestsFetched = decryptCombineInterests(fetchCombine[i]!!)
                        combineInterestsFetched.listItems.addAll(combineInterestsFetched.listItems)
                    }
                    listsCommunicationView.interestListCount(contactsUpdating, combineInterestsFetched)
                }
//                listsCommunicationView.interestListCount(contactsUpdating)
            }
        })

        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                context!!.hideProgressDialog()
                var contacts = "Personal".encryptString()
                val contactsUpdating = realm!!
                        .where(PersonalList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                val fetchCombine = realm.where(CombinePersonal::class.java).findAll()
                if (fetchCombine.size > 0) {
                    for (i in 0 until fetchCombine.size) {
                        var combinePersonalFetched = decryptCombinePersonal(fetchCombine[i]!!)
                        combinePersonalFetched.listItems.addAll(combinePersonalFetched.listItems)
                    }
                    listsCommunicationView.countPersonalList(contactsUpdating, combinePersonalFetched)
                }
//                listsCommunicationView.countPersonalList(contactsUpdating)
            }
        })

        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                context!!.hideProgressDialog()
                var contacts = "WellNess".encryptString()
                val contactsUpdating = realm!!
                        .where(WellnessList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)

                val fetchCombine = realm.where(CombineWellness::class.java).findAll()
                if (fetchCombine.size > 0) {
                    for (i in 0 until fetchCombine.size) {
                        var combineWellnessFetched = decryptCombineWellness(fetchCombine[i]!!)
                        combineWellnessFetched.listItems.addAll(combineWellnessFetched.listItems)
                    }
                    listsCommunicationView.wellnessListCount(contactsUpdating, combineWellnessFetched)
                }
//                listsCommunicationView.wellnessListCount(contactsUpdating)
            }
        })

        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                context!!.hideProgressDialog()
                var contacts = "Memories".encryptString()
                val contactsUpdating = realm!!
                        .where(MemoriesList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)

                val fetchCombine = realm.where(CombineMemories::class.java).findAll()
                if (fetchCombine.size > 0) {
                    for (i in 0 until fetchCombine.size) {
                        var combineMemoriesFetched = decryptCombineMemories(fetchCombine[i]!!)
                        combineMemoriesFetched.listItems.addAll(combineMemoriesFetched.listItems)
                    }
                    listsCommunicationView.memoryListCount(contactsUpdating, combineMemoriesFetched)
                }
//                listsCommunicationView.memoryListCount(contactsUpdating)
            }
        })

        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                context!!.hideProgressDialog()
                var contacts = "Shopping".encryptString()
                val contactsUpdating = realm!!
                        .where(ShoppingList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)

                val fetchCombine = realm.where(CombineShopping::class.java).findAll()
                if (fetchCombine.size > 0) {
                    for (i in 0 until fetchCombine.size) {
                        var combineShoppingFetched = decryptCombineShopping(fetchCombine[i]!!)
                        combineShoppingFetched.listItems.addAll(combineShoppingFetched.listItems)
                    }
                    listsCommunicationView.shoppingListCount(contactsUpdating, combineShoppingFetched)
                }
//                listsCommunicationView.shoppingListCount(contactsUpdating)
            }
        })
    }
}