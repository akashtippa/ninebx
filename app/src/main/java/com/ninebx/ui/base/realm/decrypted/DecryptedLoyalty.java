package com.ninebx.ui.base.realm.decrypted;



import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.RealmString;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 29/01/18.
 */
public class DecryptedLoyalty implements Parcelable {

    @PrimaryKey //@Required
    private int id = 0;

    @Required
    private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    @Required private List<String> photosId = new ArrayList<>();

    protected DecryptedLoyalty(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        airLine = in.readString();
        hotel = in.readString();
        carRentalCompany = in.readString();
        cruiseline = in.readString();
        railway = in.readString();
        other = in.readString();
        accountName = in.readString();
        nameOnAccount = in.readString();
        accountNumber = in.readString();
        website = in.readString();
        userName = in.readString();
        password = in.readString();
        pin = in.readString();
        notes = in.readString();
        attachmentNames = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        createdUser = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(airLine);
        dest.writeString(hotel);
        dest.writeString(carRentalCompany);
        dest.writeString(cruiseline);
        dest.writeString(railway);
        dest.writeString(other);
        dest.writeString(accountName);
        dest.writeString(nameOnAccount);
        dest.writeString(accountNumber);
        dest.writeString(website);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(pin);
        dest.writeString(notes);
        dest.writeString(attachmentNames);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(createdUser);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DecryptedLoyalty> CREATOR = new Creator<DecryptedLoyalty>() {
        @Override
        public DecryptedLoyalty createFromParcel(Parcel in) {
            return new DecryptedLoyalty(in);
        }

        @Override
        public DecryptedLoyalty[] newArray(int size) {
            return new DecryptedLoyalty[size];
        }
    };

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

    @Required private String selectionType = "";

    @Required private String airLine = "";
    @Required private String hotel = "";
    @Required private String carRentalCompany = "";
    @Required private String cruiseline = "";
    @Required private String railway = "";
    @Required private String other = "";

    @Required private String accountName = "";
    @Required private String nameOnAccount = "";
    @Required private String accountNumber = "";

    @Required private String website = "";
    @Required private String userName = "";
    @Required private String password = "";
    @Required private String pin = "";

    @Required private String notes = "";
    @Required private String attachmentNames = "";

    @Required private String created = "";
    @Required private String modified = "";
    @Required private Boolean isPrivate = false;
    @Required private String createdUser = "";

    public DecryptedLoyalty(String selectionType, String airLine, String hotel, String carRentalCompany, String cruiseline, String railway, String other, String accountName, String nameOnAccount, String accountNumber, String website, String userName, String password, String pin, String notes, String attachmentNames, String created, String modified, Boolean isPrivate, String createdUser) {
        this.selectionType = selectionType;
        this.airLine = airLine;
        this.hotel = hotel;
        this.carRentalCompany = carRentalCompany;
        this.cruiseline = cruiseline;
        this.railway = railway;
        this.other = other;
        this.accountName = accountName;
        this.nameOnAccount = nameOnAccount;
        this.accountNumber = accountNumber;
        this.website = website;
        this.userName = userName;
        this.password = password;
        this.pin = pin;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getAirLine() {
        return airLine;
    }

    public void setAirLine(String airLine) {
        this.airLine = airLine;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getCarRentalCompany() {
        return carRentalCompany;
    }

    public void setCarRentalCompany(String carRentalCompany) {
        this.carRentalCompany = carRentalCompany;
    }

    public String getCruiseline() {
        return cruiseline;
    }

    public void setCruiseline(String cruiseline) {
        this.cruiseline = cruiseline;
    }

    public String getRailway() {
        return railway;
    }

    public void setRailway(String railway) {
        this.railway = railway;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getNameOnAccount() {
        return nameOnAccount;
    }

    public void setNameOnAccount(String nameOnAccount) {
        this.nameOnAccount = nameOnAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public DecryptedLoyalty() {
    }
}
