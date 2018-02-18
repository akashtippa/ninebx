package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by smrit on 18-02-2018.
 */

public class DecryptedCombineShopping implements Parcelable{

    @PrimaryKey
    private int id = 0;

    @Required
    private RealmList<DecryptedLoyaltyPrograms> loyaltyProgramsItems            = new RealmList<>();
    @Required private RealmList<DecryptedRecentPurchase> recentPurchaseItems             = new RealmList<>();
    @Required private RealmList<DecryptedShopping> shoppingItems                         = new RealmList<>();
    @Required private RealmList<DecryptedClothingSizes> clothingSizesItems               = new RealmList<>();

    @Required private RealmList<DecryptedShoppingList> listItems                         = new RealmList<>();

    public DecryptedCombineShopping() {
    }

    public DecryptedCombineShopping(int id, RealmList<DecryptedLoyaltyPrograms> loyaltyProgramsItems, RealmList<DecryptedRecentPurchase> recentPurchaseItems, RealmList<DecryptedShopping> shoppingItems, RealmList<DecryptedClothingSizes> clothingSizesItems, RealmList<DecryptedShoppingList> listItems) {
        this.id = id;
        this.loyaltyProgramsItems = loyaltyProgramsItems;
        this.recentPurchaseItems = recentPurchaseItems;
        this.shoppingItems = shoppingItems;
        this.clothingSizesItems = clothingSizesItems;
        this.listItems = listItems;
    }

    protected DecryptedCombineShopping(Parcel in) {
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

    public static final Creator<DecryptedCombineShopping> CREATOR = new Creator<DecryptedCombineShopping>() {
        @Override
        public DecryptedCombineShopping createFromParcel(Parcel in) {
            return new DecryptedCombineShopping(in);
        }

        @Override
        public DecryptedCombineShopping[] newArray(int size) {
            return new DecryptedCombineShopping[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<DecryptedLoyaltyPrograms> getLoyaltyProgramsItems() {
        return loyaltyProgramsItems;
    }

    public void setLoyaltyProgramsItems(RealmList<DecryptedLoyaltyPrograms> loyaltyProgramsItems) {
        this.loyaltyProgramsItems = loyaltyProgramsItems;
    }

    public RealmList<DecryptedRecentPurchase> getRecentPurchaseItems() {
        return recentPurchaseItems;
    }

    public void setRecentPurchaseItems(RealmList<DecryptedRecentPurchase> recentPurchaseItems) {
        this.recentPurchaseItems = recentPurchaseItems;
    }

    public RealmList<DecryptedShopping> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(RealmList<DecryptedShopping> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public RealmList<DecryptedClothingSizes> getClothingSizesItems() {
        return clothingSizesItems;
    }

    public void setClothingSizesItems(RealmList<DecryptedClothingSizes> clothingSizesItems) {
        this.clothingSizesItems = clothingSizesItems;
    }

    public RealmList<DecryptedShoppingList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<DecryptedShoppingList> listItems) {
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
}