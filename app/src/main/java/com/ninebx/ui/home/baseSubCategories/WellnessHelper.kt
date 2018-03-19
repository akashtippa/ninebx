package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Parcelable
import com.ninebx.NineBxApplication
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.wellness.CombineWellness
import com.ninebx.utility.*
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Chinmaya on 19-03-2018.
 */
class WellnessHelper(
        var category_name : String,
        val categoryID: String,
        var classType : String?,
        var selectedDocument : Parcelable?,
        val categoryView : Level2CategoryView
){
    private var decryptedIdentification: DecryptedIdentification? = null
    private var decryptedMedicalHistory: DecryptedMedicalHistory? = null
    private var decryptedHealthcareProviders: DecryptedHealthcareProviders? = null
    private var decryptedEmergencyContacts: DecryptedEmergencyContacts? = null
    private var decryptedMedications: DecryptedMedications? = null
    private var decryptedMedicalConditions: DecryptedMedicalConditions? = null
    private var decryptedEyeglassPrescriptions: DecryptedEyeglassPrescriptions? = null
    private var decryptedVitalNumbers: DecryptedVitalNumbers? = null
    private var decryptedCheckups: DecryptedCheckups? = null

    fun initialize() {
        if( selectedDocument == null ) {
            return
        }
        when (classType) {

            DecryptedCheckups::class.java.simpleName -> {
                decryptedCheckups = selectedDocument as DecryptedCheckups
            }
            DecryptedEmergencyContacts::class.java.simpleName -> {
                decryptedEmergencyContacts = selectedDocument as DecryptedEmergencyContacts
            }
            DecryptedEyeglassPrescriptions::class.java.simpleName -> {
                decryptedEyeglassPrescriptions = selectedDocument as DecryptedEyeglassPrescriptions
            }
            DecryptedHealthcareProviders::class.java.simpleName -> {
                decryptedHealthcareProviders = selectedDocument as DecryptedHealthcareProviders
            }
            DecryptedIdentification::class.java.simpleName -> {
                decryptedIdentification = selectedDocument as DecryptedIdentification
            }
            DecryptedMedicalConditions::class.java.simpleName -> {
                decryptedMedicalConditions = selectedDocument as DecryptedMedicalConditions
            }
            DecryptedMedicalHistory::class.java.simpleName -> {
                decryptedMedicalHistory = selectedDocument as DecryptedMedicalHistory
            }
            DecryptedMedications::class.java.simpleName -> {
                decryptedMedications = selectedDocument as DecryptedMedications
            }
            DecryptedVitalNumbers::class.java.simpleName -> {
                decryptedVitalNumbers = selectedDocument as DecryptedVitalNumbers
            }
        }
    }
    fun getFormForCategory(){
        when(category_name){
            "Identification" -> {
                getIdentification()
            }
            "Medical history" -> {
                getMedicalHistory()
            }
            "Healthcare providers" -> {
                getHealthCareProviders()
            }
            "Emergency contacts" -> {
            }
            "Medications" -> {
                getMedications()
            }
            "Medical conditions/Allergies" -> {
                getMedicalConditions()
            }
            "Eyeglass prescriptions" -> {
                getEyeGlassPrescriptions()
            }
            "Vital numbers" -> {
                getVitalNumbers()
            }
            "Checkups and visits" -> {
                getCheckUps()
            }
        }
    }
    private fun getIdentification() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedIdentification == null) decryptedIdentification = DecryptedIdentification()
        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Gender", "Gender", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date of birth", "Date of birth", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Age", "Age", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Height(ft, in)", "Height(ft, in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Weight", "Weight", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Hair color", "Hair color", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Eye color", "Eye color", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Visible marks", "Visible marks", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Blood type", "Blood type", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Organ donor", "Organ donor", "", Constants.LEVEL2_SWITCH))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getMedicalHistory() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedMedicalHistory == null) decryptedMedicalHistory = DecryptedMedicalHistory()
        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Past Conditions And Treatment"
        category.subCategories.add(Level2SubCategory("Description", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Immunization History"
        category.subCategories.add(Level2SubCategory("Description", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Family History"
        category.subCategories.add(Level2SubCategory("Description", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getHealthCareProviders() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedHealthcareProviders == null) decryptedHealthcareProviders = DecryptedHealthcareProviders()
        var categoryIndex = 6004
        var category_id = "home_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Practice/Group name", "Practice/Group name", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Phone number 1", "Phone number 1", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Phone number 2", "Phone number 2", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Email address", "Email address", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Street address 1", "Street address 1", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Street address 2", "Street address 2", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("City", "City", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("State", "State", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Zip code", "Zip code", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Country", "Country", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getMedications() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedMedications == null) decryptedMedications = DecryptedMedications()
        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Frequency", "Frequency", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Start date", "Start date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("End date", "End date", "", Constants.LEVEL2_PICKER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getMedicalConditions() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedMedicalConditions == null) decryptedMedicalConditions = DecryptedMedicalConditions()
        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Description", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getEyeGlassPrescriptions() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedEyeglassPrescriptions == null) decryptedEyeglassPrescriptions = DecryptedEyeglassPrescriptions()
        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Description", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getVitalNumbers() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedVitalNumbers == null) decryptedVitalNumbers = DecryptedVitalNumbers()
        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Height (ft, in)", "Height (ft, in)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Weight(lbs)", "Weight(lbs)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Waist(in)", "Waist(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Body fat(%)", "Body fat(%)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Body mass index(BMI kg/m2)", "Body mass index(BMI kg/m2)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Blood pressure(xx/yy)", "Blood pressure(xx/yy)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Heart rate(bpm)", "Heart rate(bpm)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Total cholesterol(mg/dL)", "Total cholesterol(mg/dL)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("HDL cholesterol(mg/dL)", "HDL cholesterol(mg/dL)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("LDL cholesterol(mg/dL)", "LDL cholesterol(mg/dL)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Cholesterol ration(Total cholesterol/HDL)", "Cholesterol ration(Total cholesterol/HDL)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Triglycerides(mg/dL)", "Triglycerides(mg/dL)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Blood glucose(mg/dL)", "Blood glucose(mg/dL)", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Hemoglobin A1C(%)", "Hemoglobin A1C(%)", "", Constants.LEVEL2_SWITCH))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getCheckUps() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedCheckups == null) decryptedCheckups = DecryptedCheckups()
        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Type of physician", "Type of physician", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Reason", "Reason", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Date of visit", "Date of visit", "", Constants.LEVEL2_PICKER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }
    fun setValue(level2Category: Level2SubCategory){
        when(category_name){
            "Identification" -> {
                setIdentification(level2Category)
            }
            "Medical history" -> {
                setMedicalHistory(level2Category)
            }
            "Healthcare providers" -> {
                setHealthCareProviders(level2Category)
            }
            "Emergency contacts" -> {
                setEmergencyContacts(level2Category)
            }
            "Medications" -> {
                setMedications(level2Category)
            }
            "Medical conditions/Allergies" -> {
                setMedicalConditions(level2Category)
            }
            "Eyeglass prescriptions" -> {
                setEyeGlassPrescriptions(level2Category)
            }
            "Vital numbers" -> {
                setVitalNumbers(level2Category)
            }
            "Checkups and visits" -> {
                setCheckUps(level2Category)
            }
        }
    }
    private fun setIdentification(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Name" -> decryptedIdentification!!.name = level2Category.titleValue
            "Gender" -> decryptedIdentification!!.gender = level2Category.titleValue
            "Age" -> decryptedIdentification!!.age = level2Category.titleValue
            "Date of birth" -> decryptedIdentification!!.dateofBirth = level2Category.titleValue
            "Height (ft, in)" -> decryptedIdentification!!.height = level2Category.titleValue
            "Weight (lbs)" -> decryptedIdentification!!.weight = level2Category.titleValue
            "Hair color" -> decryptedIdentification!!.hairColor = level2Category.titleValue
            "Eye color" -> decryptedIdentification!!.eyeColor = level2Category.titleValue
            "Visible marks" -> decryptedIdentification!!.visibleMarks = level2Category.titleValue
            "Blood type" -> decryptedIdentification!!.bloodType = level2Category.titleValue
            "Organ donor" -> decryptedIdentification!!.orgonDonor = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedIdentification!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedIdentification!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setMedicalHistory(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "History" -> decryptedMedicalHistory!!.history = level2Category.titleValue
            "pastConditions" -> decryptedMedicalHistory!!.treatmentDiscription = level2Category.titleValue
            "immunications" -> decryptedMedicalHistory!!.immunizationDiscription = level2Category.titleValue
            "family" -> decryptedMedicalHistory!!.familyDiscription = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedMedicalHistory!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedMedicalHistory!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setHealthCareProviders(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Name" -> decryptedHealthcareProviders!!.name = level2Category.titleValue
            "Type of physician" -> decryptedHealthcareProviders!!.physicianType = level2Category.titleValue
            "Practice/Group name" -> decryptedHealthcareProviders!!.practiceName = level2Category.titleValue
            "Phone number 1" -> decryptedHealthcareProviders!!.phoneNumberOne = level2Category.titleValue
            "Phone number 2" -> decryptedHealthcareProviders!!.phoneNumberTwo = level2Category.titleValue
            "Email address" -> decryptedHealthcareProviders!!.emailAddress = level2Category.titleValue
            "Street Address 1" -> decryptedHealthcareProviders!!.streetAddressOne = level2Category.titleValue
            "Street Address 2" -> decryptedHealthcareProviders!!.streetAddressTwo = level2Category.titleValue
            "City" -> decryptedHealthcareProviders!!.city = level2Category.titleValue
            "State" -> decryptedHealthcareProviders!!.state = level2Category.titleValue
            "Zip code" -> decryptedHealthcareProviders!!.zipCode = level2Category.titleValue
            "Country" -> decryptedHealthcareProviders!!.country = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedHealthcareProviders!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedHealthcareProviders!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setEmergencyContacts(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Name" -> decryptedEmergencyContacts!!.name = level2Category.titleValue
            "Relationship" -> decryptedEmergencyContacts!!.relationShip = level2Category.titleValue
            "Phone number 1" -> decryptedEmergencyContacts!!.phoneNumberOne = level2Category.titleValue
            "Phone number 2" -> decryptedEmergencyContacts!!.phoneNumberTwo = level2Category.titleValue
            "Email address" -> decryptedEmergencyContacts!!.emailAddress = level2Category.titleValue
            "Street Address 1" -> decryptedEmergencyContacts!!.streetAddressOne = level2Category.titleValue
            "Street Address 2" -> decryptedEmergencyContacts!!.streetAddressTwo = level2Category.titleValue
            "City" -> decryptedEmergencyContacts!!.city = level2Category.titleValue
            "State" -> decryptedEmergencyContacts!!.state = level2Category.titleValue
            "Zip code" -> decryptedEmergencyContacts!!.zipCode = level2Category.titleValue
            "Country" -> decryptedEmergencyContacts!!.country = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedEmergencyContacts!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedEmergencyContacts!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setMedications(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Name" -> decryptedMedications!!.name = level2Category.titleValue
            "Dose/strength" -> decryptedMedications!!.strength = level2Category.titleValue
            "Frequency" -> decryptedMedications!!.frequency = level2Category.titleValue
            "Start date" -> decryptedMedications!!.startDate = level2Category.titleValue
            "End date" -> decryptedMedications!!.endDate = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedMedications!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedMedications!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setMedicalConditions(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Condition" -> decryptedMedicalConditions!!.condition = level2Category.titleValue
            "Date diagnosed" -> decryptedMedicalConditions!!.dateDiagnosed = level2Category.titleValue
            "Description" -> decryptedMedicalConditions!!.medi_description = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedMedicalConditions!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedMedicalConditions!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setEyeGlassPrescriptions(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Physician name" -> decryptedEyeglassPrescriptions!!.physicianName = level2Category.titleValue
            "Date prescribed" -> decryptedEyeglassPrescriptions!!.datePrescribed = level2Category.titleValue

            "odSphereValue" -> decryptedEyeglassPrescriptions!!.odSphereValue = level2Category.titleValue
            "osSphereValue" -> decryptedEyeglassPrescriptions!!.osSphereValue = level2Category.titleValue

            "odCylinderValue" -> decryptedEyeglassPrescriptions!!.odCylinderValue = level2Category.titleValue
            "osCylinderValue" -> decryptedEyeglassPrescriptions!!.osCylinderValue = level2Category.titleValue

            "odAxisValue" -> decryptedEyeglassPrescriptions!!.odAxisValue = level2Category.titleValue
            "osAxisValue" -> decryptedEyeglassPrescriptions!!.osAxisValue = level2Category.titleValue

            "odPrismValue" -> decryptedEyeglassPrescriptions!!.odPrismValue = level2Category.titleValue
            "osPrismValue" -> decryptedEyeglassPrescriptions!!.osPrismValue = level2Category.titleValue

            "odAddValue" -> decryptedEyeglassPrescriptions!!.odAddValue = level2Category.titleValue
            "osAddValue" -> decryptedEyeglassPrescriptions!!.osAddValue = level2Category.titleValue

            "odBaseValue" -> decryptedEyeglassPrescriptions!!.odBaseValue = level2Category.titleValue
            "osBaseValue" -> decryptedEyeglassPrescriptions!!.osBaseValue = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedEyeglassPrescriptions!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedEyeglassPrescriptions!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setVitalNumbers(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Description" -> decryptedVitalNumbers!!.vital_description = level2Category.titleValue
            "Measurement date" -> decryptedVitalNumbers!!.measurementDate = level2Category.titleValue
            "Height (ft, in)" -> decryptedVitalNumbers!!.height = level2Category.titleValue
            "Weight (lbs)" -> decryptedVitalNumbers!!.weight = level2Category.titleValue
            "Waist (in)" -> decryptedVitalNumbers!!.waist = level2Category.titleValue
            "Body fat (%)" -> decryptedVitalNumbers!!.bodyFat = level2Category.titleValue
            "Body mass index (BMI kg/m2)" -> decryptedVitalNumbers!!.bodyMassIndex = level2Category.titleValue
            "Blood pressure (xx/yy)" -> decryptedVitalNumbers!!.bloodPressure = level2Category.titleValue
            "Heart rate (bpm)" -> decryptedVitalNumbers!!.heartRate = level2Category.titleValue
            "Total cholesterol (mg/dL)" -> decryptedVitalNumbers!!.totalCholesterol = level2Category.titleValue
            "HDL cholesterol (mg/dL)" -> decryptedVitalNumbers!!.hdlCholesterol = level2Category.titleValue
            "LDL cholesterol (mg/dL)" -> decryptedVitalNumbers!!.ldlCholesterol = level2Category.titleValue
            "Cholesterol ratio (Total cholesterol/HDL)" -> decryptedVitalNumbers!!.cholesterolRatio = level2Category.titleValue
            "Triglycerides (mg/dL)" -> decryptedVitalNumbers!!.triglycerides = level2Category.titleValue
            "Blood glucose (mg/dL)" -> decryptedVitalNumbers!!.bloodGlucose = level2Category.titleValue
            "Hemoglobin A1C (%)" -> decryptedVitalNumbers!!.hemoglobin = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedVitalNumbers!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedVitalNumbers!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

    private fun setCheckUps(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Description" -> decryptedCheckups!!.checkup_description = level2Category.titleValue
            "Physician name" -> decryptedCheckups!!.physicianName = level2Category.titleValue
            "Type of physician" -> decryptedCheckups!!.physicianType = level2Category.titleValue
            "Reason" -> decryptedCheckups!!.reason = level2Category.titleValue
            "Date of visit" -> decryptedCheckups!!.dateOfVisit = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedCheckups!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedCheckups!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }
    private var mCombine : Parcelable ?= null
    @SuppressLint("StaticFieldLeak")
    fun saveDocument(context: Context, combineItem: Parcelable?, title: String, subTitle: String) {
        mCombine = combineItem
        val currentUsers = NineBxApplication.getPreferences().userFirstName + " " + NineBxApplication.getPreferences().userLastName
        val sdf = SimpleDateFormat(" E,MMM dd,yyyy, HH:mm")
        val currentDateandTime = sdf.format(Date())

        if (decryptedIdentification != null) {
            decryptedIdentification!!.selectionType = categoryID
            decryptedIdentification!!.name = title
            if( decryptedIdentification!!.created.isEmpty() )
                decryptedIdentification!!.created = currentUsers + " " + currentDateandTime
            decryptedIdentification!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedIdentification!!.id.toInt() == 0) {
                decryptedIdentification!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var identification = encryptIdentification(decryptedIdentification!!)
                            realm!!.insertOrUpdate(identification)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            var combineWellness: DecryptedCombineWellness = mCombine as DecryptedCombineWellness
                            var realmIdentification = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (realmIdentification == null) {
                                realmIdentification = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            realmIdentification!!.identificationItems.add(encryptIdentification(decryptedIdentification!!))
                            realm.insertOrUpdate(realmIdentification)
                            realm.commitTransaction()
                        }

                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if (decryptedMedicalHistory != null) {
            decryptedMedicalHistory!!.selectionType = categoryID
            decryptedMedicalHistory!!.attachmentNames = title
            if( decryptedMedicalHistory!!.created.isEmpty() )
                decryptedMedicalHistory!!.created = currentUsers + " " + currentDateandTime
            decryptedMedicalHistory!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedMedicalHistory!!.id.toInt() == 0) {
                decryptedMedicalHistory!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val medicalHistory = encryptMedicalHistory(decryptedMedicalHistory!!)
                            realm.insertOrUpdate(medicalHistory)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {

                            val combineWellness: DecryptedCombineWellness = mCombine as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.medicalHistoryItems.add(encryptMedicalHistory(decryptedMedicalHistory!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if (decryptedHealthcareProviders != null) {
            decryptedHealthcareProviders!!.selectionType = categoryID
            decryptedHealthcareProviders!!.name = title
            if( decryptedHealthcareProviders!!.created.isEmpty() )
                decryptedHealthcareProviders!!.created = currentUsers + " " + currentDateandTime
            decryptedHealthcareProviders!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedHealthcareProviders!!.id.toInt() == 0) {
                decryptedHealthcareProviders!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val healthcareProviders = encryptHealthCareProviders(decryptedHealthcareProviders!!)
                            realm.insertOrUpdate(healthcareProviders)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            //val combine: DecryptedCombine = mCombine as DecryptedCombine
                            //AppLogger.d("saveDocument", "Combine Id " + combine!!.id)
                            val combineWellness: DecryptedCombineWellness = mCombine as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.healthcareProvidersItems.add(encryptHealthCareProviders(decryptedHealthcareProviders!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if (decryptedEmergencyContacts != null) {
            decryptedEmergencyContacts!!.selectionType = categoryID
            decryptedEmergencyContacts!!.name = title
            if( decryptedEmergencyContacts!!.created.isEmpty() )
                decryptedEmergencyContacts!!.created = currentUsers + " " + currentDateandTime
            decryptedEmergencyContacts!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedEmergencyContacts!!.id.toInt() == 0) {
                decryptedEmergencyContacts!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val emergencyConatcts = encryptEmergencyContacts(decryptedEmergencyContacts!!)
                            realm.insertOrUpdate(emergencyConatcts)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineWellness: DecryptedCombineWellness = mCombine as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.emergencyContactsItems.add(encryptEmergencyContacts(decryptedEmergencyContacts!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if (decryptedMedications != null) {
            decryptedMedications!!.selectionType = categoryID
            decryptedMedications!!.name = title
            if( decryptedMedications!!.created.isEmpty() )
                decryptedMedications!!.created = currentUsers + " " + currentDateandTime
            decryptedMedications!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedMedications!!.id.toInt() == 0) {
                decryptedMedications!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val medications = encryptMedications(decryptedMedications!!)
                            realm.insertOrUpdate(medications)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineWellness: DecryptedCombineWellness = mCombine as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.medicationsItems.add(encryptMedications(decryptedMedications!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if (decryptedMedicalConditions != null) {
            decryptedMedicalConditions!!.selectionType = categoryID
            decryptedMedicalConditions!!.attachmentNames = title
            if( decryptedMedicalConditions!!.created.isEmpty() )
                decryptedMedicalConditions!!.created = currentUsers + " " + currentDateandTime
            decryptedMedicalConditions!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedMedicalConditions!!.id.toInt() == 0) {
                decryptedMedicalConditions!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val medicalConditions = encryptMedicalConditions(decryptedMedicalConditions!!)
                            realm.insertOrUpdate(medicalConditions)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineWellness: DecryptedCombineWellness = mCombine as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.medicalConditionsItems.add(encryptMedicalConditions(decryptedMedicalConditions!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if (decryptedEyeglassPrescriptions != null) {
            decryptedEyeglassPrescriptions!!.selectionType = categoryID
            decryptedEyeglassPrescriptions!!.attachmentNames = title
            decryptedEyeglassPrescriptions!!.modified = currentUsers + " " + currentDateandTime
            if( decryptedEyeglassPrescriptions!!.created.isEmpty() )
                decryptedEyeglassPrescriptions!!.created = currentUsers + " " + currentDateandTime
            if (decryptedEyeglassPrescriptions!!.id.toInt() == 0) {
                decryptedEyeglassPrescriptions!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val eyeglassPrescriptions = encryptEyeGlassPrescriptions(decryptedEyeglassPrescriptions!!)
                            realm.insertOrUpdate(eyeglassPrescriptions)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineWellness: DecryptedCombineWellness = mCombine as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.eyeglassPrescriptionsItems.add(encryptEyeGlassPrescriptions(decryptedEyeglassPrescriptions!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if (decryptedVitalNumbers != null) {
            decryptedVitalNumbers!!.selectionType = categoryID
            decryptedVitalNumbers!!.attachmentNames = title
            if( decryptedVitalNumbers!!.created.isEmpty() )
                decryptedVitalNumbers!!.created = currentUsers + " " + currentDateandTime
            decryptedVitalNumbers!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedVitalNumbers!!.id.toInt() == 0) {
                decryptedVitalNumbers!!.id = getUniqueId()
            }

            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val vitalNumbers = encryptVitalNumbers(decryptedVitalNumbers!!)
                            realm.insertOrUpdate(vitalNumbers)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineWellness: DecryptedCombineWellness = mCombine as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.vitalNumbersItems.add(encryptVitalNumbers(decryptedVitalNumbers!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if (decryptedCheckups != null) {
            decryptedCheckups!!.selectionType = categoryID
            decryptedCheckups!!.attachmentNames = title
            if( decryptedCheckups!!.created.isEmpty() )
                decryptedCheckups!!.created = currentUsers + " " + currentDateandTime
            decryptedCheckups!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedCheckups!!.id.toInt() == 0) {
                decryptedCheckups!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val checkUps = encryptCheckUps(decryptedCheckups!!)
                            realm.insertOrUpdate(checkUps)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_WELLNESS, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineWellness: DecryptedCombineWellness = mCombine as DecryptedCombineWellness
                            var combineRealm = realm!!.where(CombineWellness::class.java).equalTo("id", combineWellness.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineWellness::class.java, getUniqueId())
                            }
                            combineRealm!!.checkupsItems.add(encryptCheckUps(decryptedCheckups!!))
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
    }
}