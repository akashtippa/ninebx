package com.ninebx.ui.tutorial

import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

/***
 * Created by TechnoBlogger on 18/12/17.
 */
class WalkThroughItem : Parcelable {

    var titleText: String? = null
        private set
    var subTitleText: String? = null
        private set

    var titleTextOne = -1
        private set
    var titleTextTwo = -1
        private set
    var textDescription = -1
        private set
    var textColor: Int = 0
        private set
    var backgroundColor: Int = 0
        private set
    var foregroundImageRes = -1
        private set
    var backgroundImageRes = -1
        private set
    var titleTextRes = -1
        private set
    var subTitleTextRes = -1
        private set
    var txtDescription = -1
        private set

    var isGif = false
        private set


    constructor(@StringRes titleTextOne: Int, @StringRes  titleTextTwo: Int, @StringRes textDescription: Int, @ColorRes backgroundTextColor: Int, @ColorRes backgroundColorRelative: Int, @DrawableRes backgroundImageRes: Int, @DrawableRes foreroundImageRes: Int) {
        this.titleTextOne = titleTextOne
        this.titleTextTwo = titleTextTwo
        this.textDescription = textDescription
        this.textColor = backgroundTextColor
        this.backgroundColor = backgroundColorRelative
        this.backgroundImageRes = backgroundImageRes
        this.foregroundImageRes = foreroundImageRes
    }

/*

    constructor(titleText: String, subTitleText: String?, @ColorRes backgroundColor: Int, @DrawableRes foregroundImageRes: Int, @DrawableRes backgroundImageRes: Int) {
        this.titleTextOne = titleText
        this.titleTextTwo = subTitleText
        this.backgroundColor = backgroundColor
        this.foregroundImageRes = foregroundImageRes
        this.backgroundImageRes = backgroundImageRes
    }

    constructor(titleText: String, subTitleText: String?, @ColorRes backgroundColor: Int, @DrawableRes foregroundImageRes: Int) {
        this.titleTextOne = titleText
        this.titleTextTwo = subTitleText
        this.backgroundColor = backgroundColor
        this.foregroundImageRes = foregroundImageRes
    }

    constructor(titleText: String, subTitleText: String?, @ColorRes backgroundColor: Int, @DrawableRes foregroundImageRes: Int, isGif: Boolean) {
        this.titleTextOne = titleText
        this.titleTextTwo = subTitleText
        this.backgroundColor = backgroundColor
        this.foregroundImageRes = foregroundImageRes
        this.isGif = isGif
    }
*/

    constructor(@StringRes titleTextRes: Int, @StringRes subTitleTextRes: Int, @ColorRes backgroundColor: Int, @DrawableRes foregroundImageRes: Int, @DrawableRes backgroundImageRes: Int) {
        this.titleTextRes = titleTextRes
        this.subTitleTextRes = subTitleTextRes
        this.backgroundColor = backgroundColor
        this.foregroundImageRes = foregroundImageRes
        this.backgroundImageRes = backgroundImageRes
    }

    constructor(@StringRes titleTextRes: Int, @StringRes subTitleTextRes: Int, @ColorRes backgroundColor: Int, @DrawableRes foregroundImageRes: Int) {
        this.titleTextRes = titleTextRes
        this.subTitleTextRes = subTitleTextRes
        this.backgroundColor = backgroundColor
        this.foregroundImageRes = foregroundImageRes
    }

    override fun describeContents(): Int {
        return 0
    }


    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.titleText)
        dest.writeString(this.subTitleText)
        dest.writeInt(this.backgroundColor)
        dest.writeInt(this.foregroundImageRes)
        dest.writeInt(this.backgroundImageRes)
        dest.writeInt(this.titleTextRes)
        dest.writeInt(this.subTitleTextRes)
        dest.writeInt(if (this.isGif) 1 else 0)
    }

    protected constructor(`in`: Parcel) {
        this.titleText = `in`.readString()
        this.subTitleText = `in`.readString()
        this.backgroundColor = `in`.readInt()
        this.foregroundImageRes = `in`.readInt()
        this.backgroundImageRes = `in`.readInt()
        this.titleTextRes = `in`.readInt()
        this.subTitleTextRes = `in`.readInt()
        this.isGif = `in`.readInt() == 1
    }

    companion object {

        val CREATOR: Parcelable.Creator<WalkThroughItem> = object : Parcelable.Creator<WalkThroughItem> {
            override fun createFromParcel(source: Parcel): WalkThroughItem {
                return WalkThroughItem(source)
            }

            override fun newArray(size: Int): Array<WalkThroughItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}
