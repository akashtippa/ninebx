package com.ninebx.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import kotlinx.android.synthetic.main.activity_sign_in.*

/**
 * Created by Alok on 03/01/18.
 */
class SignInFragment : BaseAuthFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.activity_sign_in, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin.setOnClickListener{
            if( validate() ) {
                mAuthView.getAuthPresenter().signIn()
            }

        }
        btnSignUp.setOnClickListener{
            mAuthView.navigateToSignUp()
        }
        txtTermsOfUse.setOnClickListener{}
        txtPrivacyPolicy.setOnClickListener{}
    }

    override fun validate(): Boolean {
        return true
    }
}