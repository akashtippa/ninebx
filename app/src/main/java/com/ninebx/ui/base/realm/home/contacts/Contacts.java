package com.ninebx.ui.base.realm.home.contacts;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.RealmString;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
@RealmClass
public class Contacts extends RealmObject implements Parcelable {

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };
    @PrimaryKey //@Required
    private int id = 0;
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
    private RealmList<RealmString> backingImages = new RealmList<>();
    @Ignore
    @Required
    private List<String> photosId = new ArrayList<>();

    public Contacts(int id, String selectionType, String firstName, String lastName, String dateOfBirth, String anniversary, String mobileOne, String mobileTwo, String emailOne, String emailTwo, String streetAddressOne, String streetAddressTwo, String city, String state, String zipCode, String country, String created, String modified, Boolean isPrivate, String createdUser, RealmList<RealmString> backingImages) {
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

    public Contacts() {
    }

    protected Contacts(Parcel in) {
        id = in.readInt();
        selectionType = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        dateOfBirth = in.readString();
        anniversary = in.readString();
        mobileOne = in.readString();
        mobileTwo = in.readString();
        emailOne = in.readString();
        emailTwo = in.readString();
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
        createdUser = in.readString();
        photosId = in.createStringArrayList();
    }

    public static ArrayList<Contacts> createParcelableList(@NotNull RealmResults<Contacts> contactsRealmResults) {
        ArrayList contacts = new ArrayList();
        contacts.addAll(contactsRealmResults);
        return contacts;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(selectionType);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(dateOfBirth);
        dest.writeString(anniversary);
        dest.writeString(mobileOne);
        dest.writeString(mobileTwo);
        dest.writeString(emailOne);
        dest.writeString(emailTwo);
        dest.writeString(streetAddressOne);
        dest.writeString(streetAddressTwo);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zipCode);
        dest.writeString(country);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(createdUser);
        dest.writeStringList(photosId);
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
}
