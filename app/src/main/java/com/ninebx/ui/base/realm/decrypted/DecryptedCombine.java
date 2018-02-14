package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;
import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by smrit on 13-02-2018.
 */

public class DecryptedCombine implements Parcelable {

    @PrimaryKey //@Required
    private int id = 0;

    @Required
    private RealmList<DecryptedFinancial> financialItems      = new RealmList<DecryptedFinancial>();
    @Required private RealmList<DecryptedPayment> paymentItems        = new RealmList<DecryptedPayment>();
    @Required private RealmList<DecryptedProperty> propertyItems       = new RealmList<DecryptedProperty>();
    @Required private RealmList<DecryptedVehicle> vehicleItems        = new RealmList<DecryptedVehicle>();
    @Required private RealmList<DecryptedAsset> assetItems          = new RealmList<DecryptedAsset>();
    @Required private RealmList<DecryptedInsurance> insuranceItems      = new RealmList<DecryptedInsurance>();
    @Required private RealmList<DecryptedTax> taxesItems          = new RealmList<DecryptedTax>();
    @Required private RealmList<DecryptedHomeList> listItems           = new RealmList<DecryptedHomeList>();

    public DecryptedCombine() {
    }

    public DecryptedCombine(int id, RealmList<DecryptedFinancial> financialItems, RealmList<DecryptedPayment> paymentItems, RealmList<DecryptedProperty> propertyItems, RealmList<DecryptedVehicle> vehicleItems, RealmList<DecryptedAsset> assetItems, RealmList<DecryptedInsurance> insuranceItems, RealmList<DecryptedTax> taxesItems, RealmList<DecryptedHomeList> listItems) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<DecryptedFinancial> getFinancialItems() {
        return financialItems;
    }

    public void setFinancialItems(RealmList<DecryptedFinancial> financialItems) {
        this.financialItems = financialItems;
    }

    public RealmList<DecryptedPayment> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(RealmList<DecryptedPayment> paymentItems) {
        this.paymentItems = paymentItems;
    }

    public RealmList<DecryptedProperty> getPropertyItems() {
        return propertyItems;
    }

    public void setPropertyItems(RealmList<DecryptedProperty> propertyItems) {
        this.propertyItems = propertyItems;
    }

    public RealmList<DecryptedVehicle> getVehicleItems() {
        return vehicleItems;
    }

    public void setVehicleItems(RealmList<DecryptedVehicle> vehicleItems) {
        this.vehicleItems = vehicleItems;
    }

    public RealmList<DecryptedAsset> getAssetItems() {
        return assetItems;
    }

    public void setAssetItems(RealmList<DecryptedAsset> assetItems) {
        this.assetItems = assetItems;
    }

    public RealmList<DecryptedInsurance> getInsuranceItems() {
        return insuranceItems;
    }

    public void setInsuranceItems(RealmList<DecryptedInsurance> insuranceItems) {
        this.insuranceItems = insuranceItems;
    }

    public RealmList<DecryptedTax> getTaxesItems() {
        return taxesItems;
    }

    public void setTaxesItems(RealmList<DecryptedTax> taxesItems) {
        this.taxesItems = taxesItems;
    }

    public RealmList<DecryptedHomeList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<DecryptedHomeList> listItems) {
        this.listItems = listItems;
    }

    public static Creator<DecryptedCombine> getCREATOR() {
        return CREATOR;
    }

    protected DecryptedCombine(Parcel in) {
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DecryptedCombine> CREATOR = new Creator<DecryptedCombine>() {
        @Override
        public DecryptedCombine createFromParcel(Parcel in) {
            return new DecryptedCombine(in);
        }

        @Override
        public DecryptedCombine[] newArray(int size) {
            return new DecryptedCombine[size];
        }
    };

    @Override
    public String toString() {
        return "DecryptedCombine{" +
                "id=" + id +
                ", financialItems=" + financialItems +
                ", paymentItems=" + paymentItems +
                ", propertyItems=" + propertyItems +
                ", vehicleItems=" + vehicleItems +
                ", assetItems=" + assetItems +
                ", insuranceItems=" + insuranceItems +
                ", taxesItems=" + taxesItems +
                ", listItems=" + listItems +
                '}';
    }
}

