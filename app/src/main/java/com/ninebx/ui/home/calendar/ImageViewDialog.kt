package com.ninebx.ui.home.calendar


import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.ActionClickListener
import com.ninebx.utility.GravitySnapHelper

/**
 * Created by Alok on 18/01/18.
 */
class ImageViewDialog( private val context: Context, private val imagesList : ArrayList<Uri>, private val title : String ) {


    init {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.setContentView(com.ninebx.R.layout.dialog_image_view)
        val tvTitle = dialog.findViewById<TextView>(R.id.tvTitle)
        tvTitle.text = title

        val rvImages = dialog.findViewById<RecyclerView>(R.id.rvImages)
        rvImages.layoutManager = LinearLayoutManager( context, LinearLayout.HORIZONTAL, false )
        GravitySnapHelper( Gravity.START ).attachToRecyclerView(rvImages)
        rvImages.adapter = ImageRecyclerViewAdapter( imagesList, object : ActionClickListener {
            override fun onItemClick(position: Int, action: String) {

            }

        })

        val window = dialog.window
        val wlp = window.attributes


        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window.attributes = wlp
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.show()

        val imgBack = dialog.findViewById<View>(R.id.imgBack) as ImageView
        imgBack.setOnClickListener {
            dialog.cancel()
        }

    }


}