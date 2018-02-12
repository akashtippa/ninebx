package com.ninebx.utility

import android.util.Base64
import com.ninebx.NineBxApplication
import com.ninebx.ui.auth.passwordHash.CustomKeyParameter
import com.ninebx.ui.auth.passwordHash.CustomPBEParametersGenerator
import com.ninebx.ui.auth.passwordHash.CustomPKCS5S2ParametersGenerator
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.homeBanking.*
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

fun encryptAESKeyPassword( inputString : String, privateKey : ByteArray ) : String {

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

    val cipherTextBase64 = Base64.encode( cipherText, Base64.DEFAULT )

    return String( cipherTextBase64 )

}

fun decryptAESKEYPassword( cipherTextBase64: ByteArray?, masterPassword : ByteArray ) : String {

    val keyBytes = ( masterPassword )
    val key = SecretKeySpec(keyBytes, "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")

    AppLogger.d("decryptAESKey", "Cipher : " + (cipherTextBase64))
    val convertedCipher = convertToUInt8(cipherTextBase64)
    AppLogger.d("decryptAESKey", "Cipher iOS : " + convertedCipher )
    AppLogger.d("decryptAESKey", "Cipher Android : " + convertToByte(convertedCipher.toCharArray()) )

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

fun encryptAESKey( inputString : String, privateKey : String ) : String {

    Security.addProvider(org.bouncycastle.jce.provider.BouncyCastleProvider())

    val input = inputString.toByteArray()
    val keyBytes = ( privateKey.toByteArray(Charsets.UTF_8) )
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

    val cipherTextBase64 = Base64.encode( cipherText, Base64.DEFAULT )

    return String( cipherTextBase64 )

}

fun decryptAESKEY( cipherTextBase64: ByteArray?, masterPassword : String ) : String {

    val keyBytes = ( masterPassword.toByteArray(Charsets.UTF_8) )
    val key = SecretKeySpec(keyBytes, "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")

    AppLogger.d("decryptAESKey", "Cipher : " + (cipherTextBase64))
    val convertedCipher = convertToUInt8(cipherTextBase64)
    AppLogger.d("decryptAESKey", "Cipher iOS : " + convertedCipher )
    AppLogger.d("decryptAESKey", "Cipher Android : " + convertToByte(convertedCipher.toCharArray()) )

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

fun encryptDecryptAESKey( masterPassword : String ) : String {

    Security.addProvider(org.bouncycastle.jce.provider.BouncyCastleProvider())

    val input = "www.javacodegeeks.com".toByteArray()
    val keyBytes = ( masterPassword.toByteArray(Charsets.UTF_8) )
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

    val cipherTextBase64 = Base64.encode( cipherText, Base64.DEFAULT )

    AppLogger.d("decryptAESKey", "Cipher : " + Arrays.toString(cipherTextBase64))
    val convertedCipher = convertToUInt8(cipherTextBase64)
    AppLogger.d("decryptAESKey", "Cipher iOS : " + convertedCipher )
    AppLogger.d("decryptAESKey", "Cipher Android : " + convertToByte(convertedCipher.toCharArray()) )
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

fun encryptAESKey( masterPassword : String ) : String {

    Security.addProvider(org.bouncycastle.jce.provider.BouncyCastleProvider())

    val input = "www.javacodegeeks.com".toByteArray()
    val keyBytes = ( masterPassword.toByteArray(Charsets.UTF_8) )
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

    val cipherTextBase64 = Base64.encode( cipherText, Base64.DEFAULT )

    decryptAESKey( cipherTextBase64, masterPassword )



    return ""

}

fun decryptAESKey(cipherTextBase64: ByteArray?, masterPassword: String) {

    val keyBytes = ( masterPassword.toByteArray(Charsets.UTF_8) )
    val key = SecretKeySpec(keyBytes, "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")

    AppLogger.d("decryptAESKey", "Cipher : " + Arrays.toString(cipherTextBase64))
    val convertedCipher = convertToUInt8(cipherTextBase64)
    AppLogger.d("decryptAESKey", "Cipher iOS : " + convertedCipher )
    AppLogger.d("decryptAESKey", "Cipher Android : " + convertToByte(convertedCipher.toCharArray()) )

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
fun encryptKey( password: String, salt: String ) : ByteArray {
    val generator = CustomPKCS5S2ParametersGenerator(SHA256Digest())
    generator.init(CustomPBEParametersGenerator.PKCS5PasswordToUTF8Bytes(password.toCharArray()), salt.toByteArray(Charsets.UTF_8), 20000)
    val key = generator.generateDerivedMacParameters(256) as CustomKeyParameter
    return key.key
}

fun convertToUInt8IntArray( key: ByteArray? ): IntArray {

    val intArray = kotlin.IntArray(key!!.size)
    for( index in 0 until key.size ) {
        val keyValue = key[index]
        var indexKey = keyValue.toInt()
        if(indexKey < 0) {
            indexKey += 256
            intArray[index] = indexKey
        }
        else {
            intArray[index] = indexKey
        }
    }
    return (intArray)

}

fun convertToUInt8( key: ByteArray? ): String {

    val intArray = kotlin.IntArray(key!!.size)
    for( index in 0 until key.size ) {
        val keyValue = key[index]
        var indexKey = keyValue.toInt()
        if(indexKey < 0) {
            indexKey += 256
            intArray[index] = indexKey
        }
        else {
            intArray[index] = indexKey
        }
    }
    return Arrays.toString(intArray)

}

private fun convertToByte( key: CharArray ): String {

    val intArray = kotlin.IntArray(key.size)
    for( index in 0 until key.size ) {
        val keyValue = key[index]
        var indexKey = keyValue.toInt()
        if(indexKey > 128) {
            indexKey -= 256
            intArray[index] = indexKey
        }
        else {
            intArray[index] = indexKey
        }
    }
    return Arrays.toString(intArray)

}

fun isValidPassword(password: String): Boolean {

    if( password.length < 8 ) {
        return false
    }

    val pattern: Pattern
    val matcher: Matcher

    val PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\$@\$#!%?&.,/^*()_+|}{';>~`<]).*\$"

    pattern = Pattern.compile(PASSWORD_PATTERN)
    matcher = pattern.matcher(password)

    return matcher.matches()
}


fun securityTest( ) {
    val privateKey = randomString(16)

    val encryptedKey = encryptAESKey( privateKey, privateKey )
    AppLogger.d("securityTest", "Encrypted Key : " + encryptedKey)

    val decryptedKey = decryptAESKEY( encryptedKey.toByteArray(Charsets.UTF_8), privateKey )
    AppLogger.d("securityTest", "Decrypted Key : " + decryptedKey)
}

fun String.encryptString( ) : String {
    return encryptAESKey( this, NineBxApplication.getPreferences().privateKey!! )
}

fun String.decryptString( ) : String {
    return decryptAESKEY( this.toByteArray(), NineBxApplication.getPreferences().privateKey!! )
}

fun encryptUsers( currentUser: Users ): Users {

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

    currentUser.decryptedMembers = encryptMembers( currentUser.members )

    return currentUser
    
}

fun encryptMembers( members: RealmList<Member> ): RealmList<Member>? {
    for( i in 0 until members.size ) {
        var member = members[i]
        member = encryptMember(member!!)
    }
    return members
}

fun encryptMember(member: Member): Member? {

    member.firstName            .encryptString()
    member. lastName             .encryptString()
    member. relationship         .encryptString()
    member. role                 .encryptString()
    member. email                .encryptString()

    member.dateOfBirth         .encryptString()
    member.anniversary         .encryptString()
    member.gender              .encryptString()
    member.mobileNumber        .encryptString()
    member.street_1            .encryptString()
    member.street_2            .encryptString()
    member.city                .encryptString()
    member.state               .encryptString()
    member.zipCode             .encryptString()
    member.country             .encryptString()

    member.userId              .encryptString()
    
    member.profilePhoto        .encryptString()
    return member
}

fun decryptUsers( currentUser: Users ) : DecryptedUsers {
    
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

    decryptedUsers.decryptedMembers = decryptMembers( currentUser.members )
    AppLogger.d("Decrypt", "decryptUSers : " + decryptedUsers )
    return decryptedUsers
}

fun decryptMembers( members: RealmList<Member> ): RealmList<DecryptedMember>? {
    val decryptedMembers = RealmList<DecryptedMember>()
    for( i in 0 until members.size ) {
        val member = members[i]
        val decryptedMember = decryptMember( member!!)
        decryptedMembers.add( decryptedMember )
    }
    return decryptedMembers
}

fun decryptMember(member: Member): DecryptedMember? {

    val decryptedMember = DecryptedMember()
    
    decryptedMember.firstName = member.firstName            .decryptString()
    decryptedMember.lastName = member. lastName             .decryptString()
    decryptedMember.relationship = member. relationship         .decryptString()
    decryptedMember.role = member. role                 .decryptString()
    decryptedMember.email = member. email                .decryptString()

    decryptedMember.dateOfBirth = member.dateOfBirth         .decryptString()
    decryptedMember.anniversary = member.anniversary         .decryptString()
    decryptedMember.gender = member.gender              .decryptString()
    decryptedMember.mobileNumber = member.mobileNumber        .decryptString()
    decryptedMember.street_1 = member.street_1            .decryptString()
    decryptedMember.street_2 = member.street_2            .decryptString()
    decryptedMember.city = member.city                .decryptString()
    decryptedMember.state = member.state               .decryptString()
    decryptedMember.zipCode = member.zipCode             .decryptString()
    decryptedMember.country = member.country             .decryptString()

    decryptedMember.userId              = member.userId
    ///For permissions
    decryptedMember.homeAdd                = member.homeAdd
    decryptedMember.homeEdit               = member.homeEdit
    decryptedMember.homeView               = member.homeView

    decryptedMember.travelAdd              = member.travelAdd
    decryptedMember.travelEdit             = member.travelEdit
    decryptedMember.travelView             = member.travelView

    decryptedMember.contactsAdd              = member.contactsAdd
    decryptedMember.contactsEdit             = member.contactsEdit
    decryptedMember.contactsView             = member.contactsView

    decryptedMember.educationlAdd              = member.educationlAdd
    decryptedMember.educationlEdit             = member.educationlEdit
    decryptedMember.educationlView             = member.educationlView

    decryptedMember.personalAdd              = member.personalAdd
    decryptedMember.personalEdit             = member.personalEdit
    decryptedMember.personalView             = member.personalView

    decryptedMember.interestsAdd              = member.interestsAdd
    decryptedMember.interestsEdit             = member.interestsEdit
    decryptedMember.interestsView             = member.interestsView

    decryptedMember.wellnessAdd              = member.wellnessAdd
    decryptedMember.wellnessEdit             = member.wellnessEdit
    decryptedMember.wellnessView             = member.wellnessView

    decryptedMember.memoriesAdd              = member.memoriesAdd
    decryptedMember.memoriesEdit             = member.memoriesEdit
    decryptedMember.memoriesView             = member.memoriesView

    decryptedMember.shoppingAdd              = member.shoppingAdd
    decryptedMember.shoppingEdit             = member.shoppingEdit
    decryptedMember.shoppingView             = member.shoppingView

    decryptedMember.addingRemovingMember    = member.addingRemovingMember
    decryptedMember.changingMasterPassword  = member.changingMasterPassword

    decryptedMember.isCompleteProfile     = member.completeProfile
    decryptedMember.profilePhoto        = "";

    return decryptedMember
}

fun decryptFinancial(finance : Financial) : DecryptedFinancial {

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

    AppLogger.d("Decrypt", "decryptedFinancial : " + decryptedFinancial )

    return decryptedFinancial
}

fun decryptPayment(payment: Payment) : DecryptedPayment {

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

    AppLogger.d("Decrypt", "decryptedPayment : " + decryptedPayment )

    return decryptedPayment
}

fun decryptProperty(property : Property) : DecryptedProperty{
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

    AppLogger.d("Decrypt", "decryptedProperty : " + decryptedProperty )

    return decryptedProperty
}

fun decryptVehicle(vehicle: Vehicle) : DecryptedVehicle {
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

    AppLogger.d("Decrypt", "decryptedVehicle : " + decryptedVehicle )
    return decryptedVehicle
}

fun decryptAsset(asset: Asset) : DecryptedAsset{
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

    AppLogger.d("Decrypt", "decryptedAsset : " + decryptedAsset )

    return decryptedAsset
}

fun decryptInsurance(insurance: Insurance) : DecryptedInsurance{
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

    AppLogger.d("Decrypt", "decryptedInsurance : " + decryptedInsurance )
    return decryptedInsurance
}

fun decryptTaxes(taxes: Taxes) : DecryptedTax{
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

    AppLogger.d("Decrypt", "decryptedTax : " + decryptedTax )
    return decryptedTax
}

fun decryptHomeList(homeList : HomeList) : DecryptedHomeList{
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

    AppLogger.d("Decrypt", "decryptedHomeList : " + decryptedHomeList )
    return decryptedHomeList
}
