package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
public class DecryptedCombineMemories implements Parcelable {

    @Ignore
    public String searchField = "";
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private ArrayList<DecryptedMainMemories> mainMemoriesItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedMemoryTimeline> memoryTimelineItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedMemoriesList> listItems = new ArrayList<>();

    public DecryptedCombineMemories() {
    }

    public DecryptedCombineMemories(long id, ArrayList<DecryptedMainMemories> mainMemoriesItems, ArrayList<DecryptedMemoryTimeline> memoryTimelineItems, ArrayList<DecryptedMemoriesList> listItems) {
        this.id = id;
        this.mainMemoriesItems = mainMemoriesItems;
        this.memoryTimelineItems = memoryTimelineItems;
        this.listItems = listItems;
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public ArrayList<DecryptedMainMemories> getMainMemoriesItems() {
        return mainMemoriesItems;
    }

    public void setMainMemoriesItems(ArrayList<DecryptedMainMemories> mainMemoriesItems) {
        this.mainMemoriesItems = mainMemoriesItems;
    }

    public ArrayList<DecryptedMemoryTimeline> getMemoryTimelineItems() {
        return memoryTimelineItems;
    }

    public void setMemoryTimelineItems(ArrayList<DecryptedMemoryTimeline> memoryTimelineItems) {
        this.memoryTimelineItems = memoryTimelineItems;
    }

    public ArrayList<DecryptedMemoriesList> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<DecryptedMemoriesList> listItems) {
        this.listItems = listItems;
    }

    public int getLists(String selectionType, long detailsId ) {
        int count = 0;
        for (DecryptedMemoriesList decryptedLicense : listItems) {
            count += ( decryptedLicense.getSelectionType().equals(selectionType) && decryptedLicense.getDetailsId() == detailsId )  ? 1 : 0;
        }
        return count;
    }

    public int getMemory(String selectionType) {
        int count = 0;
        for (DecryptedMemoryTimeline decryptedLicense : memoryTimelineItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getOther(String selectionType) {
        int count = 0;
        for (DecryptedMainMemories decryptedLicense : mainMemoriesItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DecryptedCombineMemories that = (DecryptedCombineMemories) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.searchField);
        dest.writeLong(this.id);
        dest.writeTypedList(this.mainMemoriesItems);
        dest.writeTypedList(this.memoryTimelineItems);
        dest.writeTypedList(this.listItems);
    }

    protected DecryptedCombineMemories(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.mainMemoriesItems = in.createTypedArrayList(DecryptedMainMemories.CREATOR);
        this.memoryTimelineItems = in.createTypedArrayList(DecryptedMemoryTimeline.CREATOR);
        this.listItems = in.createTypedArrayList(DecryptedMemoriesList.CREATOR);
    }

    public static final Creator<DecryptedCombineMemories> CREATOR = new Creator<DecryptedCombineMemories>() {
        @Override
        public DecryptedCombineMemories createFromParcel(Parcel source) {
            return new DecryptedCombineMemories(source);
        }

        @Override
        public DecryptedCombineMemories[] newArray(int size) {
            return new DecryptedCombineMemories[size];
        }
    };
}
