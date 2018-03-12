package com.ninebx.ui.home.account.addmembers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.DecryptedMember
import com.ninebx.ui.base.realm.decrypted.DecryptedUsers
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.account.interfaces.IMemberAdded
import com.ninebx.utility.AWSFileTransferHelper
import com.ninebx.utility.*
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_family_users.*
import java.io.File

/***
 * Created by TechnoBlogger on 15/01/18.
 */

class AddFamilyUsersFragment : FragmentBackHelper(), IMemberAdded, AWSFileTransferHelper.FileOperationsCompletionListener {

    private val ADD_EDIT_MEMBER = 4324
    private val DELETE_MEMBER = 4325

    override fun onMemberEdit(member: DecryptedMember?) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.MEMBER, member)
        bundle.putString(Constants.FROM_CLASS, "AddMember")
        bundle.putParcelable(Constants.CURRENT_USER, currentUsers!![0])
        bundle.putBoolean(Constants.IS_NEW_ACCOUNT, false)
        startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), ADD_EDIT_MEMBER)
    }

    override fun onMemberDelete(member: DecryptedMember?) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.MEMBER, member)
        bundle.putString(Constants.FROM_CLASS, "DeleteMember")
        startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), DELETE_MEMBER)
    }

    override fun onSuccess(outputFile: File?) {
        if (outputFile != null && imgProfilePic != null)
            Glide.with(context).asBitmap().load(outputFile).into(imgProfilePic)
    }

    override fun memberAdded(member: DecryptedMember?) {
        //AppLogger.d("Member", "onMemberAdded" + member)
        mListsAdapter!!.notifyDataSetChanged()
        saveUserObject()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_family_users, container, false)
    }

    private var mListsAdapter: AddedFamilyMemberAdapter? = null
    var myList: ArrayList<DecryptedMember> = ArrayList()
    var strAddItem = ""
    var strRelationship = ""
    var strRoles = ""

    private lateinit var mAWSFileTransferHelper: AWSFileTransferHelper

    private var currentUsers: ArrayList<DecryptedUsers>? = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (NineBxApplication.instance.activityInstance != null) {
            NineBxApplication.instance.activityInstance!!.hideBottomView()
        }
        ivHome.setOnClickListener {   NineBxApplication.instance.activityInstance!!.callHomeFragment() }
        ivBack.setOnClickListener { NineBxApplication.instance.activityInstance!!.onBackPressed() }
        mAWSFileTransferHelper = AWSFileTransferHelper(context!!)
        currentUsers = arguments!!.getParcelableArrayList<DecryptedUsers>(Constants.CURRENT_USER)
        myList.addAll(currentUsers!!.get(0).members)
        myList.remove(DecryptedMember(currentUsers!!.get(0).userId))
        mListsAdapter = AddedFamilyMemberAdapter(myList, this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvAddFamilyMembers!!.layoutManager = layoutManager
        rvAddFamilyMembers!!.adapter = mListsAdapter

        layAddFamilyMembers.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(Constants.MEMBER, DecryptedMember())
            bundle.putBoolean(Constants.IS_NEW_ACCOUNT, true)
            bundle.putString(Constants.FROM_CLASS, "AddMember")
            startActivityForResult(Intent(context, ContainerActivity::class.java).putExtras(bundle), ADD_EDIT_MEMBER)
        }

        initAdmin(currentUsers!![0])
    }

    private var usersRealm: Realm? = null

    private fun initAdmin(users: DecryptedUsers?) {
        txtProfileName.text = users!!.fullName
        txtProfileEmail.text = users.emailAddress

        mAWSFileTransferHelper.setFileTransferListener(this)
        if (users.profilePhoto.isNotEmpty())
            mAWSFileTransferHelper.beginDownload("images/" + users.userId + "/" + users.profilePhoto)

        // E/AWSFileTransferHelper: download : Error during upload: 1 : Exception : The object was stored using a form of Server Side Encryption. The correct parameters must be provided to retrieve the object. (Service: Amazon S3; Status Code: 400; Error Code: InvalidRequest; Request ID: 816FA6502DC28C41)

        prepareRealmConnectionsRealmThread(context, true, Constants.REALM_END_POINT_USERS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                usersRealm = realm
                val results = usersRealm!!.where(Member::class.java).distinctValues("userId").findAll()
                myList.clear()
                myList.addAll(decryptMembers(results!!)!!.toList())
                myList.remove(DecryptedMember(currentUsers!!.get(0).userId))
                for( member in myList ) {
                    AppLogger.d("Member", "List : " + member)
                }
                mListsAdapter!!.notifyDataSetChanged()
                //saveUserObject()
            }

        })

    }

    private fun saveUserObject() {
        val userObject = createUserObject(currentUsers!![0], myList)
        usersRealm!!.beginTransaction()
        usersRealm!!.insertOrUpdate(userObject)
        val usersList = NineBxApplication.instance.activityInstance!!.getCurrentUsers()
        usersList[0] = decryptUsers(userObject)
        NineBxApplication.instance.activityInstance!!.setCurrentUsers(usersList)

        context!!.hideProgressDialog()
        mListsAdapter!!.notifyDataSetChanged()
        usersRealm!!.commitTransaction()
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.showBottomView()
        return super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_EDIT_MEMBER && resultCode == Activity.RESULT_OK) {
            val member = data!!.getParcelableExtra<DecryptedMember>(Constants.MEMBER)
            mListsAdapter!!.insertMember(member)
            memberAdded(data!!.getParcelableExtra<DecryptedMember>(Constants.MEMBER))
        }
        else if ( requestCode == DELETE_MEMBER && resultCode == Activity.RESULT_OK ) {
            val member = data!!.getParcelableExtra<DecryptedMember>(Constants.MEMBER)
            val results = usersRealm!!.where(Member::class.java).equalTo("userId", member!!.userId).findAll()
            usersRealm!!.beginTransaction()
            results.deleteAllFromRealm()
            usersRealm!!.commitTransaction()
            mListsAdapter!!.deleteItem( member )
        }
        else
            super.onActivityResult(requestCode, resultCode, data)

    }
}