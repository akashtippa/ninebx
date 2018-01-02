package com.ninebx.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.ninebx.R
import com.ninebx.ui.home.model.MenuItemList
import kotlinx.android.synthetic.main.row_menu.view.*

/***
 * Created by TechnoBlogger on 22/12/17.
 */
class MenuAdapterList : BaseAdapter {

    var menuList = ArrayList<MenuItemList>()
    var context: Context? = null

    constructor(context: Context, menuList: ArrayList<MenuItemList>) : super() {
        this.context = context
        this.menuList = menuList
    }

    override fun getCount(): Int {
        return menuList.size
    }

    override fun getItem(position: Int): Any {
        return menuList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val menu = this.menuList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var menuView = inflator.inflate(R.layout.row_menu, null)

        menuView.menuTitle.text = menu.menuName!!
        menuView.menuTitle.setCompoundDrawablesWithIntrinsicBounds(0, menu.menuImage!!, 0, 0)
        menuView.layoutBg.setBackgroundResource(menu.menuBgImage!!)

        return menuView
    }

}