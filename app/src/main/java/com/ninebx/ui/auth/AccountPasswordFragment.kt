package com.ninebx.ui.auth

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import io.realm.SyncUser
import kotlinx.android.synthetic.main.fragment_account_password.*

/**
 * Created by Alok on 04/01/18.
 */

class AccountPasswordFragment : BaseAuthFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_account_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupToolbar()
//        setHasOptionsMenu(true)
        btnSubmit.setOnClickListener {
            if (validate()) {
                mAuthView.getAuthPresenter().signUp(mAuthView.getAccountEmail(), etCreatePassword.text.toString().trim())
            }
        }
        if (NineBxApplication.autoTestMode) {
            etCreatePassword.setText("a")
            etConfirmPassword.setText("a")
        }

        ivBackPass.setOnClickListener {
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

    var mSyncUser: SyncUser? = null
    fun onSuccess(syncUser: SyncUser?) {
        mSyncUser = syncUser
        mAuthView.navigateToOTP()
    }

    private lateinit var appCompatActivity: AppCompatActivity

    private fun setupToolbar() {
        appCompatActivity = activity as AppCompatActivity
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar!!.setDisplayShowTitleEnabled(false);
        toolbar.title = " "
        appCompatActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar!!.setHomeButtonEnabled(true)
    }

    override fun validate(): Boolean {
        var isValid = true

        if (etCreatePassword.text.toString().isEmpty()) {
            isValid = false
            etCreatePassword.error = getString(R.string.required)
        }

        if (etConfirmPassword.text.toString().isEmpty()) {
            isValid = false
            etConfirmPassword.error = getString(R.string.required)
        }

        if (isValid && !etConfirmPassword.text.toString().equals(etCreatePassword.text.toString())) {
            isValid = false
            mAuthView.onError(R.string.error_passwords_dont_match)
        }

        return isValid
    }


}