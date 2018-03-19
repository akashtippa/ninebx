package com.ninebx.ui.home.baseSubCategories


import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.*
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.decrypted.*
import com.ninebx.ui.home.account.interfaces.ICountrySelected
import com.ninebx.utility.Constants
import com.ninebx.utility.DateTimeSelectionListener
import com.ninebx.utility.countryPicker.CountryPickerDialog
import com.ninebx.utility.getDateFromPicker
import com.ninebx.utility.getDateMonthYearFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/***
 * Created by TechnoBlogger on 23/01/18.
 */

class ExpandableListViewAdapter(private val _context: Context,
                                private val categories: ArrayList<Level2Category>,
                                private val level2CategoryPresenter: Level2CategoryView,
                                val categoryName: String,
                                val classType: String,
                                val membersList : ArrayList<DecryptedMember> ) : BaseExpandableListAdapter() {

    private val infalInflater = this._context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return categories[groupPosition].subCategories[childPosititon]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    private var level2LocationView: View? = null
    private var level2PasswordView: View? = null
    private var level2RadioView: View? = null
    private var level2SpinnerView: View? = null
    private var level2SwitchView: View? = null
    private var level2USDView: View? = null
    private var level2NotesView: View? = null
    private var level2PickerView: View? = null
    private var level2NumberView: View? = null
    private var level2NormalView: View? = null
    private var level2AttachmentsView: View? = null
    private var level2NormalSpinnerView: View? = null

    private var viewMap : HashMap<Int, View> = HashMap()

    private var groupView : View ?= null

    @SuppressLint("ClickableViewAccessibility")
    override fun getChildView(groupPosition: Int, childPosition: Int,
                              isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {

        var childView = convertView

        val level2SubCategory = (getChild(groupPosition, childPosition) as Level2SubCategory)
        val headerTitle = level2SubCategory.title
        val keyBoardType = level2SubCategory.inputType
        val titleValue = level2SubCategory.titleValue
        val isValueSet = level2SubCategory.isValueSet

        when (getItemType(groupPosition, childPosition)) {

            Constants.LEVEL2_LOCATION -> {
                
                if( !viewMap.containsKey(R.layout.level2_item_location) ) {
                    level2LocationView = infalInflater.inflate(R.layout.level2_item_location, parent, false)
                    viewMap.put(R.layout.level2_item_location, level2LocationView!!)
                }
                else level2LocationView = viewMap.get(R.layout.level2_item_location)

                
                level2LocationView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                level2LocationView!!.findViewById<EditText>(R.id.etSubHeader).hint = headerTitle
                level2LocationView!!.findViewById<EditText>(R.id.etSubHeader).setText(titleValue)
                level2LocationView!!.findViewById<EditText>(R.id.etSubHeader).setOnTouchListener(OnTouchListener { _, event ->
                    val DRAWABLE_RIGHT = 2

                    if (event.action == MotionEvent.ACTION_UP) {
                        if (event.rawX >= level2LocationView!!.findViewById<EditText>(R.id.etSubHeader).right - level2LocationView!!.findViewById<EditText>(R.id.etSubHeader).compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                            openStaticLayoutDialog(level2LocationView!!.findViewById<EditText>(R.id.etSubHeader))

                            return@OnTouchListener true
                        }
                    }
                    false
                })
                level2LocationView!!.findViewById<EditText>(R.id.etSubHeader).addTextChangedListener( CustomTextWatcher(level2SubCategory) )
                childView = level2LocationView

            }
            Constants.LEVEL2_PASSWORD -> {
                if( !viewMap.containsKey(R.layout.level2_password) ) {
                    level2PasswordView = infalInflater.inflate(R.layout.level2_password, parent, false)
                    viewMap.put(R.layout.level2_password, level2PasswordView!!)
                }
                else level2PasswordView = viewMap.get(R.layout.level2_password)

                
                level2PasswordView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                level2PasswordView!!.findViewById<EditText>(R.id.etCurrentPassword).hint = headerTitle
                level2PasswordView!!.findViewById<EditText>(R.id.etCurrentPassword).setText(titleValue)
                level2PasswordView!!.findViewById<EditText>(R.id.etCurrentPassword).addTextChangedListener( CustomTextWatcher(level2SubCategory) )
                childView = level2PasswordView
            }
            Constants.LEVEL2_RADIO -> {
                if( !viewMap.containsKey(R.layout.level2_radio) ) {
                    level2RadioView = infalInflater.inflate(R.layout.level2_radio, parent, false)
                    viewMap.put(R.layout.level2_radio, level2RadioView!!)
                }
                else level2RadioView = viewMap.get(R.layout.level2_radio)


                level2RadioView!!.findViewById<CheckBox>(R.id.chkLeft).hint = headerTitle
                level2RadioView!!.findViewById<CheckBox>(R.id.chkRight).hint = titleValue

                if( titleValue.equals("purchased", true) ) {
                    level2RadioView!!.findViewById<CheckBox>(R.id.chkLeft).text = "Purchased"
                    level2RadioView!!.findViewById<CheckBox>(R.id.chkRight).text = "Leased"
                    level2RadioView!!.findViewById<CheckBox>(R.id.chkLeft).isChecked = true
                    level2RadioView!!.findViewById<CheckBox>(R.id.chkRight).isChecked = false
                }
                else {
                    level2RadioView!!.findViewById<CheckBox>(R.id.chkLeft).text = "Purchased"
                    level2RadioView!!.findViewById<CheckBox>(R.id.chkRight).text = "Leased"
                    level2RadioView!!.findViewById<CheckBox>(R.id.chkLeft).isChecked = false
                    level2RadioView!!.findViewById<CheckBox>(R.id.chkRight).isChecked = true
                }
                childView = level2RadioView

            }
            Constants.LEVEL2_SPINNER -> {
                if( !viewMap.containsKey(R.layout.level2_spinner) ) {
                    level2SpinnerView = infalInflater.inflate(R.layout.level2_spinner, parent, false)
                    viewMap.put(R.layout.level2_spinner, level2SpinnerView!!)
                }
                else level2SpinnerView = viewMap.get(R.layout.level2_spinner)


                level2SpinnerView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                level2SpinnerView!!.findViewById<EditText>(R.id.etSubHeader).hint = headerTitle
                level2SpinnerView!!.findViewById<EditText>(R.id.etSubHeader).setText(titleValue)
                level2SpinnerView!!.findViewById<EditText>(R.id.etSubHeader).addTextChangedListener( CustomTextWatcher(level2SubCategory) )
                if (keyBoardType == Constants.CONTACT_SPINNER) {
                    openContactList()
                } else {
                    //TODO -
                    val arrayAdapter = ArrayAdapter(_context, R.layout.txt_usd, membersList)
                    level2SpinnerView!!.findViewById<Spinner>(R.id.spinnerUsers).adapter = arrayAdapter
                    level2SpinnerView!!.findViewById<Spinner>(R.id.spinnerUsers).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {

                        }

                        override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                            val newValue = childView!!.findViewById<Spinner>(R.id.spinnerUsers).getItemAtPosition(position) as String
                            level2SpinnerView!!.findViewById<EditText>(R.id.etSubHeader).setText(newValue)

                        }
                    }
                }
                level2SpinnerView = childView

            }
            Constants.LEVEL2_SWITCH -> {
                if( !viewMap.containsKey(R.layout.level2_switch) ) {
                    level2SwitchView = infalInflater.inflate(R.layout.level2_switch, parent, false)
                    viewMap.put(R.layout.level2_switch, level2SwitchView!!)
                }
                else level2SwitchView = viewMap.get(R.layout.level2_switch)


                level2SwitchView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                level2SwitchView!!.findViewById<Switch>(R.id.switchView).isChecked = isValueSet
                childView = level2SwitchView

            }
            Constants.LEVEL2_USD -> {
                
                if( !viewMap.containsKey(R.layout.level2_usd) ) {
                    level2USDView = infalInflater.inflate(R.layout.level2_usd, parent, false)
                    viewMap.put(R.layout.level2_usd, level2USDView!!)
                }
                else level2USDView = viewMap.get(R.layout.level2_usd)


                level2USDView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                level2USDView!!.findViewById<EditText>(R.id.etSubHeader).hint = headerTitle
                level2USDView!!.findViewById<EditText>(R.id.etSubHeader).setText(titleValue)
                level2USDView!!.findViewById<EditText>(R.id.etSubHeader).addTextChangedListener( CustomTextWatcher(level2SubCategory) )
                val spinnerCurrency: Spinner = level2USDView!!.findViewById<View>(R.id.spinnerCurrency) as Spinner

                val arrayAdapter = ArrayAdapter(_context, R.layout.txt_usd, currencyType)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCurrency.adapter = arrayAdapter
                spinnerCurrency.setSelection(currencyType.indexOf(titleValue))
                spinnerCurrency.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, currencyType )
                
                childView = level2USDView
            }
            Constants.LEVEL2_NOTES -> {
                if( !viewMap.containsKey(R.layout.level2_notes) ) {
                    level2NotesView = infalInflater.inflate(R.layout.level2_notes, parent, false)
                    viewMap.put(R.layout.level2_notes, level2NotesView!!)
                }
                else level2NotesView = viewMap.get(R.layout.level2_notes)


                if (headerTitle.equals("")) {
                    level2NotesView!!.findViewById<EditText>(R.id.edtNotes).hint = "Notes"
                } else {
                    level2NotesView!!.findViewById<EditText>(R.id.edtNotes).hint = headerTitle
                }
                level2NotesView!!.findViewById<EditText>(R.id.edtNotes).setText(titleValue)
                level2NotesView!!.findViewById<EditText>(R.id.edtNotes).addTextChangedListener( CustomTextWatcher(level2SubCategory) )
                childView = level2NotesView
            }
            Constants.LEVEL2_PICKER -> {
                if( !viewMap.containsKey(R.layout.level2_item_picker) ) {
                    level2PickerView = infalInflater.inflate(R.layout.level2_item_picker, parent, false)
                    viewMap.put(R.layout.level2_item_picker, level2PickerView!!)
                }
                else level2PickerView = viewMap.get(R.layout.level2_item_picker)


                level2PickerView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                level2PickerView!!.findViewById<TextView>(R.id.etSubHeader).hint = headerTitle
                level2PickerView!!.findViewById<TextView>(R.id.etSubHeader).text = titleValue
                level2PickerView!!.findViewById<TextView>(R.id.etSubHeader).setOnClickListener {
                    getDateFromPicker(_context, Calendar.getInstance(), object : DateTimeSelectionListener {
                        override fun onDateTimeSelected(selectedDate: Calendar) {
                            level2PickerView!!.findViewById<TextView>(R.id.etSubHeader).text = getDateMonthYearFormat(selectedDate.time)
                        }
                    })
                }
                childView = level2PickerView
            }
            Constants.LEVEL2_NUMBER -> {
                if( !viewMap.containsKey(R.layout.level2_item_number) ) {
                    level2NumberView = infalInflater.inflate(R.layout.level2_item_number, parent, false)
                    viewMap.put(R.layout.level2_item_number, level2NumberView!!)
                }
                else level2NumberView = viewMap.get(R.layout.level2_item_number)


                level2NumberView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                level2NumberView!!.findViewById<TextView>(R.id.etSubHeader).hint = headerTitle
                level2NumberView!!.findViewById<TextView>(R.id.etSubHeader).text = titleValue
                level2NumberView!!.findViewById<EditText>(R.id.etSubHeader).addTextChangedListener( CustomTextWatcher(level2SubCategory) )
                level2NumberView!!.findViewById<EditText>(R.id.etSubHeader).inputType = InputType.TYPE_CLASS_NUMBER
                
                childView = level2NumberView
            }
            Constants.LEVEL2_NORMAL -> {
                if( !viewMap.containsKey(R.layout.level2_item_normal) ) {
                    level2NormalView = infalInflater.inflate(R.layout.level2_item_normal, parent, false)
                    viewMap.put(R.layout.level2_item_normal, level2NormalView!!)
                }
                else level2NormalView = viewMap.get(R.layout.level2_item_normal)


                level2NormalView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                level2NormalView!!.findViewById<EditText>(R.id.etSubHeader).hint = headerTitle
                level2NormalView!!.findViewById<EditText>(R.id.etSubHeader).setText(titleValue)
                level2NormalView!!.findViewById<EditText>(R.id.etSubHeader).addTextChangedListener( CustomTextWatcher(level2SubCategory) )
                if (keyBoardType == Constants.KEYBOARD_NUMBER) {
                    level2NormalView!!.findViewById<EditText>(R.id.etSubHeader).inputType = InputType.TYPE_CLASS_NUMBER
                } else if (keyBoardType == Constants.KEYBOARD_SPINNER) {
                    level2NormalView!!.findViewById<EditText>(R.id.etSubHeader).hide()
                    level2NormalView!!.findViewById<Spinner>(R.id.spinnerAccountType).show()

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
                    level2NormalView!!.findViewById<Spinner>(R.id.spinnerAccountType).adapter = arrayAdapter
                    level2NormalView!!.findViewById<Spinner>(R.id.spinnerAccountType).setSelection(spinnerItems.indexOf(titleValue))
                    level2NormalView!!.findViewById<Spinner>(R.id.spinnerAccountType).onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, spinnerItems )
                } else if (keyBoardType == Constants.KEYBOARD_PICKER) {
                    getDateFromPicker(_context, Calendar.getInstance(), object : DateTimeSelectionListener {
                        override fun onDateTimeSelected(selectedDate: Calendar) {
                            level2NormalView!!.findViewById<EditText>(R.id.etSubHeader).setText(getDateMonthYearFormat(selectedDate.time))
                        }
                    })
                }
                childView = level2NormalView

            }
            Constants.LEVEL2_ATTACHMENTS -> {
                if( !viewMap.containsKey(R.layout.level2_atachments) ) {
                    level2AttachmentsView = infalInflater.inflate(R.layout.level2_atachments, parent, false)
                    viewMap.put(R.layout.level2_atachments, level2AttachmentsView!!)
                }
                else level2AttachmentsView = viewMap.get(R.layout.level2_atachments)

                childView = level2AttachmentsView
            }
            Constants.LEVEL_NORMAL_SPINNER -> {
                if( !viewMap.containsKey(R.layout.level2_item_spinner_value) ) {
                    level2NormalSpinnerView = infalInflater.inflate(R.layout.level2_item_spinner_value, parent, false)
                    viewMap.put(R.layout.level2_item_spinner_value, level2NormalSpinnerView!!)
                }
                else level2NormalSpinnerView = viewMap.get(R.layout.level2_item_spinner_value)


                level2NormalSpinnerView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle

                val spinnerItem: Spinner = level2NormalSpinnerView!!.findViewById<View>(R.id.spinnerValue) as Spinner

                when (keyBoardType) {
                // Women Shopping Category

                    Constants.PICKER_WOMEN_NUMERIC_SIZE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenTopsNumericSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(womenTopsNumericSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenTopsNumericSizes )
                    }
                    Constants.PICKER_WOMEN_SIZE_US -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenTopSize)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(womenTopSize.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenTopSize )
                    }
                    Constants.PICKER_WOMENS_DETAILS_SIZE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, sizeCategoryArray)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(sizeCategoryArray.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, sizeCategoryArray )
                    }

                    Constants.PICKER_WOMEN_SHOES -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenShoeSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(womenShoeSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenShoeSizes )
                    }
                    Constants.PICKER_WOMEN_SHOES_WIDTH -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenShoeWidthSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(womenShoeWidthSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenShoeWidthSizes )
                    }

                    Constants.PICKER_WOMEN_ACCESSORIES_BELTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenAccessoriesBelts)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(womenAccessoriesBelts.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenAccessoriesBelts )
                    }
                    Constants.PICKER_WOMEN_ACCESSORIES_HATS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenAccessoriesHats)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(womenAccessoriesHats.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenAccessoriesHats )
                    }
                    Constants.PICKER_WOMEN_ACCESSORIES_GLOVES -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenAccessoriesGloves)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(womenAccessoriesGloves.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenAccessoriesGloves )
                    }
                    Constants.PICKER_WOMEN_ACCESSORIES_TIGHTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenAccessoriesTights)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(womenAccessoriesTights.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, womenAccessoriesTights )
                    }

                // Men's Shopping Category
                    Constants.PICKER_MENS_SIZE_CATEGORY_US -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menSizeCategories)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menSizeCategories.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menSizeCategories )
                    }
                    Constants.PICKER_MENS_SIZE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menTopsSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menTopsSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menTopsSizes )
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_TOPS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menTopsNumericSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menTopsNumericSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menTopsNumericSizes )
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_BOTTOMS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menBottomsNumericSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menBottomsNumericSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menBottomsNumericSizes )
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_JACKETS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menJacketsNumericSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menJacketsNumericSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menJacketsNumericSizes )
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_PANTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menBottomsNumericSizesSuiting)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menBottomsNumericSizesSuiting.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menBottomsNumericSizesSuiting )
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_OUTERWEAR -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menTopsNumericSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menTopsNumericSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menTopsNumericSizes )
                    }
                    Constants.PICKER_MENS_SHOES -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menShoeSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menShoeSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menShoeSizes )
                    }
                    Constants.PICKER_MENS_SHOES_WIDTH -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menShoeWidthSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menShoeWidthSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menShoeWidthSizes )
                    }
                    Constants.PICKER_MENS_BELTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menBelts)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menBelts.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menBelts )
                    }
                    Constants.PICKER_MENS_BELTS_NUMERIC_SIZE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menBottomsNumericBeltsSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menBottomsNumericBeltsSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menBottomsNumericBeltsSizes )
                    }
                    Constants.PICKER_MENS_TIGHTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menHats)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menHats.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menHats )
                    }
                    Constants.PICKER_MENS_GLOVES -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, menGloves)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(menGloves.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, menGloves )
                    }


                // Girls and Boys Shopping Category

                    Constants.PICKER_GIRLS_NUMERIC_SIZE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsNumericSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(girlsNumericSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsNumericSizes )
                    }
                    Constants.PICKER_GIRLS_SHOES_TODDLER -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsShoeSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(girlsShoeSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsShoeSizes )
                    }
                    Constants.PICKER_GIRLS_SHOES_LITTLE_AND_BIG_KID -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsShoesLittleAndBigKidSize)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(girlsShoesLittleAndBigKidSize.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsShoesLittleAndBigKidSize )
                    }
                    Constants.PICKER_GIRLS_SHOES_WIDTH -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsShoeWidthSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(girlsShoeWidthSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsShoeWidthSizes )
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_BELTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsBeltSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(girlsBeltSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsBeltSizes )
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_BELTS_NUMERIC_SIZE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsNumericBeltsSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(girlsNumericBeltsSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsNumericBeltsSizes )
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_HATS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsHatSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(girlsHatSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsHatSizes )
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_GLOVES -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsGlovesSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(girlsGlovesSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsGlovesSizes )
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_TIGHTS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsTightsSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(girlsTightsSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsTightsSizes )
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_SOCKS -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, girlsSocksSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(girlsSocksSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, girlsSocksSizes )
                    }

                // Baby Shopping Category
                    Constants.PICKER_BABY_CLOTHING -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, babyClothings)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(babyClothings.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, babyClothings )
                    }
                    Constants.PICKER_BABY_SHOES -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, babyShoeSizes)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(babyShoeSizes.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, babyShoeSizes )
                    }

                    Constants.BANK_ACCOUNT_TYPE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, accountType)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(accountType.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, accountType )
                    }
                    Constants.CARD_TYPE -> {
                        val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, cardType)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerItem.adapter = arrayAdapter
                        spinnerItem.setSelection(cardType.indexOf(titleValue))
                        spinnerItem.onItemSelectedListener = CustomItemSelectedListener( level2SubCategory, cardType )
                    }


                }

                childView = level2NormalSpinnerView

            }

        }
        return childView!!
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean,
                              convertView: View?, parent: ViewGroup): View {

        groupView = convertView
        val headerTitle = (getGroup(groupPosition) as Level2Category).title
        if (groupView == null) {
            val infalInflater = this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            groupView = infalInflater.inflate(R.layout.list_header, null)
        }


        val lblListHeader = groupView!!
                .findViewById<View>(R.id.lblListHeader) as TextView
        lblListHeader.text = headerTitle

        if (isExpanded) {
            lblListHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_icon_arrow_down, 0)
        } else {
            lblListHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_five, 0)
        }

        return groupView!!
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    private fun getItemType(groupPosition: Int, childPosition: Int): Int {
        return (getChild(groupPosition, childPosition) as Level2SubCategory).type
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return categories[groupPosition].subCategories.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return categories[groupPosition]
    }

    override fun getGroupCount(): Int {
        return categories.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    private fun openStaticLayoutDialog( locationText: EditText) {
        CountryPickerDialog(locationText.context, ICountrySelected {
            strCountry -> locationText.setText(strCountry)
        })
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

    private fun openContactList() {
//        val homeIntent = Intent(NineBxApplication.instance.activityInstance!!, ContactActivity::class.java)
//        NineBxApplication.instance.activityInstance!!.startActivity(homeIntent)
    }

    inner class CustomItemSelectedListener( val level2SubCategory: Level2SubCategory, val selectionArray : Array<String> ) : AdapterView.OnItemSelectedListener {

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            level2SubCategory.titleValue = selectionArray[p2]
            level2CategoryPresenter.setValueToDocument(level2SubCategory)
        }
    }
}

