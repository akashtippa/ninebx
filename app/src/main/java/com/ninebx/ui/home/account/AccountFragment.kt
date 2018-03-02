package com.ninebx.ui.home.account

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.*
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.auth.AuthActivity
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.DecryptedUsers
import com.ninebx.ui.home.BaseHomeFragment
import com.ninebx.ui.home.account.addmembers.AddFamilyUsersFragment
import com.ninebx.ui.home.account.changePassword.MasterPasswordFragment
import com.ninebx.ui.home.adapter.SubscriptionPlanAdapter
import com.ninebx.ui.tutorial.view.CirclePageIndicator
import com.ninebx.utility.*
import io.realm.SyncUser
import kotlinx.android.synthetic.main.fragment_account.*
import java.io.File


/**
 * Created by Alok on 03/01/18.
 */
class AccountFragment : BaseHomeFragment(), AccountView, View.OnClickListener, AWSFileTransferHelper.FileOperationsCompletionListener {
    override fun onSuccess(outputFile: File?) {
        if (outputFile != null && imgProfile != null)
            Glide.with(context).asBitmap().load(outputFile).into(imgProfile)
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.txtProfile -> {
                navigateToMyProfile()
            }

            R.id.txtFamily -> {
                // later to be converted in Fragments, for now just to show UI
                navigateToMyProfileUsers()
            }

            R.id.txtSubscriptionPlan -> {
                openStaticLayoutDialog(getString(R.string.subscription_plan))
            }

            R.id.txtSecurityOverview -> {
                openStaticLayoutDialog(getString(R.string.security_overview))
            }

            R.id.txtTermsOfUse -> {
                openStaticLayoutDialog(getString(R.string.terms_of_use))
            }

            R.id.txtPrivacyPolicy -> {
                openStaticLayoutDialog(getString(R.string.privacy_policy))
            }

            R.id.txtFeedback -> {
                // ClickListener on Radio Button can be done.
                openStaticLayoutDialog(getString(R.string.give_us_feedback))
            }

            R.id.txtRecommend -> {
                openOperationDialog(getString(R.string.recommend_to_friends))
            }

            R.id.txtSupport -> {
                openOperationDialog(getString(R.string.support))
            }

            R.id.txtAutoLock -> {
                openOperationDialog(getString(R.string.auto_lock_device_setting))
            }
            R.id.txtMasterPassword -> {
                navigateToMasterPassword()
            }
        }
    }

    private fun navigateToMasterPassword() {
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val masterPasswordFragment = MasterPasswordFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList(Constants.CURRENT_USER, (mHomeView.getCurrentUsers()))
        masterPasswordFragment.arguments = bundle
        fragmentTransaction.replace(R.id.frameLayout, masterPasswordFragment).commit()
    }

    // Single method to open static page dialog,
    // like "Security", "Terms Of Use", "Privacy", "Feedback", "Subscription Plans"
    private fun openStaticLayoutDialog(option: String) {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        when (option) {
            getString(R.string.security_overview) -> {
                dialog.setContentView(R.layout.dialog_security_overview)
            }
            getString(R.string.terms_of_use) -> {
                dialog.setContentView(R.layout.dialog_terms_of_use)
            }
            getString(R.string.privacy_policy) -> {
                dialog.setContentView(R.layout.dialog_privacy_policy)
            }
            getString(R.string.subscription_plan) -> {
                dialog.setContentView(R.layout.dialog_subscription_plan)
                val adapter = SubscriptionPlanAdapter(context)
                val pager = dialog.findViewById<View>(R.id.viewpager) as ViewPager
                pager.adapter = adapter
                dialog.show()
                val circleIndicator = dialog.findViewById<View>(R.id.activity_help_view_page_indicator) as CirclePageIndicator
                circleIndicator.setViewPager(pager)
            }
            getString(R.string.give_us_feedback) -> {
                dialog.setContentView(R.layout.dialog_feedback)
            }
        }

        val window = dialog.window
        val wlp = window.attributes

        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window.attributes = wlp
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.show()

        val imgBack = dialog.findViewById<View>(R.id.imgBack) as ImageView
        imgBack.setOnClickListener {
            dialog.cancel()
        }
    }

    // Single method to open operational dialog,
    // like "Share", "Recommend", "Settings Screen"
    private fun openOperationDialog(option: String) {
        when (option) {
            getString(R.string.recommend_to_friends) -> {
                val shareBody = getString(R.string.app_name)
                val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name))
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(sharingIntent, "Share via"))
            }
            getString(R.string.support) -> {
                val email = Intent(Intent.ACTION_SEND)
                email.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.support_email)))
                email.putExtra(Intent.EXTRA_SUBJECT, "")
                email.putExtra(Intent.EXTRA_TEXT, "")
                email.type = "message/rfc822"
                startActivity(Intent.createChooser(email, "Choose an Email client :"))
            }
            getString(R.string.auto_lock_device_setting) -> {
                startActivityForResult(Intent(android.provider.Settings.ACTION_SETTINGS), 0)
            }
        }
    }

    private fun navigateToMyProfile() {
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.my_profile))
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val myProfileFragment = MyProfileFragment()
        val bundle = Bundle()
        bundle.putString("fromClass", "Account")
        bundle.putParcelableArrayList(Constants.CURRENT_USER, (mHomeView.getCurrentUsers()))
        myProfileFragment.arguments = bundle
        fragmentTransaction.replace(R.id.frameLayout, myProfileFragment).commit()
    }

    private fun navigateToMyProfileUsers() {
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.family_users))
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val addFamilyUsersFragment = AddFamilyUsersFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList(Constants.CURRENT_USER, (mHomeView.getCurrentUsers()))
        addFamilyUsersFragment.arguments = bundle
        fragmentTransaction.replace(R.id.frameLayout, addFamilyUsersFragment).commit()
    }

    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {

    }

    override fun onError(error: Int) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtUserName.text = mHomeView.getCurrentUsers()[0]!!.fullName
        txtUserEmail.text = mHomeView.getCurrentUsers()[0]!!.emailAddress

        txtProfile.setOnClickListener(this)
        txtSecurityOverview.setOnClickListener(this)
        txtTermsOfUse.setOnClickListener(this)
        txtPrivacyPolicy.setOnClickListener(this)
        txtRecommend.setOnClickListener(this)
        txtSupport.setOnClickListener(this)
        txtSubscriptionPlan.setOnClickListener(this)
        txtFeedback.setOnClickListener(this)
        txtAutoLock.setOnClickListener(this)
        txtFamily.setOnClickListener(this)
        txtMasterPassword.setOnClickListener(this)
        layoutLock.setOnClickListener {
            //            NineBxApplication.instance.activityInstance!!.showPasswordDialog()
        }
        txtPersonalPassCode.setOnClickListener {
            //AppLogger.d("Auth", "From AccountFragment")
            startActivity( Intent( context, AuthActivity::class.java).putExtra(Constants.RESET_PASSCODE, true))
        }
        layoutLogOut.setOnClickListener {
            NineBxApplication.getPreferences().clearPreferences()
            SyncUser.currentUser().logout()
            closeAllConnections()
            //AppLogger.d("Auth", "From AccountFragment")
            startActivity(Intent(context, AuthActivity::class.java))
            activity!!.finish()
        }
        switchTouchId.isChecked = NineBxApplication.getPreferences().isFingerPrintEnabled
        switchTouchId.isEnabled = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        switchTouchId.setOnCheckedChangeListener { _, isChecked ->
            if( !fromFingerPrint ) {
                startActivityForResult(Intent( context, AuthActivity::class.java).putExtra(Constants.RESET_FINGER_PRINT, true).putExtra(Constants.FINGER_PRINT, isChecked), Constants.FINGER_PRINT_COMPLETE)
            }
            else fromFingerPrint = false
        }


        /*val awsSecureFileTransfer = AWSSecureFileTransfer(context!!)
        awsSecureFileTransfer.setFileTransferListener(this)
        val profilePhoto = mHomeView.getCurrentUsers()[0]!!.profilePhoto
        if (profilePhoto.isNotEmpty()) {
            awsSecureFileTransfer.downloadSecureFile("images/" + SyncUser.currentUser().identity + "/" + profilePhoto)
        }*/
    }

    private var fromFingerPrint = false
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if( requestCode == Constants.FINGER_PRINT_COMPLETE && resultCode == Activity.RESULT_OK ) {
            fromFingerPrint = true
            switchTouchId.isChecked = NineBxApplication.getPreferences().isFingerPrintEnabled
            switchTouchId.isEnabled = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            fromFingerPrint = false
        }
        else
            super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onResume() {
        super.onResume()

    }
}