package com.ninebx.ui.auth

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.Users
import com.ninebx.utility.Constants
import com.ninebx.utility.insertOrUpdate
import com.ninebx.utility.isValidPassword
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import io.realm.SyncUser
import kotlinx.android.synthetic.main.fragment_account_password.*

/**
 * Created by Alok on 04/01/18.
 */

class AccountPasswordFragment : BaseAuthFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account_password, container, false)
    }

    private lateinit var mCurrentUser : Users

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mCurrentUser = arguments!!.getParcelable(Constants.CURRENT_USER)

        btnSubmit.setOnClickListener {
            if (validate()) {
                mAuthView.getAuthPresenter().signUp(mCurrentUser.emailAddress, etCreatePassword.text.toString().trim())
            }
        }

        if (NineBxApplication.autoTestMode) {
            etCreatePassword.setText("Password14.")
            etConfirmPassword.setText("Password14.")
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

        prepareRealmConnections( context, true, "Users", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                mCurrentUser.insertOrUpdate( realm!! )
                NineBxApplication.getPreferences().setCurrentUser( mCurrentUser )
                mAuthView.navigateToOTP()
            }
        })
    }

    override fun validate(): Boolean {
        var isValid = true

        if (etCreatePassword.text.toString().isEmpty()) {
            isValid = false
            etCreatePassword.requestFocus()
            mAuthView.onError(R.string.error_empty_password)
            return isValid
        }

        if (etConfirmPassword.text.toString().isEmpty()) {
            isValid = false
            etConfirmPassword.requestFocus()
            mAuthView.onError(R.string.error_empty_password)
            return isValid
        }

        if (isValid && !etConfirmPassword.text.toString().equals(etCreatePassword.text.toString())) {
            isValid = false
            mAuthView.onError(R.string.error_passwords_dont_match)
            return isValid
        }

        if( isValid ) {
            if( etCreatePassword.text.toString().isNotEmpty() && etCreatePassword.text.toString().trim().length < 8 ) {
                context!!.showToast(R.string.password_length_8)
                etCreatePassword.requestFocus()
                isValid = false
            }

            if( etCreatePassword.text.toString().isNotEmpty() && !isValidPassword(etCreatePassword.text.toString().trim()) ) {
                context!!.showToast(R.string.password_rules)
                etCreatePassword.requestFocus()
                isValid = false
            }
            if( etConfirmPassword.text.toString().isNotEmpty() && etConfirmPassword.text.toString().trim().length < 8 ) {
                context!!.showToast(R.string.password_length_8)
                etConfirmPassword.requestFocus()
                isValid = false
            }

            if( etConfirmPassword.text.toString().isNotEmpty() && !isValidPassword(etConfirmPassword.text.toString().trim()) ) {
                context!!.showToast(R.string.password_rules)
                etConfirmPassword.requestFocus()
                isValid = false
            }
        }

        return isValid
    }
}