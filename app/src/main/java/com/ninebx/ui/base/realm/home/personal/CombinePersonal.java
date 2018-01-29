package com.ninebx.ui.base.realm.home.personal;


import com.ninebx.ui.base.realm.lists.PersonalList;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 24/01/18.
 */

public class CombinePersonal extends RealmObject {

    @PrimaryKey
    private Integer id = 0;

    RealmList certificateItems        = new RealmList<Certificate>();
    RealmList governmentItems         = new RealmList<Government>();
    RealmList licenseItems            = new RealmList<License>();
    RealmList personalItems           = new RealmList<Personal>();
    RealmList socialItems             = new RealmList<Social>();
    RealmList taxIDItems              = new RealmList<TaxID>();
    
    RealmList listItems               = new RealmList<PersonalList>();

    public CombinePersonal(Integer id, RealmList certificateItems, RealmList governmentItems, RealmList licenseItems, RealmList personalItems, RealmList socialItems, RealmList taxIDItems, RealmList listItems) {
        this.id = id;
        this.certificateItems = certificateItems;
        this.governmentItems = governmentItems;
        this.licenseItems = licenseItems;
        this.personalItems = personalItems;
        this.socialItems = socialItems;
        this.taxIDItems = taxIDItems;
        this.listItems = listItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public CombinePersonal() {
    }
}
