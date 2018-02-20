package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by smrit on 13-02-2018.
 */

public class DecryptedCombine implements Parcelable {

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
    @PrimaryKey //@Required
    private int id = 0;
    @Required
    private RealmList<DecryptedFinancial> financialItems = new RealmList<DecryptedFinancial>();
    @Required
    private RealmList<DecryptedPayment> paymentItems = new RealmList<DecryptedPayment>();
    @Required
    private RealmList<DecryptedProperty> propertyItems = new RealmList<DecryptedProperty>();
    @Required
    private RealmList<DecryptedVehicle> vehicleItems = new RealmList<DecryptedVehicle>();
    @Required
    private RealmList<DecryptedAsset> assetItems = new RealmList<DecryptedAsset>();
    @Required
    private RealmList<DecryptedInsurance> insuranceItems = new RealmList<DecryptedInsurance>();
    @Required
    private RealmList<DecryptedTax> taxesItems = new RealmList<DecryptedTax>();
    @Required
    private RealmList<DecryptedHomeList> listItems = new RealmList<DecryptedHomeList>();

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

    protected DecryptedCombine(Parcel in) {
        id = in.readInt();
    }

    public static Creator<DecryptedCombine> getCREATOR() {
        return CREATOR;
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

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

    public int getFinanceCount(String selectionType) {
        int count = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        for (DecryptedFinancial selectedItem : financialItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    public int getPaymentCount(String selectionType) {
        int count = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        for (DecryptedPayment selectedItem : paymentItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    public int getPropertyCount(String selectionType) {
        int count = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        for (DecryptedProperty selectedItem : propertyItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    public int getAutoCount(String selectionType) {
        int count = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        for (DecryptedVehicle selectedItem : vehicleItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    public int getOtherCount(String selectionType) {
        int count = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        for (DecryptedHomeList selectedItem : listItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    public int getInsuranceCount(String selectionType) {
        int count = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        for (DecryptedInsurance selectedItem : insuranceItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    public int getTaxesCount(String selectionType) {
        int count = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        for (DecryptedTax selectedItem : taxesItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    public int getAssetCount(String selectionType) {
        int count = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        for (DecryptedAsset selectedItem : assetItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    public int getListsCount(String selectionType) {
        int count = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        for (DecryptedHomeList selectedItem : listItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }
}

