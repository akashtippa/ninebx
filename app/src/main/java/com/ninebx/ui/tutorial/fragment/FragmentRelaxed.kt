package com.ninebx.ui.tutorial.fragment

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.ImageView
import com.ninebx.R
import kotlinx.android.synthetic.main.fragment_relaxed.*
import kotlinx.android.synthetic.main.dialog_security_overview.*


/***
 * Created by TechnoBlogger on 03/01/18.
 */

class FragmentRelaxed : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_relaxed, container, false)
    }

    companion object {
        // newInstance constructor for creating fragment with arguments
        fun newInstance(): FragmentRelaxed {
            val fragmentFirst = FragmentRelaxed()
            val args = Bundle()
            fragmentFirst.arguments = args
            return fragmentFirst
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textDescription.setOnClickListener {
           openSecurityDialog()
        }
    }

    private fun openSecurityDialog() {
        val dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_security_overview)
        val window = dialog.window
        val wlp = window.attributes

        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window.attributes = wlp
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.show()
    }
}