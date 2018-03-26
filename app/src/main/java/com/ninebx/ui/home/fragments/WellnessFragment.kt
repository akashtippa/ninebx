package com.ninebx.ui.home.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedCombineWellness
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.baseCategories.SubCategory
import com.ninebx.utility.Constants
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
        ivHome.setOnClickListener {
            val homeIntent = Intent(context, HomeActivity::class.java)
            startActivity(homeIntent)
            activity!!.finishAffinity()
        }

        ivBack.setOnClickListener {  activity!!.onBackPressed()  }
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        val bundle = arguments
        val subCategory = arguments!!.getParcelable<SubCategory>(Constants.SUB_CATEGORY)

        val combineItems : DecryptedCombineWellness = arguments!!.getParcelable<DecryptedCombineWellness>(Constants.COMBINE_ITEMS)

        layoutPersonalHealthRecord.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            val bundle = Bundle()
            bundle.putString("categoryName", "Identification")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))

            bundle.putParcelable(Constants.COMBINE_ITEMS, arguments!!.getParcelable(Constants.COMBINE_ITEMS))

            val categoryFragment = Level2Fragment()
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

            val categoryFragment = Level2Fragment()
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

            val categoryFragment = Level2Fragment()
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

            val categoryFragment = Level2Fragment()
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

            val categoryFragment = Level2Fragment()
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

            val categoryFragment = Level2Fragment()
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

            val categoryFragment = Level2Fragment()
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

            val categoryFragment = Level2Fragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutEmergency.setOnClickListener{
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

           /* bundle!!.putString("categoryName", "Emergency Contacts")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))*/

            val categoryFragment = EmergencyContactsFragment()
            /*categoryFragment.arguments = bundle*/
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        tvHealthCareCount.text = combineItems.getHealthcareSize(subCategory.personName).toString()
        tvEmergencyContactsCount.text = combineItems.emergencyContactsItems.size.toString()
        tvMedicationCount.text = combineItems.medicationsItems.size.toString()
        tvMedicalAllergiesCount.text = combineItems.medicalConditionsItems.size.toString()
        tvEyeGlassPrescriptionCount.text = combineItems.eyeglassPrescriptionsItems.size.toString()
        tvVitalNumbersCount.text = combineItems.vitalNumbersItems.size.toString()
        tvCheckupAndVisitsCount.text = combineItems.checkupsItems.size.toString()
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        return super.onBackPressed()
    }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        }
    }
