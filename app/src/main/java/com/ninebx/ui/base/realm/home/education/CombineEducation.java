package com.ninebx.ui.base.realm.home.education;

import com.ninebx.ui.base.realm.lists.EducationList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
@RealmClass
public class CombineEducation extends RealmObject {

    @PrimaryKey
    private Integer id = 0;

    @Required private RealmList<Education> educationItems          = new RealmList<>();
    @Required private RealmList<MainEducation> mainEducationItems      = new RealmList<>();
    @Required private RealmList<Work> workItems               = new RealmList<>();

    @Required private RealmList<EducationList> listItems               = new RealmList<>();

    public CombineEducation(Integer id, RealmList<Education> educationItems, RealmList<MainEducation> mainEducationItems, RealmList<Work> workItems, RealmList<EducationList> listItems) {
        this.id = id;
        this.educationItems = educationItems;
        this.mainEducationItems = mainEducationItems;
        this.workItems = workItems;
        this.listItems = listItems;
    }

    public CombineEducation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealmList<Education> getEducationItems() {
        return educationItems;
    }

    public void setEducationItems(RealmList<Education> educationItems) {
        this.educationItems = educationItems;
    }

    public RealmList<MainEducation> getMainEducationItems() {
        return mainEducationItems;
    }

    public void setMainEducationItems(RealmList<MainEducation> mainEducationItems) {
        this.mainEducationItems = mainEducationItems;
    }

    public RealmList<Work> getWorkItems() {
        return workItems;
    }

    public void setWorkItems(RealmList<Work> workItems) {
        this.workItems = workItems;
    }

    public RealmList<EducationList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<EducationList> listItems) {
        this.listItems = listItems;
    }
}
