package com.ninebx.ui.home.baseCategories

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
import com.ninebx.ui.home.search.SearchView
import io.realm.RealmResults

/**
 * Created by Alok on 12/01/18.
 */
interface CategoryView : SearchView {
    fun onSuccess( categories : ArrayList<Category> )
    fun onCombineResultsFetched(combine: RealmResults<Combine>)
    fun onCombineMemoryResultsFetched(combineMemory: RealmResults<CombineMemories>)
    fun onCombineTravelResultsFetched(combineTravel: RealmResults<CombineTravel>)
    fun onCombineEducationResultsFetched(combineEducation: RealmResults<CombineEducation>)
    fun onCombineInterestsResultsFetched(combineInterests: RealmResults<CombineInterests>)
    fun onCombineWellnessResultsFetched(combineWellness: RealmResults<CombineWellness>)
    fun onCombinePersonalResultsFetched(combinePersonal: RealmResults<CombinePersonal>)
    fun onCombineShoppingResultsFetched(combineShopping: RealmResults<CombineShopping>)
    fun onCombineContactsResultsFetched(combineContacts: RealmResults<CombineContacts>)
}