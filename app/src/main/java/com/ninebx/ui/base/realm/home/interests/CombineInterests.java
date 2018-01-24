package com.ninebx.ui.base.realm.home.interests;

import com.ninebx.ui.base.realm.lists.InterestsList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 24/01/18.
 */

public class CombineInterests extends RealmObject {

    @PrimaryKey
    private Integer id = 0;

    private RealmList<Interests> interestItems = new RealmList<>();
    private RealmList<InterestsList> listItems = new RealmList<>();

    public CombineInterests(Integer id, RealmList<Interests> interestItems, RealmList<InterestsList> listItems) {
        this.id = id;
        this.interestItems = interestItems;
        this.listItems = listItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealmList<Interests> getInterestItems() {
        return interestItems;
    }

    public void setInterestItems(RealmList<Interests> interestItems) {
        this.interestItems = interestItems;
    }

    public RealmList<InterestsList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<InterestsList> listItems) {
        this.listItems = listItems;
    }
}
