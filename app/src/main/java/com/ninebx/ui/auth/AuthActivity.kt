package com.ninebx.ui.auth

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.showProgressDialog
import com.ninebx.ui.home.HomeActivity
import io.realm.SyncUser

/**
 * Created by Alok on 02/01/18.
 */
class AuthActivity : AppCompatActivity(), AuthView {

    private lateinit var mCurrentTag : String
    private lateinit var mAuthPresenter : AuthPresenter
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
        when( mCurrentTag ) {
            "SignIn" -> {
                signInFragment!!.onSuccess( syncUser )
            }
            "AccountPassword" -> {
                accountPasswordFragment!!.onSuccess( syncUser )
            }
        }
    }

    override fun navigateToHome() {
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

    private var signInFragment : SignInFragment ?= null
    override fun navigateToSignIn() {
        mCurrentTag = "SignIn"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        signInFragment = SignInFragment()
        fragmentTransaction.replace(R.id.container, signInFragment).commit()
    }

    private var accountPasswordFragment : AccountPasswordFragment ?= null
    override fun navigateToAccountPassword() {
        mCurrentTag = "AccountPassword"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        accountPasswordFragment = AccountPasswordFragment()
        fragmentTransaction.replace(R.id.container, accountPasswordFragment).commit()
    }

    override fun navigateToOTP() {
        mCurrentTag = "OTP"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val otpFragment = OTPFragment()
        val bundle = Bundle()
        bundle.putString("email", mEmail)
        otpFragment.arguments = bundle
        fragmentTransaction.replace(R.id.container, otpFragment).commit()
    }

    override fun navigateToCreatePassCode( isCreatePassCode : Boolean ) {
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
        Toast.makeText(this, getString(error), Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        mAuthPresenter = AuthPresenter(this)
    }

    override fun onBackPressed() {
        if( supportFragmentManager.backStackEntryCount > 1 ) {
            supportFragmentManager.popBackStack()
        }
        else {
            finish()
        }
    }
}