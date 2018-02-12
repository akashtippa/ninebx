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
public class DecryptedSignUp implements Parcelable {

    @Required
    private String fullName = "";
    @Required
    private String emailAddress = "";
    @Required
    private String password = "";
    @Required
    private String user_id = "";
    @PrimaryKey //@Required
    private int id = 0;

    public DecryptedSignUp(String fullName, String emailAddress, String password, String user_id, int id) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.user_id = user_id;
        this.id = id;
    }

    public DecryptedSignUp() {
    }

    protected DecryptedSignUp(Parcel in) {
        fullName = in.readString();
        emailAddress = in.readString();
        password = in.readString();
        user_id = in.readString();
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullName);
        dest.writeString(emailAddress);
        dest.writeString(password);
        dest.writeString(user_id);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DecryptedSignUp> CREATOR = new Creator<DecryptedSignUp>() {
        @Override
        public DecryptedSignUp createFromParcel(Parcel in) {
            return new DecryptedSignUp(in);
        }

        @Override
        public DecryptedSignUp[] newArray(int size) {
            return new DecryptedSignUp[size];
        }
    };

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

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
