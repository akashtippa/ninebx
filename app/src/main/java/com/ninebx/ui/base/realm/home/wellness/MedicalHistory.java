package com.ninebx.ui.base.realm.home.wellness;



import com.ninebx.ui.base.realm.RealmString;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 29/01/18.
 */
@RealmClass
public class MedicalHistory extends RealmObject {

    @PrimaryKey
    Integer id = 0;

    @Required
    private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    @Required private List<String> photosId = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealmList<RealmString> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(RealmList<RealmString> backingImages) {
        this.backingImages = backingImages;
    }

    public List<String> getPhotosId() {
        photosId = new ArrayList<>();
        for( RealmString realmString : backingImages ) {
            photosId.add( realmString.getStringValue() );
        }
        return photosId;
    }

    public void setPhotosId(List<String> photosId) {
        this.photosId = photosId;
        backingImages.clear();
        for( String string : photosId ) {
            backingImages.add( new RealmString(string) );
        }
    }

    @Required private String selectionType = "";
    @Required private String classType = "MedicalHistory";

    @Required private String history = "";
    @Required private String treatmentDiscription = "";
    @Required private String immunizationDiscription = "";
    @Required private String familyDiscription = "";

    @Required private String created = "";
    @Required private String modified = "";
    @Required private Boolean isPrivate = false;

    @Required private String notes = "";
    @Required private String attachmentNames = "";
    @Required private String createdUser = "";

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

    public MedicalHistory() {
    }
}
