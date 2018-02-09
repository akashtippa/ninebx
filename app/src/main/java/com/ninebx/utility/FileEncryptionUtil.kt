package com.ninebx.utility

import android.os.Environment
import org.cryptonode.jncryptor.AES256JNCryptor
import java.io.*
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.util.*



/**
 * Created by Alok on 22/01/18.
 */
//Encrypt file
/**
 *
var encryptedData = RNCryptor.encrypt(data: data as Data, withPassword: hashKey.description)
try encryptedData.write(to: fileURL)
 fileBytes - 3,1,-54,106
 encrypted - 3,1,71,68
 * */
fun encryptFile( inputFile : File, privateKey : CharArray ) : File {

    val size = inputFile.length().toInt()
    val fileBytes = ByteArray(size)
    val aeS256JNCryptor = AES256JNCryptor()
    val file = File(Environment.getExternalStorageDirectory().toString() + "/Encrypted_" + inputFile.name)
    try {
        val buf = BufferedInputStream(FileInputStream(inputFile))
        buf.read(fileBytes, 0, fileBytes.size)

        val encryptedFileBytes = aeS256JNCryptor.encryptData(fileBytes, privateKey)

        val bufOut = BufferedOutputStream(FileOutputStream(file))
        bufOut.write(encryptedFileBytes)

        buf.close()
        bufOut.close()

    } catch (e: FileNotFoundException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    } catch (e: IOException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }

    return file

}

//Decrypt file
/**
 *
let encryptedData = fileManager.contents(atPath: filePathNormal)
let decryptedData = try RNCryptor.decryptUsers(data: encryptedData!, withPassword: hashKey.description)
let selected_image = UIImage.sd_image(with: decryptedData)
encrypted - 3,1,71,68
decrypted Bytes - 3,1,-54,106
 * */
fun decryptFile( inputFile : File, privateKey: CharArray ) : File {

    val size = inputFile.length().toInt()
    val fileBytes = ByteArray(size)
    val aeS256JNCryptor = AES256JNCryptor()
    val file = File(Environment.getExternalStorageDirectory().toString() + "/Decrypted_" + inputFile.name)
    try {
        val buf = BufferedInputStream(FileInputStream(inputFile))
        buf.read(fileBytes, 0, fileBytes.size)

        val decryptedFileBytes = aeS256JNCryptor.decryptData(fileBytes, privateKey)

        if( file.exists() ) file.delete()

        val fileOutputStream = FileOutputStream( file.absolutePath )
        fileOutputStream.write(decryptedFileBytes)

        buf.close()
        fileOutputStream.close()

    } catch (e: FileNotFoundException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    } catch (e: IOException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }

    return file
}

private val TAG = "FileEncryptionUtil"

//Encrypt file
/**
 *
var encryptedData = RNCryptor.encrypt(data: data as Data, withPassword: hashKey.description)
try encryptedData.write(to: fileURL)
fileBytes - 3,1,-54,106
encrypted - 3,1,71,68
 * */
fun encryptFileIOS( inputFile : File, privateKey : CharArray ) : File {

    val size = inputFile.length().toInt()
    val fileBytes = ByteArray(size)
    val aeS256JNCryptor = AES256JNCryptor()
    val file = File(Environment.getExternalStorageDirectory().toString() + "/Encrypted_" + inputFile.name)
    val convertedPrivateKey = convertToUInt8(toBytes(privateKey))
    AppLogger.d(TAG, "encryptFileIOS : Encrypted password Array : " + privateKey )
    AppLogger.d(TAG, "encryptFileIOS : Encrypted password iOS Format : " + convertedPrivateKey)
    try {
        val buf = BufferedInputStream(FileInputStream(inputFile))
        buf.read(fileBytes, 0, fileBytes.size)

        val encryptedFileBytes = aeS256JNCryptor.encryptData(fileBytes, convertedPrivateKey.toCharArray())

        val bufOut = BufferedOutputStream(FileOutputStream(file))
        bufOut.write(encryptedFileBytes)

        buf.close()
        bufOut.close()

    } catch (e: FileNotFoundException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    } catch (e: IOException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }

    return file

}

//Decrypt file
/**
 *
let encryptedData = fileManager.contents(atPath: filePathNormal)
let decryptedData = try RNCryptor.decryptUsers(data: encryptedData!, withPassword: hashKey.description)
let selected_image = UIImage.sd_image(with: decryptedData)
encrypted - 3,1,71,68
decrypted Bytes - 3,1,-54,106
 * */
fun decryptFileIOS( inputFile : File, privateKey: CharArray ) : File {

    val size = inputFile.length().toInt()
    val fileBytes = ByteArray(size)
    val aeS256JNCryptor = AES256JNCryptor()
    val file = File(Environment.getExternalStorageDirectory().toString() + "/Decrypted_" + inputFile.name)
    val convertedPrivateKey = convertToUInt8(toBytes(privateKey))
    AppLogger.d(TAG, "decryptFileIOS : Encrypted password Array : " + privateKey )
    AppLogger.d(TAG, "decryptFileIOS : Encrypted password iOS Format : " + convertedPrivateKey)

    try {
        val buf = BufferedInputStream(FileInputStream(inputFile))
        buf.read(fileBytes, 0, fileBytes.size)

        val decryptedFileBytes = aeS256JNCryptor.decryptData(fileBytes, convertedPrivateKey.toCharArray())

        if( file.exists() ) file.delete()

        val fileOutputStream = FileOutputStream( file.absolutePath )
        fileOutputStream.write(decryptedFileBytes)

        buf.close()
        fileOutputStream.close()

    } catch (e: FileNotFoundException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    } catch (e: IOException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }

    return file
}

private fun toBytes(chars: CharArray): ByteArray {
    val charBuffer = CharBuffer.wrap(chars)
    val byteBuffer = Charset.forName("UTF-8").encode(charBuffer)
    val bytes = Arrays.copyOfRange(byteBuffer.array(),
            byteBuffer.position(), byteBuffer.limit())
    Arrays.fill(charBuffer.array(), '\u0000') // clear sensitive data
    Arrays.fill(byteBuffer.array(), 0.toByte()) // clear sensitive data
    return bytes
}




