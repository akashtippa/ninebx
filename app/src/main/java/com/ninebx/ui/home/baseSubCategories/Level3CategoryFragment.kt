package com.ninebx.ui.home.baseSubCategories

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.showProgressDialog
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.utility.*
import kotlinx.android.synthetic.main.fragment_level3_category.*

/***
 * Created by TechnoBlogger on 23/01/18.
 */

class Level3CategoryFragment : FragmentBackHelper(), Level2CategoryView {
    override fun savedToRealm() {
        if( context != null ) {
            context!!.hideProgressDialog()
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }
    }

    override fun setValueToDocument(level2Category: Level2SubCategory) {
        mCategoryPresenter.setValueToDocument(level2Category)
    }

    override fun saveDocument(context: Context?) {
        mCategoryPresenter.saveDocument(context, combineItem, etTitle.text.toString().trim(), etTitleValue.text.toString().trim())
    }

    private lateinit var mCategoryPresenter: Level2CategoryPresenter
    private val adapterExpandable: ExpandableListViewAdapter? = null

    private var strTitle = ""
    private var strSubTitle = ""

    private var boxValue = ""
    val prefrences = NineBxPreferences()

    private var categoryName = ""
    private var categoryID = ""
    private var classType = ""
    private var selectedDocument : Parcelable ?= null
    private var combineItem : Parcelable ?= null

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

        layExpandable.setAdapter(ExpandableListViewAdapter( context!!, categories, this, categoryName, classType,
                ArrayList(NineBxApplication.instance.activityInstance!!.getCurrentUsers()[0].members) ))

        if( selectedDocument != null ) {
            AppLogger.d("Level2Category", "Selected Document : " + selectedDocument)
            when( selectedDocument ) {
            //Home&Banking
                is DecryptedFinancial -> {
                    val decryptedFinancial : DecryptedFinancial = selectedDocument as DecryptedFinancial
                    etTitle.setText(decryptedFinancial.institutionName)
                    etTitleValue.setText(decryptedFinancial.accountName)
                    AppLogger.d("AccountName" , " " + decryptedFinancial.accountName)
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                    Log.d("AccountName",decryptedFinancial.accountName)
                    Log.d("Modified",decryptedFinancial.modified)

                }
                is DecryptedPayment -> {
                    val decryptedFinancial : DecryptedPayment = selectedDocument as DecryptedPayment
                    etTitle.setText(decryptedFinancial.cardName)
                    etTitleValue.setText(decryptedFinancial.userName)
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedProperty-> {
                    val decryptedFinancial : DecryptedProperty = selectedDocument as DecryptedProperty
                    etTitle.setText(decryptedFinancial.titleName)
                    etTitleValue.setText(decryptedFinancial.propertyName)
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedVehicle-> {
                    val decryptedFinancial : DecryptedVehicle = selectedDocument as DecryptedVehicle
                    etTitle.setText(decryptedFinancial.titleName)
                    etTitleValue.setText(decryptedFinancial.vehicleName)
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedAsset-> {
                    val decryptedFinancial : DecryptedAsset = selectedDocument as DecryptedAsset
                    etTitle.setText(decryptedFinancial.assetName)
                    etTitleValue.setText(decryptedFinancial.assetName)
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedInsurance-> {
                    val decryptedFinancial : DecryptedInsurance = selectedDocument as DecryptedInsurance
                    etTitle.setText(decryptedFinancial.insuranceCompany)
                    etTitleValue.setText(decryptedFinancial.insuredVehicle)
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedTax-> {
                    val decryptedFinancial : DecryptedTax = selectedDocument as DecryptedTax
                    etTitle.setText(decryptedFinancial.title)
                    etTitleValue.setText(decryptedFinancial.taxPayer)
                    modifiedValue.setText(decryptedFinancial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
            //Personal
                is DecryptedCertificate -> {
                    val decryptedCertificate : DecryptedCertificate = selectedDocument as DecryptedCertificate
                    etTitle.setText(decryptedCertificate.nameOnCertificate)
                    etTitleValue.setText(decryptedCertificate.nameOnCertificate)
                    modifiedValue.setText(decryptedCertificate.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedGovernment -> {
                    val decryptedGovernment : DecryptedGovernment = selectedDocument as DecryptedGovernment
                    etTitle.setText(decryptedGovernment.idName)
                    etTitleValue.setText(decryptedGovernment.nameOnId)
                    modifiedValue.setText(decryptedGovernment.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedLicense ->{
                    val decryptedLicense : DecryptedLicense = selectedDocument as DecryptedLicense
                    etTitle.setText(decryptedLicense.nameOnLicense)
                    etTitleValue.setText(decryptedLicense.lic_description)
                    modifiedValue.setText(decryptedLicense.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedPersonal -> {
                    val decryptedPersonal : DecryptedPersonal = selectedDocument as DecryptedPersonal
                    etTitle.setText(decryptedPersonal.userName)
                    etTitleValue.setText(decryptedPersonal.nameOnAccount)
                    modifiedValue.setText(decryptedPersonal.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedSocial -> {
                    val decryptedSocial : DecryptedSocial = selectedDocument as DecryptedSocial
                    etTitle.setText(decryptedSocial.cardName)
                    etTitleValue.setText(decryptedSocial.cardName)
                    modifiedValue.setText(decryptedSocial.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedTaxID -> {
                    val decryptedTaxID : DecryptedTaxID = selectedDocument as DecryptedTaxID
                    etTitle.setText(decryptedTaxID.taxIdName)
                    etTitleValue.setText(decryptedTaxID.taxIdName)
                    modifiedValue.setText(decryptedTaxID.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
            //Wellness
                is DecryptedIdentification -> {
                    val decryptedIdentification : DecryptedIdentification = selectedDocument as DecryptedIdentification
                    etTitle.setText(decryptedIdentification.name)
                    etTitleValue.setText(decryptedIdentification.name)
                    modifiedValue.setText(decryptedIdentification.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedMedicalHistory -> {
                    val decryptedMedicalHistory : DecryptedMedicalHistory = selectedDocument as DecryptedMedicalHistory
                    etTitle.setText(decryptedMedicalHistory.history)
                    modifiedValue.setText(decryptedMedicalHistory.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedCheckups -> {
                    val decryptedCheckups : DecryptedCheckups = selectedDocument as DecryptedCheckups
                    etTitle.setText(decryptedCheckups.checkup_description)
                    etTitleValue.setText(decryptedCheckups.physicianName)
                    modifiedValue.setText(decryptedCheckups.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedEmergencyContacts -> {
                    val decryptedEmergencyContacts : DecryptedEmergencyContacts = selectedDocument as DecryptedEmergencyContacts
                    modifiedValue.setText(decryptedEmergencyContacts.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedEyeglassPrescriptions -> {
                    val decryptedEyeglassPrescriptions : DecryptedEyeglassPrescriptions = selectedDocument as DecryptedEyeglassPrescriptions
                    etTitle.setText(decryptedEyeglassPrescriptions.physicianName)
                    etTitleValue.setText(decryptedEyeglassPrescriptions.datePrescribed)
                    modifiedValue.setText(decryptedEyeglassPrescriptions.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedHealthcareProviders -> {
                    val decryptedHealthcareProviders : DecryptedHealthcareProviders= selectedDocument as DecryptedHealthcareProviders
                    etTitle.setText(decryptedHealthcareProviders.name)
                    etTitleValue.setText(decryptedHealthcareProviders.physicianType)
                    modifiedValue.setText(decryptedHealthcareProviders.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedMedicalConditions -> {
                    val decryptedMedicalCOnditions : DecryptedMedicalConditions= selectedDocument as DecryptedMedicalConditions
                    etTitle.setText(decryptedMedicalCOnditions.condition)
                    etTitleValue.setText(decryptedMedicalCOnditions.dateDiagnosed)
                    modifiedValue.setText(decryptedMedicalCOnditions.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedMedications ->{
                    val decryptedMedications : DecryptedMedications= selectedDocument as DecryptedMedications
                    etTitle.setText(decryptedMedications.name)
                    etTitleValue.setText(decryptedMedications.strength)
                    modifiedValue.setText(decryptedMedications.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedVitalNumbers -> {
                    val decryptedVitalNumbers : DecryptedVitalNumbers= selectedDocument as DecryptedVitalNumbers
                    etTitle.setText(decryptedVitalNumbers.vital_description)
                    etTitleValue.setText(decryptedVitalNumbers.measurementDate)
                    modifiedValue.setText(decryptedVitalNumbers.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
            //Travel
                is DecryptedDocuments -> {
                    val decryptedDocuments : DecryptedDocuments = selectedDocument as DecryptedDocuments
                    when(decryptedDocuments.selectionType){
                        "travel_2001" -> {
                            etTitle.setText(decryptedDocuments.passportName)
                        }
                        "travel_2002"->{
                            etTitle.setText(decryptedDocuments.visaName)
                        }
                        "travel_2003" -> {
                            etTitle.setText(decryptedDocuments.travelDocumentTitle)
                        }
                    }
                    modifiedValue.setText(decryptedDocuments.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedLoyalty -> {
                    val decryptedLoyalty : DecryptedLoyalty = selectedDocument as DecryptedLoyalty
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
                    /*etTitleValue.setText(decryptedLoyalty.nameOnAccount)*/
                }
                is DecryptedTravel -> {
                    val decryptedTravel : DecryptedTravel = selectedDocument as DecryptedTravel
                    etTitle.setText(decryptedTravel.userName)
                    etTitleValue.setText(decryptedTravel.nameOnAccount)
                    modifiedValue.setText(decryptedTravel.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedVacations -> {
                    val decryptedVacations : DecryptedVacations = selectedDocument as DecryptedVacations
                    etTitle.setText(decryptedVacations.vac_description)
                    etTitleValue.setText(decryptedVacations.vac_description)
                    modifiedValue.setText(decryptedVacations.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }

            //Shopping
                is DecryptedLoyaltyPrograms -> {
                    val decryptedLoyaltyPrograms : DecryptedLoyaltyPrograms = selectedDocument as DecryptedLoyaltyPrograms
                    etTitle.setText(decryptedLoyaltyPrograms.brandName)
                    etTitleValue.setText(decryptedLoyaltyPrograms.accountName)
                    modifiedValue.setText(decryptedLoyaltyPrograms.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedRecentPurchase -> {
                    val decryptedRecentPurchase : DecryptedRecentPurchase = selectedDocument as DecryptedRecentPurchase
                    etTitle.setText(decryptedRecentPurchase.itemName)
                    etTitleValue.setText(decryptedRecentPurchase.brandName)
                    modifiedValue.setText(decryptedRecentPurchase.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedClothingSizes -> {
                    val decryptedClothingSizes : DecryptedClothingSizes = selectedDocument as DecryptedClothingSizes
                    etTitle.setText(decryptedClothingSizes.personName)
                    etTitleValue.setText(decryptedClothingSizes.sizeName)
                    modifiedValue.setText(decryptedClothingSizes.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
                is DecryptedShopping -> {
                    val decryptedShopping : DecryptedShopping = selectedDocument as DecryptedShopping
                    etTitle.setText(decryptedShopping.institutionName)
                    etTitle.setText(decryptedShopping.accountName)
                    modifiedValue.setText(decryptedShopping.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
            //Interests
                is DecryptedInterests -> {
                    val decryptedInterests : DecryptedInterests = selectedDocument as DecryptedInterests
                    etTitle.setText(decryptedInterests.institutionName)
                    etTitleValue.setText(decryptedInterests.accountName)
                    modifiedValue.setText(decryptedInterests.modified)
                    modifiedValue.setTypeface(null,Typeface.ITALIC)
                }
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_level3_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryName = arguments!!.getString("categoryName")
        categoryID = arguments!!.getString("categoryId")
        combineItem = arguments!!.getParcelable(Constants.COMBINE_ITEMS)

        if( arguments!!.containsKey("selectedDocument")) {
            selectedDocument = arguments!!.getParcelable("selectedDocument")
            classType = arguments!!.getString("classType")
            AppLogger.d("Level2", "Selected Document : " + selectedDocument)
        }

        mCategoryPresenter = Level2CategoryPresenter(categoryName, categoryID, selectedDocument, classType, this)

        NineBxApplication.instance.activityInstance!!.hideBottomView()

        NineBxApplication.instance.activityInstance!!.showQuickAddDisableText()

        boxValue = prefrences.currentBox!!

        ivBack.setOnClickListener {
            NineBxApplication.instance.activityInstance!!.onBackPressed()
        }

        setTitle()

        setCamera(boxValue)
        tvSave.setOnClickListener {
            if( validate() ) {
                context!!.showProgressDialog(getString(R.string.saving_data))
                //On clicking save
                mCategoryPresenter.saveDocument( context, combineItem, etTitle.text.toString().trim(), etTitleValue.text.toString().trim()  )
            }
        }
    }

    private fun validate(): Boolean {
        if( etTitle.text.toString().isEmpty() ) {
            context!!.showToast(R.string.error_empty_title)
            etTitle.requestFocus()
        }
        return !etTitle.text.toString().isEmpty()
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

        NineBxApplication.instance.activityInstance!!.hideQuickAdd()

        KeyboardUtil.hideSoftKeyboard(NineBxApplication.instance.activityInstance!!)
        return super.onBackPressed()
    }
}