package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.home.baseCategories.OptionItem;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by smrit on 13-02-2018.
 */

public class DecryptedCombine implements Parcelable {

    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private ArrayList<DecryptedFinancial> financialItems = new ArrayList<DecryptedFinancial>();
    @Required
    private ArrayList<DecryptedPayment> paymentItems = new ArrayList<DecryptedPayment>();
    @Required
    private ArrayList<DecryptedProperty> propertyItems = new ArrayList<DecryptedProperty>();
    @Required
    private ArrayList<DecryptedVehicle> vehicleItems = new ArrayList<DecryptedVehicle>();
    @Required
    private ArrayList<DecryptedAsset> assetItems = new ArrayList<DecryptedAsset>();
    @Required
    private ArrayList<DecryptedInsurance> insuranceItems = new ArrayList<DecryptedInsurance>();
    @Required
    private ArrayList<DecryptedTax> taxesItems = new ArrayList<DecryptedTax>();
    @Required
    private ArrayList<DecryptedHomeList> listItems = new ArrayList<DecryptedHomeList>();

    public DecryptedCombine() {
    }

    public DecryptedCombine(long id, ArrayList<DecryptedFinancial> financialItems, ArrayList<DecryptedPayment> paymentItems, ArrayList<DecryptedProperty> propertyItems, ArrayList<DecryptedVehicle> vehicleItems, ArrayList<DecryptedAsset> assetItems, ArrayList<DecryptedInsurance> insuranceItems, ArrayList<DecryptedTax> taxesItems, ArrayList<DecryptedHomeList> listItems) {
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
    @Ignore
    public String searchField = "";

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public ArrayList<DecryptedFinancial> getFinancialItems() {
        return financialItems;
    }

    public void setFinancialItems(ArrayList<DecryptedFinancial> financialItems) {
        this.financialItems = financialItems;
    }

    public ArrayList<DecryptedPayment> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(ArrayList<DecryptedPayment> paymentItems) {
        this.paymentItems = paymentItems;
    }

    public ArrayList<DecryptedProperty> getPropertyItems() {
        return propertyItems;
    }

    public void setPropertyItems(ArrayList<DecryptedProperty> propertyItems) {
        this.propertyItems = propertyItems;
    }

    public ArrayList<DecryptedVehicle> getVehicleItems() {
        return vehicleItems;
    }

    public void setVehicleItems(ArrayList<DecryptedVehicle> vehicleItems) {
        this.vehicleItems = vehicleItems;
    }

    public ArrayList<DecryptedAsset> getAssetItems() {
        return assetItems;
    }

    public void setAssetItems(ArrayList<DecryptedAsset> assetItems) {
        this.assetItems = assetItems;
    }

    public ArrayList<DecryptedInsurance> getInsuranceItems() {
        return insuranceItems;
    }

    public void setInsuranceItems(ArrayList<DecryptedInsurance> insuranceItems) {
        this.insuranceItems = insuranceItems;
    }

    public ArrayList<DecryptedTax> getTaxesItems() {
        return taxesItems;
    }

    public void setTaxesItems(ArrayList<DecryptedTax> taxesItems) {
        this.taxesItems = taxesItems;
    }

    public ArrayList<DecryptedHomeList> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<DecryptedHomeList> listItems) {
        this.listItems = listItems;
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
        ArrayList<Long> ids = new ArrayList<>();
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
        ArrayList<Long> ids = new ArrayList<>();
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
        ArrayList<Long> ids = new ArrayList<>();
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
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedVehicle selectedItem : vehicleItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    public ArrayList<OptionItem> getPropertyList() {
        ArrayList<OptionItem> ids = new ArrayList<>();
        for (DecryptedProperty selectedItem : propertyItems) {
            OptionItem optionItem = new OptionItem( selectedItem.getId(), selectedItem.getPropertyName(), "");
            if (!ids.contains(optionItem)) {
                ids.add(optionItem);
            }
        }
        return ids;
    }

    public ArrayList<OptionItem> getAutoList() {
        ArrayList<OptionItem> ids = new ArrayList<>();
        for (DecryptedVehicle selectedItem : vehicleItems) {
            OptionItem optionItem = new OptionItem( selectedItem.getId(), selectedItem.getVehicleName(), "");
            if (!ids.contains(optionItem)) {
                ids.add(optionItem);
            }
        }
        return ids;
    }

    public int getOtherCount(String selectionType) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
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
        ArrayList<Long> ids = new ArrayList<>();
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
        ArrayList<Long> ids = new ArrayList<>();
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
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedAsset selectedItem : assetItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    public int getListsCount(String selectionType, long detilsId ) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedHomeList selectedItem : listItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += (selectedItem.getSelectionType().equals(selectionType) && selectedItem.getDetailsId() == detilsId)? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeTypedList(this.financialItems);
        dest.writeTypedList(this.paymentItems);
        dest.writeTypedList(this.propertyItems);
        dest.writeTypedList(this.vehicleItems);
        dest.writeTypedList(this.assetItems);
        dest.writeTypedList(this.insuranceItems);
        dest.writeTypedList(this.taxesItems);
        dest.writeTypedList(this.listItems);
        dest.writeString(this.searchField);
    }

    protected DecryptedCombine(Parcel in) {
        this.id = in.readLong();
        this.financialItems = in.createTypedArrayList(DecryptedFinancial.CREATOR);
        this.paymentItems = in.createTypedArrayList(DecryptedPayment.CREATOR);
        this.propertyItems = in.createTypedArrayList(DecryptedProperty.CREATOR);
        this.vehicleItems = in.createTypedArrayList(DecryptedVehicle.CREATOR);
        this.assetItems = in.createTypedArrayList(DecryptedAsset.CREATOR);
        this.insuranceItems = in.createTypedArrayList(DecryptedInsurance.CREATOR);
        this.taxesItems = in.createTypedArrayList(DecryptedTax.CREATOR);
        this.listItems = in.createTypedArrayList(DecryptedHomeList.CREATOR);
        this.searchField = in.readString();
    }

    public static final Creator<DecryptedCombine> CREATOR = new Creator<DecryptedCombine>() {
        @Override
        public DecryptedCombine createFromParcel(Parcel source) {
            return new DecryptedCombine(source);
        }

        @Override
        public DecryptedCombine[] newArray(int size) {
            return new DecryptedCombine[size];
        }
    };
}

