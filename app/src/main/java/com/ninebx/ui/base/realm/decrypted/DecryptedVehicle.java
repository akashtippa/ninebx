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

public class DecryptedVehicle implements Parcelable {
    public static final Creator<DecryptedVehicle> CREATOR = new Creator<DecryptedVehicle>() {
        @Override
        public DecryptedVehicle createFromParcel(Parcel in) {
            return new DecryptedVehicle(in);
        }

        @Override
        public DecryptedVehicle[] newArray(int size) {
            return new DecryptedVehicle[size];
        }
    };
    @PrimaryKey //@Required
    private int id = 0;
    @Required
    private RealmList<RealmString> backingImages = new RealmList<>();
    @Ignore
    @Required
    private List<String> photosId = new ArrayList<>();
    @Required
    private String selectionType = "";
    @Required
    private String vehicleName = "";
    @Required
    private String licenseNumber = "";
    @Required
    private String vinNumber = "";
    @Required
    private String make = "";
    @Required
    private String model = "";
    @Required
    private String modelYear = "";
    @Required
    private String color = "";
    @Required
    private String titleName = "";
    @Required
    private String estimatedMarketValue = "";
    @Required
    private String registrationExpirydate = "";
    @Required
    private String purchasedOrLeased = "";
    @Required
    private String purchaseDate = "";
    @Required
    private String financedThroughLoan = "";
    @Required
    private String created = "";
    @Required
    private String modified = "";
    @Required
    private Boolean isPrivate = false;
    @Required
    private String createdUser = "";
    @Required
    private String leaseStartDate = "";
    @Required
    private String leaseEndDate = "";
    @Required
    private String contacts = "";
    @Required
    private String maintenanceEvent = "";
    @Required
    private String serviceProviderName = "";
    @Required
    private String dateOfService = "";
    @Required
    private String vehicle = "";
    @Required
    private String notes = "";
    @Required
    private String attachmentNames = "";

    public DecryptedVehicle() {
    }

    public DecryptedVehicle(int id, RealmList<RealmString> backingImages, List<String> photosId, String selectionType, String vehicleName, String licenseNumber, String vinNumber, String make, String model, String modelYear, String color, String titleName, String estimatedMarketValue, String registrationExpirydate, String purchasedOrLeased, String purchaseDate, String financedThroughLoan, String created, String modified, Boolean isPrivate, String createdUser, String leaseStartDate, String leaseEndDate, String contacts, String maintenanceEvent, String serviceProviderName, String dateOfService, String vehicle, String notes, String attachmentNames) {
        this.id = id;
        this.backingImages = backingImages;
        this.photosId = photosId;
        this.selectionType = selectionType;
        this.vehicleName = vehicleName;
        this.licenseNumber = licenseNumber;
        this.vinNumber = vinNumber;
        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
        this.color = color;
        this.titleName = titleName;
        this.estimatedMarketValue = estimatedMarketValue;
        this.registrationExpirydate = registrationExpirydate;
        this.purchasedOrLeased = purchasedOrLeased;
        this.purchaseDate = purchaseDate;
        this.financedThroughLoan = financedThroughLoan;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.contacts = contacts;
        this.maintenanceEvent = maintenanceEvent;
        this.serviceProviderName = serviceProviderName;
        this.dateOfService = dateOfService;
        this.vehicle = vehicle;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
    }

    protected DecryptedVehicle(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        vehicleName = in.readString();
        licenseNumber = in.readString();
        vinNumber = in.readString();
        make = in.readString();
        model = in.readString();
        modelYear = in.readString();
        color = in.readString();
        titleName = in.readString();
        estimatedMarketValue = in.readString();
        registrationExpirydate = in.readString();
        purchasedOrLeased = in.readString();
        purchaseDate = in.readString();
        financedThroughLoan = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        createdUser = in.readString();
        leaseStartDate = in.readString();
        leaseEndDate = in.readString();
        contacts = in.readString();
        maintenanceEvent = in.readString();
        serviceProviderName = in.readString();
        dateOfService = in.readString();
        vehicle = in.readString();
        notes = in.readString();
        attachmentNames = in.readString();
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

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getEstimatedMarketValue() {
        return estimatedMarketValue;
    }

    public void setEstimatedMarketValue(String estimatedMarketValue) {
        this.estimatedMarketValue = estimatedMarketValue;
    }

    public String getRegistrationExpirydate() {
        return registrationExpirydate;
    }

    public void setRegistrationExpirydate(String registrationExpirydate) {
        this.registrationExpirydate = registrationExpirydate;
    }

    public String getPurchasedOrLeased() {
        return purchasedOrLeased;
    }

    public void setPurchasedOrLeased(String purchasedOrLeased) {
        this.purchasedOrLeased = purchasedOrLeased;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getFinancedThroughLoan() {
        return financedThroughLoan;
    }

    public void setFinancedThroughLoan(String financedThroughLoan) {
        this.financedThroughLoan = financedThroughLoan;
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

    public String getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(String leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public String getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(String leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getMaintenanceEvent() {
        return maintenanceEvent;
    }

    public void setMaintenanceEvent(String maintenanceEvent) {
        this.maintenanceEvent = maintenanceEvent;
    }

    public String getServiceProviderName() {
        return serviceProviderName;
    }

    public void setServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }

    public String getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(String dateOfService) {
        this.dateOfService = dateOfService;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(vehicleName);
        dest.writeString(licenseNumber);
        dest.writeString(vinNumber);
        dest.writeString(make);
        dest.writeString(model);
        dest.writeString(modelYear);
        dest.writeString(color);
        dest.writeString(titleName);
        dest.writeString(estimatedMarketValue);
        dest.writeString(registrationExpirydate);
        dest.writeString(purchasedOrLeased);
        dest.writeString(purchaseDate);
        dest.writeString(financedThroughLoan);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(createdUser);
        dest.writeString(leaseStartDate);
        dest.writeString(leaseEndDate);
        dest.writeString(contacts);
        dest.writeString(maintenanceEvent);
        dest.writeString(serviceProviderName);
        dest.writeString(dateOfService);
        dest.writeString(vehicle);
        dest.writeString(notes);
        dest.writeString(attachmentNames);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "DecryptedVehicle{" +
                "id=" + id +
                ", backingImages=" + backingImages +
                ", photosId=" + photosId +
                ", selectionType='" + selectionType + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", vinNumber='" + vinNumber + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", modelYear='" + modelYear + '\'' +
                ", color='" + color + '\'' +
                ", titleName='" + titleName + '\'' +
                ", estimatedMarketValue='" + estimatedMarketValue + '\'' +
                ", registrationExpirydate='" + registrationExpirydate + '\'' +
                ", purchasedOrLeased='" + purchasedOrLeased + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", financedThroughLoan='" + financedThroughLoan + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", isPrivate=" + isPrivate +
                ", createdUser='" + createdUser + '\'' +
                ", leaseStartDate='" + leaseStartDate + '\'' +
                ", leaseEndDate='" + leaseEndDate + '\'' +
                ", contacts='" + contacts + '\'' +
                ", maintenanceEvent='" + maintenanceEvent + '\'' +
                ", serviceProviderName='" + serviceProviderName + '\'' +
                ", dateOfService='" + dateOfService + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", notes='" + notes + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                '}';
    }
}
