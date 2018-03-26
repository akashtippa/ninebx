package com.ninebx.ui.base.realm.home.wellness;

import com.ninebx.ui.base.realm.lists.WellnessList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 29/01/18.
 */
@RealmClass
public class CombineWellness extends RealmObject {

    @PrimaryKey //@Required
    private long id = 0;

    @Required private RealmList<Checkups> checkupsItems                   = new RealmList<Checkups>();
    @Required private RealmList<EmergencyContacts> emergencyContactsItems          = new RealmList<EmergencyContacts>();
    @Required private RealmList<EyeglassPrescriptions> eyeglassPrescriptionsItems      = new RealmList<EyeglassPrescriptions>();
    @Required private RealmList<HealthcareProviders> healthcareProvidersItems        = new RealmList<HealthcareProviders>();
    @Required private RealmList<Identification> identificationItems             = new RealmList<Identification>();
    @Required private RealmList<MedicalConditions> medicalConditionsItems          = new RealmList<MedicalConditions>();
    @Required private RealmList<MedicalHistory> medicalHistoryItems             = new RealmList<MedicalHistory>();
    @Required private RealmList<Medications> medicationsItems                = new RealmList<Medications>();
    @Required private RealmList<VitalNumbers> vitalNumbersItems               = new RealmList<VitalNumbers>();
    @Required private RealmList<Wellness> wellnessItems                   = new RealmList<Wellness>();


    @Required private RealmList<WellnessList> listItems                       = new RealmList<WellnessList>();

    public CombineWellness(long id, RealmList checkupsItems, RealmList emergencyContactsItems, RealmList eyeglassPrescriptionsItems, RealmList healthcareProvidersItems, RealmList identificationItems, RealmList medicalConditionsItems, RealmList medicalHistoryItems, RealmList medicationsItems, RealmList vitalNumbersItems, RealmList wellnessItems, RealmList listItems) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public RealmList<WellnessList> getListItems() {
        return listItems;
    }

    public void setListItems(RealmList listItems) {
        this.listItems = listItems;
    }

    public CombineWellness() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CombineWellness that = (CombineWellness) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
