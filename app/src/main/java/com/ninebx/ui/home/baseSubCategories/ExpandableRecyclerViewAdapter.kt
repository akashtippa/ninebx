package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ninebx.NineBxApplication
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.decrypted.DecryptedFinancial
import com.ninebx.ui.base.realm.decrypted.DecryptedMember
import com.ninebx.ui.home.account.interfaces.ICountrySelected
import com.ninebx.utility.Constants
import com.ninebx.utility.DateTimeSelectionListener
import com.ninebx.utility.countryPicker.CountryPicker
import com.ninebx.utility.getDateFromPicker
import com.ninebx.utility.getDateMonthYearFormat
import java.util.*

/**
 * Created by Alok on 14/03/18.
 */
@SuppressLint("ClickableViewAccessibility")
class ExpandableRecyclerViewAdapter( private val _context: Context,
                                     private val categories: ArrayList<Level2SubCategory>,
                                     private val level2CategoryPresenter: Level2CategoryView,
                                     val categoryName: String,
                                     val classType: String,
                                     val membersList : ArrayList<DecryptedMember>,
                                     val isEditMode : Boolean ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        when( viewType ) {

            Constants.LEVEL2_LOCATION -> {
                return LEVEL2_LOCATIONViewHolder(inflater.inflate(R.layout.level2_item_location, parent, false))
            }
            Constants.LEVEL2_PASSWORD -> {
                return LEVEL2_PASSWORDViewHolder(inflater.inflate(R.layout.level2_password, parent, false))
            }
            Constants.LEVEL2_RADIO -> {
                return LEVEL2_RADIOViewHolder(inflater.inflate(R.layout.level2_radio, parent, false))
            }
            Constants.LEVEL2_SPINNER -> {
                return LEVEL2_SPINNERViewHolder(inflater.inflate(R.layout.level2_spinner, parent, false))
            }
            Constants.LEVEL2_SWITCH -> {
                return LEVEL2_SWITCHViewHolder(inflater.inflate(R.layout.level2_switch, parent, false))
            }
            Constants.LEVEL2_USD -> {
                return LEVEL2_USDViewHolder(inflater.inflate(R.layout.level2_usd, parent, false))
            }
            Constants.LEVEL2_NOTES -> {
                return LEVEL2_NOTESViewHolder(inflater.inflate(R.layout.level2_notes, parent, false))
            }
            Constants.LEVEL2_PICKER -> {
                return LEVEL2_PICKERViewHolder(inflater.inflate(R.layout.level2_item_picker, parent, false))
            }
            Constants.LEVEL2_NUMBER -> {
                return LEVEL2_NUMBERViewHolder(inflater.inflate(R.layout.level2_item_number, parent, false))
            }
            Constants.LEVEL2_NORMAL -> {
                return LEVEL2_NORMALViewHolder(inflater.inflate(R.layout.level2_item_normal, parent, false))

            }
            Constants.LEVEL2_ATTACHMENTS -> {
                return LEVEL2_ATTACHMENTSViewHolder(inflater.inflate(R.layout.level2_atachments, parent, false))
            }
            else -> {
                return LEVEL_NORMAL_SPINNERViewHolder(inflater.inflate(R.layout.level2_item_spinner_value, parent, false))
            }
        }

    }

    fun getItemAtPosition( position : Int ) : Level2SubCategory {
        return categories[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        val level2SubCategory = getItemAtPosition(position)
        val headerTitle = level2SubCategory.title
        val keyBoardType = level2SubCategory.inputType
        val titleValue = level2SubCategory.titleValue
        val isValueSet = level2SubCategory.isValueSet

        when( getItemViewType(position) ) {

            Constants.LEVEL2_LOCATION -> {
                val locationViewHolder : LEVEL2_LOCATIONViewHolder = holder as LEVEL2_LOCATIONViewHolder
                locationViewHolder.txtHeader.text = headerTitle
                locationViewHolder.etSubHeader.hint = headerTitle
                locationViewHolder.etSubHeader.setText(titleValue)
                locationViewHolder.etSubHeader.addTextChangedListener( CustomTextWatcher(level2SubCategory) )
            }
            Constants.LEVEL2_PASSWORD -> {
                val locationViewHolder : LEVEL2_PASSWORDViewHolder = holder as LEVEL2_PASSWORDViewHolder
                locationViewHolder.txtHeader.text = headerTitle
                locationViewHolder.etCurrentPassword.hint = headerTitle
                locationViewHolder.etCurrentPassword.setText(titleValue)
                (locationViewHolder.etCurrentPassword).addTextChangedListener( CustomTextWatcher(level2SubCategory) )
            }
            Constants.LEVEL2_RADIO -> {
                val locationViewHolder : LEVEL2_RADIOViewHolder = holder as LEVEL2_RADIOViewHolder
                locationViewHolder.chkLeft.hint = headerTitle
                locationViewHolder.chkRight.hint = titleValue

                if( titleValue.equals("purchased", true) ) {
                    locationViewHolder.chkLeft.text = "Purchased"
                    locationViewHolder.chkRight.text = "Leased"
                    locationViewHolder.chkLeft.isChecked = true
                    locationViewHolder.chkRight.isChecked = false
                }
                else {
                    locationViewHolder.chkLeft.text = "Purchased"
                    locationViewHolder.chkRight.text = "Leased"
                    locationViewHolder.chkLeft.isChecked = false
                    locationViewHolder.chkRight.isChecked = true
                }

            }
            Constants.LEVEL2_SPINNER -> {
                val locationViewHolder : LEVEL2_SPINNERViewHolder = holder as LEVEL2_SPINNERViewHolder
                locationViewHolder.txtHeader.text = headerTitle
                locationViewHolder.etSubHeader.hint = headerTitle
                locationViewHolder.etSubHeader.setText(titleValue)
                locationViewHolder.etSubHeader.addTextChangedListener( CustomTextWatcher(level2SubCategory) )
                if (keyBoardType == Constants.CONTACT_SPINNER) {
                    openContactList()
                } else {
                    val arrayAdapter = ArrayAdapter(_context, R.layout.txt_usd, membersList)
                    (locationViewHolder.spinnerUsers).adapter = arrayAdapter
                    (locationViewHolder.spinnerUsers).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {

                        }

                        override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                            val newValue =  locationViewHolder.spinnerUsers.getItemAtPosition(position) as String
                            locationViewHolder.etSubHeader.setText(newValue)

                        }
                    }
                }

            }
            Constants.LEVEL2_SWITCH -> {
                val locationViewHolder : LEVEL2_SWITCHViewHolder = holder as LEVEL2_SWITCHViewHolder
                locationViewHolder.txtHeader.text = headerTitle
                locationViewHolder.switchView.isChecked = isValueSet

            }
            Constants.LEVEL2_USD -> {
                val locationViewHolder : LEVEL2_USDViewHolder = holder as LEVEL2_USDViewHolder

                locationViewHolder.txtHeader.text = headerTitle
                locationViewHolder.etSubHeader.hint = headerTitle
                locationViewHolder.etSubHeader.setText(titleValue)
                locationViewHolder.etSubHeader.addTextChangedListener( CustomTextWatcher(level2SubCategory) )
                val arrayAdapter = ArrayAdapter(_context, R.layout.txt_usd, currencyType)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                locationViewHolder.spinnerCurrency.adapter = arrayAdapter
                locationViewHolder.spinnerCurrency.setSelection(currencyType.indexOf(titleValue))
                locationViewHolder.spinnerCurrency.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, currencyType )
            }
            Constants.LEVEL2_NOTES -> {
                val locationViewHolder : LEVEL2_NOTESViewHolder = holder as LEVEL2_NOTESViewHolder
                if (headerTitle.equals("")) {
                    locationViewHolder.edtNotes.hint = "Notes"
                } else {
                    locationViewHolder.edtNotes.hint = headerTitle
                }
                locationViewHolder.edtNotes.setText(titleValue)
                locationViewHolder.edtNotes.addTextChangedListener( CustomTextWatcher(level2SubCategory) )
            }
            Constants.LEVEL2_PICKER -> {
                val locationViewHolder : LEVEL2_PICKERViewHolder = holder as LEVEL2_PICKERViewHolder


                locationViewHolder.txtHeader.text = headerTitle
                locationViewHolder.etSubHeader.hint = headerTitle
                locationViewHolder.etSubHeader.setText(  titleValue)
                locationViewHolder.etSubHeader.setOnClickListener {
                    getDateFromPicker(_context, Calendar.getInstance(), object : DateTimeSelectionListener {
                        override fun onDateTimeSelected(selectedDate: Calendar) {
                            locationViewHolder.etSubHeader.text = getDateMonthYearFormat(selectedDate.time)
                        }
                    })
                }
            }
            Constants.LEVEL2_NUMBER -> {
                val locationViewHolder : LEVEL2_NUMBERViewHolder = holder as LEVEL2_NUMBERViewHolder

                locationViewHolder.txtHeader.text = headerTitle
                locationViewHolder.etSubHeader.hint = headerTitle
                locationViewHolder.etSubHeader.text = titleValue
                locationViewHolder.etSubHeader.addTextChangedListener( CustomTextWatcher(level2SubCategory) )
                locationViewHolder.etSubHeader.inputType = InputType.TYPE_CLASS_NUMBER

            }
            Constants.LEVEL2_NORMAL -> {
                val locationViewHolder : LEVEL2_NORMALViewHolder = holder as LEVEL2_NORMALViewHolder
                locationViewHolder.txtHeader.text = headerTitle
                locationViewHolder.etSubHeader.hint = headerTitle
                locationViewHolder.etSubHeader.setText(titleValue)
                locationViewHolder.etSubHeader.addTextChangedListener( CustomTextWatcher(level2SubCategory) )
                if (keyBoardType == Constants.KEYBOARD_NUMBER) {
                    locationViewHolder.etSubHeader.inputType = InputType.TYPE_CLASS_NUMBER
                } else if (keyBoardType == Constants.KEYBOARD_SPINNER) {
                    locationViewHolder.etSubHeader.hide()
                    locationViewHolder.spinnerAccountType.show()

                    val spinnerItems = when (classType) {
                        DecryptedFinancial::class.java.simpleName -> {
                            accountType
                        }
                        else -> {
                            womenTopsNumericSizes
                        }
                    }
                    val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, spinnerItems)
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    locationViewHolder.spinnerAccountType.adapter = arrayAdapter
                    locationViewHolder.spinnerAccountType.setSelection(spinnerItems.indexOf(titleValue))
                    locationViewHolder.spinnerAccountType.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, spinnerItems )
                } else if (keyBoardType == Constants.KEYBOARD_PICKER) {
                    getDateFromPicker(_context, Calendar.getInstance(), object : DateTimeSelectionListener {
                        override fun onDateTimeSelected(selectedDate: Calendar) {
                            (locationViewHolder.etSubHeader).setText(getDateMonthYearFormat(selectedDate.time))
                        }
                    })
                }

            }
            Constants.LEVEL2_ATTACHMENTS -> {
                val locationViewHolder : LEVEL2_ATTACHMENTSViewHolder = holder as LEVEL2_ATTACHMENTSViewHolder

            }
            Constants.LEVEL_NORMAL_SPINNER -> {
                val locationViewHolder : LEVEL_NORMAL_SPINNERViewHolder = holder as LEVEL_NORMAL_SPINNERViewHolder
                locationViewHolder.txtHeader.text = headerTitle



                when (keyBoardType) {
                // Women Shopping Category

                    Constants.PICKER_WOMEN_NUMERIC_SIZE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenTopsNumericSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(womenTopsNumericSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenTopsNumericSizes )
                    }
                    Constants.PICKER_WOMEN_SIZE_US -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenTopSize)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(womenTopSize.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenTopSize )
                    }
                    Constants.PICKER_WOMENS_DETAILS_SIZE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, sizeCategoryArray)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(sizeCategoryArray.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, sizeCategoryArray )
                    }

                    Constants.PICKER_WOMEN_SHOES -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenShoeSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(womenShoeSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenShoeSizes )
                    }
                    Constants.PICKER_WOMEN_SHOES_WIDTH -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenShoeWidthSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(womenShoeWidthSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenShoeWidthSizes )
                    }

                    Constants.PICKER_WOMEN_ACCESSORIES_BELTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenAccessoriesBelts)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(womenAccessoriesBelts.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenAccessoriesBelts )
                    }
                    Constants.PICKER_WOMEN_ACCESSORIES_HATS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenAccessoriesHats)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(womenAccessoriesHats.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenAccessoriesHats )
                    }
                    Constants.PICKER_WOMEN_ACCESSORIES_GLOVES -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenAccessoriesGloves)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(womenAccessoriesGloves.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenAccessoriesGloves )
                    }
                    Constants.PICKER_WOMEN_ACCESSORIES_TIGHTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenAccessoriesTights)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(womenAccessoriesTights.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenAccessoriesTights )
                    }

                // Men's Shopping Category
                    Constants.PICKER_MENS_SIZE_CATEGORY_US -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menSizeCategories)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menSizeCategories.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menSizeCategories )
                    }
                    Constants.PICKER_MENS_SIZE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menTopsSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menTopsSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menTopsSizes )
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_TOPS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menTopsNumericSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menTopsNumericSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menTopsNumericSizes )
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_BOTTOMS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menBottomsNumericSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menBottomsNumericSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menBottomsNumericSizes )
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_JACKETS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menJacketsNumericSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menJacketsNumericSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menJacketsNumericSizes )
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_PANTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menBottomsNumericSizesSuiting)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menBottomsNumericSizesSuiting.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menBottomsNumericSizesSuiting )
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_OUTERWEAR -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menTopsNumericSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menTopsNumericSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menTopsNumericSizes )
                    }
                    Constants.PICKER_MENS_SHOES -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menShoeSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menShoeSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menShoeSizes )
                    }
                    Constants.PICKER_MENS_SHOES_WIDTH -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menShoeWidthSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menShoeWidthSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menShoeWidthSizes )
                    }
                    Constants.PICKER_MENS_BELTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menBelts)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menBelts.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menBelts )
                    }
                    Constants.PICKER_MENS_BELTS_NUMERIC_SIZE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menBottomsNumericBeltsSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menBottomsNumericBeltsSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menBottomsNumericBeltsSizes )
                    }
                    Constants.PICKER_MENS_TIGHTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menHats)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menHats.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menHats )
                    }
                    Constants.PICKER_MENS_GLOVES -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menGloves)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(menGloves.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menGloves )
                    }


                // Girls and Boys Shopping Category

                    Constants.PICKER_GIRLS_NUMERIC_SIZE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsNumericSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(girlsNumericSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsNumericSizes )
                    }
                    Constants.PICKER_GIRLS_SHOES_TODDLER -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsShoeSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(girlsShoeSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsShoeSizes )
                    }
                    Constants.PICKER_GIRLS_SHOES_LITTLE_AND_BIG_KID -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsShoesLittleAndBigKidSize)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(girlsShoesLittleAndBigKidSize.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsShoesLittleAndBigKidSize )
                    }
                    Constants.PICKER_GIRLS_SHOES_WIDTH -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsShoeWidthSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(girlsShoeWidthSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsShoeWidthSizes )
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_BELTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsBeltSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(girlsBeltSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsBeltSizes )
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_BELTS_NUMERIC_SIZE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsNumericBeltsSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(girlsNumericBeltsSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsNumericBeltsSizes )
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_HATS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsHatSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(girlsHatSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsHatSizes )
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_GLOVES -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsGlovesSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(girlsGlovesSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsGlovesSizes )
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_TIGHTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsTightsSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(girlsTightsSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsTightsSizes )
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_SOCKS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsSocksSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(girlsSocksSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsSocksSizes )
                    }

                // Baby Shopping Category
                    Constants.PICKER_BABY_CLOTHING -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, babyClothings)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(babyClothings.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, babyClothings )
                    }
                    Constants.PICKER_BABY_SHOES -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, babyShoeSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(babyShoeSizes.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, babyShoeSizes )
                    }

                    Constants.BANK_ACCOUNT_TYPE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, accountType)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(accountType.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, accountType )
                    }
                    Constants.OTHER_ACCOUNT_TYPE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, othersAccountTypeOptions)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(othersAccountTypeOptions.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, othersAccountTypeOptions )
                    }
                    Constants.CARD_TYPE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, cardType)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        locationViewHolder.spinnerItem.adapter = arrayAdapter
                        locationViewHolder.spinnerItem.setSelection(cardType.indexOf(titleValue))
                        locationViewHolder.spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, cardType )
                    }


                }

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return categories[position].type
    }

    override fun getItemCount(): Int {
        return categories.size
    }
    @SuppressLint("ClickableViewAccessibility")
    inner class LEVEL2_LOCATIONViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val etSubHeader = itemView.findViewById<EditText>(R.id.etSubHeader)

        init {

            etSubHeader.setOnTouchListener(View.OnTouchListener { _, event ->
                val DRAWABLE_RIGHT = 2

                if (event.action == MotionEvent.ACTION_UP) {
                    if (event.rawX >= etSubHeader.right - etSubHeader.compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                        openStaticLayoutDialog(etSubHeader)

                        return@OnTouchListener true
                    }
                }
                false
            })
            txtHeader.isEnabled = isEditMode
            etSubHeader.isEnabled = isEditMode

        }
    } // 10
    inner class LEVEL2_PASSWORDViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val etCurrentPassword = itemView.findViewById<EditText>(R.id.etCurrentPassword)

        init {
            txtHeader.isEnabled = isEditMode
            etCurrentPassword.isEnabled = isEditMode
            // childView = level2PasswordView
        }
    } // 11
    inner class LEVEL2_RADIOViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {

        val chkLeft = itemView.findViewById<CheckBox>(R.id.chkLeft)
        val chkRight = itemView.findViewById<CheckBox>(R.id.chkRight)

        init {

            chkLeft.isEnabled = isEditMode
            chkRight.isEnabled = isEditMode

            // childView = level2RadioView
        }
    } // 12
    inner class LEVEL2_SPINNERViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {


        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val etSubHeader = itemView.findViewById<EditText>(R.id.etSubHeader)
        val spinnerUsers = itemView.findViewById<Spinner>(R.id.spinnerUsers)
        init {

            txtHeader.isEnabled = isEditMode
            etSubHeader.isEnabled = isEditMode
            spinnerUsers.isEnabled = isEditMode



        }
    } // 13
    inner class LEVEL2_SWITCHViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val switchView = itemView.findViewById<Switch>(R.id.switchView)
        
        init {

            txtHeader.isEnabled = isEditMode
            switchView.isEnabled = isEditMode

          
            // childView = level2SwitchView
        }
    } // 14
    inner class LEVEL2_USDViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val etSubHeader = itemView.findViewById<EditText>(R.id.etSubHeader)
        val spinnerCurrency: Spinner = itemView.findViewById<Spinner>(R.id.spinnerCurrency)
        init {

            txtHeader.isEnabled = isEditMode
            etSubHeader.isEnabled = isEditMode
            spinnerCurrency.isEnabled = isEditMode

            // childView = level2USDView
        }
    } // 15
    inner class LEVEL2_NOTESViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {
        val edtNotes = itemView.findViewById<EditText>(R.id.edtNotes)
        init {

            edtNotes.isEnabled = isEditMode
           
            // childView = level2NotesView
        }
    } // 16
    inner class LEVEL2_NORMALViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val etSubHeader = itemView.findViewById<EditText>(R.id.etSubHeader)
        val spinnerAccountType = itemView.findViewById<Spinner>(R.id.spinnerAccountType)
        
        init {

            txtHeader.isEnabled = isEditMode
            etSubHeader.isEnabled = isEditMode
            spinnerAccountType.isEnabled = isEditMode
           
            // childView = level2NormalView
        }
    } // 17
    inner class LEVEL2_ATTACHMENTSViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {
        val etAttachment : EditText = itemView.findViewById(R.id.etAttachment)
        init {
            etAttachment.isEnabled = isEditMode
            itemView.isEnabled = isEditMode
            // childView = level2AttachmentsView
        }
    } // 18
    inner class LEVEL2_PICKERViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val etSubHeader = itemView.findViewById<TextView>(R.id.etSubHeader)
        
        init {

            txtHeader.isEnabled = isEditMode
            etSubHeader.isEnabled = isEditMode
            // childView = level2PickerView
        }
    } // 19
    inner class LEVEL2_NUMBERViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val etSubHeader = itemView.findViewById<TextView>(R.id.etSubHeader)
        
        init {


            txtHeader.isEnabled = isEditMode
            etSubHeader.isEnabled = isEditMode

            // childView = level2NumberView
        }
    } // 20
    inner class LEVEL_NORMAL_SPINNERViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val spinnerItem: Spinner = itemView.findViewById<Spinner>(R.id.spinnerValue)
        init {


            txtHeader.isEnabled = isEditMode
            spinnerItem.isEnabled = isEditMode
        

            // childView = level2NormalSpinnerView
        }
    }

    inner class CustomTextWatcher( val level2SubCategory: Level2SubCategory ) : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            val valueString = p0.toString()
            level2SubCategory.titleValue = valueString
            level2CategoryPresenter.setValueToDocument(level2SubCategory)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    }

    inner class CustomItemSelectedListener( val level2SubCategory: Level2SubCategory, val selectionArray : Array<String> ) : AdapterView.OnItemSelectedListener {

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            level2SubCategory.titleValue = selectionArray[p2]
            level2CategoryPresenter.setValueToDocument(level2SubCategory)
        }
    }

    private fun openStaticLayoutDialog( locationText: EditText) {
        val fragmentTransaction = NineBxApplication.instance.activityInstance!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        val countryPicker = CountryPicker()
        countryPicker.setCountrySelectionListener( object : ICountrySelected {
            override fun onCountrySelected(strCountry: String?) {
                locationText.setText( strCountry )
            }

        })
        fragmentTransaction.replace(R.id.frameLayout, countryPicker).commit()
    }

    private fun openContactList() {
    }
}