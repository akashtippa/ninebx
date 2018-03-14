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
    public static final Creator<DecryptedWork> CREATOR = new Creator<DecryptedWork>() {
        @Override
        public DecryptedWork createFromParcel(Parcel in) {
            return new DecryptedWork(in);
        }

        @Override
        public DecryptedWork[] newArray(int size) {
            return new DecryptedWork[size];
        }
    };
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

    protected DecryptedWork(Parcel in) {
        id = in.readInt();
        selectionType = in.readString();
        classType = in.readString();
        companyName = in.readString();
        position = in.readString();
        name = in.readString();
        location = in.readString();
        from = in.readString();
        to = in.readString();
        currentWork = in.readString();
        byte tmpIsCurrent = in.readByte();
        isCurrent = tmpIsCurrent == 0 ? null : tmpIsCurrent == 1;
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        notes = in.readString();
        attachmentNames = in.readString();
        createdUser = in.readString();
        photosId = in.createStringArrayList();
    }

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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(selectionType);
        dest.writeString(classType);
        dest.writeString(companyName);
        dest.writeString(position);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(from);
        dest.writeString(to);
        dest.writeString(currentWork);
        dest.writeByte((byte) (isCurrent == null ? 0 : isCurrent ? 1 : 2));
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(notes);
        dest.writeString(attachmentNames);
        dest.writeString(createdUser);
        dest.writeStringList(photosId);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
