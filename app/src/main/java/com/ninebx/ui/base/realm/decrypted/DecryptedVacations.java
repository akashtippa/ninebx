package com.ninebx.ui.base.realm.decrypted;



import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.RealmString;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 29/01/18.
 */
public class DecryptedVacations implements Parcelable {

    @PrimaryKey //@Required
    private int id = 0;

    @Required
    private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    @Required private List<String> photosId = new ArrayList<>();

    protected DecryptedVacations(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        vac_description = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        placesToVisit_1 = in.readString();
        placesToVisit_2 = in.readString();
        placesToVisit_3 = in.readString();
        byte tmpPlansConfirmed = in.readByte();
        plansConfirmed = tmpPlansConfirmed == 0 ? null : tmpPlansConfirmed == 1;
        notes = in.readString();
        attachmentNames = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        createdUser = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(vac_description);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(placesToVisit_1);
        dest.writeString(placesToVisit_2);
        dest.writeString(placesToVisit_3);
        dest.writeByte((byte) (plansConfirmed == null ? 0 : plansConfirmed ? 1 : 2));
        dest.writeString(notes);
        dest.writeString(attachmentNames);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(createdUser);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DecryptedVacations> CREATOR = new Creator<DecryptedVacations>() {
        @Override
        public DecryptedVacations createFromParcel(Parcel in) {
            return new DecryptedVacations(in);
        }

        @Override
        public DecryptedVacations[] newArray(int size) {
            return new DecryptedVacations[size];
        }
    };

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
        for( RealmString realmString : backingImages ) {
            photosId.add( realmString.getStringValue() );
        }
        return photosId;
    }

    public void setPhotosId(List<String> photosId) {
        this.photosId = photosId;
        backingImages.clear();
        for( String string : photosId ) {
            backingImages.add( new RealmString(string) );
        }
    }

    @Required private String selectionType = "";

    @Required private String vac_description = "";
    @Required private String startDate = "";
    @Required private String endDate = "";
    @Required private String placesToVisit_1 = "";
    @Required private String placesToVisit_2 = "";
    @Required private String placesToVisit_3 = "";

    @Required private Boolean plansConfirmed = false;

    @Required private String notes = "";
    @Required private String attachmentNames = "";

    @Required private String created = "";
    @Required private String modified = "";
    @Required private Boolean isPrivate = false;
    @Required private String createdUser = "";

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

    public DecryptedVacations() {
    }
}
