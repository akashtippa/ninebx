package com.ninebx.ui.home.lists

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.SearchItemClickListener
import com.ninebx.ui.base.realm.decrypted.DecryptedTravelList
import com.ninebx.ui.base.realm.lists.*
import com.ninebx.ui.home.adapter.Date
import com.ninebx.ui.home.lists.adapter.SubListsAdapter
import com.ninebx.ui.home.lists.helper.SwipeToDeleteCallback
import com.ninebx.ui.home.lists.model.AddedSubItem
import com.ninebx.ui.home.search.Level3SearchItem
import com.ninebx.ui.home.search.SearchAdapter
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_super_sub_list.*
import java.util.*
import kotlin.collections.ArrayList
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.utility.Constants.SEARCH_NORMAL
import com.ninebx.utility.Constants.SEARCH_RADIO


/***
 * Created by TechnoBlogger on 15/01/18.
 */
class SuperSubListFragment : FragmentBackHelper(), ListsCommunicationView, SearchItemClickListener {


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

    private var combineFetched: ArrayList<DecryptedHomeList>? = null
    private var contactsRealm: Realm? = null

    val detailsId: Long = 0
    var createdDate = ""

    fun setCombine(combineFetched: ArrayList<DecryptedHomeList>?) {
        this.combineFetched = combineFetched
        searchItems.clear()
        for (item in combineFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName, "", "", 0, item.id, "", listId))
        }
        reloadAdapter()
    }

    private var combineTravelFetched: ArrayList<DecryptedTravelList>? = null
    val preferences = NineBxApplication.getPreferences()

    fun setCombineFetched(combineFetched: ArrayList<DecryptedTravelList>?) {
        this.combineTravelFetched = combineFetched
        //searchItems.clear()
        for (item in combineFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName, "", "", 0, item.id, "", listId))
        }
        reloadAdapter()
    }

    private var combineContactsFetched: ArrayList<DecryptedContactsList>? = null

    fun setCombineContacts(combineContactsFetched: ArrayList<DecryptedContactsList>?) {
        this.combineContactsFetched = combineContactsFetched
        searchItems.clear()
        for (item in combineContactsFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName, "", "", 0, item.id, "", listId))
        }
        reloadAdapter()
    }

    private var combineEducationFetched: ArrayList<DecryptedEducationList>? = null

    fun setCombineEduction(combineEducationFetched: ArrayList<DecryptedEducationList>?) {
        this.combineEducationFetched = combineEducationFetched
        searchItems.clear()
        for (item in combineEducationFetched!!) {

            searchItems.add(Level3SearchItem(categoryName, item.listName, "", "", 0, item.id, "", listId))
        }
        reloadAdapter()
    }

    private var combinePersonalFetched: ArrayList<DecryptedPersonalList>? = null

    fun setCombinePersonal(combinePersonalFetched: ArrayList<DecryptedPersonalList>?) {
        this.combinePersonalFetched = combinePersonalFetched
        searchItems.clear()
        for (item in combinePersonalFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName, "", "", 0, item.id, "", listId))
        }
        reloadAdapter()
    }

    private var combineInterestsFetched: ArrayList<DecryptedInterestsList>? = null

    fun setCombineInterests(combineInterestsFetched: ArrayList<DecryptedInterestsList>?) {
        this.combineInterestsFetched = combineInterestsFetched
        searchItems.clear()
        for (item in combineInterestsFetched!!) {

            searchItems.add(Level3SearchItem(categoryName, item.listName, "", "", 0, item.id, "", listId))
        }
        reloadAdapter()
    }

    private var combineWellnessFetched: ArrayList<DecryptedWellnessList>? = null

    fun setCombineWellness(combineWellnessFetched: ArrayList<DecryptedWellnessList>?) {
        this.combineWellnessFetched = combineWellnessFetched
        searchItems.clear()
        for (item in combineWellnessFetched!!) {

            searchItems.add(Level3SearchItem(categoryName, item.listName, "", "", 0, item.id, "", listId))
        }
        reloadAdapter()
    }

    private var combineMemoriesFetched: ArrayList<DecryptedMemoriesList>? = null

    fun setCombineMemories(combineMemoriesFetched: ArrayList<DecryptedMemoriesList>?) {
        this.combineMemoriesFetched = combineMemoriesFetched
        searchItems.clear()
        for (item in combineMemoriesFetched!!) {

            searchItems.add(Level3SearchItem(categoryName, item.listName, "", "", 0, item.id, "", listId))
        }
        reloadAdapter()
    }

    private var combineShoppingFetched: ArrayList<DecryptedShoppingList>? = null

    fun setCombineShopping(combineShoppingFetched: ArrayList<DecryptedShoppingList>?) {
        this.combineShoppingFetched = combineShoppingFetched
        searchItems.clear()
        for (item in combineShoppingFetched!!) {
            searchItems.add(Level3SearchItem(categoryName, item.listName, "", "", 0, item.id, "", listId))
        }
        reloadAdapter()
    }

    override fun homeListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedHomeList>?) {
        combineFetched = ArrayList()
        combineFetched!!.addAll(decryptCombine!!.toList())
        setCombine(combineFetched)
    }

    override fun travelListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedTravelList>) {
        combineTravelFetched = ArrayList()
        combineTravelFetched!!.addAll(decryptCombine.toList())
        setCombineFetched(combineTravelFetched)
    }

    override fun contactListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedContactsList>) {
        combineContactsFetched = ArrayList()
        combineContactsFetched!!.addAll(decryptCombine.toList())
        setCombineContacts(combineContactsFetched)
    }

    override fun educationListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedEducationList>) {
        combineEducationFetched = ArrayList()
        combineEducationFetched!!.addAll(decryptCombine.toList())
        setCombineEduction(combineEducationFetched)
    }

    override fun interestListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedInterestsList>) {
        combineInterestsFetched = ArrayList()
        combineInterestsFetched!!.addAll(decryptCombine.toList())
        setCombineInterests(combineInterestsFetched)
    }

    override fun countPersonalList(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedPersonalList>) {
        combinePersonalFetched = ArrayList()
        combinePersonalFetched!!.addAll(decryptCombine.toList())
        setCombinePersonal(combinePersonalFetched)
    }

    override fun wellnessListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedWellnessList>) {
        combineWellnessFetched = ArrayList()
        combineWellnessFetched!!.addAll(decryptCombine.toList())
        setCombineWellness(combineWellnessFetched)
    }

    override fun memoryListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedMemoriesList>) {
        combineMemoriesFetched = ArrayList()
        combineMemoriesFetched!!.addAll(decryptCombine.toList())
        setCombineMemories(combineMemoriesFetched)
    }

    override fun shoppingListCount(contactsUpdating: Int, decryptCombine: ArrayList<DecryptedShoppingList>) {
        combineShoppingFetched = ArrayList()
        combineShoppingFetched!!.addAll(decryptCombine.toList())
        setCombineShopping(combineShoppingFetched)
    }

    private fun reloadAdapter() {
        if(mListsAdapter != null) {
            mListsAdapter = SearchAdapter(searchItems, SEARCH_RADIO, this)
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            rvAddedSubLists!!.layoutManager = layoutManager
            rvAddedSubLists!!.adapter = mListsAdapter
        }
    }

    private fun loadAdapter() {
        mListsAdapter = SearchAdapter(searchItems, SEARCH_RADIO, this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvAddedSubLists!!.layoutManager = layoutManager
        rvAddedSubLists!!.adapter = mListsAdapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_super_sub_list, container, false)
    }

    private var mSubListAdapter: SubListsAdapter? = null
    var myList: ArrayList<AddedSubItem> = ArrayList()
    var strAddItem = ""
    var decryptedTravelList: DecryptedTravelList? = null
    private lateinit var mListsAdapter: SearchAdapter
    private var searchItems: ArrayList<Level3SearchItem> = ArrayList()
    private var categoryName: Int = -1
    private var listId: Long = -1
    private var listTitleName = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ListsPresenter(this, listId, arguments!!.getInt("categoryName")).fetchDataInBackground()

        decryptedTravelList = arguments!!.getParcelable<DecryptedTravelList>(Constants.SELECTED_ITEM)

        /*mListsAdapter = SearchAdapter(searchItems, SEARCH_NORMAL, this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvAddedSubLists!!.layoutManager = layoutManager
        rvAddedSubLists!!.adapter = mListsAdapter*/
        loadAdapter()
        txtSubListName.setText(listTitleName)
        categoryName = arguments!!.getInt("categoryName")

        for (items in searchItems) {
            val dates = AddedSubItem()
            dates.strAddedItem
            myList.add(dates)
        }

        //AppLogger.e("List Id ", " is " + listId)

        txtSubListName.addTextChangedListener(object : TextWatcher {
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

        txtDone.setOnClickListener {
            updateParticularListName(categoryName)
        }

        // Swipe to delete.
        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                /*val adapter = rvAddedSubLists.adapter as SubListsAdapter
                adapter.removeAt(viewHolder.adapterPosition)

                val snackBar = Snackbar.make(view, "Item Deleted", Snackbar.LENGTH_LONG)
                snackBar.setAction("UNDO", View.OnClickListener {

                    val mLog = AddedSubItem()
                    mLog.strAddedItem = strAddItem
                    myList.add(mLog)
                    mSubListAdapter!!.notifyData(myList)
                })
                snackBar.setActionTextColor(Color.YELLOW)
                snackBar.show()*/
                val adapter = rvAddedSubLists.adapter as SearchAdapter
                val id = adapter.getItemsId(viewHolder.adapterPosition)
                adapter.removeAt(viewHolder.adapterPosition)
                deleteItemFromRealm(categoryName,id)


            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rvAddedSubLists)

        txtSubListDone.setOnClickListener {
            strAddItem = edtAddSubList.text.toString()
            txtSubListDone.hide()

            if (strAddItem == "") {
                Toast.makeText(context, getString(R.string.please_enter_title_of_the_list), Toast.LENGTH_SHORT).show()
                edtAddSubList.clearFocus()
                KeyboardUtil.hideSoftKeyboard(activity!!)
            } else {
                //val mLog = AddedSubItem()
                var itemLog = Level3SearchItem()
                itemLog.itemName = strAddItem
                itemLog.itemId = getUniqueId()
                itemLog.listItemId = listId
                //mLog.strAddedItem = strAddItem
//                myList.add(mLog)
//                mSubListAdapter!!.notifyData(myList)
                aadToParticularRealmList(categoryName, itemLog.itemId)
                //update adapter
                mListsAdapter.add(mListsAdapter.itemCount,itemLog)
                mListsAdapter.notifyDataSetChanged()
                edtAddSubList.text.clear()
                KeyboardUtil.hideSoftKeyboard(activity!!)
            }

        }

        edtAddSubList.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.trim().isEmpty()) {
                    txtSubListDone.hide()
                } else {
                    txtSubListDone.show()
                }
            }
        })

        val transparentDrawable = ColorDrawable(Color.TRANSPARENT)
        txtCheckAll.buttonDrawable = transparentDrawable
        txtUnCheckAll.buttonDrawable = transparentDrawable

        txtCheckAll.setOnClickListener {
            mSubListAdapter?.selectAll()
            txtUnCheckAll.visibility = View.VISIBLE
            txtCheckAll.visibility = View.GONE
        }

        txtUnCheckAll.setOnClickListener {
            mSubListAdapter?.deSelectAll()
            txtCheckAll.visibility = View.VISIBLE
            txtUnCheckAll.visibility = View.GONE
        }

        txtHideCompleted.setOnClickListener {
            mSubListAdapter?.showSelected()
            txtHideCompleted.visibility = View.GONE
            txtShowCompleted.visibility = View.VISIBLE
        }

        txtShowCompleted.setOnClickListener {
            mSubListAdapter?.hideSelected()
            txtHideCompleted.visibility = View.VISIBLE
            txtShowCompleted.visibility = View.GONE
        }

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
                        }
                        R.string.travel -> {
                            realm.where(TravelList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.contacts -> {
                            realm.where(ContactsList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.education_work -> {
                            realm.where(EducationList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.personal -> {
                            realm.where(PersonalList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.interests -> {
                            realm.where(InterestsList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.wellness -> {
                            realm.where(WellnessList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.memories -> {
                            realm.where(MemoriesList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
                        }
                        R.string.shopping -> {
                            realm.where(ShoppingList::class.java).equalTo("id", itemId).findAll().deleteAllFromRealm()
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

    private fun aadToParticularRealmList(categoryName: Int, uniqueId: Long) {
        savingTheCreatedTime()
        when (categoryName) {
            R.string.home_amp_money -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var homeList = HomeList()
                        homeList.id = uniqueId
                        homeList.listName = edtAddSubList.text.toString().encryptString()
                        homeList.detailsId = listId
                        homeList.selectionType = "HomeBanking".encryptString()
                        homeList.modified = createdDate
                        //AppLogger.e("Created Date ", " is " + createdDate)
                        homeList.insertOrUpdate(realm!!)
                    }

                })

//                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
//                    override fun onSuccess(realm: Realm?) {
//
//                        val checkItem = realm!!
//                                .where(HomeList::class.java)
//                                .beginGroup()
//                                .equalTo("listName", listTitleName)
//                                .and()
//                                .equalTo("selectionType", "HomeBanking".encryptString())
//                                .endGroup()
//                                .findAll()
//
//                        if (checkItem.isValid) {
//                            realm.executeTransaction({
//                                var listItems = HomeList()
//                                listItems.modified = createdDate
//                                listItems.id = getUniqueId()
//                                listItems.listName = strAddItem.encryptString()
//                                listItems.detailsId = 0
//                                listItems.selectionType = "HomeBanking".encryptString()
//                                listItems.created = createdDate
//                                //AppLogger.e("Created Date ", " is " + preferences.created)
//
//                                listItems.createdUser = preferences.userID
//                                realm.copyToRealmOrUpdate(listItems)
//                                KeyboardUtil.hideSoftKeyboard(activity!!)
//                                txtDone.hide()
//                            })
//                        }
//                    }
//
//                })

            }
            R.string.travel -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var homeList = TravelList()
                        homeList.id = uniqueId
                        homeList.listName = edtAddSubList.text.toString().encryptString()
                        homeList.detailsId = listId
                        homeList.selectionType = "Travel".encryptString()
                        homeList.modified = createdDate
                        homeList.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.contacts -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var homeList = ContactsList()
                        homeList.id = uniqueId
                        homeList.listName = edtAddSubList.text.toString().encryptString()
                        homeList.detailsId = listId
                        homeList.selectionType = "Contacts".encryptString()
                        homeList.modified = createdDate
                        homeList.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.education_work -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_EDUCATION, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var homeList = EducationList()
                        homeList.id = uniqueId
                        homeList.listName = edtAddSubList.text.toString().encryptString()
                        homeList.detailsId = listId
                        homeList.selectionType = "Education".encryptString()
                        homeList.modified = createdDate
                        homeList.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.personal -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var homeList = PersonalList()
                        homeList.id = uniqueId
                        homeList.listName = edtAddSubList.text.toString().encryptString()
                        homeList.detailsId = listId
                        homeList.selectionType = "Personal".encryptString()
                        homeList.modified = createdDate
                        homeList.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.interests -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_INTERESTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var homeList = InterestsList()
                        homeList.id = uniqueId
                        homeList.listName = edtAddSubList.text.toString().encryptString()
                        homeList.detailsId = listId
                        homeList.selectionType = "Interests".encryptString()
                        homeList.modified = createdDate
                        homeList.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.wellness -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var homeList = WellnessList()
                        homeList.id = uniqueId
                        homeList.listName = edtAddSubList.text.toString().encryptString()
                        homeList.detailsId = listId
                        homeList.selectionType = "Wellness".encryptString()
                        homeList.modified = createdDate
                        homeList.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.memories -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var homeList = MemoriesList()
                        homeList.id = uniqueId
                        homeList.listName = edtAddSubList.text.toString().encryptString()
                        homeList.detailsId = listId
                        homeList.selectionType = "Memories".encryptString()
                        homeList.modified = createdDate
                        homeList.insertOrUpdate(realm!!)
                    }

                })

            }
            R.string.shopping -> {
                savingTheCreatedTime()

                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        contactsRealm = realm
                        var homeList = ShoppingList()
                        homeList.id = uniqueId
                        homeList.listName = edtAddSubList.text.toString().encryptString()
                        homeList.detailsId = listId
                        homeList.modified = createdDate
                        homeList.selectionType = "Shopping".encryptString()
                        homeList.insertOrUpdate(realm!!)
                    }

                })

            }
        }
    }

    private fun updateParticularListName(categoryName: Int) {

        when (categoryName) {
            R.string.home_amp_money -> {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {

                        val checkItem = realm!!
                                .where(HomeList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId)
                                .and()
                                .equalTo("selectionType", "HomeBanking".encryptString())
                                .endGroup()
                                .findAll()

                        if (checkItem.isValid) {
                            realm.executeTransaction({
                                var homeList = HomeList()
                                homeList.id = listId
                                homeList.detailsId = 0
                                homeList.listName = txtSubListName.text.toString().encryptString()
                                homeList.selectionType = "HomeBanking".encryptString()

                                realm.copyToRealmOrUpdate(homeList)
                                KeyboardUtil.hideSoftKeyboard(activity!!)
                                txtDone.hide()
                            })
                        }
                    }

                })
            }
            R.string.travel -> {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_TRAVEL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {

                        val checkItem = realm!!
                                .where(TravelList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId)
                                .and()
                                .equalTo("selectionType", "Travel".encryptString())
                                .endGroup()
                                .findAll()
                        AppLogger.d("SuperSubListFragment", "Travel List" + checkItem)
                        if (checkItem.isValid) {
                            realm.executeTransaction({
                                var homeList = HomeList()
                                homeList.id = listId
                                homeList.detailsId = 0
                                homeList.listName = txtSubListName.text.toString().encryptString()
                                homeList.selectionType = "Travel".encryptString()

                                realm.copyToRealmOrUpdate(homeList)
                                KeyboardUtil.hideSoftKeyboard(activity!!)
                                txtDone.hide()
                            })
                        }
                    }

                })

            }
            R.string.contacts -> {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val checkItem = realm!!
                                .where(ContactsList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId)
                                .and()
                                .equalTo("selectionType", "Contacts".encryptString())
                                .endGroup()
                                .findAll()

                        if (checkItem.isValid) {
                            realm.executeTransaction({
                                var homeList = HomeList()
                                homeList.id = listId
                                homeList.detailsId = 0
                                homeList.listName = txtSubListName.text.toString().encryptString()
                                homeList.selectionType = "Contacts".encryptString()

                                realm.copyToRealmOrUpdate(homeList)
                                KeyboardUtil.hideSoftKeyboard(activity!!)
                                txtDone.hide()
                            })
                        }
                    }

                })

            }
            R.string.education_work -> {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_EDUCATION, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val checkItem = realm!!
                                .where(EducationList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId)
                                .and()
                                .equalTo("selectionType", "Education".encryptString())
                                .endGroup()
                                .findAll()

                        if (checkItem.isValid) {
                            realm.executeTransaction({
                                var homeList = HomeList()
                                homeList.id = listId
                                homeList.detailsId = 0
                                homeList.listName = txtSubListName.text.toString().encryptString()
                                homeList.selectionType = "Education".encryptString()

                                realm.copyToRealmOrUpdate(homeList)
                                KeyboardUtil.hideSoftKeyboard(activity!!)
                                txtDone.hide()
                            })
                        }
                    }

                })

            }
            R.string.personal -> {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_PERSONAL, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val checkItem = realm!!
                                .where(PersonalList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId)
                                .and()
                                .equalTo("selectionType", "Personal".encryptString())
                                .endGroup()
                                .findAll()

                        if (checkItem.isValid) {
                            realm.executeTransaction({
                                var homeList = HomeList()
                                homeList.id = listId
                                homeList.detailsId = 0
                                homeList.listName = txtSubListName.text.toString().encryptString()
                                homeList.selectionType = "Personal".encryptString()

                                realm.copyToRealmOrUpdate(homeList)
                                KeyboardUtil.hideSoftKeyboard(activity!!)
                                txtDone.hide()
                            })
                        }
                    }

                })

            }
            R.string.interests -> {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_INTERESTS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val checkItem = realm!!
                                .where(InterestsList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId)
                                .and()
                                .equalTo("selectionType", "Interests".encryptString())
                                .endGroup()
                                .findAll()

                        if (checkItem.isValid) {
                            realm.executeTransaction({
                                var homeList = HomeList()
                                homeList.id = listId
                                homeList.detailsId = 0
                                homeList.listName = txtSubListName.text.toString().encryptString()
                                homeList.selectionType = "Interests".encryptString()

                                realm.copyToRealmOrUpdate(homeList)
                                KeyboardUtil.hideSoftKeyboard(activity!!)
                                txtDone.hide()
                            })
                        }
                    }

                })

            }
            R.string.wellness -> {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val checkItem = realm!!
                                .where(WellnessList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId)
                                .and()
                                .equalTo("selectionType", "Wellness".encryptString())
                                .endGroup()
                                .findAll()

                        if (checkItem.isValid) {
                            realm.executeTransaction({
                                var homeList = HomeList()
                                homeList.id = listId
                                homeList.detailsId = 0
                                homeList.listName = txtSubListName.text.toString().encryptString()
                                homeList.selectionType = "Wellness".encryptString()

                                realm.copyToRealmOrUpdate(homeList)
                                KeyboardUtil.hideSoftKeyboard(activity!!)
                                txtDone.hide()
                            })
                        }
                    }

                })

            }
            R.string.memories -> {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val checkItem = realm!!
                                .where(MemoriesList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId)
                                .and()
                                .equalTo("selectionType", "Memories".encryptString())
                                .endGroup()
                                .findAll()

                        if (checkItem.isValid) {
                            realm.executeTransaction({
                                var homeList = HomeList()
                                homeList.id = listId
                                homeList.detailsId = 0
                                homeList.listName = txtSubListName.text.toString().encryptString()
                                homeList.selectionType = "Memories".encryptString()

                                realm.copyToRealmOrUpdate(homeList)
                                KeyboardUtil.hideSoftKeyboard(activity!!)
                                txtDone.hide()
                            })
                        }
                    }

                })

            }
            R.string.shopping -> {
                prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        val checkItem = realm!!
                                .where(ShoppingList::class.java)
                                .beginGroup()
                                .equalTo("detailsId", detailsId)
                                .and()
                                .equalTo("selectionType", "Personal".encryptString())
                                .endGroup()
                                .findAll()

                        if (checkItem.isValid) {
                            realm.executeTransaction({
                                var homeList = HomeList()
                                homeList.id = listId
                                homeList.detailsId = 0
                                homeList.listName = txtSubListName.text.toString().encryptString()
                                homeList.selectionType = "Shopping".encryptString()

                                realm.copyToRealmOrUpdate(homeList)
                                KeyboardUtil.hideSoftKeyboard(activity!!)
                                txtDone.hide()
                            })
                        }
                    }

                })

            }
        }
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        return super.onBackPressed()
    }

    fun setHomeList(listItem: DecryptedHomeList) {
        listId = listItem.id
        listTitleName = listItem.listName
    }

    fun setTravelList(listItem: DecryptedTravelList) {
        listId = listItem.id
        listTitleName = listItem.listName
    }

    fun setContactsList(listItem: DecryptedContactsList) {
        listId = listItem.id
        listTitleName = listItem.listName
    }

    fun setEducationList(listItem: DecryptedEducationList) {
        listId = listItem.id
        listTitleName = listItem.listName
    }

    fun setPersonalList(listItem: DecryptedPersonalList) {
        listId = listItem.id
        listTitleName = listItem.listName
    }

    fun setInterestsList(listItem: DecryptedInterestsList) {
        listId = listItem.id
        listTitleName = listItem.listName
    }

    fun setWellnessList(listItem: DecryptedWellnessList) {
        listId = listItem.id
        listTitleName = listItem.listName
    }

    fun setMemoriesList(listItem: DecryptedMemoriesList) {
        listId = listItem.id
        listTitleName = listItem.listName
    }

    fun setShoppingList(listItem: DecryptedShoppingList) {
        listId = listItem.id
        listTitleName = listItem.listName
    }

    override fun onItemClick(itemPosition: Int, position: Int, searchItem: Level3SearchItem, action : String ) {
        //TODO
    }
}