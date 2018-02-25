package com.ninebx.ui.base.realm.home.homeBanking;

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
 * Created by Alok on 24/01/18.
 */
@RealmClass
public class Payment extends RealmObject {

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

    public Payment(long id, String selectionType, String cardNumber, String cardName, String cardType, String cardHolder, String expiryDate, String cvvCode, String cardPin, String issuingBank, String website, String userName, String password, String pin, String created, String modified, boolean isPrivate, String createdUser, String notes, String attachmentNames) {
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
    }

    public Payment() {
    }

    @Override
    public String toString() {
        return "Payment{" +
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
                '}';
    }
}
