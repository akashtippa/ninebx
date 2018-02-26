package com.ninebx.ui.home.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.progressDialog
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.home.BaseHomeFragment
import com.ninebx.ui.home.lists.contacts.ContactsListFragment
import com.ninebx.ui.home.lists.education.EducationListFragment
import com.ninebx.ui.home.lists.interests.InterestListFragment
import com.ninebx.ui.home.lists.memories.MemoriesListFragment
import com.ninebx.ui.home.lists.personal.PersonalListFragment
import com.ninebx.ui.home.lists.shopping.ShoppingListFragment
import com.ninebx.ui.home.lists.travel.TravelListFragment
import com.ninebx.ui.home.lists.wellness.WellnessListFragment
import com.ninebx.utility.AppLogger

import kotlinx.android.synthetic.main.fragment_lists.*

/**
 * Created by Alok on 03/01/18.
 */
class ListsFragment : BaseHomeFragment(), ListsCommunicationView {

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
        txtHomeNumber.text = contactsUpdating.toString()
        this.combineFetched = decryptCombine
    }

    override fun travelListCount(contactsUpdating: Long, decryptCombineTravel: DecryptedCombineTravel) {
        txtTravelNumber.text = contactsUpdating.toString()
        this.combineTravelFetched = decryptCombineTravel
    }

    override fun contactListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineContacts) {
        txtContactNumber.text = contactsUpdating.toString()
        this.combineContactsFetched = decryptCombine
    }

    override fun educationListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineEducation) {
        txtEducationNumber.text = contactsUpdating.toString()
        this.combineEducationFetched = decryptCombine
    }

    override fun interestListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineInterests) {
        txtInterestsNumber.text = contactsUpdating.toString()
        this.combineInterestsFetched = decryptCombine
    }

    override fun countPersonalList(contactsUpdating: Long, decryptCombine: DecryptedCombinePersonal) {
        txtPersonalNumber.text = contactsUpdating.toString()
        this.combinePersonalFetched = decryptCombine
    }

    override fun wellnessListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineWellness) {
        txtWellnessNumber.text = contactsUpdating.toString()
        this.combineWellnessFetched = decryptCombine
    }

    override fun memoryListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineMemories) {
        txtMemoriesNumber.text = contactsUpdating.toString()
        this.combineMemoriesFetched = decryptCombine
    }

    override fun shoppingListCount(contactsUpdating: Long, decryptCombine: DecryptedCombineShopping) {
        txtShoppingNumber.text = contactsUpdating.toString()
        this.combineShoppingFetched = decryptCombine
    }


    override fun showProgress(message: Int) {}

    override fun hideProgress() {
        progressDialog!!.cancel()
    }

    override fun onError(error: Int) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ListsPresenter(this)

        layHome.setOnClickListener {
            for (listItems in combineFetched.listItems) {
                combineListArray.add(listItems)
            }
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.home_amp_money))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putString("homeScreen", "bottom")
            bundle.putSerializable("combineListItemsFetched", combineListArray)
            val categoryFragment = SubListsFragment()
            AppLogger.d("CombineListArray", " " + combineListArray)
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()

        }

        layTravel.setOnClickListener {
            for (listItems in combineTravelFetched.listItems) {
                combineTravelListArray.add(listItems)
            }
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.travel))

            AppLogger.d("CombineTravel", " " + combineTravelListArray)
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putString("homeScreen", "bottom")
            bundle.putSerializable("combineListItemsFetched", combineTravelListArray)
            val categoryFragment = TravelListFragment()
            AppLogger.d("combineTravelListArray", " " + combineTravelListArray)
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layContact.setOnClickListener {
            for (listItems in combineContactsFetched.listItems) {
                combineContactListArray.add(listItems)
            }
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.contacts))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putString("homeScreen", "bottom")
            bundle.putSerializable("combineListItemsFetched", combineContactListArray)
            val categoryFragment = ContactsListFragment()
            AppLogger.d("combineContactListArray", " " + combineContactListArray)
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layEducation.setOnClickListener {
            for (listItems in combineEducationFetched.listItems) {
                combineEducationListArray.add(listItems)
            }
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.education_work))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putString("homeScreen", "bottom")
            bundle.putSerializable("combineListItemsFetched", combineEducationListArray)
            val categoryFragment = EducationListFragment()
            AppLogger.d("combineEducationListArray", " " + combineEducationListArray)
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layPersonal.setOnClickListener {
            for (listItems in combinePersonalFetched.listItems) {
                combinePersonalListArray.add(listItems)
            }
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.personal))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putString("homeScreen", "bottom")
            bundle.putSerializable("combineListItemsFetched", combinePersonalListArray)
            val categoryFragment = PersonalListFragment()
            AppLogger.d("combinePersonalListArray", " " + combinePersonalListArray)
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layInterests.setOnClickListener {
            for (listItems in combineInterestsFetched.listItems) {
                combineInterestListArray.add(listItems)
            }
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.interests))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putString("homeScreen", "bottom")
            bundle.putSerializable("combineListItemsFetched", combineInterestListArray)
            val categoryFragment = InterestListFragment()
            AppLogger.d("combineInterestListArray", " " + combineInterestListArray)
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layWellness.setOnClickListener {
            for (listItems in combineWellnessFetched.listItems) {
                combineWellnessListArray.add(listItems)
            }
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.wellness))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putString("homeScreen", "bottom")
            bundle.putSerializable("combineListItemsFetched", combineWellnessListArray)
            val categoryFragment = WellnessListFragment()
            AppLogger.d("combineWellnessListArray", " " + combineWellnessListArray)
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layMemories.setOnClickListener {
            for (listItems in combineMemoriesFetched.listItems) {
                combineMemoriesListArray.add(listItems)
            }
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.memories))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putString("homeScreen", "bottom")
            bundle.putSerializable("combineListItemsFetched", combineMemoriesListArray)
            val categoryFragment = MemoriesListFragment()
            AppLogger.d("combineMemoriesListArray", " " + combineMemoriesListArray)
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layShopping.setOnClickListener {
            for (listItems in combineShoppingFetched.listItems) {
                combineShoppingListArray.add(listItems)
            }
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.shopping))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putString("homeScreen", "bottom")
            bundle.putSerializable("combineListItemsFetched", combineShoppingListArray)
            val categoryFragment = ShoppingListFragment()
            AppLogger.d("combineShoppingListArray", " " + combineShoppingListArray)
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }
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
            }
            getString(R.string.travel) -> {
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