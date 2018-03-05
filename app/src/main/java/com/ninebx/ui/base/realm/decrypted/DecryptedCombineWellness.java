package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alok on 29/01/18.
 */
public class DecryptedCombineWellness implements Parcelable {

    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private ArrayList<DecryptedCheckups> checkupsItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedEmergencyContacts> emergencyContactsItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedEyeglassPrescriptions> eyeglassPrescriptionsItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedHealthcareProviders> healthcareProvidersItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedIdentification> identificationItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedMedicalConditions> medicalConditionsItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedMedicalHistory> medicalHistoryItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedMedications> medicationsItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedVitalNumbers> vitalNumbersItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedWellness> wellnessItems = new ArrayList<>();
    @Required
    private ArrayList<DecryptedWellnessList> listItems = new ArrayList<>();

    public DecryptedCombineWellness(long id, ArrayList<DecryptedCheckups> checkupsItems, ArrayList<DecryptedEmergencyContacts> emergencyContactsItems, ArrayList<DecryptedEyeglassPrescriptions> eyeglassPrescriptionsItems, ArrayList<DecryptedHealthcareProviders> healthcareProvidersItems, ArrayList<DecryptedIdentification> identificationItems, ArrayList<DecryptedMedicalConditions> medicalConditionsItems, ArrayList<DecryptedMedicalHistory> medicalHistoryItems, ArrayList<DecryptedMedications> medicationsItems, ArrayList<DecryptedVitalNumbers> vitalNumbersItems, ArrayList<DecryptedWellness> wellnessItems, ArrayList<DecryptedWellnessList> listItems) {
        this.id = id;
        this.checkupsItems = checkupsItems;
        this.emergencyContactsItems = emergencyContactsItems;
        this.eyeglassPrescriptionsItems = eyeglassPrescriptionsItems;
        this.healthcareProvidersItems = healthcareProvidersItems;
        this.identificationItems = identificationItems;
        this.medicalConditionsItems = medicalConditionsItems;
        this.medicalHistoryItems = medicalHistoryItems;
        this.medicationsItems = medicationsItems;
        this.vitalNumbersItems = vitalNumbersItems;
        this.wellnessItems = wellnessItems;
        this.listItems = listItems;
    }

    protected DecryptedCombineWellness(Parcel in) {
        id = in.readInt();
    }

    public DecryptedCombineWellness() {

    }

    public static final Creator<DecryptedCombineWellness> CREATOR = new Creator<DecryptedCombineWellness>() {
        @Override
        public DecryptedCombineWellness createFromParcel(Parcel in) {
            return new DecryptedCombineWellness(in);
        }

        @Override
        public DecryptedCombineWellness[] newArray(int size) {
            return new DecryptedCombineWellness[size];
        }
    };

    public  long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public ArrayList<DecryptedCheckups> getCheckupsItems() {
        return checkupsItems;
    }

    public void setCheckupsItems(ArrayList<DecryptedCheckups> checkupsItems) {
        this.checkupsItems = checkupsItems;
    }

    public ArrayList<DecryptedEmergencyContacts> getEmergencyContactsItems() {
        return emergencyContactsItems;
    }

    public void setEmergencyContactsItems(ArrayList<DecryptedEmergencyContacts> emergencyContactsItems) {
        this.emergencyContactsItems = emergencyContactsItems;
    }

    public ArrayList<DecryptedEyeglassPrescriptions> getEyeglassPrescriptionsItems() {
        return eyeglassPrescriptionsItems;
    }

    public void setEyeglassPrescriptionsItems(ArrayList<DecryptedEyeglassPrescriptions> eyeglassPrescriptionsItems) {
        this.eyeglassPrescriptionsItems = eyeglassPrescriptionsItems;
    }

    public ArrayList<DecryptedHealthcareProviders> getHealthcareProvidersItems() {
        return healthcareProvidersItems;
    }

    public void setHealthcareProvidersItems(ArrayList<DecryptedHealthcareProviders> healthcareProvidersItems) {
        this.healthcareProvidersItems = healthcareProvidersItems;
    }

    public ArrayList<DecryptedIdentification> getIdentificationItems() {
        return identificationItems;
    }

    public void setIdentificationItems(ArrayList<DecryptedIdentification> identificationItems) {
        this.identificationItems = identificationItems;
    }

    public ArrayList<DecryptedMedicalConditions> getMedicalConditionsItems() {
        return medicalConditionsItems;
    }

    public void setMedicalConditionsItems(ArrayList<DecryptedMedicalConditions> medicalConditionsItems) {
        this.medicalConditionsItems = medicalConditionsItems;
    }
    @Ignore
    public String searchField = "";
    public ArrayList<DecryptedMedicalHistory> getMedicalHistoryItems() {
        return medicalHistoryItems;
    }

    public void setMedicalHistoryItems(ArrayList<DecryptedMedicalHistory> medicalHistoryItems) {
        this.medicalHistoryItems = medicalHistoryItems;
    }

    public ArrayList<DecryptedMedications> getMedicationsItems() {
        return medicationsItems;
    }

    public void setMedicationsItems(ArrayList<DecryptedMedications> medicationsItems) {
        this.medicationsItems = medicationsItems;
    }

    public ArrayList<DecryptedVitalNumbers> getVitalNumbersItems() {
        return vitalNumbersItems;
    }

    public void setVitalNumbersItems(ArrayList<DecryptedVitalNumbers> vitalNumbersItems) {
        this.vitalNumbersItems = vitalNumbersItems;
    }

    public ArrayList<DecryptedWellness> getWellnessItems() {
        return wellnessItems;
    }

    public void setWellnessItems(ArrayList<DecryptedWellness> wellnessItems) {
        this.wellnessItems = wellnessItems;
    }

    public ArrayList<DecryptedWellnessList> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<DecryptedWellnessList> listItems) {
        this.listItems = listItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
    }

    public int getLists(String selectionType, Integer detailsId ) {
        int count = 0;
        for (DecryptedWellnessList decryptedLicense : listItems) {
            count += ( decryptedLicense.getSelectionType().equals(selectionType) && decryptedLicense.getDetailsId() == detailsId )  ? 1 : 0;
        }
        return count;
    }

    public int getOtherAttachemnts(String selectionType) {
        int count = 0;
        for (DecryptedWellnessList decryptedLicense : listItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }

    public int getServices(String selectionType) {
        int count = 0;
        for (DecryptedWellness decryptedLicense : wellnessItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
        }
        return count;
    }
}
