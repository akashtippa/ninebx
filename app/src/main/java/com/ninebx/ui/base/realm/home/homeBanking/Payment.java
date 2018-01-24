package com.ninebx.ui.base.realm.home.homeBanking;

/**
 * Created by Alok on 24/01/18.
 */

public class Payment extends BaseAttachmentObject {

    private String selectionType = "";

    private String insuranceCompany = "";
    private String insuredProperty = "";
    private String insuredVehicle = "";
    private String insuredPerson = "";

    private String policyNumber = "";
    private String policyEffectiveDate = "";
    private String policyExpirationDate = "";
    private String contacts = "";

    private String website = "";
    private String userName = "";
    private String password = "";
    private String pin = "";

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;
    private String createdUser = "";

    private String notes = "";

    private String attachmentNames = "";

    public Payment(String selectionType, String insuranceCompany, String insuredProperty, String insuredVehicle, String insuredPerson, String policyNumber, String policyEffectiveDate, String policyExpirationDate, String contacts, String website, String userName, String password, String pin, String created, String modified, Boolean isPrivate, String createdUser, String notes, String attachmentNames) {
        this.selectionType = selectionType;
        this.insuranceCompany = insuranceCompany;
        this.insuredProperty = insuredProperty;
        this.insuredVehicle = insuredVehicle;
        this.insuredPerson = insuredPerson;
        this.policyNumber = policyNumber;
        this.policyEffectiveDate = policyEffectiveDate;
        this.policyExpirationDate = policyExpirationDate;
        this.contacts = contacts;
        this.website = website;
        this.userName = userName;
        this.password = password;
        this.pin = pin;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuredProperty() {
        return insuredProperty;
    }

    public void setInsuredProperty(String insuredProperty) {
        this.insuredProperty = insuredProperty;
    }

    public String getInsuredVehicle() {
        return insuredVehicle;
    }

    public void setInsuredVehicle(String insuredVehicle) {
        this.insuredVehicle = insuredVehicle;
    }

    public String getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(String insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyEffectiveDate() {
        return policyEffectiveDate;
    }

    public void setPolicyEffectiveDate(String policyEffectiveDate) {
        this.policyEffectiveDate = policyEffectiveDate;
    }

    public String getPolicyExpirationDate() {
        return policyExpirationDate;
    }

    public void setPolicyExpirationDate(String policyExpirationDate) {
        this.policyExpirationDate = policyExpirationDate;
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

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
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
}
