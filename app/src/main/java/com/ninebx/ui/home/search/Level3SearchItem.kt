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
        var categoryId:String = "",
        var itemIndex : Int = 0,
        var itemId : Long = 0
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(searchCategory)
        writeString(itemName)
        writeString(categoryName)
        writeString(categoryId)
        writeInt(itemIndex)
        writeLong(itemId)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Level3SearchItem

        if (categoryId != other.categoryId) return false
        if (itemId != other.itemId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = categoryId.hashCode()
        result = 31 * result + itemId.hashCode()
        return result
    }


    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Level3SearchItem> = object : Parcelable.Creator<Level3SearchItem> {
            override fun createFromParcel(source: Parcel): Level3SearchItem = Level3SearchItem(source)
            override fun newArray(size: Int): Array<Level3SearchItem?> = arrayOfNulls(size)
        }
    }


}