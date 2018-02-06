package com.ninebx.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.baseCategories.CategoryFragment
import com.ninebx.utility.NineBxPreferences
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home_updated.*

/***
 * Created by TechnoBlogger on 08/01/18.
 */
class HomeFragment : Fragment(), View.OnClickListener {

    val prefrences = NineBxPreferences()
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.txtHome -> {
                callBottomViewFragment((R.string.home_amp_money))
            }
            R.id.txtTravel -> {
                callBottomViewFragment((R.string.travel))
            }
            R.id.txtContacts -> {
                callBottomViewFragment((R.string.contacts))
            }
            R.id.txtEducation -> {
                callBottomViewFragment((R.string.education_work))
            }
            R.id.txtPersonal -> {
                callBottomViewFragment((R.string.personal))
            }
            R.id.txtInterests -> {
                callBottomViewFragment((R.string.interests))
            }
            R.id.txtWellness -> {
                callBottomViewFragment((R.string.wellness))
            }
            R.id.txtMemories -> {
                callBottomViewFragment((R.string.memories))
            }
            R.id.txtShopping -> {
                callBottomViewFragment((R.string.shopping))
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_updated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHome.setOnClickListener(this)
        txtTravel.setOnClickListener(this)
        txtContacts.setOnClickListener(this)
        txtEducation.setOnClickListener(this)
        txtPersonal.setOnClickListener(this)
        txtInterests.setOnClickListener(this)
        txtWellness.setOnClickListener(this)
        txtMemories.setOnClickListener(this)
        txtShopping.setOnClickListener(this)

    }

    private fun callBottomViewFragment(option: Int) {
//        NineBxApplication.instance.activityInstance!!.toolbarTitle.textSize = NineBxApplication.instance.activityInstance!!.pxFromDp(10F, context!!)
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        val bundle = Bundle()
        bundle.putInt("category", option)
        val categoryFragment = CategoryFragment()
        categoryFragment.arguments = bundle
        NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()

        when (option) {
            (R.string.home_amp_money) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.home_amp_money))
                prefrences.currentBox = "Home"
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.travel) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.travel))
                prefrences.currentBox = "Travel"

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.contacts) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.contacts))
                prefrences.currentBox = "Contacts"

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.education_work) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.education_work))
                prefrences.currentBox = "Education"

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.personal) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.personal))
                prefrences.currentBox = "Personal"

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.interests) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.interests))
                prefrences.currentBox = "Interests"

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.wellness) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.wellness))
                prefrences.currentBox = "Wellness"

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.memories) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.memories))
                prefrences.currentBox = "Memories"

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.shopping) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.shopping))
                prefrences.currentBox = "Shopping"

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
        }
    }


}