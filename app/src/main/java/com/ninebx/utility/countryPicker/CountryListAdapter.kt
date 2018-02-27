package com.ninebx.utility.countryPicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.home.account.interfaces.ICountrySelected
import com.ninebx.utility.NineBxPreferences

/**
 * Created by mukesh on 25/04/16.
 */
class CountryListAdapter(private val mContext: Context, internal var countries: List<Country>, private val iCountrySelected: ICountrySelected) : BaseAdapter() {
    internal var inflater: LayoutInflater
    internal var prefrences = NineBxPreferences()

    var countryp = CountryPicker()

    init {
        inflater = LayoutInflater.from(mContext)
    }

    override fun getCount(): Int {
        return countries.size
    }

    override fun getItem(arg0: Int): Any? {
        return null
    }

    override fun getItemId(arg0: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View? {
        var view = view
        val country = countries[position]

        if (view == null)
            view = inflater.inflate(R.layout.row, null)

        val cell = Cell.from(view)
        cell!!.textView.text = country.name

        country.loadFlagByCode(mContext)
        if (country.flag != -1)
            cell.imageView.setImageResource(country.flag)

        cell.layoutCountry.setOnClickListener {
            iCountrySelected.onCountrySelected(country.name)
            NineBxApplication.instance.getCountrySelected(country.name)
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }
        return view
    }


    internal class Cell {
        lateinit var textView: TextView
        lateinit var imageView: ImageView
        lateinit var layoutCountry: LinearLayout

        companion object {

            fun from(view: View?): Cell? {
                if (view == null)
                    return null

                if (view.tag == null) {
                    val cell = Cell()
                    cell.textView = view.findViewById<View>(R.id.row_title) as TextView
                    cell.imageView = view.findViewById<View>(R.id.row_icon) as ImageView
                    cell.layoutCountry = view.findViewById<View>(R.id.rowCountry) as LinearLayout
                    view.tag = cell
                    return cell
                } else {
                    return view.tag as Cell
                }
            }
        }
    }
}