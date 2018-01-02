package com.ninebx.ui.tutorial

import com.ninebx.ui.tutorial.fragment.MaterialTutorialFragment

/***
 * Created by TechnoBlogger on 18/12/17.
 */
interface WalkThroughContract {

    interface View {
        fun showNextTutorial()
        fun showEndTutorial()
        fun setBackgroundColor(color: Int)
        fun getStartedBtnClick()
        fun setViewPagerFragments(materialTutorialFragments: List<MaterialTutorialFragment>)
    }

    interface UserActionsListener {
        fun loadViewPagerFragments(tutorialItems: List<WalkThroughItem>)
        fun userActionGetStartedBtnClick()
        fun onPageSelected(pageNo: Int)
        fun transformPage(page: android.view.View, position: Float)

        val numberOfTutorials: Int
    }
}