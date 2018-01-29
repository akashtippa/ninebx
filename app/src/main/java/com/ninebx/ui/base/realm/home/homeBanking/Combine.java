package com.ninebx.ui.base.realm.home.homeBanking;

import com.ninebx.ui.base.realm.lists.HomeList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 24/01/18.
 */

public class Combine extends RealmObject {

    @PrimaryKey
    private Integer id = 0;

    private RealmList<Financial> financialItems      = new RealmList<Financial>();
    private RealmList<Payment> paymentItems        = new RealmList<Payment>();
    private RealmList<Property> propertyItems       = new RealmList<Property>();
    private RealmList<Vehicle> vehicleItems        = new RealmList<Vehicle>();
    private RealmList<Asset> assetItems          = new RealmList<Asset>();
    private RealmList<Insurance> insuranceItems      = new RealmList<Insurance>();
    private RealmList<Taxes> taxesItems          = new RealmList<Taxes>();

    private RealmList<HomeList> listItems           = new RealmList<HomeList>();

    public Combine(Integer id, RealmList<Financial> financialItems, RealmList<Payment> paymentItems, RealmList<Property> propertyItems, RealmList<Vehicle> vehicleItems, RealmList<Asset> assetItems, RealmList<Insurance> insuranceItems, RealmList<Taxes> taxesItems, RealmList<HomeList> listItems) {
        this.id = id;
        this.financialItems = financialItems;
        this.paymentItems = paymentItems;
        this.propertyItems = propertyItems;
        this.vehicleItems = vehicleItems;
        this.assetItems = assetItems;
        this.insuranceItems = insuranceItems;
        this.taxesItems = taxesItems;
        this.listItems = listItems;
    }

    public Combine() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealmList<Financial> getFinancialItems() {
        return financialItems;
    }

    public void setFinancialItems(RealmList<Financial> financialItems) {
        this.financialItems = financialItems;
    }

    public RealmList<Payment> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(RealmList<Payment> paymentItems) {
        this.paymentItems = paymentItems;
    }

    public RealmList<Property> getPropertyItems() {
        return propertyItems;
    }

    public void setPropertyItems(RealmList<Property> propertyItems) {
        this.propertyItems = propertyItems;
    }

    public RealmList<Vehicle> getVehicleItems() {
        return vehicleItems;
    }

    public void setVehicleItems(RealmList<Vehicle> vehicleItems) {
        this.vehicleItems = vehicleItems;
    }

    public RealmList<Asset> getAssetItems() {
        return assetItems;
    }

    public void setAssetItems(RealmList<Asset> assetItems) {
        this.assetItems = assetItems;
    }

    public RealmList<Insurance> getInsuranceItems() {
        return insuranceItems;
    }

    public void setInsuranceItems(RealmList<Insurance> insuranceItems) {
        this.insuranceItems = insuranceItems;
    }

    public RealmList<Taxes> getTaxesItems() {
        return taxesItems;
    }

    public void setTaxesItems(RealmList<Taxes> taxesItems) {
        this.taxesItems = taxesItems;
    }

    public RealmList<HomeList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<HomeList> listItems) {
        this.listItems = listItems;
    }
}