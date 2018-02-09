package com.ninebx.ui.home.customView

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.view.View
import com.ninebx.R
import kotlinx.android.synthetic.main.bottom_sheet_image_upload_option.view.*

/**
 * Created by Alok on 09/02/18.
 */
class CalendarBottomFragment : BottomSheetDialogFragment() {
    private var bottomSheetListener: CalendarBottomFragment.BottomSheetSelectedListener?= null

    private val mBottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }

    fun setBottomSheetSelectionListener( bottomSheetSelectedListener: BottomSheetSelectedListener ) {
        bottomSheetListener = bottomSheetSelectedListener
    }

    // make sure the Activity implemented it
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if( activity is BottomSheetSelectedListener ) {
            bottomSheetListener = activity
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if( context is BottomSheetSelectedListener ) {
            bottomSheetListener = context
        }
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        val contentView = View.inflate(context, R.layout.bottom_sheet_image_upload_option, null)
        dialog.setContentView(contentView)

        val layoutParams = (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior
        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
        }
        contentView.llCamera.setOnClickListener { bottomSheetListener!!.onOptionSelected(1) }
        contentView.llGallery.setOnClickListener { bottomSheetListener!!.onOptionSelected(2) }
    }

    interface BottomSheetSelectedListener {
        fun onOptionSelected(position: Int)
    }

}