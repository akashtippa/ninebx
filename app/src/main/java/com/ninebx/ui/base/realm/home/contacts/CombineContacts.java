package com.ninebx.ui.base.realm.home.contacts;

import com.ninebx.ui.base.realm.lists.ContactsList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Alok on 24/01/18.
 */

public class CombineContacts extends RealmObject {



    private Integer id = 0;

    private RealmList<Contacts> contactsItems               = new RealmList<>();
    private RealmList<MainContacts> mainContactsItems           = new RealmList<>();

    private RealmList<ContactsList> listItems                   = new RealmList<>();

    public CombineContacts(Integer id, RealmList<Contacts> contactsItems, RealmList<MainContacts> mainContactsItems, RealmList<ContactsList> listItems) {
        this.id = id;
        this.contactsItems = contactsItems;
        this.mainContactsItems = mainContactsItems;
        this.listItems = listItems;
    }

    public CombineContacts() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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