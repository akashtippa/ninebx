package com.ninebx.ui.base.realm;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
@RealmClass
public class Device extends RealmObject {

    @Required
    private String deviceId       = "";
    @Required private Boolean isTouchIdSet  = false;
    @Required private String passcode       = "";

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
