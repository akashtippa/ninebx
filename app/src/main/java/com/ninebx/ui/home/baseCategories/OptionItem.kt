package com.ninebx.ui.home.baseCategories

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Alok on 22/03/18.
 */
class OptionItem(var itemId: Long = 0,
                 var itemName: String = "") : Parcelable {
    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(itemId)
        writeString(itemName)
    }

    override fun toString(): String {
        return itemName
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OptionItem

        if (itemId != other.itemId) return false

        return true
    }

    override fun hashCode(): Int {
        return itemId.hashCode()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OptionItem> = object : Parcelable.Creator<OptionItem> {
            override fun createFromParcel(source: Parcel): OptionItem = OptionItem(source)
            override fun newArray(size: Int): Array<OptionItem?> = arrayOfNulls(size)
        }
    }




}