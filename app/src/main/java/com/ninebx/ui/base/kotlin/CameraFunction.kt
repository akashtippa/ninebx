package com.ninebx.ui.base.kotlin

import android.annotation.SuppressLint
import android.content.ContentUris
import android.database.Cursor
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import com.ninebx.NineBxApplication
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URISyntaxException
import java.util.*

/**
 * Created by Binay on 04/08/17.
 */
/*
* Function to save image locally before accessing imageUri in Signup and Editprofile class
* */
private val IMAGE_DIRECTORY: String = "/NineBx_Images"
fun saveImage(bitmap: Bitmap): String {
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
    val atomFileDirectory = File(Environment.getExternalStorageDirectory().toString() + IMAGE_DIRECTORY)
    // have the object build the directory structure, if needed.
    if (!atomFileDirectory.exists()) {
        atomFileDirectory.mkdirs();
    }

    try {
        val f = File(atomFileDirectory, Calendar.getInstance().timeInMillis.toString() + ".jpg")
        f.createNewFile()
        val fo = FileOutputStream(f)
        fo.write(bytes.toByteArray())
        val  context = NineBxApplication.instance
        MediaScannerConnection.scanFile(context, arrayOf(f.path), arrayOf("image/jpeg"), null);
        fo.close()
        return f.absolutePath
    } catch (e1: IOException) {
        e1.printStackTrace()
    }
    return "";
}

@SuppressLint("NewApi")
@Throws(URISyntaxException::class)
fun getPath(uri: Uri): String? {
    var documentUri = uri
    val needToCheckUri = Build.VERSION.SDK_INT >= 19
    var selection: String? = null
    var selectionArgs: Array<String>? = null
    // Uri is different in versions after KITKAT (Android 4.4), we need to
    // deal with different Uris.
    if (needToCheckUri && DocumentsContract.isDocumentUri(NineBxApplication.instance, documentUri)) {
        if (isExternalStorageDocument(documentUri)) {
            val docId = DocumentsContract.getDocumentId(documentUri)
            val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
        } else if (isDownloadsDocument(documentUri)) {
            val id = DocumentsContract.getDocumentId(documentUri)
            documentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)!!)
        } else if (isMediaDocument(documentUri)) {
            val docId = DocumentsContract.getDocumentId(documentUri)
            val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val type = split[0]
            if ("image" == type) {
                documentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            } else if ("video" == type) {
                documentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            } else if ("audio" == type) {
                documentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }
            selection = "_id=?"
            selectionArgs = arrayOf(split[1])
        }
    }
    if ("content".equals(documentUri.scheme, ignoreCase = true)) {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        var cursor: Cursor? = null
        try {
            cursor = NineBxApplication.instance.getContentResolver()
                    .query(documentUri, projection, selection, selectionArgs, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            if (cursor.moveToFirst()) {
                return cursor.getString(column_index)
            }
        } catch (e: Exception) {
        }

    } else if ("file".equals(documentUri.scheme, ignoreCase = true)) {
        return documentUri.path
    }
    return null
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is ExternalStorageProvider.
 */
fun isExternalStorageDocument(uri: Uri): Boolean {
    return "com.android.externalstorage.documents" == uri.authority
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is DownloadsProvider.
 */
fun isDownloadsDocument(uri: Uri): Boolean {
    return "com.android.providers.downloads.documents" == uri.authority
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is MediaProvider.
 */
fun isMediaDocument(uri: Uri): Boolean {
    return "com.android.providers.media.documents" == uri.authority
}