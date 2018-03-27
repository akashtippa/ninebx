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
public class DecryptedCombineContacts implements Parcelable {

    @Ignore
    public String searchField = "";
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private ArrayList<DecryptedContacts> contactsItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedMainContacts> mainContactsItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedContactsList> listItems = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public ArrayList<DecryptedContacts> getContactsItems() {
        return contactsItems;
    }

    public void setContactsItems(ArrayList<DecryptedContacts> contactsItems) {
        this.contactsItems = contactsItems;
    }

    public ArrayList<DecryptedMainContacts> getMainContactsItems() {
        return mainContactsItems;
    }

    public void setMainContactsItems(ArrayList<DecryptedMainContacts> mainContactsItems) {
        this.mainContactsItems = mainContactsItems;
    }

    public ArrayList<DecryptedContactsList> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<DecryptedContactsList> listItems) {
        this.listItems = listItems;
    }


    public int getListsCount(String selectionType, long detailsId ) {
        int count = 0;
        for (DecryptedContactsList decryptedContactList : listItems) {
            count += (decryptedContactList.getSelectionType().equals(selectionType) && decryptedContactList.getDetailsId() == detailsId ) ? 1 : 0;
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

    public int getAllContactsTest(String selectionType) {
        int count = 0;
        for (DecryptedMainContacts decryptedLicense : mainContactsItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        //get service account count and return
        return count;
    }

    @Override
    public String toString() {
        return "DecryptedCombineContacts{" +
                "id=" + id +
                ", contactsItems=" + contactsItems +
                ", mainContactsItems=" + mainContactsItems +
                ", listItems=" + listItems +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.searchField);
        dest.writeLong(this.id);
        dest.writeTypedList(this.contactsItems);
        dest.writeTypedList(this.mainContactsItems);
        dest.writeTypedList(this.listItems);
    }

    public DecryptedCombineContacts() {
    }

    protected DecryptedCombineContacts(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.contactsItems = in.createTypedArrayList(DecryptedContacts.CREATOR);
        this.mainContactsItems = in.createTypedArrayList(DecryptedMainContacts.CREATOR);
        this.listItems = in.createTypedArrayList(DecryptedContactsList.CREATOR);
    }

    public static final Creator<DecryptedCombineContacts> CREATOR = new Creator<DecryptedCombineContacts>() {
        @Override
        public DecryptedCombineContacts createFromParcel(Parcel source) {
            return new DecryptedCombineContacts(source);
        }

        @Override
        public DecryptedCombineContacts[] newArray(int size) {
            return new DecryptedCombineContacts[size];
        }
    };
}
