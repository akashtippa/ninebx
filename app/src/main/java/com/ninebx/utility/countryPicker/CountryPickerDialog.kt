package com.ninebx.utility.countryPicker


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.*
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

    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_country_picker, null)
        dialog.setContentView(dialogView)
        initView( dialog )

        val window = dialog.window
        val wlp = window.attributes

        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window.attributes = wlp
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        showDialog()
    }



    private fun initView(dialogView: Dialog?) {
        val imageView = dialogView!!.findViewById<ImageView>(R.id.imgBack)
        imageView.setOnClickListener {
            dialog.dismiss()
        }
        setCountriesList(Country.getAllCountries(), dialogView.findViewById(R.id.dialogCountryLayout))
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

            countryListView!!.onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    iCountrySelected.onCountrySelected((adapter!!.getItem(position) as Country).name)
                    dialog.dismiss()
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