package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alok on 11/01/18.
 */
public class DecryptedHash implements Parcelable {

    public static final Creator<DecryptedHash> CREATOR = new Creator<DecryptedHash>() {
        @Override
        public DecryptedHash createFromParcel(Parcel in) {
            return new DecryptedHash(in);
        }

        @Override
        public DecryptedHash[] newArray(int size) {
            return new DecryptedHash[size];
        }
    };
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private String finalPassword = "";
    @Required
    private String passcode = "";
    @Required
    private Boolean isEnabledTouchId = false;

    public DecryptedHash() {
    }

    public DecryptedHash(long id, String finalPassword, String passcode, Boolean isEnabledTouchId) {
        this.id = id;
        this.finalPassword = finalPassword;
        this.passcode = passcode;
        this.isEnabledTouchId = isEnabledTouchId;
    }

    protected DecryptedHash(Parcel in) {
        id = in.readInt();
        finalPassword = in.readString();
        passcode = in.readString();
        byte tmpIsEnabledTouchId = in.readByte();
        isEnabledTouchId = tmpIsEnabledTouchId == 0 ? null : tmpIsEnabledTouchId == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(finalPassword);
        dest.writeString(passcode);
        dest.writeByte((byte) (isEnabledTouchId == null ? 0 : isEnabledTouchId ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
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
