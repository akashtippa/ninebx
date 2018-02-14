package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.home.interests.Interests;
import com.ninebx.ui.base.realm.lists.InterestsList;

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
    private RealmList<Interests> interestItems = new RealmList<>();
    @Required
    private RealmList<InterestsList> listItems = new RealmList<>();

    public DecryptedCombineInterests(int id, RealmList<Interests> interestItems, RealmList<InterestsList> listItems) {
        this.id = id;
        this.interestItems = interestItems;
        this.listItems = listItems;
    }

    protected DecryptedCombineInterests(Parcel in) {
        id = in.readInt();
    }

    public DecryptedCombineInterests() {
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

    public RealmList<Interests> getInterestItems() {
        return interestItems;
    }

    public void setInterestItems(RealmList<Interests> interestItems) {
        this.interestItems = interestItems;
    }

    public RealmList<InterestsList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<InterestsList> listItems) {
        this.listItems = listItems;
    }
}
