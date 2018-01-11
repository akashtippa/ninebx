package com.ninebx.ui.base.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Alok on 10/01/18.
 */
class RecentSearch(
        @PrimaryKey
        var id : Int = 0,
        var search_id : Int = 0,
        var detail_id : Int = 0,
        var listName : String = "",
        var subCategory : String = "",
        var mainCategory : String = "",
        var createdDate : Date = Date(),
        var classType : String = ""
) : RealmObject() {
}