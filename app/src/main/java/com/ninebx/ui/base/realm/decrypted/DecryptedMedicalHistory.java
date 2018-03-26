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
 * Created by Alok on 29/01/18.
 */
public class DecryptedMedicalHistory implements Parcelable {

    @Ignore public String searchField = "";
    @PrimaryKey //@Required
            long id = 0;
    @Required
    private ArrayList<RealmString> backingImages = new ArrayList<>();
    @Ignore
    @Required
    private List<String> photosId = new ArrayList<>();
    @Required
    private String selectionType = "";
    @Required
    private String classType = "MedicalHistory";
    @Required
    private String history = "";
    @Required
    private String treatmentDiscription = "";
    @Required
    private String immunizationDiscription = "";
    @Required
    private String familyDiscription = "";
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

    public DecryptedMedicalHistory(String selectionType, String classType, String history, String treatmentDiscription, String immunizationDiscription, String familyDiscription, String created, String modified, Boolean isPrivate, String notes, String attachmentNames, String createdUser) {
        this.selectionType = selectionType;
        this.classType = classType;
        this.history = history;
        this.treatmentDiscription = treatmentDiscription;
        this.immunizationDiscription = immunizationDiscription;
        this.familyDiscription = familyDiscription;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.createdUser = createdUser;
    }

    public DecryptedMedicalHistory() {
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

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTreatmentDiscription() {
        return treatmentDiscription;
    }

    public void setTreatmentDiscription(String treatmentDiscription) {
        this.treatmentDiscription = treatmentDiscription;
    }

    public String getImmunizationDiscription() {
        return immunizationDiscription;
    }

    public void setImmunizationDiscription(String immunizationDiscription) {
        this.immunizationDiscription = immunizationDiscription;
    }

    public String getFamilyDiscription() {
        return familyDiscription;
    }

    public void setFamilyDiscription(String familyDiscription) {
        this.familyDiscription = familyDiscription;
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
        return "DecryptedMedicalHistory{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", classType='" + classType + '\'' +
                ", history='" + history + '\'' +
                ", treatmentDiscription='" + treatmentDiscription + '\'' +
                ", immunizationDiscription='" + immunizationDiscription + '\'' +
                ", familyDiscription='" + familyDiscription + '\'' +
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
        dest.writeString(this.searchField);
        dest.writeLong(this.id);
        dest.writeList(this.backingImages);
        dest.writeStringList(this.photosId);
        dest.writeString(this.selectionType);
        dest.writeString(this.classType);
        dest.writeString(this.history);
        dest.writeString(this.treatmentDiscription);
        dest.writeString(this.immunizationDiscription);
        dest.writeString(this.familyDiscription);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.notes);
        dest.writeString(this.attachmentNames);
        dest.writeString(this.createdUser);
    }

    protected DecryptedMedicalHistory(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
        this.selectionType = in.readString();
        this.classType = in.readString();
        this.history = in.readString();
        this.treatmentDiscription = in.readString();
        this.immunizationDiscription = in.readString();
        this.familyDiscription = in.readString();
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.notes = in.readString();
        this.attachmentNames = in.readString();
        this.createdUser = in.readString();
    }

    public static final Creator<DecryptedMedicalHistory> CREATOR = new Creator<DecryptedMedicalHistory>() {
        @Override
        public DecryptedMedicalHistory createFromParcel(Parcel source) {
            return new DecryptedMedicalHistory(source);
        }

        @Override
        public DecryptedMedicalHistory[] newArray(int size) {
            return new DecryptedMedicalHistory[size];
        }
    };
}
