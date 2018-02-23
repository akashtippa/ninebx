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
public class DecryptedCombineContacts implements Parcelable {

    public static final Creator<DecryptedCombineContacts> CREATOR = new Creator<DecryptedCombineContacts>() {
        @Override
        public DecryptedCombineContacts createFromParcel(Parcel in) {
            return new DecryptedCombineContacts(in);
        }

        @Override
        public DecryptedCombineContacts[] newArray(int size) {
            return new DecryptedCombineContacts[size];
        }
    };
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private RealmList<DecryptedContacts> contactsItems = new RealmList<>();
    @Required
    private RealmList<DecryptedMainContacts> mainContactsItems = new RealmList<>();
    @Required
    private RealmList<DecryptedContactsList> listItems = new RealmList<>();

    public DecryptedCombineContacts(long id, RealmList<DecryptedContacts> contactsItems, RealmList<DecryptedMainContacts> mainContactsItems, RealmList<DecryptedContactsList> listItems) {
        this.id = id;
        this.contactsItems = contactsItems;
        this.mainContactsItems = mainContactsItems;
        this.listItems = listItems;
    }

    public DecryptedCombineContacts() {
    }

    protected DecryptedCombineContacts(Parcel in) {
        id = in.readInt();
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public RealmList<DecryptedContacts> getContactsItems() {
        return contactsItems;
    }

    public void setContactsItems(RealmList<DecryptedContacts> contactsItems) {
        this.contactsItems = contactsItems;
    }

    public RealmList<DecryptedMainContacts> getMainContactsItems() {
        return mainContactsItems;
    }

    public void setMainContactsItems(RealmList<DecryptedMainContacts> mainContactsItems) {
        this.mainContactsItems = mainContactsItems;
    }

    public RealmList<DecryptedContactsList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<DecryptedContactsList> listItems) {
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


    public int getListsCount(String selectionType) {
        int count = 0;
        for (DecryptedContactsList decryptedLicense : listItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getAllContacts(String selectionType) {
        int count = 0;
        for (DecryptedContacts decryptedLicense : contactsItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }


}
