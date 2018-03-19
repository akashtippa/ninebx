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
 * Created by smrit on 11-02-2018.
 */

public class DecryptedProperty implements Parcelable {
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
    private String propertyName = "";
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
    private String titleName = "";
    @Required
    private String purchaseDate = "";
    @Required
    private String purchasePrice = "";
    @Required
    private String estimatedMarketValue = "";
    @Required
    private String contacts = "";
    @Required
    private Boolean currentlyRented = false;
    @Required
    private String tenantName = "";
    @Required
    private String leaseStartDate = "";
    @Required
    private String leaseEndDate = "";
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String createdUser = "";
    @Required
    private String notes = "";
    @Required
    private String attachmentNames = "";

    public DecryptedProperty() {
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

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
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

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getEstimatedMarketValue() {
        return estimatedMarketValue;
    }

    public void setEstimatedMarketValue(String estimatedMarketValue) {
        this.estimatedMarketValue = estimatedMarketValue;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Boolean getCurrentlyRented() {
        return currentlyRented;
    }

    public void setCurrentlyRented(Boolean currentlyRented) {
        this.currentlyRented = currentlyRented;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(String leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public String getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(String leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
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



    @Override
    public String toString() {
        return "DecryptedProperty{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", streetAddressOne='" + streetAddressOne + '\'' +
                ", streetAddressTwo='" + streetAddressTwo + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", titleName='" + titleName + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", purchasePrice='" + purchasePrice + '\'' +
                ", estimatedMarketValue='" + estimatedMarketValue + '\'' +
                ", contacts='" + contacts + '\'' +
                ", currentlyRented=" + currentlyRented +
                ", tenantName='" + tenantName + '\'' +
                ", leaseStartDate='" + leaseStartDate + '\'' +
                ", leaseEndDate='" + leaseEndDate + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", createdUser='" + createdUser + '\'' +
                ", notes='" + notes + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
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
        dest.writeString(this.propertyName);
        dest.writeString(this.streetAddressOne);
        dest.writeString(this.streetAddressTwo);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.zipCode);
        dest.writeString(this.country);
        dest.writeString(this.titleName);
        dest.writeString(this.purchaseDate);
        dest.writeString(this.purchasePrice);
        dest.writeString(this.estimatedMarketValue);
        dest.writeString(this.contacts);
        dest.writeValue(this.currentlyRented);
        dest.writeString(this.tenantName);
        dest.writeString(this.leaseStartDate);
        dest.writeString(this.leaseEndDate);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.createdUser);
        dest.writeString(this.notes);
        dest.writeString(this.attachmentNames);
    }

    protected DecryptedProperty(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
        this.selectionType = in.readString();
        this.propertyName = in.readString();
        this.streetAddressOne = in.readString();
        this.streetAddressTwo = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.zipCode = in.readString();
        this.country = in.readString();
        this.titleName = in.readString();
        this.purchaseDate = in.readString();
        this.purchasePrice = in.readString();
        this.estimatedMarketValue = in.readString();
        this.contacts = in.readString();
        this.currentlyRented = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.tenantName = in.readString();
        this.leaseStartDate = in.readString();
        this.leaseEndDate = in.readString();
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.createdUser = in.readString();
        this.notes = in.readString();
        this.attachmentNames = in.readString();
    }

    public static final Creator<DecryptedProperty> CREATOR = new Creator<DecryptedProperty>() {
        @Override
        public DecryptedProperty createFromParcel(Parcel source) {
            return new DecryptedProperty(source);
        }

        @Override
        public DecryptedProperty[] newArray(int size) {
            return new DecryptedProperty[size];
        }
    };
}
