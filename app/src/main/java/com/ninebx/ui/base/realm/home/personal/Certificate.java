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

public class Certificate extends RealmObject {

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

    private String cer_description = "";
    private String nameOnCertificate = "";

    private String gender = "";
    private String dateOfBirth = "";
    private String timeOfBirth = "";
    private String placeOfBirth = "";

    private String dateOfMarriage = "";
    private String placeOfMarriage = "";

    private String nameOneCertificate = "";
    private String nameTwoCertificate = "";

    private String notes = "";

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;

    private String attachmentNames = "";

    private String createdUser = "";

    public Certificate(String selectionType, String cer_description, String nameOnCertificate, String gender, String dateOfBirth, String timeOfBirth, String placeOfBirth, String dateOfMarriage, String placeOfMarriage, String nameOneCertificate, String nameTwoCertificate, String notes, String created, String modified, Boolean isPrivate, String attachmentNames, String createdUser) {
        this.selectionType = selectionType;
        this.cer_description = cer_description;
        this.nameOnCertificate = nameOnCertificate;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.timeOfBirth = timeOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.dateOfMarriage = dateOfMarriage;
        this.placeOfMarriage = placeOfMarriage;
        this.nameOneCertificate = nameOneCertificate;
        this.nameTwoCertificate = nameTwoCertificate;
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

    public String getCer_description() {
        return cer_description;
    }

    public void setCer_description(String cer_description) {
        this.cer_description = cer_description;
    }

    public String getNameOnCertificate() {
        return nameOnCertificate;
    }

    public void setNameOnCertificate(String nameOnCertificate) {
        this.nameOnCertificate = nameOnCertificate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTimeOfBirth() {
        return timeOfBirth;
    }

    public void setTimeOfBirth(String timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getDateOfMarriage() {
        return dateOfMarriage;
    }

    public void setDateOfMarriage(String dateOfMarriage) {
        this.dateOfMarriage = dateOfMarriage;
    }

    public String getPlaceOfMarriage() {
        return placeOfMarriage;
    }

    public void setPlaceOfMarriage(String placeOfMarriage) {
        this.placeOfMarriage = placeOfMarriage;
    }

    public String getNameOneCertificate() {
        return nameOneCertificate;
    }

    public void setNameOneCertificate(String nameOneCertificate) {
        this.nameOneCertificate = nameOneCertificate;
    }

    public String getNameTwoCertificate() {
        return nameTwoCertificate;
    }

    public void setNameTwoCertificate(String nameTwoCertificate) {
        this.nameTwoCertificate = nameTwoCertificate;
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
