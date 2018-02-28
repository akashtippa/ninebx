package com.ninebx.ui.base.realm.home.shopping;

import com.ninebx.ui.base.realm.lists.ShoppingList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 29/01/18.
 */

@RealmClass
public class CombineShopping extends RealmObject {

    /* @Required*/
    @PrimaryKey
    private long id = 0;

    @Required private RealmList<LoyaltyPrograms> loyaltyProgramsItems            = new RealmList<LoyaltyPrograms>();
    @Required private RealmList<RecentPurchase> recentPurchaseItems             = new RealmList<RecentPurchase>();
    @Required private RealmList<Shopping> shoppingItems                   = new RealmList<Shopping>();
    @Required private RealmList<ClothingSizes> clothingSizesItems              = new RealmList<ClothingSizes>();

    @Required private RealmList<ShoppingList> listItems                       = new RealmList<ShoppingList>();

    public CombineShopping() {
    }

    public CombineShopping(long id, RealmList loyaltyProgramsItems, RealmList recentPurchaseItems, RealmList shoppingItems, RealmList clothingSizesItems, RealmList listItems) {
        this.id = id;
        this.loyaltyProgramsItems = loyaltyProgramsItems;
        this.recentPurchaseItems = recentPurchaseItems;
        this.shoppingItems = shoppingItems;
        this.clothingSizesItems = clothingSizesItems;
        this.listItems = listItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public RealmList<ShoppingList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList listItems) {
        this.listItems = listItems;
    }
}
