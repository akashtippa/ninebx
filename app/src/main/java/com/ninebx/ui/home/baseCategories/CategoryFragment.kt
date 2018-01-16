package com.ninebx.ui.home.baseCategories

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Created by Alok on 12/01/18.
 */
class CategoryFragment : FragmentBackHelper(), CategoryView {

    override fun showProgress(message: Int) {
        layoutProgress.show()
        tvProgress.text = getString(message)
    }

    override fun hideProgress() {
        layoutProgress.hide()
    }

    override fun onError(error: Int) {
        hideProgress()
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(categories: ArrayList<Category>) {
        hideProgress()
        inflateLayout(categories)
    }

    private fun inflateLayout(categories: ArrayList<Category>) {

        val inflater = LayoutInflater.from(context)

        for (category in categories) {
            val categoryView = inflater.inflate(R.layout.layout_category_view, null) as LinearLayout
            val tvCategory = categoryView.findViewById<TextView>(R.id.tvCategory)
            val tvCount = categoryView.findViewById<TextView>(R.id.tvCount)
            val rvSubCategory = categoryView.findViewById<RecyclerView>(R.id.rvSubCategory)
            val id = context!!.resources.getIdentifier(category.drawableString, "drawable", context!!.packageName)
            tvCategory.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context!!, id), null, null, null)
            tvCategory.compoundDrawablePadding = context!!.resources.getDimensionPixelOffset(R.dimen.default_mini_padding)
            tvCategory.text = category.title
            tvCount.text = category.formsCount.toString()
            rvSubCategory.layoutManager = LinearLayoutManager(context)
            val subCategoryAdapter = SubCategoryAdapter(category.subCategories, object : CategoryItemClickListener {
                override fun onItemClick(subCategory: SubCategory, action: String) {
                    //DO something with this
                }
            })
            rvSubCategory.adapter = subCategoryAdapter
            layoutCategory.addView(categoryView)
        }
    }

    val titleText = "<font color=#263238>nine</font><font color=#FF00B0FF>bx</font>"
    private lateinit var mCategoryPresenter: CategoryPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCategoryPresenter = CategoryPresenter(arguments!!.getInt("category"), this)
    }

    override fun onBackPressed(): Boolean {
        NineBxApplication.instance.activityInstance!!.changeToolbarTitle(titleText)
        NineBxApplication.instance.activityInstance!!.hideHomeNShowQuickAdd()
        return super.onBackPressed()
    }
}