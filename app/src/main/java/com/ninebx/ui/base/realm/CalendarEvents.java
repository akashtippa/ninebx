package com.ninebx.ui.base.realm;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

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

    @PrimaryKey //@Required
    private long id = 0;
    
    @Required
    private RealmList<String> eventID = new RealmList<>();
    @Required private String classType = "Calendar";

    @Required private RealmList<String> title = new RealmList<>();
    @Required private RealmList<String> location = new RealmList<>();

    @Required private RealmList<Boolean> isAllDay = new RealmList<>();

    @Required private RealmList<String> notes = new RealmList<>();
    @Required private RealmList<String> startsDate = new RealmList<>();
    @Required private RealmList<String> endsDate = new RealmList<>();

    @Required private RealmList<String> repeats = new RealmList<>();
    @Required private RealmList<String> endRepeat = new RealmList<>();
    @Required private RealmList<String> reminder = new RealmList<>();
    @Required private RealmList<String> travelTime = new RealmList<>();
    //dynamic var invites = RLMArray
    @Required private RealmList<String> alert = new RealmList<>();
    @Required private RealmList<String> showAs = new RealmList<>();
    @Required private RealmList<String> url = new RealmList<>();

    @Required private RealmList<String> isReminderSet = new RealmList<>();

    @Required private String attachmentNames = "";

    @Required private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    @Required private RealmList<String> photosId = new RealmList<>();

    @Ignore
    public ArrayList<String> allDays = new ArrayList<>();


    public CalendarEvents() {
    }

    public CalendarEvents(long id, RealmList<String> eventID, String classType, RealmList<String> title, RealmList<String> location, RealmList<Boolean> isAllDay, RealmList<String> notes, RealmList<String> startsDate, RealmList<String> endsDate, RealmList<String> repeats, RealmList<String> endRepeat, RealmList<String> reminder, RealmList<String> travelTime, RealmList<String> alert, RealmList<String> showAs, RealmList<String> url, RealmList<String> isReminderSet, String attachmentNames, RealmList<RealmString> backingImages, RealmList<String> photosId) {
        this.id = id;
        this.eventID = eventID;
        this.classType = classType;
        this.title = title;
        this.location = location;
        this.isAllDay = isAllDay;
        this.notes = notes;
        this.startsDate = startsDate;
        this.endsDate = endsDate;
        this.repeats = repeats;
        this.endRepeat = endRepeat;
        this.reminder = reminder;
        this.travelTime = travelTime;
        this.alert = alert;
        this.showAs = showAs;
        this.url = url;
        this.isReminderSet = isReminderSet;
        this.attachmentNames = attachmentNames;
        this.backingImages = backingImages;
        this.photosId = photosId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public RealmList<String> getIsReminderSet() {
        return isReminderSet;
    }

    public void setIsReminderSet(RealmList<String> isReminderSet) {
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

    public RealmList<String> getPhotosId() {
        return photosId;
    }

    public void setPhotosId(RealmList<String> photosId) {
        this.photosId = photosId;
    }

    @Override
    public String toString() {
        return "CalendarEvents{" +
                "id=" + id +
                ", eventID=" + eventID +
                ", classType='" + classType + '\'' +
                ", title=" + title +
                ", location=" + location +
                ", isAllDay=" + isAllDay +
                ", notes=" + notes +
                ", startsDate=" + startsDate +
                ", endsDate=" + endsDate +
                ", repeats=" + repeats +
                ", endRepeat=" + endRepeat +
                ", reminder=" + reminder +
                ", travelTime=" + travelTime +
                ", alert=" + alert +
                ", showAs=" + showAs +
                ", url=" + url +
                ", isReminderSet=" + isReminderSet +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                '}';
    }

    @NotNull
    public int getPositionForDay(@NotNull Date selectedDateIndex) {
        int position = 0;
        return position;
    }
}
