package com.ninebx.ui.home.baseCategories

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.progressDialog
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.home.fragments.*
import com.ninebx.ui.home.lists.ListsCommunicationView
import com.ninebx.ui.home.lists.ListsPresenter
import com.ninebx.ui.home.lists.SubListsFragment
import com.ninebx.ui.home.lists.contacts.ContactsListFragment
import com.ninebx.ui.home.lists.education.EducationListFragment
import com.ninebx.ui.home.lists.interests.InterestListFragment
import com.ninebx.ui.home.lists.memories.MemoriesListFragment
import com.ninebx.ui.home.lists.personal.PersonalListFragment
import com.ninebx.ui.home.lists.shopping.ShoppingListFragment
import com.ninebx.ui.home.lists.travel.TravelListFragment
import com.ninebx.ui.home.lists.wellness.WellnessListFragment
import com.ninebx.ui.home.search.SearchPresenter
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_lists.*

/**
 * Created by Alok on 12/01/18.
 */
class ListFinderFragment : FragmentBackHelper(), ListsCommunicationView {
    override fun showProgress(message: Int) {
    }

    override fun hideProgress() {
        progressDialog!!.cancel()
    }

    override fun onError(error: Int) {
    }


    var fromWhichBox: Int? = null
    var combineFetched = DecryptedCombine()
    var combineTravelFetched = DecryptedCombineTravel()
    var combineContactsFetched = DecryptedCombineContacts()
    var combineEducationFetched = DecryptedCombineEducation()
    var combineInterestsFetched = DecryptedCombineInterests()
    var combinePersonalFetched = DecryptedCombinePersonal()
    var combineWellnessFetched = DecryptedCombineWellness()
    var combineMemoriesFetched = DecryptedCombineMemories()
    var combineShoppingFetched = DecryptedCombineShopping()

    var combineListArray = ArrayList<DecryptedHomeList>()
    var combineTravelListArray = ArrayList<DecryptedTravelList>()
    var combineContactListArray = ArrayList<DecryptedContactsList>()
    var combineEducationListArray = ArrayList<DecryptedEducationList>()
    var combinePersonalListArray = ArrayList<DecryptedPersonalList>()
    var combineInterestListArray = ArrayList<DecryptedInterestsList>()
    var combineWellnessListArray = ArrayList<DecryptedWellnessList>()
    var combineMemoriesListArray = ArrayList<DecryptedMemoriesList>()
    var combineShoppingListArray = ArrayList<DecryptedShoppingList>()

    override fun homeListCount(contactsUpdating: Long, decryptCombine: DecryptedCombine) {
        this.combineFetched = decryptCombine
    }

    override fun travelListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineTravel) {
        this.combineTravelFetched = decryptCombine
    }

    override fun contactListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineContacts) {
        this.combineContactsFetched = decryptCombine
    }

    override fun educationListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineEducation) {
        this.combineEducationFetched = decryptCombine
    }

    override fun interestListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineInterests) {
        this.combineInterestsFetched = decryptCombine
    }

    override fun countPersonalList(contactsUpdating: Long, decryptCombine: DecryptedCombinePersonal) {
        this.combinePersonalFetched = decryptCombine
    }

    override fun wellnessListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineWellness) {
        this.combineWellnessFetched = decryptCombine
    }

    override fun memoryListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineMemories) {
        this.combineMemoriesFetched = decryptCombine
    }

    override fun shoppingListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineShopping) {
        this.combineShoppingFetched = decryptCombine
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_finder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ListsPresenter(this)

        fromWhichBox = arguments!!.getInt("category")
        navigateToParticularList(fromWhichBox)

    }

    private fun navigateToParticularList(fromWhichBox: Int?) {
        when (fromWhichBox) {
            R.string.home_amp_money -> {
                for (listItems in combineFetched.listItems) {
                    combineListArray.add(listItems)
                }
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.home_amp_money))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putSerializable("combineListItemsFetched", combineListArray)
                val categoryFragment = SubListsFragment()
                AppLogger.d("CombineListArray", " " + combineListArray)
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()

            }
            R.string.travel -> {
                for (listItems in combineTravelFetched.listItems) {
                    combineTravelListArray.add(listItems)
                }
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.travel))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putSerializable("combineListItemsFetched", combineTravelListArray)
                val categoryFragment = TravelListFragment()
                AppLogger.d("combineTravelListArray", " " + combineTravelListArray)
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.contacts -> {
                for (listItems in combineContactsFetched.listItems) {
                    combineContactListArray.add(listItems)
                }
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.contacts))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putSerializable("combineListItemsFetched", combineContactListArray)
                val categoryFragment = ContactsListFragment()
                AppLogger.d("combineContactListArray", " " + combineContactListArray)
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.education_work -> {
                for (listItems in combineEducationFetched.listItems) {
                    combineEducationListArray.add(listItems)
                }

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.education_work))

                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putSerializable("combineListItemsFetched", combineEducationListArray)
                val categoryFragment = EducationListFragment()
                AppLogger.d("combineEducationListArray", " " + combineEducationListArray)
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.personal -> {
                for (listItems in combinePersonalFetched.listItems) {
                    combinePersonalListArray.add(listItems)
                }
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.personal))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putSerializable("combineListItemsFetched", combinePersonalListArray)
                val categoryFragment = PersonalListFragment()
                AppLogger.d("combinePersonalListArray", " " + combinePersonalListArray)
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.interests -> {
                for (listItems in combineInterestsFetched.listItems) {
                    combineInterestListArray.add(listItems)
                }
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.interests))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putSerializable("combineListItemsFetched", combineInterestListArray)
                val categoryFragment = InterestListFragment()
                AppLogger.d("combineInterestListArray", " " + combineInterestListArray)
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.wellness -> {
                for (listItems in combineWellnessFetched.listItems) {
                    combineWellnessListArray.add(listItems)
                }
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.wellness))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putSerializable("combineListItemsFetched", combineWellnessListArray)
                val categoryFragment = WellnessListFragment()
                AppLogger.d("combineWellnessListArray", " " + combineWellnessListArray)
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.memories -> {
                for (listItems in combineMemoriesFetched.listItems) {
                    combineMemoriesListArray.add(listItems)
                }
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.memories))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putSerializable("combineListItemsFetched", combineMemoriesListArray)
                val categoryFragment = MemoriesListFragment()
                AppLogger.d("combineMemoriesListArray", " " + combineMemoriesListArray)
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.shopping -> {
                for (listItems in combineShoppingFetched.listItems) {
                    combineShoppingListArray.add(listItems)
                }
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.shopping))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putSerializable("combineListItemsFetched", combineShoppingListArray)
                val categoryFragment = ShoppingListFragment()
                AppLogger.d("combineShoppingListArray", " " + combineShoppingListArray)
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
        }
    }

    override fun onBackPressed(): Boolean {
//        NineBxApplication.instance.activityInstance!!.changeToolbarTitle()
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.hideQuickAdd()
        return super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
    }
}