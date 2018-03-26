package com.ninebx.ui.auth

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.home.account.addmembers.MemberPresenter
import com.ninebx.utility.*
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

    private lateinit var mCurrentUser: Users

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mCurrentUser = arguments!!.getParcelable(Constants.CURRENT_USER)

        btnSubmit.setOnClickListener {
            if (validate()) {
                mAuthView.getAuthPresenter().signUp(mCurrentUser.emailAddress, etCreatePassword.text.toString().trim())
            }
        }

        if (NineBxApplication.autoTestMode) {
            etCreatePassword.setText(TEST_PASSWORD)
            etConfirmPassword.setText(TEST_PASSWORD)
        }

        ivBackPass.setOnClickListener {
            activity!!.onBackPressed()
        }

        etConfirmPassword.setOnFocusChangeListener { _, isFocused ->
            if( isFocused ) {
                if( etCreatePassword.text.toString().isEmpty() || !isValidPassword(etCreatePassword.text.toString().trim()) ) {
                    context!!.showToast(R.string.password_rules)
                    etCreatePassword.requestFocus()
                }
            }
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
    @SuppressLint("StaticFieldLeak")
    fun onSuccess(syncUser: SyncUser?) {
        mSyncUser = syncUser

        object : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg p0: Void?) {
                prepareRealmConnections(context, true, Constants.REALM_END_POINT_USERS, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm?) {
                        mCurrentUser.id = getUniqueId()
                        mCurrentUser = encryptUsers(mCurrentUser)
                        val adminMember = Member()
                        adminMember.userId = mCurrentUser.userId
                        adminMember.country = mCurrentUser.country
                        adminMember.firstName = mCurrentUser.firstName
                        adminMember.lastName = mCurrentUser.lastName
                        adminMember.relationship = mCurrentUser.relationship
                        adminMember.zipCode = mCurrentUser.zipCode
                        adminMember.city = mCurrentUser.city
                        adminMember.state = mCurrentUser.state
                        adminMember.role = "Administrator"
                        adminMember.email = mCurrentUser.emailAddress
                        adminMember.street_1 = mCurrentUser.street_1
                        adminMember.street_2 = mCurrentUser.street_2
                        adminMember.anniversary = mCurrentUser.anniversary
                        adminMember.mobileNumber = mCurrentUser.mobileNumber
                        adminMember.completeProfile = mCurrentUser.completeProfile
                        adminMember.dateOfBirth = mCurrentUser.dateOfBirth
                        setPermissionsForMember(adminMember, adminMember.role)
                        mCurrentUser.members.add(adminMember)
                        //AppLogger.d("Encrypted", "Encrypted USer : " + mCurrentUser.toString())
                        mCurrentUser.insertOrUpdate(realm!!)

                    }
                })

            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                mAuthView.navigateToOTP(false)
            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

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

        if (isValid) {
            if (etCreatePassword.text.toString().isNotEmpty() && etCreatePassword.text.toString().trim().length < 8) {
                context!!.showToast(R.string.password_length_8)
                etCreatePassword.requestFocus()
                isValid = false
            }

            if (etCreatePassword.text.toString().isNotEmpty() && !isValidPassword(etCreatePassword.text.toString().trim())) {
                context!!.showToast(R.string.password_rules)
                etCreatePassword.requestFocus()
                isValid = false
            }

            if (etConfirmPassword.text.toString().isNotEmpty() && etConfirmPassword.text.toString().trim().length < 8) {
                context!!.showToast(R.string.password_length_8)
                etConfirmPassword.requestFocus()
                isValid = false
            }

            if (etConfirmPassword.text.toString().isNotEmpty() && !isValidPassword(etConfirmPassword.text.toString().trim())) {
                context!!.showToast(R.string.password_rules)
                etConfirmPassword.requestFocus()
                isValid = false
            }
        }

        return isValid
    }
}