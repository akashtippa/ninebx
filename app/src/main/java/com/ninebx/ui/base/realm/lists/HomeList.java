package com.ninebx.ui.base.realm.lists;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.Users;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
@RealmClass
public class HomeList extends RealmObject implements Parcelable{

    @PrimaryKey //@Required
    private long id = 0;

    @Required
    private String selectionType = "";
    @Required
    private String classType = "HomeList";

    @Required
    private String listName = "";
    @Required
    private String dueDate = "";

//    @Required
    private long detailsId = 0;
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

    public HomeList(long id, String selectionType, String classType, String listName, String dueDate, long detailsId, Boolean isSelected, Date selectedDate, Date createdDate, String created, String modified, Boolean isPrivate, String createdUser) {
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

    public HomeList() {
    }

    protected HomeList(Parcel in) {
        id = in.readLong();
        selectionType = in.readString();
        classType = in.readString();
        listName = in.readString();
        dueDate = in.readString();

      /*  if (in.readLong() == 0) {
            detailsId = Long.parseLong(null);
        } else {
            detailsId = in.readLong();
        }*/
        detailsId = in.readLong();

        byte tmpIsSelected = in.readByte();
        isSelected = tmpIsSelected == 0 ? null : tmpIsSelected == 1;
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        createdUser = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(selectionType);
        dest.writeString(classType);
        dest.writeString(listName);
        dest.writeString(dueDate);
       /* if (detailsId == 1L) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(detailsId);
        }*/

        dest.writeLong(detailsId);

        dest.writeByte((byte) (isSelected == null ? 0 : isSelected ? 1 : 2));
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(createdUser);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HomeList> CREATOR = new Creator<HomeList>() {
        @Override
        public HomeList createFromParcel(Parcel in) {
            return new HomeList(in);
        }

        @Override
        public HomeList[] newArray(int size) {
            return new HomeList[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(long detailsId) {
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

    public static ArrayList<HomeList> createParcelableList(@NotNull RealmResults<HomeList> currentUsers) {
        ArrayList users = new ArrayList();
        users.addAll(currentUsers);
        return users;
    }
}
