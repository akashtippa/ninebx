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

    public static final Creator<DecryptedGovernment> CREATOR = new Creator<DecryptedGovernment>() {
        @Override
        public DecryptedGovernment createFromParcel(Parcel in) {
            return new DecryptedGovernment(in);
        }

        @Override
        public DecryptedGovernment[] newArray(int size) {
            return new DecryptedGovernment[size];
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

    protected DecryptedGovernment(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        idName = in.readString();
        name = in.readString();
        nameOnId = in.readString();
        issuingCountry = in.readString();
        issuingState = in.readString();
        idNumber = in.readString();
        dateIssued = in.readString();
        expirationDate = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        notes = in.readString();
        attachmentNames = in.readString();
        createdUser = in.readString();
    }

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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(idName);
        dest.writeString(name);
        dest.writeString(nameOnId);
        dest.writeString(issuingCountry);
        dest.writeString(issuingState);
        dest.writeString(idNumber);
        dest.writeString(dateIssued);
        dest.writeString(expirationDate);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(notes);
        dest.writeString(attachmentNames);
        dest.writeString(createdUser);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
