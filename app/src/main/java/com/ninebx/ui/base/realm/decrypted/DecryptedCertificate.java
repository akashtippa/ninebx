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

    public static final Creator<DecryptedCertificate> CREATOR = new Creator<DecryptedCertificate>() {
        @Override
        public DecryptedCertificate createFromParcel(Parcel in) {
            return new DecryptedCertificate(in);
        }

        @Override
        public DecryptedCertificate[] newArray(int size) {
            return new DecryptedCertificate[size];
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

    protected DecryptedCertificate(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        cer_description = in.readString();
        nameOnCertificate = in.readString();
        gender = in.readString();
        dateOfBirth = in.readString();
        timeOfBirth = in.readString();
        placeOfBirth = in.readString();
        dateOfMarriage = in.readString();
        placeOfMarriage = in.readString();
        nameOneCertificate = in.readString();
        nameTwoCertificate = in.readString();
        notes = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        attachmentNames = in.readString();
        createdUser = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(cer_description);
        dest.writeString(nameOnCertificate);
        dest.writeString(gender);
        dest.writeString(dateOfBirth);
        dest.writeString(timeOfBirth);
        dest.writeString(placeOfBirth);
        dest.writeString(dateOfMarriage);
        dest.writeString(placeOfMarriage);
        dest.writeString(nameOneCertificate);
        dest.writeString(nameTwoCertificate);
        dest.writeString(notes);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
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
}