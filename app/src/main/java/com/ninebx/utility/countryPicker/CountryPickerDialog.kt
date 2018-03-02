package com.ninebx.utility.countryPicker


import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.ListView
import com.ninebx.R
import com.ninebx.ui.home.account.interfaces.ICountrySelected
import java.util.ArrayList

/**
 * Created by Alok on 28/02/18.
 */
class CountryPickerDialog(private val context : Context, private val iCountrySelected : ICountrySelected ) {
    private var dialog: Dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.country_picker, null)
        dialog.setContentView(dialogView)
        initView( dialogView )
        showDialog()
    }


    private var searchEditText: EditText? = null
    private var countryListView: ListView? = null
    private var adapter: CountryListAdapter? = null
    private val countriesList = ArrayList<Country>()
    private var selectedCountriesList: MutableList<Country> = ArrayList()
    private var listener: CountryPickerListener? = null

    private fun initView(dialogView: View?) {

    }

    private fun showDialog() {

    }

}