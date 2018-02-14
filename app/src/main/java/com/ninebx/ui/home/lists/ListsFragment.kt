package com.ninebx.ui.home.lists

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.BaseHomeFragment
import kotlinx.android.synthetic.main.fragment_lists.*

/**
 * Created by Alok on 03/01/18.
 */
class ListsFragment : BaseHomeFragment(), ListsCommunicationView {

    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {

    }

    override fun onError(error: Int) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layHome.setOnClickListener {
            callSubListFragment(getString(R.string.home_amp_money))
        }
        layTravel.setOnClickListener {
            callSubListFragment(getString(R.string.travel))
        }
        layContact.setOnClickListener {
            callSubListFragment(getString(R.string.contacts))
        }
        layEducation.setOnClickListener {
            callSubListFragment(getString(R.string.education_work))
        }
        layPersonal.setOnClickListener {
            callSubListFragment(getString(R.string.personal))
        }
        layInterests.setOnClickListener {
            callSubListFragment(getString(R.string.interests))
        }
        layWellness.setOnClickListener {
            callSubListFragment(getString(R.string.wellness))
        }
        layMemories.setOnClickListener {
            callSubListFragment(getString(R.string.memories))
        }
        layShopping.setOnClickListener {
            callSubListFragment(getString(R.string.shopping))
        }

    }

    private fun callSubListFragment(option: String) {
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        val bundle = Bundle()
        bundle.putString("homeScreen", "bottom")

        val categoryFragment = SubListsFragment()
        categoryFragment.arguments = bundle
        fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()

        when (option) {
            getString(R.string.home_amp_money) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.home_amp_money))
            }
            getString(R.string.travel) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.travel))
            }
            getString(R.string.contacts) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.contacts))
            }
            getString(R.string.education_work) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.education_work))
            }
            getString(R.string.personal) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.personal))
            }
            getString(R.string.interests) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.interests))
            }
            getString(R.string.wellness) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.wellness))
            }
            getString(R.string.memories) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.memories))
            }
            getString(R.string.shopping) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle("Lists - " + getString(R.string.shopping))
            }
        }
    }


}