package com.ninebx.ui.home.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.SearchItemClickListener
import com.ninebx.ui.base.realm.decrypted.DecryptedCombine
import com.ninebx.ui.home.BaseHomeFragment
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * Created by Alok on 03/01/18.
 */

class SearchFragment : BaseHomeFragment(), SearchView {

    private var mDecryptCombine: DecryptedCombine? = null

    override fun onCombineFetched(combine: DecryptedCombine) {
        this.mDecryptCombine = combine
        hideProgress()
    }

    override fun showProgress(message: Int) {
        edtSearch.isEnabled = false
        progressLayout.show()
    }

    override fun hideProgress() {
        edtSearch.isEnabled = true
        progressLayout.hide()
    }

    override fun onError(error: Int) {
        context!!.showToast(error)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    private lateinit var searchDecryptCombine: DecryptedCombine

    private lateinit var mSearchPresenter: SearchPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideAllLayouts()
        showProgress(R.string.loading)
        mSearchPresenter = SearchPresenter(this)

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = edtSearch.text.toString().trim()
                searchDecryptCombine = mSearchPresenter.searchHomeItems(text)
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

    private var mSearchTravelItems = ArrayList<Level3SearchItem>()
    private var mSearchContactsItems = ArrayList<Level3SearchItem>()
    private var mSearchEducationItems = ArrayList<Level3SearchItem>()
    private var mSearchPersonalItems = ArrayList<Level3SearchItem>()
    private var mSearchInterestsItems = ArrayList<Level3SearchItem>()
    private var mSearchWellnessItems = ArrayList<Level3SearchItem>()
    private var mSearchMemoriesItems = ArrayList<Level3SearchItem>()
    private var mSearchShoppingItems = ArrayList<Level3SearchItem>()
    private var mSearchHomeList = ArrayList<Level3SearchItem>()

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
        for (finance in searchDecryptCombine.financialItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, finance.accountName, "finance"))
        }
        for (payment in searchDecryptCombine.paymentItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, payment.userName, "payment"))
        }
        for (asset in searchDecryptCombine.assetItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, asset.assetName, "asset"))
        }
        for (insurance in searchDecryptCombine.insuranceItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, insurance.insuranceCompany, "insurance"))
        }
        for (tax in searchDecryptCombine.taxesItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, tax.taxPayer, "tax"))
        }
        for (vehicle in searchDecryptCombine.vehicleItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, vehicle.titleName, "vehicle"))
        }
        for (property in searchDecryptCombine.propertyItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, property.propertyName, "property"))
        }
        for (home in searchDecryptCombine.listItems) {
            mSearchHomeList.add(Level3SearchItem(R.string.home_amp_money, home.listName, "home"))
        }
        if (mSearchHomeList.size > 0) //Pass the right recyclerview and layout to be shown with searchlist to be populated
            setupAdapter(rvHomeMoney, homeLayout, mSearchHomeList)
    }

    private fun setupAdapter(searchRecyclerView: RecyclerView?, layout: LinearLayout, searchList: ArrayList<Level3SearchItem>) {

        searchRecyclerView!!.layoutManager = LinearLayoutManager(context)
        searchRecyclerView.adapter = SearchAdapter(searchList, object : SearchItemClickListener {
            override fun onItemClick(position: Int, searchItem: Level3SearchItem) {
                when (searchItem.searchCategory) {
                    R.string.home_amp_money -> {
                        switchHomeItems(position, searchItem)
                    }
                    (R.string.travel) -> {
                        switchTravelItems(position, searchItem)
                    }
                    (R.string.contacts) -> {
                        switchContactsItems(position, searchItem)
                    }
                    (R.string.education_work) -> {
                        switchEducationItems(position, searchItem)
                    }
                    (R.string.personal) -> {
                        switchPersonalItems(position, searchItem)
                    }
                    (R.string.interests) -> {
                        switchInterestsItems(position, searchItem)
                    }
                    (R.string.wellness) -> {
                        switchWellnessItems(position, searchItem)
                    }
                    (R.string.memories) -> {
                        switchMemoriesItems(position, searchItem)
                    }
                    (R.string.shopping) -> {
                        switchShoppingItems(position, searchItem)
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
        //TODO
    }

    private fun switchHomeItems(position: Int, searchItem: Level3SearchItem) {
        when (searchItem.categoryName) {
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

