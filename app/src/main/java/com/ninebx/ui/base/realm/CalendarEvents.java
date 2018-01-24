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

/**
 * Created by Alok on 11/01/18.
 */

public class CalendarEvents extends RealmObject {

    @PrimaryKey
    Integer id = 0;
    
    private RealmList<String> eventID = new RealmList<>();
    private String classType = "Calendar";

    private RealmList<String> title = new RealmList<>();
    private RealmList<String> location = new RealmList<>();

    private RealmList<Boolean> isAllDay = new RealmList<>();

    private RealmList<String> notes = new RealmList<>();
    private RealmList<String> startsDate = new RealmList<>();
    private RealmList<String> endsDate = new RealmList<>();

    private RealmList<String> repeats = new RealmList<>();
    private RealmList<String> endRepeat = new RealmList<>();
    private RealmList<String> reminder = new RealmList<>();
    private RealmList<String> travelTime = new RealmList<>();
    //dynamic var invites = RLMArray
    private RealmList<String> alert = new RealmList<>();
    private RealmList<String> showAs = new RealmList<>();
    private RealmList<String> url = new RealmList<>();

    private RealmList<Boolean> isReminderSet = new RealmList<>();

    private String attachmentNames = "";

    private RealmList<String> backingImages = new RealmList<>();

    @Ignore
    private List<String> photosId = new ArrayList<>();


    public CalendarEvents() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealmList<String> getEventID() {
        return eventID;
    }

    public void setEventID(RealmList<String> eventID) {
        this.eventID = eventID;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public RealmList<String> getTitle() {
        return title;
    }

    public void setTitle(RealmList<String> title) {
        this.title = title;
    }

    public RealmList<String> getLocation() {
        return location;
    }

    public void setLocation(RealmList<String> location) {
        this.location = location;
    }

    public RealmList<Boolean> getIsAllDay() {
        return isAllDay;
    }

    public void setIsAllDay(RealmList<Boolean> isAllDay) {
        this.isAllDay = isAllDay;
    }

    public RealmList<String> getNotes() {
        return notes;
    }

    public void setNotes(RealmList<String> notes) {
        this.notes = notes;
    }

    public RealmList<String> getStartsDate() {
        return startsDate;
    }

    public void setStartsDate(RealmList<String> startsDate) {
        this.startsDate = startsDate;
    }

    public RealmList<String> getEndsDate() {
        return endsDate;
    }

    public void setEndsDate(RealmList<String> endsDate) {
        this.endsDate = endsDate;
    }

    public RealmList<String> getRepeats() {
        return repeats;
    }

    public void setRepeats(RealmList<String> repeats) {
        this.repeats = repeats;
    }

    public RealmList<String> getEndRepeat() {
        return endRepeat;
    }

    public void setEndRepeat(RealmList<String> endRepeat) {
        this.endRepeat = endRepeat;
    }

    public RealmList<String> getReminder() {
        return reminder;
    }

    public void setReminder(RealmList<String> reminder) {
        this.reminder = reminder;
    }

    public RealmList<String> getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(RealmList<String> travelTime) {
        this.travelTime = travelTime;
    }

    public RealmList<String> getAlert() {
        return alert;
    }

    public void setAlert(RealmList<String> alert) {
        this.alert = alert;
    }

    public RealmList<String> getShowAs() {
        return showAs;
    }

    public void setShowAs(RealmList<String> showAs) {
        this.showAs = showAs;
    }

    public RealmList<String> getUrl() {
        return url;
    }

    public void setUrl(RealmList<String> url) {
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

    public RealmList<String> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(RealmList<String> backingImages) {
        this.backingImages = backingImages;
    }

    public List<String> getPhotosId() {
        return photosId;
    }

    public void setPhotosId(List<String> photosId) {
        this.photosId = photosId;
    }


}
