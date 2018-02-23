package com.ninebx.ui.base.realm.decrypted;

/**
 * Created by Alok on 16/02/18.
 */

public class TestSearch {

    private Integer id;
    private String testName;
    private String testName1;
    private String testName2;
    private String testName3;

    public TestSearch(Integer id, String testName) {
        this.id = id;
        this.testName = testName;
    }

    public TestSearch(Integer id, String testName, String testName1, String testName2, String testName3) {
        this.id = id;
        this.testName = testName;
        this.testName1 = testName1;
        this.testName2 = testName2;
        this.testName3 = testName3;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestName1() {
        return testName1;
    }

    public void setTestName1(String testName1) {
        this.testName1 = testName1;
    }

    public String getTestName2() {
        return testName2;
    }

    public void setTestName2(String testName2) {
        this.testName2 = testName2;
    }

    public String getTestName3() {
        return testName3;
    }

    public void setTestName3(String testName3) {
        this.testName3 = testName3;
    }
}
