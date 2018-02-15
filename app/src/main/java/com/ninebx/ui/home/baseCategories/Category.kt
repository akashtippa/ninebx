package com.ninebx.ui.home.baseCategories

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Alok on 12/01/18.
 */
class Category (
        var category_id: String = "",
        var title: String = "",
        var drawableString: String = "",
        var formsCount: Int = 0,
        var subCategories: ArrayList<SubCategory> = ArrayList()
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.createTypedArrayList(SubCategory.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(category_id)
        writeString(title)
        writeString(drawableString)
        writeInt(formsCount)
        writeTypedList(subCategories)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (category_id != other.category_id) return false

        return true
    }

    override fun hashCode(): Int {
        return category_id.hashCode()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Category> = object : Parcelable.Creator<Category> {
            override fun createFromParcel(source: Parcel): Category = Category(source)
            override fun newArray(size: Int): Array<Category?> = arrayOfNulls(size)
        }
    }


}