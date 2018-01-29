package com.ninebx.ui.base.realm;

import io.realm.RealmObject;

/**
 * Created by Alok on 11/01/18.
 */

public class Member extends RealmObject {

    private String  firstName            = "";
    private String  lastName             = "";
    private String  relationship         = "";
    private String  role                 = "";
    private String  email                = "";

    private String dateOfBirth         = "";
    private String anniversary         = "";
    private String gender              = "";
    private String mobileNumber        = "";
    private String street_1            = "";
    private String street_2            = "";
    private String city                = "";
    private String state               = "";
    private String zipCode             = "";
    private String country             = "";

    private String userId              = "";
    ///For permissions
    private Boolean homeAdd                = true;
    private Boolean homeEdit               = false;
    private Boolean homeView               = true;

    private Boolean travelAdd              = true;
    private Boolean travelEdit             = false;
    private Boolean travelView             = true;

    private Boolean contactsAdd              = true;
    private Boolean contactsEdit             = false;
    private Boolean contactsView             = true;

    private Boolean educationlAdd              = true;
    private Boolean educationlEdit             = false;
    private Boolean educationlView             = true;

    private Boolean personalAdd              = true;
    private Boolean personalEdit             = false;
    private Boolean personalView             = true;

    private Boolean interestsAdd              = true;
    private Boolean interestsEdit             = false;
    private Boolean interestsView             = true;

    private Boolean wellnessAdd              = true;
    private Boolean wellnessEdit             = false;
    private Boolean wellnessView             = true;

    private Boolean memoriesAdd              = true;
    private Boolean memoriesEdit             = false;
    private Boolean memoriesView             = true;

    private Boolean shoppingAdd              = true;
    private Boolean shoppingEdit             = false;
    private Boolean shoppingView             = true;

    private Boolean addingRemovingMember    = false;
    private Boolean changingMasterPassword  = false;

    private Boolean isCompleteProfile     = false;
    private String profilePhoto        = "";

    public Member(String firstName, String lastName, String relationship, String role, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
        this.role = role;
        this.email = email;
    }

    public Member() {
    }

    public Member(String firstName, String lastName, String relationship, String role, String email, String dateOfBirth, String anniversary, String gender, String mobileNumber, String street_1, String street_2, String city, String state, String zipCode, String country, String userId, Boolean homeAdd, Boolean homeEdit, Boolean homeView, Boolean travelAdd, Boolean travelEdit, Boolean travelView, Boolean contactsAdd, Boolean contactsEdit, Boolean contactsView, Boolean educationlAdd, Boolean educationlEdit, Boolean educationlView, Boolean personalAdd, Boolean personalEdit, Boolean personalView, Boolean interestsAdd, Boolean interestsEdit, Boolean interestsView, Boolean wellnessAdd, Boolean wellnessEdit, Boolean wellnessView, Boolean memoriesAdd, Boolean memoriesEdit, Boolean memoriesView, Boolean shoppingAdd, Boolean shoppingEdit, Boolean shoppingView, Boolean addingRemovingMember, Boolean changingMasterPassword, Boolean isCompleteProfile, String profilePhoto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
        this.role = role;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.anniversary = anniversary;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.street_1 = street_1;
        this.street_2 = street_2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.userId = userId;
        this.homeAdd = homeAdd;
        this.homeEdit = homeEdit;
        this.homeView = homeView;
        this.travelAdd = travelAdd;
        this.travelEdit = travelEdit;
        this.travelView = travelView;
        this.contactsAdd = contactsAdd;
        this.contactsEdit = contactsEdit;
        this.contactsView = contactsView;
        this.educationlAdd = educationlAdd;
        this.educationlEdit = educationlEdit;
        this.educationlView = educationlView;
        this.personalAdd = personalAdd;
        this.personalEdit = personalEdit;
        this.personalView = personalView;
        this.interestsAdd = interestsAdd;
        this.interestsEdit = interestsEdit;
        this.interestsView = interestsView;
        this.wellnessAdd = wellnessAdd;
        this.wellnessEdit = wellnessEdit;
        this.wellnessView = wellnessView;
        this.memoriesAdd = memoriesAdd;
        this.memoriesEdit = memoriesEdit;
        this.memoriesView = memoriesView;
        this.shoppingAdd = shoppingAdd;
        this.shoppingEdit = shoppingEdit;
        this.shoppingView = shoppingView;
        this.addingRemovingMember = addingRemovingMember;
        this.changingMasterPassword = changingMasterPassword;
        this.isCompleteProfile = isCompleteProfile;
        this.profilePhoto = profilePhoto;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(String anniversary) {
        this.anniversary = anniversary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getStreet_1() {
        return street_1;
    }

    public void setStreet_1(String street_1) {
        this.street_1 = street_1;
    }

    public String getStreet_2() {
        return street_2;
    }

    public void setStreet_2(String street_2) {
        this.street_2 = street_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(Boolean homeAdd) {
        this.homeAdd = homeAdd;
    }

    public Boolean getHomeEdit() {
        return homeEdit;
    }

    public void setHomeEdit(Boolean homeEdit) {
        this.homeEdit = homeEdit;
    }

    public Boolean getHomeView() {
        return homeView;
    }

    public void setHomeView(Boolean homeView) {
        this.homeView = homeView;
    }

    public Boolean getTravelAdd() {
        return travelAdd;
    }

    public void setTravelAdd(Boolean travelAdd) {
        this.travelAdd = travelAdd;
    }

    public Boolean getTravelEdit() {
        return travelEdit;
    }

    public void setTravelEdit(Boolean travelEdit) {
        this.travelEdit = travelEdit;
    }

    public Boolean getTravelView() {
        return travelView;
    }

    public void setTravelView(Boolean travelView) {
        this.travelView = travelView;
    }

    public Boolean getContactsAdd() {
        return contactsAdd;
    }

    public void setContactsAdd(Boolean contactsAdd) {
        this.contactsAdd = contactsAdd;
    }

    public Boolean getContactsEdit() {
        return contactsEdit;
    }

    public void setContactsEdit(Boolean contactsEdit) {
        this.contactsEdit = contactsEdit;
    }

    public Boolean getContactsView() {
        return contactsView;
    }

    public void setContactsView(Boolean contactsView) {
        this.contactsView = contactsView;
    }

    public Boolean getEducationlAdd() {
        return educationlAdd;
    }

    public void setEducationlAdd(Boolean educationlAdd) {
        this.educationlAdd = educationlAdd;
    }

    public Boolean getEducationlEdit() {
        return educationlEdit;
    }

    public void setEducationlEdit(Boolean educationlEdit) {
        this.educationlEdit = educationlEdit;
    }

    public Boolean getEducationlView() {
        return educationlView;
    }

    public void setEducationlView(Boolean educationlView) {
        this.educationlView = educationlView;
    }

    public Boolean getPersonalAdd() {
        return personalAdd;
    }

    public void setPersonalAdd(Boolean personalAdd) {
        this.personalAdd = personalAdd;
    }

    public Boolean getPersonalEdit() {
        return personalEdit;
    }

    public void setPersonalEdit(Boolean personalEdit) {
        this.personalEdit = personalEdit;
    }

    public Boolean getPersonalView() {
        return personalView;
    }

    public void setPersonalView(Boolean personalView) {
        this.personalView = personalView;
    }

    public Boolean getInterestsAdd() {
        return interestsAdd;
    }

    public void setInterestsAdd(Boolean interestsAdd) {
        this.interestsAdd = interestsAdd;
    }

    public Boolean getInterestsEdit() {
        return interestsEdit;
    }

    public void setInterestsEdit(Boolean interestsEdit) {
        this.interestsEdit = interestsEdit;
    }

    public Boolean getInterestsView() {
        return interestsView;
    }

    public void setInterestsView(Boolean interestsView) {
        this.interestsView = interestsView;
    }

    public Boolean getWellnessAdd() {
        return wellnessAdd;
    }

    public void setWellnessAdd(Boolean wellnessAdd) {
        this.wellnessAdd = wellnessAdd;
    }

    public Boolean getWellnessEdit() {
        return wellnessEdit;
    }

    public void setWellnessEdit(Boolean wellnessEdit) {
        this.wellnessEdit = wellnessEdit;
    }

    public Boolean getWellnessView() {
        return wellnessView;
    }

    public void setWellnessView(Boolean wellnessView) {
        this.wellnessView = wellnessView;
    }

    public Boolean getMemoriesAdd() {
        return memoriesAdd;
    }

    public void setMemoriesAdd(Boolean memoriesAdd) {
        this.memoriesAdd = memoriesAdd;
    }

    public Boolean getMemoriesEdit() {
        return memoriesEdit;
    }

    public void setMemoriesEdit(Boolean memoriesEdit) {
        this.memoriesEdit = memoriesEdit;
    }

    public Boolean getMemoriesView() {
        return memoriesView;
    }

    public void setMemoriesView(Boolean memoriesView) {
        this.memoriesView = memoriesView;
    }

    public Boolean getShoppingAdd() {
        return shoppingAdd;
    }

    public void setShoppingAdd(Boolean shoppingAdd) {
        this.shoppingAdd = shoppingAdd;
    }

    public Boolean getShoppingEdit() {
        return shoppingEdit;
    }

    public void setShoppingEdit(Boolean shoppingEdit) {
        this.shoppingEdit = shoppingEdit;
    }

    public Boolean getShoppingView() {
        return shoppingView;
    }

    public void setShoppingView(Boolean shoppingView) {
        this.shoppingView = shoppingView;
    }

    public Boolean getAddingRemovingMember() {
        return addingRemovingMember;
    }

    public void setAddingRemovingMember(Boolean addingRemovingMember) {
        this.addingRemovingMember = addingRemovingMember;
    }

    public Boolean getChangingMasterPassword() {
        return changingMasterPassword;
    }

    public void setChangingMasterPassword(Boolean changingMasterPassword) {
        this.changingMasterPassword = changingMasterPassword;
    }

    public Boolean getCompleteProfile() {
        return isCompleteProfile;
    }

    public void setCompleteProfile(Boolean completeProfile) {
        isCompleteProfile = completeProfile;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
