package com.ninebx.utility

import com.amazonaws.regions.Regions

/**
 * Created by TechnoBlogger on 18/12/17.
 */

object Constants {
    val FIRST_RUN = "is_first_run"
    val IS_LOGIN = "is_login"
    val IS_PASSWORD_REQUIRED = "is_password_required"
    val IS_MAPS_SHOWN = "IS_MAPS_SHOWN"

    var SERVER_URL = "realm://13.59.129.162:9080/~/"
    var SERVER_ADDRESS = "realm://13.59.129.162:9080/"
    var SERVER_IP = "http://13.59.129.162:9080"
    var SERVER_API_ADDRESS = "http://13.59.129.162/api/"

    val CURRENT_STEP: String = "current_step"

    val ALL_COMPLETE = 99
    val SIGN_UP_COMPLETE = 1
    val ACCOUNT_PASSWORD_COMPLETE = 2
    val OTP_COMPLETE = 3
    val PASS_CODE_COMPLETE = 4
    val FINGER_PRINT_COMPLETE = 5
    val INVITE_USERS_COMPLETE = 6


    val SUB_CATEGORY_ADD_PERSON = 0
    val SUB_CATEGORY_ADD_ITEM = 1
    val SUB_CATEGORY_DISPLAY_PERSON = 2
    val COGNITO_POOL_ID: String = "us-west-2:c2651f16-d7e1-4c2f-9f1f-1100caf406e3"
    val COGNITO_POOL_REGION: Regions = Regions.US_WEST_2
    val BUCKET_NAME: String? = "ninebxfiles"
    val INDEX_NOT_CHECKED: Int = -1

    val LEVEL2_LOCATION = 10
    val LEVEL2_PASSWORD = 11
    val LEVEL2_RADIO = 12
    val LEVEL2_SPINNER = 13
    val LEVEL2_SWITCH = 14
    val LEVEL2_USD = 15
    val LEVEL2_NOTES = 16
    val LEVEL2_NORMAL = 17
    val LEVEL2_ATTACHMENTS = 18

    val KEYBOARD_NUMBER = "number"
    val KEYBOARD_SPINNER = "spinner"
    val KEYBOARD_SEARCH = "search"
    val KEYBOARD_PICKER = "picker"


}
