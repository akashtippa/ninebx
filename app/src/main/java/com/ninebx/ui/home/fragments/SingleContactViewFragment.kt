package com.ninebx.ui.home.fragments

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.utility.Constants
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import io.realm.SyncConfiguration
import io.realm.SyncCredentials
import io.realm.SyncUser
import kotlinx.android.synthetic.main.fragment_level2_contacts.*
import kotlinx.android.synthetic.main.fragment_test_a.*

/***
 * Created by TechnoBlogger on 24/01/18.
 */
class SingleContactViewFragment : FragmentBackHelper() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_level2_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.showToolbar()
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Shared Contact")

        strContactName = arguments!!.getString(Constants.BUNDLE_CONTACT_NAME)
        strContactNumber = arguments!!.getString(Constants.BUNDLE_CONTACT_NO)

        mContacts = arguments!!.getParcelable(Constants.CONTACTS)


        txtFirstName.setText(strContactName)
        txtMobileNumber.setText(strContactNumber)

        ivBackContactView.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        imgEdit.setOnClickListener {
            enableEditing()
        }

        SyncTheDb().execute()

        txtSaveContacts.setOnClickListener {
            saveTheEditedContacts()
        }


    }

    private fun saveTheEditedContacts() {
        strFirstName = edtFirstName.text.toString()
        strLastName = edtFirstName.text.toString()
        strFullName = strFirstName + strLastName
        strBirthday = txtDOB.text.toString()
        strAnniversary = txtAnniversary.text.toString()
        strPhone1 = txtMobileNumber.text.toString()
        strPhone2 = txtMobileNumber2.text.toString()
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

        sendDataToServer()

        prepareRealmConnections(context, false, "Contacts", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {

            }

        })
    }


    private fun enableEditing() {
        NineBxApplication.instance.activityInstance!!.hideToolbar()
        toolbarContacts.show()
        imgEdit.setImageResource(R.drawable.ic_icon_save)
        txtUserName.setTextColor(resources.getColor(R.color.colorPrimary))
        txtFirstName.isEnabled = true
        txtLastName.isEnabled = true
        txtDOB.isClickable = true
        txtAnniversary.isClickable = true
        txtMobileNumber.isEnabled = true
        edtCountry.isEnabled = true

    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        return super.onBackPressed()
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


    var strUsername: String = "aman.shekhar@cognitiveclouds.com"
    var strPassword: String = "[219, 80, 120, 19, 74, 36, 40, 74, 173, 169, 201, 144, 10, 213, 102, 44, 154, 239, 237, 49, 132, 210, 196, 168, 186, 136, 44, 34, 0, 30, 35, 44]"

    var myCredentials: SyncCredentials? = null
    var user: SyncUser? = null
    var config: SyncConfiguration? = null
    lateinit var realm: Realm


    private fun syncNow() {
        myCredentials = SyncCredentials.usernamePassword(strUsername, strPassword, false)
        user = SyncUser.login(myCredentials, Constants.SERVER_IP)
        config = SyncConfiguration.Builder(user, Constants.SERVER_URL + "Contacts")
                .waitForInitialRemoteData()
                .build()

        realm = Realm.getInstance(config)
    }

    private fun sendDataToServer() {
        realm = Realm.getInstance(config)

        realm.executeTransactionAsync({ bgRealm ->
            val contacts = bgRealm.createObject(Contacts::class.java)
            contacts.firstName = mContacts.firstName
            contacts.lastName = mContacts.lastName
            contacts.dateOfBirth = mContacts.dateOfBirth
            contacts.anniversary = mContacts.anniversary
            contacts.mobileOne = mContacts.mobileOne
            contacts.mobileTwo = mContacts.mobileTwo
            contacts.emailOne = mContacts.emailOne
            contacts.emailTwo = mContacts.emailTwo
            contacts.streetAddressOne = mContacts.streetAddressOne
            contacts.streetAddressTwo = mContacts.streetAddressTwo
            contacts.city = mContacts.city
            contacts.state = mContacts.state
            contacts.zipCode = mContacts.zipCode
            contacts.country = mContacts.country

        }, {
            Toast.makeText(context, "Contacts Added Successfully'", Toast.LENGTH_LONG).show()
        }, {
            Toast.makeText(context, "Contacts Not Added'", Toast.LENGTH_LONG).show()
        })

    }


}