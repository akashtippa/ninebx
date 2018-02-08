package com.ninebx.ui.base.realm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 11/01/18.
 */
@RealmClass
public class CalendarEvents extends RealmObject {

    @PrimaryKey
    Integer id = 0;
    
    @Required
    private RealmList<RealmString> eventID = new RealmList<>();
    @Required private String classType = "Calendar";

    @Required private RealmList<RealmString> title = new RealmList<>();
    @Required private RealmList<RealmString> location = new RealmList<>();

    @Required private RealmList<Boolean> isAllDay = new RealmList<>();

    @Required private RealmList<RealmString> notes = new RealmList<>();
    @Required private RealmList<RealmString> startsDate = new RealmList<>();
    @Required private RealmList<RealmString> endsDate = new RealmList<>();

    @Required private RealmList<RealmString> repeats = new RealmList<>();
    @Required private RealmList<RealmString> endRepeat = new RealmList<>();
    @Required private RealmList<RealmString> reminder = new RealmList<>();
    @Required private RealmList<RealmString> travelTime = new RealmList<>();
    //dynamic var invites = RLMArray
    @Required private RealmList<RealmString> alert = new RealmList<>();
    @Required private RealmList<RealmString> showAs = new RealmList<>();
    @Required private RealmList<RealmString> url = new RealmList<>();

    @Required private RealmList<Boolean> isReminderSet = new RealmList<>();

    @Required private String attachmentNames = "";

    @Required private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    @Required private List<String> photosId = new ArrayList<>();


    public CalendarEvents() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealmList<RealmString> getEventID() {
        return eventID;
    }

    public void setEventID(RealmList<RealmString> eventID) {
        this.eventID = eventID;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public RealmList<RealmString> getTitle() {
        return title;
    }

    public void setTitle(RealmList<RealmString> title) {
        this.title = title;
    }

    public RealmList<RealmString> getLocation() {
        return location;
    }

    public void setLocation(RealmList<RealmString> location) {
        this.location = location;
    }

    public RealmList<Boolean> getIsAllDay() {
        return isAllDay;
    }

    public void setIsAllDay(RealmList<Boolean> isAllDay) {
        this.isAllDay = isAllDay;
    }

    public RealmList<RealmString> getNotes() {
        return notes;
    }

    public void setNotes(RealmList<RealmString> notes) {
        this.notes = notes;
    }

    public RealmList<RealmString> getStartsDate() {
        return startsDate;
    }

    public void setStartsDate(RealmList<RealmString> startsDate) {
        this.startsDate = startsDate;
    }

    public RealmList<RealmString> getEndsDate() {
        return endsDate;
    }

    public void setEndsDate(RealmList<RealmString> endsDate) {
        this.endsDate = endsDate;
    }

    public RealmList<RealmString> getRepeats() {
        return repeats;
    }

    public void setRepeats(RealmList<RealmString> repeats) {
        this.repeats = repeats;
    }

    public RealmList<RealmString> getEndRepeat() {
        return endRepeat;
    }

    public void setEndRepeat(RealmList<RealmString> endRepeat) {
        this.endRepeat = endRepeat;
    }

    public RealmList<RealmString> getReminder() {
        return reminder;
    }

    public void setReminder(RealmList<RealmString> reminder) {
        this.reminder = reminder;
    }

    public RealmList<RealmString> getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(RealmList<RealmString> travelTime) {
        this.travelTime = travelTime;
    }

    public RealmList<RealmString> getAlert() {
        return alert;
    }

    public void setAlert(RealmList<RealmString> alert) {
        this.alert = alert;
    }

    public RealmList<RealmString> getShowAs() {
        return showAs;
    }

    public void setShowAs(RealmList<RealmString> showAs) {
        this.showAs = showAs;
    }

    public RealmList<RealmString> getUrl() {
        return url;
    }

    public void setUrl(RealmList<RealmString> url) {
        this.url = url;
    }

    public RealmList<Boolean> getIsReminderSet() {
        return isReminderSet;
    }

    public void setIsReminderSet(RealmList<Boolean> isReminderSet) {
        this.isReminderSet = isReminderSet;
    }

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public RealmList<RealmString> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(RealmList<RealmString> backingImages) {
        this.backingImages = backingImages;
    }

    public List<String> getPhotosId() {
        return photosId;
    }

    public void setPhotosId(List<String> photosId) {
        this.photosId = photosId;
    }


}
