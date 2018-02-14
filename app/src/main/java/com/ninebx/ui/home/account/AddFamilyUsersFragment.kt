package com.ninebx.ui.home.account

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
import com.ninebx.ui.home.account.adapter.AddedFamilyMemberAdapter
import com.ninebx.ui.home.account.interfaces.IMemberAdded
import com.ninebx.ui.home.calendar.events.AWSFileTransferHelper
import com.ninebx.utility.*
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_family_users.*
import java.io.File
import kotlin.collections.ArrayList

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
        startActivityForResult( Intent( context, ContainerActivity::class.java).putExtras( bundle ), ADD_EDIT_MEMBER )
        if( NineBxApplication.getPreferences().currentStep < Constants.ALL_COMPLETE)
            NineBxApplication.getPreferences().currentStep = Constants.ALL_COMPLETE
    }

    override fun onSuccess(outputFile: File?) {
        if( outputFile != null && imgProfilePic != null )
            Glide.with(context).asBitmap().load(outputFile).into(imgProfilePic)
    }

    override fun memberAdded( member : Member? ) {
        AppLogger.d("Member", "onMemberAdded" + member)
        myList.add( member!! )
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

        if( NineBxApplication.instance.activityInstance != null ) {
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
            startActivityForResult( Intent( context, ContainerActivity::class.java).putExtras( bundle ), ADD_EDIT_MEMBER )

            if( NineBxApplication.getPreferences().currentStep < Constants.ALL_COMPLETE)
                NineBxApplication.getPreferences().currentStep = Constants.ALL_COMPLETE

        }

        initAdmin( currentUsers!![0] )
    }

    private var usersRealm: Realm? = null

    private fun initAdmin(users: Users?) {

        txtProfileName.text = users!!.fullName.decryptString()
        txtProfileEmail.text = users.emailAddress.decryptString()

        mAWSFileTransferHelper.setFileTransferListener(this)
        if( users.profilePhoto.isNotEmpty() )
            mAWSFileTransferHelper.beginDownload( "images/" + users.userId + "/" + users.profilePhoto)

        prepareRealmConnections( context, true, "Users", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                usersRealm = realm
                saveUserObject()
            }

        })

    }

    private fun saveUserObject() {
        val userObject = Users.createUserObject( currentUsers!![0], myList )
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

    private fun openStaticLayoutDialog() {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.fragment_add_family_member)
        dialog.show()

        val imgBack = dialog.findViewById<View>(R.id.ivBackAddOthers) as ImageView
        imgBack.setOnClickListener {
            dialog.cancel()
        }
        val txtSave = dialog.findViewById<View>(R.id.txtSave) as TextView
        val txtFirstName = dialog.findViewById<View>(R.id.txtFirstName) as TextView
        val txtLastName = dialog.findViewById<View>(R.id.txtLastName) as TextView
        val txtPermissions = dialog.findViewById<View>(R.id.txtPermissions) as TextView
        val edtEmailAddress = dialog.findViewById<View>(R.id.edtEmailAddress) as TextView
        val txtRelationship = dialog.findViewById<View>(R.id.txtRelationship) as Spinner
        val txtsRole = dialog.findViewById<View>(R.id.txtsRole) as Spinner
        val layoutOtherViews = dialog.findViewById<View>(R.id.layoutOtherViews) as LinearLayout
        val layNonUser = dialog.findViewById<View>(R.id.layNonUser) as LinearLayout


        txtRelationship.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                // your code here
                val newValue = txtRelationship.getItemAtPosition(position) as String
                if (newValue == "Pet") {
                    strRelationship = newValue
                    layoutOtherViews.hide()
                } else {
                    strRelationship = newValue
                    layoutOtherViews.show()
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                Toast.makeText(context, "Please enter 'Relationship'", Toast.LENGTH_LONG).show()
            }

        }

        txtsRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                // your code here
                val newRoles = txtsRole.getItemAtPosition(position) as String
                if (newRoles == "Non-user") {
                    strRoles = newRoles
                    layNonUser.hide()
                } else {
                    strRoles = newRoles
                    layNonUser.show()
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
                Toast.makeText(context, "Please enter 'Role'", Toast.LENGTH_LONG).show()

            }

        }

        txtPermissions.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.frameLayout, PermissionFragment()).commit()
            dialog.dismiss()
        }

        txtSave.setOnClickListener {
            if (txtFirstName.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Please enter 'First name'", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (txtLastName.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Please enter 'Last name'", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (strRelationship.isEmpty()) {
                Toast.makeText(context, "Please enter 'Relationship'", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (strRoles.isEmpty()) {
                Toast.makeText(context, "Please enter 'Role'", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (edtEmailAddress.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Please enter 'Email address'", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            // Setting Data
            val mLog = Member()
            mLog.firstName = txtFirstName.text.toString()
            mLog.lastName = txtLastName.text.toString()
            mLog.relationship = strRelationship
            mLog.email = edtEmailAddress.text.toString()
            mLog.role = strRoles
            myList.add(mLog)
            mListsAdapter!!.notifyDataSetChanged()
            dialog.dismiss()


        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if( requestCode == ADD_EDIT_MEMBER && resultCode == Activity.RESULT_OK ) {
            memberAdded( data!!.getParcelableExtra( Constants.MEMBER ))
        }
        else
            super.onActivityResult(requestCode, resultCode, data)

    }
}