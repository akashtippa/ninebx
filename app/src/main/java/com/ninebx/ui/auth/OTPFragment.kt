package com.ninebx.ui.auth

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.utility.*
import com.ninebx.utility.Constants.PASSCODE_CREATE
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_otp.*

/**
 * Created by Alok on 04/01/18.
 */
@SuppressLint("StaticFieldLeak")
class OTPFragment : BaseAuthFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupToolbar()
//        toolbar.title = ""
//        setHasOptionsMenu(true)
        tvEmail.text = arguments!!.getString("email", "")
        btnSubmit.setOnClickListener {
            if( validate() ) {
                emailOtp = ""
                handler.removeCallbacks(runnable)
                object : AsyncTask<Void, Void, Int>() {
                    override fun doInBackground(vararg p0: Void?) : Int {
                        prepareRealmConnections( context, true, Constants.REALM_END_POINT_USERS, object : Realm.Callback( ) {
                            override fun onSuccess(realm: Realm?) {
                                val currentUsers = getCurrentUsers( realm!! )
                                if (currentUsers != null && currentUsers.size > 0) {
                                    val email = currentUsers[0]!!.emailAddress.decryptString()
                                    if( email.isNotEmpty() )
                                    NineBxApplication.getPreferences().userEmail = email
                                    AppLogger.d("Email", "OTP prepareRealmConnections " + NineBxApplication.getPreferences().userEmail!!)
                                }
                                else {
                                    onPostExecute(R.string.unable_to_find_user)
                                }
                            }

                        })
                        return -1
                    }

                    override fun onPostExecute(result: Int) {
                        super.onPostExecute(result)
                        context!!.hideProgressDialog()
                        mAuthView.navigateToCreatePassCode(PASSCODE_CREATE, "")

                        if( result != -1 ) mAuthView.onError(result)
                    }


                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)


            }
        }
        tvResend.setOnClickListener {
            etOtp1.setText("")
            etOtp2.setText("")
            etOtp3.setText("")
            etOtp4.setText("")
            etOtp5.setText("")
            etOtp6.setText("")
            tvResend.isEnabled = false
            handler.postDelayed(runnable, 60000)
            mAuthView.getAuthPresenter().requestOTP(mAuthView.getAccountEmail())

        }
        tvResend.isEnabled = false
        setupOtp()
    }

    var isPaused = false
    override fun onPause() {
        super.onPause()
        isPaused = true
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        isPaused = false
    }

    private var handler: Handler = Handler()
    private var runnable: Runnable = Runnable {
        if (tvResend != null) {
            if( !isPaused )
                context!!.showToast(R.string.otp_expired)
            emailOtp = ""
            tvResend.isEnabled = true
        }

    }

    private fun setupOtp() {
        etOtp1.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                when( etOtp1.text.length ) {
                    0 -> { etOtp1.requestFocus() }
                    1 -> {
                        etOtp2.requestFocus()
                        etOtp1.setSelection(etOtp1.text.length)
                    }
                }
            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

        })

        etOtp2.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                when( etOtp2.text.length ) {
                    0 -> { etOtp1.requestFocus() }
                    1 -> {
                        etOtp3.requestFocus()
                        etOtp2.setSelection(etOtp2.text.length)
                    }
                }

            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        etOtp3.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                when( etOtp3.text.length ) {
                    0 -> { etOtp2.requestFocus() }
                    1 -> {
                        etOtp4.requestFocus()
                        etOtp3.setSelection(etOtp3.text.length)
                    }
                }
            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        etOtp4.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                when( etOtp4.text.length ) {
                    0 -> { etOtp3.requestFocus() }
                    1 -> { etOtp5.requestFocus()
                        etOtp4.setSelection(etOtp4.text.length)
                    }
                }
            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }
        })

        etOtp5.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                when( etOtp5.text.length ) {
                    0 -> { etOtp4.requestFocus() }
                    1 -> { etOtp6.requestFocus()
                        etOtp5.setSelection(etOtp5.text.length)
                    }
                }
            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }
        })

        etOtp6.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                when( etOtp6.text.length ) {
                    0 -> { etOtp5.requestFocus() }
                    1 -> { btnSubmit.isEnabled = true
                        etOtp6.setSelection(etOtp6.text.length)}
                }
            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }
        })

        mAuthView.getAuthPresenter().requestOTP( mAuthView.getAccountEmail() )
        handler.postDelayed(runnable, 60000)

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
        titleTextView.text = getString(R.string.create_account_password)
        toolbar.navigationIcon = ContextCompat.getDrawable(context!!, R.drawable.ic_arrow_back)
        appCompatActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar!!.setHomeButtonEnabled(true)
    }

    override fun validate(): Boolean {
        var isValid = true

        if( !validateView( etOtp1 ) ) isValid = false
        if( !validateView( etOtp2 ) ) isValid = false
        if( !validateView( etOtp3 ) ) isValid = false
        if( !validateView( etOtp4 ) ) isValid = false
        if( !validateView( etOtp5 ) ) isValid = false
        if( !validateView( etOtp6 ) ) isValid = false

        if( isValid && emailOtp != "" ) {
            val otp = etOtp1.text.toString().trim() +
                    etOtp2.text.toString().trim() +
                    etOtp3.text.toString().trim() +
                    etOtp4.text.toString().trim() +
                    etOtp5.text.toString().trim() +
                    etOtp6.text.toString().trim()
            isValid = emailOtp == otp
        }
        else if( emailOtp == "" ) {
            isValid = false
            context!!.showToast(R.string.otp_expired)
        }

        return isValid
    }

    private fun validateView(etOtp: EditText?): Boolean {
        val isValid = etOtp!!.text.toString().isNotEmpty()
        if( !isValid ) {
            etOtp.error = getString(R.string.required)
        }
        return isValid
    }

    var emailOtp = ""

    fun setEmailOTP(emailOtp: String) {
        this.emailOtp = emailOtp
        if( NineBxApplication.autoTestMode && etOtp1 != null ) {
            var index = 0
            etOtp1.setText(emailOtp[index++].toString())
            etOtp2.setText(emailOtp[index++].toString())
            etOtp3.setText(emailOtp[index++].toString())
            etOtp4.setText(emailOtp[index++].toString())
            etOtp5.setText(emailOtp[index++].toString())
            etOtp6.setText(emailOtp[index].toString())
        }
    }
}