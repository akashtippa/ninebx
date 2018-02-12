package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 11/01/18.
 */
public class DecryptedHash implements Parcelable {

    @PrimaryKey //@Required
    private int id = 0;
    @Required private String  finalPassword = "";
    @Required private String  passcode = "";
    @Required private Boolean  isEnabledTouchId = false;

    public DecryptedHash() {
    }

    public DecryptedHash(int id, String finalPassword, String passcode, Boolean isEnabledTouchId) {
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
        dest.writeInt(id);
        dest.writeString(finalPassword);
        dest.writeString(passcode);
        dest.writeByte((byte) (isEnabledTouchId == null ? 0 : isEnabledTouchId ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
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
