package com.ninebx.utility

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

    var userEmailID by stringPref(Constants.USER_EMAIL_ID)
    var userPassword by stringPref(Constants.USER_PASSWORD, "")
    var userPasswordUINT8 by stringPref(Constants.USER_PASSWORD_UINT, "")

    var countrySelected by stringPref(Constants.COUNTRY_SELECTED)
    var isFingerPrintEnabled by booleanPref(Constants.FINGER_PRINT)
    fun clearPreferences() {
        firstRun = false
        isPasswordRequired = false
        isPasswordEnabled = false
        currentStep = NONE_COMPLETE
        userEmail = ""
        passCode = ""
        privateKey = ""
        currentBox = ""
        countrySelected = ""
        isFingerPrintEnabled = false
    }


}