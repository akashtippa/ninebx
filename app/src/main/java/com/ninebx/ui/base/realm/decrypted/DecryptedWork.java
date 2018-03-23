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
 * Created by smrit on 12-02-2018.
 */

public class DecryptedWork implements Parcelable {

    @Ignore public String searchField = "";
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private String selectionType = "";
    @Required
    private String classType = "Work";
    @Required
    private String companyName = "";
    @Required
    private String position = "";
    @Required
    private String name = "";
    @Required
    private String location = "";
    @Required
    private String from = "";
    @Required
    private String to = "";
    @Required
    private String currentWork = "";
    @Required
    private Boolean isCurrent = false;
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
    @Required
    private ArrayList<RealmString> backingImages = new ArrayList<>();
    @Ignore
    @Required
    private List<String> photosId = new ArrayList<>();

    public DecryptedWork() {
    }

    public DecryptedWork(long id, String selectionType, String classType, String companyName, String position, String name, String location, String from, String to, String currentWork, Boolean isCurrent, String created, String modified, Boolean isPrivate, String notes, String attachmentNames, String createdUser, ArrayList<RealmString> backingImages, List<String> photosId) {
        this.id = id;
        this.selectionType = selectionType;
        this.classType = classType;
        this.companyName = companyName;
        this.position = position;
        this.name = name;
        this.location = location;
        this.from = from;
        this.to = to;
        this.currentWork = currentWork;
        this.isCurrent = isCurrent;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.createdUser = createdUser;
        this.backingImages = backingImages;
        this.photosId = photosId;
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

    public long getId() {
        return id;
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

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCurrentWork() {
        return currentWork;
    }

    public void setCurrentWork(String currentWork) {
        this.currentWork = currentWork;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
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

    public ArrayList<RealmString> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(ArrayList<RealmString> backingImages) {
        this.backingImages = backingImages;
    }

    @Override
    public String toString() {
        return "DecryptedWork{" +
                "id=" + id +
                ", selectionType='" + selectionType + '\'' +
                ", classType='" + classType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", currentWork='" + currentWork + '\'' +
                ", isCurrent=" + isCurrent +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", notes='" + notes + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", createdUser='" + createdUser + '\'' +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
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
        dest.writeString(this.classType);
        dest.writeString(this.companyName);
        dest.writeString(this.position);
        dest.writeString(this.name);
        dest.writeString(this.location);
        dest.writeString(this.from);
        dest.writeString(this.to);
        dest.writeString(this.currentWork);
        dest.writeValue(this.isCurrent);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.notes);
        dest.writeString(this.attachmentNames);
        dest.writeString(this.createdUser);
        dest.writeList(this.backingImages);
        dest.writeStringList(this.photosId);
    }

    protected DecryptedWork(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.selectionType = in.readString();
        this.classType = in.readString();
        this.companyName = in.readString();
        this.position = in.readString();
        this.name = in.readString();
        this.location = in.readString();
        this.from = in.readString();
        this.to = in.readString();
        this.currentWork = in.readString();
        this.isCurrent = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.notes = in.readString();
        this.attachmentNames = in.readString();
        this.createdUser = in.readString();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
    }

    public static final Creator<DecryptedWork> CREATOR = new Creator<DecryptedWork>() {
        @Override
        public DecryptedWork createFromParcel(Parcel source) {
            return new DecryptedWork(source);
        }

        @Override
        public DecryptedWork[] newArray(int size) {
            return new DecryptedWork[size];
        }
    };
}
