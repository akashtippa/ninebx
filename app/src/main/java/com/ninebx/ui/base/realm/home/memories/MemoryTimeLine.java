package com.ninebx.ui.base.realm.home.memories;

import com.ninebx.ui.base.realm.home.homeBanking.BaseAttachmentObject;

import java.util.Date;

/**
 * Created by Alok on 24/01/18.
 */

public class MemoryTimeLine extends BaseAttachmentObject {

    private String selectionType = "";

    private String title = "";
    private String date = "";
    private String place = "";
    private String contacts = "";
    private String notes = "";

    private String attachmentNames = "";

    private Date selectedDate = new Date();

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;
    private String createdUser = "";

    public MemoryTimeLine(String selectionType, String title, String date, String place, String contacts, String notes, String attachmentNames, Date selectedDate, String created, String modified, Boolean isPrivate, String createdUser) {
        this.selectionType = selectionType;
        this.title = title;
        this.date = date;
        this.place = place;
        this.contacts = contacts;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.selectedDate = selectedDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
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

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
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
}
