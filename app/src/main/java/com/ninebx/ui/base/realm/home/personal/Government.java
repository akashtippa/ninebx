package com.ninebx.ui.base.realm.home.personal;



import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 24/01/18.
 */

public class Government extends RealmObject {

    @PrimaryKey
    Integer id = 0;

    private RealmList<String> backingImages = new RealmList<>();

    @Ignore
    private List<String> photosId = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealmList<String> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(RealmList<String> backingImages) {
        this.backingImages = backingImages;
    }

    public List<String> getPhotosId() {
        photosId = new ArrayList<>();
        photosId.addAll( backingImages.subList(0, backingImages.size() - 1));
        return photosId;
    }

    public void setPhotosId(List<String> photosId) {
        this.photosId = photosId;
        backingImages.clear();
        backingImages.addAll(photosId);
    }

    private String selectionType = "";

    private String idName = "";

    private String name = "";

    private String nameOnId = "";
    private String issuingCountry = "";
    private String issuingState = "";
    private String idNumber = "";
    private String dateIssued = "";
    private String expirationDate = "";

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;

    private String notes = "";

    private String attachmentNames = "";

    private String createdUser = "";

    public Government(String selectionType, String idName, String name, String nameOnId, String issuingCountry, String issuingState, String idNumber, String dateIssued, String expirationDate, String created, String modified, Boolean isPrivate, String notes, String attachmentNames, String createdUser) {
        this.selectionType = selectionType;
        this.idName = idName;
        this.name = name;
        this.nameOnId = nameOnId;
        this.issuingCountry = issuingCountry;
        this.issuingState = issuingState;
        this.idNumber = idNumber;
        this.dateIssued = dateIssued;
        this.expirationDate = expirationDate;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.createdUser = createdUser;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOnId() {
        return nameOnId;
    }

    public void setNameOnId(String nameOnId) {
        this.nameOnId = nameOnId;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
}
