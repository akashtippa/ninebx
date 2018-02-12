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

public class DecryptedMainEducation implements Parcelable{

    @PrimaryKey //@Required
    private int id = 0;

    @Required
    private String selectionType = "";
    @Required private String classType = "MainEducation";

    @Required private String institutionName = "";
    @Required private String qualification = "";

    @Required private String name = "";
    @Required private String location = "";
    @Required private String major = "";
    @Required private String from = "";
    @Required private String to = "";
    @Required private String currentlyStudying = "";

    @Required private Boolean isCurrent = false;

    @Required private String notes = "";

    @Required private String created = "";
    @Required private String modified = "";
    @Required private Boolean isPrivate = false;

    @Required private String attachmentNames = "";

    @Required private String createdUser = "";

    @Required private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    @Required private List<String> photosId = new ArrayList<>();

    public List<String> getPhotosId() {
        photosId = new ArrayList<>();
        for( RealmString realmString : backingImages ) {
            photosId.add( realmString.getStringValue() );
        }
        return photosId;
    }

    public void setPhotosId(List<String> photosId) {
        this.photosId = photosId;
        backingImages.clear();
        for( String string : photosId ) {
            backingImages.add( new RealmString(string) );
        }
    }

    public DecryptedMainEducation() {
    }

    public DecryptedMainEducation(int id, String selectionType, String classType, String institutionName, String qualification, String name, String location, String major, String from, String to, String currentlyStudying, Boolean isCurrent, String notes, String created, String modified, Boolean isPrivate, String attachmentNames, String createdUser, RealmList<RealmString> backingImages, List<String> photosId) {
        this.id = id;
        this.selectionType = selectionType;
        this.classType = classType;
        this.institutionName = institutionName;
        this.qualification = qualification;
        this.name = name;
        this.location = location;
        this.major = major;
        this.from = from;
        this.to = to;
        this.currentlyStudying = currentlyStudying;
        this.isCurrent = isCurrent;
        this.notes = notes;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.attachmentNames = attachmentNames;
        this.createdUser = createdUser;
        this.backingImages = backingImages;
        this.photosId = photosId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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

    public String getCurrentlyStudying() {
        return currentlyStudying;
    }

    public void setCurrentlyStudying(String currentlyStudying) {
        this.currentlyStudying = currentlyStudying;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
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

    public RealmList<RealmString> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(RealmList<RealmString> backingImages) {
        this.backingImages = backingImages;
    }

    protected DecryptedMainEducation(Parcel in) {
        id = in.readInt();
        selectionType = in.readString();
        classType = in.readString();
        institutionName = in.readString();
        qualification = in.readString();
        name = in.readString();
        location = in.readString();
        major = in.readString();
        from = in.readString();
        to = in.readString();
        currentlyStudying = in.readString();
        byte tmpIsCurrent = in.readByte();
        isCurrent = tmpIsCurrent == 0 ? null : tmpIsCurrent == 1;
        notes = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        attachmentNames = in.readString();
        createdUser = in.readString();
        photosId = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(selectionType);
        dest.writeString(classType);
        dest.writeString(institutionName);
        dest.writeString(qualification);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(major);
        dest.writeString(from);
        dest.writeString(to);
        dest.writeString(currentlyStudying);
        dest.writeByte((byte) (isCurrent == null ? 0 : isCurrent ? 1 : 2));
        dest.writeString(notes);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(attachmentNames);
        dest.writeString(createdUser);
        dest.writeStringList(photosId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DecryptedMainEducation> CREATOR = new Creator<DecryptedMainEducation>() {
        @Override
        public DecryptedMainEducation createFromParcel(Parcel in) {
            return new DecryptedMainEducation(in);
        }

        @Override
        public DecryptedMainEducation[] newArray(int size) {
            return new DecryptedMainEducation[size];
        }
    };

    @Override
    public String toString() {
        return "DecryptedMainEducation{" +
                "id=" + id +
                ", selectionType='" + selectionType + '\'' +
                ", classType='" + classType + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", qualification='" + qualification + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", major='" + major + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", currentlyStudying='" + currentlyStudying + '\'' +
                ", isCurrent=" + isCurrent +
                ", notes='" + notes + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", createdUser='" + createdUser + '\'' +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                '}';
    }
}
