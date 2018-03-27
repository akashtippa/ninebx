package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by smrit on 15-02-2018.
 */

public class DecryptedCombineTravel implements Parcelable {

    @Ignore
    public String searchField = "";
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private ArrayList<DecryptedDocuments> documentsItems = new ArrayList<DecryptedDocuments>();
    @Required
    private ArrayList<DecryptedLoyalty> loyaltyItems = new ArrayList<DecryptedLoyalty>();
    @Required
    private ArrayList<DecryptedTravel> travelItems = new ArrayList<DecryptedTravel>();
    @Required
    private ArrayList<DecryptedVacations> vacationsItems = new ArrayList<DecryptedVacations>();
    @Required
    private ArrayList<DecryptedTravelList> listItems = new ArrayList<DecryptedTravelList>();

    public DecryptedCombineTravel() {
    }

    public DecryptedCombineTravel(long id, ArrayList<DecryptedDocuments> documentsItems, ArrayList<DecryptedLoyalty> loyaltyItems, ArrayList<DecryptedTravel> travelItems, ArrayList<DecryptedVacations> vacationsItems, ArrayList<DecryptedTravelList> listItems) {
        this.id = id;
        this.documentsItems = documentsItems;
        this.loyaltyItems = loyaltyItems;
        this.travelItems = travelItems;
        this.vacationsItems = vacationsItems;
        this.listItems = listItems;
    }

    public  long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public ArrayList<DecryptedDocuments> getDocumentsItems() {
        return documentsItems;
    }

    public void setDocumentsItems(ArrayList<DecryptedDocuments> documentsItems) {
        this.documentsItems = documentsItems;
    }

    public ArrayList<DecryptedLoyalty> getLoyaltyItems() {
        return loyaltyItems;
    }

    public void setLoyaltyItems(ArrayList<DecryptedLoyalty> loyaltyItems) {
        this.loyaltyItems = loyaltyItems;
    }

    public ArrayList<DecryptedTravel> getTravelItems() {
        return travelItems;
    }

    public void setTravelItems(ArrayList<DecryptedTravel> travelItems) {
        this.travelItems = travelItems;
    }

    public ArrayList<DecryptedVacations> getVacationsItems() {
        return vacationsItems;
    }

    public void setVacationsItems(ArrayList<DecryptedVacations> vacationsItems) {
        this.vacationsItems = vacationsItems;
    }

    public ArrayList<DecryptedTravelList> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<DecryptedTravelList> listItems) {
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


    // Loyalty
    public int getLoyaltyCount(String selectionType) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedLoyalty selectedItem : loyaltyItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }


    // Travel Documents
    public int getTravelDocuments(String selectionType) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedDocuments selectedItem : documentsItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    public int getTravelDatesAndPlans(String selectionType) {
        int count = 0;

        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedTravel selectedItem : travelItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    // Travel Dates And Plans
    public int getTravelDatesPlans(String selectionType) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedVacations selectedItem : vacationsItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    // Travel Dates And Plans
    public int getServices(String selectionType) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
       for (DecryptedTravel selectedItem : travelItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }

        return count;
    }

    // Travel Lists
    public int getTravelLists(String selectionType, long detailsId ) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedTravelList selectedItem : listItems) {
            count += (selectedItem.getSelectionType().equals(selectionType) && selectedItem.getDetailsId() == detailsId )? 1 : 0;
            if (!ids.contains(selectedItem.getId())) {
                //count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return ids.size();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.searchField);
        dest.writeLong(this.id);
        dest.writeTypedList(this.documentsItems);
        dest.writeTypedList(this.loyaltyItems);
        dest.writeTypedList(this.travelItems);
        dest.writeTypedList(this.vacationsItems);
        dest.writeTypedList(this.listItems);
    }

    protected DecryptedCombineTravel(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.documentsItems = in.createTypedArrayList(DecryptedDocuments.CREATOR);
        this.loyaltyItems = in.createTypedArrayList(DecryptedLoyalty.CREATOR);
        this.travelItems = in.createTypedArrayList(DecryptedTravel.CREATOR);
        this.vacationsItems = in.createTypedArrayList(DecryptedVacations.CREATOR);
        this.listItems = in.createTypedArrayList(DecryptedTravelList.CREATOR);
    }

    public static final Creator<DecryptedCombineTravel> CREATOR = new Creator<DecryptedCombineTravel>() {
        @Override
        public DecryptedCombineTravel createFromParcel(Parcel source) {
            return new DecryptedCombineTravel(source);
        }

        @Override
        public DecryptedCombineTravel[] newArray(int size) {
            return new DecryptedCombineTravel[size];
        }
    };
}