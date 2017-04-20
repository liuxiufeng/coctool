package com.shadow.coctool.main.model;

import android.content.Intent;

/**
 * Created by lxf on 2017/4/20.
 */

public class IconMenu {
    private int resId;

    private Intent intent;

    private String text;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
