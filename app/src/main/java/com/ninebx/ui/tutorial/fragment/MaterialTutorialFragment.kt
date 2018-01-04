package com.ninebx.ui.tutorial.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R
import com.ninebx.ui.tutorial.WalkThroughItem
import com.ninebx.utility.NineBxPreferences
import kotlinx.android.synthetic.main.fragment_help_tutorial.*


/***
 * Created by TechnoBlogger on 02/01/18.
 */

class MaterialTutorialFragment : Fragment() {

    private lateinit var tutorialItem: WalkThroughItem
    internal var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = arguments
        if (b != null) {
            tutorialItem = b.getParcelable(ARG_TUTORIAL_ITEM)
        }
        if (b != null) {
            page = b.getInt(ARG_PAGE)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(R.layout.fragment_help_tutorial, container, false)
        v.tag = page
        return v
    }


    companion object {
        private val ARG_TUTORIAL_ITEM = "arg_tut_item"
        private val TAG = "MaterialTutFragment"
        private val ARG_PAGE = "arg_page"

        fun newInstance(tutorialItem: WalkThroughItem, page: Int): MaterialTutorialFragment {
            val helpTutorialImageFragment = MaterialTutorialFragment()
            val args = Bundle()
            args.putParcelable(ARG_TUTORIAL_ITEM, tutorialItem)
            args.putInt(ARG_PAGE, page)
            helpTutorialImageFragment.arguments = args
            return helpTutorialImageFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = NineBxPreferences()
//        preferences.firstRun = true

        if (!TextUtils.isEmpty(tutorialItem.titleText)) {
            titleHelpText.text = tutorialItem.titleText
        } else if (tutorialItem.titleTextRes != -1) {
            titleHelpText.setText(tutorialItem.titleTextRes)
        }
        if (!TextUtils.isEmpty(tutorialItem.subTitleText)) {
            titleHelpTextExtended.text = tutorialItem.subTitleText
        } else if (tutorialItem.subTitleTextRes != -1) {
            titleHelpTextExtended.setText(tutorialItem.subTitleTextRes)
        }
       /* if (!TextUtils.isEmpty(tutorialItem.textDescription)) {
            textDescription.text = tutorialItem.textDescription
        } else if (tutorialItem.txtDescription != -1) {
            textDescription.setText(tutorialItem.txtDescription)
        }*/

        imgDescription.setImageDrawable(activity?.let { ContextCompat.getDrawable(it, tutorialItem.foregroundImageRes) })
    }


}

