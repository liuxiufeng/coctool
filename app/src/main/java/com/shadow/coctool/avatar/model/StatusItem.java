package com.shadow.coctool.avatar.model;

/**
 * Created by lxf on 2017/6/26.
 */

public class StatusItem {
    private String showName;

    private String name;

    private boolean isCheck;

    public StatusItem() {

    }

    public StatusItem(String showName, String name) {
        this.showName = showName;
        this.name = name;
        this.isCheck = false;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
