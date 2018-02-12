package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
public class DecryptedDevice implements Parcelable {

    public static final Creator<DecryptedDevice> CREATOR = new Creator<DecryptedDevice>() {
        @Override
        public DecryptedDevice createFromParcel(Parcel in) {
            return new DecryptedDevice(in);
        }

        @Override
        public DecryptedDevice[] newArray(int size) {
            return new DecryptedDevice[size];
        }
    };
    @Required
    private String deviceId = "";
    @Required
    private Boolean isTouchIdSet = false;
    @Required
    private String passcode = "";

    public DecryptedDevice(String deviceId, Boolean isTouchIdSet, String passcode) {
        this.deviceId = deviceId;
        this.isTouchIdSet = isTouchIdSet;
        this.passcode = passcode;
    }

    public DecryptedDevice() {
    }

    protected DecryptedDevice(Parcel in) {
        deviceId = in.readString();
        byte tmpIsTouchIdSet = in.readByte();
        isTouchIdSet = tmpIsTouchIdSet == 0 ? null : tmpIsTouchIdSet == 1;
        passcode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(deviceId);
        dest.writeByte((byte) (isTouchIdSet == null ? 0 : isTouchIdSet ? 1 : 2));
        dest.writeString(passcode);
    }

    @Override
    public int describeContents() {
        return 0;
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
