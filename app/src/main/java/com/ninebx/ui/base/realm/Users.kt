package com.ninebx.ui.base.realm

import io.realm.RealmObject

/**
 * Created by Alok on 10/01/18.
 */
class Users(
        var fullName            : String = "",
        var emailAddress        : String = "",
        var relationship        : String = "",
        var dateOfBirth         : String = "",
        var anniversary         : String = "",
        var gender              : String = "",
        var mobileNumber        : String = "",

        var street_1            : String = "",
        var street_2            : String = "",
        var city                : String = "",
        var state               : String = "",
        var zipCode             : String = "",
        var country             : String = "",

        var id                  : Int = 0,

        var members : ArrayList<Member>                     = ArrayList<Member>()
) : RealmObject()