package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Parcelable
import android.util.Log
import com.ninebx.NineBxApplication
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.base.realm.home.shopping.CombineShopping
import com.ninebx.utility.*
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Chinmaya on 20-03-2018.
 */
class ShoppingHelper(
        var category_name : String,
        val categoryID: String,
        var classType : String?,
        var selectedDocument : Parcelable?,
        val categoryView : Level2CategoryView
){
    private var decryptedLoyaltyPrograms: DecryptedLoyaltyPrograms? = null
    private var decryptedRecentPurchase: DecryptedRecentPurchase? = null
    private var decryptedClothingSizes: DecryptedClothingSizes? = null
    private var decryptedShopping: DecryptedShopping? = null

    fun initialize(){
        if(selectedDocument==null){
            return
        }
        when(classType){
            DecryptedLoyaltyPrograms::class.java.simpleName -> {
                decryptedLoyaltyPrograms = selectedDocument as DecryptedLoyaltyPrograms
            }
            DecryptedRecentPurchase::class.java.simpleName -> {
                decryptedRecentPurchase = selectedDocument as DecryptedRecentPurchase
            }
            DecryptedClothingSizes::class.java.simpleName -> {
                decryptedClothingSizes = selectedDocument as DecryptedClothingSizes
            }
            DecryptedShopping::class.java.simpleName -> {
                decryptedShopping = selectedDocument as DecryptedShopping
            }
        }
    }
    fun getFormForCategory(){
        when(category_name){
        // Shopping
            "Loyalty Programs" -> {
                getLoyaltyPrograms()
            }

            "Recent Purchases" -> {
                getRecentPurchases()
            }
        // Clothing Sizes
            "Womens sizes" -> {
                getWomensSizes()
            }
            "Mens sizes" -> {
                getMenSizes()
            }
            "Girls sizes" -> {
                getGirlsSizes()
            }
            "Boy's sizes" -> {
                getBoysSizes()
            }
            "Baby's sizes" -> {
                getBabysSizes()
            }
        }
    }
    fun setValue(level2Category: Level2SubCategory){
        when(category_name){
        // Shopping
            "Loyalty Programs" -> {
                setLoyaltyPrograms(level2Category)
            }

            "Recent Purchases" -> {
                setRecentPurchases(level2Category)
            }
        // Clothing Sizes
            "Womens sizes" -> {
                setClothingSizes(level2Category)
            }
            "Mens sizes" -> {
                setClothingSizes(level2Category)
            }
            "Girls sizes" -> {
                setClothingSizes(level2Category)
            }
            "Boy's sizes" -> {
                setClothingSizes(level2Category)
            }
            "Baby's sizes" -> {
                setClothingSizes(level2Category)
            }
        }
    }
    private fun getBabysSizes() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedClothingSizes == null) decryptedClothingSizes = DecryptedClothingSizes()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Clothing", "Clothing", Constants.PICKER_BABY_CLOTHING, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Shoes", "Shoes", Constants.PICKER_BABY_SHOES, Constants.LEVEL_NORMAL_SPINNER))

        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getBoysSizes() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedClothingSizes == null) decryptedClothingSizes = DecryptedClothingSizes()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Tops"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))

        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Bottoms"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)


        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Outerwear"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Shoes"
        category.subCategories.add(Level2SubCategory("Toddler size (US)", "Toddler size (US)", Constants.PICKER_GIRLS_SHOES_TODDLER, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Little and big kid size(US)", "Little and big kid size(US)", Constants.PICKER_GIRLS_SHOES_LITTLE_AND_BIG_KID, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Width", "Width", Constants.PICKER_GIRLS_SHOES_WIDTH, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Accessories"
        category.subCategories.add(Level2SubCategory("Belts", "Belts", Constants.PICKER_GIRLS_ACCESSORIES_BELTS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Belts(Numeric size)", "Belts(Numeric size)", Constants.PICKER_GIRLS_ACCESSORIES_BELTS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Hats", "Hats", Constants.PICKER_GIRLS_ACCESSORIES_HATS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Gloves", "Gloves", Constants.PICKER_GIRLS_ACCESSORIES_GLOVES, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Tights", "Tights", Constants.PICKER_GIRLS_ACCESSORIES_TIGHTS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Socks", "Socks", Constants.PICKER_GIRLS_ACCESSORIES_SOCKS, Constants.LEVEL_NORMAL_SPINNER))

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Measurements"
        category.subCategories.add(Level2SubCategory("Chest(in)", "Chest(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Waist(in)", "Waist(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Seat(in)", "Seat(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Inseam(in)", "Inseam(in)", "", Constants.LEVEL2_NUMBER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getGirlsSizes() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedClothingSizes == null) decryptedClothingSizes = DecryptedClothingSizes()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Tops"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))

        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Bottoms"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Dresses"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Outerwear"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Swimwear"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_GIRLS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Shoes"
        category.subCategories.add(Level2SubCategory("Toddler size (US)", "Toddler size (US)", Constants.PICKER_GIRLS_SHOES_TODDLER, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Little and big kid size(US)", "Little and big kid size(US)", Constants.PICKER_GIRLS_SHOES_LITTLE_AND_BIG_KID, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Width", "Width", Constants.PICKER_GIRLS_SHOES_WIDTH, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Accessories"
        category.subCategories.add(Level2SubCategory("Belts", "Belts", Constants.PICKER_GIRLS_ACCESSORIES_BELTS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Belts(Numeric size)", "Belts(Numeric size)", Constants.PICKER_GIRLS_ACCESSORIES_BELTS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Hats", "Hats", Constants.PICKER_GIRLS_ACCESSORIES_HATS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Gloves", "Gloves", Constants.PICKER_GIRLS_ACCESSORIES_GLOVES, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Tights", "Tights", Constants.PICKER_GIRLS_ACCESSORIES_TIGHTS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Socks", "Socks", Constants.PICKER_GIRLS_ACCESSORIES_SOCKS, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Measurements"
        category.subCategories.add(Level2SubCategory("Chest(in)", "Chest(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Waist(in)", "Waist(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Seat(in)", "Seat(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Inseam(in)", "Inseam(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Torso(in)", "Torso(in)", "", Constants.LEVEL2_NUMBER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getMenSizes() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedClothingSizes == null) decryptedClothingSizes = DecryptedClothingSizes()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Size category(US)", "Size category(US)", Constants.PICKER_MENS_SIZE_CATEGORY_US, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Tops"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_MENS_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_MENS_NUMERIC_SIZE_TOPS, Constants.LEVEL_NORMAL_SPINNER))

        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Bottoms"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_MENS_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_MENS_NUMERIC_SIZE_BOTTOMS, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Suiting-Jackets"
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_JACKETS, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Suiting-Pants"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_MENS_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_PANTS, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Outwear"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_MENS_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_OUTERWEAR, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Shoes"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_MENS_SHOES, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Width", "Width", Constants.PICKER_MENS_SHOES_WIDTH, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Accessories"
        category.subCategories.add(Level2SubCategory("Belts", "Belts", Constants.PICKER_MENS_BELTS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Belts(Numeric size)", "Belts(Numeric size)", Constants.PICKER_MENS_BELTS_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Gloves", "Gloves", Constants.PICKER_MENS_GLOVES, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Tights", "Tights", Constants.PICKER_MENS_TIGHTS, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Measurements"
        category.subCategories.add(Level2SubCategory("Neck(in)", "Neck(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Chest(in)", "Chest(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Waist(in)", "Waist(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Arm length(in)", "Arm length(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Inseam(in)", "Inseam(in)", "", Constants.LEVEL2_NUMBER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }

    private fun getWomensSizes() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedClothingSizes == null) decryptedClothingSizes = DecryptedClothingSizes()

        var categoryIndex = 2050
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Size category(US)", "Size category(US)", Constants.PICKER_WOMENS_DETAILS_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Tops"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_WOMEN_SIZE_US, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_WOMEN_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))

        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Bottoms"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_WOMEN_SIZE_US, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_WOMEN_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Dresses"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_WOMEN_SIZE_US, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_WOMEN_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Outwear"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_WOMEN_SIZE_US, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_WOMEN_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Swimwear"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_WOMEN_SIZE_US, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Numeric (US)", "Numeric (US)", Constants.PICKER_WOMEN_NUMERIC_SIZE, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Bra band/cup size (US)", "Bra band/cup size (US)", "", Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Shoes"
        category.subCategories.add(Level2SubCategory("Size (US)", "Size (US)", Constants.PICKER_WOMEN_SHOES, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Width", "Width", Constants.PICKER_WOMEN_SHOES_WIDTH, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Accessories"
        category.subCategories.add(Level2SubCategory("Belts", "Belts", Constants.PICKER_WOMEN_ACCESSORIES_BELTS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Hats", "Hats", Constants.PICKER_WOMEN_ACCESSORIES_HATS, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Gloves", "Gloves", Constants.PICKER_WOMEN_ACCESSORIES_GLOVES, Constants.LEVEL_NORMAL_SPINNER))
        category.subCategories.add(Level2SubCategory("Tights", "Tights", Constants.PICKER_WOMEN_ACCESSORIES_TIGHTS, Constants.LEVEL_NORMAL_SPINNER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Measurements"
        category.subCategories.add(Level2SubCategory("Bust(in)", "Bust(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Waist(in)", "Waist(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Hips(in)", "Hips(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Arm length(in)", "Arm length(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Inseam(in)", "Inseam(in)", "", Constants.LEVEL2_NUMBER))
        category.subCategories.add(Level2SubCategory("Torso(in)", "Torso(in)", "", Constants.LEVEL2_NUMBER))
        categoryList.add(category)

        categoryIndex += 2050
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2001
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }
    private fun getRecentPurchases() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedRecentPurchase == null) decryptedRecentPurchase = DecryptedRecentPurchase()

        var categoryIndex = 2034
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Purchased by", decryptedRecentPurchase!!.purchasedBy, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Purchased date", decryptedRecentPurchase!!.purchasedDate, "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Purchased price", decryptedRecentPurchase!!.purchasedPrice, "", Constants.LEVEL2_USD))
        categoryList.add(category)

        categoryIndex += 2034
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedRecentPurchase!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2034
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }
    private fun getLoyaltyPrograms() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedLoyaltyPrograms == null) decryptedLoyaltyPrograms = DecryptedLoyaltyPrograms()
        var categoryIndex = 2033
        var category_id = "account_details" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Account Details"
        category.subCategories.add(Level2SubCategory("Name(s) on account", decryptedLoyaltyPrograms!!.nameOnAccount, "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", decryptedLoyaltyPrograms!!.accountNumber, "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2033
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", decryptedLoyaltyPrograms!!.website, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", decryptedLoyaltyPrograms!!.userName, "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", decryptedLoyaltyPrograms!!.password, "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", decryptedLoyaltyPrograms!!.pin, "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2033
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("Notes", decryptedLoyaltyPrograms!!.notes, "", Constants.LEVEL2_NOTES))
        categoryList.add(category)


        categoryIndex += 2033
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)

        categoryView.onSuccess(categoryList)
    }
    private fun setClothingSizes(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Name of person" -> decryptedClothingSizes!!.personName = level2Category.titleValue
            "Size name" -> decryptedClothingSizes!!.sizeName = level2Category.titleValue
            "detailsSizeCategory" -> decryptedClothingSizes!!.sizeCategory = level2Category.titleValue
            "topsSize" -> decryptedClothingSizes!!.topsSize = level2Category.titleValue
            "topsNumericSize" -> decryptedClothingSizes!!.topsNumericSize = level2Category.titleValue
            "bottomsSize" -> decryptedClothingSizes!!.bottomsSize = level2Category.titleValue
            "bottomsNumericSize" -> decryptedClothingSizes!!.bottomsNumericSize = level2Category.titleValue
            "dressesSize" -> decryptedClothingSizes!!.dressesSize = level2Category.titleValue
            "dressesNumericSize" -> decryptedClothingSizes!!.dressesNumericSize = level2Category.titleValue
            "outWearSize" -> decryptedClothingSizes!!.outWearSize = level2Category.titleValue
            "outWearNumericSize" -> decryptedClothingSizes!!.outWearNumericSize = level2Category.titleValue
            "swimWearSize" -> decryptedClothingSizes!!.swimWearSize = level2Category.titleValue
            "swimWearNumericSize" -> decryptedClothingSizes!!.swimWearNumericSize = level2Category.titleValue
            "swimWearbraBandSize" -> decryptedClothingSizes!!.swimWearBraBandCupSize = level2Category.titleValue
            "shoeSize" -> decryptedClothingSizes!!.shoeSize = level2Category.titleValue
            "shoeWidth" -> decryptedClothingSizes!!.shoeWidth = level2Category.titleValue

            "Belts" -> decryptedClothingSizes!!.beltSize = level2Category.titleValue
            "Waist (in)" -> decryptedClothingSizes!!.waist = level2Category.titleValue
            "Hats" -> decryptedClothingSizes!!.hats = level2Category.titleValue
            "Gloves" -> decryptedClothingSizes!!.gloves = level2Category.titleValue
            "Tights" -> decryptedClothingSizes!!.tights = level2Category.titleValue
            "Bust (in)" -> decryptedClothingSizes!!.bust = level2Category.titleValue
            "Hips (in)" -> decryptedClothingSizes!!.hips = level2Category.titleValue
            "Socks" -> decryptedClothingSizes!!.socks = level2Category.titleValue
            "Seat (in)" -> decryptedClothingSizes!!.seat = level2Category.titleValue

            "Arm length (in)" -> decryptedClothingSizes!!.armLength = level2Category.titleValue
            "Inseam (in)" -> decryptedClothingSizes!!.inseam = level2Category.titleValue
            "Torso (in)" -> decryptedClothingSizes!!.torso = level2Category.titleValue

            "Notes" -> decryptedClothingSizes!!.notes = level2Category.titleValue

            "clothing" -> decryptedClothingSizes!!.clothing = level2Category.titleValue
            "shoes" -> decryptedClothingSizes!!.shoes = level2Category.titleValue

            "jacketsNumericSize" -> decryptedClothingSizes!!.jacketsNumericSize = level2Category.titleValue
            "toddlerSize" -> decryptedClothingSizes!!.toddlerSize = level2Category.titleValue
            "kidsize" -> decryptedClothingSizes!!.kidSize = level2Category.titleValue

            "beltsSize" -> decryptedClothingSizes!!.beltSize = level2Category.titleValue
            "beltsNumericSize" -> decryptedClothingSizes!!.beltsNumericSize = level2Category.titleValue

            "Neck (in)" -> decryptedClothingSizes!!.neck = level2Category.titleValue
            "Chest (in)" -> decryptedClothingSizes!!.chest = level2Category.titleValue

            "beltsNumericSize" -> decryptedClothingSizes!!.beltsNumericSize = level2Category.titleValue

            "pantsSize" -> decryptedClothingSizes!!.pantsSize = level2Category.titleValue
            "pantsNumericSize" -> decryptedClothingSizes!!.pantsNumericSize = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedClothingSizes!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedClothingSizes!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }
    private fun setLoyaltyPrograms(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Brand/Retailer" -> decryptedLoyaltyPrograms!!.brandName = level2Category.titleValue
            "Account name" -> decryptedLoyaltyPrograms!!.accountName = level2Category.titleValue
            "Name(s) on account" -> decryptedLoyaltyPrograms!!.nameOnAccount = level2Category.titleValue
            "Account number" -> decryptedLoyaltyPrograms!!.accountNumber = level2Category.titleValue
            "Website" -> decryptedLoyaltyPrograms!!.website = level2Category.titleValue
            "Username/login" -> decryptedLoyaltyPrograms!!.userName = level2Category.titleValue
            "Password" -> decryptedLoyaltyPrograms!!.password = level2Category.titleValue
            "PIN" -> decryptedLoyaltyPrograms!!.pin = level2Category.titleValue
            "Notes" -> decryptedLoyaltyPrograms!!.notes = level2Category.titleValue

            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedLoyaltyPrograms!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedLoyaltyPrograms!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }
    private fun setRecentPurchases(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Item" -> decryptedRecentPurchase!!.itemName = level2Category.titleValue
            "Brand/Retailer" -> decryptedRecentPurchase!!.brandName = level2Category.titleValue
            "Purchased by" -> decryptedRecentPurchase!!.purchasedBy = level2Category.titleValue
            "Purchase date" -> decryptedRecentPurchase!!.purchasedDate = level2Category.titleValue
            "Purchase price ($)" -> decryptedRecentPurchase!!.purchasedPrice = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedRecentPurchase!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedRecentPurchase!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }
    private var mCombine : Parcelable ?= null
    @SuppressLint("StaticFieldLeak")
     fun saveDocument(context: Context,combineItem: Parcelable?,title:String,subTitle:String){
        Log.d("Combine Item", combineItem.toString())
        mCombine = combineItem

        val currentUsers = NineBxApplication.getPreferences().userFirstName + " " + NineBxApplication.getPreferences().userLastName
        val sdf = SimpleDateFormat(" E,MMM dd,yyyy, HH:mm")
        val currentDateandTime = sdf.format(Date())

        if (decryptedLoyaltyPrograms != null) {
            decryptedLoyaltyPrograms!!.selectionType = categoryID
            decryptedLoyaltyPrograms!!.brandName = title
            decryptedLoyaltyPrograms!!.accountName = subTitle
            if( decryptedLoyaltyPrograms!!.created.isEmpty() )
                decryptedLoyaltyPrograms!!.created = currentUsers + " " + currentDateandTime
            decryptedLoyaltyPrograms!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedLoyaltyPrograms!!.id.toInt() == 0) {
                decryptedLoyaltyPrograms!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var loyaltyPrograms = encryptLoyaltyPrograms(decryptedLoyaltyPrograms!!)
                            realm.insertOrUpdate(loyaltyPrograms)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combineShopping: DecryptedCombineShopping = mCombine as DecryptedCombineShopping
                            val index = combineShopping.shoppingItems.indexOf(decryptedShopping)
                            if( index != -1 ) {
                                combineShopping.shoppingItems[index] = decryptedShopping
                            }
                            else combineShopping.shoppingItems.add(decryptedShopping)
                            mCombine = combineShopping
                            var realmLoyaltyPrograms = realm!!.where(CombineShopping::class.java).equalTo("id", combineShopping.id).findFirst()
                            realm.beginTransaction()
                            if (realmLoyaltyPrograms == null) {
                                realmLoyaltyPrograms = realm.createObject(CombineShopping::class.java, getUniqueId())
                            }
                            realmLoyaltyPrograms!!.loyaltyProgramsItems.add(encryptLoyaltyProgram(decryptedLoyaltyPrograms!!))
                            realm.copyToRealmOrUpdate(realmLoyaltyPrograms)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if (decryptedRecentPurchase != null) {
            decryptedRecentPurchase!!.selectionType = categoryID
            decryptedRecentPurchase!!.itemName = title
            if( decryptedRecentPurchase!!.created.isEmpty() )
                decryptedRecentPurchase!!.created = currentUsers + " " + currentDateandTime
            decryptedRecentPurchase!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedRecentPurchase!!.id.toInt() == 0) {
                decryptedRecentPurchase!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var recentPurchase = encryptRecentPurchase(decryptedRecentPurchase!!)
                            realm!!.insertOrUpdate(recentPurchase)
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            val combineShopping: DecryptedCombineShopping = mCombine as DecryptedCombineShopping
                            val index = combineShopping.recentPurchaseItems.indexOf(decryptedRecentPurchase)
                            if( index != -1 ) {
                                combineShopping.recentPurchaseItems[index] = decryptedRecentPurchase
                            }
                            else combineShopping.recentPurchaseItems.add(decryptedRecentPurchase)
                            mCombine = combineShopping
                            var realmRecentPurchase = realm!!.where(CombineShopping::class.java).equalTo("id", combineShopping.id).findFirst()
                            realm.beginTransaction()
                            if (realmRecentPurchase == null) {
                                realmRecentPurchase = realm.createObject(CombineShopping::class.java, getUniqueId())
                            }
                            realmRecentPurchase!!.recentPurchaseItems.add(encryptRecentPurchase(decryptedRecentPurchase!!))
                            realm.copyToRealmOrUpdate(realmRecentPurchase)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if(decryptedClothingSizes != null){
            decryptedClothingSizes!!.selectionType = categoryID
            decryptedClothingSizes!!.personName = title
            if( decryptedClothingSizes!!.created.isEmpty() )
                decryptedClothingSizes!!.created = currentUsers + " " + currentDateandTime

            decryptedClothingSizes!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedClothingSizes!!.id.toInt() == 0) {
                decryptedClothingSizes!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var clothingSize = encryptClothingSizes(decryptedClothingSizes!!)
                            realm!!.insertOrUpdate(clothingSize)
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            val combineShopping: DecryptedCombineShopping = mCombine as DecryptedCombineShopping
                            val index = combineShopping.clothingSizesItems.indexOf(decryptedClothingSizes)
                            if( index != -1 ) {
                                combineShopping.clothingSizesItems[index] = decryptedClothingSizes
                            }
                            else combineShopping.clothingSizesItems.add(decryptedClothingSizes)
                            mCombine = combineShopping
                            var realmClothingSizes = realm!!.where(CombineShopping::class.java).equalTo("id", combineShopping.id).findFirst()
                            realm.beginTransaction()
                            if (realmClothingSizes == null) {
                                realmClothingSizes = realm.createObject(CombineShopping::class.java, getUniqueId())
                            }
                            realmClothingSizes!!.clothingSizesItems.add(encryptClothingSizes(decryptedClothingSizes!!))
                            realm.copyToRealmOrUpdate(realmClothingSizes)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if(decryptedShopping != null){
            decryptedShopping!!.selectionType = categoryID
            decryptedShopping!!.userName = title
            if( decryptedShopping!!.created.isEmpty() )
                decryptedShopping!!.created = currentUsers + " " + currentDateandTime
            decryptedShopping!!.modified = currentUsers + " " + currentDateandTime
            if (decryptedShopping!!.id.toInt() == 0) {
                decryptedShopping!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var shopping = encryptShopping(decryptedShopping!!)
                            realm!!.insertOrUpdate(shopping)
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            object : AsyncTask<Void, Void, Unit>(){
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            val combineShopping: DecryptedCombineShopping = mCombine as DecryptedCombineShopping
                            val index = combineShopping.shoppingItems.indexOf(decryptedShopping)
                            if( index != -1 ) {
                                combineShopping.shoppingItems[index] = decryptedShopping
                            }
                            else combineShopping.shoppingItems.add(decryptedShopping)
                            mCombine = combineShopping
                            var realmShopping = realm!!.where(CombineShopping::class.java).equalTo("id", combineShopping.id).findFirst()
                            realm.beginTransaction()
                            if (realmShopping == null) {
                                realmShopping = realm.createObject(CombineShopping::class.java, getUniqueId())
                            }
                            realmShopping!!.shoppingItems.add(encryptShopping(decryptedShopping!!))
                            realm.copyToRealmOrUpdate(realmShopping)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm( mCombine!! )
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
    }
}