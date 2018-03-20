package com.ninebx.ui.home.account.interfaces;

import com.ninebx.ui.base.realm.decrypted.DecryptedContacts;
import com.ninebx.ui.base.realm.home.contacts.Contacts;

/**
 * Created by cognitive on 15/02/18.
 */

public interface IContactsAdded {
    void contactsEdited(DecryptedContacts contacts);

    void contactsClicked(DecryptedContacts contacts, Boolean isEditable);

    void contactsDeleted(DecryptedContacts contacts);

}
