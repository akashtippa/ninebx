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

    @PrimaryKey //@Required
            int id = 0;

    @Required
    private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    @Required private List<String> photosId = new ArrayList<>();

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DecryptedTax> CREATOR = new Creator<DecryptedTax>() {
        @Override
        public DecryptedTax createFromParcel(Parcel in) {
            return new DecryptedTax(in);
        }

        @Override
        public DecryptedTax[] newArray(int size) {
            return new DecryptedTax[size];
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

    @Required private String returnName = "";
    @Required private String taxYear = "";
    @Required private String taxPayer = "";
    @Required private String contacts = "";

    @Required private String imageName = "";
    @Required private String attachmentNames = "";

    @Required private String notes = "";
    @Required private String title = "";

    @Required private String created = "";
    @Required private String modified = "";
    @Required private Boolean isPrivate = false;
    @Required private String createdUser = "";

    public DecryptedTax() {
    }

    public DecryptedTax(int id, RealmList<RealmString> backingImages, List<String> photosId, String selectionType, String returnName, String taxYear, String taxPayer, String contacts, String imageName, String attachmentNames, String notes, String title, String created, String modified, Boolean isPrivate, String createdUser) {
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

    protected DecryptedTax(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        returnName = in.readString();
        taxYear = in.readString();
        taxPayer = in.readString();
        contacts = in.readString();
        imageName = in.readString();
        attachmentNames = in.readString();
        notes = in.readString();
        title = in.readString();
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
        dest.writeString(returnName);
        dest.writeString(taxYear);
        dest.writeString(taxPayer);
        dest.writeString(contacts);
        dest.writeString(imageName);
        dest.writeString(attachmentNames);
        dest.writeString(notes);
        dest.writeString(title);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(createdUser);
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
}
