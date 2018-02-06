package com.ninebx.utility.countryPicker

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.ninebx.NineBxApplication

import com.ninebx.R
import com.ninebx.utility.AppLogger
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.country_picker.*

import java.util.ArrayList
import java.util.Locale

/**
 * Created by mukesh on 25/04/16.
 */
class CountryPicker : FragmentBackHelper() {

    private var searchEditText: EditText? = null
    private var countryListView: ListView? = null
    private var adapter: CountryListAdapter? = null
    private val countriesList = ArrayList<Country>()
    private var selectedCountriesList: MutableList<Country> = ArrayList()
    private var listener: CountryPickerListener? = null


    init {
        setCountriesList(Country.getAllCountries())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.country_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NineBxApplication.instance.activityInstance!!.hideToolbar()

        ivBackFromSelectCountry.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        searchEditText = view.findViewById<View>(R.id.country_code_picker_search) as EditText
        countryListView = view.findViewById<View>(R.id.country_code_picker_listview) as ListView

        selectedCountriesList = ArrayList(countriesList.size)
        selectedCountriesList.addAll(countriesList)

        adapter = CountryListAdapter(activity, selectedCountriesList)
        countryListView!!.adapter = adapter

        countryListView!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (listener != null) {
                val country = selectedCountriesList[position]
                listener!!.onSelectCountry(country.name, country.code, country.dialCode,
                        country.flag)
                Toast.makeText(context, "Selected Country is " + country.name, Toast.LENGTH_LONG).show()
                AppLogger.e("Selected Country", " is " + country.name)

            }
        }



        searchEditText!!.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {
                search(s.toString())
            }
        })

    }

    fun setListener(listener: CountryPickerListener) {
        this.listener = listener
    }

    @SuppressLint("DefaultLocale")
    private fun search(text: String) {
        selectedCountriesList.clear()
        for (country in countriesList) {
            if (country.name.toLowerCase(Locale.ENGLISH).contains(text.toLowerCase())) {
                selectedCountriesList.add(country)
            }
        }
        adapter!!.notifyDataSetChanged()
    }

    fun setCountriesList(newCountries: List<Country>) {
        this.countriesList.clear()
        this.countriesList.addAll(newCountries)
    }

    companion object {
        fun newInstance(dialogTitle: String): CountryPicker {
            val picker = CountryPicker()
            val bundle = Bundle()
            bundle.putString("dialogTitle", dialogTitle)
            picker.arguments = bundle
            return picker
        }
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.showToolbar()

        return super.onBackPressed()
    }


}
