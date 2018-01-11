package com.ninebx.ui.home.calendar.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 11/01/18.
 */

public class CalendarEvents extends RealmObject {

    @PrimaryKey
    private Integer  id  = 0;
    private String  classType  = "Calendar";
    private String  title  = "";
    private String  location  = "";
    private Boolean  isAllDay = false;
    private String  notes  = "";
    private Date  startsDate  = new Date();
    private Date  endsDate  = new Date();
    private String  repeats  = "";
    private String  endRepeat  = "";
    private String  reminder  = "";
    private String  travelTime  = "";
    private String  alert  = "";
    private String  showAs  = "";
    private String  url  = "";
    private Boolean  isReminderSet  = false;
    private String  attachmentNames  = "";

    public CalendarEvents(Integer id, String classType, String title, String location, Boolean isAllDay, String notes, Date startsDate, Date endsDate, String repeats, String endRepeat, String reminder, String travelTime, String alert, String showAs, String url, Boolean isReminderSet, String attachmentNames) {
        this.id = id;
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
    }

    public CalendarEvents() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getAllDay() {
        return isAllDay;
    }

    public void setAllDay(Boolean allDay) {
        isAllDay = allDay;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getStartsDate() {
        return startsDate;
    }

    public void setStartsDate(Date startsDate) {
        this.startsDate = startsDate;
    }

    public Date getEndsDate() {
        return endsDate;
    }

    public void setEndsDate(Date endsDate) {
        this.endsDate = endsDate;
    }

    public String getRepeats() {
        return repeats;
    }

    public void setRepeats(String repeats) {
        this.repeats = repeats;
    }

    public String getEndRepeat() {
        return endRepeat;
    }

    public void setEndRepeat(String endRepeat) {
        this.endRepeat = endRepeat;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getShowAs() {
        return showAs;
    }

    public void setShowAs(String showAs) {
        this.showAs = showAs;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getReminderSet() {
        return isReminderSet;
    }

    public void setReminderSet(Boolean reminderSet) {
        isReminderSet = reminderSet;
    }

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarEvents that = (CalendarEvents) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
