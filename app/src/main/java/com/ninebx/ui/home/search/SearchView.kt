package com.ninebx.ui.home.search

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.decrypted.DecryptedCombine

/**
 * Created by Alok on 03/01/18.
 */
interface SearchView : BaseView {
    fun onCombineFetched(combine: DecryptedCombine)
}