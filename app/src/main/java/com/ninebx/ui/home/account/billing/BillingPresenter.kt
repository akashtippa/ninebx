package com.ninebx.ui.home.account.billing

import com.module.inappbilling.billing.BillingManager

/**
 * Created by Alok on 07/02/18.
 */
class BillingPresenter( val billingView: BillingView ) : NineBxBillingProvider {

    override fun isOnePlusYearSubscribed(): Boolean {
        return false
    }

    override fun isFamilyYearlySubscribed(): Boolean {
        return false
    }

    override fun isFamilyPlusYearlySubscribed(): Boolean {
        return false
    }

    override fun isStoragePurchased(): Boolean {
        return false
    }

    override fun isOnePlusMonthlySubscribed(): Boolean {
        return false
    }

    override fun isFamilyMonthlyPurchased(): Boolean {
        return false
    }

    override fun isFamilyPlusMonthlyPurchased(): Boolean {
        return false
    }


    override fun getBillingManager(): BillingManager {
        return null!!
    }



}