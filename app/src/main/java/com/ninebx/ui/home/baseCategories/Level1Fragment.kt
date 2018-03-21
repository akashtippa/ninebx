package com.ninebx.ui.home.baseCategories

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.hideProgressDialog
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.ui.base.realm.Member
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
import com.ninebx.ui.home.ContainerActivity
import com.ninebx.ui.home.baseSubCategories.Level3CategoryFragment
import com.ninebx.ui.home.fragments.*
import com.ninebx.ui.home.lists.ListsFragment
import com.ninebx.ui.home.search.Level3SearchItem
import com.ninebx.ui.home.search.SearchPresenter
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.layout_category_view.view.*

/**
 * Created by Alok on 12/01/18.
 */
class Level1Fragment : FragmentBackHelper(), CategoryView {

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

    lateinit var rvSubCategory: RecyclerView
    lateinit var subCategoryAdapter: SubCategoryAdapter
    var categoryView: LinearLayout ?= null
    private fun inflateLayout(categories: ArrayList<Category>) {

        val inflater = LayoutInflater.from(context)

        for (category in categories) {

            categoryView = inflater.inflate(R.layout.layout_category_view, null) as LinearLayout


            val tvCategory = categoryView!!.findViewById<TextView>(R.id.tvCategory)
            val tvCount = categoryView!!.findViewById<TextView>(R.id.tvCount)
            rvSubCategory = categoryView!!.findViewById<RecyclerView>(R.id.rvSubCategory)
            //val personSpinner = categoryView.findViewById<Spinner>(R.id.memberListSpinner)
            val id = context!!.resources.getIdentifier(category.drawableString, "drawable", context!!.packageName)
            tvCategory.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context!!, id), null, null, null)

            tvCategory.compoundDrawablePadding = context!!.resources.getDimensionPixelOffset(R.dimen.default_mini_padding)
            tvCategory.text = category.title
            if (category.subCategories.size == 0) {
                tvCount.show()
                tvCount.text = category.formsCount.toString()
            } else {
                tvCount.hide()
            }
            rvSubCategory.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

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
                        bundle.putInt(Constants.CURRENT_BOX, categoryInt)
                        val level2Fragment = Level2Fragment()
                        level2Fragment.arguments = bundle
                        fragmentTransaction.replace(R.id.frameLayout, level2Fragment).commit()
                        Toast.makeText(context, "ID is " + categoryID, Toast.LENGTH_LONG).show()
                    }
                }
            }


            var subCategoryAdapter = SubCategoryAdapter(category, category.subCategories, object : CategoryItemClickListener {


                override fun onItemClick(adapter: SubCategoryAdapter, mainCategory: Category, subCategory: SubCategory, action: String) {
                    categoryName = subCategory.title
                    categoryID = subCategory.subCategoryId
                    if( subCategory.formsCount == 0 )
                    if( categoryName == "Maintenance" || categoryName == "Auto insurance" ) {
                        if( !checkForAsset("Vehicles", categories) ) {
                            context!!.showToast(R.string.error_empty_vehicle_list)
                            return
                        }
                    }
                    if( action == "add_item" ) {
                        val bundle = Bundle()
                        bundle.putString("categoryName", categoryName)
                        bundle.putString("categoryId", categoryID)
                        bundle.putParcelable(Constants.COMBINE_ITEMS, combinedItems)
                        bundle.putString("action", "add")
                        bundle.putString(Constants.FROM_CLASS, "Level2Fragment")
                        bundle.putInt(Constants.CURRENT_BOX, categoryInt )
                        /*val level3CategoryFragment = Level3CategoryFragment()
                        level3CategoryFragment.arguments = bundle
                        fragmentTransaction.replace(R.id.frameLayout, level3CategoryFragment).commit()*/
                        val intent = Intent( context, ContainerActivity::class.java)
                        intent.putExtras(bundle)
                        startActivityForResult(
                                intent, 12313)

                    }
                    else {
                        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                        fragmentTransaction.addToBackStack(null)
                        val bundle = Bundle()
                        bundle.putString("categoryName", categoryName)
                        bundle.putString("categoryId", categoryID)
                        bundle.putInt(Constants.CURRENT_BOX, categoryInt )
                        bundle.putParcelable(Constants.COMBINE_ITEMS, combinedItems)
                        when {
                            subCategory.title == "Add Persons." -> {
                                if(!memberList.isEmpty()) {
                                    CustomDropDown(adapter, mainCategory.subCategories)
                                }
                                else {
                                    Toast.makeText(context, "All Family/Users added to the list!", Toast.LENGTH_SHORT).show()
                                }
                            }
                            subCategory.title == "Add Person." -> {
                                val categoryFragment = ClothesFragment()
                                categoryFragment.arguments = bundle
                                fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
                            }
                            else -> {

                                //bundle.putParcelable(Constants.COMBINE_ITEMS, combinedItems)
                                if(subCategory.subCategoryId == "2") { //getString(Constants.SUB_CATEGORY_DISPLAY_PERSON) not working
                                    when(category.title) {
                                        "Work" -> {
                                            val categoryFragment = Level2Fragment()
                                            categoryFragment.arguments = bundle
                                            fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
                                        }
                                        "Education" -> {
                                            val categoryFragment =  Level2Fragment()
                                            categoryFragment.arguments = bundle
                                            fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
                                        }
                                        "Personal Health Record" -> {
                                            val categoryFragment = WellnessFragment()
                                            categoryFragment.arguments = bundle
                                            fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
                                        }
                                        "Clothing sizes" -> {
                                            val categoryFragment = ClothesFragment()
                                            categoryFragment.arguments = bundle
                                            fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
                                        }
                                    }
                                } else {
                               val categoryFragment = Level2Fragment()
                                    categoryFragment.arguments = bundle
                                    fragmentTransaction.replace(R.id.frameLayout, categoryFragment).commit()
                                }
                                Toast.makeText(context, "ID is " + categoryID, Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                }
            })
            rvSubCategory.adapter = subCategoryAdapter
           /* if(category.title == "Personal Health Record" || category.title == "Education"
                    || category.title == "Work" || category.title == "Clothing sizes") {
                peopleSubCategoryAdapter = subCategoryAdapter
            }*/
            layoutCategory.addView(categoryView)
        }
    }

    private fun checkForAsset(categoryName: String, categories: ArrayList<Category>): Boolean {
        var isAssetPresent = false
        for( category in categories ) {
            val subCategoryIndex = category.subCategories.indexOf(SubCategory(categoryName))
            if( subCategoryIndex != -1 ) {
                val subCategory = category.subCategories[subCategoryIndex]
                isAssetPresent = subCategory.formsCount > 0
            }
            if( isAssetPresent ) break
        }
        return isAssetPresent
    }

    var addedPersonList: ArrayList<DecryptedMember> = ArrayList()
    var memberListAdapter: MemberListAdapter ?= null
    var levelDialog: AlertDialog ?= null
    private fun CustomDropDown(adapter : SubCategoryAdapter, subCategories: ArrayList<SubCategory>) {
        //todo
        val dialogView: View = LayoutInflater.from(context).inflate(R.layout.layout_members, null)
        val cancelTextView: TextView = dialogView.findViewById(R.id.cancelTextView)
        val dialogTitleTextView : TextView = dialogView.findViewById(R.id.titleTextView)
        val membersRecyclerView : RecyclerView = dialogView.findViewById(R.id.membersRecyclerView)
        levelDialog = AlertDialog.Builder(context!!)
                .setView(dialogView)
                .create()
        dialogTitleTextView.setText("Family/Users")
        membersRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        memberListAdapter = MemberListAdapter(memberList, object: AdapterClickListener {
            override fun onItemClick(position: Int) {
                val member = memberListAdapter!!.getItem(position)
                addedPersonList.add(member)
                subCategories.add(0, SubCategory(
                        member.firstName + " " + member.lastName,
                        "",
                        0,
                        Constants.SUB_CATEGORY_DISPLAY_PERSON,
                        Constants.SUB_CATEGORY_DISPLAY_PERSON.toString()))
                adapter.notifyDataSetChanged()
                memberListAdapter!!.removeItem(position)
                memberListAdapter!!.notifyDataSetChanged()
                levelDialog?.dismiss()
            }

        })
        membersRecyclerView.adapter = memberListAdapter
        cancelTextView.setOnClickListener{
            levelDialog?.dismiss()
        }
        levelDialog?.show()
    }

    private var usersRealm: Realm? = null
    var memberList: ArrayList<DecryptedMember> = ArrayList()

    fun fetchMembers() {
        prepareRealmConnectionsRealmThread(context, true, Constants.REALM_END_POINT_USERS, object : Realm.Callback() {
            override fun onSuccess(realm: Realm?) {
                usersRealm = realm
                val results = usersRealm!!.where(Member::class.java).distinctValues("userId").findAll()
                memberList.clear()
                memberList.addAll(decryptMembers(results!!)!!.toList())
                for( member in memberList ) {
                    AppLogger.d("Member", "List : " + member)
                }
                //mListsAdapter!!.notifyDataSetChanged()
                //saveUserObject()
            }

        })
    }

    private lateinit var mCategoryPresenter: CategoryPresenter
    private lateinit var mSearchPresenter: SearchPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    private var categoryInt: Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fromWhichBox = arguments!!.getInt("category")
        categoryInt = arguments!!.getInt("category")
        init()
    }

    private fun init() {
        showProgress(R.string.loading)
        mSearchPresenter = SearchPresenter(this, categoryInt)
        toolbarTitle.text = getString(fromWhichBox!!)
        ivBack.setOnClickListener { NineBxApplication.instance.activityInstance!!.onBackPressed() }
        ivHome.setOnClickListener { NineBxApplication.instance.activityInstance!!.callHomeFragment() }
        KeyboardUtil.hideSoftKeyboard(NineBxApplication.instance.activityInstance!!)
        fetchMembers()
    }

    override fun onBackPressed(): Boolean {
        return super.onBackPressed()
    }

    private fun getLists() {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val listsFragment = ListsFragment()

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
            R.string.shopping -> {
                bundle.putString("listOption", "Shopping")
                bundle.putInt("categoryName", (R.string.shopping))
            }
            R.string.contacts -> {
                bundle.putString("listOption", "Contacts")
                bundle.putInt("categoryName", (R.string.contacts))
            }
        }

        listsFragment.arguments = bundle
        fragmentTransaction.add(R.id.frameLayout, listsFragment).commit()
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

    private var decryptedCombineContact: DecryptedCombineContacts ?= null
    private fun gettingContactsList() {

        if(combinedItems != null) {
            val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val addFamilyUsersFragment = ContactsListContainerFragment()
            val bundle = Bundle()
            bundle.putString("categoryName", categoryName)
            bundle.putString("categoryId", categoryID)
            bundle.putParcelable(Constants.REALM_CONTACTS, combinedItems!!/*Contacts.createParcelableList(combinedItems!!)*/)
            addFamilyUsersFragment.arguments = bundle
            fragmentTransaction.replace(R.id.frameLayout, addFamilyUsersFragment).commit()
        }
        /*prepareRealmConnections(context, true, Constants.REALM_END_POINT_COMBINE_CONTACTS, object : Realm.Callback() {

            override fun onSuccess(realm: Realm?) {
                hideProgress()

                allContacts = getCurrentContactList(realm!!)
                combineContacts = realm.where(CombineContacts::class.java).findAll()
                decryptedCombineContact = DecryptedCombineContacts()
                fetchCombineContacts(combineContacts!!)
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
                    if(combineContacts != null) {
                        bundle.putParcelableArrayList(Constants.COMBINE_ITEMS, decryptedCombineContacts)
                    }
                    addFamilyUsersFragment.arguments = bundle
                    fragmentTransaction.replace(R.id.frameLayout, addFamilyUsersFragment).commit()
                }
            }
        })*/
    }

    private var decryptedCombineContacts: ArrayList<DecryptedCombineContacts> ?= null

    private fun fetchCombineContacts(combineContacts: RealmResults<CombineContacts>) {
        decryptedCombineContacts= ArrayList()
        for(contact in combineContacts) {
            decryptedCombineContacts!!.add(decryptCombineContacts(contact))
        }
    }

    private var searchItems: ArrayList<Level3SearchItem> = ArrayList()

    private var combineContactsFetched: ArrayList<ContactsList>? = null

    override fun onResume() {
        super.onResume()
        NineBxApplication.instance.activityInstance!!.hideQuickAdd()
        NineBxApplication.instance.activityInstance!!.showBottomView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if( requestCode == 12313 && resultCode == Activity.RESULT_OK ) {
            AppLogger.d("ActivityResult", "onActivityResult : Level1" )
            activity!!.supportFragmentManager.beginTransaction().detach(this).attach(this).commit()
        }
    }
}