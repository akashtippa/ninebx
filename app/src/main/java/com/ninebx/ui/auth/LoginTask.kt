package com.ninebx.ui.auth

import android.os.AsyncTask
import android.util.Base64
import com.ninebx.R
import com.ninebx.utility.AppLogger
import com.ninebx.utility.Constants
import com.ninebx.utility.NineBxPreferences
import com.ninebx.utility.Preferences
import io.realm.SyncCredentials
import io.realm.SyncUser
import java.math.BigInteger
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom


/**
 * Created by Alok on 03/01/18.
 */
class LoginTask(private var userName: String, private var password: String, private val authView: AuthView) : AsyncTask<Void, Void, SyncUser?>() {

    val TAG: String = LoginTask::class.java.simpleName
    var strUsername: String = "test.box24@yopmail.com"
    val prefrences = NineBxPreferences()

    var strPassword: String = "[188, 156, 77, 221, 202, 199, 239, 127, 240, 3, 139, 248, 54, 89, 82, 75, 68, 77, 138, 158, 124, 167, 135, 222, 160, 208, 203, 142, 112, 179, 91, 49]"

    override fun onPostExecute(result: SyncUser?) {
        super.onPostExecute(result)
        authView.hideProgress()
        if (result == null) {
            authView.onError(R.string.error_login)

            // Later we will put below CODE on getting Success Result.
            prefrences.isLogin = true
        } else {
            AppLogger.d(TAG, result.toJson())
        }
        //TestFlow
        authView.onSuccess(null)
    }

    override fun onPreExecute() {
        super.onPreExecute()
        authView.showProgress(R.string.logging_in)

    }

    override fun doInBackground(vararg aVoid: Void?): SyncUser? {

        userName = "test.box24@yopmail.com"
        password = "Test.box24"
        try {
            encryptPassword()

            val utf8Bytes = userName.toByteArray(Charsets.UTF_8)
            val utfPaBytes = password.toByteArray(Charsets.UTF_8)

            val myCredentials = SyncCredentials.usernamePassword(utf8Bytes.toString(), utfPaBytes.toString(), false)
            val user = SyncUser.login(myCredentials, Constants.SERVER_IP)
            if (user != null) {
                if (user.isValid) {
                    return user
                } else return null
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return null

    }

    /**
     *
     *
    let password: Array<UInt8> = Array(password.utf8)
    let salt: Array<UInt8> = Array(email.utf8)
    let valueTwo = try PKCS5.PBKDF2(password: password, salt: salt, iterations: 20000, variant: .sha256).calculate()
    return valueTwo.description
     * */

    fun encryptPassword() {
        val utf8Bytes = userName.toByteArray(Charsets.UTF_8)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val keyspec = PBEKeySpec(password.toCharArray(), utf8Bytes, 20000, 256)
        val key = factory.generateSecret(keyspec)
        password = Arrays.toString(key.encoded)
        AppLogger.d(TAG, " Password is " + password)
        AppLogger.d(TAG, "Password : " + strPassword)
    }

    @Throws(NoSuchAlgorithmException::class)
    private fun getSalt(): ByteArray {
        val sr = SecureRandom.getInstance("SHA1PRNG")
        val salt = ByteArray(16)
        sr.nextBytes(salt)
        return salt
    }

    @Throws(NoSuchAlgorithmException::class)
    private fun toHex(array: ByteArray): String {
        val bi = BigInteger(1, array)
        val hex = bi.toString(16)
        val paddingLength = array.size * 2 - hex.length
        return if (paddingLength > 0) {
            String.format("%0" + paddingLength + "d", 0) + hex
        } else {
            hex
        }
    }

    @Throws(NoSuchAlgorithmException::class)
    fun SHA256(text: String): ByteArray {
        val md = MessageDigest.getInstance("SHA-256")
        md.update(text.toByteArray())
        val digest = md.digest()
        return Base64.encode(digest, Base64.DEFAULT)

    }
}