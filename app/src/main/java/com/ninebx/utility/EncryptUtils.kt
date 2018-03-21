package com.ninebx.utility

import com.ninebx.ui.base.realm.*
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.base.realm.home.contacts.MainContacts
import com.ninebx.ui.base.realm.home.education.CombineEducation
import com.ninebx.ui.base.realm.home.education.Education
import com.ninebx.ui.base.realm.home.education.MainEducation
import com.ninebx.ui.base.realm.home.education.Work
import com.ninebx.ui.base.realm.home.homeBanking.*
import com.ninebx.ui.base.realm.home.interests.CombineInterests
import com.ninebx.ui.base.realm.home.interests.Interests
import com.ninebx.ui.base.realm.home.memories.CombineMemories
import com.ninebx.ui.base.realm.home.memories.MainMemories
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.base.realm.home.personal.*
import com.ninebx.ui.base.realm.home.shopping.*
import com.ninebx.ui.base.realm.home.travel.*
import com.ninebx.ui.base.realm.home.wellness.*
import com.ninebx.ui.base.realm.lists.*
import io.realm.RealmList

/**
 * Created by Alok on 27/02/18.
 */

fun encryptFinancial(finance: DecryptedFinancial): Financial {

    val decryptedFinancial = Financial()

    decryptedFinancial.id = finance.id
    decryptedFinancial.photosId = finance.photosId
    decryptedFinancial.backingImages .addAll(finance.backingImages)

    decryptedFinancial.selectionType = finance.selectionType.encryptString()
    decryptedFinancial.institutionName = finance.institutionName.encryptString()
    decryptedFinancial.accountName = finance.accountName.encryptString()
    decryptedFinancial.accountType = finance.accountType.encryptString()
    decryptedFinancial.nameOnAccount = finance.nameOnAccount.encryptString()
    decryptedFinancial.accountNumber = finance.accountNumber.encryptString()
    decryptedFinancial.location = finance.location.encryptString()
    decryptedFinancial.swiftCode = finance.swiftCode.encryptString()
    decryptedFinancial.abaRoutingNumber = finance.abaRoutingNumber.encryptString()
    decryptedFinancial.abaRoutingNumber = finance.abaRoutingNumber.encryptString()
    decryptedFinancial.contacts = finance.contacts.encryptString()
    decryptedFinancial.website = finance.website.encryptString()
    decryptedFinancial.userName = finance.userName.encryptString()
    decryptedFinancial.password = finance.password.encryptString()
    decryptedFinancial.pin = finance.pin.encryptString()
    decryptedFinancial.created = finance.created
    decryptedFinancial.modified = finance.modified
    decryptedFinancial.createdUser = finance.createdUser
    decryptedFinancial.created = finance.created
    decryptedFinancial.modified = finance.modified
    decryptedFinancial.createdUser = finance.createdUser
    decryptedFinancial.notes = finance.notes.encryptString()
    decryptedFinancial.attachmentNames = finance.attachmentNames

    AppLogger.d("Encrypt", "decryptedFinancial : " + decryptedFinancial)

    return decryptedFinancial
}

fun encryptPayment(payment: DecryptedPayment): Payment {

    val decryptedPayment = Payment()

    decryptedPayment.id = payment.id

    decryptedPayment.selectionType = payment.selectionType.encryptString()
    decryptedPayment.cardName = payment.cardName.encryptString()
    decryptedPayment.cardNumber = payment.cardNumber.encryptString()
    decryptedPayment.cardType = payment.cardType.encryptString()
    decryptedPayment.cardHolder = payment.cardHolder.encryptString()
    decryptedPayment.expiryDate = payment.expiryDate.encryptString()
    decryptedPayment.cvvCode = payment.cvvCode.encryptString()
    decryptedPayment.cardPin = payment.cardPin.encryptString()
    decryptedPayment.issuingBank = payment.issuingBank.encryptString()
    decryptedPayment.website = payment.website.encryptString()
    decryptedPayment.password = payment.password.encryptString()
    decryptedPayment.pin = payment.pin.encryptString()
    decryptedPayment.created = payment.created
    decryptedPayment.modified = payment.modified
    decryptedPayment.isPrivate = payment.isPrivate
    decryptedPayment.createdUser = payment.createdUser
    decryptedPayment.notes = payment.notes
    decryptedPayment.attachmentNames = payment.attachmentNames
    AppLogger.d("Encrypt", "decryptedPayment : " + decryptedPayment)
    return decryptedPayment
}

fun encryptProperty(property: DecryptedProperty): Property {
    val decryptedProperty = Property()

    decryptedProperty.id = property.id
    decryptedProperty.photosId = property.photosId
    decryptedProperty.backingImages .addAll(property.backingImages)

    decryptedProperty.selectionType = property.selectionType.encryptString()
    decryptedProperty.propertyName = property.propertyName.encryptString()
    decryptedProperty.streetAddressOne = property.streetAddressOne.encryptString()
    decryptedProperty.streetAddressTwo = property.streetAddressTwo.encryptString()
    decryptedProperty.city = property.city.encryptString()
    decryptedProperty.state = property.state.encryptString()
    decryptedProperty.zipCode = property.zipCode.encryptString()
    decryptedProperty.country = property.country.encryptString()
    decryptedProperty.titleName = property.titleName.encryptString()
    decryptedProperty.purchaseDate = property.purchaseDate.encryptString()
    decryptedProperty.purchasePrice = property.purchasePrice.encryptString()
    decryptedProperty.estimatedMarketValue = property.estimatedMarketValue.encryptString()
    decryptedProperty.contacts = property.contacts.encryptString()
    decryptedProperty.currentlyRented = property.currentlyRented
    decryptedProperty.tenantName = property.tenantName.encryptString()
    decryptedProperty.leaseStartDate = property.leaseStartDate.encryptString()
    decryptedProperty.leaseEndDate = property.leaseEndDate.encryptString()
    decryptedProperty.created = property.created
    decryptedProperty.modified = property.modified

    decryptedProperty.createdUser = property.createdUser
    decryptedProperty.notes = property.notes
    decryptedProperty.attachmentNames = property.attachmentNames

    AppLogger.d("Encrypt", "decryptedProperty : " + decryptedProperty)

    return decryptedProperty
}

fun encryptVehicle(vehicle: DecryptedVehicle): Vehicle {
    val decryptedVehicle = Vehicle()

    decryptedVehicle.id = vehicle.id
    decryptedVehicle.photosId = vehicle.photosId
    decryptedVehicle.backingImages .addAll(vehicle.backingImages)

    decryptedVehicle.selectionType = vehicle.selectionType.encryptString()
    decryptedVehicle.vehicleName = vehicle.vehicleName.encryptString()
    decryptedVehicle.vinNumber = vehicle.vinNumber.encryptString()
    decryptedVehicle.make = vehicle.make.encryptString()
    decryptedVehicle.model = vehicle.model.encryptString()
    decryptedVehicle.modelYear = vehicle.modelYear.encryptString()
    decryptedVehicle.color = vehicle.color.encryptString()
    decryptedVehicle.titleName = vehicle.titleName.encryptString()
    decryptedVehicle.estimatedMarketValue = vehicle.estimatedMarketValue.encryptString()
    decryptedVehicle.registrationExpirydate = vehicle.registrationExpirydate.encryptString()
    decryptedVehicle.purchasedOrLeased = vehicle.purchasedOrLeased.encryptString()
    decryptedVehicle.financedThroughLoan = vehicle.financedThroughLoan.encryptString()
    decryptedVehicle.created = vehicle.created
    decryptedVehicle.modified = vehicle.modified
    // decryptedVehicle.isPrivate = vehicle.isPrivate
    decryptedVehicle.createdUser = vehicle.createdUser
    decryptedVehicle.leaseStartDate = vehicle.leaseStartDate
    decryptedVehicle.leaseEndDate = vehicle.leaseEndDate
    decryptedVehicle.contacts = vehicle.contacts.encryptString()
    decryptedVehicle.maintenanceEvent = vehicle.maintenanceEvent.encryptString()
    decryptedVehicle.serviceProviderName = vehicle.serviceProviderName.encryptString()
    decryptedVehicle.dateOfService = vehicle.dateOfService
    decryptedVehicle.vehicle = vehicle.vehicle.encryptString()
    decryptedVehicle.notes = vehicle.notes.encryptString()
    decryptedVehicle.attachmentNames = vehicle.attachmentNames

    AppLogger.d("Encrypt", "decryptedVehicle : " + decryptedVehicle)
    return decryptedVehicle
}

fun encryptAsset(asset: DecryptedAsset): Asset {
    val decryptedAsset = Asset()

    decryptedAsset.id = asset.id
    decryptedAsset.photosId = asset.photosId
    decryptedAsset.backingImages .addAll(asset.backingImages)

    decryptedAsset.selectionType = asset.selectionType.encryptString()
    decryptedAsset.test = asset.test.encryptString()
    decryptedAsset.assetName = asset.assetName.encryptString()
    decryptedAsset.descriptionOrLocation = asset.descriptionOrLocation.encryptString()
    decryptedAsset.estimatedMarketValue = asset.estimatedMarketValue.encryptString()
    decryptedAsset.serialNumber = asset.serialNumber.encryptString()
    decryptedAsset.purchaseDate = asset.purchaseDate.encryptString()
    decryptedAsset.purchasePrice = asset.purchasePrice.encryptString()
    decryptedAsset.contacts = asset.contacts.encryptString()
    decryptedAsset.created = asset.created
    decryptedAsset.modified = asset.modified
    //decryptedAsset.isPrivate = asset.isPrivate
    decryptedAsset.createdUser = asset.createdUser
    decryptedAsset.notes = asset.notes
    decryptedAsset.imageName = asset.imageName
    decryptedAsset.attachmentNames = asset.attachmentNames

    AppLogger.d("Encrypt", "decryptedAsset : " + decryptedAsset)

    return decryptedAsset
}

fun encryptInsurance(insurance: DecryptedInsurance): Insurance {
    val decryptedInsurance = Insurance()

    decryptedInsurance.id = insurance.id
    decryptedInsurance.photosId = insurance.photosId
    decryptedInsurance.backingImages .addAll(insurance.backingImages)

    decryptedInsurance.selectionType = insurance.selectionType.encryptString()
    decryptedInsurance.insuranceCompany = insurance.insuranceCompany.encryptString()
    decryptedInsurance.insuredProperty = insurance.insuredProperty.encryptString()
    decryptedInsurance.insuredVehicle = insurance.insuredVehicle.encryptString()
    decryptedInsurance.insuredPerson = insurance.insuredPerson.encryptString()
    decryptedInsurance.policyNumber = insurance.policyNumber.encryptString()
    decryptedInsurance.policyEffectiveDate = insurance.policyEffectiveDate.encryptString()
    decryptedInsurance.policyExpirationDate = insurance.policyExpirationDate.encryptString()
    decryptedInsurance.contacts = insurance.contacts.encryptString()
    decryptedInsurance.website = insurance.website.encryptString()
    decryptedInsurance.userName = insurance.userName.encryptString()
    decryptedInsurance.password = insurance.password.encryptString()
    decryptedInsurance.pin = insurance.pin.encryptString()
    decryptedInsurance.created = insurance.created
    decryptedInsurance.modified = insurance.modified
    //decryptedInsurance.isPrivate = insurance.isPrivate
    decryptedInsurance.createdUser = insurance.createdUser
    decryptedInsurance.notes = insurance.notes
    decryptedInsurance.attachmentNames = insurance.attachmentNames

    AppLogger.d("Encrypt", "decryptedInsurance : " + decryptedInsurance)
    return decryptedInsurance
}

fun encryptTaxes(taxes: DecryptedTax): Taxes {
    val decryptedTax = Taxes()

    decryptedTax.id = taxes.id
    decryptedTax.photosId = taxes.photosId
    decryptedTax.backingImages .addAll(taxes.backingImages)

    decryptedTax.selectionType = taxes.selectionType.encryptString()
    decryptedTax.returnName = taxes.returnName.encryptString()
    decryptedTax.taxYear = taxes.taxYear.encryptString()
    decryptedTax.taxPayer = taxes.taxPayer.encryptString()
    decryptedTax.contacts = taxes.contacts.encryptString()
    decryptedTax.imageName = taxes.imageName.encryptString()
    decryptedTax.attachmentNames = taxes.attachmentNames
    decryptedTax.notes = taxes.notes.encryptString()
    decryptedTax.title = taxes.title.encryptString()
    decryptedTax.created = taxes.created
    decryptedTax.modified = taxes.modified
    // decryptedTax.isPrivate = taxes.isPrivate
    decryptedTax.createdUser = taxes.createdUser
    AppLogger.d("Encrypt", "decryptedTax : " + decryptedTax)
    return decryptedTax
}

fun encryptHomeList(homeList: DecryptedHomeList): HomeList {
    val decryptedHomeList = HomeList()

    decryptedHomeList.id = homeList.id

    decryptedHomeList.selectionType = homeList.selectionType.encryptString()
    decryptedHomeList.listName = homeList.listName.encryptString()
    decryptedHomeList.dueDate = homeList.dueDate.encryptString()
    decryptedHomeList.detailsId = homeList.detailsId
    decryptedHomeList.selected = homeList.selected
    decryptedHomeList.selectedDate = homeList.selectedDate
    decryptedHomeList.createdDate = homeList.createdDate
    decryptedHomeList.created = homeList.created
    decryptedHomeList.modified = homeList.modified
    decryptedHomeList.private = homeList.private
    decryptedHomeList.createdUser = homeList.createdUser

    AppLogger.d("Encrypt", "decryptedHomeList : " + decryptedHomeList)
    return decryptedHomeList
}

fun encryptEducation(education: DecryptedEducation): Education {
    val decryptedEducation = Education()
    decryptedEducation.id = education.id

    decryptedEducation.selectionType = education.selectionType.encryptString()
    decryptedEducation.institutionName = education.institutionName.encryptString()
    decryptedEducation.accountName = education.accountName.encryptString()
    decryptedEducation.accountType = education.accountType.encryptString()
    decryptedEducation.nameOnAccount = education.nameOnAccount.encryptString()
    decryptedEducation.accountNumber = education.accountNumber.encryptString()
    decryptedEducation.location = education.location.encryptString()
    decryptedEducation.swiftCode = education.swiftCode.encryptString()
    decryptedEducation.abaRoutingNumber = education.abaRoutingNumber.encryptString()
    decryptedEducation.contacts = education.contacts.encryptString()
    decryptedEducation.website = education.website.encryptString()
    decryptedEducation.userName = education.userName.encryptString()
    decryptedEducation.password = education.password.encryptString()
    decryptedEducation.pin = education.pin.encryptString()
    decryptedEducation.paymentMethodOnFile = education.paymentMethodOnFile.encryptString()
    decryptedEducation.notes = education.notes.encryptString()
    decryptedEducation.attachmentNames = education.attachmentNames
    decryptedEducation.title = education.title.encryptString()
    decryptedEducation.created = education.created
    decryptedEducation.createdUser = education.createdUser
    decryptedEducation.modified = education.modified
    decryptedEducation.private = education.private
    decryptedEducation.backingImages .addAll(education.backingImages)
    decryptedEducation.photosId = education.photosId

    AppLogger.d("Encrypt", "decryptedEducation : " + decryptedEducation)
    return decryptedEducation
}

fun encryptMainEducation(mainEducation: DecryptedMainEducation): MainEducation {
    val decryptedMainEducation = MainEducation()
    decryptedMainEducation.id = mainEducation.id
    decryptedMainEducation.selectionType = mainEducation.selectionType.encryptString()
    decryptedMainEducation.classType = mainEducation.classType
    decryptedMainEducation.institutionName = mainEducation.institutionName.encryptString()
    decryptedMainEducation.qualification = mainEducation.qualification.encryptString()
    decryptedMainEducation.name = mainEducation.name.encryptString()
    decryptedMainEducation.location = mainEducation.location.encryptString()
    decryptedMainEducation.major = mainEducation.major.encryptString()
    decryptedMainEducation.from = mainEducation.from.encryptString()
    decryptedMainEducation.to = mainEducation.to.encryptString()
    decryptedMainEducation.currentlyStudying = mainEducation.currentlyStudying.encryptString()
    decryptedMainEducation.notes = mainEducation.notes.encryptString()
    decryptedMainEducation.created = mainEducation.created
    decryptedMainEducation.modified = mainEducation.modified
    decryptedMainEducation.private = mainEducation.private
    decryptedMainEducation.attachmentNames = mainEducation.attachmentNames
    decryptedMainEducation.createdUser = mainEducation.createdUser
    decryptedMainEducation.backingImages .addAll(mainEducation.backingImages)
    AppLogger.d("Encrypt", "decryptedMainEducation : " + decryptedMainEducation)
    return decryptedMainEducation
}

fun encryptWork(work: DecryptedWork): Work {
    val decryptedWork = Work()
    decryptedWork.id = work.id
    decryptedWork.selectionType = work.selectionType.encryptString()
    decryptedWork.classType = work.classType
    decryptedWork.companyName = work.companyName.encryptString()
    decryptedWork.position = work.position.encryptString()
    decryptedWork.name = work.name.encryptString()
    decryptedWork.location = work.location.encryptString()
    decryptedWork.from = work.from.encryptString()
    decryptedWork.to = work.to.encryptString()
    decryptedWork.currentWork = work.currentWork.encryptString()
    decryptedWork.current = work.current
    decryptedWork.created = work.created
    decryptedWork.modified = work.modified
    decryptedWork.private = work.private
    decryptedWork.notes = work.notes.encryptString()
    decryptedWork.attachmentNames = work.attachmentNames
    decryptedWork.createdUser = work.createdUser
    decryptedWork.backingImages .addAll(work.backingImages)

    AppLogger.d("Encrypt", "decryptedWork : " + decryptedWork)
    return decryptedWork
}


// For Contacts
fun encryptContact(contactsList: DecryptedContacts): Contacts {
    val decryptedContacts = Contacts()

    decryptedContacts.id = contactsList.id
    decryptedContacts.selectionType = contactsList.selectionType.encryptString()
    decryptedContacts.firstName = contactsList.firstName.encryptString()
    decryptedContacts.lastName = contactsList.lastName.encryptString()
    decryptedContacts.dateOfBirth = contactsList.dateOfBirth.encryptString()
    decryptedContacts.anniversary = contactsList.anniversary.encryptString()
    decryptedContacts.mobileOne = contactsList.mobileOne.encryptString()
    decryptedContacts.mobileTwo = contactsList.mobileTwo.encryptString()
    decryptedContacts.emailOne = contactsList.emailOne.encryptString()
    decryptedContacts.emailTwo = contactsList.emailTwo.encryptString()
    decryptedContacts.streetAddressOne = contactsList.streetAddressOne.encryptString()
    decryptedContacts.streetAddressTwo = contactsList.streetAddressTwo.encryptString()
    decryptedContacts.city = contactsList.city.encryptString()
    decryptedContacts.state = contactsList.state.encryptString()
    decryptedContacts.zipCode = contactsList.zipCode.encryptString()
    decryptedContacts.country = contactsList.country.encryptString()
    decryptedContacts.created = contactsList.created
    decryptedContacts.modified = contactsList.modified
    decryptedContacts.createdUser = contactsList.createdUser
    decryptedContacts.private = contactsList.private
    decryptedContacts.backingImages .addAll(contactsList.backingImages)

    AppLogger.d("Encrypt", "decryptedContacts : " + decryptedContacts)
    return decryptedContacts
}

fun encryptRealmString(members: RealmList<DecryptedRealmString>): RealmList<RealmString>? {
    val decryptedMembers = RealmList<RealmString>()
    for (i in 0 until members.size) {
        val member = members[i]
        val decryptedMember = encryptRealmString(member!!)
        decryptedMembers.add(decryptedMember)
    }
    return decryptedMembers
}

fun encryptRealmString(realmString: DecryptedRealmString): RealmString? {
    val decryptedRealmString = RealmString()

    decryptedRealmString.stringValue = realmString.stringValue.encryptString()

    return decryptedRealmString
}

// Going to make Decrypted Methods for every Decrypted Model Class
fun encryptAddress(address: DecryptedAddress): Address {
    val decryptAddress = Address()
    decryptAddress.street_1 = address.street_1.encryptString()
    decryptAddress.street_2 = address.street_2.encryptString()
    decryptAddress.city = address.city.encryptString()
    decryptAddress.state = address.state.encryptString()
    decryptAddress.zipCode = address.zipCode.encryptString()
    decryptAddress.country = address.country.encryptString()
    return decryptAddress
}

fun encryptCalendarEvents(calendarEvents: DecryptedCalendarEvents): CalendarEvents {
    val decryptedCalendarEvents = CalendarEvents()
    decryptedCalendarEvents.id = calendarEvents.id
    decryptedCalendarEvents.eventID = calendarEvents.eventID
    decryptedCalendarEvents.classType = calendarEvents.classType
    decryptedCalendarEvents.title = calendarEvents.title
    decryptedCalendarEvents.location = calendarEvents.location
    decryptedCalendarEvents.isAllDay = calendarEvents.isAllDay
    decryptedCalendarEvents.notes = calendarEvents.notes
    decryptedCalendarEvents.startsDate = calendarEvents.startsDate
    decryptedCalendarEvents.endsDate = calendarEvents.endsDate
    decryptedCalendarEvents.repeats = calendarEvents.repeats
    decryptedCalendarEvents.endRepeat = calendarEvents.endRepeat
    decryptedCalendarEvents.reminder = calendarEvents.reminder
    decryptedCalendarEvents.travelTime = calendarEvents.travelTime
    decryptedCalendarEvents.alert = calendarEvents.alert
    decryptedCalendarEvents.showAs = calendarEvents.showAs
    decryptedCalendarEvents.url = calendarEvents.url
    decryptedCalendarEvents.isReminderSet = calendarEvents.isReminderSet
    decryptedCalendarEvents.attachmentNames = calendarEvents.attachmentNames
    decryptedCalendarEvents.backingImages .addAll(calendarEvents.backingImages)
    decryptedCalendarEvents.photosId = calendarEvents.photosId

    return decryptedCalendarEvents
}

fun encryptCertificate(certificate: DecryptedCertificate): Certificate {
    val decryptCertificate = Certificate()
    decryptCertificate.id = certificate.id
    decryptCertificate.selectionType = certificate.selectionType.encryptString()
    decryptCertificate.cer_description = certificate.cer_description.encryptString()
    decryptCertificate.nameOnCertificate = certificate.nameOnCertificate.encryptString()
    decryptCertificate.gender = certificate.gender.encryptString()
    decryptCertificate.dateOfBirth = certificate.dateOfBirth.encryptString()
    decryptCertificate.timeOfBirth = certificate.timeOfBirth.encryptString()
    decryptCertificate.placeOfBirth = certificate.placeOfBirth.encryptString()
    decryptCertificate.dateOfMarriage = certificate.dateOfMarriage.encryptString()
    decryptCertificate.placeOfMarriage = certificate.placeOfMarriage.encryptString()
    decryptCertificate.nameOneCertificate = certificate.nameOneCertificate.encryptString()
    decryptCertificate.nameTwoCertificate = certificate.nameTwoCertificate.encryptString()
    decryptCertificate.notes = certificate.notes.encryptString()
    decryptCertificate.created = certificate.created
    decryptCertificate.modified = certificate.modified
    decryptCertificate.private = certificate.private
    decryptCertificate.attachmentNames = certificate.attachmentNames
    decryptCertificate.createdUser = certificate.createdUser
    decryptCertificate.backingImages .addAll(certificate.backingImages)

    return decryptCertificate
}

fun encryptGovernment(government: DecryptedGovernment): Government {
    val decryptGovernment = Government()
    decryptGovernment.id = government.id
    decryptGovernment.selectionType = government.selectionType.encryptString()
    decryptGovernment.idName = government.idName.encryptString()
    decryptGovernment.name = government.name.encryptString()
    decryptGovernment.nameOnId = government.nameOnId.encryptString()
    decryptGovernment.issuingCountry = government.issuingCountry.encryptString()
    decryptGovernment.issuingState = government.issuingState.encryptString()
    decryptGovernment.idNumber = government.idNumber.encryptString()
    decryptGovernment.dateIssued = government.dateIssued.encryptString()
    decryptGovernment.expirationDate = government.expirationDate.encryptString()
    decryptGovernment.created = government.created
    decryptGovernment.modified = government.modified
    decryptGovernment.private = government.private
    decryptGovernment.notes = government.notes.encryptString()
    decryptGovernment.attachmentNames = government.attachmentNames
    decryptGovernment.createdUser = government.createdUser
    decryptGovernment.backingImages .addAll(government.backingImages)
    return decryptGovernment
}

fun encryptLicense(license: DecryptedLicense): License {
    val decryptLicense = License()
    decryptLicense.id = license.id
    decryptLicense.selectionType = license.selectionType.encryptString()
    decryptLicense.lic_description = license.lic_description.encryptString()
    decryptLicense.nameOnLicense = license.nameOnLicense.encryptString()
    decryptLicense.issuingCountry = license.issuingCountry.encryptString()
    decryptLicense.issuingState = license.issuingState.encryptString()
    decryptLicense.licenseNumber = license.licenseNumber.encryptString()
    decryptLicense.dateIssued = license.dateIssued.encryptString()
    decryptLicense.expirationDate = license.expirationDate.encryptString()
    decryptLicense.notes = license.notes.encryptString()
    decryptLicense.created = license.created
    decryptLicense.modified = license.modified
    decryptLicense.private = license.private
    decryptLicense.attachmentNames = license.attachmentNames
    decryptLicense.createdUser = license.createdUser
    decryptLicense.backingImages .addAll(license.backingImages)
    return decryptLicense
}

fun encryptPersonal(personal: DecryptedPersonal): Personal {
    val decryptPersonal = Personal()
    decryptPersonal.id = personal.id
    decryptPersonal.selectionType = personal.selectionType.encryptString()
    decryptPersonal.institutionName = personal.institutionName.encryptString()
    decryptPersonal.accountName = personal.accountName.encryptString()
    decryptPersonal.accountType = personal.accountType.encryptString()
    decryptPersonal.nameOnAccount = personal.nameOnAccount.encryptString()
    decryptPersonal.accountNumber = personal.accountNumber.encryptString()
    decryptPersonal.location = personal.location.encryptString()
    decryptPersonal.swiftCode = personal.swiftCode.encryptString()
    decryptPersonal.abaRoutingNumber = personal.abaRoutingNumber.encryptString()
    decryptPersonal.contacts = personal.contacts.encryptString()
    decryptPersonal.website = personal.website.encryptString()
    decryptPersonal.userName = personal.userName.encryptString()
    decryptPersonal.password = personal.password.encryptString()
    decryptPersonal.pin = personal.pin.encryptString()
    decryptPersonal.paymentMethodOnFile = personal.paymentMethodOnFile.encryptString()
    decryptPersonal.notes = personal.notes.encryptString()
    decryptPersonal.attachmentNames = personal.attachmentNames
    decryptPersonal.title = personal.title.encryptString()
    decryptPersonal.created = personal.created
    decryptPersonal.modified = personal.modified
    decryptPersonal.createdUser = personal.createdUser
    decryptPersonal.backingImages .addAll(personal.backingImages)
    return decryptPersonal
}

fun encryptSocial(social: DecryptedSocial): Social {
    val decryptSocial = Social()
    decryptSocial.id = social.id
    decryptSocial.selectionType = social.selectionType.encryptString()
    decryptSocial.cardName = social.cardName.encryptString()
    decryptSocial.nameOnCard = social.nameOnCard.encryptString()
    decryptSocial.socialSecurityNumber = social.socialSecurityNumber.encryptString()
    decryptSocial.notes = social.notes.encryptString()
    decryptSocial.created = social.created
    decryptSocial.modified = social.modified
    decryptSocial.private = social.private
    decryptSocial.attachmentNames = social.attachmentNames
    decryptSocial.createdUser = social.createdUser
    decryptSocial.backingImages .addAll(social.backingImages)
    return decryptSocial
}

fun encryptTaxID(taxID: DecryptedTaxID): TaxID {
    val decryptTaxID = TaxID()
    decryptTaxID.id = taxID.id
    decryptTaxID.selectionType = taxID.selectionType.encryptString()
    decryptTaxID.taxIdName = taxID.taxIdName.encryptString()
    decryptTaxID.taxIdNumber = taxID.taxIdNumber.encryptString()
    decryptTaxID.issuingCountry = taxID.issuingCountry.encryptString()
    decryptTaxID.name = taxID.name.encryptString()
    decryptTaxID.notes = taxID.notes.encryptString()
    decryptTaxID.created = taxID.created
    decryptTaxID.modified = taxID.modified
    decryptTaxID.private = taxID.private
    decryptTaxID.attachmentNames = taxID.attachmentNames
    decryptTaxID.backingImages .addAll(taxID.backingImages)
    return decryptTaxID
}

fun encryptPersonalList(personalList: DecryptedPersonalList): PersonalList {
    val decryptPersonalList = PersonalList()
    decryptPersonalList.id = personalList.id
    decryptPersonalList.selectionType = personalList.selectionType.encryptString()
    decryptPersonalList.classType = personalList.classType
    decryptPersonalList.listName = personalList.listName.encryptString()
    decryptPersonalList.dueDate = personalList.dueDate.encryptString()
    decryptPersonalList.detailsId = personalList.detailsId
    decryptPersonalList.selected = personalList.selected
    decryptPersonalList.selectedDate = personalList.selectedDate
    decryptPersonalList.createdDate = personalList.createdDate
    decryptPersonalList.created = personalList.created
    decryptPersonalList.modified = personalList.modified
    decryptPersonalList.createdUser = personalList.createdUser
    decryptPersonalList.private = personalList.private
    return decryptPersonalList
}

fun encryptCheckUps(checkups: DecryptedCheckups): Checkups {
    val decryptCheckUps = Checkups()
    decryptCheckUps.id = checkups.id
    decryptCheckUps.selectionType = checkups.selectionType.encryptString()
    decryptCheckUps.classType = checkups.classType
    decryptCheckUps.physicianName = checkups.physicianName.encryptString()
    decryptCheckUps.checkup_description = checkups.checkup_description.encryptString()
    decryptCheckUps.physicianType = checkups.physicianType.encryptString()
    decryptCheckUps.reason = checkups.reason.encryptString()
    decryptCheckUps.dateOfVisit = checkups.dateOfVisit.encryptString()
    decryptCheckUps.notes = checkups.notes.encryptString()
    decryptCheckUps.attachmentNames = checkups.attachmentNames
    decryptCheckUps.created = checkups.created
    decryptCheckUps.modified = checkups.modified
    decryptCheckUps.createdUser = checkups.createdUser
    decryptCheckUps.private = checkups.private
    decryptCheckUps.backingImages .addAll(checkups.backingImages)
    decryptCheckUps.photosId = checkups.photosId
    return decryptCheckUps
}

fun encryptEmergencyContacts(emergencyContacts: DecryptedEmergencyContacts): EmergencyContacts {
    val decryptEmergencyContacts = EmergencyContacts()
    decryptEmergencyContacts.id = emergencyContacts.id
    decryptEmergencyContacts.selectionType = emergencyContacts.selectionType.encryptString()
    decryptEmergencyContacts.classType = emergencyContacts.classType
    decryptEmergencyContacts.name = emergencyContacts.name.encryptString()
    decryptEmergencyContacts.relationShip = emergencyContacts.relationShip.encryptString()
    decryptEmergencyContacts.phoneNumberOne = emergencyContacts.phoneNumberOne.encryptString()
    decryptEmergencyContacts.phoneNumberTwo = emergencyContacts.phoneNumberTwo.encryptString()
    decryptEmergencyContacts.emailAddress = emergencyContacts.emailAddress.encryptString()
    decryptEmergencyContacts.streetAddressOne = emergencyContacts.streetAddressOne.encryptString()
    decryptEmergencyContacts.streetAddressTwo = emergencyContacts.streetAddressTwo.encryptString()
    decryptEmergencyContacts.city = emergencyContacts.city.encryptString()
    decryptEmergencyContacts.state = emergencyContacts.state.encryptString()
    decryptEmergencyContacts.zipCode = emergencyContacts.zipCode.encryptString()
    decryptEmergencyContacts.country = emergencyContacts.country.encryptString()
    decryptEmergencyContacts.created = emergencyContacts.created
    decryptEmergencyContacts.modified = emergencyContacts.modified
    decryptEmergencyContacts.notes = emergencyContacts.notes.encryptString()
    decryptEmergencyContacts.attachmentNames = emergencyContacts.attachmentNames
    decryptEmergencyContacts.createdUser = emergencyContacts.createdUser
    decryptEmergencyContacts.private = emergencyContacts.private
    decryptEmergencyContacts.backingImages .addAll(emergencyContacts.backingImages)
    return decryptEmergencyContacts
}

fun encryptEyeGlassPrescriptions(eyeglassPrescriptions: DecryptedEyeglassPrescriptions): EyeglassPrescriptions {
    val decryptEyeGlassPrescriptions = EyeglassPrescriptions()
    decryptEyeGlassPrescriptions.id = eyeglassPrescriptions.id
    decryptEyeGlassPrescriptions.selectionType = eyeglassPrescriptions.selectionType.encryptString()
    decryptEyeGlassPrescriptions.classType = eyeglassPrescriptions.classType
    decryptEyeGlassPrescriptions.physicianName = eyeglassPrescriptions.physicianName.encryptString()
    decryptEyeGlassPrescriptions.datePrescribed = eyeglassPrescriptions.datePrescribed.encryptString()
    decryptEyeGlassPrescriptions.odSphereValue = eyeglassPrescriptions.odSphereValue.encryptString()
    decryptEyeGlassPrescriptions.osSphereValue = eyeglassPrescriptions.osSphereValue.encryptString()
    decryptEyeGlassPrescriptions.odCylinderValue = eyeglassPrescriptions.odCylinderValue.encryptString()
    decryptEyeGlassPrescriptions.osCylinderValue = eyeglassPrescriptions.osCylinderValue.encryptString()
    decryptEyeGlassPrescriptions.odAxisValue = eyeglassPrescriptions.odAxisValue.encryptString()
    decryptEyeGlassPrescriptions.osAxisValue = eyeglassPrescriptions.osAxisValue.encryptString()
    decryptEyeGlassPrescriptions.odPrismValue = eyeglassPrescriptions.odPrismValue.encryptString()
    decryptEyeGlassPrescriptions.osPrismValue = eyeglassPrescriptions.osPrismValue.encryptString()
    decryptEyeGlassPrescriptions.odAddValue = eyeglassPrescriptions.odAddValue.encryptString()
    decryptEyeGlassPrescriptions.osAddValue = eyeglassPrescriptions.osAddValue.encryptString()
    decryptEyeGlassPrescriptions.odBaseValue = eyeglassPrescriptions.odBaseValue.encryptString()
    decryptEyeGlassPrescriptions.osBaseValue = eyeglassPrescriptions.osBaseValue.encryptString()
    decryptEyeGlassPrescriptions.notes = eyeglassPrescriptions.notes.encryptString()
    decryptEyeGlassPrescriptions.attachmentNames = eyeglassPrescriptions.attachmentNames
    decryptEyeGlassPrescriptions.created = eyeglassPrescriptions.created
    decryptEyeGlassPrescriptions.modified = eyeglassPrescriptions.modified
    decryptEyeGlassPrescriptions.createdUser = eyeglassPrescriptions.createdUser
    decryptEyeGlassPrescriptions.private = eyeglassPrescriptions.private
    decryptEyeGlassPrescriptions.backingImages .addAll(eyeglassPrescriptions.backingImages)
    return decryptEyeGlassPrescriptions
}

fun encryptHealthCareProviders(healthcareProviders: DecryptedHealthcareProviders): HealthcareProviders {
    val decryptHealthCareProviders = HealthcareProviders()
    decryptHealthCareProviders.id = healthcareProviders.id
    decryptHealthCareProviders.selectionType = healthcareProviders.selectionType.encryptString()
    decryptHealthCareProviders.classType = healthcareProviders.classType
    decryptHealthCareProviders.name = healthcareProviders.name.encryptString()
    decryptHealthCareProviders.physicianType = healthcareProviders.physicianType.encryptString()
    decryptHealthCareProviders.practiceName = healthcareProviders.practiceName.encryptString()
    decryptHealthCareProviders.phoneNumberOne = healthcareProviders.phoneNumberOne.encryptString()
    decryptHealthCareProviders.phoneNumberTwo = healthcareProviders.phoneNumberTwo.encryptString()
    decryptHealthCareProviders.emailAddress = healthcareProviders.emailAddress.encryptString()
    decryptHealthCareProviders.streetAddressOne = healthcareProviders.streetAddressOne.encryptString()
    decryptHealthCareProviders.streetAddressTwo = healthcareProviders.streetAddressTwo.encryptString()
    decryptHealthCareProviders.city = healthcareProviders.city.encryptString()
    decryptHealthCareProviders.state = healthcareProviders.state.encryptString()
    decryptHealthCareProviders.zipCode = healthcareProviders.zipCode.encryptString()
    decryptHealthCareProviders.country = healthcareProviders.country.encryptString()
    decryptHealthCareProviders.created = healthcareProviders.created
    decryptHealthCareProviders.modified = healthcareProviders.modified
    decryptHealthCareProviders.notes = healthcareProviders.notes.encryptString()
    decryptHealthCareProviders.attachmentNames = healthcareProviders.attachmentNames
    decryptHealthCareProviders.createdUser = healthcareProviders.createdUser
    decryptHealthCareProviders.private = healthcareProviders.private
    decryptHealthCareProviders.backingImages .addAll(healthcareProviders.backingImages)
    return decryptHealthCareProviders
}

fun encryptIdentification(identification: DecryptedIdentification): Identification {
    val decryptHomeList = Identification()
    decryptHomeList.id = identification.id
    decryptHomeList.selectionType = identification.selectionType.encryptString()
    decryptHomeList.classType = identification.classType
    decryptHomeList.name = identification.name.encryptString()
    decryptHomeList.gender = identification.gender.encryptString()
    decryptHomeList.dateofBirth = identification.dateofBirth.encryptString()
    decryptHomeList.age = identification.age.encryptString()
    decryptHomeList.height = identification.height.encryptString()
    decryptHomeList.weight = identification.weight.encryptString()
    decryptHomeList.hairColor = identification.hairColor.encryptString()
    decryptHomeList.eyeColor = identification.eyeColor.encryptString()
    decryptHomeList.visibleMarks = identification.visibleMarks.encryptString()
    decryptHomeList.bloodType = identification.bloodType.encryptString()
    decryptHomeList.orgonDonor = identification.orgonDonor.encryptString()
    decryptHomeList.created = identification.created
    decryptHomeList.modified = identification.modified
    decryptHomeList.notes = identification.notes.encryptString()
    decryptHomeList.attachmentNames = identification.attachmentNames
    decryptHomeList.createdUser = identification.createdUser
    decryptHomeList.private = identification.private
    decryptHomeList.backingImages .addAll(identification.backingImages)
    return decryptHomeList
}

fun encryptMedicalConditions(medicalConditions: DecryptedMedicalConditions): MedicalConditions {
    val decryptMedicalConditions = MedicalConditions()
    decryptMedicalConditions.id = medicalConditions.id
    decryptMedicalConditions.selectionType = medicalConditions.selectionType.encryptString()
    decryptMedicalConditions.classType = medicalConditions.classType
    decryptMedicalConditions.condition = medicalConditions.condition.encryptString()
    decryptMedicalConditions.dateDiagnosed = medicalConditions.dateDiagnosed.encryptString()
    decryptMedicalConditions.medi_description = medicalConditions.medi_description.encryptString()
    decryptMedicalConditions.notes = medicalConditions.notes.encryptString()
    decryptMedicalConditions.attachmentNames = medicalConditions.attachmentNames
    decryptMedicalConditions.created = medicalConditions.created
    decryptMedicalConditions.modified = medicalConditions.modified
    decryptMedicalConditions.createdUser = medicalConditions.createdUser
    decryptMedicalConditions.backingImages .addAll(medicalConditions.backingImages)
    decryptMedicalConditions.private = medicalConditions.private
    return decryptMedicalConditions
}

fun encryptMedicalHistory(medicalHistory: DecryptedMedicalHistory): MedicalHistory {
    val decryptMedicalHistory = MedicalHistory()
    decryptMedicalHistory.id = medicalHistory.id
    decryptMedicalHistory.selectionType = medicalHistory.selectionType.encryptString()
    decryptMedicalHistory.classType = medicalHistory.classType
    decryptMedicalHistory.history = medicalHistory.history.encryptString()
    decryptMedicalHistory.treatmentDiscription = medicalHistory.treatmentDiscription.encryptString()
    decryptMedicalHistory.immunizationDiscription = medicalHistory.immunizationDiscription.encryptString()
    decryptMedicalHistory.familyDiscription = medicalHistory.familyDiscription.encryptString()
    decryptMedicalHistory.created = medicalHistory.created
    decryptMedicalHistory.modified = medicalHistory.modified
    decryptMedicalHistory.notes = medicalHistory.notes.encryptString()
    decryptMedicalHistory.attachmentNames = medicalHistory.attachmentNames
    decryptMedicalHistory.createdUser = medicalHistory.createdUser
    decryptMedicalHistory.private = medicalHistory.private
    decryptMedicalHistory.backingImages .addAll(medicalHistory.backingImages)
    return decryptMedicalHistory
}

fun encryptMedications(medications: DecryptedMedications): Medications {
    val decryptMedications = Medications()
    decryptMedications.id = medications.id
    decryptMedications.selectionType = medications.selectionType.encryptString()
    decryptMedications.classType = medications.classType
    decryptMedications.name = medications.name.encryptString()
    decryptMedications.strength = medications.strength.encryptString()
    decryptMedications.frequency = medications.frequency.encryptString()
    decryptMedications.startDate = medications.startDate.encryptString()
    decryptMedications.endDate = medications.endDate.encryptString()
    decryptMedications.notes = medications.notes.encryptString()
    decryptMedications.attachmentNames = medications.attachmentNames
    decryptMedications.created = medications.created
    decryptMedications.modified = medications.modified
    decryptMedications.createdUser = medications.createdUser
    decryptMedications.private = medications.private
    decryptMedications.backingImages .addAll(medications.backingImages)
    return decryptMedications
}

fun encryptVitalNumbers(vitalNumbers: DecryptedVitalNumbers): VitalNumbers {
    val decryptVitalNumbers = VitalNumbers()
    decryptVitalNumbers.id = vitalNumbers.id
    decryptVitalNumbers.selectionType = vitalNumbers.selectionType.encryptString()
    decryptVitalNumbers.classType = vitalNumbers.classType
    decryptVitalNumbers.vital_description = vitalNumbers.vital_description.encryptString()
    decryptVitalNumbers.measurementDate = vitalNumbers.measurementDate.encryptString()
    decryptVitalNumbers.height = vitalNumbers.height.encryptString()
    decryptVitalNumbers.weight = vitalNumbers.weight.encryptString()
    decryptVitalNumbers.waist = vitalNumbers.waist.encryptString()
    decryptVitalNumbers.bodyFat = vitalNumbers.bodyFat.encryptString()
    decryptVitalNumbers.bodyMassIndex = vitalNumbers.bodyMassIndex.encryptString()
    decryptVitalNumbers.bloodPressure = vitalNumbers.bloodPressure.encryptString()
    decryptVitalNumbers.heartRate = vitalNumbers.heartRate.encryptString()
    decryptVitalNumbers.totalCholesterol = vitalNumbers.totalCholesterol.encryptString()
    decryptVitalNumbers.hdlCholesterol = vitalNumbers.hdlCholesterol.encryptString()
    decryptVitalNumbers.ldlCholesterol = vitalNumbers.ldlCholesterol.encryptString()
    decryptVitalNumbers.cholesterolRatio = vitalNumbers.cholesterolRatio.encryptString()
    decryptVitalNumbers.triglycerides = vitalNumbers.triglycerides.encryptString()
    decryptVitalNumbers.bloodGlucose = vitalNumbers.bloodGlucose.encryptString()
    decryptVitalNumbers.hemoglobin = vitalNumbers.hemoglobin.encryptString()
    decryptVitalNumbers.created = vitalNumbers.created
    decryptVitalNumbers.modified = vitalNumbers.modified
    decryptVitalNumbers.notes = vitalNumbers.notes.encryptString()
    decryptVitalNumbers.attachmentNames = vitalNumbers.attachmentNames
    decryptVitalNumbers.createdUser = vitalNumbers.createdUser
    decryptVitalNumbers.private = vitalNumbers.private
    decryptVitalNumbers.backingImages .addAll(vitalNumbers.backingImages)
    return decryptVitalNumbers
}

fun encryptWellness(wellness: DecryptedWellness): Wellness {
    val decryptWellness = Wellness()
    decryptWellness.id = wellness.id
    decryptWellness.selectionType = wellness.selectionType.encryptString()
    decryptWellness.institutionName = wellness.institutionName.encryptString()
    decryptWellness.accountName = wellness.accountName.encryptString()
    decryptWellness.accountType = wellness.accountType.encryptString()
    decryptWellness.nameOnAccount = wellness.nameOnAccount.encryptString()
    decryptWellness.accountNumber = wellness.accountNumber.encryptString()
    decryptWellness.location = wellness.location.encryptString()
    decryptWellness.swiftCode = wellness.swiftCode.encryptString()
    decryptWellness.abaRoutingNumber = wellness.abaRoutingNumber.encryptString()
    decryptWellness.contacts = wellness.contacts.encryptString()
    decryptWellness.website = wellness.website.encryptString()
    decryptWellness.userName = wellness.userName.encryptString()
    decryptWellness.password = wellness.password.encryptString()
    decryptWellness.pin = wellness.pin.encryptString()
    decryptWellness.paymentMethodOnFile = wellness.paymentMethodOnFile.encryptString()
    decryptWellness.notes = wellness.notes.encryptString()
    decryptWellness.attachmentNames = wellness.attachmentNames
    decryptWellness.title = wellness.title.encryptString()
    decryptWellness.created = wellness.created
    decryptWellness.modified = wellness.modified
    decryptWellness.createdUser = wellness.createdUser
    decryptWellness.private = wellness.private
    decryptWellness.backingImages .addAll(wellness.backingImages)
    return decryptWellness
}

fun encryptWellnessList(wellnessList: DecryptedWellnessList): WellnessList {
    val decryptWellnessList = WellnessList()
    decryptWellnessList.id = wellnessList.id
    decryptWellnessList.selectionType = wellnessList.selectionType.encryptString()
    decryptWellnessList.classType = wellnessList.classType
    decryptWellnessList.listName = wellnessList.listName.encryptString()
    decryptWellnessList.dueDate = wellnessList.dueDate.encryptString()
    decryptWellnessList.created = wellnessList.created
    decryptWellnessList.modified = wellnessList.modified
    decryptWellnessList.createdUser = wellnessList.createdUser
    decryptWellnessList.private = wellnessList.private
    return decryptWellnessList
}

fun encryptedClothingSizes(clothingSizes: DecryptedClothingSizes): ClothingSizes {
    val decryptClothingSizes = ClothingSizes()
    decryptClothingSizes.id = clothingSizes.id
    decryptClothingSizes.selectionType = clothingSizes.selectionType.encryptString()
    decryptClothingSizes.classType = clothingSizes.classType
    decryptClothingSizes.personName = clothingSizes.personName.encryptString()
    decryptClothingSizes.sizeName = clothingSizes.sizeName.encryptString()
    decryptClothingSizes.sizeCategory = clothingSizes.sizeCategory.encryptString()
    decryptClothingSizes.topsSize = clothingSizes.topsSize.encryptString()
    decryptClothingSizes.topsNumericSize = clothingSizes.topsNumericSize.encryptString()
    decryptClothingSizes.bottomsSize = clothingSizes.bottomsSize.encryptString()
    decryptClothingSizes.bottomsNumericSize = clothingSizes.bottomsNumericSize.encryptString()
    decryptClothingSizes.dressesSize = clothingSizes.dressesSize.encryptString()
    decryptClothingSizes.dressesNumericSize = clothingSizes.dressesNumericSize.encryptString()
    decryptClothingSizes.outWearSize = clothingSizes.outWearSize.encryptString()
    decryptClothingSizes.outWearNumericSize = clothingSizes.outWearNumericSize.encryptString()
    decryptClothingSizes.swimWearSize = clothingSizes.swimWearSize.encryptString()
    decryptClothingSizes.swimWearNumericSize = clothingSizes.swimWearNumericSize.encryptString()
    decryptClothingSizes.swimWearBraBandCupSize = clothingSizes.swimWearBraBandCupSize.encryptString()
    decryptClothingSizes.shoeSize = clothingSizes.shoeSize.encryptString()
    decryptClothingSizes.shoeWidth = clothingSizes.shoeWidth.encryptString()
    decryptClothingSizes.hats = clothingSizes.hats.encryptString()
    decryptClothingSizes.gloves = clothingSizes.gloves.encryptString()
    decryptClothingSizes.tights = clothingSizes.tights.encryptString()
    decryptClothingSizes.bust = clothingSizes.bust.encryptString()
    decryptClothingSizes.waist = clothingSizes.waist.encryptString()
    decryptClothingSizes.hips = clothingSizes.hips.encryptString()
    decryptClothingSizes.armLength = clothingSizes.armLength.encryptString()
    decryptClothingSizes.inseam = clothingSizes.inseam.encryptString()
    decryptClothingSizes.torso = clothingSizes.torso.encryptString()
    decryptClothingSizes.jacketsSize = clothingSizes.jacketsSize.encryptString()
    decryptClothingSizes.jacketsNumericSize = clothingSizes.jacketsNumericSize.encryptString()
    decryptClothingSizes.pantsSize = clothingSizes.pantsSize.encryptString()
    decryptClothingSizes.pantsNumericSize = clothingSizes.pantsNumericSize.encryptString()
    decryptClothingSizes.toddlerSize = clothingSizes.toddlerSize.encryptString()
    decryptClothingSizes.kidSize = clothingSizes.kidSize.encryptString()
    decryptClothingSizes.neck = clothingSizes.neck.encryptString()
    decryptClothingSizes.chest = clothingSizes.chest.encryptString()
    decryptClothingSizes.clothing = clothingSizes.clothing.encryptString()
    decryptClothingSizes.shoes = clothingSizes.shoes.encryptString()
    decryptClothingSizes.notes = clothingSizes.notes.encryptString()
    decryptClothingSizes.attachmentNames = clothingSizes.attachmentNames
    decryptClothingSizes.beltsNumericSize = clothingSizes.beltsNumericSize.encryptString()
    decryptClothingSizes.socks = clothingSizes.socks.encryptString()
    decryptClothingSizes.seat = clothingSizes.seat.encryptString()
    decryptClothingSizes.beltSize = clothingSizes.beltSize.encryptString()
    decryptClothingSizes.created = clothingSizes.created
    decryptClothingSizes.modified = clothingSizes.modified
    decryptClothingSizes.createdUser = clothingSizes.createdUser
    decryptClothingSizes.private = clothingSizes.private
    decryptClothingSizes.backingImages .addAll(clothingSizes.backingImages)
    return decryptClothingSizes
}

fun encryptContactsList(contactsList: DecryptedContactsList): ContactsList {
    val decryptContactList = ContactsList()
    decryptContactList.id = contactsList.id
    decryptContactList.selectionType = contactsList.selectionType.encryptString()
    decryptContactList.classType = contactsList.classType.encryptString()
    decryptContactList.listName = contactsList.listName
    decryptContactList.dueDate = contactsList.dueDate.encryptString()
    decryptContactList.created = contactsList.created
    decryptContactList.modified = contactsList.modified
    decryptContactList.createdUser = contactsList.createdUser
    decryptContactList.private = contactsList.private
    return decryptContactList
}

fun encryptDevice(device: DecryptedDevice): Device {
    val decryptDevice = Device()
    decryptDevice.deviceId = device.deviceId.encryptString()
    decryptDevice.passcode = device.passcode.encryptString()
    return decryptDevice
}

fun encryptDocuments(documents: DecryptedDocuments): Documents {
    val decryptDocuments = Documents()
    decryptDocuments.id = documents.id
    decryptDocuments.selectionType = documents.selectionType.encryptString()
    decryptDocuments.passportName = documents.passportName.encryptString()
    decryptDocuments.visaName = documents.visaName.encryptString()
    decryptDocuments.nameOnPassport = documents.nameOnPassport.encryptString()
    decryptDocuments.nameOnVisa = documents.nameOnVisa.encryptString()
    decryptDocuments.visaType = documents.visaType.encryptString()
    decryptDocuments.visaNumber = documents.visaNumber.encryptString()
    decryptDocuments.travelDocumentTitle = documents.travelDocumentTitle.encryptString()
    decryptDocuments.nameOnTravelDocument = documents.nameOnTravelDocument.encryptString()
    decryptDocuments.travelDocumentType = documents.travelDocumentType.encryptString()
    decryptDocuments.travelDocumentNumber = documents.travelDocumentNumber.encryptString()
    decryptDocuments.issuingCountry = documents.issuingCountry.encryptString()
    decryptDocuments.passportNumber = documents.passportNumber.encryptString()
    decryptDocuments.placeIssued = documents.placeIssued.encryptString()
    decryptDocuments.dateIssued = documents.dateIssued.encryptString()
    decryptDocuments.expirationDate = documents.expirationDate.encryptString()
    decryptDocuments.notes = documents.notes.encryptString()
    decryptDocuments.attachmentNames = documents.attachmentNames
    decryptDocuments.created = documents.created
    decryptDocuments.modified = documents.modified
    decryptDocuments.createdUser = documents.createdUser
    return decryptDocuments
}

fun encryptEducationList(educationList: DecryptedEducationList): EducationList {
    val decryptEducationList = EducationList()
    decryptEducationList.id = educationList.id
    decryptEducationList.selectionType = educationList.selectionType.encryptString()
    decryptEducationList.classType = educationList.classType
    decryptEducationList.listName = educationList.listName.encryptString()
    decryptEducationList.dueDate = educationList.dueDate.encryptString()
    decryptEducationList.created = educationList.created
    decryptEducationList.modified = educationList.modified
    decryptEducationList.createdUser = educationList.createdUser
    decryptEducationList.private = educationList.private
    return decryptEducationList
}

fun decrypytHash(hash: DecryptedHash): Hash {
    val decryptHash = Hash()
    decryptHash.id = hash.id
    decryptHash.finalPassword = hash.finalPassword.encryptString()
    decryptHash.passcode = hash.passcode.encryptString()
    return decryptHash
}

fun encryptHomeAndBanking(homeBankingList: DecryptedHomeBankingList): HomeBankingList {
    val decryptHomeAndBanking = HomeBankingList()
    decryptHomeAndBanking.id = homeBankingList.id
    decryptHomeAndBanking.selectionType = homeBankingList.selectionType.encryptString()
    decryptHomeAndBanking.listName = homeBankingList.listName.encryptString()
    decryptHomeAndBanking.dueDate = homeBankingList.dueDate.encryptString()
    decryptHomeAndBanking.created = homeBankingList.created
    decryptHomeAndBanking.modified = homeBankingList.modified
    decryptHomeAndBanking.private = homeBankingList.private
    return decryptHomeAndBanking
}

fun encryptInterests(interest: DecryptedInterests): Interests {
    val decryptInterests = Interests()
    decryptInterests.id = interest.id
    decryptInterests.selectionType = interest.selectionType.encryptString()
    decryptInterests.institutionName = interest.institutionName.encryptString()
    decryptInterests.accountName = interest.accountName.encryptString()
    decryptInterests.accountType = interest.accountType.encryptString()
    decryptInterests.nameOnAccount = interest.nameOnAccount.encryptString()
    decryptInterests.accountNumber = interest.accountNumber.encryptString()
    decryptInterests.location = interest.location.encryptString()
    decryptInterests.swiftCode = interest.swiftCode.encryptString()
    decryptInterests.abaRoutingNumber = interest.abaRoutingNumber.encryptString()
    decryptInterests.contacts = interest.contacts.encryptString()
    decryptInterests.website = interest.website.encryptString()
    decryptInterests.userName = interest.userName.encryptString()
    decryptInterests.password = interest.password.encryptString()
    decryptInterests.pin = interest.pin.encryptString()
    decryptInterests.paymentMethodOnFile = interest.paymentMethodOnFile.encryptString()
    decryptInterests.notes = interest.notes.encryptString()
    decryptInterests.attachmentNames = interest.attachmentNames
    decryptInterests.title = interest.title.encryptString()
    decryptInterests.created = interest.created
    decryptInterests.modified = interest.modified
    decryptInterests.createdUser = interest.createdUser
    decryptInterests.private = interest.private
    return decryptInterests
}

fun encryptInterestList(interestsList: DecryptedInterestsList): InterestsList {
    val decryptInterestList = InterestsList()
    decryptInterestList.id = interestsList.id
    decryptInterestList.selectionType = interestsList.selectionType.encryptString()
    decryptInterestList.classType = interestsList.classType
    decryptInterestList.listName = interestsList.listName.encryptString()
    decryptInterestList.dueDate = interestsList.dueDate.encryptString()
    decryptInterestList.created = interestsList.created
    decryptInterestList.modified = interestsList.modified
    decryptInterestList.createdUser = interestsList.createdUser
    decryptInterestList.private = interestsList.private
    return decryptInterestList
}

fun encryptLoyality(loyalty: DecryptedLoyalty): Loyalty {
    val decryptLoyality = Loyalty()
    decryptLoyality.id = loyalty.id
    decryptLoyality.selectionType = loyalty.selectionType.encryptString()
    decryptLoyality.airLine = loyalty.airLine.encryptString()
    decryptLoyality.hotel = loyalty.hotel.encryptString()
    decryptLoyality.carRentalCompany = loyalty.carRentalCompany.encryptString()
    decryptLoyality.cruiseline = loyalty.cruiseline.encryptString()
    decryptLoyality.railway = loyalty.railway.encryptString()
    decryptLoyality.other = loyalty.other.encryptString()
    decryptLoyality.accountName = loyalty.accountName.encryptString()
    decryptLoyality.nameOnAccount = loyalty.nameOnAccount.encryptString()
    decryptLoyality.accountNumber = loyalty.accountNumber.encryptString()
    decryptLoyality.website = loyalty.website.encryptString()
    decryptLoyality.userName = loyalty.userName.encryptString()
    decryptLoyality.password = loyalty.password.encryptString()
    decryptLoyality.pin = loyalty.pin.encryptString()
    decryptLoyality.notes = loyalty.notes.encryptString()
    decryptLoyality.attachmentNames = loyalty.attachmentNames
    decryptLoyality.created = loyalty.created
    decryptLoyality.modified = loyalty.modified
    decryptLoyality.createdUser = loyalty.createdUser
    decryptLoyality.private = loyalty.private
    return decryptLoyality
}

fun encryptLoyaltyPrograms(loyalityPrograms: DecryptedLoyaltyPrograms): LoyaltyPrograms {
    val decryptLoyaltyPrograms = LoyaltyPrograms()
    decryptLoyaltyPrograms.id = loyalityPrograms.id
    decryptLoyaltyPrograms.selectionType = loyalityPrograms.selectionType.encryptString()
    decryptLoyaltyPrograms.brandName = loyalityPrograms.brandName.encryptString()
    decryptLoyaltyPrograms.accountName = loyalityPrograms.accountName.encryptString()
    decryptLoyaltyPrograms.nameOnAccount = loyalityPrograms.nameOnAccount.encryptString()
    decryptLoyaltyPrograms.accountNumber = loyalityPrograms.accountNumber.encryptString()
    decryptLoyaltyPrograms.website = loyalityPrograms.website.encryptString()
    decryptLoyaltyPrograms.userName = loyalityPrograms.userName.encryptString()
    decryptLoyaltyPrograms.password = loyalityPrograms.password.encryptString()
    decryptLoyaltyPrograms.pin = loyalityPrograms.pin.encryptString()
    decryptLoyaltyPrograms.notes = loyalityPrograms.notes.encryptString()
    decryptLoyaltyPrograms.attachmentNames = loyalityPrograms.attachmentNames
    decryptLoyaltyPrograms.created = loyalityPrograms.created
    decryptLoyaltyPrograms.modified = loyalityPrograms.modified
    decryptLoyaltyPrograms.createdUser = loyalityPrograms.createdUser
    decryptLoyaltyPrograms.private = loyalityPrograms.private
    return decryptLoyaltyPrograms
}

fun encryptMainContacts(mainContacts: DecryptedMainContacts): MainContacts {
    val decryptMainContacts = MainContacts()
    decryptMainContacts.id = mainContacts.id
    decryptMainContacts.selectionType = mainContacts.selectionType.encryptString()
    decryptMainContacts.institutionName = mainContacts.institutionName.encryptString()
    decryptMainContacts.accountName = mainContacts.accountName.encryptString()
    decryptMainContacts.accountType = mainContacts.accountType.encryptString()
    decryptMainContacts.nameOnAccount = mainContacts.nameOnAccount.encryptString()
    decryptMainContacts.accountNumber = mainContacts.accountNumber.encryptString()
    decryptMainContacts.location = mainContacts.location.encryptString()
    decryptMainContacts.swiftCode = mainContacts.swiftCode.encryptString()
    decryptMainContacts.abaRoutingNumber = mainContacts.abaRoutingNumber.encryptString()
    decryptMainContacts.contacts = mainContacts.contacts.encryptString()
    decryptMainContacts.website = mainContacts.website.encryptString()
    decryptMainContacts.userName = mainContacts.userName.encryptString()
    decryptMainContacts.password = mainContacts.password.encryptString()
    decryptMainContacts.pin = mainContacts.pin.encryptString()
    decryptMainContacts.paymentMethodOnFile = mainContacts.paymentMethodOnFile.encryptString()
    decryptMainContacts.notes = mainContacts.notes.encryptString()
    decryptMainContacts.attachmentNames = mainContacts.attachmentNames
    decryptMainContacts.title = mainContacts.title.encryptString()
    decryptMainContacts.created = mainContacts.created
    decryptMainContacts.modified = mainContacts.modified
    decryptMainContacts.createdUser = mainContacts.createdUser
    return decryptMainContacts
}

fun decrypytMainEducation(mainEducation: DecryptedMainEducation): MainEducation {
    val decrypytMainEducation = MainEducation()
    decrypytMainEducation.id = mainEducation.id
    decrypytMainEducation.selectionType = mainEducation.selectionType.encryptString()
    decrypytMainEducation.classType = mainEducation.classType
    decrypytMainEducation.institutionName = mainEducation.institutionName.encryptString()
    decrypytMainEducation.qualification = mainEducation.qualification.encryptString()
    decrypytMainEducation.name = mainEducation.name.encryptString()
    decrypytMainEducation.location = mainEducation.location.encryptString()
    decrypytMainEducation.major = mainEducation.major.encryptString()
    decrypytMainEducation.from = mainEducation.from.encryptString()
    decrypytMainEducation.to = mainEducation.to.encryptString()
    decrypytMainEducation.currentlyStudying = mainEducation.currentlyStudying.encryptString()
    decrypytMainEducation.notes = mainEducation.notes.encryptString()
    decrypytMainEducation.created = mainEducation.created
    decrypytMainEducation.modified = mainEducation.modified
    decrypytMainEducation.attachmentNames = mainEducation.attachmentNames
    decrypytMainEducation.createdUser = mainEducation.createdUser
    decrypytMainEducation.private = mainEducation.private
    decrypytMainEducation.backingImages .addAll(mainEducation.backingImages)
    return decrypytMainEducation
}

fun encryptMainMemories(mainMemories: DecryptedMainMemories): MainMemories {
    val decrypytMainMemories = MainMemories()
    decrypytMainMemories.id = mainMemories.id
    decrypytMainMemories.selectionType = mainMemories.selectionType.encryptString()
    decrypytMainMemories.accountName = mainMemories.accountName.encryptString()
    decrypytMainMemories.institutionName = mainMemories.institutionName.encryptString()
    decrypytMainMemories.accountType = mainMemories.accountType.encryptString()
    decrypytMainMemories.nameOnAccount = mainMemories.nameOnAccount.encryptString()
    decrypytMainMemories.location = mainMemories.location.encryptString()
    decrypytMainMemories.accountNumber = mainMemories.accountNumber.encryptString()
    decrypytMainMemories.location = mainMemories.location.encryptString()
    decrypytMainMemories.swiftCode = mainMemories.swiftCode.encryptString()
    decrypytMainMemories.abaRoutingNumber = mainMemories.abaRoutingNumber.encryptString()
    decrypytMainMemories.contacts = mainMemories.contacts.encryptString()
    decrypytMainMemories.website = mainMemories.website.encryptString()
    decrypytMainMemories.userName = mainMemories.userName.encryptString()
    decrypytMainMemories.password = mainMemories.password.encryptString()
    decrypytMainMemories.pin = mainMemories.pin.encryptString()
    decrypytMainMemories.created = mainMemories.created
    decrypytMainMemories.modified = mainMemories.modified
    decrypytMainMemories.notes = mainMemories.notes.encryptString()
    decrypytMainMemories.attachmentNames = mainMemories.attachmentNames
    decrypytMainMemories.title = mainMemories.title.encryptString()
    decrypytMainMemories.createdUser = mainMemories.createdUser

    return decrypytMainMemories
}

fun encryptMemoriesList(memoriesList: DecryptedMemoriesList): MemoriesList {
    val decryptMemoriesList = MemoriesList()
    decryptMemoriesList.id = memoriesList.id
    decryptMemoriesList.selectionType = memoriesList.selectionType.encryptString()
    decryptMemoriesList.classType = memoriesList.classType
    decryptMemoriesList.listName = memoriesList.listName.encryptString()
    decryptMemoriesList.dueDate = memoriesList.dueDate.encryptString()
    decryptMemoriesList.created = memoriesList.created
    decryptMemoriesList.modified = memoriesList.modified
    decryptMemoriesList.createdUser = memoriesList.createdUser
    decryptMemoriesList.private = memoriesList.private
    return decryptMemoriesList
}

fun encryptMemoryTimeLine(memoryTimeline: DecryptedMemoryTimeline): MemoryTimeline {
    var decryptMemoryTimeLine = MemoryTimeline()
     decryptMemoryTimeLine.id = memoryTimeline.id
    decryptMemoryTimeLine.selectionType = memoryTimeline.selectionType.encryptString()
    decryptMemoryTimeLine.title = memoryTimeline.title.encryptString()
    decryptMemoryTimeLine.date = memoryTimeline.date.encryptString()
    decryptMemoryTimeLine.place = memoryTimeline.place.encryptString()
    decryptMemoryTimeLine.contacts = memoryTimeline.contacts.encryptString()
    decryptMemoryTimeLine.notes = memoryTimeline.notes.encryptString()
    decryptMemoryTimeLine.attachmentNames = memoryTimeline.attachmentNames
    decryptMemoryTimeLine.created = memoryTimeline.created
    decryptMemoryTimeLine.modified = memoryTimeline.modified
    decryptMemoryTimeLine.createdUser = memoryTimeline.createdUser
    decryptMemoryTimeLine.private = memoryTimeline.private
    return decryptMemoryTimeLine
}

fun encryptNotifications(notifications: DecryptedNotifications): Notifications {
    val decryptNotifications = Notifications()
    decryptNotifications.id = notifications.id
    decryptNotifications.message = notifications.message.encryptString()
    decryptNotifications.boxName = notifications.boxName.encryptString()
    decryptNotifications.dueDate = notifications.dueDate
    decryptNotifications.subTitle = notifications.subTitle.encryptString()
    decryptNotifications.notifyDate = notifications.notifyDate
    decryptNotifications.read = notifications.read
    decryptNotifications.created = notifications.created
    decryptNotifications.modified = notifications.modified
    return decryptNotifications
}

fun encryptRecentPurchase(recentPurchase: DecryptedRecentPurchase): RecentPurchase {
    val decryptRecentPurchase = RecentPurchase()
    decryptRecentPurchase.id = recentPurchase.id
    decryptRecentPurchase.selectionType = recentPurchase.selectionType.encryptString()
    decryptRecentPurchase.brandName = recentPurchase.brandName.encryptString()
    decryptRecentPurchase.itemName = recentPurchase.itemName.encryptString()
    decryptRecentPurchase.purchasedBy = recentPurchase.purchasedBy.encryptString()
    decryptRecentPurchase.purchasedDate = recentPurchase.purchasedDate.encryptString()
    decryptRecentPurchase.purchasedPrice = recentPurchase.purchasedPrice.encryptString()
    decryptRecentPurchase.notes = recentPurchase.notes.encryptString()
    decryptRecentPurchase.created = recentPurchase.created
    decryptRecentPurchase.modified = recentPurchase.modified
    decryptRecentPurchase.attachmentNames = recentPurchase.attachmentNames
    decryptRecentPurchase.createdUser = recentPurchase.createdUser
    return decryptRecentPurchase
}

fun encryptRecentSearch(recentSearch: DecryptedRecentSearch?): RecentSearch {
    val decryptRecentSearch = RecentSearch()
    decryptRecentSearch.id = recentSearch!!.id
    decryptRecentSearch.listName = recentSearch!!.listName.encryptString()
    decryptRecentSearch.subCategory = recentSearch!!.subCategory.encryptString()
    decryptRecentSearch.mainCategory = recentSearch!!.mainCategory.encryptString()
    decryptRecentSearch.classType = recentSearch!!.classType
    return decryptRecentSearch
}

fun encryptShopping(shopping: DecryptedShopping): Shopping {
    val decryptShopping = Shopping()
    decryptShopping.id = shopping.id
    decryptShopping.selectionType = shopping.selectionType.encryptString()
    decryptShopping.institutionName = shopping.institutionName.encryptString()
    decryptShopping.accountName = shopping.accountName.encryptString()
    decryptShopping.accountType = shopping.accountType.encryptString()
    decryptShopping.nameOnAccount = shopping.nameOnAccount.encryptString()
    decryptShopping.accountNumber = shopping.accountNumber.encryptString()
    decryptShopping.location = shopping.location.encryptString()
    decryptShopping.swiftCode = shopping.swiftCode.encryptString()
    decryptShopping.abaRoutingNumber = shopping.abaRoutingNumber.encryptString()
    decryptShopping.contacts = shopping.contacts.encryptString()
    decryptShopping.website = shopping.website.encryptString()
    decryptShopping.userName = shopping.userName.encryptString()
    decryptShopping.password = shopping.password.encryptString()
    decryptShopping.pin = shopping.pin.encryptString()
    decryptShopping.paymentMethodOnFile = shopping.paymentMethodOnFile.encryptString()
    decryptShopping.notes = shopping.notes.encryptString()
    decryptShopping.imageName = shopping.imageName.encryptString()
    decryptShopping.attachmentNames = shopping.attachmentNames
    decryptShopping.title = shopping.title.encryptString()
    decryptShopping.created = shopping.created
    decryptShopping.modified = shopping.modified

    return decryptShopping
}

fun encryptShoppingList(shoppingList: DecryptedShoppingList): ShoppingList {
    val decryptShoppingList = ShoppingList()
    decryptShoppingList.id = shoppingList.id
    decryptShoppingList.selectionType = shoppingList.selectionType.encryptString()
    decryptShoppingList.classType = shoppingList.classType
    decryptShoppingList.listName = shoppingList.listName.encryptString()
    decryptShoppingList.dueDate = shoppingList.dueDate.encryptString()
    decryptShoppingList.created = shoppingList.created
    decryptShoppingList.modified = shoppingList.modified
    decryptShoppingList.createdUser = shoppingList.createdUser
    decryptShoppingList.private = shoppingList.private
    return decryptShoppingList
}

fun encryptSignUp(signUp: DecryptedSignUp): SignUp {
    val decryptSignUp = SignUp()
    decryptSignUp.id = signUp.id
    decryptSignUp.fullName = signUp.fullName.encryptString()
    decryptSignUp.emailAddress = signUp.emailAddress.encryptString()
    decryptSignUp.password = signUp.password.encryptString()
    decryptSignUp.user_id = signUp.user_id.encryptString()
    return decryptSignUp
}

fun encryptTravel(travel: DecryptedTravel): Travel {
    val decryptTravel = Travel()
    decryptTravel.id = travel.id
    decryptTravel.selectionType = travel.selectionType.encryptString()
    decryptTravel.institutionName = travel.institutionName.encryptString()
    decryptTravel.accountName = travel.accountName.encryptString()
    decryptTravel.accountType = travel.accountType.encryptString()
    decryptTravel.nameOnAccount = travel.nameOnAccount.encryptString()
    decryptTravel.accountNumber = travel.accountNumber.encryptString()
    decryptTravel.location = travel.location.encryptString()
    decryptTravel.swiftCode = travel.swiftCode.encryptString()
    decryptTravel.abaRoutingNumber = travel.abaRoutingNumber.encryptString()
    decryptTravel.contacts = travel.contacts.encryptString()
    decryptTravel.website = travel.website.encryptString()
    decryptTravel.userName = travel.userName.encryptString()
    decryptTravel.password = travel.password.encryptString()
    decryptTravel.pin = travel.pin.encryptString()
    decryptTravel.paymentMethodOnFile = travel.paymentMethodOnFile.encryptString()
    decryptTravel.notes = travel.notes.encryptString()
    decryptTravel.imageName = travel.imageName.encryptString()
    decryptTravel.attachmentNames = travel.attachmentNames
    decryptTravel.title = travel.title.encryptString()
    decryptTravel.created = travel.created
    decryptTravel.modified = travel.modified
    decryptTravel.createdUser = travel.createdUser
    return decryptTravel

}


fun encryptVacations(vacations: DecryptedVacations): Vacations {
    val decryptVacations = Vacations()
    decryptVacations.id = vacations.id
    decryptVacations.selectionType = vacations.selectionType.encryptString()
    decryptVacations.vac_description = vacations.vac_description.encryptString()
    decryptVacations.startDate = vacations.startDate.encryptString()
    decryptVacations.endDate = vacations.endDate.encryptString()
    decryptVacations.placesToVisit_1 = vacations.placesToVisit_1.encryptString()
    decryptVacations.placesToVisit_2 = vacations.placesToVisit_2.encryptString()
    decryptVacations.placesToVisit_3 = vacations.placesToVisit_3.encryptString()
    decryptVacations.notes = vacations.notes.encryptString()
    decryptVacations.attachmentNames = vacations.attachmentNames
    decryptVacations.created = vacations.created
    decryptVacations.modified = vacations.modified
    decryptVacations.createdUser = vacations.createdUser


    return decryptVacations
}


fun encryptHash(hash: DecryptedHash): Hash {
    val encryptedhash = Hash()
    encryptedhash.id = hash.id
    encryptedhash.finalPassword = hash.finalPassword.encryptString()
    encryptedhash.passcode = hash.passcode.encryptString()
    return encryptedhash
}



fun encryptMemories(mainMemories: DecryptedMainMemories): MainMemories {
    
    val encrypteMainMemories = MainMemories()
    encrypteMainMemories.id = mainMemories.id
    encrypteMainMemories.selectionType = mainMemories.selectionType.encryptString()
    encrypteMainMemories.institutionName = mainMemories.institutionName.encryptString()
    encrypteMainMemories.accountName = mainMemories.accountName.encryptString()
    encrypteMainMemories.accountType = mainMemories.accountType.encryptString()
    encrypteMainMemories.nameOnAccount = mainMemories.nameOnAccount.encryptString()
    encrypteMainMemories.accountNumber = mainMemories.accountNumber.encryptString()
    encrypteMainMemories.location = mainMemories.location.encryptString()
    encrypteMainMemories.swiftCode = mainMemories.swiftCode.encryptString()
    encrypteMainMemories.abaRoutingNumber = mainMemories.abaRoutingNumber.encryptString()
    encrypteMainMemories.contacts = mainMemories.contacts.encryptString()
    encrypteMainMemories.website = mainMemories.website.encryptString()
    encrypteMainMemories.userName = mainMemories.userName.encryptString()
    encrypteMainMemories.password = mainMemories.password.encryptString()
    encrypteMainMemories.pin = mainMemories.pin.encryptString()
    encrypteMainMemories.paymentMethodOnFile = mainMemories.paymentMethodOnFile.encryptString()
    encrypteMainMemories.created = mainMemories.created
    encrypteMainMemories.modified = mainMemories.modified
    encrypteMainMemories.notes = mainMemories.notes.encryptString()
    encrypteMainMemories.attachmentNames = mainMemories.attachmentNames
    encrypteMainMemories.title = mainMemories.title.encryptString()
    encrypteMainMemories.createdUser = mainMemories.createdUser

    return encrypteMainMemories
}

fun encryptMemoryTimeLIne(memoryTimeline: DecryptedMemoryTimeline): MemoryTimeline {
    val enMemoryTimeLine = MemoryTimeline()
    enMemoryTimeLine.id = memoryTimeline.id
    enMemoryTimeLine.selectionType = memoryTimeline.selectionType.encryptString()
    enMemoryTimeLine.title = memoryTimeline.title.encryptString()
    enMemoryTimeLine.date = memoryTimeline.date.encryptString()
    enMemoryTimeLine.place = memoryTimeline.place.encryptString()
    enMemoryTimeLine.contacts = memoryTimeline.contacts.encryptString()
    enMemoryTimeLine.notes = memoryTimeline.notes.encryptString()
    enMemoryTimeLine.attachmentNames = memoryTimeline.attachmentNames
    enMemoryTimeLine.created = memoryTimeline.created
    enMemoryTimeLine.modified = memoryTimeline.modified
    enMemoryTimeLine.createdUser = memoryTimeline.createdUser

    return enMemoryTimeLine
}




fun encryptClothingSizes(clothingSizes: DecryptedClothingSizes): ClothingSizes {
    val enClothingSizes = ClothingSizes()
    enClothingSizes.id = clothingSizes.id
    enClothingSizes.selectionType = clothingSizes.selectionType.encryptString()
    enClothingSizes.classType = clothingSizes.classType.encryptString()
    enClothingSizes.personName = clothingSizes.personName.encryptString()
    enClothingSizes.sizeName = clothingSizes.sizeName.encryptString()
    enClothingSizes.sizeCategory = clothingSizes.sizeCategory.encryptString()
    enClothingSizes.topsSize = clothingSizes.topsSize.encryptString()
    enClothingSizes.topsNumericSize = clothingSizes.topsNumericSize.encryptString()
    enClothingSizes.bottomsSize = clothingSizes.bottomsSize.encryptString()
    enClothingSizes.bottomsNumericSize = clothingSizes.bottomsNumericSize.encryptString()
    enClothingSizes.dressesSize = clothingSizes.dressesSize.encryptString()
    enClothingSizes.dressesNumericSize = clothingSizes.dressesNumericSize.encryptString()
    enClothingSizes.outWearSize = clothingSizes.outWearSize.encryptString()
    enClothingSizes.outWearNumericSize = clothingSizes.outWearNumericSize.encryptString()
    enClothingSizes.swimWearSize = clothingSizes.swimWearSize.encryptString()
    enClothingSizes.swimWearNumericSize = clothingSizes.swimWearNumericSize.encryptString()
    enClothingSizes.swimWearBraBandCupSize = clothingSizes.swimWearBraBandCupSize.encryptString()
    enClothingSizes.shoeSize = clothingSizes.shoeSize.encryptString()
    enClothingSizes.shoeWidth = clothingSizes.shoeWidth.encryptString()
    enClothingSizes.hats = clothingSizes.hats.encryptString()
    enClothingSizes.gloves = clothingSizes.gloves.encryptString()
    enClothingSizes.tights = clothingSizes.tights.encryptString()
    enClothingSizes.bust = clothingSizes.bust.encryptString()
    enClothingSizes.waist = clothingSizes.waist.encryptString()
    enClothingSizes.hips = clothingSizes.hips.encryptString()
    enClothingSizes.armLength = clothingSizes.armLength.encryptString()
    enClothingSizes.inseam = clothingSizes.inseam.encryptString()
    enClothingSizes.torso = clothingSizes.torso.encryptString()
    // Mens
    enClothingSizes.jacketsSize = clothingSizes.jacketsSize.encryptString()
    enClothingSizes.jacketsNumericSize = clothingSizes.jacketsNumericSize.encryptString()
    enClothingSizes.pantsSize = clothingSizes.pantsSize.encryptString()
    enClothingSizes.pantsNumericSize = clothingSizes.pantsNumericSize.encryptString()
    enClothingSizes.toddlerSize = clothingSizes.toddlerSize.encryptString()
    enClothingSizes.kidSize = clothingSizes.kidSize.encryptString()
    enClothingSizes.neck = clothingSizes.neck.encryptString()
    enClothingSizes.chest = clothingSizes.chest.encryptString()
    // Baby
    enClothingSizes.clothing = clothingSizes.clothing.encryptString()
    enClothingSizes.shoes = clothingSizes.shoes.encryptString()
    enClothingSizes.notes = clothingSizes.notes.encryptString()
    enClothingSizes.attachmentNames = clothingSizes.attachmentNames
    //  Boys
    enClothingSizes.beltsNumericSize = clothingSizes.beltsNumericSize.encryptString()
    enClothingSizes.socks = clothingSizes.socks.encryptString()
    enClothingSizes.seat = clothingSizes.seat.encryptString()
    enClothingSizes.beltSize = clothingSizes.beltSize.encryptString()
    enClothingSizes.created = clothingSizes.created
    enClothingSizes.modified = clothingSizes.modified
    enClothingSizes.createdUser = clothingSizes.createdUser
    return enClothingSizes
}

fun encryptLoyaltyProgram(loyalityPrograms: DecryptedLoyaltyPrograms): LoyaltyPrograms {
    val enLaoyaltyPrograms = LoyaltyPrograms()
    enLaoyaltyPrograms.id = loyalityPrograms.id
    enLaoyaltyPrograms.selectionType = loyalityPrograms.selectionType.encryptString()
  enLaoyaltyPrograms.brandName = loyalityPrograms.brandName.encryptString()
  enLaoyaltyPrograms.accountName = loyalityPrograms.accountName.encryptString()
  enLaoyaltyPrograms.nameOnAccount = loyalityPrograms.nameOnAccount.encryptString()
  enLaoyaltyPrograms.accountNumber = loyalityPrograms.accountNumber.encryptString()
  enLaoyaltyPrograms.website = loyalityPrograms.website.encryptString()
  enLaoyaltyPrograms.userName = loyalityPrograms.userName.encryptString()
  enLaoyaltyPrograms.password = loyalityPrograms.password.encryptString()
  enLaoyaltyPrograms.pin = loyalityPrograms.pin.encryptString()
  enLaoyaltyPrograms.notes = loyalityPrograms.notes.encryptString()
  enLaoyaltyPrograms.attachmentNames = loyalityPrograms.attachmentNames
  enLaoyaltyPrograms.created = loyalityPrograms.created
  enLaoyaltyPrograms.modified = loyalityPrograms.modified
  enLaoyaltyPrograms.createdUser = loyalityPrograms.createdUser

    return enLaoyaltyPrograms
}



fun encryptLoyalty(loyalty: DecryptedLoyalty): Loyalty {
    val encryptedLoyalty = Loyalty()
    encryptedLoyalty.id = loyalty.id
    encryptedLoyalty.selectionType = loyalty.selectionType.encryptString()
    encryptedLoyalty.airLine = loyalty.airLine.encryptString()
    encryptedLoyalty.hotel = loyalty.hotel.encryptString()
    encryptedLoyalty.carRentalCompany = loyalty.carRentalCompany.encryptString()
    encryptedLoyalty.cruiseline = loyalty.cruiseline.encryptString()
    encryptedLoyalty.railway = loyalty.railway.encryptString()
    encryptedLoyalty.other = loyalty.other.encryptString()
    encryptedLoyalty.accountName = loyalty.accountName.encryptString()
    encryptedLoyalty.nameOnAccount = loyalty.nameOnAccount.encryptString()
    encryptedLoyalty.accountNumber = loyalty.accountNumber.encryptString()
    encryptedLoyalty.website = loyalty.website.encryptString()
    encryptedLoyalty.userName = loyalty.userName.encryptString()
    encryptedLoyalty.password = loyalty.password.encryptString()
    encryptedLoyalty.pin = loyalty.pin.encryptString()
    encryptedLoyalty.notes = loyalty.notes.encryptString()
    encryptedLoyalty.attachmentNames = loyalty.attachmentNames
    encryptedLoyalty.created = loyalty.created
    encryptedLoyalty.modified = loyalty.modified
    encryptedLoyalty.createdUser = loyalty.createdUser
    return encryptedLoyalty
}



fun encryptCombine(combine: DecryptedCombine) : Combine {
    val decryptedCombine = Combine()
    decryptedCombine.id = combine.id
    for ( financialItems in combine.financialItems ) {
        val decryptedItem = encryptFinancial(financialItems)
        decryptedCombine.financialItems.add( decryptedItem )
    }

    for (paymentItems in combine.paymentItems ) {
        val decryptedItem = encryptPayment(paymentItems)
        decryptedCombine.paymentItems.add(decryptedItem)
    }

    for (propertyItems in combine.propertyItems){
        decryptedCombine.propertyItems.add(encryptProperty(propertyItems))
    }

    for (vehicleItems in combine.vehicleItems){
        decryptedCombine.vehicleItems.add(encryptVehicle(vehicleItems))
    }

    for (assetItems in combine.assetItems){
        decryptedCombine.assetItems.add(encryptAsset(assetItems))
    }

    for (insuranceItems in combine.insuranceItems){
        decryptedCombine.insuranceItems.add(encryptInsurance(insuranceItems))
    }

    for (taxesItems in combine.taxesItems){
        decryptedCombine.taxesItems.add(encryptTaxes(taxesItems))
    }

    for (listItems in combine.listItems){
        decryptedCombine.listItems.add(encryptHomeList(listItems))
    }
    return decryptedCombine
}

fun encryptCombineTravel(combineTravel: DecryptedCombineTravel) : CombineTravel {
    val decryptedCombineTravel = CombineTravel()
    decryptedCombineTravel.id = combineTravel.id
    for (documentsItems in combineTravel.documentsItems) {
        decryptedCombineTravel.documentsItems.add(encryptDocuments(documentsItems))
    }
    for (loyaltyItems in combineTravel.loyaltyItems) {
        decryptedCombineTravel.loyaltyItems.add(encryptLoyality(loyaltyItems))
    }
    for (travelItems in combineTravel.travelItems) {
        decryptedCombineTravel.travelItems.add(encryptTravel(travelItems))
    }
    for (vacationItems in combineTravel.vacationsItems) {
        decryptedCombineTravel.vacationsItems.add(encryptVacations(vacationItems))
    }
    for (listItems in combineTravel.listItems) {
        decryptedCombineTravel.listItems.add(encryptTravelList(listItems))
    }
    return decryptedCombineTravel
}

fun encryptTravelList(travelList: DecryptedTravelList): TravelList {
    val enTravelList = TravelList()
  enTravelList.selectionType = travelList.selectionType.encryptString()
  enTravelList.classType = travelList.classType.encryptString()
  enTravelList.listName = travelList.listName.encryptString()
  enTravelList.dueDate = travelList.dueDate.encryptString()
  enTravelList.created = travelList.created
  enTravelList.modified = travelList.modified
  enTravelList.createdUser = travelList.createdUser
    return enTravelList
}

fun encryptCombineMemories(combineMemories : DecryptedCombineMemories) : CombineMemories {
    val decryptedCombineMemories = CombineMemories()
    decryptedCombineMemories.id = combineMemories.id
    for(mainMemoryItems in combineMemories.mainMemoriesItems)  {
        decryptedCombineMemories.mainMemoriesItems.add(encryptMainMemories(mainMemoryItems))
    }
    for(memoryTimelineItems in combineMemories.memoryTimelineItems){
        decryptedCombineMemories.memoryTimelineItems.add(encryptMemoryTimeLine(memoryTimelineItems))
    }
    for(listItems in combineMemories.listItems){
        decryptedCombineMemories.listItems.add(encryptMemoriesList(listItems))
    }
    return decryptedCombineMemories
}

fun encryptCombineEducation(combineEducation: DecryptedCombineEducation) : CombineEducation {
    val decryptedCombineEducation = CombineEducation()
    decryptedCombineEducation.id = combineEducation.id
    for(educationItems in combineEducation.educationItems){
        decryptedCombineEducation.educationItems.add(encryptEducation(educationItems))
    }

    for(mainEducationItems in combineEducation.mainEducationItems){
        decryptedCombineEducation.mainEducationItems.add(encryptMainEducation(mainEducationItems))
    }

    for(workItems in combineEducation.workItems){
        decryptedCombineEducation.workItems.add(encryptWork(workItems))
    }

    for(listItems in combineEducation.listItems){
        decryptedCombineEducation.listItems.add(encryptEducationList(listItems))
    }
    return decryptedCombineEducation
}

fun encryptCombineInterests(combineInterest : DecryptedCombineInterests) : CombineInterests {
    val decryptedCombineInterests = CombineInterests()
    decryptedCombineInterests.id = combineInterest.id
    for(interestItems  in combineInterest.interestItems){
        decryptedCombineInterests.interestItems.add(encryptInterests(interestItems))
    }

    for(listItems in combineInterest.listItems){
        decryptedCombineInterests.listItems.add(encryptInterestList(listItems))
    }
    return decryptedCombineInterests
}

fun encryptCombinePersonal(combinePersonal: DecryptedCombinePersonal): CombinePersonal {
    val decryptedCombinePersonal = CombinePersonal()
    decryptedCombinePersonal.id = combinePersonal.id
    for (certificateItems in combinePersonal.certificateItems) {
        decryptedCombinePersonal.certificateItems.add(encryptCertificate(certificateItems as DecryptedCertificate))
    }

    for (governmentItems in combinePersonal.governmentItems) {
        decryptedCombinePersonal.governmentItems.add(encryptGovernment(governmentItems as DecryptedGovernment))
    }

    for (licenseItems in combinePersonal.licenseItems) {
        decryptedCombinePersonal.licenseItems.add(encryptLicense(licenseItems as DecryptedLicense))
    }

    for (personalItems in combinePersonal.personalItems) {
        decryptedCombinePersonal.personalItems.add(encryptPersonal(personalItems as DecryptedPersonal))
    }

    for (socialItems in combinePersonal.socialItems) {
        decryptedCombinePersonal.socialItems.add(encryptSocial(socialItems as DecryptedSocial))
    }

    for (taxIDItems in combinePersonal.taxIDItems) {
        decryptedCombinePersonal.taxIDItems.add(encryptTaxID(taxIDItems))
    }

    for (listItems in combinePersonal.listItems) {
        decryptedCombinePersonal.listItems.add(encryptPersonalList(listItems))
    }
    return decryptedCombinePersonal
}

fun encryptCombineWellness(combineWellness: DecryptedCombineWellness): CombineWellness {
    val decryptedCombineWellness = CombineWellness()
    decryptedCombineWellness.id = combineWellness.id
    for (checkupsItems in combineWellness.checkupsItems) {
        decryptedCombineWellness.checkupsItems.add(encryptCheckUps(checkupsItems))
    }

    for (emergencyContactItems in combineWellness.emergencyContactsItems) {
        decryptedCombineWellness.emergencyContactsItems.add(encryptEmergencyContacts(emergencyContactItems))
    }

    for (eyeglassPrescriptionsItems in combineWellness.eyeglassPrescriptionsItems) {
        decryptedCombineWellness.eyeglassPrescriptionsItems.add(encryptEyeGlassPrescriptions(eyeglassPrescriptionsItems))
    }

    for (healthcareProvidersItems in combineWellness.healthcareProvidersItems) {
        decryptedCombineWellness.healthcareProvidersItems.add(encryptHealthCareProviders(healthcareProvidersItems))
    }

    for (identificationItems in combineWellness.identificationItems) {
        decryptedCombineWellness.identificationItems.add(encryptIdentification(identificationItems))
    }

    for (medicalConditionsItems in combineWellness.medicalConditionsItems) {
        decryptedCombineWellness.medicalConditionsItems.add(encryptMedicalConditions(medicalConditionsItems))
    }

    for(medicalHistoryItems in combineWellness.medicalHistoryItems){
        decryptedCombineWellness.medicalHistoryItems.add(encryptMedicalHistory(medicalHistoryItems))
    }

    for (medicationsItems in combineWellness.medicationsItems) {
        decryptedCombineWellness.medicationsItems.add(encryptMedications(medicationsItems))
    }

    for (vitalNumbersItems in combineWellness.vitalNumbersItems) {
        decryptedCombineWellness.vitalNumbersItems.add(encryptVitalNumbers(vitalNumbersItems))
    }

    for (wellnessItems in combineWellness.wellnessItems) {
        decryptedCombineWellness.wellnessItems.add(encryptWellness(wellnessItems))
    }

    for (listItems in combineWellness.listItems) {
        decryptedCombineWellness.listItems.add(encryptWellnessList(listItems))
    }
    return decryptedCombineWellness
}

fun encryptCombineShopping(combineShopping : DecryptedCombineShopping) : CombineShopping {
    val decryptedCombineShopping = CombineShopping()
    decryptedCombineShopping.id = combineShopping.id
    for(loyaltyProgramItems in combineShopping.loyaltyProgramsItems){
        decryptedCombineShopping.loyaltyProgramsItems.add(encryptLoyaltyPrograms(loyaltyProgramItems as DecryptedLoyaltyPrograms))
    }
    for(recentPurchaseItems in combineShopping.recentPurchaseItems){
        decryptedCombineShopping.recentPurchaseItems.add(encryptRecentPurchase(recentPurchaseItems as DecryptedRecentPurchase))
    }
    for(shoppingItems in combineShopping.shoppingItems){
        decryptedCombineShopping.shoppingItems.add(encryptShopping(shoppingItems as DecryptedShopping))
    }

    for(clothingSizesItems in combineShopping.clothingSizesItems){
        decryptedCombineShopping.clothingSizesItems.add(encryptedClothingSizes(clothingSizesItems as DecryptedClothingSizes))
    }

    for(listItems in combineShopping.listItems){
        decryptedCombineShopping.listItems.add(encryptShoppingList(listItems as DecryptedShoppingList))
    }
    return decryptedCombineShopping
}

fun encryptCombineContacts(combineContacts: DecryptedCombineContacts): CombineContacts {
    val decryptedCombineContacts = CombineContacts()
    decryptedCombineContacts.id = combineContacts.id
    for(contactItems in combineContacts.contactsItems){
        decryptedCombineContacts.contactsItems.add(encryptContact(contactItems as DecryptedContacts))
    }
    for(mainContactItems in combineContacts.mainContactsItems){
        decryptedCombineContacts.mainContactsItems.add(encryptMainContacts(mainContactItems))
    }
    for(listItems in combineContacts.listItems){
        decryptedCombineContacts.listItems.add(encryptContactsList(listItems))
    }
    return decryptedCombineContacts
}