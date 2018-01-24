package com.ninebx.ui.home.baseSubCategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_level2_category.*

/***
 * Created by TechnoBlogger on 23/01/18.
 */

class Level2CategoryFragment : FragmentBackHelper(), Level2CategoryView {

    private lateinit var mCategoryPresenter: Level2CategoryPresenter
    private val adapterExpandable: ExpandableListViewAdapter? = null

    override fun showProgress(message: Int) {

    }

    override fun hideProgress() {
        layoutProgress.hide()
    }

    override fun onError(error: Int) {
    }

    override fun onSuccess(categories: ArrayList<Level2Category>) {
        hideProgress()
        inflateLayout(categories)
    }

    private fun inflateLayout(categories: ArrayList<Level2Category>) {
        layExpandable.setAdapter(ExpandableListViewAdapter(context!!, categories))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_level2_category, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCategoryPresenter = Level2CategoryPresenter(arguments!!.getString("categoryName"), this)
        NineBxApplication.instance.activityInstance!!.hideBottomView()
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.showBottomView()
        return super.onBackPressed()


    }

}
