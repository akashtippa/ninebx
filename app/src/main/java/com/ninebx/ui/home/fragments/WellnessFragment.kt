package com.ninebx.ui.home.fragments

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.baseSubCategories.Level3CategoryFragment
import com.ninebx.utility.AppLogger
import com.ninebx.utility.Constants
import com.ninebx.utility.FragmentBackHelper
import com.onegravity.contactpicker.ContactElement
import com.onegravity.contactpicker.contact.Contact
import com.onegravity.contactpicker.contact.ContactDescription
import com.onegravity.contactpicker.contact.ContactSortOrder
import com.onegravity.contactpicker.core.ContactPickerActivity
import com.onegravity.contactpicker.group.Group
import com.onegravity.contactpicker.picture.ContactPictureType
import kotlinx.android.synthetic.main.fragment_wellness.*
import java.util.concurrent.atomic.AtomicBoolean

/***
 * Created by TechnoBlogger on 28/01/18.
 */
class WellnessFragment : FragmentBackHelper(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.layoutPersonalHealthRecord -> {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wellness, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivHome.setOnClickListener {
            val homeIntent = Intent(context, HomeActivity::class.java)
            startActivity(homeIntent)
            activity!!.finishAffinity()
        }

        ivBack.setOnClickListener {  activity!!.onBackPressed()  }
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        val bundle = arguments
        layoutPersonalHealthRecord.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            val bundle = Bundle()
            bundle.putString("categoryName", "Identification")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutMedicalHistory.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Medical history")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutHealthCare.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Healthcare providers")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutMedications.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Medications")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutMedicalConditions.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Medical conditions/Allergies")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutEye.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Eyeglass prescriptions")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutVitalNumber.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Vital numbers")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutCheckUps.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Checkups and visits")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutEmergency.setOnClickListener{
            checkPermissions(arrayOf(Manifest.permission.READ_CONTACTS))
            callForContact()

          /*  val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Emergency Contacts")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()*/
        }
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        return super.onBackPressed()
    }

    private fun checkPermissions(permissions: Array<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissionInternal(permissions)
        }
    }

    private val mRequestPermissionsInProcess = AtomicBoolean()
    private val REQUEST_PERMISSION = 3
    private val PREFERENCE_PERMISSION_DENIED = "PREFERENCE_PERMISSION_DENIED"
    private val REQUEST_CONTACT = 0
    private var mContacts: ArrayList<Contact>? = ArrayList()
    private var mGroups: List<Group>? = null

    @TargetApi(Build.VERSION_CODES.M)
    private fun checkPermissionInternal(permissions: Array<String>): Boolean {
        val requestPerms = ArrayList<String>()
        for (permission in permissions) {
            if (context!!.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED && !userDeniedPermissionAfterRationale(permission)) {
                requestPerms.add(permission)
            }
        }
        if (requestPerms.size > 0 && !mRequestPermissionsInProcess.getAndSet(true)) {
            //  We do not have this essential permission, ask for it
            requestPermissions(requestPerms.toTypedArray(), REQUEST_PERMISSION)
            return true
        }
        return false
    }
    private fun userDeniedPermissionAfterRationale(permission: String): Boolean {
        val sharedPrefs = context!!.getSharedPreferences(javaClass.simpleName, Context.MODE_PRIVATE)
        return sharedPrefs.getBoolean(PREFERENCE_PERMISSION_DENIED + permission, false)
    }

    private fun callForContact() {
        callContactPicker()
        populateContactList(mGroups, mContacts)
    }
    private fun populateContactList(groups: List<Group>?, contacts: List<Contact>?) {
        // we got a result from the contact picker --> show the picked contacts
        val result = SpannableStringBuilder()

        try {
            if (groups != null && !groups.isEmpty()) {
                result.append("GROUPS\n")
                for (group in groups) {
                    populateContact(result, group, "")
                    for (contact in group.contacts) {
                        populateContact(result, contact, "    ")
                    }
                }
            }
            if (contacts != null && !contacts.isEmpty()) {
                result.append("\n")
                for (contact in contacts) {
                    populateContact(result, contact, "")
                }
            }
        } catch (e: Exception) {
            result.append(e.message)
        }
    }
    private fun callContactPicker() {
        val intent = Intent(context, ContactPickerActivity::class.java)

                .putExtra(ContactPickerActivity.EXTRA_CONTACT_BADGE_TYPE,
                        ContactPictureType.ROUND.name)
                .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION,
                        ContactDescription.ADDRESS.name)
                .putExtra(ContactPickerActivity.EXTRA_SHOW_CHECK_ALL, true)
                .putExtra(ContactPickerActivity.EXTRA_SELECT_CONTACTS_LIMIT, 0)
                .putExtra(ContactPickerActivity.EXTRA_ONLY_CONTACTS_WITH_PHONE, false)
                .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION_TYPE,
                        ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                .putExtra(ContactPickerActivity.EXTRA_CONTACT_SORT_ORDER,
                        ContactSortOrder.AUTOMATIC.name)
        startActivityForResult(intent, REQUEST_CONTACT)
    }
    private fun populateContact(result: SpannableStringBuilder, element: ContactElement, prefix: String) {
        //int start = result.length();
        val displayName = element.displayName
        result.append(prefix)
        result.append(displayName + "\n")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK && data != null &&
                (data.hasExtra(ContactPickerActivity.RESULT_GROUP_DATA) || data.hasExtra(ContactPickerActivity.RESULT_CONTACT_DATA))) {

            // we got a result from the contact picker --> show the picked contacts
            mGroups = data.getSerializableExtra(ContactPickerActivity.RESULT_GROUP_DATA) as List<Group>
            mContacts = data.getSerializableExtra(ContactPickerActivity.RESULT_CONTACT_DATA) as ArrayList<Contact>
//            setContactsList()
            AppLogger.d("WellnessFragment ", "emergencyContactsGroups " + mGroups)
            AppLogger.d("WellnessFragment ", "emergencyContactsContacts " + mContacts)
        }
    }
}