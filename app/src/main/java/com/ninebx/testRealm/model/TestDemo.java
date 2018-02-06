package com.ninebx.testRealm.model;

import io.realm.RealmObject;

/**
 * Created by cognitive on 06/02/18.
 */

public class TestDemo extends RealmObject {
    private String id, name, address;


    public TestDemo() {

    }

    public TestDemo(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
