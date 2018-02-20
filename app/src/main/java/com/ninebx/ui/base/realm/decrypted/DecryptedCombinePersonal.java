package com.ninebx.ui.base.realm.decrypted;


import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 24/01/18.
 */
public class DecryptedCombinePersonal implements Parcelable {

    public static final Creator<DecryptedCombinePersonal> CREATOR = new Creator<DecryptedCombinePersonal>() {
        @Override
        public DecryptedCombinePersonal createFromParcel(Parcel in) {
            return new DecryptedCombinePersonal(in);
        }

        @Override
        public DecryptedCombinePersonal[] newArray(int size) {
            return new DecryptedCombinePersonal[size];
        }
    };
    RealmList<DecryptedCertificate> certificateItems = new RealmList<>();
    RealmList<DecryptedGovernment> governmentItems = new RealmList<>();
    RealmList<DecryptedLicense> licenseItems = new RealmList<>();
    RealmList<DecryptedPersonal> personalItems = new RealmList<>();
    RealmList<DecryptedSocial> socialItems = new RealmList<>();
    RealmList<DecryptedTaxID> taxIDItems = new RealmList<>();
    RealmList<DecryptedPersonalList> listItems = new RealmList<>();
    @PrimaryKey //@Required
    private int id = 0;

    public DecryptedCombinePersonal() {
    }

    public DecryptedCombinePersonal(RealmList<DecryptedCertificate> certificateItems, RealmList<DecryptedGovernment> governmentItems, RealmList<DecryptedLicense> licenseItems, RealmList<DecryptedPersonal> personalItems, RealmList<DecryptedSocial> socialItems, RealmList<DecryptedTaxID> taxIDItems, RealmList<DecryptedPersonalList> listItems, int id) {
        this.certificateItems = certificateItems;
        this.governmentItems = governmentItems;
        this.licenseItems = licenseItems;
        this.personalItems = personalItems;
        this.socialItems = socialItems;
        this.taxIDItems = taxIDItems;
        this.listItems = listItems;
        this.id = id;
    }

    protected DecryptedCombinePersonal(Parcel in) {
        id = in.readInt();
    }

    public RealmList<DecryptedCertificate> getCertificateItems() {
        return certificateItems;
    }

    public void setCertificateItems(RealmList<DecryptedCertificate> certificateItems) {
        this.certificateItems = certificateItems;
    }

    public RealmList<DecryptedGovernment> getGovernmentItems() {
        return governmentItems;
    }

    public void setGovernmentItems(RealmList<DecryptedGovernment> governmentItems) {
        this.governmentItems = governmentItems;
    }

    public RealmList<DecryptedLicense> getLicenseItems() {
        return licenseItems;
    }

    public void setLicenseItems(RealmList<DecryptedLicense> licenseItems) {
        this.licenseItems = licenseItems;
    }

    public RealmList<DecryptedPersonal> getPersonalItems() {
        return personalItems;
    }

    public void setPersonalItems(RealmList<DecryptedPersonal> personalItems) {
        this.personalItems = personalItems;
    }

    public RealmList<DecryptedSocial> getSocialItems() {
        return socialItems;
    }

    public void setSocialItems(RealmList<DecryptedSocial> socialItems) {
        this.socialItems = socialItems;
    }

    public RealmList<DecryptedTaxID> getTaxIDItems() {
        return taxIDItems;
    }

    public void setTaxIDItems(RealmList<DecryptedTaxID> taxIDItems) {
        this.taxIDItems = taxIDItems;
    }

    public RealmList<DecryptedPersonalList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<DecryptedPersonalList> listItems) {
        this.listItems = listItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }

    public int getDriversLicense(String selectionType) {
        int count = 0;
        for (DecryptedLicense decryptedLicense : licenseItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getSocialSecurity(String selectionType) {
        int count = 0;
        for (DecryptedSocial decryptedLicense : socialItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getTAXID(String selectionType) {
        int count = 0;
        for (DecryptedTaxID decryptedLicense : taxIDItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getOtherGovernment(String selectionType) {
        int count = 0;
        for (DecryptedTaxID decryptedLicense : taxIDItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getMarriageCertificate(String selectionType) {
        int count = 0;
        for (DecryptedCertificate decryptedLicense : certificateItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }


}
