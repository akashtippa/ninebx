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
import android.support.v7.widget.OrientationHelper
import android.content.Intent


/**
 * Created by Alok on 18/01/18.
 */
class ImageViewDialog( private val context: Context, private val imagesList : ArrayList<Uri>, private val title : String ) {


    private var mLinearLayoutManager : LinearLayoutManager
    private var mRvImages : RecyclerView

    init {

        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.setContentView(com.ninebx.R.layout.dialog_image_view)
        val tvTitle = dialog.findViewById<TextView>(R.id.tvTitle)
        tvTitle.text = title

        mRvImages = dialog.findViewById<RecyclerView>(R.id.rvImages)
        mLinearLayoutManager = LinearLayoutManager( context, LinearLayout.HORIZONTAL, false )
        mRvImages.layoutManager = mLinearLayoutManager

        val ivShare = dialog.findViewById<ImageView>(R.id.ivShare)
        ivShare.setOnClickListener {
            val position = findFirstVisibleItemPosition()
            if( position != RecyclerView.NO_POSITION ) {
                val uri = imagesList[position]
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "image/jpeg"
                intent.putExtra(Intent.EXTRA_STREAM, uri)
                context.startActivity(Intent.createChooser(intent, "Share Image"))
            }
        }

        GravitySnapHelper( Gravity.START ).attachToRecyclerView(mRvImages)

        mRvImages.adapter = ImageRecyclerViewAdapter( imagesList, object : ActionClickListener {
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

    /**
     * Returns the adapter position of the first visible view. This position does not include
     * adapter changes that were dispatched after the last layout pass.
     *
     * @return The adapter position of the first visible item or [RecyclerView.NO_POSITION] if
     * there aren't any visible items.
     */
    private fun findFirstVisibleItemPosition( ): Int {
        val child = findOneVisibleChild(0, mLinearLayoutManager.childCount, false, true)
        return if (child == null) RecyclerView.NO_POSITION else mRvImages.getChildAdapterPosition(child)
    }

    private fun findOneVisibleChild(fromIndex: Int, toIndex: Int, completelyVisible: Boolean,
                            acceptPartiallyVisible: Boolean): View? {
        val helper: OrientationHelper
        if (mLinearLayoutManager.canScrollVertically()) {
            helper = OrientationHelper.createVerticalHelper(mLinearLayoutManager)
        } else {
            helper = OrientationHelper.createHorizontalHelper(mLinearLayoutManager)
        }

        val start = helper.startAfterPadding
        val end = helper.endAfterPadding
        val next = if (toIndex > fromIndex) 1 else -1
        var partiallyVisible: View? = null
        var i = fromIndex
        while (i != toIndex) {
            val child = mLinearLayoutManager.getChildAt(i)
            val childStart = helper.getDecoratedStart(child)
            val childEnd = helper.getDecoratedEnd(child)
            if (childStart < end && childEnd > start) {
                if (completelyVisible) {
                    if (childStart >= start && childEnd <= end) {
                        return child
                    } else if (acceptPartiallyVisible && partiallyVisible == null) {
                        partiallyVisible = child
                    }
                } else {
                    return child
                }
            }
            i += next
        }
        return partiallyVisible
    }

    interface ImageActionListener {
        fun onImageAction( imagesList: ArrayList<Uri> )
    }


}