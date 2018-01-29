package com.ninebx.ui.base.realm.home.wellness;

import com.ninebx.ui.base.realm.home.homeBanking.BaseAttachmentObject;

/**
 * Created by Alok on 29/01/18.
 */

public class MedicalHistory extends BaseAttachmentObject {

    private String selectionType = "";
    private String classType = "MedicalHistory";

    private String history = "";
    private String treatmentDiscription = "";
    private String immunizationDiscription = "";
    private String familyDiscription = "";

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;

    private String notes = "";
    private String attachmentNames = "";
    private String createdUser = "";

    public MedicalHistory(String selectionType, String classType, String history, String treatmentDiscription, String immunizationDiscription, String familyDiscription, String created, String modified, Boolean isPrivate, String notes, String attachmentNames, String createdUser) {
        this.selectionType = selectionType;
        this.classType = classType;
        this.history = history;
        this.treatmentDiscription = treatmentDiscription;
        this.immunizationDiscription = immunizationDiscription;
        this.familyDiscription = familyDiscription;
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

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTreatmentDiscription() {
        return treatmentDiscription;
    }

    public void setTreatmentDiscription(String treatmentDiscription) {
        this.treatmentDiscription = treatmentDiscription;
    }

    public String getImmunizationDiscription() {
        return immunizationDiscription;
    }

    public void setImmunizationDiscription(String immunizationDiscription) {
        this.immunizationDiscription = immunizationDiscription;
    }

    public String getFamilyDiscription() {
        return familyDiscription;
    }

    public void setFamilyDiscription(String familyDiscription) {
        this.familyDiscription = familyDiscription;
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
