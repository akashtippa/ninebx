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
 * Created by smrit on 11-02-2018.
 */

public class DecryptedTax implements Parcelable {

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
    private String returnName = "";
    @Required
    private String taxYear = "";
    @Required
    private String taxPayer = "";
    @Required
    private String contacts = "";
    @Required
    private String imageName = "";
    @Required
    private String attachmentNames = "";
    @Required
    private String notes = "";
    @Required
    private String title = "";
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String createdUser = "";

    public DecryptedTax() {
    }

    public DecryptedTax(long id, ArrayList<RealmString> backingImages, List<String> photosId, String selectionType, String returnName, String taxYear, String taxPayer, String contacts, String imageName, String attachmentNames, String notes, String title, String created, String modified, Boolean isPrivate, String createdUser) {
        this.id = id;
        this.backingImages = backingImages;
        this.photosId = photosId;
        this.selectionType = selectionType;
        this.returnName = returnName;
        this.taxYear = taxYear;
        this.taxPayer = taxPayer;
        this.contacts = contacts;
        this.imageName = imageName;
        this.attachmentNames = attachmentNames;
        this.notes = notes;
        this.title = title;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
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

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(String taxYear) {
        this.taxYear = taxYear;
    }

    public String getTaxPayer() {
        return taxPayer;
    }

    public void setTaxPayer(String taxPayer) {
        this.taxPayer = taxPayer;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "DecryptedTax{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", returnName='" + returnName + '\'' +
                ", taxYear='" + taxYear + '\'' +
                ", taxPayer='" + taxPayer + '\'' +
                ", contacts='" + contacts + '\'' +
                ", imageName='" + imageName + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", notes='" + notes + '\'' +
                ", title='" + title + '\'' +
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
        dest.writeString(this.returnName);
        dest.writeString(this.taxYear);
        dest.writeString(this.taxPayer);
        dest.writeString(this.contacts);
        dest.writeString(this.imageName);
        dest.writeString(this.attachmentNames);
        dest.writeString(this.notes);
        dest.writeString(this.title);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.createdUser);
    }

    protected DecryptedTax(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
        this.selectionType = in.readString();
        this.returnName = in.readString();
        this.taxYear = in.readString();
        this.taxPayer = in.readString();
        this.contacts = in.readString();
        this.imageName = in.readString();
        this.attachmentNames = in.readString();
        this.notes = in.readString();
        this.title = in.readString();
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.createdUser = in.readString();
    }

    public static final Creator<DecryptedTax> CREATOR = new Creator<DecryptedTax>() {
        @Override
        public DecryptedTax createFromParcel(Parcel source) {
            return new DecryptedTax(source);
        }

        @Override
        public DecryptedTax[] newArray(int size) {
            return new DecryptedTax[size];
        }
    };
}
