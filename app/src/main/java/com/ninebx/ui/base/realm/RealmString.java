package com.ninebx.ui.base.realm;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by smrit on 08-02-2018.
 */

public class RealmString extends RealmObject{
    @Required
    private String stringValue = "";

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public RealmString(){}
    public RealmString(String stringValue) {
        this.stringValue = stringValue;
    }
}