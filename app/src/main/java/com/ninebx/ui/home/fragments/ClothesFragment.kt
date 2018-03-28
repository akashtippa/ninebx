package com.ninebx.ui.home.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.realm.decrypted.DecryptedCombineShopping
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.baseCategories.SubCategory
import com.ninebx.ui.home.baseSubCategories.Level3CategoryFragment
import com.ninebx.utility.AppLogger
import com.ninebx.utility.Constants
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_clothes.*

/***
 * Created by TechnoBlogger on 28/01/18.
 */
class ClothesFragment : FragmentBackHelper() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_clothes, container, false)
    }
    lateinit var combineItems : DecryptedCombineShopping
    lateinit var subCategory: SubCategory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivBack.setOnClickListener { activity!!.onBackPressed() }
        ivHome.setOnClickListener {
            val homeIntent = Intent(context, HomeActivity::class.java)
            startActivity(homeIntent)
            activity!!.finishAffinity()

        }
        val bundle = arguments
        combineItems = arguments!!.getParcelable(Constants.COMBINE_ITEMS)
        subCategory = arguments!!.getParcelable<SubCategory>(Constants.SUB_CATEGORY)
        setSizeCount()

        //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Select Category")
        layoutWomens.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Womens sizes")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            Log.d("Combine Items",Constants.COMBINE_ITEMS)
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))
            bundle.putString(Constants.FROM_CLASS, "ClothesFragment")
            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 21313)
        }

        layoutMens.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Mens sizes")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            Log.d("Combine Items",Constants.COMBINE_ITEMS)
            bundle.putString(Constants.FROM_CLASS, "ClothesFragment")
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))
            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 21313)
        }

        layoutGirls.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Girls sizes")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            Log.d("Combine Items",Constants.COMBINE_ITEMS)
            bundle.putString(Constants.FROM_CLASS, "ClothesFragment")
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))
            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 21313)
        }

        layoutBoys.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Boy's sizes")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putString(Constants.FROM_CLASS, "ClothesFragment")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            Log.d("Combine Items",Constants.COMBINE_ITEMS)
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))
            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 21313)
        }

        layoutBaby.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)

            bundle!!.putString("categoryName", "Baby's sizes")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putString(Constants.FROM_CLASS, "ClothesFragment")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            Log.d("Combine Items",Constants.COMBINE_ITEMS)
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))
            val intent = Intent( context, ContainerActivity::class.java)
            intent.putExtras(bundle)
            startActivityForResult(
                    intent, 21313)
        }

    }

    private fun setSizeCount() {
        tvWomensSizesCount.text = combineItems.getClothingSizesItems(subCategory.personName, "Women's Size").toString()
        tvMensSizesCount.text = combineItems.getClothingSizesItems(subCategory.personName, "Mens Size").toString()
        tvBabysSizesCount.text = combineItems.getClothingSizesItems(subCategory.personName, "Babys Size").toString()
        tvBoysSizesCount.text = combineItems.getClothingSizesItems(subCategory.personName, "Boys Size").toString()
        tvGirlsSizesCount.text = combineItems.getClothingSizesItems(subCategory.personName, "Girls Size").toString()
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        return super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if( requestCode == 21313 && resultCode == Activity.RESULT_OK ) {
            AppLogger.d("ActivityResult", "onActivityResult : Wellness" )
            combineItems = data!!.getParcelableExtra(Constants.COMBINE_ITEMS)
            setSizeCount()
        }
    }
}