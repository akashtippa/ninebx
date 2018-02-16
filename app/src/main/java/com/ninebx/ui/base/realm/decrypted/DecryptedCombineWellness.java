package com.ninebx.ui.base.realm.decrypted;

import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.home.wellness.Checkups;
import com.ninebx.ui.base.realm.home.wellness.EmergencyContacts;
import com.ninebx.ui.base.realm.home.wellness.EyeglassPrescriptions;
import com.ninebx.ui.base.realm.home.wellness.HealthcareProviders;
import com.ninebx.ui.base.realm.home.wellness.Identification;
import com.ninebx.ui.base.realm.home.wellness.MedicalConditions;
import com.ninebx.ui.base.realm.home.wellness.MedicalHistory;
import com.ninebx.ui.base.realm.home.wellness.Medications;
import com.ninebx.ui.base.realm.home.wellness.VitalNumbers;
import com.ninebx.ui.base.realm.home.wellness.Wellness;
import com.ninebx.ui.base.realm.lists.WellnessList;

import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alok on 29/01/18.
 */
public class DecryptedCombineWellness implements Parcelable {

    @PrimaryKey //@Required
    private int id = 0;
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

    public DecryptedCombineWellness(int id, RealmList<DecryptedCheckups> checkupsItems, RealmList<DecryptedEmergencyContacts> emergencyContactsItems, RealmList<DecryptedEyeglassPrescriptions> eyeglassPrescriptionsItems, RealmList<DecryptedHealthcareProviders> healthcareProvidersItems, RealmList<DecryptedIdentification> identificationItems, RealmList<DecryptedMedicalConditions> medicalConditionsItems, RealmList<DecryptedMedicalHistory> medicalHistoryItems, RealmList<DecryptedMedications> medicationsItems, RealmList<DecryptedVitalNumbers> vitalNumbersItems, RealmList<DecryptedWellness> wellnessItems, RealmList<DecryptedWellnessList> listItems) {
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

    public DecryptedCombineWellness() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        dest.writeInt(id);
    }
}
