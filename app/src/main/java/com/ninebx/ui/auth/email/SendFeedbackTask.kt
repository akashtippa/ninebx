package com.ninebx.ui.auth.email

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.app.FragmentManager
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.showProgressDialog
import com.sendgrid.SendGrid
import com.sendgrid.SendGridException
import java.io.IOException
import android.widget.Toast
import io.realm.internal.SyncObjectServerFacade.getApplicationContext
import android.content.DialogInterface
import android.os.Build
import com.ninebx.BuildConfig
import com.ninebx.NineBxApplication
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.account.AccountFragment
import com.ninebx.utility.AppLogger

/**
 * Created by Sourav on 09-03-2018.
 */
@SuppressLint("StaticFieldLeak")
class SendFeedbackTask (
                        val dialogFragment: Dialog,
                        private val context: Context,
                         private val emailUser : String,
                         private val emailBody : String ) : AsyncTask<Void, Void, String>(), ShowDialog {


    override fun onPreExecute() {
        super.onPreExecute()
        context.showProgressDialog(context.getString(R.string.sending_feedback))
    }
    private val TAG = "SendFeedback"
    var versionCode=BuildConfig.VERSION_CODE
    override fun doInBackground(vararg p0: Void?): String {
        try {
            val sendgrid = SendGrid("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")
            val email = SendGrid.Email()
            email.addTo("souravstudy@gmail.com")
            email.from = emailUser
            email.subject = "NineBx Feedback - "+versionCode
            email.text = emailBody

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

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        context.hideProgressDialog()
        showFeedbackDialog()
        AppLogger.e("on post execute" , "true")
    }

    override fun showFeedbackDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("NineBx")
        builder.setIcon(R.mipmap.ic_launcher)
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { p0, p1 ->
            //NineBxApplication.instance.activityInstance!!.onBackPressed()
           // NineBxApplication.instance.activityInstance!!.onBackPressed()
            dialogFragment.cancel()
        }
        builder.setMessage("Thank you for your valuable feedback!")
        builder.show()
    }
}