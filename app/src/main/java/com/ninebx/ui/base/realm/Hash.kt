package com.ninebx.ui.base.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Alok on 10/01/18.
 */
class Hash(
        @PrimaryKey
        var id : Int = 0,
        var finalPassword: String = "",
        var passcode: String = "",
        var isEnabledTouchId : Boolean = false
) : RealmObject() {
}