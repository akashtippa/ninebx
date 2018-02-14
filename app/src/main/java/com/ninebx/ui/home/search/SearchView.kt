package com.ninebx.ui.home.search

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.decrypted.DecryptedCombine
import com.ninebx.ui.base.realm.home.homeBanking.Combine

/**
 * Created by Alok on 03/01/18.
 */
interface SearchView : BaseView {
    fun onCombineFetched(combine: DecryptedCombine)
}