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
        var type: Int = 0,
        var subCategoryId: String = "",
        var personName : String = ""
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(drawableString)
        writeInt(formsCount)
        writeInt(type)
        writeString(subCategoryId)
        writeString(personName)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SubCategory

        if (title != other.title) return false
        if (subCategoryId != other.subCategoryId) return false
        if (personName != other.personName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + subCategoryId.hashCode()
        result = 31 * result + personName.hashCode()
        return result
    }


    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SubCategory> = object : Parcelable.Creator<SubCategory> {
            override fun createFromParcel(source: Parcel): SubCategory = SubCategory(source)
            override fun newArray(size: Int): Array<SubCategory?> = arrayOfNulls(size)
        }
    }


}