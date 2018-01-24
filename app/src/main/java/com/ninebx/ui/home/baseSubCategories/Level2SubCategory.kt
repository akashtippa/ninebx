package com.ninebx.ui.home.baseSubCategories

import android.os.Parcel
import android.os.Parcelable

/***
 * Created by TechnoBlogger on 23/01/18.
 */
class Level2SubCategory(
        var title: String = "",
        var subTitle: String = "",
        var drawableString: String = "",
        var type: Int = 0
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(subTitle)
        writeString(drawableString)
        writeInt(type)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<com.ninebx.ui.home.baseSubCategories.Level2SubCategory> = object : Parcelable.Creator<com.ninebx.ui.home.baseSubCategories.Level2SubCategory> {
            override fun createFromParcel(source: Parcel): com.ninebx.ui.home.baseSubCategories.Level2SubCategory = Level2SubCategory(source)
            override fun newArray(size: Int): Array<com.ninebx.ui.home.baseSubCategories.Level2SubCategory?> = arrayOfNulls(size)
        }
    }
}