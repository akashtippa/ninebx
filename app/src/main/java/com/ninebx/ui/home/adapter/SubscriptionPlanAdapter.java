package com.ninebx.ui.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninebx.R;

public class SubscriptionPlanAdapter extends PagerAdapter {

    Context mContext;
    int resId = 0;

    public SubscriptionPlanAdapter(Context context) {
        mContext = context;
    }


    public Object instantiateItem(ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        switch (position) {
            case 0:
                resId = R.layout.pager_choose_plan;
                break;
            case 1:
                resId = R.layout.pager_compare_plan;
                break;
        }
        ViewGroup layout = (ViewGroup) inflater.inflate(resId, collection, false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
}