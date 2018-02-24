package com.ninebx.ui.home.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.base.realm.lists.*
import com.ninebx.ui.home.BaseHomeFragment
import com.ninebx.ui.home.account.addmembers.AddFamilyUsersFragment
import com.ninebx.ui.home.search.SearchView
import com.ninebx.utility.*
import com.ninebx.utility.Constants.FINGER_PRINT_COMPLETE

import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_lists.*

/**
 * Created by Alok on 03/01/18.
 */
class ListsFragment : BaseHomeFragment(), ListsCommunicationView, SearchView {

    override fun onCombineFetched(combine: DecryptedCombine) {
        txtHomeNumber.text = combine.listItems.count().toString()
    }

    override fun onCombineMemoryFetched(combineMemory: DecryptedCombineMemories) {
        txtMemoriesNumber.text = combineMemory.listItems.count().toString()
    }

    override fun onCombineTravelFetched(combineTravel: DecryptedCombineTravel) {
        txtTravelNumber.text = combineTravel.listItems.count().toString()
    }

    override fun onCombineEducationFetched(combineEducation: DecryptedCombineEducation) {
        txtEducationNumber.text = combineEducation.listItems.count().toString()
    }

    override fun onCombineInterestsFetched(combineInterests: DecryptedCombineInterests) {
        txtInterestsNumber.text = combineInterests.listItems.count().toString()
    }

    override fun onCombineWellnessFetched(combineWellness: DecryptedCombineWellness) {
        txtWellnessNumber.text = combineWellness.listItems.count().toString()
    }

    override fun onCombinePersonalFetched(combinePersonal: DecryptedCombinePersonal) {
        txtPersonalNumber.text = combinePersonal.listItems.count().toString()
    }

    override fun onCombineShoppingFetched(combineShopping: DecryptedCombineShopping) {
        txtShoppingNumber.text = combineShopping.listItems.count().toString()
    }

    override fun onCombineContactsFetched(combineContacts: DecryptedCombineContacts) {
        txtContactNumber.text = combineContacts.listItems.count().toString()
    }

    override fun onRecentSearchFetched(recentSearch: ArrayList<DecryptedRecentSearch>) {
        TODO()
    }

    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {
        context!!.hideProgressDialog()
    }

    override fun onError(error: Int) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }
//
//    private val mDecryptCombine: DecryptedCombine = DecryptedCombine()
//    private val mDecryptedCombineMemories = DecryptedCombineMemories()
//    private val mDecryptedCombineTravel = DecryptedCombineTravel()
//    private val mDecryptCombineEducation = DecryptedCombineEducation()
//    private val mDecryptCombineInterests = DecryptedCombineInterests()
//    private val mDecryptCombineWellness = DecryptedCombineWellness()
//    private val mDecryptCombinePersonal = DecryptedCombinePersonal()
//    private val mDecryptCombineShopping = DecryptedCombineShopping()
//    private val mDecryptedCombineContacts = DecryptedCombineContacts()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layHome.setOnClickListener {
            callSubListFragment(getString(R.string.home_amp_money))
        }
        layTravel.setOnClickListener {
            callSubListFragment(getString(R.string.travel))
        }
        layContact.setOnClickListener {
            callSubListFragment(getString(R.string.contacts))
        }
        layEducation.setOnClickListener {
            callSubListFragment(getString(R.string.education_work))
        }
        layPersonal.setOnClickListener {
            callSubListFragment(getString(R.string.personal))
        }
        layInterests.setOnClickListener {
            callSubListFragment(getString(R.string.interests))
        }
        layWellness.setOnClickListener {
            callSubListFragment(getString(R.string.wellness))
        }
        layMemories.setOnClickListener {
            callSubListFragment(getString(R.string.memories))
        }
        layShopping.setOnClickListener {
            callSubListFragment(getString(R.string.shopping))
        }
//
//        var countHome = mDecryptCombine.getListsCount("HomeBanking")
//        var countTravel = (mDecryptedCombineTravel.getTravelLists("Travel"))
//        var countContact = (mDecryptedCombineContacts.getListsCount("Contacts"))
//        var countEducation = (mDecryptCombineEducation.getListItemsCount("Education"))
//        var countInterests = (mDecryptCombineInterests.getLists("Interests"))
//        var countPersonal = (mDecryptCombinePersonal.getListsCount("Personal"))
//        var countWellness = mDecryptedCombineMemories.getLists("WellNess")
//        var countMemories = (mDecryptCombineWellness.getLists("WellNess"))
//        var countShopping = mDecryptCombineShopping.getShoppingLists("Shopping")


        prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                var contacts = "HomeBanking".encryptString()
                val contactsUpdating = realm!!
                        .where(HomeList::class.java)
                        .equalTo("selectionType", contacts)
                        .count()
                AppLogger.e("Count ", " is " + contactsUpdating)
                txtHomeNumber.text = contactsUpdating.toString()
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
                txtTravelNumber.text = contactsUpdating.toString()
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
                txtContactNumber.text = contactsUpdating.toString()
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
                if (contactsUpdating.toString().isEmpty()) {
                    return
                } else {
                    txtEducationNumber.text = contactsUpdating.toString()
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
                txtInterestsNumber.text = contactsUpdating.toString()
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
                txtPersonalNumber.text = contactsUpdating.toString()
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
                txtWellnessNumber.text = contactsUpdating.toString()
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
                txtMemoriesNumber.text = contactsUpdating.toString()
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
                txtShoppingNumber.text = contactsUpdating.toString()
            }
        })

        var currentUsers: RealmResults<HomeList>? = null

        prepareRealmConnections(context, true, "Users", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {

                currentUsers = getHomeList(realm!!)
                if (currentUsers != null) {
                    context!!.hideProgressDialog()
                    AppLogger.e("CurrentUser", "Users from Realm : " + currentUsers.toString())
                    if (NineBxApplication.getPreferences().currentStep == FINGER_PRINT_COMPLETE) {
                        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                        fragmentTransaction.addToBackStack(null)
                        val addFamilyUsersFragment = SubListsFragment()
                        val bundle = Bundle()
                        bundle.putParcelableArrayList(Constants.LIST_HOME, HomeList.createParcelableList(currentUsers!!))
                        addFamilyUsersFragment.arguments = bundle
                        fragmentTransaction.replace(R.id.frameLayout, addFamilyUsersFragment).commit()
                    }
                }
            }
        })
    }

    private fun callSubListFragment(option: String) {
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        val bundle = Bundle()
        bundle.putString("homeScreen", "bottom")

        val categoryFragment = SubListsFragment()
        categoryFragment.arguments = bundle
        fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()

        when (option) {
            getString(R.string.home_amp_money) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.home_amp_money))
            }
            getString(R.string.travel) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.travel))
            }
            getString(R.string.contacts) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.contacts))
            }
            getString(R.string.education_work) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.education_work))
            }
            getString(R.string.personal) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.personal))
            }
            getString(R.string.interests) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.interests))
            }
            getString(R.string.wellness) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.wellness))
            }
            getString(R.string.memories) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.memories))
            }
            getString(R.string.shopping) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.shopping))
            }
        }
    }


}