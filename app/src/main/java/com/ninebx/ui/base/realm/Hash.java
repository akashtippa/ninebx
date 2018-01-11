package com.ninebx.ui.base.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 11/01/18.
 */

public class Hash extends RealmObject {

    @PrimaryKey
    private Integer  id = 0;
    private String  finalPassword = "";
    private String  passcode = "";
    private Boolean  isEnabledTouchId = false;
    
    public Hash() {
    }

    public Hash(Integer id, String finalPassword, String passcode, Boolean isEnabledTouchId) {
        this.id = id;
        this.finalPassword = finalPassword;
        this.passcode = passcode;
        this.isEnabledTouchId = isEnabledTouchId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hash hash = (Hash) o;

        return id != null ? id.equals(hash.id) : hash.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
