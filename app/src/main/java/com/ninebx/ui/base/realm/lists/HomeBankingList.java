package com.ninebx.ui.base.realm.lists;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 24/01/18.
 */

public class HomeBankingList extends RealmObject {

    @PrimaryKey
    private Integer id = 0;

    private String selectionType = "";

    private String listName = "";
    private String dueDate = "";

    private Integer detailsId = 0;
    private Boolean isSelected = false;

    private Date selectedDate = new Date();
    private Date createdDate = new Date();

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;

    public HomeBankingList(Integer id, String selectionType, String listName, String dueDate, Integer detailsId, Boolean isSelected, Date selectedDate, Date createdDate, String created, String modified, Boolean isPrivate) {
        this.id = id;
        this.selectionType = selectionType;
        this.listName = listName;
        this.dueDate = dueDate;
        this.detailsId = detailsId;
        this.isSelected = isSelected;
        this.selectedDate = selectedDate;
        this.createdDate = createdDate;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
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
}
