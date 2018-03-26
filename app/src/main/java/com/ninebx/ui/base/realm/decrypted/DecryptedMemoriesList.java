package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
public class DecryptedMemoriesList implements Parcelable {

    @Ignore
    public String searchField = "";
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private String selectionType = "";
    @Required
    private String classType = "MemoriesList";
    @Required
    private String listName = "";
    @Required
    private String dueDate = "";
    @Required
    private Integer detailsId = 0;
    @Required
    private Boolean isSelected = false;
    @Required
    private Date selectedDate = new Date();
    @Required
    private Date createdDate = new Date();
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String createdUser = "";

    public DecryptedMemoriesList(long id, String selectionType, String classType, String listName, String dueDate, Integer detailsId, Boolean isSelected, Date selectedDate, Date createdDate, String created, String modified, Boolean isPrivate, String createdUser) {
        this.id = id;
        this.selectionType = selectionType;
        this.classType = classType;
        this.listName = listName;
        this.dueDate = dueDate;
        this.detailsId = detailsId;
        this.isSelected = isSelected;
        this.selectedDate = selectedDate;
        this.createdDate = createdDate;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
    }

    public DecryptedMemoriesList() {
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
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

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    @Override
    public String toString() {
        return "DecryptedMemoriesList{" +
                "id=" + id +
                ", selectionType='" + selectionType + '\'' +
                ", classType='" + classType + '\'' +
                ", listName='" + listName + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", detailsId=" + detailsId +
                ", isSelected=" + isSelected +
                ", selectedDate=" + selectedDate +
                ", createdDate=" + createdDate +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", createdUser='" + createdUser + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.searchField);
        dest.writeLong(this.id);
        dest.writeString(this.selectionType);
        dest.writeString(this.classType);
        dest.writeString(this.listName);
        dest.writeString(this.dueDate);
        dest.writeValue(this.detailsId);
        dest.writeValue(this.isSelected);
        dest.writeLong(this.selectedDate != null ? this.selectedDate.getTime() : -1);
        dest.writeLong(this.createdDate != null ? this.createdDate.getTime() : -1);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.createdUser);
    }

    protected DecryptedMemoriesList(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.selectionType = in.readString();
        this.classType = in.readString();
        this.listName = in.readString();
        this.dueDate = in.readString();
        this.detailsId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isSelected = (Boolean) in.readValue(Boolean.class.getClassLoader());
        long tmpSelectedDate = in.readLong();
        this.selectedDate = tmpSelectedDate == -1 ? null : new Date(tmpSelectedDate);
        long tmpCreatedDate = in.readLong();
        this.createdDate = tmpCreatedDate == -1 ? null : new Date(tmpCreatedDate);
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.createdUser = in.readString();
    }

    public static final Creator<DecryptedMemoriesList> CREATOR = new Creator<DecryptedMemoriesList>() {
        @Override
        public DecryptedMemoriesList createFromParcel(Parcel source) {
            return new DecryptedMemoriesList(source);
        }

        @Override
        public DecryptedMemoriesList[] newArray(int size) {
            return new DecryptedMemoriesList[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DecryptedMemoriesList that = (DecryptedMemoriesList) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
