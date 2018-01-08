package com.ninebx.ui.home.passcode


import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import com.ninebx.R
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.ninebx.utility.AppLogger




/**
 * Created by Alok on 08/01/18.
 */
class PassCodeDialog( val context : Context, val passCode : String, val passCodeListener : PassCodeDialogListener ) {

    private var dialog: Dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
    private val TAG = PassCodeDialog::class.java.simpleName

    init {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_pass_code)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)

        val window = dialog.window
        val wlp = window.attributes

        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window.attributes = wlp

        val etPassCode = dialog.findViewById<EditText>(R.id.etPassCode)

        val ivOtp1 = dialog.findViewById<ImageView>(R.id.ivOtp1)
        val ivOtp2 = dialog.findViewById<ImageView>(R.id.ivOtp2)
        val ivOtp3 = dialog.findViewById<ImageView>(R.id.ivOtp3)
        val ivOtp4 = dialog.findViewById<ImageView>(R.id.ivOtp4)
        val ivOtp5 = dialog.findViewById<ImageView>(R.id.ivOtp5)
        val ivOtp6 = dialog.findViewById<ImageView>(R.id.ivOtp6)

        etPassCode.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {


            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                when( etPassCode.text.toString().trim().length ) {

                    0 -> {
                        ivOtp1.isSelected = false
                        ivOtp2.isSelected = false
                        ivOtp3.isSelected = false
                        ivOtp4.isSelected = false
                        ivOtp5.isSelected = false
                        ivOtp6.isSelected = false
                    }
                    1 -> {
                        ivOtp1.isSelected = true
                        ivOtp2.isSelected = false
                        ivOtp3.isSelected = false
                        ivOtp4.isSelected = false
                        ivOtp5.isSelected = false
                        ivOtp6.isSelected = false
                    }
                    2 -> {
                        ivOtp1.isSelected = true
                        ivOtp2.isSelected = true
                        ivOtp3.isSelected = false
                        ivOtp4.isSelected = false
                        ivOtp5.isSelected = false
                        ivOtp6.isSelected = false
                    }
                    3 -> {
                        ivOtp1.isSelected = true
                        ivOtp2.isSelected = true
                        ivOtp3.isSelected = true
                        ivOtp4.isSelected = false
                        ivOtp5.isSelected = false
                        ivOtp6.isSelected = false
                    }
                    4 -> {
                        ivOtp1.isSelected = true
                        ivOtp2.isSelected = true
                        ivOtp3.isSelected = true
                        ivOtp4.isSelected = true
                        ivOtp5.isSelected = false
                        ivOtp6.isSelected = false
                    }
                    5 -> {
                        ivOtp1.isSelected = true
                        ivOtp2.isSelected = true
                        ivOtp3.isSelected = true
                        ivOtp4.isSelected = true
                        ivOtp5.isSelected = true
                        ivOtp6.isSelected = false
                    }
                    6 -> {
                        ivOtp1.isSelected = true
                        ivOtp2.isSelected = true
                        ivOtp3.isSelected = true
                        ivOtp4.isSelected = true
                        ivOtp5.isSelected = true
                        ivOtp6.isSelected = true

                        val text = etPassCode.text.toString().trim()

                        AppLogger.d(TAG, "Passcode : " + text)
                        if( text.length == 6 ) {
                            if( text.equals(passCode) ) {
                                passCodeListener.onSuccess()
                                dialog.dismiss()
                            }
                            else {
                                passCodeListener.onFailure(R.string.error_wrong_passcode)
                            }
                        }

                    }
                }
            }

        })

        val tvNum0 = dialog.findViewById<TextView>(R.id.tvNum0)
        val tvNum1 = dialog.findViewById<TextView>(R.id.tvNum1)
        val tvNum2 = dialog.findViewById<TextView>(R.id.tvNum2)
        val tvNum3 = dialog.findViewById<TextView>(R.id.tvNum3)
        val tvNum4 = dialog.findViewById<TextView>(R.id.tvNum4)
        val tvNum5 = dialog.findViewById<TextView>(R.id.tvNum5)
        val tvNum6 = dialog.findViewById<TextView>(R.id.tvNum6)
        val tvNum7 = dialog.findViewById<TextView>(R.id.tvNum7)
        val tvNum8 = dialog.findViewById<TextView>(R.id.tvNum8)
        val tvNum9 = dialog.findViewById<TextView>(R.id.tvNum9)

        tvNum0.setOnClickListener { etPassCode.append(tvNum0.text.toString().trim()) }
        tvNum1.setOnClickListener { etPassCode.append(tvNum1.text.toString().trim()) }
        tvNum2.setOnClickListener { etPassCode.append(tvNum2.text.toString().trim()) }
        tvNum3.setOnClickListener { etPassCode.append(tvNum3.text.toString().trim()) }
        tvNum4.setOnClickListener { etPassCode.append(tvNum4.text.toString().trim()) }
        tvNum5.setOnClickListener { etPassCode.append(tvNum5.text.toString().trim()) }
        tvNum6.setOnClickListener { etPassCode.append(tvNum6.text.toString().trim()) }
        tvNum7.setOnClickListener { etPassCode.append(tvNum7.text.toString().trim()) }
        tvNum8.setOnClickListener { etPassCode.append(tvNum8.text.toString().trim()) }
        tvNum9.setOnClickListener { etPassCode.append(tvNum9.text.toString().trim()) }

        val ivBack = dialog.findViewById<ImageView>(R.id.ivBack)

        ivBack.setOnClickListener {
            val length = etPassCode.text.toString().length
            if (length > 0) {
                etPassCode.text.delete(length - 1, length)
            }
        }

        etPassCode.requestFocus()

        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.show()

    }

    fun dismissDialog() {
        dialog.dismiss()
    }

    interface PassCodeDialogListener {
        fun onSuccess()
        fun onFailure( error : Int )
    }

}