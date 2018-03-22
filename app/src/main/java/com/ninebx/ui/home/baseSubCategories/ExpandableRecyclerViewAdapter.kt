package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.widget.PopupWindowCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.*
import android.widget.*
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.ui.base.realm.decrypted.DecryptedFinancial
import com.ninebx.ui.base.realm.decrypted.DecryptedMember
import com.ninebx.ui.home.account.interfaces.ICountrySelected
import com.ninebx.utility.*
import com.ninebx.utility.countryPicker.CountryPickerDialog
import java.util.*

/**
 * Created by Alok on 14/03/18.
 */
@SuppressLint("ClickableViewAccessibility", "SetTextI18n")
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
            Constants.LEVEL2_TIMEPICKER -> {
                return LEVEL2_TIMEPICKERViewHolder(inflater.inflate(R.layout.level2_time_picker, parent, false))
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
                locationViewHolder.spinnerCurrency.setText(titleValue)
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
                locationViewHolder.etSubHeader.addTextChangedListener(CustomTextWatcher(level2SubCategory))
                locationViewHolder.etSubHeader.setOnClickListener {
                    getDateFromPicker(_context, Calendar.getInstance(), object : DateTimeSelectionListener {
                        override fun onDateTimeSelected(selectedDate: Calendar) {
                            locationViewHolder.etSubHeader.text = getDateMonthYearFormat(selectedDate.time)
                        }
                    })
                }
            }
            Constants.LEVEL2_TIMEPICKER -> {
                val locationViewHolder : LEVEL2_TIMEPICKERViewHolder = holder as LEVEL2_TIMEPICKERViewHolder

                locationViewHolder.txtHeader.text = headerTitle
                locationViewHolder.etSubHeader.hint = headerTitle
                locationViewHolder.etSubHeader.setText(titleValue)
                locationViewHolder.etSubHeader.addTextChangedListener(CustomTextWatcher(level2SubCategory))
                locationViewHolder.etSubHeader.setOnClickListener{
                    getTimeFromPicker(_context, Calendar.getInstance(), object : DateTimeSelectionListener {
                        override fun onDateTimeSelected(selectedDate: Calendar) {
                            locationViewHolder.etSubHeader.text = getTimeFormat(selectedDate.time)
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
                locationViewHolder.spinnerAccountType.setText(titleValue)

            }
            Constants.LEVEL2_ATTACHMENTS -> {
                val locationViewHolder : LEVEL2_ATTACHMENTSViewHolder = holder as LEVEL2_ATTACHMENTSViewHolder

            }
            Constants.LEVEL_NORMAL_SPINNER -> {
                val locationViewHolder : LEVEL_NORMAL_SPINNERViewHolder = holder as LEVEL_NORMAL_SPINNERViewHolder
                locationViewHolder.txtHeader.text = headerTitle
                locationViewHolder.spinnerItem.setText((titleValue))
               

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
    inner class LEVEL2_SPINNERViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {
        override fun onClick(p0: View?) {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                val item = categories[position]
                val keyBoardType = item.inputType
                if (keyBoardType == Constants.CONTACT_SPINNER) {
                    openContactList()
                } else {
                    showMemberPopup( spinnerUsers, membersList )
                }
            }
        }


        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val etSubHeader = itemView.findViewById<EditText>(R.id.etSubHeader)
        val spinnerUsers = itemView.findViewById<EditText>(R.id.spinnerUsers)
        init {

            txtHeader.isEnabled = isEditMode
            etSubHeader.isEnabled = isEditMode
            spinnerUsers.isEnabled = isEditMode
            spinnerUsers.setOnClickListener(this)


        }



    } // 13

    private fun showMemberPopup(optionEditText: EditText?, optionsList: ArrayList<DecryptedMember>) {


        val popupView = LayoutInflater.from(_context).inflate(R.layout.popup_window_list_layout, null)
        //popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(_context, R.color.white))

        //popupWindow.contentView = popupView
        //popupWindow.isOutsideTouchable = true
        val optionsListView = popupView.findViewById<ListView>(R.id.optionsListView)
        val arrayAdapter = ArrayAdapter(_context, R.layout.txt_usd, optionsList)
        optionsListView.adapter = arrayAdapter
        optionsListView.onItemClickListener = AdapterView.OnItemClickListener { p0, p1, p2, p3 ->
            val newValue =  optionsList[p2] as String
            optionEditText!!.setText(newValue)
        }
        val popupWindow = AlertDialog.Builder(_context).setView(popupView).create()
        popupWindow.show()
        //PopupWindowCompat.showAsDropDown(popupWindow, optionEditText!!, optionEditText.x.toInt(), optionEditText.y.toInt(), Gravity.BOTTOM)

    }

    private fun showPopup( level2SubCategory: Level2SubCategory, optionEditText: TextView?, optionsList: Array<String>) {

        val popupView = LayoutInflater.from(_context).inflate(R.layout.popup_window_list_layout, null)
        //popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(_context, R.color.white))

        //popupWindow.contentView = popupView
        //popupWindow.isOutsideTouchable = true
        val optionsListView = popupView.findViewById<ListView>(R.id.optionsListView)
        val arrayAdapter = ArrayAdapter(_context, R.layout.txt_usd, optionsList)
        optionsListView.adapter = arrayAdapter

        val popupWindow = AlertDialog.Builder(_context).setView(popupView).create()

        optionsListView.onItemClickListener = CustomItemSelectedListener( level2SubCategory, optionsList, optionEditText, popupWindow )
        popupWindow.show()

        //PopupWindowCompat.showAsDropDown(popupWindow, optionEditText!!, optionEditText.x.toInt(), optionEditText.y.toInt(), Gravity.BOTTOM)

    }

    inner class LEVEL2_SWITCHViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val switchView = itemView.findViewById<Switch>(R.id.switchView)

        init {

            txtHeader.isEnabled = isEditMode
            switchView.isEnabled = isEditMode


            // childView = level2SwitchView
        }
    } // 14
    inner class LEVEL2_USDViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {
        override fun onClick(p0: View?) {
            val postion = adapterPosition
            if( postion != RecyclerView.NO_POSITION ) {
                val level2SubCategory = categories[postion]
                showPopup(level2SubCategory, spinnerCurrency, currencyType)
            }
        }

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val etSubHeader = itemView.findViewById<EditText>(R.id.etSubHeader)
        val spinnerCurrency: TextView = itemView.findViewById<TextView>(R.id.spinnerCurrency)
        init {

            txtHeader.isEnabled = isEditMode
            etSubHeader.isEnabled = isEditMode
            spinnerCurrency.isEnabled = isEditMode
            spinnerCurrency.setOnClickListener(this)
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
    inner class LEVEL2_NORMALViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {
        override fun onClick(p0: View?) {

            val postion = adapterPosition
            if( postion != RecyclerView.NO_POSITION ) {
                val level2SubCategory = categories[postion]
                val keyBoardType = level2SubCategory.inputType
                if (keyBoardType == Constants.KEYBOARD_NUMBER) {
                    etSubHeader.inputType = InputType.TYPE_CLASS_NUMBER
                } else if (keyBoardType == Constants.KEYBOARD_SPINNER) {
                    etSubHeader.hide()
                    spinnerAccountType.show()

                    val spinnerItems = when (classType) {
                        DecryptedFinancial::class.java.simpleName -> {
                            accountType
                        }
                        else -> {
                            womenTopsNumericSizes
                        }
                    }
                    showPopup(level2SubCategory, spinnerAccountType, spinnerItems)
                    
                    val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, spinnerItems)
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerAccountType.setText(level2SubCategory.titleValue)
                } else if (keyBoardType == Constants.KEYBOARD_PICKER) {
                    getDateFromPicker(_context, Calendar.getInstance(), object : DateTimeSelectionListener {
                        override fun onDateTimeSelected(selectedDate: Calendar) {
                            (etSubHeader).setText(getDateMonthYearFormat(selectedDate.time))
                        }
                    })
                } else if (keyBoardType == Constants.KEYBOARD_TIMEPICKER){
                    getTimeFromPicker(_context, Calendar.getInstance(), object : DateTimeSelectionListener{
                        override fun onDateTimeSelected(selectedDate: Calendar) {
                            (etSubHeader).setText(getTimeFormat(selectedDate.time))
                        }
                    })
                }
            }
        }

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val etSubHeader = itemView.findViewById<EditText>(R.id.etSubHeader)
        val spinnerAccountType = itemView.findViewById<TextView>(R.id.spinnerAccountType)

        init {

            txtHeader.isEnabled = isEditMode
            etSubHeader.isEnabled = isEditMode
            spinnerAccountType.isEnabled = isEditMode
            spinnerAccountType.setOnClickListener(this)
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
    inner class LEVEL2_TIMEPICKERViewHolder( itemView: View ) : RecyclerView.ViewHolder( itemView ){
        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val etSubHeader = itemView.findViewById<TextView>(R.id.etSubHeader)

        init {

            txtHeader.isEnabled = isEditMode
            etSubHeader.isEnabled = isEditMode
            // childView = level2PickerView
        }
    }
    inner class LEVEL2_NUMBERViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ) {

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val etSubHeader = itemView.findViewById<TextView>(R.id.etSubHeader)

        init {


            txtHeader.isEnabled = isEditMode
            etSubHeader.isEnabled = isEditMode

            // childView = level2NumberView
        }
    } // 20
    inner class LEVEL_NORMAL_SPINNERViewHolder( itemView : View ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {
        override fun onClick(p0: View?) {

            val postion = adapterPosition
            if( postion != RecyclerView.NO_POSITION ) {
                val level2SubCategory = categories[postion]
                val keyBoardType = level2SubCategory.inputType
                val titleValue = level2SubCategory.titleValue

                val spinnerItems = when (keyBoardType) {

                    Constants.BANK_ACCOUNT_TYPE -> {
                        accountType
                    }

                    Constants.PICKER_WOMEN_NUMERIC_SIZE -> {
                        ( womenTopsNumericSizes)
                    }
                    Constants.PICKER_WOMEN_SIZE_US -> {
                        ( womenTopSize)
                    }
                    Constants.PICKER_WOMENS_DETAILS_SIZE -> {
                        ( sizeCategoryArray)
                    }

                    Constants.PICKER_WOMEN_SHOES -> {
                        ( womenShoeSizes)
                    }
                    Constants.PICKER_WOMEN_SHOES_WIDTH -> {
                        ( womenShoeWidthSizes)
                    }

                    Constants.PICKER_WOMEN_ACCESSORIES_BELTS -> {
                        ( womenAccessoriesBelts)
                    }
                    Constants.PICKER_WOMEN_ACCESSORIES_HATS -> {
                        ( womenAccessoriesHats)
                    }
                    Constants.PICKER_WOMEN_ACCESSORIES_GLOVES -> {
                        ( womenAccessoriesGloves)
                    }
                    Constants.PICKER_WOMEN_ACCESSORIES_TIGHTS -> {
                        ( womenAccessoriesTights)
                    }
                // Men's Shopping Category
                    Constants.PICKER_MENS_SIZE_CATEGORY_US -> {
                        ( menSizeCategories)
                    }
                    Constants.PICKER_MENS_SIZE -> {
                        ( menTopsSizes)
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_TOPS -> {
                        ( menTopsNumericSizes)
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_BOTTOMS -> {
                        ( menBottomsNumericSizes)
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_JACKETS -> {
                        ( menJacketsNumericSizes)
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_PANTS -> {
                        ( menBottomsNumericSizesSuiting)
                    }
                    Constants.PICKER_MENS_NUMERIC_SIZE_SUITING_OUTERWEAR -> {
                        ( menTopsNumericSizes)
                    }
                    Constants.PICKER_MENS_SHOES -> {
                        ( menShoeSizes)
                    }
                    Constants.PICKER_MENS_SHOES_WIDTH -> {
                        ( menShoeWidthSizes)
                    }
                    Constants.PICKER_MENS_BELTS -> {
                        ( menBelts)
                    }
                    Constants.PICKER_MENS_BELTS_NUMERIC_SIZE -> {
                        ( menBottomsNumericBeltsSizes)
                    }
                    Constants.PICKER_MENS_TIGHTS -> {
                        ( menHats)
                    }
                    Constants.PICKER_MENS_GLOVES -> {
                        ( menGloves)
                    }


                // Girls and Boys Shopping Category

                    Constants.PICKER_GIRLS_NUMERIC_SIZE -> {
                        ( girlsNumericSizes)
                    }
                    Constants.PICKER_GIRLS_SHOES_TODDLER -> {
                        ( girlsShoeSizes)
                    }
                    Constants.PICKER_GIRLS_SHOES_LITTLE_AND_BIG_KID -> {
                        ( girlsShoesLittleAndBigKidSize)
                    }
                    Constants.PICKER_GIRLS_SHOES_WIDTH -> {
                        ( girlsShoeWidthSizes)
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_BELTS -> {
                        ( girlsBeltSizes)
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_BELTS_NUMERIC_SIZE -> {
                        ( girlsNumericBeltsSizes)
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_HATS -> {
                        ( girlsHatSizes)
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_GLOVES -> {
                        ( girlsGlovesSizes)
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_TIGHTS -> {
                        ( girlsTightsSizes)
                    }
                    Constants.PICKER_GIRLS_ACCESSORIES_SOCKS -> {
                        ( girlsSocksSizes)
                    }

                // Baby Shopping Category
                    Constants.PICKER_BABY_CLOTHING -> {
                        ( babyClothings)
                    }
                    Constants.PICKER_BABY_SHOES -> {
                        ( babyShoeSizes)
                    }

                    Constants.OTHER_ACCOUNT_TYPE -> {
                        ( othersAccountTypeOptions)
                    }
                    Constants.CARD_TYPE -> {
                        ( cardType)
                    }

                    /*Constants.GENDER*/ else -> {
                        (gender)
                    }

                }

                showPopup(level2SubCategory, spinnerItem, spinnerItems)
                
            }
        }

        val txtHeader = itemView.findViewById<TextView>(R.id.txtHeader)
        val spinnerItem: TextView = itemView.findViewById<TextView>(R.id.spinnerValue)
        init {


            txtHeader.isEnabled = isEditMode
            spinnerItem.isEnabled = isEditMode
            spinnerItem.setOnClickListener(this)

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

    inner class CustomItemSelectedListener(private val level2SubCategory: Level2SubCategory,
                                           private val selectionArray : Array<String>,
                                           private val optionEditText: TextView?,
                                           private val popupWindow: AlertDialog ) : AdapterView.OnItemClickListener {

        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            level2SubCategory.titleValue = selectionArray[p2]
            level2CategoryPresenter.setValueToDocument(level2SubCategory)
            optionEditText!!.setText(level2SubCategory.titleValue)
            popupWindow.dismiss()
        }

    }

    private fun openStaticLayoutDialog( locationText: EditText) {

        CountryPickerDialog(locationText.context, ICountrySelected {
            strCountry -> locationText.setText(strCountry)
        })

    }

    private fun openContactList() {
    }
}