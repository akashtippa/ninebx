package com.ninebx.ui.home.lists

import android.annotation.SuppressLint
import android.os.AsyncTask
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
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok on 03/01/18.
 */
@SuppressLint("StaticFieldLeak")
class ListsPresenter(val listsCommunicationView: ListsCommunicationView, val detailsId : Long, var categoryInt : Int = -1 ) {

    private var categoryCount = 0
    val context = getApplicationContext()


    fun fetchDataInBackground() {
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

    private var shoppingList: RealmResults<ShoppingList> ?= null

    private fun prepareShopping() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val contacts = "Shopping".encryptString()
                        shoppingList = realm!!
                                .where(ShoppingList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId)
                                .and()
                                .equalTo("selectionType", contacts)
                                .endGroup()
                                .findAll()
                    }
                })
            }

            override fun onPostExecute(result: Unit) {
                super.onPostExecute(result)
                listsCommunicationView.shoppingListCount(shoppingList!!.count(), shoppingList!!)
                categoryCount++
                hideProgressDialog()
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private var memoriesList: RealmResults<MemoriesList> ?= null

    private fun prepareMemories() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val contacts = "Memories".encryptString()
                        memoriesList = realm!!
                                .where(MemoriesList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId )
                                .and()
                                .equalTo("selectionType", contacts)
                                .endGroup()
                                .findAll()

                    }
                })
            }
            override fun onPostExecute(result: Unit) {
                super.onPostExecute(result)
                listsCommunicationView.memoryListCount(memoriesList!!.count(), memoriesList!!)
                categoryCount++
                hideProgressDialog()
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private var wellnessList: RealmResults<WellnessList>? = null

    private fun prepareWellness() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val contacts = "WellNess".encryptString()
                        wellnessList = realm!!
                                .where(WellnessList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId )
                                .and()
                                .equalTo("selectionType", contacts)
                                .endGroup()
                                .findAll()

                    }
                })
            }
            override fun onPostExecute(result: Unit) {
                super.onPostExecute(result)
                //AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.wellnessListCount(wellnessList!!.count(), wellnessList!!)
                categoryCount++
                hideProgressDialog()
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private var personalList: RealmResults<PersonalList>? = null

    private fun preparePersonal() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val contacts = "Personal".encryptString()
                        personalList = realm!!
                                .where(PersonalList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId )
                                .and()
                                .equalTo("selectionType", contacts)
                                .endGroup()
                                .findAll()
                        //AppLogger.e("Count ", " is " + contactsUpdating)
                    }
                })

            }
            override fun onPostExecute(result: Unit) {
                super.onPostExecute(result)
                //AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.countPersonalList(personalList!!.count(), personalList!!)
                categoryCount++
                hideProgressDialog()
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private var interestsList: RealmResults<InterestsList>?=null

    private fun prepareInterests() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_INTERESTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val contacts = "Interests".encryptString()
                        interestsList = realm!!
                                .where(InterestsList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId )
                                .and()
                                .equalTo("selectionType", contacts)
                                .endGroup()
                                .findAll()
                        //AppLogger.e("Count ", " is " + contactsUpdating)


                    }
                })
            }
            override fun onPostExecute(result: Unit) {
                super.onPostExecute(result)
                //AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.interestListCount(interestsList!!.count(), interestsList!!)
                categoryCount++
                hideProgressDialog()
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private var eductationList: RealmResults<EducationList>? = null

    private fun prepareEducation() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_EDUCATION, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val contacts = "Education".encryptString()
                        eductationList = realm!!
                                .where(EducationList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId )
                                .and()
                                .equalTo("selectionType", contacts)
                                .endGroup()
                                .findAll()
                    }
                })
            }
            override fun onPostExecute(result: Unit) {
                super.onPostExecute(result)
                //AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.educationListCount(eductationList!!.count(), eductationList!!)
                categoryCount++
                hideProgressDialog()
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private var contactsList: RealmResults<ContactsList>? = null

    private fun prepareContacts() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val contacts = "Contacts".encryptString()
                        contactsList = realm!!
                                .where(ContactsList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId )
                                .and()
                                .equalTo("selectionType", contacts)
                                .endGroup()
                                .findAll()


                    }
                })
            }
            override fun onPostExecute(result: Unit) {
                super.onPostExecute(result)
                //AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.contactListCount(contactsList!!.count(), contactsList!!)
                categoryCount++
                hideProgressDialog()
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private var travelList: RealmResults<TravelList>? = null

    private fun prepareTravel() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val contacts = "Travel".encryptString()
                        travelList = realm!!
                                .where(TravelList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId )
                                .and()
                                .equalTo("selectionType", contacts)
                                .endGroup()
                                .findAll()

                    }
                })
            }
            override fun onPostExecute(result: Unit) {
                super.onPostExecute(result)
                //AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.travelListCount(travelList!!.count(), travelList!!)
                categoryCount++
                hideProgressDialog()
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private var homeList: RealmResults<HomeList>?=null

    private fun prepareCombine() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val contacts = "HomeBanking".encryptString()
                        homeList = realm!!
                                .where(HomeList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId )
                                .and()
                                .equalTo("selectionType", contacts)
                                .endGroup()
                                .findAll()


                    }
                })
            }
            override fun onPostExecute(result: Unit) {
                super.onPostExecute(result)
                //AppLogger.e("Count ", " is " + contactsUpdating)
                listsCommunicationView.homeListCount( homeList!!.count(), homeList )
                categoryCount++
                hideProgressDialog()
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
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