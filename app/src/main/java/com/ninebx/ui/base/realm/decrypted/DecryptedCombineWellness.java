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
    private int id = 0;
    @Required
    private RealmList<Checkups> checkupsItems = new RealmList<Checkups>();
    @Required
    private RealmList<EmergencyContacts> emergencyContactsItems = new RealmList<EmergencyContacts>();
    @Required
    private RealmList<EyeglassPrescriptions> eyeglassPrescriptionsItems = new RealmList<EyeglassPrescriptions>();
    @Required
    private RealmList<HealthcareProviders> healthcareProvidersItems = new RealmList<HealthcareProviders>();
    @Required
    private RealmList<Identification> identificationItems = new RealmList<Identification>();
    @Required
    private RealmList<MedicalConditions> medicalConditionsItems = new RealmList<MedicalConditions>();
    @Required
    private RealmList<MedicalHistory> medicalHistoryItems = new RealmList<MedicalHistory>();
    @Required
    private RealmList<Medications> medicationsItems = new RealmList<Medications>();
    @Required
    private RealmList<VitalNumbers> vitalNumbersItems = new RealmList<VitalNumbers>();
    @Required
    private RealmList<Wellness> wellnessItems = new RealmList<Wellness>();
    @Required
    private RealmList<WellnessList> listItems = new RealmList<WellnessList>();

    public DecryptedCombineWellness(int id, RealmList checkupsItems, RealmList emergencyContactsItems, RealmList eyeglassPrescriptionsItems, RealmList healthcareProvidersItems, RealmList identificationItems, RealmList medicalConditionsItems, RealmList medicalHistoryItems, RealmList medicationsItems, RealmList vitalNumbersItems, RealmList wellnessItems, RealmList listItems) {
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList getCheckupsItems() {
        return checkupsItems;
    }

    public void setCheckupsItems(RealmList checkupsItems) {
        this.checkupsItems = checkupsItems;
    }

    public RealmList getEmergencyContactsItems() {
        return emergencyContactsItems;
    }

    public void setEmergencyContactsItems(RealmList emergencyContactsItems) {
        this.emergencyContactsItems = emergencyContactsItems;
    }

    public RealmList getEyeglassPrescriptionsItems() {
        return eyeglassPrescriptionsItems;
    }

    public void setEyeglassPrescriptionsItems(RealmList eyeglassPrescriptionsItems) {
        this.eyeglassPrescriptionsItems = eyeglassPrescriptionsItems;
    }

    public RealmList getHealthcareProvidersItems() {
        return healthcareProvidersItems;
    }

    public void setHealthcareProvidersItems(RealmList healthcareProvidersItems) {
        this.healthcareProvidersItems = healthcareProvidersItems;
    }

    public RealmList getIdentificationItems() {
        return identificationItems;
    }

    public void setIdentificationItems(RealmList identificationItems) {
        this.identificationItems = identificationItems;
    }

    public RealmList getMedicalConditionsItems() {
        return medicalConditionsItems;
    }

    public void setMedicalConditionsItems(RealmList medicalConditionsItems) {
        this.medicalConditionsItems = medicalConditionsItems;
    }

    public RealmList getMedicalHistoryItems() {
        return medicalHistoryItems;
    }

    public void setMedicalHistoryItems(RealmList medicalHistoryItems) {
        this.medicalHistoryItems = medicalHistoryItems;
    }

    public RealmList getMedicationsItems() {
        return medicationsItems;
    }

    public void setMedicationsItems(RealmList medicationsItems) {
        this.medicationsItems = medicationsItems;
    }

    public RealmList getVitalNumbersItems() {
        return vitalNumbersItems;
    }

    public void setVitalNumbersItems(RealmList vitalNumbersItems) {
        this.vitalNumbersItems = vitalNumbersItems;
    }

    public RealmList getWellnessItems() {
        return wellnessItems;
    }

    public void setWellnessItems(RealmList wellnessItems) {
        this.wellnessItems = wellnessItems;
    }

    public RealmList getListItems() {
        return listItems;
    }

    public void setListItems(RealmList listItems) {
        this.listItems = listItems;
    }
}
