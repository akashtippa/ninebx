package com.ninebx.ui.auth

import android.os.AsyncTask
import com.ninebx.R
import com.ninebx.utility.*
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.realm.ObjectServerError
import io.realm.SyncCredentials
import io.realm.SyncUser
import okhttp3.Response
import kotlin.collections.HashMap


/**
 * Created by Alok on 03/01/18.
 */
class LoginSignupTask(private var userName: String,
                      private var password: String,
                      private val authView: AuthView,
                      val type: String) : AsyncTask<Void, Void, SyncCredentials?>(),
        SyncUser.Callback<SyncUser>, Observer<Response> {

    override fun onError(e: Throwable) {
        authView.hideProgress()
    }

    override fun onComplete() {
        AppLogger.d( TAG, "GetUserAPI : onComplete" )
    }

    override fun onNext(t: Response) {
        //User already present - show error dialog and take back to login screen?
        //Attempt login

    }

    override fun onSubscribe(d: Disposable) {
        mCompositeDisposable.add(d)
    }

    override fun onSuccess(result: SyncUser?) {
        authView.hideProgress()
        if (result == null) {
            authView.onError(R.string.error_login)
            prefrences.isLogin = true
        } else {
            AppLogger.d(TAG, "login : result : " + result.toString())
            AppLogger.d(TAG, result.toJson())
            if( type == "Signup" ) {
                //Check if user is an existing user
                val userMap = HashMap<String, Any>()

                //let myDict:NSDictionary = ["user_id": userKey, "admin_id": userKey, "email": hashUserName, "hash": finalHashKey, "is_admin": true, "secure_key":secureKey]
                userMap.put("user_id", result.identity)
                userMap.put("admin_id", result.identity)
                userMap.put("email", userName)
                userMap.put("hash", encryptedPassword)
                userMap.put("is_admin", type == "Signup" )
                val randomKey = encryptKey( randomString(16), encryptedPassword )
                userMap.put("secure_key", randomKey)
                AppLogger.d(TAG, "UserMap : Random Key " + randomKey)
                AppLogger.d(TAG, "UserMap : " + userMap)

                val privateKey = randomString(16)
                val encryptedKey = encryptAESKey( privateKey, privateKey )
                AppLogger.d(TAG, "Encrypted Key : " + encryptedKey)
                val decryptedKey = decryptAESKEY( encryptedKey.toByteArray(), privateKey )
                AppLogger.d(TAG, "Decrypted Key : " + decryptedKey)

                encryptKey( privateKey, encryptedPassword )
                encryptAESKey( privateKey )
                /*NineBxApplication.getUserAPI!!.getUser( userMap )
                        .subscribeOn(Schedulers.io())
                        .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                        .subscribe(this)*/
            }
            else
            authView.onSuccess(result)
        }
    }

    override fun onError(error: ObjectServerError?) {
        if( type == "Signup" ) {
            onPostExecute(SyncCredentials.usernamePassword( userName, encryptedPassword, false ))
        }
        else {
            authView.hideProgress()
            if( error != null ) {
                error.printStackTrace()
                authView.onError(error.errorMessage!!)
            }
        }

    }

    val TAG: String = LoginSignupTask::class.java.simpleName
    private val prefrences = NineBxPreferences()
    var strPassword: String = "[219, 80, 120, 19, 74, 36, 40, 74, 173, 169, 201, 144, 10, 213, 102, 44, 154, 239, 237, 49, 132, 210, 196, 168, 186, 136, 44, 34, 0, 30, 35, 44]"
    private var syncCredentials : SyncCredentials ?= null
    private val mCompositeDisposable : CompositeDisposable = CompositeDisposable()
    private var encryptedPassword : String = ""

    override fun onPreExecute() {
        super.onPreExecute()
        authView.showProgress(R.string.logging_in)
    }

    override fun onPostExecute(result: SyncCredentials?) {
        super.onPostExecute(result)
        syncCredentials = result
        if( result != null )
            SyncUser.loginAsync(result, Constants.SERVER_IP, this)
        else {
            authView.hideProgress()
            authView.onError(R.string.error_login)
        }
    }

    override fun doInBackground(vararg aVoid: Void?): SyncCredentials? {
        encryptedPassword = (encryptKey( password, userName ))
        AppLogger.d(TAG, "Encrypted : " + encryptedPassword)
        AppLogger.d(TAG, "Encrypted iOS : " + strPassword)
        //Attempt to login with credentials by default - if successful when signing up - the user already exists
        val isSignup = (type == "Signup")

        return SyncCredentials.usernamePassword( userName, encryptedPassword, isSignup )
    }


}