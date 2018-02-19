package com.ninebx.ui.home.baseSubCategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.utility.FragmentBackHelper
import com.ninebx.utility.KeyboardUtil
import com.ninebx.utility.NineBxPreferences
import kotlinx.android.synthetic.main.fragment_level2_category.*

/***
 * Created by TechnoBlogger on 23/01/18.
 */

class Level2CategoryFragment : FragmentBackHelper(), Level2CategoryView {

    private lateinit var mCategoryPresenter: Level2CategoryPresenter
    private val adapterExpandable: ExpandableListViewAdapter? = null

    private var strTitle = ""
    private var strSubTitle = ""

    private var boxValue = ""
    val prefrences = NineBxPreferences()

    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {
        layoutProgress.hide()
    }

    override fun onError(error: Int) {
    }


    override fun onSuccess(categories: ArrayList<Level2Category>) {
        hideProgress()
        inflateLayout(categories)
    }

    private fun inflateLayout(categories: ArrayList<Level2Category>) {
        layExpandable.setAdapter(ExpandableListViewAdapter(context!!, categories))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_level2_category, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCategoryPresenter = Level2CategoryPresenter(arguments!!.getString("categoryName"), arguments!!.getString("categoryID"), this)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.hideToolbar()
        NineBxApplication.instance.activityInstance!!.showQuickAddDisableText()

        boxValue = prefrences.currentBox!!
        ivBack.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }
        setTitle()

        setCamera(boxValue)
    }

    private fun setCamera(boxValue: String) {
        NineBxApplication.instance.activityInstance!!.changeQuickAddCamera(boxValue)
    }

    private fun setTitle() {
        val bundleValue = arguments!!.getString("categoryName")

        when (bundleValue) {

        // Common Items in Every

        // Financial Accounts
            "Banking" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"
            }
            "Investments/Retirement" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"
            }
            "Loan/Mortgages" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"
            }
            "Other financial accounts" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"
            }

        // Payment Methods
            "Credit/Debit cards" -> {
                etTitle.hint = "Card name"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Account"
            }

            "Other payment accounts" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"
            }

        // Property
            "Primary home (owned)" -> {
                etTitleValue.hint = ""
                etTitle.hint = "Property name"
                toolbarTitle.text = "Add Property"

            }
            "Property (rented for own use)" -> {
                etTitleValue.hint = ""
                etTitle.hint = "Property name"
                toolbarTitle.text = "Add Account"

            }
            "Vacation home" -> {
                etTitle.hint = "Property name"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Account"
            }
            "Investment/Rental property" -> {
                etTitle.hint = "Property name"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Account"
            }

        // Auto
            "Vehicles" -> {
                etTitle.hint = "Vehicle name"
                etTitleValue.hint = "License plate number"
                toolbarTitle.text = "Add Vehicle"
            }

            "Maintenance" -> {
                etTitle.hint = "Maintenance event"
                etTitleValue.hint = "Vehicle"
                toolbarTitle.text = "Add Record"
            }

        // Other Assets
            "Jewelry" -> {
                etTitle.hint = "Asset name"
                etTitleValue.hint = "Description/location"
                toolbarTitle.text = "Add Asset"
            }

            "Art and collectibles" -> {
                etTitle.hint = "Asset name"
                etTitleValue.hint = "Description/location"
                toolbarTitle.text = "Add Asset"
            }

            "Computers and electronics" -> {
                etTitle.hint = "Asset name"
                etTitleValue.hint = "Description/location"
                toolbarTitle.text = "Add Asset"
            }

            "Furniture" -> {
                etTitle.hint = "Asset name"
                etTitleValue.hint = "Description/location"
                toolbarTitle.text = "Add Asset"
            }

            "Others" -> {
                etTitle.hint = "Asset name"
                etTitleValue.hint = "Description/location"
                toolbarTitle.text = "Add Asset"
            }

        // Insurance
            "Homeowners/Renters insurance" -> {
                etTitle.hint = "Insurance company"
                etTitleValue.hint = "Insured property"
                toolbarTitle.text = "Add Policy"
            }

            "Auto insurance" -> {
                etTitle.hint = "Insurance company"
                etTitleValue.hint = "Insured vehicle"
                toolbarTitle.text = "Add Policy"
            }

            "Life insurance" -> {
                etTitle.hint = "Insurance company"
                etTitleValue.hint = "Insured person"
                toolbarTitle.text = "Add Policy"
            }

            "Health insurance" -> {
                etTitle.hint = "Insurance company"
                etTitleValue.hint = "Insured person"
                toolbarTitle.text = "Add Policy"
            }

            "Umbrella insurance" -> {
                etTitle.hint = "Insurance company"
                etTitleValue.hint = "Insured person"
                toolbarTitle.text = "Add Policy"
            }

        // Taxes

            "Past returns" -> {
                etTitle.hint = "Return name"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Return"
            }

            "Returns to be filed" -> {
                etTitle.hint = "Return name"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Return"
            }

        // Travel
            "Airline" -> {
                etTitle.hint = "Airline"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"

            }

            "Hotel" -> {
                etTitle.hint = "Hotel"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"

            }

            "Car Rental" -> {
                etTitle.hint = "Car Rental Company"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"

            }

            "Cruiseline" -> {
                etTitle.hint = "Cruiseline"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"

            }

            "Railway" -> {
                etTitle.hint = "Railway"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"

            }

            "Other" -> {
                etTitle.hint = "Other"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"

            }

            "Passport" -> {
                etTitle.hint = "Passport name"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Passport"

            }

            "Visa" -> {
                etTitle.hint = "Visa name"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Visa"

            }

            "Other travel document" -> {
                etTitle.hint = "Travel document title"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Document"

            }

            "Travel Dates And Plans" -> {
                etTitle.hint = "Description"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Travel Plan"

            }

            "Services/Other Accounts" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"
            }

            "Other Attachments" -> {
                etTitle.hint = "Title"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Attachment"
            }

        // Personal
            "Drivers License" -> {
                etTitle.hint = "Description"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add License"
            }
            "Social Security Card" -> {
                etTitle.hint = "Card name"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Card"
            }
            "Tax ID" -> {
                etTitle.hint = "Tax ID name"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add ID"
            }
            "Birth Certificate" -> {
                etTitle.hint = "Description"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Certificate"
            }
            "Marriage Certificate" -> {
                etTitle.hint = "Description"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Certificate"
            }
            "Other Government-Issued ID" -> {
                etTitle.hint = "ID name"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add ID"
            }

            "Add Person" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Qualification/degree"
                toolbarTitle.text = "Add Institution"
            }
            "Add person" -> {
                etTitle.hint = "Company name"
                etTitleValue.hint = "Position"
                toolbarTitle.text = "Add Company"
            }

            "Loyalty Programs" -> {
                etTitle.hint = "Brand/Retailer"
                etTitleValue.hint = "Account name"
                toolbarTitle.text = "Add Account"

            }

            "Recent Purchases" -> {
                etTitleValue.hint = "Brand/Retailer"
                etTitle.hint = "Item"
                toolbarTitle.text = "Add Purchase"
            }


            "Womens sizes" -> {
                etTitle.hint = "User name"
                etTitleValue.hint = "Size name"
                toolbarTitle.text = "Add Women's sizes"
            }


            "Mens sizes" -> {
                etTitle.hint = "User name"
                etTitleValue.hint = "Size name"
                toolbarTitle.text = "Add Mens's sizes"
            }

            "Girls sizes" -> {
                etTitle.hint = "User name"
                etTitleValue.hint = "Size name"
                toolbarTitle.text = "Add Girls's sizes"
            }
            "Boy's sizes" -> {
                etTitle.hint = "User name"
                etTitleValue.hint = "Size name"
                toolbarTitle.text = "Add Boy's sizes"
            }

            "Baby's sizes" -> {
                etTitle.hint = "User name"
                etTitleValue.hint = "Size name"
                toolbarTitle.text = "Add Baby's sizes"
            }

            "Identification" -> {
                etTitle.hint = "User name"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add Identification"
            }


            "Medical history" -> {
                etTitle.hint = "History"
                etTitleValue.hint = ""
                toolbarTitle.text = "Add History"
            }


            "Healthcare providers" -> {
                etTitle.hint = "Name"
                etTitleValue.hint = "Type of physician"
                toolbarTitle.text = "Add Providers"
            }


            "Emergency contacts" -> {
            }


            "Medications" -> {
                etTitle.hint = "Name"
                etTitleValue.hint = "Dose/strength"
                toolbarTitle.text = "Add Medications"
            }

            "Medical conditions/Allergies" -> {
                etTitle.hint = "Condition"
                etTitleValue.hint = "Date diagnosed"
                toolbarTitle.text = "Add Conditions"
            }

            "Eyeglass prescriptions" -> {
                etTitle.hint = "Physician name"
                etTitleValue.hint = "Date prescribed"
                toolbarTitle.text = "Add Prescription"
            }

            "Vital numbers" -> {
                etTitle.hint = "Description"
                etTitleValue.hint = "Measurement date"
                toolbarTitle.text = "Add Record"
            }


            "Checkups and visits" -> {
                etTitle.hint = "Description"
                etTitleValue.hint = "Physician name"
                toolbarTitle.text = "Add Visit"
            }


        }
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.hideBottomView()
        NineBxApplication.instance.activityInstance!!.showToolbar()
        NineBxApplication.instance.activityInstance!!.hideQuickAdd()
        NineBxApplication.instance.activityInstance!!.showBackIcon()
        KeyboardUtil.hideSoftKeyboard(NineBxApplication.instance.activityInstance!!)
        return super.onBackPressed()
    }

}
