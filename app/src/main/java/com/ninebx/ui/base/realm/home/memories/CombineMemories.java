package com.ninebx.ui.base.realm.home.memories;

import com.ninebx.ui.base.realm.lists.MemoriesList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 24/01/18.
 */

public class CombineMemories extends RealmObject {

    @PrimaryKey
    private Integer id = 0;

    private RealmList<MainMemories> mainMemoriesItems       = new RealmList<MainMemories>();
    private RealmList<MemoryTimeLine> memoryTimelineItems     = new RealmList<MemoryTimeLine>();

    private RealmList<MemoriesList> listItems               = new RealmList<MemoriesList>();

    public CombineMemories(Integer id, RealmList<MainMemories> mainMemoriesItems, RealmList<MemoryTimeLine> memoryTimelineItems, RealmList<MemoriesList> listItems) {
        this.id = id;
        this.mainMemoriesItems = mainMemoriesItems;
        this.memoryTimelineItems = memoryTimelineItems;
        this.listItems = listItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealmList<MainMemories> getMainMemoriesItems() {
        return mainMemoriesItems;
    }

    public void setMainMemoriesItems(RealmList<MainMemories> mainMemoriesItems) {
        this.mainMemoriesItems = mainMemoriesItems;
    }

    public RealmList<MemoryTimeLine> getMemoryTimelineItems() {
        return memoryTimelineItems;
    }

    public void setMemoryTimelineItems(RealmList<MemoryTimeLine> memoryTimelineItems) {
        this.memoryTimelineItems = memoryTimelineItems;
    }

    public RealmList<MemoriesList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<MemoriesList> listItems) {
        this.listItems = listItems;
    }

    public CombineMemories() {
    }
}
