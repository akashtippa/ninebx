package com.ninebx.ui.base.realm.home.homeBanking;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 24/01/18.
 */

public class Vehicle extends RealmObject {

    @PrimaryKey
    Integer id = 0;

    private RealmList<String> backingImages = new RealmList<>();

    @Ignore
    private List<String> photosId = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealmList<String> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(RealmList<String> backingImages) {
        this.backingImages = backingImages;
    }

    public List<String> getPhotosId() {
        photosId = new ArrayList<>();
        photosId.addAll( backingImages.subList(0, backingImages.size() - 1));
        return photosId;
    }

    public void setPhotosId(List<String> photosId) {
        this.photosId = photosId;
        backingImages.clear();
        backingImages.addAll(photosId);
    }

    private String selectionType = "";

    private String vehicleName = "";
    private String licenseNumber = "";

    private String vinNumber = "";
    private String make = "";
    private String model = "";
    private String modelYear = "";
    private String color = "";
    private String titleName = "";
    private String estimatedMarketValue = "";

    private String registrationExpirydate = "";
    private String purchasedOrLeased = "";
    private String purchaseDate = "";
    private String financedThroughLoan = "";

    private String created = "";
    private String modified = "";
    private Boolean isPrivate = false;
    private String createdUser = "";

    private String leaseStartDate = "";
    private String leaseEndDate = "";
    private String contacts = "";

    private String maintenanceEvent = "";
    private String serviceProviderName = "";
    private String dateOfService = "";
    private String vehicle = "";

    private String notes = "";
    private String attachmentNames = "";

    public Vehicle(String selectionType, String vehicleName, String licenseNumber, String vinNumber, String make, String model, String modelYear, String color, String titleName, String estimatedMarketValue, String registrationExpirydate, String purchasedOrLeased, String purchaseDate, String financedThroughLoan, String created, String modified, Boolean isPrivate, String createdUser, String leaseStartDate, String leaseEndDate, String contacts, String maintenanceEvent, String serviceProviderName, String dateOfService, String vehicle, String notes, String attachmentNames) {
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
}
