package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.RealmString;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by smrit on 11-02-2018.
 */

public class DecryptedAsset implements Parcelable {
    public static final Creator<DecryptedAsset> CREATOR = new Creator<DecryptedAsset>() {
        @Override
        public DecryptedAsset createFromParcel(Parcel in) {
            return new DecryptedAsset(in);
        }

        @Override
        public DecryptedAsset[] newArray(int size) {
            return new DecryptedAsset[size];
        }
    };
    @PrimaryKey //@Required
            private long id = 0;
    @Required
    private ArrayList<RealmString> backingImages = new ArrayList<>();
    @Ignore
    @Required
    private List<String> photosId = new ArrayList<>();
    @Required
    private String selectionType = "";
    @Required
    private String test = "";
    @Required
    private String assetName = "";
    @Required
    private String descriptionOrLocation = "";
    @Required
    private String estimatedMarketValue = "";
    @Required
    private String serialNumber = "";
    @Required
    private String purchaseDate = "";
    @Required
    private String purchasePrice = "";
    @Required
    private String contacts = "";
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String createdUser = "";
    @Required
    private String notes = "";
    @Required
    private String imageName = "";
    @Required
    private String attachmentNames = "";

    public DecryptedAsset() {
    }

    public DecryptedAsset(long id, ArrayList<RealmString> backingImages, List<String> photosId, String selectionType, String test, String assetName, String descriptionOrLocation, String estimatedMarketValue, String serialNumber, String purchaseDate, String purchasePrice, String contacts, String created, String modified, Boolean isPrivate, String createdUser, String notes, String imageName, String attachmentNames) {
        this.id = id;
        this.backingImages = backingImages;
        this.photosId = photosId;
        this.selectionType = selectionType;
        this.test = test;
        this.assetName = assetName;
        this.descriptionOrLocation = descriptionOrLocation;
        this.estimatedMarketValue = estimatedMarketValue;
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.contacts = contacts;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
        this.notes = notes;
        this.imageName = imageName;
        this.attachmentNames = attachmentNames;
    }

    protected DecryptedAsset(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        test = in.readString();
        assetName = in.readString();
        descriptionOrLocation = in.readString();
        estimatedMarketValue = in.readString();
        serialNumber = in.readString();
        purchaseDate = in.readString();
        purchasePrice = in.readString();
        contacts = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        createdUser = in.readString();
        notes = in.readString();
        imageName = in.readString();
        attachmentNames = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public ArrayList<RealmString> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(ArrayList<RealmString> backingImages) {
        this.backingImages = backingImages;
    }

    public List<String> getPhotosId() {
        photosId = new ArrayList<>();
        for (RealmString realmString : backingImages) {
            photosId.add(realmString.getStringValue());
        }
        return photosId;
    }

    public void setPhotosId(List<String> photosId) {
        this.photosId = photosId;
        backingImages.clear();
        for (String string : photosId) {
            backingImages.add(new RealmString(string));
        }
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getDescriptionOrLocation() {
        return descriptionOrLocation;
    }

    public void setDescriptionOrLocation(String descriptionOrLocation) {
        this.descriptionOrLocation = descriptionOrLocation;
    }

    public String getEstimatedMarketValue() {
        return estimatedMarketValue;
    }

    public void setEstimatedMarketValue(String estimatedMarketValue) {
        this.estimatedMarketValue = estimatedMarketValue;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(test);
        dest.writeString(assetName);
        dest.writeString(descriptionOrLocation);
        dest.writeString(estimatedMarketValue);
        dest.writeString(serialNumber);
        dest.writeString(purchaseDate);
        dest.writeString(purchasePrice);
        dest.writeString(contacts);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(createdUser);
        dest.writeString(notes);
        dest.writeString(imageName);
        dest.writeString(attachmentNames);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "DecryptedAsset{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", test='" + test + '\'' +
                ", assetName='" + assetName + '\'' +
                ", descriptionOrLocation='" + descriptionOrLocation + '\'' +
                ", estimatedMarketValue='" + estimatedMarketValue + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", purchasePrice='" + purchasePrice + '\'' +
                ", contacts='" + contacts + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", createdUser='" + createdUser + '\'' +
                ", notes='" + notes + '\'' +
                ", imageName='" + imageName + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                '}';
    }
}
