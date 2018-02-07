package com.ninebx.ui.home.calendar.events

import android.annotation.SuppressLint
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ninebx.R
import com.ninebx.ui.base.ActionClickListener
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show

/**
 * Created by Alok on 18/01/18.
 */
class AttachmentRecyclerViewAdapter( val mImagesList : ArrayList<Uri>, val actionClickListener: ActionClickListener, val orientation : Int )  : RecyclerView.Adapter<AttachmentRecyclerViewAdapter.ViewHolder>()  {

    private val requestOptions = RequestOptions()

    override fun getItemCount(): Int {
        if( orientation == LinearLayoutManager.VERTICAL ) {
            return mImagesList.size
        }
        else return mImagesList.size + 1
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        if( orientation == LinearLayoutManager.HORIZONTAL ) {

            if( position == mImagesList.size ) {
                holder!!.layoutAddImage!!.show()
                holder.layoutImage.hide()
            }

            else {
                Glide.with( holder!!.itemView.context )
                        .asBitmap()
                        .load( mImagesList[position] )
                        .apply(requestOptions)
                        .into(holder.ivAttachment)
                holder.layoutImage.show()
                holder.layoutAddImage!!.hide()
            }

        }
        else {

            Glide.with( holder!!.itemView.context )
                    .asBitmap()
                    .load( mImagesList[position] )
                    .apply(requestOptions)
                    .into(holder.ivAttachment)

        }

    }

    @SuppressLint("CheckResult")
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        requestOptions.centerCrop()
        requestOptions.placeholder(R.mipmap.ic_launcher)
        if( orientation == LinearLayoutManager.VERTICAL )
            return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_attachment, parent, false))
        else
            return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_horizontal_attachment, parent, false))
    }

    inner class ViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(view: View?) {

            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                when( view!!.id ) {
                    R.id.ivDelete ->{
                       actionClickListener.onItemClick( position, "delete" )
                    }
                    R.id.ivAttachment -> {
                        actionClickListener.onItemClick( position, "view" )
                    }
                    R.id.layoutAddImage -> {
                        actionClickListener.onItemClick( position, "add" )
                    }
                }
            }

        }

        val ivAttachment = itemView!!.findViewById<ImageView>(R.id.ivAttachment)
        val etAttachment = itemView!!.findViewById<EditText>(R.id.etAttachment)
        val layoutImage = itemView!!.findViewById<FrameLayout>(R.id.layoutImage)
        val ivDelete = itemView!!.findViewById<ImageView>(R.id.ivDelete)
        var layoutAddImage : FrameLayout ?= null

        init {
            if( orientation == LinearLayoutManager.HORIZONTAL ) {
                layoutAddImage = itemView!!.findViewById(R.id.layoutAddImage)
                layoutAddImage!!.setOnClickListener( this )
            }
            ivAttachment.setOnClickListener( this )
            ivDelete.setOnClickListener( this )
        }
    }
}