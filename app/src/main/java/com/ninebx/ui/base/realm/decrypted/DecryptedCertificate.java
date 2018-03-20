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
public class DecryptedCertificate implements Parcelable {

    @Ignore public String searchField = "";
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
    private String cer_description = "";
    @Required
    private String nameOnCertificate = "";
    @Required
    private String gender = "";
    @Required
    private String dateOfBirth = "";
    @Required
    private String timeOfBirth = "";
    @Required
    private String placeOfBirth = "";
    @Required
    private String dateOfMarriage = "";
    @Required
    private String placeOfMarriage = "";
    @Required
    private String nameOneCertificate = "";
    @Required
    private String nameTwoCertificate = "";
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

    public DecryptedCertificate() {
    }

    public DecryptedCertificate(long id, ArrayList<RealmString> backingImages, List<String> photosId, String selectionType, String cer_description, String nameOnCertificate, String gender, String dateOfBirth, String timeOfBirth, String placeOfBirth, String dateOfMarriage, String placeOfMarriage, String nameOneCertificate, String nameTwoCertificate, String notes, String created, String modified, Boolean isPrivate, String attachmentNames, String createdUser) {
        this.id = id;
        this.backingImages = backingImages;
        this.photosId = photosId;
        this.selectionType = selectionType;
        this.cer_description = cer_description;
        this.nameOnCertificate = nameOnCertificate;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.timeOfBirth = timeOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.dateOfMarriage = dateOfMarriage;
        this.placeOfMarriage = placeOfMarriage;
        this.nameOneCertificate = nameOneCertificate;
        this.nameTwoCertificate = nameTwoCertificate;
        this.notes = notes;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.attachmentNames = attachmentNames;
        this.createdUser = createdUser;
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
        return photosId;
    }

    public void setPhotosId(List<String> photosId) {
        this.photosId = photosId;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getCer_description() {
        return cer_description;
    }

    public void setCer_description(String cer_description) {
        this.cer_description = cer_description;
    }

    public String getNameOnCertificate() {
        return nameOnCertificate;
    }

    public void setNameOnCertificate(String nameOnCertificate) {
        this.nameOnCertificate = nameOnCertificate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTimeOfBirth() {
        return timeOfBirth;
    }

    public void setTimeOfBirth(String timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getDateOfMarriage() {
        return dateOfMarriage;
    }

    public void setDateOfMarriage(String dateOfMarriage) {
        this.dateOfMarriage = dateOfMarriage;
    }

    public String getPlaceOfMarriage() {
        return placeOfMarriage;
    }

    public void setPlaceOfMarriage(String placeOfMarriage) {
        this.placeOfMarriage = placeOfMarriage;
    }

    public String getNameOneCertificate() {
        return nameOneCertificate;
    }

    public void setNameOneCertificate(String nameOneCertificate) {
        this.nameOneCertificate = nameOneCertificate;
    }

    public String getNameTwoCertificate() {
        return nameTwoCertificate;
    }

    public void setNameTwoCertificate(String nameTwoCertificate) {
        this.nameTwoCertificate = nameTwoCertificate;
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
        return "DecryptedCertificate{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", cer_description='" + cer_description + '\'' +
                ", nameOnCertificate='" + nameOnCertificate + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", timeOfBirth='" + timeOfBirth + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", dateOfMarriage='" + dateOfMarriage + '\'' +
                ", placeOfMarriage='" + placeOfMarriage + '\'' +
                ", nameOneCertificate='" + nameOneCertificate + '\'' +
                ", nameTwoCertificate='" + nameTwoCertificate + '\'' +
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
        dest.writeList(this.backingImages);
        dest.writeStringList(this.photosId);
        dest.writeString(this.selectionType);
        dest.writeString(this.cer_description);
        dest.writeString(this.nameOnCertificate);
        dest.writeString(this.gender);
        dest.writeString(this.dateOfBirth);
        dest.writeString(this.timeOfBirth);
        dest.writeString(this.placeOfBirth);
        dest.writeString(this.dateOfMarriage);
        dest.writeString(this.placeOfMarriage);
        dest.writeString(this.nameOneCertificate);
        dest.writeString(this.nameTwoCertificate);
        dest.writeString(this.notes);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.attachmentNames);
        dest.writeString(this.createdUser);
    }

    protected DecryptedCertificate(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
        this.selectionType = in.readString();
        this.cer_description = in.readString();
        this.nameOnCertificate = in.readString();
        this.gender = in.readString();
        this.dateOfBirth = in.readString();
        this.timeOfBirth = in.readString();
        this.placeOfBirth = in.readString();
        this.dateOfMarriage = in.readString();
        this.placeOfMarriage = in.readString();
        this.nameOneCertificate = in.readString();
        this.nameTwoCertificate = in.readString();
        this.notes = in.readString();
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.attachmentNames = in.readString();
        this.createdUser = in.readString();
    }

    public static final Creator<DecryptedCertificate> CREATOR = new Creator<DecryptedCertificate>() {
        @Override
        public DecryptedCertificate createFromParcel(Parcel source) {
            return new DecryptedCertificate(source);
        }

        @Override
        public DecryptedCertificate[] newArray(int size) {
            return new DecryptedCertificate[size];
        }
    };
}