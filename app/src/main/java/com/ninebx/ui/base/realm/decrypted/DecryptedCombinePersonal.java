package com.ninebx.ui.base.realm.decrypted;


import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.home.personal.Certificate;
import com.ninebx.ui.base.realm.home.personal.Government;
import com.ninebx.ui.base.realm.home.personal.License;
import com.ninebx.ui.base.realm.home.personal.Personal;
import com.ninebx.ui.base.realm.home.personal.Social;
import com.ninebx.ui.base.realm.home.personal.TaxID;
import com.ninebx.ui.base.realm.lists.PersonalList;

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
    RealmList<Certificate> certificateItems = new RealmList<Certificate>();
    RealmList<Government> governmentItems = new RealmList<Government>();
    RealmList<License> licenseItems = new RealmList<License>();
    RealmList<Personal> personalItems = new RealmList<Personal>();
    RealmList<Social> socialItems = new RealmList<Social>();
    RealmList<TaxID> taxIDItems = new RealmList<TaxID>();

    RealmList<PersonalList> listItems = new RealmList<PersonalList>();
    @PrimaryKey //@Required
    private int id = 0;

    public DecryptedCombinePersonal(int id, RealmList certificateItems, RealmList governmentItems, RealmList licenseItems, RealmList personalItems, RealmList socialItems, RealmList taxIDItems, RealmList listItems) {
        this.id = id;
        this.certificateItems = certificateItems;
        this.governmentItems = governmentItems;
        this.licenseItems = licenseItems;
        this.personalItems = personalItems;
        this.socialItems = socialItems;
        this.taxIDItems = taxIDItems;
        this.listItems = listItems;
    }

    protected DecryptedCombinePersonal(Parcel in) {
        id = in.readInt();
    }

    public DecryptedCombinePersonal() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
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

    public RealmList getCertificateItems() {
        return certificateItems;
    }

    public void setCertificateItems(RealmList certificateItems) {
        this.certificateItems = certificateItems;
    }

    public RealmList getGovernmentItems() {
        return governmentItems;
    }

    public void setGovernmentItems(RealmList governmentItems) {
        this.governmentItems = governmentItems;
    }

    public RealmList getLicenseItems() {
        return licenseItems;
    }

    public void setLicenseItems(RealmList licenseItems) {
        this.licenseItems = licenseItems;
    }

    public RealmList getPersonalItems() {
        return personalItems;
    }

    public void setPersonalItems(RealmList personalItems) {
        this.personalItems = personalItems;
    }

    public RealmList getSocialItems() {
        return socialItems;
    }

    public void setSocialItems(RealmList socialItems) {
        this.socialItems = socialItems;
    }

    public RealmList getTaxIDItems() {
        return taxIDItems;
    }

    public void setTaxIDItems(RealmList taxIDItems) {
        this.taxIDItems = taxIDItems;
    }

    public RealmList getListItems() {
        return listItems;
    }

    public void setListItems(RealmList listItems) {
        this.listItems = listItems;
    }
}
