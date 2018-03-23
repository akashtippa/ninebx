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
public class DecryptedVitalNumbers implements Parcelable {

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
    private String classType = "VitalNumbers";
    @Required
    private String vital_description = "";
    @Required
    private String measurementDate = "";
    @Required
    private String height = "";
    @Required
    private String weight = "";
    @Required
    private String waist = "";
    @Required
    private String bodyFat = "";
    @Required
    private String bodyMassIndex = "";
    @Required
    private String bloodPressure = "";
    @Required
    private String heartRate = "";
    @Required
    private String totalCholesterol = "";
    @Required
    private String hdlCholesterol = "";
    @Required
    private String ldlCholesterol = "";
    @Required
    private String cholesterolRatio = "";
    @Required
    private String triglycerides = "";
    @Required
    private String bloodGlucose = "";
    @Required
    private String hemoglobin = "";
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String notes = "";
    @Required
    private String attachmentNames = "";
    @Required
    private String createdUser = "";

    public DecryptedVitalNumbers(String selectionType, String classType, String vital_description, String measurementDate, String height, String weight, String waist, String bodyFat, String bodyMassIndex, String bloodPressure, String heartRate, String totalCholesterol, String hdlCholesterol, String ldlCholesterol, String cholesterolRatio, String triglycerides, String bloodGlucose, String hemoglobin, String created, String modified, Boolean isPrivate, String notes, String attachmentNames, String createdUser) {
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

    public DecryptedVitalNumbers() {
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

    @Override
    public String toString() {
        return "DecryptedVitalNumbers{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", classType='" + classType + '\'' +
                ", vital_description='" + vital_description + '\'' +
                ", measurementDate='" + measurementDate + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", waist='" + waist + '\'' +
                ", bodyFat='" + bodyFat + '\'' +
                ", bodyMassIndex='" + bodyMassIndex + '\'' +
                ", bloodPressure='" + bloodPressure + '\'' +
                ", heartRate='" + heartRate + '\'' +
                ", totalCholesterol='" + totalCholesterol + '\'' +
                ", hdlCholesterol='" + hdlCholesterol + '\'' +
                ", ldlCholesterol='" + ldlCholesterol + '\'' +
                ", cholesterolRatio='" + cholesterolRatio + '\'' +
                ", triglycerides='" + triglycerides + '\'' +
                ", bloodGlucose='" + bloodGlucose + '\'' +
                ", hemoglobin='" + hemoglobin + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", notes='" + notes + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
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
        dest.writeString(this.classType);
        dest.writeString(this.vital_description);
        dest.writeString(this.measurementDate);
        dest.writeString(this.height);
        dest.writeString(this.weight);
        dest.writeString(this.waist);
        dest.writeString(this.bodyFat);
        dest.writeString(this.bodyMassIndex);
        dest.writeString(this.bloodPressure);
        dest.writeString(this.heartRate);
        dest.writeString(this.totalCholesterol);
        dest.writeString(this.hdlCholesterol);
        dest.writeString(this.ldlCholesterol);
        dest.writeString(this.cholesterolRatio);
        dest.writeString(this.triglycerides);
        dest.writeString(this.bloodGlucose);
        dest.writeString(this.hemoglobin);
        dest.writeString(this.created);
        dest.writeString(this.modified);
        dest.writeValue(this.isPrivate);
        dest.writeString(this.notes);
        dest.writeString(this.attachmentNames);
        dest.writeString(this.createdUser);
    }

    protected DecryptedVitalNumbers(Parcel in) {
        this.searchField = in.readString();
        this.id = in.readLong();
        this.backingImages = new ArrayList<RealmString>();
        in.readList(this.backingImages, RealmString.class.getClassLoader());
        this.photosId = in.createStringArrayList();
        this.selectionType = in.readString();
        this.classType = in.readString();
        this.vital_description = in.readString();
        this.measurementDate = in.readString();
        this.height = in.readString();
        this.weight = in.readString();
        this.waist = in.readString();
        this.bodyFat = in.readString();
        this.bodyMassIndex = in.readString();
        this.bloodPressure = in.readString();
        this.heartRate = in.readString();
        this.totalCholesterol = in.readString();
        this.hdlCholesterol = in.readString();
        this.ldlCholesterol = in.readString();
        this.cholesterolRatio = in.readString();
        this.triglycerides = in.readString();
        this.bloodGlucose = in.readString();
        this.hemoglobin = in.readString();
        this.created = in.readString();
        this.modified = in.readString();
        this.isPrivate = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.notes = in.readString();
        this.attachmentNames = in.readString();
        this.createdUser = in.readString();
    }

    public static final Creator<DecryptedVitalNumbers> CREATOR = new Creator<DecryptedVitalNumbers>() {
        @Override
        public DecryptedVitalNumbers createFromParcel(Parcel source) {
            return new DecryptedVitalNumbers(source);
        }

        @Override
        public DecryptedVitalNumbers[] newArray(int size) {
            return new DecryptedVitalNumbers[size];
        }
    };
}
