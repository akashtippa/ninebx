package com.ninebx.ui.signIn.model;

import io.realm.RealmObject;

/***
 * Created by TechnoBlogger on 19/12/17.
 */

public class UserCredentials extends RealmObject {
    private String strUserName;
    private String strPassword;

    public String getStrUserName() {
        return strUserName;
    }

    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }
}
