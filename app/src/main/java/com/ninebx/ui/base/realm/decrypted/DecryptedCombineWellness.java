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

    public DecryptedCombineWellness() {

    }

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


    public int getLists(String selectionType, Integer detailsId ) {
        int count = 0;
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedWellnessList selectedItem : listItems) {
            count += (selectedItem.getSelectionType().equals(selectionType) && selectedItem.getDetailsId() == detailsId )? 1 : 0;
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
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
        ArrayList<Long> ids = new ArrayList<>();
        for (DecryptedWellness selectedItem : wellnessItems) {
            if (!ids.contains(selectedItem.getId())) {
                count += selectedItem.getSelectionType().equals(selectionType) ? 1 : 0;
                ids.add(selectedItem.getId());
            }
        }
        return count;
    }

    public int getHealthcareSize(String name){
        int count = 0;
        ArrayList<Long> ids = new ArrayList<Long>();
        for (DecryptedHealthcareProviders selectedItem : healthcareProvidersItems ){
            if( selectedItem.getName().equals(name) && !ids.contains(selectedItem.id)) {
                count++;
                ids.add(selectedItem.id);
            }
        }
        return count;
    }

    public int getEmergencyContactsSize(String name){
        int count = 0;
        ArrayList<Long> ids = new ArrayList<Long>();
        for(DecryptedEmergencyContacts selectedItem : emergencyContactsItems){
            if (selectedItem.getName().equals(name) && !ids.contains(selectedItem.id)){
                count++;
                ids.add(selectedItem.id);
            }
        }
        return count;
    }

    public int getMedicationsSize(String name){
        int count = 0 ;
        ArrayList<Long> ids = new ArrayList<Long>();
        for(DecryptedMedications selectedItem : medicationsItems){
            if(selectedItem.getName().equals(name) && !ids.contains(selectedItem.id)){
                count++;
                ids.add(selectedItem.id);
            }
        }
        return count;
    }

   /* public int getMedicalConditionSize(String name){
        int count = 0;
        ArrayList<Long> ids = new ArrayList<Long>();
        for(DecryptedMedicalConditions selectedItem : medicalConditionsItems){
            if(selectedItem.getName().equals(name) && !ids.contains(selectedItem.id)){
                count++;
                ids.add(selectedItem.id);
            }
        }
        return count;
    }*/
  /* public int getEyeglassPrescriptionSize(String name){
       int count = 0 ;
       ArrayList<Long> ids = new ArrayList<Long>();
       for(DecryptedEyeglassPrescriptions selectedItem : eyeglassPrescriptionsItems){
           if(selectedItem.getName().equals(name) && !ids.contains(selectedItem.id)){
               count++;
               ids.add(selectedItem.id);
           }
       }
       return count;
   }*/
 /*  public int getVitalNumbersSize(String name){
       int count = 0 ;
       ArrayList<Long> ids = new ArrayList<Long>();
       for(DecryptedVitalNumbers selectedItem : vitalNumbersItems){
           if(selectedItem.getName().equals(name) && !ids.contains(selectedItem.id)){
               count++;
               ids.add(selectedItem.id);
           }
       }
       return count;
   }*/
  /* public int getCheckupsSize(String name){
       int count = 0 ;
       ArrayList<Long> ids = new ArrayList<Long>();
       for(DecryptedCheckups selectedItem : checkupsItems){
           if(selectedItem.getName().equals(name) && !ids.contains(selectedItem.id)){
               count++;
               ids.add(selectedItem.id);
           }
       }
       return count;
   }*/
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeTypedList(this.checkupsItems);
        dest.writeTypedList(this.emergencyContactsItems);
        dest.writeTypedList(this.eyeglassPrescriptionsItems);
        dest.writeTypedList(this.healthcareProvidersItems);
        dest.writeTypedList(this.identificationItems);
        dest.writeTypedList(this.medicalConditionsItems);
        dest.writeTypedList(this.medicalHistoryItems);
        dest.writeTypedList(this.medicationsItems);
        dest.writeTypedList(this.vitalNumbersItems);
        dest.writeTypedList(this.wellnessItems);
        dest.writeTypedList(this.listItems);
        dest.writeString(this.searchField);
    }

    protected DecryptedCombineWellness(Parcel in) {
        this.id = in.readLong();
        this.checkupsItems = in.createTypedArrayList(DecryptedCheckups.CREATOR);
        this.emergencyContactsItems = in.createTypedArrayList(DecryptedEmergencyContacts.CREATOR);
        this.eyeglassPrescriptionsItems = in.createTypedArrayList(DecryptedEyeglassPrescriptions.CREATOR);
        this.healthcareProvidersItems = in.createTypedArrayList(DecryptedHealthcareProviders.CREATOR);
        this.identificationItems = in.createTypedArrayList(DecryptedIdentification.CREATOR);
        this.medicalConditionsItems = in.createTypedArrayList(DecryptedMedicalConditions.CREATOR);
        this.medicalHistoryItems = in.createTypedArrayList(DecryptedMedicalHistory.CREATOR);
        this.medicationsItems = in.createTypedArrayList(DecryptedMedications.CREATOR);
        this.vitalNumbersItems = in.createTypedArrayList(DecryptedVitalNumbers.CREATOR);
        this.wellnessItems = in.createTypedArrayList(DecryptedWellness.CREATOR);
        this.listItems = in.createTypedArrayList(DecryptedWellnessList.CREATOR);
        this.searchField = in.readString();
    }

    public static final Creator<DecryptedCombineWellness> CREATOR = new Creator<DecryptedCombineWellness>() {
        @Override
        public DecryptedCombineWellness createFromParcel(Parcel source) {
            return new DecryptedCombineWellness(source);
        }

        @Override
        public DecryptedCombineWellness[] newArray(int size) {
            return new DecryptedCombineWellness[size];
        }
    };
}
