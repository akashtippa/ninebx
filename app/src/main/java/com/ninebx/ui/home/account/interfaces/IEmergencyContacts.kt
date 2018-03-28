package com.ninebx.ui.home.account.interfaces

import com.ninebx.ui.base.realm.decrypted.DecryptedEmergencyContacts

/**
 * Created by smrit on 28-03-2018.
 */
interface IEmergencyContacts {

     fun emergencyContactsEdited(contacts: DecryptedEmergencyContacts)

     fun emergencyContactsClicked(contacts: DecryptedEmergencyContacts, isEditable: Boolean)

     fun emergencyContactsDeleted(contacts: DecryptedEmergencyContacts)
}