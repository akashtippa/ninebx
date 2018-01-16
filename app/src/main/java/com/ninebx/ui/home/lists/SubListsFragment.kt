package com.ninebx.ui.home.lists

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
import com.ninebx.ui.home.lists.adapter.RecyclerAdapter
import com.ninebx.ui.home.lists.model.AddedItem
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.KeyboardUtil
import kotlinx.android.synthetic.main.fragment_sub_list.*
import java.util.*


/**
 * Created by Alok on 03/01/18.
 */
class SubListsFragment : FragmentBackHelper() {

    private var mRecyclerAdapter: RecyclerAdapter? = null
    var myList: ArrayList<AddedItem> = ArrayList()
    var strAddItem = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sub_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerAdapter = RecyclerAdapter(myList)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvAddedLists!!.layoutManager = layoutManager
        rvAddedLists!!.adapter = mRecyclerAdapter

        txtDone.setOnClickListener {
            strAddItem = edtAddList.text.toString()
            txtDone.hide()

            if (strAddItem == "") {
                Toast.makeText(context, "Please enter title of the list", Toast.LENGTH_SHORT).show()
                edtAddList.clearFocus()
                KeyboardUtil.hideSoftKeyboard(activity!!)
            } else {
                val mLog = AddedItem()
                mLog.strAddedItem = strAddItem
                myList.add(mLog)
                mRecyclerAdapter!!.notifyData(myList)
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

    /** Setting the adapter to the ListView */

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.lists))
        NineBxApplication.instance.activityInstance!!.showBottomView()
        return super.onBackPressed()
    }
}

