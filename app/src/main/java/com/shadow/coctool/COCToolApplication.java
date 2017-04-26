package com.shadow.coctool;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

/**
 * Created by lxf on 2017/4/26.
 */

public class COCToolApplication extends Application {

    @Override
    public void onCreate() {
        Hawk.init(getApplicationContext()).build();
    }
}
