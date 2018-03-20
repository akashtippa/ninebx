package com.ninebx.ui.auth

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.*
import android.widget.ImageView
import com.ninebx.BuildConfig
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.utility.*
import io.realm.SyncUser
import kotlinx.android.synthetic.main.activity_sign_in.*

/**
 * Created by Alok on 03/01/18.
 */
class SignInFragment : BaseAuthFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            if (validate()) {
                AppLogger.d("Signin", "Comparing : " + edtEmailAddress.text.toString().toLowerCase().trim() + " : " + NineBxApplication.getPreferences().userEmail)
                if( !edtEmailAddress.text.toString().toLowerCase().trim().equals(NineBxApplication.getPreferences().userEmail, true)) {
                    NineBxApplication.getPreferences().clearPreferences()
                }
                else {
                    if( NineBxApplication.getPreferences().currentStep > Constants.OTP_COMPLETE )
                        NineBxApplication.getPreferences().currentStep = Constants.ALL_COMPLETE
                }
                mAuthView.setAccountEmail(edtEmailAddress.text.toString().toLowerCase().trim())
                mAuthView.getAuthPresenter().signIn(edtEmailAddress.text.toString().toLowerCase().trim(), edtPassword.text.toString())
            }
        }
        btnSignUp.setOnClickListener {
            mAuthView.navigateToSignUp()
        }
        txtTermsOfUse.setOnClickListener {
            openStaticLayoutDialog((R.string.terms_of_use))
        }
        txtPrivacyPolicy.setOnClickListener {
            openStaticLayoutDialog((R.string.privacy_policy))
        }

        if (NineBxApplication.autoTestMode) {
            edtEmailAddress.setText(TEST_EMAIL)
            edtPassword.setText(TEST_PASSWORD)
        }

        // For First Name
        edtEmailAddress.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.trim().isEmpty()) {
                    imgDelEmail.visibility = View.INVISIBLE
                } else {
                    imgDelEmail.show()
                }
            }
        })


        imgDelEmail.setOnClickListener {
            edtEmailAddress.setText("")
        }
    }

    override fun validate(): Boolean {
        var isValid = true

        if (edtEmailAddress.text.toString().isEmpty()) {
            isValid = false
            edtEmailAddress.requestFocus()
            context!!.showToast(R.string.error_empty_email)
        } else {
            if (!isValidEmail(edtEmailAddress.text.toString().trim())) {
                isValid = false
                edtEmailAddress.requestFocus()
                mAuthView.onError(R.string.invalid_email_format)
            }
        }

        if (edtPassword.text.toString().isEmpty()) {
            isValid = false
            edtPassword.requestFocus()
            context!!.showToast(R.string.error_empty_password)
        }

        if (edtPassword.text.toString().isNotEmpty() && edtPassword.text.toString().trim().length < 8) {
            context!!.showToast(R.string.password_length_8)
            edtPassword.requestFocus()
            isValid = false
        }

        if (edtPassword.text.toString().isNotEmpty() && !isValidPassword(edtPassword.text.toString().trim())) {
            context!!.showToast(R.string.password_rules)
            edtPassword.requestFocus()
            isValid = false
        }

        return isValid
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    var mSyncUser: SyncUser? = null
    fun onSuccess(syncUser: SyncUser?) {
        mSyncUser = syncUser
        if( NineBxApplication.getPreferences().currentStep == Constants.ALL_COMPLETE ) {
            mAuthView.navigateToHome()
        }
        else {
            mAuthView.navigateToOTP(true)
        }

    }

    private fun openStaticLayoutDialog(option: Int) {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        when (option) {
            (R.string.terms_of_use) -> {
                dialog.setContentView(R.layout.dialog_terms_of_use)
            }
            (R.string.privacy_policy) -> {
                dialog.setContentView(R.layout.dialog_privacy_policy)
            }
        }

        val window = dialog.window
        val wlp = window.attributes

        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window.attributes = wlp
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.show()

        val imgBack = dialog.findViewById<View>(R.id.imgBack) as ImageView
        imgBack.setOnClickListener {
            dialog.cancel()
        }
    }
}