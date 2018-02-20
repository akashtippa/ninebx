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
public class DecryptedHealthcareProviders implements Parcelable {

    public static final Creator<DecryptedHealthcareProviders> CREATOR = new Creator<DecryptedHealthcareProviders>() {
        @Override
        public DecryptedHealthcareProviders createFromParcel(Parcel in) {
            return new DecryptedHealthcareProviders(in);
        }

        @Override
        public DecryptedHealthcareProviders[] newArray(int size) {
            return new DecryptedHealthcareProviders[size];
        }
    };
    @PrimaryKey //@Required
            int id = 0;
    @Required
    private RealmList<RealmString> backingImages = new RealmList<>();
    @Ignore
    @Required
    private List<String> photosId = new ArrayList<>();
    @Required
    private String selectionType = "";
    @Required
    private String classType = "HealthcareProviders";
    @Required
    private String name = "";
    @Required
    private String physicianType = "";
    @Required
    private String practiceName = "";
    @Required
    private String phoneNumberOne = "";
    @Required
    private String phoneNumberTwo = "";
    @Required
    private String emailAddress = "";
    @Required
    private String streetAddressOne = "";
    @Required
    private String streetAddressTwo = "";
    @Required
    private String city = "";
    @Required
    private String state = "";
    @Required
    private String zipCode = "";
    @Required
    private String country = "";
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

    protected DecryptedHealthcareProviders(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        classType = in.readString();
        name = in.readString();
        physicianType = in.readString();
        practiceName = in.readString();
        phoneNumberOne = in.readString();
        phoneNumberTwo = in.readString();
        emailAddress = in.readString();
        streetAddressOne = in.readString();
        streetAddressTwo = in.readString();
        city = in.readString();
        state = in.readString();
        zipCode = in.readString();
        country = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        notes = in.readString();
        attachmentNames = in.readString();
        createdUser = in.readString();
    }

    public DecryptedHealthcareProviders(String selectionType, String classType, String name, String physicianType, String practiceName, String phoneNumberOne, String phoneNumberTwo, String emailAddress, String streetAddressOne, String streetAddressTwo, String city, String state, String zipCode, String country, String created, String modified, Boolean isPrivate, String notes, String attachmentNames, String createdUser) {
        this.selectionType = selectionType;
        this.classType = classType;
        this.name = name;
        this.physicianType = physicianType;
        this.practiceName = practiceName;
        this.phoneNumberOne = phoneNumberOne;
        this.phoneNumberTwo = phoneNumberTwo;
        this.emailAddress = emailAddress;
        this.streetAddressOne = streetAddressOne;
        this.streetAddressTwo = streetAddressTwo;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.createdUser = createdUser;
    }

    public DecryptedHealthcareProviders() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(classType);
        dest.writeString(name);
        dest.writeString(physicianType);
        dest.writeString(practiceName);
        dest.writeString(phoneNumberOne);
        dest.writeString(phoneNumberTwo);
        dest.writeString(emailAddress);
        dest.writeString(streetAddressOne);
        dest.writeString(streetAddressTwo);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zipCode);
        dest.writeString(country);
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

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<RealmString> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(RealmList<RealmString> backingImages) {
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

    public String getPhysicianType() {
        return physicianType;
    }

    public void setPhysicianType(String physicianType) {
        this.physicianType = physicianType;
    }

    public String getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    public String getPhoneNumberOne() {
        return phoneNumberOne;
    }

    public void setPhoneNumberOne(String phoneNumberOne) {
        this.phoneNumberOne = phoneNumberOne;
    }

    public String getPhoneNumberTwo() {
        return phoneNumberTwo;
    }

    public void setPhoneNumberTwo(String phoneNumberTwo) {
        this.phoneNumberTwo = phoneNumberTwo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getStreetAddressOne() {
        return streetAddressOne;
    }

    public void setStreetAddressOne(String streetAddressOne) {
        this.streetAddressOne = streetAddressOne;
    }

    public String getStreetAddressTwo() {
        return streetAddressTwo;
    }

    public void setStreetAddressTwo(String streetAddressTwo) {
        this.streetAddressTwo = streetAddressTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        return "DecryptedHealthcareProviders{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", classType='" + classType + '\'' +
                ", name='" + name + '\'' +
                ", physicianType='" + physicianType + '\'' +
                ", practiceName='" + practiceName + '\'' +
                ", phoneNumberOne='" + phoneNumberOne + '\'' +
                ", phoneNumberTwo='" + phoneNumberTwo + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", streetAddressOne='" + streetAddressOne + '\'' +
                ", streetAddressTwo='" + streetAddressTwo + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", notes='" + notes + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", createdUser='" + createdUser + '\'' +
                '}';
    }
}
