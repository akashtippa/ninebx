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
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY
import android.provider.MediaStore
import android.service.autofill.Validators.and
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.account.contactsView.ContactsView
import com.ninebx.ui.home.account.interfaces.IContactsAdded
import com.ninebx.ui.home.account.interfaces.ICountrySelected
import com.ninebx.utility.AWSFileTransferHelper
import com.ninebx.utility.*
import com.ninebx.utility.countryPicker.Country
import com.ninebx.utility.countryPicker.CountryPicker
import com.onegravity.contactpicker.contact.Contact
import com.onegravity.contactpicker.core.ContactPickerActivity
import com.onegravity.contactpicker.group.Group
import io.realm.Realm
import io.realm.RealmResults
import io.realm.internal.SyncObjectServerFacade.getApplicationContext
import kotlinx.android.synthetic.main.fragment_level2_contacts.*
import java.io.*
import java.util.*


/***
 * Created by TechnoBlogger on 24/01/18.
 */
class SingleContactViewFragment : FragmentBackHelper(), AWSFileTransferHelper.FileOperationsCompletionListener, ICountrySelected {

    override fun onCountrySelected(strCountry: String?) {
        edtCountry.setText(strCountry)
    }

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
    val PICK_CONTACT = 1

    private lateinit var mContacts: Contacts
    private lateinit var mAWSFileTransferHelper: AWSFileTransferHelper
    private lateinit var mContactsView: ContactsView
    private var contactsRealm: Realm? = null

    var contactID: Long? = null

    private val COUNTRY = 3000

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

        contactID = arguments!!.getLong("ID")

        edtFirstName.setText(strContactName)
        edtMobileOne.setText(strContactNumber)

        ivBackContactView.setOnClickListener {
            activity!!.finish()
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

        ivHome.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
            activity!!.finish()
        }

        imgDelete.setOnClickListener {
            prepareRealmConnections(context, true, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    val contactDeleting = realm!!
                            .where(Contacts::class.java)
                            .equalTo("id", mContacts.id)
                            .findAll()
                    if (contactDeleting.isValid) {
                        realm.beginTransaction()
                        contactDeleting.deleteAllFromRealm()
                        realm.commitTransaction()
//                        activity!!.onBackPressed()
                        activity!!.finish()
                    }
                }
            })
        }

        NineBxApplication.instance.setCountrySelected(this)

        edtCountry.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.fragmentContainer, CountryPicker()).commit()
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
            Toast.makeText(context, "Please enter First name", Toast.LENGTH_LONG).show()
            return
        }

        if (strPhone1.trim().isEmpty()) {
            Toast.makeText(context, "Please enter Mobile Number'", Toast.LENGTH_LONG).show()
            return
        }


        if (contactID!!.equals("0")) {

            var contactsNew = Contacts()
            contactsNew.firstName = strFirstName.encryptString()
            contactsNew.lastName = strLastName.encryptString()
            contactsNew.dateOfBirth = strBirthday.encryptString()
            contactsNew.anniversary = strAnniversary.encryptString()
            contactsNew.mobileOne = strPhone1.encryptString()
            contactsNew.mobileTwo = strPhone2.encryptString()
            contactsNew.emailOne = strEmail1.encryptString()
            contactsNew.emailTwo = strEmail2.encryptString()
            contactsNew.streetAddressOne = strStreetAddress1.encryptString()
            contactsNew.streetAddressTwo = strStreetAddress2.encryptString()
            contactsNew.city = strCity.encryptString()
            contactsNew.state = strState.encryptString()
            contactsNew.zipCode = strZipCode.encryptString()
            contactsNew.country = strCountry.encryptString()
            contactsNew.selectionType = "Contacts".encryptString()

            contactsNew.id = UUID.randomUUID().hashCode().toLong()

            prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    contactsNew.insertOrUpdate(realm!!)
                    mContactsView.onContacts(contactsNew)
                }
            })
        } else {
            prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {

                    val contactsUpdating = realm!!
                            .where(Contacts::class.java)
                            .equalTo("id", mContacts.id)
                            .findAll()

                    if (contactsUpdating.isValid) {
                        realm.executeTransaction({
                            var contactsUpdate = Contacts()
                            contactsUpdate.id = contactID

                            contactsUpdate.firstName = strFirstName.encryptString()
                            contactsUpdate.lastName = strLastName.encryptString()
                            contactsUpdate.dateOfBirth = strBirthday.encryptString()
                            contactsUpdate.anniversary = strAnniversary.encryptString()
                            contactsUpdate.mobileOne = strPhone1.encryptString()
                            contactsUpdate.mobileTwo = strPhone2.encryptString()
                            contactsUpdate.emailOne = strEmail1.encryptString()
                            contactsUpdate.emailTwo = strEmail2.encryptString()
                            contactsUpdate.streetAddressOne = strStreetAddress1.encryptString()
                            contactsUpdate.streetAddressTwo = strStreetAddress2.encryptString()
                            contactsUpdate.city = strCity.encryptString()
                            contactsUpdate.state = strState.encryptString()
                            contactsUpdate.zipCode = strZipCode.encryptString()
                            contactsUpdate.country = strCountry.encryptString()
                            contactsUpdate.selectionType = "Contacts".encryptString()
                            realm.copyToRealmOrUpdate(contactsUpdate)
//                            activity!!.onBackPressed()
                            activity!!.finish()
                        })
                    }

                }
            })
        }
    }

    private fun enableEditing() {
        NineBxApplication.instance.activityInstance!!.hideToolbar()
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
        ivHome.hide()
        txtSaveContacts.show()
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        return super.onBackPressed()
    }

    private fun populateView(contacts: Contacts?) {

        var contactID = contacts!!.id
        if (contacts.firstName.isNotEmpty())
            edtFirstName.setText(contacts.firstName.decryptString())
        edtFirstName.setTextColor(resources.getColor(R.color.black))

        if (contacts.lastName.isNotEmpty())
            edtLastName.setText(contacts.lastName.decryptString())
        edtLastName.setTextColor(resources.getColor(R.color.black))

        if (contacts.dateOfBirth.isNotEmpty())
            txtDOB.text = contacts.dateOfBirth.decryptString()
        txtDOB.setTextColor(resources.getColor(R.color.black))

        if (contacts.anniversary.isNotEmpty())
            txtAnniversary.text = contacts.anniversary.decryptString()
        txtAnniversary.setTextColor(resources.getColor(R.color.black))

        if (contacts.mobileOne.isNotEmpty())
            edtMobileOne.setText(contacts.mobileOne.decryptString())
        edtMobileOne.setTextColor(resources.getColor(R.color.black))

        if (contacts.mobileTwo.isNotEmpty())
            edtMobileTwo.setText(contacts.mobileTwo.decryptString())
        edtMobileTwo.setTextColor(resources.getColor(R.color.black))

        if (contacts.emailOne.isNotEmpty())
            edtEmail1.setText(contacts.emailOne.decryptString())
        edtEmail1.setTextColor(resources.getColor(R.color.black))

        if (contacts.emailTwo.isNotEmpty())
            edtEmail2.setText(contacts.emailTwo.decryptString())
        edtEmail2.setTextColor(resources.getColor(R.color.black))

        if (contacts.streetAddressOne.isNotEmpty())
            txtAddress1.setText(contacts.streetAddressOne.decryptString())
        txtAddress1.setTextColor(resources.getColor(R.color.black))

        if (contacts.streetAddressTwo.isNotEmpty())
            txtAddress2.setText(contacts.streetAddressTwo.decryptString())
        txtAddress2.setTextColor(resources.getColor(R.color.black))

        if (contacts.city.isNotEmpty())
            edtCity.setText(contacts.city.decryptString())
        edtCity.setTextColor(resources.getColor(R.color.black))

        if (contacts.state.isNotEmpty())
            edtState.setText(contacts.state.decryptString())
        edtState.setTextColor(resources.getColor(R.color.black))

        if (contacts.zipCode.isNotEmpty())
            edtZipCode.setText(contacts.zipCode.decryptString())
        edtZipCode.setTextColor(resources.getColor(R.color.black))

        if (contacts.country.isNotEmpty())
            edtCountry.setText(contacts.country.decryptString())
        edtCountry.setTextColor(resources.getColor(R.color.black))

        mAWSFileTransferHelper.setFileTransferListener(this)
        if (contacts!!.photosId.isNotEmpty())
            mAWSFileTransferHelper.beginDownload("images/" + contacts.id + "/" + contacts.photosId)

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