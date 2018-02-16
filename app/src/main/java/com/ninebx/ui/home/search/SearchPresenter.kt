package com.ninebx.ui.home.search

import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.home.education.CombineEducation
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.ui.base.realm.home.interests.CombineInterests
import com.ninebx.ui.base.realm.home.memories.CombineMemories
import com.ninebx.ui.base.realm.home.personal.CombinePersonal
import com.ninebx.ui.base.realm.home.shopping.CombineShopping
import com.ninebx.ui.base.realm.home.travel.CombineTravel
import com.ninebx.ui.base.realm.home.wellness.CombineWellness
import com.ninebx.utility.AppLogger
import com.ninebx.utility.decryptCombine
import com.ninebx.utility.prepareRealmConnections
import io.realm.Realm
import io.realm.internal.SyncObjectServerFacade.getApplicationContext

/**
 * Created by Alok on 03/01/18.
 */
class SearchPresenter( private val searchView: SearchView ) {

    private val mDecryptCombine: DecryptedCombine = DecryptedCombine()

    init {
        val context = getApplicationContext()

        prepareRealmConnections(context, false, "Combine", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineResult = realm!!.where(Combine::class.java).findAll()
                if (combineResult.size > 0) {
                    for (i in 0 until combineResult.size) {
                        val decryptedCombine = decryptCombine(combineResult[i]!!)
                        appendToDecrypt(decryptedCombine)
                    }
                    AppLogger.d("COmbineDecrypted", "Decrypted combine financial" + mDecryptCombine)
                    searchView.onCombineFetched(mDecryptCombine)
                } else {
                    searchView.onCombineFetched(mDecryptCombine)
                }
            }
        })

        prepareRealmConnections(context, false, "CombineTravel", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineTravel = realm!!.where(CombineTravel::class.java).findAll()
                AppLogger.d("Combine", "CombinedTravel : " + combineTravel)
            }
        })

        prepareRealmConnections(context, false, "CombineMemories", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineMemories = realm!!.where(CombineMemories::class.java).findAll()
                AppLogger.d("Combine", "CombinedMemories : " + combineMemories)
            }
        })

        prepareRealmConnections(context, false, "CombineShopping", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineShopping = realm!!.where(CombineShopping::class.java).findAll()
                AppLogger.d("Combine", "CombinedShopping : " + combineShopping)
            }
        })

        prepareRealmConnections(context, false, "CombineContacts", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineContacts = realm!!.where(CombineContacts::class.java).findAll()
                AppLogger.d("Combine", "CombinedContacts : " + combineContacts)
            }
        })

        prepareRealmConnections(context, false, "CombineEducation", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineEducation = realm!!.where(CombineEducation::class.java).findAll()
                AppLogger.d("Combine", "CombinedEducation : " + combineEducation)
            }
        })

        prepareRealmConnections(context, false, "CombinePersonal", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combinePersonal = realm!!.where(CombinePersonal::class.java).findAll()
                AppLogger.d("Combine", "CombinedPersonal : " + combinePersonal)
            }
        })

        prepareRealmConnections(context, false, "CombineInterests", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineInterests = realm!!.where(CombineInterests::class.java).findAll()
                AppLogger.d("Combine", "CombinedInterests : " + combineInterests)
            }
        })

        prepareRealmConnections(context, false, "CombineWellness", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineWellness = realm!!.where(CombineWellness::class.java).findAll()
                AppLogger.d("Combine", "CombinedWellness : " + combineWellness)
            }
        })

        prepareRealmConnections(context, false, "CombineEvents", object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                val combineEvents = realm!!.where(Combine::class.java).findAll()
                AppLogger.d("Combine", "CombinedEvents : " + combineEvents)
            }
        })
    }

    private fun appendToDecrypt(decryptedCombine: DecryptedCombine) {
        mDecryptCombine.listItems.addAll(decryptedCombine.listItems)
        mDecryptCombine.propertyItems.addAll(decryptedCombine.propertyItems)
        mDecryptCombine.vehicleItems.addAll(decryptedCombine.vehicleItems)
        mDecryptCombine.taxesItems.addAll(decryptedCombine.taxesItems)
        mDecryptCombine.insuranceItems.addAll(decryptedCombine.insuranceItems)
        mDecryptCombine.assetItems.addAll(decryptedCombine.assetItems)
        mDecryptCombine.paymentItems.addAll(decryptedCombine.paymentItems)
        mDecryptCombine.financialItems.addAll(decryptedCombine.financialItems)
    }

    fun searchHomeItems(text: String): DecryptedCombine {
        if (text.contains("Home", ignoreCase = true)) {
            return mDecryptCombine
        }
        val searchDecryptCombine = DecryptedCombine()
        var searchFinanceItems = ArrayList<DecryptedFinancial>()
        var searchPaymentItems = ArrayList<DecryptedPayment>()
        var searchPropertyItems = ArrayList<DecryptedProperty>()
        var searchVehicleItems = ArrayList<DecryptedVehicle>()
        var searchAssetItems = ArrayList<DecryptedAsset>()
        var searchInsuranceItems = ArrayList<DecryptedInsurance>()
        var searchTaxItems = ArrayList<DecryptedTax>()
        var searchHomeList = ArrayList<DecryptedHomeList>()
        AppLogger.d("Search", "Decryptex : " + mDecryptCombine.financialItems)
        for (financeItems in mDecryptCombine.financialItems) {
            if (financeItems.selectionType.contains(text) || financeItems.institutionName.contains(text) || financeItems.accountName.contains(text) ||
                    financeItems.accountType.contains(text) || financeItems.nameOnAccount.contains(text) || financeItems.accountNumber.contains(text) ||
                    financeItems.location.contains(text) || financeItems.swiftCode.contains(text) || financeItems.abaRoutingNumber.contains(text) ||
                    financeItems.contacts.contains(text) || financeItems.website.contains(text) || financeItems.userName.contains(text) ||
                    financeItems.password.contains(text) || financeItems.pin.contains(text) || financeItems.created.contains(text) ||
                    financeItems.modified.contains(text) || financeItems.createdUser.contains(text) || financeItems.notes.contains(text)
                    || financeItems.attachmentNames.contains(text))

                searchFinanceItems.add(financeItems)
        }

        searchDecryptCombine.financialItems.addAll(searchFinanceItems)
        AppLogger.d("Search", "SearchItems : " + searchFinanceItems)
        AppLogger.d("Search", "DecryptedCombine : " + searchDecryptCombine)

        for (paymentItems in mDecryptCombine.paymentItems) {

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

        for (propertyItems in mDecryptCombine.propertyItems) {
            if (propertyItems.selectionType.contains(text) || propertyItems.propertyName.contains(text) || propertyItems.streetAddressOne.contains(text) ||
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

        for (vehicleItems in mDecryptCombine.vehicleItems) {
            if (vehicleItems.selectionType.contains(text) || vehicleItems.vehicleName.contains(text) || vehicleItems.licenseNumber.contains(text) ||
                    vehicleItems.vinNumber.contains(text) || vehicleItems.make.contains(text) || vehicleItems.model.contains(text) || vehicleItems.modelYear.contains(text) ||
                    vehicleItems.color.contains(text) || vehicleItems.titleName.contains(text) || vehicleItems.estimatedMarketValue.contains(text) || vehicleItems.registrationExpirydate.contains(text) ||
                    vehicleItems.purchasedOrLeased.contains(text) || vehicleItems.purchaseDate.contains(text) || vehicleItems.financedThroughLoan.contains(text) ||
                    vehicleItems.created.contains(text) || vehicleItems.modified.contains(text) || vehicleItems.createdUser.contains(text) || vehicleItems.leaseStartDate.contains(text) ||
                    vehicleItems.leaseEndDate.contains(text) || vehicleItems.contacts.contains(text) || vehicleItems.maintenanceEvent.contains(text) || vehicleItems.serviceProviderName.contains(text) ||
                    vehicleItems.dateOfService.contains(text) || vehicleItems.vehicle.contains(text) || vehicleItems.notes.contains(text) || vehicleItems.attachmentNames.contains(text))

                searchVehicleItems.add(vehicleItems)
        }
        searchDecryptCombine.vehicleItems.addAll(searchVehicleItems)
        AppLogger.d("Search", "SearchVehicle" + searchVehicleItems)

        for (assetItems in mDecryptCombine.assetItems) {
            if (assetItems.selectionType.contains(text) || assetItems.test.contains(text) || assetItems.assetName.contains(text) || assetItems.descriptionOrLocation.contains(text)
                    || assetItems.estimatedMarketValue.contains(text) || assetItems.serialNumber.contains(text) || assetItems.purchaseDate.contains(text)
                    || assetItems.purchasePrice.contains(text) || assetItems.contacts.contains(text) || assetItems.created.contains(text) || assetItems.modified.contains(text)
                    || assetItems.createdUser.contains(text) || assetItems.notes.contains(text) || assetItems.imageName.contains(text) || assetItems.attachmentNames.contains(text))

                searchAssetItems.add(assetItems)
        }
        searchDecryptCombine.assetItems.addAll(searchAssetItems)
        AppLogger.d("Search", "SearchAsset" + searchAssetItems)

        for (insuranceItems in mDecryptCombine.insuranceItems) {
            if (insuranceItems.selectionType.contains(text) || insuranceItems.insuranceCompany.contains(text) || insuranceItems.insuredProperty.contains(text) || insuranceItems.insuredVehicle.contains(text)
                    || insuranceItems.insuredPerson.contains(text) || insuranceItems.policyNumber.contains(text) || insuranceItems.policyEffectiveDate.contains(text) || insuranceItems.policyExpirationDate.contains(text)
                    || insuranceItems.contacts.contains(text) || insuranceItems.website.contains(text) || insuranceItems.userName.contains(text) || insuranceItems.password.contains(text) || insuranceItems.pin.contains(text)
                    || insuranceItems.created.contains(text) || insuranceItems.modified.contains(text) || insuranceItems.createdUser.contains(text) || insuranceItems.notes.contains(text) || insuranceItems.attachmentNames.contains(text))

                searchInsuranceItems.add(insuranceItems)
        }
        searchDecryptCombine.insuranceItems.addAll(searchInsuranceItems)
        AppLogger.d("Search", "SearchInsurance" + searchInsuranceItems)

        for (taxItems in mDecryptCombine.taxesItems) {
            if (taxItems.selectionType.contains(text) || taxItems.returnName.contains(text) || taxItems.taxYear.contains(text) || taxItems.taxPayer.contains(text) || taxItems.contacts.contains(text)
                    || taxItems.imageName.contains(text) || taxItems.attachmentNames.contains(text) || taxItems.notes.contains(text) || taxItems.title.contains(text) || taxItems.created.contains(text)
                    || taxItems.modified.contains(text) || taxItems.createdUser.contains(text))

                searchTaxItems.add(taxItems)
        }
        searchDecryptCombine.taxesItems.addAll(searchTaxItems)
        AppLogger.d("Search", "SearchTax" + searchTaxItems)

        for (listItems in mDecryptCombine.listItems) {
            if (listItems.selectionType.contains(text) || listItems.classType.contains(text) || listItems.listName.contains(text) || listItems.dueDate.contains(text)
                    || listItems.created.contains(text) || listItems.modified.contains(text) || listItems.createdUser.contains(text))

                searchHomeList.add(listItems)
        }
        searchDecryptCombine.listItems.addAll(searchHomeList)
        AppLogger.d("Search", "SearchHomeList" + searchHomeList)

        return searchDecryptCombine
    }
}
