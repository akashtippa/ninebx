package com.ninebx.ui.base.realm.home.wellness;



import com.ninebx.ui.base.realm.RealmString;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Alok on 29/01/18.
 */
@RealmClass
public class VitalNumbers extends RealmObject {

    @PrimaryKey
    Integer id = 0;

    private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    private List<String> photosId = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    private String selectionType = "";
    private String classType = "VitalNumbers";

    private String vital_description = "";
    private String measurementDate = "";

    private String height = "";
    private String weight = "";
    private String waist = "";
    private String bodyFat = "";
    private String bodyMassIndex = "";
    private String bloodPressure = "";
    private String heartRate = "";
    private String totalCholesterol = "";
    private String hdlCholesterol = "";
    private String ldlCholesterol = "";
    private String cholesterolRatio = "";
    private String triglycerides = "";
    private String bloodGlucose  = "";
    private String hemoglobin = "";

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;

    private String notes = "";
    private String attachmentNames = "";

    private String createdUser = "";

    public VitalNumbers(String selectionType, String classType, String vital_description, String measurementDate, String height, String weight, String waist, String bodyFat, String bodyMassIndex, String bloodPressure, String heartRate, String totalCholesterol, String hdlCholesterol, String ldlCholesterol, String cholesterolRatio, String triglycerides, String bloodGlucose, String hemoglobin, String created, String modified, Boolean isPrivate, String notes, String attachmentNames, String createdUser) {
        this.selectionType = selectionType;
        this.classType = classType;
        this.vital_description = vital_description;
        this.measurementDate = measurementDate;
        this.height = height;
        this.weight = weight;
        this.waist = waist;
        this.bodyFat = bodyFat;
        this.bodyMassIndex = bodyMassIndex;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.totalCholesterol = totalCholesterol;
        this.hdlCholesterol = hdlCholesterol;
        this.ldlCholesterol = ldlCholesterol;
        this.cholesterolRatio = cholesterolRatio;
        this.triglycerides = triglycerides;
        this.bloodGlucose = bloodGlucose;
        this.hemoglobin = hemoglobin;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
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

    public String getVital_description() {
        return vital_description;
    }

    public void setVital_description(String vital_description) {
        this.vital_description = vital_description;
    }

    public String getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(String measurementDate) {
        this.measurementDate = measurementDate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(String bodyFat) {
        this.bodyFat = bodyFat;
    }

    public String getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(String bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getTotalCholesterol() {
        return totalCholesterol;
    }

    public void setTotalCholesterol(String totalCholesterol) {
        this.totalCholesterol = totalCholesterol;
    }

    public String getHdlCholesterol() {
        return hdlCholesterol;
    }

    public void setHdlCholesterol(String hdlCholesterol) {
        this.hdlCholesterol = hdlCholesterol;
    }

    public String getLdlCholesterol() {
        return ldlCholesterol;
    }

    public void setLdlCholesterol(String ldlCholesterol) {
        this.ldlCholesterol = ldlCholesterol;
    }

    public String getCholesterolRatio() {
        return cholesterolRatio;
    }

    public void setCholesterolRatio(String cholesterolRatio) {
        this.cholesterolRatio = cholesterolRatio;
    }

    public String getTriglycerides() {
        return triglycerides;
    }

    public void setTriglycerides(String triglycerides) {
        this.triglycerides = triglycerides;
    }

    public String getBloodGlucose() {
        return bloodGlucose;
    }

    public void setBloodGlucose(String bloodGlucose) {
        this.bloodGlucose = bloodGlucose;
    }

    public String getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(String hemoglobin) {
        this.hemoglobin = hemoglobin;
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

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public VitalNumbers() {
    }
}
