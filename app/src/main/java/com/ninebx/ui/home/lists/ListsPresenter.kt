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

        prepareRealmConnections(context, false, "CombineTravel", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                context!!.hideProgressDialog()
                var contacts = "Travel".encryptString()
                val contactsUpdating = realm!!
                        .where(TravelList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)

                val fetchCombineTravel = realm.where(CombineTravel::class.java).findAll()
                if (fetchCombineTravel.size > 0) {
                    for (i in 0 until fetchCombineTravel.size) {
                        var decryptCombineTravel = decryptCombineTravel(fetchCombineTravel[i]!!)
                        combineTravelFetched.listItems.addAll(decryptCombineTravel.listItems)
                    }
                    listsCommunicationView.travelListCount(contactsUpdating, combineTravelFetched)
                }
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
                val fetchCombineContacts = realm.where(CombineContacts::class.java).findAll()
                if (fetchCombineContacts.size > 0) {
                    for (i in 0 until fetchCombineContacts.size) {
                        var decryptCombineContacts = decryptCombineContacts(fetchCombineContacts[i]!!)
                        combineContactsFetched.listItems.addAll(decryptCombineContacts.listItems)
                    }
                    listsCommunicationView.contactListCount(contactsUpdating, combineContactsFetched)
                }
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
                val fetchCombineEducation = realm.where(CombineEducation::class.java).findAll()
                if (fetchCombineEducation.size > 0) {
                    for (i in 0 until fetchCombineEducation.size) {
                        var decryptCombineEducation = decryptCombineEducation(fetchCombineEducation[i]!!)
                        combineEducationFetched.listItems.addAll(decryptCombineEducation.listItems)
                    }
                    listsCommunicationView.educationListCount(contactsUpdating, combineEducationFetched)
                }
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

                val fetchCombineInterests = realm.where(CombineInterests::class.java).findAll()
                if (fetchCombineInterests.size > 0) {
                    for (i in 0 until fetchCombineInterests.size) {
                        var decryptCombineInterests = decryptCombineInterests(fetchCombineInterests[i]!!)
                        combineInterestsFetched.listItems.addAll(decryptCombineInterests.listItems)
                    }
                    listsCommunicationView.interestListCount(contactsUpdating, combineInterestsFetched)
                }
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
                val fetchCombinePersonal = realm.where(CombinePersonal::class.java).findAll()
                if (fetchCombinePersonal.size > 0) {
                    for (i in 0 until fetchCombinePersonal.size) {
                        var decryptCombinePersonal = decryptCombinePersonal(fetchCombinePersonal[i]!!)
                        combinePersonalFetched.listItems.addAll(decryptCombinePersonal.listItems)
                    }
                    listsCommunicationView.countPersonalList(contactsUpdating, combinePersonalFetched)
                }
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

                val fetchCombineWellness = realm.where(CombineWellness::class.java).findAll()
                if (fetchCombineWellness.size > 0) {
                    for (i in 0 until fetchCombineWellness.size) {
                        var decryptCombineWellness= decryptCombineWellness(fetchCombineWellness[i]!!)
                        combineWellnessFetched.listItems.addAll(decryptCombineWellness.listItems)
                    }
                    listsCommunicationView.wellnessListCount(contactsUpdating, combineWellnessFetched)
                }
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

                val fetchCombineMemories = realm.where(CombineMemories::class.java).findAll()
                if (fetchCombineMemories.size > 0) {
                    for (i in 0 until fetchCombineMemories.size) {
                        var decryptCombineMemories= decryptCombineMemories(fetchCombineMemories[i]!!)
                        combineMemoriesFetched.listItems.addAll(decryptCombineMemories.listItems)
                    }
                    listsCommunicationView.memoryListCount(contactsUpdating, combineMemoriesFetched)
                }
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

                val fetchCombineShopping = realm.where(CombineShopping::class.java).findAll()
                if (fetchCombineShopping.size > 0) {
                    for (i in 0 until fetchCombineShopping.size) {
                        var decryptCombineShopping= decryptCombineShopping(fetchCombineShopping[i]!!)
                        combineShoppingFetched.listItems.addAll(decryptCombineShopping.listItems)
                    }
                    listsCommunicationView.shoppingListCount(contactsUpdating, combineShoppingFetched)
                }
            }
        })
    }
}