package com.ninebx.ui.home.baseSubCategories

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.*
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.baseCategories.OptionItem
import com.ninebx.utility.*
import kotlinx.android.synthetic.main.fragment_level3_category.*

/***
 * Created by TechnoBlogger on 23/01/18.
 */

class Level3CategoryFragment : FragmentBackHelper(), Level2CategoryView {

    override fun savedToRealm( combine : Parcelable ) {
        if( context != null ) {
            context!!.hideProgressDialog()
            arguments!!.putString("action", "add")
            arguments!!.putParcelable( "selectedDocument", selectedDocument )
            arguments!!.putParcelable(Constants.COMBINE_ITEMS, combine)
            (activity!! as ContainerActivity).onLevel3Action(arguments!!)
        }
    }

    override fun setValueToDocument(level2Category: Level2SubCategory) {
        mCategoryPresenter.setValueToDocument(level2Category)
    }

    override fun saveDocument(context: Context?) {
        val subTitle = if( etTitleValue.isVisible() ) etTitleValue.text.toString().trim() else tvTitleValue.text.toString()
        mCategoryPresenter.saveDocument( context, combineItem, etTitle.text.toString().trim(), subTitle )
    }

    private lateinit var mCategoryPresenter: Level2CategoryPresenter

    private var strTitle = ""
    private var strSubTitle = ""

    private var boxValue = ""
    val prefrences = NineBxPreferences()

    private var categoryName = ""
    private var categoryID = ""
    private var categoryInt = -1
    private var classType = ""
    private var selectedDocument : Parcelable ?= null
    private var combineItem : Parcelable ?= null
    private var action : String = ""
    private var isEditMode = false

    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {
        layoutProgress.hide()
    }

    override fun onError(error: Int) {
    }

    private lateinit var level2Categories: ArrayList<Level2Category>

    override fun onSuccess(categories: ArrayList<Level2Category>) {
        hideProgress()
        level2Categories = categories
        inflateLayout(categories)
    }

    private val visibilityMap : HashMap<String, Int> = HashMap()
    private fun inflateLayout(categories: ArrayList<Level2Category>) {

        etTitle.isEnabled = isEditMode
        tvTitleValue.isEnabled = isEditMode
        etTitleValue.isEnabled = isEditMode

        layExpandable.removeAllViews()
        for( category in categories ) {

            val level3ExpandableLayout = LayoutInflater.from(context).inflate(R.layout.layout_level3_expandable_recyclerview,null)
            val lblListHeader : TextView = level3ExpandableLayout.findViewById(R.id.lblListHeader)
            val rvLevel3 : RecyclerView = level3ExpandableLayout.findViewById(R.id.rvLevel3)
            val ivExpand : ImageView = level3ExpandableLayout.findViewById(R.id.ivExpand)
            val ivCollapse : ImageView = level3ExpandableLayout.findViewById(R.id.ivCollapse)

            rvLevel3.layoutManager = LinearLayoutManager(context)
            lblListHeader.text = category.title
            rvLevel3.adapter = ExpandableRecyclerViewAdapter(
                    context!!,
                    category.subCategories,
                    this,
                    categoryName,
                    classType,
                    ArrayList(NineBxApplication.instance.activityInstance!!.getCurrentUsers()[0].members),
                    isEditMode)
            layExpandable.addView(level3ExpandableLayout)

            lblListHeader.setOnClickListener {
                if( rvLevel3.isVisible() ) {
                    ivCollapse.show()
                    ivExpand.hide()
                }
                else {
                    ivCollapse.hide()
                    ivExpand.show()
                }
                rvLevel3.toggleVisibility()
                visibilityMap.put(category.title, rvLevel3.visibility)
            }
            rvLevel3.visibility = if( visibilityMap.containsKey(category.title) ) {
                visibilityMap[category.title]!!
            } else {
                visibilityMap.put(category.title, View.GONE)
                visibilityMap[category.title]!!
            }
        }

        if( selectedDocument != null ) {
            AppLogger.d("Level2Category", "Selected Document : " + selectedDocument)
            when( selectedDocument ) {
            //Home&Banking
                is DecryptedFinancial -> {
                    val decryptedFinancial : DecryptedFinancial = selectedDocument as DecryptedFinancial
                    etTitle.setText(decryptedFinancial.institutionName)
                    etTitleValue.setText(decryptedFinancial.accountName)
                    AppLogger.d("AccountName" , " " + decryptedFinancial.accountName)
                    createdValue.text = decryptedFinancial.created
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                    Log.d("AccountName",decryptedFinancial.accountName)
                    Log.d("Modified",decryptedFinancial.modified)

                }
                is DecryptedPayment -> {
                    val decryptedFinancial : DecryptedPayment = selectedDocument as DecryptedPayment
                    etTitle.setText(decryptedFinancial.cardName)
                    etTitleValue.setText("")
                    etTitleValue.isEnabled = false
                    createdValue.text = decryptedFinancial.created
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedProperty-> {
                    val decryptedFinancial : DecryptedProperty = selectedDocument as DecryptedProperty
                    etTitle.setText(decryptedFinancial.propertyName)
                    etTitleValue.setText(if( decryptedFinancial.titleName.isNotEmpty()) decryptedFinancial.titleName else decryptedFinancial.propertyName)
                    createdValue.text = decryptedFinancial.created
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedVehicle-> {
                    val decryptedFinancial : DecryptedVehicle = selectedDocument as DecryptedVehicle
                    etTitle.setText(decryptedFinancial.vehicleName)
                    etTitleValue.setText(decryptedFinancial.licenseNumber)
                    createdValue.text = decryptedFinancial.created
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedAsset-> {
                    val decryptedFinancial : DecryptedAsset = selectedDocument as DecryptedAsset
                    etTitle.setText(decryptedFinancial.assetName)
                    etTitleValue.setText(decryptedFinancial.descriptionOrLocation)
                    createdValue.text = decryptedFinancial.created
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedInsurance-> {
                    val decryptedFinancial : DecryptedInsurance = selectedDocument as DecryptedInsurance
                    etTitle.setText(decryptedFinancial.insuranceCompany)
                    etTitleValue.setText(decryptedFinancial.insuredProperty)
                    createdValue.text = decryptedFinancial.created
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedTax-> {
                    val decryptedFinancial : DecryptedTax = selectedDocument as DecryptedTax
                    etTitle.setText(decryptedFinancial.returnName)
                    etTitleValue.setText("")
                    etTitleValue.isEnabled = false
                    createdValue.text = decryptedFinancial.created
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }

            //Personal
                is DecryptedCertificate -> {
                    val decryptedCertificate : DecryptedCertificate = selectedDocument as DecryptedCertificate
                    etTitle.setText(decryptedCertificate.nameOnCertificate)
                    etTitleValue.setEnabled(false)

                    createdValue.text = decryptedCertificate.created
                    modifiedValue.setText(decryptedCertificate.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedGovernment -> {
                    val decryptedGovernment : DecryptedGovernment = selectedDocument as DecryptedGovernment
                    etTitle.setText(decryptedGovernment.idName)
                    etTitleValue.setEnabled(false)

                    createdValue.text = decryptedGovernment.created
                    modifiedValue.setText(decryptedGovernment.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedLicense ->{
                    val decryptedLicense : DecryptedLicense = selectedDocument as DecryptedLicense
                    etTitle.setText(decryptedLicense.lic_description)
                    etTitleValue.setEnabled(false)

                    createdValue.text = decryptedLicense.created
                    modifiedValue.setText(decryptedLicense.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedPersonal -> {
                    val decryptedPersonal : DecryptedPersonal = selectedDocument as DecryptedPersonal
                    etTitle.setText(decryptedPersonal.institutionName)
                    etTitleValue.setText(decryptedPersonal.accountName)
                    //etTitleValue.setEnabled(false)
                    createdValue.text = decryptedPersonal.created
                    modifiedValue.setText(decryptedPersonal.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedSocial -> {
                    val decryptedSocial : DecryptedSocial = selectedDocument as DecryptedSocial
                    etTitle.setText(decryptedSocial.cardName)
                    etTitleValue.setEnabled(false)

                    modifiedValue.setText(decryptedSocial.modified)
                    createdValue.text = decryptedSocial.created
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedTaxID -> {
                    val decryptedTaxID : DecryptedTaxID = selectedDocument as DecryptedTaxID
                    etTitle.setText(decryptedTaxID.taxIdName)
                    etTitleValue.setEnabled(false)

                    modifiedValue.setText(decryptedTaxID.modified)
                    createdValue.text = decryptedTaxID.created
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
            //Wellness
                is DecryptedIdentification -> {
                    val decryptedIdentification : DecryptedIdentification = selectedDocument as DecryptedIdentification
                    etTitle.setText(decryptedIdentification.name)
                    etTitleValue.setText(decryptedIdentification.name)
                    modifiedValue.setText(decryptedIdentification.modified)
                    createdValue.text = decryptedIdentification.created
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedMedicalHistory -> {
                    val decryptedMedicalHistory : DecryptedMedicalHistory = selectedDocument as DecryptedMedicalHistory
                    etTitle.setText(decryptedMedicalHistory.history)
                    modifiedValue.setText(decryptedMedicalHistory.modified)
                    createdValue.text = decryptedMedicalHistory.created
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedCheckups -> {
                    val decryptedCheckups : DecryptedCheckups = selectedDocument as DecryptedCheckups
                    etTitle.setText(decryptedCheckups.checkup_description)
                    etTitleValue.setText(decryptedCheckups.physicianName)
                    modifiedValue.setText(decryptedCheckups.modified)
                    createdValue.setText(decryptedCheckups.created)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedEmergencyContacts -> {
                    val decryptedEmergencyContacts : DecryptedEmergencyContacts = selectedDocument as DecryptedEmergencyContacts
                    modifiedValue.setText(decryptedEmergencyContacts.modified)
                    createdValue.text = decryptedEmergencyContacts.created
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedEyeglassPrescriptions -> {
                    val decryptedEyeglassPrescriptions : DecryptedEyeglassPrescriptions = selectedDocument as DecryptedEyeglassPrescriptions
                    etTitle.setText(decryptedEyeglassPrescriptions.physicianName)
                    etTitleValue.setText(decryptedEyeglassPrescriptions.datePrescribed)
                    modifiedValue.setText(decryptedEyeglassPrescriptions.modified)
                    createdValue.text = decryptedEyeglassPrescriptions.created
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedHealthcareProviders -> {
                    val decryptedHealthcareProviders : DecryptedHealthcareProviders= selectedDocument as DecryptedHealthcareProviders
                    etTitle.setText(decryptedHealthcareProviders.name)
                    etTitleValue.setText(decryptedHealthcareProviders.physicianType)
                    modifiedValue.setText(decryptedHealthcareProviders.modified)
                    createdValue.text = decryptedHealthcareProviders.created
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedMedicalConditions -> {
                    val decryptedMedicalCOnditions : DecryptedMedicalConditions= selectedDocument as DecryptedMedicalConditions
                    etTitle.setText(decryptedMedicalCOnditions.condition)
                    etTitleValue.setText(decryptedMedicalCOnditions.dateDiagnosed)
                    modifiedValue.setText(decryptedMedicalCOnditions.modified)
                    createdValue.text = decryptedMedicalCOnditions.created
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedMedications ->{
                    val decryptedMedications : DecryptedMedications= selectedDocument as DecryptedMedications
                    etTitle.setText(decryptedMedications.name)
                    etTitleValue.setText(decryptedMedications.strength)
                    modifiedValue.setText(decryptedMedications.modified)
                    createdValue.text = decryptedMedications.created
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedVitalNumbers -> {
                    val decryptedVitalNumbers : DecryptedVitalNumbers= selectedDocument as DecryptedVitalNumbers
                    etTitle.setText(decryptedVitalNumbers.vital_description)
                    etTitleValue.setText(decryptedVitalNumbers.measurementDate)
                    modifiedValue.setText(decryptedVitalNumbers.modified)
                    createdValue.text = decryptedVitalNumbers.created
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedWellness -> {
                    val decryptedWellness: DecryptedWellness = selectedDocument as DecryptedWellness
                    createdValue.setText(decryptedWellness.created)
                    etTitle.setText(decryptedWellness.institutionName)
                    etTitleValue.setText(decryptedWellness.accountName)
                    modifiedValue.setText(decryptedWellness.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
            //Travel
                is DecryptedDocuments -> {
                    val decryptedDocuments : DecryptedDocuments = selectedDocument as DecryptedDocuments
                    when(decryptedDocuments.selectionType){
                        "travel_2001" -> {
                            etTitle.setText(decryptedDocuments.passportName)
                            etTitleValue.setEnabled(false)
                        }
                        "travel_2002"->{
                            etTitle.setText(decryptedDocuments.visaName)
                            etTitleValue.setEnabled(false)
                        }
                        "travel_2003" -> {
                            etTitle.setText(decryptedDocuments.travelDocumentTitle)
                            etTitleValue.setEnabled(false)
                        }
                    }
                    modifiedValue.setText(decryptedDocuments.modified)
                    createdValue.setText(decryptedDocuments.created)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedLoyalty -> {
                    val decryptedLoyalty : DecryptedLoyalty = selectedDocument as DecryptedLoyalty
                    createdValue.setText(decryptedLoyalty.created)
                    AppLogger.d("Level2Category", "decryptedLoyalty " + decryptedLoyalty)
                    when( decryptedLoyalty.selectionType ) {
                        "travel_1001" -> {
                            etTitle.setText(decryptedLoyalty.airLine)
                            etTitleValue.setText(decryptedLoyalty.accountName)
                            AppLogger.d("AccountName" , " " + decryptedLoyalty.accountName)
                        }
                        "travel_1002" -> {
                            etTitle.setText(decryptedLoyalty.hotel)
                            etTitleValue.setText(decryptedLoyalty.accountName)
                            AppLogger.d("AccountName" , " " + decryptedLoyalty.accountName)
                        }
                        "travel_1003" -> {
                            etTitle.setText(decryptedLoyalty.carRentalCompany)
                            etTitleValue.setText(decryptedLoyalty.accountName)
                            AppLogger.d("AccountName" , " " + decryptedLoyalty.accountName)
                        }
                        "travel_1004" -> {
                            etTitle.setText(decryptedLoyalty.cruiseline)
                            etTitleValue.setText(decryptedLoyalty.accountName)
                            AppLogger.d("AccountName" , " " + decryptedLoyalty.accountName)
                        }
                        "travel_1005" -> {
                            etTitle.setText(decryptedLoyalty.railway)
                            etTitleValue.setText(decryptedLoyalty.accountName)
                            AppLogger.d("AccountName" , " " + decryptedLoyalty.accountName)
                        }
                        "travel_1006" -> {
                            etTitle.setText(decryptedLoyalty.other)
                            etTitleValue.setText(decryptedLoyalty.accountName)
                            AppLogger.d("AccountName" , " " + decryptedLoyalty.accountName)
                        }
                    }
                    modifiedValue.setText(decryptedLoyalty.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                    /*etTitleValue.setText(decryptedLoyalty.nameOnAccount)*/
                }
                is DecryptedTravel -> {
                    val decryptedTravel : DecryptedTravel = selectedDocument as DecryptedTravel
                    createdValue.setText(decryptedTravel.created)
                    etTitle.setText(decryptedTravel.institutionName)
                    etTitleValue.setText(decryptedTravel.accountName)
                    modifiedValue.setText(decryptedTravel.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedVacations -> {
                    val decryptedVacations : DecryptedVacations = selectedDocument as DecryptedVacations
                    createdValue.setText(decryptedVacations.created)
                    etTitle.setText(decryptedVacations.vac_description)
                    etTitleValue.setEnabled(false)
                    modifiedValue.setText(decryptedVacations.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }

            //Shopping
                is DecryptedLoyaltyPrograms -> {
                    val decryptedLoyaltyPrograms : DecryptedLoyaltyPrograms = selectedDocument as DecryptedLoyaltyPrograms
                    createdValue.setText(decryptedLoyaltyPrograms.created)
                    etTitle.setText(decryptedLoyaltyPrograms.brandName)
                    etTitleValue.setText(decryptedLoyaltyPrograms.accountName)
                    modifiedValue.setText(decryptedLoyaltyPrograms.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedRecentPurchase -> {
                    val decryptedRecentPurchase : DecryptedRecentPurchase = selectedDocument as DecryptedRecentPurchase
                    createdValue.setText(decryptedRecentPurchase.created)
                    etTitle.setText(decryptedRecentPurchase.itemName)
                    etTitleValue.setText(decryptedRecentPurchase.brandName)
                    modifiedValue.setText(decryptedRecentPurchase.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedClothingSizes -> {
                    val decryptedClothingSizes : DecryptedClothingSizes = selectedDocument as DecryptedClothingSizes
                    createdValue.setText(decryptedClothingSizes.created)
                    etTitle.setText(decryptedClothingSizes.personName)
                    etTitleValue.setText(decryptedClothingSizes.sizeName)
                    modifiedValue.setText(decryptedClothingSizes.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
                is DecryptedShopping -> {
                    val decryptedShopping : DecryptedShopping = selectedDocument as DecryptedShopping
                    createdValue.setText(decryptedShopping.created)
                    etTitle.setText(decryptedShopping.institutionName)
                    etTitleValue.setText(decryptedShopping.accountName)
                    modifiedValue.setText(decryptedShopping.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
            //Interests
                is DecryptedInterests -> {
                    val decryptedInterests : DecryptedInterests = selectedDocument as DecryptedInterests
                    createdValue.setText(decryptedInterests.created)
                    etTitle.setText(decryptedInterests.institutionName)
                    etTitleValue.setText(decryptedInterests.accountName)
                    modifiedValue.setText(decryptedInterests.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
            //Contacts
                is DecryptedMainContacts -> {
                    val decryptedMainContacts = selectedDocument as DecryptedMainContacts
                    createdValue.setText(decryptedMainContacts.created)
                    etTitle.setText(decryptedMainContacts.institutionName)
                    etTitleValue.setText(decryptedMainContacts.accountName)
                    modifiedValue.setText(decryptedMainContacts.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
            //EducationAndWork
                is DecryptedEducation -> {
                    val decryptedEducationItems = selectedDocument as DecryptedEducation
                    createdValue.setText(decryptedEducationItems.created)
                    etTitle.setText(decryptedEducationItems.institutionName)
                    etTitleValue.setText(decryptedEducationItems.accountName)
                    modifiedValue.setText(decryptedEducationItems.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
            //Memories
                is DecryptedMainMemories -> {
                    val decryptedMainMemores = selectedDocument as DecryptedMainMemories
                    createdValue.setText(decryptedMainMemores.created)
                    etTitle.setText(decryptedMainMemores.institutionName)
                    etTitleValue.setText(decryptedMainMemores.accountName)
                    modifiedValue.setText(decryptedMainMemores.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    createdValue.setTypeface(null, Typeface.ITALIC)
                }
            }
            if( modifiedValue.text.equals(createdValue.text) ) {
                modified.hide()
                modifiedValue.hide()
            }
            tvTitleValue.text = etTitleValue.text.toString()
            if( arguments!!.containsKey(Constants.SUB_OPTIONS)
                    && arguments!!.getParcelableArrayList<OptionItem>(Constants.SUB_OPTIONS ) != null ) {
                tvTitleValue.show()
                etTitleValue.hide()
                tvTitleValue.hint = etTitleValue.hint
                tvTitleValue.setOnClickListener {
                    showDropDownForOptions( arguments!!.getParcelableArrayList<OptionItem>(Constants.SUB_OPTIONS))
                }
            }
            else {
                tvTitleValue.hide()
                etTitleValue.show()
            }
            toolbarTitle.text = etTitle.text.toString()
        }

    }

    private fun showDropDownForOptions(optionsList: java.util.ArrayList<OptionItem>?) {

        if( optionsList!!.size == 0 ) {
            return
        }

        val popupView = LayoutInflater.from(context).inflate(R.layout.popup_window_list_layout, null)
        //popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(_context, R.color.white))

        //popupWindow.contentView = popupView
        //popupWindow.isOutsideTouchable = true
        val optionsListView = popupView.findViewById<ListView>(R.id.optionsListView)
        val arrayAdapter = ArrayAdapter(context, R.layout.txt_usd, optionsList)
        optionsListView.adapter = arrayAdapter

        val popupWindow = android.support.v7.app.AlertDialog.Builder(context!!).setView(popupView).create()

        optionsListView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                tvTitleValue.text = optionsList!![p2].itemName
                popupWindow.dismiss()
            }

        }
        popupWindow.show()

        //PopupWindowCompat.showAsDropDown(popupWindow, optionEditText!!, optionEditText.x.toInt(), optionEditText.y.toInt(), Gravity.BOTTOM)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_level3_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryName = arguments!!.getString("categoryName")
        categoryID = arguments!!.getString("categoryId")
        categoryInt = arguments!!.getInt(Constants.CURRENT_BOX)
        combineItem = arguments!!.getParcelable(Constants.COMBINE_ITEMS)
        action = arguments!!.getString("action")
        isEditMode = action == "add" || action == "edit"
        if( action == "add" ) {
            ivEdit.hide()
            ivDelete.hide()
            ivHome.hide()
        }
        else if( action == "edit" ) {
            ivEdit.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_icon_edit_blue))
            ivHome.hide()
        }
        if( arguments!!.containsKey("selectedDocument")) {
            selectedDocument = arguments!!.getParcelable("selectedDocument")
            classType = arguments!!.getString("classType")
            AppLogger.d("Level2", "Selected Document : " + selectedDocument)
        }

        mCategoryPresenter = Level2CategoryPresenter(categoryInt, categoryName, categoryID, selectedDocument, classType, this)

        boxValue = prefrences.currentBox!!

        ivBack.setOnClickListener {
            activity!!.finish()
        }
        ivHome.setOnClickListener {
            val homeIntent = Intent(context, HomeActivity::class.java)
            startActivity(homeIntent)
            activity!!.finishAffinity()
        }
        setTitle()

        //setCamera(boxValue)
        tvSave.hide()
        tvSave.setOnClickListener {
            if( validate() ) {
                context!!.showProgressDialog(getString(R.string.saving_data))
                //On clicking save
                val subTitle = if( etTitleValue.isVisible() ) etTitleValue.text.toString().trim() else tvTitleValue.text.toString()
                mCategoryPresenter.saveDocument( context, combineItem, etTitle.text.toString().trim(), subTitle )
            }
        }
        ivEdit.setOnClickListener {
            if( !isEditMode ) {
                ivEdit.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_icon_edit_blue))
                isEditMode = true
                inflateLayout(level2Categories)
                tvSave.show()
                ivHome.hide()
            }
        }
        ivDelete.setOnClickListener {
            if( isEditMode ) {
                /* arguments!!.putString("action", "delete")
                 (activity!! as ContainerActivity).onLevel3Action(arguments!!)*/
                val builder = AlertDialog.Builder(context)
                builder.setTitle("NineBx")
                builder.setCancelable(false)
                builder.setMessage("Are you sure you want to delete?")
                builder.setPositiveButton("OK") { dialog, p1 ->
                    arguments!!.putString("action", "delete")
                    (activity!! as ContainerActivity).onLevel3Action(arguments!!)
                }
                builder.setNegativeButton("Cancel") { dialog, which -> dialog?.cancel() }
                builder.show()
            }
        }

        if( isEditMode ) tvSave.show()

        /* ivHome.setOnClickListener {
             NineBxApplication.instance.activityInstance!!.callHomeFragment() }*/
    }

    private fun validate(): Boolean {
        if( etTitle.text.toString().isEmpty() ) {
            context!!.showToast(R.string.error_empty_title)
            etTitle.requestFocus()
        }
        return !etTitle.text.toString().isEmpty()
    }

    private fun setTitle() {

        val bundleValue = arguments!!.getString("categoryName")
        // toolbarTitle.text = "Add " + bundleValue

        when (bundleValue) {

        // Common Items in Every
            "Loans/Mortgages"-> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"
            }
            "Banking" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"
            }
            "Investments/Retirement" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"
            }
            "Loan/Mortgages" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"
            }
            "Other financial accounts" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"
            }

        // Payment Methods
            "Credit/Debit cards" -> {
                etTitle.hint = "Card name"
                etTitleValue.hint = ""
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"
            }

            "Other payment accounts" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"
            }

        // Property
            "Primary home (owned)" -> {
                etTitleValue.hint = ""
                etTitle.hint = "Property name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Property"

            }
            "Property (rented for own use)" -> {
                etTitleValue.hint = ""
                etTitle.hint = "Property name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"

            }
            "Vacation home" -> {
                etTitle.hint = "Property name"
                etTitleValue.hint = ""
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"
            }
            "Investment/Rental property" -> {
                etTitle.hint = "Property name"
                etTitleValue.hint = ""
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"
            }

        // Auto
            "Vehicles" -> {
                etTitle.hint = "Vehicle name"
                etTitleValue.hint = "License plate number"
                if( selectedDocument == null ) toolbarTitle.text = "Add Vehicle"
            }

            "Maintenance" -> {
                etTitle.hint = "Maintenance event"
                etTitleValue.hint = "Vehicle"
                if( selectedDocument == null ) toolbarTitle.text = "Add Record"
            }

        // Other Assets
            "Jewelry" -> {
                etTitle.hint = "Asset name"
                etTitleValue.hint = "Description/location"
                if( selectedDocument == null ) toolbarTitle.text = "Add Asset"
            }

            "Art and collectibles" -> {
                etTitle.hint = "Asset name"
                etTitleValue.hint = "Description/location"
                if( selectedDocument == null ) toolbarTitle.text = "Add Asset"
            }

            "Computers and electronics" -> {
                etTitle.hint = "Asset name"
                etTitleValue.hint = "Description/location"
                if( selectedDocument == null ) toolbarTitle.text = "Add Asset"
            }

            "Furniture" -> {
                etTitle.hint = "Asset name"
                etTitleValue.hint = "Description/location"
                if( selectedDocument == null ) toolbarTitle.text = "Add Asset"
            }

            "Others" -> {
                etTitle.hint = "Asset name"
                etTitleValue.hint = "Description/location"
                if( selectedDocument == null ) toolbarTitle.text = "Add Asset"
            }

        // Insurance
            "Homeowners/Renters insurance" -> {
                etTitle.hint = "Insurance company"
                etTitleValue.hint = "Insured property"
                if( selectedDocument == null ) toolbarTitle.text = "Add Policy"
            }

            "Auto insurance" -> {
                etTitle.hint = "Insurance company"
                etTitleValue.hint = "Insured vehicle"
                if( selectedDocument == null ) toolbarTitle.text = "Add Policy"
            }

            "Life insurance" -> {
                etTitle.hint = "Insurance company"
                etTitleValue.hint = "Insured person"
                if( selectedDocument == null ) toolbarTitle.text = "Add Policy"
            }

            "Health insurance" -> {
                etTitle.hint = "Insurance company"
                etTitleValue.hint = "Insured person"
                if( selectedDocument == null ) toolbarTitle.text = "Add Policy"
            }

            "Umbrella insurance" -> {
                etTitle.hint = "Insurance company"
                etTitleValue.hint = "Insured person"
                if( selectedDocument == null ) toolbarTitle.text = "Add Policy"
            }

        // Taxes

            "Past returns" -> {
                etTitle.hint = "Return name"
                etTitleValue.hint = ""
                if( selectedDocument == null ) toolbarTitle.text = "Add Return"
            }

            "Returns to be filed" -> {
                etTitle.hint = "Return name"
                etTitleValue.hint = ""
                if( selectedDocument == null ) toolbarTitle.text = "Add Return"
            }

        // Travel
            "Airline" -> {
                etTitle.hint = "Airline"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"
            }

            "Hotel" -> {
                etTitle.hint = "Hotel"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"

            }

            "Car Rental" -> {
                etTitle.hint = "Car Rental Company"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"

            }

            "Cruiseline" -> {
                etTitle.hint = "Cruiseline"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"

            }

            "Railway" -> {
                etTitle.hint = "Railway"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"

            }

            "Other" -> {
                etTitle.hint = "Other"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"

            }

            "Passport" -> {
                etTitle.hint = "Passport name"
                etTitleValue.hint = ""
                etTitleValue.setEnabled(false)
                if( selectedDocument == null ) toolbarTitle.text = "Add Passport"
            }

            "Visa" -> {
                etTitle.hint = "Visa name"
                etTitleValue.hint = ""
                etTitleValue.setEnabled(false)
                if( selectedDocument == null ) toolbarTitle.text = "Add Visa"
            }

            "Other travel document" -> {
                etTitle.hint = "Travel document title"
                etTitleValue.hint = ""
                etTitleValue.setEnabled(false)
                if( selectedDocument == null ) toolbarTitle.text = "Add Document"

            }

            "Travel Dates And Plans" -> {
                etTitle.hint = "Description"
                etTitleValue.hint = ""
                etTitleValue.setEnabled(false)
                if( selectedDocument == null ) toolbarTitle.text = "Add Travel Plan"

            }

            "Services/Other Accounts" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"
            }

            "Other Attachments" -> {
                etTitle.hint = "Title"
                etTitleValue.hint = ""
                if( selectedDocument == null ) toolbarTitle.text = "Add Attachment"
            }

        // Personal
            "Drivers License" -> {
                etTitle.hint = "Description"
                etTitleValue.hint = ""
                etTitleValue.setEnabled(false)

                if( selectedDocument == null ) toolbarTitle.text = "Add License"
            }
            "Social Security Card" -> {
                etTitle.hint = "Card name"
                etTitleValue.hint = ""
                etTitleValue.setEnabled(false)

                if( selectedDocument == null ) toolbarTitle.text = "Add Card"
            }
            "Tax ID" -> {
                etTitle.hint = "Tax ID name"
                etTitleValue.hint = ""
                etTitleValue.setEnabled(false)

                if( selectedDocument == null ) toolbarTitle.text = "Add ID"
            }
            "Birth Certificate" -> {
                etTitle.hint = "Description"
                etTitleValue.hint = ""
                etTitleValue.isEnabled = false

                if( selectedDocument == null ) toolbarTitle.text = "Add Certificate"
            }
            "Marriage Certificate" -> {
                etTitle.hint = "Description"
                etTitleValue.hint = ""
                etTitleValue.isEnabled = false

                if( selectedDocument == null ) toolbarTitle.text = "Add Certificate"
            }
            "Other Government-Issued ID" -> {
                etTitle.hint = "ID name"
                etTitleValue.hint = ""
                etTitleValue.setEnabled(false)

                if( selectedDocument == null ) toolbarTitle.text = "Add ID"
            }
        //work and education
            "Add Person" -> {
                etTitle.hint = "Institution name"
                etTitleValue.hint = "Qualification/degree"
                if( selectedDocument == null ) toolbarTitle.text = "Add Institution"
            }
            "Add person" -> {
                etTitle.hint = "Company name"
                etTitleValue.hint = "Position"
                if( selectedDocument == null ) toolbarTitle.text = "Add Company"
            }

            "Loyalty Programs" -> {
                etTitle.hint = "Brand/Retailer"
                etTitleValue.hint = "Account name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Account"

            }

            "Recent Purchases" -> {
                etTitleValue.hint = "Brand/Retailer"
                etTitle.hint = "Item"
                if( selectedDocument == null ) toolbarTitle.text = "Add Purchase"
            }

            "Womens sizes" -> {
                etTitle.hint = "User name"
                etTitleValue.hint = "Size name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Women's sizes"
            }

            "Mens sizes" -> {
                etTitle.hint = "User name"
                etTitleValue.hint = "Size name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Mens's sizes"
            }

            "Girls sizes" -> {
                etTitle.hint = "User name"
                etTitleValue.hint = "Size name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Girls's sizes"
            }
            "Boy's sizes" -> {
                etTitle.hint = "User name"
                etTitleValue.hint = "Size name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Boy's sizes"
            }

            "Baby's sizes" -> {
                etTitle.hint = "User name"
                etTitleValue.hint = "Size name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Baby's sizes"
            }

            "Identification" -> {
                etTitle.hint = "User name"
                etTitleValue.hint = ""
                if( selectedDocument == null ) toolbarTitle.text = "Add Identification"
            }

            "Medical history" -> {
                etTitle.hint = "History"
                etTitleValue.hint = ""
                if( selectedDocument == null ) toolbarTitle.text = "Add History"
            }

            "Healthcare providers" -> {
                etTitle.hint = "Name"
                etTitleValue.hint = "Type of physician"
                if( selectedDocument == null ) toolbarTitle.text = "Add Providers"
            }

            "Emergency contacts" -> {

            }

            "Medications" -> {
                etTitle.hint = "Name"
                etTitleValue.hint = "Dose/strength"
                if( selectedDocument == null ) toolbarTitle.text = "Add Medications"
            }

            "Medical conditions/Allergies" -> {
                etTitle.hint = "Condition"
                etTitleValue.hint = "Date diagnosed"
                if( selectedDocument == null ) toolbarTitle.text = "Add Conditions"
            }

            "Eyeglass prescriptions" -> {
                etTitle.hint = "Physician name"
                etTitleValue.hint = "Date prescribed"
                if( selectedDocument == null ) toolbarTitle.text = "Add Prescription"
            }

            "Vital numbers" -> {
                etTitle.hint = "Description"
                etTitleValue.hint = "Measurement date"
                if( selectedDocument == null ) toolbarTitle.text = "Add Record"
            }

            "Checkups and visits" -> {
                etTitle.hint = "Description"
                etTitleValue.hint = "Physician name"
                if( selectedDocument == null ) toolbarTitle.text = "Add Visit"
            }
        }

        if( arguments!!.containsKey(Constants.SUB_OPTIONS) && arguments!!.getParcelableArrayList<OptionItem>(Constants.SUB_OPTIONS ) != null ) {
            tvTitleValue.show()
            etTitleValue.hide()
            tvTitleValue.hint = etTitleValue.hint
            tvTitleValue.setOnClickListener {
                showDropDownForOptions( arguments!!.getParcelableArrayList<OptionItem>(Constants.SUB_OPTIONS))
            }
        }
        else {
            tvTitleValue.hide()
            etTitleValue.show()
        }
    }

    override fun onBackPressed(): Boolean {
        activity!!.finish()
        return true
    }
}
