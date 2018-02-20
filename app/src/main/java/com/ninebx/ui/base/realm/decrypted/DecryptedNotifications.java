package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alok on 11/01/18.
 */
public class DecryptedNotifications implements Parcelable {

    public static final Creator<DecryptedNotifications> CREATOR = new Creator<DecryptedNotifications>() {
        @Override
        public DecryptedNotifications createFromParcel(Parcel in) {
            return new DecryptedNotifications(in);
        }

        @Override
        public DecryptedNotifications[] newArray(int size) {
            return new DecryptedNotifications[size];
        }
    };
    @Required
    private String message = "";
    @Required
    private String boxName = "";
    @Required
    private String category = "";
    @Required
    private String dueDate = "";
    @Required
    private Date updatedDate = new Date();
    @Required
    private String subTitle = "";
    @Required
    private String notifyDate = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean read = true;
    @Required
    private Integer form_id = 0;
    @PrimaryKey //@Required
    private int id = 0;

    public DecryptedNotifications(String message, String boxName, String category, String dueDate, Date updatedDate, String subTitle, String notifyDate, Boolean isPrivate, String created, String modified, Boolean read, Integer form_id, int id) {
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

    public DecryptedNotifications() {
    }

    protected DecryptedNotifications(Parcel in) {
        message = in.readString();
        boxName = in.readString();
        category = in.readString();
        dueDate = in.readString();
        subTitle = in.readString();
        notifyDate = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        created = in.readString();
        modified = in.readString();
        byte tmpRead = in.readByte();
        read = tmpRead == 0 ? null : tmpRead == 1;
        if (in.readByte() == 0) {
            form_id = null;
        } else {
            form_id = in.readInt();
        }
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeString(boxName);
        dest.writeString(category);
        dest.writeString(dueDate);
        dest.writeString(subTitle);
        dest.writeString(notifyDate);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (read == null ? 0 : read ? 1 : 2));
        if (form_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(form_id);
        }
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DecryptedNotifications{" +
                "message='" + message + '\'' +
                ", boxName='" + boxName + '\'' +
                ", category='" + category + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", updatedDate=" + updatedDate +
                ", subTitle='" + subTitle + '\'' +
                ", notifyDate='" + notifyDate + '\'' +
                ", isPrivate=" + isPrivate +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", read=" + read +
                ", form_id=" + form_id +
                ", id=" + id +
                '}';
    }
}
