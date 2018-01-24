package com.ninebx.ui.home.baseSubCategories


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
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
        var convertView = convertView

        if (convertView == null) {
            val infalInflater = this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val headerTitle = (getChild(groupPosition, childPosition) as Level2SubCategory).title

            when (getItemType(groupPosition, childPosition)) {

                Constants.LEVEL2_LOCATION -> {
                    convertView = infalInflater.inflate(R.layout.level2_item_location, null)
                    val lblListHeader = convertView!!
                            .findViewById<View>(R.id.txtHeader) as TextView
                    lblListHeader.text = headerTitle
                }
                Constants.LEVEL2_PASSWORD -> {
                    convertView = infalInflater.inflate(R.layout.level2_password, null)

                }
                Constants.LEVEL2_RADIO -> {
                    convertView = infalInflater.inflate(R.layout.level2_radio, null)

                }
                Constants.LEVEL2_SPINNER -> {
                    convertView = infalInflater.inflate(R.layout.level2_spinner, null)

                }
                Constants.LEVEL2_SWITCH -> {
                    convertView = infalInflater.inflate(R.layout.level2_switch, null)

                }
                Constants.LEVEL2_USD -> {
                    convertView = infalInflater.inflate(R.layout.level2_usd, null)

                }
                Constants.LEVEL2_NOTES -> {
                    convertView = infalInflater.inflate(R.layout.level2_notes, null)

                }
                Constants.LEVEL2_NORMAL -> {
                    convertView = infalInflater.inflate(R.layout.level2_item_normal, null)

                }
                Constants.LEVEL2_ATTACHMENTS -> {
                    convertView = infalInflater.inflate(R.layout.level2_atachments, null)
                }

            }
        }
        return convertView!!
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
}
