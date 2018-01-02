package com.ninebx.ui.tutorial.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ninebx.R
import com.ninebx.ui.tutorial.MaterialTutorialPresenter
import com.ninebx.ui.tutorial.WalkThroughContract
import com.ninebx.ui.tutorial.fragment.MaterialTutorialFragment
import com.ninebx.utility.NineBxPreferences
import javax.inject.Inject

class WalkThroughActivity : AppCompatActivity(), WalkThroughContract.View {

    @Inject
    lateinit var materialTutorialPresenter: MaterialTutorialPresenter

    @Inject
    lateinit var preferences: NineBxPreferences


    override fun showNextTutorial() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEndTutorial() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBackgroundColor(color: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStartedBtnClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setViewPagerFragments(materialTutorialFragments: List<MaterialTutorialFragment>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk_through)
        val preferences = NineBxPreferences()

        preferences.firstRun = true

    }


}
