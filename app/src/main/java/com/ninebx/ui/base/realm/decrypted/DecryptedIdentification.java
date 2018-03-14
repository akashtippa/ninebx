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
public class DecryptedIdentification implements Parcelable {

    public static final Creator<DecryptedIdentification> CREATOR = new Creator<DecryptedIdentification>() {
        @Override
        public DecryptedIdentification createFromParcel(Parcel in) {
            return new DecryptedIdentification(in);
        }

        @Override
        public DecryptedIdentification[] newArray(int size) {
            return new DecryptedIdentification[size];
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
    private String classType = "Identification";
    @Required
    private String name = "";
    @Required
    private String gender = "";
    @Required
    private String dateofBirth = "";
    @Required
    private String age = "";
    @Required
    private String height = "";
    @Required
    private String weight = "";
    @Required
    private String hairColor = "";
    @Required
    private String eyeColor = "";
    @Required
    private String visibleMarks = "";
    @Required
    private String bloodType = "";
    @Required
    private String orgonDonor = "";
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

    protected DecryptedIdentification(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        classType = in.readString();
        name = in.readString();
        gender = in.readString();
        dateofBirth = in.readString();
        age = in.readString();
        height = in.readString();
        weight = in.readString();
        hairColor = in.readString();
        eyeColor = in.readString();
        visibleMarks = in.readString();
        bloodType = in.readString();
        orgonDonor = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        notes = in.readString();
        attachmentNames = in.readString();
        createdUser = in.readString();
    }

    public DecryptedIdentification(String selectionType, String classType, String name, String gender, String dateofBirth, String age, String height, String weight, String hairColor, String eyeColor, String visibleMarks, String bloodType, String orgonDonor, String created, String modified, Boolean isPrivate, String notes, String attachmentNames, String createdUser) {
        this.selectionType = selectionType;
        this.classType = classType;
        this.name = name;
        this.gender = gender;
        this.dateofBirth = dateofBirth;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.visibleMarks = visibleMarks;
        this.bloodType = bloodType;
        this.orgonDonor = orgonDonor;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.createdUser = createdUser;
    }

    public DecryptedIdentification() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(classType);
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(dateofBirth);
        dest.writeString(age);
        dest.writeString(height);
        dest.writeString(weight);
        dest.writeString(hairColor);
        dest.writeString(eyeColor);
        dest.writeString(visibleMarks);
        dest.writeString(bloodType);
        dest.writeString(orgonDonor);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(notes);
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getVisibleMarks() {
        return visibleMarks;
    }

    public void setVisibleMarks(String visibleMarks) {
        this.visibleMarks = visibleMarks;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getOrgonDonor() {
        return orgonDonor;
    }

    public void setOrgonDonor(String orgonDonor) {
        this.orgonDonor = orgonDonor;
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
        return "DecryptedIdentification{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", classType='" + classType + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dateofBirth='" + dateofBirth + '\'' +
                ", age='" + age + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", visibleMarks='" + visibleMarks + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", orgonDonor='" + orgonDonor + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", notes='" + notes + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", createdUser='" + createdUser + '\'' +
                '}';
    }
}
