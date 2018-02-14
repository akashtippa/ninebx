package com.ninebx.ui.auth

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.utility.KeyboardUtil
import kotlinx.android.synthetic.main.fragment_pass_code.*

/**
 * Created by Alok on 04/01/18.
 */
class PassCodeFragment : BaseAuthFragment() {

    override fun validate(): Boolean {
        if( isCreatePassCode ) {
            return etPassCode.text.toString().trim().length == 6
        }
        else {
            val currentPassCode = etPassCode.text.toString().trim()
            passCode = NineBxApplication.getPreferences().passCode!!
            if( currentPassCode != passCode ) context!!.showToast(R.string.error_passcodes_dont_match)
            return !(currentPassCode.length != 6 || currentPassCode != passCode)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pass_code, container, false)
    }

    private var isCreatePassCode: Boolean = true
    private var passCode : String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        tvTitle.text = if( isCreatePassCode ) getString(R.string.create_your_pass_code) else getString(R.string.confirm_your_passcode)
        setupToolbar()
        setHasOptionsMenu(!isCreatePassCode)



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

                        if( isCreatePassCode ) {
                            //KeyboardUtil.hideSoftKeyboard(activity!!)
                            mAuthView.navigateToCreatePassCode(false, etPassCode.text.toString().trim())
                        }
                        else {
                            //KeyboardUtil.hideSoftKeyboard(activity!!)
                            if( validate() ) {
                                KeyboardUtil.hideKeyboard(etPassCode)
                                mAuthView.navigateToFingerPrint()
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

    private lateinit var appCompatActivity: AppCompatActivity

    private fun setupToolbar() {
        appCompatActivity = activity as AppCompatActivity
        appCompatActivity.setSupportActionBar(toolbar)
        /*val assets = Typeface.createFromAsset(context.assets,"fonts/Futura-Medium.ttf")
        titleTextView.typeface = assets*/
        titleTextView.text = getString(R.string.create_personal_passcode)
        appCompatActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(!isCreatePassCode)
        appCompatActivity.supportActionBar!!.setHomeButtonEnabled(!isCreatePassCode)
    }

    fun setCreatePassCode(createPassCode: Boolean) {
        this.isCreatePassCode = createPassCode
        if( !isCreatePassCode ) {
            passCode = NineBxApplication.getPreferences().passCode!!
            etPassCode.setText("")

            ivOtp1.isSelected = false
            ivOtp2.isSelected = false
            ivOtp3.isSelected = false
            ivOtp4.isSelected = false
            ivOtp5.isSelected = false
            ivOtp6.isSelected = false

            tvTitle.text = if( isCreatePassCode ) getString(R.string.create_your_pass_code) else getString(R.string.confirm_your_passcode)
            setupToolbar()
            setHasOptionsMenu(!isCreatePassCode)

        }
    }

}