package com.ninebx.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.utility.DateTimeSelectionListener
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.countryPicker.CountryPicker
import com.ninebx.utility.getDateFromPicker
import com.ninebx.utility.getDateMonthYearFormat
import kotlinx.android.synthetic.main.add_memory.*
import java.util.*

/***
 * Created by TechnoBlogger on 24/01/18.
 */
class MemoryTimeLineFragment : FragmentBackHelper() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_memory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NineBxApplication.instance.activityInstance!!.hideToolbar()
        NineBxApplication.instance.activityInstance!!.hideBottomView()

        ivBack.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        txtDate.setOnClickListener {
            getDateFromPicker(this.context!!, Calendar.getInstance(), object : DateTimeSelectionListener {
                override fun onDateTimeSelected(selectedDate: Calendar) {
                    txtDate.text = (getDateMonthYearFormat(selectedDate.time))
                }
            })
        }

        edtLocation.setOnTouchListener(View.OnTouchListener { _, event ->
            val DRAWABLE_RIGHT = 2

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= edtLocation.right - edtLocation.compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                    val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.replace(R.id.frameLayout, CountryPicker()).commit()
                    return@OnTouchListener true
                }
            }
            false
        })
    }


    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.showToolbar()
        NineBxApplication.instance.activityInstance!!.showBottomView()
        return super.onBackPressed()
    }
}