package com.ninebx.ui.base.realm;

import io.realm.RealmObject;

/**
 * Created by Alok on 11/01/18.
 */

public class Member extends RealmObject {

    private String  firstName            = "";
    private String  lastName             = "";
    private String  relationship         = "";
    private String  role                 = "";
    private String  email                = "";

    public Member(String firstName, String lastName, String relationship, String role, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
        this.role = role;
        this.email = email;
    }

    public Member() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
