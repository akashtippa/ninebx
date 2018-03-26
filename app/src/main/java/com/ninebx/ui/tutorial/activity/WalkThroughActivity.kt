package com.ninebx.ui.tutorial.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.auth.AuthActivity
import com.ninebx.ui.tutorial.adapter.TutorialAdapter
import kotlinx.android.synthetic.main.activity_walk_through.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class WalkThroughActivity : AppCompatActivity() {

    val prefrences = NineBxApplication.getPreferences()

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
                        txtGetStarted.setTextColor(resources.getColor(R.color.get_started))
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

        txtGetStarted.setOnClickListener({
            prefrences.firstRunDone = true
            val intent = Intent(this@WalkThroughActivity, AuthActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

}
