package com.ninebx.ui.auth

import android.os.AsyncTask
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.network.SignInResponse
import com.ninebx.utility.*
import com.ninebx.utility.Constants.NONE_COMPLETE
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.ObjectServerError
import io.realm.SyncCredentials
import io.realm.SyncUser
import okhttp3.ResponseBody
import java.util.*
import kotlin.collections.HashMap


/**
 * Created by Alok on 03/01/18.
 */
class LoginSignupTask(private var userName: String,
                      private var password: String,
                      private val authView: AuthView,
                      var type: String) : AsyncTask<Void, Void, SyncCredentials?>(),
        SyncUser.Callback<SyncUser> {


    override fun onSuccess(result: SyncUser?) {

        if (result == null) {
            authView.hideProgress()
            authView.onError(R.string.error_login)
            prefrences.isLogin = true
        } else {
            AppLogger.d(TAG, "login : result : " + result.toString())
            AppLogger.d(TAG, result.toJson())
            mCurrentUser = result
            if (type == "Signup") {

                //Save user data to realm
                val userMap = HashMap<String, Any>()

                //let myDict:NSDictionary = ["user_id": userKey, "admin_id": userKey, "email": hashUserName, "hash": finalHashKey, "is_admin": true, "secure_key":secureKey]
                userMap.put("user_id", result.identity)
                userMap.put("admin_id", result.identity)
                userMap.put("email", userName)
                userMap.put("hash", encryptedPassword)
                userMap.put("is_admin", type == "Signup")

                val privateKey = randomString(16)
                NineBxApplication.getPreferences().privateKey = privateKey

                val encryptedPrivateKey = encryptAESKeyPassword(privateKey, encryptedPasswordByteArray)

                AppLogger.d(TAG, "Encrypted Key : " + encryptedPrivateKey)

                userMap.put("secure_key", encryptedPrivateKey)
                AppLogger.d(TAG, "UserMap : Random Key " + privateKey)
                AppLogger.d(TAG, "UserMap : " + userMap)

                val decryptedKey = decryptAESKEYPassword(encryptedPrivateKey.toByteArray(), encryptedPasswordByteArray)
                AppLogger.d(TAG, "Decrypted Key : " + decryptedKey)

                NineBxApplication.getUserAPI()!!.postUserDetails(userMap)
                        .subscribeOn(Schedulers.io())
                        .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                        .subscribe(getSignupResponse())
            } else {

                NineBxApplication.getUserAPI()!!.getUserDetails("eq." + result.identity)
                        .subscribeOn(Schedulers.io())
                        .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                        .subscribe(getResponseMap())

            }

        }
    }

    private fun getSignupResponse(): Observer<ResponseBody> {
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
                AppLogger.d(TAG, "Successfully saved userMap : " + String(t.bytes()))
                authView.onSuccess(mCurrentUser)
            }

            override fun onError(e: Throwable) {
                authView.hideProgress()
            }

            override fun onComplete() {
                AppLogger.d(TAG, "GetUserAPI : onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                mCompositeDisposable.add(d)
            }

        }
    }

    private fun getResponseMap(): Observer<ArrayList<SignInResponse>> {
        return object : Observer<ArrayList<SignInResponse>> {
            override fun onError(e: Throwable) {
                authView.hideProgress()
            }

            override fun onComplete() {
                AppLogger.d(TAG, "GetUserAPI : onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                mCompositeDisposable.add(d)
            }

            override fun onNext(responseList: ArrayList<SignInResponse>) {
                AppLogger.d(TAG, "Response : Signin : " + responseList.toString())
                val privateKey = decryptAESKEYPassword(responseList[0].secureKey.toByteArray(), encryptedPasswordByteArray)
                AppLogger.d(TAG, "Save user secure key : " + privateKey)
                NineBxApplication.getPreferences().privateKey = privateKey
                authView.onSuccess(mCurrentUser)
            }

        }
    }


    override fun onError(error: ObjectServerError?) {
        if (error != null) {
            error.printStackTrace()
        }
        if (type == "Signup") {
            authView.hideProgress()
            if (error != null) {
                error.printStackTrace()
                authView.onError(error.errorMessage!!)
            }
            NineBxApplication.getPreferences().currentStep = NONE_COMPLETE
            authView.navigateToStart()
            //type = "Signin"
            //onPostExecute(SyncCredentials.usernamePassword( userName, Arrays.toString(encryptedPassword), false ))
        } else {
            authView.hideProgress()
            if (error != null) {
                error.printStackTrace()
                authView.onError(error.errorMessage!!)
            }
        }

    }

    val TAG: String = LoginSignupTask::class.java.simpleName
    private val prefrences = NineBxPreferences()
    var strPassword: String = "[219, 80, 120, 19, 74, 36, 40, 74, 173, 169, 201, 144, 10, 213, 102, 44, 154, 239, 237, 49, 132, 210, 196, 168, 186, 136, 44, 34, 0, 30, 35, 44]"
    private var syncCredentials: SyncCredentials? = null
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var encryptedPasswordByteArray: ByteArray
    private lateinit var encryptedPassword: IntArray
    private var mCurrentUser: SyncUser? = null

    override fun onPreExecute() {
        super.onPreExecute()
        authView.showProgress(R.string.logging_in)
    }

    override fun onPostExecute(result: SyncCredentials?) {
        super.onPostExecute(result)
        syncCredentials = result
        if (result != null) {
            SyncUser.loginAsync(result, Constants.SERVER_IP, this)
        } else {
            authView.hideProgress()
            authView.onError(R.string.error_login)
        }
    }

    override fun doInBackground(vararg aVoid: Void?): SyncCredentials? {

        encryptedPasswordByteArray = (encryptKey(password, userName))
        encryptedPassword = convertToUInt8IntArray(encryptedPasswordByteArray)

        AppLogger.d(TAG, "Encrypted : " + encryptedPassword)
        AppLogger.d(TAG, "Encrypted iOS : " + strPassword)
        //Attempt to login with credentials by default - if successful when signing up - the user already exists
        val isSignup = (type == "Signup")

        return SyncCredentials.usernamePassword(userName, Arrays.toString(encryptedPassword), isSignup)
    }


}