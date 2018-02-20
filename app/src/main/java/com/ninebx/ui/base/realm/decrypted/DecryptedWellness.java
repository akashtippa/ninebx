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
public class DecryptedWellness implements Parcelable {

    public static final Creator<DecryptedWellness> CREATOR = new Creator<DecryptedWellness>() {
        @Override
        public DecryptedWellness createFromParcel(Parcel in) {
            return new DecryptedWellness(in);
        }

        @Override
        public DecryptedWellness[] newArray(int size) {
            return new DecryptedWellness[size];
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
    private String institutionName = "";
    @Required
    private String accountName = "";
    @Required
    private String accountType = "";
    @Required
    private String nameOnAccount = "";
    @Required
    private String accountNumber = "";
    @Required
    private String location = "";
    @Required
    private String swiftCode = "";
    @Required
    private String abaRoutingNumber = "";
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
    private String paymentMethodOnFile = "";
    @Required
    private String notes = "";
    @Required
    private String attachmentNames = "";
    @Required
    private String title = "";
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String createdUser = "";

    protected DecryptedWellness(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        institutionName = in.readString();
        accountName = in.readString();
        accountType = in.readString();
        nameOnAccount = in.readString();
        accountNumber = in.readString();
        location = in.readString();
        swiftCode = in.readString();
        abaRoutingNumber = in.readString();
        contacts = in.readString();
        website = in.readString();
        userName = in.readString();
        password = in.readString();
        pin = in.readString();
        paymentMethodOnFile = in.readString();
        notes = in.readString();
        attachmentNames = in.readString();
        title = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        createdUser = in.readString();
    }

    public DecryptedWellness(String selectionType, String institutionName, String accountName, String accountType, String nameOnAccount, String accountNumber, String location, String swiftCode, String abaRoutingNumber, String contacts, String website, String userName, String password, String pin, String paymentMethodOnFile, String notes, String attachmentNames, String title, String created, String modified, Boolean isPrivate, String createdUser) {
        this.selectionType = selectionType;
        this.institutionName = institutionName;
        this.accountName = accountName;
        this.accountType = accountType;
        this.nameOnAccount = nameOnAccount;
        this.accountNumber = accountNumber;
        this.location = location;
        this.swiftCode = swiftCode;
        this.abaRoutingNumber = abaRoutingNumber;
        this.contacts = contacts;
        this.website = website;
        this.userName = userName;
        this.password = password;
        this.pin = pin;
        this.paymentMethodOnFile = paymentMethodOnFile;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.title = title;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
    }

    public DecryptedWellness() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(institutionName);
        dest.writeString(accountName);
        dest.writeString(accountType);
        dest.writeString(nameOnAccount);
        dest.writeString(accountNumber);
        dest.writeString(location);
        dest.writeString(swiftCode);
        dest.writeString(abaRoutingNumber);
        dest.writeString(contacts);
        dest.writeString(website);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(pin);
        dest.writeString(paymentMethodOnFile);
        dest.writeString(notes);
        dest.writeString(attachmentNames);
        dest.writeString(title);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
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

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getAbaRoutingNumber() {
        return abaRoutingNumber;
    }

    public void setAbaRoutingNumber(String abaRoutingNumber) {
        this.abaRoutingNumber = abaRoutingNumber;
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

    public String getPaymentMethodOnFile() {
        return paymentMethodOnFile;
    }

    public void setPaymentMethodOnFile(String paymentMethodOnFile) {
        this.paymentMethodOnFile = paymentMethodOnFile;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "DecryptedWellness{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", nameOnAccount='" + nameOnAccount + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", location='" + location + '\'' +
                ", swiftCode='" + swiftCode + '\'' +
                ", abaRoutingNumber='" + abaRoutingNumber + '\'' +
                ", contacts='" + contacts + '\'' +
                ", website='" + website + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", pin='" + pin + '\'' +
                ", paymentMethodOnFile='" + paymentMethodOnFile + '\'' +
                ", notes='" + notes + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", title='" + title + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", createdUser='" + createdUser + '\'' +
                '}';
    }
}
