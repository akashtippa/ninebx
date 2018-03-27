package com.ninebx.ui.auth

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.utility.Constants.PASSCODE_CONFIRM
import com.ninebx.utility.Constants.PASSCODE_CREATE
import com.ninebx.utility.Constants.PASSCODE_RESET
import com.ninebx.utility.KeyboardUtil
import kotlinx.android.synthetic.main.fragment_pass_code.*

/**
 * Created by Alok on 04/01/18.
 */
class PassCodeFragment : BaseAuthFragment() {

    override fun validate(): Boolean {
        return when( isCreatePassCode ) {
            PASSCODE_CREATE -> {
                etPassCode.text.toString().trim().length == 6
            }
            else -> {

                val currentPassCode = etPassCode.text.toString().trim()
                if( currentPassCode.length != 6 ) {
                    context!!.showToast(R.string.error_passcode_6)
                    return false
                }
                passCode = NineBxApplication.getPreferences().passCode!!
                if( currentPassCode != passCode ) {
                    context!!.showToast(R.string.error_passcodes_dont_match)
                    return false
                }
                return true
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pass_code, container, false)
    }

    private var isCreatePassCode: Int = -2
    private var passCode : String = ""

    private var fromPassCodeReset: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        tvTitle.text = when( isCreatePassCode ) {
            PASSCODE_CREATE -> getString(R.string.create_your_pass_code)
            PASSCODE_RESET -> getString(R.string.enter_your_current_passcode)
            else -> getString(R.string.confirm_your_passcode)
        }

        if( isCreatePassCode == PASSCODE_RESET ) {
            titleTextView.text = getString(R.string.personal_passcode)
        }
        else titleTextView.text = getString(R.string.create_personal_passcode)
        setHasOptionsMenu(isCreatePassCode == PASSCODE_CONFIRM)

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

                        if( validate( ) ) {
                            when( isCreatePassCode ) {
                                PASSCODE_CONFIRM -> {
                                    KeyboardUtil.hideKeyboard(etPassCode)
                                    if( fromPassCodeReset ) {
                                        context!!.showToast(R.string.passcode_changed)
                                        activity!!.finish()
                                    }
                                    else {
                                        mAuthView.navigateToFingerPrint(false)
                                    }

                                }
                                PASSCODE_RESET -> {
                                    fromPassCodeReset = true
                                    mAuthView.navigateToCreatePassCode(PASSCODE_CREATE, "")
                                }
                                PASSCODE_CREATE -> {
                                    //KeyboardUtil.hideSoftKeyboard(activity!!)
                                    mAuthView.navigateToCreatePassCode(PASSCODE_CONFIRM, etPassCode.text.toString().trim())
                                }
                            }
                        }


                    }
                }
            }

        })

        etPassCode.requestFocus()
        Handler().postDelayed( { KeyboardUtil.forceShowKeyboard(etPassCode) }, 700)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when( item!!.itemId ) {
            android.R.id.home -> {
                activity!!.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setCreatePassCode(createPassCode: Int) {

        this.isCreatePassCode = createPassCode
        if( isCreatePassCode != PASSCODE_RESET && etPassCode != null ) {

            passCode = NineBxApplication.getPreferences().passCode!!
            Log.d("Passcode",passCode)
            etPassCode.setText("")

            ivOtp1.isSelected = false
            ivOtp2.isSelected = false
            ivOtp3.isSelected = false
            ivOtp4.isSelected = false
            ivOtp5.isSelected = false
            ivOtp6.isSelected = false

            tvTitle.text = if( isCreatePassCode == PASSCODE_CREATE ) getString(R.string.create_your_pass_code) else getString(R.string.confirm_your_passcode)
            setHasOptionsMenu(!(isCreatePassCode == PASSCODE_CREATE))

        }
    }

}