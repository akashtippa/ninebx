package com.ninebx.utility

import android.util.Log
import com.ninebx.ui.auth.passwordHash.CustomKeyParameter
import com.ninebx.ui.auth.passwordHash.CustomPBEParametersGenerator
import com.ninebx.ui.auth.passwordHash.CustomPKCS5S2ParametersGenerator
import org.spongycastle.crypto.digests.SHA256Digest
import java.security.*
import java.util.*
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException
import javax.crypto.ShortBufferException
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

fun encryptAESKey( ) : String {
    Security.addProvider(org.bouncycastle.jce.provider.BouncyCastleProvider())

    val input = "Password14.".toByteArray(Charsets.UTF_8)
    val keyBytes = byteArrayOf(0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17)

    val key = SecretKeySpec(keyBytes, "AES")
    val plainText : ByteArray
    val cipherText : ByteArray
    try {
        val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        cipherText = ByteArray(cipher.getOutputSize(input.size))
        val ctLength = cipher.update(input, 0, input.size, cipherText, 0)
        cipher.init(Cipher.DECRYPT_MODE, key)
        Log.d("Encryption", "Encryption String" + convertToUInt8(cipherText))
        plainText = ByteArray(cipher.getOutputSize(cipherText.size))
        val ptLength = cipher.update(cipherText, 0, cipherText.size, plainText, 0)
        //          ptLength += cipher.doFinal(plainText, ptLength);
        Log.d("Decryption", "Decryption String" + convertToUInt8(plainText))

        return convertToUInt8(cipherText)
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    } catch (e: InvalidKeyException) {
        e.printStackTrace()
    } catch (e: ShortBufferException) {
        e.printStackTrace()
    } catch (e: NoSuchPaddingException) {
        e.printStackTrace()
    } catch (e: NoSuchProviderException) {
        e.printStackTrace()
    }

    return ""

}

fun decryptAESKey( masterPassword : String ) : String {

    Security.addProvider(org.bouncycastle.jce.provider.BouncyCastleProvider())

    val input = "www.javacodegeeks.com".toByteArray()
    val keyBytes = ( masterPassword.toByteArray(Charsets.UTF_8) )
    val key = SecretKeySpec(keyBytes, "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")
    AppLogger.d("decryptAESKey", "Master Password : " + Arrays.toString(keyBytes))
    AppLogger.d("decryptAESKey", "Input : " + String(input))
    AppLogger.d("decryptAESKey", "Input Bytes : " + Arrays.toString(input))

    // encryption pass
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val cipherText = ByteArray(cipher.getOutputSize(input.size))
    var ctLength = cipher.update(input, 0, input.size, cipherText, 0)
    ctLength += cipher.doFinal(cipherText, ctLength)

    AppLogger.d("decryptAESKey", "Cipher : " + Arrays.toString(cipherText))
    val convertedCipher = convertToUInt8(cipherText)
    AppLogger.d("decryptAESKey", "Cipher iOS : " + convertedCipher )
    AppLogger.d("decryptAESKey", "Cipher Android : " + convertToByte(convertedCipher.toCharArray()) )
    AppLogger.d("decryptAESKey", "Cipher Length : " + cipherText.size)


    // decryption pass
    cipher.init(Cipher.DECRYPT_MODE, key)
    val plainText = ByteArray(cipher.getOutputSize(ctLength))
    var ptLength = cipher.update(cipherText, 0, plainText.size, plainText, 0)
    ptLength += cipher.doFinal(plainText, ptLength)

    AppLogger.d("decryptAESKey", "Plain Text : " + String(plainText).substring(0, ptLength))
    AppLogger.d("decryptAESKey", "Plain Text Bytes : " + Arrays.toString(plainText))
    AppLogger.d("decryptAESKey", "ptLength : " + plainText.size)

    return ""

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
fun encryptKey( password: String, salt: String ) : String {
    val generator = CustomPKCS5S2ParametersGenerator(SHA256Digest())
    generator.init(CustomPBEParametersGenerator.PKCS5PasswordToUTF8Bytes(password.toCharArray()), salt.toByteArray(Charsets.UTF_8), 20000)
    val key = generator.generateDerivedMacParameters(256) as CustomKeyParameter
    return convertToUInt8(key.key)
}

private fun convertToUInt8( key: ByteArray? ): String {

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