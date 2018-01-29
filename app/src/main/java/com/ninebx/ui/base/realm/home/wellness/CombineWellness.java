package com.ninebx.ui.base.realm.home.wellness;

import com.ninebx.ui.base.realm.lists.WellnessList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alok on 29/01/18.
 */

public class CombineWellness extends RealmObject {

    @PrimaryKey
    private Integer id = 0;

    private RealmList<Checkups> checkupsItems                   = new RealmList<Checkups>();
    private RealmList<EmergencyContacts> emergencyContactsItems          = new RealmList<EmergencyContacts>();
    private RealmList<EyeglassPrescriptions> eyeglassPrescriptionsItems      = new RealmList<EyeglassPrescriptions>();
    private RealmList<HealthcareProviders> healthcareProvidersItems        = new RealmList<HealthcareProviders>();
    private RealmList<Identification> identificationItems             = new RealmList<Identification>();
    private RealmList<MedicalConditions> medicalConditionsItems          = new RealmList<MedicalConditions>();
    private RealmList<MedicalHistory> medicalHistoryItems             = new RealmList<MedicalHistory>();
    private RealmList<Medication> medicationsItems                = new RealmList<Medication>();
    private RealmList<VitalNumbers> vitalNumbersItems               = new RealmList<VitalNumbers>();
    private RealmList<Wellness> wellnessItems                   = new RealmList<Wellness>();


    private RealmList<WellnessList> listItems                       = new RealmList<WellnessList>();

    public CombineWellness(Integer id, RealmList checkupsItems, RealmList emergencyContactsItems, RealmList eyeglassPrescriptionsItems, RealmList healthcareProvidersItems, RealmList identificationItems, RealmList medicalConditionsItems, RealmList medicalHistoryItems, RealmList medicationsItems, RealmList vitalNumbersItems, RealmList wellnessItems, RealmList listItems) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public CombineWellness() {
    }
}
