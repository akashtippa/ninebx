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
public class DecryptedMedications implements Parcelable {

    public static final Creator<DecryptedMedications> CREATOR = new Creator<DecryptedMedications>() {
        @Override
        public DecryptedMedications createFromParcel(Parcel in) {
            return new DecryptedMedications(in);
        }

        @Override
        public DecryptedMedications[] newArray(int size) {
            return new DecryptedMedications[size];
        }
    };
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
    private String classType = "Medications";
    @Required
    private String name = "";
    @Required
    private String strength = "";
    @Required
    private String frequency = "";
    @Required
    private String startDate = "";
    @Required
    private String endDate = "";
    @Required
    private String notes = "";
    @Required
    private String attachmentNames = "";
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String createdUser = "";

    protected DecryptedMedications(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        classType = in.readString();
        name = in.readString();
        strength = in.readString();
        frequency = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        notes = in.readString();
        attachmentNames = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        createdUser = in.readString();
    }

    public DecryptedMedications(String selectionType, String classType, String name, String strength, String frequency, String startDate, String endDate, String notes, String attachmentNames, String created, String modified, Boolean isPrivate, String createdUser) {
        this.selectionType = selectionType;
        this.classType = classType;
        this.name = name;
        this.strength = strength;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
    }

    public DecryptedMedications() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(classType);
        dest.writeString(name);
        dest.writeString(strength);
        dest.writeString(frequency);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(notes);
        dest.writeString(attachmentNames);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
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

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    @Override
    public String toString() {
        return "DecryptedMedications{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", classType='" + classType + '\'' +
                ", name='" + name + '\'' +
                ", strength='" + strength + '\'' +
                ", frequency='" + frequency + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", notes='" + notes + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", createdUser='" + createdUser + '\'' +
                '}';
    }
}
