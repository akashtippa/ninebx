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
    private long id = 0;

    @Required private RealmList<MainMemories> mainMemoriesItems       = new RealmList<>();
    @Required private RealmList<MemoryTimeline> memoryTimelineItems     = new RealmList<>();

    @Required private RealmList<MemoriesList> listItems               = new RealmList<>();

    public CombineMemories(long id, RealmList<MainMemories> mainMemoriesItems, RealmList<MemoryTimeline> memoryTimelineItems, RealmList<MemoriesList> listItems) {
        this.id = id;
        this.mainMemoriesItems = mainMemoriesItems;
        this.memoryTimelineItems = memoryTimelineItems;
        this.listItems = listItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RealmList<MainMemories> getMainMemoriesItems() {
        return mainMemoriesItems;
    }

    public void setMainMemoriesItems(RealmList<MainMemories> mainMemoriesItems) {
        this.mainMemoriesItems = mainMemoriesItems;
    }

    public RealmList<MemoryTimeline> getMemoryTimelineItems() {
        return memoryTimelineItems;
    }

    public void setMemoryTimelineItems(RealmList<MemoryTimeline> memoryTimelineItems) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CombineMemories that = (CombineMemories) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
