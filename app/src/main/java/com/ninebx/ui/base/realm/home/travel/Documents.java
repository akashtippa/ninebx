package com.ninebx.ui.base.realm.home.travel;



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
public class Documents extends RealmObject {

    @Required @PrimaryKey
    private Integer id = 0;

    @Required
    private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    @Required private List<String> photosId = new ArrayList<>();

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

    @Required private String selectionType = "";

    @Required private String passportName = "";
    @Required private String visaName = "";

    @Required private String nameOnPassport = "";

    @Required private String nameOnVisa = "";
    @Required private String visaType = "";
    @Required private String visaNumber = "";

    @Required private String travelDocumentTitle = "";
    @Required private String nameOnTravelDocument = "";
    @Required private String travelDocumentType = "";
    @Required private String travelDocumentNumber = "";

    @Required private String issuingCountry = "";
    @Required private String passportNumber = "";
    @Required private String placeIssued = "";
    @Required private String dateIssued = "";
    @Required private String expirationDate = "";

    @Required private String notes = "";
    @Required private String attachmentNames = "";

    @Required private String created = "";
    @Required private String modified = "";
    @Required private Boolean isPrivate = false;
    @Required private String createdUser = "";

    public Documents(String selectionType, String passportName, String visaName, String nameOnPassport, String nameOnVisa, String visaType, String visaNumber, String travelDocumentTitle, String nameOnTravelDocument, String travelDocumentType, String travelDocumentNumber, String issuingCountry, String passportNumber, String placeIssued, String dateIssued, String expirationDate, String notes, String attachmentNames, String created, String modified, Boolean isPrivate, String createdUser) {
        this.selectionType = selectionType;
        this.passportName = passportName;
        this.visaName = visaName;
        this.nameOnPassport = nameOnPassport;
        this.nameOnVisa = nameOnVisa;
        this.visaType = visaType;
        this.visaNumber = visaNumber;
        this.travelDocumentTitle = travelDocumentTitle;
        this.nameOnTravelDocument = nameOnTravelDocument;
        this.travelDocumentType = travelDocumentType;
        this.travelDocumentNumber = travelDocumentNumber;
        this.issuingCountry = issuingCountry;
        this.passportNumber = passportNumber;
        this.placeIssued = placeIssued;
        this.dateIssued = dateIssued;
        this.expirationDate = expirationDate;
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

    public String getPassportName() {
        return passportName;
    }

    public void setPassportName(String passportName) {
        this.passportName = passportName;
    }

    public String getVisaName() {
        return visaName;
    }

    public void setVisaName(String visaName) {
        this.visaName = visaName;
    }

    public String getNameOnPassport() {
        return nameOnPassport;
    }

    public void setNameOnPassport(String nameOnPassport) {
        this.nameOnPassport = nameOnPassport;
    }

    public String getNameOnVisa() {
        return nameOnVisa;
    }

    public void setNameOnVisa(String nameOnVisa) {
        this.nameOnVisa = nameOnVisa;
    }

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public String getVisaNumber() {
        return visaNumber;
    }

    public void setVisaNumber(String visaNumber) {
        this.visaNumber = visaNumber;
    }

    public String getTravelDocumentTitle() {
        return travelDocumentTitle;
    }

    public void setTravelDocumentTitle(String travelDocumentTitle) {
        this.travelDocumentTitle = travelDocumentTitle;
    }

    public String getNameOnTravelDocument() {
        return nameOnTravelDocument;
    }

    public void setNameOnTravelDocument(String nameOnTravelDocument) {
        this.nameOnTravelDocument = nameOnTravelDocument;
    }

    public String getTravelDocumentType() {
        return travelDocumentType;
    }

    public void setTravelDocumentType(String travelDocumentType) {
        this.travelDocumentType = travelDocumentType;
    }

    public String getTravelDocumentNumber() {
        return travelDocumentNumber;
    }

    public void setTravelDocumentNumber(String travelDocumentNumber) {
        this.travelDocumentNumber = travelDocumentNumber;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPlaceIssued() {
        return placeIssued;
    }

    public void setPlaceIssued(String placeIssued) {
        this.placeIssued = placeIssued;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
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

    public Documents() {
    }
}
