package com.ninebx.ui.home.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.R.string.contacts
import com.ninebx.R.string.date
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.account.interfaces.IMemoryAdded
import com.ninebx.ui.home.adapter.Date
import com.ninebx.ui.home.adapter.MemoriesAdapter
import com.ninebx.ui.home.adapter.MemoriesDateAdapter
import com.ninebx.utility.*
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_memories_list_container.*
import java.util.*

/***
 * Created by TechnoBlogger on 24/01/18.
 */
class FragmentMemoriesListContainer : FragmentBackHelper(), IMemoryAdded {

    override fun onDateClicked(strDate: String?) {
        //AppLogger.e("The Selected Date ", " is " + strDate)
        Toast.makeText(context, " Date " + strDate, Toast.LENGTH_LONG).show()
        var position: Int = 0
        rvMemoryView.scrollToPosition(position)
    }

    override fun onMemoryDeleted(memoryTimeline: MemoryTimeline?) {
        memoryRealm!!.beginTransaction()
        memoryTimeline!!.deleteFromRealm()
        memoryRealm!!.commitTransaction()
    }

    override fun onMemoryEdit(memoryTimeLine: MemoryTimeline?) {
        mListsAdapter!!.notifyDataSetChanged()
        val bundle = Bundle()
        bundle.putParcelable(Constants.MEMORY_TIMELINE, memoryTimeLine)
        bundle.putString(Constants.FROM_CLASS, "MemoryView")
        bundle.putString("Operation", "Edit")
        startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), ADD_MEMORY_TIMELINE)
    }

    override fun memoryAdded(memoryTimeLine: MemoryTimeline?) {
        myList.add(memoryTimeLine!!)
        myDateList.addAll(currentDateList!!)
        mListsAdapter!!.notifyDataSetChanged()
        mListsDateAdapter!!.notifyDataSetChanged()
        saveMemoryTimeLine()
    }


    private val ADD_MEMORY_TIMELINE = 9000

    private var mListsAdapter: MemoriesAdapter? = null
    var myList: ArrayList<MemoryTimeline> = ArrayList()
    private var currentMemoriesList: ArrayList<MemoryTimeline>? = ArrayList()
    private var memoryRealm: Realm? = null


    private var mListsDateAdapter: MemoriesDateAdapter? = null
    var myDateList: ArrayList<Date> = ArrayList()
    var test: ArrayList<Any> = ArrayList()
    private var currentDateList: ArrayList<Date>? = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_memories_list_container, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Memory timeline")

        currentMemoriesList = arguments!!.getParcelableArrayList<MemoryTimeline>(Constants.REALM_MEMORY_VIEW)

        for (contact in currentMemoriesList!!) {
            val dates = Date()
            dates.strDate
            dates.strDate = contact.date

            currentDateList!!.add(dates)
        }

        myList.addAll(currentMemoriesList!!)
        myDateList.addAll(currentDateList!!)

        mListsAdapter = MemoriesAdapter(myList, this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvMemoryView!!.layoutManager = layoutManager
        rvMemoryView!!.adapter = mListsAdapter

        mListsDateAdapter = MemoriesDateAdapter(myDateList, this)
        val layoutManagerDate = LinearLayoutManager(context)
        layoutManagerDate.orientation = LinearLayoutManager.HORIZONTAL
        rvDate!!.layoutManager = layoutManagerDate
        rvDate!!.adapter = mListsDateAdapter

        layoutAddList.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(Constants.MEMORY_TIMELINE, MemoryTimeline())
            bundle.putString(Constants.FROM_CLASS, "MemoryView")
            bundle.putString("Operation", "Add")
            startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), ADD_MEMORY_TIMELINE)
        }

        prepareRealmConnections(context, true, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                memoryRealm = realm
                saveMemoryTimeLine()
            }
        })
    }


    private fun getCategoryPos(category: MemoryTimeline): Int {
        return currentMemoriesList!!.indexOf(category)
    }

    private fun saveMemoryTimeLine() {
        if (currentMemoriesList!!.size != 0) {
            val memoryObject = MemoryTimeline.createMemoryTimeLine(currentMemoriesList!![0])
            if (memoryObject.id.toString().trim() != "0") {
                memoryObject.insertOrUpdate(memoryRealm!!)
                context!!.hideProgressDialog()
                val index: Int = myList.size - 1
                myList.removeAt(index)
                myList.add(memoryObject)
                mListsAdapter!!.notifyDataSetChanged()
            } else {
                context!!.hideProgressDialog()
            }
        } else {
            context!!.hideProgressDialog()
        }
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