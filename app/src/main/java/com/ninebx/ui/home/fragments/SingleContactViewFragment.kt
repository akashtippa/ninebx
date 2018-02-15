package com.ninebx.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_level2_contacts.*
import java.util.*


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

//        mContacts = arguments!!.getParcelable(Constants.CONTACTS)


        edtFirstName.setText(strContactName)
        txtMobileNumber.setText(strContactNumber)

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

        fetchTheContactListFromRealm()

    }


    private var contactList: RealmResults<Contacts>? = null
    private var contacts: ArrayList<Contacts>? = ArrayList()


    private fun fetchTheContactListFromRealm() {
        prepareRealmConnections(context, false, Constants.REALM_END_POINT_CONTACTS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                contactList = getCurrentContactList(realm!!)
                contacts!!.clear()
                for (contact in contactList!!.iterator()) {
                    contacts!!.add(contact)
                    strFirstName = contact.firstName.decryptString()
                    strLastName = contact.lastName.decryptString()
                    strBirthday = contact.dateOfBirth.decryptString()
                    strAnniversary = contact.anniversary.decryptString()
                    strPhone1 = contact.mobileOne.decryptString()
                    strPhone2 = contact.mobileTwo.decryptString()
                    strEmail1 = contact.emailOne.decryptString()
                    strEmail2 = contact.emailTwo.decryptString()
                    strStreetAddress1 = contact.streetAddressOne.decryptString()
                    strStreetAddress2 = contact.streetAddressTwo.decryptString()
                    strCity = contact.city.decryptString()
                    strState = contact.state.decryptString()
                    strZipCode = contact.zipCode.decryptString()
                    strCountry = contact.country.decryptString()

                    AppLogger.e("Contacts", "First Name : " + strFirstName)
                    AppLogger.e("Contacts", "Last Name : " + strLastName)
                    AppLogger.e("Contacts", "DOB : " + strBirthday)
                    AppLogger.e("Contacts", "Anniversary : " + strAnniversary)
                    AppLogger.e("Contacts", "Phone 1 : " + strPhone1)
                    AppLogger.e("Contacts", "Phone 2 : " + strPhone2)
                    AppLogger.e("Contacts", "Email 1 : " + strEmail1)
                    AppLogger.e("Contacts", "Email 2 : " + strEmail2)
                    AppLogger.e("Contacts", "Address 1 : " + strStreetAddress1)
                    AppLogger.e("Contacts", "Address 2 : " + strStreetAddress2)
                    AppLogger.e("Contacts", "City : " + strCity)
                    AppLogger.e("Contacts", "State : " + strState)
                    AppLogger.e("Contacts", "Zip Code : " + strZipCode)
                    AppLogger.e("Contacts", "Country : " + strCountry)

                }

                setContactDetails(contactList!![0])

//                edtFirstName.setText(strFirstName)
//                edtLastName.setText(strFirstName)
//                txtDOB.setText(strBirthday)
//                txtAnniversary.setText(strAnniversary)
//                txtMobileNumber.setText(strPhone1)
//                txtMobileNumber2.setText(strPhone2)
//                edtEmail1.setText(strEmail1)
//                edtEmail2.setText(strEmail2)
//                txtAddress1.setText(strStreetAddress1)
//                txtAddress2.setText(strStreetAddress2)
//                edtCity.setText(strCity)
//                edtState.setText(strState)
//                edtZipCode.setText(strZipCode)
//                edtCountry.setText(strCountry)
//                AppLogger.e("Contacts", "Contacts Results : " + contactList)
            }
        })
    }

    private fun setContactDetails(contacts: Contacts?) {
        edtFirstName.setText(contacts!!.firstName.decryptString())
        edtLastName.setText(contacts.lastName.decryptString())
        txtDOB.text = contacts.dateOfBirth.decryptString()
        txtAnniversary.text = contacts.anniversary.decryptString()
        txtMobileNumber.setText(contacts.mobileOne.decryptString())
        txtMobileNumber2.setText(contacts.mobileTwo.decryptString())
        edtEmail1.setText(contacts.emailOne.decryptString())
        edtEmail2.setText(contacts.emailTwo.decryptString())
        txtAddress1.setText(contacts.streetAddressOne.decryptString())
        txtAddress2.setText(contacts.streetAddressTwo.decryptString())
        edtCity.setText(contacts.city.decryptString())
        edtState.setText(contacts.state.decryptString())
        edtZipCode.setText(contacts.zipCode.decryptString())
        edtCountry.setText(contacts.country.decryptString())

    }


    private fun saveTheEditedContacts() {
        strFirstName = edtFirstName.text.toString()
        strLastName = edtLastName.text.toString()
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

        var contacts = Contacts()
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

//        contacts.firstName = strFirstName
//        contacts.lastName = strLastName
//        contacts.dateOfBirth = strBirthday
//        contacts.anniversary = strAnniversary
//        contacts.mobileOne = strPhone1
//        contacts.mobileTwo = strPhone2
//        contacts.emailOne = strEmail1
//        contacts.emailTwo = strEmail2
//        contacts.streetAddressOne = strStreetAddress1
//        contacts.streetAddressTwo = strStreetAddress2
//        contacts.city = strCity
//        contacts.state = strState
//        contacts.zipCode = strZipCode
//        contacts.country = strCountry

        prepareRealmConnections(context, false, Constants.REALM_END_POINT_CONTACTS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {

//                contacts = encryptContact(contacts)
                contacts.insertOrUpdate(realm!!)
                NineBxApplication.instance.activityInstance!!.onBackPressed()
            }
        })
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
        txtMobileNumber.isEnabled = true
        txtMobileNumber2.isEnabled = true

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
}