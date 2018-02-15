package com.ninebx.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.showProgressDialog
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.home.account.addmembers.AddFamilyMemberOrUsersFragment
import com.ninebx.ui.home.account.addmembers.MemberView
import com.ninebx.ui.home.account.confirmPassword.ConfirmPasswordFragment
import com.ninebx.ui.home.account.memoryView.MemoryView
import com.ninebx.ui.home.fragments.MemoryTimeLineFragment
import com.ninebx.utility.Constants
import com.ninebx.utility.Constants.ALL_COMPLETE
import io.realm.SyncUser

/**
 * Created by Alok on 14/02/18.
 */
class ContainerActivity : AppCompatActivity(), MemberView, MemoryView {

    override fun onMemoryTimeLine(memoryTimeLine: MemoryTimeline) {
        val intent = Intent()
        intent.putExtra(Constants.MEMORY_TIMELINE, memoryTimeLine)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun showError(error: String) {
        this.showToast(error)
    }

    override fun onNewMember(member: Member) {
        val intent = Intent()
        intent.putExtra(Constants.MEMBER, member)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onMemberSignup(user: SyncUser) {
        if (addFamilyMemberOrUsersFragment != null)
            addFamilyMemberOrUsersFragment!!.onAccountCreated(user)
    }

    override fun showProgress(message: Int) {
        this.showProgressDialog(getString(message))
    }

    override fun hideProgress() {
        this.hideProgressDialog()
    }

    override fun onError(error: Int) {
        this.showToast(error)
    }

    private var addFamilyMemberOrUsersFragment: AddFamilyMemberOrUsersFragment? = null

    override fun onConfirmPassword(password: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        addFamilyMemberOrUsersFragment = AddFamilyMemberOrUsersFragment()
        val bundle = intent.extras
        bundle.putString(Constants.USER_PASSWORD, password)
        addFamilyMemberOrUsersFragment!!.arguments = bundle
        fragmentTransaction.replace(R.id.fragmentContainer, addFamilyMemberOrUsersFragment).commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        if (NineBxApplication.getPreferences().currentStep < ALL_COMPLETE)
            NineBxApplication.getPreferences().currentStep = ALL_COMPLETE
        var fromWhichClass = ""

        val intent = intent
        fromWhichClass = intent.extras!!.getString(Constants.FROM_CLASS)

        when (fromWhichClass) {
            "MemoryView" -> {
                loadMemoryTimeLine()
            }
            "AddMember" -> {
                loadMasterPasswordFragment()
            }
        }


    }

    private fun loadMasterPasswordFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val confirmPasswordFragment = ConfirmPasswordFragment()
        fragmentTransaction.replace(R.id.fragmentContainer, confirmPasswordFragment).commit()
    }

    private fun loadMemoryTimeLine() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val memoryTimeLine = MemoryTimeLineFragment()
        memoryTimeLine.arguments = intent.extras
        fragmentTransaction.replace(R.id.fragmentContainer, memoryTimeLine).commit()
    }


}