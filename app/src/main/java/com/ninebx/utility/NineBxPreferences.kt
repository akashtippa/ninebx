package com.ninebx.utility

import com.google.gson.Gson
import com.ninebx.ui.base.realm.Users
import com.ninebx.utility.Constants.NONE_COMPLETE


/***
 * Created by TechnoBlogger on 18/12/17.
 */

class NineBxPreferences : Preferences() {


    var firstRun by booleanPref(Constants.FIRST_RUN)
    var isLogin by booleanPref(Constants.IS_LOGIN)
    var isPasswordRequired by booleanPref(Constants.IS_PASSWORD_REQUIRED)
    var isPasswordEnabled by booleanPref(Constants.IS_MAPS_SHOWN)
    var currentStep by intPref(Constants.CURRENT_STEP, NONE_COMPLETE)
    var userEmail by stringPref(Constants.USER_EMAIL, "")
    var passCode by stringPref(Constants.PASSCODE, "")
    var privateKey by stringPref(Constants.PRIVATE_KEY)
    var currentBox by stringPref(Constants.CURRENT_BOX)
    var testinFragmentA by stringPref(Constants.TEST_FRAGMENT_A)
    var testinFragmentB by stringPref(Constants.TEST_FRAGMENT_B)

    var countrySelected by stringPref(Constants.COUNTRY_SELECTED)
    var isFingerPrintEnabled by booleanPref(Constants.FINGER_PRINT)


}