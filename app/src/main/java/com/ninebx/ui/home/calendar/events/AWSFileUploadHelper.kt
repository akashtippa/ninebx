package com.ninebx.ui.home.calendar.events

import android.content.Context
import android.util.Log
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferType
import com.ninebx.ui.base.kotlin.showToast
import com.ninebx.utility.Constants
import com.ninebx.utility.Util.fillMap
import com.ninebx.utility.Util.getTransferUtility
import java.io.File
import java.util.ArrayList
import java.util.HashMap

/**
 * Created by Alok on 19/01/18.
 */
class AWSFileUploadHelper(private val context : Context) {

    private val TAG = AWSFileUploadHelper::class.java.simpleName
    /**
     * This map is used to provide data to the SimpleAdapter above. See the
     * fillMap() function for how it relates observers to rows in the displayed
     * activity.
     */
    private var transferRecordMaps: ArrayList<HashMap<String, Any>> = ArrayList()
    private val transferUtility = getTransferUtility(context)

    // A List of all transfers
    private var observers: MutableList<TransferObserver>? = null
    // Which row in the UI is currently checked (if any)
    private var checkedIndex: Int = Constants.INDEX_NOT_CHECKED

    init {
        initData()
    }

    private fun initData() {

        transferRecordMaps.clear()
        // Use TransferUtility to get all upload transfers.
        observers = transferUtility.getTransfersWithType(TransferType.UPLOAD)
        val listener = UploadListener()
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
            context.showToast("Could not find the filepath of the selected file")
            return
        }
        val file = File(filePath)
        val observer = transferUtility.upload(Constants.BUCKET_NAME, file.name,
                file)
        /*
         * Note that usually we set the transfer listener after initializing the
         * transfer. However it isn't required in this sample app. The flow is
         * click upload button -> start an activity for image selection
         * startActivityForResult -> onActivityResult -> beginUpload -> onResume
         * -> set listeners to in progress transfers.
         */
        // observer.setTransferListener(new UploadListener());
    }

    fun clearTransferListeners( ) {
        // Clear transfer listeners to prevent memory leak, or
        // else this activity won't be garbage collected.
        if (observers != null && !observers!!.isEmpty()) {
            for (observer in observers!!) {
                observer.cleanTransferListener()
            }
        }
    }

    /*
     * A TransferListener class that can listen to a upload task and be notified
     * when the status changes.
     */
    private inner class UploadListener : TransferListener {

        // Simply updates the UI list when notified.
        override fun onError(id: Int, e: Exception) {
            Log.e(TAG, "Error during upload: " + id, e)
            updateList()
        }

        override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
            Log.d(TAG, String.format("onProgressChanged: %d, total: %d, current: %d",
                    id, bytesTotal, bytesCurrent))
            updateList()
        }

        override fun onStateChanged(id: Int, newState: TransferState) {
            Log.d(TAG, "onStateChanged: $id, $newState")
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
    }


}