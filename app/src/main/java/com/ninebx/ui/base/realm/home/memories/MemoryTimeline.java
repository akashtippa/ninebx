package com.ninebx.ui.base.realm.home.memories;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.RealmString;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
@RealmClass
public class MemoryTimeline extends RealmObject implements Parcelable {

    public static final Creator<MemoryTimeline> CREATOR = new Creator<MemoryTimeline>() {
        @Override
        public MemoryTimeline createFromParcel(Parcel in) {
            return new MemoryTimeline(in);
        }

        @Override
        public MemoryTimeline[] newArray(int size) {
            return new MemoryTimeline[size];
        }
    };
    @PrimaryKey //@Required
            int id = 0;
    @Required
    private RealmList<RealmString> backingImages = new RealmList<>();
    @Ignore
    @Required
    private List<String> photosId = new ArrayList<>();
    @Required
    private String selectionType = "";
    @Required
    private String title = "";
    @Required
    private String date = "";
    @Required
    private String place = "";
    @Required
    private String contacts = "";
    @Required
    private String notes = "";
    @Required
    private String attachmentNames = "";
    @Required
    private Date selectedDate = new Date();
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String createdUser = "";

    protected MemoryTimeline(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        title = in.readString();
        date = in.readString();
        place = in.readString();
        contacts = in.readString();
        notes = in.readString();
        attachmentNames = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        createdUser = in.readString();
    }

    public MemoryTimeline(String selectionType, String title, String date, String place, String contacts, String notes, String attachmentNames, Date selectedDate, String created, String modified, Boolean isPrivate, String createdUser) {
        this.selectionType = selectionType;
        this.title = title;
        this.date = date;
        this.place = place;
        this.contacts = contacts;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.selectedDate = selectedDate;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
    }

    public MemoryTimeline() {
    }

    public static ArrayList<MemoryTimeline> createParcelableList(@NotNull RealmResults<MemoryTimeline> memoryTimelineRealmResults) {
        ArrayList memoryView = new ArrayList();
        memoryView.addAll(memoryTimelineRealmResults);
        return memoryView;
    }

    @NotNull
    public static MemoryTimeline createMemoryTimeLine(@NotNull MemoryTimeline memoryTimeline) {
        MemoryTimeline memoryTimeline1 = new MemoryTimeline();
        memoryTimeline1.setTitle(memoryTimeline.title);
        memoryTimeline1.setDate(memoryTimeline.date);
        memoryTimeline1.setPlace(memoryTimeline.place);
        memoryTimeline1.setContacts(memoryTimeline.contacts);
        memoryTimeline1.setNotes(memoryTimeline.notes);
        return memoryTimeline1;

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<RealmString> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(RealmList<RealmString> backingImages) {
        this.backingImages = backingImages;
    }

    public List<String> getPhotosId() {
        photosId = new ArrayList<>();
        for (RealmString realmString : backingImages) {
            photosId.add(realmString.getStringValue());
        }
        return photosId;
    }

    public void setPhotosId(List<String> photosId) {
        this.photosId = photosId;
        backingImages.clear();
        for (String string : photosId) {
            backingImages.add(new RealmString(string));
        }
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(place);
        dest.writeString(contacts);
        dest.writeString(notes);
        dest.writeString(attachmentNames);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(createdUser);
    }

}
