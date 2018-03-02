package com.ninebx.utility.countryPicker


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import com.ninebx.R
import com.ninebx.ui.home.account.interfaces.ICountrySelected
import java.util.*

/**
 * Created by Alok on 28/02/18.
 */
class CountryPickerDialog(private val context : Context, private val iCountrySelected : ICountrySelected ) {

    private var dialog: Dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)

    private var searchEditText: EditText? = null
    private var countryListView: ListView? = null
    private var adapter: CountryListAdapter? = null
    private val countriesList = ArrayList<Country>()
    private var selectedCountriesList: MutableList<Country> = ArrayList()
    private var listener: CountryPickerListener? = null

    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.country_picker, null)
        dialog.setContentView(dialogView)
        initView( dialogView )
        showDialog()
    }



    private fun initView(dialogView: View?) {
        dialogView!!.findViewById<ImageView>(R.id.ivBackFromSelectCountry).setOnClickListener {
            dialog.dismiss()
        }
        setCountriesList(Country.getAllCountries(), dialogView)
    }

    private fun setCountriesList(newCountries: List<Country>, view: View) {
        this.countriesList.addAll(newCountries)
        if( adapter != null ) {
            adapter!!.notifyDataSetChanged()
        }
        else {
            searchEditText = view.findViewById<View>(R.id.country_code_picker_search) as EditText
            countryListView = view.findViewById<View>(R.id.country_code_picker_listview) as ListView

            selectedCountriesList = ArrayList(countriesList.size)
            selectedCountriesList.addAll(countriesList)

            adapter = CountryListAdapter(context, selectedCountriesList, iCountrySelected)
            countryListView!!.adapter = adapter

            countryListView!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->

                val country = selectedCountriesList[position]
                iCountrySelected.onCountrySelected(country.name)
                /*listener!!.onSelectCountry(country.name, country.code, country.dialCode,
                        country.flag)*/
                dialog.dismiss()

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

    private fun showDialog() {
        dialog.show()
    }

}