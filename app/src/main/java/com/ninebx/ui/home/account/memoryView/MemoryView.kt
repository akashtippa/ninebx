package com.ninebx.ui.home.account.memoryView

import com.ninebx.ui.base.BaseView
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline

/**
 * Created by cognitive on 15/02/18.
 */

internal interface MemoryView : BaseView {
    fun onMemoryTimeLine(memoryTimeLine: MemoryTimeline)
    fun showError(error: String)
}
