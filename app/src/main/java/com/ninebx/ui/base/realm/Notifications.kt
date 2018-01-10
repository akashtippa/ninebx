package com.ninebx.ui.base.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Alok on 10/01/18.
 */
class Notifications (
        var message : String = "",
        var boxName : String = "",
        var category : String = "",
        var dueDate : String = "",
        var updatedDate : Date = Date(),
        var subTitle : String = "",
        var notifyDate : String = "",
        var isPrivate : Boolean = false,
        var created : String = "",
        var modified : String = "",
        var read : Boolean = true,
        var form_id : Int = 0,
        @PrimaryKey
        var id : Int = 0
) : RealmObject() {
}