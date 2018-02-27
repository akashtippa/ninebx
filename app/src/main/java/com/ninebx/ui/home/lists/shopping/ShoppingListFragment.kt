package com.ninebx.ui.home.lists.shopping

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
import com.ninebx.ui.base.realm.decrypted.DecryptedHomeList
import com.ninebx.ui.base.realm.decrypted.DecryptedShoppingList
import com.ninebx.ui.base.realm.lists.HomeList
import com.ninebx.ui.home.lists.adapter.ListsAdapter
import com.ninebx.ui.home.lists.helper.SwipeToDeleteCallback
import com.ninebx.utility.AppLogger
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.KeyboardUtil
import com.ninebx.utility.NineBxPreferences
import kotlinx.android.synthetic.main.fragment_sub_list.*
import java.util.ArrayList

/**
 * Created by Alok on 03/01/18.
 */
class ShoppingListFragment : FragmentBackHelper() {

    private var mListsAdapter: ShoppingListsAdapter? = null
    var strAddItem = ""
    var fragmentValue = ""

    private var myList: ArrayList<DecryptedHomeList>? = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sub_list, container, false)
    }

    private var currentUsers: ArrayList<HomeList>? = ArrayList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        currentUsers = arguments!!.getParcelableArrayList<HomeList>(Constants.LIST_HOME)
//        myList.addAll(currentUsers!!)
        var getArrayList: ArrayList<DecryptedShoppingList> = getArguments()!!.getSerializable("combineListItemsFetched") as ArrayList<DecryptedShoppingList>


        mListsAdapter = ShoppingListsAdapter(getArrayList)
        AppLogger.d("CombineListArray", " " + getArrayList)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvAddedLists!!.layoutManager = layoutManager
        rvAddedLists!!.adapter = mListsAdapter

        fragmentValue = arguments!!.getString("homeScreen")

        NineBxApplication.instance.activityInstance!!.hideBottomView()

        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rvAddedLists.adapter as ShoppingListsAdapter
                adapter.removeAt(viewHolder.adapterPosition)
                val snackBar = Snackbar.make(view, "Item Deleted", Snackbar.LENGTH_LONG)
                snackBar.setAction("UNDO", View.OnClickListener {
                    // undo is selected, restore the deleted item
                    val mLog = DecryptedShoppingList()
                    mLog.listName = strAddItem
                    getArrayList.add(mLog)
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
                val mLog = DecryptedShoppingList()
                mLog.listName = strAddItem
                getArrayList.add(mLog)
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
            transaction.remove(this@ShoppingListFragment)
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
}