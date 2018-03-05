package com.ninebx.ui.home.account.subscriptionPlan;

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

public class SubscriptionAdapter extends FragmentPagerAdapter {

    public SubscriptionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {

            case 0:
                return ChoosePlanFragment.Companion.newInstance();
            case 1:
                return ComparePlanFragment.Companion.newInstance();
            default:
                return ChoosePlanFragment.Companion.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}