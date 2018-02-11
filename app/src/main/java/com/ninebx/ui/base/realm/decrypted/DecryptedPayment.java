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

public class DecryptedPayment implements Parcelable{

    @PrimaryKey //@Required
    private int id = 0;

    @Required
    private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    @Required private List<String> photosId = new ArrayList<>();


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

    @Required private String insuranceCompany = "";
    @Required private String insuredProperty = "";
    @Required private String insuredVehicle = "";
    @Required private String insuredPerson = "";

    @Required private String policyNumber = "";
    @Required private String policyEffectiveDate = "";
    @Required private String policyExpirationDate = "";
    @Required private String contacts = "";

    @Required private String website = "";
    @Required private String userName = "";
    @Required private String password = "";
    @Required private String pin = "";

    @Required private String created = "";
    @Required private String modified = "";
    @Required private Boolean isPrivate = false;
    @Required private String createdUser = "";

    @Required private String notes = "";

    @Required private String attachmentNames = "";

    public DecryptedPayment() {
    }

    public DecryptedPayment(int id, RealmList<RealmString> backingImages, List<String> photosId, String selectionType, String insuranceCompany, String insuredProperty, String insuredVehicle, String insuredPerson, String policyNumber, String policyEffectiveDate, String policyExpirationDate, String contacts, String website, String userName, String password, String pin, String created, String modified, Boolean isPrivate, String createdUser, String notes, String attachmentNames) {
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

    protected DecryptedPayment(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        insuranceCompany = in.readString();
        insuredProperty = in.readString();
        insuredVehicle = in.readString();
        insuredPerson = in.readString();
        policyNumber = in.readString();
        policyEffectiveDate = in.readString();
        policyExpirationDate = in.readString();
        contacts = in.readString();
        website = in.readString();
        userName = in.readString();
        password = in.readString();
        pin = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        createdUser = in.readString();
        notes = in.readString();
        attachmentNames = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(insuranceCompany);
        dest.writeString(insuredProperty);
        dest.writeString(insuredVehicle);
        dest.writeString(insuredPerson);
        dest.writeString(policyNumber);
        dest.writeString(policyEffectiveDate);
        dest.writeString(policyExpirationDate);
        dest.writeString(contacts);
        dest.writeString(website);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(pin);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(createdUser);
        dest.writeString(notes);
        dest.writeString(attachmentNames);
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

}
