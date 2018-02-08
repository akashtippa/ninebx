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
public class RecentSearch extends RealmObject {

    @PrimaryKey
    private  Integer id  = 0;
    @Required private  Integer search_id  = 0;
    @Required private  Integer detail_id  = 0;
    @Required private  String listName  = "";
    @Required private  String subCategory  = "";
    @Required private  String mainCategory  = "";
    @Required private  Date createdDate = new Date();
    @Required private  String classType  = "";

    public RecentSearch(Integer id, Integer search_id, Integer detail_id, String listName, String subCategory, String mainCategory, Date createdDate, String classType) {
        this.id = id;
        this.search_id = search_id;
        this.detail_id = detail_id;
        this.listName = listName;
        this.subCategory = subCategory;
        this.mainCategory = mainCategory;
        this.createdDate = createdDate;
        this.classType = classType;
    }

    public RecentSearch() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecentSearch that = (RecentSearch) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
