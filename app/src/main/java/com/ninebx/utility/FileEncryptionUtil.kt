package com.ninebx.utility

import android.os.Environment
import com.ninebx.ui.auth.passwordHash.CustomKeyParameter
import com.ninebx.ui.auth.passwordHash.CustomPBEParametersGenerator
import com.ninebx.ui.auth.passwordHash.CustomPKCS5S2ParametersGenerator
import org.cryptonode.jncryptor.AES256JNCryptor
import org.spongycastle.crypto.digests.SHA256Digest
import java.io.*
import java.util.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap



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

        val encryptedFileBytes = aeS256JNCryptor.encryptData(fileBytes, generateKey("master_password", "alok"))

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

        val decryptedFileBytes = aeS256JNCryptor.decryptData(fileBytes, generateKey("master_password", "alok"))

        if( file.exists() ) file.delete()

        //val bufOut = BufferedOutputStream(file.outputStream())
        //bufOut.write(decryptedFileBytes)

        val fileOutputStream = FileOutputStream( file.absolutePath )
        //fileOutputStream.write(decryptedFileBytes)

        val bitmap = BitmapFactory.decodeByteArray(decryptedFileBytes, 0, decryptedFileBytes.size)
        if( bitmap != null )
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)


        buf.close()
        fileOutputStream.close()
        //bufOut.close()

    } catch (e: FileNotFoundException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    } catch (e: IOException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }

    return file
}

fun generateKey(password: String, username : String ) : CharArray {
    val generator = CustomPKCS5S2ParametersGenerator(SHA256Digest())
    generator.init(CustomPBEParametersGenerator.PKCS5PasswordToUTF8Bytes(password.toCharArray()), username.toByteArray(Charsets.UTF_8), 20000)
    val key = generator.generateDerivedMacParameters(256) as CustomKeyParameter
    return Arrays.toString(key.key.toTypedArray()).toCharArray()
}