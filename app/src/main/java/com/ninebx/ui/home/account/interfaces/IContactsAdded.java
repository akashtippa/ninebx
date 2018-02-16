package com.ninebx.ui.home.account.interfaces;

import com.ninebx.ui.base.realm.home.contacts.Contacts;
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline;

/**
 * Created by cognitive on 15/02/18.
 */

public interface IContactsAdded {
    void contactsAdded(Contacts contacts);

    void contactsEdited(Contacts contacts);

}
