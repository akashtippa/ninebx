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
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.home.lists.adapter.SubListsAdapter
import com.ninebx.ui.home.lists.helper.SwipeToDeleteCallback
import com.ninebx.ui.home.lists.model.AddedSubItem
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.KeyboardUtil
import kotlinx.android.synthetic.main.fragment_super_sub_list.*
import java.util.*


/***
 * Created by TechnoBlogger on 15/01/18.
 */
class FragmentSuperSubListFragment : FragmentBackHelper() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_super_sub_list, container, false)
    }

    private var mSubListAdapter: SubListsAdapter? = null
    var myList: ArrayList<AddedSubItem> = ArrayList()
    var strAddItem = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSubListAdapter = SubListsAdapter(myList)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvAddedSubLists!!.layoutManager = layoutManager
        rvAddedSubLists!!.adapter = mSubListAdapter

        // Swipe to delete.
        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rvAddedSubLists.adapter as SubListsAdapter
                adapter.removeAt(viewHolder.adapterPosition)

                val snackBar = Snackbar.make(view, "Item Deleted", Snackbar.LENGTH_LONG)
                snackBar.setAction("UNDO", View.OnClickListener {

                    val mLog = AddedSubItem()
                    mLog.strAddedItem = strAddItem
                    myList.add(mLog)
                    mSubListAdapter!!.notifyData(myList)
                })
                snackBar.setActionTextColor(Color.YELLOW)
                snackBar.show()


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
                val mLog = AddedSubItem()
                mLog.strAddedItem = strAddItem
                myList.add(mLog)
                mSubListAdapter!!.notifyData(myList)
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
            mSubListAdapter!!.selectAll()
            txtUnCheckAll.visibility = View.VISIBLE
            txtCheckAll.visibility = View.GONE
        }

        txtUnCheckAll.setOnClickListener {
            mSubListAdapter!!.deSelectAll()
            txtCheckAll.visibility = View.VISIBLE
            txtUnCheckAll.visibility = View.GONE
        }

        txtHideCompleted.setOnClickListener {
            mSubListAdapter!!.showSelected()
            txtHideCompleted.visibility = View.GONE
            txtShowCompleted.visibility = View.VISIBLE
        }

        txtShowCompleted.setOnClickListener {
            mSubListAdapter!!.hideSelected()
            txtHideCompleted.visibility = View.VISIBLE
            txtShowCompleted.visibility = View.GONE
        }

    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.hideBackIcon()
        NineBxApplication.instance.activityInstance!!.showToolbar()
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.account))
        return super.onBackPressed()
    }
}