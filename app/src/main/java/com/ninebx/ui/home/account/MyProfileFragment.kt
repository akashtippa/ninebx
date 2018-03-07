package com.ninebx.ui.home.account

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.*
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.DecryptedUsers
import com.ninebx.ui.home.account.interfaces.ICountrySelected
import com.ninebx.utility.AWSFileTransferHelper
import com.ninebx.ui.home.customView.CustomBottomSheetProfileDialogFragment
import com.ninebx.utility.*
import com.ninebx.utility.countryPicker.CountryPicker
import com.ninebx.utility.countryPicker.CountryPickerDialog
import io.realm.Realm
import io.realm.SyncUser
import kotlinx.android.synthetic.main.fragment_my_profile.*
import java.io.File
import java.util.*


/***
 * Created by TechnoBlogger on 15/01/18.
 */

class MyProfileFragment : FragmentBackHelper(), AWSFileTransferHelper.FileOperationsCompletionListener, CustomBottomSheetProfileDialogFragment.BottomSheetSelectedListener, ICountrySelected {

    override fun onCountrySelected(strCountry: String?) {
        Toast.makeText(context, "Selected Country is " + strCountry, Toast.LENGTH_LONG).show()
        txtCountry.setText(strCountry)
    }

    override fun onSuccess(outputFile: File?) {
        if (outputFile != null && imgEditProfile != null)
            Glide.with(context).asBitmap().load(outputFile).into(imgEditProfile)
    }

    var fromWhichClass = ""


    var strFirstName = ""
    var strLastName = ""
    var strEmail = ""
    var strRelationship = ""
    var strDOB = ""
    var strAnniversary = ""
    var strGender = ""
    var strMobileNumber = ""
    var strStreetAddress1 = ""
    var strStreetAddress2 = ""
    var strCity = ""
    var strState = ""
    var strZipCode = ""
    var strCountry = ""
    var idUser: Long? = null
    var idUserID = ""

    val prefrences = NineBxPreferences()
    var user: SyncUser? = null
    lateinit var realm: Realm

    private var currentUsers: ArrayList<DecryptedUsers>? = ArrayList()
    private lateinit var mAWSFileTransferHelper: AWSFileTransferHelper


    private lateinit var selectedGender: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()


        currentUsers = arguments!!.getParcelableArrayList<DecryptedUsers>(Constants.CURRENT_USER)
        mAWSFileTransferHelper = AWSFileTransferHelper(context!!)

        bottomSheetDialogFragment = CustomBottomSheetProfileDialogFragment()
        bottomSheetDialogFragment.setBottomSheetSelectionListener(this)

        fromWhichClass = arguments!!.getString("fromClass")

        ivHome.setOnClickListener { NineBxApplication.instance.activityInstance!!.onBackPressed() }
        ivCompleteHome.setOnClickListener{ NineBxApplication.instance.activityInstance!!.onBackPressed() }

        imgEdit.setOnClickListener {
            enableEditing()
        }

        txtDOB.setOnClickListener {
            getDateFromPicker(this.context!!, Calendar.getInstance(), object : DateTimeSelectionListener {
                override fun onDateTimeSelected(selectedDate: Calendar) {
                    txtDOB.text = (getDateMonthYearFormat(selectedDate.time))
                }
            })
        }

        txtAnniversary.setOnClickListener {
            getDateFromPicker(this.context!!, Calendar.getInstance(), object : DateTimeSelectionListener {
                override fun onDateTimeSelected(selectedDate: Calendar) {
                    txtAnniversary.text = (getDateMonthYearFormat(selectedDate.time))
                }
            })
        }

        txtSave.setOnClickListener {
            if (checkValidations()) {
                updateTheUserInfo()
            }
        }

        ivBack.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        NineBxApplication.instance.setCountrySelected(this)

        txtCountry.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            CountryPickerDialog(context!!, ICountrySelected {
                strCountry -> txtCountry.text = strCountry
                this.strCountry = strCountry
            })
        }

        imgEditProfile.setOnClickListener {
            startCameraIntent()
        }

        selectedGender = txtGender.selectedItem.toString()
        txtGender.prompt = "Gender"

        populateUserInfo(currentUsers!![0]) // Reading the User Data from Realm

        txtSaveCompletedProfile.setOnClickListener {
            if (checkValidations()) {
                updateTheUserInfo()
            }
        }

        when (fromWhichClass) {
            "Home" -> {
                toolbarProfile.hide()
                toolbarCompleteProfile.show()
                enableEditing()
                imgEdit.hide()
            }
            "Account" -> {
                toolbarProfile.show()
                toolbarCompleteProfile.hide()
                txtGender.isEnabled = false
                imgEdit.show()
            }
        }
    }


    private fun populateUserInfo(users: DecryptedUsers?) {
        AppLogger.d("MyProfile", "User : " + users)
        idUser = users!!.id
        idUserID = users.userId

        if (users.firstName.isNotEmpty())
            edtFirstName.setText(users.firstName)

        if (users.lastName.isNotEmpty())
            edtLastName.setText(users.lastName)

        if( users.emailAddress.isEmpty() ) users.emailAddress = NineBxApplication.getPreferences().userEmail

        if (users.emailAddress.isNotEmpty())
            edtEmail.setText(users.emailAddress)

        if (users.relationship.isNotEmpty())
            edtRelationship.setText(users.relationship)

        if (users.gender.isNotEmpty())
            txtGender.setSelection( context!!.resources.getStringArray(R.array.gender).indexOf(users.gender) )

        if (users.dateOfBirth.isNotEmpty())
            txtDOB.text = users.dateOfBirth

        if (users.anniversary.isNotEmpty())
            txtAnniversary.text = users.anniversary

        if (users.mobileNumber.isNotEmpty())
            edtMobileNumber.setText(users.mobileNumber)

        if (users.street_1.isNotEmpty())
            edtAddress1.setText(users.street_1)

        if (users.street_2.isNotEmpty())
            edtAddress2.setText(users.street_2)

        if (users.city.isNotEmpty())
            edtCity.setText(users.city)

        if (users.state.isNotEmpty())
            edtState.setText(users.state)

        if (users.zipCode.isNotEmpty())
            edtZipCode.setText(users.zipCode)

        if (users.country.isNotEmpty())
            txtCountry.text = users.country

        //mAWSFileTransferHelper.setFileTransferListener(this)
        /*val awsSecureFileTransfer = AWSSecureFileTransfer(context!!)
        awsSecureFileTransfer.setFileTransferListener(this)
        if (users.profilePhoto.isNotEmpty()) {
            awsSecureFileTransfer.downloadSecureFile("images/" + SyncUser.currentUser().identity + "/" + users.profilePhoto)
        }*/
        //mAWSFileTransferHelper.beginSecureDownload("images/" + SyncUser.currentUser().identity + "/" + users.profilePhoto)
    }

    private fun enableEditing() {

        if( fromWhichClass == "Account" )
            toolbarProfile.show()
        txtSave.show()
        ivHome.hide()
        imgEdit.setImageResource(R.drawable.ic_icon_save)
        txtUserName.setTextColor(resources.getColor(R.color.colorPrimary))
        edtFirstName.isEnabled = true
        edtLastName.isEnabled = true
        txtDOB.isEnabled = true
        txtAnniversary.isEnabled = true
        txtGender.isEnabled = true
        edtMobileNumber.isEnabled = true
        edtAddress1.isEnabled = true
        edtAddress2.isEnabled = true
        edtCity.isEnabled = true
        edtState.isEnabled = true
        txtCountry.isEnabled = true
        edtZipCode.isEnabled = true
    }

    private fun checkValidations(): Boolean {

        strFirstName = edtFirstName.text.toString()
        strLastName = edtLastName.text.toString()
        strEmail = edtEmail.text.toString()
        strRelationship = edtRelationship.text.toString()
        strDOB = txtDOB.text.toString()
        strGender = txtGender.selectedItem.toString()
        strAnniversary = txtAnniversary.text.toString()
        strMobileNumber = edtMobileNumber.text.toString()
        strStreetAddress1 = edtAddress1.text.toString()
        strStreetAddress2 = edtAddress2.text.toString()
        strCity = edtCity.text.toString()
        strState = edtState.text.toString()
        strZipCode = edtZipCode.text.toString()
        strCountry = txtCountry.text.toString()


        if (strFirstName.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'First name'", Toast.LENGTH_LONG).show()
            edtFirstName.requestFocus()
            return false
        }

        if (strDOB.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Date of birth'", Toast.LENGTH_LONG).show()
            txtDOB.requestFocus()
            return false
        }

        if (!(txtGender != null && txtGender.selectedItem.toString() != "Gender")) {
            Toast.makeText(context, "Please enter 'Gender'", Toast.LENGTH_LONG).show()
            txtGender.requestFocus()
            return false
        }

        if (strMobileNumber.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Mobile number'", Toast.LENGTH_LONG).show()
            edtMobileNumber.requestFocus()
            return false
        }

        if (strStreetAddress1.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Street address 1'", Toast.LENGTH_LONG).show()
            edtAddress1.requestFocus()
            return false
        }

        if (strStreetAddress2.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'City'", Toast.LENGTH_LONG).show()
            edtCity.requestFocus()
            return false
        }

        if (strState.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Street address 2'", Toast.LENGTH_LONG).show()
            edtAddress2.requestFocus()
            return false
        }

        if (strZipCode.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Zip code'", Toast.LENGTH_LONG).show()
            edtZipCode.requestFocus()
            return false
        }

        return true
//        updateTheUserInfo() // Updating the User Info to Realm

    }

    private val TAG = "Profile"
    @SuppressLint("StaticFieldLeak")
    private fun updateTheUserInfo() {
        context!!.showProgressDialog(getString(R.string.saving_user))
        prepareRealmConnectionsRealmThread(context, false, Constants.REALM_END_POINT_USERS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {

                val users = realm!!.where(Users::class.java).equalTo("userId", idUserID).findFirst()
                realm.beginTransaction()

                users!!.firstName = strFirstName.encryptString()
                users.lastName = strLastName.encryptString()
                users.fullName = (strFirstName + " " + strLastName).encryptString()
                users.emailAddress = strEmail.encryptString()
                users.relationship = strRelationship.encryptString()
                users.dateOfBirth = strDOB.encryptString()
                users.anniversary = strAnniversary.encryptString()
                users.gender = strGender.encryptString()
                users.mobileNumber = strMobileNumber.encryptString()
                users.street_1 = strStreetAddress1.encryptString()
                users.street_2 = strStreetAddress2.encryptString()
                users.city = strCity.encryptString()
                users.state = strState.encryptString()
                users.zipCode = strZipCode.encryptString()
                users.country = strCountry.encryptString()
                users.completeProfile = true
                //                realm.copyToRealmOrUpdate(updatingUserInfo)
                realm.insertOrUpdate(users)
                realm.commitTransaction()
                val isCompleteProfile = toolbarCompleteProfile.isVisible()
                NineBxApplication.instance.activityInstance!!.getCurrentUsers()[0] = decryptUsers(users)
                context!!.hideProgressDialog()

                NineBxApplication.instance.activityInstance!!.onBackPressed()
                if( isCompleteProfile )
                    NineBxApplication.instance.activityInstance!!.navigateToAddMembers()
            }
        })

    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.showBottomView()


        //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.account))
        return super.onBackPressed()
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

    override fun onResume() {
        super.onResume()
    }

}