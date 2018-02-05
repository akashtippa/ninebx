package com.ninebx.testRealm.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by cognitive on 05/02/18.
 */

public class TestUserDetails extends RealmObject implements Parcelable {

    private String strFirstName = "";
    private String strLastName = "";

    public TestUserDetails() {

    }

    public String getStrFirstName() {
        return strFirstName;
    }

    public void setStrFirstName(String strFirstName) {
        this.strFirstName = strFirstName;
    }

    public String getStrLastName() {
        return strLastName;
    }

    public void setStrLastName(String strLastName) {
        this.strLastName = strLastName;
    }

    public static Creator<TestUserDetails> getCREATOR() {
        return CREATOR;
    }

    protected TestUserDetails(Parcel in) {
    }

    public static final Creator<TestUserDetails> CREATOR = new Creator<TestUserDetails>() {
        @Override
        public TestUserDetails createFromParcel(Parcel in) {
            return new TestUserDetails(in);
        }

        @Override
        public TestUserDetails[] newArray(int size) {
            return new TestUserDetails[size];
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
