package com.ninebx.ui.auth

import android.os.AsyncTask
import android.util.Base64
import com.ninebx.R
import com.ninebx.ui.auth.passwordHash.CustomKeyParameter
import com.ninebx.ui.auth.passwordHash.CustomPBEParametersGenerator
import com.ninebx.ui.auth.passwordHash.CustomPKCS5S2ParametersGenerator
import com.ninebx.ui.auth.passwordHash.PasswordEncrypter
import com.ninebx.utility.*
import io.realm.ObjectServerError
import io.realm.RealmAsyncTask
import io.realm.SyncCredentials
import io.realm.SyncUser
import org.spongycastle.crypto.digests.SHA256Digest
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec


/**
 * Created by Alok on 03/01/18.
 */
class LoginSignupTask(private var userName: String, private var password: String, private val authView: AuthView, val type: String) : AsyncTask<Void, Void, SyncCredentials?>(), SyncUser.Callback<SyncUser> {

    override fun onSuccess(result: SyncUser?) {
        authView.hideProgress()
        if (result == null) {
            authView.onError(R.string.error_login)
            prefrences.isLogin = true
        } else {
            AppLogger.d(TAG, "login : result : " + result.toString())
            AppLogger.d(TAG, result.toJson())
            authView.onSuccess(result)
        }
    }

    override fun onError(error: ObjectServerError?) {
        authView.hideProgress()
        if( error != null ) {
            error.printStackTrace()
            authView.onError(error.errorMessage!!)
        }
    }

    val TAG: String = LoginSignupTask::class.java.simpleName
    private val prefrences = NineBxPreferences()
    var strPassword: String = "[219, 80, 120, 19, 74, 36, 40, 74, 173, 169, 201, 144, 10, 213, 102, 44, 154, 239, 237, 49, 132, 210, 196, 168, 186, 136, 44, 34, 0, 30, 35, 44]"

    override fun onPreExecute() {
        super.onPreExecute()
        authView.showProgress(R.string.logging_in)
    }

    override fun onPostExecute(result: SyncCredentials?) {
        super.onPostExecute(result)
        if( result != null )
            SyncUser.loginAsync(result, Constants.SERVER_IP, this)
        else {
            authView.hideProgress()
            authView.onError(R.string.error_login)
        }
    }

    override fun doInBackground(vararg aVoid: Void?): SyncCredentials? {
        encryptViaSpongyCastle()
        val createNewUser = type == "Signup"
        return SyncCredentials.usernamePassword( userName, password, createNewUser )
    }

    /**
     *
     *
    let password: Array<UInt8> = Array(password.utf8)
    let salt: Array<UInt8> = Array(email.utf8)
    let valueTwo = try PKCS5.PBKDF2(password: password, salt: salt, iterations: 20000, variant: .sha256).calculate()
    return valueTwo.description
    [-68, -100, 77, -35, -54, -57, -17, 127, -16, 3, -117, -8, 54, 89, 82, 75, 68, 77, -118, -98, 124, -89, -121, -34, -96, -48, -53, -114, 112, -77, 91, 49]
     * */

    private fun encryptViaSpongyCastle() {

        val generator = CustomPKCS5S2ParametersGenerator(SHA256Digest())
        generator.init(CustomPBEParametersGenerator.PKCS5PasswordToUTF8Bytes(password.toCharArray()), userName.toByteArray(Charsets.UTF_8), 20000)
        val key = generator.generateDerivedMacParameters(256) as CustomKeyParameter
        password = Arrays.toString(key.key.toTypedArray())

        val intArray = kotlin.IntArray(key.key.size)
        for( index in 0 until key.key.size ) {
            var keyValue =key.key[index]
            var key = keyValue.toInt()
            if(key < 0) {
                key += 256
                intArray[index] = key
            }
            else {
                intArray[index] = key
            }
        }

        AppLogger.d(TAG, "encryptViaSpongyCastle Password generate " + password)
        AppLogger.d(TAG, "encryptViaSpongyCastle Password original " + strPassword)
        password = Arrays.toString(intArray)
        AppLogger.d(TAG, "encryptViaSpongyCastle Password non convert " +  Arrays.toString(intArray))
    }

}