package com.ninebx.ui.home.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.baseSubCategories.Level2CategoryFragment
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_list_container.*
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

/***
 * Created by TechnoBlogger on 24/01/18.
 */
class FragmentListContainer : FragmentBackHelper() {

    var fragmentValue = ""

    private val PARAM_REQUEST_IN_PROCESS = "requestPermissionsInProcess"

    private val REQUEST_PERMISSION = 3
    private val PREFERENCE_PERMISSION_DENIED = "PREFERENCE_PERMISSION_DENIED"

    private val mRequestPermissionsInProcess = AtomicBoolean()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_container, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        val bundle = Bundle()
        fragmentValue = arguments!!.getString("categoryName")
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(fragmentValue)

        layoutAddList.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            bundle.putString("categoryName", fragmentValue)

            if (fragmentValue == "Shared Contacts") {
                checkPermissions(arrayOf(Manifest.permission.READ_CONTACTS))

                txtAdd.text = "Add Shared Contact"
                val categoryFragment = ContactsFragments()
                categoryFragment.arguments = bundle
                fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()

            } else if (fragmentValue == "Memory Timeline") {

                val categoryFragment = MemoryTimeLineFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()

            } else {
                val categoryFragment = Level2CategoryFragment()
                categoryFragment.arguments = bundle
                fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
            }

        }
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideQuickAdd()
        NineBxApplication.instance.activityInstance!!.showBottomView()
        NineBxApplication.instance.activityInstance!!.showBackIcon()
        return super.onBackPressed()
    }

    // Checking The Permission is Enabled Or Not

    private fun checkPermissions(permissions: Array<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissionInternal(permissions)
        }
    }

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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSION) {
            var i = 0
            val len = permissions.size
            while (i < len) {
                val permission = permissions[i]
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    if (Manifest.permission.READ_CONTACTS == permission) {
                        showRationale(permission, R.string.permission_denied_contacts)
                    }
                }
                i++
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun showRationale(permission: String, promptResId: Int) {
        if (shouldShowRequestPermissionRationale(permission) && !userDeniedPermissionAfterRationale(permission)) {

            //  Notify the user of the reduction in functionality and possibly exit (app dependent)
            AlertDialog.Builder(context!!)
                    .setTitle(R.string.permission_denied)
                    .setMessage(promptResId)
                    .setPositiveButton(R.string.permission_deny) { dialog, which ->
                        try {
                            dialog.dismiss()
                        } catch (ignore: Exception) {
                        }

                        setUserDeniedPermissionAfterRationale(permission)
                        mRequestPermissionsInProcess.set(false)
                    }
                    .setNegativeButton(R.string.permission_retry) { dialog, which ->
                        try {

                            dialog.dismiss()
                        } catch (ignore: Exception) {
                        }

                        mRequestPermissionsInProcess.set(false)
                        checkPermissions(arrayOf(permission))
                    }
                    .show()
        } else {
            mRequestPermissionsInProcess.set(false)
        }
    }


    private fun userDeniedPermissionAfterRationale(permission: String): Boolean {
        val sharedPrefs = context!!.getSharedPreferences(javaClass.simpleName, Context.MODE_PRIVATE)
        return sharedPrefs.getBoolean(PREFERENCE_PERMISSION_DENIED + permission, false)
    }

    private fun setUserDeniedPermissionAfterRationale(permission: String) {
        val editor = context!!.getSharedPreferences(javaClass.simpleName, Context.MODE_PRIVATE).edit()
        editor.putBoolean(PREFERENCE_PERMISSION_DENIED + permission, true).commit()
    }

}