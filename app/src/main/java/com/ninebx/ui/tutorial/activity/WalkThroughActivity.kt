package com.ninebx.ui.tutorial.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ninebx.R
import com.ninebx.ui.auth.AuthActivity
import com.ninebx.ui.tutorial.adapter.TutorialAdapter
import com.ninebx.utility.NineBxPreferences
import kotlinx.android.synthetic.main.activity_walk_through.*

class WalkThroughActivity : AppCompatActivity() {

    val prefrences = NineBxPreferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_walk_through)

        val viewPager = findViewById<View>(R.id.activity_help_view_pager) as ViewPager
        viewPager.adapter = TutorialAdapter(supportFragmentManager)
        activity_help_view_page_indicator.setViewPager(activity_help_view_pager!!)

        activity_help_view_page_indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Here, we will change the text color of GET STARTED
                when (position) {
                    0 -> {
                        txtGetStarted.setTextColor(resources.getColor(R.color.black))
                        txtGetStarted.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_one, 0);
                        imgWalkthroughBg.setImageDrawable(null)
                    }
                    1 -> {
                        txtGetStarted.setTextColor(resources.getColor(R.color.be_organized))
                        txtGetStarted.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_two, 0);
                        imgWalkthroughBg.setImageResource(R.drawable.walkthrough_bg)

                    }
                    2 -> {
                        txtGetStarted.setTextColor(resources.getColor(R.color.be_engaged))
                        txtGetStarted.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_three, 0);

                        imgWalkthroughBg.setImageResource(R.drawable.walkthrough_bg)
                    }
                    3 -> {
                        txtGetStarted.setTextColor(resources.getColor(R.color.be_empowered))
                        txtGetStarted.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_four, 0);

                        imgWalkthroughBg.setImageResource(R.drawable.walkthrough_bg)
                    }
                    4 -> {
                        txtGetStarted.setTextColor(resources.getColor(R.color.be_relaxed))
                        txtGetStarted.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_five, 0);

                        imgWalkthroughBg.setImageResource(R.drawable.walkthrough_bg)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
            }

        })

        txtGetStarted.setOnClickListener(View.OnClickListener {
            prefrences.firstRun = true
            val intent = Intent(this@WalkThroughActivity, AuthActivity::class.java)
            startActivity(intent)
            finish()
        })

/*

        materialTutorialPresenter = MaterialTutorialPresenter(this*/
/*, this*//*
)
        val actionBar = actionBar

        if (actionBar != null) {
            getActionBar()!!.hide()
        }

        txtGetStarted!!.setOnClickListener { materialTutorialPresenter.userActionGetStartedBtnClick() }

        val tutorialItem1 = WalkThroughItem(R.string.screen_one_title_1,
                R.string.screen_one_title_2,
                R.string.screen_one_description,
                R.color.colorPrimary,
                R.color.colorAccent,
                R.drawable.walkthrough_1,
                R.drawable.walkthrough_bg)

        val tutorialItem2 = WalkThroughItem(R.string.screen_two_title_1,
                R.string.screen_two_title_2,
                R.string.screen_two_description,
                R.color.colorAccent,
                R.color.colorPrimary,
                R.drawable.walkthrough_2,
                R.drawable.walkthrough_bg)

        val tutorialItem3 = WalkThroughItem(R.string.screen_three_title_1,
                R.string.screen_three_title_2,
                R.string.screen_three_description,
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.drawable.walkthrough_3,
                R.drawable.walkthrough_bg)

        val tutorialItem4 = WalkThroughItem(R.string.screen_four_title_1,
                R.string.screen_four_title_2,
                R.string.screen_four_description,
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.drawable.walkthrough_4,
                R.drawable.walkthrough_bg)


        val tutorialItems = ArrayList<WalkThroughItem>()
        tutorialItems.add(tutorialItem1)
        tutorialItems.add(tutorialItem2)
        tutorialItems.add(tutorialItem3)
        tutorialItems.add(tutorialItem4)
        materialTutorialPresenter.loadViewPagerFragments(tutorialItems)
*/


    }
/*
    override fun showNextTutorial() {
        val currentItem = activity_help_view_pager.currentItem
        if (currentItem < materialTutorialPresenter.numberOfTutorials) {
            activity_help_view_pager!!.currentItem = activity_help_view_pager!!.currentItem + 1
        }
    }*/
/*

    override fun showEndTutorial() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun setBackgroundColor(color: Int) {
//        activity_help_root.setBackgroundColor(color)
    }

    override fun getStartedBtnClick() {
        val homeActivityIntent = Intent(this, SignInActivity::class.java)
        startActivity(homeActivityIntent)
        finish()
    }
*/


/*

    override fun setViewPagerFragments(materialTutorialFragments: List<MaterialTutorialFragment>) {
        activity_help_view_pager.adapter = MaterialTutorialAdapter(supportFragmentManager, materialTutorialFragments)
        activity_help_view_page_indicator.setViewPager(activity_help_view_pager!!)
        activity_help_view_page_indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i2: Int) {

            }

            override fun onPageSelected(i: Int) {
                materialTutorialPresenter.onPageSelected(activity_help_view_pager.currentItem)

            }

            override fun onPageScrollStateChanged(i: Int) {

            }
        })
        activity_help_view_pager.setPageTransformer(true

        ) { page, position -> materialTutorialPresenter.transformPage(page, position) }
    }

*/

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

}
