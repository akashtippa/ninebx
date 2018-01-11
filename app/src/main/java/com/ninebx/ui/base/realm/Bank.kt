package com.ninebx.ui.base.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Alok on 10/01/18.
 */
class Bank(
        @PrimaryKey
        var id : Int = 0,
        var institutionName : String = "",
        var accountName : String = "",
        var accountType : String = "",
        var nameOnAccount : String = "",
        var accountNumber : String = "",
        var location : String = "",
        var swiftCode : String = "",
        var abaRoutingNumber : String = "",
        var contacts : String = "",
        var website : String = "",
        var userName : String = "",
        var password : String = "",
        var pin : String = ""
) : RealmObject() {
}