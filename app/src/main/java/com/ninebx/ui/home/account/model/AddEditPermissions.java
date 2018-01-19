package com.ninebx.ui.home.account.model;

/***
 * Created by TechnoBlogger on 18/01/18.
 */

public class AddEditPermissions {
    String id;
    String menuName;

    boolean isView = false;
    boolean isAdd = false;
    boolean isEdit = false;

    public AddEditPermissions(String menuName, boolean isView, boolean isAdd, boolean isEdit) {
        this.menuName = menuName;
        this.isView = isView;
        this.isAdd = isAdd;
        this.isEdit = isEdit;
    }
}
