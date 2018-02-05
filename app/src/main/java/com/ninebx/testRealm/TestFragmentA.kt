package com.ninebx.testRealm

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
import io.realm.Realm
import com.ninebx.ui.base.realm.Users
import com.ninebx.utility.*
import java.util.UUID.randomUUID
import io.realm.SyncManager
import io.realm.SyncConfiguration
import io.realm.SyncUser
import kotlinx.android.synthetic.main.fragment_test_a.*


/***
 * Created by TechnoBlogger on 28/01/18.
 */
class TestFragmentA : FragmentBackHelper() {

    private lateinit var mCurrentUserDetails : TestUserDetails


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        mCurrentUserDetails = arguments!!.getParcelable(Constants.TEST_FRAGMENT_A)

        btnSubmit.setOnClickListener {

        }



    }

    private fun sendData() {
        val realm = Realm.getDefaultInstance()

        val user = SyncUser.currentUser()
        val config = SyncConfiguration.Builder(user, Constants.SERVER_URL + "TestFragmentA" )
                .waitForInitialRemoteData()
                .build()

        realm.executeTransactionAsync({ bgRealm ->
            val userDetails = bgRealm.createObject(TestUserDetails::class.java)
            userDetails.strFirstName = "TestFirstName"
            val privateKey = randomString(16)

            userDetails.strLastName = encryptAESKey("TestLastName", privateKey)

        }, {
            Toast.makeText(context, "Success: ", Toast.LENGTH_LONG).show()
        }, {
            Toast.makeText(context, "Failure: ", Toast.LENGTH_LONG).show()
        })

//        var mSyncUser: SyncUser? = null
//
//        prepareRealmConnections( context, true, "/TestFragmentA", object : Realm.Callback() {
//            override fun onSuccess(realm: Realm?) {
//                mCurrentUserDetails.insertOrUpdate( realm!! )
//                NineBxApplication.getPreferences().setTestFragmentA( mCurrentUserDetails )
////                mAuthView.navigateToOTP()
//            }
//
//        })
    }

}