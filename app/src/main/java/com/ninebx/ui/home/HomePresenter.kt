package com.ninebx.ui.home

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.home.education.CombineEducation
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.ui.base.realm.home.personal.CombinePersonal
import com.ninebx.ui.base.realm.home.travel.CombineTravel
import com.ninebx.ui.base.realm.home.wellness.CombineWellness
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok Omkar on 2018-02-24.
 */
@SuppressLint("StaticFieldLeak")
class HomePresenter( val homeView : HomeView ) {

    private val context = getApplicationContext()

    private val mDecryptCombineHome = DecryptedCombine()
    private val mDecryptedCombineTravel = DecryptedCombineTravel()
    private val mDecryptCombineEducation = DecryptedCombineEducation()
    private val mDecryptCombineWellness = DecryptedCombineWellness()
    private val mDecryptCombinePersonal = DecryptedCombinePersonal()
    private val mDecryptedCombineContacts = DecryptedCombineContacts()


    fun fetchDataInBackground() {

        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val combineResult = realm!!.where(Combine::class.java).distinctValues("id").findAll()
                        if (combineResult.size > 0) {
                            for (i in 0 until combineResult.size) {
                                val decryptedCombine = decryptCombine(combineResult[i]!!)
                                mDecryptCombineHome.listItems.addAll(decryptedCombine.listItems)
                                mDecryptCombineHome.propertyItems.addAll(decryptedCombine.propertyItems)
                                mDecryptCombineHome.vehicleItems.addAll(decryptedCombine.vehicleItems)
                                mDecryptCombineHome.taxesItems.addAll(decryptedCombine.taxesItems)
                                mDecryptCombineHome.insuranceItems.addAll(decryptedCombine.insuranceItems)
                                mDecryptCombineHome.assetItems.addAll(decryptedCombine.assetItems)
                                mDecryptCombineHome.paymentItems.addAll(decryptedCombine.paymentItems)
                                mDecryptCombineHome.financialItems.addAll(decryptedCombine.financialItems)
                            }
                        }
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                homeView!!.onCombineHomeFetched(mDecryptCombineHome)

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val combineTravel = realm!!.where(CombineTravel::class.java).distinctValues("id").findAll()
                        if (combineTravel.size > 0) {
                            for (i in 0 until combineTravel.size) {
                                val decryptedCombineTravel = decryptCombineTravel(combineTravel[i]!!)
                                mDecryptedCombineTravel.documentsItems.addAll(decryptedCombineTravel.documentsItems)
                                mDecryptedCombineTravel.loyaltyItems.addAll(decryptedCombineTravel.loyaltyItems)
                                mDecryptedCombineTravel.travelItems.addAll(decryptedCombineTravel.travelItems)
                                mDecryptedCombineTravel.vacationsItems.addAll(decryptedCombineTravel.vacationsItems)
                                mDecryptedCombineTravel.listItems.addAll(decryptedCombineTravel.listItems)
                            }

                        }
                        //AppLogger.d("Combine", "CombinedTravel : " + combineTravel)
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                homeView!!.onCombineTravelFetched(mDecryptedCombineTravel)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val combineContacts = realm!!.where(CombineContacts::class.java).distinctValues("id").findAll()
                        if (combineContacts.size > 0) {
                            for (i in 0 until combineContacts.size) {
                                val decryptedCombineContacts = decryptCombineContacts(combineContacts[i]!!)
                                mDecryptedCombineContacts.contactsItems.addAll(decryptedCombineContacts.contactsItems)
                                mDecryptedCombineContacts.mainContactsItems.addAll(decryptedCombineContacts.mainContactsItems)
                                mDecryptedCombineContacts.listItems.addAll(decryptedCombineContacts.listItems)
                                //AppLogger.d("Recent Search", "Decrypted Recent Search " + decryptCombineContacts(combineContacts[i]!!))

                            }

                        }
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                homeView!!.onCombineContactsFetched(mDecryptedCombineContacts)

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val combinePersonal = realm!!.where(CombinePersonal::class.java).distinctValues("id").findAll()
                        if (combinePersonal.size > 0) {
                            for (i in 0 until combinePersonal.size) {
                                val decryptedCombinePersonal = decryptCombinePersonal(combinePersonal[i]!!)
                                mDecryptCombinePersonal.certificateItems.addAll(decryptedCombinePersonal.certificateItems)
                                mDecryptCombinePersonal.governmentItems.addAll(decryptedCombinePersonal.governmentItems)
                                mDecryptCombinePersonal.licenseItems.addAll(decryptedCombinePersonal.licenseItems)
                                mDecryptCombinePersonal.personalItems.addAll(decryptedCombinePersonal.personalItems)
                                mDecryptCombinePersonal.socialItems.addAll(decryptedCombinePersonal.socialItems)
                                mDecryptCombinePersonal.taxIDItems.addAll(decryptedCombinePersonal.taxIDItems)
                                mDecryptCombinePersonal.listItems.addAll(decryptedCombinePersonal.listItems)
                            }
                        }
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                homeView!!.onCombinePersonalFetched(mDecryptCombinePersonal)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val combineWellness = realm!!.where(CombineWellness::class.java).distinctValues("id").findAll()
                        if (combineWellness.size > 0) {
                            for (i in 0 until combineWellness.size) {
                                val decryptedCombineWellness = decryptCombineWellness(combineWellness[i]!!)
                                mDecryptCombineWellness.checkupsItems.addAll(decryptedCombineWellness.checkupsItems)
                                mDecryptCombineWellness.emergencyContactsItems.addAll(decryptedCombineWellness.emergencyContactsItems)
                                mDecryptCombineWellness.eyeglassPrescriptionsItems.addAll(decryptedCombineWellness.eyeglassPrescriptionsItems)
                                mDecryptCombineWellness.healthcareProvidersItems.addAll(decryptedCombineWellness.healthcareProvidersItems)
                                mDecryptCombineWellness.identificationItems.addAll(decryptedCombineWellness.identificationItems)
                                mDecryptCombineWellness.medicalConditionsItems.addAll(decryptedCombineWellness.medicalConditionsItems)
                                mDecryptCombineWellness.medicalHistoryItems.addAll(decryptedCombineWellness.medicalHistoryItems)
                                mDecryptCombineWellness.medicationsItems.addAll(decryptedCombineWellness.medicationsItems)
                                mDecryptCombineWellness.vitalNumbersItems.addAll(decryptedCombineWellness.vitalNumbersItems)
                                mDecryptCombineWellness.wellnessItems.addAll(decryptedCombineWellness.wellnessItems)
                                mDecryptCombineWellness.listItems.addAll(decryptedCombineWellness.listItems)
                            }

                        }
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                homeView!!.onCombineWellnessFetched(mDecryptCombineWellness)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_EDUCATION, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val combineEducation = realm!!.where(CombineEducation::class.java).distinctValues("id").findAll()
                        if (combineEducation.size > 0) {
                            for (i in 0 until combineEducation.size) {
                                val decryptedCombineEducation = decryptCombineEducation(combineEducation[i]!!)
                                mDecryptCombineEducation.listItems.addAll(decryptedCombineEducation.listItems)
                                mDecryptCombineEducation.educationItems.addAll(decryptedCombineEducation.educationItems)
                                mDecryptCombineEducation.mainEducationItems.addAll(decryptedCombineEducation.mainEducationItems)
                                mDecryptCombineEducation.workItems.addAll(decryptedCombineEducation.workItems)
                            }
                            for (finance in mDecryptCombineEducation.workItems) {
                                //AppLogger.d("REcords", finance.toString())
                            }

                        }
                    }
                })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                homeView!!.onCombineEducationFetched(mDecryptCombineEducation)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private var currentUsers: ArrayList<DecryptedUsers>?=null

    fun fetchCurrentUsers() {
        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(homeView.getContextForScreen(), true, Constants.REALM_END_POINT_USERS,
                        object : Realm.Callback() {
                            override fun onSuccess(realm: Realm?) {
                                currentUsers = ArrayList()
                                for( user in realm!!.where(Users::class.java).findAll() ) {
                                    currentUsers!!.add( decryptUsers(user) )
                                }
                            }
                        })
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                homeView.setCurrentUsers(currentUsers!!)
                registerUsersChangeListener()
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

    }

    fun registerUsersChangeListener() {
        prepareRealmConnections(homeView.getContextForScreen(), false, Constants.REALM_END_POINT_USERS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                realm!!.where(Users::class.java).findAll().addChangeListener( object : RealmChangeListener<RealmResults<Users>> {
                    override fun onChange(t: RealmResults<Users>?) {
                        currentUsers = ArrayList()
                        for( user in t!! ) {
                            currentUsers!!.add( decryptUsers(user) )
                        }
                        homeView.setCurrentUsers(currentUsers)
                    }
                } )
            }

        })
    }
/*
    fun addNotification(expirationDate: String, date: Date, subTitle: String, box_Name : String) {
        //AppLogger.d("UpdateNotification", "Method invoked ")
        var notifications = Notifications()
        var message = "AndroidTest"
        notifications.id =  UUID.randomUUID().hashCode().toLong()
        notifications.message = message.encryptString()
        notifications.boxName = box_Name.encryptString()
        notifications.dueDate = expirationDate
        notifications.subTitle = subTitle.encryptString()
        notifications.private = false
        notifications.created = box_Name + date

        prepareRealmConnections(context, false, "Notifications", object : Realm.Callback(){
            override fun onSuccess(realm: Realm?) {
                realm!!.beginTransaction()
                notifications.insertOrUpdate(realm)
                realm.commitTransaction()
                //AppLogger.d("NewNotification", "Added" )
            }
        })
    }*/
}