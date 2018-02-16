package com.ninebx.ui.home.account.addmembers

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.*
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.home.account.permissions.PermissionFragment
import com.ninebx.ui.home.customView.CustomBottomSheetProfileDialogFragment
import com.ninebx.utility.Constants
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.decryptString
import com.ninebx.utility.encryptString
import io.realm.SyncUser
import kotlinx.android.synthetic.main.fragment_add_family_member.*
import java.util.*


/***
 * Created by TechnoBlogger on 18/01/18.
 */

class AddFamilyMemberOrUsersFragment : FragmentBackHelper(), CustomBottomSheetProfileDialogFragment.BottomSheetSelectedListener {

    private lateinit var selectedRelation: String
    private lateinit var selectedRole: String
    private lateinit var member: Member
    private var isNewAccount: Boolean = false
    private lateinit var memberPresenter: MemberPresenter
    private lateinit var memberView: MemberView
    private lateinit var adminId : String

    private var strFirstName = ""
    private var strLastName = ""
    private var strAccountHolder = ""
    private var strRole = ""
    private var strEmail = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_family_member, container, false)
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MemberView) {
            memberView = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adminId = SyncUser.currentUser().identity
        memberPresenter = MemberPresenter(memberView, SyncUser.currentUser(), adminId)
        bottomSheetDialogFragment = CustomBottomSheetProfileDialogFragment()
        bottomSheetDialogFragment.setBottomSheetSelectionListener(this)

        ivBackAddOthers.setOnClickListener {
            onBackPressed()
        }

        txtPermissions.setOnClickListener {

            if( NineBxApplication.disabledFeature ) {
                context!!.showToast("To be done")
                return@setOnClickListener
            }
            if( checkValidations() ) {

                memberPresenter.setPermissionsForMember( updateMember!!, strRole )

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                val permissionsFragment = PermissionFragment()
                val bundle = Bundle()
                bundle.putParcelable(Constants.MEMBER, member)
                permissionsFragment.arguments = bundle

                fragmentTransaction.replace(R.id.frameLayout, permissionsFragment).commit()
            }

        }

        member = arguments!!.getParcelable(Constants.MEMBER)
        isNewAccount = arguments!!.getBoolean(Constants.IS_NEW_ACCOUNT, false)
        selectedRelation = txtRelationship.selectedItem.toString()
        selectedRole = txtsRole.selectedItem.toString()

        txtRelationship.prompt = "Relationship"
        txtsRole.prompt = "Role"

        txtRelationship.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                // your code here
                val newValue = txtRelationship.getItemAtPosition(position) as String
                if (newValue == "Pet") {
                    layoutOtherViews.hide()
                } else {
                    layoutOtherViews.show()
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                Toast.makeText(context, "Please enter 'Relationship'", Toast.LENGTH_LONG).show()
            }

        }

        txtsRole.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                // your code here
                val newValue = txtsRole.getItemAtPosition(position) as String
                if (newValue == "Non-user") {
                    layNonUser.hide()
                } else {
                    layNonUser.show()
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
                Toast.makeText(context, "Please enter 'Role'", Toast.LENGTH_LONG).show()

            }

        }


        txtSave.setOnClickListener {
            if( checkValidations() ) {
                saveDetails()
            }
        }

        txtAddEditPhoto.setOnClickListener {
            startCameraIntent()
        }

        populateView(member)
    }

    private fun saveDetails() {
        if (isNewAccount)
            memberPresenter.saveToUserAccount(strEmail, arguments!!.getString(Constants.USER_PASSWORD))
        else {
            saveUpdatedMember( this@AddFamilyMemberOrUsersFragment.member.userId )
            memberView.onNewMember(updateMember!!)
        }
    }

    lateinit var bottomSheetDialogFragment: CustomBottomSheetProfileDialogFragment
    private val PICK_IMAGE_REQUEST = 238
    private val CAMERA_REQUEST_CODE = 239
    private val PERMISSIONS_REQUEST_CODE_CAMERA = 115
    private val PERMISSIONS_REQUEST_CODE_GALLERY = 116

    private fun startCameraIntent() {
        bottomSheetDialogFragment.show(childFragmentManager, bottomSheetDialogFragment.tag)
    }

    override fun onOptionSelected(position: Int) {
        bottomSheetDialogFragment.dismiss()
        if (position == 1) {
            val permissionList = arrayListOf<String>(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            if (!handleMultiplePermission(context!!, permissionList)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissionList.toTypedArray(), PERMISSIONS_REQUEST_CODE_CAMERA)
                } else {
                    beginCameraAttachmentFlow()
                }
            } else {
                beginCameraAttachmentFlow()
            }

        } else {
            val permissionList = arrayListOf<String>(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            if (!handleMultiplePermission(context!!, permissionList)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissionList.toTypedArray(), PERMISSIONS_REQUEST_CODE_GALLERY)
                } else {
                    beginGalleryAttachmentFlow()
                }
            } else {
                beginGalleryAttachmentFlow()
            }
        }
    }

    private fun beginGalleryAttachmentFlow() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun beginCameraAttachmentFlow() {

        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (callCameraIntent.resolveActivity(context!!.packageManager) != null) {
            startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (requestCode == PERMISSIONS_REQUEST_CODE_CAMERA) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                beginCameraAttachmentFlow()
            } else {
                Toast.makeText(context, "Some permissions were denied", Toast.LENGTH_LONG).show()
            }
        } else if (requestCode == PERMISSIONS_REQUEST_CODE_GALLERY) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                beginGalleryAttachmentFlow()
            } else {
                Toast.makeText(context, "Some permissions were denied", Toast.LENGTH_LONG).show()
            }
        }

    }

    //a Uri object to store file path
    private var filePath: Uri? = null
    private var mImagesList: ArrayList<Uri> = ArrayList()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            if (data.clipData != null) {
                val count = data.clipData.itemCount
                var currentItem = 0
                while (currentItem < count) {
                    val imageUri = data.clipData.getItemAt(currentItem).uri
                    mImagesList.add(imageUri)
                    setProfileImage(imageUri)
                    currentItem += 1
                }


            } else if (data.data != null) {
                mImagesList.add(data.data)
                setProfileImage(data.data)
            }
        } else if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            saveImage(data.extras.get("data") as Bitmap)
            filePath = activity!!.getImageUri(data.extras.get("data") as Bitmap)
            mImagesList.add(filePath!!)
            setProfileImage(filePath!!)
        } else
            super.onActivityResult(requestCode, resultCode, data)

    }

    private fun setProfileImage(imageUri: Uri) {
        Glide.with(context).load(imageUri).into(imgEditProfile)
    }

    private fun populateView(member: Member?) {

        if (member!!.firstName.isNotEmpty())
            txtFirstName.setText(member.firstName.decryptString())

        if (member.lastName.isNotEmpty())
            txtLastName.setText(member.lastName.decryptString())

        if (member.relationship.isNotEmpty())
            txtRelationship.prompt = member.relationship

        if (member.role.isNotEmpty())
            txtsRole.prompt = member.role

        if (member.email.isNotEmpty())
            edtEmailAddress.setText(member.email.decryptString())
    }


    private fun checkValidations() : Boolean {

        strFirstName = txtFirstName.text.toString()
        strLastName = txtLastName.text.toString()
        strAccountHolder = txtRelationship.selectedItem.toString()
        strRole = txtsRole.selectedItem.toString()
        strEmail = edtEmailAddress.text.toString()


        if (strFirstName.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'First name'", Toast.LENGTH_LONG).show()
            txtFirstName.requestFocus()
            return false
        }

        if (strLastName.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Last name'", Toast.LENGTH_LONG).show()
            txtLastName.requestFocus()
            return false
        }

        if ((txtRelationship.selectedItem.toString().trim() == "Relationship" || txtRelationship.selectedItem.toString().trim().isEmpty())) {
            Toast.makeText(context, "Please enter 'Relationship'", Toast.LENGTH_LONG).show()
            return false
        } else if ((txtRelationship.selectedItem.toString().trim() == "Pet")) {
            layoutOtherViews.hide()
        } else {
            if ((txtsRole.selectedItem.toString().trim() == "Role" || txtsRole.selectedItem.toString().trim().isEmpty())) {
                Toast.makeText(context, "Please enter 'Role'", Toast.LENGTH_LONG).show()
                return false
            } else if (txtsRole.selectedItem.toString().trim() == "Non-user") {
                layNonUser.hide()
            } else
                if (edtEmailAddress.text.toString().trim().isEmpty()) {
                    Toast.makeText(context, "Please enter 'Email address'", Toast.LENGTH_LONG).show()
                    edtEmailAddress.requestFocus()
                    return false
                }
        }

        return true




        // Add method to add it in a RecyclerView
        //NineBxApplication.instance.activityInstance!!.onBackPressed()



    }

    private fun saveUpdatedMember(userId: String?) {

        updateMember = Member()
        updateMember!!.userId = userId
        updateMember!!.firstName = strFirstName.encryptString()
        updateMember!!.lastName = strLastName.encryptString()
        updateMember!!.relationship = strAccountHolder.encryptString()
        updateMember!!.email = strEmail.encryptString()
        updateMember!!.role = strRole.encryptString()
        updateMember!!.relationship = txtRelationship.selectedItem.toString().encryptString()
        memberPresenter.setPermissionsForMember( updateMember!!, strRole )

    }


    override fun onBackPressed(): Boolean {
        activity!!.finish()
        return true
    }

    private var updateMember: Member ?= null

    fun onAccountCreated(user: SyncUser) {
        saveUpdatedMember(user.identity)
        memberView.onNewMember(updateMember!!)
        //update user and member permissions
        user.logout()
    }


}