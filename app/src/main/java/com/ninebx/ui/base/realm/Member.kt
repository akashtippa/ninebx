package com.ninebx.ui.base.realm

import io.realm.RealmObject

/**
 * Created by Alok on 10/01/18.
 */
class Member(
        var firstName           :String = "",
        var lastName            :String = "",
        var relationship        :String = "",
        var role                :String = "",
        var email               :String = ""
) : RealmObject() {
}