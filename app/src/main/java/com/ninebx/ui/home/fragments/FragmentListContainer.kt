package com.ninebx.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.baseSubCategories.Level2CategoryFragment
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_list_container.*

/***
 * Created by TechnoBlogger on 24/01/18.
 */
class FragmentListContainer : FragmentBackHelper() {

    var fragmentValue = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_container, container, false)
    }

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

            if (fragmentValue == "Shared Contact") {

                val categoryFragment = ContactsViewFragment()
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
}