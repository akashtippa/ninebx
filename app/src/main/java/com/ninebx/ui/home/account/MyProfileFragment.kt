package com.ninebx.ui.home.account

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
import com.ninebx.ui.base.realm.Users
import com.ninebx.utility.*
import com.ninebx.utility.countryPicker.CountryPicker
import io.realm.Realm
import io.realm.SyncConfiguration
import io.realm.SyncCredentials
import io.realm.SyncUser
import kotlinx.android.synthetic.main.fragment_my_profile.*
import java.util.*


/***
 * Created by TechnoBlogger on 15/01/18.
 */
class MyProfileFragment : FragmentBackHelper() {

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

    val prefrences = NineBxPreferences()
    var strUsername: String = "aman.shekhar@cognitiveclouds.com"
    var strPassword: String = "[219, 80, 120, 19, 74, 36, 40, 74, 173, 169, 201, 144, 10, 213, 102, 44, 154, 239, 237, 49, 132, 210, 196, 168, 186, 136, 44, 34, 0, 30, 35, 44]"

    var myCredentials: SyncCredentials? = null
    var user: SyncUser? = null
    var config: SyncConfiguration? = null
    lateinit var realm: Realm


    private lateinit var selectedGender: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.showBackIcon()


        SyncTheDb().execute()


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

    private fun enableEditing() {
        NineBxApplication.instance.activityInstance!!.hideToolbar()
        toolbarProfile.show()
        imgEdit.setImageResource(R.drawable.ic_icon_save)
        txtUserName.setTextColor(resources.getColor(R.color.colorPrimary))
        txtFirstName.isEnabled = true
        txtLastName.isEnabled = true
        txtDOB.isClickable = true
        txtAnniversary.isClickable = true
        txtMobileNumber.isEnabled = true
        txtCountry.isEnabled = true

    }

    private fun checkValidations() {
        if (txtFirstName.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'First name'", Toast.LENGTH_LONG).show()
            txtFirstName.requestFocus()
            return
        }

        if (txtDOB.text.toString().trim().isEmpty()) {
             Toast.makeText(context, "Please enter 'Date of birth'", Toast.LENGTH_LONG).show()
             txtDOB.requestFocus()
            return
         }

         if (!(txtGender != null && txtGender.selectedItem.toString() != null)) {
             Toast.makeText(context, "Please enter 'Gender'", Toast.LENGTH_LONG).show()
             txtGender.requestFocus()
             return
         }

         if (txtMobileNumber.text.toString().trim().isEmpty()) {
             Toast.makeText(context, "Please enter 'Mobile number'", Toast.LENGTH_LONG).show()
             txtMobileNumber.requestFocus()
             return
         }

         if (txtAddress1.text.toString().trim().isEmpty()) {
             Toast.makeText(context, "Please enter 'Street address 1'", Toast.LENGTH_LONG).show()
             txtAddress1.requestFocus()
             return
         }

         if (txtCity.text.toString().trim().isEmpty()) {
             Toast.makeText(context, "Please enter 'City'", Toast.LENGTH_LONG).show()
             txtCity.requestFocus()
             return
         }

         if (txtState.text.toString().trim().isEmpty()) {
             Toast.makeText(context, "Please enter 'State'", Toast.LENGTH_LONG).show()
             txtState.requestFocus()
             return
         }

         if (txtZipCode.text.toString().trim().isEmpty()) {
             Toast.makeText(context, "Please enter 'Zip code'", Toast.LENGTH_LONG).show()
             txtZipCode.requestFocus()
             return
         }

        strFirstName = encryptAESKey(txtFirstName.text.toString(), Constants.TEMP_PRIVATE_KEY)
        strLastName = encryptAESKey(txtLastName.text.toString(), Constants.TEMP_PRIVATE_KEY)

        strCity = encryptAESKey(txtCity.text.toString(), Constants.TEMP_PRIVATE_KEY)
        strState = encryptAESKey(txtState.text.toString(), Constants.TEMP_PRIVATE_KEY)
        strZipCode = encryptAESKey(txtZipCode.text.toString(), Constants.TEMP_PRIVATE_KEY)
        strStreetAddress1 = encryptAESKey(txtAddress1.text.toString(), Constants.TEMP_PRIVATE_KEY)
        strStreetAddress2 = encryptAESKey(txtAddress2.text.toString(), Constants.TEMP_PRIVATE_KEY)

//        sendDataToServer()
//        NineBxApplication.instance.activityInstance!!.onBackPressed()


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

    override fun onResume() {
        super.onResume()
//        enableEditing()
        var countrySelected = prefrences.countrySelected
        if (countrySelected.toString().trim().isEmpty()) {
            txtCountry.hint = "Select Country"
        } else {
            txtCountry.text = countrySelected
            txtCountry.setTextColor(resources.getColor(R.color.black))
        }

    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.hideBackIcon()
        NineBxApplication.instance.activityInstance!!.showToolbar()
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.account))
        return super.onBackPressed()
    }
}