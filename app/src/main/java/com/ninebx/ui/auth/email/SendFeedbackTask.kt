package com.ninebx.ui.auth.email

import android.annotation.SuppressLint
import android.app.AlertDialog
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

/**
 * Created by Sourav on 09-03-2018.
 */
@SuppressLint("StaticFieldLeak")
class SendFeedbackTask ( private val context: Context,
                         private val emailUser : String,
                         private val emailBody : String ) : AsyncTask<Void, Void, String>(), ShowDialog {


    override fun onPreExecute() {
        super.onPreExecute()
        context.showProgressDialog(context.getString(R.string.sending_feedback))
    }
    private val TAG = "SendFeedback"
    override fun doInBackground(vararg p0: Void?): String {
        try {
            val sendgrid = SendGrid("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")
            val email = SendGrid.Email()
            email.addTo("feedback@ninebx.com")
            email.from = emailUser
            email.subject = "NineBx Feedback - Version 1.00"
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
    }

    override fun showFeedbackDialog() {
        val alertDialog = AlertDialog.Builder(context).create()

        alertDialog.setTitle("NineBx")

        // Setting Dialog Message
        alertDialog.setMessage("Thank you for your valuable feedback")

        // Setting Icon to Dialog
       // alertDialog.setIcon(R.drawable.tick)

        // Setting OK Button
        alertDialog.setButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Write your code here to execute after dialog closed
            Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show()
        })

        // Showing Alert Message
        alertDialog.show()
    }
}