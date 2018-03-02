package com.ninebx.ui.base.realm;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;


/**
 * Created by Alok on 11/01/18.
 */

public class Bank {

    @PrimaryKey //@Required
    private long id  = 0;
    @Required private String  institutionName  = "";
    @Required private String  accountName  = "";
    @Required private String  accountType  = "";
    @Required private String  nameOnAccount  = "";
    @Required private String  accountNumber  = "";
    @Required private String  location  = "";
    @Required private String  swiftCode  = "";
    @Required private String  abaRoutingNumber  = "";
    @Required private String  contacts  = "";
    @Required private String  website  = "";
    @Required private String  userName  = "";
    @Required private String  password  = "";
    @Required private String  pin  = "";

    public Bank(long id, String institutionName, String accountName, String accountType, String nameOnAccount, String accountNumber, String location, String swiftCode, String abaRoutingNumber, String contacts, String website, String userName, String password, String pin) {
        this.id = id;
        this.institutionName = institutionName;
        this.accountName = accountName;
        this.accountType = accountType;
        this.nameOnAccount = nameOnAccount;
        this.accountNumber = accountNumber;
        this.location = location;
        this.swiftCode = swiftCode;
        this.abaRoutingNumber = abaRoutingNumber;
        this.contacts = contacts;
        this.website = website;
        this.userName = userName;
        this.password = password;
        this.pin = pin;
    }

    public Bank() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getNameOnAccount() {
        return nameOnAccount;
    }

    public void setNameOnAccount(String nameOnAccount) {
        this.nameOnAccount = nameOnAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getAbaRoutingNumber() {
        return abaRoutingNumber;
    }

    public void setAbaRoutingNumber(String abaRoutingNumber) {
        this.abaRoutingNumber = abaRoutingNumber;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
