package com.ninebx.ui.tutorial

import android.view.View
import com.ninebx.ui.tutorial.fragment.MaterialTutorialFragment
import java.util.ArrayList
import javax.inject.Inject

/***
 * Created by TechnoBlogger on 02/01/18.
 */

class MaterialTutorialPresenter @Inject internal constructor(private val tutorialView: WalkThroughContract.View) : WalkThroughContract.UserActionsListener {
    private lateinit var fragments: MutableList<MaterialTutorialFragment>
    private lateinit var tutorialItems: List<WalkThroughItem>


    override fun loadViewPagerFragments(tutorialItems: List<WalkThroughItem>) {
        fragments = ArrayList<MaterialTutorialFragment>()
        this.tutorialItems = tutorialItems
        tutorialItems.indices
                .map { MaterialTutorialFragment.newInstance(tutorialItems[it], it) }
                .forEach { fragments.add(it) }
        tutorialView.setViewPagerFragments(fragments)
    }

    override fun userActionGetStartedBtnClick() {

        tutorialView.getStartedBtnClick()
    }

    override fun onPageSelected(pageNo: Int) {
    }

    override fun transformPage(page: View, position: Float) {
    }

    override val numberOfTutorials: Int
        get() {
            return tutorialItems.size
        }

}
