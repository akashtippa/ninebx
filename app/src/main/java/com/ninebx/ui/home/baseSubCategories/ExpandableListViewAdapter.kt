package com.ninebx.ui.home.baseSubCategories


import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.ninebx.R
import com.ninebx.utility.Constants


/***
 * Created by TechnoBlogger on 23/01/18.
 */

class ExpandableListViewAdapter(private val _context: Context, private val categories: ArrayList<Level2Category>) : BaseExpandableListAdapter() {

    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return categories[groupPosition].subCategories[childPosititon]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int,
                              isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var childView = convertView

        //if (childView == null) {

        val infalInflater = this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val level2SubCategory = (getChild(groupPosition, childPosition) as Level2SubCategory)
        val headerTitle = level2SubCategory.title

        /**
         * I have a Expandable List View, with different Child Layout.
         * Suppose I clicked the GroupView of 3rd Position, and its respective child Layout pop up.
         * So far so good.
         * But now if I click, say group position 2nd, then its child layout will pop out, but the child layout of previous group is also adding.
         * Any Help.
         */


        when (getItemType(groupPosition, childPosition)) {

            Constants.LEVEL2_LOCATION -> {
                childView = infalInflater.inflate(R.layout.level2_item_location, null)
                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                childView.findViewById<EditText>(R.id.etSubHeader).hint = headerTitle
                childView.findViewById<EditText>(R.id.etSubHeader).setText(level2SubCategory.titleValue)
            }
            Constants.LEVEL2_PASSWORD -> {
                childView = infalInflater.inflate(R.layout.level2_password, null)
                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                childView.findViewById<EditText>(R.id.etCurrentPassword).hint = headerTitle
//                    convertView.findViewById<EditText>(R.id.etCurrentPassword).setText(level2SubCategory.titleValue)
            }
            Constants.LEVEL2_RADIO -> {
                childView = infalInflater.inflate(R.layout.level2_radio, null)

                childView!!.findViewById<CheckBox>(R.id.chkLeft).hint = headerTitle
                childView.findViewById<CheckBox>(R.id.chkRight).hint = level2SubCategory.titleValue

            }
            Constants.LEVEL2_SPINNER -> {
                childView = infalInflater.inflate(R.layout.level2_spinner, null)
                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                childView.findViewById<EditText>(R.id.etSubHeader).hint = headerTitle
//                    convertView.findViewById<EditText>(R.id.etSubHeader).setText(level2SubCategory.titleValue)

            }
            Constants.LEVEL2_SWITCH -> {
                childView = infalInflater.inflate(R.layout.level2_switch, null)
                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
            }
            Constants.LEVEL2_USD -> {
                childView = infalInflater.inflate(R.layout.level2_usd, null)
                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                childView.findViewById<EditText>(R.id.etSubHeader).hint = headerTitle
//                    convertView.findViewById<EditText>(R.id.etSubHeader).setText(level2SubCategory.titleValue)
            }
            Constants.LEVEL2_NOTES -> {
                childView = infalInflater.inflate(R.layout.level2_notes, null)
            }
            Constants.LEVEL2_NORMAL -> {
                childView = infalInflater.inflate(R.layout.level2_item_normal, null)

                childView!!.findViewById<TextView>(R.id.txtHeader).text = headerTitle
                childView.findViewById<EditText>(R.id.etSubHeader).hint = headerTitle
                childView.findViewById<EditText>(R.id.etSubHeader).setText(level2SubCategory.titleValue)

            }
            Constants.LEVEL2_ATTACHMENTS -> {
                childView = infalInflater.inflate(R.layout.level2_atachments, null)
            }

        }
        //}
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
            lblListHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_icon_arrow_down, 0);
        } else {
            lblListHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_five, 0);
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
        /* used to make the notifyDataSetChanged() method work */
        super.registerDataSetObserver(observer)
    }
}
