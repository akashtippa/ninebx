package com.ninebx.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.showProgressDialog
import com.ninebx.ui.home.HomeActivity
import com.ninebx.utility.Constants
import io.realm.SyncUser
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import com.ninebx.ui.auth.fingerprint.FingerPrintFragment


/**
 * Created by Alok on 02/01/18.
 */

class AuthActivity : AppCompatActivity(), AuthView {

    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    private lateinit var mCurrentTag: String
    private lateinit var mAuthPresenter: AuthPresenter
    private var mEmail: String? = ""

    override fun getAuthPresenter(): AuthPresenter {
        return mAuthPresenter
    }

    override fun getAccountEmail(): String {
        return mEmail!!
    }

    override fun setAccountEmail(accountEmail: String) {
        this.mEmail = accountEmail
    }

    override fun onSuccess(syncUser: SyncUser?) {
        when (mCurrentTag) {
            "SignIn" -> {
                signInFragment!!.onSuccess(syncUser)
            }
            "AccountPassword" -> {
                accountPasswordFragment!!.onSuccess(syncUser)
            }
        }
    }

    override fun navigateToHome() {

        if (NineBxApplication.getPreferences().currentStep < Constants.ALL_COMPLETE)
            NineBxApplication.getPreferences().currentStep = Constants.ALL_COMPLETE

        val homeIntent = Intent(this@AuthActivity, HomeActivity::class.java)
        startActivity(homeIntent)
        finish()
    }

    override fun navigateToSignUp() {
        mCurrentTag = "SignUp"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.container, SignUpFragment()).commit()
    }

    private var signInFragment: SignInFragment? = null
    override fun navigateToSignIn() {
        mCurrentTag = "SignIn"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        signInFragment = SignInFragment()
        fragmentTransaction.replace(R.id.container, signInFragment).commit()
    }

    private var accountPasswordFragment: AccountPasswordFragment? = null
    override fun navigateToAccountPassword() {
        if (NineBxApplication.getPreferences().currentStep < Constants.SIGN_UP_COMPLETE)
            NineBxApplication.getPreferences().currentStep = Constants.SIGN_UP_COMPLETE
        mCurrentTag = "AccountPassword"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        accountPasswordFragment = AccountPasswordFragment()
        fragmentTransaction.replace(R.id.container, accountPasswordFragment).commit()
    }

    override fun navigateToOTP() {
        if (NineBxApplication.getPreferences().currentStep < Constants.ACCOUNT_PASSWORD_COMPLETE)
            NineBxApplication.getPreferences().currentStep = Constants.ACCOUNT_PASSWORD_COMPLETE
        mCurrentTag = "OTP"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val otpFragment = OTPFragment()
        val bundle = Bundle()
        bundle.putString("email", mEmail)
        otpFragment.arguments = bundle
        fragmentTransaction.replace(R.id.container, otpFragment).commit()
    }

    override fun navigateToCreatePassCode(isCreatePassCode: Boolean) {
        if (!isCreatePassCode) {
            if (NineBxApplication.getPreferences().currentStep < Constants.OTP_COMPLETE)
                NineBxApplication.getPreferences().currentStep = Constants.OTP_COMPLETE
        }
        mCurrentTag = "PassCode"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val passCodeFragment = PassCodeFragment()
        val bundle = Bundle()
        bundle.putBoolean("isCreatePassCode", isCreatePassCode)
        passCodeFragment.arguments = bundle
        fragmentTransaction.replace(R.id.container, passCodeFragment).commit()
    }

    override fun showProgress(message: Int) {
        showProgressDialog(getString(message))
    }

    override fun hideProgress() {
        hideProgressDialog()
    }

    override fun onError(error: Int) {
        onError(getString(error))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        navigateToScreen()

    }

    private fun navigateToScreen() {
        mAuthPresenter = AuthPresenter(this)
        when (NineBxApplication.getPreferences().currentStep) {
            Constants.ALL_COMPLETE -> {
                navigateToHome()
            }
            Constants.ACCOUNT_PASSWORD_COMPLETE -> {
                navigateToSignUp()
                navigateToAccountPassword()
                navigateToOTP()
            }
            Constants.OTP_COMPLETE -> {
                navigateToSignUp()
                navigateToAccountPassword()
                navigateToOTP()
                navigateToCreatePassCode(true)
            }
            Constants.PASS_CODE_COMPLETE -> {
                if( checkForFingerPrint() )
                    navigateToFingerPrint()
                else {
                    navigateToHome()
                }
            }
            Constants.FINGER_PRINT_COMPLETE ->{
                navigateToHome()
            }
            Constants.SIGN_UP_COMPLETE -> {
                navigateToSignUp()
                navigateToAccountPassword()
            }
        }

    }

    private fun checkForFingerPrint(): Boolean {
        // Check if we're running on Android 6.0 (M) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Fingerprint API only available on from Android 6.0 (M)
            val fingerprintManager = this.getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager
            return fingerprintManager.isHardwareDetected
        }
        else return false
    }

    override fun navigateToFingerPrint() {
        if (NineBxApplication.getPreferences().currentStep < Constants.FINGER_PRINT_COMPLETE)
            NineBxApplication.getPreferences().currentStep = Constants.FINGER_PRINT_COMPLETE
        mCurrentTag = "FingerPrint"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val fingerPrintFragment = FingerPrintFragment()
        fragmentTransaction.replace(R.id.container, fingerPrintFragment).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}