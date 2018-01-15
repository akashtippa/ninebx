package com.ninebx.ui.home.lists

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.utility.FragmentBackHelper
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_sub_list.*
import android.widget.EditText
import com.ninebx.ui.home.lists.model.AddedItem
import java.util.*
import java.util.Arrays.asList
import com.ninebx.ui.home.lists.adapter.RecyclerAdapter
import android.support.v7.widget.LinearLayoutManager


/**
 * Created by Alok on 03/01/18.
 */
class SubListsFragment : FragmentBackHelper() {

    private var mRecyclerAdapter: RecyclerAdapter? = null
    var myList: ArrayList<AddedItem> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_sub_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        rvListedEvents

        mRecyclerAdapter = RecyclerAdapter(myList)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL



    }

    /** Setting the adapter to the ListView */


    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.lists))
        NineBxApplication.instance.activityInstance!!.showBottomView()
        return super.onBackPressed()
    }
}

