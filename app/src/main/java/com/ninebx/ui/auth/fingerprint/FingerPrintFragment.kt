package com.ninebx.ui.auth.fingerprint

import android.Manifest
import android.app.KeyguardManager
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties.*
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ninebx.R
import com.ninebx.ui.auth.BaseAuthFragment
import com.ninebx.ui.base.kotlin.handleMultiplePermission
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

    }

    override fun createKey(keyName: String, invalidatedByBiometricEnrollment: Boolean) {

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
        checkPermissions()
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
        showAuthDialog(defaultCipher, DEFAULT_KEY_NAME)
    }

    private fun setUpPurchaseButtons(cipherNotInvalidated: Cipher, defaultCipher: Cipher) {

        val keyguardManager = context!!.getSystemService(KeyguardManager::class.java)
        if (!keyguardManager.isKeyguardSecure) {
            // Show a message that the user hasn't set up a fingerprint or lock screen.
            mAuthView.onError(getString(R.string.setup_lock_screen))
            return
        }

        val fingerprintManager = context!!.getSystemService(FingerprintManager::class.java)
        if (!fingerprintManager.hasEnrolledFingerprints()) {
            // This happens when no fingerprints are registered.
            mAuthView.onError(getString(R.string.register_fingerprint))
            return
        }

        createKey(DEFAULT_KEY_NAME)
        createKey(KEY_NAME_NOT_INVALIDATED, false)

        showAuthDialog(defaultCipher, DEFAULT_KEY_NAME)
    }

    private fun showAuthDialog(cipher: Cipher, keyName: String) {
        val fragment = FingerprintAuthenticationDialogFragment()
        fragment.setCryptoObject(FingerprintManager.CryptoObject(cipher))
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
            val intent: Intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", context!!.packageName, null));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
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
}