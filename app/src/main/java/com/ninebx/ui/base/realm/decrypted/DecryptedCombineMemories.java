package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.home.memories.MainMemories;
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline;
import com.ninebx.ui.base.realm.lists.MemoriesList;

import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
public class DecryptedCombineMemories implements Parcelable {

    public static final Creator<DecryptedCombineMemories> CREATOR = new Creator<DecryptedCombineMemories>() {
        @Override
        public DecryptedCombineMemories createFromParcel(Parcel in) {
            return new DecryptedCombineMemories(in);
        }

        @Override
        public DecryptedCombineMemories[] newArray(int size) {
            return new DecryptedCombineMemories[size];
        }
    };
    @PrimaryKey //@Required
    private int id = 0;
    @Required
    private RealmList<MainMemories> mainMemoriesItems = new RealmList<>();
    @Required
    private RealmList<MemoryTimeline> memoryTimelineItems = new RealmList<>();
    @Required
    private RealmList<MemoriesList> listItems = new RealmList<>();

    public DecryptedCombineMemories(int id, RealmList<MainMemories> mainMemoriesItems, RealmList<MemoryTimeline> memoryTimelineItems, RealmList<MemoriesList> listItems) {
        this.id = id;
        this.mainMemoriesItems = mainMemoriesItems;
        this.memoryTimelineItems = memoryTimelineItems;
        this.listItems = listItems;
    }

    protected DecryptedCombineMemories(Parcel in) {
        id = in.readInt();
    }

    public DecryptedCombineMemories() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
