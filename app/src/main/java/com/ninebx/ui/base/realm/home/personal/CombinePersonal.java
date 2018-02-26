package com.ninebx.ui.base.realm.home.personal;


import com.ninebx.ui.base.realm.lists.PersonalList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Alok on 24/01/18.
 */
@RealmClass
public class CombinePersonal extends RealmObject {

    RealmList<Certificate> certificateItems = new RealmList<>();
    RealmList<Government> governmentItems = new RealmList<>();
    RealmList<License> licenseItems = new RealmList<>();
    RealmList<Personal> personalItems = new RealmList<>();
    RealmList<Social> socialItems = new RealmList<>();
    RealmList<TaxID> taxIDItems = new RealmList<>();
    RealmList<PersonalList> listItems = new RealmList<>();
    @PrimaryKey //@Required
    private int id = 0;

    public CombinePersonal(int id, RealmList<Certificate> certificateItems, RealmList<Government> governmentItems, RealmList<License> licenseItems, RealmList<Personal> personalItems, RealmList<Social> socialItems, RealmList<TaxID> taxIDItems, RealmList<PersonalList> listItems) {
        this.id = id;
        this.certificateItems = certificateItems;
        this.governmentItems = governmentItems;
        this.licenseItems = licenseItems;
        this.personalItems = personalItems;
        this.socialItems = socialItems;
        this.taxIDItems = taxIDItems;
        this.listItems = listItems;
    }

    public CombinePersonal() {
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

    public RealmList<PersonalList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList listItems) {
        this.listItems = listItems;
    }
}
