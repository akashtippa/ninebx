package com.ninebx.ui.auth.email

import android.os.AsyncTask
import android.util.Log
import com.ninebx.R
import com.ninebx.ui.auth.AuthView
import com.sendgrid.SendGridException
import com.sendgrid.SendGrid
import java.io.IOException


/**
 * Created by Alok on 30/01/18.
 */
class SendEmailTask( private val emailOtp : String, private val emailId : String, private val authView : AuthView ) : AsyncTask<Void, Void, String>() {

    override fun onCancelled(result: String?) {
        super.onCancelled(result)
        authView.hideProgress()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        authView.hideProgress()
        authView.validateEmailOTP( emailOtp )
    }

    override fun onPreExecute() {
        super.onPreExecute()
        authView.showProgress(R.string.requesting_otp)
    }


    override fun doInBackground(vararg p0: Void?): String {

        try {
            val sendgrid = SendGrid("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")

            val email = SendGrid.Email()

            email.addTo(emailId)
            email.from = "ninebx.support@nineBx.com"
            email.subject = "OTP confirmation alert for your NineBx Application!"
            email.text = emailOtp

            // Send email, execute http request
            val response = sendgrid.send(email)

            Log.d("SendAppExample", response.message)
            return response.message
        } catch (e: SendGridException) {
            Log.e("SendAppExample", e.toString())
        } catch (e: IOException) {
            Log.e("SendAppExample", e.toString())
        }

        return ""


    }
}