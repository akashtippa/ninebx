package com.ninebx.ui.auth.passwordHash

/**
 * Created by Sourav on 16-03-2018.
 */
interface OTPView {

    fun otpVerificationGet():Boolean
    fun otpVerificationSet(isPaused : Boolean)
}