package com.ninebx.utility

import android.util.Base64
import com.ninebx.NineBxApplication
import com.ninebx.ui.auth.passwordHash.CustomKeyParameter
import com.ninebx.ui.auth.passwordHash.CustomPBEParametersGenerator
import com.ninebx.ui.auth.passwordHash.CustomPKCS5S2ParametersGenerator
import com.ninebx.ui.base.realm.*
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.contacts.CombineContacts
import com.ninebx.ui.base.realm.home.contacts.Contacts
import com.ninebx.ui.base.realm.home.education.Education
import com.ninebx.ui.base.realm.home.education.MainEducation
import com.ninebx.ui.base.realm.home.education.Work
import com.ninebx.ui.base.realm.home.homeBanking.*
import com.ninebx.ui.base.realm.home.personal.Certificate
import com.ninebx.ui.base.realm.home.shopping.ClothingSizes
import com.ninebx.ui.base.realm.home.travel.Documents
import com.ninebx.ui.base.realm.home.wellness.Checkups
import com.ninebx.ui.base.realm.lists.ContactsList
import com.ninebx.ui.base.realm.lists.HomeList
import io.realm.RealmList
import org.spongycastle.crypto.digests.SHA256Digest
import java.security.SecureRandom
import java.security.Security
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * Created by Alok on 02/02/18.
 */
val alphabetRange = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
var secureRandom = SecureRandom()

fun randomString(len: Int): String {
    val sb = StringBuilder(len)
    for (i in 0 until len)
        sb.append(alphabetRange[secureRandom.nextInt(alphabetRange.length)])
    return sb.toString()
}

/** Encrypt / Decrypt using AES
let aes = try! AES(key: encyptKey, blockMode: .ECB, padding: .pkcs7)  //try AES(key: key, blockMode: .CBC(iv: iv), padding: .pkcs7)
let encrypted = try! publicKey.encryptToBase64(cipher: aes)
secureKey = encrypted!
KeychainWrapper.standard.set(encrypted!, forKey: "9BX_SecureKey")

print("encrypted ===>", encrypted ?? "")

let decrypted = try! encrypted?.decryptBase64ToString(cipher: aes)

print("decrypted ===>", decrypted ?? "")
 * */

fun encryptAESKeyPassword(inputString: String, privateKey: ByteArray): String {

    Security.addProvider(org.bouncycastle.jce.provider.BouncyCastleProvider())

    val input = inputString.toByteArray()
    val keyBytes = privateKey
    val key = SecretKeySpec(keyBytes, "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")
    AppLogger.d("encryptAESKeyPassword", "Private Key : " + Arrays.toString(keyBytes))
    AppLogger.d("encryptAESKeyPassword", "Input : " + String(input))
    AppLogger.d("encryptAESKeyPassword", "Input Bytes : " + Arrays.toString(input))

    // encryption pass
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val cipherText = ByteArray(cipher.getOutputSize(input.size))
    var ctLength = cipher.update(input, 0, input.size, cipherText, 0)
    ctLength += cipher.doFinal(cipherText, ctLength)

    val cipherTextBase64 = Base64.encode(cipherText, Base64.DEFAULT)

    return String(cipherTextBase64)

}

fun decryptAESKEYPassword(cipherTextBase64: ByteArray?, masterPassword: ByteArray): String {

    val keyBytes = (masterPassword)
    val key = SecretKeySpec(keyBytes, "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")

    AppLogger.d("decryptAESKey", "Cipher : " + (cipherTextBase64))
    val convertedCipher = convertToUInt8(cipherTextBase64)
    AppLogger.d("decryptAESKey", "Cipher iOS : " + convertedCipher)
    AppLogger.d("decryptAESKey", "Cipher Android : " + convertToByte(convertedCipher.toCharArray()))

    // decryption pass
    cipher.init(Cipher.DECRYPT_MODE, key)
    val plainText = ByteArray(cipher.getOutputSize(convertedCipher.length))
    val cipherText = Base64.decode(cipherTextBase64, Base64.DEFAULT)

    var ptLength = cipher.update(cipherText, 0, cipherText.size, plainText, 0)
    ptLength += cipher.doFinal(plainText, ptLength)

    AppLogger.d("decryptAESKey", "Plain Text : " + String(plainText).substring(0, ptLength))
    AppLogger.d("decryptAESKey", "Plain Text Bytes : " + Arrays.toString(plainText))
    AppLogger.d("decryptAESKey", "ptLength : " + plainText.size)

    return String(plainText).substring(0, ptLength)
}

fun encryptAESKey(inputString: String, privateKey: String): String {

    Security.addProvider(org.bouncycastle.jce.provider.BouncyCastleProvider())

    val input = inputString.toByteArray()
    val keyBytes = (privateKey.toByteArray(Charsets.UTF_8))
    val key = SecretKeySpec(keyBytes, "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")
    AppLogger.d("encryptAESKey", "Private Key : " + Arrays.toString(keyBytes))
    AppLogger.d("encryptAESKey", "Input : " + String(input))
    AppLogger.d("encryptAESKey", "Input Bytes : " + Arrays.toString(input))

    // encryption pass
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val cipherText = ByteArray(cipher.getOutputSize(input.size))
    var ctLength = cipher.update(input, 0, input.size, cipherText, 0)
    ctLength += cipher.doFinal(cipherText, ctLength)

    val cipherTextBase64 = Base64.encode(cipherText, Base64.DEFAULT)

    return String(cipherTextBase64)

}

fun decryptAESKEY(cipherTextBase64: ByteArray?, masterPassword: String): String {

    val keyBytes = (masterPassword.toByteArray(Charsets.UTF_8))
    val key = SecretKeySpec(keyBytes, "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")

    AppLogger.d("decryptAESKey", "Cipher : " + (cipherTextBase64))
    val convertedCipher = convertToUInt8(cipherTextBase64)
    AppLogger.d("decryptAESKey", "Cipher iOS : " + convertedCipher)
    AppLogger.d("decryptAESKey", "Cipher Android : " + convertToByte(convertedCipher.toCharArray()))

    // decryption pass
    cipher.init(Cipher.DECRYPT_MODE, key)
    val plainText = ByteArray(cipher.getOutputSize(convertedCipher.length))
    val cipherText = Base64.decode(cipherTextBase64, Base64.DEFAULT)

    var ptLength = cipher.update(cipherText, 0, cipherText.size, plainText, 0)
    ptLength += cipher.doFinal(plainText, ptLength)

    AppLogger.d("decryptAESKey", "Plain Text : " + String(plainText).substring(0, ptLength))
    AppLogger.d("decryptAESKey", "Plain Text Bytes : " + Arrays.toString(plainText))
    AppLogger.d("decryptAESKey", "ptLength : " + plainText.size)

    return String(plainText).substring(0, ptLength)
}

fun encryptDecryptAESKey(masterPassword: String): String {

    Security.addProvider(org.bouncycastle.jce.provider.BouncyCastleProvider())

    val input = "www.javacodegeeks.com".toByteArray()
    val keyBytes = (masterPassword.toByteArray(Charsets.UTF_8))
    val key = SecretKeySpec(keyBytes, "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")
    AppLogger.d("decryptAESKey", "Private Key : " + Arrays.toString(keyBytes))
    AppLogger.d("decryptAESKey", "Input : " + String(input))
    AppLogger.d("decryptAESKey", "Input Bytes : " + Arrays.toString(input))

    // encryption pass
    cipher.init(Cipher.ENCRYPT_MODE, key)
    var cipherText = ByteArray(cipher.getOutputSize(input.size))
    var ctLength = cipher.update(input, 0, input.size, cipherText, 0)
    ctLength += cipher.doFinal(cipherText, ctLength)

    val cipherTextBase64 = Base64.encode(cipherText, Base64.DEFAULT)

    AppLogger.d("decryptAESKey", "Cipher : " + Arrays.toString(cipherTextBase64))
    val convertedCipher = convertToUInt8(cipherTextBase64)
    AppLogger.d("decryptAESKey", "Cipher iOS : " + convertedCipher)
    AppLogger.d("decryptAESKey", "Cipher Android : " + convertToByte(convertedCipher.toCharArray()))
    AppLogger.d("decryptAESKey", "Cipher Length : " + cipherText.size)
    AppLogger.d("decryptAESKey", "Cipher Length : " + ctLength)

    // decryption pass
    cipher.init(Cipher.DECRYPT_MODE, key)
    val plainText = ByteArray(cipher.getOutputSize(ctLength))
    cipherText = Base64.decode(cipherTextBase64, Base64.DEFAULT)

    var ptLength = cipher.update(cipherText, 0, plainText.size, plainText, 0)
    ptLength += cipher.doFinal(plainText, ptLength)

    AppLogger.d("decryptAESKey", "Plain Text : " + String(plainText).substring(0, ptLength))
    AppLogger.d("decryptAESKey", "Plain Text Bytes : " + Arrays.toString(plainText))
    AppLogger.d("decryptAESKey", "ptLength : " + plainText.size)

    return ""

}

fun encryptAESKey(masterPassword: String): String {

    Security.addProvider(org.bouncycastle.jce.provider.BouncyCastleProvider())

    val input = "www.javacodegeeks.com".toByteArray()
    val keyBytes = (masterPassword.toByteArray(Charsets.UTF_8))
    val key = SecretKeySpec(keyBytes, "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")

    AppLogger.d("encryptAESKey", "Private Key : " + Arrays.toString(keyBytes))
    AppLogger.d("encryptAESKey", "Input : " + String(input))
    AppLogger.d("encryptAESKey", "Input Bytes : " + Arrays.toString(input))

    // encryption pass
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val cipherText = ByteArray(cipher.getOutputSize(input.size))
    var ctLength = cipher.update(input, 0, input.size, cipherText, 0)
    ctLength += cipher.doFinal(cipherText, ctLength)

    val cipherTextBase64 = Base64.encode(cipherText, Base64.DEFAULT)

    decryptAESKey(cipherTextBase64, masterPassword)



    return ""

}


fun decryptAESKey(cipherTextBase64: ByteArray?, masterPassword: String) {

    val keyBytes = (masterPassword.toByteArray(Charsets.UTF_8))
    val key = SecretKeySpec(keyBytes, "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")

    AppLogger.d("decryptAESKey", "Cipher : " + Arrays.toString(cipherTextBase64))
    val convertedCipher = convertToUInt8(cipherTextBase64)
    AppLogger.d("decryptAESKey", "Cipher iOS : " + convertedCipher)
    AppLogger.d("decryptAESKey", "Cipher Android : " + convertToByte(convertedCipher.toCharArray()))

    // decryption pass
    cipher.init(Cipher.DECRYPT_MODE, key)
    val plainText = ByteArray(cipher.getOutputSize(convertedCipher.length))
    val cipherText = Base64.decode(cipherTextBase64, Base64.DEFAULT)

    var ptLength = cipher.update(cipherText, 0, cipherText.size, plainText, 0)
    ptLength += cipher.doFinal(plainText, ptLength)

    AppLogger.d("decryptAESKey", "Plain Text : " + String(plainText).substring(0, ptLength))
    AppLogger.d("decryptAESKey", "Plain Text Bytes : " + Arrays.toString(plainText))
    AppLogger.d("decryptAESKey", "ptLength : " + plainText.size)
}

/**
 *
 *
let password: Array<UInt8> = Array(password.utf8)
let salt: Array<UInt8> = Array(email.utf8)
let valueTwo = try PKCS5.PBKDF2(password: password, salt: salt, iterations: 20000, variant: .sha256).calculate()
return valueTwo.description
[-68, -100, 77, -35, -54, -57, -17, 127, -16, 3, -117, -8, 54, 89, 82, 75, 68, 77, -118, -98, 124, -89, -121, -34, -96, -48, -53, -114, 112, -77, 91, 49]
 * */
fun encryptKey(password: String, salt: String): ByteArray {
    val generator = CustomPKCS5S2ParametersGenerator(SHA256Digest())
    generator.init(CustomPBEParametersGenerator.PKCS5PasswordToUTF8Bytes(password.toCharArray()), salt.toByteArray(Charsets.UTF_8), 20000)
    val key = generator.generateDerivedMacParameters(256) as CustomKeyParameter
    return key.key
}

fun convertToUInt8IntArray(key: ByteArray?): IntArray {

    val intArray = kotlin.IntArray(key!!.size)
    for (index in 0 until key.size) {
        val keyValue = key[index]
        var indexKey = keyValue.toInt()
        if (indexKey < 0) {
            indexKey += 256
            intArray[index] = indexKey
        } else {
            intArray[index] = indexKey
        }
    }
    return (intArray)

}

fun convertToUInt8(key: ByteArray?): String {

    val intArray = kotlin.IntArray(key!!.size)
    for (index in 0 until key.size) {
        val keyValue = key[index]
        var indexKey = keyValue.toInt()
        if (indexKey < 0) {
            indexKey += 256
            intArray[index] = indexKey
        } else {
            intArray[index] = indexKey
        }
    }
    return Arrays.toString(intArray)

}

private fun convertToByte(key: CharArray): String {

    val intArray = kotlin.IntArray(key.size)
    for (index in 0 until key.size) {
        val keyValue = key[index]
        var indexKey = keyValue.toInt()
        if (indexKey > 128) {
            indexKey -= 256
            intArray[index] = indexKey
        } else {
            intArray[index] = indexKey
        }
    }
    return Arrays.toString(intArray)

}

fun isValidPassword(password: String): Boolean {

    if (password.length < 8) {
        return false
    }

    val pattern: Pattern
    val matcher: Matcher

    val PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\$@\$#!%?&.,/^*()_+|}{';>~`<]).*\$"

    pattern = Pattern.compile(PASSWORD_PATTERN)
    matcher = pattern.matcher(password)

    return matcher.matches()
}


fun securityTest() {
    val privateKey = randomString(16)

    val encryptedKey = encryptAESKey(privateKey, privateKey)
    AppLogger.d("securityTest", "Encrypted Key : " + encryptedKey)

    val decryptedKey = decryptAESKEY(encryptedKey.toByteArray(Charsets.UTF_8), privateKey)
    AppLogger.d("securityTest", "Decrypted Key : " + decryptedKey)
}

fun String.encryptString(): String {
    return encryptAESKey(this, NineBxApplication.getPreferences().privateKey!!)
}

fun String.decryptString(): String {
    if (this.isEmpty()) {
        return ""
    }
    return decryptAESKEY(this.toByteArray(), NineBxApplication.getPreferences().privateKey!!)
}

fun encryptUsers(currentUser: Users): Users {

    currentUser.fullName = currentUser.fullName.encryptString()
    currentUser.firstName = currentUser.firstName.encryptString()
    currentUser.lastName = currentUser.lastName.encryptString()
    currentUser.emailAddress = currentUser.emailAddress.encryptString()
    currentUser.relationship = currentUser.relationship.encryptString()
    currentUser.dateOfBirth = currentUser.dateOfBirth.encryptString()
    currentUser.anniversary = currentUser.anniversary.encryptString()
    currentUser.gender = currentUser.gender.encryptString()
    currentUser.mobileNumber = currentUser.mobileNumber.encryptString()

    currentUser.street_1 = currentUser.street_1.encryptString()
    currentUser.street_2 = currentUser.street_2.encryptString()
    currentUser.city = currentUser.city.encryptString()
    currentUser.state = currentUser.state.encryptString()
    currentUser.zipCode = currentUser.zipCode.encryptString()
    currentUser.country = currentUser.country.encryptString()

    currentUser.decryptedMembers = encryptMembers(currentUser.members)

    return currentUser

}

fun encryptMembers(members: RealmList<Member>): RealmList<Member>? {
    for (i in 0 until members.size) {
        var member = members[i]
        member = encryptMember(member!!)
    }
    return members
}

fun encryptMember(member: Member): Member? {

    member.firstName.encryptString()
    member.lastName.encryptString()
    member.relationship.encryptString()
    member.role.encryptString()
    member.email.encryptString()

    member.dateOfBirth.encryptString()
    member.anniversary.encryptString()
    member.gender.encryptString()
    member.mobileNumber.encryptString()
    member.street_1.encryptString()
    member.street_2.encryptString()
    member.city.encryptString()
    member.state.encryptString()
    member.zipCode.encryptString()
    member.country.encryptString()

    member.userId.encryptString()

    member.profilePhoto.encryptString()
    return member
}

fun decryptUsers(currentUser: Users): DecryptedUsers {
    
    val decryptedUsers = DecryptedUsers()

    decryptedUsers.fullName = currentUser.fullName.decryptString()
    decryptedUsers.firstName = currentUser.firstName.decryptString()
    decryptedUsers.lastName = currentUser.lastName.decryptString()
    decryptedUsers.emailAddress = currentUser.emailAddress.decryptString()
    decryptedUsers.relationship = currentUser.relationship.decryptString()
    decryptedUsers.dateOfBirth = currentUser.dateOfBirth.decryptString()
    decryptedUsers.anniversary = currentUser.anniversary.decryptString()
    decryptedUsers.gender = currentUser.gender.decryptString()
    decryptedUsers.mobileNumber = currentUser.mobileNumber.decryptString()

    decryptedUsers.street_1 = currentUser.street_1.decryptString()
    decryptedUsers.street_2 = currentUser.street_2.decryptString()
    decryptedUsers.city = currentUser.city.decryptString()
    decryptedUsers.state = currentUser.state.decryptString()
    decryptedUsers.zipCode = currentUser.zipCode.decryptString()
    decryptedUsers.country = currentUser.country.decryptString()
    decryptedUsers.userId = currentUser.userId
    decryptedUsers.profilePhoto = currentUser.profilePhoto

    decryptedUsers.decryptedMembers = decryptMembers(currentUser.members)
    AppLogger.d("Decrypt", "decryptUSers : " + decryptedUsers)
    return decryptedUsers
}

fun decryptMembers(members: RealmList<Member>): RealmList<DecryptedMember>? {
    val decryptedMembers = RealmList<DecryptedMember>()
    for (i in 0 until members.size) {
        val member = members[i]
        val decryptedMember = decryptMember(member!!)
        decryptedMembers.add(decryptedMember)
    }
    return decryptedMembers
}

fun decryptMember(member: Member): DecryptedMember? {

    val decryptedMember = DecryptedMember()

    decryptedMember.firstName = member.firstName.decryptString()
    decryptedMember.lastName = member.lastName.decryptString()
    decryptedMember.relationship = member.relationship.decryptString()
    decryptedMember.role = member.role.decryptString()
    decryptedMember.email = member.email.decryptString()

    decryptedMember.dateOfBirth = member.dateOfBirth.decryptString()
    decryptedMember.anniversary = member.anniversary.decryptString()
    decryptedMember.gender = member.gender.decryptString()
    decryptedMember.mobileNumber = member.mobileNumber.decryptString()
    decryptedMember.street_1 = member.street_1.decryptString()
    decryptedMember.street_2 = member.street_2.decryptString()
    decryptedMember.city = member.city.decryptString()
    decryptedMember.state = member.state.decryptString()
    decryptedMember.zipCode = member.zipCode.decryptString()
    decryptedMember.country = member.country.decryptString()

    decryptedMember.userId = member.userId
    ///For permissions
    decryptedMember.homeAdd = member.homeAdd
    decryptedMember.homeEdit = member.homeEdit
    decryptedMember.homeView = member.homeView

    decryptedMember.travelAdd = member.travelAdd
    decryptedMember.travelEdit = member.travelEdit
    decryptedMember.travelView = member.travelView

    decryptedMember.contactsAdd = member.contactsAdd
    decryptedMember.contactsEdit = member.contactsEdit
    decryptedMember.contactsView = member.contactsView

    decryptedMember.educationlAdd = member.educationlAdd
    decryptedMember.educationlEdit = member.educationlEdit
    decryptedMember.educationlView = member.educationlView

    decryptedMember.personalAdd = member.personalAdd
    decryptedMember.personalEdit = member.personalEdit
    decryptedMember.personalView = member.personalView

    decryptedMember.interestsAdd = member.interestsAdd
    decryptedMember.interestsEdit = member.interestsEdit
    decryptedMember.interestsView = member.interestsView

    decryptedMember.wellnessAdd = member.wellnessAdd
    decryptedMember.wellnessEdit = member.wellnessEdit
    decryptedMember.wellnessView = member.wellnessView

    decryptedMember.memoriesAdd = member.memoriesAdd
    decryptedMember.memoriesEdit = member.memoriesEdit
    decryptedMember.memoriesView = member.memoriesView

    decryptedMember.shoppingAdd = member.shoppingAdd
    decryptedMember.shoppingEdit = member.shoppingEdit
    decryptedMember.shoppingView = member.shoppingView

    decryptedMember.addingRemovingMember = member.addingRemovingMember
    decryptedMember.changingMasterPassword = member.changingMasterPassword

    decryptedMember.isCompleteProfile = member.completeProfile
    decryptedMember.profilePhoto = ""

    return decryptedMember
}

fun decryptFinancial(finance: Financial): DecryptedFinancial {

    val decryptedFinancial = DecryptedFinancial()

    //decryptedFinancial.id = finance.id.decryptString()
    //decryptedFinancial.photosId = finance.photosId.decryptString()
    //decryptedFinancial.backingImages = finance.backingImages.decryptString()

    decryptedFinancial.selectionType = finance.selectionType.decryptString()
    decryptedFinancial.institutionName = finance.institutionName.decryptString()
    decryptedFinancial.accountName = finance.accountName.decryptString()
    decryptedFinancial.accountType = finance.accountType.decryptString()
    decryptedFinancial.nameOnAccount = finance.nameOnAccount.decryptString()
    decryptedFinancial.accountNumber = finance.accountNumber.decryptString()
    decryptedFinancial.location = finance.location.decryptString()
    decryptedFinancial.swiftCode = finance.swiftCode.decryptString()
    decryptedFinancial.abaRoutingNumber = finance.abaRoutingNumber.decryptString()
    decryptedFinancial.abaRoutingNumber = finance.abaRoutingNumber.decryptString()
    decryptedFinancial.contacts = finance.contacts.decryptString()
    decryptedFinancial.website = finance.website.decryptString()
    decryptedFinancial.userName = finance.userName.decryptString()
    decryptedFinancial.password = finance.password.decryptString()
    decryptedFinancial.pin = finance.pin.decryptString()
    decryptedFinancial.created = finance.created.decryptString()
    decryptedFinancial.modified = finance.modified.decryptString()
    // decryptedFinancial.isPrivate = finance.isPrivate.decryptString()
    decryptedFinancial.createdUser = finance.createdUser.decryptString()
    decryptedFinancial.notes = finance.notes.decryptString()
    decryptedFinancial.attachmentNames = finance.attachmentNames.decryptString()

    AppLogger.d("Decrypt", "decryptedFinancial : " + decryptedFinancial)

    return decryptedFinancial
}

fun decryptPayment(payment: Payment): DecryptedPayment {

    val decryptedPayment = DecryptedPayment()

    //decryptedPayment.id = payment.id.decryptString()
    //decryptedPayment.photosId = payment.photosId.decryptString()
    //decryptedPayment.backingImages = payment.backingImages.decryptString()

    decryptedPayment.selectionType = payment.selectionType.decryptString()
    decryptedPayment.insuranceCompany = payment.insuranceCompany.decryptString()
    decryptedPayment.insuredProperty = payment.insuredProperty.decryptString()
    decryptedPayment.insuredVehicle = payment.insuredVehicle.decryptString()
    decryptedPayment.insuredPerson = payment.insuredPerson.decryptString()
    decryptedPayment.policyNumber = payment.policyNumber.decryptString()
    decryptedPayment.policyEffectiveDate = payment.policyEffectiveDate.decryptString()
    decryptedPayment.policyExpirationDate = payment.policyExpirationDate.decryptString()
    decryptedPayment.contacts = payment.contacts.decryptString()
    decryptedPayment.website = payment.website.decryptString()
    decryptedPayment.password = payment.password.decryptString()
    decryptedPayment.pin = payment.pin.decryptString()
    decryptedPayment.created = payment.created.decryptString()
    decryptedPayment.modified = payment.modified.decryptString()
    // decryptedPayment.isPrivate = payment.isPrivate.decryptString()
    decryptedPayment.createdUser = payment.createdUser.decryptString()
    decryptedPayment.notes = payment.notes.decryptString()
    decryptedPayment.attachmentNames = payment.attachmentNames.decryptString()

    AppLogger.d("Decrypt", "decryptedPayment : " + decryptedPayment)

    return decryptedPayment
}

fun decryptProperty(property: Property): DecryptedProperty {
    val decryptedProperty = DecryptedProperty()

    //decryptedProperty.id = property.id.decryptString()
    //decryptedProperty.photosId = property.photosId.decryptString()
    //decryptedProperty.backingImages = property.backingImages.decryptString()

    decryptedProperty.selectionType = property.selectionType.decryptString()
    decryptedProperty.propertyName = property.propertyName.decryptString()
    decryptedProperty.streetAddressOne = property.streetAddressOne.decryptString()
    decryptedProperty.streetAddressTwo = property.streetAddressTwo.decryptString()
    decryptedProperty.city = property.city.decryptString()
    decryptedProperty.state = property.state.decryptString()
    decryptedProperty.zipCode = property.zipCode.decryptString()
    decryptedProperty.country = property.country.decryptString()
    decryptedProperty.titleName = property.titleName.decryptString()
    decryptedProperty.purchaseDate = property.purchaseDate.decryptString()
    decryptedProperty.purchasePrice = property.purchasePrice.decryptString()
    decryptedProperty.estimatedMarketValue = property.estimatedMarketValue.decryptString()
    decryptedProperty.contacts = property.contacts.decryptString()
    // decryptedProperty.currentlyRented = property.currentlyRented.decryptString()
    decryptedProperty.tenantName = property.tenantName.decryptString()
    decryptedProperty.leaseStartDate = property.leaseStartDate.decryptString()
    decryptedProperty.leaseEndDate = property.leaseEndDate.decryptString()
    decryptedProperty.created = property.created.decryptString()
    decryptedProperty.modified = property.modified.decryptString()
    // decryptedProperty.isPrivate = property.isPrivate.decryptString()
    decryptedProperty.createdUser = property.createdUser.decryptString()
    decryptedProperty.notes = property.notes.decryptString()
    decryptedProperty.attachmentNames = property.attachmentNames.decryptString()

    AppLogger.d("Decrypt", "decryptedProperty : " + decryptedProperty)

    return decryptedProperty
}

fun decryptVehicle(vehicle: Vehicle): DecryptedVehicle {
    val decryptedVehicle = DecryptedVehicle()

    //decryptedVehicle.id = vehicle.id.decryptString()
    //decryptedVehicle.photosId = vehicle.photosId.decryptString()
    //decryptedVehicle.backingImages = vehicle.backingImages.decryptString()

    decryptedVehicle.selectionType = vehicle.selectionType.decryptString()
    decryptedVehicle.vehicleName = vehicle.vehicleName.decryptString()
    decryptedVehicle.vinNumber = vehicle.vinNumber.decryptString()
    decryptedVehicle.make = vehicle.make.decryptString()
    decryptedVehicle.model = vehicle.model.decryptString()
    decryptedVehicle.modelYear = vehicle.modelYear.decryptString()
    decryptedVehicle.color = vehicle.color.decryptString()
    decryptedVehicle.titleName = vehicle.titleName.decryptString()
    decryptedVehicle.estimatedMarketValue = vehicle.estimatedMarketValue.decryptString()
    decryptedVehicle.registrationExpirydate = vehicle.registrationExpirydate.decryptString()
    decryptedVehicle.purchasedOrLeased = vehicle.purchasedOrLeased.decryptString()
    decryptedVehicle.financedThroughLoan = vehicle.financedThroughLoan.decryptString()
    decryptedVehicle.created = vehicle.created.decryptString()
    decryptedVehicle.modified = vehicle.modified.decryptString()
    // decryptedVehicle.isPrivate = vehicle.isPrivate.decryptString()
    decryptedVehicle.createdUser = vehicle.createdUser.decryptString()
    decryptedVehicle.leaseStartDate = vehicle.leaseStartDate.decryptString()
    decryptedVehicle.leaseEndDate = vehicle.leaseEndDate.decryptString()
    decryptedVehicle.contacts = vehicle.contacts.decryptString()
    decryptedVehicle.maintenanceEvent = vehicle.maintenanceEvent.decryptString()
    decryptedVehicle.serviceProviderName = vehicle.serviceProviderName.decryptString()
    decryptedVehicle.dateOfService = vehicle.dateOfService.decryptString()
    decryptedVehicle.vehicle = vehicle.vehicle.decryptString()
    decryptedVehicle.notes = vehicle.notes.decryptString()
    decryptedVehicle.attachmentNames = vehicle.attachmentNames.decryptString()

    AppLogger.d("Decrypt", "decryptedVehicle : " + decryptedVehicle)
    return decryptedVehicle
}

fun decryptAsset(asset: Asset): DecryptedAsset {
    val decryptedAsset = DecryptedAsset()

    //decryptedAsset.id = asset.id.decryptString()
    //decryptedAsset.photosId = asset.photosId.decryptString()
    //decryptedAsset.backingImages = asset.backingImages.decryptString()

    decryptedAsset.selectionType = asset.selectionType.decryptString()
    decryptedAsset.test = asset.test.decryptString()
    decryptedAsset.assetName = asset.assetName.decryptString()
    decryptedAsset.descriptionOrLocation = asset.descriptionOrLocation.decryptString()
    decryptedAsset.estimatedMarketValue = asset.estimatedMarketValue.decryptString()
    decryptedAsset.serialNumber = asset.serialNumber.decryptString()
    decryptedAsset.purchaseDate = asset.purchaseDate.decryptString()
    decryptedAsset.purchasePrice = asset.purchasePrice.decryptString()
    decryptedAsset.contacts = asset.contacts.decryptString()
    decryptedAsset.created = asset.created.decryptString()
    decryptedAsset.modified = asset.modified.decryptString()
    //decryptedAsset.isPrivate = asset.isPrivate.decryptString()
    decryptedAsset.createdUser = asset.createdUser.decryptString()
    decryptedAsset.notes = asset.notes.decryptString()
    decryptedAsset.imageName = asset.imageName.decryptString()
    decryptedAsset.attachmentNames = asset.attachmentNames.decryptString()

    AppLogger.d("Decrypt", "decryptedAsset : " + decryptedAsset)

    return decryptedAsset
}

fun decryptInsurance(insurance: Insurance): DecryptedInsurance {
    val decryptedInsurance = DecryptedInsurance()

    //decryptedInsurance.id = insurance.id.decryptString()
    //decryptedInsurance.photosId = insurance.photosId.decryptString()
    //decryptedInsurance.backingImages = insurance.backingImages.decryptString()

    decryptedInsurance.selectionType = insurance.selectionType.decryptString()
    decryptedInsurance.insuranceCompany = insurance.insuranceCompany.decryptString()
    decryptedInsurance.insuredProperty = insurance.insuredProperty.decryptString()
    decryptedInsurance.insuredVehicle = insurance.insuredVehicle.decryptString()
    decryptedInsurance.insuredPerson = insurance.insuredPerson.decryptString()
    decryptedInsurance.policyNumber = insurance.policyNumber.decryptString()
    decryptedInsurance.policyEffectiveDate = insurance.policyEffectiveDate.decryptString()
    decryptedInsurance.policyExpirationDate = insurance.policyExpirationDate.decryptString()
    decryptedInsurance.contacts = insurance.contacts.decryptString()
    decryptedInsurance.website = insurance.website.decryptString()
    decryptedInsurance.userName = insurance.userName.decryptString()
    decryptedInsurance.password = insurance.password.decryptString()
    decryptedInsurance.pin = insurance.pin.decryptString()
    decryptedInsurance.created = insurance.created.decryptString()
    decryptedInsurance.modified = insurance.modified.decryptString()
    //decryptedInsurance.isPrivate = insurance.isPrivate.decryptString()
    decryptedInsurance.createdUser = insurance.createdUser.decryptString()
    decryptedInsurance.notes = insurance.notes.decryptString()
    decryptedInsurance.attachmentNames = insurance.attachmentNames.decryptString()

    AppLogger.d("Decrypt", "decryptedInsurance : " + decryptedInsurance)
    return decryptedInsurance
}

fun decryptTaxes(taxes: Taxes): DecryptedTax {
    val decryptedTax = DecryptedTax()

    //decryptedInsurance.id = insurance.id.decryptString()
    //decryptedInsurance.photosId = insurance.photosId.decryptString()
    //decryptedInsurance.backingImages = insurance.backingImages.decryptString()

    decryptedTax.selectionType = taxes.selectionType.decryptString()
    decryptedTax.returnName = taxes.returnName.decryptString()
    decryptedTax.taxYear = taxes.taxYear.decryptString()
    decryptedTax.taxPayer = taxes.taxPayer.decryptString()
    decryptedTax.contacts = taxes.contacts.decryptString()
    decryptedTax.imageName = taxes.imageName.decryptString()
    decryptedTax.attachmentNames = taxes.attachmentNames.decryptString()
    decryptedTax.notes = taxes.notes.decryptString()
    decryptedTax.title = taxes.title.decryptString()
    decryptedTax.created = taxes.created.decryptString()
    decryptedTax.modified = taxes.modified.decryptString()
    // decryptedTax.isPrivate = taxes.isPrivate.decryptString()
    decryptedTax.createdUser = taxes.createdUser.decryptString()

    AppLogger.d("Decrypt", "decryptedTax : " + decryptedTax)
    return decryptedTax
}

fun decryptHomeList(homeList: HomeList): DecryptedHomeList {
    val decryptedHomeList = DecryptedHomeList()

    //decryptedHomeList.id = homeList.id.decryptString()

    decryptedHomeList.selectionType = homeList.selectionType.decryptString()
    decryptedHomeList.listName = homeList.listName.decryptString()
    decryptedHomeList.dueDate = homeList.dueDate.decryptString()
    /*  decryptedHomeList.detailsId = homeList.detailsId.decryptString()
      decryptedHomeList.isSelected = homeList.isSelected.decryptString() */
    /*   decryptedHomeList.selectedDate = homeList.selectedDate.decryptString()
       decryptedHomeList.createdDate = homeList.createdDate.decryptString()*/
    decryptedHomeList.created = homeList.created.decryptString()
    decryptedHomeList.modified = homeList.modified.decryptString()
    /*decryptedHomeList.isPrivate = homeList.isPrivate.decryptString()  */
    decryptedHomeList.createdUser = homeList.createdUser.decryptString()

    AppLogger.d("Decrypt", "decryptedHomeList : " + decryptedHomeList)
    return decryptedHomeList
}

fun decryptEducation(education: Education): DecryptedEducation {
    val decryptedEducation = DecryptedEducation()
    decryptedEducation.selectionType = education.selectionType.decryptString()
    decryptedEducation.institutionName = education.institutionName.decryptString()
    decryptedEducation.accountName = education.accountName.decryptString()
    decryptedEducation.nameOnAccount = education.nameOnAccount.decryptString()
    decryptedEducation.accountNumber = education.accountNumber.decryptString()
    decryptedEducation.location = education.location.decryptString()
    decryptedEducation.swiftCode = education.swiftCode.decryptString()
    decryptedEducation.abaRoutingNumber = education.abaRoutingNumber.decryptString()
    decryptedEducation.contacts = education.contacts.decryptString()
    decryptedEducation.website = education.website.decryptString()
    decryptedEducation.userName = education.userName.decryptString()
    decryptedEducation.password = education.password.decryptString()
    decryptedEducation.pin = education.pin.decryptString()
    decryptedEducation.paymentMethodOnFile = education.paymentMethodOnFile.decryptString()
    decryptedEducation.notes = education.notes.decryptString()
    decryptedEducation.attachmentNames = education.attachmentNames.decryptString()
    decryptedEducation.title = education.title.decryptString()
    decryptedEducation.created = education.created.decryptString()
    decryptedEducation.createdUser = education.createdUser.decryptString()
    decryptedEducation.modified = education.modified.decryptString()

    AppLogger.d("Decrypt", "decryptedEducation : " + decryptedEducation)
    return decryptedEducation
}

fun decryptMainEducation(mainEducation: MainEducation): DecryptedMainEducation {
    val decryptedMainEducation = DecryptedMainEducation()
    decryptedMainEducation.selectionType = mainEducation.selectionType.decryptString()
    decryptedMainEducation.classType = mainEducation.classType.decryptString()
    decryptedMainEducation.institutionName = mainEducation.institutionName.decryptString()
    decryptedMainEducation.qualification = mainEducation.qualification.decryptString()
    decryptedMainEducation.name = mainEducation.name.decryptString()
    decryptedMainEducation.location = mainEducation.location.decryptString()
    decryptedMainEducation.major = mainEducation.major.decryptString()
    decryptedMainEducation.from = mainEducation.from.decryptString()
    decryptedMainEducation.to = mainEducation.to.decryptString()
    decryptedMainEducation.currentlyStudying = mainEducation.to.decryptString()
    decryptedMainEducation.notes = mainEducation.notes.decryptString()
    decryptedMainEducation.created = mainEducation.created.decryptString()
    decryptedMainEducation.modified = mainEducation.modified.decryptString()
    decryptedMainEducation.attachmentNames = mainEducation.attachmentNames.decryptString()
    decryptedMainEducation.createdUser = mainEducation.createdUser.decryptString()

    AppLogger.d("Decrypt", "decryptedMainEducation : " + decryptedMainEducation)
    return decryptedMainEducation
}

fun decryptWork(work: Work): DecryptedWork {
    val decryptedWork = DecryptedWork()
    decryptedWork.selectionType = work.selectionType.decryptString()
    decryptedWork.classType = work.classType.decryptString()
    decryptedWork.companyName = work.companyName.decryptString()
    decryptedWork.position = work.position.decryptString()
    decryptedWork.name = work.name.decryptString()
    decryptedWork.location = work.location.decryptString()
    decryptedWork.from = work.from.decryptString()
    decryptedWork.to = work.to.decryptString()
    decryptedWork.currentWork = work.currentWork.decryptString()
    decryptedWork.created = work.created.decryptString()
    decryptedWork.modified = work.modified.decryptString()
    decryptedWork.notes = work.notes.decryptString()
    decryptedWork.attachmentNames = work.attachmentNames.decryptString()
    decryptedWork.createdUser = work.createdUser.decryptString()

    AppLogger.d("Decrypt", "decryptedWork : " + decryptedWork)
    return decryptedWork
}



// For Contacts List

fun decryptContactList(contactsList: Contacts): DecryptedContacts {
    val decryptedContacts = DecryptedContacts()

    decryptedContacts.selectionType = contactsList.selectionType.decryptString()
    decryptedContacts.firstName = contactsList.firstName.decryptString()
    decryptedContacts.lastName = contactsList.lastName.decryptString()
    decryptedContacts.dateOfBirth = contactsList.dateOfBirth.decryptString()
    decryptedContacts.anniversary = contactsList.anniversary.decryptString()
    decryptedContacts.mobileOne = contactsList.mobileOne.decryptString()
    decryptedContacts.mobileTwo = contactsList.mobileTwo.decryptString()
    decryptedContacts.emailOne = contactsList.emailOne.decryptString()
    decryptedContacts.emailTwo = contactsList.emailTwo.decryptString()
    decryptedContacts.streetAddressOne = contactsList.streetAddressOne.decryptString()
    decryptedContacts.streetAddressTwo = contactsList.streetAddressTwo.decryptString()
    decryptedContacts.city = contactsList.city.decryptString()
    decryptedContacts.state = contactsList.state.decryptString()
    decryptedContacts.zipCode = contactsList.zipCode.decryptString()
    decryptedContacts.country = contactsList.country.decryptString()
    decryptedContacts.created = contactsList.created.decryptString()
    decryptedContacts.modified = contactsList.modified.decryptString()
    decryptedContacts.createdUser = contactsList.createdUser.decryptString()

    AppLogger.d("Decrypt", "decryptedContacts : " + decryptedContacts)
    return decryptedContacts
}


fun decryptRealmString(members: RealmList<RealmString>): RealmList<DecryptedRealmString>? {
    val decryptedMembers = RealmList<DecryptedRealmString>()
    for (i in 0 until members.size) {
        val member = members[i]
        val decryptedMember = decryptRealmString(member!!)
        decryptedMembers.add(decryptedMember)
    }
    return decryptedMembers
}


fun decryptRealmString(realmString: RealmString): DecryptedRealmString? {
    val decryptedRealmString = DecryptedRealmString()

    decryptedRealmString.stringValue = realmString.stringValue.decryptString()

    return decryptedRealmString
}

fun encryptContact(contacts: Contacts): Contacts {
    contacts.selectionType = contacts.selectionType.encryptString()
    contacts.firstName = contacts.firstName.encryptString()
    contacts.lastName = contacts.lastName.encryptString()
    contacts.dateOfBirth = contacts.dateOfBirth.encryptString()
    contacts.anniversary = contacts.anniversary.encryptString()
    contacts.mobileOne = contacts.mobileOne.encryptString()
    contacts.mobileTwo = contacts.mobileTwo.encryptString()
    contacts.emailOne = contacts.emailOne.encryptString()
    contacts.emailTwo = contacts.emailTwo.encryptString()
    contacts.streetAddressOne = contacts.streetAddressOne.encryptString()
    contacts.streetAddressTwo = contacts.streetAddressTwo.encryptString()
    contacts.city = contacts.city.encryptString()
    contacts.state = contacts.state.encryptString()
    contacts.zipCode = contacts.zipCode.encryptString()
    contacts.country = contacts.country.encryptString()
    contacts.created = contacts.created.encryptString()
    contacts.modified = contacts.modified.encryptString()
    contacts.createdUser = contacts.createdUser.encryptString()

    contacts.backingImages = encryptContactBackingImages(contacts.backingImages)
    return contacts
}


fun encryptContactBackingImages(members: RealmList<RealmString>): RealmList<RealmString>? {
    for (i in 0 until members.size) {
        var member = members[i]
        member = encryptContactBacking(member!!)
    }
    return members
}

fun encryptContactBacking(member: RealmString): RealmString {
    member.stringValue.encryptString()
    return member
}

// Going to make Decrypted Methods for every Decrypted Model Class

fun decryptAddress(address: Address): DecryptedAddress {
    val decryptAddress = DecryptedAddress()
    decryptAddress.street_1 = address.street_1.decryptString()
    decryptAddress.street_2 = address.street_2.decryptString()
    decryptAddress.city = address.city.decryptString()
    decryptAddress.state = address.state.decryptString()
    decryptAddress.zipCode = address.zipCode.decryptString()
    decryptAddress.country = address.country.decryptString()
    return decryptAddress
}

fun decryptCalendarEvents(calendarEvents: CalendarEvents): DecryptedCalendarEvents {
    val decrptCalendarEvents = DecryptedCalendarEvents()

    return decrptCalendarEvents

}


fun decryptCertificate(certificate: Certificate): DecryptedCertificate {
    val decryptCertificate = DecryptedCertificate()
    decryptCertificate.selectionType = certificate.selectionType.decryptString()
    decryptCertificate.cer_description = certificate.cer_description.decryptString()
    decryptCertificate.nameOnCertificate = certificate.nameOnCertificate.decryptString()
    decryptCertificate.gender = certificate.gender.decryptString()
    decryptCertificate.dateOfBirth = certificate.dateOfBirth.decryptString()
    decryptCertificate.timeOfBirth = certificate.timeOfBirth.decryptString()
    decryptCertificate.placeOfBirth = certificate.placeOfBirth.decryptString()
    decryptCertificate.dateOfMarriage = certificate.dateOfMarriage.decryptString()
    decryptCertificate.placeOfMarriage = certificate.placeOfMarriage.decryptString()
    decryptCertificate.nameOneCertificate = certificate.nameOneCertificate.decryptString()
    decryptCertificate.nameTwoCertificate = certificate.nameTwoCertificate.decryptString()
    decryptCertificate.notes = certificate.notes.decryptString()
    decryptCertificate.created = certificate.created.decryptString()
    decryptCertificate.modified = certificate.modified.decryptString()
    decryptCertificate.attachmentNames = certificate.attachmentNames.decryptString()
    decryptCertificate.createdUser = certificate.createdUser.decryptString()


    return decryptCertificate

}


fun decryptCheckUps(checkups: Checkups): DecryptedCheckups {
    val decryptCheckUps = DecryptedCheckups()
    decryptCheckUps.selectionType = checkups.selectionType.decryptString()
    decryptCheckUps.classType = checkups.classType.decryptString()
    decryptCheckUps.physicianName = checkups.physicianName.decryptString()
    decryptCheckUps.checkup_description = checkups.checkup_description.decryptString()
    decryptCheckUps.physicianType = checkups.physicianType.decryptString()
    decryptCheckUps.reason = checkups.reason.decryptString()
    decryptCheckUps.dateOfVisit = checkups.dateOfVisit.decryptString()
    decryptCheckUps.notes = checkups.notes.decryptString()
    decryptCheckUps.attachmentNames = checkups.attachmentNames.decryptString()
    decryptCheckUps.created = checkups.created.decryptString()
    decryptCheckUps.modified = checkups.modified.decryptString()
    decryptCheckUps.createdUser = checkups.createdUser.decryptString()

    return decryptCheckUps
}

fun decryptedClothingSizes(clothingSizes: ClothingSizes): DecryptedClothingSizes {
    val decryptClothingSizes = DecryptedClothingSizes()

    decryptClothingSizes.selectionType = clothingSizes.selectionType.decryptString()
    decryptClothingSizes.classType = clothingSizes.classType.decryptString()
    decryptClothingSizes.personName = clothingSizes.personName.decryptString()
    decryptClothingSizes.sizeName = clothingSizes.sizeName.decryptString()
    decryptClothingSizes.sizeCategory = clothingSizes.sizeCategory.decryptString()
    decryptClothingSizes.topsSize = clothingSizes.topsSize.decryptString()
    decryptClothingSizes.topsNumericSize = clothingSizes.topsNumericSize.decryptString()
    decryptClothingSizes.bottomsSize = clothingSizes.bottomsSize.decryptString()
    decryptClothingSizes.bottomsNumericSize = clothingSizes.bottomsNumericSize.decryptString()
    decryptClothingSizes.dressesSize = clothingSizes.dressesSize.decryptString()
    decryptClothingSizes.dressesNumericSize = clothingSizes.dressesNumericSize.decryptString()
    decryptClothingSizes.outWearSize = clothingSizes.outWearSize.decryptString()
    decryptClothingSizes.outWearNumericSize = clothingSizes.outWearNumericSize.decryptString()
    decryptClothingSizes.swimWearSize = clothingSizes.swimWearSize.decryptString()
    decryptClothingSizes.swimWearNumericSize = clothingSizes.swimWearNumericSize.decryptString()
    decryptClothingSizes.swimWearBraBandCupSize = clothingSizes.swimWearBraBandCupSize.decryptString()
    decryptClothingSizes.shoeSize = clothingSizes.shoeSize.decryptString()
    decryptClothingSizes.shoeWidth = clothingSizes.shoeWidth.decryptString()
    decryptClothingSizes.hats = clothingSizes.hats.decryptString()
    decryptClothingSizes.gloves = clothingSizes.gloves.decryptString()
    decryptClothingSizes.tights = clothingSizes.tights.decryptString()
    decryptClothingSizes.bust = clothingSizes.bust.decryptString()
    decryptClothingSizes.waist = clothingSizes.waist.decryptString()
    decryptClothingSizes.hips = clothingSizes.hips.decryptString()
    decryptClothingSizes.armLength = clothingSizes.armLength.decryptString()
    decryptClothingSizes.inseam = clothingSizes.inseam.decryptString()
    decryptClothingSizes.torso = clothingSizes.torso.decryptString()
    decryptClothingSizes.jacketsSize = clothingSizes.jacketsSize.decryptString()
    decryptClothingSizes.jacketsNumericSize = clothingSizes.jacketsNumericSize.decryptString()
    decryptClothingSizes.pantsSize = clothingSizes.pantsSize.decryptString()
    decryptClothingSizes.pantsNumericSize = clothingSizes.pantsNumericSize.decryptString()
    decryptClothingSizes.toddlerSize = clothingSizes.toddlerSize.decryptString()
    decryptClothingSizes.kidSize = clothingSizes.kidSize.decryptString()
    decryptClothingSizes.neck = clothingSizes.neck.decryptString()
    decryptClothingSizes.chest = clothingSizes.chest.decryptString()
    decryptClothingSizes.clothing = clothingSizes.clothing.decryptString()
    decryptClothingSizes.shoes = clothingSizes.shoes.decryptString()
    decryptClothingSizes.notes = clothingSizes.notes.decryptString()
    decryptClothingSizes.attachmentNames = clothingSizes.attachmentNames.decryptString()
    decryptClothingSizes.beltsNumericSize = clothingSizes.beltsNumericSize.decryptString()
    decryptClothingSizes.socks = clothingSizes.socks.decryptString()
    decryptClothingSizes.seat = clothingSizes.seat.decryptString()
    decryptClothingSizes.beltSize = clothingSizes.beltSize.decryptString()
    decryptClothingSizes.created = clothingSizes.created.decryptString()
    decryptClothingSizes.modified = clothingSizes.modified.decryptString()
    decryptClothingSizes.createdUser = clothingSizes.createdUser.decryptString()

    return decryptClothingSizes
}

fun decryptCombineContacts(combineContacts: CombineContacts): DecryptedCombineContacts {
    val decryptCombineContacts = DecryptedCombineContacts()

    return decryptCombineContacts

}


fun decryptContactsList(contactsList: ContactsList): DecryptedContactsList {
    val decryptContactList = DecryptedContactsList()

    decryptContactList.selectionType = contactsList.selectionType.decryptString()
    decryptContactList.classType = contactsList.classType.decryptString()
    decryptContactList.listName = contactsList.listName.decryptString()
    decryptContactList.dueDate = contactsList.dueDate.decryptString()
    decryptContactList.created = contactsList.created.decryptString()
    decryptContactList.modified = contactsList.modified.decryptString()
    decryptContactList.createdUser = contactsList.createdUser.decryptString()

    return decryptContactList

}


fun decryptDevice(device: Device): DecryptedDevice {
    val decryptDevice = DecryptedDevice()
    decryptDevice.deviceId = device.deviceId.decryptString()
    decryptDevice.passcode = device.passcode.decryptString()
    return decryptDevice
}

fun decryptDocuments(documents: Documents): DecryptedDocuments {
    val decryptDocuments = DecryptedDocuments()
    decryptDocuments.selectionType = documents.selectionType.decryptString()
    decryptDocuments.passportName = documents.passportName.decryptString()
    decryptDocuments.visaName = documents.visaName.decryptString()
    decryptDocuments.nameOnPassport = documents.nameOnPassport.decryptString()
    decryptDocuments.nameOnVisa = documents.nameOnVisa.decryptString()
    decryptDocuments.visaType = documents.visaType.decryptString()
    decryptDocuments.visaNumber = documents.visaNumber.decryptString()
    decryptDocuments.travelDocumentTitle = documents.travelDocumentTitle.decryptString()
    decryptDocuments.nameOnTravelDocument = documents.nameOnTravelDocument.decryptString()
    decryptDocuments.travelDocumentType = documents.travelDocumentType.decryptString()
    decryptDocuments.travelDocumentNumber = documents.travelDocumentNumber.decryptString()
    decryptDocuments.issuingCountry = documents.issuingCountry.decryptString()
    decryptDocuments.passportNumber = documents.passportNumber.decryptString()
    decryptDocuments.placeIssued = documents.placeIssued.decryptString()
    decryptDocuments.dateIssued = documents.dateIssued.decryptString()
    decryptDocuments.expirationDate = documents.expirationDate.decryptString()
    decryptDocuments.notes = documents.notes.decryptString()
    decryptDocuments.attachmentNames = documents.attachmentNames.decryptString()
    decryptDocuments.created = documents.created.decryptString()
    decryptDocuments.modified = documents.modified.decryptString()
    decryptDocuments.createdUser = documents.createdUser.decryptString()

    return decryptDocuments
}

















































