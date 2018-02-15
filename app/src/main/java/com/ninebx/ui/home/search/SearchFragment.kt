package com.ninebx.ui.home.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R

import com.ninebx.ui.home.BaseHomeFragment
import com.ninebx.utility.AppLogger
import kotlinx.android.synthetic.main.fragment_search.*
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.SearchItemClickListener
import com.ninebx.ui.base.realm.decrypted.*


/**
 * Created by Alok on 03/01/18.
 */

class SearchFragment() : BaseHomeFragment(), SearchView {

    private var mDecryptCombine : DecryptedCombine ?= null

    override fun onCombineFetched(combine: DecryptedCombine) {
        this.mDecryptCombine = combine
    }
    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {

    }

    override fun onError(error: Int) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_search, container, false)
    }

    private lateinit var searchDecryptCombine: DecryptedCombine

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideAllLayouts()

        SearchPresenter(this)

        edtSearch.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                searchDecryptCombine = DecryptedCombine()
                var text = edtSearch.getText().toString()

                var searchFinanceItems = ArrayList<DecryptedFinancial>()
                var searchPaymentItems = ArrayList<DecryptedPayment>()
                var searchPropertyItems = ArrayList<DecryptedProperty>()
                var searchVehicleItems = ArrayList<DecryptedVehicle>()
                var searchAssetItems = ArrayList<DecryptedAsset>()
                var searchInsuranceItems = ArrayList<DecryptedInsurance>()
                var searchTaxItems = ArrayList<DecryptedTax>()
                var searchHomeList = ArrayList<DecryptedHomeList>()

                for( financeItems in mDecryptCombine!!.financialItems ) {
                    if( financeItems.selectionType.contains(text) || financeItems.institutionName.contains(text) || financeItems.accountName.contains(text) ||
                            financeItems.accountType.contains(text) || financeItems.nameOnAccount.contains(text) || financeItems.accountNumber.contains(text) ||
                            financeItems.location.contains(text) || financeItems.swiftCode.contains(text) || financeItems.abaRoutingNumber.contains(text) ||
                            financeItems.contacts.contains(text) || financeItems.website.contains(text) || financeItems.userName.contains(text) ||
                            financeItems.password.contains(text) ||  financeItems.pin.contains(text) || financeItems.created.contains(text) ||
                            financeItems.modified.contains(text) || financeItems.createdUser.contains(text) || financeItems.notes.contains(text) 
                            || financeItems.attachmentNames.contains(text))

                        searchFinanceItems.add(financeItems)
                }

                searchDecryptCombine.financialItems.addAll(searchFinanceItems)
                AppLogger.d("Search", "SearchItems : " + searchFinanceItems)
                AppLogger.d("Search", "DecryptedCombine : " + searchDecryptCombine)

                for( paymentItems in mDecryptCombine!!.paymentItems){

                    if (paymentItems.selectionType.contains(text) || paymentItems.insuranceCompany.contains(text) || paymentItems.insuredProperty.contains(text) ||
                            paymentItems.insuredVehicle.contains(text) || paymentItems.insuredPerson.contains(text) || paymentItems.policyNumber.contains(text) ||
                            paymentItems.policyEffectiveDate.contains(text) || paymentItems.policyExpirationDate.contains(text) || paymentItems.contacts.contains(text) ||
                            paymentItems.website.contains(text) || paymentItems.userName.contains(text) || paymentItems.password.contains(text) ||
                            paymentItems.pin.contains(text) || paymentItems.created.contains(text) || paymentItems.modified.contains(text) ||
                            paymentItems.createdUser.contains(text) || paymentItems.notes.contains(text) || paymentItems.attachmentNames.contains(text))


                        searchPaymentItems.add(paymentItems)
                }

                searchDecryptCombine.paymentItems.addAll(searchPaymentItems)
                AppLogger.d("Search", "SearchPayment : " + searchPaymentItems)

                for( propertyItems in mDecryptCombine!!.propertyItems) {
                    if(propertyItems.selectionType.contains(text) || propertyItems.propertyName.contains(text) || propertyItems.streetAddressOne.contains(text) ||
                            propertyItems.streetAddressTwo.contains(text) || propertyItems.city.contains(text) || propertyItems.state.contains(text) ||
                            propertyItems.zipCode.contains(text) || propertyItems.country.contains(text) || propertyItems.titleName.contains(text) ||
                            propertyItems.purchaseDate.contains(text) || propertyItems.purchasePrice.contains(text) || propertyItems.estimatedMarketValue.contains(text) ||
                            propertyItems.contacts.contains(text) || propertyItems.selectionType.contains(text) || propertyItems.tenantName.contains(text) ||
                            propertyItems.leaseEndDate.contains(text) || propertyItems.leaseStartDate.contains(text) || propertyItems.created.contains(text) ||
                            propertyItems.modified.contains(text) || propertyItems.createdUser.contains(text) || propertyItems.notes.contains(text) || propertyItems.attachmentNames.contains(text))

                        searchPropertyItems.add(propertyItems)
                }

                searchDecryptCombine.propertyItems.addAll(searchPropertyItems)
                AppLogger.d("Search", "SearchProperty : " + searchPropertyItems)

                for(vehicleItems in mDecryptCombine!!.vehicleItems){
                    if(vehicleItems.selectionType.contains(text) || vehicleItems.vehicleName.contains(text) || vehicleItems.licenseNumber.contains(text) ||
                            vehicleItems.vinNumber.contains(text) || vehicleItems.make.contains(text) || vehicleItems.model.contains(text) || vehicleItems.modelYear.contains(text) ||
                            vehicleItems.color.contains(text) || vehicleItems.titleName.contains(text) || vehicleItems.estimatedMarketValue.contains(text) || vehicleItems.registrationExpirydate.contains(text) ||
                            vehicleItems.purchasedOrLeased.contains(text) || vehicleItems.purchaseDate.contains(text) || vehicleItems.financedThroughLoan.contains(text) ||
                            vehicleItems.created.contains(text) || vehicleItems.modified.contains(text) || vehicleItems.createdUser.contains(text) || vehicleItems.leaseStartDate.contains(text) ||
                            vehicleItems.leaseEndDate.contains(text) || vehicleItems.contacts.contains(text) || vehicleItems.maintenanceEvent.contains(text) || vehicleItems.serviceProviderName.contains(text) ||
                            vehicleItems.dateOfService.contains(text) || vehicleItems.vehicle.contains(text) || vehicleItems.notes.contains(text) || vehicleItems.attachmentNames.contains(text) )

                        searchVehicleItems.add(vehicleItems)
                }
                searchDecryptCombine.vehicleItems.addAll(searchVehicleItems)
                AppLogger.d("Search", "SearchVehicle" + searchVehicleItems)

                for(assetItems in mDecryptCombine!!.assetItems)
                {
                    if(assetItems.selectionType.contains(text) || assetItems.test.contains(text) || assetItems.assetName.contains(text) || assetItems.descriptionOrLocation.contains(text)
                            || assetItems.estimatedMarketValue.contains(text) || assetItems.serialNumber.contains(text) || assetItems.purchaseDate.contains(text)
                            || assetItems.purchasePrice.contains(text) || assetItems.contacts.contains(text) || assetItems.created.contains(text) || assetItems.modified.contains(text)
                            || assetItems.createdUser.contains(text) || assetItems.notes.contains(text) || assetItems.imageName.contains(text) || assetItems.attachmentNames.contains(text))

                        searchAssetItems.add(assetItems)
                }
                searchDecryptCombine.assetItems.addAll(searchAssetItems)
                AppLogger.d("Search", "SearchAsset" + searchAssetItems)

                for(insuranceItems in mDecryptCombine!!.insuranceItems){
                    if(insuranceItems.selectionType.contains(text) || insuranceItems.insuranceCompany.contains(text) || insuranceItems.insuredProperty.contains(text) || insuranceItems.insuredVehicle.contains(text)
                            || insuranceItems.insuredPerson.contains(text) || insuranceItems.policyNumber.contains(text) ||  insuranceItems.policyEffectiveDate.contains(text) || insuranceItems.policyExpirationDate.contains(text)
                            || insuranceItems.contacts.contains(text) || insuranceItems.website.contains(text) || insuranceItems.userName.contains(text) || insuranceItems.password.contains(text) || insuranceItems.pin.contains(text)
                            || insuranceItems.created.contains(text) || insuranceItems.modified.contains(text)|| insuranceItems.createdUser.contains(text) || insuranceItems.notes.contains(text) || insuranceItems.attachmentNames.contains(text))

                        searchInsuranceItems.add(insuranceItems)
                }
                searchDecryptCombine.insuranceItems.addAll(searchInsuranceItems)
                AppLogger.d("Search", "SearchInsurance" + searchInsuranceItems)

                for (taxItems in mDecryptCombine!!.taxesItems)
                {
                    if (taxItems.selectionType.contains(text) || taxItems.returnName.contains(text) || taxItems.taxYear.contains(text) || taxItems.taxPayer.contains(text) || taxItems.contacts.contains(text)
                            || taxItems.imageName.contains(text) || taxItems.attachmentNames.contains(text) || taxItems.notes.contains(text) || taxItems.title.contains(text) || taxItems.created.contains(text)
                            || taxItems.modified.contains(text) || taxItems.createdUser.contains(text))

                        searchTaxItems.add(taxItems)
                }
                searchDecryptCombine.taxesItems.addAll(searchTaxItems)
                AppLogger.d("Search", "SearchTax" + searchTaxItems)

                for(listItems in mDecryptCombine!!.listItems){
                    if (listItems.selectionType.contains(text) || listItems.classType.contains(text) || listItems.listName.contains(text) || listItems.dueDate.contains(text)
                            || listItems.created.contains(text) || listItems.modified.contains(text) || listItems.createdUser.contains(text))

                        searchHomeList.add(listItems)
                }
                searchDecryptCombine.listItems.addAll(searchHomeList)
                AppLogger.d("Search", "SearchHomeList" + searchHomeList)

                setAdapter()



            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun hideAllLayouts() {
        homeLayout.hide()
        travelLayout.hide()
        contactsLayout.hide()
        educationLayout.hide()
        personalLayout.hide()
        interestsLayout.hide()
        wellnessLayout.hide()
        memoriesLayout.hide()
        shoppingLayout.hide()
    }

    var mSearchTravelItems = ArrayList<Level3SearchItem>()
    var mSearchContactsItems = ArrayList<Level3SearchItem>()
    var mSearchEducationItems = ArrayList<Level3SearchItem>()
    var mSearchPersonalItems = ArrayList<Level3SearchItem>()
    var mSearchInterestsItems = ArrayList<Level3SearchItem>()
    var mSearchWellnessItems = ArrayList<Level3SearchItem>()
    var mSearchMemoriesItems = ArrayList<Level3SearchItem>()
    var mSearchShoppingItems = ArrayList<Level3SearchItem>()
    var mSearchHomeList = ArrayList<Level3SearchItem>()

    private fun setAdapter() {

        hideAllLayouts()

        mSearchTravelItems.clear()
        mSearchContactsItems.clear()
        mSearchEducationItems.clear()
        mSearchPersonalItems.clear()
        mSearchInterestsItems.clear()
        mSearchWellnessItems.clear()
        mSearchMemoriesItems.clear()
        mSearchHomeList.clear()
        mSearchShoppingItems.clear()

        setupHomeItems()
        setupTravelItems()
        setupContactsItems()
        setupEducationItems()
        setupPersonalItems()
        setupInterestsItems()
        setupWellnessItems()
        setupMemoriesItems()
        setupShoppingItems()

        //TODO - Do for all boxes

    }

    private fun setupTravelItems() {
        //TODO
    }

    private fun setupContactsItems() {
        //TODO
    }

    private fun setupEducationItems() {
        //TODO
    }

    private fun setupPersonalItems() {
        //TODO
    }

    private fun setupInterestsItems() {
        //TODO
    }

    private fun setupWellnessItems() {
        //TODO
    }

    private fun setupMemoriesItems() {
        //TODO
    }

    private fun setupShoppingItems() {
        //TODO
    }

    private fun setupHomeItems() {
        for( finance in searchDecryptCombine.financialItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  finance.accountName, "finance" ))
        }
        for( payment in searchDecryptCombine.paymentItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  payment.userName, "payment" ))
        }
        for( asset in searchDecryptCombine.assetItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  asset.assetName, "asset" ))
        }
        for( insurance in searchDecryptCombine.insuranceItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  insurance.insuranceCompany, "insurance" ))
        }
        for( tax in searchDecryptCombine.taxesItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  tax.taxPayer, "tax" ))
        }
        for( vehicle in searchDecryptCombine.vehicleItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  vehicle.titleName, "vehicle" ))
        }
        for( property in searchDecryptCombine.propertyItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  property.propertyName, "property" ))
        }
        for( home in searchDecryptCombine.listItems ) {
            mSearchHomeList.add(Level3SearchItem( R.string.home_amp_money,  home.listName, "home" ))
        }
        if( mSearchHomeList.size > 0 ) //Pass the right recyclerview and layout to be shown with searchlist to be populated
            setupAdapter( rvHomeMoney, homeLayout, mSearchHomeList )
    }

    private fun setupAdapter( searchRecyclerView: RecyclerView?, layout: LinearLayout, searchList: ArrayList<Level3SearchItem>) {

        searchRecyclerView!!.layoutManager = LinearLayoutManager(context)
        searchRecyclerView.adapter = SearchAdapter( searchList, object : SearchItemClickListener {
            override fun onItemClick(position: Int, searchItem: Level3SearchItem) {
                when( searchItem.searchCategory ) {
                    R.string.home_amp_money -> {
                        switchHomeItems( position, searchItem )
                    }
                    (R.string.travel) -> {
                        switchTravelItems( position, searchItem )
                    }
                    (R.string.contacts) -> {
                        switchContactsItems( position, searchItem )
                    }
                    (R.string.education_work) -> {
                        switchEducationItems( position, searchItem )
                    }
                    (R.string.personal) -> {
                        switchPersonalItems( position, searchItem )
                    }
                    (R.string.interests) -> {
                        switchInterestsItems( position, searchItem )
                    }
                    (R.string.wellness) -> {
                        switchWellnessItems( position, searchItem )
                    }
                    (R.string.memories) -> {
                        switchMemoriesItems( position, searchItem )
                    }
                    (R.string.shopping) -> {
                        switchShoppingItems( position, searchItem )
                    }
                }
            }
        })
        layout.show()

    }

    private fun switchShoppingItems(position: Int, searchItem: Level3SearchItem) {
        //TODO
    }

    private fun switchMemoriesItems(position: Int, searchItem: Level3SearchItem) {
        //TODO
    }

    private fun switchWellnessItems(position: Int, searchItem: Level3SearchItem) {
        //TODO
    }

    private fun switchInterestsItems(position: Int, searchItem: Level3SearchItem) {
        //TODO
    }

    private fun switchPersonalItems(position: Int, searchItem: Level3SearchItem) {
        //TODO
    }

    private fun switchEducationItems(position: Int, searchItem: Level3SearchItem) {
        //TODO
    }

    private fun switchContactsItems(position: Int, searchItem: Level3SearchItem) {
        //TODO
    }

    private fun switchTravelItems(position: Int, searchItem: Level3SearchItem) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun switchHomeItems(position: Int, searchItem: Level3SearchItem) {
        when( searchItem.categoryName ) {
            "finance" -> {
                val selectedDocument = searchDecryptCombine.financialItems[position]
            }
            "payment" -> {
                val selectedDocument = searchDecryptCombine.paymentItems[position]
            }
            "asset" -> {
                val selectedDocument = searchDecryptCombine.assetItems[position]
            }
            "insurance" -> {
                val selectedDocument = searchDecryptCombine.insuranceItems[position]
            }
            "tax" -> {
                val selectedDocument = searchDecryptCombine.taxesItems[position]
            }
            "vehicle" -> {
                val selectedDocument = searchDecryptCombine.vehicleItems[position]
            }
            "property" -> {
                val selectedDocument = searchDecryptCombine.propertyItems[position]
            }
            "home" -> {
                val selectedDocument = searchDecryptCombine.listItems[position]
            }
        }
    }


}

