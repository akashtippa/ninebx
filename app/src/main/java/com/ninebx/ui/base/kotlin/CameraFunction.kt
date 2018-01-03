package com.ninebx.ui.base.kotlin

import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import com.ninebx.NineBxApplication
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

/**
 * Created by Binay on 04/08/17.
 */
/*
* Function to save image locally before accessing imageUri in Signup and Editprofile class
* */
private val IMAGE_DIRECTORY: String = "/FITLINKS"
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