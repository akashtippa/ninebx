package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alok on 29/01/18.
 */
public class DecryptedCombineWellness implements Parcelable {

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
    @PrimaryKey //@Required
    private long id = 0;
    @Required
    private RealmList<DecryptedCheckups> checkupsItems = new RealmList<>();
    @Required
    private RealmList<DecryptedEmergencyContacts> emergencyContactsItems = new RealmList<>();
    @Required
    private RealmList<DecryptedEyeglassPrescriptions> eyeglassPrescriptionsItems = new RealmList<>();
    @Required
    private RealmList<DecryptedHealthcareProviders> healthcareProvidersItems = new RealmList<>();
    @Required
    private RealmList<DecryptedIdentification> identificationItems = new RealmList<>();
    @Required
    private RealmList<DecryptedMedicalConditions> medicalConditionsItems = new RealmList<>();
    @Required
    private RealmList<DecryptedMedicalHistory> medicalHistoryItems = new RealmList<>();
    @Required
    private RealmList<DecryptedMedications> medicationsItems = new RealmList<>();
    @Required
    private RealmList<DecryptedVitalNumbers> vitalNumbersItems = new RealmList<>();
    @Required
    private RealmList<DecryptedWellness> wellnessItems = new RealmList<>();
    @Required
    private RealmList<DecryptedWellnessList> listItems = new RealmList<>();

    public DecryptedCombineWellness(long id, RealmList<DecryptedCheckups> checkupsItems, RealmList<DecryptedEmergencyContacts> emergencyContactsItems, RealmList<DecryptedEyeglassPrescriptions> eyeglassPrescriptionsItems, RealmList<DecryptedHealthcareProviders> healthcareProvidersItems, RealmList<DecryptedIdentification> identificationItems, RealmList<DecryptedMedicalConditions> medicalConditionsItems, RealmList<DecryptedMedicalHistory> medicalHistoryItems, RealmList<DecryptedMedications> medicationsItems, RealmList<DecryptedVitalNumbers> vitalNumbersItems, RealmList<DecryptedWellness> wellnessItems, RealmList<DecryptedWellnessList> listItems) {
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

    public  long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public RealmList<DecryptedCheckups> getCheckupsItems() {
        return checkupsItems;
    }

    public void setCheckupsItems(RealmList<DecryptedCheckups> checkupsItems) {
        this.checkupsItems = checkupsItems;
    }

    public RealmList<DecryptedEmergencyContacts> getEmergencyContactsItems() {
        return emergencyContactsItems;
    }

    public void setEmergencyContactsItems(RealmList<DecryptedEmergencyContacts> emergencyContactsItems) {
        this.emergencyContactsItems = emergencyContactsItems;
    }

    public RealmList<DecryptedEyeglassPrescriptions> getEyeglassPrescriptionsItems() {
        return eyeglassPrescriptionsItems;
    }

    public void setEyeglassPrescriptionsItems(RealmList<DecryptedEyeglassPrescriptions> eyeglassPrescriptionsItems) {
        this.eyeglassPrescriptionsItems = eyeglassPrescriptionsItems;
    }

    public RealmList<DecryptedHealthcareProviders> getHealthcareProvidersItems() {
        return healthcareProvidersItems;
    }

    public void setHealthcareProvidersItems(RealmList<DecryptedHealthcareProviders> healthcareProvidersItems) {
        this.healthcareProvidersItems = healthcareProvidersItems;
    }

    public RealmList<DecryptedIdentification> getIdentificationItems() {
        return identificationItems;
    }

    public void setIdentificationItems(RealmList<DecryptedIdentification> identificationItems) {
        this.identificationItems = identificationItems;
    }

    public RealmList<DecryptedMedicalConditions> getMedicalConditionsItems() {
        return medicalConditionsItems;
    }

    public void setMedicalConditionsItems(RealmList<DecryptedMedicalConditions> medicalConditionsItems) {
        this.medicalConditionsItems = medicalConditionsItems;
    }

    public RealmList<DecryptedMedicalHistory> getMedicalHistoryItems() {
        return medicalHistoryItems;
    }

    public void setMedicalHistoryItems(RealmList<DecryptedMedicalHistory> medicalHistoryItems) {
        this.medicalHistoryItems = medicalHistoryItems;
    }

    public RealmList<DecryptedMedications> getMedicationsItems() {
        return medicationsItems;
    }

    public void setMedicationsItems(RealmList<DecryptedMedications> medicationsItems) {
        this.medicationsItems = medicationsItems;
    }

    public RealmList<DecryptedVitalNumbers> getVitalNumbersItems() {
        return vitalNumbersItems;
    }

    public void setVitalNumbersItems(RealmList<DecryptedVitalNumbers> vitalNumbersItems) {
        this.vitalNumbersItems = vitalNumbersItems;
    }

    public RealmList<DecryptedWellness> getWellnessItems() {
        return wellnessItems;
    }

    public void setWellnessItems(RealmList<DecryptedWellness> wellnessItems) {
        this.wellnessItems = wellnessItems;
    }

    public RealmList<DecryptedWellnessList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList<DecryptedWellnessList> listItems) {
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

    public int getLists(String selectionType) {
        int count = 0;
        for (DecryptedWellnessList decryptedLicense : listItems) {
            count += decryptedLicense.getSelectionType().equals(selectionType) ? 1 : 0;
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
