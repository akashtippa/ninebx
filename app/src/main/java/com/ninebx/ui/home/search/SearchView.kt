package com.ninebx.ui.home.search

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.homeBanking.Combine

/**
 * Created by Alok on 03/01/18.
 */
interface SearchView : BaseView {
    fun onCombineFetched(combine: DecryptedCombine)
    fun onCombineMemoryFetched(combineMemory : DecryptedCombineMemories)
    fun onCombineTravelFetched(combineTravel: DecryptedCombineTravel)
    fun onCombineEducationFetched(combineEducation: DecryptedCombineEducation)
    fun onCombineInterestsFetched(combineInterests: DecryptedCombineInterests)
    fun onCombineWellnessFetched(combineWellness: DecryptedCombineWellness)
    fun onCombinePersonalFetched(combinePersonal: DecryptedCombinePersonal)
    fun onCombineShoppingFetched(combineShopping: DecryptedCombineShopping)
    fun onCombineContactsFetched(combineContacts: DecryptedCombineContacts)
}