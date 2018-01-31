package com.ninebx.ui.home.baseSubCategories


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.database.DataSetObserver
import android.text.InputType
import android.view.*
import android.view.View.OnTouchListener
import android.widget.*
import com.ninebx.R
import com.ninebx.ui.base.kotlin.hide
import com.ninebx.ui.base.kotlin.show
import com.ninebx.utility.Constants
import com.ninebx.utility.DateTimeSelectionListener
import com.ninebx.utility.getDateFromPicker
import com.ninebx.utility.getDateMonthYearFormat
import java.util.*


/***
 * Created by TechnoBlogger on 23/01/18.
 */

class ExpandableListViewAdapter(private val _context: Context, private val categories: ArrayList<Level2Category>) : BaseExpandableListAdapter() {

    // In this way I'll create all the spinner values, and will use it in this constant, "LEVEL_NORMAL_SPINNER"

    var sizeCategoryArray = arrayOf("Size category (US)", "Regular", "Petite", "Tall")
    var womenTopSize = arrayOf("Size (US)", "XXXS", "XXS", "XS", "S", "M", "L", "XL", "XXL", "XXXL")
    var accountType = arrayOf("Checking", "Savings", "Other")
    var cardType = arrayOf("Card type", "Credit", "Debit")

    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return categories[groupPosition].subCategories[childPosititon]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun getChildView(groupPosition: Int, childPosition: Int,
                              isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var childView = convertView

        //if (childView == null) {

        val infalInflater = this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val level2SubCategory = (getChild(groupPosition, childPosition) as Level2SubCategory)
        val headerTitle = level2SubCategory.title
        val keyBoardType = level2SubCategory.inputType

        when (getItemType(groupPosition, childPosition)) {

            Constants.LEVEL2_LOCATION -> {
                childView = infalInflater.inflate(R.layout.level2_item_location, null)
                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                childView.findViewById<EditText>(R.id.etSubHeader).hint = headerTitle


                childView.findViewById<EditText>(R.id.etSubHeader).setOnTouchListener(OnTouchListener { _, event ->
                    val DRAWABLE_RIGHT = 2

                    if (event.action == MotionEvent.ACTION_UP) {
                        if (event.rawX >= childView!!.findViewById<EditText>(R.id.etSubHeader).right - childView!!.findViewById<EditText>(R.id.etSubHeader).getCompoundDrawables()[DRAWABLE_RIGHT].bounds.width()) {
                            openStaticLayoutDialog()
                            return@OnTouchListener true
                        }
                    }
                    false
                });
            }
            Constants.LEVEL2_PASSWORD -> {
                childView = infalInflater.inflate(R.layout.level2_password, null)
                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                childView.findViewById<EditText>(R.id.etCurrentPassword).hint = headerTitle
            }
            Constants.LEVEL2_RADIO -> {
                childView = infalInflater.inflate(R.layout.level2_radio, null)

                childView.findViewById<CheckBox>(R.id.chkLeft).hint = headerTitle
                childView.findViewById<CheckBox>(R.id.chkRight).hint = level2SubCategory.titleValue

            }

            Constants.LEVEL2_SPINNER -> {
                childView = infalInflater.inflate(R.layout.level2_spinner, null)
                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                childView.findViewById<EditText>(R.id.etSubHeader).hint = headerTitle


                childView.findViewById<Spinner>(R.id.spinnerUsers).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                        val newValue = childView!!.findViewById<Spinner>(R.id.spinnerUsers).getItemAtPosition(position) as String
                        childView!!.findViewById<EditText>(R.id.etSubHeader).setText(newValue)

                    }


                };


            }
            Constants.LEVEL2_SWITCH -> {
                childView = infalInflater.inflate(R.layout.level2_switch, null)
                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
            }
            Constants.LEVEL2_USD -> {
                childView = infalInflater.inflate(R.layout.level2_usd, null)
                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                childView.findViewById<EditText>(R.id.etSubHeader).hint = headerTitle


                childView.findViewById<Spinner>(R.id.spinnerCurrency).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                        val newValue = childView!!.findViewById<Spinner>(R.id.spinnerCurrency).getItemAtPosition(position) as String
                        var test = newValue.split("-")


                    }


                };

            }
            Constants.LEVEL2_EYEGLASS -> {
                childView = infalInflater.inflate(R.layout.item_eye_prescription, null)
            }
            Constants.LEVEL2_NOTES -> {

                childView = infalInflater.inflate(R.layout.level2_notes, null)

                if (headerTitle.equals("")) {
                    childView!!.findViewById<EditText>(R.id.edtNotes).hint = "Notes"
                } else {
                    childView!!.findViewById<EditText>(R.id.edtNotes).hint = headerTitle
                }
            }
            Constants.LEVEL2_PICKER -> {
                childView = infalInflater.inflate(R.layout.level2_item_picker, null)

                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                childView.findViewById<TextView>(R.id.etSubHeader).hint = headerTitle

                childView.findViewById<TextView>(R.id.etSubHeader).setOnClickListener {
                    getDateFromPicker(_context, Calendar.getInstance(), object : DateTimeSelectionListener {
                        override fun onDateTimeSelected(selectedDate: Calendar) {
                            childView!!.findViewById<TextView>(R.id.etSubHeader).text = getDateMonthYearFormat(selectedDate.time)
                        }
                    })
                }
            }

            Constants.LEVEL2_NUMBER -> {
                childView = infalInflater.inflate(R.layout.level2_item_number, null)

                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                childView.findViewById<TextView>(R.id.etSubHeader).hint = headerTitle

                childView.findViewById<EditText>(R.id.etSubHeader).inputType = InputType.TYPE_CLASS_NUMBER
            }
            Constants.LEVEL2_NORMAL -> {
                childView = infalInflater.inflate(R.layout.level2_item_normal, null)

                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                childView.findViewById<EditText>(R.id.etSubHeader).hint = headerTitle


                if (keyBoardType == Constants.KEYBOARD_NUMBER) {
                    childView = infalInflater.inflate(R.layout.level2_item_number, null)
                    childView.findViewById<EditText>(R.id.etSubHeader).inputType = InputType.TYPE_CLASS_NUMBER
                } else if (keyBoardType == Constants.KEYBOARD_SPINNER) {
                    childView.findViewById<EditText>(R.id.etSubHeader).hide()
                    childView.findViewById<Spinner>(R.id.spinnerAccountType).show()


                    childView.findViewById<Spinner>(R.id.spinnerAccountType).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {

                        }

                        override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
//                            val newValue = childView!!.findViewById<Spinner>(R.id.spinnerAccountType).getItemAtPosition(position) as String

                        }


                    }

                } else if (keyBoardType == Constants.KEYBOARD_PICKER) {
                    getDateFromPicker(_context, Calendar.getInstance(), object : DateTimeSelectionListener {
                        override fun onDateTimeSelected(selectedDate: Calendar) {
                            childView!!.findViewById<EditText>(R.id.etSubHeader).setText(getDateMonthYearFormat(selectedDate.time))
                        }
                    })
                }

            }
            Constants.LEVEL2_ATTACHMENTS -> {
                childView = infalInflater.inflate(R.layout.level2_atachments, null)
            }
            Constants.LEVEL_NORMAL_SPINNER -> {

                childView = infalInflater.inflate(R.layout.level2_item_spinner_value, null)

                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle

                val spinnerItem: Spinner = childView.findViewById<View>(R.id.spinnerValue) as Spinner

                if (headerTitle == "Size category(US)") {
                    val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, sizeCategoryArray)
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerItem.adapter = arrayAdapter
                } else if (headerTitle == "Size (US)") {
                    val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, womenTopSize)
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerItem.adapter = arrayAdapter
                } else if (headerTitle == "Account type") {
                    val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, accountType)
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerItem.adapter = arrayAdapter
                } else if (headerTitle == "Card type") {
                    val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_spinner_item, cardType)
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerItem.adapter = arrayAdapter
                }


            }

        }
        return childView!!
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean,
                              convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val headerTitle = (getGroup(groupPosition) as Level2Category).title
        if (convertView == null) {
            val infalInflater = this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.list_header, null)
        }


        val lblListHeader = convertView!!
                .findViewById<View>(R.id.lblListHeader) as TextView
        lblListHeader.text = headerTitle

        if (isExpanded) {
            lblListHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_five, 0);
        } else {
            lblListHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_icon_arrow_down, 0);
        }

        return convertView
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

    override fun registerDataSetObserver(observer: DataSetObserver) {
        super.registerDataSetObserver(observer)
    }

    private fun openStaticLayoutDialog() {
        val dialog = Dialog(_context, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.setContentView(R.layout.fragment_search_location)
        dialog.show()

        val imgBack = dialog.findViewById<View>(R.id.ivBack) as ImageView
        imgBack.setOnClickListener {
            dialog.cancel()
        }
    }


}
