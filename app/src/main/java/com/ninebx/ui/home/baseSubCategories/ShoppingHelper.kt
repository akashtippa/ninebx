package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Parcelable
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
        if (com.ninebx.utility.decryptedClothingSizes == null) com.ninebx.utility.decryptedClothingSizes = DecryptedClothingSizes()

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
        if (com.ninebx.utility.decryptedClothingSizes == null) com.ninebx.utility.decryptedClothingSizes = DecryptedClothingSizes()

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
        if (com.ninebx.utility.decryptedClothingSizes == null) com.ninebx.utility.decryptedClothingSizes = DecryptedClothingSizes()

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
        if (com.ninebx.utility.decryptedClothingSizes == null) com.ninebx.utility.decryptedClothingSizes = DecryptedClothingSizes()

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
        if (com.ninebx.utility.decryptedClothingSizes == null) com.ninebx.utility.decryptedClothingSizes = DecryptedClothingSizes()

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
        category.subCategories.add(Level2SubCategory("Purchased by", "Purchased by", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Purchased date", "Purchased date", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Purchased price", "Purchased price", "", Constants.LEVEL2_USD))
        categoryList.add(category)

        categoryIndex += 2034
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
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
        category.subCategories.add(Level2SubCategory("Name(s) on account", "Name(s) on account", "", Constants.LEVEL2_SPINNER))
        category.subCategories.add(Level2SubCategory("Account number", "Account number", "", Constants.LEVEL2_NORMAL))
        categoryList.add(category)

        categoryIndex += 2033
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Online Access"
        category.subCategories.add(Level2SubCategory("Website", "Website", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Username/login", "Username/login", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Password", "Password", "", Constants.LEVEL2_PASSWORD))
        category.subCategories.add(Level2SubCategory("PIN", "PIN", "", Constants.LEVEL2_PASSWORD))
        categoryList.add(category)

        categoryIndex += 2033
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
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
            "Name of person" -> com.ninebx.utility.decryptedClothingSizes!!.personName = level2Category.titleValue
            "Size name" -> com.ninebx.utility.decryptedClothingSizes!!.sizeName = level2Category.titleValue
            "detailsSizeCategory" -> com.ninebx.utility.decryptedClothingSizes!!.sizeCategory = level2Category.titleValue
            "topsSize" -> com.ninebx.utility.decryptedClothingSizes!!.topsSize = level2Category.titleValue
            "topsNumericSize" -> com.ninebx.utility.decryptedClothingSizes!!.topsNumericSize = level2Category.titleValue
            "bottomsSize" -> com.ninebx.utility.decryptedClothingSizes!!.bottomsSize = level2Category.titleValue
            "bottomsNumericSize" -> com.ninebx.utility.decryptedClothingSizes!!.bottomsNumericSize = level2Category.titleValue
            "dressesSize" -> com.ninebx.utility.decryptedClothingSizes!!.dressesSize = level2Category.titleValue
            "dressesNumericSize" -> com.ninebx.utility.decryptedClothingSizes!!.dressesNumericSize = level2Category.titleValue
            "outWearSize" -> com.ninebx.utility.decryptedClothingSizes!!.outWearSize = level2Category.titleValue
            "outWearNumericSize" -> com.ninebx.utility.decryptedClothingSizes!!.outWearNumericSize = level2Category.titleValue
            "swimWearSize" -> com.ninebx.utility.decryptedClothingSizes!!.swimWearSize = level2Category.titleValue
            "swimWearNumericSize" -> com.ninebx.utility.decryptedClothingSizes!!.swimWearNumericSize = level2Category.titleValue
            "swimWearbraBandSize" -> com.ninebx.utility.decryptedClothingSizes!!.swimWearBraBandCupSize = level2Category.titleValue
            "shoeSize" -> com.ninebx.utility.decryptedClothingSizes!!.shoeSize = level2Category.titleValue
            "shoeWidth" -> com.ninebx.utility.decryptedClothingSizes!!.shoeWidth = level2Category.titleValue

            "Belts" -> com.ninebx.utility.decryptedClothingSizes!!.beltSize = level2Category.titleValue
            "Waist (in)" -> com.ninebx.utility.decryptedClothingSizes!!.waist = level2Category.titleValue
            "Hats" -> com.ninebx.utility.decryptedClothingSizes!!.hats = level2Category.titleValue
            "Gloves" -> com.ninebx.utility.decryptedClothingSizes!!.gloves = level2Category.titleValue
            "Tights" -> com.ninebx.utility.decryptedClothingSizes!!.tights = level2Category.titleValue
            "Bust (in)" -> com.ninebx.utility.decryptedClothingSizes!!.bust = level2Category.titleValue
            "Hips (in)" -> com.ninebx.utility.decryptedClothingSizes!!.hips = level2Category.titleValue
            "Socks" -> com.ninebx.utility.decryptedClothingSizes!!.socks = level2Category.titleValue
            "Seat (in)" -> com.ninebx.utility.decryptedClothingSizes!!.seat = level2Category.titleValue

            "Arm length (in)" -> com.ninebx.utility.decryptedClothingSizes!!.armLength = level2Category.titleValue
            "Inseam (in)" -> com.ninebx.utility.decryptedClothingSizes!!.inseam = level2Category.titleValue
            "Torso (in)" -> com.ninebx.utility.decryptedClothingSizes!!.torso = level2Category.titleValue

            "Notes" -> com.ninebx.utility.decryptedClothingSizes!!.notes = level2Category.titleValue

            "clothing" -> com.ninebx.utility.decryptedClothingSizes!!.clothing = level2Category.titleValue
            "shoes" -> com.ninebx.utility.decryptedClothingSizes!!.shoes = level2Category.titleValue

            "jacketsNumericSize" -> com.ninebx.utility.decryptedClothingSizes!!.jacketsNumericSize = level2Category.titleValue
            "toddlerSize" -> com.ninebx.utility.decryptedClothingSizes!!.toddlerSize = level2Category.titleValue
            "kidsize" -> com.ninebx.utility.decryptedClothingSizes!!.kidSize = level2Category.titleValue

            "beltsSize" -> com.ninebx.utility.decryptedClothingSizes!!.beltSize = level2Category.titleValue
            "beltsNumericSize" -> com.ninebx.utility.decryptedClothingSizes!!.beltsNumericSize = level2Category.titleValue

            "Neck (in)" -> com.ninebx.utility.decryptedClothingSizes!!.neck = level2Category.titleValue
            "Chest (in)" -> com.ninebx.utility.decryptedClothingSizes!!.chest = level2Category.titleValue

            "beltsNumericSize" -> com.ninebx.utility.decryptedClothingSizes!!.beltsNumericSize = level2Category.titleValue

            "pantsSize" -> com.ninebx.utility.decryptedClothingSizes!!.pantsSize = level2Category.titleValue
            "pantsNumericSize" -> com.ninebx.utility.decryptedClothingSizes!!.pantsNumericSize = level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> com.ninebx.utility.decryptedClothingSizes!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> com.ninebx.utility.decryptedClothingSizes!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }
    private fun setLoyaltyPrograms(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Brand/Retailer" -> decryptedLoyaltyPrograms!!.brandName = level2Category.titleValue
            "Account name" -> decryptedLoyaltyPrograms!!.accountName = level2Category.titleValue
            "Name on account" -> decryptedLoyaltyPrograms!!.nameOnAccount = level2Category.titleValue
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
        mCombine = combineItem
        val currentUsers = NineBxApplication.getPreferences().userFirstName + " " + NineBxApplication.getPreferences().userLastName
        val sdf = SimpleDateFormat(" E,MMM dd,yyyy, HH:mm")
        val currentDateandTime = sdf.format(Date())

        if (decryptedLoyaltyPrograms != null) {
            decryptedLoyaltyPrograms!!.selectionType = categoryID
            decryptedLoyaltyPrograms!!.brandName = title
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
        if(com.ninebx.utility.decryptedClothingSizes != null){
            com.ninebx.utility.decryptedClothingSizes!!.selectionType = categoryID
            com.ninebx.utility.decryptedClothingSizes!!.personName = title
            if( com.ninebx.utility.decryptedClothingSizes!!.created.isEmpty() )
                com.ninebx.utility.decryptedClothingSizes!!.created = currentUsers + " " + currentDateandTime

            com.ninebx.utility.decryptedClothingSizes!!.modified = currentUsers + " " + currentDateandTime
            if (com.ninebx.utility.decryptedClothingSizes!!.id.toInt() == 0) {
                com.ninebx.utility.decryptedClothingSizes!!.id = getUniqueId()
            }
            var isSaveComplete = false
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE_SHOPPING, object : Realm.Callback(){
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            var clothingSize = encryptClothingSizes(com.ninebx.utility.decryptedClothingSizes!!)
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
                            var realmClothingSizes = realm!!.where(CombineShopping::class.java).equalTo("id", combineShopping.id).findFirst()
                            realm.beginTransaction()
                            if (realmClothingSizes == null) {
                                realmClothingSizes = realm.createObject(CombineShopping::class.java, getUniqueId())
                            }
                            realmClothingSizes!!.clothingSizesItems.add(encryptClothingSizes(com.ninebx.utility.decryptedClothingSizes!!))
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