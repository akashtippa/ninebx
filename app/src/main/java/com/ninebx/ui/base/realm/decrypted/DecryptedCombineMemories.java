package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

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
    private RealmList<DecryptedMainMemories> mainMemoriesItems = new RealmList<>();
    @Required
    private RealmList<DecryptedMemoryTimeline> memoryTimelineItems = new RealmList<>();
    @Required
    private RealmList<DecryptedMemoriesList> listItems = new RealmList<>();

    public DecryptedCombineMemories() {
    }

    public DecryptedCombineMemories(int id, RealmList<DecryptedMainMemories> mainMemoriesItems, RealmList<DecryptedMemoryTimeline> memoryTimelineItems, RealmList<DecryptedMemoriesList> listItems) {
        this.id = id;
        this.mainMemoriesItems = mainMemoriesItems;
        this.memoryTimelineItems = memoryTimelineItems;
        this.listItems = listItems;
    }

    protected DecryptedCombineMemories(Parcel in) {
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<DecryptedMainMemories> getMainMemoriesItems() {
        return mainMemoriesItems;
    }

    public void setMainMemoriesItems(RealmList<DecryptedMainMemories> mainMemoriesItems) {
        this.mainMemoriesItems = mainMemoriesItems;
    }

    public RealmList<DecryptedMemoryTimeline> getMemoryTimelineItems() {
        return memoryTimelineItems;
    }

    public void setMemoryTimelineItems(RealmList<DecryptedMemoryTimeline> memoryTimelineItems) {
        this.memoryTimelineItems = memoryTimelineItems;
    }

    public RealmList<DecryptedMemoriesList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<DecryptedMemoriesList> listItems) {
        this.listItems = listItems;
    }

    public int getLists(String selectionType) {
        int count = 0;
        for (DecryptedMemoriesList decryptedLicense : listItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }
}
