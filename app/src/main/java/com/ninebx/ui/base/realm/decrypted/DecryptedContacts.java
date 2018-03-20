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
public class DecryptedContacts implements Parcelable {

    @Ignore public String searchField = "";
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private String selectionType = "";
    @Required
    private String firstName = "";
    @Required
    private String lastName = "";
    @Required
    private String dateOfBirth = "";
    @Required
    private String anniversary = "";
    @Required
    private String mobileOne = "";
    @Required
    private String mobileTwo = "";
    @Required
    private String emailOne = "";
    @Required
    private String emailTwo = "";
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
    private String createdUser = "";
    @Required
    private ArrayList<RealmString> backingImages = new ArrayList<>();
    @Ignore
    @Required
    private List<String> photosId = new ArrayList<>();

    public DecryptedContacts(long id, String selectionType, String firstName, String lastName, String dateOfBirth, String anniversary, String mobileOne, String mobileTwo, String emailOne, String emailTwo, String streetAddressOne, String streetAddressTwo, String city, String state, String zipCode, String country, String created, String modified, Boolean isPrivate, String createdUser, ArrayList<RealmString> backingImages) {
        this.id = id;
        this.selectionType = selectionType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.anniversary = anniversary;
        this.mobileOne = mobileOne;
        this.mobileTwo = mobileTwo;
        this.emailOne = emailOne;
        this.emailTwo = emailTwo;
        this.streetAddressOne = streetAddressOne;
        this.streetAddressTwo = streetAddressTwo;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
        this.backingImages = backingImages;
    }


    public DecryptedContacts() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(String anniversary) {
        this.anniversary = anniversary;
    }

    public String getMobileOne() {
        return mobileOne;
    }

    public void setMobileOne(String mobileOne) {
        this.mobileOne = mobileOne;
    }

    public String getMobileTwo() {
        return mobileTwo;
    }

    public void setMobileTwo(String mobileTwo) {
        this.mobileTwo = mobileTwo;
    }

    public String getEmailOne() {
        return emailOne;
    }

    public void setEmailOne(String emailOne) {
        this.emailOne = emailOne;
    }

    public String getEmailTwo() {
        return emailTwo;
    }

    public void setEmailTwo(String emailTwo) {
        this.emailTwo = emailTwo;
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



    @Override
    public String toString() {
        return "DecryptedContacts{" +
                "id=" + id +
                ", selectionType='" + selectionType + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", anniversary='" + anniversary + '\'' +
                ", mobileOne='" + mobileOne + '\'' +
                ", mobileTwo='" + mobileTwo + '\'' +
                ", emailOne='" + emailOne + '\'' +
                ", emailTwo='" + emailTwo + '\'' +
                ", streetAddressOne='" + streetAddressOne + '\'' +
                ", streetAddressTwo='" + streetAddressTwo + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
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
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.dateOfBirth);
        dest.writeString(this.anniversary);
        dest.writeString(this.mobileOne);
        dest.writeString(this.mobileTwo);
        dest.writeString(this.emailOne);
        dest.writeString(this.emailTwo);
        dest.writeString(this.streetAddressOne);
        dest.writeString(this.streetAddressTwo);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.zipCode);
        dest.writeString(this.country);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.createdUser);
        dest.writeList(this.backingImages);
        dest.writeStringList(this.photosId);
    }

    protected DecryptedContacts(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.selectionType = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.dateOfBirth = in.readString();
        this.anniversary = in.readString();
        this.mobileOne = in.readString();
        this.mobileTwo = in.readString();
        this.emailOne = in.readString();
        this.emailTwo = in.readString();
        this.streetAddressOne = in.readString();
        this.streetAddressTwo = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.zipCode = in.readString();
        this.country = in.readString();
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.createdUser = in.readString();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
    }

    public static final Creator<DecryptedContacts> CREATOR = new Creator<DecryptedContacts>() {
        @Override
        public DecryptedContacts createFromParcel(Parcel source) {
            return new DecryptedContacts(source);
        }

        @Override
        public DecryptedContacts[] newArray(int size) {
            return new DecryptedContacts[size];
        }
    };
}
