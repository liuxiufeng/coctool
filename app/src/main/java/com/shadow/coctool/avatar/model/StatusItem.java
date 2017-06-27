package com.shadow.coctool.avatar.model;

/**
 * Created by lxf on 2017/6/26.
 */

public class StatusItem {
    private String showName;

    private String name;

    private String status;

    private boolean isCheck;

    public StatusItem() {

    }

    public StatusItem(String showName, String name, int status) {
        this.showName = showName;
        this.name = name;
        this.status = String.valueOf(status);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
