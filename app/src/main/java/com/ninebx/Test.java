package com.ninebx;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import com.ninebx.ui.home.account.interfaces.IMemberAdded;

import java.util.Calendar;

import io.realm.RealmConfiguration;

/***
 * Created by TechnoBlogger on 19/12/17.
 */

public class Test {
//    RealmConfiguration config = RealmConfiguration.Builder(this).build();


    private IMemberAdded iMemberAdded;

    public IMemberAdded getiMemberAdded() {
        return iMemberAdded;
    }

    public void setiMemberAdded(IMemberAdded iMemberAdded) {
        this.iMemberAdded = iMemberAdded;
    }


}
