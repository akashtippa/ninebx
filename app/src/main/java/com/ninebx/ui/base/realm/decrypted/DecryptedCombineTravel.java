package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.home.travel.Documents;
import com.ninebx.ui.base.realm.home.travel.Loyalty;
import com.ninebx.ui.base.realm.home.travel.Travel;
import com.ninebx.ui.base.realm.home.travel.Vacations;
import com.ninebx.ui.base.realm.lists.TravelList;

import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by smrit on 15-02-2018.
 */

public class DecryptedCombineTravel implements Parcelable {
    public static final Creator<DecryptedCombineTravel> CREATOR = new Creator<DecryptedCombineTravel>() {
        @Override
        public DecryptedCombineTravel createFromParcel(Parcel in) {
            return new DecryptedCombineTravel(in);
        }

        @Override
        public DecryptedCombineTravel[] newArray(int size) {
            return new DecryptedCombineTravel[size];
        }
    };
    @PrimaryKey //@Required
    private int id = 0;

    @Required private RealmList<DecryptedDocuments> documentsItems      = new RealmList<DecryptedDocuments>();
    @Required private RealmList<DecryptedLoyalty> loyaltyItems        = new RealmList<DecryptedLoyalty>();
    @Required private RealmList<DecryptedTravel> travelItems         = new RealmList<DecryptedTravel>();
    @Required private RealmList<DecryptedVacations> vacationsItems      = new RealmList<DecryptedVacations>();
    @Required private RealmList<DecryptedTravelList> listItems           = new RealmList<DecryptedTravelList>();
    @Required
    private RealmList<Documents> documentsItems = new RealmList<Documents>();
    @Required
    private RealmList<Loyalty> loyaltyItems = new RealmList<Loyalty>();
    @Required
    private RealmList<Travel> travelItems = new RealmList<Travel>();
    @Required
    private RealmList<Vacations> vacationsItems = new RealmList<Vacations>();
    @Required
    private RealmList<TravelList> listItems = new RealmList<TravelList>();

    public DecryptedCombineTravel() {
    }

    public DecryptedCombineTravel(int id, RealmList<DecryptedDocuments> documentsItems, RealmList<DecryptedLoyalty> loyaltyItems, RealmList<DecryptedTravel> travelItems, RealmList<DecryptedVacations> vacationsItems, RealmList<DecryptedTravelList> listItems) {
        this.id = id;
        this.documentsItems = documentsItems;
        this.loyaltyItems = loyaltyItems;
        this.travelItems = travelItems;
        this.vacationsItems = vacationsItems;
        this.listItems = listItems;
    }

    protected DecryptedCombineTravel(Parcel in) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<DecryptedDocuments> getDocumentsItems() {
        return documentsItems;
    }

    public void setDocumentsItems(RealmList<DecryptedDocuments> documentsItems) {
        this.documentsItems = documentsItems;
    }

    public RealmList<DecryptedLoyalty> getLoyaltyItems() {
        return loyaltyItems;
    }

    public void setLoyaltyItems(RealmList<DecryptedLoyalty> loyaltyItems) {
        this.loyaltyItems = loyaltyItems;
    }

    public RealmList<DecryptedTravel> getTravelItems() {
        return travelItems;
    }

    public void setTravelItems(RealmList<DecryptedTravel> travelItems) {
        this.travelItems = travelItems;
    }

    public RealmList<DecryptedVacations> getVacationsItems() {
        return vacationsItems;
    }

    public void setVacationsItems(RealmList<DecryptedVacations> vacationsItems) {
        this.vacationsItems = vacationsItems;
    }

    public RealmList<DecryptedTravelList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<DecryptedTravelList> listItems) {
        this.listItems = listItems;
    }

    @Override
    public String toString() {
        return "DecryptedCombineTravel{" +
                "id=" + id +
                ", documentsItems=" + documentsItems +
                ", loyaltyItems=" + loyaltyItems +
                ", travelItems=" + travelItems +
                ", vacationsItems=" + vacationsItems +
                ", listItems=" + listItems +
                '}';
    }
}
