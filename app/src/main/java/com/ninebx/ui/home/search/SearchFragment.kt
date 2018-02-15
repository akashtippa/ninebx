package com.ninebx.ui.home.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
import com.ninebx.ui.base.realm.decrypted.*
import io.realm.RealmList


/**
 * Created by Alok on 03/01/18.
 */

class SearchFragment() : BaseHomeFragment(), SearchView {

    private lateinit var finance : RealmList<DecryptedFinancial>
    private lateinit var pay : RealmList<DecryptedPayment>
    private lateinit var property : RealmList<DecryptedProperty>
    private lateinit var vehicle : RealmList<DecryptedVehicle>
    private lateinit var asset : RealmList<DecryptedAsset>
    private lateinit var insurance : RealmList<DecryptedInsurance>
    private lateinit var tax : RealmList<DecryptedTax>
    private lateinit var homelist : RealmList<DecryptedHomeList>


    override fun onCombineFetched(combine: DecryptedCombine) {

        if(combine.financialItems.size > 0)
            finance = combine.financialItems

        if(combine.paymentItems.size > 0)
            pay = combine.paymentItems

        if(combine.propertyItems.size > 0)
            property = combine.propertyItems

        if (combine.vehicleItems.size > 0)
            vehicle = combine.vehicleItems

        if (combine.assetItems.size > 0)
            asset = combine.assetItems

        if (combine.insuranceItems.size > 0)
            insurance = combine.insuranceItems

        if (combine.taxesItems.size > 0)
            tax = combine.taxesItems

        if (combine.listItems.size > 0)
            homelist = combine.listItems
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SearchPresenter(this)

        var editSearch = edtSearch.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                var decryptedCombine = DecryptedCombine()
                var text = edtSearch.getText().toString()

                var searchFinanceItems = ArrayList<DecryptedFinancial>()
                var searchPaymentItems = ArrayList<DecryptedPayment>()
                var searchPropertyItems = ArrayList<DecryptedProperty>()
                var searchVehicleItems = ArrayList<DecryptedVehicle>()
                var searchAssetItems = ArrayList<DecryptedAsset>()
                var searchInsuranceItems = ArrayList<DecryptedInsurance>()
                var searchTaxItems = ArrayList<DecryptedTax>()
                var searchHomeList = ArrayList<DecryptedHomeList>()

                for( financeItems in finance ) {
                    if( financeItems.selectionType.contains(text) || financeItems.institutionName.contains(text) || financeItems.accountName.contains(text) ||
                            financeItems.accountType.contains(text) || financeItems.nameOnAccount.contains(text) || financeItems.accountNumber.contains(text) ||
                            financeItems.location.contains(text) || financeItems.swiftCode.contains(text) || financeItems.abaRoutingNumber.contains(text) ||
                            financeItems.contacts.contains(text) || financeItems.website.contains(text) || financeItems.userName.contains(text) ||
                            financeItems.password.contains(text) ||  financeItems.pin.contains(text) || financeItems.created.contains(text) ||
                            financeItems.modified.contains(text) || financeItems.createdUser.contains(text) || financeItems.notes.contains(text) || financeItems.attachmentNames.contains(text))

                        searchFinanceItems.add(financeItems)
                }

                decryptedCombine.financialItems.addAll(searchFinanceItems)
                AppLogger.d("Search", "SearchItems : " + searchFinanceItems)
                AppLogger.d("Search", "DecryptedCombine : " + decryptedCombine)

                for( paymentItems in pay){

                    if (paymentItems.selectionType.contains(text) || paymentItems.insuranceCompany.contains(text) || paymentItems.insuredProperty.contains(text) ||
                            paymentItems.insuredVehicle.contains(text) || paymentItems.insuredPerson.contains(text) || paymentItems.policyNumber.contains(text) ||
                            paymentItems.policyEffectiveDate.contains(text) || paymentItems.policyExpirationDate.contains(text) || paymentItems.contacts.contains(text) ||
                            paymentItems.website.contains(text) || paymentItems.userName.contains(text) || paymentItems.password.contains(text) ||
                            paymentItems.pin.contains(text) || paymentItems.created.contains(text) || paymentItems.modified.contains(text) ||
                            paymentItems.createdUser.contains(text) || paymentItems.notes.contains(text) || paymentItems.attachmentNames.contains(text))


                        searchPaymentItems.add(paymentItems)
                }

                decryptedCombine.paymentItems.addAll(searchPaymentItems)
                AppLogger.d("Search", "SearchPayment : " + searchPaymentItems)

                for( propertyItems in property) {
                    if(propertyItems.selectionType.contains(text) || propertyItems.propertyName.contains(text) || propertyItems.streetAddressOne.contains(text) ||
                            propertyItems.streetAddressTwo.contains(text) || propertyItems.city.contains(text) || propertyItems.state.contains(text) ||
                            propertyItems.zipCode.contains(text) || propertyItems.country.contains(text) || propertyItems.titleName.contains(text) ||
                            propertyItems.purchaseDate.contains(text) || propertyItems.purchasePrice.contains(text) || propertyItems.estimatedMarketValue.contains(text) ||
                            propertyItems.contacts.contains(text) || propertyItems.selectionType.contains(text) || propertyItems.tenantName.contains(text) ||
                            propertyItems.leaseEndDate.contains(text) || propertyItems.leaseStartDate.contains(text) || propertyItems.created.contains(text) ||
                            propertyItems.modified.contains(text) || propertyItems.createdUser.contains(text) || propertyItems.notes.contains(text) || propertyItems.attachmentNames.contains(text))

                        searchPropertyItems.add(propertyItems)
                }

                decryptedCombine.propertyItems.addAll(searchPropertyItems)
                AppLogger.d("Search", "SearchProperty : " + searchPropertyItems)

                for(vehicleItems in vehicle){
                    if(vehicleItems.selectionType.contains(text) || vehicleItems.vehicleName.contains(text) || vehicleItems.licenseNumber.contains(text) ||
                            vehicleItems.vinNumber.contains(text) || vehicleItems.make.contains(text) || vehicleItems.model.contains(text) || vehicleItems.modelYear.contains(text) ||
                            vehicleItems.color.contains(text) || vehicleItems.titleName.contains(text) || vehicleItems.estimatedMarketValue.contains(text) || vehicleItems.registrationExpirydate.contains(text) ||
                            vehicleItems.purchasedOrLeased.contains(text) || vehicleItems.purchaseDate.contains(text) || vehicleItems.financedThroughLoan.contains(text) ||
                            vehicleItems.created.contains(text) || vehicleItems.modified.contains(text) || vehicleItems.createdUser.contains(text) || vehicleItems.leaseStartDate.contains(text) ||
                            vehicleItems.leaseEndDate.contains(text) || vehicleItems.contacts.contains(text) || vehicleItems.maintenanceEvent.contains(text) || vehicleItems.serviceProviderName.contains(text) ||
                            vehicleItems.dateOfService.contains(text) || vehicleItems.vehicle.contains(text) || vehicleItems.notes.contains(text) || vehicleItems.attachmentNames.contains(text) )

                        searchVehicleItems.add(vehicleItems)
                }
                decryptedCombine.vehicleItems.addAll(searchVehicleItems)
                AppLogger.d("Search", "SearchVehicle" + searchVehicleItems)

                for(assetItems in asset)
                {
                    if(assetItems.selectionType.contains(text) || assetItems.test.contains(text) || assetItems.assetName.contains(text) || assetItems.descriptionOrLocation.contains(text)
                            || assetItems.estimatedMarketValue.contains(text) || assetItems.serialNumber.contains(text) || assetItems.purchaseDate.contains(text)
                            || assetItems.purchasePrice.contains(text) || assetItems.contacts.contains(text) || assetItems.created.contains(text) || assetItems.modified.contains(text)
                            || assetItems.createdUser.contains(text) || assetItems.notes.contains(text) || assetItems.imageName.contains(text) || assetItems.attachmentNames.contains(text))

                        searchAssetItems.add(assetItems)
                }
                decryptedCombine.assetItems.addAll(searchAssetItems)
                AppLogger.d("Search", "SearchAsset" + searchAssetItems)

                for(insuranceItems in insurance){
                    if(insuranceItems.selectionType.contains(text) || insuranceItems.insuranceCompany.contains(text) || insuranceItems.insuredProperty.contains(text) || insuranceItems.insuredVehicle.contains(text)
                            || insuranceItems.insuredPerson.contains(text) || insuranceItems.policyNumber.contains(text) ||  insuranceItems.policyEffectiveDate.contains(text) || insuranceItems.policyExpirationDate.contains(text)
                            || insuranceItems.contacts.contains(text) || insuranceItems.website.contains(text) || insuranceItems.userName.contains(text) || insuranceItems.password.contains(text) || insuranceItems.pin.contains(text)
                            || insuranceItems.created.contains(text) || insuranceItems.modified.contains(text)|| insuranceItems.createdUser.contains(text) || insuranceItems.notes.contains(text) || insuranceItems.attachmentNames.contains(text))

                        searchInsuranceItems.add(insuranceItems)
                }
                decryptedCombine.insuranceItems.addAll(searchInsuranceItems)
                AppLogger.d("Search", "SearchInsurance" + searchInsuranceItems)

                for (taxItems in tax)
                {
                    if (taxItems.selectionType.contains(text) || taxItems.returnName.contains(text) || taxItems.taxYear.contains(text) || taxItems.taxPayer.contains(text) || taxItems.contacts.contains(text)
                            || taxItems.imageName.contains(text) || taxItems.attachmentNames.contains(text) || taxItems.notes.contains(text) || taxItems.title.contains(text) || taxItems.created.contains(text)
                            || taxItems.modified.contains(text) || taxItems.createdUser.contains(text))

                        searchTaxItems.add(taxItems)
                }
                decryptedCombine.taxesItems.addAll(searchTaxItems)
                AppLogger.d("Search", "SearchTax" + searchTaxItems)

                for(listItems in homelist){
                    if (listItems.selectionType.contains(text) || listItems.classType.contains(text) || listItems.listName.contains(text) || listItems.dueDate.contains(text)
                            || listItems.created.contains(text) || listItems.modified.contains(text) || listItems.createdUser.contains(text))

                        searchHomeList.add(listItems)
                }
                decryptedCombine.listItems.addAll(searchHomeList)
                AppLogger.d("Search", "SearchHomeList" + searchHomeList)

                rvRecentSearch.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
                val adapter = SearchAdapter(decryptedCombine)
                rvRecentSearch.setAdapter(adapter)

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }
}

