package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alok on 11/01/18.
 */
public class DecryptedRecentSearch implements Parcelable {

    public static final Creator<DecryptedRecentSearch> CREATOR = new Creator<DecryptedRecentSearch>() {
        @Override
        public DecryptedRecentSearch createFromParcel(Parcel in) {
            return new DecryptedRecentSearch(in);
        }

        @Override
        public DecryptedRecentSearch[] newArray(int size) {
            return new DecryptedRecentSearch[size];
        }
    };
    @PrimaryKey //@Required
    private int id = 0;
    @Required
    private Integer search_id = 0;
    @Required
    private Integer detail_id = 0;
    @Required
    private String listName = "";
    @Required
    private String subCategory = "";
    @Required
    private String mainCategory = "";
    @Required
    private Date createdDate = new Date();
    @Required
    private String classType = "";

    public DecryptedRecentSearch(int id, Integer search_id, Integer detail_id, String listName, String subCategory, String mainCategory, Date createdDate, String classType) {
        this.id = id;
        this.search_id = search_id;
        this.detail_id = detail_id;
        this.listName = listName;
        this.subCategory = subCategory;
        this.mainCategory = mainCategory;
        this.createdDate = createdDate;
        this.classType = classType;
    }

    public DecryptedRecentSearch() {
    }

    protected DecryptedRecentSearch(Parcel in) {
        id = in.readInt();
        if (in.readByte() == 0) {
            search_id = null;
        } else {
            search_id = in.readInt();
        }
        if (in.readByte() == 0) {
            detail_id = null;
        } else {
            detail_id = in.readInt();
        }
        listName = in.readString();
        subCategory = in.readString();
        mainCategory = in.readString();
        classType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        if (search_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(search_id);
        }
        if (detail_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(detail_id);
        }
        dest.writeString(listName);
        dest.writeString(subCategory);
        dest.writeString(mainCategory);
        dest.writeString(classType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSearch_id() {
        return search_id;
    }

    public void setSearch_id(Integer search_id) {
        this.search_id = search_id;
    }

    public Integer getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(Integer detail_id) {
        this.detail_id = detail_id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    @Override
    public String toString() {
        return "DecryptedRecentSearch{" +
                "id=" + id +
                ", search_id=" + search_id +
                ", detail_id=" + detail_id +
                ", listName='" + listName + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", mainCategory='" + mainCategory + '\'' +
                ", createdDate=" + createdDate +
                ", classType='" + classType + '\'' +
                '}';
    }
}
