package com.ninebx.ui.home.account

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.account.adapter.AddOrEditPermissionAdapter
import com.ninebx.ui.home.account.model.AddEditPermissions
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_permissions.*


/***
 * Created by TechnoBlogger on 18/01/18.
 */

class PermissionFragment : FragmentBackHelper() {

    private lateinit var myList: ArrayList<AddEditPermissions>
    private var mListsAdapter: AddOrEditPermissionAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_permissions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NineBxApplication.instance.activityInstance!!.hideToolbar()
        ivBackPermissions.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        txtSave.setOnClickListener {

        }

        myList = ArrayList()
        for (i in 0 until PermissionData.menuName.size) {
            myList.add(AddEditPermissions(
                    PermissionData.bgDrawable[i],
                    PermissionData.menuDrawable[i],
                    PermissionData.menuName[i],
                    PermissionData.chkView[i],
                    PermissionData.chkAdd[i],
                    PermissionData.chkEdit[i]
            ))
        }

        mListsAdapter = AddOrEditPermissionAdapter(myList)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvPermissions!!.layoutManager = layoutManager
        rvPermissions!!.adapter = mListsAdapter


    }
}