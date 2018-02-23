package com.ninebx.ui.base.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 11/01/18.
 */
@RealmClass
public class SignUp extends RealmObject {

    @Required
    private String fullName  = "";
    @Required private String emailAddress  = "";
    @Required private String password  = "";
    @Required private String user_id  = "";
    @PrimaryKey //@Required
    private long id = 0;

    public SignUp(String fullName, String emailAddress, String password, String user_id, long id) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.user_id = user_id;
        this.id = id;
    }

    public SignUp() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
