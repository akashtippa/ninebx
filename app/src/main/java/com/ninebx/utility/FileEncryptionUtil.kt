package com.ninebx.utility

import android.os.Environment
import org.cryptonode.jncryptor.AES256JNCryptor
import java.io.*
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
fun encryptFile( inputFile : File ) : File {

    val size = inputFile.length().toInt()
    val fileBytes = ByteArray(size)
    val aeS256JNCryptor = AES256JNCryptor()
    val file = File(Environment.getExternalStorageDirectory().toString() + "/Encrypted_" + inputFile.name)
    try {
        val buf = BufferedInputStream(FileInputStream(inputFile))
        buf.read(fileBytes, 0, fileBytes.size)

        val encryptedFileBytes = aeS256JNCryptor.encryptData(fileBytes, encryptKey("master_password", "alok"))

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
let decryptedData = try RNCryptor.decrypt(data: encryptedData!, withPassword: hashKey.description)
let selected_image = UIImage.sd_image(with: decryptedData)
encrypted - 3,1,71,68
decrypted Bytes - 3,1,-54,106
 * */
fun decryptFile( inputFile : File ) : File {

    val size = inputFile.length().toInt()
    val fileBytes = ByteArray(size)
    val aeS256JNCryptor = AES256JNCryptor()
    val file = File(Environment.getExternalStorageDirectory().toString() + "/Decrypted_" + inputFile.name)
    try {
        val buf = BufferedInputStream(FileInputStream(inputFile))
        buf.read(fileBytes, 0, fileBytes.size)

        val decryptedFileBytes = aeS256JNCryptor.decryptData(fileBytes, encryptKey("master_password", "alok"))

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



fun generateRandomOTP() : String {
    var otp = ""
    val random = Random()
    otp += random.nextInt(10)
    otp += random.nextInt(10)
    otp += random.nextInt(10)
    otp += random.nextInt(10)
    otp += random.nextInt(10)
    otp += random.nextInt(10)
    AppLogger.d("EmailOTP", otp)
    return otp
}


