package com.ninebx.ui.home.account;

import com.ninebx.R;

/***
 * Created by TechnoBlogger on 22/01/18.
 */

public class PermissionData {
    static Integer[] bgDrawable = {R.drawable.bg_lt_home,
            R.drawable.bg_lt_travell,
            R.drawable.bg_lt_contacts,
            R.drawable.bg_lt_edu,
            R.drawable.bg_lt_personal,
            R.drawable.bg_lt_interests,
            R.drawable.bg_lt_wellness,
            R.drawable.bg_lt_memories,
            R.drawable.bg_lt_shopping
    };

    static Integer[] menuDrawable = {R.drawable.ic_home_icon_home_money_per,
            R.drawable.ic_home_icon_travel,
            R.drawable.ic_home_icon_contacts,
            R.drawable.ic_home_icon_education_work_per,
            R.drawable.ic_home_icon_personal,
            R.drawable.ic_home_icon_interests,
            R.drawable.ic_home_icon_wellness,
            R.drawable.ic_home_icon_memories,
            R.drawable.ic_home_icon_shopping};

    static String[] menuName = {"Home & Money",
            "Travel",
            "Contacts",
            "Education & Work",
            "Personal",
            "Interests",
            "Wellness",
            "Memories",
            "Shopping"};

    static boolean[] chkView = {true, true, true, true, true, true, true, true, true};
    static boolean[] chkAdd = {true, true, true, true, true, true, true, true, true};
    static boolean[] chkEdit = {true, true, true, true, true, true, true, true, true};
}
