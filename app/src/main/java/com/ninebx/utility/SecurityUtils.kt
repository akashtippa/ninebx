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
import org.spongycastle.crypto.digests.SHA256Digest
import java.security.MessageDigest
import java.security.SecureRandom
import java.security.Security
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
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
    return String(cipherTextBase64).trim()

}

fun decryptAESKEY(cipherTextBase64: ByteArray?, masterPassword: String): String {

    try {

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

    } catch (e: IllegalBlockSizeException) {
        //To catch exception while decrypting non decrypted string.
        e.printStackTrace()
        return Arrays.toString(cipherTextBase64)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        return Arrays.toString(cipherTextBase64)
    }

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
        encryptMember(members[i]!!)
    }
    return members
}

fun encryptMember(member: Member): Member? {

    member.firstName = member.firstName.encryptString()
    member.lastName = member.lastName.encryptString()
    member.relationship = member.relationship.encryptString()
    member.role = member.role.encryptString()
    member.email = member.email.encryptString()

    member.dateOfBirth = member.dateOfBirth.encryptString()
    member.anniversary = member.anniversary.encryptString()
    member.gender = member.gender
    member.mobileNumber = member.mobileNumber.encryptString()
    member.street_1 = member.street_1.encryptString()
    member.street_2 = member.street_2.encryptString()
    member.city = member.city.encryptString()
    member.state = member.state.encryptString()
    member.zipCode = member.zipCode.encryptString()
    member.country = member.country.encryptString()

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

    decryptedFinancial.id = finance.id
    decryptedFinancial.photosId = finance.photosId
    decryptedFinancial.backingImages = finance.backingImages

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
    decryptedFinancial.created = finance.created
    decryptedFinancial.modified = finance.modified
    decryptedFinancial.createdUser = finance.createdUser
    decryptedFinancial.created = finance.created
    decryptedFinancial.modified = finance.modified
    decryptedFinancial.createdUser = finance.createdUser
    decryptedFinancial.notes = finance.notes.decryptString()
    decryptedFinancial.attachmentNames = finance.attachmentNames.decryptString()

    AppLogger.d("Decrypt", "decryptedFinancial : " + decryptedFinancial)

    return decryptedFinancial
}

fun decryptPayment(payment: Payment): DecryptedPayment {

    val decryptedPayment = DecryptedPayment()

    decryptedPayment.id = payment.id
    decryptedPayment.photosId = payment.photosId
    decryptedPayment.backingImages = payment.backingImages

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
    decryptedPayment.created = payment.created
    decryptedPayment.modified = payment.modified
   /* decryptedPayment.isPrivate = payment.isPrivate*/
    decryptedPayment.createdUser = payment.createdUser
    decryptedPayment.notes = payment.notes
    decryptedPayment.attachmentNames = payment.attachmentNames

    AppLogger.d("Decrypt", "decryptedPayment : " + decryptedPayment)

    return decryptedPayment
}

fun decryptProperty(property: Property): DecryptedProperty {
    val decryptedProperty = DecryptedProperty()

    decryptedProperty.id = property.id
    decryptedProperty.photosId = property.photosId
    decryptedProperty.backingImages = property.backingImages

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
    decryptedProperty.currentlyRented = property.currentlyRented
    decryptedProperty.tenantName = property.tenantName.decryptString()
    decryptedProperty.leaseStartDate = property.leaseStartDate.decryptString()
    decryptedProperty.leaseEndDate = property.leaseEndDate.decryptString()
    decryptedProperty.created = property.created
    decryptedProperty.modified = property.modified
   // decryptedProperty.isPrivate = property.isPrivate
    decryptedProperty.createdUser = property.createdUser
    decryptedProperty.notes = property.notes
    decryptedProperty.attachmentNames = property.attachmentNames

    AppLogger.d("Decrypt", "decryptedProperty : " + decryptedProperty)

    return decryptedProperty
}

fun decryptVehicle(vehicle: Vehicle): DecryptedVehicle {
    val decryptedVehicle = DecryptedVehicle()

    decryptedVehicle.id = vehicle.id
    decryptedVehicle.photosId = vehicle.photosId
    decryptedVehicle.backingImages = vehicle.backingImages

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
    decryptedVehicle.created = vehicle.created
    decryptedVehicle.modified = vehicle.modified
   // decryptedVehicle.isPrivate = vehicle.isPrivate
    decryptedVehicle.createdUser = vehicle.createdUser
    decryptedVehicle.leaseStartDate = vehicle.leaseStartDate
    decryptedVehicle.leaseEndDate = vehicle.leaseEndDate
    decryptedVehicle.contacts = vehicle.contacts.decryptString()
    decryptedVehicle.maintenanceEvent = vehicle.maintenanceEvent.decryptString()
    decryptedVehicle.serviceProviderName = vehicle.serviceProviderName.decryptString()
    decryptedVehicle.dateOfService = vehicle.dateOfService
    decryptedVehicle.vehicle = vehicle.vehicle.decryptString()
    decryptedVehicle.notes = vehicle.notes.decryptString()
    decryptedVehicle.attachmentNames = vehicle.attachmentNames.decryptString()

    AppLogger.d("Decrypt", "decryptedVehicle : " + decryptedVehicle)
    return decryptedVehicle
}

fun decryptAsset(asset: Asset): DecryptedAsset {
    val decryptedAsset = DecryptedAsset()

    decryptedAsset.id = asset.id
    decryptedAsset.photosId = asset.photosId
    decryptedAsset.backingImages = asset.backingImages

    decryptedAsset.selectionType = asset.selectionType.decryptString()
    decryptedAsset.test = asset.test.decryptString()
    decryptedAsset.assetName = asset.assetName.decryptString()
    decryptedAsset.descriptionOrLocation = asset.descriptionOrLocation.decryptString()
    decryptedAsset.estimatedMarketValue = asset.estimatedMarketValue.decryptString()
    decryptedAsset.serialNumber = asset.serialNumber.decryptString()
    decryptedAsset.purchaseDate = asset.purchaseDate.decryptString()
    decryptedAsset.purchasePrice = asset.purchasePrice.decryptString()
    decryptedAsset.contacts = asset.contacts.decryptString()
    decryptedAsset.created = asset.created
    decryptedAsset.modified = asset.modified
    //decryptedAsset.isPrivate = asset.isPrivate
    decryptedAsset.createdUser = asset.createdUser
    decryptedAsset.notes = asset.notes
    decryptedAsset.imageName = asset.imageName
    decryptedAsset.attachmentNames = asset.attachmentNames

    AppLogger.d("Decrypt", "decryptedAsset : " + decryptedAsset)

    return decryptedAsset
}

fun decryptInsurance(insurance: Insurance): DecryptedInsurance {
    val decryptedInsurance = DecryptedInsurance()

    decryptedInsurance.id = insurance.id
    decryptedInsurance.photosId = insurance.photosId
    decryptedInsurance.backingImages = insurance.backingImages

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
    decryptedInsurance.created = insurance.created
    decryptedInsurance.modified = insurance.modified
    //decryptedInsurance.isPrivate = insurance.isPrivate
    decryptedInsurance.createdUser = insurance.createdUser
    decryptedInsurance.notes = insurance.notes
    decryptedInsurance.attachmentNames = insurance.attachmentNames

    AppLogger.d("Decrypt", "decryptedInsurance : " + decryptedInsurance)
    return decryptedInsurance
}

fun decryptTaxes(taxes: Taxes): DecryptedTax {
    val decryptedTax = DecryptedTax()

    decryptedTax.id = taxes.id
    decryptedTax.photosId = taxes.photosId
    decryptedTax.backingImages = taxes.backingImages

    decryptedTax.selectionType = taxes.selectionType.decryptString()
    decryptedTax.returnName = taxes.returnName.decryptString()
    decryptedTax.taxYear = taxes.taxYear.decryptString()
    decryptedTax.taxPayer = taxes.taxPayer.decryptString()
    decryptedTax.contacts = taxes.contacts.decryptString()
    decryptedTax.imageName = taxes.imageName.decryptString()
    decryptedTax.attachmentNames = taxes.attachmentNames.decryptString()
    decryptedTax.notes = taxes.notes.decryptString()
    decryptedTax.title = taxes.title.decryptString()
    decryptedTax.created = taxes.created
    decryptedTax.modified = taxes.modified
   // decryptedTax.isPrivate = taxes.isPrivate
    decryptedTax.createdUser = taxes.createdUser
    AppLogger.d("Decrypt", "decryptedTax : " + decryptedTax)
    return decryptedTax
}

fun decryptHomeList(homeList: HomeList): DecryptedHomeList {
    val decryptedHomeList = DecryptedHomeList()

    decryptedHomeList.id = homeList.id

    decryptedHomeList.selectionType = homeList.selectionType.decryptString()
    decryptedHomeList.listName = homeList.listName.decryptString()
    decryptedHomeList.dueDate = homeList.dueDate.decryptString()
    decryptedHomeList.detailsId = homeList.detailsId
    decryptedHomeList.selected = homeList.selected
    decryptedHomeList.selectedDate = homeList.selectedDate
    decryptedHomeList.createdDate = homeList.createdDate
    decryptedHomeList.created = homeList.created
    decryptedHomeList.modified = homeList.modified
    decryptedHomeList.private = homeList.private
    decryptedHomeList.createdUser = homeList.createdUser

    AppLogger.d("Decrypt", "decryptedHomeList : " + decryptedHomeList)
    return decryptedHomeList
}

fun decryptEducation(education: Education): DecryptedEducation {
    val decryptedEducation = DecryptedEducation()
    decryptedEducation.id = education.id

    decryptedEducation.selectionType = education.selectionType.decryptString()
    decryptedEducation.institutionName = education.institutionName.decryptString()
    decryptedEducation.accountName = education.accountName.decryptString()
    decryptedEducation.accountType = education.accountType.decryptString()
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
    decryptedEducation.created = education.created
    decryptedEducation.createdUser = education.createdUser
    decryptedEducation.modified = education.modified
    decryptedEducation.private = education.private
    decryptedEducation.backingImages = education.backingImages
    decryptedEducation.photosId = education.photosId

    AppLogger.d("Decrypt", "decryptedEducation : " + decryptedEducation)
    return decryptedEducation
}

fun decryptMainEducation(mainEducation: MainEducation): DecryptedMainEducation {
    val decryptedMainEducation = DecryptedMainEducation()
    decryptedMainEducation.id = mainEducation.id
    decryptedMainEducation.selectionType = mainEducation.selectionType.decryptString()
    decryptedMainEducation.classType = mainEducation.classType
    decryptedMainEducation.institutionName = mainEducation.institutionName.decryptString()
    decryptedMainEducation.qualification = mainEducation.qualification.decryptString()
    decryptedMainEducation.name = mainEducation.name.decryptString()
    decryptedMainEducation.location = mainEducation.location.decryptString()
    decryptedMainEducation.major = mainEducation.major.decryptString()
    decryptedMainEducation.from = mainEducation.from.decryptString()
    decryptedMainEducation.to = mainEducation.to.decryptString()
    decryptedMainEducation.currentlyStudying = mainEducation.currentlyStudying.decryptString()
    decryptedMainEducation.notes = mainEducation.notes.decryptString()
    decryptedMainEducation.created = mainEducation.created
    decryptedMainEducation.modified = mainEducation.modified
    decryptedMainEducation.private = mainEducation.private
    decryptedMainEducation.attachmentNames = mainEducation.attachmentNames.decryptString()
    decryptedMainEducation.createdUser = mainEducation.createdUser
    decryptedMainEducation.backingImages = mainEducation.backingImages
    AppLogger.d("Decrypt", "decryptedMainEducation : " + decryptedMainEducation)
    return decryptedMainEducation
}

fun decryptWork(work: Work): DecryptedWork {
    val decryptedWork = DecryptedWork()
    decryptedWork.id = work.id
    decryptedWork.selectionType = work.selectionType.decryptString()
    decryptedWork.classType = work.classType
    decryptedWork.companyName = work.companyName.decryptString()
    decryptedWork.position = work.position.decryptString()
    decryptedWork.name = work.name.decryptString()
    decryptedWork.location = work.location.decryptString()
    decryptedWork.from = work.from.decryptString()
    decryptedWork.to = work.to.decryptString()
    decryptedWork.currentWork = work.currentWork.decryptString()
    decryptedWork.current = work.current
    decryptedWork.created = work.created
    decryptedWork.modified = work.modified
    decryptedWork.private = work.private
    decryptedWork.notes = work.notes.decryptString()
    decryptedWork.attachmentNames = work.attachmentNames.decryptString()
    decryptedWork.createdUser = work.createdUser
    decryptedWork.backingImages = work.backingImages

    AppLogger.d("Decrypt", "decryptedWork : " + decryptedWork)
    return decryptedWork
}


// For Contacts
fun decryptContact(contactsList: Contacts): DecryptedContacts {
    val decryptedContacts = DecryptedContacts()

    decryptedContacts.id = contactsList.id
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
    decryptedContacts.created = contactsList.created
    decryptedContacts.modified = contactsList.modified
    decryptedContacts.createdUser = contactsList.createdUser
    decryptedContacts.private = contactsList.private
    decryptedContacts.backingImages = contactsList.backingImages

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
    val decryptedCalendarEvents = DecryptedCalendarEvents()
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
    decryptedCalendarEvents.attachmentNames = calendarEvents.attachmentNames.decryptString()
    decryptedCalendarEvents.backingImages = calendarEvents.backingImages
    decryptedCalendarEvents.photosId = calendarEvents.photosId

    return decryptedCalendarEvents
}

fun decryptCertificate(certificate: Certificate): DecryptedCertificate {
    val decryptCertificate = DecryptedCertificate()
    decryptCertificate.id = certificate.id
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
    decryptCertificate.created = certificate.created
    decryptCertificate.modified = certificate.modified
    decryptCertificate.private = certificate.private
    decryptCertificate.attachmentNames = certificate.attachmentNames.decryptString()
    decryptCertificate.createdUser = certificate.createdUser
    decryptCertificate.backingImages = certificate.backingImages

    return decryptCertificate
}

fun decryptGovernment(government: Government): DecryptedGovernment {
    val decryptGovernment = DecryptedGovernment()
    decryptGovernment.id = government.id
    decryptGovernment.selectionType = government.selectionType.decryptString()
    decryptGovernment.idName = government.idName.decryptString()
    decryptGovernment.name = government.name.decryptString()
    decryptGovernment.nameOnId = government.nameOnId.decryptString()
    decryptGovernment.issuingCountry = government.issuingCountry.decryptString()
    decryptGovernment.issuingState = government.issuingState.decryptString()
    decryptGovernment.idNumber = government.idNumber.decryptString()
    decryptGovernment.dateIssued = government.dateIssued.decryptString()
    decryptGovernment.expirationDate = government.expirationDate.decryptString()
    decryptGovernment.created = government.created
    decryptGovernment.modified = government.modified
    decryptGovernment.private = government.private
    decryptGovernment.notes = government.notes.decryptString()
    decryptGovernment.attachmentNames = government.attachmentNames.decryptString()
    decryptGovernment.createdUser = government.createdUser
    decryptGovernment.backingImages = government.backingImages
    return decryptGovernment
}

fun decryptLicense(license: License): DecryptedLicense {
    val decryptLicense = DecryptedLicense()
    decryptLicense.id = license.id
    decryptLicense.selectionType = license.selectionType.decryptString()
    decryptLicense.lic_description = license.lic_description.decryptString()
    decryptLicense.nameOnLicense = license.nameOnLicense.decryptString()
    decryptLicense.issuingCountry = license.issuingCountry.decryptString()
    decryptLicense.issuingState = license.issuingState.decryptString()
    decryptLicense.licenseNumber = license.licenseNumber.decryptString()
    decryptLicense.dateIssued = license.dateIssued.decryptString()
    decryptLicense.expirationDate = license.expirationDate.decryptString()
    decryptLicense.notes = license.notes.decryptString()
    decryptLicense.created = license.created
    decryptLicense.modified = license.modified
    decryptLicense.private = license.private
    decryptLicense.attachmentNames = license.attachmentNames.decryptString()
    decryptLicense.createdUser = license.createdUser
    decryptLicense.backingImages = license.backingImages
    return decryptLicense
}

fun decryptPersonal(personal: Personal): DecryptedPersonal {
    val decryptPersonal = DecryptedPersonal()
    decryptPersonal.id = personal.id
    decryptPersonal.selectionType = personal.selectionType.decryptString()
    decryptPersonal.institutionName = personal.institutionName.decryptString()
    decryptPersonal.accountName = personal.accountName.decryptString()
    decryptPersonal.accountType = personal.accountType.decryptString()
    decryptPersonal.nameOnAccount = personal.nameOnAccount.decryptString()
    decryptPersonal.accountNumber = personal.accountNumber.decryptString()
    decryptPersonal.location = personal.location.decryptString()
    decryptPersonal.swiftCode = personal.swiftCode.decryptString()
    decryptPersonal.abaRoutingNumber = personal.abaRoutingNumber.decryptString()
    decryptPersonal.contacts = personal.contacts.decryptString()
    decryptPersonal.website = personal.website.decryptString()
    decryptPersonal.userName = personal.userName.decryptString()
    decryptPersonal.password = personal.password.decryptString()
    decryptPersonal.pin = personal.pin.decryptString()
    decryptPersonal.paymentMethodOnFile = personal.paymentMethodOnFile.decryptString()
    decryptPersonal.notes = personal.notes.decryptString()
    decryptPersonal.attachmentNames = personal.attachmentNames.decryptString()
    decryptPersonal.title = personal.title.decryptString()
    decryptPersonal.created = personal.created
    decryptPersonal.modified = personal.modified
    decryptPersonal.createdUser = personal.createdUser
    decryptPersonal.backingImages = personal.backingImages
    return decryptPersonal
}

fun decryptSocial(social: Social): DecryptedSocial {
    val decryptSocial = DecryptedSocial()
    decryptSocial.id = social.id
    decryptSocial.selectionType = social.selectionType.decryptString()
    decryptSocial.cardName = social.cardName.decryptString()
    decryptSocial.nameOnCard = social.nameOnCard.decryptString()
    decryptSocial.socialSecurityNumber = social.socialSecurityNumber.decryptString()
    decryptSocial.notes = social.notes.decryptString()
    decryptSocial.created = social.created
    decryptSocial.modified = social.modified
    decryptSocial.private = social.private
    decryptSocial.attachmentNames = social.attachmentNames.decryptString()
    decryptSocial.createdUser = social.createdUser
    decryptSocial.backingImages = social.backingImages
    return decryptSocial
}

fun decryptTaxID(taxID: TaxID): DecryptedTaxID {
    val decryptTaxID = DecryptedTaxID()
    decryptTaxID.id = taxID.id
    decryptTaxID.selectionType = taxID.selectionType.decryptString()
    decryptTaxID.taxIdName = taxID.taxIdName.decryptString()
    decryptTaxID.taxIdNumber = taxID.taxIdNumber.decryptString()
    decryptTaxID.issuingCountry = taxID.issuingCountry.decryptString()
    decryptTaxID.name = taxID.name.decryptString()
    decryptTaxID.notes = taxID.notes.decryptString()
    decryptTaxID.created = taxID.created
    decryptTaxID.modified = taxID.modified
    decryptTaxID.private = taxID.private
    decryptTaxID.attachmentNames = taxID.attachmentNames.decryptString()
    decryptTaxID.backingImages = taxID.backingImages
    return decryptTaxID
}

fun decryptPersonalList(personalList: PersonalList): DecryptedPersonalList {
    val decryptPersonalList = DecryptedPersonalList()
    decryptPersonalList.id = personalList.id
    decryptPersonalList.selectionType = personalList.selectionType.decryptString()
    decryptPersonalList.classType = personalList.classType
    decryptPersonalList.listName = personalList.listName.decryptString()
    decryptPersonalList.dueDate = personalList.dueDate.decryptString()
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

fun decryptCheckUps(checkups: Checkups): DecryptedCheckups {
    val decryptCheckUps = DecryptedCheckups()
    decryptCheckUps.id = checkups.id
    decryptCheckUps.selectionType = checkups.selectionType.decryptString()
    decryptCheckUps.classType = checkups.classType
    decryptCheckUps.physicianName = checkups.physicianName.decryptString()
    decryptCheckUps.checkup_description = checkups.checkup_description.decryptString()
    decryptCheckUps.physicianType = checkups.physicianType.decryptString()
    decryptCheckUps.reason = checkups.reason.decryptString()
    decryptCheckUps.dateOfVisit = checkups.dateOfVisit.decryptString()
    decryptCheckUps.notes = checkups.notes.decryptString()
    decryptCheckUps.attachmentNames = checkups.attachmentNames.decryptString()
    decryptCheckUps.created = checkups.created
    decryptCheckUps.modified = checkups.modified
    decryptCheckUps.createdUser = checkups.createdUser
    decryptCheckUps.private = checkups.private
    decryptCheckUps.backingImages = checkups.backingImages
    decryptCheckUps.photosId = checkups.photosId
    return decryptCheckUps
}

fun decryptEmergencyContacts(emergencyContacts: EmergencyContacts): DecryptedEmergencyContacts {
    val decryptEmergencyContacts = DecryptedEmergencyContacts()
    decryptEmergencyContacts.id = emergencyContacts.id
    decryptEmergencyContacts.selectionType = emergencyContacts.selectionType.decryptString()
    decryptEmergencyContacts.classType = emergencyContacts.classType
    decryptEmergencyContacts.name = emergencyContacts.name.decryptString()
    decryptEmergencyContacts.relationShip = emergencyContacts.relationShip.decryptString()
    decryptEmergencyContacts.phoneNumberOne = emergencyContacts.phoneNumberOne.decryptString()
    decryptEmergencyContacts.phoneNumberTwo = emergencyContacts.phoneNumberTwo.decryptString()
    decryptEmergencyContacts.emailAddress = emergencyContacts.emailAddress.decryptString()
    decryptEmergencyContacts.streetAddressOne = emergencyContacts.streetAddressOne.decryptString()
    decryptEmergencyContacts.streetAddressTwo = emergencyContacts.streetAddressTwo.decryptString()
    decryptEmergencyContacts.city = emergencyContacts.city.decryptString()
    decryptEmergencyContacts.state = emergencyContacts.state.decryptString()
    decryptEmergencyContacts.zipCode = emergencyContacts.zipCode.decryptString()
    decryptEmergencyContacts.country = emergencyContacts.country.decryptString()
    decryptEmergencyContacts.created = emergencyContacts.created
    decryptEmergencyContacts.modified = emergencyContacts.modified
    decryptEmergencyContacts.notes = emergencyContacts.notes.decryptString()
    decryptEmergencyContacts.attachmentNames = emergencyContacts.attachmentNames.decryptString()
    decryptEmergencyContacts.createdUser = emergencyContacts.createdUser
    decryptEmergencyContacts.private = emergencyContacts.private
    decryptEmergencyContacts.backingImages = emergencyContacts.backingImages
    return decryptEmergencyContacts
}

fun decryptEyeGlassPrescriptions(eyeglassPrescriptions: EyeglassPrescriptions): DecryptedEyeglassPrescriptions {
    val decryptEyeGlassPrescriptions = DecryptedEyeglassPrescriptions()
    decryptEyeGlassPrescriptions.id = eyeglassPrescriptions.id
    decryptEyeGlassPrescriptions.selectionType = eyeglassPrescriptions.selectionType.decryptString()
    decryptEyeGlassPrescriptions.classType = eyeglassPrescriptions.classType
    decryptEyeGlassPrescriptions.physicianName = eyeglassPrescriptions.physicianName.decryptString()
    decryptEyeGlassPrescriptions.datePrescribed = eyeglassPrescriptions.datePrescribed.decryptString()
    decryptEyeGlassPrescriptions.odSphereValue = eyeglassPrescriptions.odSphereValue.decryptString()
    decryptEyeGlassPrescriptions.osSphereValue = eyeglassPrescriptions.osSphereValue.decryptString()
    decryptEyeGlassPrescriptions.odCylinderValue = eyeglassPrescriptions.odCylinderValue.decryptString()
    decryptEyeGlassPrescriptions.osCylinderValue = eyeglassPrescriptions.osCylinderValue.decryptString()
    decryptEyeGlassPrescriptions.odAxisValue = eyeglassPrescriptions.odAxisValue.decryptString()
    decryptEyeGlassPrescriptions.osAxisValue = eyeglassPrescriptions.osAxisValue.decryptString()
    decryptEyeGlassPrescriptions.odPrismValue = eyeglassPrescriptions.odPrismValue.decryptString()
    decryptEyeGlassPrescriptions.osPrismValue = eyeglassPrescriptions.osPrismValue.decryptString()
    decryptEyeGlassPrescriptions.odAddValue = eyeglassPrescriptions.odAddValue.decryptString()
    decryptEyeGlassPrescriptions.osAddValue = eyeglassPrescriptions.osAddValue.decryptString()
    decryptEyeGlassPrescriptions.odBaseValue = eyeglassPrescriptions.odBaseValue.decryptString()
    decryptEyeGlassPrescriptions.osBaseValue = eyeglassPrescriptions.osBaseValue.decryptString()
    decryptEyeGlassPrescriptions.notes = eyeglassPrescriptions.notes.decryptString()
    decryptEyeGlassPrescriptions.attachmentNames = eyeglassPrescriptions.attachmentNames.decryptString()
    decryptEyeGlassPrescriptions.created = eyeglassPrescriptions.created
    decryptEyeGlassPrescriptions.modified = eyeglassPrescriptions.modified
    decryptEyeGlassPrescriptions.createdUser = eyeglassPrescriptions.createdUser
    decryptEyeGlassPrescriptions.private = eyeglassPrescriptions.private
    decryptEyeGlassPrescriptions.backingImages = eyeglassPrescriptions.backingImages
    return decryptEyeGlassPrescriptions
}

fun decryptHealthCareProviders(healthcareProviders: HealthcareProviders): DecryptedHealthcareProviders {
    val decryptHealthCareProviders = DecryptedHealthcareProviders()
    decryptHealthCareProviders.id = healthcareProviders.id
    decryptHealthCareProviders.selectionType = healthcareProviders.selectionType.decryptString()
    decryptHealthCareProviders.classType = healthcareProviders.classType
    decryptHealthCareProviders.name = healthcareProviders.name.decryptString()
    decryptHealthCareProviders.physicianType = healthcareProviders.physicianType.decryptString()
    decryptHealthCareProviders.practiceName = healthcareProviders.practiceName.decryptString()
    decryptHealthCareProviders.phoneNumberOne = healthcareProviders.phoneNumberOne.decryptString()
    decryptHealthCareProviders.phoneNumberTwo = healthcareProviders.phoneNumberTwo.decryptString()
    decryptHealthCareProviders.emailAddress = healthcareProviders.emailAddress.decryptString()
    decryptHealthCareProviders.streetAddressOne = healthcareProviders.streetAddressOne.decryptString()
    decryptHealthCareProviders.streetAddressTwo = healthcareProviders.streetAddressTwo.decryptString()
    decryptHealthCareProviders.city = healthcareProviders.city.decryptString()
    decryptHealthCareProviders.state = healthcareProviders.state.decryptString()
    decryptHealthCareProviders.zipCode = healthcareProviders.zipCode.decryptString()
    decryptHealthCareProviders.country = healthcareProviders.country.decryptString()
    decryptHealthCareProviders.created = healthcareProviders.created
    decryptHealthCareProviders.modified = healthcareProviders.modified
    decryptHealthCareProviders.notes = healthcareProviders.notes.decryptString()
    decryptHealthCareProviders.attachmentNames = healthcareProviders.attachmentNames.decryptString()
    decryptHealthCareProviders.createdUser = healthcareProviders.createdUser
    decryptHealthCareProviders.private = healthcareProviders.private
    decryptHealthCareProviders.backingImages = healthcareProviders.backingImages
    return decryptHealthCareProviders
}

fun decryptIdentification(identification: Identification): DecryptedIdentification {
    val decryptHomeList = DecryptedIdentification()
    decryptHomeList.id = identification.id
    decryptHomeList.selectionType = identification.selectionType.decryptString()
    decryptHomeList.classType = identification.classType
    decryptHomeList.name = identification.name.decryptString()
    decryptHomeList.gender = identification.gender.decryptString()
    decryptHomeList.dateofBirth = identification.dateofBirth.decryptString()
    decryptHomeList.age = identification.age.decryptString()
    decryptHomeList.height = identification.height.decryptString()
    decryptHomeList.weight = identification.weight.decryptString()
    decryptHomeList.hairColor = identification.hairColor.decryptString()
    decryptHomeList.eyeColor = identification.eyeColor.decryptString()
    decryptHomeList.visibleMarks = identification.visibleMarks.decryptString()
    decryptHomeList.bloodType = identification.bloodType.decryptString()
    decryptHomeList.orgonDonor = identification.orgonDonor.decryptString()
    decryptHomeList.created = identification.created
    decryptHomeList.modified = identification.modified
    decryptHomeList.notes = identification.notes.decryptString()
    decryptHomeList.attachmentNames = identification.attachmentNames.decryptString()
    decryptHomeList.createdUser = identification.createdUser
    decryptHomeList.private = identification.private
    decryptHomeList.backingImages = identification.backingImages
    return decryptHomeList
}

fun decryptMedicalConditions(medicalConditions: MedicalConditions): DecryptedMedicalConditions {
    val decryptMedicalConditions = DecryptedMedicalConditions()
    decryptMedicalConditions.id = medicalConditions.id
    decryptMedicalConditions.selectionType = medicalConditions.selectionType.decryptString()
    decryptMedicalConditions.classType = medicalConditions.classType
    decryptMedicalConditions.condition = medicalConditions.condition.decryptString()
    decryptMedicalConditions.dateDiagnosed = medicalConditions.dateDiagnosed.decryptString()
    decryptMedicalConditions.medi_description = medicalConditions.medi_description.decryptString()
    decryptMedicalConditions.notes = medicalConditions.notes.decryptString()
    decryptMedicalConditions.attachmentNames = medicalConditions.attachmentNames.decryptString()
    decryptMedicalConditions.created = medicalConditions.created
    decryptMedicalConditions.modified = medicalConditions.modified
    decryptMedicalConditions.createdUser = medicalConditions.createdUser
    decryptMedicalConditions.backingImages = medicalConditions.backingImages
    decryptMedicalConditions.private = medicalConditions.private
    return decryptMedicalConditions
}

fun decryptMedicalHistory(medicalHistory: MedicalHistory): DecryptedMedicalHistory {
    val decryptMedicalHistory = DecryptedMedicalHistory()
    decryptMedicalHistory.id = medicalHistory.id
    decryptMedicalHistory.selectionType = medicalHistory.selectionType.decryptString()
    decryptMedicalHistory.classType = medicalHistory.classType
    decryptMedicalHistory.history = medicalHistory.history.decryptString()
    decryptMedicalHistory.treatmentDiscription = medicalHistory.treatmentDiscription.decryptString()
    decryptMedicalHistory.immunizationDiscription = medicalHistory.immunizationDiscription.decryptString()
    decryptMedicalHistory.familyDiscription = medicalHistory.familyDiscription.decryptString()
    decryptMedicalHistory.created = medicalHistory.created
    decryptMedicalHistory.modified = medicalHistory.modified
    decryptMedicalHistory.notes = medicalHistory.notes.decryptString()
    decryptMedicalHistory.attachmentNames = medicalHistory.attachmentNames.decryptString()
    decryptMedicalHistory.createdUser = medicalHistory.createdUser
    decryptMedicalHistory.private = medicalHistory.private
    decryptMedicalHistory.backingImages = medicalHistory.backingImages
    return decryptMedicalHistory
}

fun decryptMedications(medications: Medications): DecryptedMedications {
    val decryptMedications = DecryptedMedications()
    decryptMedications.id = medications.id
    decryptMedications.selectionType = medications.selectionType.decryptString()
    decryptMedications.classType = medications.classType
    decryptMedications.name = medications.name.decryptString()
    decryptMedications.strength = medications.strength.decryptString()
    decryptMedications.frequency = medications.frequency.decryptString()
    decryptMedications.startDate = medications.startDate.decryptString()
    decryptMedications.endDate = medications.endDate.decryptString()
    decryptMedications.notes = medications.notes.decryptString()
    decryptMedications.attachmentNames = medications.attachmentNames.decryptString()
    decryptMedications.created = medications.created
    decryptMedications.modified = medications.modified
    decryptMedications.createdUser = medications.createdUser
    decryptMedications.private = medications.private
    decryptMedications.backingImages = medications.backingImages
    return decryptMedications
}

fun decryptVitalNumbers(vitalNumbers: VitalNumbers): DecryptedVitalNumbers {
    val decryptVitalNumbers = DecryptedVitalNumbers()
    decryptVitalNumbers.id = vitalNumbers.id
    decryptVitalNumbers.selectionType = vitalNumbers.selectionType.decryptString()
    decryptVitalNumbers.classType = vitalNumbers.classType
    decryptVitalNumbers.vital_description = vitalNumbers.vital_description.decryptString()
    decryptVitalNumbers.measurementDate = vitalNumbers.measurementDate.decryptString()
    decryptVitalNumbers.height = vitalNumbers.height.decryptString()
    decryptVitalNumbers.weight = vitalNumbers.weight.decryptString()
    decryptVitalNumbers.waist = vitalNumbers.waist.decryptString()
    decryptVitalNumbers.bodyFat = vitalNumbers.bodyFat.decryptString()
    decryptVitalNumbers.bodyMassIndex = vitalNumbers.bodyMassIndex.decryptString()
    decryptVitalNumbers.bloodPressure = vitalNumbers.bloodPressure.decryptString()
    decryptVitalNumbers.heartRate = vitalNumbers.heartRate.decryptString()
    decryptVitalNumbers.totalCholesterol = vitalNumbers.totalCholesterol.decryptString()
    decryptVitalNumbers.hdlCholesterol = vitalNumbers.hdlCholesterol.decryptString()
    decryptVitalNumbers.ldlCholesterol = vitalNumbers.ldlCholesterol.decryptString()
    decryptVitalNumbers.cholesterolRatio = vitalNumbers.cholesterolRatio.decryptString()
    decryptVitalNumbers.triglycerides = vitalNumbers.triglycerides.decryptString()
    decryptVitalNumbers.bloodGlucose = vitalNumbers.bloodGlucose.decryptString()
    decryptVitalNumbers.hemoglobin = vitalNumbers.hemoglobin.decryptString()
    decryptVitalNumbers.created = vitalNumbers.created
    decryptVitalNumbers.modified = vitalNumbers.modified
    decryptVitalNumbers.notes = vitalNumbers.notes.decryptString()
    decryptVitalNumbers.attachmentNames = vitalNumbers.attachmentNames.decryptString()
    decryptVitalNumbers.createdUser = vitalNumbers.createdUser
    decryptVitalNumbers.private = vitalNumbers.private
    decryptVitalNumbers.backingImages = vitalNumbers.backingImages
    return decryptVitalNumbers
}

fun decryptWellness(wellness: Wellness): DecryptedWellness {
    val decryptWellness = DecryptedWellness()
    decryptWellness.id = wellness.id
    decryptWellness.selectionType = wellness.selectionType.decryptString()
    decryptWellness.institutionName = wellness.institutionName.decryptString()
    decryptWellness.accountName = wellness.accountName.decryptString()
    decryptWellness.accountType = wellness.accountType.decryptString()
    decryptWellness.nameOnAccount = wellness.nameOnAccount.decryptString()
    decryptWellness.accountNumber = wellness.accountNumber.decryptString()
    decryptWellness.location = wellness.location.decryptString()
    decryptWellness.swiftCode = wellness.swiftCode.decryptString()
    decryptWellness.abaRoutingNumber = wellness.abaRoutingNumber.decryptString()
    decryptWellness.contacts = wellness.contacts.decryptString()
    decryptWellness.website = wellness.website.decryptString()
    decryptWellness.userName = wellness.userName.decryptString()
    decryptWellness.password = wellness.password.decryptString()
    decryptWellness.pin = wellness.pin.decryptString()
    decryptWellness.paymentMethodOnFile = wellness.paymentMethodOnFile.decryptString()
    decryptWellness.notes = wellness.notes.decryptString()
    decryptWellness.attachmentNames = wellness.attachmentNames.decryptString()
    decryptWellness.title = wellness.title.decryptString()
    decryptWellness.created = wellness.created
    decryptWellness.modified = wellness.modified
    decryptWellness.createdUser = wellness.createdUser
    decryptWellness.private = wellness.private
    decryptWellness.backingImages = wellness.backingImages
    return decryptWellness
}

fun decryptWellnessList(wellnessList: WellnessList): DecryptedWellnessList {
    val decryptWellnessList = DecryptedWellnessList()
    decryptWellnessList.id = wellnessList.id
    decryptWellnessList.selectionType = wellnessList.selectionType.decryptString()
    decryptWellnessList.classType = wellnessList.classType
    decryptWellnessList.listName = wellnessList.listName.decryptString()
    decryptWellnessList.dueDate = wellnessList.dueDate.decryptString()
    decryptWellnessList.created = wellnessList.created
    decryptWellnessList.modified = wellnessList.modified
    decryptWellnessList.createdUser = wellnessList.createdUser
    decryptWellnessList.private = wellnessList.private
    return decryptWellnessList
}

fun decryptedClothingSizes(clothingSizes: ClothingSizes): DecryptedClothingSizes {
    val decryptClothingSizes = DecryptedClothingSizes()
    decryptClothingSizes.id = clothingSizes.id
    decryptClothingSizes.selectionType = clothingSizes.selectionType.decryptString()
    decryptClothingSizes.classType = clothingSizes.classType
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
    decryptClothingSizes.created = clothingSizes.created
    decryptClothingSizes.modified = clothingSizes.modified
    decryptClothingSizes.createdUser = clothingSizes.createdUser
    decryptClothingSizes.private = clothingSizes.private
    decryptClothingSizes.backingImages = clothingSizes.backingImages
    return decryptClothingSizes
}

fun decryptContactsList(contactsList: ContactsList): DecryptedContactsList {
    val decryptContactList = DecryptedContactsList()
    decryptContactList.id = contactsList.id
    decryptContactList.selectionType = contactsList.selectionType.decryptString()
    decryptContactList.classType = contactsList.classType.decryptString()
    decryptContactList.listName = contactsList.listName
    decryptContactList.dueDate = contactsList.dueDate.decryptString()
    decryptContactList.created = contactsList.created
    decryptContactList.modified = contactsList.modified
    decryptContactList.createdUser = contactsList.createdUser
    decryptContactList.private = contactsList.private
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
    decryptDocuments.id = documents.id
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
    decryptDocuments.created = documents.created
    decryptDocuments.modified = documents.modified
    decryptDocuments.createdUser = documents.createdUser
    return decryptDocuments
}

fun decryptEducationList(educationList: EducationList): DecryptedEducationList {
    val decryptEducationList = DecryptedEducationList()
    decryptEducationList.id = educationList.id
    decryptEducationList.selectionType = educationList.selectionType.decryptString()
    decryptEducationList.classType = educationList.classType
    decryptEducationList.listName = educationList.listName.decryptString()
    decryptEducationList.dueDate = educationList.dueDate.decryptString()
    decryptEducationList.created = educationList.created
    decryptEducationList.modified = educationList.modified
    decryptEducationList.createdUser = educationList.createdUser
    decryptEducationList.private = educationList.private
    return decryptEducationList
}

fun decrypytHash(hash: Hash): DecryptedHash {
    val decryptHash = DecryptedHash()
    decryptHash.finalPassword = hash.finalPassword.decryptString()
    decryptHash.passcode = hash.passcode.decryptString()
    return decryptHash
}

fun decryptHomeAndBanking(homeBankingList: HomeBankingList): DecryptedHomeBankingList {
    val decryptHomeAndBanking = DecryptedHomeBankingList()
    decryptHomeAndBanking.id = homeBankingList.id
    decryptHomeAndBanking.selectionType = homeBankingList.selectionType.decryptString()
    decryptHomeAndBanking.listName = homeBankingList.listName.decryptString()
    decryptHomeAndBanking.dueDate = homeBankingList.dueDate.decryptString()
    decryptHomeAndBanking.created = homeBankingList.created
    decryptHomeAndBanking.modified = homeBankingList.modified
    decryptHomeAndBanking.private = homeBankingList.private
    return decryptHomeAndBanking
}

fun decryptInterests(interest: Interests): DecryptedInterests {
    val decryptInterests = DecryptedInterests()
    decryptInterests.id = interest.id
    decryptInterests.selectionType = interest.selectionType.decryptString()
    decryptInterests.institutionName = interest.institutionName.decryptString()
    decryptInterests.accountName = interest.accountName.decryptString()
    decryptInterests.accountType = interest.accountType.decryptString()
    decryptInterests.nameOnAccount = interest.nameOnAccount.decryptString()
    decryptInterests.accountNumber = interest.accountNumber.decryptString()
    decryptInterests.location = interest.location.decryptString()
    decryptInterests.swiftCode = interest.swiftCode.decryptString()
    decryptInterests.abaRoutingNumber = interest.abaRoutingNumber.decryptString()
    decryptInterests.contacts = interest.contacts.decryptString()
    decryptInterests.website = interest.website.decryptString()
    decryptInterests.userName = interest.userName.decryptString()
    decryptInterests.password = interest.password.decryptString()
    decryptInterests.pin = interest.pin.decryptString()
    decryptInterests.paymentMethodOnFile = interest.paymentMethodOnFile.decryptString()
    decryptInterests.notes = interest.notes.decryptString()
    decryptInterests.attachmentNames = interest.attachmentNames.decryptString()
    decryptInterests.title = interest.title.decryptString()
    decryptInterests.created = interest.created
    decryptInterests.modified = interest.modified
    decryptInterests.createdUser = interest.createdUser
    decryptInterests.private = interest.private
    return decryptInterests
}

fun decryptInterestList(interestsList: InterestsList): DecryptedInterestsList {
    val decryptInterestList = DecryptedInterestsList()
    decryptInterestList.id = interestsList.id
    decryptInterestList.selectionType = interestsList.selectionType.decryptString()
    decryptInterestList.classType = interestsList.classType
    decryptInterestList.listName = interestsList.listName.decryptString()
    decryptInterestList.dueDate = interestsList.dueDate.decryptString()
    decryptInterestList.created = interestsList.created
    decryptInterestList.modified = interestsList.modified
    decryptInterestList.createdUser = interestsList.createdUser
    decryptInterestList.private = interestsList.private
    return decryptInterestList
}

fun decryptLoyality(loyalty: Loyalty): DecryptedLoyalty {
    val decryptLoyality = DecryptedLoyalty()
    decryptLoyality.id = loyalty.id
    decryptLoyality.selectionType = loyalty.selectionType.decryptString()
    decryptLoyality.airLine = loyalty.airLine.decryptString()
    decryptLoyality.hotel = loyalty.hotel.decryptString()
    decryptLoyality.carRentalCompany = loyalty.carRentalCompany.decryptString()
    decryptLoyality.cruiseline = loyalty.cruiseline.decryptString()
    decryptLoyality.railway = loyalty.railway.decryptString()
    decryptLoyality.other = loyalty.other.decryptString()
    decryptLoyality.accountName = loyalty.accountName.decryptString()
    decryptLoyality.nameOnAccount = loyalty.nameOnAccount.decryptString()
    decryptLoyality.accountNumber = loyalty.accountNumber.decryptString()
    decryptLoyality.website = loyalty.website.decryptString()
    decryptLoyality.userName = loyalty.userName.decryptString()
    decryptLoyality.password = loyalty.password.decryptString()
    decryptLoyality.pin = loyalty.pin.decryptString()
    decryptLoyality.notes = loyalty.notes.decryptString()
    decryptLoyality.attachmentNames = loyalty.attachmentNames.decryptString()
    decryptLoyality.created = loyalty.created
    decryptLoyality.modified = loyalty.modified
    decryptLoyality.createdUser = loyalty.createdUser
    decryptLoyality.private = loyalty.private
    return decryptLoyality
}

fun decryptLoyaltyPrograms(loyalityPrograms: LoyaltyPrograms): DecryptedLoyaltyPrograms {
    val decryptLoyaltyPrograms = DecryptedLoyaltyPrograms()
    decryptLoyaltyPrograms.id = loyalityPrograms.id
    decryptLoyaltyPrograms.selectionType = loyalityPrograms.selectionType.decryptString()
    decryptLoyaltyPrograms.brandName = loyalityPrograms.brandName.decryptString()
    decryptLoyaltyPrograms.accountName = loyalityPrograms.accountName.decryptString()
    decryptLoyaltyPrograms.nameOnAccount = loyalityPrograms.nameOnAccount.decryptString()
    decryptLoyaltyPrograms.accountNumber = loyalityPrograms.accountNumber.decryptString()
    decryptLoyaltyPrograms.website = loyalityPrograms.website.decryptString()
    decryptLoyaltyPrograms.userName = loyalityPrograms.userName.decryptString()
    decryptLoyaltyPrograms.password = loyalityPrograms.password.decryptString()
    decryptLoyaltyPrograms.pin = loyalityPrograms.pin.decryptString()
    decryptLoyaltyPrograms.notes = loyalityPrograms.notes.decryptString()
    decryptLoyaltyPrograms.attachmentNames = loyalityPrograms.attachmentNames.decryptString()
    decryptLoyaltyPrograms.created = loyalityPrograms.created
    decryptLoyaltyPrograms.modified = loyalityPrograms.modified
    decryptLoyaltyPrograms.createdUser = loyalityPrograms.createdUser
    decryptLoyaltyPrograms.private = loyalityPrograms.private
    return decryptLoyaltyPrograms
}

fun decryptMainContacts(mainContacts: MainContacts): DecryptedMainContacts {
    val decryptMainContacts = DecryptedMainContacts()
    decryptMainContacts.id = mainContacts.id
    decryptMainContacts.selectionType = decryptMainContacts.selectionType.decryptString()
    decryptMainContacts.institutionName = decryptMainContacts.institutionName.decryptString()
    decryptMainContacts.accountName = decryptMainContacts.accountName.decryptString()
    decryptMainContacts.accountType = decryptMainContacts.accountType.decryptString()
    decryptMainContacts.nameOnAccount = decryptMainContacts.nameOnAccount.decryptString()
    decryptMainContacts.accountNumber = decryptMainContacts.accountNumber.decryptString()
    decryptMainContacts.location = decryptMainContacts.location.decryptString()
    decryptMainContacts.swiftCode = decryptMainContacts.swiftCode.decryptString()
    decryptMainContacts.abaRoutingNumber = decryptMainContacts.abaRoutingNumber.decryptString()
    decryptMainContacts.contacts = decryptMainContacts.contacts.decryptString()
    decryptMainContacts.website = decryptMainContacts.website.decryptString()
    decryptMainContacts.userName = decryptMainContacts.userName.decryptString()
    decryptMainContacts.password = decryptMainContacts.password.decryptString()
    decryptMainContacts.pin = decryptMainContacts.pin.decryptString()
    decryptMainContacts.paymentMethodOnFile = decryptMainContacts.paymentMethodOnFile.decryptString()
    decryptMainContacts.notes = decryptMainContacts.notes.decryptString()
    decryptMainContacts.attachmentNames = decryptMainContacts.attachmentNames.decryptString()
    decryptMainContacts.title = decryptMainContacts.title.decryptString()
    decryptMainContacts.created = decryptMainContacts.created
    decryptMainContacts.modified = decryptMainContacts.modified
    decryptMainContacts.createdUser = decryptMainContacts.createdUser
    return decryptMainContacts
}

fun decrypytMainEducation(mainEducation: MainEducation): DecryptedMainEducation {
    val decrypytMainEducation = DecryptedMainEducation()
    decrypytMainEducation.id = mainEducation.id
    decrypytMainEducation.selectionType = mainEducation.selectionType.decryptString()
    decrypytMainEducation.classType = mainEducation.classType
    decrypytMainEducation.institutionName = mainEducation.institutionName.decryptString()
    decrypytMainEducation.qualification = mainEducation.qualification.decryptString()
    decrypytMainEducation.name = mainEducation.name.decryptString()
    decrypytMainEducation.location = mainEducation.location.decryptString()
    decrypytMainEducation.major = mainEducation.major.decryptString()
    decrypytMainEducation.from = mainEducation.from.decryptString()
    decrypytMainEducation.to = mainEducation.to.decryptString()
    decrypytMainEducation.currentlyStudying = mainEducation.currentlyStudying.decryptString()
    decrypytMainEducation.notes = mainEducation.notes.decryptString()
    decrypytMainEducation.created = mainEducation.created
    decrypytMainEducation.modified = mainEducation.modified
    decrypytMainEducation.attachmentNames = mainEducation.attachmentNames.decryptString()
    decrypytMainEducation.createdUser = mainEducation.createdUser
    decrypytMainEducation.private = mainEducation.private
    decrypytMainEducation.backingImages = mainEducation.backingImages
    return decrypytMainEducation
}

fun decryptMainMemories(mainMemories: MainMemories): DecryptedMainMemories {
    val decrypytMainMemories = DecryptedMainMemories()
    decrypytMainMemories.id = mainMemories.id
    decrypytMainMemories.selectionType = mainMemories.selectionType.decryptString()
    decrypytMainMemories.accountName = mainMemories.accountName.decryptString()
    decrypytMainMemories.institutionName = mainMemories.institutionName.decryptString()
    decrypytMainMemories.accountType = mainMemories.accountType.decryptString()
    decrypytMainMemories.nameOnAccount = mainMemories.nameOnAccount.decryptString()
    decrypytMainMemories.location = mainMemories.location.decryptString()
    decrypytMainMemories.accountNumber = mainMemories.accountNumber.decryptString()
    decrypytMainMemories.location = mainMemories.location.decryptString()
    decrypytMainMemories.swiftCode = mainMemories.swiftCode.decryptString()
    decrypytMainMemories.abaRoutingNumber = mainMemories.abaRoutingNumber.decryptString()
    decrypytMainMemories.contacts = mainMemories.contacts.decryptString()
    decrypytMainMemories.website = mainMemories.website.decryptString()
    decrypytMainMemories.userName = mainMemories.userName.decryptString()
    decrypytMainMemories.password = mainMemories.password.decryptString()
    decrypytMainMemories.pin = mainMemories.pin.decryptString()
    decrypytMainMemories.created = mainMemories.created
    decrypytMainMemories.modified = mainMemories.modified
    decrypytMainMemories.notes = mainMemories.notes.decryptString()
    decrypytMainMemories.attachmentNames = mainMemories.attachmentNames.decryptString()
    decrypytMainMemories.title = mainMemories.title.decryptString()
    decrypytMainMemories.createdUser = mainMemories.createdUser

    return decrypytMainMemories
}

fun decryptMemoriesList(memoriesList: MemoriesList): DecryptedMemoriesList {
    val decryptMemoriesList = DecryptedMemoriesList()
    decryptMemoriesList.id = memoriesList.id
    decryptMemoriesList.selectionType = memoriesList.selectionType.decryptString()
    decryptMemoriesList.classType = memoriesList.classType
    decryptMemoriesList.listName = memoriesList.listName.decryptString()
    decryptMemoriesList.dueDate = memoriesList.dueDate.decryptString()
    decryptMemoriesList.created = memoriesList.created
    decryptMemoriesList.modified = memoriesList.modified
    decryptMemoriesList.createdUser = memoriesList.createdUser.decryptString()
    decryptMemoriesList.private = memoriesList.private
    return decryptMemoriesList
}

fun decryptMemoryTimeLine(memoryTimeline: MemoryTimeline): DecryptedMemoryTimeline {
    var decryptMemoryTimeLine = DecryptedMemoryTimeline()
   /* decryptMemoryTimeLine.id = memoryTimeline.id*/
    decryptMemoryTimeLine.selectionType = memoryTimeline.selectionType.decryptString()
    decryptMemoryTimeLine.title = memoryTimeline.title.decryptString()
    decryptMemoryTimeLine.date = memoryTimeline.date.decryptString()
    decryptMemoryTimeLine.place = memoryTimeline.place.decryptString()
    decryptMemoryTimeLine.contacts = memoryTimeline.contacts.decryptString()
    decryptMemoryTimeLine.notes = memoryTimeline.notes.decryptString()
    decryptMemoryTimeLine.attachmentNames = memoryTimeline.attachmentNames.decryptString()
    decryptMemoryTimeLine.created = memoryTimeline.created
    decryptMemoryTimeLine.modified = memoryTimeline.modified
    decryptMemoryTimeLine.createdUser = memoryTimeline.createdUser
    decryptMemoryTimeLine.private = memoryTimeline.private
    return decryptMemoryTimeLine
}

fun decryptNotifications(notifications: Notifications): DecryptedNotifications {
    val decryptNotifications = DecryptedNotifications()
    decryptNotifications.id = notifications.id
    decryptNotifications.message = notifications.message.decryptString()
    decryptNotifications.boxName = notifications.boxName.decryptString()
    decryptNotifications.dueDate = notifications.dueDate
    decryptNotifications.subTitle = notifications.subTitle.decryptString()
    decryptNotifications.notifyDate = notifications.notifyDate
    decryptNotifications.read = notifications.read
    decryptNotifications.created = notifications.created
    decryptNotifications.modified = notifications.modified
    return decryptNotifications
}

fun decryptRecentPurchase(recentPurchase: RecentPurchase): DecryptedRecentPurchase {
    val decryptRecentPurchase = DecryptedRecentPurchase()
    decryptRecentPurchase.id = recentPurchase.id
    decryptRecentPurchase.selectionType = recentPurchase.selectionType.decryptString()
    decryptRecentPurchase.brandName = recentPurchase.brandName.decryptString()
    decryptRecentPurchase.itemName = recentPurchase.itemName.decryptString()
    decryptRecentPurchase.purchasedBy = recentPurchase.purchasedBy.decryptString()
    decryptRecentPurchase.purchasedDate = recentPurchase.purchasedDate.decryptString()
    decryptRecentPurchase.purchasedPrice = recentPurchase.purchasedPrice.decryptString()
    decryptRecentPurchase.notes = recentPurchase.notes.decryptString()
    decryptRecentPurchase.created = recentPurchase.created
    decryptRecentPurchase.modified = recentPurchase.modified
    decryptRecentPurchase.attachmentNames = recentPurchase.attachmentNames.decryptString()
    decryptRecentPurchase.createdUser = recentPurchase.createdUser
    return decryptRecentPurchase
}

fun decryptRecentSearch(recentSearch: RecentSearch?): DecryptedRecentSearch {
    val decryptRecentSearch = DecryptedRecentSearch()
    decryptRecentSearch.id = recentSearch!!.id
    decryptRecentSearch.listName = recentSearch!!.listName.decryptString()
    decryptRecentSearch.subCategory = recentSearch!!.subCategory.decryptString()
    decryptRecentSearch.mainCategory = recentSearch!!.mainCategory.decryptString()
    decryptRecentSearch.classType = recentSearch!!.classType
    return decryptRecentSearch
}

fun decryptShopping(shopping: Shopping): DecryptedShopping {
    val decryptShopping = DecryptedShopping()
    decryptShopping.id = shopping.id
    decryptShopping.selectionType = shopping.selectionType.decryptString()
    decryptShopping.institutionName = shopping.institutionName.decryptString()
    decryptShopping.accountName = shopping.accountName.decryptString()
    decryptShopping.accountType = shopping.accountType.decryptString()
    decryptShopping.nameOnAccount = shopping.nameOnAccount.decryptString()
    decryptShopping.accountNumber = shopping.accountNumber.decryptString()
    decryptShopping.location = shopping.location.decryptString()
    decryptShopping.swiftCode = shopping.swiftCode.decryptString()
    decryptShopping.abaRoutingNumber = shopping.abaRoutingNumber.decryptString()
    decryptShopping.contacts = shopping.contacts.decryptString()
    decryptShopping.website = shopping.website.decryptString()
    decryptShopping.userName = shopping.userName.decryptString()
    decryptShopping.password = shopping.password.decryptString()
    decryptShopping.pin = shopping.pin.decryptString()
    decryptShopping.paymentMethodOnFile = shopping.paymentMethodOnFile.decryptString()
    decryptShopping.notes = shopping.notes.decryptString()
    decryptShopping.imageName = shopping.imageName.decryptString()
    decryptShopping.attachmentNames = shopping.attachmentNames.decryptString()
    decryptShopping.title = shopping.title.decryptString()
    decryptShopping.created = shopping.created
    decryptShopping.modified = shopping.modified

    return decryptShopping
}

fun decryptShoppingList(shoppingList: ShoppingList): DecryptedShoppingList {
    val decryptShoppingList = DecryptedShoppingList()
    decryptShoppingList.id = shoppingList.id
    decryptShoppingList.selectionType = shoppingList.selectionType.decryptString()
    decryptShoppingList.classType = shoppingList.classType
    decryptShoppingList.listName = shoppingList.listName.decryptString()
    decryptShoppingList.dueDate = shoppingList.dueDate.decryptString()
    decryptShoppingList.created = shoppingList.created
    decryptShoppingList.modified = shoppingList.modified
    decryptShoppingList.createdUser = shoppingList.createdUser
    decryptShoppingList.private = shoppingList.private
    return decryptShoppingList
}

fun decryptSignUp(signUp: SignUp): DecryptedSignUp {
    val decryptSignUp = DecryptedSignUp()
    decryptSignUp.id = signUp.id
    decryptSignUp.fullName = signUp.fullName.decryptString()
    decryptSignUp.emailAddress = signUp.emailAddress.decryptString()
    decryptSignUp.password = signUp.password.decryptString()
    decryptSignUp.user_id = signUp.user_id.decryptString()
    return decryptSignUp
}

fun decryptTravel(travel: Travel): DecryptedTravel {
    val decryptTravel = DecryptedTravel()
    decryptTravel.id = travel.id
    decryptTravel.selectionType = travel.selectionType.decryptString()
    decryptTravel.institutionName = travel.institutionName.decryptString()
    decryptTravel.accountName = travel.accountName.decryptString()
    decryptTravel.accountType = travel.accountType.decryptString()
    decryptTravel.nameOnAccount = travel.nameOnAccount.decryptString()
    decryptTravel.accountNumber = travel.accountNumber.decryptString()
    decryptTravel.location = travel.location.decryptString()
    decryptTravel.swiftCode = travel.swiftCode.decryptString()
    decryptTravel.abaRoutingNumber = travel.abaRoutingNumber.decryptString()
    decryptTravel.contacts = travel.contacts.decryptString()
    decryptTravel.website = travel.website.decryptString()
    decryptTravel.userName = travel.userName.decryptString()
    decryptTravel.password = travel.password.decryptString()
    decryptTravel.pin = travel.pin.decryptString()
    decryptTravel.paymentMethodOnFile = travel.paymentMethodOnFile.decryptString()
    decryptTravel.notes = travel.notes.decryptString()
    decryptTravel.imageName = travel.imageName.decryptString()
    decryptTravel.attachmentNames = travel.attachmentNames.decryptString()
    decryptTravel.title = travel.title.decryptString()
    decryptTravel.created = travel.created
    decryptTravel.modified = travel.modified
    decryptTravel.createdUser = travel.createdUser
    return decryptTravel

}

fun decryptTravelList(travelList: TravelList): DecryptedTravelList {
    val decryptTravelList = DecryptedTravelList()
    decryptTravelList.id = travelList.id
    decryptTravelList.selectionType = travelList.selectionType.decryptString()
    decryptTravelList.classType = travelList.classType.decryptString()
    decryptTravelList.listName = travelList.listName.decryptString()
    decryptTravelList.dueDate = travelList.dueDate.decryptString()
    decryptTravelList.created = travelList.created.decryptString()
    decryptTravelList.modified = travelList.modified.decryptString()
    decryptTravelList.createdUser = travelList.createdUser.decryptString()
    return decryptTravelList
}

fun decryptVacations(vacations: Vacations): DecryptedVacations {
    val decryptVacations = DecryptedVacations()
    decryptVacations.id = vacations.id
    decryptVacations.selectionType = vacations.selectionType.decryptString()
    decryptVacations.vac_description = vacations.vac_description.decryptString()
    decryptVacations.startDate = vacations.startDate.decryptString()
    decryptVacations.endDate = vacations.endDate.decryptString()
    decryptVacations.placesToVisit_1 = vacations.placesToVisit_1.decryptString()
    decryptVacations.placesToVisit_2 = vacations.placesToVisit_2.decryptString()
    decryptVacations.placesToVisit_3 = vacations.placesToVisit_3.decryptString()
    decryptVacations.notes = vacations.notes.decryptString()
    decryptVacations.attachmentNames = vacations.attachmentNames.decryptString()
    decryptVacations.created = vacations.created
    decryptVacations.modified = vacations.modified
    decryptVacations.createdUser = vacations.createdUser


    return decryptVacations
}
// Making Encryption Method for Every Normal Class


fun encryptEducationList(education: EducationList): EducationList {
    education.selectionType = education.selectionType.encryptString()
    education.classType = education.classType.encryptString()
    education.listName = education.listName.encryptString()
    education.dueDate = education.dueDate.encryptString()
    education.created = education.created.encryptString()
    education.modified = education.modified.encryptString()
    education.createdUser = education.createdUser.encryptString()
    return education
}

fun encryptHomeBankingList(homeBankingList: HomeBankingList): HomeBankingList {
    homeBankingList.selectionType = homeBankingList.selectionType.encryptString()
    homeBankingList.listName = homeBankingList.listName.encryptString()
    homeBankingList.dueDate = homeBankingList.dueDate.encryptString()
    homeBankingList.created = homeBankingList.created.encryptString()
    homeBankingList.modified = homeBankingList.modified.encryptString()
    return homeBankingList
}

fun encryptHomeList(homeList: HomeList): HomeList {
    homeList.selectionType = homeList.selectionType.encryptString()
    homeList.classType = homeList.classType.encryptString()
    homeList.listName = homeList.listName.encryptString()
    homeList.dueDate = homeList.dueDate.encryptString()
    homeList.created = homeList.created.encryptString()
    homeList.modified = homeList.modified.encryptString()
    homeList.createdUser = homeList.createdUser.encryptString()
    return homeList
}

fun encryptInterestList(interestsList: InterestsList): InterestsList {
    interestsList.selectionType = interestsList.selectionType.encryptString()
    interestsList.classType = interestsList.classType.encryptString()
    interestsList.listName = interestsList.listName.encryptString()
    interestsList.dueDate = interestsList.dueDate.encryptString()
    interestsList.created = interestsList.created.encryptString()
    interestsList.modified = interestsList.modified.encryptString()
    interestsList.createdUser = interestsList.createdUser.encryptString()
    return interestsList
}

fun encryptMemoriesList(memorieslist: MemoriesList): MemoriesList {
    memorieslist.selectionType = memorieslist.selectionType.encryptString()
    memorieslist.classType = memorieslist.classType.encryptString()
    memorieslist.listName = memorieslist.listName.encryptString()
    memorieslist.dueDate = memorieslist.dueDate.encryptString()
    memorieslist.created = memorieslist.created.encryptString()
    memorieslist.modified = memorieslist.modified.encryptString()
    memorieslist.createdUser = memorieslist.createdUser.encryptString()
    return memorieslist
}

fun encryptPersonalLists(personalList: PersonalList): PersonalList {
    personalList.selectionType = personalList.selectionType.encryptString()
    personalList.classType = personalList.classType.encryptString()
    personalList.listName = personalList.listName.encryptString()
    personalList.dueDate = personalList.dueDate.encryptString()
    personalList.created = personalList.created.encryptString()
    personalList.modified = personalList.modified.encryptString()
    personalList.createdUser = personalList.createdUser.encryptString()
    return personalList
}

fun encryptShoppingList(shoppingList: ShoppingList): ShoppingList {
    shoppingList.selectionType = shoppingList.selectionType.encryptString()
    shoppingList.classType = shoppingList.classType.encryptString()
    shoppingList.listName = shoppingList.listName.encryptString()
    shoppingList.dueDate = shoppingList.dueDate.encryptString()
    shoppingList.created = shoppingList.created.encryptString()
    shoppingList.modified = shoppingList.modified.encryptString()
    shoppingList.createdUser = shoppingList.createdUser.encryptString()
    return shoppingList
}

fun encryptTravelList(travelList: TravelList): TravelList {
    travelList.selectionType = travelList.selectionType.encryptString()
    travelList.classType = travelList.classType.encryptString()
    travelList.listName = travelList.listName.encryptString()
    travelList.dueDate = travelList.dueDate.encryptString()
    travelList.created = travelList.created.encryptString()
    travelList.modified = travelList.modified.encryptString()
    travelList.createdUser = travelList.createdUser.encryptString()
    return travelList
}

fun encryptWellnessList(wellnessList: WellnessList): WellnessList {
    wellnessList.selectionType = wellnessList.selectionType.encryptString()
    wellnessList.classType = wellnessList.classType.encryptString()
    wellnessList.listName = wellnessList.listName.encryptString()
    wellnessList.dueDate = wellnessList.dueDate.encryptString()
    wellnessList.created = wellnessList.created.encryptString()
    wellnessList.modified = wellnessList.modified.encryptString()
    wellnessList.createdUser = wellnessList.createdUser.encryptString()
    return wellnessList
}

fun encryptAddress(address: Address): Address {
    address.street_1 = address.street_1.encryptString()
    address.street_2 = address.street_2.encryptString()
    address.city = address.city.encryptString()
    address.state = address.state.encryptString()
    address.zipCode = address.zipCode.encryptString()
    address.country = address.country.encryptString()
    return address
}

fun encryptBank(bank: Bank): Bank {
    bank.institutionName = bank.institutionName.encryptString()
    bank.accountName = bank.accountName.encryptString()
    bank.accountType = bank.accountType.encryptString()
    bank.nameOnAccount = bank.nameOnAccount.encryptString()
    bank.accountNumber = bank.accountNumber.encryptString()
    bank.location = bank.location.encryptString()
    bank.swiftCode = bank.swiftCode.encryptString()
    bank.abaRoutingNumber = bank.abaRoutingNumber.encryptString()
    bank.contacts = bank.contacts.encryptString()
    bank.website = bank.website.encryptString()
    bank.userName = bank.userName.encryptString()
    bank.password = bank.password.encryptString()
    bank.pin = bank.pin.encryptString()
    return bank
}

fun encryptCalendarEvents(calendarEvents: CalendarEvents): CalendarEvents {
    return calendarEvents
}

fun encryptDevice(device: Device): Device {
    device.deviceId = device.deviceId.encryptString()
    device.passcode = device.passcode.encryptString()
    return device
}

fun encryptHash(hash: Hash): Hash {
    hash.finalPassword = hash.finalPassword.encryptString()
    hash.passcode = hash.passcode.encryptString()
    return hash
}

fun encryptNotifications(notifications: Notifications): Notifications {
    notifications.message = notifications.message.encryptString()
    notifications.boxName = notifications.boxName.encryptString()
    notifications.category = notifications.category.encryptString()
    notifications.dueDate = notifications.dueDate.encryptString()
    notifications.subTitle = notifications.subTitle.encryptString()
    notifications.notifyDate = notifications.notifyDate.encryptString()
    notifications.created = notifications.created.encryptString()
    notifications.modified = notifications.modified.encryptString()
    return notifications
}

fun encryptRecentSearch(recentSearch: RecentSearch): RecentSearch {
    recentSearch.listName = recentSearch.listName.encryptString()
    recentSearch.subCategory = recentSearch.subCategory.encryptString()
    recentSearch.mainCategory = recentSearch.mainCategory.encryptString()
    recentSearch.classType = recentSearch.classType.encryptString()
    return recentSearch
}

fun encryptEducation(education: Education): Education {
    education.selectionType = education.selectionType.encryptString()
    education.institutionName = education.institutionName.encryptString()
    education.accountName = education.accountName.encryptString()
    education.accountType = education.accountType.encryptString()
    education.nameOnAccount = education.nameOnAccount.encryptString()
    education.accountNumber = education.accountNumber.encryptString()
    education.location = education.location.encryptString()
    education.swiftCode = education.swiftCode.encryptString()
    education.abaRoutingNumber = education.abaRoutingNumber.encryptString()
    education.contacts = education.contacts.encryptString()
    education.website = education.website.encryptString()
    education.userName = education.userName.encryptString()
    education.password = education.password.encryptString()
    education.pin = education.pin.encryptString()
    education.paymentMethodOnFile = education.paymentMethodOnFile.encryptString()
    education.notes = education.notes.encryptString()
    education.attachmentNames = education.attachmentNames.encryptString()
    education.title = education.title.encryptString()
    education.created = education.created.encryptString()
    education.modified = education.modified.encryptString()
    education.createdUser = education.createdUser.encryptString()
    return education
}

fun encryptMainEducation(mainEducation: MainEducation): MainEducation {
    mainEducation.selectionType = mainEducation.selectionType.encryptString()
    mainEducation.classType = mainEducation.classType.encryptString()
    mainEducation.institutionName = mainEducation.institutionName.encryptString()
    mainEducation.qualification = mainEducation.qualification.encryptString()
    mainEducation.name = mainEducation.name.encryptString()
    mainEducation.location = mainEducation.location.encryptString()
    mainEducation.major = mainEducation.major.encryptString()
    mainEducation.from = mainEducation.from.encryptString()
    mainEducation.to = mainEducation.to.encryptString()
    mainEducation.currentlyStudying = mainEducation.currentlyStudying.encryptString()
    mainEducation.notes = mainEducation.notes.encryptString()
    mainEducation.created = mainEducation.created.encryptString()
    mainEducation.modified = mainEducation.modified.encryptString()
    mainEducation.attachmentNames = mainEducation.attachmentNames.encryptString()
    mainEducation.createdUser = mainEducation.createdUser.encryptString()
    return mainEducation
}

fun encryptWork(work: Work): Work {
    work.selectionType = work.selectionType.encryptString()
    work.classType = work.classType.encryptString()
    work.companyName = work.companyName.encryptString()
    work.position = work.position.encryptString()
    work.name = work.name.encryptString()
    work.location = work.location.encryptString()
    work.from = work.from.encryptString()
    work.to = work.to.encryptString()
    work.currentWork = work.currentWork.encryptString()
    work.created = work.created.encryptString()
    work.modified = work.modified.encryptString()
    work.notes = work.notes.encryptString()
    work.attachmentNames = work.attachmentNames.encryptString()
    work.createdUser = work.createdUser.encryptString()
    return work
}

fun encryptInterests(interest: Interests): Interests {
    interest.selectionType = interest.selectionType.encryptString()
    interest.institutionName = interest.institutionName.encryptString()
    interest.accountName = interest.accountName.encryptString()
    interest.accountType = interest.accountType.encryptString()
    interest.nameOnAccount = interest.nameOnAccount.encryptString()
    interest.accountNumber = interest.accountNumber.encryptString()
    interest.location = interest.location.encryptString()
    interest.swiftCode = interest.swiftCode.encryptString()
    interest.abaRoutingNumber = interest.abaRoutingNumber.encryptString()
    interest.contacts = interest.contacts.encryptString()
    interest.website = interest.website.encryptString()
    interest.userName = interest.userName.encryptString()
    interest.password = interest.password.encryptString()
    interest.pin = interest.pin.encryptString()
    interest.paymentMethodOnFile = interest.paymentMethodOnFile.encryptString()
    interest.notes = interest.notes.encryptString()
    interest.attachmentNames = interest.attachmentNames.encryptString()
    interest.title = interest.title.encryptString()
    interest.created = interest.created.encryptString()
    interest.modified = interest.modified.encryptString()
    interest.createdUser = interest.createdUser.encryptString()
    return interest
}

fun encryptMemories(mainMemories: MainMemories): MainMemories {
    mainMemories.selectionType = mainMemories.selectionType.encryptString()
    mainMemories.institutionName = mainMemories.institutionName.encryptString()
    mainMemories.accountName = mainMemories.accountName.encryptString()
    mainMemories.accountType = mainMemories.accountType.encryptString()
    mainMemories.nameOnAccount = mainMemories.nameOnAccount.encryptString()
    mainMemories.accountNumber = mainMemories.accountNumber.encryptString()
    mainMemories.location = mainMemories.location.encryptString()
    mainMemories.swiftCode = mainMemories.swiftCode.encryptString()
    mainMemories.abaRoutingNumber = mainMemories.abaRoutingNumber.encryptString()
    mainMemories.contacts = mainMemories.contacts.encryptString()
    mainMemories.website = mainMemories.website.encryptString()
    mainMemories.userName = mainMemories.userName.encryptString()
    mainMemories.password = mainMemories.password.encryptString()
    mainMemories.pin = mainMemories.pin.encryptString()
    mainMemories.paymentMethodOnFile = mainMemories.paymentMethodOnFile.encryptString()
    mainMemories.created = mainMemories.created.encryptString()
    mainMemories.modified = mainMemories.modified.encryptString()
    mainMemories.notes = mainMemories.notes.encryptString()
    mainMemories.attachmentNames = mainMemories.attachmentNames.encryptString()
    mainMemories.title = mainMemories.title.encryptString()
    mainMemories.createdUser = mainMemories.createdUser.encryptString()

    return mainMemories
}

fun encryptMemoryTimeLIne(memoryTimeline: MemoryTimeline): MemoryTimeline {
    memoryTimeline.selectionType = memoryTimeline.selectionType.encryptString()
    memoryTimeline.title = memoryTimeline.title.encryptString()
    memoryTimeline.date = memoryTimeline.date.encryptString()
    memoryTimeline.place = memoryTimeline.place.encryptString()
    memoryTimeline.contacts = memoryTimeline.contacts.encryptString()
    memoryTimeline.notes = memoryTimeline.notes.encryptString()
    memoryTimeline.attachmentNames = memoryTimeline.attachmentNames.encryptString()
    memoryTimeline.created = memoryTimeline.created.encryptString()
    memoryTimeline.modified = memoryTimeline.modified.encryptString()
    memoryTimeline.createdUser = memoryTimeline.createdUser.encryptString()

    return memoryTimeline
}


fun encryptCertificate(certificate: Certificate): Certificate {
    certificate.selectionType = certificate.selectionType.encryptString()
    certificate.cer_description = certificate.cer_description.encryptString()
    certificate.nameOnCertificate = certificate.nameOnCertificate.encryptString()
    certificate.gender = certificate.gender.encryptString()
    certificate.dateOfBirth = certificate.dateOfBirth.encryptString()
    certificate.timeOfBirth = certificate.timeOfBirth.encryptString()
    certificate.placeOfBirth = certificate.placeOfBirth.encryptString()
    certificate.dateOfMarriage = certificate.dateOfMarriage.encryptString()
    certificate.placeOfMarriage = certificate.placeOfMarriage.encryptString()
    certificate.nameOneCertificate = certificate.nameOneCertificate.encryptString()
    certificate.nameTwoCertificate = certificate.nameTwoCertificate.encryptString()
    certificate.notes = certificate.notes.encryptString()
    certificate.created = certificate.created.encryptString()
    certificate.modified = certificate.modified.encryptString()
    certificate.attachmentNames = certificate.attachmentNames.encryptString()
    certificate.createdUser = certificate.createdUser.encryptString()

    return certificate
}

fun encryptGovernment(government: Government): Government {
    government.selectionType = government.selectionType.encryptString()
    government.idName = government.idName.encryptString()
    government.name = government.name.encryptString()
    government.nameOnId = government.nameOnId.encryptString()
    government.issuingCountry = government.issuingCountry.encryptString()
    government.issuingState = government.issuingState.encryptString()
    government.idNumber = government.idNumber.encryptString()
    government.dateIssued = government.dateIssued.encryptString()
    government.expirationDate = government.expirationDate.encryptString()
    government.created = government.created.encryptString()
    government.modified = government.modified.encryptString()
    government.notes = government.notes.encryptString()
    government.attachmentNames = government.attachmentNames.encryptString()
    government.createdUser = government.createdUser.encryptString()
    return government
}

fun encryptLicense(license: License): License {
    license.selectionType = license.selectionType.encryptString()
    license.lic_description = license.lic_description.encryptString()
    license.nameOnLicense = license.nameOnLicense.encryptString()
    license.issuingCountry = license.issuingCountry.encryptString()
    license.issuingState = license.issuingState.encryptString()
    license.licenseNumber = license.licenseNumber.encryptString()
    license.dateIssued = license.dateIssued.encryptString()
    license.expirationDate = license.expirationDate.encryptString()
    license.notes = license.notes.encryptString()
    license.created = license.created.encryptString()
    license.modified = license.modified.encryptString()
    license.attachmentNames = license.attachmentNames.encryptString()
    license.createdUser = license.createdUser.encryptString()
    return license
}

fun encryptPersonal(personal: Personal): Personal {
    personal.selectionType = personal.selectionType.encryptString()
    personal.institutionName = personal.institutionName.encryptString()
    personal.accountName = personal.accountName.encryptString()
    personal.accountType = personal.accountType.encryptString()
    personal.nameOnAccount = personal.nameOnAccount.encryptString()
    personal.accountNumber = personal.accountNumber.encryptString()
    personal.location = personal.location.encryptString()
    personal.swiftCode = personal.swiftCode.encryptString()
    personal.abaRoutingNumber = personal.abaRoutingNumber.encryptString()
    personal.contacts = personal.contacts.encryptString()
    personal.website = personal.website.encryptString()
    personal.userName = personal.userName.encryptString()
    personal.password = personal.password.encryptString()
    personal.pin = personal.pin.encryptString()
    personal.paymentMethodOnFile = personal.paymentMethodOnFile.encryptString()
    personal.notes = personal.notes.encryptString()
    personal.attachmentNames = personal.attachmentNames.encryptString()
    personal.title = personal.title.encryptString()
    personal.created = personal.created.encryptString()
    personal.modified = personal.modified.encryptString()
    personal.createdUser = personal.createdUser.encryptString()
    return personal
}

fun encryptSocial(social: Social): Social {
    social.selectionType = social.selectionType.encryptString()
    social.cardName = social.cardName.encryptString()
    social.nameOnCard = social.nameOnCard.encryptString()
    social.socialSecurityNumber = social.socialSecurityNumber.encryptString()
    social.notes = social.notes.encryptString()
    social.created = social.created.encryptString()
    social.modified = social.modified.encryptString()
    social.attachmentNames = social.attachmentNames.encryptString()
    social.createdUser = social.createdUser.encryptString()
    return social
}

fun encryptTAXID(taxID: TaxID): TaxID {
    taxID.selectionType = taxID.selectionType.encryptString()
    taxID.taxIdName = taxID.taxIdName.encryptString()
    taxID.taxIdNumber = taxID.taxIdNumber.encryptString()
    taxID.issuingCountry = taxID.issuingCountry.encryptString()
    taxID.name = taxID.name.encryptString()
    taxID.notes = taxID.notes.encryptString()
    taxID.created = taxID.created.encryptString()
    taxID.modified = taxID.modified.encryptString()
    taxID.attachmentNames = taxID.attachmentNames.encryptString()
    return taxID
}

fun encryptClothingSizes(clothingSizes: ClothingSizes): ClothingSizes {
    clothingSizes.selectionType = clothingSizes.selectionType.encryptString()
    clothingSizes.classType = clothingSizes.classType.encryptString()
    clothingSizes.personName = clothingSizes.personName.encryptString()
    clothingSizes.sizeName = clothingSizes.sizeName.encryptString()
    clothingSizes.sizeCategory = clothingSizes.sizeCategory.encryptString()
    clothingSizes.topsSize = clothingSizes.topsSize.encryptString()
    clothingSizes.topsNumericSize = clothingSizes.topsNumericSize.encryptString()
    clothingSizes.bottomsSize = clothingSizes.bottomsSize.encryptString()
    clothingSizes.bottomsNumericSize = clothingSizes.bottomsNumericSize.encryptString()
    clothingSizes.dressesSize = clothingSizes.dressesSize.encryptString()
    clothingSizes.dressesNumericSize = clothingSizes.dressesNumericSize.encryptString()
    clothingSizes.outWearSize = clothingSizes.outWearSize.encryptString()
    clothingSizes.outWearNumericSize = clothingSizes.outWearNumericSize.encryptString()
    clothingSizes.swimWearSize = clothingSizes.swimWearSize.encryptString()
    clothingSizes.swimWearNumericSize = clothingSizes.swimWearNumericSize.encryptString()
    clothingSizes.swimWearBraBandCupSize = clothingSizes.swimWearBraBandCupSize.encryptString()
    clothingSizes.shoeSize = clothingSizes.shoeSize.encryptString()
    clothingSizes.shoeWidth = clothingSizes.shoeWidth.encryptString()
    clothingSizes.hats = clothingSizes.hats.encryptString()
    clothingSizes.gloves = clothingSizes.gloves.encryptString()
    clothingSizes.tights = clothingSizes.tights.encryptString()
    clothingSizes.bust = clothingSizes.bust.encryptString()
    clothingSizes.waist = clothingSizes.waist.encryptString()
    clothingSizes.hips = clothingSizes.hips.encryptString()
    clothingSizes.armLength = clothingSizes.armLength.encryptString()
    clothingSizes.inseam = clothingSizes.inseam.encryptString()
    clothingSizes.torso = clothingSizes.torso.encryptString()
    // Mens
    clothingSizes.jacketsSize = clothingSizes.jacketsSize.encryptString()
    clothingSizes.jacketsNumericSize = clothingSizes.jacketsNumericSize.encryptString()
    clothingSizes.pantsSize = clothingSizes.pantsSize.encryptString()
    clothingSizes.pantsNumericSize = clothingSizes.pantsNumericSize.encryptString()
    clothingSizes.toddlerSize = clothingSizes.toddlerSize.encryptString()
    clothingSizes.kidSize = clothingSizes.kidSize.encryptString()
    clothingSizes.neck = clothingSizes.neck.encryptString()
    clothingSizes.chest = clothingSizes.chest.encryptString()
    // Baby
    clothingSizes.clothing = clothingSizes.clothing.encryptString()
    clothingSizes.shoes = clothingSizes.shoes.encryptString()
    clothingSizes.notes = clothingSizes.notes.encryptString()
    clothingSizes.attachmentNames = clothingSizes.attachmentNames.encryptString()
    //  Boys
    clothingSizes.beltsNumericSize = clothingSizes.beltsNumericSize.encryptString()
    clothingSizes.socks = clothingSizes.socks.encryptString()
    clothingSizes.seat = clothingSizes.seat.encryptString()
    clothingSizes.beltSize = clothingSizes.beltSize.encryptString()
    clothingSizes.created = clothingSizes.created.encryptString()
    clothingSizes.modified = clothingSizes.modified.encryptString()
    clothingSizes.createdUser = clothingSizes.createdUser.encryptString()
    return clothingSizes
}

fun encryptLoyaltyProgram(loyalityPrograms: LoyaltyPrograms): LoyaltyPrograms {
    loyalityPrograms.selectionType = loyalityPrograms.selectionType.encryptString()
    loyalityPrograms.brandName = loyalityPrograms.brandName.encryptString()
    loyalityPrograms.accountName = loyalityPrograms.accountName.encryptString()
    loyalityPrograms.nameOnAccount = loyalityPrograms.nameOnAccount.encryptString()
    loyalityPrograms.accountNumber = loyalityPrograms.accountNumber.encryptString()
    loyalityPrograms.website = loyalityPrograms.website.encryptString()
    loyalityPrograms.userName = loyalityPrograms.userName.encryptString()
    loyalityPrograms.password = loyalityPrograms.password.encryptString()
    loyalityPrograms.pin = loyalityPrograms.pin.encryptString()
    loyalityPrograms.notes = loyalityPrograms.notes.encryptString()
    loyalityPrograms.attachmentNames = loyalityPrograms.attachmentNames.encryptString()
    loyalityPrograms.created = loyalityPrograms.created.encryptString()
    loyalityPrograms.modified = loyalityPrograms.modified.encryptString()
    loyalityPrograms.createdUser = loyalityPrograms.createdUser.encryptString()

    return loyalityPrograms
}

fun encryptRecentPurchase(recentPurchase: RecentPurchase): RecentPurchase {
    recentPurchase.selectionType = recentPurchase.selectionType.encryptString()
    recentPurchase.brandName = recentPurchase.brandName.encryptString()
    recentPurchase.itemName = recentPurchase.itemName.encryptString()
    recentPurchase.purchasedBy = recentPurchase.purchasedBy.encryptString()
    recentPurchase.purchasedDate = recentPurchase.purchasedDate.encryptString()
    recentPurchase.purchasedPrice = recentPurchase.purchasedPrice.encryptString()
    recentPurchase.notes = recentPurchase.notes.encryptString()
    recentPurchase.created = recentPurchase.created.encryptString()
    recentPurchase.modified = recentPurchase.modified.encryptString()
    recentPurchase.attachmentNames = recentPurchase.attachmentNames.encryptString()
    recentPurchase.createdUser = recentPurchase.createdUser.encryptString()
    return recentPurchase
}

fun encryptShopping(shopping: Shopping): Shopping {
    shopping.selectionType = shopping.selectionType.encryptString()
    shopping.institutionName = shopping.institutionName.encryptString()
    shopping.accountName = shopping.accountName.encryptString()
    shopping.accountType = shopping.accountType.encryptString()
    shopping.nameOnAccount = shopping.nameOnAccount.encryptString()
    shopping.accountNumber = shopping.accountNumber.encryptString()
    shopping.location = shopping.location.encryptString()
    shopping.swiftCode = shopping.swiftCode.encryptString()
    shopping.abaRoutingNumber = shopping.abaRoutingNumber.encryptString()
    shopping.contacts = shopping.contacts.encryptString()
    shopping.website = shopping.website.encryptString()
    shopping.userName = shopping.userName.encryptString()
    shopping.password = shopping.password.encryptString()
    shopping.pin = shopping.pin.encryptString()
    shopping.paymentMethodOnFile = shopping.paymentMethodOnFile.encryptString()
    shopping.notes = shopping.notes.encryptString()
    shopping.imageName = shopping.imageName.encryptString()
    shopping.attachmentNames = shopping.attachmentNames.encryptString()
    shopping.title = shopping.title.encryptString()
    shopping.created = shopping.created.encryptString()
    shopping.modified = shopping.modified.encryptString()
    return shopping
}

fun encryptDocuments(documents: Documents): Documents {
    documents.selectionType = documents.selectionType.encryptString()
    documents.passportName = documents.passportName.encryptString()
    documents.visaName = documents.visaName.encryptString()
    documents.nameOnPassport = documents.nameOnPassport.encryptString()
    documents.nameOnVisa = documents.nameOnVisa.encryptString()
    documents.visaType = documents.visaType.encryptString()
    documents.visaNumber = documents.visaNumber.encryptString()
    documents.travelDocumentTitle = documents.travelDocumentTitle.encryptString()
    documents.nameOnTravelDocument = documents.nameOnTravelDocument.encryptString()
    documents.travelDocumentType = documents.travelDocumentType.encryptString()
    documents.travelDocumentNumber = documents.travelDocumentNumber.encryptString()
    documents.issuingCountry = documents.issuingCountry.encryptString()
    documents.passportNumber = documents.passportNumber.encryptString()
    documents.placeIssued = documents.placeIssued.encryptString()
    documents.dateIssued = documents.dateIssued.encryptString()
    documents.expirationDate = documents.expirationDate.encryptString()
    documents.notes = documents.notes.encryptString()
    documents.attachmentNames = documents.attachmentNames.encryptString()
    documents.created = documents.created.encryptString()
    documents.modified = documents.modified.encryptString()
    documents.createdUser = documents.createdUser.encryptString()

    return documents
}

fun encryptLoyalty(loyalty: Loyalty): Loyalty {
    loyalty.selectionType = loyalty.selectionType.encryptString()
    loyalty.airLine = loyalty.airLine.encryptString()
    loyalty.hotel = loyalty.hotel.encryptString()
    loyalty.carRentalCompany = loyalty.carRentalCompany.encryptString()
    loyalty.cruiseline = loyalty.cruiseline.encryptString()
    loyalty.railway = loyalty.railway.encryptString()
    loyalty.other = loyalty.other.encryptString()
    loyalty.accountName = loyalty.accountName.encryptString()
    loyalty.nameOnAccount = loyalty.nameOnAccount.encryptString()
    loyalty.accountNumber = loyalty.accountNumber.encryptString()
    loyalty.website = loyalty.website.encryptString()
    loyalty.userName = loyalty.userName.encryptString()
    loyalty.password = loyalty.password.encryptString()
    loyalty.pin = loyalty.pin.encryptString()
    loyalty.notes = loyalty.notes.encryptString()
    loyalty.attachmentNames = loyalty.attachmentNames.encryptString()
    loyalty.created = loyalty.created.encryptString()
    loyalty.modified = loyalty.modified.encryptString()
    loyalty.createdUser = loyalty.createdUser.encryptString()
    return loyalty
}

fun encryptTravel(travel: Travel): Travel {
    travel.selectionType = travel.selectionType.encryptString()
    travel.institutionName = travel.institutionName.encryptString()
    travel.accountName = travel.accountName.encryptString()
    travel.accountType = travel.accountType.encryptString()
    travel.nameOnAccount = travel.nameOnAccount.encryptString()
    travel.accountNumber = travel.accountNumber.encryptString()
    travel.location = travel.location.encryptString()
    travel.swiftCode = travel.swiftCode.encryptString()
    travel.abaRoutingNumber = travel.abaRoutingNumber.encryptString()
    travel.contacts = travel.contacts.encryptString()
    travel.website = travel.website.encryptString()
    travel.userName = travel.userName.encryptString()
    travel.password = travel.password.encryptString()
    travel.pin = travel.pin.encryptString()
    travel.paymentMethodOnFile = travel.paymentMethodOnFile.encryptString()
    travel.notes = travel.notes.encryptString()
    travel.imageName = travel.imageName.encryptString()
    travel.attachmentNames = travel.attachmentNames.encryptString()
    travel.title = travel.title.encryptString()
    travel.created = travel.created.encryptString()
    travel.modified = travel.modified.encryptString()
    travel.createdUser = travel.createdUser.encryptString()
    return travel
}

fun encryptVacations(vacations: Vacations): Vacations {
    vacations.selectionType = vacations.selectionType.encryptString()
    vacations.vac_description = vacations.vac_description.encryptString()
    vacations.startDate = vacations.startDate.encryptString()
    vacations.endDate = vacations.endDate.encryptString()
    vacations.placesToVisit_1 = vacations.placesToVisit_1.encryptString()
    vacations.placesToVisit_2 = vacations.placesToVisit_2.encryptString()
    vacations.placesToVisit_3 = vacations.placesToVisit_3.encryptString()
    vacations.notes = vacations.notes.encryptString()
    vacations.attachmentNames = vacations.attachmentNames.encryptString()
    vacations.created = vacations.created.encryptString()
    vacations.modified = vacations.modified.encryptString()
    vacations.createdUser = vacations.createdUser.encryptString()
    return vacations
}


fun decryptCombine(combine: Combine) : DecryptedCombine {
    val decryptedCombine = DecryptedCombine()

    for ( financialItems in combine.financialItems ) {
        val decryptedItem = decryptFinancial(financialItems)
        decryptedCombine.financialItems.add( decryptedItem )
    }

    for (paymentItems in combine.paymentItems ) {
        val decryptedItem = decryptPayment(paymentItems)
        decryptedCombine.paymentItems.add(decryptedItem)
    }

    for (propertyItems in combine.propertyItems){
        decryptedCombine.propertyItems.add(decryptProperty(propertyItems))
    }

    for (vehicleItems in combine.vehicleItems){
        decryptedCombine.vehicleItems.add(decryptVehicle(vehicleItems))
    }

    for (assetItems in combine.assetItems){
        decryptedCombine.assetItems.add(decryptAsset(assetItems))
    }

    for (insuranceItems in combine.insuranceItems){
        decryptedCombine.insuranceItems.add(decryptInsurance(insuranceItems))
    }

    for (taxesItems in combine.taxesItems){
        decryptedCombine.taxesItems.add(decryptTaxes(taxesItems))
    }

    for (listItems in combine.listItems){
        decryptedCombine.listItems.add(decryptHomeList(listItems))
    }
    return decryptedCombine
}

fun decryptCombineTravel(combineTravel: CombineTravel) : DecryptedCombineTravel{
    val decryptedCombineTravel = DecryptedCombineTravel()
    for (documentsItems in combineTravel.documentsItems) {
        decryptedCombineTravel.documentsItems.add(decryptDocuments(documentsItems))
    }
    for (loyaltyItems in combineTravel.loyaltyItems) {
        decryptedCombineTravel.loyaltyItems.add(decryptLoyality(loyaltyItems))
    }
    for (travelItems in combineTravel.travelItems) {
        decryptedCombineTravel.travelItems.add(decryptTravel(travelItems))
    }
    for (vacationItems in combineTravel.vacationsItems) {
        decryptedCombineTravel.vacationsItems.add(decryptVacations(vacationItems))
    }
    for (listItems in combineTravel.listItems) {
        decryptedCombineTravel.listItems.add(decryptTravelList(listItems))
    }
    return decryptedCombineTravel
}

fun decryptCombineMemories(combineMemories : CombineMemories) : DecryptedCombineMemories{
    val decryptedCombineMemories = DecryptedCombineMemories()

    for(mainMemoryItems in combineMemories.mainMemoriesItems)  {
        decryptedCombineMemories.mainMemoriesItems.add(decryptMainMemories(mainMemoryItems))
    }
    for(memoryTimelineItems in combineMemories.memoryTimelineItems){
        decryptedCombineMemories.memoryTimelineItems.add(decryptMemoryTimeLine(memoryTimelineItems))
    }
    for(listItems in combineMemories.listItems){
        decryptedCombineMemories.listItems.add(decryptMemoriesList(listItems))
    }
    return decryptedCombineMemories
}

fun decryptCombineEducation(combineEducation: CombineEducation) : DecryptedCombineEducation{
    val decryptedCombineEducation = DecryptedCombineEducation()

    for(educationItems in combineEducation.educationItems){
        decryptedCombineEducation.educationItems.add(decryptEducation(educationItems))
    }

    for(mainEducationItems in combineEducation.mainEducationItems){
        decryptedCombineEducation.mainEducationItems.add(decryptMainEducation(mainEducationItems))
    }

    for(workItems in combineEducation.workItems){
        decryptedCombineEducation.workItems.add(decryptWork(workItems))
    }

    for(listItems in combineEducation.listItems){
        decryptedCombineEducation.listItems.add(decryptEducationList(listItems))
    }
    return decryptedCombineEducation
}

fun decryptCombineInterests(combineInterest : CombineInterests) : DecryptedCombineInterests {
    val decryptedCombineInterests = DecryptedCombineInterests()

    for(interestItems  in combineInterest.interestItems){
        decryptedCombineInterests.interestItems.add(decryptInterests(interestItems))
    }

    for(listItems in combineInterest.listItems){
        decryptedCombineInterests.listItems.add(decryptInterestList(listItems))
    }
    return decryptedCombineInterests
}

fun decryptCombinePersonal(combinePersonal: CombinePersonal): DecryptedCombinePersonal {
    val decryptedCombinePersonal = DecryptedCombinePersonal()

    for (certificateItems in combinePersonal.certificateItems) {
        decryptedCombinePersonal.certificateItems.add(decryptCertificate(certificateItems as Certificate))
    }

    for (governmentItems in combinePersonal.governmentItems) {
        decryptedCombinePersonal.governmentItems.add(decryptGovernment(governmentItems as Government))
    }

    for (licenseItems in combinePersonal.licenseItems) {
        decryptedCombinePersonal.licenseItems.add(decryptLicense(licenseItems as License))
    }

    for (personalItems in combinePersonal.personalItems) {
        decryptedCombinePersonal.personalItems.add(decryptPersonal(personalItems as Personal))
    }

    for (socialItems in combinePersonal.socialItems) {
        decryptedCombinePersonal.socialItems.add(decryptSocial(socialItems as Social))
    }

    for (taxIDItems in combinePersonal.taxIDItems) {
        decryptedCombinePersonal.taxIDItems.add(decryptTaxID(taxIDItems as TaxID))
    }

    for (listItems in combinePersonal.listItems) {
        decryptedCombinePersonal.listItems.add(decryptPersonalList(listItems as PersonalList))
    }
    return decryptedCombinePersonal
}

fun decryptCombineWellness(combineWellness: CombineWellness): DecryptedCombineWellness {
    val decryptedCombineWellness = DecryptedCombineWellness()
    for (checkupsItems in combineWellness.checkupsItems) {
        decryptedCombineWellness.checkupsItems.add(decryptCheckUps(checkupsItems as Checkups))
    }

    for (emergencyContactItems in combineWellness.emergencyContactsItems) {
        decryptedCombineWellness.emergencyContactsItems.add(decryptEmergencyContacts(emergencyContactItems as EmergencyContacts))
    }

    for (eyeglassPrescriptionsItems in combineWellness.eyeglassPrescriptionsItems) {
        decryptedCombineWellness.eyeglassPrescriptionsItems.add(decryptEyeGlassPrescriptions(eyeglassPrescriptionsItems as EyeglassPrescriptions))
    }

    for (healthcareProvidersItems in combineWellness.healthcareProvidersItems) {
        decryptedCombineWellness.healthcareProvidersItems.add(decryptHealthCareProviders(healthcareProvidersItems as HealthcareProviders))
    }

    for (identificationItems in combineWellness.identificationItems) {
        decryptedCombineWellness.identificationItems.add(decryptIdentification(identificationItems as Identification))
    }

    for (medicalConditionsItems in combineWellness.medicalConditionsItems) {
        decryptedCombineWellness.medicalConditionsItems.add(decryptMedicalConditions(medicalConditionsItems as MedicalConditions))
    }

    for(medicalHistoryItems in combineWellness.medicalHistoryItems){
        decryptedCombineWellness.medicalHistoryItems.add(decryptMedicalHistory(medicalHistoryItems as MedicalHistory))
    }

    for (medicationsItems in combineWellness.medicationsItems) {
        decryptedCombineWellness.medicationsItems.add(decryptMedications(medicationsItems as Medications))
    }

    for (vitalNumbersItems in combineWellness.vitalNumbersItems) {
        decryptedCombineWellness.vitalNumbersItems.add(decryptVitalNumbers(vitalNumbersItems as VitalNumbers))
    }

    for (wellnessItems in combineWellness.wellnessItems) {
        decryptedCombineWellness.wellnessItems.add(decryptWellness(wellnessItems as Wellness))
    }

    for (listItems in combineWellness.listItems) {
        decryptedCombineWellness.listItems.add(decryptWellnessList(listItems as WellnessList))
    }
    return decryptedCombineWellness
}

fun decryptCombineShopping(combineShopping : CombineShopping) : DecryptedCombineShopping{
    val decryptedCombineShopping = DecryptedCombineShopping()
    for(loyaltyProgramItems in combineShopping.loyaltyProgramsItems){
        decryptedCombineShopping.loyaltyProgramsItems.add(decryptLoyaltyPrograms(loyaltyProgramItems as LoyaltyPrograms))
    }
    for(recentPurchaseItems in combineShopping.recentPurchaseItems){
        decryptedCombineShopping.recentPurchaseItems.add(decryptRecentPurchase(recentPurchaseItems as RecentPurchase))
    }
    for(shoppingItems in combineShopping.shoppingItems){
        decryptedCombineShopping.shoppingItems.add(decryptShopping(shoppingItems as Shopping))
    }

    for(clothingSizesItems in combineShopping.clothingSizesItems){
        decryptedCombineShopping.clothingSizesItems.add(decryptedClothingSizes(clothingSizesItems as ClothingSizes))
    }

    for(listItems in combineShopping.listItems){
        decryptedCombineShopping.listItems.add(decryptShoppingList(listItems as ShoppingList))
    }
    return decryptedCombineShopping
}

fun decryptCombineContacts(combineContacts: CombineContacts): DecryptedCombineContacts {
    val decryptedCombineContacts = DecryptedCombineContacts()
    for(contactItems in combineContacts.contactsItems){
        decryptedCombineContacts.contactsItems.add(decryptContact(contactItems as Contacts))
    }
    for(mainContactItems in combineContacts.mainContactsItems){
        decryptedCombineContacts.mainContactsItems.add(decryptMainContacts(mainContactItems))
    }
    for(listItems in combineContacts.listItems){
        decryptedCombineContacts.listItems.add(decryptContactsList(listItems as ContactsList))
    }
    return decryptedCombineContacts
}