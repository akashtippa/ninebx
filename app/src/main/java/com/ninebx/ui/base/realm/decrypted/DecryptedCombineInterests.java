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
public class DecryptedCombineInterests implements Parcelable {

    public static final Creator<DecryptedCombineInterests> CREATOR = new Creator<DecryptedCombineInterests>() {
        @Override
        public DecryptedCombineInterests createFromParcel(Parcel in) {
            return new DecryptedCombineInterests(in);
        }

        @Override
        public DecryptedCombineInterests[] newArray(int size) {
            return new DecryptedCombineInterests[size];
        }
    };
    @Ignore
    public String searchField = "";
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private ArrayList<DecryptedInterests> interestItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedInterestsList> listItems = new ArrayList<>();

    public DecryptedCombineInterests() {
    }

    public DecryptedCombineInterests(long id, ArrayList<DecryptedInterests> interestItems, ArrayList<DecryptedInterestsList> listItems) {
        this.id = id;
        this.interestItems = interestItems;
        this.listItems = listItems;
    }

    protected DecryptedCombineInterests(Parcel in) {
        id = in.readInt();
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public ArrayList<DecryptedInterests> getInterestItems() {
        return interestItems;
    }

    public void setInterestItems(ArrayList<DecryptedInterests> interestItems) {
        this.interestItems = interestItems;
    }

    public ArrayList<DecryptedInterestsList> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<DecryptedInterestsList> listItems) {
        this.listItems = listItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
    }

    public int getServicesOrOtherAccounts(String selectionType) {
        int count = 0;
        for (DecryptedInterests decryptedLicense : interestItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getAttachments(String selectionType) {
        int count = 0;
        for (DecryptedInterests decryptedLicense : interestItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getLists(String selectionType, long detailsId ) {
        int count = 0;
        for (DecryptedInterestsList decryptedLicense : listItems) {
            count += ( decryptedLicense.getSelectionType().equals(selectionType) && decryptedLicense.getDetailsId() == detailsId )? 1 : 0;
        }
        return count;
    }

    @Override
    public String toString() {
        return "DecryptedCombineInterests{" +
                "id=" + id +
                ", interestItems=" + interestItems +
                ", listItems=" + listItems +
                '}';
    }
}
