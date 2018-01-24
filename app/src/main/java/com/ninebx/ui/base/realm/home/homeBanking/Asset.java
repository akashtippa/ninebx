package com.ninebx.ui.base.realm.home.homeBanking;

/**
 * Created by Alok on 24/01/18.
 */

public class Asset extends BaseAttachmentObject {

    private String selectionType = "";
    private String test = "";

    private String assetName = "";
    private String descriptionOrLocation = "";

    private String estimatedMarketValue = "";
    private String serialNumber = "";
    private String purchaseDate = "";
    private String purchasePrice = "";
    private String contacts = "";

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;
    private String createdUser = "";

    private String notes = "";

    private String imageName = "";
    private String attachmentNames = "";

    public Asset(String selectionType, String test, String assetName, String descriptionOrLocation, String estimatedMarketValue, String serialNumber, String purchaseDate, String purchasePrice, String contacts, String created, String modified, Boolean isPrivate, String createdUser, String notes, String imageName, String attachmentNames) {
        this.selectionType = selectionType;
        this.test = test;
        this.assetName = assetName;
        this.descriptionOrLocation = descriptionOrLocation;
        this.estimatedMarketValue = estimatedMarketValue;
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.contacts = contacts;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
        this.notes = notes;
        this.imageName = imageName;
        this.attachmentNames = attachmentNames;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getDescriptionOrLocation() {
        return descriptionOrLocation;
    }

    public void setDescriptionOrLocation(String descriptionOrLocation) {
        this.descriptionOrLocation = descriptionOrLocation;
    }

    public String getEstimatedMarketValue() {
        return estimatedMarketValue;
    }

    public void setEstimatedMarketValue(String estimatedMarketValue) {
        this.estimatedMarketValue = estimatedMarketValue;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }
}
