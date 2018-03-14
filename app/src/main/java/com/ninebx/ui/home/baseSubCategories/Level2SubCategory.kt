package com.ninebx.ui.home.baseSubCategories

import android.os.Parcel
import android.os.Parcelable

/***
 * Created by TechnoBlogger on 23/01/18.
 */
class Level2SubCategory(
        var title: String = "",
        var titleValue: String = "",
        var inputType: String = "",
        var type: Int = 0,
        var isValueSet: Boolean = false
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(titleValue)
        writeString(inputType)
        writeInt(type)
        writeInt((if (isValueSet) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Level2SubCategory> = object : Parcelable.Creator<Level2SubCategory> {
            override fun createFromParcel(source: Parcel): Level2SubCategory = Level2SubCategory(source)
            override fun newArray(size: Int): Array<Level2SubCategory?> = arrayOfNulls(size)
        }
    }
}