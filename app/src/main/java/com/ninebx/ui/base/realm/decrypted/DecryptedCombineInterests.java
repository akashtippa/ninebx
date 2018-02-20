package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import io.realm.RealmList;
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
    @PrimaryKey //@Required
    private int id = 0;
    @Required
    private RealmList<DecryptedInterests> interestItems = new RealmList<>();
    @Required
    private RealmList<DecryptedInterestsList> listItems = new RealmList<>();

    public DecryptedCombineInterests() {
    }

    public DecryptedCombineInterests(int id, RealmList<DecryptedInterests> interestItems, RealmList<DecryptedInterestsList> listItems) {
        this.id = id;
        this.interestItems = interestItems;
        this.listItems = listItems;
    }

    protected DecryptedCombineInterests(Parcel in) {
        id = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<DecryptedInterests> getInterestItems() {
        return interestItems;
    }

    public void setInterestItems(RealmList<DecryptedInterests> interestItems) {
        this.interestItems = interestItems;
    }

    public RealmList<DecryptedInterestsList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<DecryptedInterestsList> listItems) {
        this.listItems = listItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
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

    public int getLists(String selectionType) {
        int count = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        for (DecryptedInterestsList selectedItem : listItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
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
