package com.ninebx.ui.home.account.billing

import com.module.inappbilling.billing.BillingManager

/**
 * Created by Alok on 07/02/18.
 */
interface NineBxBillingProvider {

    fun getBillingManager(): BillingManager
    fun isOnePlusYearSubscribed(): Boolean
    fun isFamilyYearlySubscribed(): Boolean
    fun isFamilyPlusYearlySubscribed(): Boolean
    fun isStoragePurchased(): Boolean
    fun isOnePlusMonthlySubscribed() : Boolean
    fun isFamilyMonthlyPurchased(): Boolean
    fun isFamilyPlusMonthlyPurchased(): Boolean

}