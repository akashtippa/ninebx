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
 * Created by smrit on 18-02-2018.
 */

public class DecryptedMedicalConditions implements Parcelable {

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
    private String classType = "MedicalConditions";
    @Required
    private String condition = "";
    @Required
    private String dateDiagnosed = "";
    @Required
    private String medi_description = "";
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

    public DecryptedMedicalConditions() {
    }

    public DecryptedMedicalConditions(long id, ArrayList<RealmString> backingImages, List<String> photosId, String selectionType, String classType, String condition, String dateDiagnosed, String medi_description, String notes, String attachmentNames, String created, String modified, Boolean isPrivate, String createdUser) {
        this.id = id;
        this.backingImages = backingImages;
        this.photosId = photosId;
        this.selectionType = selectionType;
        this.classType = classType;
        this.condition = condition;
        this.dateDiagnosed = dateDiagnosed;
        this.medi_description = medi_description;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDateDiagnosed() {
        return dateDiagnosed;
    }

    public void setDateDiagnosed(String dateDiagnosed) {
        this.dateDiagnosed = dateDiagnosed;
    }

    public String getMedi_description() {
        return medi_description;
    }

    public void setMedi_description(String medi_description) {
        this.medi_description = medi_description;
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
        return "DecryptedMedicalConditions{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", classType='" + classType + '\'' +
                ", condition='" + condition + '\'' +
                ", dateDiagnosed='" + dateDiagnosed + '\'' +
                ", medi_description='" + medi_description + '\'' +
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
        dest.writeString(this.condition);
        dest.writeString(this.dateDiagnosed);
        dest.writeString(this.medi_description);
        dest.writeString(this.notes);
        dest.writeString(this.attachmentNames);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.createdUser);
    }

    protected DecryptedMedicalConditions(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
        this.selectionType = in.readString();
        this.classType = in.readString();
        this.condition = in.readString();
        this.dateDiagnosed = in.readString();
        this.medi_description = in.readString();
        this.notes = in.readString();
        this.attachmentNames = in.readString();
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.createdUser = in.readString();
    }

    public static final Creator<DecryptedMedicalConditions> CREATOR = new Creator<DecryptedMedicalConditions>() {
        @Override
        public DecryptedMedicalConditions createFromParcel(Parcel source) {
            return new DecryptedMedicalConditions(source);
        }

        @Override
        public DecryptedMedicalConditions[] newArray(int size) {
            return new DecryptedMedicalConditions[size];
        }
    };
}
