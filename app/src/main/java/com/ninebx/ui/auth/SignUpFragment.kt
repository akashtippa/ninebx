package com.ninebx.ui.auth

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.text.TextUtils



/**
 * Created by Alok on 03/01/18.
 */
class SignUpFragment : BaseAuthFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setHasOptionsMenu(true)
        btnSubmit.setOnClickListener {
            if( validate() ) {
                mAuthView.setAccountEmail( edtEmailAddress.text.toString().trim() )
                mAuthView.navigateToAccountPassword()
            }
        }
        if( NineBxApplication.autoTestMode ) {
            edtFirstName.setText("Test")
            edtLastName.setText("Test")
            edtEmailAddress.setText("test.box24@yopmail.com")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when( item!!.itemId ) {
            android.R.id.home -> {
                activity!!.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private lateinit var appCompatActivity: AppCompatActivity

    private fun setupToolbar() {
        appCompatActivity = activity as AppCompatActivity
        appCompatActivity.setSupportActionBar(toolbar)
        /*val assets = Typeface.createFromAsset(context.assets,"fonts/Futura-Medium.ttf")
        titleTextView.typeface = assets*/
        titleTextView.text = getString(R.string.sign_up)
        toolbar.navigationIcon = ContextCompat.getDrawable(context!!, R.drawable.ic_arrow_back)
        appCompatActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar!!.setHomeButtonEnabled(true)
    }

    override fun validate(): Boolean {
        var isValid = true

        if( edtFirstName.text.toString().isEmpty() ) {
            isValid = false
            edtFirstName.requestFocus()
            edtFirstName.error = getString(R.string.required)
        }

        if( edtLastName.text.toString().isEmpty() ) {
            isValid = false
            edtLastName.requestFocus()
            edtLastName.error = getString(R.string.required)
        }

        if( edtEmailAddress.text.toString().isEmpty() ) {
            isValid = false
            edtEmailAddress.requestFocus()
            edtEmailAddress.error = getString(R.string.required)
        }
        else {
            if( !isValidEmail(edtEmailAddress.text.toString().trim()) ) {
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
}