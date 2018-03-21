package com.ninebx.ui.base.realm.decrypted;


import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.RealmString;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alok on 29/01/18.
 */
public class DecryptedVacations implements Parcelable {

    @Ignore public String searchField = "";
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private ArrayList<RealmString> backingImages = new ArrayList<>();
    @Ignore
    @Required
    private List<String> photosId = new ArrayList<>();
    @Required
    private String selectionType = "";
    @Required
    private String vac_description = "";
    @Required
    private String startDate = "";
    @Required
    private String endDate = "";
    @Required
    private String placesToVisit_1 = "";
    @Required
    private String placesToVisit_2 = "";
    @Required
    private String placesToVisit_3 = "";
    @Required
    private Boolean plansConfirmed = false;
    @Required
    private String notes = "";
    @Required
    private String attachmentNames = "";
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String createdUser = "";

    public DecryptedVacations(String selectionType, String vac_description, String startDate, String endDate, String placesToVisit_1, String placesToVisit_2, String placesToVisit_3, Boolean plansConfirmed, String notes, String attachmentNames, String created, String modified, Boolean isPrivate, String createdUser) {
        this.selectionType = selectionType;
        this.vac_description = vac_description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.placesToVisit_1 = placesToVisit_1;
        this.placesToVisit_2 = placesToVisit_2;
        this.placesToVisit_3 = placesToVisit_3;
        this.plansConfirmed = plansConfirmed;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
    }

    public DecryptedVacations() {
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

    public String getVac_description() {
        return vac_description;
    }

    public void setVac_description(String vac_description) {
        this.vac_description = vac_description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPlacesToVisit_1() {
        return placesToVisit_1;
    }

    public void setPlacesToVisit_1(String placesToVisit_1) {
        this.placesToVisit_1 = placesToVisit_1;
    }

    public String getPlacesToVisit_2() {
        return placesToVisit_2;
    }

    public void setPlacesToVisit_2(String placesToVisit_2) {
        this.placesToVisit_2 = placesToVisit_2;
    }

    public String getPlacesToVisit_3() {
        return placesToVisit_3;
    }

    public void setPlacesToVisit_3(String placesToVisit_3) {
        this.placesToVisit_3 = placesToVisit_3;
    }

    public Boolean getPlansConfirmed() {
        return plansConfirmed;
    }

    public void setPlansConfirmed(Boolean plansConfirmed) {
        this.plansConfirmed = plansConfirmed;
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
        return "DecryptedVacations{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", vac_description='" + vac_description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", placesToVisit_1='" + placesToVisit_1 + '\'' +
                ", placesToVisit_2='" + placesToVisit_2 + '\'' +
                ", placesToVisit_3='" + placesToVisit_3 + '\'' +
                ", plansConfirmed=" + plansConfirmed +
                ", notes='" + notes + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
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
        dest.writeString(this.vac_description);
        dest.writeString(this.startDate);
        dest.writeString(this.endDate);
        dest.writeString(this.placesToVisit_1);
        dest.writeString(this.placesToVisit_2);
        dest.writeString(this.placesToVisit_3);
        dest.writeValue(this.plansConfirmed);
        dest.writeString(this.notes);
        dest.writeString(this.attachmentNames);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.createdUser);
    }

    protected DecryptedVacations(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
        this.selectionType = in.readString();
        this.vac_description = in.readString();
        this.startDate = in.readString();
        this.endDate = in.readString();
        this.placesToVisit_1 = in.readString();
        this.placesToVisit_2 = in.readString();
        this.placesToVisit_3 = in.readString();
        this.plansConfirmed = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.notes = in.readString();
        this.attachmentNames = in.readString();
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.createdUser = in.readString();
    }

    public static final Creator<DecryptedVacations> CREATOR = new Creator<DecryptedVacations>() {
        @Override
        public DecryptedVacations createFromParcel(Parcel source) {
            return new DecryptedVacations(source);
        }

        @Override
        public DecryptedVacations[] newArray(int size) {
            return new DecryptedVacations[size];
        }
    };
}
