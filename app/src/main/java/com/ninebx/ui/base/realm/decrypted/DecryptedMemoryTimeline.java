package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.RealmString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alok on 24/01/18.
 */
public class DecryptedMemoryTimeline implements Parcelable {

    @Ignore public String searchField = "";
    @PrimaryKey //@Required
            long id = 0;
    @Required
    private ArrayList<RealmString> backingImages = new ArrayList<>();
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

    public DecryptedMemoryTimeline(long id, ArrayList<RealmString> backingImages, List<String> photosId, String selectionType, String title, String date, String place, String contacts, String notes, String attachmentNames, Date selectedDate, String created, String modified, Boolean isPrivate, String createdUser) {
        this.id = id;
        this.backingImages = backingImages;
        this.photosId = photosId;
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

    public DecryptedMemoryTimeline() {
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public ArrayList<RealmString> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(ArrayList<RealmString> backingImages) {
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
    public String toString() {
        return "DecryptedMemoryTimeline{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", place='" + place + '\'' +
                ", contacts='" + contacts + '\'' +
                ", notes='" + notes + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", selectedDate=" + selectedDate +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", createdUser='" + createdUser + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.searchField);
        dest.writeLong(this.id);
        dest.writeList(this.backingImages);
        dest.writeStringList(this.photosId);
        dest.writeString(this.selectionType);
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeString(this.place);
        dest.writeString(this.contacts);
        dest.writeString(this.notes);
        dest.writeString(this.attachmentNames);
        dest.writeLong(this.selectedDate != null ? this.selectedDate.getTime() : -1);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.createdUser);
    }

    protected DecryptedMemoryTimeline(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
        this.selectionType = in.readString();
        this.title = in.readString();
        this.date = in.readString();
        this.place = in.readString();
        this.contacts = in.readString();
        this.notes = in.readString();
        this.attachmentNames = in.readString();
        long tmpSelectedDate = in.readLong();
        this.selectedDate = tmpSelectedDate == -1 ? null : new Date(tmpSelectedDate);
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.createdUser = in.readString();
    }

    public static final Creator<DecryptedMemoryTimeline> CREATOR = new Creator<DecryptedMemoryTimeline>() {
        @Override
        public DecryptedMemoryTimeline createFromParcel(Parcel source) {
            return new DecryptedMemoryTimeline(source);
        }

        @Override
        public DecryptedMemoryTimeline[] newArray(int size) {
            return new DecryptedMemoryTimeline[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DecryptedMemoryTimeline that = (DecryptedMemoryTimeline) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
