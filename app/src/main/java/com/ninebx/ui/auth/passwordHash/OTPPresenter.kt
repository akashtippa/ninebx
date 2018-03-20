package com.ninebx.ui.auth.passwordHash

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Handler
import android.widget.EditText
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.R.id.etOtp1
import com.ninebx.R.id.tvResend
import com.ninebx.ui.auth.AuthView
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.progressDialog
import com.ninebx.ui.base.kotlin.showProgressDialog
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.utility.*
import io.realm.Realm

/**
 * Created by Sourav on 16-03-2018.
 */
class OTPPresenter(var context: Context?, var mOTPView: OTPView, var mAuthView: AuthView) {


        var handler: Handler = Handler()
        var emailOtp = ""

    var isSuccess : Boolean = false



    fun submit(etOtp1: EditText? , etOtp2: EditText? , etOtp3: EditText? , etOtp4: EditText? ,etOtp5: EditText? ,etOtp6: EditText? , emailOtpString:String){

        emailOtp = emailOtpString

        context!!.showProgressDialog("Loading")

        if( validate( etOtp1,etOtp2 , etOtp3 ,etOtp4 ,etOtp5 ,etOtp6 ) ) {

            emailOtp = ""
            handler.removeCallbacks(runnable)
            object : AsyncTask<Void, Void, Int>() {
                override fun doInBackground(vararg p0: Void?) : Int {
                    prepareRealmConnections( context, true, Constants.REALM_END_POINT_USERS, object : Realm.Callback( ) {
                        override fun onSuccess(realm: Realm?) {
                            val currentUsers = getCurrentUsers( realm!! )
                            if (currentUsers != null && currentUsers.size > 0) {
                                val email = currentUsers[0]!!.emailAddress.decryptString()
                                if( email.isNotEmpty() )
                                    NineBxApplication.getPreferences().userEmail = email
                                  AppLogger.d("Email", "OTP prepareRealmConnections " + NineBxApplication.getPreferences().userEmail!!)
                            }
                            else {
                                onPostExecute(R.string.unable_to_find_user)
                            }
                        }



                    })
                    return -1
                }

                override fun onPostExecute(result: Int) {
                    super.onPostExecute(result)
                    context!!.hideProgressDialog()
                    isSuccess = true
                    mAuthView.navigateToCreatePassCode(Constants.PASSCODE_CREATE, "")

                    if( result != -1 ) mAuthView.onError(result)
                }


            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

        }else{

            val builder = AlertDialog.Builder(context)
            builder.setTitle("NineBx")
            builder.setIcon(R.mipmap.ic_launcher)
            builder.setPositiveButton("OK"  ,object :  DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0?.cancel()
                }
            })
            builder.setMessage("Incorrect OTP")
            builder.show()
        }

        context!!.hideProgressDialog()
    }




    private var runnable: Runnable = Runnable {
        if (tvResend != null) {
            if(mOTPView.otpVerificationGet()){
                context!!.showToast(R.string.otp_expired)
            }
           // emailOtp = ""
        }

    }



    fun validate(etOtp1: EditText?,etOtp2: EditText?,etOtp3: EditText?,etOtp4: EditText?,etOtp5: EditText?,etOtp6: EditText?): Boolean {
        var isValid = true

        if( !validateView( etOtp1 ) ) isValid = false
        if( !validateView( etOtp2 ) ) isValid = false
        if( !validateView( etOtp3 ) ) isValid = false
        if( !validateView( etOtp4 ) ) isValid = false
        if( !validateView( etOtp5 ) ) isValid = false
        if( !validateView( etOtp6 ) ) isValid = false

        if( isValid && emailOtp != "") {
            val otp = etOtp1?.text.toString().trim() +
                    etOtp2?.text.toString().trim() +
                    etOtp3?.text.toString().trim() +
                    etOtp4?.text.toString().trim() +
                    etOtp5?.text.toString().trim() +
                    etOtp6?.text.toString().trim()
            isValid = emailOtp == otp


        }
        else if( emailOtp == "" ) {
            isValid = false
            context!!.showToast(R.string.otp_expired)
        }

        return isValid
    }


     fun validateView(etOtp: EditText?): Boolean {
        val isValid = etOtp!!.text.toString().isNotEmpty()
        if( !isValid ) {
            etOtp.error = context?.getString(R.string.required)

        }
        return isValid
    }




}