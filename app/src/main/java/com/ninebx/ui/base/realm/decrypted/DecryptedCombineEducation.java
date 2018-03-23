package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by smrit on 16-02-2018.
 */

public class DecryptedCombineEducation implements Parcelable {


    @Ignore
    public String searchField = "";
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private ArrayList<DecryptedEducation> educationItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedMainEducation> mainEducationItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedWork> workItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedEducationList> listItems = new ArrayList<>();

    public DecryptedCombineEducation() {
    }

    public DecryptedCombineEducation(long id, ArrayList<DecryptedEducation> educationItems, ArrayList<DecryptedMainEducation> mainEducationItems, ArrayList<DecryptedWork> workItems, ArrayList<DecryptedEducationList> listItems) {
        this.id = id;
        this.educationItems = educationItems;
        this.mainEducationItems = mainEducationItems;
        this.workItems = workItems;
        this.listItems = listItems;
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public ArrayList<DecryptedEducation> getEducationItems() {
        return educationItems;
    }

    public void setEducationItems(ArrayList<DecryptedEducation> educationItems) {
        this.educationItems = educationItems;
    }

    public ArrayList<DecryptedMainEducation> getMainEducationItems() {
        return mainEducationItems;
    }

    public void setMainEducationItems(ArrayList<DecryptedMainEducation> mainEducationItems) {
        this.mainEducationItems = mainEducationItems;
    }

    public ArrayList<DecryptedWork> getWorkItems() {
        return workItems;
    }

    public void setWorkItems(ArrayList<DecryptedWork> workItems) {
        this.workItems = workItems;
    }

    public ArrayList<DecryptedEducationList> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<DecryptedEducationList> listItems) {
        this.listItems = listItems;
    }


    public int getEducationItemsCount(String selectionType) {
        int count = 0;
        for (DecryptedEducation selectedItem : educationItems) {
            count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getMainEducationItemsCount(String selectionType) {
        int count = 0;
        for (DecryptedMainEducation selectedItem : mainEducationItems) {
            count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getWorkItemsCount(String selectionType) {
        int count = 0;
        for (DecryptedWork selectedItem : workItems) {
            count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getListItemsCount(String selectionType, long detailsId ) {
        int count = 0;
        for (DecryptedEducationList selectedItem : listItems) {
            count += ( selectedItem.getSelectionType().equals(selectionType) && selectedItem.getDetailsId() == detailsId ) ? 1 : 0;
        }
        return count;
    }

    public int getServices(String selectionType) {
        int count = 0;
        for (DecryptedEducation selectedItem : educationItems) {
            count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.searchField);
        dest.writeLong(this.id);
        dest.writeTypedList(this.educationItems);
        dest.writeTypedList(this.mainEducationItems);
        dest.writeTypedList(this.workItems);
        dest.writeTypedList(this.listItems);
    }

    protected DecryptedCombineEducation(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.educationItems = in.createTypedArrayList(DecryptedEducation.CREATOR);
        this.mainEducationItems = in.createTypedArrayList(DecryptedMainEducation.CREATOR);
        this.workItems = in.createTypedArrayList(DecryptedWork.CREATOR);
        this.listItems = in.createTypedArrayList(DecryptedEducationList.CREATOR);
    }

    public static final Creator<DecryptedCombineEducation> CREATOR = new Creator<DecryptedCombineEducation>() {
        @Override
        public DecryptedCombineEducation createFromParcel(Parcel source) {
            return new DecryptedCombineEducation(source);
        }

        @Override
        public DecryptedCombineEducation[] newArray(int size) {
            return new DecryptedCombineEducation[size];
        }
    };
}
