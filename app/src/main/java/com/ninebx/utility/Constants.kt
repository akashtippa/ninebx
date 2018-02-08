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
    val LEVEL2_PICKER = 19
    val LEVEL2_NUMBER = 20
    val LEVEL_NORMAL_SPINNER = 21

    val KEYBOARD_NUMBER = "number"
    val KEYBOARD_SPINNER = "spinner"
    val KEYBOARD_SEARCH = "search"
    val KEYBOARD_PICKER = "picker"
    val CURRENT_USER: String = "current_user"
    val USER_EMAIL: String = "user_email"
    val PRIVATE_KEY: String = "private_key"

    val CURRENT_BOX = "valCurrentBox"

    // Just for Testing
    val TEST_FRAGMENT_A: String = "test_fragment_a"
    val TEST_FRAGMENT_B: String = "test_fragment_b"

    val COUNTRY_SELECTED: String = "country_selected"

    // Shopping Size List For Women

    val PICKER_WOMENS_DETAILS_SIZE = "valPickerWomenSize"
    val PICKER_WOMEN_SIZE_US = "valPickerWomenSizeUS"
    val PICKER_WOMEN_NUMERIC_SIZE = "valPickerNumericSize"
    val PICKER_WOMEN_SHOES = "valWomenPickerShoesSize"
    val PICKER_WOMEN_SHOES_WIDTH = "valWomenPickerWomenShoeWidth"
    val PICKER_WOMEN_ACCESSORIES_BELTS = "valWomenAccessoriesBelts"
    val PICKER_WOMEN_ACCESSORIES_HATS = "valWomenAccessoriesHats"
    val PICKER_WOMEN_ACCESSORIES_GLOVES = "valWomenAccessoriesGloves"
    val PICKER_WOMEN_ACCESSORIES_TIGHTS = "valWomenAccessoriesTights"

    // Shopping Size List For Men

    val PICKER_MENS_SIZE_CATEGORY_US = "valMenSizeCategory"
    val PICKER_MENS_SIZE = "valPickerMensSize"
    val PICKER_MENS_NUMERIC_SIZE_TOPS = "valNumericSizeTops"
    val PICKER_MENS_NUMERIC_SIZE_BOTTOMS = "valNumericSizeBottoms"
    val PICKER_MENS_NUMERIC_SIZE_SUITING_JACKETS = "valNumericSizeSuitingJackets"
    val PICKER_MENS_NUMERIC_SIZE_SUITING_PANTS = "valNumericSizeSuitingPants"
    val PICKER_MENS_NUMERIC_SIZE_SUITING_OUTERWEAR = "valNumericSizeOuterWear"
    val PICKER_MENS_SHOES = "valPickerMensShoes"
    val PICKER_MENS_SHOES_WIDTH = "valPickerMensShoesWidth"
    val PICKER_MENS_BELTS = "valPickerMensBelts"
    val PICKER_MENS_BELTS_NUMERIC_SIZE = "valPickerMensBeltsNumericSize"
    val PICKER_MENS_TIGHTS = "valPickerMensTights"
    val PICKER_MENS_GLOVES = "valPickerMensGloves"

    // Shopping List For Girls and Boys

    val PICKER_GIRLS_NUMERIC_SIZE = "valGirlsNumericSize"
    val PICKER_GIRLS_SHOES_TODDLER = "valGirlsShoes"
    val PICKER_GIRLS_SHOES_LITTLE_AND_BIG_KID = "valGirlsLittleAndBigKid"
    val PICKER_GIRLS_SHOES_WIDTH = "valGirlsShoesWidth"
    val PICKER_GIRLS_ACCESSORIES_BELTS = "valGirlsAccessoriesBelts"
    val PICKER_GIRLS_ACCESSORIES_BELTS_NUMERIC_SIZE = "valGirlsAccessoriesBeltsNumericSize"
    val PICKER_GIRLS_ACCESSORIES_HATS = "valGirlsAccessoriesHats"
    val PICKER_GIRLS_ACCESSORIES_GLOVES = "valGirlsAccessoriesGloves"
    val PICKER_GIRLS_ACCESSORIES_TIGHTS = "valGirlsAccessoriesTights"
    val PICKER_GIRLS_ACCESSORIES_SOCKS = "valGirlsAccessoriesSocks"

    // Shopping List For Baby

    val PICKER_BABY_CLOTHING = "valPickerBabyClothing"
    val PICKER_BABY_SHOES = "valPickerBabyShoes"

    // Other DropDown Values

    val BANK_ACCOUNT_TYPE = "bankAccountType"
    val CARD_TYPE = "cardType"
    val CONTACT_SPINNER = "contactSpinner"

    // Temporary Private Key

    val TEMP_PRIVATE_KEY = "nB8hEnaqppfWOp5L"

}
