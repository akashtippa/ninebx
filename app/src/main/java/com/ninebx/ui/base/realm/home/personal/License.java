package com.ninebx.ui.base.realm.home.personal;

import io.realm.RealmObject;

/**
 * Created by Alok on 24/01/18.
 */

public class License extends RealmObject {

    private String selectionType = "";

    private String lic_description = "";
    private String nameOnLicense = "";
    private String issuingCountry = "";
    private String issuingState = "";
    private String licenseNumber = "";
    private String dateIssued = "";
    private String expirationDate = "";

    private String notes = "";

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;

    private String attachmentNames = "";

    private String createdUser = "";

    public License(String selectionType, String lic_description, String nameOnLicense, String issuingCountry, String issuingState, String licenseNumber, String dateIssued, String expirationDate, String notes, String created, String modified, Boolean isPrivate, String attachmentNames, String createdUser) {
        this.selectionType = selectionType;
        this.lic_description = lic_description;
        this.nameOnLicense = nameOnLicense;
        this.issuingCountry = issuingCountry;
        this.issuingState = issuingState;
        this.licenseNumber = licenseNumber;
        this.dateIssued = dateIssued;
        this.expirationDate = expirationDate;
        this.notes = notes;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.attachmentNames = attachmentNames;
        this.createdUser = createdUser;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getLic_description() {
        return lic_description;
    }

    public void setLic_description(String lic_description) {
        this.lic_description = lic_description;
    }

    public String getNameOnLicense() {
        return nameOnLicense;
    }

    public void setNameOnLicense(String nameOnLicense) {
        this.nameOnLicense = nameOnLicense;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public String getIssuingState() {
        return issuingState;
    }

    public void setIssuingState(String issuingState) {
        this.issuingState = issuingState;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
}
