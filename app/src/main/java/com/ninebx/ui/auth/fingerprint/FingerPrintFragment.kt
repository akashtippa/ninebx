package com.ninebx.ui.auth.fingerprint

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.KeyguardManager
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.security.keystore.KeyProperties.*
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.auth.BaseAuthFragment
import com.ninebx.ui.base.kotlin.handleMultiplePermission
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.utility.Constants
import kotlinx.android.synthetic.main.fragment_finger_print.*
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey

/**
 * Created by Alok on 15/01/18.
 * https://github.com/googlesamples/android-FingerprintDialog/blob/master/kotlinApp/app/src/main/java/com/example/android/fingerprintdialog/MainActivity.kt
 */


@RequiresApi(Build.VERSION_CODES.M)
class FingerPrintFragment : BaseAuthFragment(), FingerprintAuthenticationDialogFragment.Callback {

    override fun onPurchased(withFingerprint: Boolean, crypto: FingerprintManager.CryptoObject?) {

        if( context != null )
            context!!.showToast("Verified successfully")

        if( arguments != null && arguments!!.getBoolean(Constants.RESET_FINGER_PRINT) ) {
            activity!!.setResult(RESULT_OK, Intent())
            activity!!.finish()
        }

        tvSkip.setText(R.string.save)

    }

    override fun createKey(keyName: String, invalidatedByBiometricEnrollment: Boolean) {
        // The enrolling flow for fingerprint. This is where you ask the user to set up fingerprint
        // for your flow. Use of keys is necessary if you need to know if the set of
        // enrolled fingerprints has changed.
        try {
            keyStore.load(null)
            // Set the alias of the entry in Android KeyStore where the key will appear
            // and the constrains (purposes) in the constructor of the Builder

            val builder = KeyGenParameterSpec.Builder(keyName,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    // Require the user to authenticate with a fingerprint to authorize every use
                    // of the key
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)

            // This is a workaround to avoid crashes on devices whose API level is < 24
            // because KeyGenParameterSpec.Builder#setInvalidatedByBiometricEnrollment is only
            // visible on API level +24.
            // Ideally there should be a compat library for KeyGenParameterSpec.Builder but
            // which isn't available yet.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                builder.setInvalidatedByBiometricEnrollment(invalidatedByBiometricEnrollment)
            }
            keyGenerator.init(builder.build())
            keyGenerator.generateKey()
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        } catch (e: InvalidAlgorithmParameterException) {
            throw RuntimeException(e)
        } catch (e: CertificateException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }

    override fun validate(): Boolean {
        return true
    }

    private val PERMISSIONS_REQUEST_CODE_FINGER_PRINT = 113

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_finger_print, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switchTouchId.setOnCheckedChangeListener { _, isChecked ->
            NineBxApplication.getPreferences().isFingerPrintEnabled = isChecked
            if (isChecked)
                checkPermissions()

        }
        tvSkip.setOnClickListener {
            if (tvSkip.text.toString() == "Skip") {
                mAuthView.navigateToHome()
            } else {
                mAuthView.navigateToInvitePeople()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvQuickAccess.text = Html.fromHtml(getString(R.string.allow_quicker_access), Html.FROM_HTML_MODE_LEGACY)
        } else {
            tvQuickAccess.text = Html.fromHtml(getString(R.string.allow_quicker_access))
        }

        switchTouchId.isChecked = NineBxApplication.getPreferences().isFingerPrintEnabled
        if( arguments != null && arguments!!.getBoolean(Constants.RESET_FINGER_PRINT) ) {
            checkPermissions()
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermissions() {
        val permissionList = arrayListOf<String>(Manifest.permission.USE_FINGERPRINT)
        if (!handleMultiplePermission(context!!, permissionList)) {
            requestPermissions( permissionList.toTypedArray(), PERMISSIONS_REQUEST_CODE_FINGER_PRINT)
        }
        else {
            loadFingerPrintAuth()
        }
    }

    private lateinit var keyStore: KeyStore
    private lateinit var keyGenerator: KeyGenerator
    private lateinit var sharedPreferences: SharedPreferences
    private fun loadFingerPrintAuth() {
        setupKeyStoreAndKeyGenerator()
        val (defaultCipher: Cipher, cipherNotInvalidated: Cipher) = setupCiphers()
        setUpPurchaseButtons(cipherNotInvalidated, defaultCipher)
        //showAuthDialog(defaultCipher, DEFAULT_KEY_NAME)
    }

    private fun setUpPurchaseButtons(cipherNotInvalidated: Cipher, defaultCipher: Cipher) {

        val keyguardManager = context!!.getSystemService(KeyguardManager::class.java)
        if (!keyguardManager.isKeyguardSecure) {
            // Show a message that the user hasn't set up a fingerprint or lock screen.
            mAuthView.onError(getString(R.string.setup_lock_screen))
            switchTouchId.isChecked = false
            return
        }

        val fingerprintManager = context!!.getSystemService(FingerprintManager::class.java)
        if (!fingerprintManager.hasEnrolledFingerprints()) {
            // This happens when no fingerprints are registered.
            mAuthView.onError(getString(R.string.register_fingerprint))
            switchTouchId.isChecked = false
            return
        }

        createKey(DEFAULT_KEY_NAME)
        createKey(KEY_NAME_NOT_INVALIDATED, false)

        showAuthDialog(defaultCipher, DEFAULT_KEY_NAME)
    }

    private fun showAuthDialog(cipher: Cipher, keyName: String) {
        val fragment = FingerprintAuthenticationDialogFragment()
        fragment.setCryptoObject(FingerprintManager.CryptoObject(cipher))
        fragment.setAuthView(mAuthView)
        fragment.setCallback(this)

        // Set up the crypto object for later, which will be authenticated by fingerprint usage.
        if (initCipher(cipher, keyName)) {

            // Show the fingerprint dialog. The user has the option to use the fingerprint with
            // crypto, or can fall back to using a server-side verified password.
            val useFingerprintPreference = sharedPreferences
                    .getBoolean(getString(R.string.use_fingerprint_to_authenticate_key), true)
            if (useFingerprintPreference) {
                fragment.setStage(Stage.FINGERPRINT)
            } else {
                fragment.setStage(Stage.PASSWORD)
            }
        } else {
            // This happens if the lock screen has been disabled or or a fingerprint was
            // enrolled. Thus, show the dialog to authenticate with their password first and ask
            // the user if they want to authenticate with a fingerprint in the future.
            fragment.setStage(Stage.NEW_FINGERPRINT_ENROLLED)
        }
        fragment.show(childFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    /**
     * Initialize the [Cipher] instance with the created key in the [createKey] method.
     *
     * @param keyName the key name to init the cipher
     * @return `true` if initialization succeeded, `false` if the lock screen has been disabled or
     * reset after key generation, or if a fingerprint was enrolled after key generation.
     */
    private fun initCipher(cipher: Cipher, keyName: String): Boolean {
        try {
            keyStore.load(null)
            cipher.init(Cipher.ENCRYPT_MODE, keyStore.getKey(keyName, null) as SecretKey)
            return true
        } catch (e: Exception) {
            when (e) {
                is KeyPermanentlyInvalidatedException -> return false
                is KeyStoreException,
                is CertificateException,
                is UnrecoverableKeyException,
                is IOException,
                is NoSuchAlgorithmException,
                is InvalidKeyException -> throw RuntimeException("Failed to init Cipher", e)
                else -> throw e
            }
        }
    }

    /**
     * Sets up default cipher and a non-invalidated cipher
     */

    private fun setupCiphers(): Pair<Cipher, Cipher> {
        val defaultCipher: Cipher
        val cipherNotInvalidated: Cipher
        try {
            val cipherString = "$KEY_ALGORITHM_AES/$BLOCK_MODE_CBC/$ENCRYPTION_PADDING_PKCS7"
            defaultCipher = Cipher.getInstance(cipherString)
            cipherNotInvalidated = Cipher.getInstance(cipherString)
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is NoSuchPaddingException ->
                    throw RuntimeException("Failed to get an instance of Cipher", e)
                else -> throw e
            }
        }
        return Pair(defaultCipher, cipherNotInvalidated)
    }

    private fun setupKeyStoreAndKeyGenerator() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        try {
            keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        } catch (e: KeyStoreException) {
            throw RuntimeException("Failed to get an instance of KeyStore", e)
        }

        try {
            keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM_AES, ANDROID_KEY_STORE)
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is NoSuchProviderException ->
                    throw RuntimeException("Failed to get an instance of KeyGenerator", e)
                else -> throw e
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
       if( requestCode == PERMISSIONS_REQUEST_CODE_FINGER_PRINT ) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadFingerPrintAuth()
            } else if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED){
                loadDialog()
            }
        }
    }

    private fun loadDialog() {
        val dialogBuilder = AlertDialog.Builder(context!!)
        dialogBuilder.setTitle("Finger print permission required to enable instant access")
        dialogBuilder.setMessage("Allow app to access finger print?")
        dialogBuilder.setPositiveButton("Open App Permission") { dialog, whichButton ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", context!!.packageName, null))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, whichButton ->
            Toast.makeText(context, "Some permissions were denied", Toast.LENGTH_LONG).show()
        }
        val b = dialogBuilder.create()
        b.show()
    }

    companion object {
        private val ANDROID_KEY_STORE = "AndroidKeyStore"
        private val DIALOG_FRAGMENT_TAG = "myFragment"
        private val KEY_NAME_NOT_INVALIDATED = "key_not_invalidated"
        private val SECRET_MESSAGE = "Very secret message"
        private val TAG = FingerPrintFragment::class.java.simpleName
    }

    fun fingerPrintCancelled() {
        if (switchTouchId != null) switchTouchId.isChecked = false

        if( arguments != null && arguments!!.getBoolean(Constants.RESET_FINGER_PRINT) ) {
            activity!!.setResult(RESULT_OK, Intent())
            activity!!.finish()
        }
    }
}