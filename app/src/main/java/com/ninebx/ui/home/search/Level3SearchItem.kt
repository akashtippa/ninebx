package com.ninebx.ui.home.search

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Alok on 15/02/18.
 */
class Level3SearchItem(
        var searchCategory: Int = 0,
        var itemName: String = "",
        var categoryName: String = "",
        var itemIndex : Int = 0
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(searchCategory)
        writeString(itemName)
        writeString(categoryName)
        writeInt(itemIndex)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Level3SearchItem> = object : Parcelable.Creator<Level3SearchItem> {
            override fun createFromParcel(source: Parcel): Level3SearchItem = Level3SearchItem(source)
            override fun newArray(size: Int): Array<Level3SearchItem?> = arrayOfNulls(size)
        }
    }
}