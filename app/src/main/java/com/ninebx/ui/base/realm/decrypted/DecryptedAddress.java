package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.annotations.Ignore;
import io.realm.annotations.Required;

/**
 * Created by Alok on 11/01/18.
 */
public class DecryptedAddress implements Parcelable {

    public static final Creator<DecryptedAddress> CREATOR = new Creator<DecryptedAddress>() {
        @Override
        public DecryptedAddress createFromParcel(Parcel in) {
            return new DecryptedAddress(in);
        }

        @Override
        public DecryptedAddress[] newArray(int size) {
            return new DecryptedAddress[size];
        }
    };
    @Ignore
    public String searchField = "";
    @Required
    private String street_1 = "";
    @Required
    private String street_2 = "";
    @Required
    private String city = "";
    @Required
    private String state = "";
    @Required
    private String zipCode = "";
    @Required
    private String country = "";

    public DecryptedAddress(String street_1, String street_2, String city, String state, String zipCode, String country) {
        this.street_1 = street_1;
        this.street_2 = street_2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public DecryptedAddress() {
    }

    protected DecryptedAddress(Parcel in) {
        street_1 = in.readString();
        street_2 = in.readString();
        city = in.readString();
        state = in.readString();
        zipCode = in.readString();
        country = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street_1);
        dest.writeString(street_2);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zipCode);
        dest.writeString(country);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getStreet_1() {
        return street_1;
    }

    public void setStreet_1(String street_1) {
        this.street_1 = street_1;
    }

    public String getStreet_2() {
        return street_2;
    }

    public void setStreet_2(String street_2) {
        this.street_2 = street_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}

