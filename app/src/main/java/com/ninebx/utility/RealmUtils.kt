package com.ninebx.utility

import io.realm.Realm
import io.realm.edRealmObject

/**
 * Created by Alok on 18/01/18.
 */

fun saveRealmObject( realmObject: RealmObject ) {
    Realm.getDefaultInstance().executeTransactionAsync ( object : Realm.Transaction {
        override fun execute(realm: Realm?) {

        }

    })
}