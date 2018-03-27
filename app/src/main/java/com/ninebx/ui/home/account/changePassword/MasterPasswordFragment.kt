package com.ninebx.ui.home.account.changePassword

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.showProgressDialog
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.Users
import com.ninebx.utility.*
import kotlinx.android.synthetic.main.fragment_master_account_password.*
import java.util.*
import kotlin.collections.ArrayList

/***
 * Created by TechnoBlogger on 15/01/18.
 */
class MasterPasswordFragment : FragmentBackHelper(), MasterPasswordView {
    override fun getCurrentUsers(): ArrayList<Users> {
        return mCurrentUsers
    }

    override fun getCurrentPassword(): String {
        return etCurrentPassword.text.toString().trim()
    }

    override fun getNewPassword(): String {
        return etConfirmPassword.text.toString().trim()
    }

    override fun showProgress(message: Int) {
        if( context != null )
        context!!.showProgressDialog(getString(message))
    }

    override fun hideProgress() {
        if( context != null )
        context!!.hideProgressDialog()
    }

    override fun onError(error: Int) {
        hideProgress()
        context!!.showToast(error)
    }

    override fun onPasswordReset(message: Int) {
        onError(message)
        NineBxApplication.instance.activityInstance!!.onBackPressed()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_master_account_password, container, false)
    }

    private lateinit var mMasterPasswordPresenter : MasterPasswordPresenter
    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        NineBxApplication.instance.activityInstance!!.hideQuickAdd()

        //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.account_password))
        btnSubmit.setOnClickListener {
            if( validate() ) {
                updatePassword()
            }
        }
        mCurrentUsers = arguments!!.getParcelableArrayList(Constants.CURRENT_USER)
        mMasterPasswordPresenter = MasterPasswordPresenter(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvStrongPassword.text = Html.fromHtml(getString(R.string.choose_a_strong_password, Html.FROM_HTML_MODE_LEGACY))
            txtSharePassword.text = Html.fromHtml(getString(R.string.account_master_password_share, Html.FROM_HTML_MODE_LEGACY))
            txtRememberIt.text = Html.fromHtml(getString(R.string.account_master_password_remember, Html.FROM_HTML_MODE_LEGACY))
        }
        else {
            tvStrongPassword.text = Html.fromHtml(getString(R.string.choose_a_strong_password))
            txtSharePassword.text = Html.fromHtml(getString(R.string.account_master_password_share))
            txtRememberIt.text = Html.fromHtml(getString(R.string.account_master_password_remember))
        }
    }

    private fun updatePassword() {
        mMasterPasswordPresenter.changePassword()
    }

    private lateinit var newPassword: String
    private lateinit var confirmPassword: String
    private lateinit var  mCurrentUsers : ArrayList<Users>
    private fun validate(): Boolean {

        if( etCurrentPassword.text.toString().isEmpty() ) {
            context!!.showToast(R.string.error_empty_password)
            etCurrentPassword.requestFocus()
            return false
        }

        if( etNewPassword.text.toString().isEmpty() ) {
            context!!.showToast(R.string.error_empty_password)
            etNewPassword.requestFocus()
            return false
        }

        if( etConfirmPassword.text.toString().isEmpty() ) {
            context!!.showToast(R.string.error_empty_password)
            etConfirmPassword.requestFocus()
            return false
        }

        newPassword = etNewPassword.text.toString()
        confirmPassword = etConfirmPassword.text.toString()

        if( newPassword != confirmPassword ) {
            context!!.showToast(R.string.error_passwords_dont_match)
            etConfirmPassword.requestFocus()
            return false
        }

        if (etNewPassword.text.toString().isNotEmpty() && etNewPassword.text.toString().trim().length < 8) {
            context!!.showToast(R.string.password_length_8)
            etNewPassword.requestFocus()
            return false
        }

        if (etNewPassword.text.toString().isNotEmpty() && !isValidPassword(etNewPassword.text.toString().trim())) {
            context!!.showToast(R.string.password_rules)
            etNewPassword.requestFocus()
            return false
        }

        if (etConfirmPassword.text.toString().isNotEmpty() && etConfirmPassword.text.toString().trim().length < 8) {
            context!!.showToast(R.string.password_length_8)
            etConfirmPassword.requestFocus()
            return false
        }

        if (etConfirmPassword.text.toString().isNotEmpty() && !isValidPassword(etConfirmPassword.text.toString().trim())) {
            context!!.showToast(R.string.password_rules)
            etConfirmPassword.requestFocus()
            return false
        }

        return if( validateMasterPassword() ) {
            true
        }
        else {
            context!!.showToast(R.string.check_password)
            etCurrentPassword.requestFocus()
            false
        }

    }

    private fun validateMasterPassword(): Boolean {
        val password = etCurrentPassword.text.toString().trim()
        val preferences = NineBxApplication.getPreferences()
        AppLogger.d("Email", "MasterPassword - validateMP " + NineBxApplication.getPreferences().userEmail!!)
        return !password.isEmpty() && preferences.userPassword!!.equals(Arrays.toString(encryptKey(password, preferences.userEmail!!)))
    }

    override fun onBackPressed(): Boolean {
        //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.account))

        NineBxApplication.instance.activityInstance!!.showBottomView()

        return super.onBackPressed()
    }
}