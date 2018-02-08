package com.ninebx.ui.base.realm;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 11/01/18.
 */
@RealmClass
public class Notifications extends RealmObject {

    @Required
    private  String message  = "";
    @Required private  String boxName  = "";
    @Required private  String category  = "";
    @Required private  String dueDate  = "";
    @Required private  Date updatedDate = new Date();
    @Required private  String subTitle  = "";
    @Required private  String notifyDate  = "";
    @Required private  Boolean isPrivate = false;
    @Required private  String created  = "";
    @Required private  String modified  = "";
    @Required private  Boolean read  = true;
    @Required private  Integer form_id  = 0;
    @PrimaryKey
    @Required private  Integer id  = 0;

    public Notifications(String message, String boxName, String category, String dueDate, Date updatedDate, String subTitle, String notifyDate, Boolean isPrivate, String created, String modified, Boolean read, Integer form_id, Integer id) {
        this.message = message;
        this.boxName = boxName;
        this.category = category;
        this.dueDate = dueDate;
        this.updatedDate = updatedDate;
        this.subTitle = subTitle;
        this.notifyDate = notifyDate;
        this.isPrivate = isPrivate;
        this.created = created;
        this.modified = modified;
        this.read = read;
        this.form_id = form_id;
        this.id = id;
    }

    public Notifications() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(String notifyDate) {
        this.notifyDate = notifyDate;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
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

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Integer getForm_id() {
        return form_id;
    }

    public void setForm_id(Integer form_id) {
        this.form_id = form_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notifications that = (Notifications) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
