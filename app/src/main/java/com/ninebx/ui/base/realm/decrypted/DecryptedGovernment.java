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
 * Created by Alok on 24/01/18.
 */
public class DecryptedGovernment implements Parcelable {

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
    private String idName = "";
    @Ignore public String searchField = "";
    @Required
    private String name = "";
    @Required
    private String nameOnId = "";
    @Required
    private String issuingCountry = "";
    @Required
    private String issuingState = "";
    @Required
    private String idNumber = "";
    @Required
    private String dateIssued = "";
    @Required
    private String expirationDate = "";
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String notes = "";
    @Required
    private String attachmentNames = "";
    @Required
    private String createdUser = "";


    public DecryptedGovernment(String selectionType, String idName, String name, String nameOnId, String issuingCountry, String issuingState, String idNumber, String dateIssued, String expirationDate, String created, String modified, Boolean isPrivate, String notes, String attachmentNames, String createdUser) {
        this.selectionType = selectionType;
        this.idName = idName;
        this.name = name;
        this.nameOnId = nameOnId;
        this.issuingCountry = issuingCountry;
        this.issuingState = issuingState;
        this.idNumber = idNumber;
        this.dateIssued = dateIssued;
        this.expirationDate = expirationDate;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.createdUser = createdUser;
    }

    public DecryptedGovernment() {
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

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOnId() {
        return nameOnId;
    }

    public void setNameOnId(String nameOnId) {
        this.nameOnId = nameOnId;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public String getIssuingState() {
        return issuingState;
    }

    public void setIssuingState(String issuingState) {
        this.issuingState = issuingState;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
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

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    @Override
    public String toString() {
        return "DecryptedGovernment{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", idName='" + idName + '\'' +
                ", name='" + name + '\'' +
                ", nameOnId='" + nameOnId + '\'' +
                ", issuingCountry='" + issuingCountry + '\'' +
                ", issuingState='" + issuingState + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", dateIssued='" + dateIssued + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", notes='" + notes + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", createdUser='" + createdUser + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeList(this.backingImages);
        dest.writeStringList(this.photosId);
        dest.writeString(this.selectionType);
        dest.writeString(this.idName);
        dest.writeString(this.searchField);
        dest.writeString(this.name);
        dest.writeString(this.nameOnId);
        dest.writeString(this.issuingCountry);
        dest.writeString(this.issuingState);
        dest.writeString(this.idNumber);
        dest.writeString(this.dateIssued);
        dest.writeString(this.expirationDate);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.notes);
        dest.writeString(this.attachmentNames);
        dest.writeString(this.createdUser);
    }

    protected DecryptedGovernment(Parcel in) {
        this.id = in.readLong();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
        this.selectionType = in.readString();
        this.idName = in.readString();
        this.searchField = in.readString();
        this.name = in.readString();
        this.nameOnId = in.readString();
        this.issuingCountry = in.readString();
        this.issuingState = in.readString();
        this.idNumber = in.readString();
        this.dateIssued = in.readString();
        this.expirationDate = in.readString();
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.notes = in.readString();
        this.attachmentNames = in.readString();
        this.createdUser = in.readString();
    }

    public static final Creator<DecryptedGovernment> CREATOR = new Creator<DecryptedGovernment>() {
        @Override
        public DecryptedGovernment createFromParcel(Parcel source) {
            return new DecryptedGovernment(source);
        }

        @Override
        public DecryptedGovernment[] newArray(int size) {
            return new DecryptedGovernment[size];
        }
    };
}
