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

public class DecryptedInsurance implements Parcelable {

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
    private String insuranceCompany = "";
    @Required
    private String insuredProperty = "";
    @Required
    private String insuredVehicle = "";
    @Required
    private String insuredPerson = "";
    @Required
    private String policyNumber = "";
    @Required
    private String policyEffectiveDate = "";
    @Required
    private String policyExpirationDate = "";
    @Required
    private String contacts = "";
    @Required
    private String website = "";
    @Required
    private String userName = "";
    @Required
    private String password = "";
    @Required
    private String pin = "";
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

    public DecryptedInsurance() {
    }

    public DecryptedInsurance(long id, ArrayList<RealmString> backingImages, List<String> photosId, String selectionType, String insuranceCompany, String insuredProperty, String insuredVehicle, String insuredPerson, String policyNumber, String policyEffectiveDate, String policyExpirationDate, String contacts, String website, String userName, String password, String pin, String created, String modified, Boolean isPrivate, String createdUser, String notes, String attachmentNames) {
        this.id = id;
        this.backingImages = backingImages;
        this.photosId = photosId;
        this.selectionType = selectionType;
        this.insuranceCompany = insuranceCompany;
        this.insuredProperty = insuredProperty;
        this.insuredVehicle = insuredVehicle;
        this.insuredPerson = insuredPerson;
        this.policyNumber = policyNumber;
        this.policyEffectiveDate = policyEffectiveDate;
        this.policyExpirationDate = policyExpirationDate;
        this.contacts = contacts;
        this.website = website;
        this.userName = userName;
        this.password = password;
        this.pin = pin;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
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

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuredProperty() {
        return insuredProperty;
    }

    public void setInsuredProperty(String insuredProperty) {
        this.insuredProperty = insuredProperty;
    }

    public String getInsuredVehicle() {
        return insuredVehicle;
    }

    public void setInsuredVehicle(String insuredVehicle) {
        this.insuredVehicle = insuredVehicle;
    }

    public String getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(String insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyEffectiveDate() {
        return policyEffectiveDate;
    }

    public void setPolicyEffectiveDate(String policyEffectiveDate) {
        this.policyEffectiveDate = policyEffectiveDate;
    }

    public String getPolicyExpirationDate() {
        return policyExpirationDate;
    }

    public void setPolicyExpirationDate(String policyExpirationDate) {
        this.policyExpirationDate = policyExpirationDate;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
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
        return "DecryptedInsurance{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                ", insuredProperty='" + insuredProperty + '\'' +
                ", insuredVehicle='" + insuredVehicle + '\'' +
                ", insuredPerson='" + insuredPerson + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", policyEffectiveDate='" + policyEffectiveDate + '\'' +
                ", policyExpirationDate='" + policyExpirationDate + '\'' +
                ", contacts='" + contacts + '\'' +
                ", website='" + website + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", pin='" + pin + '\'' +
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
        dest.writeString(this.insuranceCompany);
        dest.writeString(this.insuredProperty);
        dest.writeString(this.insuredVehicle);
        dest.writeString(this.insuredPerson);
        dest.writeString(this.policyNumber);
        dest.writeString(this.policyEffectiveDate);
        dest.writeString(this.policyExpirationDate);
        dest.writeString(this.contacts);
        dest.writeString(this.website);
        dest.writeString(this.userName);
        dest.writeString(this.password);
        dest.writeString(this.pin);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.createdUser);
        dest.writeString(this.notes);
        dest.writeString(this.attachmentNames);
    }

    protected DecryptedInsurance(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
        this.selectionType = in.readString();
        this.insuranceCompany = in.readString();
        this.insuredProperty = in.readString();
        this.insuredVehicle = in.readString();
        this.insuredPerson = in.readString();
        this.policyNumber = in.readString();
        this.policyEffectiveDate = in.readString();
        this.policyExpirationDate = in.readString();
        this.contacts = in.readString();
        this.website = in.readString();
        this.userName = in.readString();
        this.password = in.readString();
        this.pin = in.readString();
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.createdUser = in.readString();
        this.notes = in.readString();
        this.attachmentNames = in.readString();
    }

    public static final Creator<DecryptedInsurance> CREATOR = new Creator<DecryptedInsurance>() {
        @Override
        public DecryptedInsurance createFromParcel(Parcel source) {
            return new DecryptedInsurance(source);
        }

        @Override
        public DecryptedInsurance[] newArray(int size) {
            return new DecryptedInsurance[size];
        }
    };
}
