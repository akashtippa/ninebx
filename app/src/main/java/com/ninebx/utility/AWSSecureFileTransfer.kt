package com.ninebx.utility

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Environment
import android.util.Base64
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.*
import com.ninebx.NineBxApplication
import com.ninebx.ui.base.kotlin.showToast
import java.io.*
import java.security.Security
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


/**
 * Created by Alok on 21/02/18.
 */

class AWSSecureFileTransfer( val context: Context ) {

    private var s3client: AmazonS3? = null

    private var privateKey: CharArray = NineBxApplication.getPreferences().privateKey!!.toCharArray()
    private var privateKeyArrayBase64 : String

    private var fileOperationsListener : AWSFileTransferHelper.FileOperationsCompletionListener ?= null
    private val TAG = "SecureTransfer"
    init {
        s3client = AmazonS3Client(Util.getCredProvider(context))

        AppLogger.d(TAG, "Private KEy : " + NineBxApplication.getPreferences().privateKey)
        val privateKeyInt8 = convertToUInt8(NineBxApplication.getPreferences().privateKey!!.toByteArray())
        AppLogger.d(TAG, "Private KEy UINT8: " + privateKeyInt8)
        privateKeyArrayBase64 = encryptAESKey(NineBxApplication.getPreferences().privateKey!!, NineBxApplication.getPreferences().privateKey!!)

        AppLogger.d(TAG, "Private KEy Encrypted base 64 : " + privateKeyArrayBase64)
        AppLogger.d(TAG, "Private KEy Encrypted base 64 Arrays : " + Arrays.toString(privateKeyArrayBase64.toByteArray(Charsets.UTF_8)))
        val encryptedMd5 = encryptAESKeyMD5(NineBxApplication.getPreferences().privateKey!!, NineBxApplication.getPreferences().privateKey!!)
        AppLogger.d(TAG, "Private encrypted Md5 : " + encryptedMd5)
        AppLogger.d(TAG, "Private encrypted Md5 UINT8 : " + convertToUInt8(encryptedMd5.toByteArray(Charsets.UTF_8)))
        val md5Data = MD5Helper.md5(privateKeyArrayBase64)
        AppLogger.d(TAG, "Private KEy MD5 String : " + md5Data)
        val md5Base64 = String(Base64.encode(md5Data.toByteArray(Charsets.UTF_8), Base64.DEFAULT)).trim()
        AppLogger.d(TAG, "Private KEy MD5 Base64 String : " + md5Base64)
        AppLogger.d(TAG, "Private KEy MD5 convertToUInt8 : " + convertToUInt8(md5Data.toByteArray(Charsets.UTF_8)))
        val sampleMD5 = "[212, 82, 54, 202, 160, 144, 250, 242, 255, 168, 30, 5, 79, 86, 98, 66]"
        AppLogger.d(TAG, "MD5Base64 sample : " + String(Base64.encode(sampleMD5.toByteArray(Charsets.UTF_8), Base64.DEFAULT)).trim())



    }

    fun downloadSecureFile( filePath : String ) {
        // Get a range of bytes from an object.
        val getObjectRequest = GetObjectRequest(Constants.BUCKET_NAME, filePath)
        val sseCustomerKey = SSECustomerKey(privateKeyArrayBase64)
        sseCustomerKey.md5 = String(Base64.encode(MD5Helper.getMD5(privateKeyArrayBase64).toByteArray(), Base64.DEFAULT)).trim()
        sseCustomerKey.algorithm = SSEAlgorithm.AES256.algorithm
        executeDownload( getObjectRequest, sseCustomerKey, filePath )

    }

    @SuppressLint("StaticFieldLeak")
    private fun executeDownload(getObjectRequest: GetObjectRequest, sseCustomerKey: SSECustomerKey, filePath: String ) {

        object : AsyncTask<Void, Void, File?>() {
            override fun doInBackground(vararg p0: Void?): File? {
                getObjectRequest.sseCustomerKey = sseCustomerKey
                val s3Object = s3client!!.getObject(getObjectRequest)
                val objectContent = s3Object.objectContent
                val size = objectContent!!.available()
                val fileBytes = ByteArray(size)
                val file = File(Environment.getExternalStorageDirectory().toString() +"/"+  filePath)
                try {
                    val buf = BufferedInputStream(objectContent)
                    buf.read(fileBytes, 0, fileBytes.size)

                    if( file.exists() ) file.delete()
                    if( !file.parentFile.exists() ) file.parentFile.mkdirs()
                    file.createNewFile()

                    val outStream = FileOutputStream(file)
                    outStream.write(fileBytes)
                    buf.close()

                    return file
                } catch (e: FileNotFoundException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    return null
                } catch (e: IOException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    return null
                }
            }

            override fun onPostExecute(result: File?) {
                super.onPostExecute(result)
                if( result != null )
                    decryptFileTask( result )
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, null)
    }

    fun setFileTransferListener( fileOperationsCompletionListener: AWSFileTransferHelper.FileOperationsCompletionListener ) {
        this.fileOperationsListener = fileOperationsCompletionListener
    }

    @SuppressLint("StaticFieldLeak")
    inner class FileOperationsTask(private val operationType : String,
                                   private val filePath : String,
                                   private val privateKey : CharArray,
                                   private val fileOperationsCompletionListener: AWSFileTransferHelper.FileOperationsCompletionListener) : AsyncTask<Void, Void, File>() {


        override fun onPostExecute(result: File?) {
            super.onPostExecute(result)
            fileOperationsCompletionListener.onSuccess( result )
        }

        override fun onPreExecute() {
            super.onPreExecute()
            context.showToast(operationType + " in progress" )
        }

        override fun doInBackground(vararg aVoid: Void?): File {
            AppLogger.d("FileOperations", operationType + " : " + filePath )
            if( operationType == "Encryption" )
                return encryptFileIOS( File( filePath ), privateKey )
            else
                return decryptFileIOS( File( filePath ), privateKey )
        }

    }

    private fun decryptFileTask(file: File) {
        FileOperationsTask("Decryption", file.absolutePath, privateKey, object : AWSFileTransferHelper.FileOperationsCompletionListener {
            override fun onSuccess(outputFile: File?) {
                if (fileOperationsListener != null) {
                    fileOperationsListener!!.onSuccess(outputFile)
                }
            }

        }).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, null)
    }


}
