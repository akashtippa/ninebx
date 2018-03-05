package com.ninebx.utility.countryPicker

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.account.interfaces.ICountrySelected
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.country_picker.*
import java.util.*

class CountryPicker : FragmentBackHelper() {


    private var searchEditText: EditText? = null
    private var countryListView: ListView? = null
    private var adapter: CountryListAdapter? = null
    private val countriesList = ArrayList<Country>()
    private var selectedCountriesList: MutableList<Country> = ArrayList()
    private var listener: CountryPickerListener? = null
    private var iCountrySelected: ICountrySelected? = null

    fun setCountrySelectionListener ( iCountrySelected: ICountrySelected ) {
        this.iCountrySelected = iCountrySelected
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

        setCountriesList(Country.getAllCountries(), view)



    }
//
//    public fun onSelectedCountry(selectedCountry: String) {
//        NineBxApplication.instance.getCountrySelected(selectedCountry)
//        NineBxApplication.instance.activityInstance!!.onBackPressed()
//    }

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

    private fun setCountriesList(newCountries: List<Country>, view: View) {
        this.countriesList.clear()
        this.countriesList.addAll(newCountries)
        if( adapter != null ) {
            adapter!!.notifyDataSetChanged()
        }
        else {
            searchEditText = view.findViewById<View>(R.id.country_code_picker_search) as EditText
            countryListView = view.findViewById<View>(R.id.country_code_picker_listview) as ListView

            selectedCountriesList = ArrayList(countriesList.size)
            selectedCountriesList.addAll(countriesList)

            adapter = CountryListAdapter(context!!, selectedCountriesList, iCountrySelected!!)
            countryListView!!.adapter = adapter

            countryListView!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                if (listener != null) {
                    val country = selectedCountriesList[position]
                    listener!!.onSelectCountry(country.name, country.code, country.dialCode,
                            country.flag)
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
