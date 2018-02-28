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

    protected DecryptedCombineMemories(Parcel in) {
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
