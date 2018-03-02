package com.ninebx.ui.base.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 11/01/18.
 */
@RealmClass
public class Hash extends RealmObject {

    @PrimaryKey //@Required
    private long id = 0;
    @Required private String  finalPassword = "";
    @Required private String  passcode = "";
    @Required private Boolean  isEnabledTouchId = false;
    
    public Hash() {
    }

    public Hash(int id, String finalPassword, String passcode, Boolean isEnabledTouchId) {
        this.id = id;
        this.finalPassword = finalPassword;
        this.passcode = passcode;
        this.isEnabledTouchId = isEnabledTouchId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFinalPassword() {
        return finalPassword;
    }

    public void setFinalPassword(String finalPassword) {
        this.finalPassword = finalPassword;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public Boolean getEnabledTouchId() {
        return isEnabledTouchId;
    }

    public void setEnabledTouchId(Boolean enabledTouchId) {
        isEnabledTouchId = enabledTouchId;
    }

}
