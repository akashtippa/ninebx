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
import com.ninebx.ui.base.realm.home.education.Education
import com.ninebx.ui.base.realm.home.education.MainEducation
import com.ninebx.ui.base.realm.home.education.Work
import com.ninebx.ui.base.realm.home.homeBanking.*
import com.ninebx.ui.base.realm.home.interests.Interests
import com.ninebx.ui.base.realm.home.memories.MainMemories
import com.ninebx.ui.base.realm.home.memories.MemoryTimeline
import com.ninebx.ui.base.realm.home.personal.*
import com.ninebx.ui.base.realm.home.shopping.ClothingSizes
import com.ninebx.ui.base.realm.home.shopping.LoyaltyPrograms
import com.ninebx.ui.base.realm.home.shopping.RecentPurchase
import com.ninebx.ui.base.realm.home.shopping.Shopping
import com.ninebx.ui.base.realm.home.travel.Documents
import com.ninebx.ui.base.realm.home.travel.Loyalty
import com.ninebx.ui.base.realm.home.travel.Travel
import com.ninebx.ui.base.realm.home.travel.Vacations
import com.ninebx.ui.base.realm.home.wellness.*
import com.ninebx.ui.base.realm.lists.*
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
<<<<<<< HEAD
    /*if( !this.endsWith("=", true) ) {
        return this
    }*/
    return decryptAESKEY( this.toByteArray(), NineBxApplication.getPreferences().privateKey!! )
=======
    return decryptAESKEY(this.toByteArray(), NineBxApplication.getPreferences().privateKey!!)
>>>>>>> 1212c93a8abf09a63554248ed4a73756e0e1f5e0
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

<<<<<<< HEAD
fun encryptMembers( members: RealmList<Member> ): RealmList<Member>? {
    for( i in 0 until members.size ) {
        encryptMember(members[i]!!)
=======
fun encryptMembers(members: RealmList<Member>): RealmList<Member>? {
    for (i in 0 until members.size) {
       encryptMember(member!!)
    }
    return members
}

fun encryptMember(member: Member): Member? {

    member.firstName = member.firstName            .encryptString()
    member.lastName = member. lastName             .encryptString()
    member.relationship = member. relationship         .encryptString()
    member.role = member. role.encryptString()
    member.email = member. email                .encryptString()

    member.dateOfBirth = member.dateOfBirth         .encryptString()
    member.anniversary = member.anniversary         .encryptString()
    member.gender = member.gender
    member.mobileNumber = member.mobileNumber        .encryptString()
    member.street_1 = member.street_1            .encryptString()
    member.street_2 = member.street_2            .encryptString()
    member.city = member.city                .encryptString()
    member.state = member.state               .encryptString()
    member.zipCode = member.zipCode             .encryptString()
    member.country = member.country             .encryptString()

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

fun decryptEducationList(educationList: EducationList): DecryptedEducationList {
    val decryptEducationList = DecryptedEducationList()
    decryptEducationList.selectionType = educationList.selectionType.decryptString()
    decryptEducationList.classType = educationList.classType.decryptString()
    decryptEducationList.listName = educationList.listName.decryptString()
    decryptEducationList.dueDate = educationList.dueDate.decryptString()
    decryptEducationList.created = educationList.created.decryptString()
    decryptEducationList.modified = educationList.modified.decryptString()
    decryptEducationList.createdUser = educationList.createdUser.decryptString()

    return decryptEducationList
}

fun decryptEmergencyContacts(emergencyContacts: EmergencyContacts): DecryptedEmergencyContacts {
    val decryptEmergencyContacts = DecryptedEmergencyContacts()
    decryptEmergencyContacts.selectionType = emergencyContacts.selectionType.decryptString()
    decryptEmergencyContacts.classType = emergencyContacts.classType.decryptString()
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
    decryptEmergencyContacts.created = emergencyContacts.created.decryptString()
    decryptEmergencyContacts.modified = emergencyContacts.modified.decryptString()
    decryptEmergencyContacts.notes = emergencyContacts.notes.decryptString()
    decryptEmergencyContacts.attachmentNames = emergencyContacts.attachmentNames.decryptString()
    decryptEmergencyContacts.createdUser = emergencyContacts.createdUser.decryptString()

    return decryptEmergencyContacts
}

fun decryptEyeGlassPrescriptions(eyeglassPrescriptions: EyeglassPrescriptions): DecryptedEyeglassPrescriptions {
    val decryptEyeGlassPrescriptions = DecryptedEyeglassPrescriptions()
    decryptEyeGlassPrescriptions.selectionType = eyeglassPrescriptions.selectionType.decryptString()
    decryptEyeGlassPrescriptions.classType = eyeglassPrescriptions.classType.decryptString()
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
    decryptEyeGlassPrescriptions.created = eyeglassPrescriptions.created.decryptString()
    decryptEyeGlassPrescriptions.modified = eyeglassPrescriptions.modified.decryptString()
    decryptEyeGlassPrescriptions.createdUser = eyeglassPrescriptions.createdUser.decryptString()

    return decryptEyeGlassPrescriptions
}

fun decryptGovernment(government: Government): DecryptedGovernment {
    val decryptGovernment = DecryptedGovernment()
    decryptGovernment.selectionType = government.selectionType.decryptString()
    decryptGovernment.idName = government.idName.decryptString()
    decryptGovernment.name = government.name.decryptString()
    decryptGovernment.nameOnId = government.nameOnId.decryptString()
    decryptGovernment.issuingCountry = government.issuingCountry.decryptString()
    decryptGovernment.issuingState = government.issuingState.decryptString()
    decryptGovernment.idNumber = government.idNumber.decryptString()
    decryptGovernment.dateIssued = government.dateIssued.decryptString()
    decryptGovernment.expirationDate = government.expirationDate.decryptString()
    decryptGovernment.created = government.created.decryptString()
    decryptGovernment.modified = government.modified.decryptString()
    decryptGovernment.notes = government.notes.decryptString()
    decryptGovernment.attachmentNames = government.attachmentNames.decryptString()
    decryptGovernment.createdUser = government.createdUser.decryptString()

    return decryptGovernment
}

fun decrypytHash(hash: Hash): DecryptedHash {
    val decryptHash = DecryptedHash()
    decryptHash.finalPassword = hash.finalPassword.decryptString()
    decryptHash.passcode = hash.passcode.decryptString()

    return decryptHash

}

fun decryptHealthCareProviders(healthcareProviders: HealthcareProviders): DecryptedHealthcareProviders {
    val decryptHealthCareProviders = DecryptedHealthcareProviders()
    decryptHealthCareProviders.selectionType = healthcareProviders.selectionType.decryptString()
    decryptHealthCareProviders.classType = healthcareProviders.classType.decryptString()
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
    decryptHealthCareProviders.created = healthcareProviders.created.decryptString()
    decryptHealthCareProviders.modified = healthcareProviders.modified.decryptString()
    decryptHealthCareProviders.notes = healthcareProviders.notes.decryptString()
    decryptHealthCareProviders.attachmentNames = healthcareProviders.attachmentNames.decryptString()
    decryptHealthCareProviders.createdUser = healthcareProviders.createdUser.decryptString()

    return decryptHealthCareProviders

}

fun decryptHomeAndBanking(homeBankingList: HomeBankingList): DecryptedHomeBankingList {
    val decryptHomeAndBanking = DecryptedHomeBankingList()
    decryptHomeAndBanking.selectionType = homeBankingList.selectionType.decryptString()
    decryptHomeAndBanking.listName = homeBankingList.listName.decryptString()
    decryptHomeAndBanking.dueDate = homeBankingList.dueDate.decryptString()
    decryptHomeAndBanking.created = homeBankingList.created.decryptString()
    decryptHomeAndBanking.modified = homeBankingList.modified.decryptString()

    return decryptHomeAndBanking
}

fun decryptIdentification(identification: Identification): DecryptedIdentification {
    val decryptHomeList = DecryptedIdentification()
    decryptHomeList.selectionType = identification.selectionType.decryptString()
    decryptHomeList.classType = identification.classType.decryptString()
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
    decryptHomeList.created = identification.created.decryptString()
    decryptHomeList.modified = identification.modified.decryptString()
    decryptHomeList.notes = identification.notes.decryptString()
    decryptHomeList.attachmentNames = identification.attachmentNames.decryptString()
    decryptHomeList.createdUser = identification.createdUser.decryptString()

    return decryptHomeList
}

fun decryptInterests(interest: Interests): DecryptedInterests {
    val decryptInterests = DecryptedInterests()
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
    decryptInterests.created = interest.created.decryptString()
    decryptInterests.modified = interest.modified.decryptString()
    decryptInterests.createdUser = interest.createdUser.decryptString()

    return decryptInterests
}

fun decryptInterestList(interestsList: InterestsList): DecryptedInterestsList {
    val decryptInterestList = DecryptedInterestsList()
    decryptInterestList.selectionType = interestsList.selectionType.decryptString()
    decryptInterestList.classType = interestsList.classType.decryptString()
    decryptInterestList.listName = interestsList.listName.decryptString()
    decryptInterestList.dueDate = interestsList.dueDate.decryptString()
    decryptInterestList.created = interestsList.created.decryptString()
    decryptInterestList.modified = interestsList.modified.decryptString()
    decryptInterestList.createdUser = interestsList.createdUser.decryptString()

    return decryptInterestList
}

fun decryptLicense(license: License): DecryptedLicense {
    val decryptLicense = DecryptedLicense()
    decryptLicense.selectionType = license.selectionType.decryptString()
    decryptLicense.lic_description = license.lic_description.decryptString()
    decryptLicense.nameOnLicense = license.nameOnLicense.decryptString()
    decryptLicense.issuingCountry = license.issuingCountry.decryptString()
    decryptLicense.issuingState = license.issuingState.decryptString()
    decryptLicense.licenseNumber = license.licenseNumber.decryptString()
    decryptLicense.dateIssued = license.dateIssued.decryptString()
    decryptLicense.expirationDate = license.expirationDate.decryptString()
    decryptLicense.notes = license.notes.decryptString()
    decryptLicense.created = license.created.decryptString()
    decryptLicense.modified = license.modified.decryptString()
    decryptLicense.attachmentNames = license.attachmentNames.decryptString()
    decryptLicense.createdUser = license.createdUser.decryptString()

    return decryptLicense
}

fun decryptLoyality(loyalty: Loyalty): DecryptedLoyalty {
    val decryptLoyality = DecryptedLoyalty()
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
    decryptLoyality.created = loyalty.created.decryptString()
    decryptLoyality.modified = loyalty.modified.decryptString()
    decryptLoyality.createdUser = loyalty.createdUser.decryptString()

    return decryptLoyality
}

fun decryptLoyaltyPrograms(loyalityPrograms: LoyaltyPrograms): DecryptedLoyaltyPrograms {
    val decryptLoyaltyPrograms = DecryptedLoyaltyPrograms()
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
    decryptLoyaltyPrograms.created = loyalityPrograms.created.decryptString()
    decryptLoyaltyPrograms.modified = loyalityPrograms.modified.decryptString()
    decryptLoyaltyPrograms.createdUser = loyalityPrograms.createdUser.decryptString()

    return decryptLoyaltyPrograms

}

fun decryptMainContacts(decryptMainContacts: MainContacts): DecryptedMainContacts {
    val decryptMainContacts = DecryptedMainContacts()
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
    decryptMainContacts.created = decryptMainContacts.created.decryptString()
    decryptMainContacts.modified = decryptMainContacts.modified.decryptString()
    decryptMainContacts.createdUser = decryptMainContacts.createdUser.decryptString()

    return decryptMainContacts
}

fun decrypytMainEducation(mainEducation: MainEducation): DecryptedMainEducation {
    val decrypytMainEducation = DecryptedMainEducation()
    decrypytMainEducation.selectionType = mainEducation.selectionType.decryptString()
    decrypytMainEducation.classType = mainEducation.classType.decryptString()
    decrypytMainEducation.institutionName = mainEducation.institutionName.decryptString()
    decrypytMainEducation.qualification = mainEducation.qualification.decryptString()
    decrypytMainEducation.name = mainEducation.name.decryptString()
    decrypytMainEducation.location = mainEducation.location.decryptString()
    decrypytMainEducation.major = mainEducation.major.decryptString()
    decrypytMainEducation.from = mainEducation.from.decryptString()
    decrypytMainEducation.to = mainEducation.to.decryptString()
    decrypytMainEducation.currentlyStudying = mainEducation.currentlyStudying.decryptString()
    decrypytMainEducation.notes = mainEducation.notes.decryptString()
    decrypytMainEducation.created = mainEducation.created.decryptString()
    decrypytMainEducation.modified = mainEducation.modified.decryptString()
    decrypytMainEducation.attachmentNames = mainEducation.attachmentNames.decryptString()
    decrypytMainEducation.createdUser = mainEducation.createdUser.decryptString()

    return decrypytMainEducation
}

fun decrypytMainMemories(mainMemories: MainMemories): DecryptedMainMemories {
    val decrypytMainMemories = DecryptedMainMemories()
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
    decrypytMainMemories.created = mainMemories.created.decryptString()
    decrypytMainMemories.modified = mainMemories.modified.decryptString()
    decrypytMainMemories.notes = mainMemories.notes.decryptString()
    decrypytMainMemories.attachmentNames = mainMemories.attachmentNames.decryptString()
    decrypytMainMemories.title = mainMemories.title.decryptString()
    decrypytMainMemories.createdUser = mainMemories.createdUser.decryptString()

    return decrypytMainMemories
}

fun decryptMedicalConditions(medicalConditions: MedicalConditions): DecryptedMedicalConditions {
    val decryptMedicalConditions = DecryptedMedicalConditions()

    decryptMedicalConditions.selectionType = medicalConditions.selectionType.decryptString()
    decryptMedicalConditions.classType = medicalConditions.classType.decryptString()
    decryptMedicalConditions.condition = medicalConditions.condition.decryptString()
    decryptMedicalConditions.dateDiagnosed = medicalConditions.dateDiagnosed.decryptString()
    decryptMedicalConditions.medi_description = medicalConditions.medi_description.decryptString()
    decryptMedicalConditions.notes = medicalConditions.notes.decryptString()
    decryptMedicalConditions.attachmentNames = medicalConditions.attachmentNames.decryptString()
    decryptMedicalConditions.created = medicalConditions.created.decryptString()
    decryptMedicalConditions.modified = medicalConditions.modified.decryptString()
    decryptMedicalConditions.createdUser = medicalConditions.createdUser.decryptString()

    return decryptMedicalConditions
}

fun decryptMedicalHistory(medicalHistory: MedicalHistory): DecryptedMedicalHistory {
    val decryptMedicalHistory = DecryptedMedicalHistory()

    decryptMedicalHistory.selectionType = medicalHistory.selectionType.decryptString()
    decryptMedicalHistory.classType = medicalHistory.classType.decryptString()
    decryptMedicalHistory.history = medicalHistory.history.decryptString()
    decryptMedicalHistory.treatmentDiscription = medicalHistory.treatmentDiscription.decryptString()
    decryptMedicalHistory.immunizationDiscription = medicalHistory.immunizationDiscription.decryptString()
    decryptMedicalHistory.familyDiscription = medicalHistory.familyDiscription.decryptString()
    decryptMedicalHistory.created = medicalHistory.created.decryptString()
    decryptMedicalHistory.modified = medicalHistory.modified.decryptString()
    decryptMedicalHistory.notes = medicalHistory.notes.decryptString()
    decryptMedicalHistory.attachmentNames = medicalHistory.attachmentNames.decryptString()
    decryptMedicalHistory.createdUser = medicalHistory.createdUser.decryptString()


    return decryptMedicalHistory
}

fun decryptMedications(medications: Medications): DecryptedMedications {
    val decryptMedications = DecryptedMedications()
    decryptMedications.selectionType = medications.selectionType.decryptString()
    decryptMedications.classType = medications.classType.decryptString()
    decryptMedications.name = medications.name.decryptString()
    decryptMedications.strength = medications.strength.decryptString()
    decryptMedications.frequency = medications.frequency.decryptString()
    decryptMedications.startDate = medications.startDate.decryptString()
    decryptMedications.endDate = medications.endDate.decryptString()
    decryptMedications.notes = medications.notes.decryptString()
    decryptMedications.attachmentNames = medications.attachmentNames.decryptString()
    decryptMedications.created = medications.created.decryptString()
    decryptMedications.modified = medications.modified.decryptString()
    decryptMedications.createdUser = medications.createdUser.decryptString()

    return decryptMedications
}

fun decryptMemoriesList(memoriesList: MemoriesList): DecryptedMemoriesList {
    val decryptMemoriesList = DecryptedMemoriesList()

    decryptMemoriesList.selectionType = memoriesList.selectionType.decryptString()
    decryptMemoriesList.classType = memoriesList.classType.decryptString()
    decryptMemoriesList.listName = memoriesList.listName.decryptString()
    decryptMemoriesList.dueDate = memoriesList.dueDate.decryptString()
    decryptMemoriesList.created = memoriesList.created.decryptString()
    decryptMemoriesList.modified = memoriesList.modified.decryptString()
    decryptMemoriesList.createdUser = memoriesList.createdUser.decryptString()

    return decryptMemoriesList
}

fun decryptMemoryTimeLine(memoryTimeline: MemoryTimeline): DecryptedMemoryTimeline {
    val decryptMemoryTimeLine = DecryptedMemoryTimeline()
    decryptMemoryTimeLine.selectionType = memoryTimeline.selectionType.decryptString()
    decryptMemoryTimeLine.title = memoryTimeline.title.decryptString()
    decryptMemoryTimeLine.date = memoryTimeline.date.decryptString()
    decryptMemoryTimeLine.place = memoryTimeline.place.decryptString()
    decryptMemoryTimeLine.contacts = memoryTimeline.contacts.decryptString()
    decryptMemoryTimeLine.notes = memoryTimeline.notes.decryptString()
    decryptMemoryTimeLine.attachmentNames = memoryTimeline.attachmentNames.decryptString()
    decryptMemoryTimeLine.created = memoryTimeline.created.decryptString()
    decryptMemoryTimeLine.modified = memoryTimeline.modified.decryptString()
    decryptMemoryTimeLine.createdUser = memoryTimeline.createdUser.decryptString()
    return decryptMemoryTimeLine
}

fun decryptNotifications(notifications: Notifications): DecryptedNotifications {
    val decryptNotifications = DecryptedNotifications()
    decryptNotifications.message = notifications.message.decryptString()
    decryptNotifications.boxName = notifications.boxName.decryptString()
    decryptNotifications.dueDate = notifications.dueDate.decryptString()
    decryptNotifications.subTitle = notifications.subTitle.decryptString()
    decryptNotifications.notifyDate = notifications.notifyDate.decryptString()
    decryptNotifications.created = notifications.created.decryptString()
    decryptNotifications.modified = notifications.modified.decryptString()
    return decryptNotifications
}

fun decryptPersonal(personal: Personal): DecryptedPersonal {
    val decryptPersonal = DecryptedPersonal()
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
    decryptPersonal.created = personal.created.decryptString()
    decryptPersonal.modified = personal.modified.decryptString()
    decryptPersonal.createdUser = personal.createdUser.decryptString()

    return decryptPersonal
}

fun decryptPersonalList(personalList: PersonalList): DecryptedPersonalList {
    val decryptPersonalList = DecryptedPersonalList()
    decryptPersonalList.selectionType = personalList.selectionType.decryptString()
    decryptPersonalList.classType = personalList.classType.decryptString()
    decryptPersonalList.listName = personalList.listName.decryptString()
    decryptPersonalList.dueDate = personalList.dueDate.decryptString()
    decryptPersonalList.created = personalList.created.decryptString()
    decryptPersonalList.modified = personalList.modified.decryptString()
    decryptPersonalList.createdUser = personalList.createdUser.decryptString()
    return decryptPersonalList
}

fun decryptRecentPurchase(recentPurchase: RecentPurchase): DecryptedRecentPurchase {
    val decryptRecentPurchase = DecryptedRecentPurchase()
    decryptRecentPurchase.selectionType = recentPurchase.selectionType.decryptString()
    decryptRecentPurchase.brandName = recentPurchase.brandName.decryptString()
    decryptRecentPurchase.itemName = recentPurchase.itemName.decryptString()
    decryptRecentPurchase.purchasedBy = recentPurchase.purchasedBy.decryptString()
    decryptRecentPurchase.purchasedDate = recentPurchase.purchasedDate.decryptString()
    decryptRecentPurchase.purchasedPrice = recentPurchase.purchasedPrice.decryptString()
    decryptRecentPurchase.notes = recentPurchase.notes.decryptString()
    decryptRecentPurchase.created = recentPurchase.created.decryptString()
    decryptRecentPurchase.modified = recentPurchase.modified.decryptString()
    decryptRecentPurchase.attachmentNames = recentPurchase.attachmentNames.decryptString()
    decryptRecentPurchase.createdUser = recentPurchase.createdUser.decryptString()

    return decryptRecentPurchase
}

fun decryptRecentSearch(recentSearch: RecentSearch): DecryptedRecentSearch {
    val decryptRecentSearch = DecryptedRecentSearch()
    decryptRecentSearch.listName = recentSearch.listName.decryptString()
    decryptRecentSearch.subCategory = recentSearch.subCategory.decryptString()
    decryptRecentSearch.mainCategory = recentSearch.mainCategory.decryptString()
    decryptRecentSearch.classType = recentSearch.classType.decryptString()
    return decryptRecentSearch
}

fun decryptShopping(shopping: Shopping): DecryptedShopping {
    val decryptShopping = DecryptedShopping()
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
    decryptShopping.created = shopping.created.decryptString()
    decryptShopping.modified = shopping.modified.decryptString()

    return decryptShopping
}

fun decryptShoppingList(shoppingList: ShoppingList): DecryptedShoppingList {
    val decryptShoppingList = DecryptedShoppingList()
    decryptShoppingList.selectionType = shoppingList.selectionType.decryptString()
    decryptShoppingList.classType = shoppingList.classType.decryptString()
    decryptShoppingList.listName = shoppingList.listName.decryptString()
    decryptShoppingList.dueDate = shoppingList.dueDate.decryptString()
    decryptShoppingList.created = shoppingList.created.decryptString()
    decryptShoppingList.modified = shoppingList.modified.decryptString()
    decryptShoppingList.createdUser = shoppingList.createdUser.decryptString()
    return decryptShoppingList
}

fun decryptSignUp(signUp: SignUp): DecryptedSignUp {
    val decryptSignUp = DecryptedSignUp()
    decryptSignUp.fullName = signUp.fullName.decryptString()
    decryptSignUp.emailAddress = signUp.emailAddress.decryptString()
    decryptSignUp.password = signUp.password.decryptString()
    decryptSignUp.user_id = signUp.user_id.decryptString()
    return decryptSignUp
}

fun decryptSocial(social: Social): DecryptedSocial {
    val decryptSocial = DecryptedSocial()
    decryptSocial.selectionType = social.selectionType.decryptString()
    decryptSocial.cardName = social.cardName.decryptString()
    decryptSocial.nameOnCard = social.nameOnCard.decryptString()
    decryptSocial.socialSecurityNumber = social.socialSecurityNumber.decryptString()
    decryptSocial.notes = social.notes.decryptString()
    decryptSocial.created = social.created.decryptString()
    decryptSocial.modified = social.modified.decryptString()
    decryptSocial.attachmentNames = social.attachmentNames.decryptString()
    decryptSocial.createdUser = social.createdUser.decryptString()

    return decryptSocial

}

fun decryptTaxID(taxID: TaxID): DecryptedTaxID {
    val decryptTaxID = DecryptedTaxID()
    decryptTaxID.selectionType = taxID.selectionType.decryptString()
    decryptTaxID.taxIdName = taxID.taxIdName.decryptString()
    decryptTaxID.taxIdNumber = taxID.taxIdNumber.decryptString()
    decryptTaxID.issuingCountry = taxID.issuingCountry.decryptString()
    decryptTaxID.name = taxID.name.decryptString()
    decryptTaxID.notes = taxID.notes.decryptString()
    decryptTaxID.created = taxID.created.decryptString()
    decryptTaxID.modified = taxID.modified.decryptString()
    decryptTaxID.attachmentNames = taxID.attachmentNames.decryptString()
    return decryptTaxID

}

fun decryptTravel(travel: Travel): DecryptedTravel {
    val decryptTravel = DecryptedTravel()
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
    decryptTravel.created = travel.created.decryptString()
    decryptTravel.modified = travel.modified.decryptString()
    decryptTravel.createdUser = travel.createdUser.decryptString()
    return decryptTravel

}

fun decryptTravelList(travelList: TravelList): DecryptedTravelList {
    val decryptTravelList = DecryptedTravelList()
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
    decryptVacations.selectionType = vacations.selectionType.decryptString()
    decryptVacations.vac_description = vacations.vac_description.decryptString()
    decryptVacations.startDate = vacations.startDate.decryptString()
    decryptVacations.endDate = vacations.endDate.decryptString()
    decryptVacations.placesToVisit_1 = vacations.placesToVisit_1.decryptString()
    decryptVacations.placesToVisit_2 = vacations.placesToVisit_2.decryptString()
    decryptVacations.placesToVisit_3 = vacations.placesToVisit_3.decryptString()
    decryptVacations.notes = vacations.notes.decryptString()
    decryptVacations.attachmentNames = vacations.attachmentNames.decryptString()
    decryptVacations.created = vacations.created.decryptString()
    decryptVacations.modified = vacations.modified.decryptString()
    decryptVacations.createdUser = vacations.createdUser.decryptString()


    return decryptVacations
}

fun decryptVitalNumbers(vitalNumbers: VitalNumbers): DecryptedVitalNumbers {
    val decryptVitalNumbers = DecryptedVitalNumbers()
    decryptVitalNumbers.selectionType = vitalNumbers.selectionType.decryptString()
    decryptVitalNumbers.classType = vitalNumbers.classType.decryptString()
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
    decryptVitalNumbers.created = vitalNumbers.created.decryptString()
    decryptVitalNumbers.modified = vitalNumbers.modified.decryptString()
    decryptVitalNumbers.notes = vitalNumbers.notes.decryptString()
    decryptVitalNumbers.attachmentNames = vitalNumbers.attachmentNames.decryptString()
    decryptVitalNumbers.createdUser = vitalNumbers.createdUser.decryptString()

    return decryptVitalNumbers
}

fun decryptWellness(wellness: Wellness): DecryptedWellness {
    val decryptWellness = DecryptedWellness()
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
    decryptWellness.created = wellness.created.decryptString()
    decryptWellness.modified = wellness.modified.decryptString()
    decryptWellness.createdUser = wellness.createdUser.decryptString()


    return decryptWellness
}

fun decryptWellnessList(wellnessList: WellnessList): DecryptedWellnessList {
    val decryptWellnessList = DecryptedWellnessList()
    decryptWellnessList.selectionType = wellnessList.selectionType.decryptString()
    decryptWellnessList.classType = wellnessList.classType.decryptString()
    decryptWellnessList.listName = wellnessList.listName.decryptString()
    decryptWellnessList.dueDate = wellnessList.dueDate.decryptString()
    decryptWellnessList.created = wellnessList.created.decryptString()
    decryptWellnessList.modified = wellnessList.modified.decryptString()
    decryptWellnessList.createdUser = wellnessList.createdUser.decryptString()

    return decryptWellnessList
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








































