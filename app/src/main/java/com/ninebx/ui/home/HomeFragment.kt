package com.ninebx.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.baseCategories.Level1Fragment
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
                callLevel1Fragment((R.string.home_amp_money))
            }
            R.id.txtTravel -> {
                callLevel1Fragment((R.string.travel))
            }
            R.id.txtContacts -> {
                callLevel1Fragment((R.string.contacts))
            }
            R.id.txtEducation -> {
                callLevel1Fragment((R.string.education_work))
            }
            R.id.txtPersonal -> {
                callLevel1Fragment((R.string.personal))
            }
            R.id.txtInterests -> {
                callLevel1Fragment((R.string.interests))
            }
            R.id.txtWellness -> {
                callLevel1Fragment((R.string.wellness))
            }
            R.id.txtMemories -> {
                callLevel1Fragment((R.string.memories))
            }
            R.id.txtShopping -> {
                callLevel1Fragment((R.string.shopping))
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



    private fun callLevel1Fragment(option: Int) {
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        val level1Fragment = Level1Fragment()
        val bundle = Bundle()
        bundle.putInt("category", option)
        level1Fragment.arguments = bundle

        NineBxApplication.instance.activityInstance!!.hideQuickAdd()
        prefrences.currentBox = getString(option)
        fragmentTransaction.add(R.id.frameLayout, level1Fragment).commit()

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