package com.ninebx.ui.home.lists

import com.ninebx.R
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
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok on 03/01/18.
 */
class ListsPresenter(val listsCommunicationView: ListsCommunicationView, val detailsId : Long, var categoryInt : Int = -1 ) {
    
    private var categoryCount = 0
    val context = getApplicationContext()
    init {
        listsCommunicationView.showProgress(R.string.loading)
        categoryCount = 0
        if( categoryInt == -1 ) {
            prepareCombine()

            prepareTravel()

            prepareContacts()

            prepareEducation()

            prepareInterests()

            preparePersonal()

            prepareWellness()

            prepareMemories()

            prepareShopping()
        }
        else {
            when( categoryInt ) {
                R.string.home_amp_money -> {
                    prepareCombine()
                }
                R.string.travel -> {
                    prepareTravel()
                }
                R.string.contacts -> {
                    prepareContacts()
                }
                R.string.education_work -> {
                    prepareEducation()
                }
                R.string.personal -> {
                    preparePersonal()
                }
                R.string.interests -> {
                    prepareInterests()
                }
                R.string.wellness -> {
                    prepareWellness()
                }
                R.string.memories -> {
                    prepareWellness()
                }
                R.string.shopping -> {
                    prepareShopping()
                }
            }

        }

    }

    private fun prepareShopping() {
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                categoryCount++
                hideProgressDialog()
                val contacts = "Shopping".encryptString()
                val contactsUpdating = realm!!
                        .where(ShoppingList::class.java)
                        .beginGroup()
                        .equalTo("detailsId", detailsId )
                        .and()
                        .equalTo("selectionType", contacts)
                        .endGroup()
                        .findAll()
                AppLogger.e("Count ", " is " + contactsUpdating)

                val fetchCombineShopping = realm.where(CombineShopping::class.java).findAll()
                if (fetchCombineShopping.size > 0) {
                    listsCommunicationView.shoppingListCount(contactsUpdating.count(), contactsUpdating)
                }
            }
        })
    }

    private fun prepareMemories() {
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                categoryCount++
                hideProgressDialog()
                val contacts = "Memories".encryptString()
                val contactsUpdating = realm!!
                        .where(MemoriesList::class.java)
                        .beginGroup()
                        .equalTo("detailsId", detailsId )
                        .and()
                        .equalTo("selectionType", contacts)
                        .endGroup()
                        .findAll()
                AppLogger.e("Count ", " is " + contactsUpdating)

                val fetchCombineMemories = realm.where(CombineMemories::class.java).findAll()
                if (fetchCombineMemories.size > 0) {
                    listsCommunicationView.memoryListCount(contactsUpdating.count(), contactsUpdating)
                }
            }
        })
    }

    private fun prepareWellness() {
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                categoryCount++
                hideProgressDialog()
                val contacts = "WellNess".encryptString()
                val contactsUpdating = realm!!
                        .where(WellnessList::class.java)
                        .beginGroup()
                        .equalTo("detailsId", detailsId )
                        .and()
                        .equalTo("selectionType", contacts)
                        .endGroup()
                        .findAll()
                AppLogger.e("Count ", " is " + contactsUpdating)

                val fetchCombineWellness = realm.where(CombineWellness::class.java).findAll()
                if (fetchCombineWellness.size > 0) {
                    listsCommunicationView.wellnessListCount(contactsUpdating.count(), contactsUpdating)
                }
            }
        })
    }

    private fun preparePersonal() {
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                categoryCount++
                hideProgressDialog()
                val contacts = "Personal".encryptString()
                val contactsUpdating = realm!!
                        .where(PersonalList::class.java)
                        .beginGroup()
                        .equalTo("detailsId", detailsId )
                        .and()
                        .equalTo("selectionType", contacts)
                        .endGroup()
                        .findAll()
                AppLogger.e("Count ", " is " + contactsUpdating)
                val fetchCombinePersonal = realm.where(CombinePersonal::class.java).findAll()
                if (fetchCombinePersonal.size > 0) {
                    listsCommunicationView.countPersonalList(contactsUpdating.count(), contactsUpdating)
                }
            }
        })
    }

    private fun prepareInterests() {
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_INTERESTS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                categoryCount++
                hideProgressDialog()
                val contacts = "Interests".encryptString()
                val contactsUpdating = realm!!
                        .where(InterestsList::class.java)
                        .beginGroup()
                        .equalTo("detailsId", detailsId )
                        .and()
                        .equalTo("selectionType", contacts)
                        .endGroup()
                        .findAll()
                AppLogger.e("Count ", " is " + contactsUpdating)

                val fetchCombineInterests = realm.where(CombineInterests::class.java).findAll()
                if (fetchCombineInterests.size > 0) {
                    listsCommunicationView.interestListCount(contactsUpdating.count(), contactsUpdating)
                }
            }
        })
    }

    private fun prepareEducation() {
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_EDUCATION, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                categoryCount++
                hideProgressDialog()
                val contacts = "Education".encryptString()
                val contactsUpdating = realm!!
                        .where(EducationList::class.java)
                        .beginGroup()
                        .equalTo("detailsId", detailsId )
                        .and()
                        .equalTo("selectionType", contacts)
                        .endGroup()
                        .findAll()
                AppLogger.e("Count ", " is " + contactsUpdating)
                val fetchCombineEducation = realm.where(CombineEducation::class.java).findAll()
                if (fetchCombineEducation.size > 0) {
                    listsCommunicationView.educationListCount(contactsUpdating.count(), contactsUpdating)
                }
            }
        })
    }

    private fun prepareContacts() {
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                categoryCount++
                hideProgressDialog()
                val contacts = "Contacts".encryptString()
                val contactsUpdating = realm!!
                        .where(ContactsList::class.java)
                        .beginGroup()
                        .equalTo("detailsId", detailsId )
                        .and()
                        .equalTo("selectionType", contacts)
                        .endGroup()
                        .findAll()
                AppLogger.e("Count ", " is " + contactsUpdating)
                val fetchCombineContacts = realm.where(CombineContacts::class.java).findAll()
                if (fetchCombineContacts.size > 0) {
                    listsCommunicationView.contactListCount(contactsUpdating.count(), contactsUpdating)
                }
            }
        })
    }

    private fun prepareTravel() {
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                categoryCount++
                hideProgressDialog()
                val contacts = "Travel".encryptString()
                val contactsUpdating = realm!!
                        .where(TravelList::class.java)
                        .beginGroup()
                        .equalTo("detailsId", detailsId )
                        .and()
                        .equalTo("selectionType", contacts)
                        .endGroup()
                        .findAll()
                AppLogger.e("Count ", " is " + contactsUpdating)

                val fetchCombineTravel = realm.where(CombineTravel::class.java).findAll()
                if (fetchCombineTravel.size > 0) {
                    listsCommunicationView.travelListCount(contactsUpdating.count(), contactsUpdating)
                }
            }
        })
    }

    private fun prepareCombine() {
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                categoryCount++
                hideProgressDialog()
                val contacts = "HomeBanking".encryptString()
                val contactsUpdating = realm!!
                        .where(HomeList::class.java)
                        .beginGroup()
                        .equalTo("detailsId", detailsId )
                        .and()
                        .equalTo("selectionType", contacts)
                        .endGroup()
                        .findAll()
                AppLogger.d("Count ", " is " + contactsUpdating)

                val fetchCombine = realm.where(Combine::class.java).findAll()
                if (fetchCombine.size > 0) {
                    listsCommunicationView.homeListCount(contactsUpdating.count(), contactsUpdating)
                }
            }
        })
    }

    private fun hideProgressDialog() {
        if( categoryInt == -1  && categoryCount == 9 ) {
            categoryCount = 0
            listsCommunicationView.hideProgress()
        }
        else if( categoryInt != -1 ) {
            categoryCount = 0
            listsCommunicationView.hideProgress()
        }
    }
}