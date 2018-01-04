package com.ninebx.ui.tutorial.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ninebx.ui.tutorial.fragment.MaterialTutorialFragment

/***
 * Created by TechnoBlogger on 02/01/18.
 */
class MaterialTutorialAdapter(fm: FragmentManager, private val fragments: List<MaterialTutorialFragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return this.fragments[position]
    }


    override fun getCount(): Int {
        return this.fragments.size
    }




}
