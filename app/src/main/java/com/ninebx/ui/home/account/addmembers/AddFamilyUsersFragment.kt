package com.ninebx.ui.home.account.addmembers

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import com.bumptech.glide.Glide
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.account.interfaces.IMemberAdded
import com.ninebx.ui.home.account.permissions.PermissionFragment
import com.ninebx.ui.home.calendar.events.AWSFileTransferHelper
import com.ninebx.utility.*
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_family_users.*
import java.io.File

/***
 * Created by TechnoBlogger on 15/01/18.
 */

class AddFamilyUsersFragment : FragmentBackHelper(), IMemberAdded, AWSFileTransferHelper.FileOperationsCompletionListener {

    private val ADD_EDIT_MEMBER = 4324

    override fun onMemberEdit(member: Member?) {
        myList.remove(member)
        mListsAdapter!!.notifyDataSetChanged()
        val bundle = Bundle()
        bundle.putParcelable(Constants.MEMBER, member)
        bundle.putBoolean(Constants.IS_NEW_ACCOUNT, false)
        startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), ADD_EDIT_MEMBER)
    }

    override fun onSuccess(outputFile: File?) {
        if (outputFile != null && imgProfilePic != null)
            Glide.with(context).asBitmap().load(outputFile).into(imgProfilePic)
    }

    override fun memberAdded(member: Member?) {
        AppLogger.d("Member", "onMemberAdded" + member)
        myList.add(member!!)
        mListsAdapter!!.notifyDataSetChanged()
        saveUserObject()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_family_users, container, false)
    }

    private var mListsAdapter: AddedFamilyMemberAdapter? = null
    var myList: ArrayList<Member> = ArrayList()
    var strAddItem = ""
    var strRelationship = ""
    var strRoles = ""

    private lateinit var mAWSFileTransferHelper: AWSFileTransferHelper

    private var currentUsers: ArrayList<Users>? = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (NineBxApplication.instance.activityInstance != null) {
            NineBxApplication.instance.activityInstance!!.hideBottomView()
            NineBxApplication.instance.activityInstance!!.showBackIcon()
        }

        mAWSFileTransferHelper = AWSFileTransferHelper(context!!)
        currentUsers = arguments!!.getParcelableArrayList<Users>(Constants.CURRENT_USER)
        myList.addAll(currentUsers!!.get(0).members)

        mListsAdapter = AddedFamilyMemberAdapter(myList, this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvAddFamilyMembers!!.layoutManager = layoutManager
        rvAddFamilyMembers!!.adapter = mListsAdapter

        layAddFamilyMembers.setOnClickListener {

            val bundle = Bundle()
            bundle.putParcelable(Constants.MEMBER, Member())
            bundle.putBoolean(Constants.IS_NEW_ACCOUNT, true)
            startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), ADD_EDIT_MEMBER)

        }

        initAdmin(currentUsers!![0])
    }

    private var usersRealm: Realm? = null

    private fun initAdmin(users: Users?) {
        txtProfileName.text = users!!.fullName.decryptString()
        txtProfileEmail.text = users.emailAddress.decryptString()

        mAWSFileTransferHelper.setFileTransferListener(this)
        if (users.profilePhoto.isNotEmpty())
            mAWSFileTransferHelper.beginDownload("images/" + users.userId + "/" + users.profilePhoto)

        prepareRealmConnections(context, true, "Users", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                usersRealm = realm
                saveUserObject()
            }

        })

    }

    private fun saveUserObject() {
        val userObject = Users.createUserObject(currentUsers!![0], myList)
        userObject.insertOrUpdate(usersRealm!!)
        context!!.hideProgressDialog()
        myList.clear()
        myList.addAll(userObject.members)
        mListsAdapter!!.notifyDataSetChanged()
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.hideBackIcon()
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.account))
        return super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_EDIT_MEMBER && resultCode == Activity.RESULT_OK) {
            memberAdded(data!!.getParcelableExtra(Constants.MEMBER))
        } else
            super.onActivityResult(requestCode, resultCode, data)

    }
}