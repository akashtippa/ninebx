package com.ninebx.ui.signIn

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.ninebx.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import android.os.AsyncTask
import android.util.Log
import io.reactivex.Observable
import io.realm.*
import io.reactivex.android.schedulers.AndroidSchedulers
import java.net.URLEncoder
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.ninebx.ui.home.HomeActivity
import com.ninebx.utility.Constants
import com.ninebx.utility.NineBxPreferences
import io.reactivex.disposables.CompositeDisposable

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    val disposables: CompositeDisposable = CompositeDisposable()

    lateinit var realm: Realm
    //    var strURL: String = "http://18.221.67.220:9080"
    var strUsername: String = "test.box24@yopmail.com"
    var strPassword: String = "[188, 156, 77, 221, 202, 199, 239, 127, 240, 3, 139, 248, 54, 89, 82, 75, 68, 77, 138, 158, 124, 167, 135, 222, 160, 208, 203, 142, 112, 179, 91, 49]"
    //    var strPassword: String = "Test.box24"


    val preferences = NineBxPreferences()


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnLogin -> {
                LoginAsync().execute()
            }
            R.id.btnSignUp -> {

            }
            R.id.txtTermsOfUse -> {

            }
            R.id.txtPrivacyPolicy -> {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Making Screen go without Toolbar
        val window = window
        val winParams = window.attributes
        winParams.flags = winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        window.attributes = winParams
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        setContentView(R.layout.activity_sign_in)
        btnLogin.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
        txtTermsOfUse.setOnClickListener(this)
        txtPrivacyPolicy.setOnClickListener(this)


        realm = Realm.getDefaultInstance()
//hash()
//
//        sha256(strPassword.toByteArray())
//        encryptPassword(strPassword)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    inner class LoginAsync : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: String?): String {

            var Result: String = "";

            try {
//                encryptPassword(strPassword)

                val utf8Bytes = strUsername.toByteArray(Charsets.UTF_8)
                val utfPaBytes = strPassword.toByteArray(Charsets.UTF_8)

                val myCredentials = SyncCredentials.usernamePassword(strUsername, strPassword, false)
                Log.e("Password Sent", " is " + strPassword)
                val user = SyncUser.login(myCredentials, Constants.SERVER_IP)

                if (user != null) {
                    if (user.isValid) {
                        showToast("User Is Valid")
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return Result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

        }
    }

    fun showToast(msg: String) {
        Observable.just(msg)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                    startActivity(intent)
                    preferences.isLogin = true
                    finish()
                })
    }

//    let password: Array<UInt8> = Array(password.utf8)
//    let salt: Array<UInt8> = Array(email.utf8)
//    PKCS5.PBKDF2(password: password,
//    salt: salt,
//    iterations: 20000,
//    variant: .sha256)
//    .calculate()


    @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    fun encryptPassword(strEncryptedPassword: String) {
        val nePassword = URLEncoder.encode(strPassword, "utf-8")
        val utf8Bytes = strUsername.toByteArray(Charsets.UTF_8)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val keyspec = PBEKeySpec(nePassword.toCharArray(), utf8Bytes, 20000, 64 * 4)
        val key = factory.generateSecret(keyspec)
        strPassword = Arrays.toString(key.encoded)
        Log.e("Encrypted ", " Password is " + strPassword)
    }

//    private val SHA_256 = "SHA-256"
//    private val DIGITS_LOWER = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
//    private val DIGITS_UPPER = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')

/*
    fun hash(): String {
        val bytes = strPassword.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        strPassword = Arrays.toString(digest)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

    fun sha256(data: ByteArray): String {
        return String(encodeHex(sha256Bytes(data)))
    }

    fun sha256(text: String): String {
        return String(encodeHex(sha256Bytes(Helper.getRawBytes(text))))
    }

    fun sha256Bytes(data: ByteArray): ByteArray {
        return getDigest(SHA_256).digest(data)
    }

    fun getDigest(algorithm: String): MessageDigest {
        try {
            return MessageDigest.getInstance(algorithm)
        } catch (e: NoSuchAlgorithmException) {
            throw IllegalArgumentException(e)
        }

    }

    fun encodeHex(data: ByteArray, toLowerCase: Boolean = true): CharArray {
        return encodeHex(data, if (toLowerCase) DIGITS_LOWER else DIGITS_UPPER)
    }

    fun encodeHex(data: ByteArray, toDigits: CharArray): CharArray {
        val l = data.size
        val out = CharArray(l shl 1)
        var i = 0
        var j = 0
        while (i < l) {
            out[j++] = toDigits[(240 and data[i].toInt()).ushr(4)]
            out[j++] = toDigits[15 and data[i].toInt()]
            i++
        }
        return out
    }

    private object Helper {

        fun getRandomString(): String = SecureRandom().nextLong().toString()

        fun getRandomBytes(size: Int): ByteArray {
            val random = SecureRandom()
            val bytes = ByteArray(size)
            random.nextBytes(bytes)
            return bytes
        }

        fun getRawBytes(text: String): ByteArray {
            try {
                return text.toByteArray(Charsets.UTF_8)
            } catch (e: UnsupportedEncodingException) {
                return text.toByteArray()
            }
        }

        fun getString(data: ByteArray): String {
            try {
                return String(data, Charsets.UTF_8)
            } catch (e: UnsupportedEncodingException) {
                return String(data)
            }

        }
    }

*/
}