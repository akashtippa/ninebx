package com.ninebx.ui.home.baseSubCategories

import android.os.Parcel
import android.os.Parcelable

/***
 * Created by TechnoBlogger on 23/01/18.
 */
class Level2Category(
        var category_id: String = "",
        var title: String = "",
        var drawableString: String = "",
        var subCategories: ArrayList<Level2SubCategory> = ArrayList()
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.createTypedArrayList(Level2SubCategory.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(category_id)
        writeString(title)
        writeString(drawableString)
        writeTypedList(subCategories)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Level2Category

        if (category_id != other.category_id) return false

        return true
    }

    override fun hashCode(): Int {
        return category_id.hashCode()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Level2Category> = object : Parcelable.Creator<Level2Category> {
            override fun createFromParcel(source: Parcel): Level2Category = Level2Category(source)
            override fun newArray(size: Int): Array<Level2Category?> = arrayOfNulls(size)
        }
    }


}