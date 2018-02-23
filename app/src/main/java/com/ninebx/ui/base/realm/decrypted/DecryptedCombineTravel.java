package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

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
    private long id = 0;
    @Required
    private RealmList<DecryptedDocuments> documentsItems = new RealmList<DecryptedDocuments>();
    @Required
    private RealmList<DecryptedLoyalty> loyaltyItems = new RealmList<DecryptedLoyalty>();
    @Required
    private RealmList<DecryptedTravel> travelItems = new RealmList<DecryptedTravel>();
    @Required
    private RealmList<DecryptedVacations> vacationsItems = new RealmList<DecryptedVacations>();
    @Required
    private RealmList<DecryptedTravelList> listItems = new RealmList<DecryptedTravelList>();

    public DecryptedCombineTravel() {
    }

    public DecryptedCombineTravel(long id, RealmList<DecryptedDocuments> documentsItems, RealmList<DecryptedLoyalty> loyaltyItems, RealmList<DecryptedTravel> travelItems, RealmList<DecryptedVacations> vacationsItems, RealmList<DecryptedTravelList> listItems) {
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
        dest.writeLong(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public  long getId() {
        return id;
    }

    public void setId( long id ) {
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


    // Loyalty Programs
    public int getLoyaltyCount(String selectionType) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();

        for (DecryptedLoyalty decryptedLicense : loyaltyItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }


    // Travel Documents
    public int getTravelDocuments(String selectionType) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedDocuments decryptedLicense : documentsItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    // Travel Dates And Plans
    public int getTravelDatesAndPlans(String selectionType) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedVacations decryptedLicense : vacationsItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    // Travel Dates And Plans
    public int getServices(String selectionType) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedTravel decryptedLicense : travelItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    // Travel Lists
    public int getTravelLists(String selectionType) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedTravelList decryptedLicense : listItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }


}