package com.ninebx.ui.home.baseCategories

import android.os.Bundle
import android.os.Parcelable
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
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.base.realm.home.education.CombineEducation
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.ui.base.realm.home.interests.CombineInterests
import com.ninebx.ui.base.realm.home.memories.CombineMemories
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.base.realm.home.personal.CombinePersonal
import com.ninebx.ui.base.realm.home.shopping.CombineShopping
import com.ninebx.ui.base.realm.home.travel.CombineTravel
import com.ninebx.ui.base.realm.home.wellness.CombineWellness
import com.ninebx.ui.base.realm.lists.*
import com.ninebx.ui.home.fragments.*
import com.ninebx.ui.home.lists.ListsFragment
import com.ninebx.ui.home.search.Level3SearchItem
import com.ninebx.ui.home.search.SearchPresenter
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Created by Alok on 12/01/18.
 */
class CategoryFragment : FragmentBackHelper(), CategoryView {


    private var combine: RealmResults<Combine> ?= null
    private var combineMemories : RealmResults<CombineMemories> ?= null
    private var combineTravel : RealmResults<CombineTravel> ?= null
    private var combineEducation : RealmResults<CombineEducation> ?= null
    private var combineInterests : RealmResults<CombineInterests> ?= null
    private var combineWellness : RealmResults<CombineWellness> ?= null
    private var combinePeronal : RealmResults<CombinePersonal> ?= null
    private var combineShopping : RealmResults<CombineShopping> ?= null
    private var combineContacts : RealmResults<CombineContacts> ?= null


    override fun onCombineResultsFetched(combine: RealmResults<Combine>) {
        this.combine = combine
        //setupUI()
    }

    override fun onCombineMemoryResultsFetched(combineMemory: RealmResults<CombineMemories>) {
        this.combineMemories = combineMemory
        //setupUI()
    }

    override fun onCombineTravelResultsFetched(combineTravel: RealmResults<CombineTravel>) {
        this.combineTravel = combineTravel
        //setupUI()
    }

    override fun onCombineEducationResultsFetched(combineEducation: RealmResults<CombineEducation>) {
        this.combineEducation = combineEducation
        //setupUI()
    }

    override fun onCombineInterestsResultsFetched(combineInterests: RealmResults<CombineInterests>) {
        this.combineInterests = combineInterests
        //setupUI()
    }

    override fun onCombineWellnessResultsFetched(combineWellness: RealmResults<CombineWellness>) {
        this.combineWellness = combineWellness
        //setupUI()
    }

    override fun onCombinePersonalResultsFetched(combinePersonal: RealmResults<CombinePersonal>) {
        this.combinePeronal = combinePersonal
        //setupUI()
    }

    override fun onCombineShoppingResultsFetched(combineShopping: RealmResults<CombineShopping>) {
        this.combineShopping = combineShopping
        //setupUI()
    }

    override fun onCombineContactsResultsFetched(combineContacts: RealmResults<CombineContacts>) {
        this.combineContacts = combineContacts
        //setupUI()
    }

    override fun onRecentSearchFetched(recentSearch: ArrayList<DecryptedRecentSearch>) {
    }

    private var combinedItems: Parcelable? = null

    override fun onCombineFetched(combine: DecryptedCombine) {
        this.combinedItems = combine
        setupUI()
    }

    override fun onCombineMemoryFetched(combineMemory: DecryptedCombineMemories) {
        this.combinedItems = combineMemory
        setupUI()
    }

    override fun onCombineTravelFetched(combineTravel: DecryptedCombineTravel) {
        this.combinedItems = combineTravel
        setupUI()
    }

    override fun onCombineEducationFetched(combineEducation: DecryptedCombineEducation) {
        this.combinedItems = combineEducation
        setupUI()
    }

    override fun onCombineInterestsFetched(combineInterests: DecryptedCombineInterests) {
        this.combinedItems = combineInterests
        setupUI()
    }

    override fun onCombineWellnessFetched(combineWellness: DecryptedCombineWellness) {
        this.combinedItems = combineWellness
        setupUI()
    }

    override fun onCombinePersonalFetched(combinePersonal: DecryptedCombinePersonal) {
        this.combinedItems = combinePersonal
        setupUI()
    }

    override fun onCombineShoppingFetched(combineShopping: DecryptedCombineShopping) {
        this.combinedItems = combineShopping
        setupUI()
    }

    override fun onCombineContactsFetched(combineContacts: DecryptedCombineContacts) {
        this.combinedItems = combineContacts
        setupUI()
    }

    override fun showProgress(message: Int) {
        if( layoutProgress != null  ) {
            layoutProgress.show()
            tvProgress.text = getString(message)
        }
    }

    private fun setupUI() {
        showProgress(R.string.loading)
        mCategoryPresenter = CategoryPresenter(arguments!!.getInt("category"), combinedItems!!, this)
    }

    var categoryName = ""
    var categoryID = ""

    var fromWhichBox: Int? = null


    private var allMemoryView: RealmResults<MemoryTimeline>? = null
    private var allContacts: RealmResults<Contacts>? = null

    override fun hideProgress() {
        if( layoutProgress != null )
            layoutProgress.hide()
    }

    override fun onError(error: Int) {
        hideProgress()
        if( context != null )
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    private lateinit var categories: ArrayList<Category>

    override fun onSuccess(categories: ArrayList<Category>) {
        this.categories = categories
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
                categoryID = category.category_id
                when {
                    category.title == "Lists" -> getLists()
                    category.title == "Memory Timeline" -> gettingMemoryTimeLineView()
                    category.title == "Shared Contacts" -> gettingContactsList()

                    category.subCategories.size == 0 -> {
                        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                        fragmentTransaction.addToBackStack(null)
                        val bundle = Bundle()
                        bundle.putParcelable(Constants.COMBINE_ITEMS, combinedItems)
                        bundle.putString("categoryName", categoryName)
                        bundle.putString("categoryId", categoryID)
                        val categoryFragment = FragmentListContainer()
                        categoryFragment.arguments = bundle
                        fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
                        Toast.makeText(context, "ID is " + categoryID, Toast.LENGTH_LONG).show()
                    }
                }
            }

            val subCategoryAdapter = SubCategoryAdapter(category.subCategories, object : CategoryItemClickListener {
                override fun onItemClick(category: SubCategory, action: String) {

                    val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    val bundle = Bundle()
                    categoryName = category.title
                    categoryID = category.subCategoryId

                    bundle.putString("categoryName", categoryName)
                    bundle.putString("categoryId", categoryID)
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
                            bundle.putParcelable(Constants.COMBINE_ITEMS, combinedItems)
                            categoryFragment.arguments = bundle
                            fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
                            Toast.makeText(context, "ID is " + categoryID, Toast.LENGTH_LONG).show()

                        }
                    }
                }
            })

            rvSubCategory.adapter = subCategoryAdapter
            layoutCategory.addView(categoryView)
        }
    }

    private lateinit var mCategoryPresenter: CategoryPresenter
    private lateinit var mSearchPresenter: SearchPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgress(R.string.loading)
        mSearchPresenter = SearchPresenter(this, arguments!!.getInt("category"))
        fromWhichBox = arguments!!.getInt("category")
        toolbarTitle.text = getString(fromWhichBox!!)
        ivBack.setOnClickListener { NineBxApplication.instance.activityInstance!!.onBackPressed() }
        ivHome.setOnClickListener { NineBxApplication.instance.activityInstance!!.callHomeFragment() }
        KeyboardUtil.hideSoftKeyboard(NineBxApplication.instance.activityInstance!!)
    }

    override fun onBackPressed(): Boolean {
        //NineBxApplication.instance.activityInstance!!.showQuickAdd()
        //NineBxApplication.instance.activityInstance!!.hideHomeNShowQuickAdd()
        return super.onBackPressed()
    }

    private fun getLists() {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val categoryFragment = ListsFragment()

        val bundle = Bundle()
        bundle.putString("homeScreen", "HomeScreen")
        bundle.putInt("category", fromWhichBox!!)

        when (fromWhichBox) {
            R.string.home_amp_money -> {
                bundle.putString("listOption", "Home")
                bundle.putInt("categoryName", (R.string.home_amp_money))
            }
            R.string.travel -> {
                bundle.putString("listOption", "Travel")
                bundle.putInt("categoryName", (R.string.travel))
            }
            R.string.contacts -> {
                bundle.putString("listOption", "Contacts")
                bundle.putInt("categoryName", (R.string.contacts))
            }
            R.string.education_work -> {
                bundle.putString("listOption", "Education")
                bundle.putInt("categoryName", (R.string.education_work))
            }
            R.string.personal -> {
                bundle.putString("listOption", "Personal")
                bundle.putInt("categoryName", (R.string.personal))
            }
            R.string.interests -> {
                bundle.putString("listOption", "Interests")
                bundle.putInt("categoryName", (R.string.interests))
            }
            R.string.wellness -> {
                bundle.putString("listOption", "Wellness")
                bundle.putInt("categoryName", (R.string.wellness))
            }
            R.string.memories -> {
                bundle.putString("listOption", "Memories")
                bundle.putInt("categoryName", (R.string.memories))
            }
            R.string.shopping -> {
                bundle.putString("listOption", "Shopping")
                bundle.putInt("categoryName", (R.string.shopping))
            }
        }

        categoryFragment.arguments = bundle
        fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit()
    }

    private fun gettingMemoryTimeLineView() {
        prepareRealmConnections(context, true, Constants.REALM_END_POINT_COMBINE_MEMORIES, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                hideProgress()

                allMemoryView = getAllMemoryTimeLine(realm!!)
                if (allMemoryView != null) {
                    context!!.hideProgressDialog()
                    //AppLogger.e("Memory", "MemoryView from Realm : " + allMemoryView.toString())

                    val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    val addFamilyUsersFragment = FragmentMemoriesListContainer()
                    val bundle = Bundle()
                    bundle.putString("categoryName", categoryName)
                    bundle.putString("categoryId", categoryID)
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
                hideProgress()

                allContacts = getCurrentContactList(realm!!)
                if (allContacts != null) {
                    context!!.hideProgressDialog()
                    AppLogger.e("Contacts", "Contacts from Realm : " + allContacts.toString())

                    val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    val addFamilyUsersFragment = ContactsListContainerFragment()
                    val bundle = Bundle()
                    bundle.putString("categoryName", categoryName)
                    bundle.putString("categoryId", categoryID)
                    bundle.putParcelableArrayList(Constants.REALM_CONTACTS, Contacts.createParcelableList(allContacts!!))
                    addFamilyUsersFragment.arguments = bundle
                    fragmentTransaction.replace(R.id.frameLayout, addFamilyUsersFragment).commit()
                }
            }
        })
    }

    private var searchItems: ArrayList<Level3SearchItem> = ArrayList()

    private var combineContactsFetched: ArrayList<ContactsList>? = null

}