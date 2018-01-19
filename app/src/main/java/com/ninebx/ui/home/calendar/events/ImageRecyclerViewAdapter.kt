package com.ninebx.ui.home.calendar.events

import android.annotation.SuppressLint
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ninebx.R
import com.ninebx.ui.base.ActionClickListener

/**
 * Created by Alok on 18/01/18.
 */
class ImageRecyclerViewAdapter (val mImagesList : ArrayList<Uri>, val actionClickListener: ActionClickListener)  : RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder>()  {

    private val requestOptions = RequestOptions()

    override fun getItemCount(): Int {
        return mImagesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        Glide.with( holder!!.itemView.context )
                .asBitmap()
                .load( mImagesList[position] )
                .apply(requestOptions)
                .into(holder.ivAttachment)

    }

    @SuppressLint("CheckResult")
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        requestOptions.placeholder(R.mipmap.ic_launcher)
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_image_view, parent, false))
    }

    inner class ViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(view: View?) {

            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {

            }

        }

        val ivAttachment = itemView!!.findViewById<ImageView>(R.id.ivAttachment)

        init {
            ivAttachment.setOnClickListener( this )
        }
    }
}