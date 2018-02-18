package com.ninebx.ui.home.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.home.account.contactsView.ContactsView
import com.ninebx.ui.home.calendar.events.AWSFileTransferHelper
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import io.realm.internal.SyncObjectServerFacade.getApplicationContext
import kotlinx.android.synthetic.main.fragment_level2_contacts.*
import java.io.*
import java.util.*


/***
 * Created by TechnoBlogger on 24/01/18.
 */
class SingleContactViewFragment : FragmentBackHelper(), AWSFileTransferHelper.FileOperationsCompletionListener {
    override fun onSuccess(outputFile: File?) {

    }

    private var userChoosenTask = ""

    private val REQUEST_CAMERA = 0
    private val SELECT_FILE = 1
    var strContactName = ""
    var strContactNumber = ""

    var strFullName = ""
    var strFirstName = ""
    var strLastName = ""
    var strBirthday = ""
    var strAnniversary = ""
    var strPhone1 = ""
    var strPhone2 = ""
    var strEmail1 = ""
    var strEmail2 = ""
    var strStreetAddress1 = ""
    var strStreetAddress2 = ""
    var strCity = ""
    var strState = ""
    var strZipCode = ""
    var strCountry = ""

    private lateinit var mContacts: Contacts
    private lateinit var mAWSFileTransferHelper: AWSFileTransferHelper
    private lateinit var mContactsView: ContactsView

    var contactOperation = ""
    var contactID = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_level2_contacts, container, false)
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ContactsView) {
            mContactsView = context
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.showToolbar()
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Shared Contact")

        mContacts = arguments!!.getParcelable(Constants.CONTACTS_VIEW)
        mAWSFileTransferHelper = AWSFileTransferHelper(context!!)

        contactOperation = arguments!!.getString("ContactOperation")
        contactID = arguments!!.getString("ID")

        edtFirstName.setText(strContactName)
        edtMobileOne.setText(strContactNumber)

        ivBackContactView.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

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

        txtSaveContacts.setOnClickListener {
            saveTheEditedContacts()
        }

        imgEditProfile.setOnClickListener {
            selectImage()
        }

        populateView(mContacts)

    }


    private var contactList: RealmResults<Contacts>? = null
    private var contacts: ArrayList<Contacts>? = ArrayList()


    private fun saveTheEditedContacts() {
        strFirstName = edtFirstName.text.toString()
        strLastName = edtLastName.text.toString()
        strFullName = strFirstName + strLastName
        strBirthday = txtDOB.text.toString()
        strAnniversary = txtAnniversary.text.toString()
        strPhone1 = edtMobileOne.text.toString()
        strPhone2 = edtMobileTwo.text.toString()
        strEmail1 = edtEmail1.text.toString()
        strEmail2 = edtEmail2.text.toString()
        strStreetAddress1 = txtAddress1.text.toString()
        strStreetAddress2 = txtAddress2.text.toString()
        strCity = edtCity.text.toString()
        strState = edtState.text.toString()
        strZipCode = edtZipCode.text.toString()
        strCountry = edtCountry.text.toString()

        if (strFirstName.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'First name'", Toast.LENGTH_LONG).show()
            return
        }

        if (strPhone1.trim().isEmpty()) {
            Toast.makeText(context, "Please enter Mobile Number'", Toast.LENGTH_LONG).show()
            return
        }

        var contacts = Contacts()

        if (contactID.trim() == "0") {
            contacts.id = getUniqueId()
            contacts.firstName = strFirstName.encryptString()
            contacts.lastName = strLastName.encryptString()
            contacts.dateOfBirth = strBirthday.encryptString()
            contacts.anniversary = strAnniversary.encryptString()
            contacts.mobileOne = strPhone1.encryptString()
            contacts.mobileTwo = strPhone2.encryptString()
            contacts.emailOne = strEmail1.encryptString()
            contacts.emailTwo = strEmail2.encryptString()
            contacts.streetAddressOne = strStreetAddress1.encryptString()
            contacts.streetAddressTwo = strStreetAddress2.encryptString()
            contacts.city = strCity.encryptString()
            contacts.state = strState.encryptString()
            contacts.zipCode = strZipCode.encryptString()
            contacts.country = strCountry.encryptString()

            prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    contacts.insertOrUpdate(realm!!)
                    NineBxApplication.instance.activityInstance!!.onBackPressed()
                }

            })
        } else {
            contacts.id = contactID.toInt()
            prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    val contacts = realm!!.where(Contacts::class.java).equalTo("id", contactID.toInt()).findFirstAsync()
                    realm.beginTransaction()
                    AppLogger.e("Id ", " is " + contactID.toInt())
                    AppLogger.e("First Name ", " is " + strFirstName)
                    AppLogger.e("First Name ", " Encrypted is " + strFirstName.encryptString())
                    contacts!!.id = contactID.toInt()
                    contacts.firstName = strFirstName.encryptString()
                    contacts.lastName = strLastName.encryptString()
                    contacts.dateOfBirth = strBirthday.encryptString()
                    contacts.anniversary = strAnniversary.encryptString()
                    contacts.mobileOne = strPhone1.encryptString()
                    contacts.mobileTwo = strPhone2.encryptString()
                    contacts.emailOne = strEmail1.encryptString()
                    contacts.emailTwo = strEmail2.encryptString()
                    contacts.streetAddressOne = strStreetAddress1.encryptString()
                    contacts.streetAddressTwo = strStreetAddress2.encryptString()
                    contacts.city = strCity.encryptString()
                    contacts.state = strState.encryptString()
                    contacts.zipCode = strZipCode.encryptString()
                    contacts.country = strCountry.encryptString()
                    realm.commitTransaction()

                    NineBxApplication.instance.activityInstance!!.onBackPressed()
                }
            })
        }
//        mContactsView.onContacts(contacts)

    }

    private fun enableEditing() {
        NineBxApplication.instance.activityInstance!!.hideToolbar()
        toolbarContacts.show()
        imgEdit.setImageResource(R.drawable.ic_icon_save)
        txtUserName.setTextColor(resources.getColor(R.color.colorPrimary))
        edtFirstName.isEnabled = true
        edtLastName.isEnabled = true
        txtDOB.isClickable = true
        txtAnniversary.isClickable = true
        edtMobileOne.isEnabled = true
        edtMobileTwo.isEnabled = true

        edtEmail1.isEnabled = true
        edtEmail2.isEnabled = true
        txtAddress1.isEnabled = true
        txtAddress2.isEnabled = true
        edtCity.isEnabled = true
        edtState.isEnabled = true
        edtZipCode.isEnabled = true
        edtCountry.isEnabled = true
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        return super.onBackPressed()
    }

    private fun populateView(contacts: Contacts?) {

//        mAWSFileTransferHelper.setFileTransferListener(this)
//        if (contacts!!.photosId.isNotEmpty())
//            mAWSFileTransferHelper.beginDownload("images/" + contacts.id + "/" + contacts.photosId)

        var contactID = contacts!!.id
        if (contacts.firstName.isNotEmpty())
            edtFirstName.setText(contacts.firstName.decryptString())

        if (contacts.lastName.isNotEmpty())
            edtLastName.setText(contacts.lastName.decryptString())

        if (contacts.dateOfBirth.isNotEmpty())
            txtDOB.text = contacts.dateOfBirth.decryptString()

        if (contacts.anniversary.isNotEmpty())
            txtAnniversary.text = contacts.anniversary.decryptString()

        if (contacts.mobileOne.isNotEmpty())
            edtMobileOne.setText(contacts.mobileOne.decryptString())

        if (contacts.mobileTwo.isNotEmpty())
            edtMobileTwo.setText(contacts.mobileTwo.decryptString())

        if (contacts.emailOne.isNotEmpty())
            edtEmail1.setText(contacts.emailOne.decryptString())

        if (contacts.emailTwo.isNotEmpty())
            edtEmail2.setText(contacts.emailTwo.decryptString())

        if (contacts.streetAddressOne.isNotEmpty())
            txtAddress1.setText(contacts.streetAddressOne.decryptString())

        if (contacts.streetAddressTwo.isNotEmpty())
            txtAddress2.setText(contacts.streetAddressTwo.decryptString())

        if (contacts.city.isNotEmpty())
            edtCity.setText(contacts.city.decryptString())

        if (contacts.state.isNotEmpty())
            edtState.setText(contacts.state.decryptString())

        if (contacts.zipCode.isNotEmpty())
            edtZipCode.setText(contacts.zipCode.decryptString())

        if (contacts.country.isNotEmpty())
            edtCountry.setText(contacts.country.decryptString())
    }

    private fun selectImage() {
        val items = arrayOf<CharSequence>("Take Photo", "Choose from Library", "Cancel")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Add Photo!")
        builder.setItems(items, DialogInterface.OnClickListener { dialog, item ->
            val result = Utility.checkPermission(context)
            if (items[item] == "Take Photo") {
                userChoosenTask = "Take Photo"
                if (result)
                    cameraIntent()
            } else if (items[item] == "Choose from Library") {
                userChoosenTask = "Choose from Library"
                if (result)
                    galleryIntent()
            } else if (items[item] == "Cancel") {
                dialog.dismiss()
            }
        })
        builder.show()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (userChoosenTask.equals("Take Photo"))
                    cameraIntent()
                else if (userChoosenTask.equals("Choose from Library"))
                    galleryIntent()
            } else {
                //code for deny
            }
        }
    }

    private fun galleryIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE)
    }

    private fun cameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA)
    }


    private fun onCaptureImageResult(data: Intent) {
        val thumbnail = data.extras!!.get("data") as Bitmap
        val bytes = ByteArrayOutputStream()
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes)

        val destination = File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis().toString() + ".jpg")

        val fo: FileOutputStream
        try {
            destination.createNewFile()
            fo = FileOutputStream(destination)
            fo.write(bytes.toByteArray())
            fo.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        imgEditProfile.setImageBitmap(thumbnail)
    }

    private fun onSelectFromGalleryResult(data: Intent?) {

        var bm: Bitmap? = null
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().contentResolver, data.data)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        imgEditProfile.setImageBitmap(bm)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data)
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data!!)
        }
    }

}