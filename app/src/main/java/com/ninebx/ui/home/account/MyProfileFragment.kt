package com.ninebx.ui.home.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.show
import com.ninebx.utility.DateTimeSelectionListener
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.getDateFromPicker
import com.ninebx.utility.getDateMonthYearFormat
import kotlinx.android.synthetic.main.fragment_my_profile.*
import java.util.*


/***
 * Created by TechnoBlogger on 15/01/18.
 */
class MyProfileFragment : FragmentBackHelper() {

    private lateinit var selectedGender: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.showBackIcon()

        imgEdit.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.hideToolbar()
            toolbarProfile.show()
            imgEdit.setImageResource(R.drawable.ic_icon_save)
            txtUserName.setTextColor(resources.getColor(R.color.colorPrimary))
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


        selectedGender = txtGender.selectedItem.toString()
        txtGender.prompt = "Gender"

    }

    private fun enableEditing() {

    }

    private fun checkValidations() {
        if (txtFirstName.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'First name'", Toast.LENGTH_LONG).show()
            return;
        }

        if (txtLastName.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Last name'", Toast.LENGTH_LONG).show()
            return;
        }

        if (txtDOB.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Date of birth'", Toast.LENGTH_LONG).show()
            return;
        }

        if (!(txtGender != null && txtGender.selectedItem.toString() != null)) {
            Toast.makeText(context, "Please enter 'Gender'", Toast.LENGTH_LONG).show()
            return;
        }

        if (txtMobileNumber.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Mobile number'", Toast.LENGTH_LONG).show()
            return;
        }

        if (txtAddress1.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Street address 1'", Toast.LENGTH_LONG).show()
            return;
        }

        if (txtCity.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'City'", Toast.LENGTH_LONG).show()
            return;
        }

        if (txtState.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'State'", Toast.LENGTH_LONG).show()
            return;
        }

        if (txtZipCode.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Zip code'", Toast.LENGTH_LONG).show()
            return;
        }

        NineBxApplication.instance.activityInstance!!.onBackPressed()


    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.hideBackIcon()
        NineBxApplication.instance.activityInstance!!.showToolbar()
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.account))
        return super.onBackPressed()
    }
}