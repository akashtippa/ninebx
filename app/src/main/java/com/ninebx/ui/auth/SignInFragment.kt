package com.ninebx.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.HomeActivity
import com.ninebx.utility.NineBxPreferences
import io.realm.SyncUser
import kotlinx.android.synthetic.main.activity_sign_in.*

/**
 * Created by Alok on 03/01/18.
 */
class SignInFragment : BaseAuthFragment() {

    val prefrences = NineBxPreferences()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.activity_sign_in, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            /*   if (validate()) {
                   mAuthView.getAuthPresenter().signIn(edtEmailAddress.text.toString().trim(), edtPassword.text.toString())
               }*/


            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)
            prefrences.isLogin = true
            activity.finish()


        }
        btnSignUp.setOnClickListener {
            mAuthView.navigateToSignUp()
        }
        txtTermsOfUse.setOnClickListener {}
        txtPrivacyPolicy.setOnClickListener {}

        if (NineBxApplication.autoTestMode) {
            edtEmailAddress.setText("test.box24@yopmail.com")
            edtPassword.setText("Test.box24")
        }
    }

    override fun validate(): Boolean {
        var isValid = true

        if (edtEmailAddress.text.toString().isEmpty()) {
            isValid = false
            edtEmailAddress.requestFocus()
            edtEmailAddress.error = getString(R.string.required)
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
            edtPassword.error = getString(R.string.required)
        }

        return isValid
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    var mSyncUser: SyncUser? = null
    fun onSuccess(syncUser: SyncUser?) {
        mSyncUser = syncUser
        mAuthView.navigateToHome()
    }
}