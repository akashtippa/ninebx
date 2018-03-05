package com.ninebx.ui.home.account.interfaces;

import com.ninebx.ui.base.realm.Member;
import com.ninebx.ui.base.realm.decrypted.DecryptedMember;

/***
 * Created by TechnoBlogger on 21/01/18.
 */

public interface IMemberAdded {
    void memberAdded(DecryptedMember member);

    void onMemberEdit(DecryptedMember member);
}
