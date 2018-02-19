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
import com.ninebx.ui.base.kotlin.getImageUri
import com.ninebx.ui.base.kotlin.handleMultiplePermission
import com.ninebx.ui.base.kotlin.saveImage
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.home.calendar.events.AWSFileTransferHelper
import com.ninebx.ui.home.customView.CustomBottomSheetProfileDialogFragment
import com.ninebx.utility.*
import com.ninebx.utility.countryPicker.CountryPicker
import io.realm.Realm
import io.realm.SyncConfiguration
import io.realm.SyncCredentials
import io.realm.SyncUser
import kotlinx.android.synthetic.main.fragment_my_profile.*
import java.io.File
import java.util.*


/***
 * Created by TechnoBlogger on 15/01/18.
 */
class MyProfileFragment : FragmentBackHelper(), AWSFileTransferHelper.FileOperationsCompletionListener, CustomBottomSheetProfileDialogFragment.BottomSheetSelectedListener {

    override fun onSuccess(outputFile: File?) {
        if (outputFile != null && imgEditProfile != null)
            Glide.with(context).asBitmap().load(outputFile).into(imgEditProfile)
    }

    var strFullName = ""
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
    var idUser: Int? = null
    var idUserID = ""

    val prefrences = NineBxPreferences()
    var strUsername: String = "aman.shekhar@cognitiveclouds.com"
    var strPassword: String = "[219, 80, 120, 19, 74, 36, 40, 74, 173, 169, 201, 144, 10, 213, 102, 44, 154, 239, 237, 49, 132, 210, 196, 168, 186, 136, 44, 34, 0, 30, 35, 44]"

    var myCredentials: SyncCredentials? = null
    var user: SyncUser? = null
    var config: SyncConfiguration? = null
    lateinit var realm: Realm

    private var currentUsers: ArrayList<Users>? = ArrayList()
    private lateinit var mAWSFileTransferHelper: AWSFileTransferHelper


    private lateinit var selectedGender: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.showBackIcon()

        currentUsers = arguments!!.getParcelableArrayList<Users>(Constants.CURRENT_USER)
        mAWSFileTransferHelper = AWSFileTransferHelper(context!!)

        populateUserInfo(currentUsers!![0]) // Reading the User Data from Realm

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
            checkValidations()
        }

        ivBack.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        txtCountry.setOnClickListener {
            var countrySelected = prefrences.countrySelected
            if (countrySelected.toString().trim().isEmpty()) {
                txtCountry.hint = "Select Country"
            } else {
                txtCountry.text = countrySelected
            }
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.frameLayout, CountryPicker()).commit()
        }

        selectedGender = txtGender.selectedItem.toString()
        txtGender.prompt = "Gender"

    }

    private fun populateUserInfo(users: Users?) {
        idUser = users!!.id
        idUserID = users.userId
        edtFirstName.setText(users.firstName.decryptString())
        edtLastName.setText(users.lastName.decryptString())
        edtEmail.setText(users.emailAddress.decryptString())
        edtRelationship.setText(users.relationship.decryptString())
        txtDOB.text = users.dateOfBirth.decryptString()
        txtAnniversary.text = users.anniversary.decryptString()

        txtGender.prompt = users.gender.decryptString()
        edtMobileNumber.setText(users.mobileNumber.decryptString())
        edtAddress1.setText(users.street_1.decryptString())
        edtAddress2.setText(users.street_2.decryptString())
        edtCity.setText(users.city.decryptString())
        edtState.setText(users.state.decryptString())
        edtZipCode.setText(users.zipCode.decryptString())

        txtCountry.text = users.country.decryptString()

        mAWSFileTransferHelper.setFileTransferListener(this)
        if (users.profilePhoto.isNotEmpty())
            mAWSFileTransferHelper.beginDownload("images/" + users.id + "/" + users.profilePhoto)


    }

    private fun enableEditing() {
        NineBxApplication.instance.activityInstance!!.hideToolbar()
        toolbarProfile.show()
        imgEdit.setImageResource(R.drawable.ic_icon_save)
        txtUserName.setTextColor(resources.getColor(R.color.colorPrimary))
        edtFirstName.isEnabled = true
        edtLastName.isEnabled = true
        txtDOB.isClickable = true
        txtAnniversary.isClickable = true
        edtMobileNumber.isEnabled = true
        edtAddress1.isEnabled = true
        edtAddress2.isEnabled = true
        edtCity.isEnabled = true
        edtState.isEnabled = true
        txtCountry.isEnabled = true
        edtZipCode.isEnabled = true
    }

    private fun checkValidations() {

        strFirstName = edtFirstName.text.toString()
        strLastName = edtLastName.text.toString()
        strDOB = txtDOB.text.toString()
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
            return
        }

        if (strDOB.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Date of birth'", Toast.LENGTH_LONG).show()
            txtDOB.requestFocus()
            return
        }

        if (!(txtGender != null && txtGender.selectedItem.toString() != null)) {
            Toast.makeText(context, "Please enter 'Gender'", Toast.LENGTH_LONG).show()
            txtGender.requestFocus()
            return
        }

        if (strMobileNumber.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Mobile number'", Toast.LENGTH_LONG).show()
            edtMobileNumber.requestFocus()
            return
        }

        if (strStreetAddress1.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Street address 1'", Toast.LENGTH_LONG).show()
            edtAddress1.requestFocus()
            return
        }

        if (strStreetAddress2.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'City'", Toast.LENGTH_LONG).show()
            edtCity.requestFocus()
            return
        }

        if (strState.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'State'", Toast.LENGTH_LONG).show()
            edtState.requestFocus()
            return
        }

        if (strZipCode.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Zip code'", Toast.LENGTH_LONG).show()
            edtZipCode.requestFocus()
            return
        }

        updateTheUserInfo() // Updating the User Info to Realm

    }

    private fun updateTheUserInfo() {
//        var users = Users()
//        users.id = idUser
//        users.userId = idUserID
//        users.firstName = edtFirstName.text.toString().decryptString()
//        users.lastName = edtLastName.text.toString().decryptString()
//        users.dateOfBirth = txtDOB.text.toString().decryptString()
//        users.anniversary = txtAnniversary.text.toString().decryptString()
//        users.mobileNumber = edtMobileNumber.text.toString().decryptString()
//        users.street_1 = edtAddress1.text.toString().decryptString()
//        users.street_2 = edtAddress2.text.toString().decryptString()
//        users.city = edtCity.text.toString().decryptString()
//        users.state = edtState.text.toString().decryptString()
//        users.country = txtCountry.text.toString().decryptString()


        prepareRealmConnections(context, false, Constants.REALM_END_POINT_USERS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val updatingUserInfo = realm!!.where(Users::class.java).equalTo("userId", idUserID).findFirst()
                realm.beginTransaction()

                updatingUserInfo!!.firstName = strFirstName.encryptString()
                updatingUserInfo.lastName = strLastName.encryptString()
                updatingUserInfo.dateOfBirth = strDOB.encryptString()
                updatingUserInfo.anniversary = strAnniversary.encryptString()
                updatingUserInfo.mobileNumber = strMobileNumber.encryptString()
                updatingUserInfo.street_1 = strStreetAddress1.encryptString()
                updatingUserInfo.street_2 = strStreetAddress2.encryptString()
                updatingUserInfo.city = strCity.encryptString()
                updatingUserInfo.state = strState.encryptString()
                updatingUserInfo.country = strCountry.encryptString()
//                realm.copyToRealmOrUpdate(updatingUserInfo)
                realm.commitTransaction()

                NineBxApplication.instance.activityInstance!!.onBackPressed()
            }
        })
    }


    @SuppressLint("StaticFieldLeak")
    inner class SyncTheDb : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg p0: String?): String {
            var Result: String = ""
            try {
                syncNow()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return Result
        }

    }

    private fun syncNow() {
        myCredentials = SyncCredentials.usernamePassword(strUsername, strPassword, false)
        user = SyncUser.login(myCredentials, Constants.SERVER_IP)
        config = SyncConfiguration.Builder(user, Constants.SERVER_URL + "Test")
                .waitForInitialRemoteData()
                .build()

        realm = Realm.getInstance(config)
    }

    private fun sendDataToServer() {
        realm = Realm.getInstance(config)

        realm.executeTransactionAsync({ bgRealm ->
            val user = bgRealm.createObject(Users::class.java)
            user.firstName = strFirstName // Just for testing, LGb5ps0HypDZMGF/llxgbg==

        }, {
            // Transaction was a success.
            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
        }, {
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
        })

    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.hideBackIcon()
        NineBxApplication.instance.activityInstance!!.showToolbar()
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.account))
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
}