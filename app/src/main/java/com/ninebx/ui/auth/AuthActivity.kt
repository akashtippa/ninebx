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
import android.support.annotation.RequiresApi
import com.ninebx.ui.auth.fingerprint.FingerPrintFragment
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.Users


/**
 * Created by Alok on 02/01/18.
 */

class AuthActivity : AppCompatActivity(), AuthView {
    override fun navigateToStart() {
        supportFragmentManager.popBackStack()
        supportFragmentManager.popBackStack()
    }

    override fun validateEmailOTP(emailOtp: String) {
        if( otpFragment != null ) {
            otpFragment!!.setEmailOTP( emailOtp )
        }
    }

    override fun createUser(firstName: String, lastName: String, email: String) {
        val currentUser = mAuthPresenter.createUser( email, firstName, lastName )
        navigateToAccountPassword( currentUser )
    }


    override fun onError(error: String) {
        this@AuthActivity.showToast(error)
    }

    private lateinit var mCurrentTag: String
    private lateinit var mAuthPresenter: AuthPresenter
    private var mEmail: String = ""

    override fun getAuthPresenter(): AuthPresenter {
        return mAuthPresenter
    }

    override fun getAccountEmail(): String {
        return mEmail
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
        fragmentTransaction.addToBackStack(SignUpFragment::class.java.simpleName)
        fragmentTransaction.replace(R.id.container, SignUpFragment()).commit()
    }

    private var signInFragment: SignInFragment? = null
    override fun navigateToSignIn() {
        mCurrentTag = "SignIn"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(SignInFragment::class.java.simpleName)
        signInFragment = SignInFragment()
        fragmentTransaction.replace(R.id.container, signInFragment).commit()
    }

    private var accountPasswordFragment: AccountPasswordFragment? = null
    override fun navigateToAccountPassword( users : Users ) {
        if (NineBxApplication.getPreferences().currentStep < Constants.SIGN_UP_COMPLETE)
            NineBxApplication.getPreferences().currentStep = Constants.SIGN_UP_COMPLETE
        mCurrentTag = "AccountPassword"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(AccountPasswordFragment::class.java.simpleName)
        accountPasswordFragment = AccountPasswordFragment()
        val bundle = Bundle()
        bundle.putParcelable(Constants.CURRENT_USER, users)
        accountPasswordFragment!!.arguments = bundle
        fragmentTransaction.replace(R.id.container, accountPasswordFragment).commit()
    }

    private var otpFragment : OTPFragment ?= null
    override fun navigateToOTP() {
        if (NineBxApplication.getPreferences().currentStep < Constants.ACCOUNT_PASSWORD_COMPLETE)
            NineBxApplication.getPreferences().currentStep = Constants.ACCOUNT_PASSWORD_COMPLETE
        mCurrentTag = "OTP"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(OTPFragment::class.java.simpleName)
        otpFragment = OTPFragment()
        val bundle = Bundle()

        if( mEmail.isEmpty() )
            mEmail = NineBxApplication.getPreferences().userEmail!!

        bundle.putString("email", mEmail)
        otpFragment!!.arguments = bundle
        fragmentTransaction.replace(R.id.container, otpFragment).commit()
    }

    private var passCodeFragment: PassCodeFragment ?= null
    override fun navigateToCreatePassCode(isCreatePassCode: Boolean, passCode: String) {
        if (!isCreatePassCode) {
            if (NineBxApplication.getPreferences().currentStep < Constants.OTP_COMPLETE)
                NineBxApplication.getPreferences().currentStep = Constants.OTP_COMPLETE
        }
        mCurrentTag = "PassCode"
        if( passCodeFragment == null ) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(PassCodeFragment::class.java.simpleName)
            passCodeFragment = PassCodeFragment()
            fragmentTransaction.replace(R.id.container, passCodeFragment).commit()
        }
        NineBxApplication.getPreferences().passCode = passCode
        passCodeFragment!!.setCreatePassCode( isCreatePassCode )

    }

    override fun navigateToInvitePeople() {
        if (NineBxApplication.getPreferences().currentStep < Constants.FINGER_PRINT_COMPLETE)
            NineBxApplication.getPreferences().currentStep = Constants.FINGER_PRINT_COMPLETE
        mCurrentTag = "InvitePeople"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(InvitePeopleFragment::class.java.simpleName)
        val invitePeopleFragment = InvitePeopleFragment()
        fragmentTransaction.replace(R.id.container, invitePeopleFragment).commit()
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
            Constants.ACCOUNT_PASSWORD_COMPLETE -> {
                navigateToOTP()
            }
            Constants.OTP_COMPLETE -> {
                navigateToCreatePassCode(true, "")
            }
            Constants.PASS_CODE_COMPLETE -> {
                if( checkForFingerPrint() )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        navigateToFingerPrint()
                    }
                else {
                    navigateToInvitePeople()
                }
            }
            Constants.FINGER_PRINT_COMPLETE ->{
                //navigateToInvitePeople()
                navigateToHome()
            }
            Constants.INVITE_USERS_COMPLETE, Constants.ALL_COMPLETE -> {
                navigateToHome()
            }
            Constants.SIGN_UP_COMPLETE -> {
                navigateToSignUp()
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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun navigateToFingerPrint() {
        if( checkForFingerPrint() ) {
            if (NineBxApplication.getPreferences().currentStep < Constants.PASS_CODE_COMPLETE)
                NineBxApplication.getPreferences().currentStep = Constants.PASS_CODE_COMPLETE
            mCurrentTag = "FingerPrint"
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(FingerPrintFragment::class.java.simpleName)
            val fingerPrintFragment = FingerPrintFragment()
            fragmentTransaction.replace(R.id.container, fingerPrintFragment).commit()
        }
        else {
            navigateToInvitePeople()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}