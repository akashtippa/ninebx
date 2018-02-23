package com.ninebx.ui.home.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.NineBxPreferences
import com.ninebx.utility.encryptString
import com.ninebx.utility.isValidPassword
import kotlinx.android.synthetic.main.fragment_master_account_password.*

/***
 * Created by TechnoBlogger on 15/01/18.
 */
class MasterPasswordFragment : FragmentBackHelper() {

    var strOldPassword = ""
    var strNewPassword = ""
    var strConfirmNewPassword = ""

    var passwordFromPrefrence = ""

    val prefrences = NineBxPreferences()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_master_account_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.hideHomeIcon()
        NineBxApplication.instance.activityInstance!!.hideQuickAdd()
        NineBxApplication.instance.activityInstance!!.showBackIcon()
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.account_password))

        passwordFromPrefrence = prefrences.userPassword!!

        btnSubmit.setOnClickListener {
            checkValidation()
        }

    }

    private fun checkValidation() {
        strOldPassword = etCurrentPassword.text.toString()
        strNewPassword = etNewPassword.text.toString()
        strConfirmNewPassword = etConfirmPassword.text.toString()

        if (strOldPassword.trim().isEmpty()) {
            Toast.makeText(context, R.string.error_empty_password, Toast.LENGTH_LONG).show()
            return
        }
        if (strOldPassword.trim().encryptString() == passwordFromPrefrence) {
            Toast.makeText(context, R.string.error_passwords_dont_match, Toast.LENGTH_LONG).show()
            return
        }
        if (strNewPassword.trim().isEmpty()) {
            Toast.makeText(context, "Please enter New Password", Toast.LENGTH_LONG).show()
            return
        }

        if (strNewPassword.isNotEmpty() && strNewPassword.trim().length < 8) {
            context!!.showToast(R.string.password_length_8)
            etConfirmPassword.requestFocus()
            return
        }

        if (strNewPassword.isNotEmpty() && !isValidPassword(strNewPassword.trim())) {
            context!!.showToast(R.string.password_rules)
            etConfirmPassword.requestFocus()
            return
        }

        if (strConfirmNewPassword.trim().isEmpty()) {
            Toast.makeText(context, "Please enter Confirm Password", Toast.LENGTH_LONG).show()
            return
        }
        if (!(strNewPassword.trim().equals(strConfirmNewPassword))) {
            Toast.makeText(context, "Password mismatch", Toast.LENGTH_LONG).show()
            return
        }

        updateMasterPassword()

    }

    private fun updateMasterPassword() {
        // Method to Update Master Password
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.account))
        NineBxApplication.instance.activityInstance!!.showHomeIcon()
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.hideBackIcon()
        return super.onBackPressed()
    }
}