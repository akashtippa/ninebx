package com.ninebx.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.show
import com.ninebx.utility.Constants
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_level2_contacts.*

/***
 * Created by TechnoBlogger on 24/01/18.
 */
class SingleContactViewFragment : FragmentBackHelper() {

    var strContactName = ""
    var strContactNumber = ""

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


        txtFirstName.setText(strContactName)
        txtMobileNumber.setText(strContactNumber)

        ivBackContactView.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        imgEdit.setOnClickListener {
            enableEditing()
        }

        txtSaveContacts.setOnClickListener {
            saveTheEditedContacts()
        }


    }

    private fun saveTheEditedContacts() {

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
        txtCountry.isEnabled = true

    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        return super.onBackPressed()
    }
}