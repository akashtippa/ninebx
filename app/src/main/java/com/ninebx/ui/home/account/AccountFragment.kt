package com.ninebx.ui.home.account

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.ninebx.R
import kotlinx.android.synthetic.main.fragment_account.*
import android.content.Intent
import android.support.v4.view.ViewPager
import android.widget.ImageView
import com.ninebx.ui.home.adapter.SubscriptionPlanAdapter
import com.ninebx.ui.tutorial.view.CirclePageIndicator


/**
 * Created by Alok on 03/01/18.
 */
class AccountFragment : Fragment(), AccountView, View.OnClickListener {


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.txtProfile -> {
                navigateToMyProfile()
            }

            R.id.txtSubscriptionPlan -> {
                openSubscriptionDialog()
            }

            R.id.txtSecurityOverview -> {
                openSecurityDialog()
            }

            R.id.txtTermsOfUse -> {
                openTermsOfUseDialog()
            }

            R.id.txtPrivacyPolicy -> {
                openPrivacyPolicyDialog()
            }

            R.id.txtRecommend -> {
                recommendToFriend()
            }

            R.id.txtSupport -> {
                support()
            }
        }
    }

    private fun openSubscriptionDialog() {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_subscription_plan)
        dialog.setCanceledOnTouchOutside(false)

        val window = dialog.window
        val wlp = window.attributes

        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window.attributes = wlp
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        val adapter = SubscriptionPlanAdapter(context)
        val pager = dialog.findViewById<View>(R.id.viewpager) as ViewPager
        pager.adapter = adapter
        dialog.show()

        val circleIndicator = dialog.findViewById<View>(R.id.activity_help_view_page_indicator) as CirclePageIndicator
        circleIndicator.setViewPager(pager)

        val imgBack = dialog.findViewById<View>(R.id.imgBack) as ImageView
        imgBack.setOnClickListener {
            dialog.cancel()
        }
    }


    private fun openSecurityDialog() {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_security_overview)

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

    private fun openTermsOfUseDialog() {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_terms_of_use)
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

    private fun openPrivacyPolicyDialog() {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_privacy_policy)
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

    private fun recommendToFriend() {
        val shareBody = getString(R.string.app_name)
        val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name))

        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }

    private fun support() {
        val email = Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.support_email)))
        email.putExtra(Intent.EXTRA_SUBJECT, "")
        email.putExtra(Intent.EXTRA_TEXT, "")
        email.type = "message/rfc822"
        startActivity(Intent.createChooser(email, "Choose an Email client :"))
    }

    private fun navigateToMyProfile() {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.fragment_my_profile)
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
        txtSecurityOverview.setOnClickListener(this)
        txtTermsOfUse.setOnClickListener(this)
        txtPrivacyPolicy.setOnClickListener(this)
        txtRecommend.setOnClickListener(this)
        txtSupport.setOnClickListener(this)
        txtSubscriptionPlan.setOnClickListener(this)
    }

}