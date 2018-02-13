package com.ninebx.ui.base.realm;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.NineBxApplication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 11/01/18.
 */
@RealmClass
public class Users extends RealmObject implements Parcelable {

    @Required private String fullName             = "";
    @Required
    private String firstName            = "";
    @Required private String lastName             = "";
    @Required private String emailAddress         = "";
    @Required private String relationship         = "";
    @Required private String dateOfBirth          = "";
    @Required private String anniversary          = "";
    @Required private String gender               = "";
    @Required private String mobileNumber         = "";

    @Required private String street_1             = "";
    @Required private String street_2             = "";
    @Required private String city                 = "";
    @Required private String state                = "";
    @Required private String zipCode              = "";
    @Required private String country              = "";
    @Required private String userId               = "";

    @PrimaryKey //@Required
    private int id                  = 0;
    @Required private Boolean isCompleteProfile   = false;
    @Required private String profilePhoto         = "";

    @Required private RealmList<Member> members = new RealmList<Member>();
    @Ignore private RealmList<Member> decryptedMembers = new RealmList<Member>();


    public Users(String fullName, String emailAddress, String relationship, String dateOfBirth, String anniversary, String gender, String mobileNumber, String street_1, String street_2, String city, String state, String zipCode, String country, int id, RealmList<Member> members) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.relationship = relationship;
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
        this.id = id;
        this.members = members;
    }

    public Users() {
    }

    public Users(String fullName, String emailAddress, String relationship, String dateOfBirth, String anniversary, String gender, String mobileNumber, String street_1, String street_2, String city, String state, String zipCode, String country, String userId, int id, RealmList<Member> members) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.relationship = relationship;
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
        this.id = id;
        this.members = members;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Member> getMembers() {
        return members;
    }

    public void setMembers(RealmList<Member> members) {
        this.members = members;
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

    public RealmList<Member> getDecryptedMembers() {
        return decryptedMembers;
    }

    public void setDecryptedMembers(RealmList<Member> decryptedMembers) {
        this.decryptedMembers = decryptedMembers;
    }

    @NotNull
    public static Users createUser(@NotNull String email, @NotNull String firstName, @NotNull String lastName) {

        Users users = new Users();
        users.firstName = firstName;
        users.lastName = lastName;
        users.fullName = firstName + " " + lastName;
        users.emailAddress = email;
        return users;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fullName);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.emailAddress);
        dest.writeString(this.relationship);
        dest.writeString(this.dateOfBirth);
        dest.writeString(this.anniversary);
        dest.writeString(this.gender);
        dest.writeString(this.mobileNumber);
        dest.writeString(this.street_1);
        dest.writeString(this.street_2);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.zipCode);
        dest.writeString(this.country);
        dest.writeString(this.userId);
        dest.writeValue(this.id);
        dest.writeValue(this.isCompleteProfile);
        dest.writeString(this.profilePhoto);
        dest.writeTypedList(this.members);
        dest.writeTypedList(this.decryptedMembers);
    }

    protected Users(Parcel in) {
        this.fullName = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.emailAddress = in.readString();
        this.relationship = in.readString();
        this.dateOfBirth = in.readString();
        this.anniversary = in.readString();
        this.gender = in.readString();
        this.mobileNumber = in.readString();
        this.street_1 = in.readString();
        this.street_2 = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.zipCode = in.readString();
        this.country = in.readString();
        this.userId = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isCompleteProfile = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.profilePhoto = in.readString();
        this.members = new RealmList<>();
        this.members.addAll(in.createTypedArrayList(Member.CREATOR));
        this.decryptedMembers = new RealmList<>();
        this.decryptedMembers.addAll(in.createTypedArrayList(Member.CREATOR));
    }

    public static final Parcelable.Creator<Users> CREATOR = new Parcelable.Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel source) {
            return new Users(source);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    @Override
    public String toString() {
        return "Users{" +
                "fullName='" + fullName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", relationship='" + relationship + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", anniversary='" + anniversary + '\'' +
                ", gender='" + gender + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", street_1='" + street_1 + '\'' +
                ", street_2='" + street_2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", userId='" + userId + '\'' +
                ", id=" + id +
                ", isCompleteProfile=" + isCompleteProfile +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", members=" + members +
                '}';
    }


    public static ArrayList<Users> createParcelableList(@NotNull RealmResults<Users> currentUsers) {
        ArrayList users = new ArrayList();
        users.addAll(currentUsers);
        return users;
    }

    @NotNull
    public static Users createUserObject(@NotNull Users users, ArrayList<Member> members) {

        Users newUser = new Users();
        newUser.setAnniversary(users.anniversary);
        newUser.setCity(users.city);
        newUser.setCompleteProfile(users.isCompleteProfile);
        newUser.setCountry(users.country);
        newUser.setUserId(users.userId);
        newUser.setId(users.id);
        newUser.setDateOfBirth(users.dateOfBirth);
        newUser.setZipCode(users.zipCode);
        newUser.setEmailAddress(users.emailAddress);
        newUser.setFirstName(users.firstName);
        newUser.setFullName(users.fullName);
        newUser.setLastName(users.lastName);
        newUser.setGender(users.gender);
        newUser.setStreet_1(users.street_1);
        newUser.setStreet_2(users.street_2);
        newUser.setState(users.state);
        newUser.setMobileNumber(users.mobileNumber);
        newUser.setProfilePhoto(users.profilePhoto);
        newUser.members.addAll(members);
        newUser.setRelationship(users.relationship);
        return newUser;

    }
}
