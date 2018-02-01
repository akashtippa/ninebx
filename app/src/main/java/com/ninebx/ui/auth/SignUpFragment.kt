package com.ninebx.ui.auth

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.*
import android.widget.ImageView
import com.ninebx.NineBxApplication
import com.ninebx.R
import kotlinx.android.synthetic.main.activity_sign_up.*


/**
 * Created by Alok on 03/01/18.
 */
class SignUpFragment : BaseAuthFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSubmit.setOnClickListener {
            if (validate()) {
                mAuthView.setAccountEmail(edtEmailAddress.text.toString().trim())
                mAuthView.createUser( edtFirstName.text.toString().trim(), edtLastName.text.toString().trim(), edtEmailAddress.text.toString().trim() )

            }
        }
        if (NineBxApplication.autoTestMode) {
            edtFirstName.setText("Aman")
            edtLastName.setText("Shekhar")
            edtEmailAddress.setText("aman.shekhar@cognitiveclouds.com")
        }

        txtTermsOfUse.setOnClickListener {
            openStaticLayoutDialog(getString(R.string.terms_of_use))
        }
        txtPrivacyPolicy.setOnClickListener {
            openStaticLayoutDialog(getString(R.string.privacy_policy))
        }

        ivBackSignUp.setOnClickListener {
            activity!!.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                activity!!.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private lateinit var appCompatActivity: AppCompatActivity

    override fun validate(): Boolean {
        var isValid = true

        if (edtFirstName.text.toString().isEmpty()) {
            isValid = false
            edtFirstName.requestFocus()
            edtFirstName.error = getString(R.string.required)
        }

        if (edtLastName.text.toString().isEmpty()) {
            isValid = false
            edtLastName.requestFocus()
            edtLastName.error = getString(R.string.required)
        }

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

        return isValid
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
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