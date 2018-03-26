package com.ninebx.ui.auth

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.*
import android.widget.ImageView
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showToast
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.view.MotionEvent
import android.view.View.OnTouchListener
import com.ninebx.utility.TEST_EMAIL
import com.ninebx.utility.TEST_FIRST_NAME
import com.ninebx.utility.TEST_LAST_NAME


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
                mAuthView.setAccountEmail(edtEmailAddress.text.toString().toLowerCase().trim())
                mAuthView.createUser(edtFirstName.text.toString().trim(), edtLastName.text.toString().trim(), edtEmailAddress.text.toString().toLowerCase().trim())
            }
        }

        if (NineBxApplication.autoTestMode) {
            edtFirstName.setText(TEST_FIRST_NAME)
            edtLastName.setText(TEST_LAST_NAME)
            edtEmailAddress.setText(TEST_EMAIL)
        }

        txtTermsOfUse.setOnClickListener {
            openStaticLayoutDialog(getString(R.string.terms_of_use))
        }

        txtPrivacyPolicy.setOnClickListener {
            openStaticLayoutDialog(getString(R.string.privacy_policy))
        }

        ivBackSignUp.setOnClickListener {
            //activity!!.onBackPressed()
            fragmentManager!!.popBackStackImmediate()
        }


        // For First Name
        edtFirstName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                imgDelLastName.hide()
                imgDelEmailName.hide()
                if (s.trim().isEmpty()) {
                    imgDelFirstName.visibility = View.INVISIBLE
                } else {
                    imgDelFirstName.show()
                }
            }
        })

        // For Last Name
        edtLastName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                imgDelFirstName.hide()
                imgDelEmailName.hide()
                if (s.trim().isEmpty()) {
                    imgDelLastName.visibility = View.INVISIBLE
                } else {
                    imgDelLastName.show()
                }
            }
        })

        edtFirstName.setOnTouchListener { _, _ ->
            if (edtFirstName.text.toString().isNotEmpty())
                imgDelFirstName.show()
            imgDelLastName.hide()
            imgDelEmailName.hide()
            false
        }
        edtLastName.setOnTouchListener { _, _ ->
            if (edtFirstName.text.toString().isNotEmpty())
                imgDelFirstName.hide()
            imgDelLastName.show()
            imgDelEmailName.hide()
            false
        }
        edtEmailAddress.setOnTouchListener { _, _ ->
            if (edtFirstName.text.toString().isNotEmpty())
                imgDelFirstName.hide()
            imgDelLastName.hide()
            imgDelEmailName.show()
            false
        }


        // For Email
        edtEmailAddress.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                imgDelFirstName.hide()
                imgDelLastName.hide()
                if (s.trim().isEmpty()) {
                    imgDelEmailName.visibility = View.INVISIBLE
                } else {
                    imgDelEmailName.show()
                }
            }
        })


        imgDelFirstName.setOnClickListener {
            edtFirstName.setText("")
        }

        imgDelLastName.setOnClickListener {
            edtLastName.setText("")
        }

        imgDelEmailName.setOnClickListener {
            edtEmailAddress.setText("")
        }

    }

    private fun managingTheClearButtonOnEditText() {
        if (!edtFirstName.text.toString().isEmpty()) {

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
            context!!.showToast(R.string.error_empty_first_name)
            edtFirstName.requestFocus()
            return false
        }

        // There is no validation on Last Name

//        if (edtLastName.text.toString().isEmpty()) {
//            isValid = false
//            context!!.showToast(R.string.error_empty_last_name)
//            edtLastName.requestFocus()
//            return false
//        }

        if (edtEmailAddress.text.toString().isEmpty()) {
            isValid = false
            context!!.showToast(R.string.error_empty_email)
            edtEmailAddress.requestFocus()
            return false
        } else {
            if (!isValidEmail(edtEmailAddress.text.toString().trim())) {
                isValid = false
                edtEmailAddress.requestFocus()
                mAuthView.onError(R.string.invalid_email_format)
                return false
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