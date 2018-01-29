package com.ninebx.ui.base.realm.home.travel;



import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 29/01/18.
 */

public class Vacations extends RealmObject {

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

    private String vac_description = "";
    private String startDate = "";
    private String endDate = "";
    private String placesToVisit_1 = "";
    private String placesToVisit_2 = "";
    private String placesToVisit_3 = "";

    private Boolean plansConfirmed = false;

    private String notes = "";
    private String attachmentNames = "";

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;
    private String createdUser = "";

    public Vacations(String selectionType, String vac_description, String startDate, String endDate, String placesToVisit_1, String placesToVisit_2, String placesToVisit_3, Boolean plansConfirmed, String notes, String attachmentNames, String created, String modified, Boolean isPrivate, String createdUser) {
        this.selectionType = selectionType;
        this.vac_description = vac_description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.placesToVisit_1 = placesToVisit_1;
        this.placesToVisit_2 = placesToVisit_2;
        this.placesToVisit_3 = placesToVisit_3;
        this.plansConfirmed = plansConfirmed;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getVac_description() {
        return vac_description;
    }

    public void setVac_description(String vac_description) {
        this.vac_description = vac_description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPlacesToVisit_1() {
        return placesToVisit_1;
    }

    public void setPlacesToVisit_1(String placesToVisit_1) {
        this.placesToVisit_1 = placesToVisit_1;
    }

    public String getPlacesToVisit_2() {
        return placesToVisit_2;
    }

    public void setPlacesToVisit_2(String placesToVisit_2) {
        this.placesToVisit_2 = placesToVisit_2;
    }

    public String getPlacesToVisit_3() {
        return placesToVisit_3;
    }

    public void setPlacesToVisit_3(String placesToVisit_3) {
        this.placesToVisit_3 = placesToVisit_3;
    }

    public Boolean getPlansConfirmed() {
        return plansConfirmed;
    }

    public void setPlansConfirmed(Boolean plansConfirmed) {
        this.plansConfirmed = plansConfirmed;
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

    public Vacations() {
    }
}
