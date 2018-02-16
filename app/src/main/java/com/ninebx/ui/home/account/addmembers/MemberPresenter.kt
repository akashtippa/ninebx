package com.ninebx.ui.home.account.addmembers

import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.utility.*
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.*
import io.realm.permissions.Permission
import okhttp3.ResponseBody
import java.util.*
import io.realm.ObjectServerError
import io.realm.PermissionManager
import io.realm.permissions.AccessLevel
import io.realm.permissions.PermissionRequest
import io.realm.permissions.UserCondition



/**
 * Created by Alok on 14/02/18.
 */
class MemberPresenter(private val memberView: MemberView, private val adminUser : SyncUser, private val adminId : String ) : SyncUser.Callback<SyncUser> {


    private val TAG = MemberPresenter::class.java.simpleName
    private lateinit var userName: String
    private lateinit var encryptedPassword: String
    private lateinit var encryptedPasswordByteArray: ByteArray
    private var mCurrentUser: SyncUser? = null
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    init {

    }


    fun saveToUserAccount(strEmail: String, password: String) {
        userName = strEmail
        memberView.showProgress(R.string.loading)
        encryptedPasswordByteArray = (encryptKey(password, strEmail))
        encryptedPassword = Arrays.toString(convertToUInt8IntArray(encryptedPasswordByteArray))
        val credentials = SyncCredentials.usernamePassword(strEmail, encryptedPassword, true)
        SyncUser.loginAsync(credentials, Constants.SERVER_IP, this)
    }

    override fun onSuccess(result: SyncUser?) {

        mCurrentUser = result
        //Save user data to realm
        val userMap = HashMap<String, Any>()

        //let myDict:NSDictionary = ["user_id": userKey, "admin_id": userKey, "email": hashUserName, "hash": finalHashKey, "is_admin": true, "secure_key":secureKey]
        userMap.put("user_id", result!!.identity)
        userMap.put("admin_id", adminId)
        userMap.put("email", userName)
        userMap.put("hash", encryptedPassword)
        userMap.put("is_admin", false)

        val encryptedPrivateKey = encryptAESKeyPassword(NineBxApplication.getPreferences().privateKey!!, encryptedPasswordByteArray)

        AppLogger.d(TAG, "Encrypted Key : " + encryptedPrivateKey)

        userMap.put("secure_key", encryptedPrivateKey)
        AppLogger.d(TAG, "UserMap : " + userMap)

        val decryptedKey = decryptAESKEYPassword(encryptedPrivateKey.toByteArray(), encryptedPasswordByteArray)
        AppLogger.d(TAG, "Decrypted Key : " + decryptedKey)

        NineBxApplication.getUserAPI()!!.postUserDetails(userMap)
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(getSignupResponse())

    }

    private fun getSignupResponse(): Observer<ResponseBody> {
        return object : Observer<ResponseBody> {
            //[
            // {"user_id":"f2184c44c0a564dbbae1f9a0014a48bf",
            // "admin_id":"f2184c44c0a564dbbae1f9a0014a48bf",
            // "is_admin":true,
            // "hash":"[164, 219, 42, 177, 85, 148, 90, 49, 197, 147, 198, 137, 151, 158, 162, 66, 47, 189, 36, 137, 98, 11, 82, 198, 39, 63, 179, 37, 44, 20, 49, 27]",
            // "email":"jeyachandran.m@cognitiveclouds.com",
            // "secure_key":"i4+nCkYYPpSrrMVIx9wMkNulVw+FvN7d/u5f8S2pjSM="}
            // ]
            override fun onNext(t: ResponseBody) {
                //User details saved successfully - save user object to realm
                AppLogger.d(TAG, "Successfully saved userMap : " + String(t.bytes()))
                memberView.onMemberSignup(mCurrentUser!!)
            }

            override fun onError(e: Throwable) {
                memberView.hideProgress()
            }

            override fun onComplete() {
                AppLogger.d(TAG, "GetUserAPI : onComplete")
                memberView.hideProgress()
            }

            override fun onSubscribe(d: Disposable) {
                mCompositeDisposable.add(d)
            }

        }
    }

    private fun setUserPermissions() {
        val permissionManager = adminUser.permissionManager
        // Create request
        val condition = UserCondition.userId(mCurrentUser!!.identity)
        val accessLevel = AccessLevel.WRITE
        val request = PermissionRequest(condition, "/~/Users", accessLevel)

        permissionManager.applyPermissions(request, object : PermissionManager.ApplyPermissionsCallback {
            override fun onSuccess() {
                memberView.onMemberSignup(mCurrentUser!!)
                memberView.hideProgress()
            }

            override fun onError(error: ObjectServerError) {
                error.printStackTrace()
                memberView.hideProgress()
                memberView.onError(R.string.error_permissions)
            }
        })

    }


    override fun onError(error: ObjectServerError?) {
        memberView.hideProgress()
        if (error != null) {
            error.printStackTrace()
        }
        if (error?.message != null) {
            memberView.showError(error.errorMessage!!)
        }
    }

    fun setPermissionsForMember( updateMember: Member, memberRole: String ) {

        updateMember.homeAdd = memberRole == "Co-administrator" || memberRole == "User"
        updateMember.homeEdit = memberRole == "Co-administrator"
        updateMember.homeView = memberRole == "Co-administrator" || memberRole == "User"

        updateMember.travelAdd = memberRole == "Co-administrator" || memberRole == "User"
        updateMember.travelEdit = memberRole == "Co-administrator"
        updateMember.travelView = memberRole == "Co-administrator" || memberRole == "User"

        updateMember.contactsAdd = memberRole == "Co-administrator" || memberRole == "User"
        updateMember.contactsEdit = memberRole == "Co-administrator"
        updateMember.contactsView = memberRole == "Co-administrator" || memberRole == "User"

        updateMember.educationlAdd = memberRole == "Co-administrator" || memberRole == "User"
        updateMember.educationlEdit = memberRole == "Co-administrator"
        updateMember.educationlView = memberRole == "Co-administrator" || memberRole == "User"

        updateMember.personalAdd = memberRole == "Co-administrator" || memberRole == "User"
        updateMember.personalEdit = memberRole == "Co-administrator"
        updateMember.personalView = memberRole == "Co-administrator" || memberRole == "User"

        updateMember.interestsAdd = memberRole == "Co-administrator" || memberRole == "User"
        updateMember.interestsEdit = memberRole == "Co-administrator"
        updateMember.interestsView = memberRole == "Co-administrator" || memberRole == "User"

        updateMember.wellnessAdd = memberRole == "Co-administrator" || memberRole == "User"
        updateMember.wellnessEdit = memberRole == "Co-administrator"
        updateMember.wellnessView = memberRole == "Co-administrator" || memberRole == "User"

        updateMember.memoriesAdd = memberRole == "Co-administrator" || memberRole == "User"
        updateMember.memoriesEdit = memberRole == "Co-administrator"
        updateMember.memoriesView = memberRole == "Co-administrator" || memberRole == "User"

        updateMember.shoppingAdd = memberRole == "Co-administrator" || memberRole == "User"
        updateMember.shoppingEdit = memberRole == "Co-administrator"
        updateMember.shoppingView = memberRole == "Co-administrator" || memberRole == "User"

        updateMember.addingRemovingMember = memberRole == "Co-administrator"
        updateMember.changingMasterPassword = false

    }

}