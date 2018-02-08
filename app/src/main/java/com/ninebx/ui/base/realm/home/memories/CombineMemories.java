package com.ninebx.ui.base.realm.home.memories;

import com.ninebx.ui.base.realm.lists.MemoriesList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
@RealmClass
public class CombineMemories extends RealmObject {

    @PrimaryKey //@Required
    private int id = 0;

    @Required private RealmList<MainMemories> mainMemoriesItems       = new RealmList<>();
    @Required private RealmList<MemoryTimeLine> memoryTimelineItems     = new RealmList<>();

    @Required private RealmList<MemoriesList> listItems               = new RealmList<>();

    public CombineMemories(int id, RealmList<MainMemories> mainMemoriesItems, RealmList<MemoryTimeLine> memoryTimelineItems, RealmList<MemoriesList> listItems) {
        this.id = id;
        this.mainMemoriesItems = mainMemoriesItems;
        this.memoryTimelineItems = memoryTimelineItems;
        this.listItems = listItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
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
