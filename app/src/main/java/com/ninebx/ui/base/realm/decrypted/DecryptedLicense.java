package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.RealmString;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
public class DecryptedLicense implements Parcelable {
    @Ignore
    public String searchField = "";
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private String selectionType = "";
    @Required
    private String lic_description = "";
    @Required
    private String nameOnLicense = "";
    @Required
    private String issuingCountry = "";
    @Required
    private String issuingState = "";
    @Required
    private String licenseNumber = "";
    @Required
    private String dateIssued = "";
    @Required
    private String expirationDate = "";
    @Required
    private String notes = "";
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String attachmentNames = "";
    @Required
    private String createdUser = "";
    @Required
    private ArrayList<RealmString> backingImages = new ArrayList<>();

    public DecryptedLicense() {
    }

    public DecryptedLicense(long id, String selectionType, String lic_description, String nameOnLicense, String issuingCountry, String issuingState, String licenseNumber, String dateIssued, String expirationDate, String notes, String created, String modified, Boolean isPrivate, String attachmentNames, String createdUser, ArrayList<RealmString> backingImages) {
        this.id = id;
        this.selectionType = selectionType;
        this.lic_description = lic_description;
        this.nameOnLicense = nameOnLicense;
        this.issuingCountry = issuingCountry;
        this.issuingState = issuingState;
        this.licenseNumber = licenseNumber;
        this.dateIssued = dateIssued;
        this.expirationDate = expirationDate;
        this.notes = notes;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.attachmentNames = attachmentNames;
        this.createdUser = createdUser;
        this.backingImages = backingImages;
    }



    public long getId() {
        return id;
    }

    public ArrayList<RealmString> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(ArrayList<RealmString> backingImages) {
        this.backingImages = backingImages;
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

    public String getLic_description() {
        return lic_description;
    }

    public void setLic_description(String lic_description) {
        this.lic_description = lic_description;
    }

    public String getNameOnLicense() {
        return nameOnLicense;
    }

    public void setNameOnLicense(String nameOnLicense) {
        this.nameOnLicense = nameOnLicense;
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

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
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

    @Override
    public String toString() {
        return "DecryptedLicense{" +
                "id=" + id +
                ", selectionType='" + selectionType + '\'' +
                ", lic_description='" + lic_description + '\'' +
                ", nameOnLicense='" + nameOnLicense + '\'' +
                ", issuingCountry='" + issuingCountry + '\'' +
                ", issuingState='" + issuingState + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", dateIssued='" + dateIssued + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", notes='" + notes + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
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
        dest.writeString(this.searchField);
        dest.writeLong(this.id);
        dest.writeString(this.selectionType);
        dest.writeString(this.lic_description);
        dest.writeString(this.nameOnLicense);
        dest.writeString(this.issuingCountry);
        dest.writeString(this.issuingState);
        dest.writeString(this.licenseNumber);
        dest.writeString(this.dateIssued);
        dest.writeString(this.expirationDate);
        dest.writeString(this.notes);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.attachmentNames);
        dest.writeString(this.createdUser);
        dest.writeList(this.backingImages);
    }

    protected DecryptedLicense(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.selectionType = in.readString();
        this.lic_description = in.readString();
        this.nameOnLicense = in.readString();
        this.issuingCountry = in.readString();
        this.issuingState = in.readString();
        this.licenseNumber = in.readString();
        this.dateIssued = in.readString();
        this.expirationDate = in.readString();
        this.notes = in.readString();
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.attachmentNames = in.readString();
        this.createdUser = in.readString();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
    }

    public static final Creator<DecryptedLicense> CREATOR = new Creator<DecryptedLicense>() {
        @Override
        public DecryptedLicense createFromParcel(Parcel source) {
            return new DecryptedLicense(source);
        }

        @Override
        public DecryptedLicense[] newArray(int size) {
            return new DecryptedLicense[size];
        }
    };
}
