package com.ninebx.utility

import com.google.gson.Gson
import com.ninebx.ui.base.realm.Users


/***
 * Created by TechnoBlogger on 18/12/17.
 */

class NineBxPreferences : Preferences() {


    var firstRun by booleanPref(Constants.FIRST_RUN)
    var isLogin by booleanPref(Constants.IS_LOGIN)
    var isPasswordRequired by booleanPref(Constants.IS_PASSWORD_REQUIRED)
    var isPasswordEnabled by booleanPref(Constants.IS_MAPS_SHOWN)
    var currentStep by intPref(Constants.CURRENT_STEP)
    var currentUser by stringPref(Constants.CURRENT_USER)
    var privateKey by stringPref(Constants.PRIVATE_KEY)
    var currentBox by stringPref(Constants.CURRENT_BOX)
    var testinFragmentA by stringPref(Constants.TEST_FRAGMENT_A)
    var testinFragmentB by stringPref(Constants.TEST_FRAGMENT_B)

    var countrySelected by stringPref(Constants.COUNTRY_SELECTED)

    fun getCurrentUser(): Users? {
        return Gson().fromJson( currentUser, Users::class.java )
    }

    fun setCurrentUser( users : Users ) {
        currentUser = Gson().toJson(users)
    }




}