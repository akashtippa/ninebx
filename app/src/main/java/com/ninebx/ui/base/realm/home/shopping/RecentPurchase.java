package com.ninebx.ui.base.realm.home.shopping;



import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 29/01/18.
 */

public class RecentPurchase extends RealmObject {

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

    private String brandName = "";
    private String itemName = "";

    private String purchasedBy = "";
    private String purchasedDate = "";
    private String purchasedPrice = "";

    private String notes = "";

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;

    private String attachmentNames = "";

    private String createdUser = "";

    public RecentPurchase(String selectionType, String brandName, String itemName, String purchasedBy, String purchasedDate, String purchasedPrice, String notes, String created, String modified, Boolean isPrivate, String attachmentNames, String createdUser) {
        this.selectionType = selectionType;
        this.brandName = brandName;
        this.itemName = itemName;
        this.purchasedBy = purchasedBy;
        this.purchasedDate = purchasedDate;
        this.purchasedPrice = purchasedPrice;
        this.notes = notes;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.attachmentNames = attachmentNames;
        this.createdUser = createdUser;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy(String purchasedBy) {
        this.purchasedBy = purchasedBy;
    }

    public String getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public String getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(String purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
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
}
