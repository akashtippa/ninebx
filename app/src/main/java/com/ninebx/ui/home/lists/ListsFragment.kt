package com.ninebx.ui.home.lists

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.decrypted.*

import com.ninebx.ui.home.BaseHomeFragment
import com.ninebx.utility.Constants

import kotlinx.android.synthetic.main.fragment_lists.*

/**
 * Created by Alok on 03/01/18.
 */
class ListsFragment : BaseHomeFragment(), ListsCommunicationView {

    override fun homeListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedHomeList>?) {
        txtHomeNumber.text = contactsUpdating.toString()
        combineFetched!!.clear()
        this.combineFetched!!.addAll(decryptCombine!!.toList())
    }

    override fun travelListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedTravelList>) {
        txtTravelNumber.text = contactsUpdating.toString()
        combineTravelFetched!!.clear()
        this.combineTravelFetched!!.addAll(decryptCombine.toList())
    }

    override fun contactListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedContactsList>) {
        txtContactNumber.text = contactsUpdating.toString()
        combineContactsFetched!!.clear()
        this.combineContactsFetched!!.addAll(decryptCombine.toList())
    }

    override fun educationListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedEducationList>) {
        txtEducationNumber.text = contactsUpdating.toString()
        combineEducationFetched!!.clear()
        this.combineEducationFetched!!.addAll(decryptCombine.toList())
    }

    override fun interestListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedInterestsList>) {
        txtInterestsNumber.text = contactsUpdating.toString()
        combineInterestsFetched!!.clear()
        this.combineInterestsFetched!!.addAll(decryptCombine.toList())
    }

    override fun countPersonalList(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedPersonalList>) {
        txtPersonalNumber.text = contactsUpdating.toString()
        combinePersonalFetched!!.clear()
        this.combinePersonalFetched!!.addAll(decryptCombine.toList())
    }

    override fun wellnessListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedWellnessList>) {
        txtWellnessNumber.text = contactsUpdating.toString()
        combineWellnessFetched!!.clear()
        this.combineWellnessFetched!!.addAll(decryptCombine.toList())
    }

    override fun memoryListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedMemoriesList>) {
        txtMemoriesNumber.text = contactsUpdating.toString()
        combineMemoriesFetched!!.clear()
        this.combineMemoriesFetched!!.addAll(decryptCombine.toList())
    }

    override fun shoppingListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedShoppingList>) {
        txtShoppingNumber.text = contactsUpdating.toString()
        combineShoppingFetched!!.clear()
        this.combineShoppingFetched!!.addAll(decryptCombine.toList())
    }

    val combineFetched: ArrayList<DecryptedHomeList>? = ArrayList()
    val combineTravelFetched: ArrayList<DecryptedTravelList>? = ArrayList()
    val combineContactsFetched: ArrayList<DecryptedContactsList>? = ArrayList()
    val combineEducationFetched: ArrayList<DecryptedEducationList>? = ArrayList()
    val combineInterestsFetched: ArrayList<DecryptedInterestsList>? = ArrayList()
    val combinePersonalFetched: ArrayList<DecryptedPersonalList>? = ArrayList()
    val combineWellnessFetched: ArrayList<DecryptedWellnessList>? = ArrayList()
    val combineMemoriesFetched: ArrayList<DecryptedMemoriesList>? = ArrayList()
    val combineShoppingFetched: ArrayList<DecryptedShoppingList>? = ArrayList()


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
    var combinedItem: Parcelable ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        ivHome.setOnClickListener { NineBxApplication.instance.activityInstance!!.callHomeFragment() }
        ListsPresenter(this, 0, -1).fetchDataInBackground()

        //val listOp = arguments!!.getString("listOption")
        fragmentValue = arguments!!.getString("homeScreen")
        categoryName = arguments!!.getInt("categoryName")
        combinedItem = arguments!!.getParcelable(Constants.COMBINE_ITEMS)
        val fromWhichBox = arguments!!.getInt("category")

        if(fromWhichBox != null)
        switchToDirectSubList(fromWhichBox, combinedItem!!)

        /*when (listOp) {
            "Home" -> {
                switchToDirectSubList(fromWhichBox, combinedItem!!)
            }
            "Travel" -> {
                switchToDirectSubList(categoryName, combinedItem!!)
            }
            "Contacts" -> {
                switchToDirectSubList(R.string.contacts, combinedItem!!)
            }
            "Education" -> {
                switchToDirectSubList(R.string.contacts, combinedItem!!)
            }
        }*/

        val categoryFragment = SubListsFragment()

        layHome.setOnClickListener {

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
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

            //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.travel))
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
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
            //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.contacts))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
            NineBxApplication.instance.activityInstance!!.hideBottomView()

            val bundle = Bundle()
            bundle.putInt("categoryName", (R.string.contacts))
            bundle.putString("homeScreen", "bottom")
            bundle.putString("listOption", "Contacts")
            categoryFragment.setCombineContacts(combineContactsFetched!!)
            //val categoryFragment = ContactsListFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layEducation.setOnClickListener {
            //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.education_work))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
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
            //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.personal))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
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

            //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.interests))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
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

            //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.wellness))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
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

            //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.memories))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
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

            //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.shopping))

            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
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

    private fun switchToDirectSubList(whichBox: Int, combineItem: Parcelable) {
        val categoryFragment = SubListsFragment()

        when (whichBox) {
            R.string.home_amp_money -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.home_amp_money))
                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.hideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()
                val combineHome = combineItem as DecryptedCombine
                val bundle = Bundle()
                bundle.putInt("categoryName", (R.string.home_amp_money))
                bundle.putString("listOption", "Home")
                bundle.putString("homeScreen", "HomeScreen")
                categoryFragment.setCombine(combineHome.listItems)

                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }

            R.string.travel -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.travel))
                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.hideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()
                val combineTravel = combineItem as DecryptedCombineTravel
                val bundle = Bundle()
                bundle.putInt("categoryName", (R.string.travel))
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Travel")
                categoryFragment.setCombineFetched(combineTravel.listItems)
                //val categoryFragment = TravelListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.contacts -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.contacts))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.hideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()
                val combineContacts = combineItem as DecryptedCombineContacts
                /*combineContactsFetched!!.clear()
                combineContactsFetched.addAll(combineContacts.listItems)*/
                val bundle = Bundle()
                bundle.putInt("categoryName", (R.string.contacts))
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Contacts")
                categoryFragment.setCombineContacts(combineContacts.listItems)
                //val categoryFragment = ContactsListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.education_work -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.education_work))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.hideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()
                val combineEducation = combineItem as DecryptedCombineEducation
                val bundle = Bundle()
                bundle.putInt("categoryName", (R.string.education_work))
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Education")
                categoryFragment.setCombineEduction(combineEducation.listItems)
                //val categoryFragment = EducationListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.personal -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.personal))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.hideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val combinePersonal = combineItem as DecryptedCombinePersonal
                val bundle = Bundle()
                bundle.putInt("categoryName", (R.string.personal))
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Personal")
                categoryFragment.setCombinePersonal(combinePersonal.listItems)
                //val categoryFragment = PersonalListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.interests -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.interests))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.hideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val combineInterests = combineItem as DecryptedCombineInterests
                val bundle = Bundle()
                bundle.putInt("categoryName", (R.string.interests))
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Interests")
                categoryFragment.setCombineInterests(combineInterests.listItems)
                //val categoryFragment = InterestListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.wellness -> {

                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.wellness))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.hideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val combineWellness = combineItem as DecryptedCombineWellness
                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Wellness")
                bundle.putInt("categoryName", (R.string.wellness))
                categoryFragment.setCombineWellness(combineWellness.listItems)
                //val categoryFragment = WellnessListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.memories -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.memories))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.hideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val combineMemories = combineItem as DecryptedCombineMemories
                val bundle = Bundle()
                bundle.putString("homeScreen", "HomeScreen")
                bundle.putString("listOption", "Memories")
                bundle.putInt("categoryName", (R.string.memories))
                categoryFragment.setCombineMemories(combineMemories.listItems)
                //val categoryFragment = MemoriesListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            R.string.shopping -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.shopping))

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                NineBxApplication.instance.activityInstance!!.hideQuickAdd()
                NineBxApplication.instance.activityInstance!!.hideBottomView()

                val combineShopping = combineItem as DecryptedCombineShopping
                val bundle = Bundle()
                bundle.putString("listOption", "Shopping")
                bundle.putInt("categoryName", (R.string.shopping))
                bundle.putString("homeScreen", "HomeScreen")
                categoryFragment.setCombineShopping(combineShopping.listItems)
                //val categoryFragment = ShoppingListFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
        }
    }
}