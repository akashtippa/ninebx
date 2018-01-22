package com.ninebx.ui.home.calendar.events

import android.content.Context
import android.os.Environment
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferType
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.utility.AppLogger
import com.ninebx.utility.Constants
import com.ninebx.utility.Util.fillMap
import com.ninebx.utility.Util.getTransferUtility
import com.ninebx.utility.decryptFile
import com.ninebx.utility.encryptFile
import java.io.File
import java.util.ArrayList
import java.util.HashMap

/**
 * Created by Alok on 19/01/18.
 */
class AWSFileTransferHelper(private val context : Context) {

    private val TAG = AWSFileTransferHelper::class.java.simpleName

    /**
     * This map is used to provide data to the SimpleAdapter above. See the
     * fillMap() function for how it relates observers to rows in the displayed
     * activity.
     */
    private var transferRecordMaps: ArrayList<HashMap<String, Any>> = ArrayList()
    private var transferDownloadRecordMaps: ArrayList<HashMap<String, Any>> = ArrayList()
    private val transferUtility = getTransferUtility(context)

    // A List of all transfers
    private var observers: MutableList<TransferObserver>? = null
    private var downloadObservers: MutableList<TransferObserver>? = null

    // Which row in the UI is currently checked (if any)
    private var checkedIndex: Int = Constants.INDEX_NOT_CHECKED
    private var downloadCheckedIndex: Int = Constants.INDEX_NOT_CHECKED

    init {
        initUploadData()
        initDownloadData()
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
            if (TransferState.WAITING == observer.getState()
                    || TransferState.WAITING_FOR_NETWORK == observer.getState()
                    || TransferState.IN_PROGRESS == observer.getState()) {
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
            if (TransferState.WAITING == observer.getState()
                    || TransferState.WAITING_FOR_NETWORK == observer.getState()
                    || TransferState.IN_PROGRESS == observer.getState()) {
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
            context.showToast("Could not find the filepath of the selected file")
            return
        }

        val file = encryptFile( File(filePath) )

        val observer = transferUtility.upload(Constants.BUCKET_NAME, file.name,
                file)
        /*
         * Note that usually we set the transfer listener after initializing the
         * transfer. However it isn't required in this sample app. The flow is
         * click upload button -> start an activity for image selection
         * startActivityForResult -> onActivityResult -> beginUpload -> onResume
         * -> set listeners to in progress transfers.
         */
         observer.setTransferListener(FileTransferListener("upload"))
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

    /*
     * A TransferListener class that can listen to a upload task and be notified
     * when the status changes.
     */
    private inner class FileTransferListener( val type : String ) : TransferListener {

        // Simply updates the UI list when notified.
        override fun onError(id: Int, e: Exception) {
            AppLogger.e(TAG, type + " : Error during upload: " + id + " : Exception : " + e.message)
            updateList()
        }

        override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
            AppLogger.d(TAG, type + " : " + String.format("onProgressChanged: %d, total: %d, current: %d",
                    id, bytesTotal, bytesCurrent))
            updateList()
        }

        override fun onStateChanged(id: Int, newState: TransferState) {
            AppLogger.d(TAG, "$type : onStateChanged: $id, $newState")

            updateList()
        }
    }

    private fun updateList() {
        var observer: TransferObserver? = null
        var map: HashMap<String, Any>? = null
        for (i in observers!!.indices) {
            observer = observers!!.get(i)
            map = transferRecordMaps[i]
            fillMap(map, observer, i == checkedIndex)
        }

        var downloadObserver: TransferObserver? = null
        var downloadMap: HashMap<String, Any>? = null
        for (i in downloadObservers!!.indices) {
            downloadObserver = downloadObservers!!.get(i)
            downloadMap = transferDownloadRecordMaps[i]
            fillMap(downloadMap, downloadObserver, i == downloadCheckedIndex)
        }
        /*if( downloadMap != null ) {
            for( key in downloadMap.keys ) {
                if( key == "fileName" ) {
                    val decryptFile = decryptFile(File(downloadMap[key].toString()))
                }

            }
        }*/
    }


}