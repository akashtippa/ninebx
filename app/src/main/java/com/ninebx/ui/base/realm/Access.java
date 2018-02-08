package com.ninebx.ui.base.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 11/01/18.
 */

@RealmClass
public class Access extends RealmObject {

    @PrimaryKey

    @Required private Integer  id = 0;

    @Required private String  adminId  = "";
    @Required private String  adminEmail  = "";
    @Required private String  finalHash  = "";

    public Access(Integer id, String adminId, String adminEmail, String finalHash) {
        this.id = id;
        this.adminId = adminId;
        this.adminEmail = adminEmail;
        this.finalHash = finalHash;
    }

    public Access() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getFinalHash() {
        return finalHash;
    }

    public void setFinalHash(String finalHash) {
        this.finalHash = finalHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Access access = (Access) o;

        return id != null ? id.equals(access.id) : access.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
