package com.ninebx.ui.home.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.home.account.interfaces.IMemberAdded
import com.ninebx.utility.*
import kotlinx.android.synthetic.main.fragment_add_family_member.*


/***
 * Created by TechnoBlogger on 18/01/18.
 */

class AddFamilyMemberOrUsersFragment : FragmentBackHelper() {

    private lateinit var selectedRelation: String
    private lateinit var selectedRole: String
    private lateinit var iMemberAdded : IMemberAdded
    private lateinit var member : Member

    private var strFirstName = ""
    private var strLastName = ""
    private var strAccountHolder = ""
    private var strRole = ""
    private var strEmail = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_family_member, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivBackAddOthers.setOnClickListener {
            //NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        txtPermissions.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.frameLayout, PermissionFragment()).commit()
        }

        member = arguments!!.getParcelable(Constants.MEMBER)

        selectedRelation = txtRelationship.selectedItem.toString()
        selectedRole = txtsRole.selectedItem.toString()

        txtRelationship.prompt = "Relationship"
        txtsRole.prompt = "Role"

        txtRelationship.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                // your code here
                val newValue = txtRelationship.getItemAtPosition(position) as String
                if (newValue == "Pet") {
                    layoutOtherViews.hide()
                } else {
                    layoutOtherViews.show()
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                Toast.makeText(context, "Please enter 'Relationship'", Toast.LENGTH_LONG).show()
            }

        };

        txtsRole.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                // your code here
                val newValue = txtsRole.getItemAtPosition(position) as String
                if (newValue == "Non-user") {
                    layNonUser.hide()
                } else {
                    layNonUser.show()
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
                Toast.makeText(context, "Please enter 'Role'", Toast.LENGTH_LONG).show()

            }

        };


        txtSave.setOnClickListener {
            checkValidations()
        }

        populateView( member )
    }

    private fun populateView(member: Member?) {

        if( member!!.firstName.isNotEmpty() )
            txtFirstName.setText( member.firstName.decryptString() )

        if( member.lastName.isNotEmpty() )
            txtLastName.setText( member.lastName.decryptString() )

        if( member.relationship.isNotEmpty() )
            txtRelationship.prompt = member.relationship

        if( member.role.isNotEmpty() )
            txtsRole.prompt = member.role

        if( member.email.isNotEmpty() )
            edtEmailAddress.setText( member.email.decryptString() )
    }


    private fun checkValidations() {

        strFirstName = txtFirstName.text.toString()
        strLastName = txtLastName.text.toString()
        strAccountHolder = txtRelationship.selectedItem.toString()
        strRole = txtsRole.selectedItem.toString()
        strEmail = edtEmailAddress.text.toString()


        if (strFirstName.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'First name'", Toast.LENGTH_LONG).show()
            txtFirstName.requestFocus()
            return
        }

        if (strLastName.trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Last name'", Toast.LENGTH_LONG).show()
            txtLastName.requestFocus()
            return
        }

        if ((txtRelationship.selectedItem.toString().trim() == "Relationship" || txtRelationship.selectedItem.toString().trim().isEmpty())) {
            Toast.makeText(context, "Please enter 'Relationship'", Toast.LENGTH_LONG).show()
            return
        } else if ((txtRelationship.selectedItem.toString().trim() == "Pet")) {
            layoutOtherViews.hide()
        } else {
            if ((txtsRole.selectedItem.toString().trim() == "Role" || txtsRole.selectedItem.toString().trim().isEmpty())) {
                Toast.makeText(context, "Please enter 'Role'", Toast.LENGTH_LONG).show()
                return
            } else if (txtsRole.selectedItem.toString().trim() == "Non-user") {
                layNonUser.hide()
            } else
                if (edtEmailAddress.text.toString().trim().isEmpty()) {
                    Toast.makeText(context, "Please enter 'Email address'", Toast.LENGTH_LONG).show()
                    edtEmailAddress.requestFocus()
                    return
                }
        }

        var member = Member()
        member.userId = this@AddFamilyMemberOrUsersFragment.member.userId
        member.firstName = strFirstName.encryptString()
        member.lastName = strLastName.encryptString()
        member.relationship = strAccountHolder.encryptString()
        member.email = strEmail.encryptString()
        member.role = strRole.encryptString()
        member.relationship = txtRelationship.selectedItem.toString().encryptString()

        //member.insertOrUpdate( realm!! )
        // Set the data.
        iMemberAdded.memberAdded( member )

        // Add method to add it in a RecyclerView
        //NineBxApplication.instance.activityInstance!!.onBackPressed()



    }


    override fun onBackPressed(): Boolean {
        //NineBxApplication.instance.activityInstance!!.showToolbar()
        return super.onBackPressed()
    }

    fun setIMemberAdded(iMemberAdded: IMemberAdded) {
        this.iMemberAdded = iMemberAdded
    }

}