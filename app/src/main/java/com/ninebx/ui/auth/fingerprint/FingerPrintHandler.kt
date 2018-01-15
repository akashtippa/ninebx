package com.ninebx.ui.auth.fingerprint

import android.Manifest
import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.CancellationSignal
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import com.ninebx.ui.auth.AuthView


/**
 * Created by Alok on 15/01/18.
 */
class FingerPrintHandler(private val authView: AuthView, private val context: Context ) : FingerprintManager.AuthenticationCallback() {

    @RequiresApi(Build.VERSION_CODES.M)
    fun startAuth(manager: FingerprintManager, cryptoObject: FingerprintManager.CryptoObject) {
        val cancellationSignal = CancellationSignal()
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null)
    }


    override fun onAuthenticationError(errMsgId: Int, errString: CharSequence) {
        this.update("Fingerprint Authentication error\n" + errString, false)
    }


    override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence) {
        this.update("Fingerprint Authentication help\n" + helpString, false)
    }


    override fun onAuthenticationFailed() {
        this.update("Fingerprint Authentication failed.", false)
    }


    override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult) {
        this.update("Fingerprint Authentication succeeded.", true)
    }


    fun update(e: String, success: Boolean?) {
        authView.onError(e)
    }
}