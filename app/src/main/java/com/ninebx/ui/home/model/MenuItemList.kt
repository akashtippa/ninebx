package com.ninebx.ui.home.model

/***
 * Created by TechnoBlogger on 22/12/17.
 */
class MenuItemList {
    var menuName: String? = null
    var menuImage: Int? = null
    var menuBgImage: Int? = null

    constructor(menuName: String, menuImage: Int, menuBgImage: Int){
        this.menuName = menuName
        this.menuImage = menuImage
        this.menuBgImage = menuBgImage
    }
}