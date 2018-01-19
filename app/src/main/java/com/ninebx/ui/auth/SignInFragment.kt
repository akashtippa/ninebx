package com.ninebx.ui.auth

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.ImageView
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            /*   if (validate()) {
                   mAuthView.getAuthPresenter().signIn(edtEmailAddress.text.toString().trim(), edtPassword.text.toString())
               }*/


            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)
            prefrences.isLogin = true
            activity!!.finish()


        }
        btnSignUp.setOnClickListener {
            mAuthView.navigateToSignUp()
        }
        txtTermsOfUse.setOnClickListener {
            openStaticLayoutDialog(getString(R.string.terms_of_use))
        }
        txtPrivacyPolicy.setOnClickListener {
            openStaticLayoutDialog(getString(R.string.privacy_policy))
        }

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

    private fun openStaticLayoutDialog(option: String) {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        when (option) {
            getString(R.string.terms_of_use) -> {
                dialog.setContentView(R.layout.dialog_terms_of_use)
            }
            getString(R.string.privacy_policy) -> {
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