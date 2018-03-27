package com.ninebx.utility

import com.ninebx.NineBxApplication
import com.ninebx.utility.Constants.NONE_COMPLETE


/***
 * Created by TechnoBlogger on 18/12/17.
 */

class NineBxPreferences( var userId : String = "" ) : Preferences() {

    var adminId by stringPref( userId + "_" + Constants.ADMIN)
    var firstRunDone by booleanPref(Constants.FIRST_RUN)
    var isLogin by booleanPref(Constants.IS_LOGIN)
    var isPasswordRequired by booleanPref(userId + "_" +Constants.IS_PASSWORD_REQUIRED)
    var isPasswordEnabled by booleanPref(userId + "_" +Constants.IS_MAPS_SHOWN)
    var currentStep by intPref(userId + "_" +Constants.CURRENT_STEP, NONE_COMPLETE)
    var userEmail by stringPref(userId + "_" +Constants.USER_EMAIL)
    var passCode by stringPref(userId + "_" +Constants.PASSCODE)
    var privateKey by stringPref(userId + "_" +Constants.PRIVATE_KEY)
    var currentBox by stringPref(userId + "_" +Constants.CURRENT_BOX)

    var userID by stringPref(userId + "_" +Constants.USER_EMAIL_ID)
    var userFirstName by stringPref(userId + "_" +Constants.USER_FIRST_NAME)
    var userLastName by stringPref(userId + "_" +Constants.USER_LAST_NAME)
    var created by stringPref(userId + "_" +Constants.USER_LAST_NAME)
    var userPassword by stringPref(userId + "_" +Constants.USER_PASSWORD)
    var userPasswordUINT8 by stringPref(userId + "_" +Constants.USER_PASSWORD_UINT)

    var forTestingBackPress by stringPref(Constants.USER_FOR_TESTING)
    var isFingerPrintEnabled by booleanPref(Constants.FINGER_PRINT)

    fun clearPreferences() {
        firstRunDone = true
        isPasswordRequired = false
        isPasswordEnabled = false
        currentStep = NONE_COMPLETE
        userEmail = ""
        passCode = ""
        privateKey = ""
        currentBox = ""
        isFingerPrintEnabled = false
        userID = ""
        userFirstName = ""
        userLastName = ""
        created = ""
        userPassword = ""
        userPasswordUINT8 = ""
        AppLogger.d("Email", "Email : in clearPreferences : " + NineBxApplication.getPreferences().userEmail)
    }

    fun clearLogOutPreferences(email: String) {
        firstRunDone = true
        isPasswordRequired = false
        isPasswordEnabled = false
        currentStep = NONE_COMPLETE
        privateKey = ""
        currentBox = ""
        userID = ""
        userEmail = email
        userFirstName = ""
        userLastName = ""
        created = ""
        userPassword = ""
        userPasswordUINT8 = ""
        AppLogger.d("Email", "Email : in clearLogOutPreferences : " + NineBxApplication.getPreferences().userEmail)
    }



}