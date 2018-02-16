package com.ninebx.ui.base.realm

import com.ninebx.ui.home.search.Level3SearchItem

/**
 * Created by Alok on 15/02/18.
 */
interface SearchItemClickListener {
    fun onItemClick(position: Int, searchItem: Level3SearchItem)
}