package com.ninebx.ui.base.realm.home.memories;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
@RealmClass
public class MainMemories extends RealmObject {

    @PrimaryKey //@Required
    private int id = 0;

    @Required
    private String selectionType = "";
    @Required private String institutionName = "";
    @Required private String accountName = "";

    @Required private String accountType = "";
    @Required private String nameOnAccount = "";
    @Required private String accountNumber = "";
    @Required private String location = "";
    @Required private String swiftCode = "";
    @Required private String abaRoutingNumber = "";
    @Required private String contacts = "";

    @Required private String website = "";
    @Required private String userName = "";
    @Required private String password = "";
    @Required private String pin = "";
    @Required private String paymentMethodOnFile = "";

    @Required private String created = "";
    @Required private String modified = "";
    @Required private Boolean isPrivate = false;

    @Required private String notes = "";
    @Required private String attachmentNames = "";
    @Required private String title = "";
    @Required private String createdUser = "";

    public MainMemories(String selectionType, String institutionName, String accountName, String accountType, String nameOnAccount, String accountNumber, String location, String swiftCode, String abaRoutingNumber, String contacts, String website, String userName, String password, String pin, String paymentMethodOnFile, String created, String modified, Boolean isPrivate, String notes, String attachmentNames, String title, String createdUser) {
        this.selectionType = selectionType;
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
        this.paymentMethodOnFile = paymentMethodOnFile;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.title = title;
        this.createdUser = createdUser;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
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

    public String getPaymentMethodOnFile() {
        return paymentMethodOnFile;
    }

    public void setPaymentMethodOnFile(String paymentMethodOnFile) {
        this.paymentMethodOnFile = paymentMethodOnFile;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public MainMemories() {
    }
}
