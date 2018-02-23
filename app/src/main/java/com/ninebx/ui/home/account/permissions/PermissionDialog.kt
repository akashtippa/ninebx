package com.ninebx.ui.home.account.permissions


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.home.account.model.AddEditPermissions

/**
 * Created by Alok on 23/02/18.
 */
@SuppressLint("InflateParams")
class PermissionDialog(val context : Context, private var tempMember: Member  ) : PermissionsView {


    private var dialog: Dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)


    interface PermissionEdited {
        fun onPermissionEdited( member : Member)
    }

    private lateinit var mPermissionEdited : PermissionEdited
    private lateinit var myList: ArrayList<AddEditPermissions>
    private var mListsAdapter: AddOrEditPermissionAdapter? = null
    private lateinit var chkAddOrRemoveUsers : CheckBox


    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.fragment_permissions, null)
        dialog.setContentView(dialogView)
        initView( dialogView )
        showDialog()
    }

    private fun showDialog() {
        dialog.show()
    }

    private fun initView(dialogView: View) {
        dialogView.findViewById<ImageView>(R.id.ivBackPermissions).setOnClickListener {
            dialog.dismiss()
        }
        chkAddOrRemoveUsers = dialogView.findViewById(R.id.chkAddOrRemoveUsers)

        dialogView.findViewById<TextView>(R.id.txtSave).setOnClickListener {
            mListsAdapter!!.getPermissions( tempMember )
            tempMember.addingRemovingMember = chkAddOrRemoveUsers.isChecked
            mPermissionEdited.onPermissionEdited(tempMember)
            dialog.dismiss()
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

        val rvPermissions = dialogView.findViewById<RecyclerView>(R.id.rvPermissions)
        mListsAdapter = AddOrEditPermissionAdapter(myList)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvPermissions!!.layoutManager = layoutManager
        rvPermissions.adapter = mListsAdapter

        chkAddOrRemoveUsers.isChecked = tempMember.addingRemovingMember
    }


    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {

    }

    override fun onError(error: Int) {

    }

    fun setPermissionsEdited( permissionEdited: PermissionDialog.PermissionEdited) {
        this.mPermissionEdited = permissionEdited
    }
}