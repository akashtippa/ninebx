package com.ninebx.ui.base.realm.home.travel;

import com.ninebx.ui.base.realm.lists.TravelList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 29/01/18.
 */
@RealmClass
public class CombineTravel extends RealmObject {

    @PrimaryKey //@Required
    private int id = 0;

    @Required private RealmList<Documents> documentsItems      = new RealmList<Documents>();
    @Required private RealmList<Loyalty> loyaltyItems        = new RealmList<Loyalty>();
    @Required private RealmList<Travel> travelItems         = new RealmList<Travel>();
    @Required private RealmList<Vacations> vacationsItems      = new RealmList<Vacations>();

    @Required private RealmList<TravelList> listItems           = new RealmList<TravelList>();

    public CombineTravel(int id, RealmList documentsItems, RealmList loyaltyItems, RealmList travelItems, RealmList vacationsItems, RealmList listItems) {
        this.id = id;
        this.documentsItems = documentsItems;
        this.loyaltyItems = loyaltyItems;
        this.travelItems = travelItems;
        this.vacationsItems = vacationsItems;
        this.listItems = listItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList getDocumentsItems() {
        return documentsItems;
    }

    public void setDocumentsItems(RealmList documentsItems) {
        this.documentsItems = documentsItems;
    }

    public RealmList getLoyaltyItems() {
        return loyaltyItems;
    }

    public void setLoyaltyItems(RealmList loyaltyItems) {
        this.loyaltyItems = loyaltyItems;
    }

    public RealmList getTravelItems() {
        return travelItems;
    }

    public void setTravelItems(RealmList travelItems) {
        this.travelItems = travelItems;
    }

    public RealmList getVacationsItems() {
        return vacationsItems;
    }

    public void setVacationsItems(RealmList vacationsItems) {
        this.vacationsItems = vacationsItems;
    }

    public RealmList getListItems() {
        return listItems;
    }

    public void setListItems(RealmList listItems) {
        this.listItems = listItems;
    }

    public CombineTravel() {
    }
}
