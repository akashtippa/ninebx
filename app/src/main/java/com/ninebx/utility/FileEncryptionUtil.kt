package com.ninebx.utility

import org.cryptonode.jncryptor.AES256JNCryptor
import java.io.*

/**
 * Created by Alok on 22/01/18.
 */
//Encrypt file
fun encryptFile( inputFile : File ) : File {

    val size = inputFile.length().toInt()
    val fileBytes = ByteArray(size)
    val aeS256JNCryptor = AES256JNCryptor()

    try {
        val buf = BufferedInputStream(FileInputStream(inputFile))
        buf.read(fileBytes, 0, fileBytes.size)

        val encryptedFileBytes = aeS256JNCryptor.encryptData(fileBytes, "master_password".toCharArray())

        val bufOut = BufferedOutputStream(inputFile.outputStream(), encryptedFileBytes.size)
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

    return inputFile

}

//Decrypt file
fun decryptFile( inputFile : File ) : File {

    val size = inputFile.length().toInt()
    val fileBytes = ByteArray(size)
    val aeS256JNCryptor = AES256JNCryptor()

    try {
        val buf = BufferedInputStream(FileInputStream(inputFile))
        buf.read(fileBytes, 0, fileBytes.size)

        val decryptedFileBytes = aeS256JNCryptor.decryptData(fileBytes, "master_password".toCharArray())

        val bufOut = BufferedOutputStream(inputFile.outputStream(), decryptedFileBytes.size)
        bufOut.write(decryptedFileBytes)

        buf.close()
        bufOut.close()

    } catch (e: FileNotFoundException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    } catch (e: IOException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }

    return inputFile
}