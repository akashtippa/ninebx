package com.ninebx.ui.home.baseCategories

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Alok on 12/01/18.
 */
class SubCategory(
        var title: String = "",
        var drawableString: String = "",
        var formsCount: Int = 0,
        var type: Int = 0
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(drawableString)
        writeInt(formsCount)
        writeInt(type)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SubCategory> = object : Parcelable.Creator<SubCategory> {
            override fun createFromParcel(source: Parcel): SubCategory = SubCategory(source)
            override fun newArray(size: Int): Array<SubCategory?> = arrayOfNulls(size)
        }
    }
}