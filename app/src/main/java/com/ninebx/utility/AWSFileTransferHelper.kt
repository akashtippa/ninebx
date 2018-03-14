package com.ninebx.utility

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Environment
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferType
import com.ninebx.NineBxApplication
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.utility.Util.fillMap
import com.ninebx.utility.Util.getTransferUtility
import java.io.File
import java.util.*
import com.amazonaws.services.s3.model.ObjectMetadata
import com.ninebx.utility.Util.getSecureTransferUtility


/**
 * Created by Alok on 19/01/18.
 */
class AWSFileTransferHelper( private val context : Context? ) {

    private val TAG = AWSFileTransferHelper::class.java.simpleName

    /**
     * This map is used to provide data to the SimpleAdapter above. See the
     * fillMap() function for how it relates observers to rows in the displayed
     * activity.
     */
    private var transferRecordMaps: ArrayList<HashMap<String, Any>> = ArrayList()
    private var transferDownloadRecordMaps: ArrayList<HashMap<String, Any>> = ArrayList()

    private var transferSecureRecordMaps: ArrayList<HashMap<String, Any>> = ArrayList()
    private var transferSecureDownloadRecordMaps: ArrayList<HashMap<String, Any>> = ArrayList()

    private val transferUtility = getTransferUtility(context!!)
    private val transferSecureUtility = getSecureTransferUtility(context!!)

    // A List of all transfers
    private var observers: MutableList<TransferObserver>? = null
    private var downloadObservers: MutableList<TransferObserver>? = null

    private var secureObservers: MutableList<TransferObserver>? = null
    private var secureDownloadObservers: MutableList<TransferObserver>? = null

    // Which row in the UI is currently checked (if any)
    private var checkedIndex: Int = Constants.INDEX_NOT_CHECKED
    private var downloadCheckedIndex: Int = Constants.INDEX_NOT_CHECKED

    private var secureCheckedIndex: Int = Constants.INDEX_NOT_CHECKED
    private var secureDownloadCheckedIndex: Int = Constants.INDEX_NOT_CHECKED

    private var privateKey: CharArray = NineBxApplication.getPreferences().privateKey!!.toCharArray()
    private var privateKeyArrayBase64 : String = encryptAESKey(NineBxApplication.getPreferences().privateKey!!, NineBxApplication.getPreferences().privateKey!!)
    private var fileOperationsCompletionListener: FileOperationsCompletionListener? = null

    init {
        initUploadData()
        initSecureUploadData()
        initDownloadData()
        initSecureDownloadData()
    }

    private fun initSecureDownloadData() {
        transferSecureDownloadRecordMaps.clear()
        // Uses TransferUtility to get all previous download records.
        secureDownloadObservers = transferUtility.getTransfersWithType(TransferType.DOWNLOAD)
        val listener = FileTransferListener("secureDownload")
        for (observer in secureDownloadObservers!!) {
            val map = HashMap<String, Any>()
            fillMap(map, observer, false)
            transferSecureDownloadRecordMaps.add(map)

            // Sets listeners to in progress transfers
            if (TransferState.WAITING == observer.state
                    || TransferState.WAITING_FOR_NETWORK == observer.state
                    || TransferState.IN_PROGRESS == observer.state) {
                observer.setTransferListener(listener)
            }
        }
    }

    private fun initSecureUploadData() {
        transferSecureRecordMaps.clear()
        // Use TransferUtility to get all upload transfers.
        secureObservers = transferUtility.getTransfersWithType(TransferType.UPLOAD)
        val listener = FileTransferListener("secureUpload")
        for ( observer in secureObservers!! ) {

            // For each transfer we will will create an entry in
            // transferRecordMaps which will display
            // as a single row in the UI
            val map = HashMap<String, Any>()
            fillMap(map, observer, false)
            transferSecureRecordMaps.add(map)

            // Sets listeners to in progress transfers
            if (TransferState.WAITING == observer.state
                    || TransferState.WAITING_FOR_NETWORK == observer.state
                    || TransferState.IN_PROGRESS == observer.state) {
                observer.setTransferListener(listener)
            }
        }
    }

    fun setFileTransferListener(fileTransferListener: FileOperationsCompletionListener) {
        this.fileOperationsCompletionListener = fileTransferListener
    }

    private fun initDownloadData() {
        transferDownloadRecordMaps.clear()
        // Uses TransferUtility to get all previous download records.
        downloadObservers = transferUtility.getTransfersWithType(TransferType.DOWNLOAD)
        val listener = FileTransferListener("download")
        for (observer in downloadObservers!!) {
            val map = HashMap<String, Any>()
            fillMap(map, observer, false)
            transferDownloadRecordMaps.add(map)

            // Sets listeners to in progress transfers
            if (TransferState.WAITING == observer.state
                    || TransferState.WAITING_FOR_NETWORK == observer.state
                    || TransferState.IN_PROGRESS == observer.state) {
                observer.setTransferListener(listener)
            }
        }
    }

    private fun initUploadData() {

        transferRecordMaps.clear()
        // Use TransferUtility to get all upload transfers.
        observers = transferUtility.getTransfersWithType(TransferType.UPLOAD)
        val listener = FileTransferListener("upload")
        for ( observer in observers!! ) {

            // For each transfer we will will create an entry in
            // transferRecordMaps which will display
            // as a single row in the UI
            val map = HashMap<String, Any>()
            fillMap(map, observer, false)
            transferRecordMaps.add(map)

            // Sets listeners to in progress transfers
            if (TransferState.WAITING == observer.state
                    || TransferState.WAITING_FOR_NETWORK == observer.state
                    || TransferState.IN_PROGRESS == observer.state) {
                observer.setTransferListener(listener)
            }
        }
    }

    /*
     * Begins to upload the file specified by the file path.
     */
    fun beginUpload(filePath: String?) {

        if (filePath == null) {
            AppLogger.e( TAG, "Could not find the filepath of the selected file" )
            context?.showToast("Could not find the filepath of the selected file")
            return
        }

        FileOperationsTask( "Encryption", filePath, privateKey, object : FileOperationsCompletionListener {
            override fun onSuccess(outputFile: File?) {

                val observer = transferUtility.upload(Constants.BUCKET_NAME, outputFile!!.name,
                        outputFile)
                /*
                 * Note that usually we set the transfer listener after initializing the
                 * transfer. However it isn't required in this sample app. The flow is
                 * click upload button -> start an activity for image selection
                 * startActivityForResult -> onActivityResult -> beginUpload -> onResume
                 * -> set listeners to in progress transfers.
                 */
                observer.setTransferListener(FileTransferListener("upload"))
            }

        }).execute()




    }

    fun clearTransferListeners( ) {
        // Clear transfer listeners to prevent memory leak, or
        // else this activity won't be garbage collected.
        if (observers != null && !observers!!.isEmpty()) {
            for (observer in observers!!) {
                observer.cleanTransferListener()
            }
        }

        if (downloadObservers != null && !downloadObservers!!.isEmpty()) {
            for (observer in downloadObservers!!) {
                observer.cleanTransferListener()
            }
        }
        if (secureObservers != null && !secureObservers!!.isEmpty()) {
            for (observer in secureObservers!!) {
                observer.cleanTransferListener()
            }
        }

        if (secureDownloadObservers != null && !secureDownloadObservers!!.isEmpty()) {
            for (observer in secureDownloadObservers!!) {
                observer.cleanTransferListener()
            }
        }
    }

    /*
    * Begins to download the file specified by the key in the bucket.
    */
    fun beginSecureDownload(key: String) {
        // Location to download files from S3 to. You can choose any accessible
        // file.
        val file = File(Environment.getExternalStorageDirectory().toString() + "/" + key)

        // Initiate the download
        val observer = transferSecureUtility.download(Constants.BUCKET_NAME, key, file)
        /*
         * Note that usually we set the transfer listener after initializing the
         * transfer. However it isn't required in this sample app. The flow is
         * click upload button -> start an activity for image selection
         * startActivityForResult -> onActivityResult -> beginUpload -> onResume
         * -> set listeners to in progress transfers.
         */
        observer.setTransferListener( FileTransferListener( "secureDownload"))



    }

    /*
     * Begins to download the file specified by the key in the bucket.
     */
    fun beginDownload(key: String) {
        // Location to download files from S3 to. You can choose any accessible
        // file.
        val file = File(Environment.getExternalStorageDirectory().toString() + "/" + key)

        // Initiate the download
        val observer = transferUtility.download(Constants.BUCKET_NAME, key, file)
        /*
         * Note that usually we set the transfer listener after initializing the
         * transfer. However it isn't required in this sample app. The flow is
         * click upload button -> start an activity for image selection
         * startActivityForResult -> onActivityResult -> beginUpload -> onResume
         * -> set listeners to in progress transfers.
         */
         observer.setTransferListener( FileTransferListener( "download"))



    }

    fun beginEncryptedUpload( filePath: String? ) {
        val myObjectMetadata = ObjectMetadata()

        //create a map to store user metadata
        val userMetadata = HashMap<String, String>()
        userMetadata.put("sseCustomerKey", privateKeyArrayBase64)
        userMetadata.put("sseCustomerKeyMD5", MD5Helper.getMD5(privateKeyArrayBase64))
        userMetadata.put("sseCustomerAlgorithm", "AES256")

        //call setUserMetadata on our ObjectMetadata object, passing it our map
        myObjectMetadata.userMetadata = userMetadata

        if (filePath == null) {
            AppLogger.e( TAG, "Could not find the filepath of the selected file" )
            context?.showToast("Could not find the filepath of the selected file")
            return
        }

        FileOperationsTask( "Encryption", filePath, privateKey, object : FileOperationsCompletionListener {
            override fun onSuccess(outputFile: File?) {

                val observer = transferUtility.upload(Constants.BUCKET_NAME, outputFile!!.name,
                        outputFile, myObjectMetadata)
                /*
                 * Note that usually we set the transfer listener after initializing the
                 * transfer. However it isn't required in this sample app. The flow is
                 * click upload button -> start an activity for image selection
                 * startActivityForResult -> onActivityResult -> beginUpload -> onResume
                 * -> set listeners to in progress transfers.
                 */
                observer.setTransferListener(FileTransferListener("upload"))
            }

        }).execute()
    }

    /*
     * A TransferListener class that can listen to a upload task and be notified
     * when the status changes.
     */
    private inner class FileTransferListener( val type : String ) : TransferListener {

        // Simply updates the UI list when notified.
        override fun onError(id: Int, e: Exception) {
            AppLogger.e(TAG, type + " : Error during upload: " + id + " : Exception : " + e.message)
            updateList( type )
        }

        override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
            AppLogger.d(TAG, type + " : " + String.format("onProgressChanged: %d, total: %d, current: %d",
                    id, bytesTotal, bytesCurrent))
            updateList( type )
        }

        override fun onStateChanged(id: Int, newState: TransferState) {
            AppLogger.d(TAG, "$type : onStateChanged: $id, $newState")
            updateList( type )
        }
    }

    private fun updateList(type: String) {

        if( type == "upload" ) {

            var observer: TransferObserver?
            var map: HashMap<String, Any>?
            for (i in observers!!.indices) {
                observer = observers!!.get(i)
                map = transferRecordMaps[i]
                fillMap(map, observer, i == checkedIndex)
            }
        }
        else if( type == "download" ) {
            var downloadObserver: TransferObserver?
            var downloadMap: HashMap<String, Any>? = null
            for (i in downloadObservers!!.indices) {
                downloadObserver = downloadObservers!!.get(i)
                downloadMap = transferDownloadRecordMaps[i]
                fillMap(downloadMap, downloadObserver, i == downloadCheckedIndex)
            }
            decryptFiles( downloadMap )
        }
        else if ( type == "secureUpload" ) {
            var observer: TransferObserver?
            var map: HashMap<String, Any>?
            for (i in secureObservers!!.indices) {
                observer = secureObservers!!.get(i)
                map = transferSecureRecordMaps[i]
                fillMap(map, observer, i == secureCheckedIndex)
            }
        }
        else {
            var downloadObserver: TransferObserver?
            var downloadMap: HashMap<String, Any>? = null
            for (i in secureDownloadObservers!!.indices) {
                downloadObserver = secureDownloadObservers!!.get(i)
                downloadMap = transferSecureDownloadRecordMaps[i]
                fillMap(downloadMap, downloadObserver, i == secureDownloadCheckedIndex)
            }
            decryptFiles( downloadMap )
        }

    }

    private fun decryptFiles(downloadMap: HashMap<String, Any>?) {
        if( downloadMap != null ) {
            for( key in downloadMap.keys ) {
                if( key == "fileName" ) {
                    FileOperationsTask("Decryption", downloadMap.get("fileName").toString(), privateKey, object : FileOperationsCompletionListener {
                        override fun onSuccess(outputFile: File?) {
                            if( fileOperationsCompletionListener != null ) {
                                fileOperationsCompletionListener!!.onSuccess(outputFile)
                            }
                        }

                    })
                }

            }
        }
    }

    interface FileOperationsCompletionListener {
        fun onSuccess( outputFile : File? )
    }

    @SuppressLint("StaticFieldLeak")
    inner class FileOperationsTask( private val operationType : String,
                                    private val filePath : String,
                                    private val privateKey : CharArray,
                                    private val fileOperationsCompletionListener: FileOperationsCompletionListener) : AsyncTask<Void, Void, File>() {


        override fun onPostExecute(result: File?) {
            super.onPostExecute(result)
            fileOperationsCompletionListener.onSuccess( result )
        }

        override fun onPreExecute() {
            super.onPreExecute()
            context?.showToast(operationType + " in progress" )
        }

        override fun doInBackground(vararg aVoid: Void?): File {
            AppLogger.d("FileOperations", operationType + " : " + filePath )
            if( operationType == "Encryption" )
                return encryptFileIOS( File( filePath ), privateKey )
            else
                return decryptFileIOS( File( filePath ), privateKey )
        }

    }

    //Testing code
    fun performOperation(filePath: String, fileOperationsCompletionListener: FileOperationsCompletionListener, privateKey: CharArray ) {
        FileOperationsTask( "Encryption", filePath, privateKey, object : FileOperationsCompletionListener {
            override fun onSuccess(outputFile: File?) {

                if( outputFile != null ) {
                    context?.showToast("Encryption Success" )
                    //performDecryption( outputFile, privateKey, fileOperationsCompletionListener )
                }

            }

        }).execute()
    }

    fun performDecryption(outputFile: File, privateKey: CharArray, fileOperationsCompletionListener: FileOperationsCompletionListener) {
        FileOperationsTask("Decryption", outputFile.absolutePath, this.privateKey, object : FileOperationsCompletionListener {
            override fun onSuccess(outputFile: File?) {

                if( outputFile != null ) {
                    context?.showToast("Decryption Success" )
                    fileOperationsCompletionListener.onSuccess(outputFile)
                }

            }

        }).execute()
    }

    fun decryptEncryptedFile(outputFile: File, privateKey: CharArray ) {
        FileOperationsTask("Decryption", outputFile.absolutePath, privateKey, object : FileOperationsCompletionListener {
            override fun onSuccess(outputFile: File?) {

                if( outputFile != null ) {
                    context?.showToast("Decryption Success" )
                    //fileOperationsCompletionListener.onSuccess(outputFile)
                }



            }

        }).execute()
    }


}