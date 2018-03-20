package com.ninebx.ui.base.realm.home.contacts;

import com.ninebx.ui.base.realm.lists.ContactsList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
@RealmClass
public class CombineContacts extends RealmObject {

    @PrimaryKey //@Required
    private long id = 0;

    @Required
    private RealmList<Contacts> contactsItems = new RealmList<>();
    @Required
    private RealmList<MainContacts> mainContactsItems = new RealmList<>();
    @Required
    private RealmList<ContactsList> listItems = new RealmList<>();

    public CombineContacts(long id, RealmList<Contacts> contactsItems, RealmList<MainContacts> mainContactsItems, RealmList<ContactsList> listItems) {
        this.id = id;
        this.contactsItems = contactsItems;
        this.mainContactsItems = mainContactsItems;
        this.listItems = listItems;
    }

    public CombineContacts() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
