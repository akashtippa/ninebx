package com.ninebx.ui.home.lists

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.R.string.contacts
import com.ninebx.R.string.created
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.SearchItemClickListener
import com.ninebx.ui.base.realm.decrypted.DecryptedHomeList
import com.ninebx.ui.base.realm.home.contacts.Contacts

import com.ninebx.ui.base.realm.lists.*
import com.ninebx.ui.home.adapter.Date
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.home.lists.helper.SwipeToDeleteCallback
import com.ninebx.ui.home.lists.model.AddedItem
import com.ninebx.ui.home.search.Level3SearchItem
import com.ninebx.ui.home.search.SearchAdapter
import com.ninebx.utility.*
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_sub_list.*
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Alok on 03/01/18.
 */
class SubListsFragment : FragmentBackHelper(), SearchItemClickListener {


    var strAddItem = ""
    var fragmentValue = ""
    var listOption = ""
    var categoryName = 0
    var createdDate = ""

    private lateinit var mListsAdapter: SearchAdapter
    private var searchItems: ArrayList<Level3SearchItem> = ArrayList()
    private var contactsRealm: Realm? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sub_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvAddedLists!!.layoutManager = layoutManager
        mListsAdapter = SearchAdapter(searchItems, this)
        rvAddedLists!!.adapter = mListsAdapter

        listOption = arguments!!.getString("listOption")
        fragmentValue = arguments!!.getString("homeScreen")
        categoryName = arguments!!.getInt("categoryName")

        NineBxApplication.instance.activityInstance!!.hideBottomView()

        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rvAddedLists.adapter as SearchAdapter
                adapter.removeAt(viewHolder.adapterPosition)
                val snackBar = Snackbar.make(view, "Item Deleted", Snackbar.LENGTH_LONG)
                snackBar.setAction("UNDO", View.OnClickListener {
                    // undo is selected, restore the deleted item
                    val mLog = AddedItem()
                    mLog.strAddedItem = strAddItem
                    //getArrayList.add(mLog)
                    mListsAdapter!!.notifyDataSetChanged()

                })
                snackBar.setActionTextColor(Color.YELLOW)
                snackBar.show()

            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rvAddedLists)

        txtDone.setOnClickListener {
            strAddItem = edtAddList.text.toString()
            txtDone.hide()

            if (strAddItem == "") {
                Toast.makeText(context, getString(R.string.please_enter_title_of_the_list), Toast.LENGTH_SHORT).show()
                edtAddList.clearFocus()
                KeyboardUtil.hideSoftKeyboard(activity!!)
            } else {
                val mLog = AddedItem()
                mLog.strAddedItem = strAddItem
                mListsAdapter!!.notifyDataSetChanged()
                KeyboardUtil.hideSoftKeyboard(activity!!)
                aadToParticularRealmList(categoryName)
                edtAddList.text.clear()
            }
        }

        // As soon as user starts typing, I've to show Done option to the end of the EditText
        edtAddList.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.trim().isEmpty()) {
                    txtDone.hide()
                } else {
                    txtDone.show()
                }
            }
        })

    }

    private fun savingTheCreatedTime() {
        val firstName = preferences.userFirstName
        val lastName = preferences.userLastName

        val fullName = firstName + lastName
        val selectedDate = Calendar.getInstance()
        val mSelectedDate: java.util.Date

        selectedDate.timeInMillis = java.util.Date().time
        mSelectedDate = selectedDate.time

        val date = parseDateForCreatedUser(mSelectedDate)
        val created = fullName + ", " + date
        createdDate = created
    }

    val preferences = NineBxPreferences()
    private fun aadToParticularRealmList(categoryName: Int) {
        when (categoryName) {
            R.string.home_amp_money -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = HomeList()
                        listItems.id = getUniqueId()
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "HomeBanking".encryptString()
                        listItems.created = createdDate
                        //AppLogger.e("Created Date ", " is " + preferences.created)

                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.travel -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = TravelList()
                        listItems.id = getUniqueId()
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Travel".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.contacts -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = ContactsList()
                        listItems.id = getUniqueId()
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Contacts".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.education_work -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_EDUCATION, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = EducationList()
                        listItems.id = getUniqueId()
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Education".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.personal -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = PersonalList()
                        listItems.id = getUniqueId()
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Personal".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.interests -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_INTERESTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = InterestsList()
                        listItems.id = getUniqueId()
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Interests".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.wellness -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = WellnessList()
                        listItems.id = getUniqueId()
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Wellness".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.memories -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = MemoriesList()
                        listItems.id = getUniqueId()
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Memories".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.shopping -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {

                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = ShoppingList()
                        listItems.id = getUniqueId()
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Shopping".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)
                    }

                })

            }


        }
    }

    override fun onBackPressed(): Boolean {

        if (fragmentValue == "HomeScreen") {
            NineBxApplication.instance.activityInstance!!.showBottomView()
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
            val fm = activity!!.supportFragmentManager
            val transaction = fm.beginTransaction()
            transaction.remove(this@SubListsFragment)
            transaction.commit()

            // For now used the creepy way to manage things.
            if (preferences.forTestingBackPress.equals("Home")) {
                fm.popBackStack()
                fm.popBackStack()
            } else {
                fm.popBackStack()
            }
            val preferences = NineBxPreferences()
            val toolbarTitle = preferences.currentBox
            //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(toolbarTitle.toString())

        } else if (fragmentValue == "bottom") {
            //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.lists))
            NineBxApplication.instance.activityInstance!!.showBottomView()

        }
        return super.onBackPressed()
    }

    private var combineFetched: ArrayList<DecryptedHomeList>? = null

    fun setCombine(combineFetched: ArrayList<DecryptedHomeList>?) {
        this.combineFetched = combineFetched
        searchItems.clear()
        for (item in combineFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName))
        }
    }

    private var combineTravelFetched: ArrayList<DecryptedTravelList>? = null


    fun setCombineFetched(combineFetched: ArrayList<DecryptedTravelList>?) {
        this.combineTravelFetched = combineFetched
        searchItems.clear()
        for (item in combineFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName))
        }

    }

    private var combineContactsFetched: ArrayList<DecryptedContactsList>? = null

    fun setCombineContacts(combineContactsFetched: ArrayList<DecryptedContactsList>?) {
        this.combineContactsFetched = combineContactsFetched
        searchItems.clear()
        for (item in combineContactsFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName))
        }

    }

    private var combineEducationFetched: ArrayList<DecryptedEducationList>? = null

    fun setCombineEduction(combineEducationFetched: ArrayList<DecryptedEducationList>?) {
        this.combineEducationFetched = combineEducationFetched
        searchItems.clear()
        for (item in combineEducationFetched!!) {

            searchItems.add(Level3SearchItem(categoryName, item.listName))
        }

    }

    private var combinePersonalFetched: ArrayList<DecryptedPersonalList>? = null

    fun setCombinePersonal(combinePersonalFetched: ArrayList<DecryptedPersonalList>?) {
        this.combinePersonalFetched = combinePersonalFetched
        searchItems.clear()
        for (item in combinePersonalFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName))
        }

    }

    private var combineInterestsFetched: ArrayList<DecryptedInterestsList>? = null

    fun setCombineInterests(combineInterestsFetched: ArrayList<DecryptedInterestsList>?) {
        this.combineInterestsFetched = combineInterestsFetched
        searchItems.clear()
        for (item in combineInterestsFetched!!) {

            searchItems.add(Level3SearchItem(categoryName, item.listName))
        }

    }

    private var combineWellnessFetched: ArrayList<DecryptedWellnessList>? = null

    fun setCombineWellness(combineWellnessFetched: ArrayList<DecryptedWellnessList>?) {
        this.combineWellnessFetched = combineWellnessFetched
        searchItems.clear()
        for (item in combineWellnessFetched!!) {

            searchItems.add(Level3SearchItem(categoryName, item.listName))
        }

    }

    private var combineMemoriesFetched: ArrayList<DecryptedMemoriesList>? = null

    fun setCombineMemories(combineMemoriesFetched: ArrayList<DecryptedMemoriesList>?) {
        this.combineMemoriesFetched = combineMemoriesFetched
        searchItems.clear()
        for (item in combineMemoriesFetched!!) {

            searchItems.add(Level3SearchItem(categoryName, item.listName))
        }

    }

    private var combineShoppingFetched: ArrayList<DecryptedShoppingList>? = null

    fun setCombineShopping(combineShoppingFetched: ArrayList<DecryptedShoppingList>?) {
        this.combineShoppingFetched = combineShoppingFetched
        searchItems.clear()
        for (item in combineShoppingFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName))
        }

    }

    override fun onItemClick(itemPosition: Int, position: Int, searchItem: Level3SearchItem) {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        // For now managing in a bad way, just to make it work.
        if (fragmentValue == "HomeScreen") {
            preferences.forTestingBackPress = "Home"
        } else {
            preferences.forTestingBackPress = "Bottom"
        }
        val superSubListFragment = SuperSubListFragment()

        val bundle = Bundle()
        bundle.putInt("categoryName", categoryName)
        superSubListFragment.arguments = bundle

        when (categoryName) {
            R.string.home_amp_money -> {
                val listItem = combineFetched!![itemPosition]
                superSubListFragment.setHomeList(listItem)
            }
            R.string.travel -> {
                val listItem = combineTravelFetched!![itemPosition]
                superSubListFragment.setTravelList(listItem)
            }
            R.string.contacts -> {
                val listItem = combineContactsFetched!![itemPosition]
                superSubListFragment.setContactsList(listItem)
            }
            R.string.education_work -> {
                val listItem = combineEducationFetched!![itemPosition]
                superSubListFragment.setEducationList(listItem)
            }
            R.string.personal -> {
                val listItem = combinePersonalFetched!![itemPosition]
                superSubListFragment.setPersonalList(listItem)
            }
            R.string.interests -> {
                val listItem = combineInterestsFetched!![itemPosition]
                superSubListFragment.setInterestsList(listItem)
            }
            R.string.wellness -> {
                val listItem = combineWellnessFetched!![itemPosition]
                superSubListFragment.setWellnessList(listItem)
            }
            R.string.memories -> {
                val listItem = combineMemoriesFetched!![itemPosition]
                superSubListFragment.setMemoriesList(listItem)
            }
            R.string.shopping -> {
                val listItem = combineShoppingFetched!![itemPosition]
                superSubListFragment.setShoppingList(listItem)
            }
        }
        fragmentTransaction.replace(R.id.frameLayout, superSubListFragment).commit()
    }

    override fun onResume() {
        super.onResume()
    }
}

