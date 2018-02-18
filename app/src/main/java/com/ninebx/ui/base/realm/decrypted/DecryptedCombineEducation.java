package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.home.education.Education;
import com.ninebx.ui.base.realm.home.education.MainEducation;
import com.ninebx.ui.base.realm.home.education.Work;
import com.ninebx.ui.base.realm.lists.EducationList;

import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by smrit on 16-02-2018.
 */

public class DecryptedCombineEducation implements Parcelable{

    @PrimaryKey //@Required
    private int id = 0;

    @Required
    private RealmList<DecryptedEducation> educationItems          = new RealmList<>();
    @Required private RealmList<DecryptedMainEducation> mainEducationItems      = new RealmList<>();
    @Required private RealmList<DecryptedWork> workItems               = new RealmList<>();

    @Required private RealmList<DecryptedEducationList> listItems               = new RealmList<>();

    public DecryptedCombineEducation() {
    }

    public DecryptedCombineEducation(int id, RealmList<DecryptedEducation> educationItems, RealmList<DecryptedMainEducation> mainEducationItems, RealmList<DecryptedWork> workItems, RealmList<DecryptedEducationList> listItems) {
        this.id = id;
        this.educationItems = educationItems;
        this.mainEducationItems = mainEducationItems;
        this.workItems = workItems;
        this.listItems = listItems;
    }

    protected DecryptedCombineEducation(Parcel in) {
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

    public static final Creator<DecryptedCombineEducation> CREATOR = new Creator<DecryptedCombineEducation>() {
        @Override
        public DecryptedCombineEducation createFromParcel(Parcel in) {
            return new DecryptedCombineEducation(in);
        }

        @Override
        public DecryptedCombineEducation[] newArray(int size) {
            return new DecryptedCombineEducation[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<DecryptedEducation> getEducationItems() {
        return educationItems;
    }

    public void setEducationItems(RealmList<DecryptedEducation> educationItems) {
        this.educationItems = educationItems;
    }

    public RealmList<DecryptedMainEducation> getMainEducationItems() {
        return mainEducationItems;
    }

    public void setMainEducationItems(RealmList<DecryptedMainEducation> mainEducationItems) {
        this.mainEducationItems = mainEducationItems;
    }

    public RealmList<DecryptedWork> getWorkItems() {
        return workItems;
    }

    public void setWorkItems(RealmList<DecryptedWork> workItems) {
        this.workItems = workItems;
    }

    public RealmList<DecryptedEducationList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<DecryptedEducationList> listItems) {
        this.listItems = listItems;
    }
}