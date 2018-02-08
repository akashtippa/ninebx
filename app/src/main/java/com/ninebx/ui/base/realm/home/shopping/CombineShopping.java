package com.ninebx.ui.base.realm.home.shopping;

import com.ninebx.ui.base.realm.lists.ShoppingList;

import io.realm.RealmList;
import io.realm.annotations.RealmClass;

/**
 * Created by Alok on 29/01/18.
 */

public class CombineShopping {

    private int id = 0;

    private RealmList<LoyaltyPrograms> loyaltyProgramsItems            = new RealmList<LoyaltyPrograms>();
    private RealmList<RecentPurchase> recentPurchaseItems             = new RealmList<RecentPurchase>();
    private RealmList<Shopping> shoppingItems                   = new RealmList<Shopping>();
    private RealmList<ClothingSizes> clothingSizesItems              = new RealmList<ClothingSizes>();

    private RealmList<ShoppingList> listItems                       = new RealmList<ShoppingList>();

    public CombineShopping(int id, RealmList loyaltyProgramsItems, RealmList recentPurchaseItems, RealmList shoppingItems, RealmList clothingSizesItems, RealmList listItems) {
        this.id = id;
        this.loyaltyProgramsItems = loyaltyProgramsItems;
        this.recentPurchaseItems = recentPurchaseItems;
        this.shoppingItems = shoppingItems;
        this.clothingSizesItems = clothingSizesItems;
        this.listItems = listItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList getLoyaltyProgramsItems() {
        return loyaltyProgramsItems;
    }

    public void setLoyaltyProgramsItems(RealmList loyaltyProgramsItems) {
        this.loyaltyProgramsItems = loyaltyProgramsItems;
    }

    public RealmList getRecentPurchaseItems() {
        return recentPurchaseItems;
    }

    public void setRecentPurchaseItems(RealmList recentPurchaseItems) {
        this.recentPurchaseItems = recentPurchaseItems;
    }

    public RealmList getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(RealmList shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public RealmList getClothingSizesItems() {
        return clothingSizesItems;
    }

    public void setClothingSizesItems(RealmList clothingSizesItems) {
        this.clothingSizesItems = clothingSizesItems;
    }

    public RealmList getListItems() {
        return listItems;
    }

    public void setListItems(RealmList listItems) {
        this.listItems = listItems;
    }
}
