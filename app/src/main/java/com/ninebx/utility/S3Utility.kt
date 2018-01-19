package com.ninebx.utility

/**
 * Created by Alok on 19/01/18.
 */
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri

import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.services.s3.AmazonS3Client

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID

/*
 * Handles basic helper functions used throughout the app.
 */
object Util {

    // We only need one instance of the clients and credentials provider
    private var sS3Client:AmazonS3Client? = null
    private var sCredProvider:CognitoCachingCredentialsProvider? = null

    @SuppressLint("StaticFieldLeak")
    private var sTransferUtility:TransferUtility? = null

    /**
     * Gets an instance of CognitoCachingCredentialsProvider which is
     * constructed using the given Context.
     *
     * @param context An Context instance.
     * @return A default credential provider.
     */
    private fun getCredProvider(context:Context):CognitoCachingCredentialsProvider {
        if (sCredProvider == null)
        {
            sCredProvider = CognitoCachingCredentialsProvider(
                    context.applicationContext,
                    Constants.COGNITO_POOL_ID,
                    (Constants.COGNITO_POOL_REGION))
        }
        return sCredProvider!!
    }

    /**
     * Gets an instance of a S3 client which is constructed using the given
     * Context.
     *
     * @param context An Context instance.
     * @return A default S3 client.
     */
    fun getS3Client(context:Context):AmazonS3Client {
        if (sS3Client == null)
        {
            sS3Client = AmazonS3Client(getCredProvider(context.applicationContext))
            sS3Client!!.setRegion(Region.getRegion(Constants.COGNITO_POOL_REGION))
        }
        return sS3Client!!
    }

    /**
     * Gets an instance of the TransferUtility which is constructed using the
     * given Context
     *
     * @param context
     * @return a TransferUtility instance
     */
    fun getTransferUtility(context:Context):TransferUtility {
        if (sTransferUtility == null)
        {
            sTransferUtility = TransferUtility.builder()
                    .s3Client(getS3Client(context.applicationContext))
                    .context(context).build()

        }

        return sTransferUtility!!
    }

    /**
     * Converts number of bytes into proper scale.
     *
     * @param bytes number of bytes to be converted.
     * @return A string that represents the bytes in a proper scale.
     */
    fun getBytesString(bytes:Long):String {
        val quantifiers = arrayOf("KB", "MB", "GB", "TB")
        var speedNum = bytes.toDouble()
        var i = 0
        while (true)
        {
            if (i >= quantifiers.size)
            {
                return ""
            }
            speedNum /= 1024.0
            if (speedNum < 512)
            {
                return String.format("%.2f", speedNum) + " " + quantifiers[i]
            }
            i++
        }
    }

    /**
     * Copies the data from the passed in Uri, to a new file for use with the
     * Transfer Service
     *
     * @param context
     * @param uri
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    fun copyContentUriToFile(context:Context, uri:Uri):File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val copiedData = File(context.getDir("SampleImagesDir", Context.MODE_PRIVATE), UUID
                .randomUUID().toString())
        copiedData.createNewFile()

        val fos = FileOutputStream(copiedData)
        val buf = ByteArray(2046)
        var read = -1
        read = inputStream!!.read(buf)
        while ( read != -1)
        {
            fos.write(buf, 0, read)
            read = inputStream.read(buf)
        }

        fos.flush()
        fos.close()

        return copiedData
    }

    /*
        * Fills in the map with information in the observer so that it can be used
        * with a SimpleAdapter to populate the UI
        */
    fun fillMap(map:MutableMap<String, Any>, observer:TransferObserver, isChecked:Boolean) {
        val progress = (observer.bytesTransferred.toDouble() * 100 / observer
                .bytesTotal).toInt()
        map.put("id", observer.id)
        map.put("checked", isChecked)
        map.put("fileName", observer.absoluteFilePath)
        map.put("progress", progress)
        map.put("bytes",
                getBytesString(observer.bytesTransferred) + "/"
                        + getBytesString(observer.bytesTotal))
        map.put("state", observer.state)
        map.put("percentage", (progress).toString() + "%")
    }

}