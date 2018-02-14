package com.ninebx.ui.home.account.interfaces;

import com.ninebx.ui.base.realm.Member;

/***
 * Created by TechnoBlogger on 21/01/18.
 */

public interface IMemberAdded {
    void memberAdded(Member member);
    void onMemberEdit(Member member);
}
