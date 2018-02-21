package com.ninebx.ui.home.account.interfaces;

import com.ninebx.ui.base.realm.home.contacts.Contacts;

/**
 * Created by cognitive on 15/02/18.
 */

public interface IContactsAdded {
    void contactsAdded(Contacts contacts);

    void contactsEdited(Contacts contacts);

    void contactsDeleted(Contacts contacts);

}
