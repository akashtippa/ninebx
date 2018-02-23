package com.ninebx.ui.home.account.interfaces;

import com.ninebx.ui.base.realm.home.memories.MemoryTimeline;

/**
 * Created by cognitive on 15/02/18.
 */

public interface IMemoryAdded {
    void memoryAdded(MemoryTimeline memoryTimeLine);

    void onMemoryEdit(MemoryTimeline memoryTimeLine);

    void onMemoryDeleted(MemoryTimeline memoryTimeline);

    void onDateClicked(String strDate);

}
