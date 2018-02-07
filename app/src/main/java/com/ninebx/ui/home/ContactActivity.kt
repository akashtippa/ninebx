package com.ninebx.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.ninebx.R
import com.wafflecopter.multicontactpicker.MultiContactPicker


class ContactActivity : AppCompatActivity() {
    private val CONTACT_PICKER_REQUEST = 991
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        MultiContactPicker.Builder(this@ContactActivity) //Activity/fragment context
                .theme(R.style.MyCustomPickerTheme) //Optional - default: MultiContactPicker.Azure
                .hideScrollbar(false) //Optional - default: false
                .showTrack(true) //Optional - default: true
                .searchIconColor(Color.WHITE) //Option - default: White
                .handleColor(ContextCompat.getColor(this@ContactActivity, R.color.colorPrimary)) //Optional - default: Azure Blue
                .bubbleColor(ContextCompat.getColor(this@ContactActivity, R.color.colorPrimary)) //Optional - default: Azure Blue
                .bubbleTextColor(Color.WHITE) //Optional - default: White
                .showPickerForResult(CONTACT_PICKER_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CONTACT_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val results = MultiContactPicker.obtainResult(data)
            } else if (resultCode == Activity.RESULT_CANCELED) {
                println("User closed the picker without selecting items.")
            }
        }
    }
}
