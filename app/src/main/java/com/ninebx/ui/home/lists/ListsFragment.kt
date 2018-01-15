package com.ninebx.ui.home.lists

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import kotlinx.android.synthetic.main.fragment_lists.*

/**
 * Created by Alok on 03/01/18.
 */
class ListsFragment : Fragment(), ListsCommunicationView {

    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {

    }

    override fun onError(error: Int) {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_lists, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layHome.setOnClickListener {
            callBottomViewFragment(getString(R.string.home_amp_money))
        }
        layTravel.setOnClickListener {
            callBottomViewFragment(getString(R.string.travel))
        }
        layContact.setOnClickListener {
            callBottomViewFragment(getString(R.string.contacts))
        }
        layEducation.setOnClickListener {
            callBottomViewFragment(getString(R.string.education_work))
        }
        layPersonal.setOnClickListener {
            callBottomViewFragment(getString(R.string.personal))
        }
        layInterests.setOnClickListener {
            callBottomViewFragment(getString(R.string.interests))
        }
        layWellness.setOnClickListener {
            callBottomViewFragment(getString(R.string.wellness))
        }
        layMemories.setOnClickListener {
            callBottomViewFragment(getString(R.string.memories))
        }
        layShopping.setOnClickListener {
            callBottomViewFragment(getString(R.string.shopping))
        }

    }

    private fun callBottomViewFragment(option: String) {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.home_amp_money))
        NineBxApplication.instance.activityInstance!!.showHomeNhideQuickAdd()
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        when (option) {
            getString(R.string.home_amp_money) -> {
                fragmentTransaction.add(R.id.frameLayout, SubListsFragment()).commit()
            }
            getString(R.string.travel) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.travel))
                fragmentTransaction.add(R.id.frameLayout, SubListsFragment()).commit()
            }
            getString(R.string.contacts) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.contacts))
                fragmentTransaction.add(R.id.frameLayout, SubListsFragment()).commit()
            }
            getString(R.string.education_work) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.education_work))
                fragmentTransaction.add(R.id.frameLayout, SubListsFragment()).commit()
            }
            getString(R.string.personal) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.personal))
                fragmentTransaction.add(R.id.frameLayout, SubListsFragment()).commit()
            }
            getString(R.string.interests) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.interests))
                fragmentTransaction.add(R.id.frameLayout, SubListsFragment()).commit()
            }
            getString(R.string.wellness) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.wellness))
                fragmentTransaction.add(R.id.frameLayout, SubListsFragment()).commit()
            }
            getString(R.string.memories) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.memories))
                fragmentTransaction.add(R.id.frameLayout, SubListsFragment()).commit()
            }
            getString(R.string.shopping) -> {
                NineBxApplication.instance.activityInstance!!.changeToolbarTitle(getString(R.string.shopping))
                fragmentTransaction.add(R.id.frameLayout, SubListsFragment()).commit()
            }
        }
    }


}