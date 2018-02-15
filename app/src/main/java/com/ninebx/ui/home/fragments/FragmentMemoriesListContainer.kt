package com.ninebx.ui.home.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.account.interfaces.IMemoryAdded
import com.ninebx.ui.home.adapter.MemoriesAdapter
import com.ninebx.utility.*
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_memories_list_container.*
import java.util.*

/***
 * Created by TechnoBlogger on 24/01/18.
 */
class FragmentMemoriesListContainer : FragmentBackHelper(), IMemoryAdded {

    override fun onMemoryEdit(memoryTimeLine: MemoryTimeline?) {
        mListsAdapter!!.notifyDataSetChanged()
        val bundle = Bundle()
        bundle.putParcelable(Constants.MEMORY_TIMELINE, memoryTimeLine)
        bundle.putString(Constants.FROM_CLASS, "MemoryView")
        startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), ADD_MEMORY_TIMELINE)
    }

    override fun memoryAdded(memoryTimeLine: MemoryTimeline?) {
        AppLogger.d("Memory", "memoryTimeLine" + memoryTimeLine)
        myList.add(memoryTimeLine!!)
        mListsAdapter!!.notifyDataSetChanged()
        saveMemoryTimeLine()
    }


    private val ADD_MEMORY_TIMELINE = 9000

    private var mListsAdapter: MemoriesAdapter? = null
    var myList: ArrayList<MemoryTimeline> = ArrayList()
    private var currentMemoriesList: ArrayList<MemoryTimeline>? = ArrayList()
    private var usersRealm: Realm? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_memories_list_container, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Memory timeline")

        currentMemoriesList = arguments!!.getParcelableArrayList<MemoryTimeline>(Constants.REALM_MEMORY_VIEW)
        myList.addAll(currentMemoriesList!!)

        mListsAdapter = MemoriesAdapter(myList, this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvMemoryView!!.layoutManager = layoutManager
        rvMemoryView!!.adapter = mListsAdapter

        layoutAddList.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(Constants.MEMORY_TIMELINE, MemoryTimeline())
            bundle.putString(Constants.FROM_CLASS, "MemoryView")
            startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), ADD_MEMORY_TIMELINE)
        }

        prepareRealmConnections(context, true, "Users", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                usersRealm = realm
                saveMemoryTimeLine()
            }
        })

    }


    private fun saveMemoryTimeLine() {
        val memoryObject = MemoryTimeline.createMemoryTimeLine(currentMemoriesList!![0])
        memoryObject.insertOrUpdate(usersRealm!!)
        context!!.hideProgressDialog()
        myList.clear()
        myList.add(memoryObject)
        mListsAdapter!!.notifyDataSetChanged()
    }


    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideQuickAdd()
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.showBackIcon()
        return super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_MEMORY_TIMELINE && resultCode == Activity.RESULT_OK) {
            memoryAdded(data!!.getParcelableExtra(Constants.MEMORY_TIMELINE))
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }

}