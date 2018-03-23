package com.ninebx.ui.home.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.baseSubCategories.Level3CategoryFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivBack.setOnClickListener { activity!!.onBackPressed() }
        ivHome.setOnClickListener {
            val homeIntent = Intent(context, HomeActivity::class.java)
            startActivity(homeIntent)
            activity!!.finishAffinity()
        }

        //NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Select Category")
        layoutWomens.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Womens sizes")
            bundle.putString("categoryId", "1")
            bundle.putString("action","add")
            bundle.putInt(Constants.CURRENT_BOX,arguments!!.getInt(Constants.CURRENT_BOX))
            bundle.putParcelable(Constants.COMBINE_ITEMS,arguments!!.getParcelable(Constants.COMBINE_ITEMS))
            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutMens.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Mens sizes")
            bundle.putString("categoryId", "1")

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutGirls.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Girls sizes")
            bundle.putString("categoryId", "1")

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutBoys.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Boy's sizes")
            bundle.putString("categoryId", "1")

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }

        layoutBaby.setOnClickListener {
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val bundle = Bundle()
            bundle.putString("categoryName", "Baby's sizes")
            bundle.putString("categoryId", "1")

            val categoryFragment = Level3CategoryFragment()
            categoryFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, categoryFragment).commit()
        }
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        return super.onBackPressed()
    }
}