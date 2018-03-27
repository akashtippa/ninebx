package com.ninebx.ui.home.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedCombineWellness
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.baseCategories.SubCategory
import com.ninebx.utility.AppLogger
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

    private lateinit var combineItems: DecryptedCombineWellness

    private lateinit var subCategory: SubCategory

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
        subCategory = arguments!!.getParcelable<SubCategory>(Constants.SUB_CATEGORY)

        combineItems = arguments!!.getParcelable<DecryptedCombineWellness>(Constants.COMBINE_ITEMS)

        layoutPersonalHealthRecord.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Identification")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putString(Constants.FROM_CLASS, "WellnessFragment")

            /* bundle.putParcelable(Constants.COMBINE_ITEMS, arguments!!.getParcelable(Constants.COMBINE_ITEMS))*/

            val combineWellness : DecryptedCombineWellness = DecryptedCombineWellness()
            for (i in 0 until combineItems.identificationItems.size){
                combineWellness.identificationItems.add(combineItems.identificationItems[i])
            }
            bundle.putParcelable(Constants.COMBINE_ITEMS, combineWellness)

            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 22313)
        }

        layoutMedicalHistory.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Medical history")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putString(Constants.FROM_CLASS, "WellnessFragment")
            /*bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))*/

            val combineWellness : DecryptedCombineWellness = DecryptedCombineWellness()
            for (i in 0 until combineItems.medicalHistoryItems.size){
                combineWellness.medicalHistoryItems.add(combineItems.medicalHistoryItems[i])
            }
            bundle.putParcelable(Constants.COMBINE_ITEMS, combineWellness)

            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 22313)
        }

        layoutHealthCare.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Healthcare providers")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putString(Constants.FROM_CLASS, "WellnessFragment")
            /*bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))*/

            val combineWellness : DecryptedCombineWellness = DecryptedCombineWellness()
            for (i in 0 until combineItems.healthcareProvidersItems.size){
                combineWellness.healthcareProvidersItems.add(combineItems.healthcareProvidersItems[i])
            }
            bundle.putParcelable(Constants.COMBINE_ITEMS, combineWellness)

            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 22313)
        }

        layoutMedications.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Medications")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putString(Constants.FROM_CLASS, "WellnessFragment")
            /*bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))*/

            val combineWellness : DecryptedCombineWellness = DecryptedCombineWellness()
            for (i in 0 until combineItems.medicationsItems.size){
                combineWellness.medicationsItems.add(combineItems.medicationsItems[i])
            }
            bundle.putParcelable(Constants.COMBINE_ITEMS, combineWellness)

            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 22313)
        }

        layoutMedicalConditions.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Medical conditions/Allergies")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putString(Constants.FROM_CLASS, "WellnessFragment")
            /*bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))*/

            val combineWellness : DecryptedCombineWellness = DecryptedCombineWellness()
            for (i in 0 until combineItems.medicalConditionsItems.size){
                combineWellness.medicalConditionsItems.add(combineItems.medicalConditionsItems[i])
            }
            bundle.putParcelable(Constants.COMBINE_ITEMS, combineWellness)

            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 22313)
        }

        layoutEye.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Eyeglass prescriptions")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putString(Constants.FROM_CLASS, "WellnessFragment")
            /*bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))*/

            val combineWellness : DecryptedCombineWellness = DecryptedCombineWellness()
            for (i in 0 until combineItems.eyeglassPrescriptionsItems.size){
                combineWellness.eyeglassPrescriptionsItems.add(combineItems.eyeglassPrescriptionsItems[i])
            }
            bundle.putParcelable(Constants.COMBINE_ITEMS, combineWellness)

            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 22313)
        }

        layoutVitalNumber.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Vital numbers")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putString(Constants.FROM_CLASS, "WellnessFragment")
            /*  bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))*/

            val combineWellness : DecryptedCombineWellness = DecryptedCombineWellness()
            for (i in 0 until combineItems.vitalNumbersItems.size){
                combineWellness.vitalNumbersItems.add(combineItems.vitalNumbersItems[i])
            }
            bundle.putParcelable(Constants.COMBINE_ITEMS, combineWellness)
            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 22313)
        }

        layoutCheckUps.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Checkups and visits")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putString(Constants.FROM_CLASS, "WellnessFragment")
            /* bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))*/

            val combineWellness : DecryptedCombineWellness = DecryptedCombineWellness()
            for (i in 0 until combineItems.checkupsItems.size){
                combineWellness.checkupsItems.add(combineItems.checkupsItems[i])
            }
            bundle.putParcelable(Constants.COMBINE_ITEMS, combineWellness)

            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 22313)
        }

        layoutEmergency.setOnClickListener{

            bundle!!.putString("categoryName", "Emergency Contacts")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putString(Constants.FROM_CLASS, "EmergencyContact")

            val combineWellness = DecryptedCombineWellness()
            combineWellness.emergencyContactsItems.addAll(combineItems.emergencyContactsItems)
            bundle.putParcelable(Constants.COMBINE_ITEMS, combineWellness)

            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 22313)
        }

        setCountForDocuments()
    }

    private fun setCountForDocuments() {
        tvHealthCareCount.text = combineItems.getHealthcareSize(subCategory.personName).toString()
        tvEmergencyContactsCount.text = combineItems.getEmergencyContactsSize(subCategory.personName).toString()
        tvMedicationCount.text = combineItems.getMedicationsSize(subCategory.personName).toString()
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
        super.onActivityResult(requestCode, resultCode, data)
        if( requestCode == 12313 && resultCode == Activity.RESULT_OK ) {
            AppLogger.d("ActivityResult", "onActivityResult : Wellness" )
            combineItems = data!!.getParcelableExtra(Constants.COMBINE_ITEMS)
            setCountForDocuments()
        }
    }
}
