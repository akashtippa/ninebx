package com.ninebx.ui.base.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alok on 09/02/18.
 */

public class SignInResponse {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("admin_id")
    @Expose
    private String adminId;
    @SerializedName("is_admin")
    @Expose
    private Boolean isAdmin;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("secure_key")
    @Expose
    private String secureKey;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecureKey() {
        return secureKey;
    }

    public void setSecureKey(String secureKey) {
        this.secureKey = secureKey;
    }

    @Override
    public String toString() {
        return "SignInResponse{" +
                "userId='" + userId + '\'' +
                ", adminId='" + adminId + '\'' +
                ", isAdmin=" + isAdmin +
                ", hash='" + hash + '\'' +
                ", email='" + email + '\'' +
                ", secureKey='" + secureKey + '\'' +
                '}';
    }
}



