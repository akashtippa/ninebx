package com.ninebx.utility

import android.util.Base64
import com.ninebx.NineBxApplication
import com.ninebx.ui.auth.passwordHash.CustomKeyParameter
import com.ninebx.ui.auth.passwordHash.CustomPBEParametersGenerator
import com.ninebx.ui.auth.passwordHash.CustomPKCS5S2ParametersGenerator
import com.ninebx.ui.base.realm.Member
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.base.realm.decrypted.DecryptedUsers
import io.realm.RealmList
import org.spongycastle.crypto.digests.SHA256Digest
import java.security.*
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

    currentUser.fullName.encryptString()
    currentUser.firstName.encryptString()
    currentUser.lastName.encryptString()
    currentUser.emailAddress.encryptString()
    currentUser.relationship.encryptString()
    currentUser.dateOfBirth.encryptString()
    currentUser.anniversary.encryptString()
    currentUser.gender.encryptString()
    currentUser.mobileNumber.encryptString()

    currentUser.street_1.encryptString()
    currentUser.street_2.encryptString()
    currentUser.city.encryptString()
    currentUser.state.encryptString()
    currentUser.zipCode.encryptString()
    currentUser.country.encryptString()
    currentUser.userId.encryptString()
    currentUser.profilePhoto.encryptString()

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

fun decryptUsers(currentUser: Users ) : DecryptedUsers {
    
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

    //decryptedUsers.fullName = currentUser.decryptedMembers = decryptMembers( currentUser.members )
    AppLogger.d("Decrypt", "decryptUSers : " + decryptedUsers )
    return decryptedUsers
}

fun decryptMembers( members: RealmList<Member> ): RealmList<Member>? {
    for( i in 0 until members.size ) {
        var member = members[i]
        member = decryptMember(member!!)
    }
    return members
}

fun decryptMember(member: Member): Member? {

    member.firstName            .decryptString()
    member. lastName             .decryptString()
    member. relationship         .decryptString()
    member. role                 .decryptString()
    member. email                .decryptString()

    member.dateOfBirth         .decryptString()
    member.anniversary         .decryptString()
    member.gender              .decryptString()
    member.mobileNumber        .decryptString()
    member.street_1            .decryptString()
    member.street_2            .decryptString()
    member.city                .decryptString()
    member.state               .decryptString()
    member.zipCode             .decryptString()
    member.country             .decryptString()

    return member
}