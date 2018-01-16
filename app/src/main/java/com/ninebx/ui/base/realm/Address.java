package com.ninebx.ui.base.realm;

import io.realm.RealmObject;

/**
 * Created by Alok on 11/01/18.
 */

public class Address extends RealmObject {

    private String street_1             = "";
    private String street_2             = "";
    private String city                 = "";
    private String state                = "";
    private String zipCode              = "";
    private String country              = "";

    public Address(String street_1, String street_2, String city, String state, String zipCode, String country) {
        this.street_1 = street_1;
        this.street_2 = street_2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address() {
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