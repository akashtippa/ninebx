package com.ninebx.ui.home.lists

import android.os.Bundle
import android.os.Parcelable
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
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.SearchItemClickListener
import com.ninebx.ui.base.realm.decrypted.DecryptedHomeList

import com.ninebx.ui.base.realm.lists.*
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
import com.ninebx.ui.home.lists.helper.SwipeToDeleteCallback
import com.ninebx.ui.home.search.Level3SearchItem
import com.ninebx.ui.home.search.SearchAdapter
import com.ninebx.utility.*
import com.ninebx.utility.Constants.SEARCH_NORMAL
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

    var combinedItems: Parcelable ?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivHome.setOnClickListener { NineBxApplication.instance.activityInstance!!.callHomeFragment() }
        ivBack.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
        }
        categoryName = arguments!!.getInt("categoryName")
        /*combinedItems = arguments!!.getParcelable(Constants.COMBINE_ITEMS)
        if(combinedItems != null) {
            val categoryInt = arguments!!.getInt("categoryName")
            when(categoryInt) {
                R.string.home_amp_money -> {
                    val lists = combinedItems as DecryptedCombine
                    searchItems.clear()
                    for(item in lists.listItems) {
                        searchItems.add(Level3SearchItem(categoryName, item.listName))
                    }
                }
                R.string.travel -> {
                    val lists = combinedItems as DecryptedCombineTravel
                    searchItems.clear()
                    for(item in lists.listItems) {
                        searchItems.add(Level3SearchItem(categoryName, item.listName))
                    }
                }
                R.string.contacts -> {
                    val lists = combinedItems as DecryptedCombineContacts
                    searchItems.clear()
                    for(item in lists.listItems) {
                        searchItems.add(Level3SearchItem(categoryName, item.listName))
                    }
                }
            }
        }*/
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvAddedLists!!.layoutManager = layoutManager
        mListsAdapter = SearchAdapter(searchItems, SEARCH_NORMAL, this)
        rvAddedLists!!.adapter = mListsAdapter

        listOption = arguments!!.getString("listOption")
        fragmentValue = arguments!!.getString("homeScreen")
        toolbarTitle.text = getString(categoryName)
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rvAddedLists.adapter as SearchAdapter
                val id = adapter.getListItemId(viewHolder.adapterPosition)
                adapter.removeAt(viewHolder.adapterPosition)
                /*val snackBar = Snackbar.make(view, "Item Deleted", Snackbar.LENGTH_LONG)
                snackBar.setAction("UNDO", View.OnClickListener {
                    // undo is selected, restore the deleted item
                    val mLog = AddedItem()
                    mLog.strAddedItem = strAddItem
                    //getArrayList.add(mLog)
                    mListsAdapter!!.notifyDataSetChanged()

                })
                snackBar.setActionTextColor(Color.YELLOW)
                snackBar.show()*/
                //delete from realm as well
                deleteItemFromRealm(categoryName,id)
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
                val mLog = Level3SearchItem()
                mLog.itemName = strAddItem
                mLog.listItemId = getUniqueId()
                mListsAdapter.add(mListsAdapter.itemCount,mLog)
                mListsAdapter.notifyDataSetChanged()
                KeyboardUtil.hideSoftKeyboard(activity!!)
                aadToParticularRealmList(categoryName, mLog.listItemId)
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

    private fun getRealmEndPoint(categoryName: Int): String {
        when(categoryName) {
            R.string.home_amp_money -> { return Constants.REALM_END_POINT_COMBINE}
            R.string.travel -> { return Constants.REALM_END_POINT_COMBINE_TRAVEL }
            R.string.contacts -> { return Constants.REALM_END_POINT_COMBINE_CONTACTS }
            R.string.education_work -> { return Constants.REALM_END_POINT_COMBINE_EDUCATION }
            R.string.personal -> { return Constants.REALM_END_POINT_COMBINE_PERSONAL }
            R.string.interests -> { return Constants.REALM_END_POINT_COMBINE_INTERESTS}
            R.string.wellness -> { return Constants.REALM_END_POINT_COMBINE_WELLNESS}
            R.string.memories -> { return Constants.REALM_END_POINT_COMBINE_MEMORIES}
            R.string.shopping -> { return Constants.REALM_END_POINT_COMBINE_SHOPPING }
            else -> {return ""}
        }
    }

    private fun deleteItemFromRealm(categoryName: Int, itemId: Long) {
        val endPoint = getRealmEndPoint(categoryName)
        if(endPoint != "") {
            prepareRealmConnections(context, false, endPoint, object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    realm!!.beginTransaction()
                    when(categoryName) {
                        R.string.home_amp_money -> {
                            realm.where(HomeList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                            realm.where(HomeList::class.java).equalTo("detailsId", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.travel -> {
                            realm.where(TravelList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                            realm.where(TravelList::class.java).equalTo("detailsId", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.contacts -> {
                            realm.where(ContactsList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                            realm.where(ContactsList::class.java).equalTo("detailsId", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.education_work -> {
                            realm.where(EducationList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                            realm.where(EducationList::class.java).equalTo("detailsId", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.personal -> {
                            realm.where(PersonalList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                            realm.where(PersonalList::class.java).equalTo("detailsId", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.interests -> {
                            realm.where(InterestsList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                            realm.where(InterestsList::class.java).equalTo("detailsId", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.wellness -> {
                            realm.where(WellnessList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                            realm.where(WellnessList::class.java).equalTo("detailsId", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.memories -> {
                            realm.where(MemoriesList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                            realm.where(MemoriesList::class.java).equalTo("detailsId", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.shopping -> {
                            realm.where(ShoppingList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                            realm.where(ShoppingList::class.java).equalTo("detailsId", itemId).findAll().deleteAllFromRealm()
                        }
                    }
                    realm.commitTransaction()
                }

            })
        }
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

    val preferences = NineBxApplication.getPreferences()
    private fun aadToParticularRealmList(categoryName: Int, uniqueId: Long) {
        when (categoryName) {
            R.string.home_amp_money -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = HomeList()
                        listItems.id = uniqueId
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "HomeBanking".encryptString()
                        listItems.created = createdDate
                        //AppLogger.e("Created Date ", " is " + preferences.created)
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)

                        val combine = Combine()
                        combine.id = listItems.id
                        combine.listItems.add(listItems)
                        combine.insertOrUpdate(realm)

                        var decryptedHomeList = DecryptedHomeList()
                        decryptedHomeList.listName = strAddItem
                        decryptedHomeList.id = listItems.id
                        decryptedHomeList.detailsId = 0
                        decryptedHomeList.selectionType ="HomeBanking"
                        decryptedHomeList.created = createdDate
                        decryptedHomeList.createdUser = preferences.userID
                        combineFetched?.add(decryptedHomeList)
                    }

                })

            }
            R.string.travel -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = TravelList()
                        listItems.id = uniqueId
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Travel".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)

                        //add to combineTravel
                        val combineTravel = CombineTravel()
                        combineTravel.id = listItems.id
                        combineTravel.listItems.add(listItems)
                        combineTravel.insertOrUpdate(realm)

                        var decryptedTravelList = DecryptedTravelList()
                        decryptedTravelList.listName = strAddItem
                        decryptedTravelList.id = listItems.id
                        decryptedTravelList.detailsId = 0
                        decryptedTravelList.selectionType = "Travel"
                        decryptedTravelList.created = createdDate
                        decryptedTravelList.createdUser = preferences.userID
                        combineTravelFetched?.add(decryptedTravelList)
                    }

                })
            }
            R.string.contacts -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        //add to contactslist
                        contactsRealm = realm
                        var listItems = ContactsList()
                        listItems.id = uniqueId
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Contacts".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)

                        //add to combineContacts
                        val combineContact = CombineContacts()
                        combineContact.id = listItems.id
                        combineContact.listItems.add(listItems)
                        combineContact.insertOrUpdate(realm)


                        var decryptedContactsList = DecryptedContactsList()
                        decryptedContactsList.listName = strAddItem
                        decryptedContactsList.id = listItems.id
                        decryptedContactsList.detailsId = 0
                        decryptedContactsList.selectionType = "Contacts"
                        decryptedContactsList.created = createdDate
                        decryptedContactsList.createdUser = preferences.userID
                        combineContactsFetched?.add(decryptedContactsList)
                    }
                })
            }
            R.string.education_work -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_EDUCATION, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = EducationList()
                        listItems.id = uniqueId
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Education".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)

                        val combineEducation = CombineEducation()
                        combineEducation.id = listItems.id
                        combineEducation.listItems.add(listItems)
                        combineEducation.insertOrUpdate(realm)

                        var decryptedEducation = DecryptedEducationList()
                        decryptedEducation.listName = strAddItem
                        decryptedEducation.id = listItems.id
                        decryptedEducation.detailsId = 0
                        decryptedEducation.selectionType = "Education"
                        decryptedEducation.created = createdDate
                        decryptedEducation.createdUser = preferences.userID
                        combineEducationFetched?.add(decryptedEducation)

                    }

                })

            }
            R.string.personal -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = PersonalList()
                        listItems.id = uniqueId
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Personal".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)

                        val combinePersonal = CombinePersonal()
                        combinePersonal.id = listItems.id
                        combinePersonal.listItems.add(listItems)
                        combinePersonal.insertOrUpdate(realm)

                        var decryptedPersonalList = DecryptedPersonalList()
                        decryptedPersonalList.listName = strAddItem
                        decryptedPersonalList.id = listItems.id
                        decryptedPersonalList.detailsId = 0
                        decryptedPersonalList.selectionType = "Personal"
                        decryptedPersonalList.created = createdDate
                        decryptedPersonalList.createdUser = preferences.userID
                        combinePersonalFetched?.add(decryptedPersonalList)

                    }

                })
            }
            R.string.interests -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_INTERESTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = InterestsList()
                        listItems.id = uniqueId
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Interests".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)

                        val combineInterests = CombineInterests()
                        combineInterests.id = listItems.id
                        combineInterests.listItems.add(listItems)
                        combineInterests.insertOrUpdate(realm)

                        var decryptedInterestsList = DecryptedInterestsList()
                        decryptedInterestsList.listName = strAddItem
                        decryptedInterestsList.id = listItems.id
                        decryptedInterestsList.detailsId = 0
                        decryptedInterestsList.selectionType = "Interests"
                        decryptedInterestsList.created = createdDate
                        decryptedInterestsList.createdUser = preferences.userID
                        combineInterestsFetched?.add(decryptedInterestsList)

                    }

                })

            }
            R.string.wellness -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = WellnessList()
                        listItems.id = uniqueId
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Wellness".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)

                        val combineWellness = CombineWellness()
                        combineWellness.id = listItems.id
                        combineWellness.listItems.add(listItems)
                        combineWellness.insertOrUpdate(realm)

                        var decryptedWellnessList = DecryptedWellnessList()
                        decryptedWellnessList.listName = strAddItem
                        decryptedWellnessList.id = listItems.id
                        decryptedWellnessList.detailsId = 0
                        decryptedWellnessList.selectionType = "Wellness"
                        decryptedWellnessList.created = createdDate
                        decryptedWellnessList.createdUser = preferences.userID
                        combineWellnessFetched?.add(decryptedWellnessList)

                    }

                })

            }
            R.string.memories -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = MemoriesList()
                        listItems.id = uniqueId
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Memories".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)

                        val combineMemories = CombineMemories()
                        combineMemories.id = listItems.id
                        combineMemories.listItems.add(listItems)
                        combineMemories.insertOrUpdate(realm)

                        var decryptedMemoriesList = DecryptedMemoriesList()
                        decryptedMemoriesList.listName = strAddItem
                        decryptedMemoriesList.id = listItems.id
                        decryptedMemoriesList.detailsId = 0
                        decryptedMemoriesList.selectionType = "Memories"
                        decryptedMemoriesList.created = createdDate
                        decryptedMemoriesList.createdUser = preferences.userID
                        combineMemoriesFetched?.add(decryptedMemoriesList)
                    }

                })

            }
            R.string.shopping -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {

                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var listItems = ShoppingList()
                        listItems.id = uniqueId
                        listItems.listName = strAddItem.encryptString()
                        listItems.detailsId = 0
                        listItems.selectionType = "Shopping".encryptString()
                        listItems.created = createdDate
                        listItems.createdUser = preferences.userID
                        listItems.insertOrUpdate(realm!!)

                        val combineShopping = CombineShopping()
                        combineShopping.id = listItems.id
                        combineShopping.listItems.add(listItems)
                        combineShopping.insertOrUpdate(realm)

                        var decryptedShoppingList = DecryptedShoppingList()
                        decryptedShoppingList.listName = strAddItem
                        decryptedShoppingList.id = listItems.id
                        decryptedShoppingList.detailsId = 0
                        decryptedShoppingList.selectionType = "Shopping"
                        decryptedShoppingList.created = createdDate
                        decryptedShoppingList.createdUser = preferences.userID
                        combineShoppingFetched?.add(decryptedShoppingList)
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
            val preferences = NineBxApplication.getPreferences()
            val toolbarTitle = preferences.currentBox
            //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(toolbarTitle.toString())

        } else if (fragmentValue == "bottom") {
            //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.lists))
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
            NineBxApplication.instance.activityInstance!!.showBottomView()
        }
        return super.onBackPressed()
    }

    private var combineFetched: ArrayList<DecryptedHomeList>? = null

    fun setCombine(combineFetched: ArrayList<DecryptedHomeList>?) {
        this.combineFetched = combineFetched
        searchItems.clear()
        for (item in combineFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName,"","",0,0,"",item.id))
        }
    }

    private var combineTravelFetched: ArrayList<DecryptedTravelList>? = null

    fun setCombineFetched(combineTravelFetched: ArrayList<DecryptedTravelList>?) {
        this.combineTravelFetched = combineTravelFetched
        searchItems.clear()
        for (item in combineTravelFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName,"","",0,0,"",item.id))
        }
    }

    private var combineContactsFetched: ArrayList<DecryptedContactsList>? = null
    fun setCombineContacts(combineContactsFetched: ArrayList<DecryptedContactsList>) {
        this.combineContactsFetched = combineContactsFetched
        searchItems.clear()
        for(item in combineContactsFetched) {
            searchItems.add(Level3SearchItem(categoryName, item.listName,"","",0,0,"",item.id))
        }
    }

    private var combineEducationFetched: ArrayList<DecryptedEducationList>? = null

    fun setCombineEduction(combineEducationFetched: ArrayList<DecryptedEducationList>?) {
        this.combineEducationFetched = combineEducationFetched
        searchItems.clear()
        for (item in combineEducationFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName,"","",0,0,"",item.id))
        }

    }

    private var combinePersonalFetched: ArrayList<DecryptedPersonalList>? = null

    fun setCombinePersonal(combinePersonalFetched: ArrayList<DecryptedPersonalList>?) {
        this.combinePersonalFetched = combinePersonalFetched
        searchItems.clear()
        for (item in combinePersonalFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName,"","",0,0,"",item.id))
        }

    }

    private var combineInterestsFetched: ArrayList<DecryptedInterestsList>? = null

    fun setCombineInterests(combineInterestsFetched: ArrayList<DecryptedInterestsList>?) {
        this.combineInterestsFetched = combineInterestsFetched
        searchItems.clear()
        for (item in combineInterestsFetched!!) {

            searchItems.add(Level3SearchItem(categoryName, item.listName,"","",0,0,"",item.id))
        }
    }

    private var combineWellnessFetched: ArrayList<DecryptedWellnessList>? = null

    fun setCombineWellness(combineWellnessFetched: ArrayList<DecryptedWellnessList>?) {
        this.combineWellnessFetched = combineWellnessFetched
        searchItems.clear()
        for (item in combineWellnessFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName,"","",0,0,"",item.id))
        }

    }

    private var combineMemoriesFetched: ArrayList<DecryptedMemoriesList>? = null

    fun setCombineMemories(combineMemoriesFetched: ArrayList<DecryptedMemoriesList>?) {
        this.combineMemoriesFetched = combineMemoriesFetched
        searchItems.clear()
        for (item in combineMemoriesFetched!!) {

            searchItems.add(Level3SearchItem(categoryName, item.listName,"","",0,0,"",item.id))
        }

    }

    private var combineShoppingFetched: ArrayList<DecryptedShoppingList>? = null

    fun setCombineShopping(combineShoppingFetched: ArrayList<DecryptedShoppingList>?) {
        this.combineShoppingFetched = combineShoppingFetched
        searchItems.clear()
        for (item in combineShoppingFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName,"","",0,0,"",item.id))
        }
    }

    override fun onItemClick(itemPosition: Int, position: Int, searchItem: Level3SearchItem, action : String ) {
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

