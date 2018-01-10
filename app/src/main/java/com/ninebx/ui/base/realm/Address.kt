package com.ninebx.ui.base.realm

import io.realm.RealmObject

/**
 * Created by Alok on 10/01/18.
 */
class Address(
        var street_1            : String = "",
        var street_2            : String = "",
        var city                : String = "",
        var state               : String = "",
        var zipCode             : String = "",
        var country             : String = ""
) : RealmObject() {

}