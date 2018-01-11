package com.ninebx.ui.base.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Alok on 10/01/18.
 */
class SignUp(
        var fullName : String = "",
        var emailAddress : String = "",
        var password : String = "",
        var user_id : String = "",
        @PrimaryKey
        var id : Int = 0

) : RealmObject() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SignUp

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}