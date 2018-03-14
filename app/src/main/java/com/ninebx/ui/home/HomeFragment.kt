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
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        val categoryFragment = CategoryFragment()
        val bundle = Bundle()
        bundle.putInt("category", option)
        categoryFragment.arguments = bundle

        NineBxApplication.instance.activityInstance!!.hideQuickAdd()

        when (option) {
            (R.string.home_amp_money) -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.home_amp_money))
                prefrences.currentBox = getString(R.string.home_amp_money)
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.travel) -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.travel))
                prefrences.currentBox = getString(R.string.travel)
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.contacts) -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.contacts))
                prefrences.currentBox = getString(R.string.contacts)

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.education_work) -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.education_work))
                prefrences.currentBox = getString(R.string.education_work)

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.personal) -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.personal))
                prefrences.currentBox = getString(R.string.personal)

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.interests) -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.interests))
                prefrences.currentBox = getString(R.string.interests)

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.wellness) -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.wellness))
                prefrences.currentBox = getString(R.string.wellness)

                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.memories) -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.memories))
                prefrences.currentBox = getString(R.string.memories)
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
            (R.string.shopping) -> {
                //NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.shopping))
                prefrences.currentBox = getString(R.string.shopping)
                fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
            }
        }
    }

    companion object {
        fun getHomeInstance() : HomeFragment {
            if( instance == null ) {
                instance = HomeFragment()
            }
            return instance!!
        }
        var instance : HomeFragment? = null

    }
}