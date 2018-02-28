package com.ninebx.ui.home.account.changePassword

import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.utility.*
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.ObjectServerError
import io.realm.SyncCredentials
import io.realm.SyncUser
import okhttp3.ResponseBody
import java.util.*

/**
 * Created by Alok on 22/02/18.
 */
class MasterPasswordPresenter(private val masterPasswordView: MasterPasswordView ) : SyncUser.Callback<SyncUser> {

    private val TAG = MasterPasswordPresenter::class.java.simpleName

    override fun onSuccess(result: SyncUser?) {
        saveResult( result!! )
        changeMasterPasswordForMembers( )
        NineBxApplication.getPreferences().userPassword = Arrays.toString(encryptedPasswordByteArray)
    }

    private fun changeMasterPasswordForMembers() {
        val members = masterPasswordView.getCurrentUsers()[0].members
        //If only 1 user is present - it means no members are added yet
        if( members.size > 0 ) {
            for( familyUser in members ) {
                val userEmail = familyUser.email.decryptString()
                if( userEmail != NineBxApplication.getPreferences().userEmail ) {
                    val newPassword = Arrays.toString(convertToUInt8IntArray((encryptKey(masterPasswordView.getNewPassword(), userEmail))))
                    val userPassword = masterPasswordView.getCurrentPassword()
                    val encryptedPasswordByteArray = (encryptKey(userPassword, userEmail))
                    val encryptedPassword = convertToUInt8IntArray(encryptedPasswordByteArray)
                    val credential = SyncCredentials.usernamePassword( userEmail, Arrays.toString(encryptedPassword), false )
                    SyncUser.loginAsync(credential, Constants.SERVER_IP, object : SyncUser.Callback<SyncUser> {
                        override fun onSuccess(result: SyncUser?) {
                            if( result != null ) {
                                resetPasswordForMember( result, newPassword )
                            }
                        }

                        override fun onError(error: ObjectServerError?) {
                            error!!.printStackTrace()
                            masterPasswordView.onError(R.string.error_change_password_members)
                        }
                    })
                }
            }
            masterPasswordView.hideProgress()
            masterPasswordView.onPasswordReset(R.string.password_reset_success)
        }
        else {
            masterPasswordView.hideProgress()
            masterPasswordView.onPasswordReset(R.string.password_reset_success)
        }
    }

    private fun resetPasswordForMember(result: SyncUser, newPassword: String?) {
        result.changePasswordAsync( newPassword!!, object : SyncUser.Callback<SyncUser> {
            override fun onSuccess(result: SyncUser?) {
                //Save user data to realm
                val userMap = HashMap<String, Any>()
                userMap.put("secure_key", newPassword)

                NineBxApplication.getUserAPI()!!.putUserDetails("eq." + result!!.identity, userMap)
                        .subscribeOn(Schedulers.io())
                        .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                        .subscribe(getSignUpResponse())
                result.logout()
            }

            override fun onError(error: ObjectServerError?) {
                error!!.printStackTrace()
                masterPasswordView.onError(R.string.error_change_password_members)
            }
        } )
    }

    private fun saveResult(result: SyncUser) {
        //Save user data to realm
        val userMap = HashMap<String, Any>()

        //let myDict:NSDictionary = ["user_id": userKey, "admin_id": userKey, "email": hashUserName, "hash": finalHashKey, "is_admin": true, "secure_key":secureKey]
        userMap.put("user_id", result.identity)
        userMap.put("admin_id", result.identity)
        userMap.put("email", NineBxApplication.getPreferences().userEmail!!)
        userMap.put("hash", encryptedPassword)
        userMap.put("is_admin", true)

        val privateKey =  NineBxApplication.getPreferences().privateKey

        val encryptedPrivateKey = encryptAESKeyPassword(privateKey!!, encryptedPasswordByteArray)
        userMap.put("secure_key", encryptedPrivateKey)

        NineBxApplication.getUserAPI()!!.postUserDetails(userMap)
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(getSignUpResponse())
    }

    private var isMasterPasswordReset: Boolean = false

    private fun getSignUpResponse(): Observer<ResponseBody> {
        return object : Observer<ResponseBody> {
            //[
            // {"user_id":"f2184c44c0a564dbbae1f9a0014a48bf",
            // "admin_id":"f2184c44c0a564dbbae1f9a0014a48bf",
            // "is_admin":true,
            // "hash":"[164, 219, 42, 177, 85, 148, 90, 49, 197, 147, 198, 137, 151, 158, 162, 66, 47, 189, 36, 137, 98, 11, 82, 198, 39, 63, 179, 37, 44, 20, 49, 27]",
            // "email":"jeyachandran.m@cognitiveclouds.com",
            // "secure_key":"i4+nCkYYPpSrrMVIx9wMkNulVw+FvN7d/u5f8S2pjSM="}
            // ]
            override fun onNext(t: ResponseBody) {
                //User details saved successfully - save user object to realm
                //AppLogger.d(TAG, "Successfully saved userMap : " + String(t.bytes()))

                if( !isMasterPasswordReset ) {
                    isMasterPasswordReset = true
                    return
                }
                masterPasswordView.hideProgress()
                masterPasswordView.onPasswordReset(R.string.password_reset_success)
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {
                //AppLogger.d(TAG, "GetUserAPI : onComplete")
            }

            override fun onSubscribe(d: Disposable) {

            }

        }
    }

    override fun onError(error: ObjectServerError?) {
        error!!.printStackTrace()
        masterPasswordView.onError(R.string.error_resetting_password)
    }

    private lateinit var encryptedPasswordByteArray: ByteArray
    private lateinit var encryptedPassword: IntArray

    fun changePassword() {
        val password = masterPasswordView.getNewPassword()

        encryptedPasswordByteArray = (encryptKey(password, NineBxApplication.getPreferences().userEmail!!))
        encryptedPassword = convertToUInt8IntArray(encryptedPasswordByteArray)

        masterPasswordView.showProgress(R.string.loading)
        SyncUser.currentUser().changePasswordAsync( Arrays.toString(encryptedPassword), this )
    }

}