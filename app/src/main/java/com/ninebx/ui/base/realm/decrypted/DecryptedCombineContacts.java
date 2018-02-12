package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.home.contacts.Contacts;
import com.ninebx.ui.base.realm.home.contacts.MainContacts;
import com.ninebx.ui.base.realm.lists.ContactsList;

import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
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
    private int id = 0;
    @Required
    private RealmList<Contacts> contactsItems = new RealmList<>();
    @Required
    private RealmList<MainContacts> mainContactsItems = new RealmList<>();
    @Required
    private RealmList<ContactsList> listItems = new RealmList<>();

    public DecryptedCombineContacts(int id, RealmList<Contacts> contactsItems, RealmList<MainContacts> mainContactsItems, RealmList<ContactsList> listItems) {
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

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Contacts> getContactsItems() {
        return contactsItems;
    }

    public void setContactsItems(RealmList<Contacts> contactsItems) {
        this.contactsItems = contactsItems;
    }

    public RealmList<MainContacts> getMainContactsItems() {
        return mainContactsItems;
    }

    public void setMainContactsItems(RealmList<MainContacts> mainContactsItems) {
        this.mainContactsItems = mainContactsItems;
    }

    public RealmList<ContactsList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<ContactsList> listItems) {
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
}
