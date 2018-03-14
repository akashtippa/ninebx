package com.ninebx.ui.base.realm.decrypted;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.annotations.Ignore;
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
    @Ignore
    public String searchField = "";
    ArrayList<DecryptedCertificate> certificateItems = new ArrayList<>();
    ArrayList<DecryptedGovernment> governmentItems = new ArrayList<>();
    ArrayList<DecryptedLicense> licenseItems = new ArrayList<>();
    ArrayList<DecryptedPersonal> personalItems = new ArrayList<>();
    ArrayList<DecryptedSocial> socialItems = new ArrayList<>();
    ArrayList<DecryptedTaxID> taxIDItems = new ArrayList<>();
    ArrayList<DecryptedPersonalList> listItems = new ArrayList<>();
    @PrimaryKey //@Required
    private long id = 0;

    public DecryptedCombinePersonal() {
    }

    public DecryptedCombinePersonal(ArrayList<DecryptedCertificate> certificateItems, ArrayList<DecryptedGovernment> governmentItems, ArrayList<DecryptedLicense> licenseItems, ArrayList<DecryptedPersonal> personalItems, ArrayList<DecryptedSocial> socialItems, ArrayList<DecryptedTaxID> taxIDItems, ArrayList<DecryptedPersonalList> listItems, long id) {
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

    public ArrayList<DecryptedCertificate> getCertificateItems() {
        return certificateItems;
    }

    public void setCertificateItems(ArrayList<DecryptedCertificate> certificateItems) {
        this.certificateItems = certificateItems;
    }

    public ArrayList<DecryptedGovernment> getGovernmentItems() {
        return governmentItems;
    }

    public void setGovernmentItems(ArrayList<DecryptedGovernment> governmentItems) {
        this.governmentItems = governmentItems;
    }

    public ArrayList<DecryptedLicense> getLicenseItems() {
        return licenseItems;
    }

    public void setLicenseItems(ArrayList<DecryptedLicense> licenseItems) {
        this.licenseItems = licenseItems;
    }

    public ArrayList<DecryptedPersonal> getPersonalItems() {
        return personalItems;
    }

    public void setPersonalItems(ArrayList<DecryptedPersonal> personalItems) {
        this.personalItems = personalItems;
    }

    public ArrayList<DecryptedSocial> getSocialItems() {
        return socialItems;
    }

    public void setSocialItems(ArrayList<DecryptedSocial> socialItems) {
        this.socialItems = socialItems;
    }

    public ArrayList<DecryptedTaxID> getTaxIDItems() {
        return taxIDItems;
    }

    public void setTaxIDItems(ArrayList<DecryptedTaxID> taxIDItems) {
        this.taxIDItems = taxIDItems;
    }

    public ArrayList<DecryptedPersonalList> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<DecryptedPersonalList> listItems) {
        this.listItems = listItems;
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
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
        for (DecryptedGovernment decryptedLicense : governmentItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getServicesAttachments(String selectionType) {
        int count = 0;
        for (DecryptedPersonal decryptedLicense : personalItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getOtherAttach(String selectionType) {
        int count = 0;
        for (DecryptedPersonalList decryptedLicense : listItems) {
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


    public int getListsCount(String selectionType, Integer detailsId ) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedPersonalList decryptedLicense : listItems) {
            count += (decryptedLicense.getSelectionType().equals(selectionType) && decryptedLicense.getDetailsId() == detailsId )? 1 : 0;
        }
        return count;
    }

    @Override
    public String toString() {
        return "DecryptedCombinePersonal{" +
                "certificateItems=" + certificateItems +
                ", governmentItems=" + governmentItems +
                ", licenseItems=" + licenseItems +
                ", personalItems=" + personalItems +
                ", socialItems=" + socialItems +
                ", taxIDItems=" + taxIDItems +
                ", listItems=" + listItems +
                ", id=" + id +
                '}';
    }
}
