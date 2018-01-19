package com.ninebx.ui.home.account

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ImageView
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_add_family_member.*


/***
 * Created by TechnoBlogger on 18/01/18.
 */

class AddFamilyMemberOrUsersFragment : FragmentBackHelper() {

    private lateinit var selectedRelation: String
    private lateinit var selectedRole: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_family_member, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NineBxApplication.instance.activityInstance!!.hideToolbar()
        ivBackAddOthers.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        txtPermissions.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.frameLayout, PermissionFragment()).commit()
        }

        selectedRelation = txtRelationship.selectedItem.toString()
        selectedRole = txtsRole.selectedItem.toString()

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
    }


    private fun checkValidations() {
        if (txtFirstName.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'First name'", Toast.LENGTH_LONG).show()
            return;
        }


        if (!(txtRelationship != null && txtRelationship.selectedItem.toString().trim().isEmpty())) {
            Toast.makeText(context, "Please enter 'Relationship'", Toast.LENGTH_LONG).show()
            return;
        }

        if (!(txtsRole != null && txtsRole.selectedItem.toString() != null)) {
            Toast.makeText(context, "Please enter 'Role'", Toast.LENGTH_LONG).show()
            return;
        }




        if (edtEmailAddress.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "Please enter 'Email address'", Toast.LENGTH_LONG).show()
            return;
        }


        // Add method to add it in a RecyclerView
        NineBxApplication.instance.activityInstance!!.onBackPressed()


    }


    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.showToolbar()
        return super.onBackPressed()
    }

    private fun openStaticLayoutDialog(option: String) {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_privacy_policy)


        val imgBack = dialog.findViewById<View>(R.id.imgBack) as ImageView
        imgBack.setOnClickListener {
            dialog.cancel()
        }
    }


}