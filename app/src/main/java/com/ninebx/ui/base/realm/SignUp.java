package com.ninebx.ui.base.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 11/01/18.
 */

public class SignUp extends RealmObject {

    private String fullName  = "";
    private String emailAddress  = "";
    private String password  = "";
    private String user_id  = "";
    @PrimaryKey
    private Integer id = 0;

    public SignUp(String fullName, String emailAddress, String password, String user_id, Integer id) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.user_id = user_id;
        this.id = id;
    }

    public SignUp() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SignUp signUp = (SignUp) o;

        return id != null ? id.equals(signUp.id) : signUp.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
