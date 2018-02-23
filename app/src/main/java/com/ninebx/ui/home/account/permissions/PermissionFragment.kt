package com.ninebx.ui.home.account.permissions

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.home.account.model.AddEditPermissions
import com.ninebx.utility.Constants
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_permissions.*


/***
 * Created by TechnoBlogger on 18/01/18.
 */

class PermissionFragment : FragmentBackHelper(), PermissionsView {

    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {

    }

    override fun onError(error: Int) {

    }

    interface PermissionEdited {
        fun onPermissionEdited( member : Member )
    }

    private lateinit var mPermissionEdited : PermissionEdited
    private lateinit var myList: ArrayList<AddEditPermissions>
    private var mListsAdapter: AddOrEditPermissionAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_permissions, container, false)
    }

    private lateinit var tempMember: Member

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tempMember = arguments!!.getParcelable<Member>(Constants.MEMBER)
        NineBxApplication.instance.activityInstance!!.hideToolbar()

        ivBackPermissions.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        txtSave.setOnClickListener {
            mListsAdapter!!.getPermissions( tempMember )
            tempMember.addingRemovingMember = chkAddOrRemoveUsers.isChecked
            mPermissionEdited.onPermissionEdited(tempMember)
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        /***
         * "Home & Money",
        "Travel",
        "Contacts",
        "Education & Work",
        "Personal",
        "Interests",
        "Wellness",
        "Memories",
        "Shopping"
         */
        myList = ArrayList()
        var i = 0
        myList.add(AddEditPermissions(
                PermissionData.bgDrawable[i],
                PermissionData.menuDrawable[i],
                PermissionData.menuName[i++],
                tempMember.homeView,
                tempMember.homeAdd,
                tempMember.homeEdit
        ))

        myList.add(AddEditPermissions(
                PermissionData.bgDrawable[i],
                PermissionData.menuDrawable[i],
                PermissionData.menuName[i++],
                tempMember.travelView,
                tempMember.travelAdd,
                tempMember.travelEdit
        ))

        myList.add(AddEditPermissions(
                PermissionData.bgDrawable[i],
                PermissionData.menuDrawable[i],
                PermissionData.menuName[i++],
                tempMember.contactsView,
                tempMember.contactsAdd,
                tempMember.contactsEdit
        ))

        myList.add(AddEditPermissions(
                PermissionData.bgDrawable[i],
                PermissionData.menuDrawable[i],
                PermissionData.menuName[i++],
                tempMember.educationlView,
                tempMember.educationlAdd,
                tempMember.educationlEdit
        ))

        myList.add(AddEditPermissions(
                PermissionData.bgDrawable[i],
                PermissionData.menuDrawable[i],
                PermissionData.menuName[i++],
                tempMember.personalView,
                tempMember.personalAdd,
                tempMember.personalEdit
        ))

        myList.add(AddEditPermissions(
                PermissionData.bgDrawable[i],
                PermissionData.menuDrawable[i],
                PermissionData.menuName[i++],
                tempMember.interestsView,
                tempMember.interestsAdd,
                tempMember.interestsEdit
        ))

        myList.add(AddEditPermissions(
                PermissionData.bgDrawable[i],
                PermissionData.menuDrawable[i],
                PermissionData.menuName[i++],
                tempMember.wellnessView,
                tempMember.wellnessAdd,
                tempMember.wellnessEdit
        ))

        myList.add(AddEditPermissions(
                PermissionData.bgDrawable[i],
                PermissionData.menuDrawable[i],
                PermissionData.menuName[i++],
                tempMember.memoriesView,
                tempMember.memoriesAdd,
                tempMember.memoriesEdit
        ))

        myList.add(AddEditPermissions(
                PermissionData.bgDrawable[i],
                PermissionData.menuDrawable[i],
                PermissionData.menuName[i],
                tempMember.shoppingView,
                tempMember.shoppingAdd,
                tempMember.shoppingEdit
        ))

        mListsAdapter = AddOrEditPermissionAdapter(myList)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvPermissions!!.layoutManager = layoutManager
        rvPermissions!!.adapter = mListsAdapter

        chkAddOrRemoveUsers.isChecked = tempMember.addingRemovingMember
    }

    fun setPermissionsEdited( permissionEdited: PermissionEdited ) {
        this.mPermissionEdited = permissionEdited
    }
}