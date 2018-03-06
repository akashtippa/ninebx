package com.ninebx.ui.home.account.confirmPassword

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.account.addmembers.MemberView
import com.ninebx.utility.AppLogger
import com.ninebx.utility.encryptKey
import kotlinx.android.synthetic.main.fragment_confirm_password.*
import java.util.*

/**
 * Created by Alok on 14/02/18.
 */
class ConfirmPasswordFragment : Fragment() {

    private lateinit var memberView: MemberView
    private var password: String = ""

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MemberView) {
            memberView = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_confirm_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSubmit.setOnClickListener {
            if (validate()) {
                memberView.onConfirmPassword(password)
            } else {
                memberView.showError(getString(R.string.check_password))
            }
        }
        if (NineBxApplication.autoTestMode) {
            etConfirmPassword.setText("Password14.")
        }
    }

    private fun validate(): Boolean {
        password = etConfirmPassword.text.toString().trim()
        val preferences = NineBxApplication.getPreferences()
        AppLogger.d("Email", "ConfirmPassword validate " + NineBxApplication.getPreferences().userEmail!!)
        return !password.isEmpty() && preferences.userPassword!!.equals(Arrays.toString(encryptKey(password, preferences.userEmail!!)))
    }
}