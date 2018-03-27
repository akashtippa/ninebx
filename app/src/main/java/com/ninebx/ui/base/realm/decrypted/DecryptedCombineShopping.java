package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by smrit on 18-02-2018.
 */

public class DecryptedCombineShopping implements Parcelable {

    @Ignore
    public String searchField = "";
    @PrimaryKey
    private long id = 0;
    @Required
    private ArrayList<DecryptedLoyaltyPrograms> loyaltyProgramsItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedRecentPurchase> recentPurchaseItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedShopping> shoppingItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedClothingSizes> clothingSizesItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedShoppingList> listItems = new ArrayList<>();

    public DecryptedCombineShopping() {
    }

    public DecryptedCombineShopping(long id, ArrayList<DecryptedLoyaltyPrograms> loyaltyProgramsItems, ArrayList<DecryptedRecentPurchase> recentPurchaseItems, ArrayList<DecryptedShopping> shoppingItems, ArrayList<DecryptedClothingSizes> clothingSizesItems, ArrayList<DecryptedShoppingList> listItems) {
        this.id = id;
        this.loyaltyProgramsItems = loyaltyProgramsItems;
        this.recentPurchaseItems = recentPurchaseItems;
        this.shoppingItems = shoppingItems;
        this.clothingSizesItems = clothingSizesItems;
        this.listItems = listItems;
    }

    public  long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public ArrayList<DecryptedLoyaltyPrograms> getLoyaltyProgramsItems() {
        return loyaltyProgramsItems;
    }

    public void setLoyaltyProgramsItems(ArrayList<DecryptedLoyaltyPrograms> loyaltyProgramsItems) {
        this.loyaltyProgramsItems = loyaltyProgramsItems;
    }

    public ArrayList<DecryptedRecentPurchase> getRecentPurchaseItems() {
        return recentPurchaseItems;
    }

    public void setRecentPurchaseItems(ArrayList<DecryptedRecentPurchase> recentPurchaseItems) {
        this.recentPurchaseItems = recentPurchaseItems;
    }

    public ArrayList<DecryptedShopping> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(ArrayList<DecryptedShopping> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public ArrayList<DecryptedClothingSizes> getClothingSizesItems() {
        return clothingSizesItems;
    }

    public void setClothingSizesItems(ArrayList<DecryptedClothingSizes> clothingSizesItems) {
        this.clothingSizesItems = clothingSizesItems;
    }

    public ArrayList<DecryptedShoppingList> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<DecryptedShoppingList> listItems) {
        this.listItems = listItems;
    }

    @Override
    public String toString() {
        return "DecryptedCombineShopping{" +
                "id=" + id +
                ", loyaltyProgramsItems=" + loyaltyProgramsItems +
                ", recentPurchaseItems=" + recentPurchaseItems +
                ", shoppingItems=" + shoppingItems +
                ", clothingSizesItems=" + clothingSizesItems +
                ", listItems=" + listItems +
                '}';
    }

    public int getLoyaltyPrograms(String selectionType) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedLoyaltyPrograms decryptedLicense : loyaltyProgramsItems) {
            if(!ids.contains(decryptedLicense.getId())){
                count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(decryptedLicense.getId());
            }
        }
        return count;
    }


    public int getRecentPurchases(String selectionType) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedRecentPurchase decryptedLicense : recentPurchaseItems) {
            if(!ids.contains(decryptedLicense.getId())){
                count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(decryptedLicense.getId());
            }
        }
        return count;
    }

    public int getShoppingLists(String selectionType, long detailsId ) {
        int count = 0;
        for (DecryptedShoppingList decryptedLicense : listItems) {
            count += ( decryptedLicense.getSelectionType().equals(selectionType) && decryptedLicense.getDetailsId() == detailsId )  ? 1 : 0;
        }
        return count;
    }
    public int getClothingSizesItems(String name ) {
        Log.d("Name ",name);
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedClothingSizes decryptedLicense : clothingSizesItems) {
            if(!ids.contains(decryptedLicense.getId())){
                count += decryptedLicense.getPersonName().equals(name) ? 1 : 0;
                ids.add(decryptedLicense.getId());
            }
        }
        return count;
    }

    public int getServices(String selectionType) {
        int count = 0;
        for (DecryptedShopping decryptedLicense : shoppingItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.searchField);
        dest.writeLong(this.id);
        dest.writeTypedList(this.loyaltyProgramsItems);
        dest.writeTypedList(this.recentPurchaseItems);
        dest.writeTypedList(this.shoppingItems);
        dest.writeTypedList(this.clothingSizesItems);
        dest.writeTypedList(this.listItems);
    }

    protected DecryptedCombineShopping(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.loyaltyProgramsItems = in.createTypedArrayList(DecryptedLoyaltyPrograms.CREATOR);
        this.recentPurchaseItems = in.createTypedArrayList(DecryptedRecentPurchase.CREATOR);
        this.shoppingItems = in.createTypedArrayList(DecryptedShopping.CREATOR);
        this.clothingSizesItems = in.createTypedArrayList(DecryptedClothingSizes.CREATOR);
        this.listItems = in.createTypedArrayList(DecryptedShoppingList.CREATOR);
    }

    public static final Creator<DecryptedCombineShopping> CREATOR = new Creator<DecryptedCombineShopping>() {
        @Override
        public DecryptedCombineShopping createFromParcel(Parcel source) {
            return new DecryptedCombineShopping(source);
        }

        @Override
        public DecryptedCombineShopping[] newArray(int size) {
            return new DecryptedCombineShopping[size];
        }
    };
}
