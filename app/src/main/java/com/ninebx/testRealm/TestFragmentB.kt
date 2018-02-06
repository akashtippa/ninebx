package com.ninebx.testRealm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.testRealm.model.TestUserContact
import com.ninebx.testRealm.model.TestUserDetails
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.insertOrUpdate
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_test_b.*

/***
 * Created by TechnoBlogger on 28/01/18.
 */
class TestFragmentB : FragmentBackHelper() {

    private lateinit var mCurrentUserContacts: TestUserContact

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        btnSubmit.setOnClickListener {
            prepareRealmConnections(context, true, "TestingB", object : Realm.Callback() {
                override fun onSuccess(realm: Realm?) {
                    mCurrentUserContacts.insertOrUpdate(realm!!)
                    mCurrentUserContacts.strFirstName = "TestingTesting"
                    NineBxApplication.getPreferences().setTestFragmentB(mCurrentUserContacts)
                }

            })
        }


    }

}