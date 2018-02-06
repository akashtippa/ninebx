package com.ninebx.testRealm.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by cognitive on 05/02/18.
 */

public class TestUserContact extends RealmObject implements Parcelable {

    private String strEmail = "";
    private String strContact = "";

    public TestUserContact() {

    }

    public String getStrFirstName() {
        return strEmail;
    }

    public void setStrFirstName(String strFirstName) {
        this.strEmail = strFirstName;
    }

    public String getStrLastName() {
        return strContact;
    }

    public void setStrLastName(String strLastName) {
        this.strContact = strLastName;
    }

    public static Creator<TestUserContact> getCREATOR() {
        return CREATOR;
    }

    protected TestUserContact(Parcel in) {
    }

    public static final Creator<TestUserContact> CREATOR = new Creator<TestUserContact>() {
        @Override
        public TestUserContact createFromParcel(Parcel in) {
            return new TestUserContact(in);
        }

        @Override
        public TestUserContact[] newArray(int size) {
            return new TestUserContact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
