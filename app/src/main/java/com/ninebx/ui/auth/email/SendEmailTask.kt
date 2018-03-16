package com.ninebx.ui.auth.email

import android.content.Context
import android.os.AsyncTask
import android.support.v4.content.ContextCompat
import android.text.Html
import android.util.Log
import com.ninebx.R
import com.ninebx.ui.auth.AuthView
import com.sendgrid.SendGridException
import com.sendgrid.SendGrid
import java.io.IOException
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.SpannableString
import com.ninebx.utility.AppLogger
import io.realm.internal.SyncObjectServerFacade.getApplicationContext


/**
 * Created by Alok on 30/01/18.
 */
class SendEmailTask( private val emailOtp : String,
                     private val emailId : String,
                     private val authView : AuthView ) : AsyncTask<Void, Void, String>() {

    private val TAG = SendEmailTask::class.java.simpleName

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
            /*ContextCompat.getColor(parent!!.context, R.color.red_300)*/
            val wordtoSpan = SpannableString(emailOtp)
            wordtoSpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.otp_color)), 0, wordtoSpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            val sendgrid = SendGrid("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")
            val email = SendGrid.Email()
            email.addTo(emailId)
            email.from = "ninebx.support@nineBx.com"
            email.subject = "NineBx - Authentication code"
            email.text = "Dear User,\n" +
                    "\n" +
                    "Here is your one-time, time-based code to authenticate your device.\n" +
                    "\n" +
                    "Authentication code: " + wordtoSpan + "\n" +
                    "\n" + "This is a time-sensitive code. Please enter it immediately to complete sign in.\n" +
                    "\n" + "Thanks!\n" +
                    "\n" + "The NineBx Team"

            AppLogger.d("otpColor", " " + wordtoSpan)

            /*Html.fromHtml("<font color=#ff0000>" + appdate + "</font>");*/
            // Send email, execute http request
            val response = sendgrid.send(email)
            Log.d(TAG, response.message)
            return response.message
        } catch (e: SendGridException) {
            Log.e(TAG, e.toString())
        } catch (e: IOException) {
            Log.e(TAG, e.toString())
        }
        return ""
    }
}