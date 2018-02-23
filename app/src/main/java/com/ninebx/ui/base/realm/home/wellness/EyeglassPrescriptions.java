package com.ninebx.ui.base.realm.home.wellness;



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
@RealmClass
public class EyeglassPrescriptions extends RealmObject {

    @PrimaryKey //@Required
            long id = 0;

    @Required
    private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    @Required private List<String> photosId = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    @Required private String classType = "EyeglassPrescriptions";

    @Required private String physicianName = "";
    @Required private String datePrescribed = "";

    @Required private String odSphereValue = "";
    @Required private String osSphereValue = "";

    @Required private String odCylinderValue = "";
    @Required private String osCylinderValue = "";

    @Required private String odAxisValue = "";
    @Required private String osAxisValue = "";

    @Required private String odPrismValue = "";
    @Required private String osPrismValue = "";

    @Required private String odAddValue = "";
    @Required private String osAddValue = "";

    @Required private String odBaseValue = "";
    @Required private String osBaseValue = "";

    @Required private String notes = "";
    @Required private String attachmentNames = "";

    @Required private String created = "";
    @Required private String modified = "";
    @Required private Boolean isPrivate = false;

    @Required private String createdUser = "";

    public EyeglassPrescriptions(long id, String selectionType, String classType, String physicianName, String datePrescribed, String odSphereValue, String osSphereValue, String odCylinderValue, String osCylinderValue, String odAxisValue, String osAxisValue, String odPrismValue, String osPrismValue, String odAddValue, String osAddValue, String odBaseValue, String osBaseValue, String notes, String attachmentNames, String created, String modified, Boolean isPrivate, String createdUser) {
        this.selectionType = selectionType;
        this.id = id;
        this.classType = classType;
        this.physicianName = physicianName;
        this.datePrescribed = datePrescribed;
        this.odSphereValue = odSphereValue;
        this.osSphereValue = osSphereValue;
        this.odCylinderValue = odCylinderValue;
        this.osCylinderValue = osCylinderValue;
        this.odAxisValue = odAxisValue;
        this.osAxisValue = osAxisValue;
        this.odPrismValue = odPrismValue;
        this.osPrismValue = osPrismValue;
        this.odAddValue = odAddValue;
        this.osAddValue = osAddValue;
        this.odBaseValue = odBaseValue;
        this.osBaseValue = osBaseValue;
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

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }

    public String getDatePrescribed() {
        return datePrescribed;
    }

    public void setDatePrescribed(String datePrescribed) {
        this.datePrescribed = datePrescribed;
    }

    public String getOdSphereValue() {
        return odSphereValue;
    }

    public void setOdSphereValue(String odSphereValue) {
        this.odSphereValue = odSphereValue;
    }

    public String getOsSphereValue() {
        return osSphereValue;
    }

    public void setOsSphereValue(String osSphereValue) {
        this.osSphereValue = osSphereValue;
    }

    public String getOdCylinderValue() {
        return odCylinderValue;
    }

    public void setOdCylinderValue(String odCylinderValue) {
        this.odCylinderValue = odCylinderValue;
    }

    public String getOsCylinderValue() {
        return osCylinderValue;
    }

    public void setOsCylinderValue(String osCylinderValue) {
        this.osCylinderValue = osCylinderValue;
    }

    public String getOdAxisValue() {
        return odAxisValue;
    }

    public void setOdAxisValue(String odAxisValue) {
        this.odAxisValue = odAxisValue;
    }

    public String getOsAxisValue() {
        return osAxisValue;
    }

    public void setOsAxisValue(String osAxisValue) {
        this.osAxisValue = osAxisValue;
    }

    public String getOdPrismValue() {
        return odPrismValue;
    }

    public void setOdPrismValue(String odPrismValue) {
        this.odPrismValue = odPrismValue;
    }

    public String getOsPrismValue() {
        return osPrismValue;
    }

    public void setOsPrismValue(String osPrismValue) {
        this.osPrismValue = osPrismValue;
    }

    public String getOdAddValue() {
        return odAddValue;
    }

    public void setOdAddValue(String odAddValue) {
        this.odAddValue = odAddValue;
    }

    public String getOsAddValue() {
        return osAddValue;
    }

    public void setOsAddValue(String osAddValue) {
        this.osAddValue = osAddValue;
    }

    public String getOdBaseValue() {
        return odBaseValue;
    }

    public void setOdBaseValue(String odBaseValue) {
        this.odBaseValue = odBaseValue;
    }

    public String getOsBaseValue() {
        return osBaseValue;
    }

    public void setOsBaseValue(String osBaseValue) {
        this.osBaseValue = osBaseValue;
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

    public EyeglassPrescriptions() {
    }
}
