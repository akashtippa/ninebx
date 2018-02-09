package com.ninebx.ui.home.model;

/**
 * Created by cognitive on 09/02/18.
 */

public class Contacts {
    private String strId;
    private String strContactName;

    public Contacts(String strId, String strContactName) {
        this.strId = strId;
        this.strContactName = strContactName;
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrContactName() {
        return strContactName;
    }

    public void setStrContactName(String strContactName) {
        this.strContactName = strContactName;
    }
}
