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
public class DecryptedVitalNumbers implements Parcelable {

    @PrimaryKey //@Required
    int id = 0;

    @Required
    private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    @Required private List<String> photosId = new ArrayList<>();

    protected DecryptedVitalNumbers(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        classType = in.readString();
        vital_description = in.readString();
        measurementDate = in.readString();
        height = in.readString();
        weight = in.readString();
        waist = in.readString();
        bodyFat = in.readString();
        bodyMassIndex = in.readString();
        bloodPressure = in.readString();
        heartRate = in.readString();
        totalCholesterol = in.readString();
        hdlCholesterol = in.readString();
        ldlCholesterol = in.readString();
        cholesterolRatio = in.readString();
        triglycerides = in.readString();
        bloodGlucose = in.readString();
        hemoglobin = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        notes = in.readString();
        attachmentNames = in.readString();
        createdUser = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(classType);
        dest.writeString(vital_description);
        dest.writeString(measurementDate);
        dest.writeString(height);
        dest.writeString(weight);
        dest.writeString(waist);
        dest.writeString(bodyFat);
        dest.writeString(bodyMassIndex);
        dest.writeString(bloodPressure);
        dest.writeString(heartRate);
        dest.writeString(totalCholesterol);
        dest.writeString(hdlCholesterol);
        dest.writeString(ldlCholesterol);
        dest.writeString(cholesterolRatio);
        dest.writeString(triglycerides);
        dest.writeString(bloodGlucose);
        dest.writeString(hemoglobin);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(notes);
        dest.writeString(attachmentNames);
        dest.writeString(createdUser);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DecryptedVitalNumbers> CREATOR = new Creator<DecryptedVitalNumbers>() {
        @Override
        public DecryptedVitalNumbers createFromParcel(Parcel in) {
            return new DecryptedVitalNumbers(in);
        }

        @Override
        public DecryptedVitalNumbers[] newArray(int size) {
            return new DecryptedVitalNumbers[size];
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
    @Required private String classType = "VitalNumbers";

    @Required private String vital_description = "";
    @Required private String measurementDate = "";

    @Required private String height = "";
    @Required private String weight = "";
    @Required private String waist = "";
    @Required private String bodyFat = "";
    @Required private String bodyMassIndex = "";
    @Required private String bloodPressure = "";
    @Required private String heartRate = "";
    @Required private String totalCholesterol = "";
    @Required private String hdlCholesterol = "";
    @Required private String ldlCholesterol = "";
    @Required private String cholesterolRatio = "";
    @Required private String triglycerides = "";
    @Required private String bloodGlucose  = "";
    @Required private String hemoglobin = "";

    @Required private String created = "";
    @Required private String modified = "";
    @Required private Boolean isPrivate = false;

    @Required private String notes = "";
    @Required private String attachmentNames = "";

    @Required private String createdUser = "";

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

    public DecryptedVitalNumbers() {
    }
}
