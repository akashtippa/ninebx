package com.ninebx.ui.tutorial.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ninebx.ui.tutorial.fragment.FragmentEmpowered;
import com.ninebx.ui.tutorial.fragment.FragmentEngaged;
import com.ninebx.ui.tutorial.fragment.FragmentOrganized;
import com.ninebx.ui.tutorial.fragment.FragmentRelaxed;
import com.ninebx.ui.tutorial.fragment.FragmentSwipeToLearnMore;

/***
 * Created by TechnoBlogger on 03/01/18.
 */

public class TutorialAdapter extends FragmentPagerAdapter {

    public TutorialAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {

            case 0: return FragmentSwipeToLearnMore.Companion.newInstance();
            case 1: return FragmentOrganized.Companion.newInstance();
            case 2: return FragmentEngaged.Companion.newInstance();
            case 3: return FragmentEmpowered.Companion.newInstance();
            case 4: return FragmentRelaxed.Companion.newInstance();

            default: return FragmentSwipeToLearnMore.Companion.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}