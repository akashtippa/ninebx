package com.ninebx.ui.home.account

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_family_users.*

/***
 * Created by TechnoBlogger on 15/01/18.
 */

class AddFamilyUsersFragment : FragmentBackHelper() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_family_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.showBackIcon()

        layAddFamilyMembers.setOnClickListener {
            //            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.replace(R.id.frameLayout, AddFamilyMemberOrUsersFragment()).commit()
            openStaticLayoutDialog()
        }
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.hideBackIcon()
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.account))
        return super.onBackPressed()
    }

    private fun openStaticLayoutDialog() {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.fragment_add_family_member)
        dialog.show()

        val imgBack = dialog.findViewById<View>(R.id.ivBackAddOthers) as ImageView
        imgBack.setOnClickListener {
            dialog.cancel()
        }
        val txtSave = dialog.findViewById<View>(R.id.txtSave) as TextView
        val txtFirstName = dialog.findViewById<View>(R.id.txtFirstName) as TextView
        val txtLastName = dialog.findViewById<View>(R.id.txtLastName) as TextView
        val edtEmailAddress = dialog.findViewById<View>(R.id.edtEmailAddress) as TextView
        val txtRelationship = dialog.findViewById<View>(R.id.txtRelationship) as Spinner
        val txtsRole = dialog.findViewById<View>(R.id.txtsRole) as Spinner
        val layoutOtherViews = dialog.findViewById<View>(R.id.layoutOtherViews) as LinearLayout
        val layNonUser = dialog.findViewById<View>(R.id.layNonUser) as LinearLayout


        txtRelationship.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        txtsRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
            if (txtFirstName.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Please enter 'First name'", Toast.LENGTH_LONG).show()
                return@setOnClickListener;
            }


            if (!(txtRelationship != null && txtRelationship.selectedItem.toString().trim().isEmpty())) {
                Toast.makeText(context, "Please enter 'Relationship'", Toast.LENGTH_LONG).show()
                return@setOnClickListener;
            }

            if (!(txtsRole != null && txtsRole.selectedItem.toString() != null)) {
                Toast.makeText(context, "Please enter 'Role'", Toast.LENGTH_LONG).show()
                return@setOnClickListener;
            }




            if (edtEmailAddress.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Please enter 'Email address'", Toast.LENGTH_LONG).show()
                return@setOnClickListener;
            }


            // Add method to add it in a RecyclerView
            NineBxApplication.instance.activityInstance!!.onBackPressed()

        }


    }

}