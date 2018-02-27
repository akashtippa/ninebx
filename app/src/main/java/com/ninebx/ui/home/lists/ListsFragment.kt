package com.ninebx.ui.home.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showToast

import com.ninebx.ui.base.realm.lists.*
import com.ninebx.ui.home.BaseHomeFragment
import io.realm.RealmResults

import kotlinx.android.synthetic.main.fragment_lists.*

/**
 * Created by Alok on 03/01/18.
 */
class ListsFragment : BaseHomeFragment(), ListsCommunicationView {

    override fun homeListCount(contactsUpdating: Int, decryptCombine: RealmResults<HomeList>?) {
        txtHomeNumber.text = contactsUpdating.toString()
        combineFetched!!.clear()
        this.combineFetched!!.addAll(decryptCombine!!.asIterable())
    }

    override fun travelListCount(contactsUpdating: Int, decryptCombine: RealmResults<TravelList>) {
        txtTravelNumber.text = contactsUpdating.toString()
        combineTravelFetched!!.clear()
        this.combineTravelFetched!!.addAll(decryptCombine.asIterable())
    }

    override fun contactListCount(contactsUpdating: Int, decryptCombine: RealmResults<ContactsList>) {
        txtContactNumber.text = contactsUpdating.toString()
        combineContactsFetched!!.clear()
        this.combineContactsFetched!!.addAll(decryptCombine.asIterable())
    }

    override fun educationListCount(contactsUpdating: Int, decryptCombine: RealmResults<EducationList>) {
        txtEducationNumber.text = contactsUpdating.toString()
        combineEducationFetched!!.clear()
        this.combineEducationFetched!!.addAll(decryptCombine.asIterable())
    }

    override fun interestListCount(contactsUpdating: Int, decryptCombine: RealmResults<InterestsList>) {
        txtInterestsNumber.text = contactsUpdating.toString()
        combineInterestsFetched!!.clear()
        this.combineInterestsFetched!!.addAll(decryptCombine.asIterable())
    }

    override fun countPersonalList(contactsUpdating: Int, decryptCombine: RealmResults<PersonalList>) {
        txtPersonalNumber.text = contactsUpdating.toString()
        combinePersonalFetched!!.clear()
        this.combinePersonalFetched!!.addAll(decryptCombine.asIterable())
    }

    override fun wellnessListCount(contactsUpdating: Int, decryptCombine: RealmResults<WellnessList>) {
        txtWellnessNumber.text = contactsUpdating.toString()
        combineWellnessFetched!!.clear()
        this.combineWellnessFetched!!.addAll(decryptCombine.asIterable())
    }

    override fun memoryListCount(contactsUpdating: Int, decryptCombine: RealmResults<MemoriesList>) {
        txtMemoriesNumber.text = contactsUpdating.toString()
        combineMemoriesFetched!!.clear()
        this.combineMemoriesFetched!!.addAll(decryptCombine.asIterable())
    }

    override fun shoppingListCount(contactsUpdating: Int, decryptCombine: RealmResults<ShoppingList>) {
        txtShoppingNumber.text = contactsUpdating.toString()
        combineShoppingFetched!!.clear()
        this.combineShoppingFetched!!.addAll(decryptCombine.asIterable())
    }

    val combineFetched: ArrayList<HomeList>? = ArrayList()
    val combineTravelFetched: ArrayList<TravelList>? = ArrayList()
    val combineContactsFetched: ArrayList<ContactsList>? = ArrayList()
    val combineEducationFetched: ArrayList<EducationList>? = ArrayList()
    val combineInterestsFetched: ArrayList<InterestsList>? = ArrayList()
    val combinePersonalFetched: ArrayList<PersonalList>? = ArrayList()
    val combineWellnessFetched: ArrayList<WellnessList>? = ArrayList()
    val combineMemoriesFetched: ArrayList<MemoriesList>? = ArrayList()
    val combineShoppingFetched: ArrayList<ShoppingList>? = ArrayList()


    override fun showProgress(message: Int) {
        if (progressLayout != null)
            progressLayout.show()
    }

    override fun hideProgress() {
        if (progressLayout != null)
            progressLayout.hide()
    }

    override fun onError(error: Int) {
        if (context != null) context!!.showToast(error)
        hideProgress()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }

    var fragmentValue = ""
    var categoryName = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ListsPresenter(this, 0, -1)


        fragmentValue = arguments!!.getString("homeScreen")
        categoryName = arguments!!.getInt("categoryName")

        when (fragmentValue) {
            "HomeScreen" -> {
                switchToDirectSubList(categoryName)
            }
        }

        val categoryFragment = SubListsFragment()

        layHome.setOnClickListener {

            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.home_amp_money))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putInt("categoryName", (R.string.home_amp_money))
            bundle.putString("listOption", "Home")
            bundle.putString("homeScreen", "bottom")
            categoryFragment.setCombine(combineFetched)

            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layTravel.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.travel))
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putInt("categoryName", (R.string.travel))
            bundle.putString("homeScreen", "bottom")
            bundle.putString("listOption", "Travel")
            categoryFragment.setCombineFetched(combineTravelFetched)
            //val categoryFragment = TravelListFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layContact.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.contacts))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putInt("categoryName", (R.string.contacts))
            bundle.putString("homeScreen", "bottom")
            bundle.putString("listOption", "Contacts")
            categoryFragment.setCombineContacts(combineContactsFetched)
            //val categoryFragment = ContactsListFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layEducation.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.education_work))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putInt("categoryName", (R.string.education_work))
            bundle.putString("homeScreen", "bottom")
            bundle.putString("listOption", "Education")
            categoryFragment.setCombineEduction(combineEducationFetched)
            //val categoryFragment = EducationListFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layPersonal.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.personal))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putInt("categoryName", (R.string.personal))
            bundle.putString("homeScreen", "bottom")
            bundle.putString("listOption", "Personal")
            categoryFragment.setCombinePersonal(combinePersonalFetched)
            //val categoryFragment = PersonalListFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layInterests.setOnClickListener {

            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.interests))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putInt("categoryName", (R.string.interests))
            bundle.putString("homeScreen", "bottom")
            bundle.putString("listOption", "Interests")
            categoryFragment.setCombineInterests(combineInterestsFetched)
            //val categoryFragment = InterestListFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layWellness.setOnClickListener {

            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.wellness))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putString("homeScreen", "bottom")
            bundle.putString("listOption", "Wellness")
            bundle.putInt("categoryName", (R.string.wellness))
            categoryFragment.setCombineWellness(combineWellnessFetched)
            //val categoryFragment = WellnessListFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layMemories.setOnClickListener {

            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.memories))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putString("homeScreen", "bottom")
            bundle.putString("listOption", "Memories")
            bundle.putInt("categoryName", (R.string.memories))
            categoryFragment.setCombineMemories(combineMemoriesFetched)
            //val categoryFragment = MemoriesListFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layShopping.setOnClickListener {

            NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.shopping))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putString("listOption", "Shopping")
            bundle.putInt("categoryName", (R.string.shopping))
            bundle.putString("homeScreen", "bottom")
            categoryFragment.setCombineShopping(combineShoppingFetched)
            //val categoryFragment = ShoppingListFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }
    }

    private fun switchToDirectSubList(whichBox: Int) {
        val categoryFragment = SubListsFragment()

        when (whichBox) {
            R.string.home_amp_money -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.home_amp_money))
                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putInt("categoryName", (R.string.home_amp_money))
                bundle.putString("listOption", "Home")
                bundle.putString("homeScreen", "HomeScreen")
                categoryFragment.setCombine(combineFetched)

                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }

            R.string.travel -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.travel))
                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putInt("categoryName", (R.string.travel))
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Travel")
                categoryFragment.setCombineFetched(combineTravelFetched)
                //val categoryFragment = TravelListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.contacts -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.contacts))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putInt("categoryName", (R.string.contacts))
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Contacts")
                categoryFragment.setCombineContacts(combineContactsFetched)
                //val categoryFragment = ContactsListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.education_work -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.education_work))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putInt("categoryName", (R.string.education_work))
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Education")
                categoryFragment.setCombineEduction(combineEducationFetched)
                //val categoryFragment = EducationListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.personal -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.personal))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putInt("categoryName", (R.string.personal))
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Personal")
                categoryFragment.setCombinePersonal(combinePersonalFetched)
                //val categoryFragment = PersonalListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.interests -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.interests))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putInt("categoryName", (R.string.interests))
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Interests")
                categoryFragment.setCombineInterests(combineInterestsFetched)
                //val categoryFragment = InterestListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.wellness -> {

                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.wellness))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Wellness")
                bundle.putInt("categoryName", (R.string.wellness))
                categoryFragment.setCombineWellness(combineWellnessFetched)
                //val categoryFragment = WellnessListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.memories -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.memories))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Memories")
                bundle.putInt("categoryName", (R.string.memories))
                categoryFragment.setCombineMemories(combineMemoriesFetched)
                //val categoryFragment = MemoriesListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.shopping -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.shopping))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val bundle = Bundle()
                bundle.putString("listOption", "Shopping")
                bundle.putInt("categoryName", (R.string.shopping))
                bundle.putString("homeScreen", "HomeScreen")
                categoryFragment.setCombineShopping(combineShoppingFetched)
                //val categoryFragment = ShoppingListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
        }
    }


}