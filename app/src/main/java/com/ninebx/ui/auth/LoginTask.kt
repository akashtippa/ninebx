package com.ninebx.ui.auth

import android.os.AsyncTask
import android.util.Base64
import com.ninebx.R
import com.ninebx.utility.AppLogger
import com.ninebx.utility.Constants
import io.realm.SyncCredentials
import io.realm.SyncUser
import java.math.BigInteger
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import org.spongycastle.crypto.params.KeyParameter
import org.spongycastle.crypto.PBEParametersGenerator
import org.spongycastle.crypto.digests.SHA256Digest
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator


/**
 * Created by Alok on 03/01/18.
 */
class LoginTask( private var userName : String, private var password : String, private val authView: AuthView ) : AsyncTask<Void, Void, SyncUser?>() {

    val TAG : String = LoginTask::class.java.simpleName
    var strUsername: String = "test.box24@yopmail.com"
    var strPassword: String = "[188, 156, 77, 221, 202, 199, 239, 127, 240, 3, 139, 248, 54, 89, 82, 75, 68, 77, 138, 158, 124, 167, 135, 222, 160, 208, 203, 142, 112, 179, 91, 49]"

    override fun onPostExecute(result: SyncUser?) {
        super.onPostExecute(result)
        authView.hideProgress()
        if( result == null ) {
            authView.onError(R.string.error_login)
        }
        else {
            AppLogger.d(TAG, result.toJson())
        }
        //TestFlow
        //authView.onSuccess(null)
    }

    override fun onPreExecute() {
        super.onPreExecute()
        authView.showProgress(R.string.logging_in)

    }

    override fun doInBackground(vararg aVoid: Void?): SyncUser? {

        userName = "test.box24@yopmail.com"
        password = "Test.box24"
        try {
            //encryptPassword()
            encryptViaSpongyCastle()

            val myCredentials = SyncCredentials.usernamePassword(userName, password, false)
            val user = SyncUser.login(myCredentials, Constants.SERVER_IP)
            if (user != null) {
                if (user.isValid) {
                    return user
                }
                else return null
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return null

    }

    private fun encryptViaSpongyCastle() {
        password = "Test.box24"
        val generator = PKCS5S2ParametersGenerator(SHA256Digest())
        generator.init(PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(password.toCharArray()), userName.toByteArray(Charsets.UTF_8), 20000)
        val key = generator.generateDerivedMacParameters(256) as KeyParameter
        //val key = generator.generateDerivedParameters(256, 16) as ParametersWithIV
        password = Arrays.toString(key.key)


        AppLogger.d(TAG, "encryptViaSpongyCastle Password generate " + password)
        AppLogger.d(TAG, "encryptViaSpongyCastle Password original " + strPassword)
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

    fun encryptPassword() {
        val factory = SecretKeyFactory.getInstance("PBKDF2withHmacSHA256")
        val keySpec = PBEKeySpec(password.toCharArray(), userName.toByteArray(Charsets.UTF_8), 20000, 256)
        val key = factory.generateSecret(keySpec)
        password = Arrays.toString(key.encoded.toTypedArray())
        AppLogger.d(TAG, "encryptPassword Password generate " + password)
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