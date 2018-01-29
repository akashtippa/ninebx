package com.ninebx.ui.base.realm.home.education;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 24/01/18.
 */

public class MainEducation extends RealmObject {

    @PrimaryKey
    private Integer id = 0;

    private String selectionType = "";
    private String classType = "MainEducation";

    private String institutionName = "";
    private String qualification = "";

    private String name = "";
    private String location = "";
    private String major = "";
    private String from = "";
    private String to = "";
    private String currentlyStudying = "";

    private Boolean isCurrent = false;

    private String notes = "";

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;

    private String attachmentNames = "";

    private String createdUser = "";

    private RealmList<String> backingImages = new RealmList<>();

    @Ignore
    private List<String> photosId = new ArrayList<>();

    public MainEducation(Integer id, String selectionType, String classType, String institutionName, String qualification, String name, String location, String major, String from, String to, String currentlyStudying, Boolean isCurrent, String notes, String created, String modified, Boolean isPrivate, String attachmentNames, String createdUser) {
        this.id = id;
        this.selectionType = selectionType;
        this.classType = classType;
        this.institutionName = institutionName;
        this.qualification = qualification;
        this.name = name;
        this.location = location;
        this.major = major;
        this.from = from;
        this.to = to;
        this.currentlyStudying = currentlyStudying;
        this.isCurrent = isCurrent;
        this.notes = notes;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.attachmentNames = attachmentNames;
        this.createdUser = createdUser;
    }

    public MainEducation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCurrentlyStudying() {
        return currentlyStudying;
    }

    public void setCurrentlyStudying(String currentlyStudying) {
        this.currentlyStudying = currentlyStudying;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
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
}