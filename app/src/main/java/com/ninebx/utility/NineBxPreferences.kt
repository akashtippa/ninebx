package com.ninebx.utility


/***
 * Created by TechnoBlogger on 18/12/17.
 */

class NineBxPreferences : Preferences() {

    var firstRun by booleanPref(Constants.FIRST_RUN)
    var isLogin by booleanPref(Constants.IS_LOGIN)

    var currentStep by intPref(Constants.CURRENT_STEP)

}