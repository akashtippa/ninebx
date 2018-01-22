package com.ninebx.ui.home.account.model;

/***
 * Created by TechnoBlogger on 18/01/18.
 */

public class AddEditPermissions {
    private int background;
    private int menuImage;
    private String menuName;

    private boolean isView = false;
    private boolean isAdd = false;
    private boolean isEdit = false;

    public AddEditPermissions(int background, int menuImage, String menuName, boolean isView, boolean isAdd, boolean isEdit) {
        this.background = background;
        this.menuImage = menuImage;
        this.menuName = menuName;
        this.isView = isView;
        this.isAdd = isAdd;
        this.isEdit = isEdit;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public boolean isView() {
        return isView;
    }

    public void setView(boolean view) {
        isView = view;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
