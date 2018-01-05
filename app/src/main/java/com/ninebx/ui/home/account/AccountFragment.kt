package com.ninebx.ui.home.account

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.ui.auth.AuthView
import com.ninebx.ui.auth.SignUpFragment
import com.ninebx.ui.base.BaseView
import kotlinx.android.synthetic.main.fragment_account.*

/**
 * Created by Alok on 03/01/18.
 */
class AccountFragment : Fragment(), AccountView, View.OnClickListener {


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.txtProfile -> {
                navigateToMyProfile()
            }
        }
    }


    fun navigateToMyProfile() {

    }


    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {

    }

    override fun onError(error: Int) {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtProfile.setOnClickListener(this)

    }

}