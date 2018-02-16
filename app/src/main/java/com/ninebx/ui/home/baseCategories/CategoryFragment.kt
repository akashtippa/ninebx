package com.ninebx.ui.home.baseCategories

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.home.fragments.*
import com.ninebx.ui.home.lists.SubListsFragment
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Created by Alok on 12/01/18.
 */
class CategoryFragment : FragmentBackHelper(), CategoryView {

    override fun showProgress(message: Int) {
        layoutProgress.show()
        tvProgress.text = getString(message)
    }

    var categoryName = ""
    private var allMemoryView: RealmResults<MemoryTimeline>? = null
    private var allContacts: RealmResults<Contacts>? = null

    override fun hideProgress() {
        layoutProgress.hide()
    }

    override fun onError(error: Int) {
        hideProgress()
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(categories: ArrayList<Category>) {
        hideProgress()
        inflateLayout(categories)
    }

    val prefrences = NineBxPreferences()

    private fun inflateLayout(categories: ArrayList<Category>) {

        val inflater = LayoutInflater.from(context)

        for (category in categories) {

            val categoryView = inflater.inflate(R.layout.layout_category_view, null) as LinearLayout

            val tvCategory = categoryView.findViewById<TextView>(R.id.tvCategory)
            val tvCount = categoryView.findViewById<TextView>(R.id.tvCount)
            val rvSubCategory = categoryView.findViewById<RecyclerView>(R.id.rvSubCategory)
            val id = context!!.resources.getIdentifier(category.drawableString, "drawable", context!!.packageName)
            tvCategory.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context!!, id), null, null, null)

            tvCategory.compoundDrawablePadding = context!!.resources.getDimensionPixelOffset(R.dimen.default_mini_padding)
            tvCategory.text = category.title
            if (category.subCategories.size == 0) {
                tvCount.text = category.formsCount.toString()
            } else {
                tvCount.text = ""
                tvCount.setCompoundDrawables(null, null, null, null)
            }
            rvSubCategory.layoutManager = LinearLayoutManager(context)

            tvCategory.setOnClickListener {
                categoryName = category.title
                when {
                    category.title == "Lists" -> getLists()
                    category.title == "Memory Timeline" -> gettingMemoryTimeLineView()
                    category.title == "Shared Contacts" -> gettingContactsList()

                    category.subCategories.size == 0 -> {
                        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                        fragmentTransaction.addToBackStack(null)
                        val bundle = Bundle()
                        bundle.putString("categoryName", category.title)
                        bundle.putString("categoryId", category.category_id)
                        val categoryFragment = FragmentListContainer()
                        categoryFragment.arguments = bundle
                        fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
                    }
                }
            }

            val subCategoryAdapter = SubCategoryAdapter(category.subCategories, object : CategoryItemClickListener {
                override fun onItemClick(category: SubCategory, action: String) {

                    val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    val bundle = Bundle()
                    bundle.putString("categoryName", category.title)

                    when {
                        category.title == "Add Persons." -> {
                            val categoryFragment = WellnessFragment()
                            categoryFragment.arguments = bundle
                            fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
                        }
                        category.title == "Add Person." -> {
                            val categoryFragment = ClothesFragment()
                            categoryFragment.arguments = bundle
                            fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
                        }
                        else -> {
                            val categoryFragment = FragmentListContainer()
                            categoryFragment.arguments = bundle
                            fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
                        }
                    }
                }
            })

            rvSubCategory.adapter = subCategoryAdapter
            layoutCategory.addView(categoryView)
        }
    }

    private lateinit var mCategoryPresenter: CategoryPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCategoryPresenter = CategoryPresenter(arguments!!.getInt("category"), this)
        KeyboardUtil.hideSoftKeyboard(NineBxApplication.instance.activityInstance!!)
        NineBxApplication.instance.activityInstance!!.hideQuickAdd()
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.toggleToolbarImage()
        NineBxApplication.instance.activityInstance!!.showQuickAdd()
        NineBxApplication.instance.activityInstance!!.hideHomeNShowQuickAdd()
        return super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        val toolbarTitle = prefrences.currentBox
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(toolbarTitle.toString())
    }

    private fun getMemoryTimeLine() {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val bundle = Bundle()
        bundle.putString("categoryName", categoryName)
        val categoryFragment = FragmentMemoriesListContainer()
        categoryFragment.arguments = bundle
        fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
    }

    private fun getLists() {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        val bundle = Bundle()
        bundle.putString("homeScreen", "HomeScreen")

        val categoryFragment = SubListsFragment()
        categoryFragment.arguments = bundle

        fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
    }

    private fun gettingMemoryTimeLineView() {
        prepareRealmConnections(context, true, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {

                allMemoryView = getAllMemoryTimeLine(realm!!)
                if (allMemoryView != null) {
                    context!!.hideProgressDialog()
                    AppLogger.e("Memory", "MemoryView from Realm : " + allMemoryView.toString())

                    val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    val addFamilyUsersFragment = FragmentMemoriesListContainer()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList(Constants.REALM_MEMORY_VIEW, MemoryTimeline.createParcelableList(allMemoryView!!))
                    addFamilyUsersFragment.arguments = bundle
                    fragmentTransaction.replace(R.id.frameLayout, addFamilyUsersFragment).commit()
                }
            }
        })
    }


    private fun gettingContactsList() {
        prepareRealmConnections(context, true, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {

                allContacts = getCurrentContactList(realm!!)
                if (allContacts != null) {
//                    context!!.hideProgressDialog()
                    AppLogger.e("Contacts", "Contacts from Realm : " + allContacts.toString())

                    val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    val addFamilyUsersFragment = ContactsListContainerFragment()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList(Constants.REALM_CONTACTS, Contacts.createParcelableList(allContacts!!))
                    addFamilyUsersFragment.arguments = bundle
                    fragmentTransaction.replace(R.id.frameLayout, addFamilyUsersFragment).commit()
                }
            }
        })
    }

}