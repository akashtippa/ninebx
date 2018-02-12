package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.annotations.Required;

/**
 * Created by smrit on 08-02-2018.
 */

public class DecryptedRealmString implements Parcelable {
    public static final Creator<DecryptedRealmString> CREATOR = new Creator<DecryptedRealmString>() {
        @Override
        public DecryptedRealmString createFromParcel(Parcel in) {
            return new DecryptedRealmString(in);
        }

        @Override
        public DecryptedRealmString[] newArray(int size) {
            return new DecryptedRealmString[size];
        }
    };
    @Required
    private String stringValue = "";

    protected DecryptedRealmString(Parcel in) {
        stringValue = in.readString();
    }

    public DecryptedRealmString() {
    }

    public DecryptedRealmString(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.stringValue);
    }

    @Override
    public String toString() {
        return "DecryptedRealmString{" +
                "stringValue='" + stringValue + '\'' +
                '}';
    }
}
