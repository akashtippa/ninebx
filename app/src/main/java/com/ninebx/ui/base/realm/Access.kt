package com.ninebx.ui.base.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Alok on 10/01/18.
 */
class Access(
        @PrimaryKey
        var id : Int = 0,
        var adminId : String = "",
        var adminEmail : String = "",
        var finalHash : String = ""
) : RealmObject() {
}