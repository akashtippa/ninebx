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
public class DecryptedCheckups implements Parcelable {

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
    private String classType = "Checkups";
    @Required
    private String physicianName = "";
    @Required
    private String checkup_description = "";
    @Required
    private String physicianType = "";
    @Required
    private String reason = "";
    @Required
    private String dateOfVisit = "";
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

    public DecryptedCheckups(String selectionType, String classType, String physicianName, String checkup_description, String physicianType, String reason, String dateOfVisit, String notes, String attachmentNames, String created, String modified, Boolean isPrivate, String createdUser) {
        this.selectionType = selectionType;
        this.classType = classType;
        this.physicianName = physicianName;
        this.checkup_description = checkup_description;
        this.physicianType = physicianType;
        this.reason = reason;
        this.dateOfVisit = dateOfVisit;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
    }

    public DecryptedCheckups() {
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

    public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }

    public String getCheckup_description() {
        return checkup_description;
    }

    public void setCheckup_description(String checkup_description) {
        this.checkup_description = checkup_description;
    }

    public String getPhysicianType() {
        return physicianType;
    }

    public void setPhysicianType(String physicianType) {
        this.physicianType = physicianType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(String dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
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
        return "DecryptedCheckups{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", classType='" + classType + '\'' +
                ", physicianName='" + physicianName + '\'' +
                ", checkup_description='" + checkup_description + '\'' +
                ", physicianType='" + physicianType + '\'' +
                ", reason='" + reason + '\'' +
                ", dateOfVisit='" + dateOfVisit + '\'' +
                ", notes='" + notes + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
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
        dest.writeString(this.physicianName);
        dest.writeString(this.checkup_description);
        dest.writeString(this.physicianType);
        dest.writeString(this.reason);
        dest.writeString(this.dateOfVisit);
        dest.writeString(this.notes);
        dest.writeString(this.attachmentNames);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.createdUser);
    }

    protected DecryptedCheckups(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
        this.selectionType = in.readString();
        this.classType = in.readString();
        this.physicianName = in.readString();
        this.checkup_description = in.readString();
        this.physicianType = in.readString();
        this.reason = in.readString();
        this.dateOfVisit = in.readString();
        this.notes = in.readString();
        this.attachmentNames = in.readString();
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.createdUser = in.readString();
    }

    public static final Creator<DecryptedCheckups> CREATOR = new Creator<DecryptedCheckups>() {
        @Override
        public DecryptedCheckups createFromParcel(Parcel source) {
            return new DecryptedCheckups(source);
        }

        @Override
        public DecryptedCheckups[] newArray(int size) {
            return new DecryptedCheckups[size];
        }
    };
}
