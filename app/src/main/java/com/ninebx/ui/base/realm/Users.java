package com.ninebx.ui.base.realm;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Alok on 11/01/18.
 */

public class Users extends RealmObject {

    private  String fullName             = "";
    private  String emailAddress         = "";
    private  String relationship         = "";
    private  String dateOfBirth          = "";
    private  String anniversary          = "";
    private  String gender               = "";
    private  String mobileNumber         = "";

    private  String street_1             = "";
    private  String street_2             = "";
    private  String city                 = "";
    private  String state                = "";
    private  String zipCode              = "";
    private  String country              = "";

    private  Integer id                   = 0;

    private RealmList<Member> members = new RealmList<Member>();


    public Users(String fullName, String emailAddress, String relationship, String dateOfBirth, String anniversary, String gender, String mobileNumber, String street_1, String street_2, String city, String state, String zipCode, String country, Integer id, RealmList<Member> members) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.relationship = relationship;
        this.dateOfBirth = dateOfBirth;
        this.anniversary = anniversary;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.street_1 = street_1;
        this.street_2 = street_2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.id = id;
        this.members = members;
    }

    public Users() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(String anniversary) {
        this.anniversary = anniversary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealmList<Member> getMembers() {
        return members;
    }

    public void setMembers(RealmList<Member> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        return id != null ? id.equals(users.id) : users.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
