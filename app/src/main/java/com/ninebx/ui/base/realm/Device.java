package com.ninebx.ui.base.realm;

import io.realm.RealmObject;

/**
 * Created by Alok on 24/01/18.
 */

public class Device extends RealmObject {

    private String deviceId       = "";
    private Boolean isTouchIdSet  = false;
    private String passcode       = "";

    public Device(String deviceId, Boolean isTouchIdSet, String passcode) {
        this.deviceId = deviceId;
        this.isTouchIdSet = isTouchIdSet;
        this.passcode = passcode;
    }

    public Device() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getTouchIdSet() {
        return isTouchIdSet;
    }

    public void setTouchIdSet(Boolean touchIdSet) {
        isTouchIdSet = touchIdSet;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}
