package com.ninebx.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.baseSubCategories.Level2CategoryFragment
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_wellness.*

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
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        layoutPersonalHealthRecord.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Identification")
            val categoryFragment = Level2CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layoutMedicalHistory.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Medical history")
            val categoryFragment = Level2CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layoutHealthCare.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Healthcare providers")
            val categoryFragment = Level2CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layoutEmergency.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Emergency contacts")
            val categoryFragment = Level2CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layoutMedications.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Medications")
            val categoryFragment = Level2CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layoutMedicalConditions.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Medical conditions/Allergies")
            val categoryFragment = Level2CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layoutEye.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Eyeglass prescriptions")
            val categoryFragment = Level2CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layoutVitalNumber.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Vital numbers")
            val categoryFragment = Level2CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }

        layoutCheckUps.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Checkups and visits")
            val categoryFragment = Level2CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
        }


    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.showToolbar()

        return super.onBackPressed()
    }
}