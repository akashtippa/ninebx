package com.ninebx.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ninebx.R
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.home.account.AddFamilyMemberOrUsersFragment
import com.ninebx.ui.home.account.interfaces.IMemberAdded
import com.ninebx.utility.Constants

/**
 * Created by Alok on 14/02/18.
 */
class ContainerActivity : AppCompatActivity(), IMemberAdded {
    override fun memberAdded(member: Member?) {
        val intent = Intent()
        intent.putExtra(Constants.MEMBER, member)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onMemberEdit(member: Member?) {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        loadAddFamilyMembersFragment()
    }

    private fun loadAddFamilyMembersFragment() {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val addFamilyMemberOrUsersFragment = AddFamilyMemberOrUsersFragment()
        addFamilyMemberOrUsersFragment.setIMemberAdded(this)
        addFamilyMemberOrUsersFragment.arguments = intent.extras
        fragmentTransaction.replace(R.id.fragmentContainer, addFamilyMemberOrUsersFragment).commit()

    }
}