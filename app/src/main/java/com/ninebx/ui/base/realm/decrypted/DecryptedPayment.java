package com.ninebx.ui.base.realm.decrypted;

import android.content.Intent;
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

public class DecryptedPayment implements Parcelable {
    @PrimaryKey //@Required
    private long id = 0;
    @Required private String selectionType = "";;
    @Required private String cardNumber = "";
    @Required private String cardName = "";
    @Required private String cardType = "";
    @Required private String cardHolder = "";
    @Required private String expiryDate = "";
    @Required private String cvvCode = "";
    @Required private String cardPin = "";
    @Required private String issuingBank = "";
    @Required private String website = "";
    @Required private String userName = "";
    @Required private String password = "";
    @Required private String pin = "";
    @Required private String created = "";
    @Required private String modified = "";
    private boolean isPrivate = false;
    @Required private String createdUser = "";
    @Required private String notes = "";
    @Required private String attachmentNames = "";
    @Required
    private ArrayList<RealmString> backingImages = new ArrayList<>();
    @Ignore
    @Required
    private List<String> photosId = new ArrayList<>();
    @Ignore public String searchField = "";

    public DecryptedPayment() {
    }

    public DecryptedPayment(long id, String selectionType, String cardNumber, String cardName, String cardType, String cardHolder, String expiryDate, String cvvCode, String cardPin, String issuingBank, String website, String userName, String password, String pin, String created, String modified, boolean isPrivate, String createdUser, String notes, String attachmentNames, ArrayList<RealmString> backingImages, List<String> photosId) {
        this.id = id;
        this.selectionType = selectionType;
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.cardType = cardType;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvvCode = cvvCode;
        this.cardPin = cardPin;
        this.issuingBank = issuingBank;
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
        this.backingImages = backingImages;
        this.photosId = photosId;
    }

    protected DecryptedPayment(Parcel in) {
        id = in.readLong();
        selectionType = in.readString();
        cardNumber = in.readString();
        cardName = in.readString();
        cardType = in.readString();
        cardHolder = in.readString();
        expiryDate = in.readString();
        cvvCode = in.readString();
        cardPin = in.readString();
        issuingBank = in.readString();
        website = in.readString();
        userName = in.readString();
        password = in.readString();
        pin = in.readString();
        created = in.readString();
        modified = in.readString();
        isPrivate = in.readByte() != 0;
        createdUser = in.readString();
        notes = in.readString();
        attachmentNames = in.readString();
        photosId = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(selectionType);
        dest.writeString(cardNumber);
        dest.writeString(cardName);
        dest.writeString(cardType);
        dest.writeString(cardHolder);
        dest.writeString(expiryDate);
        dest.writeString(cvvCode);
        dest.writeString(cardPin);
        dest.writeString(issuingBank);
        dest.writeString(website);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(pin);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate ? 1 : 0));
        dest.writeString(createdUser);
        dest.writeString(notes);
        dest.writeString(attachmentNames);
        dest.writeStringList(photosId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DecryptedPayment> CREATOR = new Creator<DecryptedPayment>() {
        @Override
        public DecryptedPayment createFromParcel(Parcel in) {
            return new DecryptedPayment(in);
        }

        @Override
        public DecryptedPayment[] newArray(int size) {
            return new DecryptedPayment[size];
        }
    };

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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getCardPin() {
        return cardPin;
    }

    public void setCardPin(String cardPin) {
        this.cardPin = cardPin;
    }

    public String getIssuingBank() {
        return issuingBank;
    }

    public void setIssuingBank(String issuingBank) {
        this.issuingBank = issuingBank;
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

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
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

    public ArrayList<RealmString> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(ArrayList<RealmString> backingImages) {
        this.backingImages = backingImages;
    }

    public List<String> getPhotosId() {
        return photosId;
    }

    public void setPhotosId(List<String> photosId) {
        this.photosId = photosId;
    }

    @Override
    public String toString() {
        return "DecryptedPayment{" +
                "id=" + id +
                ", selectionType='" + selectionType + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvvCode='" + cvvCode + '\'' +
                ", cardPin='" + cardPin + '\'' +
                ", issuingBank='" + issuingBank + '\'' +
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
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                '}';
    }


}
