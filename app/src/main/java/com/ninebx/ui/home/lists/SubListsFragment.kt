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
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.SearchItemClickListener
import com.ninebx.ui.base.realm.decrypted.DecryptedHomeList

import com.ninebx.ui.base.realm.lists.*

import com.ninebx.ui.home.lists.adapter.ListsAdapter
import com.ninebx.ui.home.lists.helper.SwipeToDeleteCallback
import com.ninebx.ui.home.search.Level3SearchItem
import com.ninebx.ui.home.search.SearchAdapter
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.KeyboardUtil
import com.ninebx.utility.NineBxPreferences
import com.ninebx.utility.decryptString
import kotlinx.android.synthetic.main.fragment_sub_list.*
import kotlin.collections.ArrayList


/**
 * Created by Alok on 03/01/18.
 */
class SubListsFragment : FragmentBackHelper(), SearchItemClickListener {


    var strAddItem = ""
    var fragmentValue = ""
    var categoryName = 0

    private lateinit var mListsAdapter : SearchAdapter
    private var searchItems : ArrayList<Level3SearchItem> = ArrayList()

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

        fragmentValue = arguments!!.getString("homeScreen")
        categoryName = arguments!!.getInt("categoryName")

        NineBxApplication.instance.activityInstance!!.hideBottomView()

        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rvAddedLists.adapter as ListsAdapter
                adapter.removeAt(viewHolder.adapterPosition)
                val snackBar = Snackbar.make(view, "Item Deleted", Snackbar.LENGTH_LONG)
                snackBar.setAction("UNDO", View.OnClickListener {
                    // undo is selected, restore the deleted item
                    val mLog = DecryptedHomeList()
                    mLog.listName = strAddItem
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
                val mLog = DecryptedHomeList()
                mLog.listName = strAddItem
                //getArrayList.add(mLog)
                mListsAdapter!!.notifyDataSetChanged()
                edtAddList.text.clear()
                KeyboardUtil.hideSoftKeyboard(activity!!)
            }

        }

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

    override fun onBackPressed(): Boolean {

        if (fragmentValue == "HomeScreen") {
            NineBxApplication.instance.activityInstance!!.showBottomView()
            NineBxApplication.instance.activityInstance!!.hideQuickAdd()
            val fm = activity!!.supportFragmentManager
            val transaction = fm.beginTransaction()
            transaction.remove(this@SubListsFragment)
            transaction.commit()
            fm.popBackStack()
            fm.popBackStack()
            val preferences = NineBxPreferences()
            val toolbarTitle = preferences.currentBox
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle(toolbarTitle.toString())
        } else if (fragmentValue == "bottom") {
            NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.lists))
            NineBxApplication.instance.activityInstance!!.showBottomView()
            NineBxApplication.instance.activityInstance!!.hideBackIcon()
        }
        return super.onBackPressed()
    }

    private var combineFetched: ArrayList<HomeList> ?= null

    fun setCombine(combineFetched: ArrayList<HomeList>?) {
        this.combineFetched = combineFetched
        searchItems.clear()
        for( item in combineFetched!! ) {
            searchItems.add( Level3SearchItem(categoryName, item.listName.decryptString()))
        }

    }

    private var combineTravelFetched: ArrayList<TravelList>? = null

    fun setCombineFetched(combineFetched: ArrayList<TravelList>?) {
        this.combineTravelFetched = combineFetched
        searchItems.clear()
        for( item in combineFetched!! ) {
                searchItems.add( Level3SearchItem(categoryName, item.listName.decryptString()))
        }
       
    }

    private var combineContactsFetched: ArrayList<ContactsList>? = null

    fun setCombineContacts(combineContactsFetched: ArrayList<ContactsList>?) {
        this.combineContactsFetched = combineContactsFetched
        searchItems.clear()
        for( item in combineContactsFetched!! ) {
                searchItems.add( Level3SearchItem(categoryName, item.listName.decryptString()))
        }
       
    }

    private var combineEducationFetched: ArrayList<EducationList>? = null

    fun setCombineEduction(combineEducationFetched: ArrayList<EducationList>?) {
        this.combineEducationFetched = combineEducationFetched
        searchItems.clear()
        for( item in combineEducationFetched!! ) {
            
                searchItems.add( Level3SearchItem(categoryName, item.listName.decryptString()))
        }
       
    }

    private var combinePersonalFetched: ArrayList<PersonalList>? = null

    fun setCombinePersonal(combinePersonalFetched: ArrayList<PersonalList>?) {
        this.combinePersonalFetched = combinePersonalFetched
        searchItems.clear()
        for( item in combinePersonalFetched!! ) {
             searchItems.add( Level3SearchItem(categoryName, item.listName.decryptString()))
        }
       
    }

    private var combineInterestsFetched: ArrayList<InterestsList>? = null

    fun setCombineInterests(combineInterestsFetched: ArrayList<InterestsList>?) {
        this.combineInterestsFetched = combineInterestsFetched
        searchItems.clear()
        for( item in combineInterestsFetched!! ) {
            
                searchItems.add( Level3SearchItem(categoryName, item.listName.decryptString()))
        }
       
    }

    private var combineWellnessFetched: ArrayList<WellnessList>? = null

    fun setCombineWellness(combineWellnessFetched: ArrayList<WellnessList>?) {
        this.combineWellnessFetched = combineWellnessFetched
        searchItems.clear()
        for( item in combineWellnessFetched!! ) {
            
                searchItems.add( Level3SearchItem(categoryName, item.listName.decryptString()))
        }
       
    }

    private var combineMemoriesFetched: ArrayList<MemoriesList>? = null

    fun setCombineMemories(combineMemoriesFetched: ArrayList<MemoriesList>?) {
        this.combineMemoriesFetched = combineMemoriesFetched
        searchItems.clear()
        for( item in combineMemoriesFetched!! ) {
            
                searchItems.add( Level3SearchItem(categoryName, item.listName.decryptString()))
        }
       
    }

    private var combineShoppingFetched: ArrayList<ShoppingList>? = null

    fun setCombineShopping(combineShoppingFetched: ArrayList<ShoppingList>?) {
        this.combineShoppingFetched = combineShoppingFetched
        searchItems.clear()
        for( item in combineShoppingFetched!! ) {
                searchItems.add( Level3SearchItem(categoryName, item.listName.decryptString()))
        }
       
    }

    override fun onItemClick(itemPosition : Int, position: Int, searchItem: Level3SearchItem) {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val superSubListFragment = SuperSubListFragment()
        val bundle = Bundle()
        bundle.putInt("categoryName", categoryName)
        superSubListFragment.arguments = bundle
        when( categoryName ) {
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
}

