package com.ninebx.testRealm

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.AsyncTask.execute
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.testRealm.model.TestUserDetails
import com.ninebx.ui.home.HomeFragment
import com.ninebx.ui.base.realm.Users
import com.ninebx.utility.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.*
import java.util.UUID.randomUUID
import kotlinx.android.synthetic.main.fragment_test_a.*


/***
 * Created by TechnoBlogger on 28/01/18.
 */
class TestFragmentA : FragmentBackHelper() {

    private lateinit var mCurrentUserDetails: TestUserDetails


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()


        btnSubmit.setOnClickListener {
            prepareRealmConnections(context, true, "Testing", object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    mCurrentUserDetails.insertOrUpdate(realm!!)
                    mCurrentUserDetails.strFirstName = "TestingTesting"
                    NineBxApplication.getPreferences().setTestFragmentA(mCurrentUserDetails)
                }

            })

        }
    }

}