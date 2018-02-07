package com.ninebx.ui.base.kotlin

/**
 * Created by Alok on 31/10/17.
 */
import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import java.io.ByteArrayOutputStream


@SuppressLint("InlinedApi")
fun Activity.setStatusBarColor(colorResId: Int) {
    supportsLollipop {
        with(window) {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            statusBarColor = ContextCompat.getColor(context, colorResId)
        }
    }

}

fun Activity.getDPFromPixel(pixels: Float): Float {
    val displayMetrics = DisplayMetrics()
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixels, displayMetrics)
}

fun Activity.getImageUri(bitmap: Bitmap): Uri {
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(this.contentResolver, bitmap, "Title", null)
    return Uri.parse(path);
}